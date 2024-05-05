package Views;

import Controller.MenuController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Menu;

public class InsertMenuPage {
    private Stage stage;
    private Scene scene;
    private Button insertBtn, backBtn;
    private Label upText, kodeMenuLB, namaMenuLB, hargaMenuLB, stokMenuLB;
    private TextField kodeMenuField, namaMenuField, hargaMenuField, stokMenuField;
	private HomePage homePage;
    
    public InsertMenuPage(Stage stage, HomePage homePage) {
        this.stage = stage;
        this.homePage = homePage;
        init();
        getRootNode();
        this.stage.setTitle("Insert Menu Page");
        this.scene = new Scene(getRootNode(), 800, 500);
    }
    
    private Parent getRootNode() {
        BorderPane root = new BorderPane();
        
        backBtn = new Button("Back");
        backBtn.setOnAction(e -> handleBackButton());
        
        VBox topBox = new VBox();
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10, 0, 0, 0));
        topBox.getChildren().add(upText);
        root.setTop(topBox);
        
        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER_LEFT);
        centerBox.setPadding(new Insets(20));
        centerBox.getChildren().addAll(kodeMenuLB, kodeMenuField,
            namaMenuLB, namaMenuField,
            hargaMenuLB, hargaMenuField,
            stokMenuLB, stokMenuField);
        root.setCenter(centerBox);
        
        VBox bottomBox = new VBox(10);
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        bottomBox.setPadding(new Insets(20));
        bottomBox.getChildren().addAll(insertBtn, backBtn);
        root.setBottom(bottomBox);
        
        root.setStyle("-fx-background-color: #B8B8B8");
        return root;
    }

    private Object handleBackButton() {
    	HomePage homePage = new HomePage(stage);
        stage.setScene(homePage.getScene());
		return homePage;
	}

	protected void init() {
        upText = new Label("Insert Menu Baru");
        upText.setStyle("-fx-font-size: 30px; -fx-text-fill: #003366");
        
        kodeMenuLB = new Label("Kode Menu:");
        kodeMenuField = new TextField();
        kodeMenuField.setPromptText("Masukkan Kode Menu");
        
        namaMenuLB = new Label("Nama Menu:");
        namaMenuField = new TextField();
        namaMenuField.setPromptText("Masukkan Nama Menu");
        
        hargaMenuLB = new Label("Harga Menu:");
        hargaMenuField = new TextField();
        hargaMenuField.setPromptText("Masukkan Harga Menu");
        
        stokMenuLB = new Label("Stok Menu");
        stokMenuField = new TextField();
        stokMenuField.setPromptText("Masukkan Stok Menu");
        
        insertBtn = new Button("Insert");
        
        insertBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String kodeMenu = kodeMenuField.getText();
                String namaMenu = namaMenuField.getText();
                String hargaMenu = hargaMenuField.getText();
                String stokMenu = stokMenuField.getText();
                
                if (!kodeMenu.isEmpty() && !namaMenu.isEmpty() && !hargaMenu.isEmpty() && !stokMenu.isEmpty()) {
                    try {
                        Menu newMenu = new Menu(kodeMenu, namaMenu, Integer.parseInt(hargaMenu), Integer.parseInt(stokMenu));
                        boolean respondInsert = MenuController.insertMenu(newMenu);
                        
                        if(respondInsert) {
                            showAlert("Success", "Menu inserted successfully!");
                            System.out.println("Directing to home page...");
                            stage.setScene(homePage.getScene());
                            System.out.println("Successfully directed to home page.");
                        } else {
                            showAlert("Error", "Failed to insert menu.");
                        }
                        
                    } catch (NumberFormatException e) {
                        showAlert("Invalid Input", "Harga Menu dan Stok Menu harus angka.");
                    }    
                } else {
                    showAlert("Field Empty", "Mohon isi semua kolom.");
                }
            }
        });
    }
    

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public Scene getScene() {
        return scene;
    }
}
