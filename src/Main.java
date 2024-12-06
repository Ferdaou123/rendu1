import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        EventManager eventManager = new EventManager();
        RoomManager roomManager = new RoomManager();
        TerrainManager terrainManager = new TerrainManager();
        ReservationManager reservationManager = new ReservationManager();

        int choixPrincipal;

        do {
            afficherMenuPrincipal();
            System.out.print("Votre choix : ");
            choixPrincipal = scanner.nextInt();
            scanner.nextLine(); 

            switch (choixPrincipal) {
                case 1:
                    menuGestionUtilisateurs(scanner, userManager);
                    break;
                case 2:
                    menuGestionEvenements(scanner, eventManager);
                    break;
                case 3:
                    menuGestionSalles(scanner, roomManager);
                    break;
                case 4:
                    menuGestionTerrains(scanner, terrainManager);
                    break;
                case 5:
                    menuGestionReservations(scanner, reservationManager);
                    break;
                case 0:
                    System.out.println("Mercci d'avoir utiliser notre application !");;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choixPrincipal != 0);

        scanner.close();
    }

    private static void afficherMenuPrincipal() {
        System.out.println("\n*** Menu Principal ***");
        System.out.println("1. Gestion des utilisateurs");
        System.out.println("2. Gestion des événements");
        System.out.println("3. Gestion des salles");
        System.out.println("4. Gestion des terrains");
        System.out.println("5. Gestion des réservations");
        System.out.println("0. Quitter");
    }

    private static void menuGestionUtilisateurs(Scanner scanner, UserManager userManager) {
        int choix;
        do {
            System.out.println("\n--- Gestion des utilisateurs ---");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Afficher la liste des utilisateurs");
            System.out.println("3. Supprimer un utilisateur");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    userManager.addUserFromInput();
                    break;
                case 2:
                    userManager.listUsers();
                    break;
                case 3:
                    userManager.deleteUserFromInput();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private static void menuGestionEvenements(Scanner scanner, EventManager eventManager) {
        int choix;
        do {
            System.out.println("\n--- Gestion des événements ---");
            System.out.println("1. Ajouter un événement");
            System.out.println("2. Afficher les événements");
            System.out.println("3. Mettre à jour un événement");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    eventManager.addEventFromInput(scanner);
                    break;
                case 2:
                    eventManager.listEvents(scanner);
                    break;
                case 3:
                    eventManager.updateEventFromInput(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private static void menuGestionSalles(Scanner scanner, RoomManager roomManager) {
        int choix;
        do {
            System.out.println("\n--- Gestion des salles ---");
            System.out.println("1. Ajouter une salle");
            System.out.println("2. Afficher les salles");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    roomManager.addRoomFromInput();
                    break;
                case 2:
                    roomManager.listRooms();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private static void menuGestionTerrains(Scanner scanner, TerrainManager terrainManager) {
        int choix;
        do {
            System.out.println("\n--- Gestion des terrains ---");
            System.out.println("1. Ajouter un terrain");
            System.out.println("2. Afficher les terrains");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();
    
            switch (choix) {
                case 1:
                    terrainManager.addTerrainFromInput();
                    break;
                case 2:
                    terrainManager.listTerrains(); 
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }
    
    private static void menuGestionReservations(Scanner scanner, ReservationManager reservationManager) {
        int choix;
        do {
            System.out.println("\n--- Gestion des réservations ---");
            System.out.println("1. Ajouter une réservation");
            System.out.println("2. Afficher les réservations");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    System.out.println("Entrez les détails de la réservation :");
                    System.out.print("ID utilisateur : ");
                    int userId = scanner.nextInt();
                    System.out.print("ID événement : ");
                    int eventId = scanner.nextInt();
                    System.out.print("ID salle : ");
                    int salleId = scanner.nextInt();
                    System.out.print("ID terrain : ");
                    int terrainId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Date de réservation (yyyy-mm-dd) : ");
                    String dateReservation = scanner.nextLine();

                    reservationManager.addReservation(userId, eventId, salleId, terrainId, dateReservation);
                    break;
                case 2:
                    reservationManager.listReservations();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }
}
