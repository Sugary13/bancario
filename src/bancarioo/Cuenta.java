
package bancarioo;
public class Cuenta {

    /**
     * @param saldoAnt the saldoAnt to set
     */
    public void setSaldoAnt(double saldoAnt) {
        this.saldoAnt = saldoAnt;
    }

    /**
     * @param depositos the depositos to set
     */
    public void setDepositos(double depositos) {
        this.depositos = depositos;
    }

    /**
     * @param retiros the retiros to set
     */
    public void setRetiros(double retiros) {
        this.retiros = retiros;
    }
    private String numcta;
    private double saldoAnt;
    private double depositos;
    private double retiros;
    private int id;
    private double interes;
    public Cuenta() {
        
    }
    public double getSaldo() {
        return getSaldoAnt()+getDepositos()-getRetiros();
    }
    public void depositar(double cantidad){
        setDepositos(depositos + cantidad);
    }
    
    public void retirar(double cantidad) throws SaldoException{
        if (getSaldo() < cantidad){
            throw new SaldoException("saldo insuficiente");
        }
        else {
            setRetiros(retiros + cantidad);        
             }
        }
    
   
    public String getNumcta() {
        return numcta;
    }
    
    

    /**
     * @param numcta the numcta to set
     */
    public void setNumcta(String numcta) {
        this.numcta = numcta;
    }

    /**
     * @return the saldoAnt
     */
    public double getSaldoAnt() {
        return saldoAnt;
    }

    /**
     * @return the depositos
     */
    public double getDepositos() {
        return depositos;
    }

    /**
     * @return the retiros
     */
    public double getRetiros() {
        return retiros;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the interes
     */
    public double getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(double interes) {
        this.interes = interes;
    }
}
