/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clusteringthesisdata;

import java.util.ArrayList;

/**
 *
 * @author Shiku
 */
public class Point {
    ArrayList<Double>coord;
    ArrayList<Double>tolarence;

    Double distance(Point p)
    {
        Double dis=0.0;
        for(int i=0;i<coord.size();i++)
        {
            dis+=(coord.get(i)-p.coord.get(i))*(coord.get(i)-p.coord.get(i));
        }
        dis = Math.sqrt(dis);

        return dis;

    }

}
