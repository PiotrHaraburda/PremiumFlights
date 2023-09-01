package com.example.PremiumFlights;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppLoading implements Initializable {

    @FXML
    private ImageView planeImageView;

    @FXML
    private ProgressBar progressBar;

    public static Stage mainAppStage;
    public static Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        progressBar.setStyle("-fx-accent: #87732f");
        Timeline progress = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(progressBar.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(progressBar.progressProperty(), 1)
                )
        );
        progress.setOnFinished(e->delay1());
        progress.playFromStart();
    }
    public void delay1()
    {
        Timeline delay = new Timeline(
                new KeyFrame(
                        Duration.ZERO
                ),
                new KeyFrame(
                        Duration.seconds(0.6)
                )
        );
        delay.setOnFinished(e->delay2());
        delay.playFromStart();
    }
    public void delay2()
    {
        progressBar.setVisible(false);
        Timeline delay = new Timeline(
                new KeyFrame(
                        Duration.ZERO
                ),
                new KeyFrame(
                        Duration.seconds(0.4)
                )
        );
        delay.setOnFinished(e->departure());
        delay.playFromStart();
    }
    public void departure()
    {
        Path path = new Path();
        MoveTo from = new MoveTo();
        from.setX(75);
        from.setY(75);
        LineTo to = new LineTo();
        to.setX(550);
        to.setY(200);
        path.getElements().addAll(from, to);
        PathTransition planeDeparture = new PathTransition(Duration.millis(1500), path, planeImageView);
        Platform.runLater(() ->
        {
            planeDeparture.setRate(1);
            planeDeparture.play();
        });
        planeDeparture.setOnFinished(e-> {
            try {
                appStart();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(1500));
        scaleTransition.setNode(planeImageView);
        scaleTransition.setByY(1.3);
        scaleTransition.setByX(1.3);
        scaleTransition.setAutoReverse(false);

        Platform.runLater(() -> {
            scaleTransition.setRate(1);
            scaleTransition.play();
        });
    }
    public void appStart() throws IOException
    {
        LoginController.transparent.hide();
        Parent mainAppFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainApp.fxml")));
        scene = new Scene(mainAppFXML);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("mainAppStyles.css")).toExternalForm());
        mainAppStage = new Stage();
        mainAppStage.setTitle("PremiumFlights™");
        mainAppStage.setScene(scene);
        mainAppStage.setMinHeight(640);
        mainAppStage.setMinWidth(1016);
        mainAppStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));
        mainAppStage.show();


        if(Objects.equals(Main.propFullscreen, "true"))
            mainAppStage.setFullScreen(true);
        else
        {
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            mainAppStage.setX((primScreenBounds.getWidth() - mainAppStage.getWidth()) / 2);
            mainAppStage.setY((primScreenBounds.getHeight() - mainAppStage.getHeight()) / 2);
        }
    }
}
