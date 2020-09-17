package org.qpro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WidestPlace {
	
	public static void main(String[] args) throws IOException{
		FileInputStream fileIp = new FileInputStream("tc1_input.txt");
		
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileIp));

        String line = reader.readLine();
        int totalCitysNo = Integer.parseInt(line);
        
        int[][] realPaths = new int[totalCitysNo][totalCitysNo];
        
        line = reader.readLine();
        int totalPaths = Integer.parseInt(line);
        while((line = reader.readLine()) != null){
        	String[] params = line.split(" ");
            realPaths[Integer.valueOf(params[0])][Integer.valueOf(params[1])] = 1;
        	
        }
        
	}
}
