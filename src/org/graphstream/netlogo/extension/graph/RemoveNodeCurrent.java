package org.graphstream.netlogo.extension.graph;


import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;

/**
 * Implements the {@code remove-node} and {@code rn} commands.
 * 
 * Remove a node to the current graph.
 * 
 * <pre>
 * gs:remove-node nodeName
 * gs:rn nodeName
 * </pre>
 * 
 * @param nodeName Can be a String (name of the node), a Int (index of the node) or a Turtle (which id is the name of the node)
 * 
 * @author Fiegel
 */

public class RemoveNodeCurrent extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }
    
    @Override
    public Syntax getSyntax() {
        int[] input = new int[] { Syntax.StringType() | Syntax.NumberType() | Syntax.TurtleType() };
        int output = Syntax.BooleanType();
        
        return Syntax.reporterSyntax(input, output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        boolean status = true;
        
        try {
            Object arg = args[0].get();
            
            if(arg instanceof String) {
                String nodeName = args[0].getString();
                GSManager.currentGraph.removeNode(nodeName);
            }
            else if(arg instanceof Turtle) {
                String nodeName = "" + args[0].getTurtle().id();
                GSManager.currentGraph.removeNode(nodeName);
            }
            else {
                int nodeId = args[0].getIntValue();
                GSManager.currentGraph.removeNode(nodeId);
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
