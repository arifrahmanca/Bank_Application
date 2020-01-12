package e.arif.bankapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Bank bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bank = new Bank();
    }

    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    public void createAccountButtonClicked(View view) {
        String nameOfClient = getInputOfTextField(R.id.inputOfClientName);
        String balance = getInputOfTextField(R.id.inputOfClientBalance);
        double balanceOfClient;
        if (balance.equals("")){
            balanceOfClient = -1;
        }
        else {
            balanceOfClient = Double.parseDouble(balance);
        }

        Client c = new Client(nameOfClient,balanceOfClient);
        bank.addClient(c);
        setContentsOfTextView(R.id.display, bank.toString());
    }

    public void computeTransactionButtonClicked(View view) {
        String services = getItemSelected(R.id.services);
        String debitAccount = getInputOfTextField(R.id.debitAccount);
        String creditAccount = getInputOfTextField(R.id.creditAccount);
        String amount = getInputOfTextField(R.id.transAmount);
        double transAmount = Double.parseDouble(amount);

        bank.setTransAttribute(services,debitAccount,creditAccount,transAmount);
        bank.serveClients();
        setContentsOfTextView(R.id.display, bank.toString());
    }
}
