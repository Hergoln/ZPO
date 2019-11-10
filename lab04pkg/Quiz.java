package com.company.lab04pkg;

import com.company.lab02pkg.LevQWERTY;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Quiz extends Application
{
    private HashMap<String, List<String>> questionsBase;
    public HashMap<String, Answer> answers;
    private Iterator<Map.Entry<String, List<String>>> iterator;
    private long startTime;
    private boolean endGame = false;

    @FXML
    public Label text;
    @FXML
    public TextField input;
    @FXML
    public Button submitBtn;

    public static void start(String[] args)
    {
        launch(args);
    }

    @FXML
    public void start(Stage primaryStage) throws IOException, ClassCastException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("quizView.fxml")); // <- czemu tutaj nie może być pełna ścieżka a przy wczytywaniu JSON'a musi być chociaż częściowa?
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quiz");

        primaryStage.show();
    }

    @FXML
    public void initialize() throws IOException
    {

        this.answers = new HashMap<>();
        this.questionsBase = getQuestionsFromJson("src\\com\\company\\lab04pkg\\PolEngTest.json");

        this.iterator = this.questionsBase.entrySet().iterator();
        this.text.setText(this.iterator.next().getKey());
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void submitAnswer() throws IOException, ClassCastException
    {
        if(endGame) return;
        String answer = input.getText();
        input.clear();
        addAnswer(answer, text.getText(), this.answers, this.questionsBase);

        if(iterator.hasNext())
        {
            this.text.setText(iterator.next().getKey());
        }
        else
        {
            saveQuizApproach("src\\com\\company\\lab04pkg\\Juliusz_Malka.json");
            this.text.setText("You played " + (System.currentTimeMillis() - startTime)/100.0);
            endGame = true;
        }
    }

    public class Answer
    {
        String answer;
        double point;

        Answer(String answer, double point)
        {
            this.answer = answer;
            this.point = point;
        }

        public String getAnswer()
        {
            return answer;
        }
        public double getPoint()
        {
            return point;
        }
    }

    public HashMap<String, List<String>> getQuestionsFromJson(String filePath) throws IOException, ClassCastException
    {
        Gson gson = new Gson();
        InputStream is = new FileInputStream(filePath);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while(line != null)
        {
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        buf.close();
        is.close();
        String fileAsString = sb.toString();

        return gson.fromJson(fileAsString, HashMap.class);
    }

    public void saveQuizApproach(String filePath) throws IOException, ClassCastException
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        OutputStream is = new FileOutputStream(filePath);

        BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(is));
        buf.write(gson.toJson(answers));
        buf.flush();
        buf.close();
        is.close();
    }

    public Answer addAnswer(String answer, String question, HashMap<String, Answer> answers, HashMap<String, List<String>> questionsBase)
    {
        double cost = 2, temp, point;
        Answer ans;
        for(String ansFromMap : questionsBase.get(question))
        {
            temp = LevQWERTY.compute(answer, ansFromMap);
            cost = temp < cost ? temp : cost;
        }

        if((int)cost > 1) point = 0;
        else if((int)cost == 0) point = 1;
        else point = 0.5;

        ans = new Answer(answer, point);
        answers.put(question, ans);
        return ans;
    }
}
