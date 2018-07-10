package br.com.tiberio.fechamentoexterno.task;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

/**
 * Created by Vinicius.Dantas on 15/03/2018.
 */

public class TaskDetalheArquivoEmp extends AsyncTask <String,String,List<String>> {

    Context context;

    public TaskDetalheArquivoEmp(Context context) {
        this.context = context;
    }

    @Override
    protected List<String> doInBackground(String... strings) {

        try {

            String parametro = strings[0];


            // Cria um StringTokenizer para separar valores
            StringTokenizer st = new StringTokenizer(parametro.toString());

            String usuario = st.nextToken("#");
            String nomeproduto = st.nextToken("#");
            String tipodocumento = st.nextToken("#");
            String nomearquivo = st.nextToken("#");

            // Verifica o próximo token
            Log.i( "usuario: " + usuario, "doInBackground: " + nomeproduto );
            Log.i( "nomeproduto: " + nomeproduto, "doInBackground: " + nomeproduto);
            Log.i( "tipodocumento: " + tipodocumento, "doInBackground: " + tipodocumento);
            Log.i( "nomearquivo: " + nomearquivo, "doInBackground: " + nomearquivo);

            List<String> taskretorno = new ArrayList<String>();
            WebServiceSoap wssoap =  new WebServiceSoap();
            Object res;
            //Chama o serviço Domino para Retornar Detalhe de Arquivos Via Menu Emprendimentos
            res = wssoap.RetornarDetalheArquivosEmp(usuario,nomeproduto,tipodocumento,nomearquivo);
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
        super.onPreExecute();

    }


    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute( strings );

    }



    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate( values );
    }
}
