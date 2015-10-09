package com.yipeng.cplexj;

/**
 * Created by huangyip on 09/10/2015.
 */
public class Blockage {

    private int id;

    private int source;
    private int target;

    // TODO: index blockages the timescale
    //private long earliestStartTime;
    //private long latestEndTime;
    //private long duration;

    /**
     * Construct an empty blockage with id
     * @param id index of the blockage in list
     */
    public Blockage(int id) {
        this.id = id;

        this.source = -1;
        this.target = -1;
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

    @Override
    public String toString() {
        return "B[" + this.id + "]: (" + this.source + " - " + this.target + ")";
    }
}
