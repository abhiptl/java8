package com.abhi.java8action.ch2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Ch2Test {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();

        List<Apple> greenApples = filterApples(apples, new GreenApplePredicate());
        printApples(apples, new ToStringApplePrinter());

        // using anonymous inner class
        printApples(apples, new ApplePrinter() {
            @Override
            public void print(Apple apple) {
                System.out.println("Apple's color is "+apple.getColor()+" and weight is "+apple.getWeight());
            }
        });



        filterCollections(apples, new GenericPredicate<Apple>() {
            @Override
            public boolean test(Apple o) {
                return "green".equals(o.getColor());
            }
        });

        filterApples(apples, (Apple a) -> "green".equals(a.getColor()));
        filterCollections(apples, (Apple a) -> "green".equals(a.getColor()));
        filterCollectionsWithExistingPredicate(apples, (Apple a) -> a.getWeight() > 150);

        Predicate<Apple> weightedApplePredicate = (a) -> a.getWeight() > 150;
        GenericPredicate<Apple> weightedGenericApplePredicate = (a) -> a.getWeight() > 150;
        filterCollections(apples, weightedGenericApplePredicate);
    }

    public static List<Apple> filterApples(List<Apple> apples, String color) {
        List<Apple> result = new ArrayList<>();

        for(Apple apple : apples) {
            if(color.equals(apple.getColor())) {
                result.add(apple);
            }
        }

        return  result;
    }

    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();

        for(Apple apple : apples) {
            if(predicate.test(apple)) {
                result.add(apple);
            }
        }

        return  result;
    }

    public static void printApples(List<Apple> apples, ApplePrinter applePrinter) {
        for(Apple apple : apples) {
            applePrinter.print(apple);
        }
    }


    public static <T> List<T> filterCollections(List<T> collections, GenericPredicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for(T item : collections) {
            if(predicate.test(item)) {
                result.add(item);
            }
        }

        return  result;
    }

    public static <T> List<T> filterCollectionsWithExistingPredicate(List<T> collections, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for(T item : collections) {
            if(predicate.test(item)) {
                result.add(item);
            }
        }

        return  result;
    }
}

