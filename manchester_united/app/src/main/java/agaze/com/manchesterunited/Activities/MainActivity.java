package agaze.com.manchesterunited.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.List;

import agaze.com.manchesterunited.R;


public class MainActivity extends AppCompatActivity  {

    RelativeLayout m_info_layout, m_map_layout, m_cam_layout;

    ImageButton m_info, m_map, m_cam, m_back;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_info_layout = (RelativeLayout) findViewById(R.id.info_layout);
        m_map_layout = (RelativeLayout) findViewById(R.id.map_layout);
        m_cam_layout = (RelativeLayout) findViewById(R.id.cam_layout);

        m_info = (ImageButton) findViewById(R.id.info);
        m_map = (ImageButton) findViewById(R.id.map);
        m_cam = (ImageButton) findViewById(R.id.ar_cam);
        m_back = (ImageButton) findViewById(R.id.back);

        m_info_layout.setVisibility(View.GONE);
        m_cam_layout.setVisibility(View.GONE);

        m_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_cam_layout.setVisibility(View.GONE);
                m_map_layout.setVisibility(View.GONE);
                m_info_layout.setVisibility(View.VISIBLE);
            }
        });

        m_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_cam_layout.setVisibility(View.GONE);
                m_map_layout.setVisibility(View.VISIBLE);
                m_info_layout.setVisibility(View.GONE);
            }
        });

        m_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_cam_layout.setVisibility(View.VISIBLE);
                m_map_layout.setVisibility(View.GONE);
                m_info_layout.setVisibility(View.GONE);
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
