import java.util.*;

public class Calculations implements Comparable<Node> {

    //----- Variables -----//
    double startDestinationHvalue;

    //todo:fix priorityQueue
    PriorityQueue<Node> fValues = new PriorityQueue();
    List<Node> dataList = new ArrayList<>();
    List<Node> closedCityList = new ArrayList<>();
    Double fValueToBeat;

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
    //todo:fix calcG
    //remove one input parameter and see if it fixes the NullPointError
    public double calculateG(Node current){
        //System.out.println("--------------*****-----------------");
        double totalg= 0;

        while (current.previous != null){
            //System.out.println("Calculating distance from: "+ current.getName()+" Via ->>");
            totalg += calculateH(current,current.previous);
            current = current.previous;
        }
        //System.out.println("Arriving at: "+ current.getName());
        //System.out.println("total g is: "+ totalg);
        return totalg;
    }

    //------ Returns F-value for A* alogrithm ------//
    public double calculateF(double h, double g){
        double f = h + g;
        return f;
    }

    public String calculateAStar(Node start, Node destination){
        System.out.println("Starting Point: "+start.getName()+" - to Destination: "+destination.getName());
        startDestinationHvalue = calculateH(start,destination);
        System.out.println("The Heuristic distance from Start to Destination is: "+startDestinationHvalue);
        String fastestPath="placeholder value";
        Node current = start;

        while(current != destination){
            System.out.println("i am in: "+ current.getName());
            for(Node neighbor:current.getNeighbours()){
                if(!closedCityList.contains(neighbor)){
                    neighbor.setPrevious(current);
                    System.out.println("suggestion: "+current.getName()+" -> "+neighbor.getName());
                    double h = calculateH(neighbor,destination);
                    double g = calculateG(neighbor);
                    double f = calculateF(h,g);
                    neighbor.setfValue(f);

                    //System.out.println("h: "+h);
                    //System.out.println("g: "+g);
                    System.out.println("f: "+f);
                    fValues.add(neighbor);
                }
            }
            System.out.println("fvaluelist: "+fValues);
            closedCityList.add(current);

            System.out.println("Moving to next city: "+fValues.peek().getName());
            System.out.println("-----------");
            current = fValues.peek();
            fValues.remove();

        }
        fValueToBeat=current.fValue;
        System.out.println("f-value to beat still: "+fValueToBeat);
        System.out.println("fvaluelist: "+fValues);


        return fastestPath;
    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}
//System.out.println("fvalue List atm: ");
//for (Node e: fValues) {
//    System.out.println(e.getName());
//    System.out.println(e.fValue);
//}
            /*while(!fValues.isEmpty()){
                for (Node e: fValues) {
                    System.out.println(e.getName());
                    System.out.println(e.fValue);
                }
                System.out.println("the peek atm is: "+fValues.peek().getName());
                System.out.println("----------");
                fValues.remove();
            }*/