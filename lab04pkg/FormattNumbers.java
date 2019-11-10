package com.company.lab04pkg;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FormattNumbers
{
    public static List<String> formattedNumbers(List<Double> nums, int group, char separator, int nDigits, boolean padding)
    {
        ArrayList<String> toReturn = new ArrayList<>();
        int maxLength = 0;
        int maxLengthWithSeparators = 0;
        for (Double number: nums)
            maxLength = String.valueOf(number.intValue()).length() > maxLength ? String.valueOf(number.intValue()).length() : maxLength;

        maxLengthWithSeparators = maxLength + maxLength/group;
//        String patternPadd = ("#".repeat(group) + ",").repeat((maxLength/group)-1) + "#".repeat(group) + '.' + "0".repeat(nDigits),
//               patternNotPadd = ("#".repeat(group) + ",").repeat((maxLength/group)-1) + "#".repeat(group) + '.' + "##".repeat(nDigits),
//                formatted;

        for(Double number : nums)
        {
            StringBuffer rawIntPart = new StringBuffer(String.valueOf(number.intValue() < 0 ? number.intValue()*(-1) : number.intValue())); // <- nie odwrócone
            rawIntPart = rawIntPart.reverse(); // <- odwrócony

            int howManySeparators = rawIntPart.length()/group;
            for(int i = 1; i <= howManySeparators && rawIntPart.length() > (group*i + (i-1)); ++i)
                rawIntPart.insert(group*i + (i-1), separator);

            if(number < 0) rawIntPart.append('-');

            rawIntPart.append(" ".repeat(maxLengthWithSeparators - rawIntPart.length()));
            rawIntPart = rawIntPart.reverse();

            if(padding)
            {
                DecimalFormat df = new DecimalFormat("0.00");
                rawIntPart.append(df.format(number - number.intValue()).substring(1));
            }
            else
            {
                DecimalFormat df = new DecimalFormat("#.##");
                rawIntPart.append(df.format(number - number.intValue()).substring(1));
            }

            toReturn.add(rawIntPart.toString());

//            DecimalFormat df;
//            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
//            dfs.setGroupingSeparator(separator);
//            int spaces = (maxLength - Integer.toString(number.intValue()).length());
//            if(padding)
//            {
//                df = new DecimalFormat(patternPadd, dfs);
//            }
//            else
//            {
//                df = new DecimalFormat(patternNotPadd, dfs);
//            }
//            formatted = df.format(number);
//            toReturn.add(" ".repeat(spaces) + formatted);
//            System.out.println(" ".repeat(spaces) + formatted);
        }

        return toReturn;
    }
}
