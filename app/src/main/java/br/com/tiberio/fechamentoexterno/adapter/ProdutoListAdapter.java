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
 * Created by Vinicius.Dantas on 09/03/2018.
 */

public class ProdutoListAdapter extends ArrayAdapter<ProdutoList> {

    private List<ProdutoList> items;

    public ProdutoListAdapter(@NonNull Context context, int textViewResourceId, List<ProdutoList> items) {
        super( context, textViewResourceId, items );
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            Context ctx = getContext();
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate( R.layout.modelo_listaprodutos, null);
        }
        ProdutoList produto = items.get(position);
        if (produto != null) {

            String nomeusuario =String.valueOf(produto.getusuario());
            String nomeproduto =String.valueOf(produto.getnomeproduto());

            ((TextView) v.findViewById(R.id.txtTitulo )).setText(nomeproduto);


        }
        return v;
    }

}
