import java.util.*;

public class Calculations implements Comparable<Node> {

    //----- Variables -----//
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
        /*if(start == destination){
            h = 0;
        }else if(start == null){
            h = 0;
        }else if(destination == null){
            h = 0;
        }else {*/
            h = getDistance(start.getLongitude(), start.getLatitude(), destination.getLongitude(), destination.getLatitude());
        //}
        return h;
    }

    //------ Returns G-value for A* algorithm ------//
    public double calculateG(Node current){

        double totalg= 0;
        while (current.previous != null){
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

    //----- A* allgorithm -----//
    public List<Node> calculateAStar(Node start, Node destination){
        PriorityQueue<Node> visited = new PriorityQueue();
        PriorityQueue<Node> fValueList = new PriorityQueue();
        List<Node> hasBeenExplored = new ArrayList<>();
        hasBeenExplored.add(start);
        Node current = start;
        fValueList.add(current);
        double distanceToBeat;

        // this while-loop finds the first route to destination, but might not be the optimal route to take.
        while (fValueList.size()>0) {

            for (Node neighbor : current.getNeighbours()) {
                if (!fValueList.contains(neighbor) && !visited.contains(neighbor)) {
                    neighbor.setPrevious(current);
                    double h = calculateH(neighbor, destination);
                    double g = calculateG(neighbor);
                    double f = calculateF(h, g);
                    neighbor.setfValue(f);
                    if (!fValueList.contains(neighbor)) {
                        fValueList.add(neighbor);
                    }

                }
            }
            if (!visited.contains(current)) {
                visited.add(current);
            }

            fValueList.remove(current);
            current=fValueList.peek();
        }


        List<Node> route = updateRoute(destination,start);

        distanceToBeat= calculateG(destination);
        for(Node element: route){
            if(visited.contains(element)){
                hasBeenExplored.add(element);
                visited.remove(element);
            }
        }

        // In this while-loop we revisit Nodes with fvalues that are lower than the current best route
        // The best route's value is recorded in the variable distanceToBeat and the path to take is recorded in the variable route
        // If the while-loop finds a new optimal path it will updated route
        // Once completed it will return the value.
        while (visited.size()>0) {
            current = visited.peek();

            for (Node neighbor : current.getNeighbours()) {
                if(!neighbor.equals(current.previous)){
                    if(!neighbor.equals(start)) {

                        neighbor.setPrevious(current);
                        double g = calculateG(neighbor);
                        double h = calculateH(neighbor, destination);
                        neighbor.setfValue(calculateF(h,g));

                        if (neighbor.getfValue() < distanceToBeat) {

                            neighbor.setPrevious(current);
                            visited.add(neighbor);
                            if (neighbor.equals(destination)) {
                                route = updateRoute(destination, start);

                            }
                        }
                    }
                }

            }
            visited.remove();
        }

        return route;
    }

    //----- Method for calculating route to take from destination to start -----//
    public List<Node> updateRoute(Node destination, Node start){
        List<Node> route = new ArrayList<>();
        while(destination != start){

            route.add(destination);
            destination = destination.previous;
        }
        route.add(start);

        return route;
    }

    //----- Resets Node data for new calculation ------//
    public void resetNodes(){
        for (Node e: dataList){
            e.previous = null;
        }

    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}
