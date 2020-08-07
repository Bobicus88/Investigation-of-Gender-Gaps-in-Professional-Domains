/**
 * Finds the minimum, maximum, and average outdegrees and indegrees of the nodes in pantheon_edges_1000, finds the most important person (the
 * node with the largest number of indegrees) and uses graph traversal algorithms to find the nodes that are farthest away from the most important 
 * person. 
 *
 * @author Leah Teffera and Alina Zheng
 * @version 5/1/20
 */
import java.util.*;
import java.io.*; 
import javafoundations.*; 
public class InvestigateWikipedia
{
    // instance variables 
    GraphCreator graph_holder; 
    String fileN;
    AdjListsGraph<String> graph; 
    LinkedQueue<Integer> outDegrees; 
    LinkedQueue<Integer> inDegrees; 

    /**
     * Constructor for objects of class InvestigateWikipedia
     * @param fileName the name of the file to create a GraphCreator object from
     */
    public InvestigateWikipedia(String fileName)
    {
        this.fileN = fileName; 
        graph_holder = new GraphCreator(fileN);  
        graph = graph_holder.getGraph(); 
        outDegrees = graph.getOutdegrees(); 
        inDegrees = graph.getIndegrees(); 
    }

    /**
     * Setter method for graph of InvestigateWikipedia object
     * For testing graph traversal algorithms
     * @param graph the graph to set this graph instance invariable to 
     */
    public void setGraph(AdjListsGraph<String> graph) {
        this.graph = graph; 
    }

    /**
     * Setter method for inDegrees of InvestigateWikipedia object 
     * For testing reportMinMax()
     * @param queue the queue to find the min and max of 
     */
    public void setIndegrees(LinkedQueue<Integer> queue) {
        this.inDegrees = queue; 
    }

    /**
     * Calculates the average value of a queue of integers
     * @param in_out a string representing which queue of degrees we want to look at ("in" or "out") 
     * @return the average
     */
    public float getAvg(String in_out) {
        // queue of values that that you want to find the average of 
        LinkedQueue<Integer> queue; 
        // will hold copy of queue above 
        LinkedQueue<Integer> temp = new LinkedQueue<Integer>(); 
        if (in_out.equals("in")) {
            queue = this.inDegrees; 
        } else {
            queue = this.outDegrees; 
        }
        int sum = 0; 
        int size = queue.size(); 
        // loop through, add each degree/value to sum
        for (int i = 0; i < size; i++) {
            int deg = queue.dequeue(); 
            sum += deg; 
            temp.enqueue(deg); 
        }
        if (in_out.equals("in")) {
            this.inDegrees = temp; 
        } else {
            this.outDegrees = temp; 
        } 
        // calculate average by dividing sum by the size of the queue
        float avg = (float) sum / (float) size; 
        return avg; 
    }

    /**
     * Traverses a queue of integers and determines the min and max values
     * @param in_out a string representing which queue of degrees we want to look at ("in" or "out") 
     * @return linked list with the min, max
     */
    public LinkedList<Integer> reportMinMax(String in_out) {
        // Linked list that reports min and max
        LinkedList<Integer> stats = new LinkedList<Integer>(); 
        // to store copy of indegrees/outdegrees queue
        LinkedQueue<Integer> temp = new LinkedQueue<Integer>(); 
        // stores either the indegrees queue or the outdegrees queue
        LinkedQueue<Integer> queue; 
        if (in_out.equals("in")) {
            queue = this.inDegrees; 

        } else {
            queue = this.outDegrees; 
        }
        // Set min and max equal to the first item in the queue, initially
        int size = queue.size(); 
        int max = queue.dequeue(); 
        int min = max; 
        temp.enqueue(max);  
        // Loop through the rest of the queue to find the min and max
        for (int i = 1; i < size; i++) {
            int elt = queue.dequeue(); 
            if (max < elt) {
                max = elt; 
            }
            if (min > elt) {
                min = elt; 
            }
            temp.enqueue(elt); 
        }
        if (in_out.equals("in")) {
            this.inDegrees = temp; 
        } else {
            this.outDegrees = temp; 
        }  
        // add the min and max to the linked list that reports the min and the max
        stats.add(min); 
        stats.add(max);
        return stats;
    }

