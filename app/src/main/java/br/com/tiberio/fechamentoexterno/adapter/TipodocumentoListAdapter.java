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

public class TipodocumentoListAdapter  extends ArrayAdapter<TipodocumentoList> {

    private List<TipodocumentoList> items;



    public TipodocumentoListAdapter(@NonNull Context context, int textViewResourceId, List<TipodocumentoList> items) {
        super( context, textViewResourceId, items );
        this.items = items;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            Context ctx = getContext();
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate( R.layout.modelo_listatipodocumentos, null);
        }
        TipodocumentoList tipodocumento = items.get(position);
        if (tipodocumento != null) {

            String nomeusuario =String.valueOf(tipodocumento.getusuario());
            String nomeproduto =String.valueOf(tipodocumento.getnomeproduto());
            String tpdocumento =String.valueOf(tipodocumento.getTipodocumento());

            //((TextView) v.findViewById(R.id.lblvalorseparado)).setText(valor1);
            //((TextView) v.findViewById(R.id.txtTituloProduto)).setText(nomeproduto);
            ((TextView) v.findViewById(R.id.txtTipoDocumento)).setText(tpdocumento);

            //Monta campos Resumo de Conta
            // Total da Conta
            //((TextView) v.findViewById(R.id.txtTotalConta)).setText(valor3);
            // Total a Pagar
            //TextView) v.findViewById(R.id.txtTotalaPagar)).setText(valor4);
            // Total de Adiantamento
            //((TextView) v.findViewById(R.id.txtTotalAdiantamento)).setText(valor4);
            // Total ja Pago
            // ((TextView) v.findViewById(R.id.txtTotalPago)).setText(valor5);
            //

            // ((ImageView) v.findViewById(R.id.imgContatoPhone )).setImageResource(conta.getIdImagem());
        }
        return v;
    }

}
