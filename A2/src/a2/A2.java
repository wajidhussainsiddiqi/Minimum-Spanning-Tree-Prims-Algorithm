/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2;

import Graph.Graph;
import Graph.Graph.TYPE;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author SID
 */

public class A2 {

// Prim's Algorithm function
public class Prim {

    private Prim() { }

    public Graph.CostPathPair<Integer> getMST(Graph<Integer> graph, Graph.Vertex<Integer> start) {
        if (graph == null)
            throw (new NullPointerException("Graph must be non-NULL."));

        // Prim's algorithm only works on undirected graphs
        if (graph.getType() == Graph.TYPE.DIRECTED) 
            throw (new IllegalArgumentException("Undirected graphs only."));

        double cost = 0;

        final Set<Graph.Vertex<Integer>> unvisited = new HashSet<Graph.Vertex<Integer>>();
        unvisited.addAll(graph.getVertices());
        unvisited.remove(start); // O(1)

        final List<Graph.Edge<Integer>> path = new ArrayList<Graph.Edge<Integer>>();
        final Queue<Graph.Edge<Integer>> edgesAvailable = new PriorityQueue<Graph.Edge<Integer>>();

        Graph.Vertex<Integer> vertex = start;
        while (!unvisited.isEmpty()) {
            // Add all edges to unvisited vertices
            for (Graph.Edge<Integer> e : vertex.getEdges()) {
                if (unvisited.contains(e.getToVertex()))
                    edgesAvailable.add(e);
            }

            // Remove the lowest cost edge
            final Graph.Edge<Integer> e = edgesAvailable.remove();
            cost += e.getCost();
            path.add(e); // O(1)

            vertex = e.getToVertex();
            unvisited.remove(vertex); // O(1)
        }

        return (new Graph.CostPathPair<>(cost, path));
    }

    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public void main(String []args) throws FileNotFoundException
{
    String fileName="C:\\Users\\SID\\Documents\\NetBeansProjects\\A2\\graph250.txt";
    int vertices, edges, i, j, v1, v2;
    double weight;
    LinkedList<Vertex> allVertices = new LinkedList<Vertex>();
    LinkedList<Edge> allEdges=new LinkedList<Edge>();
    //Scanning from file.
    File myFile = new File(fileName);
    
    Scanner input = new Scanner(myFile);
    vertices=input.nextInt();
    edges=input.nextInt();
    for(i=1;i<=vertices;++i)
    {
        Vertex v=new Vertex(i);
        allVertices.add(v);
    }
    for (i = 1; i <= edges; ++i) {
        v1=input.nextInt();
        v2=input.nextInt();
        weight=input.nextDouble();
            Vertex vr1=new Vertex(v1);
            Vertex vr2=new Vertex(v2);
            Edge e=new Edge(weight, vr1, vr2);
            allEdges.add(e);
    }
    Graph graph=new Graph(TYPE.UNDIRECTED, allVertices, allEdges);
    System.out.println(this.getMST(graph, new Graph.Vertex(1)).getCost()+" is the minimum cost");
}
}
}
