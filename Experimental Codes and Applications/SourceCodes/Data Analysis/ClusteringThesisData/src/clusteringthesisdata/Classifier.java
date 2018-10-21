/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clusteringthesisdata;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Shiku
 */

public class Classifier{



    public static Double min;
    public static Double max;
    public static Double avg;
    public static ArrayList<ArrayList<Double>> centroids = new ArrayList<ArrayList<Double>>();//holds the individual cluusters
    public static ArrayList<ArrayList<Double>> centroidsTemp = new ArrayList<ArrayList<Double>>();//holds the individual cluusters

    public static ArrayList<ArrayList<Double>> tolarence = new ArrayList<ArrayList<Double>>();//holds the individual cluusters
    public static ArrayList<ArrayList<Double>> stDev = new ArrayList<ArrayList<Double>>();//holds the individual cluusters

    public static ArrayList<ArrayList<Double>> stDevTemp = new ArrayList<ArrayList<Double>>();//holds the individual cluusters
    public static ArrayList<String>centroidLabel = new ArrayList<String>();
    public static Double stDevTolerance;
    public static int IterationLimit=1000;
    private static Double minErrCount=999999999.0;
    public static Double avgPercentage=0.0;
    public static Double StDevPercentage=0.0;
    public static int missedTolerance = 0;
    public static Double eps = 0.5;
    public static ArrayList<Integer> labelAssigned = new ArrayList<Integer>();

    public static void classify()
    {

        //initializeThresholds();//initialize the min,max,avg thresholds
//        System.out.println("min : "+min+" max: "+max+" average: "+avg);
        findInitialCentroids();
//        findInitialCentroidsForUser();

//        printEmotionLabels();
//        printCentroids();
//        printStDevs();
//        kMeans();
//        kMeansForUser();
//        knnNewforSingle();
//        knnForUser();
//        knnForGender();
        knn();
//        printCentroids();
//        printStDevs();
//        sortUsersBasedOnStDevs();
//        System.out.println();
//        System.out.println();
        
//        sortUsersBasedOnNumbOfPoints();
    }
    public static boolean similar(int k,int j)
    {

        int i= centroidLabel.indexOf(Main.emotionLabel.get(k));

        if(i==j)return true;

        if((i>0&& i<5) && (j>0 && j<5))return true;
        if((i>=5 && i<=9)&&(j>=5&&j<=9))return true;
        return false;
    }

    public static void printEmotionLabels()
    {
        for(int i=0;i<Main.totalCount;i++)
        {
            System.out.println(Main.emotionLabel.get(i));
        }
    }
    public static void initializeThresholds()
    {
        Double tempMin = 999999999.0;
        Double tempMax = -999999999.0;
        Double tempAvg = 0.0;

        for(int i=0;i<Main.totalCount;i++)
        {
            for(int j=0;j<Main.totalCount;j++)
            {
                if(i!=j)
                {
                    Double dist = euclideanDistance(i,j);
                    if(dist<tempMin)
                    {
                        tempMin=dist;
                    }
                    if(dist>tempMax)
                    {
                        tempMax=dist;
                    }

                }
            }
        }
        tempAvg=(tempMin+tempMax)/2;

        min = tempMin;
        max = tempMax;
        avg = tempAvg;
    }

    public static Double euclideanDistance(int i,int j)
    {
        Double dist = 0.0;

        for(int l=1;l<17;l++)
        {
//            if(l!=11 && l!=12 && l!=13 && l!=5 && l!=3 && l!=6 && l!=15)
//            if(l==16)
            dist+=(Main.AllData.get(i).get(l)-Main.AllData.get(j).get(l))*(Main.AllData.get(i).get(l)-Main.AllData.get(j).get(l));
        }
        dist=Math.sqrt(dist);



        return dist;
    }


    public static Double newSingleEuclideanDistance(int i,int j)
    {
        Double dist = 0.0;

        for(int l=1;l<17;l++)
        {
            if(l!=11 && l!=12 && l!=13 && l!=5 && l!=3 && l!=6 && l!=15)
//            if(l==16)
            dist+=(Main.SingleDataForSingleEmotion.get(i).get(l)-Main.SingleDataForSingleEmotion.get(j).get(l))*(Main.SingleDataForSingleEmotion.get(i).get(l)-Main.SingleDataForSingleEmotion.get(j).get(l));
        }
        dist=Math.sqrt(dist);



        return dist;
    }
    public static Double pointToCentroidDistance(int i,int j)
    {
        Double dist = 0.0;

//        dist+= (Main.duration.get(i)-centroids.get(j).get(0))*(Main.duration.get(i)-centroids.get(j).get(0));
        dist+= (Main.mouseClickAvg.get(i)-centroids.get(j).get(1))*(Main.mouseClickAvg.get(i)-centroids.get(j).get(1));
        dist+= (Main.arrowKey.get(i)-centroids.get(j).get(2))*(Main.arrowKey.get(i)-centroids.get(j).get(2));
        dist+= (Main.backSpace.get(i)-centroids.get(j).get(3))*(Main.backSpace.get(i)-centroids.get(j).get(3));
        dist+= (Main.cursorXDist.get(i)-centroids.get(j).get(4))*(Main.cursorXDist.get(i)-centroids.get(j).get(4));
        dist+= (Main.cursorYDist.get(i)-centroids.get(j).get(5))*(Main.cursorYDist.get(i)-centroids.get(j).get(5));
        dist+= (Main.doubleClick.get(i)-centroids.get(j).get(6))*(Main.doubleClick.get(i)-centroids.get(j).get(6));
        dist+= (Main.upToDown.get(i)-centroids.get(j).get(7))*(Main.upToDown.get(i)-centroids.get(j).get(7));
        dist+= (Main.downToDown.get(i)-centroids.get(j).get(8))*(Main.downToDown.get(i)-centroids.get(j).get(8));
        dist+= (Main.downToUp.get(i)-centroids.get(j).get(9))*(Main.downToUp.get(i)-centroids.get(j).get(9));
        dist+= (Main.enterKey.get(i)-centroids.get(j).get(10))*(Main.enterKey.get(i)-centroids.get(j).get(10));
        dist+= (Main.fnKey.get(i)-centroids.get(j).get(11))*(Main.fnKey.get(i)-centroids.get(j).get(11));
        dist+= (Main.idleTime.get(i)-centroids.get(j).get(12))*(Main.idleTime.get(i)-centroids.get(j).get(12));
        dist+= (Main.leftClick.get(i)-centroids.get(j).get(13))*(Main.leftClick.get(i)-centroids.get(j).get(13));
        dist+= (Main.mouseScroll.get(i)-centroids.get(j).get(14))*(Main.mouseScroll.get(i)-centroids.get(j).get(14));
        dist+= (Main.rightClick.get(i)-centroids.get(j).get(15))*(Main.rightClick.get(i)-centroids.get(j).get(15));
        dist+= (Main.regularKey.get(i)-centroids.get(j).get(16))*(Main.regularKey.get(i)-centroids.get(j).get(16));

        dist=Math.sqrt(dist);



        return dist;
    }


