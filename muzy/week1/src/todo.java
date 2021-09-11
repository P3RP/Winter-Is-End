package muzy.week1.src;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;


public class todo {
    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        int command;
        String work;
        
        while(true){
            System.out.println("command");
            System.out.println("0 : todo list");
            System.out.println("1 : add work");
            System.out.println("2 : delete work");
            System.out.println("3 : exit");

            command = sc.nextInt();
            if(command == 0){

                //그냥 반복 이용
                // for(int i=0;i<list.size();i++){
                //     System.out.println(String.valueOf(i)+" : "+list.get(i));
                // }

                int i = 0;
                Iterator<String> iter = list.iterator();
                while(iter.hasNext()){
                    System.out.println(String.valueOf(i)+" : "+iter.next());
                    i++;
                }
            }
            else if(command == 1){
                System.out.println("push work");
                work = sc.next();
                list.add(work);
            }
            else if(command == 2){
                System.out.println("delete work");
                command = sc.nextInt();
                list.remove(command);
            }
            else if(command == 3){
                break;
            }
        }
        sc.close();
    }
}
