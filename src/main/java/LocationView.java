package com.vetline.app;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class LocationView {

    public static void show(Stage stage) {

        Label title = new Label("Welcome!");
        title.setStyle("-fx-font-size: 26; -fx-font-weight: bold;");

        Label desc = new Label(
            "Please enter your location.\nThis step is important so we can find nearby clinics."
        );

        // Logo on welcome screen
        Image image = new Image(LocationView.class.getResourceAsStream("/images/VetLine_Welcome_Logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);

        // City options MUST match Hospital.city exactly
        ComboBox<String> locations = new ComboBox<>();
        locations.getItems().addAll(
            "Cebu City", "Mandaue City", "Lapu-Lapu City"
        );
        locations.getSelectionModel().selectFirst();

        Button confirm = new Button("CONFIRM");
        confirm.setOnAction(e ->
            CatalogView.show(stage, locations.getValue())
        );

        VBox root = new VBox(15, title, imageView, desc, locations, confirm);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(root, 600, 500));
        stage.setTitle("VetLine");
        stage.show();
    }
}