    public static void findInitialCentroids()
    {
        centroidLabel.add("Neutral");
        centroidLabel.add("Amusement");
        centroidLabel.add("Happy");
        centroidLabel.add("Inspiring");
        centroidLabel.add("Surprise");
        centroidLabel.add("Sadness");
        centroidLabel.add("Sympathy");
        centroidLabel.add("Anger");
        centroidLabel.add("Disgust");
        centroidLabel.add("Fear");


        for(int i=0;i<10;i++)
        {
//            if(i==0||i==1){

                centroids.add(new ArrayList<Double>());
                tolarence.add(new ArrayList<Double>());
                stDev.add(new ArrayList<Double>(0));

//            }
        }

        for(int i=0;i<10;i++)
        {

            for(int j=0;j<18;j++)//index 16 for regularKey and 17 for count
            {
                centroids.get(i).add(0.0);
                if(j<17)
                {

                    tolarence.get(i).add(0.0);
                    stDev.get(i).add(0.0);

                }
            }
        }
        for(int i=0;i<Main.totalCount;i++)
        {
            int index=-1;
            if(Main.emotionLabel.get(i).equals("Neutral"))
            {
                index=0;

            }
            else if(Main.emotionLabel.get(i).equals("Amusement"))
            {
                index=1;
            }
            else if(Main.emotionLabel.get(i).equals("Happy"))
            {
                index=2;
            }
            else if(Main.emotionLabel.get(i).equals("Inspiring"))
            {
                index=3;
            }
            else if(Main.emotionLabel.get(i).equals("Surprise"))
            {
                index=4;
            }
            else if(Main.emotionLabel.get(i).equals("Sadness"))
            {
                index=5;
            }
            else if(Main.emotionLabel.get(i).equals("Sympathy"))
            {
                index=6;
            }
            else if(Main.emotionLabel.get(i).equals("Anger"))
            {
                index=7;
            }
            else if(Main.emotionLabel.get(i).equals("Disgust"))
            {
                index=8;
            }
            else if(Main.emotionLabel.get(i).equals("Fear"))
            {
                index=9;
            }

            if(index==-1)
            {
                System.out.println("index "+index+" step: "+Main.emotionLabel.get(i));
            }
            else{
                centroids.get(index).set(0, centroids.get(index).get(0)+Main.duration.get(i));
                centroids.get(index).set(1, centroids.get(index).get(1)+Main.mouseClickAvg.get(i));
                centroids.get(index).set(2, centroids.get(index).get(2)+Main.arrowKey.get(i));
                centroids.get(index).set(3, centroids.get(index).get(3)+Main.backSpace.get(i));
                centroids.get(index).set(4, centroids.get(index).get(4)+Main.cursorXDist.get(i));
                centroids.get(index).set(5, centroids.get(index).get(5)+Main.cursorYDist.get(i));
                //centroids.get(index).set(6, centroids.get(index).get(6)+Main.cursorYDist.get(i));
                centroids.get(index).set(6, centroids.get(index).get(6)+Main.doubleClick.get(i));
                centroids.get(index).set(7, centroids.get(index).get(7)+Main.upToDown.get(i));
                centroids.get(index).set(8, centroids.get(index).get(8)+Main.downToDown.get(i));
                centroids.get(index).set(9, centroids.get(index).get(9)+Main.downToUp.get(i));
                centroids.get(index).set(10, centroids.get(index).get(10)+Main.enterKey.get(i));
                centroids.get(index).set(11, centroids.get(index).get(11)+Main.fnKey.get(i));
                centroids.get(index).set(12, centroids.get(index).get(12)+Main.idleTime.get(i));
                centroids.get(index).set(13, centroids.get(index).get(13)+Main.leftClick.get(i));
                centroids.get(index).set(14, centroids.get(index).get(14)+Main.mouseScroll.get(i));
                centroids.get(index).set(15, centroids.get(index).get(15)+Main.rightClick.get(i));
                centroids.get(index).set(16, centroids.get(index).get(16)+Main.regularKey.get(i));



    //            centroids.get(index).set(17, centroids.get(index).get(17)+Main.arrowKey.get(i));

                centroids.get(index).set(17, centroids.get(index).get(17)+ 1);
            }

        }


        for(int i=0;i<10;i++)//find mean by dividing by the corresponding count
        {
            for(int j=0;j<17;j++)
            {
                if(centroids.get(i).get(17)!=0)
                centroids.get(i).set(j, centroids.get(i).get(j)/centroids.get(i).get(17));
            }
        }
        for(int i=0;i<10;i++)//find std dev for each centroid
        {
            Double tempDev=0.0;
            for(int j=0;j<Main.totalCount;j++)
            {
                if(centroidLabel.get(i).equals(Main.emotionLabel.get(j)))
                {
                   for(int k=0;k<17;k++)
                   {
                        stDev.get(i).set(k, stDev.get(i).get(k)+((centroids.get(i).get(k)-Main.AllData.get(i).get(k))*(centroids.get(i).get(k)-Main.AllData.get(i).get(k))));
                   }
                }
            }
            for(int k=0;k<17;k++)
            {
                if(centroids.get(i).get(17)!=0)
                stDev.get(i).set(k, Math.sqrt(stDev.get(i).get(k)/centroids.get(i).get(17)));
            }
        }


//        for(int i=0;i<Main.totalCount;i++)
//        {
//
//        }

//        centroidsTemp=centroids;
//        stDevTemp=stDev;
        assign(centroidsTemp,centroids);
        assign(stDevTemp,stDev);
    }

