module FinalProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires java.desktop;
	requires mysql.connector.java;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Controller to javafx.base;
	opens Views to javafx.base;
	opens model to javafx.base;
}
