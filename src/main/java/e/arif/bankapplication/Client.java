package e.arif.bankapplication;

public class Client {
    String nameOfClient;
    double balanceOfClient;

    Client(String name, double balance) {
        this.nameOfClient = name;
        this.balanceOfClient = balance;
    }

    void setBalance(double balance) {
        this.balanceOfClient = balance;
    }

    void setName(String name) {
        this.nameOfClient = name;
    }

    public String getName() {
        return nameOfClient;
    }

    public double getAmount() {
        return balanceOfClient;
    }

    @Override
    public String toString() {
        String s = "";

        s += "Updated Balances of Clients:";
        s += "\n" + this.nameOfClient + ": $" + String.format("%.2f", this.balanceOfClient);

        return s;
    }
}
