package com.example.PremiumFlights;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import static java.time.temporal.ChronoUnit.YEARS;

public class RegistrationController implements Initializable {

    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField loginText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label birthdayDateLabel;
    @FXML
    private Label mainLabel;
    @FXML
    private Label submainLabel;
    @FXML
    private Label regSuccessLabel;
    @FXML
    private Label regSuccess1Label;
    @FXML
    private Button registerButton;
    @FXML
    private Hyperlink siteLink;
    @FXML
    private Hyperlink helpLink;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;

    public void writeToFile(String name, String surname, String login, String password, LocalDate birthdayDate) throws IOException
    {
        String row=name+" "+surname+" "+login+" "+password+" "+birthdayDate+" offline"+"\n";
        BufferedWriter writer=new BufferedWriter(new FileWriter("./personal_data.txt",true));
        writer.append(row);
        writer.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        siteLink.setStyle("-fx-text-fill: black");
        helpLink.setStyle("-fx-text-fill: black");
        if(Objects.equals(Main.propLanguage, "English"))
        {
            mainLabel.setText("Sign up to PremiumFlights™");
            submainLabel.setText("Enter your data to create new account:");
            nameLabel.setText("Name:");
            surnameLabel.setText("Surname:");
            passwordLabel.setText("Password:");
            birthdayDateLabel.setText("Birthday date:");
            signInButton.setText("Sign in");
            signUpButton.setText("Sign up");
            registerButton.setText("Register");
            helpLink.setText("Help");
        }
    }

    @FXML
    private void onLoggingClick(ActionEvent event) throws Exception {
        Parent loggingFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("logging.fxml")));
        Scene scene = new Scene(loggingFXML);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("mainAppStyles.css")).toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onRegisterClick() throws IOException, ExecutionException, InterruptedException {

            String name=null;
            String surname=null;
            String login=null;
            String password=null;
            LocalDate birthdayDate=null;
            boolean dataError=false;

            try {
                name = nameText.getText();
                if(!name.matches("[a-zA-Z]+") ||name.contains(" "))
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                nameText.clear();
                nameText.setPromptText("BŁĘDNE DANE!");
                dataError=true;
            }

            try {
                surname = surnameText.getText();
                if(!surname.matches("[a-zA-Z]+") ||surname.contains(" "))
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                surnameText.clear();
                surnameText.setPromptText("BŁĘDNE DANE!");
                dataError=true;
            }

            try {
                login = loginText.getText();
                if(login.contains(" "))
                    throw new NumberFormatException();
                if(CRUDService.getAccountCRUD(login)!=null)
                    throw new Exception();
            } catch (NumberFormatException e) {
                loginText.clear();
                loginText.setPromptText("BŁĘDNE DANE!");
                dataError=true;
            } catch (Exception e) {
                loginText.clear();
                loginText.setPromptText("LOGIN ZAJETY!");
                dataError=true;
            }

        try {
                password = passwordText.getText();
                if(password.contains(" "))
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                passwordText.clear();
                passwordText.setPromptText("BŁĘDNE DANE!");
                dataError=true;
            }

            try {
                birthdayDate= birthdayDatePicker.getValue();
                if(birthdayDate==null)
                    throw new NumberFormatException();
                if(YEARS.between(birthdayDate,LocalDate.now())<18)
                    throw new Exception();
            } catch (NumberFormatException e) {
                birthdayDatePicker.setPromptText("BŁĘDNE DANE!");
                dataError=true;
            }catch (Exception exception)
            {
                birthdayDatePicker.getEditor().clear();
                birthdayDatePicker.setPromptText("ZBYT NISKI WIEK");
                dataError=true;
            }

            if(!dataError)
            {
                System.out.println("Zarejestrowano!");
                createUser(name,surname,login,password,birthdayDate);
                nameText.setVisible(false);
                surnameText.setVisible(false);
                loginText.setVisible(false);
                passwordText.setVisible(false);
                birthdayDatePicker.setVisible(false);
                nameLabel.setVisible(false);
                surnameLabel.setVisible(false);
                loginLabel.setVisible(false);
                passwordLabel.setVisible(false);
                birthdayDateLabel.setVisible(false);
                submainLabel.setVisible(false);
                registerButton.setDisable(true);
                regSuccessLabel.setVisible(true);
                regSuccess1Label.setVisible(true);
            }
    }

    void createUser(String name, String surname, String login, String password, LocalDate birthdayDate) throws ExecutionException, InterruptedException {
        CRUDaccounts newUser=new CRUDaccounts();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setLogin(login);
        newUser.setPassword(password);
        List<Integer> birthDate = new ArrayList<Integer>(3);
        birthDate.add(birthdayDate.getDayOfMonth());
        birthDate.add(birthdayDate.getMonthValue());
        birthDate.add(birthdayDate.getYear());
        newUser.setBirthDate(birthDate);
        newUser.setOnline(false);
        newUser.setAuthKey("");
        CRUDService.createAccountCRUD(newUser);
    }
}
