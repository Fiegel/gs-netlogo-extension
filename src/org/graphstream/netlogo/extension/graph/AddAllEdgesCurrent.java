package org.graphstream.netlogo.extension.graph;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 * Implements the {@code add-all-edges} and {@code aae} commands.
 * 
 * Add a bunch of new edges to the current graph. All kinds of types are accepted.
 * 
 * <pre>
 * gs:add-all-edges link1
 * gs:add-all-edges linkList
 * gs:add-all-edges linkSet
 * (gs:add-all-edges link1 (list link2 link3) linkSet...)
 * gs:aae link1
 * ...
 * </pre>
 * 
 * @param link1 A Link or 1 String, 2 String/Turtles/Int (no type mixing) and 1 boolean (names of the edge, names/ids of first node and second node + directed or not)
 * 
 * @author Fiegel
 */


public class AddAllEdgesCurrent extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }

    @Override
    public Syntax getSyntax() {
        int[] input = new int[] { Syntax.WildcardType() | Syntax.RepeatableType() };
        int output = Syntax.BooleanType();
        
        return Syntax.reporterSyntax(input, output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        List<String> names = new ArrayList<String>();
        List<String> node1Names = new ArrayList<String>();
        List<String> node2Names = new ArrayList<String>();
        List<Boolean> directed = new ArrayList<Boolean>();
        boolean status = true;
        
        try {
            for(Argument arg : args) {
                Object currentArg = arg.get();
                
                if(currentArg instanceof Link) {
                    Link l = arg.getLink();
                    String node1Name = "" + l.end1().id();
                    String node2Name = "" + l.end2().id();
                    node1Names.add(node1Name);
                    node2Names.add(node2Name);
                    names.add(node1Name + node2Name);
                    directed.add(l.isDirectedLink());
                }
                else if(currentArg instanceof AgentSet) {
                    AgentSet as = arg.getAgentSet();
                    
                    for(Agent currentAgent : as.agents()) {
                        Link l = (Link) currentAgent;
                        String node1Name = "" + l.end1().id();
                        String node2Name = "" + l.end2().id();
                        node1Names.add(node1Name);
                        node2Names.add(node2Name);
                        names.add(node1Name + node2Name);
                        directed.add(l.isDirectedLink());
                    }
                }
                else if(currentArg instanceof LogoList) {
                    List list = arg.getList();
                    
                    if(list.get(0) instanceof String || list.get(0) instanceof Turtle || list.get(0) instanceof Double) {
                        names.add((String) list.get(0));

                        Object node1 = list.get(1);
                        Object node2 = list.get(2);
                        if(node1 instanceof String && node2 instanceof String) {
                            node1Names.add((String) node1);
                            node2Names.add((String) node2);
                        }
                        else if(node1 instanceof Turtle && node2 instanceof Turtle) {
                            node1Names.add("" + ((Turtle) node1).id());
                            node2Names.add("" + ((Turtle) node2).id());
                        }
                        else if(node1 instanceof Double && node2 instanceof Double) {
                            node1Names.add(GSManager.currentGraph.getNode(((Double) node1).intValue()).getId());
                            node2Names.add(GSManager.currentGraph.getNode(((Double) node2).intValue()).getId());
                        }

                        directed.add((Boolean) list.get(3));
                    }
                    else {
                        for(Object currentItem : list) {
                            if(currentItem instanceof Link) {
                                Link l = (Link) currentItem;
                                String node1Name = "" + l.end1().id();
                                String node2Name = "" + l.end2().id();
                                node1Names.add(node1Name);
                                node2Names.add(node2Name);
                                names.add(node1Name + node2Name);
                                directed.add(l.isDirectedLink());
                            }
                            else if(currentItem instanceof AgentSet) {
                                AgentSet as = (AgentSet) currentItem;
                    
                                for(Agent currentAgent : as.agents()) {
                                    Link l = (Link) currentAgent;
                                    String node1Name = "" + l.end1().id();
                                    String node2Name = "" + l.end2().id();
                                    node1Names.add(node1Name);
                                    node2Names.add(node2Name);
                                    names.add(node1Name + node2Name);
                                    directed.add(l.isDirectedLink());
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        for(int i = 0; i < names.size(); i++) {
            try {
                GSManager.currentGraph.addEdge(names.get(i), node1Names.get(i), node2Names.get(i), directed.get(i).booleanValue());
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
        }
        
        return status;
    }
}
