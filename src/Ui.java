import java.util.*;

public class Ui {

    public static void menu() {

        //---- Variables & Lists used to run the UI -----//
        Calculations dataCalculations = new Calculations(DataSource.createDataSet());
        List<Node> cities = dataCalculations.dataList;
        List<Node> result = new ArrayList<>();

        String choice = "0";
        Scanner input= new Scanner(System.in);

        //----- Main conditional loop for program options -----//
        while(!choice.equals("3")){

            System.out.println(" -------------------------");
            System.out.println("|1. View Nodes            |");
            System.out.println("|2. Calculate Best Route  |");
            System.out.println("|3. Exit                  |");
            System.out.println(" -------------------------");

            choice = input.nextLine();

            switch(choice){

                //----- Prints out to console all Nodes and their information -----//
                case "1":
                    DataSource.printNodes(cities);
                    break;

                //----- Runs the A* algorithm -----//
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

                    result = dataCalculations.calculateAStar(cities.get(start-1),cities.get(destination-1));
                    System.out.println("The optimal route is:");
                    for(int j = result.size()-1;j>=0;j--){
                        System.out.println((result.size()-j)+". "+result.get(j));
                    }
                    dataCalculations.resetNodes();
                    break;

                //----- Shuts down the UI interface -----//
                case "3":
                    System.out.println("Exiting Program, Goodbye!");
                    break;

                //----- default value in case of invalid input -----//
                default:
                    System.out.println("Invalid Input, try again...");
                    break;

            }
        }
    }
}
