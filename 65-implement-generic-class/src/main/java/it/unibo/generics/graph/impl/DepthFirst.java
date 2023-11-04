package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.api.Search;

/**
 * Implements the Dephth-First Search algoritm.
 * Uses a map to store the informations about each node, which are:
 * -father: the father of the node in the search tree; is set by default to null
 * -discovered: a boolean which is true if the node has been discovered, false otherwise
 * 
 * The implementation of the exploration is recursive.
 */
public class DepthFirst<N> implements Search<N> {

    private Map<N, NodeInfo<N>> nodesData; // stores the information about every node in the graph

    public DepthFirst() {
    }

    public List<N> findPath(final Graph<N> graph, final N source, final N target) {
        if (source != null && target != null
                && graph.nodeSet().contains(source)
                && graph.nodeSet().contains(target)) {
            this.nodesData = newMap(graph);
            /*
             * Explores the graph
             */
            initializeSource(source);
            explore(graph, source);
            return getPath(graph, target);
        }
        return null;
    }

    /*
     * Recursively explores the graph
     */
    private void explore(final Graph<N> graph, final N node) {
        for(final N current : graph.linkedNodes(node)) {
            if(!this.nodesData.get(current).isDiscovered()) {
                this.nodesData.get(current).setFather(node);
                this.nodesData.get(current).discover();
                explore(graph, current);
            }
        }
    }

    /*
     * Initializes the source of the graph
    */
    private void initializeSource(final N source) {
        this.nodesData.get(source).discover();
    }

    /**
     * Returns the list containing the nodes of the path ordered from the source
     * to the target (source is the first element)
     */
    private List<N> getPath(final Graph<N> graph, final N target) {
        final List<N> path = new ArrayList<>();
        Path(path, target);
        return nodesData.get(target).getFather() == null ? null : path;
    }
    
    /**
     * Adds the nodes of the path recursively by climbing the tree of fathers 
     * up to the source
     */
    private void Path(final List<N> path, final N node) {
        if(node != null) {
            Path(path, nodesData.get(node).getFather());
            path.add(node);
        }
    }

    /**
     * Creates a map with the informations about the nodes of the graph
     */
    private Map<N, NodeInfo<N>> newMap(final Graph<N> graph) {
        final Map<N, NodeInfo<N>> infoMap = new HashMap<>();
        for (final N node : graph.nodeSet()) {
                infoMap.put(node, new NodeInfo<>());
        }
        return infoMap;
    }
}
