
package bancarioo;
import java.util.Date;

public class Transaccion {
    private int sec;
    private String numcta;
    private String Fecha;
    private int tipo;
    private double importe;

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    
    public String getNumcta() {
        return numcta;
    }

    public void setNumcta(String numcta) {
        this.numcta = numcta;
    }
    
    public String getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getMov() {
        return tipo;
    }

    public void setMov(int tipo) {
        this.tipo = tipo;
    }

    public double getImporte() {
        return importe;
    }

    
    public void setImporte(double importe) {
        this.importe = importe;
    }
}
