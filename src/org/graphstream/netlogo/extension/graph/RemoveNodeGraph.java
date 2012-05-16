package org.graphstream.netlogo.extension.graph;


import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;

/**
 * Implements the {@code remove-node-from-graph} and {@code rng} commands.
 * 
 * Remove a node to the specified graph.
 * 
 * <pre>
 * gs:remove-node-from-graph nodeName graphName
 * gs:rng nodeName graphName
 * </pre>
 * 
 * @param nodeName Can be a String (name of the node), a Int (index of the node) or a Turtle (which id is the name of the node)
 * @param graphName A String. Name of the graph from which the specified node will be removed.
 * 
 * @author Fiegel
 */

public class RemoveNodeGraph extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }
    
    @Override
    public Syntax getSyntax() {
        int[] input = new int[] { Syntax.StringType() | Syntax.NumberType() | Syntax.TurtleType(), Syntax.StringType() };
        int output = Syntax.BooleanType();
        
        return Syntax.reporterSyntax(input, output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        boolean status = true;
        String graphName;
        
        try {
            Object arg = args[0].get();
            graphName = args[1].getString();
            
            if(arg instanceof String) {
                String nodeName = args[0].getString();
                GSManager.graphs.get(graphName).removeNode(nodeName);
            }
            else if(arg instanceof Turtle) {
                String nodeName = "" + args[0].getTurtle().id();
                GSManager.graphs.get(graphName).removeNode(nodeName);
            }
            else {
                int nodeId = args[0].getIntValue();
                GSManager.graphs.get(graphName).removeNode(nodeId);
            }
        }
        catch(ElementNotFoundException e) {
            status = false;
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        return status;
    }
}
