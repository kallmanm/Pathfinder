import java.util.*;
import java.text.*;

public class Node {

    //---- Variables for Nodes -----//
    protected String name;
    protected double latitude;
    protected double longitude;

    //----- Calculation Variables -----//
    //totalG keeps track of total distance travel in the A* algorithm
    protected double totalG = 0;
    //totalH keeps track of the heuristic value fron start to destination
    protected double totalH = 0;

    //----- Formating -----//
    DecimalFormat df = new DecimalFormat("#.##");

    //----- List & Hashmap Variables -----//
    protected List<Node> neighbours = new ArrayList<>();
    protected HashMap<String,Node> openNodes = new HashMap();
    protected HashMap<String,Node> closedNodes = new HashMap();
    protected HashMap<Node,Double> fValues = new HashMap();

    //----- Location tracking Variables -----//
    protected Node startNode;
    protected Node destinationNode;
    protected Node currentNode;
    protected Node previousNode;

    //---- Constructor ----//
    public Node(String name, double latitude, double longitude){
        setName(name);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    //---- Set Functions ----//
    public void setName(String name){
        this.name = name;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public void setLongitude(double longitude){
        this.longitude =longitude;
    }

    public void setStartNode(Node startNode){
        this.startNode = startNode;
    }
    public void setDestinationNode(Node destinationNode){
        this.destinationNode = destinationNode;
    }
    public void setCurrentNode(Node currentNode){
        this.currentNode = currentNode;
    }
    public void setPreviousNode(Node previousNode){
        this.previousNode = previousNode;
    }

    //---- Add Functions ----//
    public void addNeighbour(Node neighbour){

        this.neighbours.add(neighbour);
    }

    //---- Get Functions ----//
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
    public Node getStartNode(){
        return startNode;
    }
    public Node getDestinationNode(){
        return destinationNode;
    }
    public Node getCurrentNode(){
        return currentNode;
    }
    public Node getPreviousNode(){
        return previousNode;
    }

    //---- Other Methods & Functions ----//

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

    //Returns H-value for A* algorithm
    public double calculateH(Node currentNode,Node destinationNode){
        double h;
        if(currentNode == destinationNode){
             h = 0;
        }else {
            h = getDistance(currentNode.getLongitude(), currentNode.getLatitude(), destinationNode.getLongitude(), destinationNode.getLatitude());
        }
        return h;
    }

    //Returns G-value for A* algorithm
    public double calculateG(Node currentNode, Node previousNode){

        double g;
        if(currentNode == previousNode || previousNode == null){
            g = 0;
        }else{
            g = getDistance(currentNode.getLongitude(), currentNode.getLatitude(), previousNode.getLongitude(), previousNode.getLatitude());
        }

        return g;
    }

    //Adds g value to totalG variable
    public void updateTotalG(double g){
        totalG += g;
    }

    //returns F-value for A* algorithm
    public double calculateF(double h, double g){
        double f = h + g;
        return f;
    }

    //Todo: create A* algorithm function
    public void calculateAStar(){
        System.out.println("Your start point is "+currentNode.getName()+".");
        System.out.println("And your destination is "+destinationNode.getName()+".");
        totalH = calculateH(startNode,destinationNode);
        System.out.println("The starting heuristic value is "+df.format(totalH)+" km.");
        while (currentNode != destinationNode){
            System.out.println("Current Node is "+currentNode);
            System.out.println("Neighbouring cities of Current Node:");
            for (Node neighbour:currentNode.getNeighbours()){

                //add if statement current not in closedlist and not in openlist
                //if(Check if not on lists-> then do..){
                    System.out.println(neighbour.getName());
                    double h = calculateH(neighbour,destinationNode);
                    double g = calculateG(neighbour,currentNode);
                    double f = calculateF(h,g);
                    System.out.println("H is:"+df.format(h));
                    System.out.println("G is:"+df.format(g));
                    System.out.println("F is:"+df.format(f));
                        //if(f < existing f or f is null)
                        //update fValues
                //}
            }
            //add the current Node to closedlist
            //select node from fvalues with lowest f score and make currentNode
            //here to break the while-loop
            currentNode = destinationNode;
        }

    }

    //Creates the graph data used in this assignment
    //todo: add more nodes.
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
            System.out.println("Neighbouring Cities: ");

            for(Node neighbour:node.getNeighbours()) System.out.println("     "+neighbour.getName());

            //System.out.println("City Coordinates: ");
            //System.out.println("     Latitude: "+node.getLatitude());
            //System.out.println("     Longitude: "+node.getLongitude());
        }
    }
}
