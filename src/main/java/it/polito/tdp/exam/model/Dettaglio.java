package it.polito.tdp.exam.model;

import java.util.Objects;

public class Dettaglio implements Comparable<Dettaglio>{
	private Integer anno;
	private Double peso;
	public Dettaglio(Integer anno, Double peso) {
		super();
		this.anno = anno;
		this.peso = peso;
	}
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(anno, peso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dettaglio other = (Dettaglio) obj;
		return Objects.equals(anno, other.anno) && peso == other.peso;
	}
	@Override
	public String toString() {
		return "Dettaglio [anno=" + anno + ", peso=" + peso + "]";
	}
	@Override
	public int compareTo(Dettaglio o) {
		// TODO Auto-generated method stub
		return -(this.peso.compareTo(o.getPeso()));
	}
	
	
	
}
