package application;

public class DBSingleton {
	
	private static DBConnection instance;
	public static DBConnection getInstance() {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
}
