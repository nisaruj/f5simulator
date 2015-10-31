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

public class shopActivity extends AppCompatActivity {
    public int points = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            points = bundle.getInt("points");
        }
        ProgressBar progress = (ProgressBar)findViewById(R.id.progress);
        progress.setProgress(points);
        TextView counter = (TextView)findViewById(R.id.counter);
        counter.setText(String.valueOf(points) + " Points");

        Button back_button = (Button)findViewById(R.id.back_button);

        back_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(shopActivity.this,MainActivity.class);
                        intent.putExtra("points",points);
                        startActivity(intent);
                        finish();

                    }
                }
        );

    }



}
