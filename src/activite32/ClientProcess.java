
package activite32;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientProcess implements Runnable {

    private Socket socket;

    public ClientProcess(Socket socket) {
        this.socket = socket;
    }

    private double calculer(Operation op) {

        double a = op.getNombre1();
        double b = op.getNombre2();

        switch (op.getOperateur()) {

            case '+':
                return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            case '/':
                if (b == 0) {
                    throw new ArithmeticException(
                            "Division par zero impossible"
                    );
                }
                return a / b;

            default:
                throw new IllegalArgumentException(
                        "Operateur invalide"
                );
        }
    }

    private synchronized int incrementerCompteur() {
        return ServeurCalculatrice.compteurGlobal.incrementAndGet();
    }

    @Override
    public void run() {

        try {

            ObjectOutputStream sortie =
                    new ObjectOutputStream(socket.getOutputStream());

            sortie.flush();

            ObjectInputStream entree =
                    new ObjectInputStream(socket.getInputStream());

            while (true) {

                Object objet = entree.readObject();

                if (objet instanceof Operation) {

                    Operation op = (Operation) objet;

                    try {

                        double resultat = calculer(op);

                        int numero = incrementerCompteur();

                        System.out.println(
                                "Operation numero " + numero
                                        + " traitee par "
                                        + socket.getRemoteSocketAddress()
                        );

                        System.out.println(
                                op.getNombre1() + " "
                                        + op.getOperateur() + " "
                                        + op.getNombre2() + " = "
                                        + resultat
                        );

                        Resultat reponse = new Resultat(
                                resultat,
                                numero,
                                "Calcul effectue avec succes"
                        );

                        sortie.writeObject(reponse);
                        sortie.flush();

                    } catch (ArithmeticException |
                             IllegalArgumentException e) {

                        Resultat erreur = new Resultat(
                                0, 0, e.getMessage()
                        );

                        sortie.writeObject(erreur);
                        sortie.flush();
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "Client deconnecte : "
                            + socket.getRemoteSocketAddress()
            );

        } finally {

            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Erreur fermeture socket.");
            }
        }
    }
}
