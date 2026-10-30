
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Serveur {

    public static void main(String[] args) {

        final int PORT = 1234;

        // Compteur du nombre de clients connectés
        AtomicInteger compteur = new AtomicInteger(0);

        try (ServerSocket serveur = new ServerSocket(PORT)) {

            System.out.println("=================================");
            System.out.println("Serveur TCP demarre !");
            System.out.println("En attente des clients...");
            System.out.println("Port : " + PORT);
            System.out.println("=================================");

            // Boucle infinie : accepter plusieurs clients
            while (true) {

                // Attendre la connexion d'un client
                Socket socket = serveur.accept();

                // Attribuer un numero au client
                int numeroClient = compteur.incrementAndGet();

                // Recuperer l'adresse IP du client
                System.out.println(
                        "Client numero " + numeroClient +
                                " connecte depuis : " +
                                socket.getRemoteSocketAddress()
                );

                // Creer un thread pour ce client
                ClientHandler gestionnaire =
                        new ClientHandler(socket, numeroClient);

                Thread thread = new Thread(gestionnaire);

                // Demarrer le traitement du client
                thread.start();
            }

        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }
}
