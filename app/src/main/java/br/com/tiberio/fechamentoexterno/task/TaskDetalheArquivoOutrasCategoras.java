package br.com.tiberio.fechamentoexterno.task;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

/**
 * Created by Vinicius.Dantas on 21/03/2018.
 */

public class TaskDetalheArquivoOutrasCategoras extends AsyncTask <String,String,List<String>>{

    Context context;


    public TaskDetalheArquivoOutrasCategoras(Context context) {
        this.context = context;
    }

    @Override
    protected List<String> doInBackground(String... strings) {

        Log.i( "taskoc: " + "teste execução", "doInBackground: " + "teste execução" );

        try {

            String parametro = strings[0];

            // Cria um StringTokenizer para separar valores
            StringTokenizer st = new StringTokenizer(parametro.toString());

            String usuario = st.nextToken("#");
            String tipodocumento1 = st.nextToken("#");
            String tipodocumento2 = st.nextToken("#");
            String nomearquivo = st.nextToken("#");


            // Verifica o próximo token
            Log.i( "usuario: " + usuario, "doInBackground: " + usuario );
            Log.i( "tipodocumento1: " + tipodocumento1, "doInBackground: " + tipodocumento1);
            Log.i( "tipodocumento2: " + tipodocumento2, "doInBackground: " + tipodocumento2);
            Log.i( "nomearquivo: " + nomearquivo, "doInBackground: " + nomearquivo);

            List<String> taskretorno = new ArrayList<String>();
            WebServiceSoap wssoap =  new WebServiceSoap();
            Object res;
            //Chama o serviço Domino para Retornar Detalhe de Arquivos Via Menu Outras Categorias
            res = wssoap.RetornarDetalheArquivosOC(usuario,tipodocumento1,tipodocumento2,nomearquivo);
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
}
