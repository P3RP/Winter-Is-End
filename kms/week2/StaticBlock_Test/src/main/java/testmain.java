public class testmain {
    public static void main(String[] args) {
        //Init.b = 3;; //static initializer block 호출
        System.out.println("Init.b = " + Init.b);
        System.out.println("-------- instance 생성 전 --------");
        Init block = new Init();
        System.out.println("--------                 --------");
        new Init();
        System.out.println("-------- instance 생성 후 --------");


    }

}
class Init extends MySuper{
    private int a;
    public int [] arr = new int[5];
    public static int b;

    //instance initializer block(인스턴스 초기화 블럭)
    {
        System.out.println("++++ initializer block 실행 ++++");
        a = 11;
        // b = 22; 불가능

        for(int i = 0 ; i < 5 ; ++i){
            arr[i] = i*3;
        }

        System.out.println("인스턴스 변수 a = " + a);
        System.out.println("인스턴스변수 arr 길이 = " + arr.length);
        System.out.println("클래스 변수 b = " + b);
    }

    //static initialzier block(클래스 초기화 블럭)
    static {
        // a = 1; error
        b = 2;
        System.out.println("Init.b = " + Init.b);
        System.out.println("++++ static initialization block 실행");
    }

    Init(){
        super();
        System.out.println("생성자");
    }

}

class MySuper{
    MySuper(){
        System.out.println("Mysuper의 생성자");
    }
}

