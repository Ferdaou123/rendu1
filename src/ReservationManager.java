import java.sql.*;

public class ReservationManager {

    // Ajouter une réservation
    public void addReservation(int userId, int eventId, int salleId, int terrainId, String dateReservation) {
        String sql = "INSERT INTO reservations (id_user, id_event, id_salle, id_terrain, date_reservation) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setInt(3, salleId);
            stmt.setInt(4, terrainId);
            stmt.setDate(5, java.sql.Date.valueOf(dateReservation));  // Conversion de la chaîne en Date

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Réservation effectuée avec succès !");
            } else {
                System.out.println("Erreur lors de la réservation.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
        }
    }

    // Afficher toutes les réservations
    public void listReservations() {
        String sql = "SELECT * FROM reservations";
        
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Liste des réservations :");
            while (rs.next()) {
                System.out.printf("ID: %d, Utilisateur: %d, Événement: %d, Salle: %d, Terrain: %d, Date: %s%n",
                        rs.getInt("id_reservation"),
                        rs.getInt("id_user"),
                        rs.getInt("id_event"),
                        rs.getInt("id_salle"),
                        rs.getInt("id_terrain"),
                        rs.getDate("date_reservation"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
        }
    }

    // Modifier une réservation
    public void updateReservation(int reservationId, int newUserId, int newEventId, int newSalleId, int newTerrainId, String newDate) {
        String sql = "UPDATE reservations SET id_user = ?, id_event = ?, id_salle = ?, id_terrain = ?, date_reservation = ? WHERE id_reservation = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newUserId);
            stmt.setInt(2, newEventId);
            stmt.setInt(3, newSalleId);
            stmt.setInt(4, newTerrainId);
            stmt.setDate(5, java.sql.Date.valueOf(newDate));  // Conversion de la chaîne en Date
            stmt.setInt(6, reservationId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Réservation mise à jour avec succès !");
            } else {
                System.out.println("Aucune réservation trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
        }
    }

    // Supprimer une réservation
    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservations WHERE id_reservation = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservationId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Réservation supprimée avec succès !");
            } else {
                System.out.println("Aucune réservation trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
        }
    }
    
}