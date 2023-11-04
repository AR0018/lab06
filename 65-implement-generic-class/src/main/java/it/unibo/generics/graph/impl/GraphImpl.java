package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.api.Search;

/**
 * Implements a generic graph.
 * The nodes are represented as the keys of a hash map, 
 * and the value of each node is a set containing all of the adjacent nodes.
 */
public class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> nodes;
    private final Search<N> searchStrategy;

    /*
     * The constructor requires to insert the desired strategy for the graph exploration
     */
    public GraphImpl(final Search<N> searchStrategy) {
        this.nodes = new HashMap<>();
        this.searchStrategy = searchStrategy;
    }

    public void addNode(final N node) {
        if(node != null) {
            this.nodes.putIfAbsent(node, new HashSet<>());
        }
    }

    public void addEdge(final N source, final N target) {
        if(source != null && target != null &&
        this.nodes.containsKey(source) &&
        this.nodes.containsKey(target)) {
            this.nodes.get(source).add(target);
        }
    }

    public Set<N> nodeSet() {
        return new HashSet<>(nodes.keySet());
    }

    public Set<N> linkedNodes(final N node) {
        return new HashSet<>(nodes.get(node));
    }

    public List<N> getPath(final N source, final N target) {
        return this.searchStrategy.findPath(this, source, target);
    }

    public String toString() {
        String graphString = "Graph:\n";
        for (final N node : this.nodes.keySet()) {
            graphString += ""+node+"->"+this.nodes.get(node)+"\n";
        }
        return graphString;
    }

}
