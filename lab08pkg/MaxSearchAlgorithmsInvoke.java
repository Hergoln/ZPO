package com.company.lab08pkg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaxSearchAlgorithmsInvoke
{
    public class MaxSearchAlgorithms
    {
        public List<Integer> LeftRightScan(List<Integer> list)
        {
            List<Integer> maxes = new ArrayList<>();
            maxes.add(list.get(0));
            for(int i = 1; i < list.size(); ++i)
            {
                if(list.get(i) > maxes.get(maxes.size()-1)) maxes.add(list.get(i));
            }

            return maxes;
        }

        public List<Integer> RightLeftScan(List<Integer> list)
        {
            List<Integer> maxes = new ArrayList<>();

            for(int i = list.size()-1; i > 0; --i)
            {
                if(maxes.isEmpty())
                    maxes.add(list.get(i));
                else
                if(list.get(i) > maxes.get(maxes.size()-1)) maxes.add(list.get(i));
            }

            return maxes;
        }

        public List<Integer> OddEvenScan(List<Integer> list)
        {
            List<Integer> maxes = new ArrayList<>();
            for(int i = 0; i < list.size(); ++i)
            {
                if(i%2 != 0)
                    if(maxes.isEmpty())
                        maxes.add(list.get(i));
                    else
                    if (list.get(i) > maxes.get(maxes.size() - 1)) maxes.add(list.get(i));
            }

            for(int i = 0; i < list.size(); ++i)
            {
                if(i%2 == 0)
                    if(maxes.isEmpty())
                        maxes.add(list.get(i));
                    else
                    if(list.get(i) > maxes.get(maxes.size()-1)) maxes.add(list.get(i));
            }

            return maxes;
        }

        public List<Integer> DummyFun(List<Integer> list)
        {
            System.out.println("DummyFun invoked");
            return null;
        }

        public List<Integer> DummyDumDumFunction(List<Integer> list)
        {
            System.out.println("DummyDumDumFunction invoked");
            return null;
        }
    }

    public void doStuff(List<Integer> list)
    {
        MaxSearchAlgorithms maxObject = new MaxSearchAlgorithms();
        List<Method> methods =  Arrays.asList(maxObject.getClass().getDeclaredMethods()).stream().filter(method -> method.getName().toLowerCase().contains("scan")).collect(Collectors.toList());
        for (Method m: methods)
        {
            try
            {
                List<Integer> l = (List<Integer>)m.invoke(maxObject, list);
                for (Integer i: l) {
                    System.out.print(i + " ");
                }
                System.out.println();
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
        }
    }

}

