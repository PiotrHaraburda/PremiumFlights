package com.example.PremiumFlights;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainApp implements Initializable {

    @FXML
    private Button accountButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label accountLabel;
    @FXML
    private Pane accountPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ImageView planeImageView;
    @FXML
    private ImageView accountImageView;
    @FXML
    private ImageView settingsImageView;
    @FXML
    private ImageView creditsImageView;
    @FXML
    private ImageView logoutImageView;
    @FXML
    private ImageView avatarImageView;
    @FXML
    private Button closeAccountButton;
    @FXML
    private Label clockLabel;
    @FXML
    private DatePicker dayDatePicker;
    @FXML
    private ComboBox<String> departureComboBox;
    @FXML
    private ComboBox<String> arrivalComboBox;
    @FXML
    private AnchorPane scrollAnchorPane;
    @FXML
    private AnchorPane filterAnchorPane;
    @FXML
    private ImageView coveredImageView;
    @FXML
    private ImageView uncoveredImageView;
    @FXML
    private Button uncoveredButton;
    @FXML
    private Button coveredButton;
    @FXML
    private Label uncoveredPasswordLabel;
    @FXML
    private Label name2Label;
    @FXML
    private Label surname2Label;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Pane newPasswordPane;
    @FXML
    private TextField newPasswordTextField;
    @FXML
    private Pane accountDeletePane;
    @FXML
    private TextField deleteAccountTextField;
    @FXML
    private Pane creditsPane;
    @FXML
    private Label creditsLabel;
    @FXML
    private Pane settingsPane;
    @FXML
    private Label settingsLabel;
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private Label settingsTitleLabel;
    @FXML
    private Label languageLabel;
    @FXML
    private Button closeSettingsButton;
    @FXML
    private Label logoutLabel;
    @FXML
    private Label departureLabel;
    @FXML
    private Label arrivalLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label luxuryLabel;
    @FXML
    private Label creditsTitleLabel;
    @FXML
    private Button closeCreditsButton;
    @FXML
    private Button passwordChangeButton;
    @FXML
    private Button accountDeleteButton;
    @FXML
    private Label accountTitleLabel;
    @FXML
    private Label personalDataLabel;
    @FXML
    private Label accNameLabel;
    @FXML
    private Label accSurnameLabel;
    @FXML
    private Label accLoginLabel;
    @FXML
    private Label accPasswordLabel;
    @FXML
    private Label fullscreenLabel;
    @FXML
    private CheckBox fullscreenCheckbox;
    @FXML
    private AnchorPane sideTabAnchorPane;
    @FXML
    private AnchorPane contentAnchorPane;
    @FXML
    private Separator sideSeparator1;
    @FXML
    private Separator sideSeparator2;
    @FXML
    private Separator sideSeparator3;
    @FXML
    private Separator sideSeparator4;
    @FXML
    private Separator sideSeparator5;
    @FXML
    private Separator sideSeparator6;
    @FXML
    private Pane ticketPane;
    @FXML
    private Label ticketIdInfoLabel;
    @FXML
    private Label ticketTypeInfoLabel;
    @FXML
    private Label flightIdInfoLabel;
    @FXML
    private Label departureInfoLabel;
    @FXML
    private Label arrivalInfoLabel;
    @FXML
    private Label dateInfoLabel;
    @FXML
    private Label timeInfoLabel;
    @FXML
    private Label hourInfoLabel;
    @FXML
    private Label minuteInfoLabel;
    @FXML
    private Label transferInfoLabel;
    @FXML
    private Label planeInfoLabel;
    @FXML
    private Label priceInfoLabel;
    @FXML
    private Label currencyInfoLabel;
    @FXML
    private Label amountInfoLabel;
    @FXML
    private Label quantityInfoLabel;
    @FXML
    private CheckBox agreementCheckBox;
    @FXML
    private CheckBox showNotAvailableCheckBox;
    @FXML
    private Button buyTicketsButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Label premiumFlightsLabel;

    public static Stage logoutStage;
    public LocalDate maxDate;
    public LocalDate minDate;
    private int languageIndex;

    public List<String> airportsList= Arrays.asList("Wszystkie","Warszawa-Okęcie","Warszawa-Modlin","Kraków-Balice",
            "Katowice-Pyrzowice","Wrocław-Strachowice","Olsztyn-Szymany","Poznań-Ławica","Rzeszów-Jasionka");
    public List<String> languageList= Arrays.asList("Polski","English");
    static int nr=50;
    AnchorPane[] ticketAnchorPanes =new AnchorPane[nr];
    Label[] dateLabels=new Label[nr];
    Label[] timeLabels=new Label[nr];
    Separator[] separators1=new Separator[nr];
    ImageView[] departureImages=new ImageView[nr];
    Label[] departureLabels=new Label[nr];
    ImageView[] arrivalImages=new ImageView[nr];
    Label[] arrivalLabels=new Label[nr];
    Separator[] separators2=new Separator[nr];
    Label[] availabilityLabels=new Label[nr];
    Label[] availability2Labels=new Label[nr];
    Circle[] circles=new Circle[nr];
    Separator[] separators3=new Separator[nr];
    Label[] priceLabels=new Label[nr];
    Label[] currencyLabels=new Label[nr];
    Label[] unitLabels=new Label[nr];
    Button[] ticketButtons=new Button[nr];
    String[] ticketIds=new String[nr];
    String[] dates=new String[nr];
    String[] times=new String[nr];
    String[] departures=new String[nr];
    String[] arrivals=new String[nr];
    String[] prices=new String[nr];
    String[] quantities=new String[nr];
    String[] planes=new String[nr];
    String[] hours=new String[nr];
    String[] minutes=new String[nr];
    String[] transfers=new String[nr];
    String[] ticketTypes=new String[nr];
    String[] flightIds=new String[nr];

    int nrOfTickets=0;
    int ticketPrice=0;

    ScaleTransition makeScaleTransition(Node node, double from, double to, double duration)
    {
        ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(duration),node);
        scaleTransition.setFromX(from);
        scaleTransition.setFromY(from);
        scaleTransition.setToX(to);
        scaleTransition.setToY(to);
        scaleTransition.setInterpolator(Interpolator.LINEAR);

        return scaleTransition;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        propertiesCheck();
        setAvatar();

        dayDatePicker.setPromptText("Wszystkie");
        dayDatePicker.setStyle("-fx-focus-color: transparent;");
        if(LoginController.name!=null&& LoginController.surname!=null)
        {
            surnameLabel.setText(LoginController.surname);
            nameLabel.setText(LoginController.name);
        }

        name2Label.setText(LoginController.name);
        surname2Label.setText(LoginController.surname);
        loginLabel.setText(LoginController.login);
        uncoveredPasswordLabel.setText(LoginController.password);
        passwordLabel.setText("*****");

        departureComboBox.setVisibleRowCount(5);

        arrivalComboBox.setVisibleRowCount(5);

        languageComboBox.getItems().addAll(languageList);
        languageComboBox.setVisibleRowCount(5);

        departureComboBox.getSelectionModel().selectFirst();
        arrivalComboBox.getSelectionModel().selectFirst();

        for(int i=0;i<languageList.size();i++)
            if(Objects.equals(Main.propLanguage, languageList.get(i)))
                languageIndex=i;

        languageComboBox.getSelectionModel().select(languageIndex);

        clockUpdate();
        dateLimit();
        try {
            ticketsCheck();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        scrollPane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            scrollAnchorPane.setPrefWidth((double)newSceneWidth-15);
            accountPane.setLayoutX((double)newSceneWidth/2-200);
            settingsPane.setLayoutX((double)newSceneWidth/2-205);
            creditsPane.setLayoutX((double)newSceneWidth/2-150);
        });

        contentAnchorPane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            accountPane.setLayoutY((double)newSceneHeight/2-208+22);
            settingsPane.setLayoutY((double)newSceneHeight/2-208+22);
            creditsPane.setLayoutY((double)newSceneHeight/2-100+22);
        });

        final double[] planeIncrease = {0};

        sideTabAnchorPane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            planeImageView.setLayoutX(((double)newSceneWidth/2.0)-70- planeIncrease[0]);
            accountImageView.setLayoutX(((double)newSceneWidth/2.0+40));
            settingsImageView.setLayoutX(((double)newSceneWidth/2.0+40));
            creditsImageView.setLayoutX(((double)newSceneWidth/2.0+40));
            logoutImageView.setLayoutX(((double)newSceneWidth/2.0+40));
            avatarImageView.setLayoutX(((double)newSceneWidth/2.0)-75);
            surnameLabel.setLayoutX(((double)newSceneWidth/2.0-20));
            nameLabel.setLayoutX(((double)newSceneWidth/2.0-20));
        });

        sideTabAnchorPane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            sideSeparator1.setLayoutY(31+((double)newSceneHeight*0.1));
            sideSeparator2.setLayoutY(35+((double)newSceneHeight*0.1));
            sideSeparator3.setLayoutY(31+((double)newSceneHeight*0.2));
            sideSeparator4.setLayoutY(26+((double)newSceneHeight*0.3));
            sideSeparator5.setLayoutY(21+((double)newSceneHeight*0.4));
            sideSeparator6.setLayoutY(16+((double)newSceneHeight*0.5));

            double fontIncrease=((double)newSceneHeight-600)/60.0;
            planeIncrease[0] =((double)newSceneHeight-600)/55.0;

            avatarImageView.setLayoutY(sideSeparator1.getLayoutY()/2.0-25);
            surnameLabel.setLayoutY(sideSeparator1.getLayoutY()/2.0-20-fontIncrease);
            surnameLabel.setFont(Font.font("System",FontWeight.BOLD,17+fontIncrease));
            nameLabel.setLayoutY(sideSeparator1.getLayoutY()/2.0-fontIncrease);
            nameLabel.setFont(Font.font("System",17+fontIncrease));

            accountLabel.setLayoutY((sideSeparator3.getLayoutY()+sideSeparator2.getLayoutY())/2.0-8-fontIncrease);
            accountImageView.setLayoutY((sideSeparator3.getLayoutY()+sideSeparator2.getLayoutY())/2.0-11);
            accountButton.setLayoutY(sideSeparator2.getLayoutY());
            accountButton.setPrefHeight(sideSeparator3.getLayoutY()-sideSeparator2.getLayoutY());
            accountLabel.setFont(Font.font("System",FontWeight.BOLD,14+fontIncrease));

            settingsLabel.setLayoutY((sideSeparator4.getLayoutY()+sideSeparator3.getLayoutY())/2.0-8-fontIncrease);
            settingsImageView.setLayoutY((sideSeparator4.getLayoutY()+sideSeparator3.getLayoutY())/2.0-11);
            settingsButton.setLayoutY(sideSeparator3.getLayoutY());
            settingsButton.setPrefHeight(sideSeparator4.getLayoutY()-sideSeparator3.getLayoutY());
            settingsLabel.setFont(Font.font("System",FontWeight.BOLD,14+fontIncrease));

            creditsLabel.setLayoutY((sideSeparator5.getLayoutY()+sideSeparator4.getLayoutY())/2.0-8-fontIncrease);
            creditsImageView.setLayoutY((sideSeparator5.getLayoutY()+sideSeparator4.getLayoutY())/2.0-11);
            creditsButton.setLayoutY(sideSeparator4.getLayoutY());
            creditsButton.setPrefHeight(sideSeparator5.getLayoutY()-sideSeparator4.getLayoutY());
            creditsLabel.setFont(Font.font("System",FontWeight.BOLD,14+fontIncrease));

            logoutLabel.setLayoutY((sideSeparator6.getLayoutY()+sideSeparator5.getLayoutY())/2.0-8-fontIncrease);
            logoutImageView.setLayoutY((sideSeparator6.getLayoutY()+sideSeparator5.getLayoutY())/2.0-11);
            logoutButton.setLayoutY(sideSeparator5.getLayoutY());
            logoutButton.setPrefHeight(sideSeparator6.getLayoutY()-sideSeparator5.getLayoutY());
            logoutLabel.setFont(Font.font("System",FontWeight.BOLD,14+fontIncrease));

            planeImageView.setLayoutY(((double)newSceneHeight+sideSeparator6.getLayoutY())/2-120);
            planeImageView.setFitWidth(175+ planeIncrease[0]);
            planeImageView.setFitHeight(148+ planeIncrease[0]);

            premiumFlightsLabel.setLayoutY(planeImageView.getLayoutY()+150);

            luxuryLabel.setLayoutY(planeImageView.getLayoutY()+175);

            refreshButton.setLayoutY((sideTabAnchorPane.getHeight()+luxuryLabel.getLayoutY())/2);
            refreshButton.setFont(Font.font("System",FontWeight.BOLD,12+fontIncrease));
        });

        filterAnchorPane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) ->
                arrivalComboBox.setLayoutX(((double)newSceneWidth/2.0)));
    }

    void setAvatar()
    {
        try
        {
            Image image=new Image(Objects.requireNonNull(getClass().getResource("avatar_" + LoginController.login + ".png")).toExternalForm());
            avatarImageView.setImage(image);
        }
        catch(NullPointerException ignored) {}
    }

    void ticketsCheck() throws ExecutionException, InterruptedException {
        scrollAnchorPane.getChildren().clear();

        int j=0;
        nrOfTickets=0;

        List<QueryDocumentSnapshot> ticketDocuments=CRUDService.getTicketDocumentsCRUD();



        for (QueryDocumentSnapshot document : ticketDocuments) {
            try {
                CRUDtickets crud=CRUDService.getTicketCRUD(document.getId());
                ticketIds[j]=crud.getId();
                dates[j]= crud.getDate().get(0) +"."+crud.getDate().get(1) +"."+crud.getDate().get(2);
                times[j]=crud.getTime().get(0)+":"+crud.getTime().get(1);
                departures[j]=crud.getDeparture();
                arrivals[j]=crud.getArrival();
                prices[j]=Integer.toString(crud.getPrice());
                quantities[j]=Integer.toString(crud.getQuantity());
                planes[j]=crud.getPlane();
                hours[j]=Integer.toString(crud.getHours());
                minutes[j]=Integer.toString(crud.getMinutes());
                transfers[j]=crud.getTransfers();
                ticketTypes[j]=crud.getType();
                flightIds[j]=crud.getFlightID();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            ticketAnchorPanes[j]=new AnchorPane();
            ticketAnchorPanes[j].setPrefWidth(763);
            ticketAnchorPanes[j].setPrefHeight(87);
            ticketAnchorPanes[j].setLayoutX(14);
            ticketAnchorPanes[j].setLayoutY(14+104*nrOfTickets);
            ticketAnchorPanes[j].setStyle("-fx-background-color: #EEEEEE; -fx-background-radius: 20; -fx-border-radius: 20;" +
                    "-fx-border-width: 5; -fx-effect:  dropshadow( gaussian , rgba(0,0,0,0.7) , 15,0,0,0 )");
            AnchorPane.setRightAnchor(ticketAnchorPanes[j],12.0);
            AnchorPane.setLeftAnchor(ticketAnchorPanes[j],14.0);
            scrollAnchorPane.getChildren().add(ticketAnchorPanes[j]);

            dateLabels[j]=new Label();
            dateLabels[j].setText(dates[j]);
            dateLabels[j].setLayoutX(17);
            dateLabels[j].setLayoutY(24);
            dateLabels[j].setStyle("-fx-text-fill: #A6B1E1; -fx-font-size: 15; -fx-font-weight: bold");
            ticketAnchorPanes[j].getChildren().add(dateLabels[j]);

            timeLabels[j]=new Label();
            timeLabels[j].setText(times[j]);
            timeLabels[j].setLayoutX(33);
            timeLabels[j].setLayoutY(45);
            timeLabels[j].setStyle("-fx-text-fill: black; -fx-font-size: 15; -fx-font-weight: bold");
            ticketAnchorPanes[j].getChildren().add(timeLabels[j]);

            separators1[j]=new Separator();
            separators1[j].setOrientation(Orientation.VERTICAL);
            separators1[j].setPrefWidth(2);
            separators1[j].setPrefHeight(87);
            separators1[j].setLayoutX(108);
            separators1[j].setLayoutY(0);
            separators1[j].setStyle("-fx-border-style: solid; -fx-border-width: 0");
            ticketAnchorPanes[j].getChildren().add(separators1[j]);

            departureImages[j]=new ImageView(Objects.requireNonNull(getClass().getResource("flight_takeoff_FILL0_wght400_GRAD0_opsz48.png")).toExternalForm());
            departureImages[j].setFitWidth(41);
            departureImages[j].setFitHeight(44);
            departureImages[j].setLayoutX(146);
            departureImages[j].setLayoutY(23);
            ticketAnchorPanes[j].getChildren().add(departureImages[j]);

            departureLabels[j]=new Label();
            departureLabels[j].setText(departures[j]);
            departureLabels[j].setLayoutX(204);
            departureLabels[j].setLayoutY(35);
            departureLabels[j].setStyle("-fx-text-fill: black; -fx-font-size: 14; -fx-font-weight: bold");
            ticketAnchorPanes[j].getChildren().add(departureLabels[j]);

            arrivalImages[j]=new ImageView(Objects.requireNonNull(getClass().getResource("flight_land_FILL0_wght400_GRAD0_opsz48.png")).toExternalForm());
            arrivalImages[j].setFitWidth(41);
            arrivalImages[j].setFitHeight(44);
            arrivalImages[j].setLayoutX(353);
            arrivalImages[j].setLayoutY(23);
            AnchorPane.setRightAnchor(arrivalImages[j],375.0);
            ticketAnchorPanes[j].getChildren().add(arrivalImages[j]);

            arrivalLabels[j]=new Label();
            arrivalLabels[j].setText(arrivals[j]);
            arrivalLabels[j].setPrefWidth(150);
            arrivalLabels[j].setLayoutX(409);
            arrivalLabels[j].setLayoutY(35);
            arrivalLabels[j].setStyle("-fx-text-fill: black; -fx-font-size: 14; -fx-font-weight: bold");
            AnchorPane.setRightAnchor(arrivalLabels[j],210.0);
            ticketAnchorPanes[j].getChildren().add(arrivalLabels[j]);

            separators2[j]=new Separator();
            separators2[j].setOrientation(Orientation.VERTICAL);
            separators2[j].setPrefWidth(2);
            separators2[j].setPrefHeight(87);
            separators2[j].setLayoutX(566);
            separators2[j].setLayoutY(0);
            separators2[j].setStyle("-fx-border-style: solid; -fx-border-width: 0");
            AnchorPane.setRightAnchor(separators2[j],197.0);
            ticketAnchorPanes[j].getChildren().add(separators2[j]);

            availabilityLabels[j]=new Label();
            if(Integer.parseInt(quantities[j])>0)
                availabilityLabels[j].setText("Dostępne!");
            else
                availabilityLabels[j].setText("Niedostępne.");

            availabilityLabels[j].setPrefWidth(90);
            availabilityLabels[j].setLayoutX(582);
            availabilityLabels[j].setLayoutY(25);
            availabilityLabels[j].setStyle("-fx-text-fill: black; -fx-font-size: 12; -fx-font-weight: bold");
            AnchorPane.setRightAnchor(availabilityLabels[j],98.0);
            ticketAnchorPanes[j].getChildren().add(availabilityLabels[j]);

            availability2Labels[j]=new Label();
            if(Integer.parseInt(quantities[j])>20)
                availability2Labels[j].setText("duża ilość");
            else if(Integer.parseInt(quantities[j])<20&&Integer.parseInt(quantities[j])>0)
                availability2Labels[j].setText("mała ilość");
            else
                availability2Labels[j].setText("brak");

            availability2Labels[j].setPrefWidth(70);
            availability2Labels[j].setLayoutX(605);
            availability2Labels[j].setLayoutY(44);
            availability2Labels[j].setStyle("-fx-text-fill: black; -fx-font-size: 12; -fx-font-style: italic");
            AnchorPane.setRightAnchor(availability2Labels[j],100.0);
            ticketAnchorPanes[j].getChildren().add(availability2Labels[j]);

            circles[j]=new Circle();
            circles[j].setRadius(6);
            circles[j].setLayoutX(588);
            circles[j].setLayoutY(52);
            if(Objects.equals(availability2Labels[j].getText(), "duża ilość"))
                circles[j].setFill(Color.GREEN);
            else if(Objects.equals(availability2Labels[j].getText(), "mała ilość"))
                circles[j].setFill(Color.ORANGE);
            else if(Objects.equals(availability2Labels[j].getText(), "brak"))
                circles[j].setFill(Color.RED);
            circles[j].setStyle("-fx-stroke: black; -fx-stroke-width: 1; -fx-stroke-type: inside; -fx-stroke-line-cap: square;" +
                    " -fx-stroke-line-join: miter; -fx-stroke-miter-limit: 10");
            AnchorPane.setRightAnchor(circles[j],175.0);
            ticketAnchorPanes[j].getChildren().add(circles[j]);

            separators3[j]=new Separator();
            separators3[j].setOrientation(Orientation.VERTICAL);
            separators3[j].setPrefWidth(2);
            separators3[j].setPrefHeight(87);
            separators3[j].setLayoutX(675);
            separators3[j].setLayoutY(0);
            separators3[j].setStyle("-fx-border-style: solid; -fx-border-width: 0");
            AnchorPane.setRightAnchor(separators3[j],88.0);
            ticketAnchorPanes[j].getChildren().add(separators3[j]);

            priceLabels[j]=new Label();
            priceLabels[j].setText(prices[j]);
            priceLabels[j].setPrefWidth(40);
            priceLabels[j].setLayoutX(696);
            priceLabels[j].setLayoutY(24);
            priceLabels[j].setStyle("-fx-text-fill: black; -fx-font-size: 18; -fx-font-weight: bold");
            AnchorPane.setRightAnchor(priceLabels[j],34.0);
            ticketAnchorPanes[j].getChildren().add(priceLabels[j]);

            currencyLabels[j]=new Label();
            currencyLabels[j].setText("zł");
            currencyLabels[j].setLayoutX(733);
            currencyLabels[j].setLayoutY(24);
            currencyLabels[j].setStyle("-fx-text-fill: black; -fx-font-size: 18");
            AnchorPane.setRightAnchor(currencyLabels[j],20.0);
            ticketAnchorPanes[j].getChildren().add(currencyLabels[j]);

            unitLabels[j]=new Label();
            unitLabels[j].setText("1 os.");
            unitLabels[j].setLayoutX(708);
            unitLabels[j].setLayoutY(48);
            unitLabels[j].setStyle("-fx-text-fill: black; -fx-font-size: 12; -fx-font-style: italic");
            AnchorPane.setRightAnchor(unitLabels[j],37.0);
            ticketAnchorPanes[j].getChildren().add(unitLabels[j]);

            ticketButtons[j]=new Button();
            ticketButtons[j].setPrefWidth(735);
            ticketButtons[j].setPrefHeight(84);
            ticketButtons[j].setLayoutX(14);
            ticketButtons[j].setLayoutY(0);
            ticketButtons[j].setOpacity(0);
            ticketButtons[j].setCursor(Cursor.HAND);
            int finalJ = j;
            ticketButtons[j].setOnAction(e->{
                ticketIdInfoLabel.setText(ticketIds[finalJ]);

                if(Objects.equals(ticketTypes[finalJ], "1klasa"))
                    ticketTypeInfoLabel.setText("Miejsce siedzące w 1 klasie");
                else
                    ticketTypeInfoLabel.setText("Standardowe miejsce siedzące");

                flightIdInfoLabel.setText(flightIds[finalJ]);
                departureInfoLabel.setText(departures[finalJ]);
                arrivalInfoLabel.setText(arrivals[finalJ]);
                dateInfoLabel.setText(dates[finalJ]);
                timeInfoLabel.setText(times[finalJ]);
                hourInfoLabel.setText(hours[finalJ]);
                minuteInfoLabel.setText(minutes[finalJ]);
                if(Objects.equals(transfers[finalJ], "przesiadkowy"))
                    transferInfoLabel.setText("Tak");
                else
                    transferInfoLabel.setText("Nie");
                planeInfoLabel.setText(planes[finalJ]);
                priceInfoLabel.setText("0");
                quantityInfoLabel.setText(quantities[finalJ]);
                ticketPrice=Integer.parseInt(prices[finalJ]);
                onTicketClick();
            });
            AnchorPane.setRightAnchor(ticketButtons[j],20.0);
            AnchorPane.setLeftAnchor(ticketButtons[j],14.0);
            ticketAnchorPanes[j].getChildren().add(ticketButtons[j]);

            if(Integer.parseInt(quantities[j])==0&& Objects.equals(Main.propNotAvailable, "false"))
                scrollAnchorPane.getChildren().remove(ticketAnchorPanes[j]);
            else
                nrOfTickets++;

            j++;
        }

        scrollAnchorPane.setPrefHeight(104*nrOfTickets+14);
    }

    @FXML
    protected void onAccountClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(accountPane,0,1,0.3);

        scrollPane.setDisable(true);
        filterAnchorPane.setDisable(true);
        accountPane.setVisible(true);
        scaleTransition.play();
        accountLabel.setStyle("-fx-text-fill: black");
    }
    @FXML
    protected void onCloseAccountClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(accountPane,1,0,0.3);

        scrollPane.setDisable(false);
        filterAnchorPane.setDisable(false);
        scaleTransition.play();
        accountLabel.setStyle("-fx-text-fill: #A6B1E1");
    }
    @FXML
    void clockUpdate()
    {
            Timeline clock = new Timeline(
                    new KeyFrame(
                            Duration.seconds(0.5),
                            e->clockLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH mm")))
                    ),
                    new KeyFrame(
                            Duration.seconds(1),
                            e->clockLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))
                    )
            );
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();
    }
    @FXML
    void dateLimit()
    {
        maxDate=(LocalDate.now().plusDays(28));
        minDate=LocalDate.now().plusDays(1);

        dayDatePicker.setDayCellFactory(
                d->new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        super.updateItem(item,empty);
                        setDisable(item.isAfter(maxDate)||item.isBefore(minDate));
                    }
                }
        );
    }
    @FXML
    void onFilterClick()
    {
        String departureAirport= departureComboBox.getValue();
        String arrivalAirport= arrivalComboBox.getValue();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String fromDatePicker=null;
        if(dayDatePicker.getValue()!=null)
        {
            fromDatePicker=dayDatePicker.getValue().format(formatter);
        }

        int nrOfPanes=0;
        for(int i=0;i<nrOfTickets;i++)
        {
            if(Objects.equals(departureAirport,"Wszystkie")||Objects.equals(departureAirport,"All"))
            {
                if(Objects.equals(arrivalAirport,"Wszystkie")||Objects.equals(arrivalAirport,"All"))
                {
                    if(Objects.equals(dateLabels[i].getText(), fromDatePicker)||fromDatePicker==null)
                    {
                        ticketAnchorPanes[i].setVisible(true);
                        ticketAnchorPanes[i].setLayoutX(14);
                        ticketAnchorPanes[i].setLayoutY(14+104*nrOfPanes);
                        nrOfPanes++;
                    }
                    else
                        ticketAnchorPanes[i].setVisible(false);

                }
                else
                {
                    if(!Objects.equals(arrivalLabels[i].getText(), arrivalAirport))
                        ticketAnchorPanes[i].setVisible(false);
                    else
                    {
                        if(Objects.equals(dateLabels[i].getText(), fromDatePicker)||fromDatePicker==null)
                        {
                            ticketAnchorPanes[i].setVisible(true);
                            ticketAnchorPanes[i].setLayoutX(14);
                            ticketAnchorPanes[i].setLayoutY(14+104*nrOfPanes);
                            nrOfPanes++;
                        }
                        else
                            ticketAnchorPanes[i].setVisible(false);
                    }
                }

            }
            else
            {
                if(Objects.equals(arrivalAirport,"Wszystkie")||Objects.equals(arrivalAirport,"All"))
                    if(Objects.equals(departureLabels[i].getText(), departureAirport))
                    {
                        if(Objects.equals(dateLabels[i].getText(), fromDatePicker)||fromDatePicker==null)
                        {
                            ticketAnchorPanes[i].setVisible(true);
                            ticketAnchorPanes[i].setLayoutX(14);
                            ticketAnchorPanes[i].setLayoutY(14+104*nrOfPanes);
                            nrOfPanes++;
                        }
                        else
                            ticketAnchorPanes[i].setVisible(false);
                    }
                    else
                        ticketAnchorPanes[i].setVisible(false);
                else
                {
                    if(!Objects.equals(departureLabels[i].getText(), departureAirport))
                        ticketAnchorPanes[i].setVisible(false);
                    else
                    {
                        if (Objects.equals(arrivalLabels[i].getText(), arrivalAirport))
                        {
                            if(Objects.equals(dateLabels[i].getText(), fromDatePicker)||fromDatePicker==null)
                            {
                                ticketAnchorPanes[i].setVisible(true);
                                ticketAnchorPanes[i].setLayoutX(14);
                                ticketAnchorPanes[i].setLayoutY(14+104*nrOfPanes);
                                nrOfPanes++;
                            }
                            else
                                ticketAnchorPanes[i].setVisible(false);
                        }
                        else
                            ticketAnchorPanes[i].setVisible(false);

                    }
                }
            }
            scrollAnchorPane.setPrefHeight(104*nrOfPanes+14);
        }
    }
    @FXML
    void onDateResetClick()
    {
        dayDatePicker.getEditor().clear();
        dayDatePicker.setValue(null);
        if(Objects.equals(languageComboBox.getValue(), "Polski"))
            dayDatePicker.setPromptText("Wszystkie");
        else if(Objects.equals(languageComboBox.getValue(), "English"))
            dayDatePicker.setPromptText("All");
        onFilterClick();
    }
    @FXML
    void onUncoveredClick()
    {
        passwordLabel.setVisible(false);
        uncoveredImageView.setVisible(false);
        uncoveredPasswordLabel.setVisible(true);
        coveredImageView.setVisible(true);
        uncoveredButton.setVisible(false);
        coveredButton.setVisible(true);
    }
    @FXML
    void onCoveredClick()
    {
        passwordLabel.setVisible(true);
        uncoveredImageView.setVisible(true);
        uncoveredPasswordLabel.setVisible(false);
        coveredImageView.setVisible(false);
        uncoveredButton.setVisible(true);
        coveredButton.setVisible(false);
    }
    @FXML
    void onPasswordChangeClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(newPasswordPane,0,1,0.2);
        newPasswordPane.setVisible(true);
        passwordChangeButton.setDisable(true);
        accountDeleteButton.setDisable(true);
        closeAccountButton.setDisable(true);
        scaleTransition.play();
    }
    @FXML
    void onCancelPasswordClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(newPasswordPane,1,0,0.2);
        scaleTransition.play();
        passwordChangeButton.setDisable(false);
        accountDeleteButton.setDisable(false);
        closeAccountButton.setDisable(false);
    }
    @FXML
    void onSavePasswordClick() throws IOException, ExecutionException, InterruptedException {
        String newPassword=newPasswordTextField.getText();
        if(!Objects.equals(newPassword, ""))
        {
            CRUDaccounts updatedUser=CRUDService.getAccountCRUD(LoginController.login);
            updatedUser.setPassword(newPassword);
            CRUDService.updateAccountCRUD(updatedUser);
            
            LoginController.password=newPassword;
            uncoveredPasswordLabel.setText(newPassword);

            ScaleTransition scaleTransition=makeScaleTransition(newPasswordPane,1,0,0.2);
            scaleTransition.play();
            passwordChangeButton.setDisable(false);
            accountDeleteButton.setDisable(false);
            closeAccountButton.setDisable(false);
        }
        else
        {
            newPasswordTextField.setPromptText("BŁĄD!");
        }
    }
    @FXML
    void onAccountDeleteClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(accountDeletePane,0,1,0.2);
        accountDeletePane.setVisible(true);
        passwordChangeButton.setDisable(true);
        accountDeleteButton.setDisable(true);
        closeAccountButton.setDisable(true);
        scaleTransition.play();
    }
    @FXML
    void onConfirmAccountDeleteClick(ActionEvent event) throws IOException {
        String currentPassword=deleteAccountTextField.getText();
        if(Objects.equals(currentPassword, LoginController.password))
        {
            CRUDService.deleteAccountCRUD(LoginController.login);
            File authFile=new File("./auth.txt");
            BufferedWriter authWriter = new BufferedWriter(new FileWriter(authFile));
            authWriter.write("");
            authWriter.close();

            Parent loggingFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("logging.fxml")));
            Scene scene = new Scene(loggingFXML);
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("mainAppStyles.css")).toExternalForm());
            Stage loggingStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loggingStage.setScene(scene);
            loggingStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            loggingStage.setX((primScreenBounds.getWidth() - loggingStage.getWidth()) / 2);
            loggingStage.setY((primScreenBounds.getHeight() - loggingStage.getHeight()) / 2);
        }
        else
        {
            deleteAccountTextField.clear();
            deleteAccountTextField.setPromptText("BŁĄD!");
        }
    }
    @FXML
    void onCancelAccountDeleteClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(accountDeletePane,1,0,0.2);
        scaleTransition.play();
        passwordChangeButton.setDisable(false);
        accountDeleteButton.setDisable(false);
        closeAccountButton.setDisable(false);
    }
    @FXML
    void onCreditsClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(creditsPane,0,1,0.3);

        scrollPane.setDisable(true);
        filterAnchorPane.setDisable(true);
        creditsPane.setVisible(true);
        scaleTransition.play();
        creditsLabel.setStyle("-fx-text-fill: black");
    }
    @FXML
    void onCloseCreditsClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(creditsPane,1,0,0.3);

        scrollPane.setDisable(false);
        filterAnchorPane.setDisable(false);
        scaleTransition.play();
        creditsLabel.setStyle("-fx-text-fill: #A6B1E1");
    }
    @FXML
    void onSettingsClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(settingsPane,0,1,0.3);

        scrollPane.setDisable(true);
        filterAnchorPane.setDisable(true);
        settingsPane.setVisible(true);
        scaleTransition.play();
        settingsLabel.setStyle("-fx-text-fill: black");

        if(Objects.equals(Main.propNotAvailable, "true"))
            showNotAvailableCheckBox.setSelected(true);
        else
            showNotAvailableCheckBox.setSelected(false);

    }
    @FXML
    void onCloseSettingsClick()
    {
        ScaleTransition scaleTransition=makeScaleTransition(settingsPane,1,0,0.3);

        scrollPane.setDisable(false);
        filterAnchorPane.setDisable(false);
        scaleTransition.play();
        settingsLabel.setStyle("-fx-text-fill: #A6B1E1");
    }
    @FXML
    void onLanguageClick() throws IOException
    {
        String newLanguage= languageComboBox.getValue();
        File file=new File("./properties.txt");
        File tempFile=new File("./properties_temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String property;
        while((property=reader.readLine())!=null)
        {
            String[] data=property.split(" ");
                if(!Objects.equals(data[0], "language"))
                {
                    writer.write(data[0]+" "+data[1]+System.getProperty("line.separator"));
                }
                else
                {
                    data[1]=newLanguage;
                    writer.write(data[0]+" "+data[1]+System.getProperty("line.separator"));
                }
        }
        writer.close();
        reader.close();
        file.delete();
        tempFile.renameTo(file);
        Main.propertiesScan();
        propertiesCheck();
    }

    @FXML
    protected void onLogoutClick() throws IOException
    {
        Parent logoutFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("logout.fxml")));
        Scene scene = new Scene(logoutFXML);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("mainAppStyles.css")).toExternalForm());
        logoutStage = new Stage();
        logoutStage.initStyle(StageStyle.UTILITY);
        if(Objects.equals(Main.propLanguage, "English"))
            logoutStage.setTitle("Leaving application...");
        else if(Objects.equals(Main.propLanguage, "Polski"))
            logoutStage.setTitle("Opuszczanie aplikacji...");
        logoutStage.setScene(scene);
        logoutStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        logoutStage.setX((primScreenBounds.getWidth() - logoutStage.getWidth()) / 2);
        logoutStage.setY((primScreenBounds.getHeight() - logoutStage.getHeight()) / 2);
    }

    void propertiesCheck()
    {
        if(Objects.equals(Main.propLanguage, "Polski"))
        {
            settingsTitleLabel.setText("Ustawienia aplikacji");
            languageLabel.setText("Język aplikacji");
            accountLabel.setText("Konto");
            settingsLabel.setText("Ustawienia");
            creditsLabel.setText("Autorzy");
            logoutLabel.setText("Wyloguj się");
            departureLabel.setText("Miejsce wylotu:");
            arrivalLabel.setText("Miejsce przylotu:");
            dateLabel.setText("Dzień:");
            luxuryLabel.setText("Luksus i prestiż");
            airportsList.set(0,"Wszystkie");
            closeSettingsButton.setText("Zamknij");

            departureComboBox.getItems().clear();
            arrivalComboBox.getItems().clear();
            departureComboBox.getItems().addAll(airportsList);
            arrivalComboBox.getItems().addAll(airportsList);
            departureComboBox.getSelectionModel().selectFirst();
            arrivalComboBox.getSelectionModel().selectFirst();
            dayDatePicker.setPromptText("Wszystkie");

            for(int i=0;i<nrOfTickets;i++)
            {

                if(Objects.equals(availabilityLabels[i].getText(), "Available!"))
                    availabilityLabels[i].setText("Dostępne!");
                else if(Objects.equals(availabilityLabels[i].getText(), "Not available."))
                    availabilityLabels[i].setText("Niedostępne.");

                if(Objects.equals(availability2Labels[i].getText(), "full stock"))
                    availability2Labels[i].setText("duża ilość");
                else if(Objects.equals(availability2Labels[i].getText(), "low stock"))
                    availability2Labels[i].setText("mała ilość");
                else if(Objects.equals(availability2Labels[i].getText(), "sold out"))
                    availability2Labels[i].setText("brak");

                if(!Objects.equals(currencyLabels[i].getText(), "zł"))
                {
                    DecimalFormat df = new DecimalFormat("#");
                    double newPrice=Integer.parseInt(priceLabels[i].getText())*4.43;
                    priceLabels[i].setText(df.format(newPrice));
                    currencyLabels[i].setText("zł");
                }
            }

            creditsTitleLabel.setText("Autorzy aplikacji");
            closeCreditsButton.setText("Zamknij");
            closeAccountButton.setText("Zamknij");
            passwordChangeButton.setText("Zmień hasło");
            accountDeleteButton.setText("Usuń konto");
            accountTitleLabel.setText("Panel zarządzania kontem");
            personalDataLabel.setText("Twoje dane osobowe:");
            accNameLabel.setText("Imię:");
            accSurnameLabel.setText("Nazwisko:");
            accLoginLabel.setText("Login:");
            accPasswordLabel.setText("Hasło:");

        }
        else if(Objects.equals(Main.propLanguage, "English"))
        {
            settingsTitleLabel.setText("App options");
            languageLabel.setText("Language");
            accountLabel.setText("Account");
            settingsLabel.setText("Settings");
            creditsLabel.setText("Credits");
            logoutLabel.setText("Logout");
            departureLabel.setText("Departure:");
            arrivalLabel.setText("Arrival:");
            dateLabel.setText("Date:");
            luxuryLabel.setText("Luxury & Prestige");
            airportsList.set(0,"All");
            closeSettingsButton.setText("Close");

            departureComboBox.getItems().clear();
            arrivalComboBox.getItems().clear();
            departureComboBox.getItems().addAll(airportsList);
            arrivalComboBox.getItems().addAll(airportsList);
            departureComboBox.getSelectionModel().selectFirst();
            arrivalComboBox.getSelectionModel().selectFirst();
            dayDatePicker.setPromptText("All");

            for(int i=0;i<nrOfTickets;i++)
            {

                if(Objects.equals(availabilityLabels[i].getText(), "Dostępne!"))
                    availabilityLabels[i].setText("Available!");
                else if(Objects.equals(availabilityLabels[i].getText(), "Niedostępne."))
                    availabilityLabels[i].setText("Not available.");

                if(Objects.equals(availability2Labels[i].getText(), "duża ilość"))
                    availability2Labels[i].setText("full stock");
                else if(Objects.equals(availability2Labels[i].getText(), "mała ilość"))
                    availability2Labels[i].setText("low stock");
                else if(Objects.equals(availability2Labels[i].getText(), "brak"))
                    availability2Labels[i].setText("sold out");

                DecimalFormat df = new DecimalFormat("#");
                double newPrice=Integer.parseInt(priceLabels[i].getText())/4.43;
                priceLabels[i].setText(df.format(newPrice));
                currencyLabels[i].setText("$");
            }

            creditsTitleLabel.setText("Credits");
            closeCreditsButton.setText("Close");
            closeAccountButton.setText("Close");
            passwordChangeButton.setText("Change password");
            accountDeleteButton.setText("Delete account");
            accountTitleLabel.setText("Account management");
            personalDataLabel.setText("Your personal data:");
            accNameLabel.setText("Name:");
            accSurnameLabel.setText("Surname:");
            accLoginLabel.setText("Login:");
            accPasswordLabel.setText("Password:");
        }

        if(Objects.equals(Main.propFullscreen, "true"))
            fullscreenCheckbox.setSelected(true);
    }

    @FXML
    private void onFullscreenClick() throws IOException
    {
        if(fullscreenCheckbox.isSelected())
        {
            Main.propFullscreen="true";
            AppLoading.mainAppStage.setFullScreen(true);
        }
        else
        {
            Main.propFullscreen="false";
            AppLoading.mainAppStage.setFullScreen(false);
        }

        File file=new File("./properties.txt");
        File tempFile=new File("./properties_temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String property;
        while((property=reader.readLine())!=null)
        {
            String[] data=property.split(" ");
            if(!Objects.equals(data[0], "fullscreen"))
            {
                writer.write(data[0]+" "+data[1]+System.getProperty("line.separator"));
            }
            else
            {
                data[1]=Main.propFullscreen;
                writer.write(data[0]+" "+data[1]+System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        file.delete();
        tempFile.renameTo(file);
        Main.propertiesScan();
    }

    @FXML
    void onTicketClick()
    {
        scrollPane.setDisable(true);
        filterAnchorPane.setDisable(true);
        ticketPane.setVisible(true);
        agreementCheckBox.setSelected(false);

        Path path = new Path();
        path.getElements().add(new MoveTo(-700,contentAnchorPane.getHeight()/2-75));
        path.getElements().add(new LineTo(contentAnchorPane.getWidth()/2-120,contentAnchorPane.getHeight()/2-75));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(700));
        pathTransition.setPath(path);
        pathTransition.setNode(ticketPane);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();

        amountInfoLabel.setText("0");
        int newQuantity=Integer.parseInt(quantityInfoLabel.getText());
        quantityInfoLabel.setText(Integer.toString(newQuantity));
        currencyInfoLabel.setLayoutX(397);
    }

    @FXML
    void onTicketCloseClick()
    {
        Path path = new Path();
        path.getElements().add(new MoveTo(contentAnchorPane.getWidth()/2-120,contentAnchorPane.getHeight()/2-75));
        path.getElements().add(new LineTo(-700,contentAnchorPane.getHeight()/2-75));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(700));
        pathTransition.setPath(path);
        pathTransition.setNode(ticketPane);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();

        scrollPane.setDisable(false);
        filterAnchorPane.setDisable(false);
    }

    @FXML
    void onDecreaseAmountClick()
    {
        if(Integer.parseInt(amountInfoLabel.getText())>0)
        {
            int newAmount = Integer.parseInt(amountInfoLabel.getText()) - 1;
            amountInfoLabel.setText(Integer.toString(newAmount));

            int newQuantity = Integer.parseInt(quantityInfoLabel.getText()) + 1;
            quantityInfoLabel.setText(Integer.toString(newQuantity));

            int newPrice=ticketPrice*Integer.parseInt(amountInfoLabel.getText());

            if(Integer.toString(newPrice).length()-priceInfoLabel.getText().length()==-1)
                currencyInfoLabel.setLayoutX(currencyInfoLabel.getLayoutX()-15);

            priceInfoLabel.setText(Integer.toString(newPrice));
            if(agreementCheckBox.isSelected())
                buyTicketsButton.setDisable(false);
        }
        if(Integer.parseInt(amountInfoLabel.getText())==0)
            buyTicketsButton.setDisable(true);
    }

    @FXML
    void onIncreaseAmountClick()
    {
        if(Integer.parseInt(quantityInfoLabel.getText())>0)
        {
            int newAmount=Integer.parseInt(amountInfoLabel.getText())+1;
            amountInfoLabel.setText(Integer.toString(newAmount));

            int newQuantity=Integer.parseInt(quantityInfoLabel.getText())-1;
            quantityInfoLabel.setText(Integer.toString(newQuantity));

            int newPrice=ticketPrice*Integer.parseInt(amountInfoLabel.getText());

            if(Integer.toString(newPrice).length()-priceInfoLabel.getText().length()==1)
                currencyInfoLabel.setLayoutX(currencyInfoLabel.getLayoutX()+15);

            priceInfoLabel.setText(Integer.toString(newPrice));
            if(agreementCheckBox.isSelected())
                buyTicketsButton.setDisable(false);
        }
    }

    @FXML
    void onAgreementCheck()
    {
        if(agreementCheckBox.isSelected()&&Integer.parseInt(amountInfoLabel.getText())>0)
            buyTicketsButton.setDisable(false);
        else
            buyTicketsButton.setDisable(true);

    }

    @FXML
    void onShowNotAvailableClick() throws IOException, ExecutionException, InterruptedException {

        if(showNotAvailableCheckBox.isSelected())
            Main.propNotAvailable="true";
        else
            Main.propNotAvailable="false";

        File file=new File("./properties.txt");
        File tempFile=new File("./properties_temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String property;

        while((property=reader.readLine())!=null)
        {
            String[] data=property.split(" ");
            if(!Objects.equals(data[0], "notavailable"))
            {
                writer.write(data[0]+" "+data[1]+System.getProperty("line.separator"));
            }
            else
            {
                data[1]=Main.propNotAvailable;
                writer.write(data[0]+" "+data[1]+System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        file.delete();
        tempFile.renameTo(file);
        Main.propertiesScan();
        ticketsCheck();
    }
    @FXML
    void onRefreshClick() throws ExecutionException, InterruptedException {
        ticketsCheck();
    }

    @FXML
    void onAvatarChooseClick() throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG file","*.png"));
        File file=fileChooser.showOpenDialog(null);
        if(file!=null)
        {
            Image newImage=new Image(file.getAbsolutePath());
            avatarImageView.setImage(newImage);
            Files.copy(file.toPath(),new File("C:/Users/piotr/Desktop/PremiumFlightsApp/src/main/resources/com/" +
                    "example/logowanie/avatar_"+LoginController.login+".png").toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
