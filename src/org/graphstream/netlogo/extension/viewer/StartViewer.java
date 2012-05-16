package org.graphstream.netlogo.extension.viewer;


import org.graphstream.netlogo.extension.GSManager;
import org.graphstream.ui.swingViewer.Viewer;
import org.nlogo.api.*;

/**
 * Implements the {@code start-viewer} command.
 * 
 * Note : The added viewer becomes the current one.
 * 
 * <pre>
 * gs:start-viewer graphInViewerName
 * </pre>
 * 
 * @author Fiegel
 */

public class StartViewer extends DefaultReporter {
    
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
        String graphInViewerName;
        boolean status = true;
        
        try {
            graphInViewerName = args[0].getString();            
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        if(!GSManager.viewers.containsKey(graphInViewerName) && GSManager.graphs.containsKey(graphInViewerName)) {
            Viewer createdViewer = GSManager.graphs.get(graphInViewerName).display();
            //createdViewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
            GSManager.viewers.put(graphInViewerName, createdViewer);
            GSManager.currentViewer = createdViewer;
            GSManager.currentViewerName = graphInViewerName;
        }
        else {
            status = false;
        }
        
        return status;
    }
}
