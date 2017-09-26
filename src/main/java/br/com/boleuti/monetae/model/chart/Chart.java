package br.com.boleuti.monetae.model.chart;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Chart {

	private String label;
	private List<String> seriesLabel;
	private List<Serie> series;
	

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<Serie> getSeries() {
		return series;
	}
	public void setSeries(List<Serie> series) {
		this.series = series;
	}
	
	public List<String> getSeriesLabel() {
		return seriesLabel;
	}
	public void setSeriesLabel(List<String> seriesLabel) {
		this.seriesLabel = seriesLabel;
	}
	public void addSerie(Serie serie){
		if(this.series == null)
			this.series = new ArrayList<Serie>();
		this.series.add(serie);
	}
	

}
