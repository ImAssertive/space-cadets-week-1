import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.*;
import java.net.*;

public class Main{
    public static void main(String[] args) throws IOException {
        //To bypass University Firewall
        /*
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("proxyHost", "152.78.128.51");
        System.getProperties().put("proxyPort", "3128");
         */

        //Creating buffered reader to get user inputs
        BufferedReader inputsReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input user ID:");
        String userId = inputsReader.readLine();

        //Constructing URL bject using string concatination
        URL sotonURL = new URL("https://www.ecs.soton.ac.uk/people/" + userId);

        //Creating second buffered reader to read from web page
        BufferedReader urlReader = new BufferedReader(new InputStreamReader(sotonURL.openStream()));
        //Searching webpage for name
        boolean found = false;
        String line = "";
        while (!found){
            //Reads next html line and checks for presence of name property tag
            line = urlReader.readLine();
            Integer lineIndex = line.indexOf("property=\"name\">");
            if (lineIndex != -1){
                found = true;
                //Outputs relevant detail
                System.out.println(line.substring(lineIndex + 16, line.substring(lineIndex).indexOf("<") + lineIndex));
            }
        }
        urlReader.close();
    }
}