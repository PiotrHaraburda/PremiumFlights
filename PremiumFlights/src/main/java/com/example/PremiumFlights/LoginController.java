package com.example.PremiumFlights;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField loginText;
    @FXML
    private TextField passwordText;
    @FXML
    private Label mainLabel;
    @FXML
    private Label submainLabel;
    @FXML
    private Label dataErrorLabel;
    @FXML
    private Label loginSuccessLabel;
    @FXML
    private Label startingLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Hyperlink passRememberLink;
    @FXML
    private Hyperlink siteLink;
    @FXML
    private Hyperlink helpLink;
    @FXML
    private Button loginButton;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;

    public static boolean authorized;
    public static Stage transparent;
    public static String name;
    public static String surname;
    public static String login;
    public static String password;

    private String randomizeAuth()
    {
        String randAuth="";
        for(int i = 0; i< 10; i++)
        {
            Random random=new Random();
            randAuth=randAuth+(char)(random.nextInt(93)+'!');
        }
        return randAuth;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passRememberLink.setStyle("-fx-text-fill: black");
        siteLink.setStyle("-fx-text-fill: black");
        helpLink.setStyle("-fx-text-fill: black");

        if(Objects.equals(Main.propLanguage, "English"))
        {
            mainLabel.setText("Sign in to PremiumFlights™");
            submainLabel.setText("Enter your data:");
            passwordLabel.setText("Password:");
            passRememberLink.setText("Forgot your password?");
            signInButton.setText("Sign in");
            signUpButton.setText("Sign up");
            loginButton.setText("Login");
            helpLink.setText("Help");
            dataErrorLabel.setText("Invalid data! Try again.");
            loginSuccessLabel.setText("Logged in successfully!");
            startingLabel.setText("Launching application...");
        }


    }
    @FXML
    private void onRegistrationClick(ActionEvent event) throws Exception
    {
        Parent registrationFXML= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registration.fxml")));
        Scene scene=new Scene(registrationFXML);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("mainAppStyles.css")).toExternalForm());
        Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onEnterPressed(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
            loginButton.fire();
    }
    @FXML
    private void onLoginClick(ActionEvent event) throws IOException
    {
        String loginFromText=loginText.getText();
        String passwordFromText=passwordText.getText();
        authorized=false;
        try
        {
            CRUDaccounts registeredUser=CRUDService.getAccountCRUD(loginFromText);
            if(registeredUser.getPassword().equals(passwordFromText))
            {
                File authFile=new File("./auth.txt");
                BufferedWriter authWriter = new BufferedWriter(new FileWriter(authFile));
                authorized=true;
                name=registeredUser.getName();
                surname=registeredUser.getSurname();
                login=registeredUser.getLogin();
                password=registeredUser.getPassword();
                registeredUser.setOnline(true);
                String randomizedAuth=randomizeAuth();
                registeredUser.setAuthKey(randomizedAuth);
                CRUDService.updateAccountCRUD(registeredUser);

                authWriter.write(login+" "+randomizedAuth);
                authWriter.close();
            }
        }
        catch(Exception e)
        {}

        if(authorized)
        {
            System.out.println("Zalogowano pomyślnie!");
            dataErrorLabel.setVisible(false);
            submainLabel.setVisible(false);
            loginText.setVisible(false);
            passwordText.setVisible(false);
            loginLabel.setVisible(false);
            passwordLabel.setVisible(false);
            passRememberLink.setVisible(false);
            startingLabel.setVisible(true);
            loginSuccessLabel.setVisible(true);
            loginButton.setVisible(false);

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e ->{
                Parent appLoadingFXML;
                try
                {
                    appLoadingFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("appLoading.fxml")));
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
                Scene scene = new Scene(appLoadingFXML);
                scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("mainAppStyles.css")).toExternalForm());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);

                transparent = new Stage();
                transparent.initStyle(StageStyle.TRANSPARENT);
                Scene scene1 = stage.getScene();
                stage.setScene(null);
                transparent.setScene(scene1);
                transparent.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));
                transparent.show();

                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                transparent.setX((primScreenBounds.getWidth() - transparent.getWidth()) / 2);
                transparent.setY((primScreenBounds.getHeight() - transparent.getHeight()) / 2);
            });
            pause.play();
        }
        else
        {
            submainLabel.setVisible(false);
            dataErrorLabel.setVisible(true);
        }
    }
}