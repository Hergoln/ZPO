package com.company.lab03pkg;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RaceController
{
    private Logger logger;
    @FXML
    public ListView<Cyclist> participants;
    @FXML
    private ListView<Cyclist> headerColumn;

    public ArrayList<Cyclist> cyclists;
    public PriorityQueue<Cyclist> header;
    private int interval;
    private Stage stage;

    public RaceController()
    { }

    @FXML
    public void initialize()
    { }

    public void setCyclists(HashSet<String> cyclistsSurnames, Stage stage, Logger logger)
    {
        this.logger = logger;
        this.stage = stage;
        cyclists = new ArrayList<>();
        header = new PriorityQueue<>();

        interval = 0;
        Random rand = new Random();
        int cyclistTime;

        for (String surname : cyclistsSurnames)
        {
            cyclistTime = (int) Math.round((rand.nextGaussian() * 40) + 290);
            cyclists.add(new Cyclist(surname, cyclistTime < 240 ? 240 : cyclistTime > 350 ? 350 : cyclistTime));
        }
    }

    public void nextFrame()
    {
        --interval;
        if(interval <= 0 && participants.getItems().size() < cyclists.size())
        {
            interval = 60;
            logger.log(Level.INFO, log(0, cyclists.get(participants.getItems().size())));
            participants.getItems().add(cyclists.get(participants.getItems().size()));
        }

        for (Cyclist cyclist: participants.getItems())
        {
            if(!cyclist.reduce())
            {
                updateHeader(cyclist);
            }
        }
    }

    private void updateHeader(Cyclist cyclist)
    {
        if(!header.contains(cyclist))
        {
            logger.log(Level.INFO, log(1, cyclist));
            header.add(cyclist);
            headerColumn.getItems().clear();

            /** for unknown reasons it doesn't seem to work properly, returns 4 at the beginning but in not the right order
                ArrayList<Cyclist> list = new ArrayList<>(header);
                for(int i = 0; i < 4 && i < header.size(); ++i)
                {
                    headerColumn.getItems().add(list.get(i));
                }
             */

            PriorityQueue<Cyclist> toDisplay = new PriorityQueue<>(header);
            for(int i = 0; i < 4; ++i)
            {
                Cyclist cyc = toDisplay.poll();
                if(cyc != null)
                    headerColumn.getItems().add(cyc);
            }
        }
    }

    @FXML
    public void close()
    {
        this.stage.close();
    }

    public String log(int info, Cyclist cyclist)
    {
        switch(info)
        {
            case 0: return cyclist + " joined the race";
            case 1: return cyclist + " ended the race";
            case 2: return "Current Header " + headerColumn.getItems();
        }
        return null;
    }
}
