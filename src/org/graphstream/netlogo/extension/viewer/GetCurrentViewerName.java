package org.graphstream.netlogo.extension.viewer;




import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 * Implements the {@code get-current-viewer-name} command.
 * 
 * <pre>
 * gs:current-viewer-name
 * </pre>
 * 
 * @return String: Name of the current viewer
 * 
 * @author Fiegel
 */


public class GetCurrentViewerName extends DefaultReporter {
    
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
        return GSManager.currentViewerName;
    }
}
