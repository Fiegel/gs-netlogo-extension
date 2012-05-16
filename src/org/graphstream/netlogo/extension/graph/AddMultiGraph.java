package org.graphstream.netlogo.extension.graph;


import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;

/**
 * Implements the {@code add-multigraph} command.
 * 
 * Note : The added graph becomes the current one.
 * 
 * <pre>
 * gs:add-multigraph graphName
 * </pre>
 * 
 * @author Fiegel
 */

public class AddMultiGraph extends DefaultReporter {
    
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
        
        if(!GSManager.graphs.containsKey(graphName)) {
            Graph createdGraph = new MultiGraph(graphName);
            GSManager.graphs.put(graphName, createdGraph);
            GSManager.currentGraph = createdGraph;
            GSManager.currentGraphName = graphName;
        }
        else {
            status = false;
        }
        
        return status;
    }
}
