package com.vetline.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        LocationView.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
