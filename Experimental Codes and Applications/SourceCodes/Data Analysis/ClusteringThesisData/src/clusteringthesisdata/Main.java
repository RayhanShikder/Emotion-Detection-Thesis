/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clusteringthesisdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author Shiku
 */
public class Main {

    /**
     * @param args the command line arguments
     */
        static ArrayList<String>steps;
    public static ArrayList<Double> mouseClickAvg = new ArrayList<Double>();
    public static ArrayList<Double> duration = new ArrayList<Double>();
    public static ArrayList<Double> leftClick = new ArrayList<Double>();
    public static ArrayList<Double> rightClick = new ArrayList<Double>();
    public static ArrayList<Double> doubleClick = new ArrayList<Double>();
    public static ArrayList<Double> mouseScroll = new ArrayList<Double>();
    public static ArrayList<Double> cursorXDist = new ArrayList<Double>();
    public static ArrayList<Double> cursorYDist = new ArrayList<Double>();
    public static ArrayList<Double> upToDown = new ArrayList<Double>();
    public static ArrayList<Double> downToDown = new ArrayList<Double>();
    public static ArrayList<Double> downToUp = new ArrayList<Double>();
    public static ArrayList<Double> ctrlKey = new ArrayList<Double>();
    public static ArrayList<Double> enterKey = new ArrayList<Double>();
    public static ArrayList<Double> fnKey = new ArrayList<Double>();
    public static ArrayList<Double> backSpace = new ArrayList<Double>();
    public static ArrayList<Double> arrowKey = new ArrayList<Double>();
    public static ArrayList<Double> regularKey = new ArrayList<Double>();
    public static ArrayList<Double> idleTime = new ArrayList<Double>();
    public static ArrayList<String>emotionLabel=new ArrayList<String>();
    public static ArrayList<String>userLabel=new ArrayList<String>();
    public static ArrayList<String>GenderLabel=new ArrayList<String>();
    public static ArrayList<String>newEmotionLabel=new ArrayList<String>();


    public static ArrayList<ArrayList<Double>>SingleDataForSingleEmotion = new ArrayList<ArrayList<Double>>();


    public static int totalCount;
    public static int totalUser=22;

    public static ArrayList<ArrayList<Double>>AllData= new ArrayList<ArrayList<Double>>();

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       ParseAndFilter.filterData();
       ParseAndFilter.singleData();
       totalCount=mouseClickAvg.size();
       Classifier.classify();
//       plotPoints();
       
       System.out.println("total Data : "+totalCount);



    }

    public static void plotPoints() throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer = new PrintWriter("points.txt", "UTF-8");
//            writer.println("The first line");
//            writer.println("The second line");
        for(int i=0;i<Main.totalCount;i++)
        {
//            int x = Classifier.centroidLabel.indexOf(Main.emotionLabel.get(i));
//            if(x==1||x==4||x==5||x==7)
//            writer.println(Main.AllData.get(i).get(6)+" "+Classifier.centroidLabel.indexOf(Main.emotionLabel.get(i)));
//            writer.println(Main.AllData.get(i).get(16)+" "+Integer.parseInt(Main.userLabel.get(i)));

        }
        writer.close();

    }


 

}
