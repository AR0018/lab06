package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;
/*TODO: implement a way to choose which graph search strategy to use in getPath */
public class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> nodes;

    public GraphImpl() {
        this.nodes = new HashMap<>();
    }

    public void addNode(N node) {
        if(node != null) {
            this.nodes.putIfAbsent(node, new HashSet<>());
        }
    }

    public void addEdge(N source, N target) {
        if(source != null && target != null &&
        this.nodes.containsKey(source) &&
        this.nodes.containsKey(target)) {
            this.nodes.get(source).add(target);
        }
    }

    public Set<N> nodeSet() {
        return new HashSet<>(nodes.keySet());
    }

    public Set<N> linkedNodes(N node) {
        return new HashSet<>(nodes.get(node));
    }

    /*TODO: implement this method */
    public List<N> getPath(N source, N target) {
        return null;
    }

    public String toString() {
        String graphString = "Graph:\n";
        for (N node : this.nodes.keySet()) {
            graphString += ""+node+"->"+this.nodes.get(node)+"\n";
        }
        return graphString;
    }

}
