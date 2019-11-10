package com.company.lab04pkg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@DisplayName("Test Lab 4 zad 2")
public class lab04zad2test
{
    private static Quiz quiz;

    @BeforeAll
    public static void before()
    {
        quiz = new Quiz();
    }

    @Test
    public void answersNotInitialized()
    {
        Assertions.assertThrows(NullPointerException.class, () -> quiz.submitAnswer());
    }

    @Test
    public void questionsFileNotExist()
    {
        Assertions.assertThrows(FileNotFoundException.class, () -> quiz.getQuestionsFromJson("test.json"));
    }

    @Test
    public void correct()
    {
        Assertions.assertDoesNotThrow(() -> quiz.getQuestionsFromJson("src\\com\\company\\lab04pkg\\PolEngTest.json"));
    }

    @Test
    public void correctPoints()
    {
        Quiz.Answer answer;
        HashMap<String, List<String>> questionBase;
        HashMap<String, Quiz.Answer> answerHashMap;
        Iterator<Map.Entry<String, List<String>>> questionsIterator;
        try
        {

            questionBase = quiz.getQuestionsFromJson("src\\com\\company\\lab04pkg\\PolEngTest.json");
            answerHashMap = new HashMap<>();
            questionsIterator = questionBase.entrySet().iterator();

            Assertions.assertTrue(questionsIterator.hasNext());
            answer = quiz.addAnswer("Word", questionsIterator.next().getKey(), answerHashMap, questionBase);
            Assertions.assertEquals(1.0, answer.getPoint());
            Assertions.assertEquals("Word", answer.getAnswer());

            Assertions.assertTrue(questionsIterator.hasNext());
            answer = quiz.addAnswer("cREat", questionsIterator.next().getKey(), answerHashMap, questionBase);
            Assertions.assertEquals(.5, answer.getPoint());
            Assertions.assertEquals("cREat", answer.getAnswer());

            Assertions.assertTrue(questionsIterator.hasNext());
            answer = quiz.addAnswer("BreG", questionsIterator.next().getKey(), answerHashMap, questionBase);
            Assertions.assertEquals(.0, answer.getPoint());
            Assertions.assertEquals("BreG", answer.getAnswer());

        } catch (IOException exc)
        {
            System.out.println("Something gone really bad");
        }
    }
}
