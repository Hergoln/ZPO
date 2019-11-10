package com.company.lab02pkg;

public class Meter
{
    private String meterRep;

    /**
     * Constructs Meter object with length and precision
     * @param length Length of meter
     * @param precision Number of recursive iterations
     */
    public Meter(Integer length, Integer precision)
    {
        StringBuilder stringBuilder = new StringBuilder();
        String firstLine = "";
        for(int i = 0; i < precision*2; ++i) firstLine += "-";
        firstLine += " 0";
        stringBuilder.append(firstLine);

        for(int i = 0; i < length; ++i)
        {
            stringBuilder.append(createMeterRep(precision, precision));

            String lastLine = "\n";
            for(int j = 0; j < precision*2; ++j) lastLine += "-";
            lastLine += " " + (i+1);
            stringBuilder.append(lastLine);
        }

        meterRep = new String(stringBuilder);
    }

    private String createMeterRep(Integer precision, Integer lineLength)
    {
        if(precision < 2) return "";
        String temp = "";
        for(int i = 0; i < lineLength - precision + 1; ++i) temp += " ";
        for(int i = 0; i < (precision-1)*2; ++i) temp += "-";
        for(int i = 0; i < lineLength - precision + 1; ++i) temp += " ";

        return createMeterRep(precision - 1, lineLength)
                + "\n" + temp
                + createMeterRep(precision - 1, lineLength);
    }

    @Override
    public String toString()
    {
        return meterRep;
    }
}
