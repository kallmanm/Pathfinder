import java.util.*;
import java.text.*;

public class Ui {

    public static void menu(){

        //Trims doubles to give more human readable format.
        DecimalFormat df = new DecimalFormat("#.##");

        //Loads in data nodes.
        List <Node> nodes = new ArrayList(Node.createGraph());

        String choice = "0";
        Scanner input= new Scanner(System.in);
        //System.out.println();


        //while(!choice.equals("3")){

            System.out.println(" -------------------------");
            System.out.println("|1. View Nodes            |");
            System.out.println("|2. Calculate Best Route  |");
            System.out.println("|3. Exit                  |");
            System.out.println(" -------------------------");

            choice = input.nextLine();

            switch(choice){

                case "1":
                    Node.printNodes(nodes);
                    break;

                case "2":
                    System.out.println("Choose a Starting Point & a Destination from the following Cities:");
                    int i = 0;
                    for(Node city:nodes){
                         i++;
                        System.out.println(i+". "+city.getName());
                    }
                    System.out.println("Enter in your Starting Point (type the numeric value next to city name):");
                    int start = Integer.parseInt(input.nextLine());

                    System.out.println("Enter in your Destination:");
                    int destination = Integer.parseInt(input.nextLine());

                    //Getting and setting start & destination Nodes
                    Node N = nodes.get((start-1));
                    Node destinationNode = nodes.get((destination-1));
                    N.setStartNode(N);
                    N.setCurrentNode(N);
                    N.setDestinationNode(destinationNode);

                    //Calculations start here
                    N.calculateAStar();

                    break;

                case "3":
                    System.out.println("Exiting Program, Goodbye!");
                    break;

                default:
                    System.out.println("Invalid Input, try again...");
                    break;

            }

        //}
    }
}
