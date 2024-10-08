import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlRead {



    public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            System.out.println(reader);
            String line;

            while( (line = reader.readLine()) != null )  {

                if(line.contains("href")) {

                    int start = line.indexOf("href") +6;

                   // System.out.println(line);
                    String link = line.substring(start);
                    //int end = line.indexOf("\"");

                    int end = link.indexOf("\"");
                    if (end ==-1){
                        end = link.indexOf("'");
                    }

                    if (end==-1){
                        end = link.indexOf("--");
                    }



                    System.out.println(start + ","+end);
                    System.out.println(line.substring(start, start+end));
                }

            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
            System.out.println("double check your url and start again");
        }

    }

}
