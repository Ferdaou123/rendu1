import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Méthode statique pour établir la connexion
    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/rendu1";
        String username = "postgres";
        String password = "Dadaws2005";
        return DriverManager.getConnection(url, username, password);
    }
}
