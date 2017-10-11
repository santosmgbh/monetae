'use strict';

app
		.controller(
				'ImportacaoController',
				[
						'FluxoService',
						'CentroCustoService',
						'LancamentoService',
						'ImportacaoService',
						'$scope',
						'$location',
						'$timeout',
						function(FluxoService, CentroCustoService,
								LancamentoService, ImportacaoService, $scope,
								$location, $timeout) {

							var ng = $scope;

							CentroCustoService.loadAll().then(function() {
								ng.centrosCustos = CentroCustoService.getAll();
							}, function(error) {
							});
							FluxoService.loadAll().then(function() {
								ng.fluxos = FluxoService.getAll();
							}, function(error) {
							});

							ng.lancamentosImportados = [];
							var FORMATO_SEM_COLUNA_VALOR = 0;
							var FORMATO_COM_COLUNA_VALOR = 1;
							ng.formatoImportacao = FORMATO_COM_COLUNA_VALOR;

							var fileInput = document
									.getElementById('importLancamento');
							// parse when file loaded, then print the result to
							// console
							fileInput.addEventListener('change', function(e) {
								var file = e.target.files[0];
								ng.parseFile(file);
							});

							ng.parseFile = function(file) {
								Papa
										.parse(
												file,
												{
													delimiter : ";",
													encoding : "default",
													header : true,
													dynamicTyping : true,
													complete : function(results) {
														$timeout(
																function() {
																	var dadosImportacao = results.data;
																	ng.colunasImportacao = results.meta.fields;

																	var contemColunaValor = ng.colunasImportacao
																			.indexOf('VALOR') != -1;
																	if (contemColunaValor) {
																		ng.formatoImportacao = FORMATO_COM_COLUNA_VALOR;
																	} else {
																		ng.formatoImportacao = FORMATO_SEM_COLUNA_VALOR;
																	}

																	ng
																			.verificaCamposImportacao(dadosImportacao);

																}, 50);

													}

												});
							}

							ng.changeEdit = function(component) {
								console.log(component);
							}

							ng.verificaCamposImportacao = function(
									dadosImportacao) {
								for ( var i in dadosImportacao) {
									var registro = dadosImportacao[i];
									var lancamento = {};
									lancamento.descricao = registro['DESCRICAO'];
									lancamento.tipoLancamento = registro['VALOR'];
									lancamento.data = registro['DATA'];
									lancamento.valor = registro['VALOR'];
									lancamento.parcelas = registro['PARCELAS'];
									lancamento.centroCusto = registro['ID_CENTRO_CUSTO'];
									lancamento.fluxo = registro['ID_FLUXO'];
									lancamento.user = registro['ID_USER'];

									if (ng.isCamposLancamentoValido(lancamento)) {
										lancamento = ng
												.formatarCamposLancamento(registro);
										ng.lancamentosImportados.push({
											valido : true,
											lancamento : lancamento
										});
									} else {
										lancamento = ng
												.formatarCamposLancamento(registro);
										ng.lancamentosImportados.push({
											valido : false,
											lancamento : lancamento
										});
									}

								}
								debugger;
							}
							
							ng.openClose = function (index) {
							      if(ng.selectedValue == index){
							        ng.selectedValue = -1;
							      }else{
							      	 ng.selectedValue = index;
							      }
							    }

							ng.isCamposLancamentoValido = function(lancamento) {
								if (lancamento.descricao
										&& lancamento.descricao != "")
									if (lancamento.tipoLancamento == 1
											|| lancamento.tipoLancamento == 2)
										if (lancamento.data)
											if (lancamento.valor
													|| lancamento.valor
															.indexOf(",") == -1)
												return true;
								return false;
							}

							ng.formatarCamposLancamento = function(registro) {
								var lancamento = {
									descricao : registro['DESCRICAO'],
									tipoLancamento : ng
											.getTipoLancamento(registro['VALOR']),
									data : registro['DATA'] ? ng
											.formataData(registro['DATA'])
											: null,
									valor : ng.getValor(registro),
									parcelas : registro['PARCELAS'] ? registro['PARCELAS']
											: 1,
									centroCusto : ng.getCentroCusto(registro),
									fluxo : ng.getFluxo(registro),
									user : registro['ID_USER']
								};
								return lancamento;
							}

							ng.importarLancamentos = function() {
								var lancamentos = [];
								for ( var i in ng.lancamentosImportados) {
									var registro = ng.lancamentosImportados[i].lancamento;
									var lancamento = ng
											.formatarCamposLancamento(registro);
									lancamentos.push(lancamento);
								}
								ng.loading(true);
								ImportacaoService
										.importarLancamentos(
												lancamentos,
												function() {
													LancamentoService.loadAll();
													ng.loading(false);
													ng
															.alert(
																	ng.ALERT_SUCCESS,
																	"Lançamentos importados com sucesso!");
													$location
															.path("/lancamento");
												},
												function(response) {
													ng.loading(false);
													console.log(response);
													ng.alert(ng.ALERT_DANGER,
															"Problema ao importar dados! \n"
																	+ response);
												});

							}

							ng.getTipoLancamento = function(valor) {
								return parseInt(valor) > 0 ? 1 : 2;
							}

							ng.getValor = function(registro) {
								var valores = ng.valuesToArray(registro);
								if (ng.formatoImportacao == FORMATO_SEM_COLUNA_VALOR) {
									if (valores) {
										var c = 0;
										for (c in valores) {
											if (ng.colunasImportacao[c] != ('DESCRICAO')
													&& ng.colunasImportacao[c] != ('VALOR')
													&& ng.colunasImportacao[c] != ('DATA')
													&& ng.colunasImportacao[c] != ('PARCELAS')
													&& ng.colunasImportacao[c] != ('ID_CENTRO_CUSTO')
													&& ng.colunasImportacao[c] != ('ID_FLUXO')
													&& ng.colunasImportacao[c] != ('ID_USER')) {

												if (!ng.colunasImportacao[c]) {
													continue;
												}
												var nomeCentroCusto = ng.colunasImportacao[c];
												var cc = 0;
												for (cc in ng.centrosCustos) {
													if (ng.centrosCustos[cc].nome
															.toUpperCase() == nomeCentroCusto
															.toUpperCase()) {
														if (valores[c]) {
															return ng
																	.formataValor(valores[c]);
														}
													}
												}

											}
										}
									}
								} else {
									return ng.formataValor(valores[c]);
								}
							}

							ng.formataValor = function(valor) {
								valor = valor + "";
								if (valor && valor.indexOf(".")
										&& valor.indexOf(",") != -1) {
									valor = valor.replace(".", "");
								}
								if (valor && parseInt(valor) < 0) {
									valor = (valor + "").replace("-", "");
								}
								if (valor) {
									valor = (valor + "").replace(",", ".")
								}
								return valor;
							}

							ng.formataData = function(data) {
								var d = new Date(data);
								return d;
							}

							ng.getFluxo = function(registro) {
								if (registro['ID_FLUXO']) {
									for (cc in ng.centrosCustos) {
										if (ng.centrosCustos[cc].nome
												.toUpperCase() == registro['ID_FLUXO']) {
											if (valores[c]) {
												return ng.centrosCustos[cc];
											}
										}
									}
								}
							}

							ng.getCentroCusto = function(registro) {
								var valores = ng.valuesToArray(registro);
								if (ng.formatoImportacao == FORMATO_SEM_COLUNA_VALOR) {
									if (valores) {
										var c = 0;
										for (c in valores) {
											if (ng.colunasImportacao[c] != ('DESCRICAO')
													&& ng.colunasImportacao[c] != ('VALOR')
													&& ng.colunasImportacao[c] != ('DATA')
													&& ng.colunasImportacao[c] != ('PARCELAS')
													&& ng.colunasImportacao[c] != ('ID_CENTRO_CUSTO')
													&& ng.colunasImportacao[c] != ('ID_FLUXO')
													&& ng.colunasImportacao[c] != ('ID_USER')) {

												if (!ng.colunasImportacao[c]) {
													continue;
												}
												var nomeCentroCusto = ng.colunasImportacao[c];
												var cc = 0;
												for (cc in ng.centrosCustos) {
													if (ng.centrosCustos[cc].nome
															.toUpperCase() == nomeCentroCusto
															.toUpperCase()) {
														if (valores[c]) {
															return ng.centrosCustos[cc];
														}
													}
												}

											}
										}
									}
								} else {
									if (registro['ID_CENTRO_CUSTO']) {
										for (cc in ng.centrosCustos) {
											if (ng.centrosCustos[cc].nome
													.toUpperCase() == registro['ID_CENTRO_CUSTO']) {
												if (valores[c]) {
													return ng.centrosCustos[cc];
												}
											}
										}
									}
								}
								return null;
							}

							ng.valuesToArray = function(obj) {
								return Object.keys(obj).map(function(key) {
									return obj[key];
								});
							}

							ng.getClassValidacao = function(valido) {
								return valido ? 'item-importacao-ok'
										: 'item-importacao-has-error';
							}

						}

				]);