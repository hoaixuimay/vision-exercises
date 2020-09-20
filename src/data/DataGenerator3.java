
package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Same all connectivity
 * 1 more connect at the end
 */
public class DataGenerator3 {

    public static void main(String[] args) throws IOException{
        int N = 10000;

        FileOutputStream file = new FileOutputStream("tc5_input.txt");

        int count = 0;

        ArrayList<String> content = new ArrayList<String>();
        // connected
        for(int i = 1; i <= N; i=i+5){

            content.add((i+1) + " " + i);
            content.add((i+2) + " " + i);
            content.add((i+3) + " " + i);
            content.add((i+4) + " " + i);

            content.add((i+2) + " " + (i+1));
            content.add((i+3) + " " + (i+1));
            content.add((i+4) + " " + (i+1));

            content.add((i+3) + " " + (i+2));
            content.add((i+4) + " " + (i+2));

            content.add((i+4) + " " + (i+3));

                count = count+10;
            }

        // final connected
        content.add((N) + " " + (N-6));
        count++;


        file.write((N+"").getBytes());
        file.write("\n".getBytes());
        file.write((count+"").getBytes());
        file.write("\n".getBytes());

        for(String c: content){
            file.write(c.getBytes());
            file.write("\n".getBytes());
        }
        file.close();
    }
}