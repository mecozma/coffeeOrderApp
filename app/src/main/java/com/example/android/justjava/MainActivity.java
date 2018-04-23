/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {



        //Gets the name of the customer
        EditText customerName = (EditText) findViewById(R.id.name_field);
        String getCustomerName = customerName.getText().toString();
        Log.v("Main", "Customer name is: " + getCustomerName);

        //State of Whipped cream topping checkbox
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhippedCream = whippedCream.isChecked();

        //State of Chocolate topping checkbox
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        Boolean hasChocolate = chocolate.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, getCustomerName);

        //Sends order to email

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for: " + getCustomerName);
            intent.putExtra(Intent.EXTRA_TEXT,priceMessage );
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }



    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream Adds 1$ if the user adds whipped cream topping
     * @param hasChocolate    Adds 2$ if the user adds chocolate topping
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {

        int basePrice = 5;

        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }

        return numberOfCoffees * basePrice;
    }

    /**
     * Order summary method
     *
     * @param price
     * @param hasWhippedCream
     * @param hasChocolate
     * @param getCustomerName
     */

    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String getCustomerName) {
        String priceMessage = getString(R.string.name, getCustomerName) +
                getString(R.string.whipped_cream_jf, hasWhippedCream) +
                getString(R.string.chocolate_jf, hasChocolate) +
                getString(R.string.quantity_jf, numberOfCoffees) +
                getString(R.string.total) + calculatePrice(hasWhippedCream, hasChocolate) +
                getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method increments the number of coffees to be ordered
     */
    public void increment(View view) {

        if (numberOfCoffees > 100) {
            numberOfCoffees = 100;
            Toast.makeText(this, "You can't order more than 100 coffees",
                    Toast.LENGTH_SHORT).show();
        }

        numberOfCoffees = numberOfCoffees + 1;

        displayQuantity(numberOfCoffees);
    }

    /**
     * This method decrements the number of coffees to be ordered
     */
    public void decrement(View view) {

        numberOfCoffees = numberOfCoffees - 1;

        if (numberOfCoffees < 1) {
            numberOfCoffees = 1;
            Toast.makeText(this, "You can't order less than one coffee!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        displayQuantity(numberOfCoffees);
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param numberOfCoffees number of the coffees to be ordered
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


}