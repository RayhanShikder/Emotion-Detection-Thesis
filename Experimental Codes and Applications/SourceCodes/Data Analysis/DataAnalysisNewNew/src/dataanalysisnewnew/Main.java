/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysisnewnew;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Shiku
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        separateData();
    }

    public static void separateData() throws FileNotFoundException, IOException {


        File fin = new File("lessDominantEmotion.txt");
        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        //BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/questions.txt")));

        String line = null;
        int count = 0;
        String str = "";
        int t = -1;


        String prevVal = "";
       File fins = new File("results/results10classsss.txt");
       PrintWriter commonWriter = new PrintWriter(fins, "UTF-8");
        while ((line = br.readLine()) != null) {

            String[] singleData = line.split(" ");




        

//            PrintWriter commonWriter = new PrintWriter("resluts10classs1.txt");
            if (!singleData[0].equals("Sorry") && !prevVal.equals(singleData[0])) {
                commonWriter.close();
                File fins2 = new File("results/resultsLessDominantEmotion"+count+".txt");

                PrintWriter writer = new PrintWriter(fins2, "UTF-8");
                commonWriter = writer;

                prevVal = singleData[0];
                count++;

            }

            for (int i = 0; i < singleData.length; i++) {

                if(singleData[0].equals("Sorry"))break;

                commonWriter.print(singleData[i] + " ");


            }
            commonWriter.println();
        }
    }
}
