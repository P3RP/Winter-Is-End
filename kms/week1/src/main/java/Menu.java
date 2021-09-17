import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static DB db = new DB();
    //private static GadianTable gadianTable = new GadianTable();

    public static void menurun(){

        while(true) {
            int checkmenu = Viewmenu();
            if(checkmenu == 6){
                return;
            }
            Selectmenu(checkmenu);
        }

    }
    public static int Viewmenu(){
        int menunum;
        Scanner sc = new Scanner(System.in);


        System.out.println("1. Add Characters");
        System.out.println("2. Remove Characters");
        System.out.println("3. Search Character");
        System.out.println("4. All Show Character Schdule");
        System.out.println("5. Exit");
        menunum = sc.nextInt();

        return menunum;
    }
    public static void Selectmenu(int checkmenu){
        if(checkmenu == 1){
            int level;
            String id;
            String job;
            Scanner sc = new Scanner(System.in);

            System.out.println("you select add character");
            System.out.println("Input your character ID");
            id = sc.next();
            System.out.println("Input your character LEVEL");
            level = sc.nextInt();
            System.out.println("Input your character JOB");
            job = sc.next();

            if(db.save(id,level,job)){
                System.out.println("success input character");
            }

        }
        else if(checkmenu == 2){
            String id;
            Scanner menu2sc = new Scanner(System.in);

            System.out.println("input remove Character Id");
            id = menu2sc.next();
            if(db.remove(id)){
                System.out.println("success remove character");
            }
        }
        else if(checkmenu==3){
            String id;
            Scanner menu3sc = new Scanner(System.in);

            System.out.println("input search Character Id");
            id = menu3sc.next();
            List<Pair> Character = db.search(id);
            if(Character == null){
                System.out.println("null exception try later");
            }
            else{
                System.out.println(Character.get(0).first() + Character.get(0).second());
            }
        }
        else if(checkmenu == 4 ){
            db.printall();

        }


    }
}
