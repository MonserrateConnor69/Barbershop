package application;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;

public class Main extends Application {
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // Main Menu Buttons
        Button scheduleButton = new Button("Schedule Appointment");
        Button viewButton = new Button("View Appointments");
        Button exitButton = new Button("Exit");

        // Button Styling
        scheduleButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 250px; -fx-padding: 10px;");
        viewButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 250px; -fx-padding: 10px;");
        exitButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 250px; -fx-padding: 10px;");

        // Button Actions
        scheduleButton.setOnAction(e -> showScheduleScreen(primaryStage));
        viewButton.setOnAction(e -> showViewAppointmentsScreen(primaryStage));
        exitButton.setOnAction(e -> primaryStage.close());

        // Layout
        VBox layout = new VBox(20, scheduleButton, viewButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px;");

        // Scene with CSS
        Scene scene = new Scene(layout, 1000, 800);  // Increased size
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // Link CSS

        primaryStage.setTitle("Barbershop Appointment System");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);  // Set minimum window size
        primaryStage.setMinHeight(800); // Set minimum window height
        primaryStage.setMaxWidth(1200);  // Set maximum window size
        primaryStage.setMaxHeight(1000); // Set maximum window height
        primaryStage.show();
    }

    private void showScheduleScreen(Stage stage) {
        // Input Fields
        TextField nameField = new TextField();
        nameField.setPromptText("Customer Name");
        nameField.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 10px;");

        TextField contactField = new TextField();
        contactField.setPromptText("Contact Number");
        contactField.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 10px;");

        ComboBox<String> barberBox = new ComboBox<>();
        barberBox.getItems().addAll("Grace", "James", "Yuri", "Elbert");
        barberBox.setPromptText("Select Barber");
        barberBox.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 10px;");

        DatePicker datePicker = new DatePicker();
        datePicker.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 10px;");

        ComboBox<String> timeSlotBox = new ComboBox<>();
        timeSlotBox.getItems().addAll("10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM");
        timeSlotBox.setPromptText("Select Time Slot");
        timeSlotBox.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 10px;");

        // VIP/Regular ComboBox
        ComboBox<String> customerTypeBox = new ComboBox<>();
        customerTypeBox.getItems().addAll("Regular", "VIP");
        customerTypeBox.setPromptText("Customer Type");
        customerTypeBox.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 10px;");

        // Barbershop ComboBox
        ComboBox<String> barbershopBox = new ComboBox<>();
        barbershopBox.getItems().addAll("Old School", "Supremo", "David's Salon");
        barbershopBox.setPromptText("Select Barbershop");
        barbershopBox.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 10px;");

        // Save Button
        Button saveButton = new Button("Save Appointment");
        saveButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 250px; -fx-padding: 10px;");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String contact = contactField.getText();
            String barber = barberBox.getValue();
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : null;
            String time = timeSlotBox.getValue();
            String customerType = customerTypeBox.getValue();
            String barbershop = barbershopBox.getValue();

            if (name.isEmpty() || contact.isEmpty() || barber == null || date == null || time == null || customerType == null || barbershop == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields!");
                alert.show();
            } else {
                // Apply discount for VIP
                double discount = (customerType.equals("VIP")) ? 0.1 : 0.0; // 10% discount for VIP
                Appointment appointment = new Appointment(name, contact, barber, date, time, barbershop, discount);
                appointments.add(appointment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Saved!");
                alert.show();
            }
        });

        // Back Button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 250px; -fx-padding: 10px;");
        backButton.setOnAction(e -> start(stage));

        // Layout
        VBox layout = new VBox(20, nameField, contactField, barberBox, datePicker, timeSlotBox, customerTypeBox, barbershopBox, saveButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px;");

        // Scene with CSS
        Scene scene = new Scene(layout, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
    }

    private void showViewAppointmentsScreen(Stage stage) {
        // Table
        TableView<Appointment> table = new TableView<>();

        // Columns
        TableColumn<Appointment, String> nameColumn = new TableColumn<>("Customer Name");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCustomerName()));

        TableColumn<Appointment, String> barberColumn = new TableColumn<>("Barber");
        barberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBarber()));

        TableColumn<Appointment, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));

        TableColumn<Appointment, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTime()));

        // Add columns to table
        table.getColumns().addAll(nameColumn, barberColumn, dateColumn, timeColumn);

        // Discount Column
        TableColumn<Appointment, String> discountColumn = new TableColumn<>("Discount");
        discountColumn.setCellValueFactory(data -> {
            double discount = data.getValue().getDiscount();
            return new SimpleStringProperty(discount > 0 ? (discount * 100) + "%" : "None");
        });
        table.getColumns().add(discountColumn);

        // Set data
        table.setItems(appointments);

        // Delete Button
        Button deleteButton = new Button("Delete Appointment");
        deleteButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 250px; -fx-padding: 10px;");
        deleteButton.setOnAction(e -> {
            Appointment selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                appointments.remove(selected);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment deleted successfully!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No appointment selected!");
                alert.show();
            }
        });

        // Layout
        VBox layout = new VBox(20, table, deleteButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px;");

        // Scene with CSS
        Scene scene = new Scene(layout, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
