/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheetproject;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class Difference {
  	private int[] elements;
  	public int maximum;
        public int maximumDifference;
	// Add your code here
    public int b[]=new int[50];
    public int i=0;
    int m=0;
    int n=1;
     public Difference(int d[])
    {
        
        //for(int c=0;c<d.length;c++){
           
            //elements[c]=d[c];
        elements=d;
            
        }
    
    public void computeDifference()
    {
        for(int j=0;j<elements.length;j++)
        {
            for(int k=0;k<elements.length;k++)
            b[i++]=Math.abs(elements[j]-elements[k]);
        }
        for(int o=0;o<b.length;o++)
            System.out.println(""+b[o]);
    
    maximumDifference=b[0];
    for(int n=1;n<b.length;n++)
    {
        if(b[n]>maximumDifference)
        maximumDifference=b[n];
    }
}
   
    /*return maximumDifference;*/

} // End of Difference class}

   
    /*return maximumDifference;*/


public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of elements");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("enter elements");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        Difference difference = new Difference(a);

        difference.computeDifference();

        System.out.print(difference.maximumDifference);
    }
}