package com.example.semana03_2021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.semana03_2021.api.ServiceApi;
import com.example.semana03_2021.entity.User;
import com.example.semana03_2021.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
        ArrayList<String> data=new ArrayList<String>();
        ListView lstUsuario = null;
        ArrayAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstUsuario=findViewById(R.id.idListUsuarios);
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lstUsuario.setAdapter(adapter);

    cargaData();

    }

    public void cargaData(){
    //llamada de coneccion
        ServiceApi api= ConnectionRest.getConnection().create(ServiceApi.class);
        //acceso de metodo

        Call<List<User>> call=        api.listaUsuario();


                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        mensaje("-->"+response.isSuccessful());
                        mensaje("-->"+response.body());

                        if(response.isSuccessful()){
                            List<User> user=response.body();
                            for (User x:user){
                                data.add(x.getName());
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        mensaje(t.getMessage());
                        t.fillInStackTrace();
                    }
                });
    }
    public void mensaje(String msg){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setMessage(msg);

        alert.show();

    }
}