package com.yipeng.cplexj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyip on 09/10/2015.
 */
public class Vertex {

    private int id;

    private float x;
    private float y;

    private int nbAdjacencies;
    private List<Arc> listAdjacencies;

    /**
     * Construct an empty vertex with id
     * @param id index of the vertex in the list
     */
    public Vertex(int id) {
        this.id = id;

        this.x = 0;
        this.y = 0;

        this.nbAdjacencies = 0;
        this.listAdjacencies = new ArrayList<Arc>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getNbAdjacencies() {
        return nbAdjacencies;
    }

    public void setNbAdjacencies(int nbAdjacencies) {
        this.nbAdjacencies = nbAdjacencies;
    }

    public List<Arc> getListAdjacencies() {
        return listAdjacencies;
    }

    public void setListAdjacencies(List<Arc> listAdjacencies) {
        this.listAdjacencies = listAdjacencies;
    }

    @Override
    public String toString() {
        return "V[" + this.id + "]: (" + this.x + "," + this.y + ") : adjacencies(" + this.nbAdjacencies + ")";
    }

}
