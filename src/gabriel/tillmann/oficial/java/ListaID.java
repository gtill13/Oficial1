package gabriel.tillmann.oficial.java;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaID implements Serializable{
	
	private ArrayList<String> m_arrID = new ArrayList<String>();


	public ListaID() {
		super();
	}

	public void removeID(int index)
	{
		m_arrID.remove(index);
	}
	
	public void addID(String id)
	{
		if (!id.isEmpty())		
			m_arrID.add(id);
	}
	
	public String getID(int index)
	{
		return m_arrID.get(index);
	}
	
	public int getQtd()
	{
		return m_arrID.size();
	}
	
	public void limpar()
	{
		m_arrID.clear();
	}
	
}
