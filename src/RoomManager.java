import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RoomManager {

    // ajouter une salle 
    public void addRoomFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ajout d'une nouvelle salle :");
        System.out.print("Nom de la salle : ");
        String nomSalle = scanner.nextLine();

        System.out.print("Capacité de la salle : ");
        int capacite = scanner.nextInt();

       
        String sql = "INSERT INTO salles (nom_salle, capacite) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();  
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomSalle);
            stmt.setInt(2, capacite);

            stmt.executeUpdate();
            System.out.println("Salle ajoutée avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la salle : " + e.getMessage());
        }
    }

    // afficher toutes les salles
    public void listRooms() {
        String sql = "SELECT * FROM salles";  
        try (Connection conn = DatabaseConnection.connect();  
             Statement stmt = conn.createStatement();  
             ResultSet rs = stmt.executeQuery(sql)) {  

            System.out.println("Liste des salles :");
            while (rs.next()) {  
                System.out.printf("ID: %d, Nom: %s, Capacité: %d%n",
                        rs.getInt("id_salle"), 
                        rs.getString("nom_salle"),  
                        rs.getInt("capacite"));  
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'affichage des salles : " + e.getMessage());
        }
    }


}
