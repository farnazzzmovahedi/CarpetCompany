import java.io.*;
import java.util.*;

public class CarpetCompany {
    public static void main(String[] args) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter("/D:/JAVA/CarpetCompany/carpets2.txt"));
//        for (int i = 0; i < 4; i++) {
//            AllCarpets.generateRandomCarpets();
//
//            StringBuilder builder = new StringBuilder();
//            for(int j = 0; j < AllCarpets.carpet.length; j++)//for each row
//            {
//                for(int k = 0; k < AllCarpets.carpet[j].length; k++)//for each column
//                {
//                    builder.append(AllCarpets.carpet[j][k]+"");//append to the output string
//                    if(k < AllCarpets.carpet[j].length - 1)//if this is not the last row element
//                        builder.append(" ");//then add comma (if you don't like commas you can use spaces)
//                }
//                builder.append("\n");//append new line at the end of the row
//            }
//            //builder.append((int)Math.floor(Math.random() * (1000 - 500 + 1) + 500) + "\n");
//            writer.write(builder.toString());//save the string representation of the board
//            //writer.write((int)200);
//            //writer.write("\n");
//
//
//        }
//        writer.close();
//        int [][]array = new int[5][6];
//        ArrayList<Integer> priceOfCarpets = new ArrayList<>();
//        File file = new File("carpets.txt");
//        Scanner sc = new Scanner(file);
//        while (sc.hasNextLine()){
//            for (int i = 0; i < 5; i++) {
//                for (int j = 0; j < 6; j++) {
//                    array[i][j] = sc.nextInt();
//                }
//            }
//            priceOfCarpets.add(sc.nextInt());
//            sc.nextLine();
////            for (int i = 0; i < array.length; i++) {
////                for (int j = 0; j < array[i].length; j++) {
////                    System.out.print(array[i][j] + " ");
////                }
////                System.out.println();
////            }
////            System.out.println();
//        }


        int [][]array = new int[300][400];
        ArrayList<Integer> priceOfCarpets = new ArrayList<>();
        File file = new File("carpets.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            for (int i = 0; i < 300; i++) {
                for (int j = 0; j < 400; j++) {
                    array[i][j] = sc.nextInt();
                }
            }
            priceOfCarpets.add(sc.nextInt());
            sc.nextLine();

            ListOfCarpets.showListOfCarpets(array);
        }

        File file2 = new File("carpets2.txt");
        Scanner sc2 = new Scanner(file2);
        while (sc2.hasNextLine()) {
            int [][]array2 = new int[4][5];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    array2[i][j] = sc2.nextInt();
                }
            }

            Carpet.carpets.add(new Carpet(array2));
            sc2.nextLine();
        }

//        for (int i = 0; i < Carpet.carpets.size(); i++) {
//            for (int j = 0; j < Carpet.carpets.get(i).length; j++) {
//                for (int k = 0; k < Carpet.carpets.get(i)[j].length; k++) {
//                    System.out.print(Carpet.carpets.get(i)[j][k] +" ");
//                }
//                System.out.println();
//            }
//        }

        Menu.mainMenu(priceOfCarpets);



//        int [][]array = new int[300][400];
//        File file = new File("carpets.txt");
//        Scanner sc = new Scanner(file);
//        while (sc.hasNextLine()){
//            for (int i = 0; i < 300; i++) {
//                for (int j = 0; j < 400; j++) {
//                    array[i][j] = sc.nextInt();
//                }
//            }
//            sc.nextLine();
//            Carpet carpet = new Carpet(array);
//            Carpet.carpets.add(carpet);

//        }
    }
}

