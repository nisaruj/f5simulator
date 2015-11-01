package nisaruj_production.f5simulator;

/**
 * Created by Nisaruj on 09/31/2015.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public int count=0;
    public int[] item_count = new int[10];
    //public ProgressBar progress = (ProgressBar)findViewById(R.id.progress);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            count = bundle.getInt("points");
            item_count = bundle.getIntArray("itemCount");
        }

        //Button Declare
        Button button = (Button)findViewById(R.id.button);
        Button shop_button = (Button)findViewById(R.id.button_shop);

        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);


        TextView label1 = (TextView)findViewById(R.id.counter);
        progress.setProgress(count);

        label1.setText(String.valueOf(count) + " Points"); //Set score to 0 point

        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) { //On click
                        TextView label1 = (TextView) findViewById(R.id.counter);
                        label1.setText(String.valueOf(++count) + " Points"); // +1 Point
                        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
                        progress.setProgress(count);
                        //count++;
                    }
                }
        );

        shop_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent shop = new Intent(MainActivity.this,shopActivity.class);
                        shop.putExtra("points",count);
                        shop.putExtra("itemCount",item_count);
                        startActivity(shop);
                        finish();
                    }
                }
        );


    }
}
