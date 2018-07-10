package br.com.tiberio.fechamentoexterno.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import br.com.tiberio.fechamentoexterno.R;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

/**
 * Created by Vinicius.Dantas on 23/03/2018.
 */

public class TaskPesquisarArquivos extends AsyncTask<String,String,List<String>> {
    Context context;
    ProgressDialog progressDialog;


    public TaskPesquisarArquivos(Context context) {

        this.context = context;

    }

    @Override
    protected List<String> doInBackground(String... strings) {
        try {

            String parametro = strings[0];

            // Cria um StringTokenizer para separar valores
            StringTokenizer st = new StringTokenizer(parametro.toString());

            String usuario = st.nextToken("#");
            String Filtro = st.nextToken("#");

            // Verifica o próximo token
            Log.i( "usuario: " + usuario, "doInBackground: " + usuario );
            Log.i( "Filtro: " + Filtro, "doInBackground: " + Filtro);


            List<String> taskretorno = new ArrayList<String>();
            WebServiceSoap wssoap =  new WebServiceSoap();
            Object res;
            //Chama o serviço Domino para Retornar Lista de Arquivos Pesquisados


            res = wssoap.RetornarPesquisaArquivos(usuario,Filtro );
            List<String> listadados = (List<String>) res;

            return listadados;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERRO-XmlPull", e.toString());
            return null;
        }
    }


    @Override
    protected void onPreExecute() {

        progressDialog = new ProgressDialog(context , R.style.MyAlertDialogStyle);
        progressDialog.setTitle("Fechamento Externo");
        progressDialog.setMessage("Pesquisando Arquivos, por favor, aguarde alguns instantes.");
        progressDialog.setIndeterminate( true );
        progressDialog.setCancelable( false );
        progressDialog.show();
        super.onPreExecute();

    }


    @Override
    protected void onPostExecute(List<String> strings) {
        handler.postDelayed(runnable, 1000);
        super.onPostExecute( strings );

    }

    //Cancelar Tela
    private Handler handler  = new Handler();
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {

            if (progressDialog != null){
                progressDialog.cancel();
            }

        }


    };
}