abstract class Menu
{
    static Scanner sc = new Scanner(System.in);
    public static void mainMenu(ArrayList<Integer> priceOfCarpets) throws IOException {
        System.out.println("           ***           WELCOME TO OUR CARPET COMPANY!           ***           \n" +
                "                         YOU CAN DESIGN NEW CARPETS OR BUY SOME!\n" +
                "                         1 --> DESIGN A CARPET\n" +
                "                         2 --> BUY A CARPET\n" +
                "                         3 --> EXIT");
        int request = sc.nextInt();
        switch (request)
        {
            case 1:
                designACarpetMenu();
                break;
            case 2:
                buyACarpetMenu(priceOfCarpets);
                break;
            case 3:
                return;
            default:
                System.out.println("                         please enter the number correctly");
                mainMenu(priceOfCarpets);
        }
    }
    public static void designACarpetMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("           ***           DESIGNING A CARPET           ***           \n" +
                        "                         Enter the number of regions on your carpet: \n" +
                "                         This is the format --> 5 --> number of regions = 5 ");
        int numOfNodes = input.nextInt();
        int [][]graph = new int[numOfNodes][numOfNodes];
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numOfNodes; i++)
            nodes.add(new Node(i , -1));

        System.out.println("                         Enter the number of intersections between regions on your carpet: \n" +
                "                         This is the format --> 5 --> number of intersection = 5 ");
        int numOfEdges = input.nextInt();
        ArrayList<Edge> edges = new ArrayList<>();
        System.out.println("                         Enter the regions that are in the neighborhood together: \n" +
                "                         This is the format --> 0 3 --> 0 is in the neighborhood of 3 ");
        for (int i = 0; i < numOfEdges; i++) {
            Edge newEdge = new Edge(input.nextInt() , input.nextInt());
            edges.add(newEdge);
        }
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], 0);
        }
        for (int i = 0; i < edges.size(); i++)
            for (int j = 0; j < nodes.size(); j++)
                if (edges.get(i).src == (nodes.get(j).index))
                {
                    for (int k = 0; k < nodes.size(); k++)
                        if (edges.get(i).dest == nodes.get(k).index){
                            graph[nodes.get(j).index][nodes.get(k).index] = 1;
                            graph[nodes.get(k).index][nodes.get(j).index] = 1;
                        }
                }
