package org.graphstream.netlogo.extension;


import java.util.HashMap;
import java.util.Map;
import org.graphstream.graph.Graph;
import org.graphstream.netlogo.extension.algo.GridGeneration;
import org.graphstream.netlogo.extension.graph.*;
import org.graphstream.netlogo.extension.viewer.*;
import org.graphstream.ui.swingViewer.Viewer;
import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.PrimitiveManager;

/**
 *
 * @author Fiegel
 */

public class GSManager extends DefaultClassManager {

    public static Map<String, Graph> graphs = new HashMap<String, Graph>();
    public static Map<String, Viewer> viewers = new HashMap<String, Viewer>();
    
    public static Graph currentGraph;
    public static String currentGraphName;
    public static Viewer currentViewer;
    public static String currentViewerName;
    
    @Override
    public void load(PrimitiveManager primManager) {
        primManager.addPrimitive("add-singlegraph", new AddSingleGraph());
        primManager.addPrimitive("add-multigraph", new AddMultiGraph());
        primManager.addPrimitive("remove-graph", new RemoveGraph());
        primManager.addPrimitive("set-current-graph", new SetCurrentGraph());
        primManager.addPrimitive("graphs-names", new GetGraphsNames());
        primManager.addPrimitive("current-graph-name", new GetCurrentGraphName());
        primManager.addPrimitive("clear-current-graph", new ClearCurrentGraph());
        primManager.addPrimitive("clear-graph", new ClearGraph());
        
        primManager.addPrimitive("start-viewer", new StartViewer());
        primManager.addPrimitive("close-viewer", new CloseViewer());
        primManager.addPrimitive("set-current-viewer", new SetCurrentViewer());
        primManager.addPrimitive("viewers-names", new GetViewersNames());
        primManager.addPrimitive("current-viewer-name", new GetCurrentViewerName());
        
        primManager.addPrimitive("current-nodes-names", new GetCurrentNodesNames());
        primManager.addPrimitive("add-node", new AddNodeCurrent());
        primManager.addPrimitive("an", new AddNodeCurrent());
        primManager.addPrimitive("add-all-nodes", new AddAllNodesCurrent());
        primManager.addPrimitive("aan", new AddAllNodesCurrent());
        primManager.addPrimitive("add-node-to-graph", new AddNodeGraph());
        primManager.addPrimitive("ang", new AddNodeGraph());
        primManager.addPrimitive("remove-node", new RemoveNodeCurrent());
        primManager.addPrimitive("rn", new RemoveNodeCurrent());
        primManager.addPrimitive("remove-node-from-graph", new RemoveNodeGraph());
        primManager.addPrimitive("rng", new RemoveNodeGraph());
        
        primManager.addPrimitive("current-edges-names", new GetCurrentEdgesNames());
        primManager.addPrimitive("add-edge", new AddEdgeCurrent());
        primManager.addPrimitive("ae", new AddEdgeCurrent());
        primManager.addPrimitive("add-all-edges", new AddAllEdgesCurrent());
        primManager.addPrimitive("aae", new AddAllEdgesCurrent());
        primManager.addPrimitive("remove-edge", new RemoveEdgeCurrent());
        primManager.addPrimitive("re", new RemoveEdgeCurrent());
        primManager.addPrimitive("add-edge-to-graph", new AddEdgeGraph());
        primManager.addPrimitive("aeg", new AddEdgeGraph());
        primManager.addPrimitive("remove-edge-from-graph", new RemoveEdgeGraph());
        primManager.addPrimitive("reg", new RemoveEdgeGraph());
        
        primManager.addPrimitive("grid-generation", new GridGeneration());
    }
}
