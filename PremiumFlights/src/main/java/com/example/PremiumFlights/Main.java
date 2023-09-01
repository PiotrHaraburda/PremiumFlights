package com.example.PremiumFlights;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class Main extends Application {

    public static String propLanguage;

    public static String propFullscreen;
    public static String propNotAvailable;

    @Override
    public void start(Stage mainStage) throws IOException {
        propertiesScan();
        if(!authCheck())
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logging.fxml")); //xdd
            Scene scene = new Scene(fxmlLoader.load(), 550, 298); //xdd
            //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainApp.fxml")); //xdd
            //Scene scene = new Scene(fxmlLoader.load()); //xdd
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("mainAppStyles.css")).toExternalForm()); //xdd
            if(Objects.equals(propLanguage, "Polski"))
                mainStage.setTitle("Autoryzacja w serwisie PremiumFlights™");
            else if(Objects.equals(propLanguage, "English"))
                mainStage.setTitle("PremiumFlights™ service authorization");
            mainStage.setScene(scene);
            //mainStage.setMinHeight(640);//xdd
            //mainStage.setMinWidth(1016);//xdd
            mainStage.setResizable(false); //xdd
            mainStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));
            mainStage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            mainStage.setX((primScreenBounds.getWidth() - mainStage.getWidth()) / 2);
            mainStage.setY((primScreenBounds.getHeight() - mainStage.getHeight()) / 2);
        }
        else
        {

            Parent appLoadingFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("appLoading.fxml")));
            Scene scene = new Scene(appLoadingFXML);
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("mainAppStyles.css")).toExternalForm());
            Stage stage = new Stage();
            stage.hide();
            stage.setScene(scene);

            LoginController.transparent = new Stage();
            LoginController.transparent.initStyle(StageStyle.TRANSPARENT);
            Scene scene1 = stage.getScene();
            stage.setScene(null);
            LoginController.transparent.setScene(scene1);
            LoginController.transparent.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));
            LoginController.transparent.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            LoginController.transparent.setX((primScreenBounds.getWidth() - LoginController.transparent.getWidth()) / 2);
            LoginController.transparent.setY((primScreenBounds.getHeight() - LoginController.transparent.getHeight()) / 2);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ClassLoader classLoader = FirebaseActions.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());

        try {
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
            SpringApplication.run(FirebaseActions.class, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        launch();
    }

    public boolean authCheck() throws IOException {
        File authFile=new File("./auth.txt");
        BufferedReader authReader = new BufferedReader(new FileReader(authFile));
        String auth;
        String[] authData=new String[2];

        if((auth=authReader.readLine())!=null)
        {
            authData = auth.split(" ");
        }
        authReader.close();

        if(authData[0]!=null)
        {
            try
            {
                CRUDaccounts loggedUser=CRUDService.getAccountCRUD(authData[0]);
                if(loggedUser.isOnline())
                    if(loggedUser.getAuthKey().equals(authData[1])&&loggedUser.getLogin().equals(authData[0]))
                    {
                        LoginController.authorized=true;
                        LoginController.name=loggedUser.getName();
                        LoginController.surname=loggedUser.getSurname();
                        LoginController.login=loggedUser.getLogin();
                        LoginController.password=loggedUser.getPassword();
                        return true;
                    }
            }
            catch(Exception e){
                e.printStackTrace();}
        }

        return false;
    }

    public static void propertiesScan() throws IOException {
        File file=new File("./properties.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String property;

        while((property=reader.readLine())!=null)
        {
            String[] data=property.split(" ");
            if(Objects.equals(data[0], "language"))
            {
                propLanguage=data[1];
            }
            else if(Objects.equals(data[0], "fullscreen"))
            {
                propFullscreen=data[1];
            }
            else if(Objects.equals(data[0], "notavailable"))
            {
                propNotAvailable=data[1];
            }
        }
        reader.close();
    }
}