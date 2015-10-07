package com.yipeng.cplexj;

import ilog.concert.*;
import ilog.cplex.*;

/**
 * Created by huangyip on 07/10/2015.
 */
public class Main {

    public static void main(String[] args) {

        try {
            IloCplex cplex = new IloCplex();

            // Decision variables
            IloNumVar[] x = cplex.numVarArray(2, 0.0, Double.MAX_VALUE);

            // Objective function expression
            IloLinearNumExpr expr = cplex.linearNumExpr();
            expr.addTerm(1, x[0]);
            expr.addTerm(0.64, x[1]);
            cplex.addMaximize(expr);

            // Constraints
            cplex.addLe(cplex.sum(cplex.prod(50, x[0]), cplex.prod(31, x[1])), 250);
            cplex.addGe(cplex.sum(cplex.prod(3, x[0]), cplex.prod(-2, x[1])), -4);

            // Solve
            cplex.solve();

            System.out.println("Status: " + cplex.getStatus());
            System.out.println("z = " + cplex.getObjValue());
        } catch (IloException e) {
            System.err.println("Concert exception caught: " + e);
        }

    }
}
