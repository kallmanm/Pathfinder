import java.util.*;

public class Node {

    //---- Variables -----
    protected String name;
    protected double latitude;
    protected double longitude;
    protected List<Node> neighbours = new ArrayList<>();
    protected Node startVertex;
    protected Node destination;
    protected Node currentVertex;

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
    public void setStartVertex(Node startVertex){
        this.startVertex = startVertex;
    }
    public void setDestination(Node destination){
        this.destination = destination;
    }
    public void setCurrentVertex(Node currentVertex){
        this.currentVertex = currentVertex;
    }

    //---- Add Functions ----
    public void addNeighbour(Node neighbour){

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
    public Node getStartVertex(){
        return startVertex;
    }
    public Node getDestination(){
        return destination;
    }
    public Node getCurrentVertex(){
        return currentVertex;
    }

    //---- Other Methods & Functions ----

    //Returns the distance between two locations based on their latitude and longitude coordinates
    public double getDistance(double lon1, double lat1, double lon2, double lat2){
        lon1 = lon1*Math.PI/180.0;
        lat1 = lat1*Math.PI/180.0;
        lon2 = lon2*Math.PI/180.0;
        lat2 = lat2*Math.PI/180.0;


        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double km = 6367 * c;

        return km;
    }

    //returns H-value of A* algorithm
    //todo: optimize calculateH
    public double calculateH(Node currentVertex,Node destination){

        return getDistance(currentVertex.getLongitude(),currentVertex.getLatitude(),destination.getLongitude(),destination.getLatitude());
    }

    //returns G-value of A* algorithm
    //todo: optimize calculateG
    public double calculateG(Node startVertex){

        //calculate from start all distance traveled via different nodes
        return getDistance(startVertex.getLongitude(),startVertex.getLatitude(),this.getLongitude(),this.getLatitude());
    }

    //returns F-value of A* algorithm
    //todo: optimzie calculateF
    public double calculateF(Node startVertex,Node destination, Node currentVertex){
        //F = G + H
        double f = calculateG(startVertex) + calculateH(currentVertex,destination);
        return f;
    }

    //Todo: create A* algorithm function
    public void calculateAStar(){
        //todo:make code
    }

    //Creates the graph data used in this assignment
    public static ArrayList<Node> createGraph(){

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

    //Prints out in console all Nodes and all relating information to the Nodes
    public static void printNodes(List<Node>nodes){
        for(Node node:nodes){
            System.out.println("-----------------------------");
            System.out.println("City Name: "+node.getName());
            System.out.println("City Neighbours: ");

            for(Node neighbour:node.getNeighbours()) System.out.println("     "+neighbour.getName());

            System.out.println("City Coordinates: ");
            System.out.println("     Latitude: "+node.getLatitude());
            System.out.println("     Longitude: "+node.getLongitude());
        }
    }
}
