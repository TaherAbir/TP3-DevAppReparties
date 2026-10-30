
package activite32;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ServeurCalculatrice {

    public static final int PORT = 1234;

    public static final AtomicInteger compteurGlobal =
            new AtomicInteger(0);

    public static void main(String[] args) {

        try (ServerSocket serveur = new ServerSocket(PORT)) {

            System.out.println("Serveur calculatrice demarre !");
            System.out.println("Port : " + PORT);
            System.out.println("En attente des clients...");

            while (true) {

                Socket socket = serveur.accept();

                System.out.println(
                        "Nouveau client connecte : "
                                + socket.getRemoteSocketAddress()
                );

                ClientProcess traitement =
                        new ClientProcess(socket);

                Thread thread = new Thread(traitement);

                thread.start();
            }

        } catch (IOException e) {

            System.out.println(
                    "Erreur serveur : " + e.getMessage()
            );
        }
    }
}