    /**
     * Gets a linked list of the people with a certain number of indegrees
     * @param val the number of indegrees
     * @return linked list of people
     */
    public LinkedList<String> getPeople(int val) {
        // Holds names of important people
        LinkedList<String> people = new LinkedList<String>(); 
        // Holds indices of important people in list of vertices
        LinkedList<Integer> indices = new LinkedList<Integer>(); 
        // Will hold copy of queue of indegrees/outdegrees to look through
        LinkedQueue<Integer> temp = new LinkedQueue<Integer>(); 
        // The queue of indegrees/outdegrees to look through
        LinkedQueue<Integer> queue = this.inDegrees; 
        int size = queue.size(); 
        for (int i = 0; i < size; i++) {
            Integer elt = queue.dequeue(); 
            temp.enqueue(elt); 
            if (elt == val) {
                indices.add(i); 
            }
        }
        for (int i = 0; i < indices.size(); i++) {
            people.add(this.graph_holder.getGraph().getVertices().get(indices.get(i))); 
        }
        return people; 
    }

    /**
     * Runs BFS starting from a starting vertex to every other vertex in the list of vertices, 
     * gets the length of each path, and reports the vertex with the longest path from the starting vertex 
     * @param start_vert the starting vertex
     * @return a linked list of integers
     */
    public LinkedList<String> getFurthestPeople(String start_vert) {
        LinkedList<Integer> pathLengths = new LinkedList<Integer>();
        LinkedList<String> people = new LinkedList<String>(); 
        Vector<String> verts = this.graph_holder.getGraph().getVertices(); 
        // loop through the list of vertices, perform BFS from the start_vert to each of them, collects the path lengths in pathLengths
        for (int i = 0; i < verts.size(); i++) {
            LinkedList<String> path = this.graph_holder.getGraph().BreadthFirstSearch(start_vert, verts.get(i)); 
            // length of the path is one less than the number of vertices in the linked list of vertices traversed 
            int path_len = path.size() - 1; 
            pathLengths.add(i); 
        }
        /* 
         * loops through pathLengths, determines maximum path length (if the graph is disconnected, it will still determine maximum path length, 
         * because BFS will try its best to traverse the entire graph - thus, if there are disconnected components, those will be considered
         * the farthest away
         */
        int max_len = pathLengths.get(0); 
        for (int i = 1; i < pathLengths.size(); i++) {
            if (pathLengths.get(i) > max_len) {
                max_len = pathLengths.get(i); 
            }
        }
        System.out.println("Maximum path length from " + start_vert + ": " + max_len); 
        // loop through pathLengths again, determine people associated with maximum path length
        for (int i = 0; i < pathLengths.size(); i++) {
            if (max_len == pathLengths.get(i)) {
                String person = verts.get(i); 
                people.add(person); 
            }
        }
        // return the people with the maximum path length from the start_vert
        return people; 
    }

    /**
     * Main method for investigation of Wikipedia dataset
     */
    public static void main(String[] args) {
        /**
         * Tests reportMinMax, getAvg, and getPeople on the following queue of integers: [2, 3, 100, 1]
         */
        /*
        InvestigateWikipedia test1 = new InvestigateWikipedia("pantheon_edges_1000.csv"); 
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>(); 
        queue.enqueue(2); 
        queue.enqueue(3); 
        queue.enqueue(100); 
        queue.enqueue(1); 
        queue.enqueue(100); 
        test1.setIndegrees(queue); 
        System.out.println("Min and max of [2 ,3, 100, 1]: " + test1.reportMinMax("in")); 
        System.out.println("Avg of [2 ,3, 100, 1]: " + test1.getAvg("in")); 
        test1.getPeople(100); 
        */

        /**
         * Creation of InvestigateWikipedia object that uses GraphCreator to create a TGF file, an AdjListsGraph object, 
         * queues containing the outdegrees and indegrees of the AdjListsGraph object
         */
        System.out.println("Statistics of pantheon_edges_1000.csv"); 
        InvestigateWikipedia test = new InvestigateWikipedia("pantheon_edges_1000.csv"); 
        /*
        System.out.println("Min and max of indegrees: " + test.reportMinMax("in"));
        System.out.println("Avg of indegrees: " + test.getAvg("in")); 
        System.out.println("Min and max of outdegrees: " + test.reportMinMax("out")); 
        System.out.println("Avg of outdegrees: " + test.getAvg("out")); 
        System.out.println("The most important people have 40 indegrees."); 
        System.out.println("The most important people: " + test.getPeople(40)); 
        System.out.println("The people farthest with the maximum path length from the most important person: " + test.getFurthestPeople("Bill Clinton").toString());
        */
       System.out.println("Number of vertices: " + test.graph.getVertices().size()); 
    } 
}
