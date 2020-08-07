/**
 * Implements methods in interface Graph<T>
 *
 * @author Alina Zheng and Leah Teffera
 * @version 04/15/2020
 */
import java.util.*;
import java.io.*; 
import javafoundations.*; 
public class AdjListsGraph<T> implements Graph<T>
{
    // instance variables
    Vector<T> vertices; 
    Vector<LinkedList<T>> arcs; 

    public AdjListsGraph(){
        arcs = new Vector<LinkedList<T>>();
        vertices = new Vector<T>(); 
    }

    /**
     * Getter method for the arcs in an AdjListsGraph object
     * @return the linked list of arcs
     */
    public Vector<LinkedList<T>> getArcs() {
        return this.arcs; 
    }

    /**
     * Getter method for vertices instance variable
     * @return the linked list of vertices of the AdjListsGraph
     */
    public Vector<T> getVertices() {
        return this.vertices; 
    }

    /**
     * Setter method for arcs
     * @param the vector of arcs to set this AdjListsGraph object's arcs to
     */
    public void setArcs(Vector<LinkedList<T>> arcs) {
        this.arcs = arcs; 
    }

    /**
     *  Setter method for vertices
     * @param the vector of arcs to set this AdjListsGraph object's arcs to
     */
    public void setVertices(Vector<T> vertices) {
        this.vertices = vertices; 
    }

    /**
     * Creates a string describing the information of the AdjListsGraph object
     * @return a neatly-formatted string of the information of the AdjListsGraph objec
     */
    public String toString() {
        String result = "Vertices: \n ["; 
        for (int i = 0; i < vertices.size(); i++) {
            result += vertices.get(i) + ", "; 
        }
        result += "]\nEdges:\n"; 
        for (int i = 0; i < arcs.size(); i++) {
            result += "from " + vertices.get(i) + ": ["; 
            for (int j = 0; j < arcs.get(i).size(); j++) {
                result += arcs.get(i).get(j); 
            }
            result += "]\n"; 
        }
        return result; 
    }

    /** 
     * Returns a boolean indicating whether this graph is empty or not.
     * A graph is empty when it contains no vertice,a nd of course, no edges.
     *  
     *  @return true if this graph is empty, false otherwise.
     */
    public boolean isEmpty() {
        return vertices.size() == 0; 
    }

    /** 
     * Returns the number of vertices in this graph. 
     * 
     * @return the number of vertices in this graph
     */
    public int getNumVertices() {
        return vertices.size(); 
    }

    /** 
     * Returns the number of arcs in this graph.
     * An arc between Verteces A and B exists, if a direct connection
     * from A to B exists.
     * 
     * @return the number of arcs in this graph
     *  */
    public int getNumArcs() {
        int count = 0; 
        for (int i = 0; i < arcs.size(); i++) {
            for (int j = 0; j < arcs.get(i).size(); j++) {
                count++; 
            }
        }
        return count; 
    }

    /** 
     * Returns true if an arc (direct connection) exists 
     * from the first vertex to the second, false otherwise
     * 
     * @return true if an arc exists between the first given vertex (vertex1),
     * and the second one (vertex2),false otherwise
     * 
     *  */
    public boolean isArc (T vertex1, T vertex2) {
        if (!vertices.contains(vertex1)){
            return false; 
        }
        int vert1_index = vertices.indexOf(vertex1); 
        return (arcs.get(vert1_index).contains(vertex2)); 
    }

    /** 
     * Returns true if an edge exists between two given vertices, i.e,
     * an arch exists from the first vertex to the second one, and an arc from
     * the second to the first vertex, false otherwise.
     *  
     * @return true if an edge exists between vertex1 and vertex2, 
     * false otherwise
     * 
     * */
    public boolean isEdge (T vertex1, T vertex2) {
        return (isArc(vertex1, vertex2) && (isArc(vertex2, vertex1))); 
    }

    /** 
     * Returns true if the graph is undirected, that is, for every
     * pair of nodes i,j for which there is an arc, the opposite arc
     * is also present in the graph, false otherwise.  
     * 
     * @return true if the graph is undirected, false otherwise
     * */
    public boolean isUndirected() {
        for (int i = 0; i < arcs.size(); i++) {
            T vertex1 = vertices.get(i); 
            for (int j = 0; j < arcs.get(i).size(); j++) {
                var isEdge = isEdge(vertex1, arcs.get(i).get(j)); 
                if (isEdge == false) {
                    return false; 
                }
            }
        }
        return true; 
    }