        public static void findInitialCentroidsForUser()
    {

        
        for(int i=0;i<Main.totalUser;i++)
        {
//            if(i==0||i==1){

                centroids.add(new ArrayList<Double>());
                tolarence.add(new ArrayList<Double>());
                stDev.add(new ArrayList<Double>(0));

//            }
        }

        for(int i=0;i<Main.totalUser;i++)
        {

            for(int j=0;j<18;j++)//index 16 for regularKey and 17 for count
            {
                centroids.get(i).add(0.0);
                if(j<17)
                {

                    tolarence.get(i).add(0.0);
                    stDev.get(i).add(0.0);

                }
            }
        }
        for(int i=0;i<Main.totalCount;i++)
        {
            int index=-1;
            index = Integer.parseInt(Main.userLabel.get(i))-1;

            if(index==-1)
            {
                System.out.println("index "+index+" step: "+Main.emotionLabel.get(i));
            }
            else{
                centroids.get(index).set(0, centroids.get(index).get(0)+Main.duration.get(i));
                centroids.get(index).set(1, centroids.get(index).get(1)+Main.mouseClickAvg.get(i));
                centroids.get(index).set(2, centroids.get(index).get(2)+Main.arrowKey.get(i));
                centroids.get(index).set(3, centroids.get(index).get(3)+Main.backSpace.get(i));
                centroids.get(index).set(4, centroids.get(index).get(4)+Main.cursorXDist.get(i));
                centroids.get(index).set(5, centroids.get(index).get(5)+Main.cursorYDist.get(i));
                //centroids.get(index).set(6, centroids.get(index).get(6)+Main.cursorYDist.get(i));
                centroids.get(index).set(6, centroids.get(index).get(6)+Main.doubleClick.get(i));
                centroids.get(index).set(7, centroids.get(index).get(7)+Main.upToDown.get(i));
                centroids.get(index).set(8, centroids.get(index).get(8)+Main.downToDown.get(i));
                centroids.get(index).set(9, centroids.get(index).get(9)+Main.downToUp.get(i));
                centroids.get(index).set(10, centroids.get(index).get(10)+Main.enterKey.get(i));
                centroids.get(index).set(11, centroids.get(index).get(11)+Main.fnKey.get(i));
                centroids.get(index).set(12, centroids.get(index).get(12)+Main.idleTime.get(i));
                centroids.get(index).set(13, centroids.get(index).get(13)+Main.leftClick.get(i));
                centroids.get(index).set(14, centroids.get(index).get(14)+Main.mouseScroll.get(i));
                centroids.get(index).set(15, centroids.get(index).get(15)+Main.rightClick.get(i));
                centroids.get(index).set(16, centroids.get(index).get(16)+Main.regularKey.get(i));



    //            centroids.get(index).set(17, centroids.get(index).get(17)+Main.arrowKey.get(i));

                centroids.get(index).set(17, centroids.get(index).get(17)+ 1);
            }

        }


        for(int i=0;i<Main.totalUser;i++)//find mean by dividing by the corresponding count
        {
            for(int j=0;j<17;j++)
            {
                if(centroids.get(i).get(17)!=0)
                centroids.get(i).set(j, centroids.get(i).get(j)/centroids.get(i).get(17));
            }
        }
        for(int i=0;i<Main.totalUser;i++)//find std dev for each centroid
        {
            Double tempDev=0.0;
            for(int j=0;j<Main.totalCount;j++)
            {
//                if(centroidLabel.get(i).equals(Main.emotionLabel.get(j)))
                if(Integer.parseInt(Main.userLabel.get(j))-1 == i)
                {
                   for(int k=0;k<17;k++)
                   {
//                       System.out.println("== "+i);
                        stDev.get(i).set(k, stDev.get(i).get(k)+((centroids.get(i).get(k)-Main.AllData.get(j).get(k))*(centroids.get(i).get(k)-Main.AllData.get(j).get(k))));
                   }
                }
            }
            for(int k=0;k<17;k++)
            {
                if(centroids.get(i).get(17)!=0)
                stDev.get(i).set(k, Math.sqrt(stDev.get(i).get(k)/centroids.get(i).get(17)));
            }
        }


//        for(int i=0;i<Main.totalCount;i++)
//        {
//
//        }

       assign(centroidsTemp,centroids);
       assign(stDevTemp,stDev);
//        centroidsTemp=centroids;
//        stDevTemp=stDev;
    }

public static void assign(ArrayList<ArrayList<Double>>x, ArrayList<ArrayList<Double>>y)
    {
        x.clear();
        for(int i=0;i<y.size();i++)
        {
            x.add(new ArrayList<Double>());
            for(int j=0;j<y.get(i).size();j++)
            {
                x.get(i).add(y.get(i).get(j));
            }
        }
    }
   public static void printCentroids()
    {
       for(int i=0;i<centroids.size();i++)
       {
           for(int j=0;j<17;j++)
           {
               System.out.print(" "+centroids.get(i).get(j));
           }
           System.out.println();

       }
   }
   public static void printStDevs()
    {
       for(int i=0;i<stDev.size();i++)
       {
           for(int j=0;j<17;j++)
           {
               System.out.print(" "+stDev.get(i).get(j));
           }
           System.out.println();
       }
   }
   
   public static void printMatrix()
    {
       for(int i=0;i<10;i++)
       {
//           if(i==1  || i==4 || i==5 || i==7)//here...............................................................................................
           for(int j=0;j<10;j++)
           {
               
               Double cnt=0.0;
               Double total=0.0;
//               if(j==1   || j==4 || j==5 || j==7)//here.........................................................................................
//               {
                       for (int k = 0; k < Main.totalCount; k++)
                   {
                       if(labelAssigned.get(k)==i)
                       {
                           total+=1.0;
                           if(centroidLabel.indexOf(Main.emotionLabel.get(k))==j)cnt++;
                       }
                   }
//                       double number = 123.675;
                    String s = String.format("%.2f", (cnt/total)*100.0);
                   System.out.print(s+" ");
//               }
//               System.out.print(cnt+" ");
           }
           System.out.println();
       }
    }

