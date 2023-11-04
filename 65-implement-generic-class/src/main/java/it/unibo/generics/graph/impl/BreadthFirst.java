package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.api.Search;

/**
 * Implements the Breadth-First Search algorithm.
 * Uses a map to store the informations about each node, which are:
 * -depth: the distance from the source; has a default value of -1 and is 0 for the source
 * -father: the father of the node in the search tree; is set by default to null
 * 
 * A node is considered as "not discovered" if its depth has the default value.
 * Instead, if the value of a node is greater or equal than 0, the node is considered as "discovered"
 */
public class BreadthFirst<N> implements Search<N> {

    private static final int SOURCE_DEPTH = 0;

    private List<N> queue;
    private Map<N, NodeInfo<N>> nodesData; // stores the information about every node in the graph

    public BreadthFirst() {
    }

    public List<N> findPath(final Graph<N> graph, final N source, final N target) {
        if (source != null && target != null
                && graph.nodeSet().contains(source)
                && graph.nodeSet().contains(target)) {
            /*
             * Creates the map with the informations about the nodes.
             */
            this.nodesData = newMap(graph);
            /*
             * Explores the graph 
            */
            initializeSource(source);
            this.queue = newQueue();
            enqueue(source);
            N currentNode;
            while (!this.queue.isEmpty()) {
                currentNode = dequeue();
                for (final N node : graph.linkedNodes(currentNode)) {
                    if (nodesData.get(node).getDepth() == NodeInfo.DEF_DEPTH) {
                        nodesData.get(node).setFather(currentNode);
                        nodesData.get(node).setDepth(nodesData.get(currentNode).getDepth() + 1);
                        enqueue(node);
                    }
                }
            }
            return getPath(graph, target);

        }
        return null;
    }

    /*
     * Inserts a node in the queue
     */
    private void enqueue(final N node) {
        this.queue.add(node);
    }

    /*
     * Removes a node from the queue
     */
    private N dequeue() {
        return this.queue.remove(0);
    }

    /*
     * Initializes the source of the graph
     */
    private void initializeSource(final N source) {
        this.nodesData.get(source).setDepth(SOURCE_DEPTH);
    }

    /*
     * Returns the list containing the nodes of the path ordered from the source
     * to the target (source is the first element)
     */
    private List<N> getPath(final Graph<N> graph, final N target) {
        final List<N> path = new ArrayList<>();
        Path(path, target);
        return nodesData.get(target).getFather() == null ? null : path;
    }
    
    /*
     * Adds the nodes of the path recursively by climbing the tree of fathers 
     * up to the source
     */
    private void Path(final List<N> path, final N node) {
        if(node != null) {
            Path(path, nodesData.get(node).getFather());
            path.add(node);
        }
    }

    /*
     * Creates an empty queue
     */
    private List<N> newQueue() {
        return new LinkedList<>();
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
