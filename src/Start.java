
import java.text.*;
import java.util.*;

public class Start {

    public static void main(String [] args){

        //Trims doubles to give more human readable format.
        DecimalFormat df = new DecimalFormat("#.##");

        //Loads in data nodes.
        List <Node> nodes = new ArrayList(Node.createGraph());

        Node.printNodes(nodes);

        double test = nodes.get(0).calculateH(nodes.get(0),nodes.get(3));
        System.out.println("Distance between "+nodes.get(0).getName()+" and " + nodes.get(3).getName()+" is: "+df.format(test)+" km.");

    }
}
