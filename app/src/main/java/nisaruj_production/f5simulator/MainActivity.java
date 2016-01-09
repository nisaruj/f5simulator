package nisaruj_production.f5simulator;

/**
 * Created by Nisaruj on 09/31/2015.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public int count=0;
    public double rate=0;
    public int interval = 1000;
    public int[] item_count = new int[10];

    //Round number to 2 decimal place
    private double round_to_2nd(double num) {
        return Math.round(num * 100.0)/100.0;
    }

    //Update score
    public void setPoints() {
        //Set Progress
        ProgressBar progress = (ProgressBar)findViewById(R.id.progress);
        progress.setProgress(count);
        //Set Point
        TextView counter = (TextView)findViewById(R.id.counter);
        counter.setText(String.valueOf(count) + " Points");
        //Set Rate
        TextView rate_count = (TextView)findViewById(R.id.rate);
        rate_count.setText("Rate : " + String.valueOf(round_to_2nd(rate)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();

        for(int i=0;i<10;i++) item_count[i] = 0;

        if (bundle != null) {
            count = bundle.getInt("points");
            rate = bundle.getDouble("rate");
            item_count = bundle.getIntArray("itemCount");

        }
        if (rate == 0) interval = 0;
        else if (rate < 100)interval = (int)(1000/rate);
        else if (rate < 1000) interval = (int)(10000/rate);
        else if (rate < 10000) interval = (int)(100000/rate);

        //Button Declare
        ImageButton button = (ImageButton)findViewById(R.id.button);
        Button shop_button = (Button)findViewById(R.id.button_shop);


        setPoints();

        button.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) { //On click
                        count++;
                        setPoints();
                    }
                }
        );

        shop_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent shop = new Intent(MainActivity.this,shopActivity.class);
                        shop.putExtra("points",count);
                        shop.putExtra("itemCount",item_count);
                        shop.putExtra("rate", rate);
                        startActivity(shop);
                        finish();
                    }
                }
        );

        Timer T = new Timer();
        if (rate > 0) {
            T.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (rate < 100) count+=1;
                            else if (rate < 1000) count+=10;
                            else if (rate < 10000) count+=100;
                            setPoints();
                        }
                    });
                }
            }, interval, interval);
        }
    }


}
