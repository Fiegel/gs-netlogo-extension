package org.graphstream.netlogo.extension.algo;




import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
 * Implements the {@code grid-generation} command.
 * 
 * <pre>
 * gs:grid-generation graphName size
 * (gs:grid-generation graphName size cross tore generateXY directed)
 * </pre>
 * 
 * @param graphName String. Id of the graph in which the grid will be generated
 * @param size int. Size of the generated grid (size + 1) * (size + 1) nodes ; size * size cells
 * @param cross int. 0 or 1. Create diagonal links?
 * @param tore int. 0 or 1. Close the grid as a tore?
 * @param generateXY int. 0 or 1. Generate coordinates of nodes?
 * @param directed int. 0 or 1. Are edges directed ?
 *
 * @author Fiegel
 */


public class GridGeneration extends DefaultReporter {
    
    @Override
    public String getAgentClassString() {
        return "O";
    }

    @Override
    public Syntax getSyntax() {
        int[] input = new int[] { Syntax.StringType(), Syntax.NumberType() | Syntax.RepeatableType() }; 
        int output = Syntax.BooleanType() ;
        
        return Syntax.reporterSyntax(input, output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        boolean status = true, cross, tore, generateXY, directed;
        String graphName;
        int gridSize;

        try {
            graphName = args[0].getString();
            gridSize = args[1].getIntValue();
            
            if(args.length == 2) {
                cross = false;
                tore = false;
                generateXY = false;
                directed = false;
            }
            else if(args.length == 6) {
                cross = args[2].getIntValue() != 0;
                tore = args[3].getIntValue() != 0;
                generateXY = args[4].getIntValue() != 0;
                directed = args[5].getIntValue() != 0;
            }
            else {
                throw new ExtensionException("Il doit y avoir 2 ou 6 arguments (String, int [, int, int, int, int])");
            }
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        Generator gen = new GridGenerator(cross, tore, generateXY, directed);
        
        gen.addSink(GSManager.graphs.get(graphName));
        gen.begin();
        
        for(int i = 0; i < gridSize; i++) {
            status = gen.nextEvents();
        }
        
        gen.end();

        return status;
    }
}
