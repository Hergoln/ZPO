package com.company.lab03pkg;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab03Main extends Application
{
    private Stage primaryStage;
    private HashSet<String> surnames;
    private final static Logger logger = Logger.getLogger(Lab03Main.class.getName());

    public static void main(String[] args) throws Exception
    {
        logger.setLevel(Level.INFO);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        URL oracle = new URL("http://szgrabowski.kis.p.lodz.pl/zpo19/nazwiska.txt");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
        String inputLine;
        surnames = new HashSet<>();
        Random rand = new Random();

        for(; (inputLine = in.readLine()) != null && surnames.size() <= 12;)
        {
            if(rand.nextInt()%2 == 0)
                surnames.add(inputLine);
        }
        in.close();

        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Lab03Main.class.getResource("scene.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Race");

        RaceController raceController = loader.getController();
        raceController.setCyclists(surnames, primaryStage, logger);
        System.out.println(raceController.cyclists);

        TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                            raceController.nextFrame();
                            if (!raceController.participants.getItems().get(raceController.participants.getItems().size() - 1).reduce()) {
                                logger.info("Koniec");
                                cancel();
                            }
                        }
                    );
                }
            };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000/25);

        primaryStage.show();
    }
}
