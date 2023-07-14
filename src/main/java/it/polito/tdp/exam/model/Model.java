package it.polito.tdp.exam.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.exam.db.BaseballDAO;

public class Model {
	private BaseballDAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	public Model() {
		this.dao= new BaseballDAO();
		this.grafo = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
	}
	public List<String> allTeams(){
		return this.dao.getAllTeamName();
	}
	public void buildGrafo( String nome) {
		List<Integer> vertici = this.dao.getVertici(nome);
		Graphs.addAllVertices(this.grafo, vertici);
		
		
		for (Integer anno: this.grafo.vertexSet()) {
			for (Integer anno2 : this.grafo.vertexSet()) {
				if (anno< anno2) {
					double pesoMedio1 = this.dao.pesoMedioTeam(anno, nome);
					double pesoMedio2 = this.dao.pesoMedioTeam(anno2, nome);
					double pesoArco =Math.abs(pesoMedio1-pesoMedio2);
					Graphs.addEdge(this.grafo, anno, anno2, pesoArco);
				}
			}
		}
	}
	public Set<Integer> getVertici() {
		return this.grafo.vertexSet();
	}
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	public List<Dettaglio> getDettagli(Integer anno){
		List<Integer> adiacenti = Graphs.neighborListOf(this.grafo, anno);
		List<Dettaglio> result = new ArrayList<>();
		for (Integer annoC : adiacenti) {
			DefaultWeightedEdge arco = this.grafo.getEdge(anno, annoC);
			result.add(new Dettaglio(annoC, this.grafo.getEdgeWeight(arco)));
		}
		Collections.sort(result);
		return result;
	}
}
