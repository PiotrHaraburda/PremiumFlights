package com.example.PremiumFlights;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class Logout implements Initializable {

    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private Label infoText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Objects.equals(Main.propLanguage, "English"))
        {
            infoText.setText("Are you sure you want to logout?");
            yesButton.setText("Yes");
            noButton.setText("No");
        }
    }

    @FXML
    protected void onYesClick() throws IOException, ExecutionException, InterruptedException {

        File authFile=new File("./auth.txt");
        BufferedWriter authWriter = new BufferedWriter(new FileWriter(authFile));
        authWriter.write("");
        authWriter.close();

        CRUDaccounts loggedUser=CRUDService.getAccountCRUD(LoginController.login);
        loggedUser.setOnline(false);
        loggedUser.setAuthKey("");
        CRUDService.updateAccountCRUD(loggedUser);

        Parent loggingFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("logging.fxml")));
        Scene scene = new Scene(loggingFXML);
        Stage loggingStage = new Stage();
        loggingStage.setScene(scene);
        if(Objects.equals(Main.propLanguage, "Polski"))
            loggingStage.setTitle("Autoryzacja w serwisie PremiumFlights™");
        else if(Objects.equals(Main.propLanguage, "English"))
            loggingStage.setTitle("PremiumFlights™ service authorization");
        loggingStage.setScene(scene);
        loggingStage.setResizable(false);
        loggingStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));
        loggingStage.show();
        AppLoading.mainAppStage.close();
        MainApp.logoutStage.close();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        loggingStage.setX((primScreenBounds.getWidth() - loggingStage.getWidth()) / 2);
        loggingStage.setY((primScreenBounds.getHeight() - loggingStage.getHeight()) / 2);
    }

    @FXML
    protected void onNoClick()
    {
        MainApp.logoutStage.close();
    }
}
