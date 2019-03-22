import java.util.*;


public class Node {
    //---- Variables for Node Class -----//

    protected String name;
    protected double latitude;
    protected double longitude;
    protected Node previous;


    //----- List & Hashmap Variables -----//
    protected List<Node> neighbours = new ArrayList<>();

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
    public void setNeighbour(Node neighbour){

        this.neighbours.add(neighbour);
    }
    public void setPrevious(Node previous){
        this.previous = previous;
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
    public Node getPrevious(){
        return  previous;
    }
}
