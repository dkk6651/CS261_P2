import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph {

    public static void main(String[] args){
        new Graph(args[0]);
    }

    public Graph(String input){
        Node[] nodes = CreateGraph(input);
        System.out.println(nodes.length);
        for (Node node: nodes) {
            System.out.println(node.toString());
        }
    }

    public Node[] CreateGraph(String input){
        int count;
        String currentLine;
        String[] lineArray;

        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(input));
            count = Integer.parseInt(fileReader.readLine());
            Node[] nodes = new Node[count];
            for(int i = 0; i < count; i++){
                nodes[i] = new Node(i+1);
            }

            int lineNumber = 0;
            while((currentLine = fileReader.readLine()) != null){
                lineArray = currentLine.split(" ");
                for(int i = 1; i < lineArray.length; i++){
                    nodes[lineNumber].addAdj(nodes[Integer.parseInt(lineArray[i])-1]);
                    nodes[Integer.parseInt(lineArray[i])-1].addAdj(nodes[lineNumber]);
                }
                lineNumber++;
            }
            return nodes;
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
