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
 * Created by Vinicius.Dantas on 26/03/2018.
 */

public class TaskDetalheAquivoPesquisar extends AsyncTask <String,String,List<String>> {

    Context context;
    ProgressDialog progressDialog;


    public TaskDetalheAquivoPesquisar(Context context) {
        this.context = context;
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        try {

            String parametro = strings[0];

            //Thread.sleep(3000);

            // Cria um StringTokenizer para separar valores
            StringTokenizer st = new StringTokenizer(parametro.toString());

            String usuario = st.nextToken("#");
            String nomearquivo = st.nextToken("#");

            // Verifica o próximo token
            Log.i( "usuario: " + usuario, "doInBackground: " + usuario );
            Log.i( "nomearquivo: " + nomearquivo, "doInBackground: " + nomearquivo);

            List<String> taskretorno = new ArrayList<String>();
            WebServiceSoap wssoap =  new WebServiceSoap();
            Object res;
            //Chama o serviço Domino para Retornar Detalhe de Arquivos Via Menu Pesquisar

            handler.postDelayed(runnable, 1000);
            res = wssoap.RetornarDetalheArquivosPesquisar(usuario,nomearquivo);
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
        progressDialog.setMessage("Abrindo Arquivo, por favor, aguarde alguns instantes.");
        progressDialog.setIndeterminate( true );
        progressDialog.setCancelable( false );
        progressDialog.show();

        super.onPreExecute();

    }


    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute( strings );

    }

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