   public static void printMatrixForUser()
    {
       for(int i=0;i<Main.totalUser;i++)
       {
//           if(i==1  || i==4 || i==5 || i==7)//here...............................................................................................
           for(int j=0;j<Main.totalUser;j++)
           {

               Double cnt=0.0;
               Double total=0.0;
//               if(j==1   || j==4 || j==5 || j==7)//here.........................................................................................
//               {
                       for (int k = 0; k < Main.totalCount; k++)
                   {
                       if(labelAssigned.get(k)==i)
                       {
                           total+=1.0;
//                           if(centroidLabel.indexOf(Main.emotionLabel.get(k))==j)cnt++;
                           if(Integer.parseInt(Main.userLabel.get(k))-1==j)cnt++;

                       }
                       
                   }
//                       double number = 123.675;
                String s="0.0";
               if(total!=0)
                    {
                        s= String.format("%.2f", (cnt / total) * 100.0);
               }
               System.out.print(s+" ");
//               }
//               System.out.print(cnt+" ");
           }
           System.out.println();
       }
    }

   public static void sortUsersBasedOnStDevs()
    {

       ArrayList<Double>temp=new ArrayList<Double>();
       for(int i=0;i<Main.totalUser;i++)temp.add(0.0);

       for(int i=0;i<Main.totalUser;i++)
       {
           for(int j=1;j<17;j++)
           {
               temp.set(i, temp.get(i)+stDev.get(i).get(j));
           }
       }

       for(int i=1;i<Main.totalUser;i++)
       {
           for(int j=0;j<17;j++)
           {
               temp.set(i, temp.get(i)+centroids.get(i).get(j));
           }
       }


       ArrayList<Boolean>flag=new ArrayList<Boolean>();

       for(int i=0;i<Main.totalUser;i++)flag.add(Boolean.FALSE);

       for(int i=0;i<Main.totalUser;i++)
       {
           Double maxVal=-1.0;
           int maxId=-1;
           for(int j=0;j<Main.totalUser;j++)
           {
               if(!flag.get(j) && temp.get(j)>maxVal)
               {
                   maxVal=temp.get(j);
                   maxId=j;
               }
           }
           if(maxId!=-1)
           {
               flag.set(maxId, Boolean.TRUE);
               System.out.println(" "+maxId);
           }
       }
   }
   public static void sortUsersBasedOnNumbOfPoints()
    {
       ArrayList<Integer>temp=new ArrayList<Integer>();

       for(int i=0;i<Main.totalUser;i++)temp.add(0);
       for(int i=0;i<Main.totalUser;i++)
       {
           for(int j=0;j<Main.totalCount;j++)
           {
               if(labelAssigned.get(j)==i)temp.set(i, temp.get(i)+1);
           }
       }

       ArrayList<Boolean>flag=new ArrayList<Boolean>();
       for(int i=0;i<Main.totalUser;i++)flag.add(Boolean.FALSE);

       for(int i=0;i<Main.totalUser;i++)
       {
           Integer maxVal=-1;
           int maxId=-1;
           for(int j=0;j<Main.totalUser;j++)
           {
               if(!flag.get(j) && temp.get(j)>maxVal)
               {
                   maxVal=temp.get(j);
                   maxId=j;
               }
           }
           if(maxId!=-1)
           {
               flag.set(maxId, Boolean.TRUE);
               System.out.println(" "+maxId);
           }
       }
   }

   public static boolean contains(int i,int j)
    {
       int missed=0;
       for(int k=1;k<17;k++)
       {
//           if(k==16 ){
//           System.out.println("err at "+j);
            Double dist = centroids.get(j).get(k)-Main.AllData.get(i).get(k);
            if(dist<0)dist*=-1.0;

            if(dist>tolarence.get(j).get(k))missed++;
//           }
       }
       return missed<=missedTolerance;
   }
   public static void kMeans()
   {
       Double centroidDiff=1000000000.0;
       Double oFA=0.0,mFA=0.0,cFA=0.0;
       Double oFR=0.0,mFR=0.0,cFR=0.0;
       Double oS=0.0;
       Double oA=0.0;
       for(Double s=5.0;s>0.0;s-=0.05)
       {
           for(Double a=1.0;a>0.0;a-=0.05)//vary the average value
           {
               centroidDiff=100000000.0;
               for(int t=0;t<IterationLimit && centroidDiff>eps;t++)
               {
                   ArrayList<ArrayList<Double>>tempCent=new ArrayList<ArrayList<Double>>();
                   ArrayList<ArrayList<Double>>tempStDev=new ArrayList<ArrayList<Double>>();
//                   tempCent = centroids;
//                   tempStDev = stDev;
                   assign(tempCent,centroids);
                   assign(tempStDev,stDev);
                   for(int l=0;l<10;l++)
                   {
                       for(int u=0;u<17;u++)
                       {



//                           tolarence.get(l).set(u, (centroids.get(l).get(u)*a));
//                           tolarence.get(l).set(u, (stDev.get(l).get(u)*s));
                           tolarence.get(l).set(u,(centroids.get(l).get(u)*a)+(stDev.get(l).get(u)*s));
                           if(tolarence.get(l).get(u)<0)tolarence.get(l).set(u, tolarence.get(l).get(u)*-1);

                       }
                   }
                   labelAssigned.clear();
                   for(int i=0;i<Main.totalCount;i++)
                   {

                       labelAssigned.add(-1);
                       Double minDist=1000000000.0;
                       int minCentroidId=-1;
                       for(int j=0;j<10;j++)//calculate distance from cluster centroids
                       {
//                           if(j==1 || j==4 || j==5 || j==7 ){//here...............................................................................
                               Double tempDist = pointToCentroidDistance(i, j);
                               if(contains(i,j) && tempDist<minDist)
                               {
                                   minDist=tempDist;
                                   minCentroidId=j;
                                   labelAssigned.set(i, j); //assigned to cluster j
                               }
//                           }
                       }



                   }

               updateCentroids();
               updateStDevs();

               centroidDiff=DifferenceOfCentroid(tempCent,centroids);



               }
//               centroids = centroidsTemp;
//               stDev = stDevTemp;
               assign(centroids,centroidsTemp);
               assign(stDev,stDevTemp);

               Double falseAccept=0.0;
               Double falseReject=0.0;


               for(int i=0;i<Main.totalCount;i++)
               {
                   if(labelAssigned.get(i)==-1){falseReject++;continue;}
                   for(int j=0;j<10;j++)
                   {
//                         System.out.println(" assigned: "+labelAssigned.get(i));

                       if(labelAssigned.get(i)==j){

//                            if(!Main.emotionLabel.get(i).equals(centroidLabel.get(j)))
//                            {
//                                falseAccept++;
//                            }
                           if(!similar(i,j))falseAccept++;
                            break;
                       }
                   }
               }

               if((falseAccept+falseReject)<minErrCount)
               {
                   minErrCount = (falseAccept + falseReject);
                   oFA=falseAccept;
                   oFR=falseReject;
                   oS=s;
                   oA=a;
               }
               if(falseAccept<mFA)
               {
                   mFA=falseAccept;
                   cFR=falseReject;
               }
               if(falseReject<mFR)
               {
                   mFR=falseReject;
                   cFA=falseAccept;
               }

               System.out.println(s+" "+a+" "+ falseAccept/Main.totalCount+" "+falseReject/Main.totalCount);
               if(centroidDiff>eps)System.out.println("Sorry no optimal position...");
//               printMatrix();
           }
       }
       System.out.println("Min Error: "+minErrCount/Main.totalCount+" MinDiff: "+centroidDiff);
       System.out.println("Optimal False Accept: "+oFA+" Optimal False Reject: "+oFR);
       System.out.println("minimum false accept: "+mFA+" corresponding false reject: "+cFR);
       System.out.println("minimum false reject: "+mFR+" corresponding false accept: "+cFA);
       System.out.println("Optimal stDev PCT: "+oS+" Optimal Avg PCT: "+oA);




   }


