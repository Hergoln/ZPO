package com.company.lab05pkg;

import java.awt.List;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JanStudy
{
    private ArrayList<String> wordsToLearn;
    private HashSet<String> learntWords;
    private Queue<HashSet<String>> wordsLearntDaysBefore;
    private Integer wordsLearntPerDay;
    private Integer wordsForgottenPerDay;
    private Integer daysToAlzheimer;
    private Double probability;


    public JanStudy(String filePath, Integer wordsLearntPerDay, Integer wordsForgottenPerDay, Integer daysToAlzheimer, Double probability, Set<String> learntWords) throws IOException
    {
        this.wordsLearntPerDay = wordsLearntPerDay;
        this.wordsForgottenPerDay = wordsForgottenPerDay;
        this.daysToAlzheimer = daysToAlzheimer;
        this.probability = probability;

        HashSet<String> wordsToLearnHashSet = new HashSet<>();
        this.learntWords = learntWords != null ? new HashSet<>(learntWords) : new HashSet<>();
        this.wordsLearntDaysBefore = new LinkedList<>();

        File f = new File(filePath);
        FileReader fileReader = new FileReader(f);
        Scanner scanner = new Scanner(fileReader);

        while(scanner.hasNextLine())
        {
            wordsToLearnHashSet.add(scanner.nextLine());
        }

        this.wordsToLearn = new ArrayList<>(wordsToLearnHashSet);
    }

    public void simulate(Integer daysOfStudy)
    {
        ArrayList<String>  forgottenWords = new ArrayList<>();
        HashSet<String>    justLearnt     = new HashSet<>(wordsLearntPerDay);
        Random             rand           = new Random();
        ArrayList<String>  helper;

        HashSet<String> overallKnown = new HashSet<>();
        String temp;
        for(int i = 0, alzheimerCounter = 0; i < daysOfStudy; ++i, alzheimerCounter = 0)
        {
            justLearnt.clear();
            forgottenWords.clear();
            temp = "";
            for(int wordsCounter = 0; wordsCounter < wordsLearntPerDay;)
            {
                temp = wordsToLearn.get(rand.nextInt(wordsToLearn.size()));
                if(!overallKnown.contains(temp))
                {
                    overallKnown.add(temp);
                    justLearnt.add(temp);
                    ++wordsCounter;
                }
            }

            wordsLearntDaysBefore.add(new HashSet<>(justLearnt));
            if(daysToAlzheimer <= 0)
                this.learntWords.addAll(wordsLearntDaysBefore.poll());
            else
                --daysToAlzheimer;

            for(int j =0; j < wordsForgottenPerDay; ++j) alzheimerCounter += rand.nextDouble() <= this.probability ? 1 : 0;

            helper = learntWords.isEmpty() ? new ArrayList<>() : new ArrayList<>(learntWords);

            while(alzheimerCounter-- > 0 && !helper.isEmpty())
            {
                temp = helper.remove(rand.nextInt(helper.size()));
                overallKnown.remove(temp);
                learntWords.remove(temp);
                forgottenWords.add(temp);
            }

            System.out.println("Day " + (i+1));
            System.out.println("New words: " + justLearnt);
            System.out.println("Forgotten words: " + (forgottenWords.isEmpty() ? "---" : forgottenWords.toString()));
            System.out.println(overallKnown);
        }
    }
}
