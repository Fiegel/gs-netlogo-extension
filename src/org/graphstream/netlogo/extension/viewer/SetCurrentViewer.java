package org.graphstream.netlogo.extension.viewer;


import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;

/**
 * Implements the {@code set-current-viewer} command.
 * 
 * <pre>
 * gs:set-current-viewer viewerName
 * </pre>
 * 
 * @author Fiegel
 */


public class SetCurrentViewer extends DefaultReporter {
    
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
            GSManager.currentViewer = GSManager.viewers.get(viewerName);
            GSManager.currentViewerName = viewerName;
        }
        else {
            status = false;
        }
        
        return status;
    }
}
