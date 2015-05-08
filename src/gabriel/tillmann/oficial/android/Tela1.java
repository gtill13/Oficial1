package gabriel.tillmann.oficial.android;

import gabriel.tillmann.oficial.android.R;
import gabriel.tillmann.oficial.java.ListaID;
import gabriel.tillmann.oficial.java.Pessoa;
import gabriel.tillmann.oficial.java.PessoaAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class Tela1 extends Activity {

	private TextView m_texto;
	private ListView m_listView;
	private ArrayList<Pessoa> m_arrPessoas;
	
	public static int S_OK     = 0;
	public static int S_FAILED = 1;
	
	public static int BUSCAR_CONTATO = 1;
	public static int NOVO_CONTATO = 2;
	public static int EDITAR_CONTATO = 3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);
        
		Log.d("TiLL", "onCreate(Bundle savedInstanceState)");
        
		m_texto = (TextView) findViewById(R.id.t1_textView);
		m_listView = (ListView) findViewById(R.id.t1_listView);
		m_arrPessoas = new ArrayList<Pessoa>();
        
		m_arrPessoas.add(new Pessoa("", "Eduarda", "443.506.892-30", "sady@gbol.com", "3345-3456", "9178-7622", 'f'));
		m_arrPessoas.add(new Pessoa("", "Marcelo Fey", "835.471.631-70", "mfey@ilang.com", "3330-0899", "9114-1699", 'm'));
		
		m_listView.setAdapter( new PessoaAdapter(getApplicationContext() , m_arrPessoas) );

		m_listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicaoClicada, long arg3) {
				Log.d("TiLL", "Clicado na linha ("+posicaoClicada+")");

				Intent novaTela = new Intent(getApplicationContext(), Tela2.class);
				
				novaTela.putExtra("posicao", posicaoClicada);
				novaTela.putExtra("objeto", m_arrPessoas.get(posicaoClicada) );
				startActivityForResult(novaTela, EDITAR_CONTATO);
			}
		});
		
		m_listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int posicaoClicada, long arg3) {
				Log.d("TiLL", "LongClick na linha ("+posicaoClicada+")");
				
				final int posicao = posicaoClicada;
	
				AlertDialog.Builder alert  = new AlertDialog.Builder(Tela1.this);
				alert.setTitle("Exclusão");
				alert.setMessage("Confirma a exclusão da linha seleciona?");
				alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() 
				{
		            public void onClick(DialogInterface arg0, int arg1) {
		            	m_arrPessoas.remove(posicao);
		            	AtualizaDados();
		            }
				});
				
				alert.setNegativeButton("Não", new DialogInterface.OnClickListener() 
				{
		            public void onClick(DialogInterface arg0, int arg1) {		   
		            }
				});
					
				alert.show();
				
				AtualizaDados();
				
				return false;
			}
	    });
			
		AtualizaDados();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    		
    	if (resultCode == S_OK)
    	{
    		if (requestCode == NOVO_CONTATO || requestCode == BUSCAR_CONTATO)
    			m_arrPessoas.add( (Pessoa) data.getSerializableExtra("objeto"));
    		else if (requestCode == EDITAR_CONTATO)
    			m_arrPessoas.set(data.getExtras().getInt("posicao"), (Pessoa) data.getSerializableExtra("objeto"));
    	}
    	
    	AtualizaDados();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.menuPesquisar) {
        	
    		Intent novaTela = new Intent(getApplicationContext(), Tela3.class);

    		ListaID list = new ListaID();
    		
    		for (Pessoa p : m_arrPessoas) {
    			list.addID(p.getID());
			}
    		
    		novaTela.putExtra("ids", list );
    		
    		startActivityForResult(novaTela, BUSCAR_CONTATO);
        	
            return true;
        }
        else if (id == R.id.menuSair) {
        	      	
        	AlertDialog.Builder alert  = new AlertDialog.Builder(Tela1.this);
			alert.setTitle("Encerrar aplicação");
			alert.setMessage("Deseja realmente encerrar a aplicação?");
			alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() 
			{
	            public void onClick(DialogInterface arg0, int arg1) {
	            	Tela1.super.finish();
	            }
			});
			
			alert.setNegativeButton("Não", new DialogInterface.OnClickListener() 
			{
	            public void onClick(DialogInterface arg0, int arg1) {		   
	            }
			});
				
			alert.show();
        				
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void finish() {
    	AlertDialog.Builder alert  = new AlertDialog.Builder(Tela1.this);
		alert.setTitle("Encerrar aplicação");
		alert.setMessage("Deseja realmente encerrar a aplicação?");
		alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() 
		{
            public void onClick(DialogInterface arg0, int arg1) {
            	Tela1.super.finish();
            }
		});
		
		alert.setNegativeButton("Não", new DialogInterface.OnClickListener() 
		{
            public void onClick(DialogInterface arg0, int arg1) {		   
            }
		});
			
		alert.show();
    }
    
    public void OnAdicionar(View v) {
    	
		Log.d("TiLL", "OnAdicionar(View v)");
    	
		Intent novaTela = new Intent(getApplicationContext(), Tela2.class);
		startActivityForResult(novaTela, NOVO_CONTATO);
    	
    }
    
	public void startActivityForResult(Intent intent, int requestCode) {
		intent.putExtra("requestCode", requestCode);
		super.startActivityForResult(intent, requestCode);
	}
    
	public void AtualizaDados()
	{
		m_listView.invalidateViews();
		
		if (m_arrPessoas.size() > 0)
			m_texto.setText("Contatos App, "+m_arrPessoas.size()+" encontrado(s)");
		else
			m_texto.setText("Contatos App, nenhum encontrado");
	}
}
