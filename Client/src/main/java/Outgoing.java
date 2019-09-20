
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
public class Outgoing {

    private Double amount;
    private String outDate;

    public Outgoing() {
    }

    public Outgoing(Double amount, String outgoingDate) {
        this.amount = amount;
        this.outDate = outgoingDate;

        sendRequest();

    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setOutgoingDate(String outgoingDate) {
        this.outDate = outgoingDate;
    }

    public Double getAmount() {
        return amount;
    }

    public String getOutgoingDate() {
        return outDate;
    }

    public void sendRequest() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/resources/insertOutgoing");

        String input = "{\"amount\":\"" + amount + "\",\"outgoingDate\":\"" + outDate + "\"}";

        Response res = target.request(MediaType.APPLICATION_JSON).post(Entity.json(input));
        if (res.getStatus() != 201) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());

        } else {
            String result = res.readEntity(String.class);
            updateIncome(input);

        }

    }

    public void getRequest() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/resources/getOutgoings");

        Response res = target.request(MediaType.APPLICATION_JSON).get();
        if (res.getStatus() != 200) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }

    }

    public void updateIncome(String input) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/resources/updateIncome");
        input = "{\"amount\":\"" + amount + "\",\"outgoingDate\":\"" + outDate + "\"}";
        Response res = target.request(MediaType.APPLICATION_JSON).put(Entity.json(input));
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
        return "Outgoing{" + "amount=" + amount + ", outgoingDate=" + outDate + '}';
    }

}
