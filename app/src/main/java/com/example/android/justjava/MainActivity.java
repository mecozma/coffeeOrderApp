/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

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
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        displayQuantity(numberOfCoffees);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     *
     *@return total price
     */
    private int calculatePrice() {

        int price = numberOfCoffees * 5;
        return price;
    }

    /**
     *  Order summary method
     *
     *  @param price
     */

    private String createOrderSummary (int price) {
        String priceMessage = "Name: Kaptain Kunal" +
                                    "\nQuantity: " + numberOfCoffees +
                                    "\nTotal: " + numberOfCoffees * 5 +
                                    "\nThank you!";
        return priceMessage;
    }

    /**
     * This method increments the number of coffees to be ordered
     */
    public void increment(View view) {

        numberOfCoffees = numberOfCoffees + 1;
        displayQuantity(numberOfCoffees);
    }

    /**
     * This method decrements the number of coffees to be ordered
     */
    public void decrement(View view) {

        numberOfCoffees = numberOfCoffees - 1;
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

    /**
     * This method displays the given price on the screen.
     *
     * @param message
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}