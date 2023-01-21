package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class Home extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;
    CardView carpenter,driver,electrician,tutor,Ladiestailor,laundry,painter,photographer,plumber,packandmove,mechanic,welding;

    private long b;
    private Toast bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnm);
        bottomNavigationView.setSelectedItemId(R.id.item1);
        carpenter=findViewById(R.id.carpenter);
        driver=findViewById(R.id.driver);
        electrician=findViewById(R.id.electrician);
        tutor=findViewById(R.id.tutor);
        Ladiestailor=findViewById(R.id.Ladiestailor);
        laundry=findViewById(R.id.laundry);
        painter=findViewById(R.id.painter);
        photographer=findViewById(R.id.photographer);
        plumber=findViewById(R.id.plumber);
        packandmove=findViewById(R.id.packandmove);
        mechanic=findViewById(R.id.mechanic);
        welding=findViewById(R.id.welding);

        carpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Carpenter.class);
                startActivity(i1);
            }
        });

        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Driver.class);
                startActivity(i1);
            }
        });

        electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Electrician.class);
                startActivity(i1);
            }
        });

        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Tutor.class);
                startActivity(i1);
            }
        });

        Ladiestailor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,LadiesTailor.class);
                startActivity(i1);
            }
        });

        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Laundry.class);
                startActivity(i1);
            }
        });

        painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Painter.class);
                startActivity(i1);
            }
        });

        photographer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Photographer.class);
                startActivity(i1);
            }
        });

        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Plumber.class);
                startActivity(i1);
            }
        });

        packandmove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Packersandmovers.class);
                startActivity(i1);
            }
        });

        mechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Mechanic.class);
                startActivity(i1);
            }
        });

        welding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,welding.class);
                startActivity(i1);
            }
        });


        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Mechanic", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Carpenter", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("Plumber", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Electrician", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        url_maps.put("Welding", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-3-jpg.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Mechanic",R.drawable.car_illu);
        file_maps.put("Carpenter",R.drawable.carpen);
        file_maps.put("Plumber",R.drawable.plu_ill);
        file_maps.put("Electrician", R.drawable.el);
        file_maps.put("Welding", R.drawable.welding_illu);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        return true;
                    case R.id.item2:
                        startActivity(new Intent(getApplicationContext(), bookslot.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item3:
                        startActivity(new Intent(getApplicationContext(), favorites.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item4:
                        startActivity(new Intent(getApplicationContext(), myaccount.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item5:
                        startActivity(new Intent(getApplicationContext(), myslots.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }
    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        String s=slider.getBundle().get("extra") + "";
        if(s.equals("Carpenter")) {
            Intent i1=new Intent(Home.this,Carpenter.class);
            startActivity(i1);
        }

        else  if(s.equals("Mechanic")) {
            Intent i1=new Intent(Home.this,Mechanic.class);
            startActivity(i1);
        }
        else  if(s.equals("Welding")) {
            Intent i1=new Intent(Home.this,welding.class);
            startActivity(i1);
        }
        else  if(s.equals("Plumber")) {
            Intent i1=new Intent(Home.this,Plumber.class);
            startActivity(i1);
        }
        else  if(s.equals("Electrician")) {
            Intent i1=new Intent(Home.this,Electrician.class);
            startActivity(i1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_custom_indicator:
                mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
                break;
            case R.id.action_custom_child_animation:
                mDemoSlider.setCustomAnimation(new ChildAnimationExample());
                break;
            case R.id.action_restore_default:
                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                break;
            case R.id.action_github:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia/AndroidImageSlider"));
                startActivity(browserIntent);
                break;
            case R.id.action_github2:
                Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia/AndroidImageSlider"));
                startActivity(browserIntent2);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
    @Override
    public void onBackPressed() {
        if(b+2000 > System.currentTimeMillis())
        {
            bt.cancel();
            super.onBackPressed();
            return;
        }
        else
        {
            bt=  Toast.makeText(getBaseContext(),"Press Again To Exit",Toast.LENGTH_SHORT);
            bt.show();
        }
        b=System.currentTimeMillis();
    }
}