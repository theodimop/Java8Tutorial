import java.util.Arrays;
import java.util.List;

/**
 * Created by Theo on 6/6/2016.
 */
public class Main {

    public static void main(String[] args){
        List<Integer> integerOddList = Arrays.asList(1,3,9,11,17,7,13);
        List<Integer> integerEvenList = Arrays.asList(0,10,14,16,20);

        integerOddList.sort( (e1,e2) -> e1.compareTo(e2));

        integerOddList.forEach(System.out::println);

    }


}
