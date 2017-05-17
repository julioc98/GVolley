package com.example.jc.gvolley;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;

    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        tv = (TextView)findViewById(R.id.textws);

    }

    public void invocar(View v){

        if(et.getText().length() != 0 &&
                !et.getText().
                        toString().equals("")){
            // FAZ O QUE DEVE SER FEITO
            StringRequest sr = new StringRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?q=" +
                            et.getText().toString()+ "&apikey=3d80b28f1ea87c8d694e37b32785e448",







                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // tratando resposta propriamente dita
                            // VORTA AQUI MAMYLUS HERECTUS
                            try {
                                JSONObject jsonobj =
                                        new JSONObject(response);

                                int temperatura =
                                        jsonobj.
                                                getJSONObject("main")
                                                .getInt("temp");

                                tv.setText("Temperatura eh: " + temperatura);

                                // por fim, disponibilizar a temperatura

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError
                                                            error) {
                            tv.setText("Deu zica don: " +
                                    error.getMessage());
                        }
                    }


            );


            rq = Volley.newRequestQueue(this);
            rq.add(sr);
        }
        else{
            Toast.makeText(this,
                    "enfia id de cidade", Toast.LENGTH_SHORT);
        }
    }
}