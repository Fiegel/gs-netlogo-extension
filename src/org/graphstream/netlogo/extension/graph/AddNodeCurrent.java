package org.graphstream.netlogo.extension.graph;


import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;

/**
 * Implements the {@code add-node} and {@code an} commands.
 * 
 * Add a new node to the current graph.
 * 
 * <pre>
 * gs:add-node nodeName
 * gs:an nodeName
 * </pre>
 * 
 * @param nodeName Can be a String (name of the node) or a Turtle (which id will become the name of the node)
 * 
 * @author Fiegel
 */

public class AddNodeCurrent extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }

    @Override
    public Syntax getSyntax() {
        int[] input = new int[] { Syntax.StringType() | Syntax.TurtleType() };
        int output = Syntax.BooleanType();
        
        return Syntax.reporterSyntax(input, output);
    }
    
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String nodeName;
        boolean status = true;
        
        try {
            Object arg = args[0].get();
            
            if(arg instanceof String) {
                nodeName = args[0].getString();
            }
            else {
                nodeName = "" + args[0].getTurtle().id();
            }
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        try {
            GSManager.currentGraph.addNode(nodeName);
        }
        catch(IdAlreadyInUseException e) {
            status = false;
        }
        catch(NullPointerException e) {
            status = false;
        }
        
        return status;
    }
}
