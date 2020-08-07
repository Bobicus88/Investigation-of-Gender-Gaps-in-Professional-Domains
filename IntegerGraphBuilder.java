
/**
 * Write a description of class IntegerGraphBuilder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IntegerGraphBuilder extends GraphBuilder<Integer>
{
    public IntegerGraphBuilder() {
        
    }
    
    public Integer createOneThing(String s) {
        return Integer.parseInt(s); 
    }
    
}
