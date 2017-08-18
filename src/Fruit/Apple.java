package Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2017/8/15.
 */
public class Apple extends Fruit {
    public static void main(String[] args) {
        Apple apple=new Apple();
        List<? super Apple> fruit=new ArrayList<Fruit>();
        fruit.add(new Apple());
    }
}
