import java.util.*;

public class Ui {

    public static void menu() {

        Calculations dataCalculations = new Calculations(DataSource.createDataSet());
        List<Node> cities = dataCalculations.dataList;
        //DataSource.printNodes(data.dataList);
        for(int i=0;i<cities.size();i++){
            //System.out.println(cities.get(i).getName());
        }
        //System.out.println(dataCalculations.getDistance(cities.get(0).longitude,cities.get(0).latitude,cities.get(1).longitude,cities.get(1).latitude));
        cities.get(1).setPrevious(cities.get(0));
        cities.get(2).setPrevious(cities.get(1));
        //System.out.println(dataCalculations.calculateH(cities.get(0),cities.get(1)));
        dataCalculations.calculateG(cities.get(2),cities.get(0));
    }
}
