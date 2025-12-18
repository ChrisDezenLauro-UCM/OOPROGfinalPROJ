package com.vetline.app;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class ClinicView {

    public static void show(Stage stage, Hospital h, String city) {

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_LEFT);

        // ===== TOP-LEFT LOGO =====
        
        Image logo = new
            Image(ClinicView.class.getResourceAsStream("/images/VetLine_Logo_Official.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);
        // =========================

        Button back = new Button("← Back");
        back.setOnAction(e -> CatalogView.show(stage, city));

        Label name = new Label(h.name);
        name.setStyle("-fx-font-size: 22; -fx-font-weight: bold;");

        Label hours = new Label("Clinic Operating Hours: " + h.hours);
        Label address = new Label("Address: " + h.address);
        Label phone = new Label("Phone: " + h.phone);

        TextArea services = new TextArea(
            "Services:\n- " + String.join("\n- ", h.services)
        );
        services.setEditable(false);

        Button specialties = new Button("Animal Specialties");
        specialties.setOnAction(e ->
            SpecialtiesView.show(stage, h, city)
        );

        Button navigate = new Button("Navigate");
        navigate.setOnAction(e ->
            MapUtil.openMap(h.googleMaps)
        );

        HBox buttons = new HBox(10, specialties, navigate);

        root.getChildren().addAll(
            logoView, back, name, hours, address, phone, services, buttons
        );

        stage.setScene(new Scene(root, 750, 600));
    }
}
