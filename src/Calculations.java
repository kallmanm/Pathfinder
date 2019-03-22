import java.util.*;

public class Calculations implements Comparable<Node> {

    PriorityQueue<Node> fValues = new PriorityQueue();

    List<Node> dataList = new ArrayList<>();
    //Todo: A* calc

    //todo: Hcalc
    //todo: Gcalc
    //todo: Fcalc

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

    //Returns H-value for A* algorithm
    public double calculateH(Node start,Node destination){
        double h;
        if(start == destination){
            h = 0;
        }else {
            h = getDistance(start.getLongitude(), start.getLatitude(), destination.getLongitude(), destination.getLatitude());
        }
        return h;
    }


    @Override
    public int compareTo(Node o) {
        return 0;
    }
}