//        for (int i = 0; i < graph.length; i++) {
//            for (int j = 0; j < graph[i].length; j++) {
//                System.out.print(graph[i][j] + "   ");
//            }
//            System.out.println();
//        }
        ColoringTheGraph coloringTheGraph = new ColoringTheGraph();
        coloringTheGraph.graphColoring(graph, numOfNodes);
    }
    public static void buyACarpetMenu(ArrayList<Integer> priceOfCarpets) throws IOException {
        System.out.println("           ***           BUYING A CARPET           ***           \n" +
                "                         YOU CAN CHOOSE BETWEEN THESE OPTIONS: \n" +
                "                         1 --> SEARCH BY CARPET DESIGN \n" +
                "                         2 --> SORT THE LIST OF CARPETS BASED ON YOUR BUDGET \n" +
                "                         3 --> FIND THE NEAREST SHOP \n" +
                "                         4 --> EXIT");
        int request = sc.nextInt();
        switch (request)
        {
            case 1:
                search();
                break;
            case 2:
                showListOfCarpets(priceOfCarpets);
                break;
            case 3:
                findNearestShop(priceOfCarpets);
                break;
            case 4:
                return;
            default:
                System.out.println("                         please enter the number correctly");
                buyACarpetMenu(priceOfCarpets);
        }
    }
    public static void search(){
        Scanner input = new Scanner(System.in);
        System.out.println("           ***           SEARCHING THE MOST SIMILAR CARPET TO YOUR IMAGINED ONE           ***           \n" +
                "                         Enter the number of rows: ");
        int rows = input.nextInt();
        System.out.println("                         Enter the number of columns: ");
        int columns = input.nextInt();
        System.out.println("                         Enter the pattern of your imagined carpet: \n" +
                "                         This is the format --> 0 1 1 1 ...  \n" +
                "                                                0 0 1 0 \n" +
                "                                                .\n" +
                "                                                .\n" +
                "                                                .");
        int [][] carpet = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                carpet[i][j] = input.nextInt();
            }
        }
        findTheMostSimilarCarpet.carpetSuggestion(carpet , rows, columns);

        for (int i = 0; i < findTheMostSimilarCarpet.similarityPercentage.size(); i++) {
            System.out.println((i+1) + ": " + findTheMostSimilarCarpet.similarityPercentage.get(i) + " -- id: " + Carpet.carpets.get(i).id);
        }


    }
    public static void showListOfCarpets(ArrayList<Integer> priceOfCarpets) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("           ***           SHOW LIST OF CARPETS BASED ON YOUR BUDGET           ***           \n" +
                "                         Enter the money that you have: \n");
        int yourBudget = input.nextInt();

        // Function call
        ListOfCarpets.quickSort(priceOfCarpets, 0, priceOfCarpets.size() - 1, ListOfCarpets.allCarpets);

        int maxPrice = 0;
        ArrayList<ArrayList<Integer>> yourCarpets = new ArrayList<>();
        for (int i = 0; i < ListOfCarpets.allCarpets.size(); i++) {
            if (priceOfCarpets.get(i) + maxPrice < yourBudget)
            {
                maxPrice = priceOfCarpets.get(i) + maxPrice;
                yourCarpets.add(ListOfCarpets.allCarpets.get(i));
            }
        }
        System.out.println("Your carpets:");
        for (int i = 0; i < yourCarpets.size(); i++) {
            System.out.println(i+1 + " :");
            for (int j = 0; j < yourCarpets.get(i).size(); j++) {

                System.out.print(yourCarpets.get(i).get(j) + " ");
                if ((j + 1) % 400 == 0){
                    System.out.println();
                }
            }
            System.out.println("price: " + priceOfCarpets.get(i));
            System.out.println();
        }
    }
    public static void findNearestShop(ArrayList<Integer> priceOfCarpets) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("           ***           FINDING THE NEAREST SHOP TO YOU           ***           \n" +
                "                         As you know there are 10 intersections in our town. \n" +
                "                         Enter the intersection that you are in it in rage (0 - 9): \n" +
                "                         Our shops are in intersections : 3 , 5 , 9! ");
        int yourIntersection = input.nextInt();
        int [][] city = City.readCityFromFile();

        TheNearestShop.dijkstra(city, yourIntersection);
        System.out.println("                         You can see the nearest one with the distance and the intersections that you have to go! \n");
        System.out.println("                         NOW YOU CAN CHOOSE BETWEEN THESE OPTIONS: \n" +
                "                         1 --> BACK TO MAIN MENU \n" +
                "                         2 --> EXIT");
        int request = sc.nextInt();
        switch (request)
        {
            case 1:
                mainMenu(priceOfCarpets);
                break;
            case 2:
                return;
            default:
                System.out.println("                         please enter the number correctly");
                findNearestShop(priceOfCarpets);
        }
    }
}
class Carpet {
    static int idCounter = 0;
    int id;
    int[][] pattern;

    static ArrayList<Carpet> carpets = new ArrayList<>();

    public Carpet(int[][] pattern) {
//        carpets.add(this);
        setId();
        setPattern(pattern);
    }

    public void setId() {
        this.id = ++idCounter;
    }

