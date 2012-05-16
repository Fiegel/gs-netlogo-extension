package org.graphstream.netlogo.extension.graph;


import java.util.List;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;

/**
 * Implements the {@code remove-edge} and {@code re} commands.
 * 
 * Remove an edge from the current graph.
 * 
 * <pre>
 * gs:remove-edge edgeAtt
 * gs:re edgeAtt
 * </pre>
 * 
 * @param edgeAtt Can be one String (name of the edge), a Link or a list of two String/Turtles/Int (names/ids of two nodes), but no type mixing 
 * 
 * @author Fiegel
 */

public class RemoveEdgeCurrent extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }
    
    @Override
    public Syntax getSyntax() {
        int[] input = new int[] { Syntax.StringType() | Syntax.LinkType() | Syntax.ListType() };
        int output = Syntax.BooleanType();
        
        return Syntax.reporterSyntax(input, output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String edgeName, node1Name = null, node2Name = null;
        boolean status = true;
        
        try {
            Object arg0 = args[0].get();
            
            if(arg0 instanceof String) {
                edgeName = args[0].getString();
                GSManager.currentGraph.removeEdge(edgeName);
            }
            else if(arg0 instanceof Link) {
                edgeName = "" + args[0].getLink().end1().id() + args[0].getLink().end2().id();
                GSManager.currentGraph.removeEdge(edgeName);
            }
            else {
                List l = args[0].getList();
                
                Object node1 = l.get(0), node2 = l.get(1);
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
                
                GSManager.currentGraph.removeEdge(node1Name, node2Name);
            }
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
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
