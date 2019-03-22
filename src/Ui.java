import java.util.*;

public class Ui {

    public static void menu() {

        Calculations dataCalculations = new Calculations(DataSource.createDataSet());
        List<Node> cities = dataCalculations.dataList;
        //DataSource.printNodes(data.dataList);
        for(int i=0;i<cities.size();i++){
            System.out.println(cities.get(i).getName());
        }
        System.out.println(dataCalculations.getDistance(cities.get(0).longitude,cities.get(0).latitude,cities.get(1).longitude,cities.get(1).latitude));
        System.out.println(dataCalculations.calculateH(cities.get(0),cities.get(1)));
    }
}
