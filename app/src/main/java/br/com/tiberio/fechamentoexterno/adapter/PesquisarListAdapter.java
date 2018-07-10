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
 * Created by Vinicius.Dantas on 22/03/2018.
 */

public class PesquisarListAdapter extends ArrayAdapter<PesquisarList> {

        private List<PesquisarList> items;

        public PesquisarListAdapter(@NonNull Context context, int textViewResourceId, List<PesquisarList> items) {
            super( context, textViewResourceId, items );
            this.items = items;
        }


    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                Context ctx = getContext();
                LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate( R.layout.listview_item, null);
            }
            PesquisarList pesquisa = items.get(position);
            if (pesquisa != null) {

                String nomeusuario =String.valueOf(pesquisa.getusuario());
                String nomearquivo =String.valueOf(pesquisa.getnomearquivo());

                ((TextView) v.findViewById(R.id.nameLabel)).setText(nomearquivo);

            }
            return v;
        }


}
