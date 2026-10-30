
package activite32;

import java.io.Serializable;

public class Resultat implements Serializable {

    private static final long serialVersionUID = 1L;

    private double resultat;
    private int numeroOperation;
    private String message;

    public Resultat(double resultat, int numeroOperation, String message) {
        this.resultat = resultat;
        this.numeroOperation = numeroOperation;
        this.message = message;
    }

    public double getResultat() {
        return resultat;
    }

    public int getNumeroOperation() {
        return numeroOperation;
    }

    public String getMessage() {
        return message;
    }
}
