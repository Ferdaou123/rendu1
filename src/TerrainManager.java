import java.sql.*;
import java.util.Scanner;

public class TerrainManager {

    // ajouter un terrain 
    public void addTerrainFromInput() {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Ajout d'un nouveau terrain :");
        System.out.print("Nom du terrain : ");
        String nomTerrain = scanner.nextLine();

        System.out.print("Type du terrain : ");
        String typeTerrain = scanner.nextLine();

        
        String sql = "INSERT INTO terrains (nom_terrain, type) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();  
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomTerrain);
            stmt.setString(2, typeTerrain);

            stmt.executeUpdate();
            System.out.println("Terrain ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur de l'ajout du terrain : " + e.getMessage());
        }
    }

    // affche les terrains
    public void listTerrains() {
        String sql = "SELECT * FROM terrains";  
        try (Connection conn = DatabaseConnection.connect();  
             Statement stmt = conn.createStatement();  
             ResultSet rs = stmt.executeQuery(sql)) {  

            System.out.println("Liste des terrains :");
            while (rs.next()) {  
                System.out.printf("ID: %d, Nom: %s, Type: %s%n",
                        rs.getInt("id_terrain"), 
                        rs.getString("nom_terrain"),  
                        rs.getString("type"));  
            }
        } catch (SQLException e) {
            System.err.println("Erreur de l'affichage des terrains : " + e.getMessage());
        }
    }
}


