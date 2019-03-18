import java.util.*;
import java.text.*;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

public class Node {

    //---- Variables for Nodes -----//
    protected String name;
    protected double latitude;
    protected double longitude;

    //----- Calculation Variables -----//
    //totalG keeps track of total distance travel in the A* algorithm
    static double totalG = 0;
    static double totalH = 0;

    //----- Formating -----//
    DecimalFormat df = new DecimalFormat("#.##");

    //----- List & Hashmap Variables -----//
    protected List<Node> neighbours = new ArrayList<>();
    protected List<Node> closedNodes = new ArrayList<>();
    protected List<Node> openNodes = new ArrayList<>();
    protected List<Node> pathTraveled = new ArrayList<>();

    //todo:fix map, is being reset between every iteration
    protected Map<Node,Double> fValues = new HashMap();

    //----- Location tracking Variables -----//
    protected Node startNode;
    protected Node destinationNode;
    protected Node currentNode;
    protected Node previousNode;
    protected Node nextNode;

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
    //todo:fix g function
    public double calculateG(Node startNode, Node currentNode){

        double g =totalG;
        g = getDistance(startNode.getLongitude(), startNode.getLatitude(), currentNode.getLongitude(), currentNode.getLatitude());

        return g;


    }

    //todo: fix a linked list
    public double tcg(Node source){
        double G = 0;
        Node current = this;
        System.out.println("curent"+current.getName());
        while(current != source){
            G += calculateH(current, current.previousNode);
            current = current.previousNode;
        }
        return G;
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


    public void calculateAStar(){

        System.out.println("Your start point is "+currentNode.getName()+".");

        System.out.println("And your destination is "+destinationNode.getName()+".");
        totalH = calculateH(startNode,destinationNode);

        System.out.println("_____________________________________");

        while (currentNode != destinationNode){

            for (Node neighbour:currentNode.getNeighbours()){

                //add if statement current not in closedlist and not in openlist
                if(!closedNodes.contains(neighbour)){
                    double h = calculateH(neighbour,destinationNode);
                    double g = calculateG(currentNode,neighbour);
                    double f = calculateF(h,g);

                    fValues.put(neighbour, f);
                    if(!openNodes.contains(neighbour)){
                        openNodes.add(neighbour);
                    }
                    if(closedNodes.contains(previousNode)){
                        pathTraveled.remove(previousNode);
                    }
                }
            }

            // Rearranges Map into a sorted hashmap with values going from smallest to biggest
            Map<Node,Double> sortedFValues = fValues
                    .entrySet()
                    .stream()
                    .sorted(comparingByValue())
                    .collect(
                            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                    LinkedHashMap::new));
            nextNode =(Node) sortedFValues.keySet().toArray()[0];

            if(openNodes.contains(nextNode)){
                closedNodes.add(currentNode);
            }

            setPreviousNode(currentNode);

            //Add current to path traveled
            pathTraveled.add(currentNode);

            //Sets lowest f-value Node to current position
            setCurrentNode(nextNode);

            //removes used f-values from list
            System.out.println(fValues);
            fValues.remove(nextNode);
            System.out.println(fValues);

            if(currentNode == destinationNode){
                pathTraveled.add(destinationNode);
                System.out.println("Path traveled: ");
                System.out.println(">>"+startNode.getName());
                for(Node element: pathTraveled){
                    if(element != startNode) {
                        System.out.println(">>" + element.getName());
                    }
                }

            }
        }
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
            System.out.println("Neighbouring Cities: ");

            for(Node neighbour:node.getNeighbours()) System.out.println("     "+neighbour.getName());

            System.out.println("City Coordinates: ");
            System.out.println("     Latitude: "+node.getLatitude());
            System.out.println("     Longitude: "+node.getLongitude());
        }
    }


}