package it.unibo.generics.graph.impl;

/**
 * Implements a node to be used in the implementation of the graph
 * search classes.
 */
public class NodeInfo<N> {

    public static final int DEF_DEPTH = -1; 

    private int depth;  //distance to source
    private N father;
    private boolean discovered; //true if the node is discovered, false otherwise

    public NodeInfo() {
        this.depth = DEF_DEPTH;
        this.father = null;
        this.discovered = false;
    }

    public int getDepth() {
        return this.depth;
    }

    public N getFather() {
        return this.father;
    }

    public boolean isDiscovered() {
        return this.discovered;
    }

    public void setDepth(final int depth) {
        this.depth = depth;
    }

    public void setFather(final N father) {
        this.father = father;
    }

    public void discover() {
        this.discovered = true;
    }
}
