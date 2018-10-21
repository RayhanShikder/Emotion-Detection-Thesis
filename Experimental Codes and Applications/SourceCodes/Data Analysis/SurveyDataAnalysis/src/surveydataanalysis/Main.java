/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package surveydataanalysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.font.CreatedFontTracker;

/**
 *
 * @author chumki
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static ArrayList<String>steps;
    public static ArrayList<ArrayList<Double>> mouseClickAvg = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> duration = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> leftClick = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> rightClick = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> doubleClick = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> mouseScroll = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> cursorXDist = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> cursorYDist = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> upToDown = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> downToDown = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> downToUp = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> ctrlKey = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> enterKey = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> fnKey = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> backSpace = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> arrowKey = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> regularKey = new ArrayList<ArrayList<Double>>();
    public static ArrayList<ArrayList<Double>> idleTime = new ArrayList<ArrayList<Double>>();
    public static ArrayList<String>emotionLabel=new ArrayList<String>();

    public static ArrayList<Double>mean= new ArrayList<Double>();
    public static int totalCount=0;





    public static void main(String[] args) {
        try {
            // TODO code application logic here
            init();
            Parser.createData(steps);
//            Parser.createGenderBasedData();
//            findStdDevs();
//            find2ndDev();
//            find3rdDev();

            printData();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static void init()
    {
        
        try {

            steps = Parser.createStepNames();
            

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printData() throws FileNotFoundException, UnsupportedEncodingException
    {
        Double total_time = 0.0,cum_time=0.0;

        if(Parser.maxDuration>=0.0)Parser.maxDuration= (1)*100;
        if(Parser.maxMouseClickAvg>=0.0)Parser.maxMouseClickAvg= (1)*100;
        if(Parser.maxArrowKey>=0.0)Parser.maxArrowKey= (1)*100;
        if(Parser.maxBackSpace>=0.0)Parser.maxBackSpace= (1)*100;
        if(Parser.maxCursorXDist>=0.0)Parser.maxCursorXDist=(1)*100;
        if(Parser.maxCursorYDist>=0.0)Parser.maxCursorYDist=(1)*100;
        if(Parser.maxDoubleClick>=0.0)Parser.maxDoubleClick=(1)*100;
        if(Parser.maxUpToDown>=0.0)Parser.maxUpToDown=(1)*100;
        if(Parser.maxDownToDown>=0.0)Parser.maxDownToDown=(1)*100;
        if(Parser.maxDownToUp>=0.0)Parser.maxDownToUp=(1)*100;
        if(Parser.maxEnterKey>=0.0)Parser.maxEnterKey=(1)*100;
        if(Parser.maxFnKey>=0.0)Parser.maxFnKey=(1)*100;
        if(Parser.maxIdleTime>=0.0)Parser.maxIdleTime=(5)*100;
        if(Parser.maxLeftClick>=0.0)Parser.maxLeftClick=(1)*100;
        if(Parser.maxMouseScroll>=0.0)Parser.maxMouseScroll=(1)*100;
        if(Parser.maxRightClick>=0.0)Parser.maxRightClick=(1)*100;

        PrintWriter writer = new PrintWriter("partialPoints.txt", "UTF-8");

        for(int i=0;i<mouseClickAvg.size();i++)
        {
            for(int j=0;j<mouseClickAvg.get(i).size();j++)
            {
//                if(duration.get(i).get(j)<0)duration.get(i).set(i, duration.get(i).get(j)*-1);
//                if(mouseClickAvg.get(i).get(j)<0)mouseClickAvg.get(i).set(i, mouseClickAvg.get(i).get(j)*-1);
//                if(arrowKey.get(i).get(j)<0)arrowKey.get(i).set(i, arrowKey.get(i).get(j)*-1);
//                if(backSpace.get(i).get(j)<0)backSpace.get(i).set(i, backSpace.get(i).get(j)*-1);
//                if(cursorXDist.get(i).get(j)<0)cursorXDist.get(i).set(i, cursorXDist.get(i).get(j)*-1);
//                if(cursorYDist.get(i).get(j)<0)cursorYDist.get(i).set(i, cursorYDist.get(i).get(j)*-1);
//                if(doubleClick.get(i).get(j)<0)doubleClick.get(i).set(i, doubleClick.get(i).get(j)*-1);
//                if(upToDown.get(i).get(j)<0)upToDown.get(i).set(i, upToDown.get(i).get(j)*-1);
//                if(downToDown.get(i).get(j)<0)downToDown.get(i).set(i, downToDown.get(i).get(j)*-1);
//                if(downToUp.get(i).get(j)<0)downToUp.get(i).set(i, downToUp.get(i).get(j)*-1);
//                if(enterKey.get(i).get(j)<0)enterKey.get(i).set(i, enterKey.get(i).get(j)*-1);
//                if(fnKey.get(i).get(j)<0)fnKey.get(i).set(i, fnKey.get(i).get(j)*-1);
//                if(idleTime.get(i).get(j)<0)idleTime.get(i).set(i, idleTime.get(i).get(j)*-1);
//                if(leftClick.get(i).get(j)<0)leftClick.get(i).set(i, leftClick.get(i).get(j)*-1);
//                if(mouseScroll.get(i).get(j)<0)mouseScroll.get(i).set(i, mouseScroll.get(i).get(j)*-1);
//                if(rightClick.get(i).get(j)<0)rightClick.get(i).set(i, rightClick.get(i).get(j)*-1);
//                if(regularKey.get(i).get(j)<0)regularKey.get(i).set(i, regularKey.get(i).get(j)*-1);

//                System.out.println(mouseClickAvg.get(i).get(j));
                cum_time+=(mouseClickAvg.get(i).get(j)*duration.get(i).get(j));
                total_time+=duration.get(i).get(j);
                writer.print((duration.get(i).get(j)/Parser.maxDuration)*100);
                writer.print(" "+(mouseClickAvg.get(i).get(j)/Parser.maxMouseClickAvg)*100);
                writer.print(" "+(arrowKey.get(i).get(j)/Parser.maxArrowKey)*100);
                writer.print(" "+(backSpace.get(i).get(j)/Parser.maxBackSpace)*100);
//                System.out.println(" "+ctrlKey.get(i).get(j));
                writer.print(" "+(cursorXDist.get(i).get(j)/Parser.maxCursorXDist)*100);
                writer.print(" "+(cursorYDist.get(i).get(j)/Parser.maxCursorYDist)*100);
                writer.print(" "+(doubleClick.get(i).get(j)/Parser.maxDoubleClick)*100);
                writer.print(" "+(upToDown.get(i).get(j)/Parser.maxUpToDown)*100);
                writer.print(" "+(downToDown.get(i).get(j)/Parser.maxDownToDown)*100);
                writer.print(" "+(downToUp.get(i).get(j)/Parser.maxDownToUp)*100);
                writer.print(" "+(enterKey.get(i).get(j)/Parser.maxEnterKey)*100);
                writer.print(" "+(fnKey.get(i).get(j)/Parser.maxFnKey)*100);
                writer.print(" "+(idleTime.get(i).get(j)/Parser.maxIdleTime)*100);
                writer.print(" "+(leftClick.get(i).get(j)/Parser.maxLeftClick)*100);
                writer.print(" "+(mouseScroll.get(i).get(j)/Parser.maxMouseScroll)*100);
//                System.out.print(" "+ctrlKey.get(i).get(j));
//                System.out.println(" "+regularKey.get(i).get(j));
                writer.print(" "+(rightClick.get(i).get(j)/Parser.maxRightClick)*100);
                writer.print(" "+(regularKey.get(i).get(j)/Parser.maxRegularKey)*100);

                
                writer.print(" 19693");
                writer.print(" Student");
                writer.print(" Female");
                writer.print(" Fair");
                


                writer.print(" "+emotionLabel.get(i));
                writer.println(" 1");
            }
           

//            System.out.println(cum_time/total_time);
        }
         writer.close();
    }
    
    public static void findStdDevs()
    {
        for(int i=0;i<18;i++)mean.add(0.0);

        for(int i=0;i<mouseClickAvg.size();i++)
        {
            for(int j=0;j<mouseClickAvg.get(i).size();j++)
            {
                totalCount++;
                mean.set(0, mean.get(0)+duration.get(i).get(j));
                mean.set(1, mean.get(1)+mouseClickAvg.get(i).get(j));
                mean.set(2, mean.get(2)+arrowKey.get(i).get(j));
                mean.set(3, mean.get(3)+backSpace.get(i).get(j));
                mean.set(4, mean.get(4)+cursorXDist.get(i).get(j));
                mean.set(5, mean.get(6)+cursorYDist.get(i).get(j));
                mean.set(6, mean.get(6)+doubleClick.get(i).get(j));
                mean.set(7, mean.get(7)+upToDown.get(i).get(j));
                mean.set(8, mean.get(8)+downToDown.get(i).get(j));
                mean.set(9, mean.get(9)+downToUp.get(i).get(j));
                mean.set(10, mean.get(10)+enterKey.get(i).get(j));
                mean.set(11, mean.get(11)+fnKey.get(i).get(j));
                mean.set(12, mean.get(12)+idleTime.get(i).get(j));
                mean.set(13, mean.get(13)+leftClick.get(i).get(j));
                mean.set(14, mean.get(14)+mouseScroll.get(i).get(j));
                mean.set(15, mean.get(15)+rightClick.get(i).get(j));
                mean.set(16, mean.get(16)+regularKey.get(i).get(j));
                

            }
        }
        for(int i=0;i<18;i++)
        {
                mean.set(0, mean.get(0)/totalCount);
                mean.set(1, mean.get(1)/totalCount);
                mean.set(2, mean.get(2)/totalCount);
                mean.set(3, mean.get(3)/totalCount);
                mean.set(4, mean.get(4)/totalCount);
                mean.set(5, mean.get(5)/totalCount);
                mean.set(6, mean.get(6)/totalCount);
                mean.set(7, mean.get(7)/totalCount);
                mean.set(8, mean.get(8)/totalCount);
                mean.set(9, mean.get(9)/totalCount);
                mean.set(10, mean.get(10)/totalCount);
                mean.set(11, mean.get(11)/totalCount);
                mean.set(12, mean.get(12)/totalCount);
                mean.set(13, mean.get(13)/totalCount);
                mean.set(14, mean.get(14)/totalCount);
                mean.set(15, mean.get(15)/totalCount);
                mean.set(16, mean.get(16)/totalCount);
                mean.set(17, mean.get(17)/totalCount);
        }

        for(int i=0;i<mouseClickAvg.size();i++)
        {
            for(int j=0;j<mouseClickAvg.get(i).size();j++)
            {
                duration.get(i).set(j, mean.get(0)-duration.get(i).get(j));
                mouseClickAvg.get(i).set(j, mean.get(1)-mouseClickAvg.get(i).get(j));
                arrowKey.get(i).set(j, mean.get(2)-arrowKey.get(i).get(j));
                backSpace.get(i).set(j, mean.get(3)-backSpace.get(i).get(j));
                cursorXDist.get(i).set(j, mean.get(4)-cursorXDist.get(i).get(j));
                cursorYDist.get(i).set(j, mean.get(5)-cursorYDist.get(i).get(j));
                arrowKey.get(i).set(j, mean.get(6)-arrowKey.get(i).get(j));
                doubleClick.get(i).set(j, mean.get(7)-doubleClick.get(i).get(j));
                upToDown.get(i).set(j, mean.get(8)-upToDown.get(i).get(j));
                downToDown.get(i).set(j, mean.get(9)-downToDown.get(i).get(j));
                downToUp.get(i).set(j, mean.get(10)-downToUp.get(i).get(j));
                enterKey.get(i).set(j, mean.get(11)-enterKey.get(i).get(j));
                fnKey.get(i).set(j, mean.get(12)-fnKey.get(i).get(j));
                idleTime.get(i).set(j, mean.get(13)-idleTime.get(i).get(j));
                leftClick.get(i).set(j, mean.get(14)-leftClick.get(i).get(j));
                mouseScroll.get(i).set(j, mean.get(15)-mouseScroll.get(i).get(j));
                rightClick.get(i).set(j, mean.get(16)-rightClick.get(i).get(j));
                regularKey.get(i).set(j, mean.get(17)-regularKey.get(i).get(j));

            }
        }
        
    }

    public static void find2ndDev()
    {
        mean.clear();
        for(int i=0;i<18;i++)mean.add(0.0);
        totalCount=0;
        
        for(int i=0;i<mouseClickAvg.size();i++)
        {
            for(int j=0;j<mouseClickAvg.get(i).size();j++)
            {
                totalCount++;
                mean.set(0, mean.get(0)+duration.get(i).get(j));
                mean.set(1, mean.get(1)+mouseClickAvg.get(i).get(j));
                mean.set(2, mean.get(2)+arrowKey.get(i).get(j));
                mean.set(3, mean.get(3)+backSpace.get(i).get(j));
                mean.set(4, mean.get(4)+cursorXDist.get(i).get(j));
                mean.set(5, mean.get(5)+cursorYDist.get(i).get(j));
                mean.set(6, mean.get(6)+arrowKey.get(i).get(j));
                mean.set(7, mean.get(7)+doubleClick.get(i).get(j));
                mean.set(8, mean.get(8)+upToDown.get(i).get(j));
                mean.set(9, mean.get(9)+downToDown.get(i).get(j));
                mean.set(10, mean.get(10)+downToUp.get(i).get(j));
                mean.set(11, mean.get(11)+enterKey.get(i).get(j));
                mean.set(12, mean.get(12)+fnKey.get(i).get(j));
                mean.set(13, mean.get(13)+idleTime.get(i).get(j));
                mean.set(14, mean.get(14)+leftClick.get(i).get(j));
                mean.set(15, mean.get(15)+mouseScroll.get(i).get(j));
                mean.set(16, mean.get(16)+rightClick.get(i).get(j));
                mean.set(17, mean.get(17)+regularKey.get(i).get(j));
                

            }
        }
        for(int i=0;i<18;i++)
        {
                mean.set(0, mean.get(0)/totalCount);
                mean.set(1, mean.get(1)/totalCount);
                mean.set(2, mean.get(2)/totalCount);
                mean.set(3, mean.get(3)/totalCount);
                mean.set(4, mean.get(4)/totalCount);
                mean.set(5, mean.get(5)/totalCount);
                mean.set(6, mean.get(6)/totalCount);
                mean.set(7, mean.get(7)/totalCount);
                mean.set(8, mean.get(8)/totalCount);
                mean.set(9, mean.get(9)/totalCount);
                mean.set(10, mean.get(10)/totalCount);
                mean.set(11, mean.get(11)/totalCount);
                mean.set(12, mean.get(12)/totalCount);
                mean.set(13, mean.get(13)/totalCount);
                mean.set(14, mean.get(14)/totalCount);
                mean.set(15, mean.get(15)/totalCount);
                mean.set(16, mean.get(16)/totalCount);
                mean.set(17, mean.get(17)/totalCount);
        }

        for(int i=0;i<mouseClickAvg.size();i++)
        {
            for(int j=0;j<mouseClickAvg.get(i).size();j++)
            {
                duration.get(i).set(j, mean.get(0)-duration.get(i).get(j));
                mouseClickAvg.get(i).set(j, mean.get(1)-mouseClickAvg.get(i).get(j));
                arrowKey.get(i).set(j, mean.get(2)-arrowKey.get(i).get(j));
                backSpace.get(i).set(j, mean.get(3)-backSpace.get(i).get(j));
                cursorXDist.get(i).set(j, mean.get(4)-cursorXDist.get(i).get(j));
                cursorYDist.get(i).set(j, mean.get(5)-cursorYDist.get(i).get(j));
                arrowKey.get(i).set(j, mean.get(6)-arrowKey.get(i).get(j));
                doubleClick.get(i).set(j, mean.get(7)-doubleClick.get(i).get(j));
                upToDown.get(i).set(j, mean.get(8)-upToDown.get(i).get(j));
                downToDown.get(i).set(j, mean.get(9)-downToDown.get(i).get(j));
                downToUp.get(i).set(j, mean.get(10)-downToUp.get(i).get(j));
                enterKey.get(i).set(j, mean.get(11)-enterKey.get(i).get(j));
                fnKey.get(i).set(j, mean.get(12)-fnKey.get(i).get(j));
                idleTime.get(i).set(j, mean.get(13)-idleTime.get(i).get(j));
                leftClick.get(i).set(j, mean.get(14)-leftClick.get(i).get(j));
                mouseScroll.get(i).set(j, mean.get(15)-mouseScroll.get(i).get(j));
                rightClick.get(i).set(j, mean.get(16)-rightClick.get(i).get(j));
                regularKey.get(i).set(j, mean.get(17)-regularKey.get(i).get(j));

            }
        }
        
    }



    public static void find3rdDev()
    {
        mean.clear();
        for(int i=0;i<18;i++)mean.add(0.0);
        totalCount=0;

        for(int i=0;i<mouseClickAvg.size();i++)
        {
            for(int j=0;j<mouseClickAvg.get(i).size();j++)
            {
                totalCount++;
                mean.set(0, mean.get(0)+duration.get(i).get(j));
                mean.set(1, mean.get(1)+mouseClickAvg.get(i).get(j));
                mean.set(2, mean.get(2)+arrowKey.get(i).get(j));
                mean.set(3, mean.get(3)+backSpace.get(i).get(j));
                mean.set(4, mean.get(4)+cursorXDist.get(i).get(j));
                mean.set(5, mean.get(5)+cursorYDist.get(i).get(j));
                mean.set(6, mean.get(6)+arrowKey.get(i).get(j));
                mean.set(7, mean.get(7)+doubleClick.get(i).get(j));
                mean.set(8, mean.get(8)+upToDown.get(i).get(j));
                mean.set(9, mean.get(9)+downToDown.get(i).get(j));
                mean.set(10, mean.get(10)+downToUp.get(i).get(j));
                mean.set(11, mean.get(11)+enterKey.get(i).get(j));
                mean.set(12, mean.get(12)+fnKey.get(i).get(j));
                mean.set(13, mean.get(13)+idleTime.get(i).get(j));
                mean.set(14, mean.get(14)+leftClick.get(i).get(j));
                mean.set(15, mean.get(15)+mouseScroll.get(i).get(j));
                mean.set(16, mean.get(16)+rightClick.get(i).get(j));
                mean.set(17, mean.get(17)+regularKey.get(i).get(j));


            }
        }
        for(int i=0;i<18;i++)
        {
                mean.set(0, mean.get(0)/totalCount);
                mean.set(1, mean.get(1)/totalCount);
                mean.set(2, mean.get(2)/totalCount);
                mean.set(3, mean.get(3)/totalCount);
                mean.set(4, mean.get(4)/totalCount);
                mean.set(5, mean.get(5)/totalCount);
                mean.set(6, mean.get(6)/totalCount);
                mean.set(7, mean.get(7)/totalCount);
                mean.set(8, mean.get(8)/totalCount);
                mean.set(9, mean.get(9)/totalCount);
                mean.set(10, mean.get(10)/totalCount);
                mean.set(11, mean.get(11)/totalCount);
                mean.set(12, mean.get(12)/totalCount);
                mean.set(13, mean.get(13)/totalCount);
                mean.set(14, mean.get(14)/totalCount);
                mean.set(15, mean.get(15)/totalCount);
                mean.set(16, mean.get(16)/totalCount);
                mean.set(17, mean.get(17)/totalCount);
        }

        for(int i=0;i<mouseClickAvg.size();i++)
        {
            for(int j=0;j<mouseClickAvg.get(i).size();j++)
            {
                duration.get(i).set(j, mean.get(0)-duration.get(i).get(j));
                mouseClickAvg.get(i).set(j, mean.get(1)-mouseClickAvg.get(i).get(j));
                arrowKey.get(i).set(j, mean.get(2)-arrowKey.get(i).get(j));
                backSpace.get(i).set(j, mean.get(3)-backSpace.get(i).get(j));
                cursorXDist.get(i).set(j, mean.get(4)-cursorXDist.get(i).get(j));
                cursorYDist.get(i).set(j, mean.get(5)-cursorYDist.get(i).get(j));
                arrowKey.get(i).set(j, mean.get(6)-arrowKey.get(i).get(j));
                doubleClick.get(i).set(j, mean.get(7)-doubleClick.get(i).get(j));
                upToDown.get(i).set(j, mean.get(8)-upToDown.get(i).get(j));
                downToDown.get(i).set(j, mean.get(9)-downToDown.get(i).get(j));
                downToUp.get(i).set(j, mean.get(10)-downToUp.get(i).get(j));
                enterKey.get(i).set(j, mean.get(11)-enterKey.get(i).get(j));
                fnKey.get(i).set(j, mean.get(12)-fnKey.get(i).get(j));
                idleTime.get(i).set(j, mean.get(13)-idleTime.get(i).get(j));
                leftClick.get(i).set(j, mean.get(14)-leftClick.get(i).get(j));
                mouseScroll.get(i).set(j, mean.get(15)-mouseScroll.get(i).get(j));
                rightClick.get(i).set(j, mean.get(16)-rightClick.get(i).get(j));
                regularKey.get(i).set(j, mean.get(17)-regularKey.get(i).get(j));

            }
        }

    }
}
