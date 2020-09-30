
package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Connect latest to 1
 * connect more 500 connections
 */
public class DataGenerator5 {

    public static void main(String[] args) throws IOException{
        int N = 10000;

        int half = N / 2;
        FileOutputStream file = new FileOutputStream("tc7_input.txt");

        int count = 0;

        ArrayList<String> content = new ArrayList<String>();
        // connected
        for(int i = 1; i <= (half - 1); i++){
            for(int j =(i+1); j <= half; j++){
                String wr;
                wr = j + " " + i;
                    content.add(wr);
                count++;
            }
        }

        // partial connected
        int step = 100;
        for(int i = half + 1; i <= N; i = i + step){
            // 5001 -> 5100; 5101 -> 5200; ..
            int max = i + (step -1);
            for(int j = i; j < max; j++){
                for(int k = j+1; k <= max; k++ ){
                    String wr;

                    wr = k + " " + j;
                    content.add(wr);
                    count++;
                }
            }
        }

        content.add(N + " " + 1);
        count++;

        // connect more 500
        int mockCount = 0;
        for(int i = half + 1; i <= N; i = i + step){
            if(i-half > 1 && mockCount <= 500) {
                mockCount++;
                content.add(i + " " + (i - half));
                count++;
            }
        }

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