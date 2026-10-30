
package activite32;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        String adresseServeur = "localhost";
        int port = 1234;

        try {

            InetAddress adresse =
                    InetAddress.getByName(adresseServeur);

            Socket socket = new Socket();

            socket.connect(
                    new InetSocketAddress(adresse, port)
            );

            System.out.println("Connecte au serveur !");

            ObjectOutputStream sortie =
                    new ObjectOutputStream(socket.getOutputStream());

            sortie.flush();

            ObjectInputStream entree =
                    new ObjectInputStream(socket.getInputStream());

            Scanner clavier = new Scanner(System.in);

            while (true) {

                System.out.println("\n--- CALCULATRICE ---");

                System.out.print("Premier nombre : ");
                double a = clavier.nextDouble();

                System.out.print("Operateur (+ - * /) : ");
                char operateur = clavier.next().charAt(0);

                System.out.print("Deuxieme nombre : ");
                double b = clavier.nextDouble();

                Operation operation =
                        new Operation(a, b, operateur);

                sortie.writeObject(operation);
                sortie.flush();

                Resultat resultat =
                        (Resultat) entree.readObject();

                System.out.println(
                        "Serveur : " + resultat.getMessage()
                );

                if (resultat.getNumeroOperation() != 0) {

                    System.out.println(
                            "Resultat : " + resultat.getResultat()
                    );

                    System.out.println(
                            "Numero global de l'operation : "
                                    + resultat.getNumeroOperation()
                    );
                }

                System.out.print(
                        "\nVoulez-vous continuer ? (oui/non) : "
                );

                String choix = clavier.next();

                if (choix.equalsIgnoreCase("non")) {
                    break;
                }
            }

            socket.close();

            System.out.println("Deconnexion du serveur.");

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "Erreur client : " + e.getMessage()
            );
        }
    }
}
