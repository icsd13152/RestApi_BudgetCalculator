
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author p.petropoulos
 */
public class Income {
    
    private Double amount;
    private String inDate;

    public Income() {
    }

    public Income(Double amount, String date) {
        this.amount = amount;
        this.inDate = date;
        sendRequest();

    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setIncomeDate(String date) {
        this.inDate = date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getIncomeDate() {
        return inDate;
    }

    public void sendRequest() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/resources/insertIncome");

        String input = "{\"amount\":\"" + amount + "\",\"date\":\"" + inDate + "\"}";

        Response res = target.request(MediaType.APPLICATION_JSON).post(Entity.json(input));
        if (res.getStatus() != 201) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());

        } else {
            String result = res.readEntity(String.class);
            
            System.out.println(result);

        }

    }
    
   
    
    @Override
    public String toString() {
        return "Income{" + "amount=" + amount + ", inComeDate=" + inDate + '}';
    }
}
