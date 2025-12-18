package com.vetline.app;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class SpecialtiesView {

    // NOTE: now also receives `city` so it can go back correctly
    public static void show(Stage stage, Hospital h, String city) {

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_LEFT);

        // ===== TOP-LEFT LOGO =====
        Image logo = new Image(SpecialtiesView.class.getResourceAsStream("/images/VetLine_Logo_Official.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);
        // =========================

        Button back = new Button("← Back");
        back.setOnAction(e ->
            // Go back to the details page of THIS SAME hospital
            ClinicView.show(stage, h, city)
        );

        TextArea list = new TextArea(
            "Animal Specialties:\n- " +
            String.join("\n- ", h.specialties)
        );
        list.setEditable(false);

        root.getChildren().addAll(logoView, back, list);
        stage.setScene(new Scene(root, 600, 450));
    }
}
