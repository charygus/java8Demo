package com.viho.streamAPI;

import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author viho
 *  map中可以存放指定字段的数据  （select ....） map取出的为指定字段的所有数据
 *  filter 对于条件进行过滤     （where....）  filter取出的还是完整的对象
 * @create 2020-11-12上午 9:25
 */
public class TestTransaction {
    List<Transaction> transactions = null;
    //测试 初始化方法
    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
    //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
    @Test
    public void test1(){
        transactions.stream()
                .filter((f)->f.getYear()==2011)
                .sorted((t1,t2)->Integer.compare(t1.getValue(),t2.getValue()))
                .forEach(System.out::println);
    }
    //2. 交易员都在哪些不同的城市工作过？
    @Test
    public void test2(){
        transactions.stream()
                .map((t)->t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }
    //3. 查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test3(){
        transactions.stream()
                .filter((t)->t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted((t1,t2)->t1.getName().compareTo(t2.getName()))
                .distinct()
                .forEach(System.out::println);
    }
    //4. 返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test4(){
        transactions.stream().map((t)->t.getTrader().getName())
                .sorted().forEach(System.out::println);
        System.out.println("-------------------------------------------");
        //将每个名字拼接在一起成为一个字符串
        String reduce = transactions.stream().map((t) -> t.getTrader().getName())
                .reduce("", String::concat);
        System.out.println("reduce = " + reduce);
        System.out.println("-------------------------------------------");
        //将每个名字分成一个个字符
       transactions.stream().map((t) -> t.getTrader().getName())
                .flatMap(TestTransaction::filterCharacter)
                .sorted((t1, t2) -> t1.compareToIgnoreCase(t2))
                .forEach(System.out::println);
    }

    //5. 有没有交易员是在米兰工作的？
    @Test
    public void test5(){
        boolean milan = transactions.stream().allMatch((t) -> t.getTrader().getCity().equals("Milan"));
        System.out.println("milan = " + milan);
    }
    //6. 打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6(){
        Optional<Integer> cambridge = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println("cambridge = " + cambridge);
    }
    //7. 所有交易中，最高的交易额是多少
    @Test
    public void test7(){
        Optional<Integer> max = transactions.stream()
                .map((t) -> t.getValue())
                .max(Integer::compare);
        System.out.println("max = " + max);
    }
    @Test
    //8. 找到交易额最小的交易
    public void test8(){
        Optional<Integer> min = transactions.stream().map((t) -> t.getValue())
                .min(Integer::compare);
        System.out.println("min = " + min);
    }

    public static Stream<String> filterCharacter(String str){
        List<String> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }

        return list.stream();
    }
}
