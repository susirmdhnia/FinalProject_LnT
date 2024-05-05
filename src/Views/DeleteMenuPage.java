package Views;

import Controller.MenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteMenuPage {
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private Label kodeMenuLabel;
    private TextField kodeMenuField;
    private Button deleteBtn, backBtn;

    public DeleteMenuPage(Stage stage) {
        this.stage = stage;
        this.root = new BorderPane();
        this.stage.setTitle("Delete Menu Page");
        this.scene = new Scene(getRootNode(), 800, 500);
    }

    private VBox getRootNode() {
        VBox container = new VBox(20);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(20));

        Label titleLabel = new Label("Delete Menu");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #003366");

        kodeMenuLabel = new Label("Kode Menu:");
        kodeMenuField = new TextField();
        kodeMenuField.setPromptText("Enter Kode Menu");

        deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> handleDeleteButton());

        backBtn = new Button("Back");
        backBtn.setOnAction(e -> handleBackButton());

        container.getChildren().addAll(titleLabel, kodeMenuLabel, kodeMenuField, deleteBtn, backBtn);
        return container;
    }

    private void handleDeleteButton() {
        String kodeMenu = kodeMenuField.getText();
        if (!kodeMenu.isEmpty()) {
            boolean isDeleted = MenuController.deleteMenu(kodeMenu);
            if (isDeleted) {
                showAlert("Success", "Menu successfully deleted.");
                // Go back to the previous page
                HomePage homePage = new HomePage(stage);
                stage.setScene(homePage.getScene());
            } else {
                showAlert("Error", "Failed to delete menu. Please check the database connection.");
            }
        } else {
            showAlert("Error", "Please enter the kode menu to delete.");
        }
    }

    private void handleBackButton() {
        HomePage homePage = new HomePage(stage);
        stage.setScene(homePage.getScene());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Scene getScene() {
        return scene;
    }
}
