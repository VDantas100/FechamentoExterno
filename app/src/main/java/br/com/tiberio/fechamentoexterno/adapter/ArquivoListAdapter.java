package br.com.tiberio.fechamentoexterno.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.tiberio.fechamentoexterno.R;

/**
 * Created by Vinicius.Dantas on 12/03/2018.
 */

public class ArquivoListAdapter extends ArrayAdapter<ArquivoList> {

    private List<ArquivoList> items;

    public ArquivoListAdapter(@NonNull Context context, int textViewResourceId, List<ArquivoList> items) {
        super( context, textViewResourceId, items );
        this.items = items;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            Context ctx = getContext();
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate( R.layout.modelo_listaarquivos, null);
        }
        ArquivoList arquivo = items.get(position);
        if (arquivo != null) {

            String nomeusuario =String.valueOf(arquivo.getusuario());
            String nomeproduto =String.valueOf(arquivo.getnomeproduto());
            String tpdocumento =String.valueOf(arquivo.getTipodocumento());
            String nomearquivo =String.valueOf(arquivo.getnomearquivo());

            ((TextView) v.findViewById(R.id.TituloArquivo)).setText(nomearquivo);

        }
        return v;
    }


}