   public static void kMeansForUser()
   {
       Double oFA=9999.0,mFA=9999.0,cFA=9999.0;
       Double oFR=9999.0,mFR=9999.0,cFR=9999.0;
       Double oS=0.0;
       Double oA=0.0;

               Double trueAccept=0.0;

       Double centroidDiff=100000000.0;

       for(Double s=5.0;s>=0.0;s-=0.05)
       {
           for(Double a=1.0;a>0.0;a-=0.05)//vary the average value
           {
               centroidDiff=100000000.0;


               for(int t=0;t<IterationLimit && centroidDiff>eps;t++)
               {
                   trueAccept=0.0;
                   ArrayList<ArrayList<Double>>tempCent=new ArrayList<ArrayList<Double>>();
                   ArrayList<ArrayList<Double>>tempStDev=new ArrayList<ArrayList<Double>>();
//                   tempCent = centroids;
                   assign(tempCent,centroids);
//                   tempStDev = stDev;
                   assign(tempStDev,stDev);

                   for(int l=0;l<Main.totalUser;l++)
                   {
                       for(int u=0;u<17;u++)
                       {



//                           tolarence.get(l).set(u, (centroids.get(l).get(u)*a));
//                           tolarence.get(l).set(u, (stDev.get(l).get(u)*s));
                           tolarence.get(l).set(u,(centroids.get(l).get(u)*a)+(stDev.get(l).get(u)*s));
                           if(tolarence.get(l).get(u)<0)tolarence.get(l).set(u, tolarence.get(l).get(u)*-1);

                       }
                   }
                   labelAssigned.clear();
                   for(int i=0;i<Main.totalCount;i++)
                   {

                       labelAssigned.add(-1);
                       Double minDist=1000000000.0;
                       int minCentroidId=-1;
                       for(int j=0;j<Main.totalUser;j++)//calculate distance from cluster centroids
                       {
//                           if(j==1 || j==4 || j==5 || j==7 ){//here...............................................................................
                               Double tempDist = pointToCentroidDistance(i, j);
                               if(contains(i,j) && tempDist<minDist )
                               {
                                   
                                       minDist=tempDist;
                                       minCentroidId=j;
                                       labelAssigned.set(i, j); //assigned to cluster j
                                   
                               }
//                           }
                       }



                   }

               updateCentroidsForUser();
               updateStDevsForUser();

               centroidDiff=DifferenceOfCentroidForUser(tempCent,centroids);
//               System.out.println("diff: "+centroidDiff);



               }
//               centroids = centroidsTemp;
//               stDev = stDevTemp;
               assign(centroids,centroidsTemp);
               assign(stDev,stDevTemp);

               Double falseAccept=0.0;
               Double falseReject=0.0;


               for(int i=0;i<Main.totalCount;i++)
               {
                   if(labelAssigned.get(i)==-1){falseReject++;continue;}
                   for(int j=0;j<Main.totalUser;j++)
                   {
//                         System.out.println(" assigned: "+labelAssigned.get(i));

                       if(labelAssigned.get(i)==j){

                            if(Integer.parseInt(Main.userLabel.get(i))-1 !=j)
                            {
                                falseAccept++;
                            }
//                           if(!similar(i,j))falseAccept++;
//                            break;
                       }
                   }
               }

               if((falseAccept+falseReject)<minErrCount)
                   
               {
                   minErrCount = (falseAccept + falseReject);
                   oFA=falseAccept;
                   oFR=falseReject;
                   oS=s;
                   oA=a;
               }
               if(falseAccept<mFA)
               {
                   mFA=falseAccept;
                   cFR=falseReject;
               }
               if(falseReject<mFR)
               {
                   mFR=falseReject;
                   cFA=falseAccept;
               }
               System.out.println(s+" "+a+" "+ falseAccept/Main.totalCount+" "+falseReject/Main.totalCount);
//               printMatrixForUser();
           }
       }
       System.out.println("Min Error: "+minErrCount/Main.totalCount+" final difference "+centroidDiff+" Optimal SPCT: "+oS+" Optimal APCT: "+oA+" tA: "+trueAccept);
       System.out.println("optimal false acceptance: "+oFA/Main.totalCount+" optimal false rejection: "+oFR/Main.totalCount);
       System.out.println("minimum false acceptance: "+mFA/Main.totalCount+" corresponding false rejection: "+cFR/Main.totalCount);
       System.out.println("minimum false rejection: "+mFR/Main.totalCount+" corresponding false acceptance: "+cFA/Main.totalCount);

//       printMatrixForUser();


   }


   public static Double DifferenceOfCentroid(ArrayList<ArrayList<Double>>x,ArrayList<ArrayList<Double>>y)
    {
       Double diff=0.0,Diff=0.0;

       for(int i=0;i<10;i++)
       {
//           if(i==1  || i==4 || i==5 || i==7){ //here...........................................................................................
           diff=0.0;
           for(int j=1;j<17;j++)
               {
//                   if(j!=2 && j!=6 && j!=11 && j!=12 && j!=14 && j!=15 && j!=16)

                   diff+=(x.get(i).get(j)-y.get(i).get(j))*(x.get(i).get(j)-y.get(i).get(j));
               }
               Diff+=Math.sqrt(diff);
//           }
       }
       return Diff;

   }

