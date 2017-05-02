package com.example.elyervesson.aulaandroid_asynclv;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by elyervesson on 02/05/17.
 */
                         // Parametro de entrada doInBackGround
                         // Parametro de entrada do onProgressUpdate
                         // Valor de saida do doInBackGround e parametro de entrada do onPostExecute
public class Tarefa extends AsyncTask<String, String, Bitmap>{
    private ProgressDialog progressDialog;
    private Context context;
    private TarefaInterface tarefaInterface;

    public Tarefa(Context context, TarefaInterface tarefaInterface) {
        this.context = context;
        this.tarefaInterface = tarefaInterface;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Carregando imagem!");
        progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap img = null;

        try{
            publishProgress("Ainda Carregando imagem!");
            URL url = new URL(params[0]);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream input = conexao.getInputStream();
            img = BitmapFactory.decodeStream(input);
            publishProgress("Imagem carregada!");
        }
        catch(IOException e){}

        return img;
    }

    @Override
    protected void onProgressUpdate(String... params) {
        progressDialog.setMessage(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap params) {
        tarefaInterface.depoisDownload(params);
        progressDialog.dismiss();
    }


}
