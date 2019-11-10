package com.company.lab01pkg;

import javax.swing.*;

public class Lab01Main {
    public static void main(String[] args)
    {
//        System.out.println("Hello");
//        Car car = new Car("Toyota");
//
//        System.out.println(car);

//        int liczba = 0b1101_1000;
//        System.out.println("Chose base");
//        Scanner scanner = new Scanner(System.in);
//
//        String input = scanner.nextLine();
//
//        switch (input)
//        {
//            case "dziesiec": System.out.println(liczba); break;
//            case "trzy": System.out.println(Integer.toOctalString(liczba)); break;
//            case "szesnascie": System.out.println(Integer.toHexString(liczba)); break;
//            default: System.out.println("Wrong base");
//        }

//        try
//        {
//            Person me = new Person("Juliusz", "Malka", "97092401211");
//            System.out.println(me);
//        }
//        catch(Exception exc)
//        {
//            System.out.println("Incorrect PESEL");
//        }

        String input = JOptionPane.showInputDialog("Input expression;");
        String[] elements = input.split(" ");
        StringBuilder output = new StringBuilder();
        boolean prevOp = false;

        IntegersMap integersMap = new IntegersMap();
        SignsMap signsMap = new SignsMap();


        if(elements.length != 5)
        {
            JOptionPane.showMessageDialog(null,"Not enough or Too many parts");
            return;
        }

        try {
            for (String el : elements) {
                if (el.length() > 2) {
                    JOptionPane.showMessageDialog(null, "To long number/expression");
                }
                if (el.contains("[a-zA-Z]")) {
                    JOptionPane.showMessageDialog(null, "Not a number");
                    return;
                }
                if (el.equals("+") || el.equals("-") || el.equals("*")) {
                    if (prevOp) {
                        JOptionPane.showMessageDialog(null, "Wrong expression (doubled {+-*})");
                        return;
                    }
                    prevOp = true;
                    output.append(signsMap.getMap().get(el) + " ");
                } else {
                    prevOp = false;
                    output.append(integersMap.getFromString(el) + " ");
                }
            }

            JOptionPane.showMessageDialog(null, output);
        }
        catch (Exception exc)
        {
            System.out.println(exc);
        }
    }
}
