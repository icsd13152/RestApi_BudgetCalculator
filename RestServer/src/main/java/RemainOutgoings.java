

/**
 *
 * @author p.petropoulos
 */
public class RemainOutgoings {
    private int numOfDays;
    private double amount;
    
    public RemainOutgoings(){
        
    }

    public RemainOutgoings(int numOfDays, double amount) {
        this.numOfDays = numOfDays;
        this.amount = amount;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "RemainOutgoings{" + "numOfDays=" + numOfDays + ", amount=" + amount + '}';
    }
    
    
}
