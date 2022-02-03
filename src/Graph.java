import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph {

    public void main(String[] args){

    }

    public Graph(String input){
        int count;

        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(input));
            count = Integer.parseInt(fileReader.readLine());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
