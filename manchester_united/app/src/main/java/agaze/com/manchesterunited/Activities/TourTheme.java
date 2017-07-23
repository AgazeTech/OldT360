package agaze.com.manchesterunited.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import agaze.com.manchesterunited.R;

public class TourTheme extends AppCompatActivity {

    RelativeLayout theme1,theme2,theme3;
    Intent intent;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_theme);

        theme1 = (RelativeLayout) findViewById(R.id.theme1);
        theme2 = (RelativeLayout) findViewById(R.id.theme2);
        theme3 = (RelativeLayout) findViewById(R.id.theme3);
        back = (ImageButton) findViewById(R.id.back);

        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), TourMain.class);
                intent.putExtra("selected","theme1");
                //m_intent.putExtra("activity","TourTheme");
                startActivity(intent);
            }
        });

        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), TourMain.class);
                intent.putExtra("selected","theme2");
                //m_intent.putExtra("activity","TourTheme");
                startActivity(intent);
            }
        });

        theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), TourMain.class);
                intent.putExtra("selected","theme3");
               // m_intent.putExtra("activity","TourTheme");
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
