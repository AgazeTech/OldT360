package agaze.com.manchesterunited.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import agaze.com.manchesterunited.R;

public class TourMain extends AppCompatActivity {

    Intent m_intent;
    String m_selectedTheme;
    TextView m_welcomeTxt, m_subtitle, m_time, m_distance, m_sights;
    RelativeLayout m_wrapper;
    ImageButton m_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_main);

        m_welcomeTxt = (TextView) findViewById(R.id.welcome_txt);
        m_subtitle = (TextView) findViewById(R.id.welcome_tour);
        m_time = (TextView) findViewById(R.id.time_1);
        m_distance = (TextView) findViewById(R.id.dist_1);
        m_sights = (TextView) findViewById(R.id.sight_1);
        m_back = (ImageButton) findViewById(R.id.back);
        m_wrapper = (RelativeLayout) findViewById(R.id.wrapper);

        m_intent = getIntent();
        m_selectedTheme = m_intent.getStringExtra("selected");
        Log.i("selectedtheme", m_selectedTheme);

        if (m_selectedTheme.equals("theme1")){
            m_welcomeTxt.setText(R.string.red_devils);
            m_subtitle.setText(R.string.ardent);
            m_time.setText("90");
            m_distance.setText("2.2");
            m_sights.setText("32");
        }else if (m_selectedTheme.equals("theme2")){
            m_welcomeTxt.setText(R.string.history);
            m_subtitle.setText(R.string.rich);
            m_time.setText("60");
            m_distance.setText("1.7");
            m_sights.setText("17");
        }else if (m_selectedTheme.equals("theme3")){
            m_welcomeTxt.setText(R.string.complete);
            m_subtitle.setText(R.string.everything);
            m_time.setText("60");
            m_distance.setText("1.8");
            m_sights.setText("17");
        }

        m_wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(m_intent);
            }
        });

        m_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
