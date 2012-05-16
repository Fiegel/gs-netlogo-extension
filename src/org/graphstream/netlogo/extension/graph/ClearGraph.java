package org.graphstream.netlogo.extension.graph;




import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 * Implements the {@code clear-graph} command.
 * 
 * <pre>
 * gs:clear-graph graphName
 * </pre>
 * 
 * @author Fiegel
 */


public class ClearGraph extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }
    
    @Override
    public Syntax getSyntax() {
        int[] input = new int[] { Syntax.StringType() };
        int output = Syntax.BooleanType();
        
        return Syntax.reporterSyntax(input, output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String graphName;
        boolean status = true;
        
        try {
            graphName = args[0].getString();
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        try {
            GSManager.graphs.get(graphName).clear();
        }
        catch(NullPointerException e) {
            status = false;
        }
        
        return status;
    }
}
