import java.util.*;

public class Calculations implements Comparable<Node> {

    //----- Variables -----//
    double startDestinationHvalue;

    //todo:fix priorityQueue
    PriorityQueue<Node> fValues = new PriorityQueue();
    List<Node> dataList = new ArrayList<>();
    Node start;

    //---- Constructor ----//
    public Calculations(List<Node> dataList){
        setData(dataList);

    }

    //---- Set Functions ----//
    public void setData(List<Node> dataList){
        this.dataList = dataList;
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

    //------ Returns H-value for A* algorithm ------//
    public double calculateH(Node start,Node destination){
        double h;
        if(start == destination){
            h = 0;
        }else if(start == null){
            h = 0;
        }else if(destination == null){
            h = 0;
        }else {
            h = getDistance(start.getLongitude(), start.getLatitude(), destination.getLongitude(), destination.getLatitude());
        }
        return h;
    }

    //------ Returns G-value for A* algorithm ------//

    public double calculateG(Node current){

        double totalg= 0;
        while (current.previous != start){
            totalg += calculateH(current,current.previous);
            current = current.previous;
        }

        return totalg;
    }

    //------ Returns F-value for A* alogrithm ------//
    public double calculateF(double h, double g){
        double f = h + g;
        return f;
    }

    public List<Node> calculateAStar(Node start, Node destination){
        List<Node> candidates = new ArrayList<>();
        List<Node> visited = new ArrayList<>();
        Node current = start;
        boolean done = false;
        System.out.println(start.getName()+" >>> "+destination.getName());
        this.start = start;

        while(done == false){
            double minF= 0;
            Node next = null;

            for (Node neighbor : current.getNeighbours()) {
                if(!candidates.contains(neighbor) && !visited.contains(neighbor)){
                    candidates.add(neighbor);
                    neighbor.previous= current;
                }
            }
            for (Node element: candidates){
                if(element == destination){
                    done = true;
                    double e = calculateF(calculateH(element,destination),calculateG(element));
                    element.setfValue(e);
                }else{
                    double f = calculateF(calculateH(element,destination),calculateG(element));
                    if(minF == 0 || minF>f){
                        minF = f;
                        element.setfValue(minF);
                        next = element;
                    }
                }
            }

            if(done == false){
                current=next;
                visited.add(current);
                candidates.remove(current);
            }
        }
        List<Node> route = new ArrayList<>();
        current = destination;
        while(current != start){

            route.add(current);
            current = current.previous;
        }
        route.add(start);

        return route;
    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}
