package gabriel.tillmann.oficial.android;

import gabriel.tillmann.oficial.android.R;
import gabriel.tillmann.oficial.java.Pessoa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Tela2 extends Activity {

	private TextView m_id;
	private TextView m_nome;
	private TextView m_cpf;
	private TextView m_email;
	private TextView m_telfixo;
	private TextView m_telcel;
	private RadioGroup m_radioGroup;
	private RadioButton m_sexoMasculino;
	private RadioButton m_sexoFeminino;
	
	private int m_iPosicao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela2);
		
		m_iPosicao = 0;
		
		m_id = (TextView) findViewById(R.id.t2_id);
		m_id.setEnabled(false);
		m_nome = (TextView) findViewById(R.id.t2_nome);
		m_cpf = (TextView) findViewById(R.id.t2_cpf);
		m_email = (TextView) findViewById(R.id.t2_email);
		m_telfixo = (TextView) findViewById(R.id.t2_telfixo);
		m_telcel = (TextView) findViewById(R.id.t2_telcel);
		m_radioGroup = (RadioGroup) findViewById(R.id.t2_sexoGroup);
		m_sexoMasculino = (RadioButton) findViewById(R.id.t2_masculino);
		m_sexoFeminino = (RadioButton) findViewById(R.id.t2_feminino);
		
		int iRequestCode = getIntent().getIntExtra("requestCode", Tela1.NOVO_CONTATO);
		
		if(iRequestCode == Tela1.NOVO_CONTATO){
			setTitle("Novo Contato");
		}
		else if (iRequestCode == Tela1.EDITAR_CONTATO) {
		
			Pessoa pessoa = (Pessoa) getIntent().getExtras().getSerializable("objeto");
			
			setTitle("Editar Contato ("+pessoa.getNome()+")");
				
			m_iPosicao = getIntent().getExtras().getInt("posicao");
			
			m_id    .setText(pessoa.getID());
			m_nome  .setText(pessoa.getNome());
			m_cpf   .setText(pessoa.getCPF());
			m_email .setText(pessoa.getEmail());
			m_telfixo.setText(pessoa.getTelFixo());
			m_telcel.setText(pessoa.getTelCelular());
			
			if (pessoa.getSexo() == 'm')
			{	
				m_sexoMasculino.setChecked(true);
				m_sexoFeminino.setChecked(false);
			}
			else
			{
				m_sexoFeminino.setChecked(true);
				m_sexoMasculino.setChecked(false);
			}	
			
		}
		
	}
	
	public void onSalvar(View v) {
		Intent salvar = new Intent();
		setResult(Tela1.S_OK, salvar);
		
		Pessoa pessoa = new Pessoa(m_id     .getText().toString(),
						   m_nome   .getText().toString(),
						   m_cpf    .getText().toString(),
						   m_email  .getText().toString(),
						   m_telfixo.getText().toString(),
						   m_telcel .getText().toString(),
						   m_radioGroup.getCheckedRadioButtonId() == R.id.t2_masculino ? 'm' : 'f');
		
		salvar.putExtra("posicao", m_iPosicao);
		salvar.putExtra("objeto", pessoa);
		
		finish();
	}
	
	public void onVoltar(View v) {
		Intent voltar = new Intent();
		setResult(Tela1.S_FAILED, voltar);
		finish();
	}
}
