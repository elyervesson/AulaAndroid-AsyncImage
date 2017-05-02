package com.example.elyervesson.aulaandroid_asynclv;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements TarefaInterface {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void baixarImagem(View view){
        // Contexto, Classe que implementa o TarefaInterface
        Tarefa tarefa = new Tarefa(this,this);
        tarefa.execute("http://www.thiengo.com.br/img/system/logo/thiengo-80-80.png");

    }

    @Override
    public void depoisDownload(Bitmap bitmap) {
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }

//    // MODO ANTIGO (ABRINDO THREAD)
//    public void baixarImagem(View view){
//        // É NECESSARIO COLOCAR FINAL PARA PODER ACESSAR DENTRO DA THREAD
//        // Similar ao onPreExecute
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Carregando imagem!");
//        progressDialog.show();
//
//        // Similar ao doInBackground
//        new Thread(){
//            public void run(){
//                Bitmap img = null;
//
//                try{
//                    URL url = new URL("http://www.thiengo.com.br/img/system/logo/thiengo-80-80.png");
//                    HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
//                    InputStream input = conexao.getInputStream();
//                    img = BitmapFactory.decodeStream(input);
//                }
//                catch(IOException e){}
//
//                // Similar ao onPostExecute
//                final Bitmap imgAux = img;
//                runOnUiThread(new Runnable(){
//                    public void run(){
//                        progressDialog.setMessage("Imagem carregada!");
//                        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//                        imageView.setImageBitmap(imgAux);
//                        progressDialog.dismiss();
//                    }
//                });
//            }
//        }.start();
//    }

}
