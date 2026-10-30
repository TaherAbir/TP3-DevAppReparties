
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        String adresseServeur = "192.168.1.10";
        int port = 1234;

        try {

            InetAddress adresse =
                    InetAddress.getByName(adresseServeur);

            Socket socket = new Socket();

            socket.connect(
                    new InetSocketAddress(adresse, port)
            );

            System.out.println("Connexion reussie !");

            BufferedReader entree = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter sortie = new PrintWriter(
                    socket.getOutputStream(), true
            );

            // Recevoir le numero attribue par le serveur
            System.out.println("Serveur : " + entree.readLine());

            Scanner clavier = new Scanner(System.in);

            System.out.println("Tapez un message (quit pour quitter)");

            String message;

            while (true) {

                System.out.print("Message : ");
                message = clavier.nextLine();

                if (message.equalsIgnoreCase("quit")) {
                    break;
                }

                sortie.println(message);
            }

            socket.close();

            System.out.println("Deconnexion du serveur.");

        } catch (IOException e) {

            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
