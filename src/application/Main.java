package application;
	
import Controller.MenuController;
import Views.HomePage;
import Views.InsertMenuPage;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	public DBConnection db = new DBConnection();
	public MenuController menuController = new MenuController();
	
	public Main() {
		db.migrateTables();
		menuController.createDefaultMenu();
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			HomePage homePage = new HomePage(primaryStage);
			primaryStage.setScene(homePage.getScene());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
