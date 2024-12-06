import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EventManager {

    //  ajouter un événement
    public void addEventFromInput(Scanner scanner) {
        System.out.println("Ajout d'un nouvel événement :");
        System.out.print("Nom de l'événement : ");
        String nomEvent = scanner.nextLine();

        System.out.print("Date de l'événement (yyyy-mm-dd) : ");
        String dateEvent = scanner.nextLine();

        System.out.print("Description de l'événement : ");
        String description = scanner.nextLine();

        System.out.print("ID de l'utilisateur associé : ");
        int userId = scanner.nextInt();

        String sql = "INSERT INTO evenements (nom_event, date_event, description, id_user) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

           
            stmt.setString(1, nomEvent);
            stmt.setDate(2, java.sql.Date.valueOf(dateEvent));
            stmt.setString(3, description);
            stmt.setInt(4, userId);

            stmt.executeUpdate();
            System.out.println("Événement ajouté avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de l'événement : " + e.getMessage());
        }
    }

    // modifier un événement
    public void updateEventFromInput(Scanner scanner) {
        System.out.print("Entrez l'ID de l'événement à mettre à jour : ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Nouveau nom de l'événement : ");
        String newName = scanner.nextLine();

        System.out.print("Nouvelle date de l'événement (yyyy-mm-dd) : ");
        String newDate = scanner.nextLine();

        System.out.print("Nouvelle description de l'événement : ");
        String newDescription = scanner.nextLine();

        String sql = "UPDATE evenements SET nom_event = ?, date_event = ?, description = ? WHERE id_event = ?";
        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setDate(2, java.sql.Date.valueOf(newDate));
            stmt.setString(3, newDescription);
            stmt.setInt(4, eventId);

        
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Événement mis à jour avec succès !");
            } else {
                System.out.println("Aucun événement trouvé avec cet ID.");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la modification de l'événement : " + e.getMessage());
        }
    }

    //afficher tous les événements
    public void listEvents(Scanner scanner) {
        String sql = "SELECT * FROM evenements";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Liste des événements :");
            while (rs.next()) {
                System.out.printf("ID: %d, Nom: %s, Date: %s, Description: %s, User ID: %d%n",
                        rs.getInt("id_event"),
                        rs.getString("nom_event"),
                        rs.getDate("date_event").toString(),
                        rs.getString("description"),
                        rs.getInt("id_user"));
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des événements : " + e.getMessage());
        }
    }
}
