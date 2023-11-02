package it.unibo.generics.graph.api;

import java.util.List;

/**
 * Defines the path search operation on a graph with
 * generic type nodes
 */
public interface Search<N> {

    /**
     * Finds a path in the given graph from a source to a destination
     * 
     * @param graph
     *          the graph on which to search the path
     * @param source
     *          starting node
     * @param target
     *          ending node
     * @return the list of nodes in the path from source to target
     */
    List<N> findPath(Graph<N> graph, N source, N target);
}
