import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Graph {
    public static void main(String[] args){
        new Graph(args[0]);
    }

    public Graph(String input){
        LinkedList<Node> nodeList = CreateGraph(input);
        System.out.println(nodeList.size());
        for (Node node: nodeList) {
            System.out.println(node.toString());
        }
    }

    public LinkedList<Node> CreateGraph(String input){
        int count;
        String currentLine;
        String[] lineArray;

        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(input));
            count = Integer.parseInt(fileReader.readLine());
            LinkedList<Node> nodes = new LinkedList<>();
            for(int i = 0; i < count; i++){
                nodes.add(new Node(i+1));
            }

            int lineNumber = 0;
            while((currentLine = fileReader.readLine()) != null){
                lineArray = currentLine.split(" ");
                for(int i = 1; i < lineArray.length; i++){
                    nodes.get(lineNumber).addAdj(nodes.get(Integer.parseInt(lineArray[i]) - 1));
                    nodes.get(Integer.parseInt(lineArray[i]) - 1).addAdj(nodes.get(lineNumber));
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

    public void BreadthFirstSearch(Node input){
        int layer = 0;
        if(input.layer == -1){
            input.layer = layer;
        }
    }
}