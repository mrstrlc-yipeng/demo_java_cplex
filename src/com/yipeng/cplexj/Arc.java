package com.yipeng.cplexj;

/**
 * Created by huangyip on 09/10/2015.
 */
public class Arc {

    private int id;

    private int source;
    private int target;

    private float cost;

    /**
     * Construct an empty arc with id
     * @param id index of the arc in the list
     */
    public Arc(int id) {
        this.id = id;

        this.source = -1;
        this.target = -1;

        this.cost = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "A[" + this.id + "]: (" + this.source + " - " + this.target + "): c=" + this.cost;
    }

}
