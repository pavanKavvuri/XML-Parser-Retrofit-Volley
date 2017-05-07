package and.com.xmllistview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import and.com.xmllistview.Interface.GoogleClient;
import and.com.xmllistview.POJO.RSSFeed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetFeed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void GetFeed() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://vogella.com/")
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        GoogleClient call3 = retrofit.create(GoogleClient.class);
        Call<RSSFeed> call = call3.loadRSSFeed();


        call.enqueue(new Callback<RSSFeed>() {
            @Override
            public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
                Log.d("Hell", "Reached this place called Success");


                if (response.isSuccessful()) {
                    RSSFeed rss = response.body();
                    System.out.println("Channel title: " + rss.getChannelTitle());

                } else {
                    System.out.println(response.errorBody());
                }



            }

            @Override
            public void onFailure(Call<RSSFeed> call, Throwable t) {
                Log.d("Hell", "Reached this place called Failure");
            }
        });


    }
}


/*
* call3.enqueue(new retrofit2.Callback<RSSFeed>() {
            @Override
            public void onResponse(Call<RSSFeed> call, retrofit2.Response<RSSFeed> response) {
                Log.e("Res:", response.body().toString());

            }

            @Override
            public void onFailure(Call<RSSFeed> call, Throwable t) {
                Log.e("Err:", t.toString());
            }
        });
*
* 1*/