    public void setPattern(int[][] pattern) {
        this.pattern = pattern;
    }
}
class Edge {
    int src, dest;
    Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }
}
class Node{
    int index;
    int color;
    Node(int index, int color){
        this.index = index;
        this.color = color;
    }
}
class ColoringTheGraph{
    int []color;
    boolean isSafe(int currentVertex, int [][]graph, int []color, int currentColor, int numOfVertices)
    {
        for (int i = 0; i < numOfVertices; i++)
            if (graph[currentVertex][i] == 1 && currentColor == color[i])
                return false;
        return true;
    }
    int graphColoringUtil(int [][]graph, int []color, int currentVertex, int numOfVertices)
    {
        if (currentVertex == numOfVertices)
            return 0;
        for (int c = 1; c <= numOfVertices; c++) {
            if (isSafe(currentVertex, graph, color, c, numOfVertices)) {
                color[currentVertex] = c;
                if (graphColoringUtil(graph, color, currentVertex + 1, numOfVertices) == 0)
                    return 0;
                color[currentVertex] = 0;
            }
        }
        return -1;
    }
    void graphColoring(int [][]graph, int numOfVertices)
    {
        color = new int[numOfVertices];
        for (int i = 0; i < numOfVertices; i++)
            color[i] = 0;
        graphColoringUtil(graph, color, 0, numOfVertices);
        printSolution(color, numOfVertices);
    }
    void printSolution(int color[], int numOfVertices)
    {
        System.out.println("The minimum colors that you can color your carpet with them is:");
        for (int i = 0; i < numOfVertices; i++)
            System.out.print(" " + color[i] + " ");
        System.out.println();
    }
}
class findTheMostSimilarCarpet{
    static ArrayList<Integer> similarityPercentage = new ArrayList();
    static void carpetSuggestion(int[][] basePattern, int rows, int columns) {

        int[] oneDimentionalPattern = new int[rows * columns];
        int i, j, k = 0;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                k = i * columns + j;
                oneDimentionalPattern[k] = basePattern[i][j];
                k++;
            }
        }
        for (int l = 0; l < Carpet.carpets.size(); l++) {
            int z = 0;
            int [][] patternToCompare = Carpet.carpets.get(l).pattern;
            int[] oneDtoCompare = new int[patternToCompare.length * patternToCompare[0].length];
            for (int m = 0; m < patternToCompare.length; m++) {
                for (int n = 0; n < patternToCompare[m].length; n++) {
                    z = m * patternToCompare[0].length + n;
                    oneDtoCompare[z] = patternToCompare[m][n];
                    z++;
                }
            }
            int[][] opt = new int[oneDtoCompare.length + 1][oneDimentionalPattern.length + 1];
            //oneDimentionalPattern ---> user carpet
            //oneDtoCompare ---> file
            for (int m = 0; m <= oneDtoCompare.length; m++) {
                opt[m][oneDimentionalPattern.length] = 2 * (oneDtoCompare.length - m);
            }
            for (int m = 0; m <= oneDimentionalPattern.length; m++) {
                opt[oneDtoCompare.length][m] = 2 * (oneDimentionalPattern.length - m);
            }
            for (int m = oneDtoCompare.length - 1; m >= 0 ; m--) {
                for (int n = oneDimentionalPattern.length - 1; n >= 0 ; n--) {
                    int penalty = 0;
                    if (oneDtoCompare[m] != oneDimentionalPattern[n])
                        penalty = 1;
                    opt[m][n] = Math.min(Math.min( opt[m + 1][n + 1] + penalty , opt[m + 1][n] + 2 ), opt[m][n + 1] + 2);
                }
            }
            similarityPercentage.add(opt[0][0]);
            //System.out.println(similarityPercentage.get(l));
        }
        quickSort(similarityPercentage, 0, similarityPercentage.size() - 1);
    }
    static void swap(ArrayList<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
    static int partition(ArrayList<Integer> similarityPercentage, int low, int high) {
        int pivot = similarityPercentage.get(high);

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (similarityPercentage.get(j) < pivot) {
                i++;
                swap(similarityPercentage, i, j);
                Carpet temp = Carpet.carpets.get(i);
                Carpet.carpets.set(i, Carpet.carpets.get(j));
                Carpet.carpets.set(j, temp);
            }
        }
        swap(similarityPercentage, i + 1, high);
        Carpet temp = Carpet.carpets.get(i+1);
        Carpet.carpets.set(i+1, Carpet.carpets.get(high));
        Carpet.carpets.set(high, temp);
        return (i + 1);
    }
    static void quickSort(ArrayList<Integer> similarityPercentage, int low, int high) {
        if (low < high) {
            int pi = partition(similarityPercentage, low, high);
            quickSort(similarityPercentage, low, pi - 1);
            quickSort(similarityPercentage, pi + 1, high);
        }
    }
}
class ListOfCarpets {
    static ArrayList<ArrayList<Integer>> allCarpets = new ArrayList<>();

