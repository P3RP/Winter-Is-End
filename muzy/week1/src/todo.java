package muzy.week1.src;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class todo {

    static void textToList(ArrayList<String> list){
        try {
            File file = new File("list.txt");
            Scanner file_sc = new Scanner(file);
            while(file_sc.hasNext()){
                list.add(file_sc.next());
            }
            file_sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void listToText(ArrayList<String> list){
        try {
            PrintWriter pw = new PrintWriter("list.txt");

            Iterator<String> iter = list.iterator();

            while(iter.hasNext()){
                pw.print(iter.next()+" ");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        
    }

    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        int command;
        String work;
        CommandList command_info = new CommandList();

        textToList(list);

        while(true){
            command_info.commandInfo();
            command = sc.nextInt();

            if(command == 0){

                //그냥 반복 이용
                // for(int i=0;i<list.size();i++){
                //     System.out.println(String.valueOf(i)+" : "+list.get(i));
                // }

                int i = 0;
                Iterator<String> iter = list.iterator();
                System.out.println("------ToDo List---------");
                while(iter.hasNext()){
                    System.out.println(String.valueOf(i)+" : "+iter.next());
                    i++;
                }
                System.out.println();
            }
            else if(command == 1){
                System.out.println("push work");
                work = sc.next();
                list.add(work);
            }
            else if(command == 2){
                System.out.println("delete work.");
                command = sc.nextInt();
                list.remove(command);
            }
            else if(command == 3){
                break;
            }
            System.out.println();
        }
        sc.close();
        
        listToText(list);

    }
}

class CommandList{
    public void commandInfo(){
        System.out.println("command");
        System.out.println("0 : todo list");
        System.out.println("1 : add work");
        System.out.println("2 : delete work");
        System.out.println("3 : exit");
    }
}