   public static Double DifferenceOfCentroidForUser(ArrayList<ArrayList<Double>>x,ArrayList<ArrayList<Double>>y)
    {
       Double diff=0.0;

//       for(int i=0;i<Main.totalUser;i++)
//       {
////           if(i==1  || i==4 || i==5 || i==7){ //here...........................................................................................
//               for(int j=0;j<17;j++)
//               {
////                    if(j==16)
//                   diff+=(x.get(i).get(j)-y.get(i).get(j))*(x.get(i).get(j)-y.get(i).get(j));
//               }
//               diff=Math.sqrt(diff);
////           }
//       }
       
       for(int i=0;i<x.size();i++)
       {
//           System.out.println(" "+x.get(i).get(3)+" "+y.get(i).get(3));
       }
       for(int i=0;i<x.size();i++)
       {
           for(int j=0;j<17;j++)
           {
               diff+=(x.get(i).get(j)-y.get(i).get(j))*(x.get(i).get(j)-y.get(i).get(j));
           }
       }
       diff=Math.sqrt(diff);
       return diff;

   }


   public static void updateCentroids()
    {
       for(int i=0;i<10;i++)
       {
//           if(i==1  || i==4 || i==5 || i==7 )//here...........................................................................................
//           {
               int cnt=0;
               for(int j=0;j<Main.totalCount;j++)
               {
                   if(labelAssigned.get(j)==i)
                   {
                       cnt++;
                       for(int k=0;k<17;k++)
                       {
                           centroids.get(i).set(k, centroids.get(i).get(k)+Main.AllData.get(j).get(k));
                       }
                   }
               }
               for(int k=0;k<17;k++)centroids.get(i).set(k, centroids.get(i).get(k)/(cnt+1));
//           }
       }
   }

   public static void updateCentroidsForUser()
    {
       
       for(int i=0;i<Main.totalUser;i++)
       {
//           if(i==1  || i==4 || i==5 || i==7 )//here...........................................................................................
//           {
               int cnt=0;
               for(int j=0;j<Main.totalCount;j++)
               {
                   if(labelAssigned.get(j)==i)
                   {
                       cnt++;
                       for(int k=0;k<17;k++)
                       {
                           centroids.get(i).set(k, centroids.get(i).get(k)+Main.AllData.get(j).get(k));
                       }
                   }
               }
               centroids.get(i).set(17, cnt+1.0);
//               System.out.print(" "+cnt);
               for(int k=0;k<17;k++)centroids.get(i).set(k, centroids.get(i).get(k)/(centroids.get(i).get(17)));
               
//           }
       }
   }


   public static void updateStDevs()
   {
       for(int i=0;i<10;i++)
       {
//           if(i==1  || i==4 || i==5 || i==7 ){//here........................................................................................
               int cnt=0;
               for(int j=0;j<Main.totalCount;j++)
               {
                   if(labelAssigned.get(j)==i)
                   {
                       cnt++;
                       for(int k=0;k<17;k++)
                       {
                           if(cnt==1)stDev.get(i).set(k, (stDev.get(i).get(k)*stDev.get(i).get(k))+((centroids.get(i).get(k)-Main.AllData.get(j).get(k))*(centroids.get(i).get(k)-Main.AllData.get(j).get(k))));
                           else stDev.get(i).set(k, stDev.get(i).get(k) + ((centroids.get(i).get(k) - Main.AllData.get(j).get(k)) * (centroids.get(i).get(k) - Main.AllData.get(j).get(k))));
                       }
                   }
               }
               for(int k=0;k<17;k++)stDev.get(i).set(k, Math.sqrt(stDev.get(i).get(k)/(cnt+1)));
//        }
       }
   }

   public static void updateStDevsForUser()
   {
       stDev.clear();
       for(int i=0;i<Main.totalUser;i++)
       {

           stDev.add(new ArrayList<Double>());
           for(int j=0;j<17;j++)
           {
               stDev.get(i).add(0.0);
           }
       }
       for(int i=0;i<Main.totalUser;i++)//find std dev for each centroid
        {
            Double tempDev=0.0;
            for(int j=0;j<Main.totalCount;j++)
            {
//                if(centroidLabel.get(i).equals(Main.emotionLabel.get(j)))
                if(labelAssigned.get(j) == i)
                {
                   for(int k=0;k<17;k++)
                   {
                        stDev.get(i).set(k, stDev.get(i).get(k)+((centroids.get(i).get(k)-Main.AllData.get(j).get(k))*(centroids.get(i).get(k)-Main.AllData.get(j).get(k))));
                   }
                }
            }
            for(int k=0;k<17;k++)
            {
                if(centroids.get(i).get(17)!=0)
                stDev.get(i).set(k, Math.sqrt(stDev.get(i).get(k)/centroids.get(i).get(17)));
            }
        }
   }

   public static void updateCentroid(int j,int i)
    {
       centroids.get(j).set(0, (centroids.get(j).get(0)+Main.duration.get(i))/2);
       centroids.get(j).set(1, (centroids.get(j).get(1)+Main.mouseClickAvg.get(i))/2);
       centroids.get(j).set(2, (centroids.get(j).get(2)+Main.arrowKey.get(i))/2);
       centroids.get(j).set(3, (centroids.get(j).get(3)+Main.backSpace.get(i))/2);
       centroids.get(j).set(4, (centroids.get(j).get(4)+Main.cursorXDist.get(i))/2);
       centroids.get(j).set(5, (centroids.get(j).get(5)+Main.cursorYDist.get(i))/2);
       centroids.get(j).set(6, (centroids.get(j).get(6)+Main.doubleClick.get(i))/2);
       centroids.get(j).set(7, (centroids.get(j).get(7)+Main.upToDown.get(i))/2);
       centroids.get(j).set(8, (centroids.get(j).get(8)+Main.downToDown.get(i))/2);
       centroids.get(j).set(9, (centroids.get(j).get(9)+Main.downToUp.get(i))/2);
       centroids.get(j).set(10, (centroids.get(j).get(10)+Main.enterKey.get(i))/2);
       centroids.get(j).set(11, (centroids.get(j).get(11)+Main.fnKey.get(i))/2);
       centroids.get(j).set(12, (centroids.get(j).get(12)+Main.idleTime.get(i))/2);
       centroids.get(j).set(13, (centroids.get(j).get(13)+Main.leftClick.get(i))/2);
       centroids.get(j).set(14, (centroids.get(j).get(14)+Main.mouseScroll.get(i))/2);
       centroids.get(j).set(15, (centroids.get(j).get(15)+Main.rightClick.get(i))/2);
       centroids.get(j).set(16, (centroids.get(j).get(16)+Main.regularKey.get(i))/2);


   }

