package org.graphstream.netlogo.extension.graph;


import org.graphstream.graph.Edge;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 *
 * @author Fiegel
 */


public class GetCurrentEdgesNames extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }

    @Override
    public Syntax getSyntax() {
        int output = Syntax.ListType() ;
        
        return Syntax.reporterSyntax(output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        LogoListBuilder names = new LogoListBuilder();   

        if(GSManager.currentGraph != null) {
            for(Edge e : GSManager.currentGraph.getEdgeSet()) {
                names.add(e.getId());
            }
        }

        return names.toLogoList();
    }
}
