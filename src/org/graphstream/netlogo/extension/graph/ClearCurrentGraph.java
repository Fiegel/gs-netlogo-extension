package org.graphstream.netlogo.extension.graph;




import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 * Implements the {@code clear-current-graph} command.
 * 
 * <pre>
 * gs:clear-current-graph
 * </pre>
 * 
 * @author Fiegel
 */


public class ClearCurrentGraph extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }
    
    @Override
    public Syntax getSyntax() {
        int output = Syntax.BooleanType();
        
        return Syntax.reporterSyntax(output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        boolean status = true;
        
        if(GSManager.currentGraph != null) {
            GSManager.currentGraph.clear();
        }
        else {
            status = false;
        }
        
        return status;
    }
}