   public static void knn()
    {
       for(int k=1;k<20;k++){
           long seed = System.nanoTime();
           Collections.shuffle(Main.AllData, new Random(seed));
           Collections.shuffle(Main.emotionLabel, new Random(seed));
          // Collections.shuffle(Main.AllData);

                Double fR=0.0;
                Double fA=0.0;
           for(int i=0;i<(Main.totalCount*20)/100;i++)
           {
//               System.out.println(" -_- ");
                Double maxDist=-99999.0;
                Double minDist=99999.0;
                ArrayList<Integer>neighbour = new ArrayList<Integer>();
                for(int j=0;j<Main.totalCount;j++)
                {
                    if(i!=j)
                    {
                         Double distance = euclideanDistance(i,j);
//                         if(distance<minDist)
//                         {
                             if(neighbour.size()<k)
                             {

                                 neighbour.add(j);
                             }
                            else
                             {
                                 Double maxx=-9999.0;
                                 Integer maxId=-1;
                                 for(int l=0;l<neighbour.size();l++)
                                 {
                                     if(euclideanDistance(i,neighbour.get(l))>maxx)
                                     {
                                         maxx=euclideanDistance(i,neighbour.get(l));
                                         maxId=neighbour.get(l);
                                     }
                                 }
                                 if(euclideanDistance(i,j)<maxx)
                                 {
                                     neighbour.remove(maxId);
                                     neighbour.add(j);
                                 }
                            }


//                         }
                    }
                }

                //check neighbour to predict class
                int[] vote={0,0,0,0,0,0,0,0,0,0};
                for(int l=0;l<neighbour.size();l++)
                {
                    if(Main.emotionLabel.get(neighbour.get(l)).equals("Neutral"))vote[0]++;
                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Amusement"))vote[1]++;
                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Happy"))vote[2]++;
                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Inspiring"))vote[3]++;

                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Surprise"))vote[4]++;
                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Sadness"))vote[5]++;
                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Sympathy"))vote[6]++;
                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Anger"))vote[7]++;
                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Disgust"))vote[8]++;
                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Fear"))vote[9]++;
//                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Neutral"))vote[0]++;

                }
                int classId=-1;
                int maxVote=-1;
                for(int l=0;l<10;l++)
                {
                    if(vote[l]>maxVote)
                    {
                        maxVote=vote[l];
                        classId=l;
                    }
                }
//                System.out.println(Main.emotionLabel.get(i)+" "+centroidLabel.get(classId));
//                if(!Main.emotionLabel.get(i).equals(centroidLabel.get(classId)))fA++;
                if(!similar(i,classId))fA++;
           }
                System.out.println(k+" "+(fA*100)/(Main.totalCount*20)+" "+(1.0-(fA*100)/(Main.totalCount*20)));
        }
   }

