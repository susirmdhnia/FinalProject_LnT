package Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {
    protected Stage stage;
    protected Scene scene;
    protected BorderPane rootNode;
    protected VBox vbox;
    protected Label welcomeText;
    protected ListView<String> menuListView;
    protected Button selectBtn;

    protected void init() {
        welcomeText = new Label("PT Pudding Database Application");
        welcomeText.setStyle("-fx-font-family: 'Open Sans'; -fx-font-size:30px; -fx-text-fill: #003366");

        menuListView = new ListView<>();
        menuListView.getItems().addAll("Insert Menu Baru", "View Menu", "Update Menu", "Delete Menu");

        selectBtn = new Button("Select Menu");
        selectBtn.setOnAction(e -> handleMenuSelection());

        vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(welcomeText, menuListView, selectBtn);
    }

    private void handleMenuSelection() {
        String selectedMenu = menuListView.getSelectionModel().getSelectedItem();
        if(selectedMenu != null) {
            switch(selectedMenu) {
                case "Insert Menu Baru":
                    InsertMenuPage insertMenuPage = new InsertMenuPage(stage, this);
                    stage.setScene(insertMenuPage.getScene());
                    break;
                
                case "View Menu":
                	ViewMenuPage viewMenuPage = new ViewMenuPage(stage);
                	stage.setScene(viewMenuPage.getScene());
                	break;
                	
                case "Update Menu":
                	ViewMenuPage viewMenuPage1 = new ViewMenuPage(stage);
                	stage.setScene(viewMenuPage1.getScene());
                	break;
               
                case "Delete Page":
                	ViewMenuPage viewMenuPage2 = new ViewMenuPage(stage);
                	stage.setScene(viewMenuPage2.getScene());
                	break;
            }
        } else {
            System.out.println("No Menu Selected.");
        }
    }

    protected void setLayout() {
        rootNode = new BorderPane();
        rootNode.setPrefSize(800, 500);
        rootNode.setCenter(vbox);
        vbox.setStyle("-fx-background-color: #B8B8B8");
        BorderPane.setAlignment(vbox, Pos.CENTER);
        BorderPane.setMargin(vbox, new Insets(20));
    }

    public Scene getScene() {
        return scene;    
    }

    public HomePage(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("PT Pudding Database Application");
        init();
        setLayout();
        this.scene = new Scene(rootNode);
    }
}
