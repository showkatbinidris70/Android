package com.coderbd.restapicall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderbd.restapicall.entity.Greeting;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class BasicActity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_actity);
    }

    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment(){

        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Greeting>{

        @Override
        protected Greeting doInBackground(Void... voids) {
            try {
                final String url="http://rest-service.guides.spring.io/greeting";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Greeting greeting = restTemplate.getForObject(url, Greeting.class);
                return greeting;
            }catch (Exception e){
                Log.e("MainActivity", e.getMessage(),e);
            }
            return null;
        }

        protected  void onPostExcute(Greeting greeting){
            TextView greetingText = (TextView) findViewById(R.id.id_value);
            TextView greetingContentText = (TextView)findViewById(R.id.content_value);
            greetingIdText.setText(greeting.getId());
            greeting
        }
    }
}
