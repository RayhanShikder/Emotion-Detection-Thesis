/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package surveydataanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author chumki
 */
public class Parser {
    public static double maxIdleTime= -1000000.0;
    public static double maxRegularKey= -1000000.0;
    public static double maxEnterKey= -1000000.0;
    public static double maxArrowKey= -1000000.0;
    public static double maxMouseClickAvg= -1000000.0;
    public static double maxDuration= -1000000.0;
    public static double maxLeftClick= -1000000.0;
    public static double maxRightClick= -1000000.0;
    public static double maxDoubleClick= -1000000.0;
    public static double maxMouseScroll= -1000000.0;
    public static double maxCursorXDist= -1000000.0;
    public static double maxCursorYDist= -1000000.0;
    public static double maxDownToUp= -1000000.0;
    public static double maxUpToDown= -1000000.0;
    public static double maxDownToDown= -1000000.0;
    public static double maxControl= -1000000.0;
    public static double maxFnKey= -1000000.0;
    public static double maxBackSpace= -1000000.0;

       public static ArrayList<String> createStepNames() throws IOException {
        boolean flag = false;
        ArrayList<String> steps = new ArrayList<String>();
        File fin = new File("step_names.txt");
        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/questions.txt")));

        String line = null;
        int newline_count = 0;
        String str = "";
        Main.emotionLabel.add("Neutral");
        Main.emotionLabel.add("Amusement");
        Main.emotionLabel.add("Happy");
        Main.emotionLabel.add("Inspiring");
        Main.emotionLabel.add("Surprise");
        Main.emotionLabel.add("Sadness");
        Main.emotionLabel.add("Sympathy");
        Main.emotionLabel.add("Anger");
        Main.emotionLabel.add("Disgust");
        Main.emotionLabel.add("Fear");
        Main.emotionLabel.add("Neutral");



        while ((line = br.readLine()) != null) {

            if (!line.equals("")) {
                steps.add(line);

            }





        }

        return steps;
    }


