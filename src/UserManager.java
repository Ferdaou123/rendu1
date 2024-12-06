import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserManager {


    private Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/rendu1";
        String username = "postgres";
        String password = "Dadaws2005";
        return DriverManager.getConnection(url, username, password);
    }

    // ajout uti
public void addUserFromInput() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Ajoutet un utilisateur :");
    System.out.print("Nom : ");
    String nom = scanner.nextLine();

    System.out.print("Prenom : ");
    String prenom = scanner.nextLine();

    System.out.print("Email : ");
    String email = scanner.nextLine();

    System.out.print("Type : ");
    String type = scanner.nextLine();


    String sql = "INSERT INTO utilisateurs (nom, prenom, email, type) VALUES (?, ?, ?, ?::user_type)";
    
    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nom);
        stmt.setString(2, prenom);
        stmt.setString(3, email);
        stmt.setString(4, type);  

        
        stmt.executeUpdate();
        System.out.println("Utilisateur ajoute avec succes !");
    } catch (SQLException e) {
        System.err.println("Erreur de l'ajout de l'utilisateur  : " + e.getMessage());
    }
}


    // achiche les uti
    public void listUsers() {
        String sql = "SELECT * FROM utilisateurs";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Liste des utilisateurs :");
            while (rs.next()) {

                System.out.printf("ID: %d, Nom: %s, Prénom: %s, Email: %s, Type: %s%n",
                        rs.getInt("id_user"),  
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("type")); 
            }
        } catch (SQLException e) {
            System.err.println("Erreur de l'affichage des utilisateurs : " + e.getMessage());
        }
    }



    // suprimer un uti
    public void deleteUserFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'ID de l'utilisateur à supprimer : ");
        int userId = scanner.nextInt();

        String sql = "DELETE FROM utilisateurs WHERE id_user = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }
}