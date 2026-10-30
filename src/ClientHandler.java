
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private int numeroClient;

    public ClientHandler(Socket socket, int numeroClient) {
        this.socket = socket;
        this.numeroClient = numeroClient;
    }

    @Override
    public void run() {

        try (
                BufferedReader entree = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                PrintWriter sortie = new PrintWriter(
                        socket.getOutputStream(), true
                )
        ) {

            // Envoyer le numero au client
            sortie.println(
                    "Vous etes le client numero " + numeroClient
            );

            System.out.println(
                    "Communication demarree avec le client numero "
                            + numeroClient
            );

            // Lire les messages envoyes par le client
            String message;

            while ((message = entree.readLine()) != null) {

                System.out.println(
                        "Client numero " + numeroClient +
                                " : " + message
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Erreur avec le client numero " + numeroClient
            );

        } finally {

            try {
                socket.close();

                System.out.println(
                        "Client numero " + numeroClient + " deconnecte."
                );

            } catch (IOException e) {
                System.out.println("Erreur de fermeture du socket.");
            }
        }
    }
}
