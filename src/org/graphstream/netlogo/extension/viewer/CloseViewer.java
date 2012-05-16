package org.graphstream.netlogo.extension.viewer;

import org.graphstream.netlogo.extension.GSManager;
import org.graphstream.ui.swingViewer.Viewer;
import org.nlogo.api.*;

/**
 * Implements the {@code close-viewer} command.
 * 
 * <pre>
 * gs:close-viewer viewerName
 * </pre>
 * 
 * @author Fiegel
 */


public class CloseViewer extends DefaultReporter {
    
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
        String viewerName;
        boolean status = true;
        
        try {
            viewerName = args[0].getString();
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        if(GSManager.viewers.containsKey(viewerName)) {
            Viewer viewerToClose = GSManager.viewers.remove(viewerName);
            viewerToClose.close();
            
            if(GSManager.currentViewerName.equals(viewerName)) {
                GSManager.currentViewer = null;
                GSManager.currentViewerName = null;
            }
        }
        else {
            status = false;
        }
        
        return status;
    }
}
