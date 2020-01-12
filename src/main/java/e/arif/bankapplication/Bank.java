package e.arif.bankapplication;

public class Bank {
    String services;
    String debitAccount;
    String creditAccount;
    double transAmount;

    Client[] clients;
    int noc;
    final int MAX_NUM_CLIENTS = 6;
    boolean error;
    String errorMsg;

    Bank() {
        clients = new Client[MAX_NUM_CLIENTS];
        noc = 0;
    }

    void setTransAttribute(String services, String debitAccount, String creditAccount, double transAmount) {
        this.services = services;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.transAmount = transAmount;
    }

    void setError(String errorMsg) {
        this.error = true;
        this.errorMsg = errorMsg;
    }

    void resetError() {
        this.error = false;
        this.errorMsg = " ";
    }

    int indexOfClient(String name) {
        int index = -1;
        for (int i = 0; i < this.noc; i++) {
            if (clients[i].nameOfClient.equals(name)) {
                index = i;
            }
        }
        return index;
    }

    void addClient(Client c) {
        int index = indexOfClient(c.nameOfClient);
        if (index >= 0) {
            setError("Error: client " + c.nameOfClient + " already exists.");
        }
        else if (c.nameOfClient.equals("")){
            setError("Error: Name of Client cannot be empty.");
        }
        else if (this.noc == MAX_NUM_CLIENTS) {
            setError("Error: Maximum Number of Client Reached.");
        }
        else if (c.balanceOfClient == -1){
            setError("Error: Initial Balance cannot be empty or negative.");
        }
        else if (c.balanceOfClient <= 0) {
            setError("Error: Initial Balance must be positive.");
        }
        else {
            resetError();
            this.clients[noc] = c;
            noc++;
        }
    }

    void setDeposit(String services, String name, double transAmount) {
        this.services = services;
        this.transAmount = transAmount;
        this.creditAccount = name;
        int index = indexOfClient(name);
        if (index < 0) {
            setError("Error: client " + name + " does not exist.");
        }
        else if (transAmount <= 0) {
            setError("Error: Invalid amount");
        }
        else {
            resetError();
            if (services.equals("DEPOSIT")) {
                this.clients[index].balanceOfClient = this.clients[index].balanceOfClient + transAmount;
            }
        }
    }


    void setWithdraw(String services, String name, double transAmount) {
        this.services = services;
        this.debitAccount = name;
        this.transAmount = transAmount;
        int index = indexOfClient(name);
        if (index < 0) {
            setError("Error: client " + name + " does not exist.");
        } else if (transAmount <= 0) {
            setError("Error: Invalid amount");
        } else if (transAmount > this.clients[index].balanceOfClient) {
            setError("Error: Amount too large");
        } else {
            resetError();
            if (services.equals("WITHDRAW")) {
                this.clients[index].balanceOfClient = this.clients[index].balanceOfClient - transAmount;
            }
        }
    }

    void setTransfer(String services,String debitAccount,String creditAccount,double transAmount) {
        this.services = services;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.transAmount = transAmount;
        int index1 = indexOfClient(debitAccount);
        int index2 = indexOfClient(creditAccount);
        if (index1 < 0) {
            setError("Error: client " + debitAccount + " does not exist.");
        }
        else if (index2 < 0) {
            setError("Error: client " + creditAccount + " does not exist.");
        }
        else if (transAmount <= 0) {
            setError("Error: Invalid amount");
        }
        else if (transAmount > this.clients[index1].balanceOfClient) {
            setError("Error: Amount too large");
        }
        else {
            resetError();
            if (services.equals("TRANSFER")) {
                this.clients[index1].balanceOfClient = this.clients[index1].balanceOfClient - transAmount;
                this.clients[index2].balanceOfClient = this.clients[index2].balanceOfClient + transAmount;
            }
        }
    }

    void serveClients () {
        if (services.equals("DEPOSIT")) {
            setDeposit(services,creditAccount,transAmount);
        }
        else if  (services.equals("WITHDRAW")) {
            setWithdraw(services, debitAccount, transAmount);
        }
        else if (services.equals("TRANSFER")) {
            setTransfer(services, debitAccount, creditAccount, transAmount);
        }
    }

    @Override
    public String toString() {
        String s = "Updated Balances of Clients:";
        if (error) {
            s = errorMsg;
        }
        else {
            for (int i = 0; i < this.clients.length; i++) {
                if (this.clients[i] != null) {
                    s += "\n" + this.clients[i].nameOfClient + ": $"
                            + String.format("%.2f", this.clients[i].balanceOfClient);
                }
            }
        }
        return s;
    }
}