    static ArrayList<Integer> flatten(int[][] data) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                list.add(data[i][j]);
            }
        }

        return list;
    }

    static void showListOfCarpets(int[][] array) throws FileNotFoundException {

        allCarpets.add(flatten(array));
    }

    static void swap(ArrayList<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
    static int partition(ArrayList<Integer> arr, int low, int high , ArrayList<ArrayList<Integer>> carpets) {
        int pivot = arr.get(high);

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr.get(j) < pivot) {
                i++;
                swap(arr, i, j);
                ArrayList<Integer> temp = carpets.get(i);
                carpets.set(i, carpets.get(j));
                carpets.set(j, temp);
            }
        }
        swap(arr, i + 1, high);
        ArrayList<Integer> temp = carpets.get(i+1);
        carpets.set(i+1, carpets.get(high));
        carpets.set(high, temp);
        return (i + 1);
    }
    static void quickSort(ArrayList<Integer> arr, int low, int high, ArrayList<ArrayList<Integer>> carpets) {
        if (low < high) {
            int pi = partition(arr, low, high, carpets);
            quickSort(arr, low, pi - 1, carpets);
            quickSort(arr, pi + 1, high, carpets);
        }
    }
}
class City {
    static int[][] city = new int[10][10];

    static int[][] readCityFromFile() throws IOException {
        File file = new File("city2.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    city[i][j] = sc.nextInt();
                }
            }
            sc.nextLine();
        }

            for (int i = 0; i < city.length; i++) {
                for (int j = 0; j < city[i].length; j++) {
                    System.out.print(city[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        return city;

    }
}
class TheNearestShop{
    private static final int NO_PARENT = -1;
    static void dijkstra(int[][] adjacencyMatrix,
                                 int startVertex)
    {
        int nVertices = adjacencyMatrix[0].length;
        int[] shortestDistances = new int[nVertices];

        boolean[] added = new boolean[nVertices];

        for (int vertexIndex = 0; vertexIndex < nVertices;
             vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        shortestDistances[startVertex] = 0;

        int[] parents = new int[nVertices];

        parents[startVertex] = NO_PARENT;

        for (int i = 1; i < nVertices; i++)
        {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                if (!added[vertexIndex] &&
                        shortestDistances[vertexIndex] <
                                shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
            added[nearestVertex] = true;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0
                        && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }

        printSolution(startVertex, shortestDistances, parents);
    }
    private static void printSolution(int startVertex,
                                      int[] distances,
                                      int[] parents)
    {
        int nVertices = distances.length;
        System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++)
        {
            if (vertexIndex != startVertex && (vertexIndex == 3 || vertexIndex == 5 || vertexIndex == 9))
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }

    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private static void printPath(int currentVertex,
                                  int[] parents)
    {

        // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }
}
class AllCarpets{
    static int [][]carpet = new int[4][5];

    static void generateRandomCarpets(){
        int min = 0; // Minimum value of range
        int max = 1; // Maximum value of range
        // Print the min and max
        //System.out.println("Random value in int from "+ min + " to " + max + ":");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                // Generate random int value from min to max
                int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
                carpet[i][j] = random_int;
            }
        }
//        for (int i = 0; i < allCarpets.size(); i++) {
//            for (int j = 0; j < 300; j++) {
//                for (int k = 0; k < 400; k++) {
//                    ////System.out.print(allCarpets.get(i).carpet[j][k] + "     ");
//                }
//                System.out.println();
//            }
//        }

    }
}