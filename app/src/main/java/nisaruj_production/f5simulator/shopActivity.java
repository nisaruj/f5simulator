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
    private static final int[] BUTTON_IDS = {
            R.id.count1,
            R.id.count2
    };

    public int points = 0;

    public int[] item_count = new int[10];
    public int[] item_price = {10,50};

    //Update score
    public void setPoints() {
        //Set Progress
        ProgressBar progress = (ProgressBar)findViewById(R.id.progress);
        progress.setProgress(points);
        //Set Point
        TextView counter = (TextView)findViewById(R.id.counter);
        counter.setText(String.valueOf(points) + " Points");
        //Set item counter
        item = new ArrayList<TextView>(BUTTON_IDS.length);
        int i=0;
        for(int id : BUTTON_IDS) {
            TextView item_counter = (TextView)findViewById(id);
            item_counter.setText(String.valueOf(item_count[i++]));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        //Pass the values
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            points = bundle.getInt("points");
            item_count = bundle.getIntArray("itemCount");
        }

        setPoints();

        Button back_button = (Button)findViewById(R.id.back_button);
        Button item1 = (Button)findViewById(R.id.item1);
        Button item2 = (Button)findViewById(R.id.item2);

        back_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(shopActivity.this, MainActivity.class);
                        intent.putExtra("points", points);
                        intent.putExtra("itemCount", item_count);
                        startActivity(intent);
                        finish();

                    }
                }
        );

        item1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (points >= item_price[0]) {
                            points -= item_price[0];
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
                            item_count[1]++;
                            setPoints();
                        }
                    }
                }
        );

    }



}
