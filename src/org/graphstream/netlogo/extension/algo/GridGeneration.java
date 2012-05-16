package org.graphstream.netlogo.extension.algo;




import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.*;


/**
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
        int[] input = new int[] { Syntax.StringType() }; 
        int output = Syntax.BooleanType() ;
        
        return Syntax.reporterSyntax(input, output);
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        boolean status = true;
        String graphName;

        try {
            graphName = args[0].getString();
        }
        catch(LogoException le) {
            throw new ExtensionException(le.getMessage());
        }
        
        Generator gen = new GridGenerator();
        
        gen.addSink(GSManager.graphs.get(graphName));
        gen.begin();
        
        for(int i = 0; i < 10; i++) {
            gen.nextEvents();
        }
        
        gen.end();

        return status;
    }
}
