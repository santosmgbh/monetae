package br.com.boleuti.monetae.model.chart;

import java.util.List;


public class Serie {


	private int id;
	private String title;
	private List<String> labels;
	private List<String> valores;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public List<String> getValores() {
		return valores;
	}
	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	
	
	
	  
}
