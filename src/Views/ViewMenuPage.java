package Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Menu;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;

import Controller.MenuController; // corrected import

public class ViewMenuPage {
    // Declare Attributes
    private Stage stage; // changed to private
    private Scene scene;
    private BorderPane root;
    private TableView<Menu> menuTableView;
    private Button backBtn, updateBtn, deleteBtn;

    public ViewMenuPage(Stage stage) {
        this.stage = stage;
        this.root = new BorderPane();
        this.stage.setTitle("View Menu Page");
        this.scene = new Scene(getRootNode(), 800, 500);
    }

    private Parent getRootNode() {
        Label titleLabel = new Label("Daftar Menu");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #003366");

        menuTableView = new TableView<>();
        menuTableView.setItems(getMenuItems());
        menuTableView.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);

        TableColumn<Menu, String> kodeMenuCol = new TableColumn<>("Kode");
        kodeMenuCol.setCellValueFactory(new PropertyValueFactory<>("kodeMenu"));

        TableColumn<Menu, String> namaMenuCol = new TableColumn<>("Nama");
        namaMenuCol.setCellValueFactory(new PropertyValueFactory<>("namaMenu"));

        TableColumn<Menu, Integer> hargaMenuCol = new TableColumn<>("Harga");
        hargaMenuCol.setCellValueFactory(new PropertyValueFactory<>("hargaMenu"));

        TableColumn<Menu, Integer> stokMenuCol = new TableColumn<>("Stok");
        stokMenuCol.setCellValueFactory(new PropertyValueFactory<>("stokMenu"));

        menuTableView.getColumns().addAll(kodeMenuCol, namaMenuCol, hargaMenuCol, stokMenuCol); // removed unnecessary boolean assignment
        
        updateBtn = new Button("Update Menu");
        updateBtn.setOnAction(e -> handleUpdateButton());
        
        deleteBtn = new Button("Delete Menu");
        deleteBtn.setOnAction(e -> handleDeleteButton());
        
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
        centerBox.getChildren().addAll(menuTableView, updateBtn, deleteBtn);

        VBox bottomBox = new VBox(10);
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        bottomBox.setPadding(new Insets(20));
        bottomBox.getChildren().add(backBtn);

        root.setCenter(centerBox);
        root.setBottom(bottomBox);

        root.setStyle("-fx-background-color: #B8B8B8");
        return root;
    }

    private ObservableList<Menu> getMenuItems(){
        return FXCollections.observableArrayList(MenuController.getAllMenus());
    }
    

    private void handleBackButton() {
        HomePage homePage = new HomePage(stage);
        stage.setScene(homePage.getScene());
    }
    
    
    private void handleUpdateButton() {
    	Menu selectedMenu = menuTableView.getSelectionModel().getSelectedItem();
    	if(selectedMenu != null) {
    		String kodeMenu = selectedMenu.getKodeMenu();
    		UpdateMenuPage updateMenuPage = new UpdateMenuPage(stage, kodeMenu);
    		stage.setScene(updateMenuPage.getScene());
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText(null);
    		alert.setContentText("NO MENU SELECTED TO UPDATE!!!");
    		
    		alert.showAndWait();
    	}
    }
    
    
    private void handleDeleteButton() {
        Menu selectedMenu = menuTableView.getSelectionModel().getSelectedItem();
        if (selectedMenu != null) {
            String kodeMenu = selectedMenu.getKodeMenu();
            boolean isDeleted = MenuController.deleteMenu(kodeMenu);
            if (isDeleted) {
                showAlert("Success", "Menu successfully deleted.");
                
                HomePage homePage = new HomePage(stage);
                stage.setScene(homePage.getScene());
            } else {
                showAlert("Error", "Failed to delete menu. Please check the database connection.");
            }
        } else {
            showAlert("Error", "Please select the menu to delete.");
        }
    }
    
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Scene getScene() {
        return scene;
    }
}
