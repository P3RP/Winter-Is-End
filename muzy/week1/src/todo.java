package muzy.week1.src;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class todo {

    static void textToList(ArrayList<String> list){                   //static : 다른 곳에서 인스턴스를 생성하지 않아도 사용할 수 있음. 그냥 void : 인스턴스 생성해야 됨
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
        HashMap<String,String> map = new HashMap<String,String>();
        String id, password;

        System.out.println("Put your ID");
        id = sc.next();
        System.out.println("Put your Password");
        password = sc.next();
        
        map.put(id,password);

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
                String current_pw;
                System.out.println("put your current password");
                current_pw = sc.next();
                if(current_pw.equals(map.get(id))){                                    //자바에서 String 비교는 equals. ==는 주소값 비교
                    System.out.println("put password to change");
                    current_pw = sc.next();
                    map.replace(id, current_pw);
                    System.out.println(map);
                }
                else{
                    System.out.println("Invalid password. Try again.");
                }
            }
            else if(command == 4){
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
        System.out.println("3 : change your password");
        System.out.println("4 : exit");
    }
}