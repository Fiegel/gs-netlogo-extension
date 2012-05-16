package org.graphstream.netlogo.extension.graph;


import java.util.List;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;

/**
 * Implements the {@code add-edge} and {@code ae} commands.
 * 
 * Add a new edge to the current graph.
 * 
 * <pre>
 * gs:add-edge edgeAtt
 * gs:ae edgeAtt
 * </pre>
 * 
 * @param edgeAtt A Link or 1 String, 2 String/Turtles/Int (no type mixing) and 1 boolean (names of the edge, names/ids of first node and second node + directed or not)
 * 
 * @author Fiegel
 */

public class AddEdgeCurrent extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }

    @Override
    public Syntax getSyntax() {
        int[] input = new int[] { Syntax.LinkType() | Syntax.ListType() };
        int output = Syntax.BooleanType();
        
        return Syntax.reporterSyntax(input, output);
    }
    
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String edgeName, node1Name = null, node2Name = null;
        boolean directed, status = true;
        
        try {
            if(args[0].get() instanceof Link) {
                Link l = args[0].getLink();
                node1Name = "" + l.end1().id();
                node2Name = "" + l.end2().id();
                edgeName = node1Name + node2Name;
                directed = l.isDirectedLink();
            }
            else {
                List l = args[0].getList();
                
                edgeName = (String) l.get(0);
                
                Object node1 = l.get(1);
                Object node2 = l.get(2);
                if(node1 instanceof String && node2 instanceof String) {
                    node1Name = (String) node1;
                    node2Name = (String) node2;
                }
                else if(node1 instanceof Turtle && node2 instanceof Turtle) {
                    node1Name = "" + ((Turtle) node1).id();
                    node2Name = "" + ((Turtle) node2).id();
                }
                else if(node1 instanceof Double && node2 instanceof Double) {
                    node1Name = GSManager.currentGraph.getNode(((Double) node1).intValue()).getId();
                    node2Name = GSManager.currentGraph.getNode(((Double) node2).intValue()).getId();
                }
                
                directed = ((Boolean) l.get(3)).booleanValue();
            }
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        try {
            GSManager.currentGraph.addEdge(edgeName, node1Name, node2Name, directed);
        }
        catch(IdAlreadyInUseException e) {
            status = false;
        }
        catch(NullPointerException e) {
            status = false;
        }
        catch(ElementNotFoundException e) {
            status = false;
        }
        
        return status;
    }
}
