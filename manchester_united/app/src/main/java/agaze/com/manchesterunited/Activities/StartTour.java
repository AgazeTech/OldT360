package agaze.com.manchesterunited.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import agaze.com.manchesterunited.R;

public class StartTour extends AppCompatActivity {

    Button m_startTour, m_buyTickets;
    Intent m_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tour);

        m_startTour = (Button) findViewById(R.id.tour_guide);
        m_buyTickets = (Button) findViewById(R.id.buy_tickets);


        m_startTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_intent = new Intent(getApplicationContext(), TourTheme.class);
                startActivity(m_intent);
            }
        });

        m_buyTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_intent = new Intent(getApplicationContext(), BuyTickets.class);
                startActivity(m_intent);
            }
        });


    }
}
