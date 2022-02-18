import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Graph {
    int count;

    public static void main(String[] args){
        new Graph(args[0]);
    }

    public Graph(String input){
        LinkedList<Node> nodeList = CreateGraph(input);
        System.out.println(nodeList.size());
        for(Node node: nodeList) {
            System.out.println(node.toString());
        }
        System.out.println("");
        int component = 1;
        for(Node node: nodeList){
            if(node.layer == -1){
                System.out.printf("connected component: %d\n", component);
                BreadthFirstSearch(node);
                System.out.println("");
                if(IsBipartite(node)){
                    System.out.println("bipartite");
                }
                else{
                    System.out.println("not bipartite");
                }
                component++;
            }
        }
    }

    public LinkedList<Node> CreateGraph(String input){
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

        Node temp = input;
        temp.layer = layer;
        layer++;
        System.out.printf("%d(%d) ", temp.id, temp.layer);
        for(Node child : temp.adj){
            child.layer = layer;
        }
        LinkedList<Node> queue = new LinkedList<Node>(temp.adj);

        while(!queue.isEmpty()){
            layer++;
            temp = queue.remove();
            System.out.printf("%d(%d) ", temp.id, temp.layer);
            for(Node child : temp.adj){
                if(child.layer == -1 && !queue.contains(child)){
                    child.layer = layer;
                    queue.add(child);
                }
            }
        }
    }

    public boolean IsBipartite(Node input){
        int[] colorArr = new int[count];
        for(int i = 0; i < count; i++){
            colorArr[i] = -1;
        }

        colorArr[(input.id)-1] = 1;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(input);

        while(queue.size() != 0){
            Node temp = queue.poll();
            for(Node child : temp.adj){
                if(colorArr[temp.id - 1] == 1){
                    if(colorArr[child.id - 1] != -1){
                        return false;
                    }
                    colorArr[child.id - 1] = 0;
                }
                else{
                    if(colorArr[child.id - 1] != -1){
                        return false;
                    }
                    colorArr[child.id - 1] = 1;
                }
            }
        }
        return true;
    }
}