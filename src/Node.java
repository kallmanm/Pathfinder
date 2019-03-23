import java.util.*;


public class Node implements Comparable<Node> {
    //---- Variables for Node Class -----//

    protected String name;
    protected double latitude;
    protected double longitude;
    protected Node previous;
    protected double fValue;


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
    public void setfValue(double fValue) {
        this.fValue = fValue;
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
    public double getfValue() {
        return fValue;
    }

    //todo:fix
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node n = (Node) o;
        return Double.compare(n.fValue, fValue) == 0 &&
                Objects.equals(name, n.name);
    }
    //required override
    @Override
    public int hashCode() {
        return Objects.hash(name, fValue);
    }
    //required override
    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", fValue=" + fValue +
                '}';
    }

    //required override
    @Override
    public int compareTo(Node n) {
        if (this.getfValue() > n.getfValue()) {
            return 1;
        } else if (this.getfValue() < n.getfValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}
