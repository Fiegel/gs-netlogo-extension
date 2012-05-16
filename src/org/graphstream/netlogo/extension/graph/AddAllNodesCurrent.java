package org.graphstream.netlogo.extension.graph;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;

/**
 * Implements the {@code add-all-nodes} and {@code aan} commands.
 * 
 * Add a bunch of new nodes to the current graph. All kinds of types are accepted.
 * 
 * <pre>
 * gs:add-all-nodes nodeName
 * gs:add-all-nodes nodeNameList
 * gs:add-all-nodes turtleSet
 * (gs:add-all-nodes node1Name (list node2Name node3Name) turtleSet...)
 * gs:aan nodeName
 * ...
 * </pre>
 * 
 * @author Fiegel
 */

public class AddAllNodesCurrent extends DefaultReporter {
    
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
        Set<String> names = new HashSet<String>();
        boolean status = true;
        
        try {
            for(Argument arg : args) {
                Object currentArg = arg.get();
                
                if(currentArg instanceof String) {
                    names.add(arg.getString());
                }
                else if(currentArg instanceof Turtle) {
                    names.add("" + arg.getTurtle().id());
                }
                else if(currentArg instanceof LogoList) {
                    List l = arg.getList();
                    
                    for(Object currentItem : l) {
                        if(currentItem instanceof String) {
                            names.add((String) currentItem);
                        }
                        else if(currentItem instanceof Turtle) {
                            names.add("" + ((Turtle) currentItem).id());
                        }
                        else if(currentItem instanceof AgentSet) {
                            for(Agent currentAgent : ((AgentSet) currentItem).agents()) {
                                names.add("" + ((Turtle) currentAgent).id());
                            }
                        }
                    }
                }
                else if(currentArg instanceof AgentSet) {
                    AgentSet as = arg.getAgentSet();
                    
                    for(Agent currentAgent : as.agents()) {
                        names.add("" + ((Turtle) currentAgent).id());
                    }
                }
            }
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        for(String currentName : names) {        
            try {
                GSManager.currentGraph.addNode(currentName);
            }
            catch(IdAlreadyInUseException e) {
                status = false;
            }
            catch(NullPointerException e) {
                status = false;
            }
        }
        
        return status;
    }
}
