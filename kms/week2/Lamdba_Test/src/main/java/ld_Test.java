import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class ld_Test {

    //함수형 인터페이스
    //구현해야 할 메소드가 1개이므로 Functional Interface
    @FunctionalInterface
    public interface Math{
        public int Cal(int first,int second);
    }

    //구현해야 할 메소드가 두개 이므로 Functional Interface가 아님(오류)
    /*
    @FunctionalInterface
    public interface Math2{
        public int Cal(int first,int second);
        public int Cal2(int first,int second);
    }
    */

    public static void main(String[] args) {
        Math plusLamdba = (first,second) -> first + second;
        System.out.println(plusLamdba.Cal(3,4));

        Math minusLamdba = (first,second) -> first - second;
        System.out.println(minusLamdba.Cal(3,4));

        // IntFunction<R>
        // int 값의 인수를 받아들이고 결과를 생성하는 함수를 나타냄.
        IntFunction intSum = (x) -> x+1;
        System.out.println("intsum = " + intSum.apply(1));

        IntFunction intmod = (x) -> x/2;
        System.out.println("intmod = " + intmod.apply(1));

        //BinaryOperator<T>
        //동일한 유형의 두 피연산자에 대한 연산을 나타냄. 피연산자와 동일한 유형의 결과를 생성
        //보니까 string형태가 되어야함. 3,4를 넣었을 때 3 + 4 = 7이 아닌 3 4가 출력
        BinaryOperator stringSum = (x,y) -> x + " " + y;
        System.out.println(stringSum.apply(3,4));
        System.out.println(stringSum.apply("hello","world"));

        //등등 https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

        /*
            Stream API
         */

        //example.stream().filter(x -> x < 2).count
        // stream() <- 스트림생성
        // filter <- 중간 연산 (스트림 변환) - 연속 수행 가능
        // count <- 최종 연산 (스트림 사용) - 마지막에 단 한번만 사용 가능

        /*
        Stream은 데이터를 변경하지 않는다.
        Stream은 1회용
        Stream은 지연 연산 수행
        Stream은 병령 실행 가능
         */
        // 명령어는 distinct(), sorted(),filter(Predicate<T> predicate), limit(long maxSize(), skip(ling n) 등..
        //최종명령어는 또 따로 있음. forEach(), count() 등...
        // reduce 연산이라는 것도 있는데, stream의 요소를 하나씩 줄여가며 작동함.
        // collector 연산은 Stream의 요소를 수집하여 요소를 그룹화 하거나 결과를 담아 반환하는데 사용
        IntStream.range(1,11).filter(i -> i%2 == 0).forEach(System.out::println);
        System.out.println(IntStream.range(0,1001).
                skip(500).
                filter(i -> i%2 ==0).
                filter(i->i%5 == 0).
                sum());


    }



}
