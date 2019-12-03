package com.company.lab08pkg;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class zad2Class
{
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface IgnoreEquals {}

    private String first;
    private int second;
    private boolean third;
    private List<Integer> fourth;

//    @Override
//    public boolean equals(Object that) {
//        return  this == that
//                && that instanceof zad2Class
//                && ((zad2Class)that).getFirst().equals(this.first)
//                && ((zad2Class)that).getSecond() == this.second
//                && ((zad2Class)that).getThird() == this.third;
//    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || !(obj instanceof zad2Class)) return false;

        try {
            List<Method> methods = Arrays.asList(this.getClass().getDeclaredMethods()).stream().filter(method -> method.getName().toLowerCase().startsWith("get")).collect(Collectors.toList());
            for (Method m : methods) {
                if (!m.isAnnotationPresent(IgnoreEquals.class))
                    if (!m.invoke(this).equals(m.invoke(obj))) return false;
            }
        }
        catch (IllegalAccessException illegal)
        {
            System.out.println("nunu, method is private");
        }
        catch (InvocationTargetException invoke)
        {
            System.out.println("meh, this method is not this objects method");
        }
        catch (ClassCastException cast)
        {
            System.out.println("ajajaj, this method doesn't return List<Integer>");
        }
        return true;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setThird(boolean third) {
        this.third = third;
    }

    public void setFourth(List<Integer> fourth) {
        this.fourth = fourth;
    }

    public String getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public boolean isThird() {
        return third;
    }

    @IgnoreEquals
    public List<Integer> getFourth() {
        return fourth;
    }
}
