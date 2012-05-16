package org.graphstream.netlogo.extension.viewer;




import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 * Implements the {@code get-viewers-names} command.
 * 
 * <pre>
 * gs:viewers-names
 * </pre>
 * 
 * @return LogoList of the names of the created Viewers 
 * 
 * @author Fiegel
 */


public class GetViewersNames extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }

    @Override
    public Syntax getSyntax() {
        int output = Syntax.ListType();
        
        return Syntax.reporterSyntax(output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        LogoListBuilder viewersNames = new LogoListBuilder();

        for(String currentName : GSManager.viewers.keySet()) {
            viewersNames.add(currentName);
        }
        
        return viewersNames.toLogoList();
    }
}
