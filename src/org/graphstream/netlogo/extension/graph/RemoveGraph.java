package org.graphstream.netlogo.extension.graph;




import org.graphstream.graph.Graph;
import org.graphstream.netlogo.extension.GSManager;
import org.graphstream.ui.swingViewer.Viewer;
import org.nlogo.api.*;


/**
 * Implements the {@code remove-graph} command.
 * 
 * Note: In a Viewer is currently displaying the graph, it is closed.
 * 
 * <pre>
 * gs:remove-graph graphName
 * </pre>
 * 
 * @author Fiegel
 */


public class RemoveGraph extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }

    @Override
    public Syntax getSyntax() {
        int[] input = new int[] { Syntax.StringType() }; 
        int output = Syntax.BooleanType() ;
        
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
        
        if(GSManager.graphs.containsKey(graphName)) {
            if(GSManager.viewers.containsKey(graphName)) {
                Viewer viewerToClose = GSManager.viewers.remove(graphName);
                viewerToClose.close();
                
                if(GSManager.currentViewerName.equals(graphName)) {
                    GSManager.currentViewer = null;
                    GSManager.currentViewerName = null;
                }
            }
            
            Graph graphRemoved = GSManager.graphs.remove(graphName);
            graphRemoved.clear();
            
            if(GSManager.currentGraphName.equals(graphName)) {
                GSManager.currentGraph = null;
                GSManager.currentGraphName = null;
            }
        }
        else {
            status = false;
        }

        return status;
    }
}
