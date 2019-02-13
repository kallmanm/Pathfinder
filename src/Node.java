import java.util.*;

public class Node {

    //---- Variables -----
    protected String name;
    protected double latitude;
    protected double longitude;
    protected List<Node> neighbours = new ArrayList<>();

    //---- Constructor ----
    public Node(String name, double latitude, double longitude){
        setName(name);
        setLatitude(latitude);
        setLongitude(longitude);
        //addNeighbour(neighbour);
    }

    //---- Set Functions ----
    public void setName(String name){
        this.name = name;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public void setLongitude(double longitude){
        this.longitude =longitude;
    }

    //---- Add Functions ----
    public void addNeighbour(Node neighbour){
        //todo:fix this so can add many neighbours
        this.neighbours.add(neighbour);
    }

    //---- Get Functions ----
    public String getName(){
        return name;
    }
    public double getLatitude(){
        return latitude;
    }
    public double getLongitude(){
        return longitude;
    }
    public List<Node> getNeighbours(){
        return neighbours;
    }


    //---- Methods & Functions ----


    public static void printNodes(List<Node>nodes){
        for(Node node:nodes){
            System.out.println("------------------------");
            System.out.println("City Name: "+node.getName());
            System.out.println("City Neighbours: ");
            List <Node> nodeNeighbours = new ArrayList<>();
            nodeNeighbours = node.getNeighbours();

            for(Node neighbour:nodeNeighbours) System.out.println("     "+neighbour.getName());

            System.out.println("City Coordinates: ");
            System.out.println("     Latitude: "+node.getLatitude());
            System.out.println("     Longitude: "+node.getLongitude());
        }
    }


    public static ArrayList<Node> createGraph(){
        //todo:fix this method so it works as intended...
        Node hki = new Node("Helsingfors", 60.1640504, 24.7600896);
        Node tpe = new Node("Tammerfors", 61.6277369, 23.5501169);
        Node tku = new Node("Abo", 60.4327477, 22.0853171);
        Node jyv = new Node("Jyväskylä", 62.1373432, 25.0954598);
        Node kpo = new Node("Kuopio", 62.9950487, 26.556762);
        Node lhi = new Node("Lahtis", 60.9948736, 25.5747703);

        //Förbindelser från Helsingfors tågstation
        hki.addNeighbour(tpe); //Tammerfors
        hki.addNeighbour(tku); //Åbo
        hki.addNeighbour(lhi); //Lahtis
        //Förbindelser från Tammerfors tågstation
        tpe.addNeighbour(hki); //Helsingfors
        tpe.addNeighbour(tku); //Åbo
        tpe.addNeighbour(jyv); //Jyväskylä
        tpe.addNeighbour(lhi); //Lahtis
        //Förbindelser från Åbo tågstation
        tku.addNeighbour(hki); //Helsingfors
        tku.addNeighbour(tpe); //Tammerfors
        //Förbindelser från Jyväskylä tågstation
        jyv.addNeighbour(tpe); //Tammerfors
        //Förbindelser från Kuopio tågstation
        kpo.addNeighbour(lhi); //Lahtis
        //Förbindelser från Lahtis tågstation
        lhi.addNeighbour(hki); //Helsingors
        lhi.addNeighbour(tpe); //Tammerfors
        lhi.addNeighbour(kpo); //Kuopio

        ArrayList<Node> graph = new ArrayList();
        graph.add(hki);
        graph.add(tpe);
        graph.add(tku);
        graph.add(jyv);
        graph.add(kpo);
        graph.add(lhi);

        return graph;
    }
}
