package muzy.javaSetting;

import java.util.Scanner;
import java.io.*;

class Calculator {
    static double PI = 3.14;
    int left, right;
 
    public Calculator(int left, int right){
        this.left = left;
        this.right = right;
    }
 
    public void sum() {
        System.out.println(this.left + this.right);
    }
 
    public void avg() {
        System.out.println((this.left + this.right) / 2);
    }
}

public class hello {
    public static void Number(int j){
        int i = j;
        int a=0;
        while(a<i){
            System.out.println(a);
            a++;
        }
    }

    public static void main(String[] args){
        try {
            File file = new File("out.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextInt()){
                System.out.println(sc.nextInt()*1000);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
