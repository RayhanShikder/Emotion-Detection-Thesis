/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clusteringthesisdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Shiku
 */
public class ParseAndFilter {

        public static void filterData() throws IOException {

            boolean flag = false;
        File fin = new File("DataNormalizedNew.txt");
        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        //BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/questions.txt")));

        String line = null;
        int count = 0;
        String str = "";
        int t=-1;

        while ((line = br.readLine()) != null) {

            String[] singleData = line.split(" ");

            boolean found=false;

            //filtering the irrelevent data
            for(int i=0;i<singleData.length-4 && !found;i++)
            {
                if(Double.parseDouble(singleData[i])!=0.0)found=true;
            }

          //  System.out.println(singleData[0]);
            if(!found)continue;
            //                System.out.println("----");
            //process if not irrelevent
            
            Main.AllData.add(new ArrayList<Double>());
            t++;



            
            for(int i=0;i<singleData.length;i++)
            {
//              if(!singleData[21].equals("Neutral")){
                if(!singleData[21].equals("Amusement") &&  !singleData[21].equals("Surprise") &&  !singleData[21].equals("Sadness") && !singleData[21].equals("Anger")){
//                int x = Integer.parseInt(singleData[22]);
//                if((x==5 || x==7 || x==8 || x==9 || x==14 || x==17 || x==19 || x==21  )&& singleData[21].equals("Neutral")){
//                if((x!=5 && x!=7 && x!=8 && x!=9 || x!=14 && x!=17 && x!=19 && x!=21  && singleData[21].equals("Neutral") )){
//                if((x!=5 && x!=7 && x!=8 && x!=9 || x!=14 && x!=17 && x!=19 && x!=21 )){
//                if((x==5 || x==7 || x==8 || x==9 || x==14 || x==17 || x==19 || x==21  )){



//                if(singleData[21].equals("Anger")){
                    if(i<17){
//                    Double x=Double.parseDouble(singleData[i]);
//                x*=100.0;
//                singleData[i]=x.toString();
//                
                        Main.AllData.get(t).add(Double.parseDouble(singleData[i]));

                }
                if(i==22)Main.AllData.get(t).add(Double.parseDouble(singleData[i]));


                    switch (i) {

                         case 0:
                             Main.duration.add(Double.parseDouble(singleData[i]));
                         break;

                         case 1:
                             Main.mouseClickAvg.add(Double.parseDouble(singleData[i]));
                         break;

                         case 2:
                             Main.arrowKey.add(Double.parseDouble(singleData[i]));
                         break;

                         case 3:
                             Main.backSpace.add(Double.parseDouble(singleData[i]));
                         break;

                         case 4:
                             Main.cursorXDist.add(Double.parseDouble(singleData[i]));
                         break;

                         case 5:
                             Main.cursorYDist.add(Double.parseDouble(singleData[i]));
                         break;

                         case 6:
                             Main.doubleClick.add(Double.parseDouble(singleData[i]));
                         break;

                         case 7:
                             Main.upToDown.add(Double.parseDouble(singleData[i]));
                         break;

                         case 8:
                             Main.downToDown.add(Double.parseDouble(singleData[i]));
                         break;

                         case 9:
                             Main.downToUp.add(Double.parseDouble(singleData[i]));
                         break;

                         case 10:
                             Main.enterKey.add(Double.parseDouble(singleData[i]));
                         break;

                         case 11:
                             Main.fnKey.add(Double.parseDouble(singleData[i]));
                         break;

                         case 12:
                             Main.idleTime.add(Double.parseDouble(singleData[i]));
                         break;

                         case 13:
                             Main.leftClick.add(Double.parseDouble(singleData[i]));
                         break;

                         case 14:
                             Main.mouseScroll.add(Double.parseDouble(singleData[i]));
                         break;


                         case 15:
                             Main.rightClick.add(Double.parseDouble(singleData[i]));
                         break;

                        case 16:
                             Main.regularKey.add(Double.parseDouble(singleData[i]));
                         break;


                         case 19:
                             if(singleData[i].equals("Male"))
                             Main.GenderLabel.add("1");
                             else
                                 Main.GenderLabel.add("2");

                         break;


                         case 21:
                             if(singleData[i].equals("Neutral"))Main.newEmotionLabel.add("0");
                             else if(singleData[i].equals("Amusement"))Main.newEmotionLabel.add("1");
                             else if(singleData[i].equals("Happy"))Main.newEmotionLabel.add("2");
                             else if(singleData[i].equals("Inspiring"))Main.newEmotionLabel.add("3");
                             else if(singleData[i].equals("Surprise"))Main.newEmotionLabel.add("4");
                             else if(singleData[i].equals("Sadness"))Main.newEmotionLabel.add("5");
                             else if(singleData[i].equals("Sympathy"))Main.newEmotionLabel.add("6");
                             else if(singleData[i].equals("Anger"))Main.newEmotionLabel.add("7");
                             else if(singleData[i].equals("Disgust"))Main.newEmotionLabel.add("8");
                             else if(singleData[i].equals("Fear"))Main.newEmotionLabel.add("9");

                             Main.emotionLabel.add(singleData[i]);
                         break;


                         case 22:
                             Main.userLabel.add(singleData[i]);
                         break;





                         default:

                         break;
                    }
                }
                else
                {
//                    if(Main.AllData.size()==(t+1))
                    Main.AllData.remove(t);
                    t--;
                    break;
                }






        }


    

    }
  }

        public static void singleData()
    {
            for(int i=0;i<Main.totalUser;i++)
            {
                for(int l=0;l<10;l++)
                {
                    Main.SingleDataForSingleEmotion.add(new ArrayList<Double>());
                    for(int u=0;u<17;u++)
                    {
                        Main.SingleDataForSingleEmotion.get((i*10)+l).add(0.0);
                    }
                    Main.SingleDataForSingleEmotion.get((i*10)+l).add(i+0.0);//18th val for userid
                    Main.SingleDataForSingleEmotion.get((i*10)+l).add(l+0.0);//19th val for emotionid


                }
                for(int j=0;j<Main.totalCount;j++)
                {
                    if(Integer.parseInt(Main.userLabel.get(i))==i)
                    {
                        for(int k=0;k<17;k++){
                            if(k==0)
                            Main.SingleDataForSingleEmotion.get((i*10)+Integer.parseInt(Main.newEmotionLabel.get(j))).set(k, Main.SingleDataForSingleEmotion.get((i*10)+Integer.parseInt(Main.newEmotionLabel.get(j))).get(k)+Main.AllData.get(j).get(k));
                            else Main.SingleDataForSingleEmotion.get((i*10)+Integer.parseInt(Main.newEmotionLabel.get(j))).set(k, Main.SingleDataForSingleEmotion.get((i*10)+Integer.parseInt(Main.newEmotionLabel.get(j))).get(k)+(Main.AllData.get(j).get(k)*Main.AllData.get(j).get(0)));
                        }
                    }


                }

                for(int j=0;j<10;j++)
                {
                    for(int k=1;k<17;k++)
                    {
                        Main.SingleDataForSingleEmotion.get((i*10)+j).set(k, Main.SingleDataForSingleEmotion.get((i*10)+j).get(k)/Main.SingleDataForSingleEmotion.get((i*10)+j).get(0));
                    }
                }
            }
    }

        
}
