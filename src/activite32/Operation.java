
package activite32;

import java.io.Serializable;

public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    private double nombre1;
    private double nombre2;
    private char operateur;

    public Operation(double nombre1, double nombre2, char operateur) {
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.operateur = operateur;
    }

    public double getNombre1() {
        return nombre1;
    }

    public double getNombre2() {
        return nombre2;
    }

    public char getOperateur() {
        return operateur;
    }
}
