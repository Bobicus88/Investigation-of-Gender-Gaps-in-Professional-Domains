/**
 * Reads through each line of the Wikipedia dataset, creates "WikipediaGraph.tgf" files
 * from the provided data
 *
 * @author Leah Teffera and Alina Zheng
 * @version 4/27/20
 */

import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException; 
public class GraphCreator
{
    // instance variables 
    private String fileName; 
    private AdjListsGraph<String> graph; 

    /**
     * Constructor for objects of class GraphCreator
     * @param fileN the name of the file to read and create 
     * a TGF file from
     */
    public GraphCreator(String fileN)
    {
        this.fileName = fileN;
        graph = readFile(); 
    }

    /**
     * Gets graph of this GraphCreator object
     * @return the graph
     */
    public AdjListsGraph<String> getGraph() {
        return this.graph; 
    }

    /**
     * Reads through each line of the Wikipedia dataset, creates nodes for the "from_name" and "to_name" strings on each line,
     * connects those nodes by making an arc between them
     * @return the graph generated from the Wikipedia dataset
     */
    public AdjListsGraph<String> readFile() {
        AdjListsGraph<String> graph = new AdjListsGraph<String>(); 
        try {
            File file = new File(fileName); 
            Scanner scan = new Scanner(file);
            String[] temp = scan.nextLine().split(",");
            while (scan.hasNext()) {
                String[] temp1 = scan.nextLine().split(","); 
                String vert1 = temp1[0]; 
                String vert2 = temp1[1]; 
                graph.addVertex(vert1); 
                graph.addVertex(vert2); 
                graph.addArc(vert1, vert2);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e); 
            System.out.println("The program terminates."); 
            System.exit(0);    
        }
        return graph; 
    }

    public static void main(String[] args) {
        /**
         * Produces WikipediaGraph TGF file from pantheon_edges_1000 using the saveToTGF function
         */
        GraphCreator wiki = new GraphCreator("pantheon_edges_1000.csv"); 
        AdjListsGraph<String> graph = wiki.readFile();
        graph.saveToTGF("WikipediaGraph.tgf");
        
        /**
         * Produces WikipediaGraph TGF file from pantheon_edges_all using the saveToTGF function
         * Heap error 
         */
        /*
        GraphCreator wiki1 = new GraphCreator("pantheon_edges_all.csv"); 
        AdjListsGraph<String> graph = wiki1.readFile(); 
        graph.saveToTGF("WikipediaGraph.tgf");
         */

        /**
         * Test on small graph
         */
        GraphCreator test1 = new GraphCreator("testtable_edges_10.csv"); 
        AdjListsGraph<String> graph1 = test1.readFile(); 
        graph1.saveToTGF("test1.tgf"); 
        // Tests what BFS will do when told to travel from a start_vert to a disconnected end_vert - returns the longest path that it can 
        System.out.println(graph1.BreadthFirstSearch("Andy Warhol", "Pope Leo III").toString()); 

    } 
}
