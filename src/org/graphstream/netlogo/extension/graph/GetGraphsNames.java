package org.graphstream.netlogo.extension.graph;




import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 * Implements the {@code get-graphs-names} command.
 * 
 * <pre>
 * gs:graphs-names
 * </pre>
 * 
 * @return LogoList of the names of the added graphs 
 * 
 * @author Fiegel
 */


public class GetGraphsNames extends DefaultReporter {
    
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
        LogoListBuilder graphsNames = new LogoListBuilder();

        for(String currentName : GSManager.graphs.keySet()) {
            graphsNames.add(currentName);
        }
        
        return graphsNames.toLogoList();
    }
}
