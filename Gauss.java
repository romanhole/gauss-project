/**
A classe Gauss representa método de resolução das equações lidas no arquivo.

Instâncias desta classe permitem a relização das equações.
Nela encontramos métodos para verificar a possibilidade de resolução das equações,
fazer cada parte da solução separadamente, como trocar zeros, tornar um e tornar zero.
@author Rafael Romanhole Borrozino.
@since 2019.
*/
public class Gauss
{
	private Matriz m = null;

	public Gauss (Matriz mt) throws Exception
	{
		if(mt == null)
			throw new Exception("Matriz nula");
		m = new Matriz(mt);
	}

	public boolean verifica ()
	{
		double resultado = 0;
		int vezes = 1;
		double[] algo = new double[m.getLinha()];
		for(int i=0; i<m.getLinha()-1; i++)
		{
			int val = m.getLinha() - i;
			for(int k=1; k < val; k++)
		  	{
		   		for(int j=0; j<m.getLinha(); j++)
		        	algo[j] = m.getValor(i,j)/m.getValor(i+k,j);
		      	resultado = algo[0];
		      	for (int n = 1; n < m.getLinha(); n++)
		      	{
		        	if(algo[n] == resultado)
			    		vezes++;
				}
		      	if(vezes == m.getLinha())
		        	return false;
		        vezes = 1;
			}
		}
		return true;
	}

	public void trocaZeros() throws Exception
	{
		int contadorMaximo = 0;
		while(m.temZeroNaDiag() == true && contadorMaximo <=m.getLinha())
		{
			for(int i=0; i<m.getLinha(); i++)
			{
				if(m.getValor(i, i) == 0)
				{
					if(i != m.getLinha()-1)
					{
						double[] essaLinha = m.getVetorLinha(i);
						double[] deBaixo = m.getVetorLinha(i+1);
						for(int j=0; j<m.getColuna(); j++)
						{
							m.incluir(i+1, j, essaLinha[j]);
							m.incluir(i, j, deBaixo[j]);
						}
					}
					else
					{
						double[] essaLinha = m.getVetorLinha(i);
						double[] deCima = m.getVetorLinha(i-1);
						for(int j=0; j<m.getColuna(); j++)
						{
							m.incluir(i, j, deCima[j]);
							m.incluir(i-1, j, essaLinha[j]);
						}
					}
					contadorMaximo++;
				}
			}
		}
		if(contadorMaximo >m.getLinha())
			throw new Exception("2 Sistema inválido");
	}

	private void tornarUm(int linha)
	{
		double divisor = m.getValor(linha, linha);
		if(divisor != 1)
		{
			double[] essaLinha = m.getVetorLinha(linha);
			for(int j=0; j<m.getColuna(); j++)
				m.incluir(linha, j, essaLinha[j]/divisor);
		}
	}

	private void tornarZero(int linhaAnterior, int coluna)
	{
		for(int i=0; i<m.getLinha(); i++)
		{
			if(i != linhaAnterior && m.getValor(i,coluna) != 0)
			{
				double multiplicador = (m.getValor(i, coluna))*(-1);
				double[] ultimaLinha = m.getVetorLinha(linhaAnterior);
				double[] essaLinha = m.getVetorLinha(i);
				for(int j=0; j<m.getColuna(); j++)
				{
					m.incluir(i, j, ultimaLinha[j]*multiplicador + essaLinha[j]);
				}
			}
		}
	}

	public String resolver()
	{
		String str = "";
		for(int i=0; i<m.getLinha(); i++)
		{
			this.tornarUm(i);
			this.tornarZero(i, i);
		}
		for(int i=0; i<m.getLinha(); i++)
			str += i + 1 + "ª variável = " + m.getValor(i, m.getColuna()-1) + "\n";
		return str;
	}

	public boolean equals (Object obj)
	{
		if (this==obj)
			return true;

		if (obj==null)
			return false;

		if (this.getClass()!=obj.getClass())
			return false;

		Gauss g = (Gauss)obj;
		if (this.m.equals(g.m))
			return false;

		return true;
	}

	public String toString()
	{
		String ret = "";
		ret = m.toString();
		return ret;
	}

	public int hashCode ()
	{
		int ret=666;
		ret = ret*13/*primo*/ + this.m.hashCode ();

		if (ret<0)
			ret = -ret;

		return ret;
	}
}
