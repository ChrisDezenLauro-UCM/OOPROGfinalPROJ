package com.vetline.app;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.List;

public class CatalogView {

    public static void show(Stage stage, String city) {

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_LEFT);

        // Top-left logo
        Image logo = new Image(CatalogView.class.getResourceAsStream("/images/VetLine_Logo_Official.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);

        Label title = new Label("Veterinary Clinic Catalog");
        title.setStyle("-fx-font-size: 22; -fx-font-weight: bold;");

        Label subtitle = new Label("Showing results for: " + city);

        Button back = new Button("← Back");
        back.setOnAction(e -> LocationView.show(stage));

        VBox listBox = new VBox(10);

        // 1) Get all hospitals for selected city
        List<Hospital> hospitals = HospitalData.filterByCity(city);

        // 2) Create one card per hospital
        for (Hospital h : hospitals) {
            Label nameLbl = new Label(h.name);
            nameLbl.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

            Label hoursLbl = new Label("Clinic Operating Hours: " + h.hours);
            Label addressLbl = new Label("Address: " + h.address);
            Label phoneLbl = new Label("Phone: " + h.phone);

            VBox card = new VBox(3, nameLbl, hoursLbl, addressLbl, phoneLbl);
            card.setStyle("-fx-padding: 15; -fx-background-color: #9bbfcf;");
            // 3) When you click a card, open full details view for that SAME hospital
            card.setOnMouseClicked(e ->
                ClinicView.show(stage, h, city)
            );

            listBox.getChildren().add(card);
        }

        root.getChildren().addAll(logoView, back, title, subtitle, listBox);
        stage.setScene(new Scene(root, 750, 600));
    }
}