       public static void createData(ArrayList<String>steps) throws IOException {

            boolean flag = false;
        File fin = new File("test.txt");
        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        //BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/questions.txt")));

        String line = null;
        int count = 0;
        String str = "";

        while ((line = br.readLine()) != null) {

            if(count<steps.size() && line.equals(steps.get(count))){
                
                count++;
                Main.mouseClickAvg.add(new ArrayList<Double>());
                Main.duration.add(new ArrayList<Double>());
                Main.arrowKey.add(new ArrayList<Double>());
                Main.backSpace.add(new ArrayList<Double>());
                Main.ctrlKey.add(new ArrayList<Double>());
                Main.cursorXDist.add(new ArrayList<Double>());
                Main.cursorYDist.add(new ArrayList<Double>());
                Main.doubleClick.add(new ArrayList<Double>());
                Main.upToDown.add(new ArrayList<Double>());
                Main.downToDown.add(new ArrayList<Double>());
                Main.downToUp.add(new ArrayList<Double>());
//                Main.downToUp.add(new ArrayList<Double>());
                Main.enterKey.add(new ArrayList<Double>());
                Main.fnKey.add(new ArrayList<Double>());
                Main.idleTime.add(new ArrayList<Double>());
                Main.leftClick.add(new ArrayList<Double>());
                Main.mouseScroll.add(new ArrayList<Double>());
                Main.regularKey.add(new ArrayList<Double>());
                Main.rightClick.add(new ArrayList<Double>());


            }
            else if(line.equals("Mouse Clicks Per Sec"))
            {
                    line = br.readLine();
                    Main.mouseClickAvg.get(count-1).add(Double.parseDouble(line));
                    if(Double.parseDouble(line)>maxMouseClickAvg)maxMouseClickAvg=Double.parseDouble(line);
                    

            }
            else if(line.equals("Duration"))
            {
                    line = br.readLine();
                    Main.duration.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxDuration)maxDuration=Double.parseDouble(line);

                    if(Main.downToDown.get(count-1).size()<Main.duration.get(count-1).size())
                        Main.downToDown.get(count-1).add(0.0);
                    if(Main.upToDown.get(count-1).size()<Main.duration.get(count-1).size())
                        Main.upToDown.get(count-1).add(0.0);
                    if(Main.downToUp.get(count-1).size()<Main.duration.get(count-1).size())
                        Main.downToUp.get(count-1).add(0.0);


            }
            else if(line.equals("leftClick "))
            {
                    line = br.readLine();
                    Main.leftClick.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxLeftClick)maxLeftClick=Double.parseDouble(line);

            }
            else if(line.equals("rightClick "))
            {
                    line = br.readLine();
                    Main.rightClick.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxRightClick)maxRightClick=Double.parseDouble(line);

            }
            else if(line.equals("DoubleClick "))
            {
                    line = br.readLine();
                    Main.doubleClick.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxDoubleClick)maxDoubleClick=Double.parseDouble(line);

            }
            else if(line.equals("MouseScroll "))
            {
                    line = br.readLine();
                    Main.mouseScroll.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxMouseScroll)maxMouseScroll=Double.parseDouble(line);

            }
            else if(line.equals("CursorXDistance: "))
            {
                    line = br.readLine();
                    Main.cursorXDist.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxCursorXDist)maxCursorXDist=Double.parseDouble(line);

            }
            else if(line.equals("CursorYDistance: "))
            {
                    line = br.readLine();
                    Main.cursorYDist.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxCursorYDist)maxCursorYDist=Double.parseDouble(line);

            }
            else if(line.equals("DownToUpTime: "))
            {
                    line = br.readLine();
                    Main.downToUp.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxDownToUp)maxDownToUp=Double.parseDouble(line);

            }
            else if(line.equals("DownToDownTime: "))
            {
//                System.out.println("--Down_---");
                    line = br.readLine();
                    Main.downToDown.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxDownToDown)maxDownToDown=Double.parseDouble(line);

            }
            else if(line.equals("UpToDownTime: "))
            {
                    line = br.readLine();
                    Main.upToDown.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxUpToDown)maxUpToDown=Double.parseDouble(line);

            }
            else if(line.equals("Control"))
            {
                    line = br.readLine();
                    Main.ctrlKey.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxControl)maxControl=Double.parseDouble(line);

            }
            else if(line.equals("Enter-"))
            {
                    line = br.readLine();
                    Main.enterKey.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxEnterKey)maxEnterKey=Double.parseDouble(line);

            }
            else if(line.equals("Function-"))
            {
                    line = br.readLine();
                    Main.fnKey.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxFnKey)maxFnKey=Double.parseDouble(line);

            }
             else if(line.equals("BackSpace-"))
            {
                    line = br.readLine();
                    Main.backSpace.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxBackSpace)maxBackSpace=Double.parseDouble(line);

            }
                        else if(line.equals("Arrow-"))
            {
                    line = br.readLine();
                    Main.arrowKey.get(count-1).add(Double.parseDouble(line));


                    if(Double.parseDouble(line)>maxArrowKey)maxArrowKey=Double.parseDouble(line);


                    line = br.readLine();//getting regular key
                    String[] regKey= line.split("-");
                    if(regKey[0].equals("Regular") && regKey.length==2)
                    {

                        Main.regularKey.get(count - 1).add(Double.parseDouble(regKey[1]));
                       if(Double.parseDouble(regKey[1])>maxRegularKey)maxRegularKey=Double.parseDouble(regKey[1]);
                    }




            }
                        else if(line.equals("Regular-"))
            {
                    line = br.readLine();
                    Main.regularKey.get(count-1).add(Double.parseDouble(line));


                    if(Double.parseDouble(line)>maxRegularKey)maxRegularKey=Double.parseDouble(line);

            }

                       else if(line.equals("Idle time"))
            {
                    line = br.readLine();
                    Main.idleTime.get(count-1).add(Double.parseDouble(line));

                    if(Double.parseDouble(line)>maxIdleTime)maxIdleTime=Double.parseDouble(line);

            }






        }

       
    }


       public static void createGenderBasedData() throws FileNotFoundException, IOException
    {
        boolean flag = false;
        File fin = new File("forUserClassification.txt");
        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        //BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/questions.txt")));

        String line = null;
        int count = 0;
        String str = "";

PrintWriter writer = new PrintWriter("partialPoints.txt", "UTF-8");

        while ((line = br.readLine()) != null) {
            count++;


            String[] strs=line.split(" ");

            for(int i=0;i<23;i++)
            {
               
                if(i==19)
                {
                    if(strs[i].equals("Male"))writer.println("1 ");
                    else writer.println("2 ");
                }
                else if(i==22)
                {
                    writer.println(strs[i]);
                }
                else
                {
                    writer.print(strs[i]+" ");
                }

            }


        }
        System.out.println("count: "+count);
      }
}
