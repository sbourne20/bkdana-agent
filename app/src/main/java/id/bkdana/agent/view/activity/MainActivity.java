package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import id.bkdana.agent.R;
import id.bkdana.agent.view.fragment.fragment_dashboard;
import id.bkdana.agent.view.fragment.fragment_profile;
import id.bkdana.agent.view.fragment.fragment_scanbarcode;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation =  findViewById(R.id.navigation_main_menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new fragment_dashboard());
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    fragment = new fragment_dashboard();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_listsurvey:
                    Intent menuListSurvey = new Intent(MainActivity.this,ListSurveyActivity.class);
                    startActivity(menuListSurvey);
                    return true;
                case R.id.navigation_scanbarcode:
                    Intent menuScan = new Intent(MainActivity.this,DetailScanBarcodeActivity.class);
                    startActivity(menuScan);
                    return true;
                case R.id.navigation_profile:
                    fragment = new fragment_profile();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main_menu, fragment);
        transaction.commit();


    }
}
