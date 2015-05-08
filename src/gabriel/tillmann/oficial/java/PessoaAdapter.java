package gabriel.tillmann.oficial.java;

import gabriel.tillmann.oficial.android.R;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PessoaAdapter extends BaseAdapter{

	private ArrayList<Pessoa> arrPessoas;
	private Context           contexto;
	
	public PessoaAdapter(Context contexto, ArrayList<Pessoa> arr) {
		arrPessoas = arr;
		this.contexto = contexto;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrPessoas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arrPessoas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arrPessoas.get(arg0).hashCode();
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
		
		View linha = inflater.inflate( R.layout.linhalista, arg2, false );

		TextView id     = (TextView) linha.findViewById (R.id.linha_id);
		TextView nome     = (TextView) linha.findViewById (R.id.linha_nome);
		ImageView estrela = (ImageView) linha.findViewById(R.id.linha_estrela);
		TextView compl = (TextView) linha.findViewById (R.id.linha_complem);
		
		id   .setText(arrPessoas.get(arg0).getID());
		nome .setText(arrPessoas.get(arg0).getNome());
		compl.setText(arrPessoas.get(arg0).getEmail() + " - " + arrPessoas.get(arg0).getTelFixo() + " - " + arrPessoas.get(arg0).getTelCelular());
		
		if(arrPessoas.get(arg0).getSexo() == 'f') {
			estrela.setImageResource(android.R.drawable.btn_star_big_off);
		}
		else{
			estrela.setImageResource(android.R.drawable.btn_star_big_on);
		}
		return linha; 
	}

}
