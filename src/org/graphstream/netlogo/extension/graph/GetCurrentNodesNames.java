package org.graphstream.netlogo.extension.graph;


import org.graphstream.graph.Node;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 *
 * @author Fiegel
 */


public class GetCurrentNodesNames extends DefaultReporter {
    
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
            for(Node n : GSManager.currentGraph.getNodeSet()) {
                names.add(n.getId());
            }
        }

        return names.toLogoList();
    }
}
