package com.company.lab02pkg;

import java.util.HashMap;

public class LevQWERTY
{
    private static HashMap<Character, String> valuesMap;

    public LevQWERTY()
    {
        valuesMap = new HashMap<>();

        valuesMap.put('Q',"W");
        valuesMap.put('W',"QE");
        valuesMap.put('E',"WR");
        valuesMap.put('R',"ET");
        valuesMap.put('T',"RY");
        valuesMap.put('Y',"TU");
        valuesMap.put('U',"YI");
        valuesMap.put('I',"UO");
        valuesMap.put('O',"IP");
        valuesMap.put('P',"O");

        valuesMap.put('A',"S");
        valuesMap.put('S',"AD");
        valuesMap.put('D',"SF");
        valuesMap.put('F',"DG");
        valuesMap.put('G',"FH");
        valuesMap.put('H',"GJ");
        valuesMap.put('J',"HK");
        valuesMap.put('K',"JL");
        valuesMap.put('L',"K");

        valuesMap.put('Z',"X");
        valuesMap.put('X',"ZC");
        valuesMap.put('C',"XV");
        valuesMap.put('V',"CB");
        valuesMap.put('B',"VN");
        valuesMap.put('N',"BM");
        valuesMap.put('M',"N");
    }

    public static Double compute(String s, String t)
    {
        if(s == null || t == null) return -1.;

        double[][] matrix = new double[s.length()+1][t.length()+1];
        String s1 = s.toUpperCase(), s2 = t.toUpperCase();
        double cost = 0;
        for(int i = 0; i <= s.length(); ++i) matrix[i][0] = i;
        for(int i = 0; i <= t.length(); ++i) matrix[0][i] = i;

        if (valuesMap == null)
        {
            for (int i = 1; i < matrix.length; ++i)
            {
                for (int j = 1; j < matrix[0].length; ++j)
                {
                    if (s1.charAt(i-1) != s2.charAt(j-1))
                    {
                        cost = 1;
                    } else
                        cost = 0;

                    matrix[i][j] = Math.min(Math.min(matrix[i][j - 1], matrix[i - 1][j]), matrix[i - 1][j - 1]) + cost;
                }
            }
        } else {
            for (int i = 1; i < matrix.length; ++i)
            {
                for (int j = 1; j < matrix[0].length; ++j)
                {
                    if (s1.charAt(i-1) != s2.charAt(j-1))
                    {
                        if (valuesMap.get(s1.charAt(i-1)).indexOf(s2.charAt(j-1)) != -1)
                        {
                            cost = 0.5;
                        } else
                            cost = 1;
                    } else
                        cost = 0;

                    matrix[i][j] = Math.min(Math.min(matrix[i][j - 1], matrix[i - 1][j]), matrix[i - 1][j - 1]) + cost;
                }
            }
        }

        return matrix[s.length()][t.length()];
    }
}
