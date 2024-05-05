package Views;

import Controller.MenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateMenuPage {
	private Stage stage;
    private static Scene scene;
    private BorderPane root;
    private Label hargaMenuLabel, stokMenuLabel;
    private TextField hargaMenuField, stokMenuField;
    private Button updateBtn, backBtn;
	private String kodeMenu;


    public UpdateMenuPage(Stage stage, String kodeMenu) {
        this.stage = stage;
        this.setKodeMenu(kodeMenu);
        this.root = new BorderPane();
        this.stage.setTitle("Update Menu Page");
        this.scene = new Scene(getRootNode(), 800, 500);
    }

    private Parent getRootNode() {
        Label titleLabel = new Label("Update Menu");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #003366");

        hargaMenuLabel = new Label("Harga Baru Menu:");
        hargaMenuField = new TextField();
        hargaMenuField.setPromptText("Harga Baru Menu");

        stokMenuLabel = new Label("Stok Baru Menu");
        stokMenuField = new TextField();
        stokMenuField.setPromptText("Stok Baru Menu");

        updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> handleUpdateButton());

        backBtn = new Button("Back");
        backBtn.setOnAction(e -> handleBackButton());

        VBox topBox = new VBox();
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10, 0, 0, 0));
        topBox.getChildren().add(titleLabel);
        root.setTop(topBox);
        
        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(20));
        centerBox.getChildren().addAll(hargaMenuLabel, hargaMenuField, stokMenuLabel,stokMenuField, updateBtn);

        VBox bottomBox = new VBox(10);
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        bottomBox.setPadding(new Insets(20));
        bottomBox.getChildren().add(backBtn);

        root.setCenter(centerBox);
        root.setBottom(bottomBox);

        root.setStyle("-fx-background-color: #B8B8B8");
        return root;
    }

    private void handleUpdateButton() {
        String kodeMenu = "";
    	String hargaMenuStr = hargaMenuField.getText();
        String stokMenuStr = stokMenuField.getText();

        if (!hargaMenuStr.isEmpty() && !stokMenuStr.isEmpty()) {
            try {
                int hargaMenu = Integer.parseInt(hargaMenuStr);
                int stokMenu = Integer.parseInt(stokMenuStr);

                String kodeMenu1 = getKodeMenu();
                
                boolean isSuccess = MenuController.updateMenu(stokMenu, hargaMenu, kodeMenu1);

                if (isSuccess) {
                    showAlert("Success", "Menu successfully updated.");

                    ViewMenuPage viewMenuPage = new ViewMenuPage(stage);
                    stage.setScene(viewMenuPage.getScene());
                } else {
                	showAlert("Error", "Failed to update menu. Please check the input.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Error", "Invalid input for harga/stok. Please enter valid numbers.");
            }
        } else {
        	showAlert("Error", "Please fill in all fields.");
        }
    }

    private void showAlert(String title, String message) {
    	Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

	}

	private void handleBackButton() {
        HomePage homePage = new HomePage(stage);
        stage.setScene(homePage.getScene());
    }

    public static Scene getScene() {
        return scene;
    }

	public String getKodeMenu() {
		return kodeMenu;
	}

	public void setKodeMenu(String kodeMenu) {
		this.kodeMenu = kodeMenu;
	}
}
