package com.yipeng.cplexj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyip on 09/10/2015.
 */
public class Graph {

    private int nbVertices;
    private int nbArcs;
    private int nbRequests;
    private int nbBlockages;

    private List<Vertex> vertices;
    private List<Arc> arcs;
    private List<Request> requests;
    private List<Blockage> blockages;

    /**
     * Construct an empty graph
     */
    public Graph() {
        this.nbVertices = 0;
        this.nbArcs = 0;
        this.nbRequests = 0;
        this.nbBlockages = 0;
        this.vertices = new ArrayList<Vertex>();
        this.arcs = new ArrayList<Arc>();
        this.requests = new ArrayList<Request>();
        this.blockages = new ArrayList<Blockage>();
    }

    public void read(String filePath) {
        // flags
        boolean isVertices = false;
        boolean isArcs = false;
        boolean isRequests = false;
        boolean isBlockages = false;

        // countings as ids
        int verticesCnt = 0;
        int arcsCnt = 0;
        int requestsCnt = 0;
        int blockagesCnt = 0;

        // read the file
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            // read the file line by line till the "END" identifier
            String line = br.readLine();
            while (line != null && !line.equals("END")) {
                // split the string by "whitespace or tabulation" into several
                // parts
                String[] parts = line.trim().split("[\\s\\t]+");

                // read flags
                if (parts.length == 1) {
                    isVertices = parts[0].equals("VERTICES") || parts[0].equals("Nodes");
                    isArcs = parts[0].equals("ARCS") || parts[0].equals("EDGES");
                    isRequests = parts[0].equals("REQUESTS");
                    isBlockages = parts[0].equals("BLOCKAGES");
                }

                // retrieve number of nodes
                else if (parts.length == 2) {

                    if (parts[0].equals("NB_NODES") || parts[0].equals("NB_VERTICES")) {
                        this.nbVertices = Integer.parseInt(parts[1]);
                        Utils.console(parts[0] + "=" + this.nbVertices);

                        // initialize the node list by the number of nodes
                        for (int i = 0; i < this.nbVertices; i++) {
                            this.vertices.add(new Vertex(i));
                        }
                    }

                    else if (parts[0].equals("NB_EDGES") || parts[0].equals("NB_ARCS")) {
                        this.nbArcs = Integer.parseInt(parts[1]);
                        Utils.console(parts[0] + "=" + this.nbArcs);
                    }

                    else if (parts[0].equals("NB_REQUESTS")) {
                        this.nbRequests = Integer.parseInt(parts[1]);
                        Utils.console(parts[0] + "=" + this.nbRequests);
                    }

                    else if (parts[0].equals("NB_BLOCKAGES")) {
                        this.nbBlockages = Integer.parseInt(parts[1]);
                        Utils.console(parts[0] + "=" + this.nbBlockages);
                    }

                    // For now, the description of a blockage contains only the source and target vertex id.
                    // So if a line contains only 2 parts and if the first part differs from the above statements,
                    // the line should be a description of a blockage.
                    // TODO: remove the 2 parts reading of blockages after timescale index setting
                    else if (isBlockages) {
                        Blockage blockage = new Blockage(blockagesCnt);

                        blockage.setSource(Integer.parseInt(parts[0]) - 1);
                        blockage.setTarget(Integer.parseInt(parts[1]) - 1);

                        this.blockages.add(blockage);
                        blockagesCnt++;
                    }

                }

                // retrieve arcs
                else if (parts.length > 2) {

                    // Vertex format: {id+1, x, y}
                    if (isVertices) {
                        this.vertices.get(verticesCnt).setX(Integer.parseInt(parts[1]));
                        this.vertices.get(verticesCnt).setY(Integer.parseInt(parts[2]));

                        verticesCnt++;
                    }

                    else if (isArcs) {
                        int source = Integer.parseInt(parts[0]) - 1;
                        int target = Integer.parseInt(parts[1]) - 1;
                        int cost = Integer.parseInt(parts[2]);

                        Arc arc = new Arc(arcsCnt);

                        arc.setSource(source);
                        arc.setTarget(target);
                        arc.setCost(cost);

                        this.arcs.add(arc);

                        arcsCnt++;

                        // add the arc to the adjacency list of the source vertex
                        this.vertices.get(source).getListAdjacencies().add(arc);
                        this.vertices.get(source).setNbAdjacencies(
                                this.vertices.get(source).getNbAdjacencies() + 1
                        );
                    }

                    else if (isRequests) {
                        // TODO: set requests
                    }

                    else if (isBlockages) {
                        // TODO: set blockages with timescale
                    }

                }

                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            // TODO: handler the error
            Utils.console("No such file.");
        } catch (IOException e) {
            Utils.console("An error occurs");
        }
    }

    public int getNbVertices() {
        return nbVertices;
    }

    public void setNbVertices(int nbVertices) {
        this.nbVertices = nbVertices;
    }

    public int getNbArcs() {
        return nbArcs;
    }

    public void setNbArcs(int nbArcs) {
        this.nbArcs = nbArcs;
    }

    public int getNbRequests() {
        return nbRequests;
    }

    public void setNbRequests(int nbRequests) {
        this.nbRequests = nbRequests;
    }

    public int getNbBlockages() {
        return nbBlockages;
    }

    public void setNbBlockages(int nbBlockages) {
        this.nbBlockages = nbBlockages;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public void setArcs(List<Arc> arcs) {
        this.arcs = arcs;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Blockage> getBlockages() {
        return blockages;
    }

    public void setBlockages(List<Blockage> blockages) {
        this.blockages = blockages;
    }

}