    /** 
     * Adds the given vertex to this graph
     * If the given vertex already exists, the graph does not change
     * 
     * @param The vertex to be added to this graph
     * */
    public void addVertex (T vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            arcs.add(new LinkedList<T>());
        }
    }

    /** 
     * Removes the given vertex from this graph.
     * If the given vertex does not exist, the graph does not change.
     * 
     * @param the vertex to be removed from this graph
     *  */
    public void removeVertex (T vertex) {
        if (vertices.contains(vertex)) {
            int index = vertices.indexOf(vertex);
            vertices.remove(vertex); 
            arcs.remove(index);
        }
    }

    /** 
     * Inserts an arc between two given vertices of this graph.
     * if at least one of the vertices does not exist, the graph 
     * is not changed.
     * 
     * @param the origin of the arc to be added to this graph
     * @param the destination of the arc to be added to this graph
     * 
     *  */
    public void addArc (T vertex1, T vertex2) {
        if (vertices.contains(vertex1) && vertices.contains(vertex2)) {
            int vert1_index = vertices.indexOf(vertex1); 
            if (!arcs.get(vert1_index).contains(vertex2)) {
                arcs.get(vert1_index).add(vertex2); 
            }
        }
    }

    /** 
     * Removes the arc between two given vertices of this graph.
     * If one of the two vertices does not exist in the graph,
     * the graph does not change.
     * 
     * @param the origin of the arc to be removed from this graph
     * @param the destination of the arc to be removed from this graph
     * 
     * */
    public void removeArc (T vertex1, T vertex2) {
        if (vertices.contains(vertex1) && vertices.contains(vertex2)) {
            int vert1_index = vertices.indexOf(vertex1); 
            if (arcs.get(vert1_index).contains(vertex2)) {
                arcs.get(vert1_index).remove(vertex2); 
            }
        }
    }

    /** 
     * Inserts the edge between the two given vertices of this graph,
     * if both vertices exist, else the graph is not changed.
     * 
     * @param the origin of the edge to be added to this graph
     * @param the destination of the edge to be added to this graph
     * 
     *  */
    public void addEdge (T vertex1, T vertex2) {
        addArc(vertex1, vertex2);
        addArc(vertex2, vertex1);
    }

    /** 
     * Removes the edge between the two given vertices of this graph,
     * if both vertices exist, else the graph is not changed.
     * 
     * @param the origin of the edge to be removed from this graph
     * @param the destination of the edge to be removed from this graph
     * 
     */
    public void removeEdge (T vertex1, T vertex2) {
        removeArc(vertex1, vertex2);
        removeArc(vertex2, vertex1);
    }

    /** 
     * Return all the vertices, in this graph, adjacent to the given vertex.
     * 
     * @param A vertex in the graph whose successors will be returned.
     * @return LinkedList containing all the vertices x in the graph,
     * for which an arc exists from the given vertex to x (vertex -> x).
     *
     * */
    public LinkedList<T> getSuccessors(T vertex) {
        return arcs.get(vertices.indexOf(vertex));
    }

    /** 
     * Return all the vertices x, in this graph, that precede a given
     * vertex.
     * 
     * @param A vertex in the graph whose predecessors will be returned.
     * @return LinkedList containing all the vertices x in the graph,
     * for which an arc exists from x to the given vertex (x -> vertex).
     * 
     * */
    public LinkedList<T> getPredecessors(T vertex) {
        LinkedList<T> predecessors = new LinkedList<T>();
        for(int i=0; i<arcs.size(); i++){
            if (arcs.get(i).contains(vertex)){
                predecessors.add(vertices.get(i));
            }
        }
        return predecessors;
    }

    /** 
     * Writes this graph into a file in the TGF format.
     * 
     * @param the name of the file where this graph will be written 
     * in the TGF format.
     * */
    public void saveToTGF(String tgf_file_name) {
        try {
            PrintWriter writer = new PrintWriter(new File(tgf_file_name));
            for (int i=0; i<vertices.size(); i++){
                int vertNum = i+1;
                writer.println(vertNum + " " + vertices.get(i));
            }
            writer.println("#");
            for (int j=0; j<arcs.size(); j++){
                int vert = j+1;
                for (int k=0; k<arcs.get(j).size(); k++){
                    writer.println(vert + " " + (vertices.indexOf(arcs.get(j).get(k))+1)); 
                }
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println (e);
        }
    }

    /**
     * Implements the depth first search algorithm
     * @param vertex the starting vertex 
     * @return a linked list describing the order of traversal 
     */
    public LinkedList<T> DepthFirstSearch(T vertex, T end_vert) {
        ArrayStack<T> traversalStack = new ArrayStack<T>(); 
        LinkedList<T> result = new LinkedList<T>();
        boolean[] visited = new boolean[this.getNumVertices()];
        T currentVertex = vertex; 
        boolean found; 

        // initialize the visited array to have all "false" to start with
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false; 
        }

        // push the origin vertex onto the traversal stack, add it to the result, mark it as visited
        traversalStack.push(currentVertex); 
        result.add(currentVertex); 
        visited[vertices.indexOf(currentVertex)] = true; 

        while (!traversalStack.isEmpty() && currentVertex != end_vert) {
            // set the current vertex to the first successor of the currentVertex
            currentVertex = traversalStack.peek(); 
            found = false; 

            // get the first successor of the current vertex and test if it has been visited
            for (int i = 0; i < this.getSuccessors(currentVertex).size() && !found; i++) {
                T vert = this.getSuccessors(currentVertex).get(i); 
                if (!visited[vertices.indexOf(vert)]) {
                    traversalStack.push(vert); 
                    result.add(vert); 
                    visited[vertices.indexOf(vert)] = true; 
                    found = true; 
                    break; // break out so that you can go to the adjacency list of what you pushed onto the traversal stack
                }
            }

            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop(); 
            }
        }
        return result; 
    }

    /**
     * Implements the breadth first search algorithm
     * @param vertex the starting vertex
     * @return a linked list describing the order of traversal
     */
    public LinkedList<T> BreadthFirstSearch(T vertex, T end_vert) {
        LinkedQueue<T> traversalQueue = new LinkedQueue<T>(); 
        LinkedList<T> result = new LinkedList<T>(); 

        // array of booleans to keep track of visited vertices
        boolean[] visited = new boolean[this.getNumVertices()]; 
        for (int i = 0; i < this.getNumVertices(); i++) {
            visited[i] = false; 
        }

        /* push the first vertex onto the traversal stack, 
        add it to the result and mark the vertex as visited*/
        traversalQueue.enqueue(vertex); 
        visited[vertices.indexOf(vertex)] = true; 
        result.add(vertex); 
        T currentVertex = vertex;  
        T currentSuc = vertex; 
        while (!traversalQueue.isEmpty()) {
            /* get the successors of the current vertex */
            LinkedList<T> suc = getSuccessors(currentVertex);
            for (int i = 0; i < suc.size() && (!currentSuc.equals(end_vert)); i++) {
                currentSuc = suc.get(i); 
                //System.out.println("curSuc: " + currentSuc); 
                /* if the successor hasn't been visited, 
                 * push it onto the traversal stack and mark it as visited */
                if (!visited[vertices.indexOf(currentSuc)]) {
                    traversalQueue.enqueue(currentSuc);
                    visited[vertices.indexOf(currentSuc)] = true; 
                }
            }
            traversalQueue.dequeue(); 
            /* set the current vertex to be the next vertex
            after the recently popped vertex, add it to the result list */ 
            if (traversalQueue.size() > 0) {
                currentVertex = traversalQueue.first(); 
                result.add(currentVertex); 
                if (currentVertex.equals(end_vert)) {
                    return result; 
                }
            }
        }     
        return result; 
    }

    /**
     * Loops through the linked lists of arcs of an AdjListsGraph object, gets the outdegrees of each vertex (by getting the 
     * number of items in each list of arcs), collects those outdegrees in a queue
     * @return a LinkedQueue containing all of the outdegrees 
     */
    public LinkedQueue<Integer> getOutdegrees() { 
        LinkedQueue<Integer> outQ = new LinkedQueue<Integer>(); 
        int size = arcs.size(); 
        for (int i =0; i<size; i++){
            LinkedList<T> list = arcs.get(i); 
            outQ.enqueue(list.size()); 
        }
        return outQ; 
    }

    /**
     * Loops through the linked lists of arcs of an AdjListsGraph object, gets the indegrees of each vertex,  
     * collects those indegrees in a queue
     * @return a LinkedQueue containing all of the outdegrees 
     */
    public LinkedQueue<Integer> getIndegrees() {
        LinkedQueue<Integer> inQ = new LinkedQueue<Integer>(); 
        int size = arcs.size(); 
        for (int i = 0; i < size; i++) {
            int in_deg = 0; 
            for (int j = 0; j < size; j++) {
                if (arcs.get(j).contains(vertices.get(i))) {
                    in_deg++; 
                }
            }
            inQ.enqueue(in_deg); 
        }
        return inQ; 
    }  

    public static void main(String[] args) {
        AdjListsGraph<Integer> graph = new AdjListsGraph<Integer>(); 
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addArc(1,2);
        graph.addArc(2,1);
        graph.addArc(2,3); 
        graph.vertices.toString(); 
        graph.arcs.toString(); 
        System.out.println("Successors of vertex 2, should be 1 and 3: " + graph.getSuccessors(2)); 
        System.out.println("Should be true: " + graph.isEdge(1,2));
        System.out.println("Should be true: " + graph.isUndirected());
        graph.removeVertex(3);
        graph.removeArc(1,2);
        System.out.println("Should be false: " + graph.isUndirected());
        System.out.println(graph);

        AdjListsGraph<String> graph1 = new StringGraphBuilder().build("Graph1.tgf");
        System.out.println("Displays contents of Graph1.tgf, which is a graph of strings"); 
        System.out.println(graph1); 
        System.out.println("Displays contents of Graph2.tgf, which is a graph of integers"); 
        AdjListsGraph<Integer> graph2 = new IntegerGraphBuilder().build("Graph2.tgf"); 
        System.out.println(graph2); 
        
        
        AdjListsGraph<Integer> graph3 = new AdjListsGraph<Integer>(); 
        graph3.addVertex(1); 
        graph3.addVertex(2); 
        graph3.addVertex(3); 
        graph3.addVertex(4);
        graph3.addVertex(5);
        graph3.addVertex(6); 
        graph3.addArc(1, 2); 
        graph3.addArc(2, 3); 
        graph3.addArc(3, 4); 
        graph3.addArc(2, 5); 
        graph3.addArc(5, 6);      
        System.out.println("Performs depth first search on graph 3 from vertex 1 to 4; should be '1 2 3 4': " + graph3.DepthFirstSearch(1, 4).toString()); 
        System.out.println("Performs depth first search on graph 3 from vertex 1 to 6; should be '1 2 3 4 5 6': " + graph3.DepthFirstSearch(1, 6).toString()); 
        System.out.println("Performs breadth first search on graph 3 from vertex 1 to 6; should be '1 2 3 5 4 6': " + graph3.BreadthFirstSearch(1, 6).toString());
        System.out.println("Performs breadth first search on graph 3 from vertex 1 to 4: should be '1 2 3 5 4': " + graph3.BreadthFirstSearch(1, 4).toString()); 
        System.out.println("List of outdegrees of graph 3: \n" + graph3.getOutdegrees().toString()); 
        System.out.println("list of indegrees of graph 3: \n" + graph3.getIndegrees().toString());
        System.out.println("\n"); 

        AdjListsGraph<Integer> graph4 = new AdjListsGraph<Integer>(); 
        graph4.addVertex(1); 
        graph4.addVertex(2); 
        graph4.addVertex(3); 
        graph4.addVertex(4); 
        graph4.addVertex(5); 
        graph4.addVertex(6); 
        graph4.addVertex(7); 
        graph4.addArc(1, 2); 
        graph4.addArc(1, 3); 
        graph4.addArc(2, 4); 
        graph4.addArc(2, 5); 
        graph4.addArc(3, 6);
        graph4.addArc(3, 7);
        System.out.println("Performs depth first search on graph 3; should be '1 2 4 5 3 6': " + graph4.DepthFirstSearch(1, 6).toString()); 
        System.out.println("Performs breadth first search on graph 3 from vertex 1 to 7; should be '1 2 3 4 5 6 7': " + graph4.BreadthFirstSearch(1, 7).toString());
        System.out.println("Performs breadth first search on graph 3 from vertex 1 to 4; should be '1 2 3 4': " + graph4.BreadthFirstSearch(1, 4).toString()); 
        System.out.println("List of outdegrees of graph 4: \n" + graph4.getOutdegrees().toString()); 
        System.out.println("list of indegrees of graph 4: \n" + graph4.getIndegrees().toString()); 
    }
}
