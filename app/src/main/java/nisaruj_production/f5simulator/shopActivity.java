package nisaruj_production.f5simulator;

/**
 * Created by Nisaruj on 10/31/2015.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class shopActivity extends AppCompatActivity {
    private List<TextView> item;

    private static final int[] TEXT_IDS = {
            R.id.count1,
            R.id.count2,
            R.id.count3
    };
    private static final int[] BUTTON_IDS = {
            R.id.item1,
            R.id.item2,
            R.id.item3
    };

    private static final int[] BASE_COST = {10,50,100};
    private static final double[] item_rate = {0.1,0.5,1};
    private static final String[] item_name = {"Auto-Reload","Item 2","Item 3"};

    /** Item List
        - Auto-Reload
        - Computer
        - น้ำมนต์
        - วันหยุด
     */

    private static final int ARRAY_SIZE = 3;

    public int points = 0;
    public double rate = 0.0;

    public int[] item_count = new int[10];
    public int[] item_price = {10,50,100};



    //Update items' price
    private void setPrice() {
        //Set new price
        for(int i=0;i<ARRAY_SIZE;i++) item_price[i] = (int) (Math.pow(1.15,item_count[i])*(double)BASE_COST[i]);
    }

    //Round number to 2 decimal place
    private double round_to_2nd(double num) {
        return Math.round(num * 100.0)/100.0;
    }


    //Update score
    private void setPoints() {
        setPrice();
        //Set Progress
        ProgressBar progress = (ProgressBar)findViewById(R.id.progress);
        progress.setProgress(points);
        //Set Point
        TextView counter = (TextView)findViewById(R.id.counter);
        counter.setText(String.valueOf(points) + " Points");
        //Set Rate
        TextView rate_count = (TextView)findViewById(R.id.rate);
        rate_count.setText("Rate : " + String.valueOf(round_to_2nd(rate)));
        //Set item counter
        item = new ArrayList<TextView>(TEXT_IDS.length);
        int i=0;
        for(int id : TEXT_IDS) {
            if (i >= ARRAY_SIZE) break;
            TextView item_counter = (TextView)findViewById(id);
            item_counter.setText(String.valueOf(item_count[i++]));
        }

        //Set button text
        i=0;
        for(int id : BUTTON_IDS) {
            if (i >= ARRAY_SIZE) break;
            Button button = (Button)findViewById(id);
            button.setText(item_name[i] + " (" + String.valueOf(item_price[i]) + ")");
            i++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        for(int i=0;i<ARRAY_SIZE;i++) item_count[i] = 0;

        //Pass the values
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            points = bundle.getInt("points");
            rate = bundle.getDouble("rate");
            item_count = bundle.getIntArray("itemCount");
        }

        setPoints();

        //Declare Button
        Button back_button = (Button)findViewById(R.id.back_button);
        Button item1 = (Button)findViewById(R.id.item1);
        Button item2 = (Button)findViewById(R.id.item2);
        Button item3 = (Button)findViewById(R.id.item3);

        back_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(shopActivity.this, MainActivity.class);
                        intent.putExtra("points", points);
                        intent.putExtra("rate",rate);
                        intent.putExtra("itemCount", item_count);
                        startActivity(intent);
                        finish();

                    }
                }
        );

        //Item 1
        item1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (points >= item_price[0]) {
                            points -= item_price[0];
                            rate+=item_rate[0];
                            item_count[0]++;
                            setPoints();
                        }
                    }
                }
        );

        item2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (points >= item_price[1]) {
                            points -= item_price[1];
                            rate+=item_rate[1];
                            item_count[1]++;
                            setPoints();
                        }
                    }
                }
        );

        item3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (points >= item_price[2]) {
                            points -= item_price[2];
                            rate+=item_rate[2];
                            item_count[2]++;
                            setPoints();
                        }
                    }
                }
        );

    }



}
