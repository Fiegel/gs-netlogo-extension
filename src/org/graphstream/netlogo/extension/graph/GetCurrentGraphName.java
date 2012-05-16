package org.graphstream.netlogo.extension.graph;




import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 * Implements the {@code get-current-graph-name} command.
 * 
 * <pre>
 * gs:current-graph-name
 * </pre>
 * 
 * @return String: Name of the current graph
 * 
 * @author Fiegel
 */


public class GetCurrentGraphName extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }

    @Override
    public Syntax getSyntax() {
        int output = Syntax.StringType();
        
        return Syntax.reporterSyntax(output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        return GSManager.currentGraphName;
    }
}
