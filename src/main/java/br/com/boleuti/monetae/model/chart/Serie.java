package br.com.boleuti.monetae.model.chart;

import java.util.ArrayList;
import java.util.List;


public class Serie {


	private int id;
	private String title;
	private String color;
	private List<String> labels;
	private List<Object> valores;
	
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
	public List<Object> getValores() {
		return valores;
	}
	public void setValores(List<Object> valores) {
		this.valores = valores;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void addLabel(String label) {
		if(labels == null)
			labels = new ArrayList();
		labels.add(label);		
	}
	public void addValor(Object valor) {
		if(valores == null)
			valores = new ArrayList();
		valores.add(valor);
	}

	
	
	
	  
}
