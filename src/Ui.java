import java.util.*;

public class Ui {

    public static void menu() {

        Calculations dataCalculations = new Calculations(DataSource.createDataSet());
        List<Node> cities = dataCalculations.dataList;

        String choice = "0";
        Scanner input= new Scanner(System.in);

        while(!choice.equals("3")){

            System.out.println(" -------------------------");
            System.out.println("|1. View Nodes            |");
            System.out.println("|2. Calculate Best Route  |");
            System.out.println("|3. Exit                  |");
            System.out.println(" -------------------------");

            choice = input.nextLine();

            switch(choice){

                case "1":
                    //DataSource.printNodes(cities);
                    double a=dataCalculations.calculateH(cities.get(3),cities.get(1));
                    double b=dataCalculations.calculateH(cities.get(1),cities.get(0));
                    System.out.println(a+b);
                    double c=dataCalculations.calculateH(cities.get(3),cities.get(1));
                    double d=dataCalculations.calculateH(cities.get(1),cities.get(5));
                    double e=dataCalculations.calculateH(cities.get(5),cities.get(0));
                    System.out.println(c+d+e);
                    break;

                case "2":
                    System.out.println("Choose a Starting Point & a Destination from the following Cities:");
                    int i = 0;
                    for(Node city:cities){
                        i++;
                        System.out.println(i+". "+city.getName());
                    }
                    System.out.println("Enter in your Starting Point (type the numeric value next to city name):");
                    int start = Integer.parseInt(input.nextLine());
                    System.out.println("Enter in your Destination:");
                    int destination = Integer.parseInt(input.nextLine());

                    dataCalculations.calculateAStar(cities.get(start-1),cities.get(destination-1));
                    break;

                case "3":
                    System.out.println("Exiting Program, Goodbye!");
                    break;

                default:
                    System.out.println("Invalid Input, try again...");
                    break;

            }

        }

    }
}
//0.helsinki
//1.tammerfors
//2.åbo
//3.jyvä
//4.kuopio
//5.lahtis

//System.out.println(dataCalculations.getDistance(cities.get(0).longitude,cities.get(0).latitude,cities.get(1).longitude,cities.get(1).latitude));
//cities.get(0).setPrevious(cities.get(2));
//cities.get(1).setPrevious(cities.get(0));
//cities.get(3).setPrevious(cities.get(1));
//System.out.println(dataCalculations.calculateH(cities.get(0),cities.get(1)));
//dataCalculations.calculateG(cities.get(3),cities.get(0));
//cities.get(0).setPrevious(cities.get(2));
//cities.get(1).setPrevious(cities.get(0));
//System.out.println(dataCalculations.calculateG(cities.get(0)));