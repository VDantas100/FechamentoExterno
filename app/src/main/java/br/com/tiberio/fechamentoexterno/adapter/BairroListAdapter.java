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
 * Created by Vinicius.Dantas on 16/03/2018.
 */

public class BairroListAdapter extends ArrayAdapter<BairroList> {

    private List<BairroList> items;

    public BairroListAdapter(@NonNull Context context, int textViewResourceId, List<BairroList> items) {
        super( context, textViewResourceId, items );
        this.items = items;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            Context ctx = getContext();
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate( R.layout.modelo_listabairros, null);
        }
        BairroList bairro = items.get(position);
        if (bairro != null) {

            String nomeusuario =String.valueOf(bairro.getusuario());
            String nomelocalidade =String.valueOf(bairro.getlocalidade());
            String nomebairro =String.valueOf(bairro.getbairro());

            ((TextView) v.findViewById(R.id.txtBairro)).setText(nomebairro);


        }
        return v;
    }
}
