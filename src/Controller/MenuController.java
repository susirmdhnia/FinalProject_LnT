package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.DBConnection;
import application.DBSingleton;
import model.Menu;

public class MenuController {
    private static DBConnection db = DBSingleton.getInstance();

    
    public static void createDefaultMenu() {
        try {
            insertMenu(new Menu("PD-789", "Banana Pudding", 100000, 25));
            insertMenu(new Menu("PD-554", "Mango Yakult Pudding", 75000, 15));
            insertMenu(new Menu("PD-125", "Strawberry Chocolatte Pudding", 80000, 5));
            System.out.println("Successfully Insert Default Menu :)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public static boolean insertMenu(Menu menu) {
        if (menuExists(menu)) {
            return false;
        }
        String query = "INSERT INTO menu (kodeMenu, namaMenu, hargaMenu, stokMenu) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(query);
            stmt.setString(1, menu.getKodeMenu());
            stmt.setString(2, menu.getNamaMenu());
            stmt.setInt(3, menu.getHargaMenu());
            stmt.setInt(4, menu.getStokMenu());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static boolean menuExists(Menu menu) {
        String query = "SELECT COUNT(*) FROM menu WHERE kodeMenu = ?";
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(query);
            stmt.setString(1, menu.getKodeMenu());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM menu";

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String kodeMenu = rs.getString("kodeMenu");
                String namaMenu = rs.getString("namaMenu");
                int hargaMenu = rs.getInt("hargaMenu");
                int stokMenu = rs.getInt("stokMenu");
                menus.add(new Menu(kodeMenu, namaMenu, hargaMenu, stokMenu));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }


	public static boolean updateMenu(int stokMenu, int hargaMenu, String kodeMenu) {
		String query = "UPDATE menu SET hargaMenu = ?, stokMenu = ? WHERE kodeMenu = ?";
		try {
			PreparedStatement stmt = db.getConnection().prepareStatement(query);
			stmt.setInt(1, hargaMenu);
			stmt.setInt(2, stokMenu);
			stmt.setString(3, kodeMenu);
			
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	public static boolean deleteMenu(String kodeMenu) {
	    try {
	        return db.deleteMenu(kodeMenu);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
