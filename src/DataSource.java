import java.util.*;

public class DataSource {

    //----- Creates the graph data used in this assignment -----//
    public static ArrayList<Node> createDataSet(){

        Node hki = new Node("Helsingfors", 60.1640504, 24.7600896);
        Node tpe = new Node("Tammerfors", 61.6277369, 23.5501169);
        Node tku = new Node("Abo", 60.4327477, 22.0853171);
        Node jyv = new Node("Jyväskylä", 62.1373432, 25.0954598);
        Node kpo = new Node("Kuopio", 62.9950487, 26.556762);
        Node lhi = new Node("Lahtis", 60.9948736, 25.5747703);

        //Förbindelser från Helsingfors tågstation
        hki.setNeighbour(tpe); //Tammerfors
            hki.setNeighbour(tku); //Åbo
        hki.setNeighbour(lhi); //Lahtis
        //Förbindelser från Tammerfors tågstation
        tpe.setNeighbour(hki); //Helsingfors
        tpe.setNeighbour(tku); //Åbo
        tpe.setNeighbour(jyv); //Jyväskylä
        tpe.setNeighbour(lhi); //Lahtis
        //Förbindelser från Åbo tågstation
        tku.setNeighbour(hki); //Helsingfors
        tku.setNeighbour(tpe); //Tammerfors
        //Förbindelser från Jyväskylä tågstation
        jyv.setNeighbour(tpe); //Tammerfors
        //Förbindelser från Kuopio tågstation
        kpo.setNeighbour(lhi); //Lahtis
        //Förbindelser från Lahtis tågstation
        lhi.setNeighbour(hki); //Helsingors
        lhi.setNeighbour(tpe); //Tammerfors
        lhi.setNeighbour(kpo); //Kuopio

        ArrayList<Node> graph = new ArrayList();
        graph.add(hki);
        graph.add(tpe);
        graph.add(tku);
        graph.add(jyv);
        graph.add(kpo);
        graph.add(lhi);

        return graph;
    }

    //----- Prints out in console all Nodes and all relating information to the Nodes -----//
    public static void printNodes(List<Node>nodes){
        for(Node node:nodes){
            System.out.println("-----------------------------");
            System.out.println("City Name: "+node.getName());
            System.out.println("Neighbouring Cities: ");

            for(Node neighbour:node.getNeighbours()) System.out.println("     "+neighbour.getName());

            System.out.println("City Coordinates: ");
            System.out.println("     Latitude: "+node.getLatitude());
            System.out.println("     Longitude: "+node.getLongitude());
        }
    }
}