      public static void knnForUser()
    {
          Double minError=999999.0;
          int oK=-1;
       for(int k=1;k<20;k++){
           long seed = System.nanoTime();
           Collections.shuffle(Main.AllData, new Random(seed));
           Collections.shuffle(Main.emotionLabel, new Random(seed));
           Collections.shuffle(Main.userLabel, new Random(seed));

          // Collections.shuffle(Main.AllData);

                Double fR=0.0;
                Double fA=0.0;
                Double tA=0.0;
           for(int i=0;i<(Main.totalCount*20)/100;i++)
           {
//               System.out.println(" -_- ");
                Double maxDist=-999999.0;
                Double minDist=999999.0;
                ArrayList<Integer>neighbour = new ArrayList<Integer>();
                for(int j=0;j<Main.totalCount;j++)
                {
                    if(i!=j)
                    {
                         Double distance = euclideanDistance(i,j);
//                         if(distance<minDist)
//                         {
                             if(neighbour.size()<k)
                             {

                                 neighbour.add(j);
                             }
                            else
                             {
                                 Double maxx=-9999.0;
                                 Integer maxId=-1;
                                 for(int l=0;l<neighbour.size();l++)
                                 {
                                     if(euclideanDistance(i,neighbour.get(l))>maxx)
                                     {
                                         maxx=euclideanDistance(i,neighbour.get(l));
                                         maxId=neighbour.get(l);
                                     }
                                 }
                                 if(euclideanDistance(i,j)<maxx)
                                 {
                                     neighbour.remove(maxId);
                                     neighbour.add(j);
                                 }
                            }


//                         }
                    }
                }

                //check neighbour to predict class
                Double[] vote={0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
                
                for(int l=0;l<neighbour.size();l++)
                {
//                    vote[Integer.parseInt(Main.userLabel.get(neighbour.get(l)))-1]++;
                    vote[Main.AllData.get(neighbour.get(l)).get(17).intValue()-1]+=1;
//                    vote[Main.AllData.get(neighbour.get(l)).get(17).intValue()-1]/=euclideanDistance(i, neighbour.get(l));



//                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Neutral"))vote[0]++;

                }
                int classId=-1;
                Double maxVote=-1.0;
                for(int l=0;l<Main.totalUser;l++)
                {
                    if(vote[l]>maxVote)
                    {
                        maxVote=vote[l];
                        classId=l;
                    }
                }
//                System.out.println(Main.emotionLabel.get(i)+" "+centroidLabel.get(classId));
//                if(!Main.emotionLabel.get(i).equals(centroidLabel.get(classId)))fA++;
//                if(Integer.parseInt(Main.userLabel.get(i))-1!=classId)fA++;
                  if(Main.AllData.get(i).get(17).intValue()-1!=classId)
                  {
                      fA++;
                    }
                    else
                  {
//                      System.out.println("bingoo "+classId);
                      tA++;
                  
                   }
//                if(!similar(i,classId))fA++;
           }
                System.out.println(k+" "+(fA*100)/(Main.totalCount*20)+" "+(tA*100)/(Main.totalCount*20));
                if(fA<minError)
                {
                    minError = fA;
                    oK=k;
                }
        }
          System.out.println("MinError: "+(minError*100)/(Main.totalCount*20));
          System.out.println("Optimal k: "+oK);

   }

            public static void knnForGender()
    {
          Double minError=999999.0;
          int oK=-1;
       for(int k=1;k<20;k++){
           long seed = System.nanoTime();
           Collections.shuffle(Main.AllData, new Random(seed));
           Collections.shuffle(Main.emotionLabel, new Random(seed));
           Collections.shuffle(Main.userLabel, new Random(seed));

          // Collections.shuffle(Main.AllData);

                Double fR=0.0;
                Double fA=0.0;
                Double tA=0.0;
           for(int i=0;i<(Main.totalCount*20)/100;i++)
           {
//               System.out.println(" -_- ");
                Double maxDist=-999999.0;
                Double minDist=999999.0;
                ArrayList<Integer>neighbour = new ArrayList<Integer>();
                for(int j=0;j<Main.totalCount;j++)
                {
                    if(i!=j)
                    {
                         Double distance = euclideanDistance(i,j);
//                         if(distance<minDist)
//                         {
                             if(neighbour.size()<k)
                             {

                                 neighbour.add(j);
                             }
                            else
                             {
                                 Double maxx=-9999.0;
                                 Integer maxId=-1;
                                 for(int l=0;l<neighbour.size();l++)
                                 {
                                     if(euclideanDistance(i,neighbour.get(l))>maxx)
                                     {
                                         maxx=euclideanDistance(i,neighbour.get(l));
                                         maxId=neighbour.get(l);
                                     }
                                 }
                                 if(euclideanDistance(i,j)<maxx)
                                 {
                                     neighbour.remove(maxId);
                                     neighbour.add(j);
                                 }
                            }


//                         }
                    }
                }

                //check neighbour to predict class
                Double[] vote={0.0,0.0};

                for(int l=0;l<neighbour.size();l++)
                {
//                    vote[Integer.parseInt(Main.userLabel.get(neighbour.get(l)))-1]++;
                    vote[Integer.parseInt(Main.GenderLabel.get(neighbour.get(l)))-1]+=1;
//                    vote[Main.AllData.get(neighbour.get(l)).get(17).intValue()-1]/=euclideanDistance(i, neighbour.get(l));



//                    else if(Main.emotionLabel.get(neighbour.get(l)).equals("Neutral"))vote[0]++;

                }
                int classId=-1;
                Double maxVote=-1.0;
                for(int l=0;l<2;l++)
                {
                    if(vote[l]>maxVote)
                    {
                        maxVote=vote[l];
                        classId=l;
                    }
                }
//                System.out.println(Main.emotionLabel.get(i)+" "+centroidLabel.get(classId));
//                if(!Main.emotionLabel.get(i).equals(centroidLabel.get(classId)))fA++;
//                if(Integer.parseInt(Main.userLabel.get(i))-1!=classId)fA++;
                  if(Integer.parseInt(Main.GenderLabel.get(i))-1!=classId)
                  {
                      fA++;
                    }
                    else
                  {
//                      System.out.println("bingoo "+classId);
                      tA++;

                   }
//                if(!similar(i,classId))fA++;
           }
                System.out.println(k+" "+(fA*100)/(Main.totalCount*20)+" "+(tA*100)/(Main.totalCount*20));
                if(fA<minError)
                {
                    minError = fA;
                    oK=k;
                }
        }
          System.out.println("MinError: "+(minError*100)/(Main.totalCount*100));
          System.out.println("Optimal k: "+oK);

   }

               public static void knnNewforSingle()
    {
                   System.out.println(Main.SingleDataForSingleEmotion.size());
                   int mFA=9999;
       for(int k=1;k<40;k++){
           long seed = System.nanoTime();
           Collections.shuffle(Main.SingleDataForSingleEmotion, new Random(seed));
           Collections.shuffle(Main.emotionLabel, new Random(seed));
          // Collections.shuffle(Main.AllData);

                int fR=0;
                int fA=0;
           for(int i=0;i<(Main.SingleDataForSingleEmotion.size()*100)/100;i++)
           {
//               System.out.println(" -_- ");
                Double maxDist=-99999.0;
                Double minDist=99999.0;
                ArrayList<Integer>neighbour = new ArrayList<Integer>();
                for(int j=0;j<Main.SingleDataForSingleEmotion.size();j++)
                {
                    if(i!=j)
                    {
                         Double distance = newSingleEuclideanDistance(i,j);
//                         if(distance<minDist)
//                         {
                             if(neighbour.size()<k)
                             {

                                 neighbour.add(j);
                             }
                            else
                             {
                                 Double maxx=-9999.0;
                                 Integer maxId=-1;
                                 for(int l=0;l<neighbour.size();l++)
                                 {
                                     if(newSingleEuclideanDistance(i,neighbour.get(l))>maxx)
                                     {
                                         maxx=newSingleEuclideanDistance(i,neighbour.get(l));
                                         maxId=neighbour.get(l);
                                     }
                                 }
                                 if(newSingleEuclideanDistance(i,j)<maxx)
                                 {
                                     neighbour.remove(maxId);
                                     neighbour.add(j);
                                 }
                            }


//                         }
                    }
                }

                //check neighbour to predict class
                int[] vote={0,0,0,0,0,0,0,0,0,0};
                for(int l=0;l<neighbour.size();l++)
                {

                    vote[Main.SingleDataForSingleEmotion.get(neighbour.get(l)).get(18).intValue()]++;

                }
                int classId=-1;
                int maxVote=-1;
                for(int l=0;l<10;l++)
                {
                    if(vote[l]>maxVote)
                    {
                        maxVote=vote[l];
                        classId=l;
                    }
                }
//                System.out.println(Main.emotionLabel.get(i)+" "+centroidLabel.get(classId));
                if(Main.SingleDataForSingleEmotion.get(i).get(18).intValue()!=classId)fA++;
//                if(!similar(i,classId))fA++;
           }
                System.out.println(k+" "+fA+" "+Main.totalCount);
                if(fA<mFA)mFA=fA;
        }
                   System.out.println("min fA: "+mFA);
   }

}
