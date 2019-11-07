import java.io.*;
import java.util.*;

/**
A classe Leitor representa o prcesso de leitura do arquivo texto e a passagem
dos dados para uma instância da classe Matriz.

Instâncias desta classe permitem ler um arquivo e armazenar seus valores numa instância da classe Matriz.
Nela encontramos um método para leitura, que retorna um objeto da classe Matriz.
@author Rafael Romanhole Borrozino.
@since 2019.
*/
public class Leitor
{
	private static String arq = "";

	public Leitor(String nomeArquivo) throws Exception
	{
		if (nomeArquivo == null)
			throw new Exception ("nome de arquivo é nulo");
		this.arq = nomeArquivo;
	}

	public  Matriz LerMatriz() throws Exception
	{
		Matriz m = null;
		try
		{
			BufferedReader arquivo = new BufferedReader(new FileReader(this.arq));
			int qtd = Integer.parseInt(arquivo.readLine());
			m = new Matriz(qtd);
			for(int i = 0; i < qtd; i++)
			{
				StringTokenizer quebrador = new StringTokenizer (arquivo.readLine());
				int j = 0;
				while(quebrador.hasMoreTokens())
				{
					m.incluir(i, j, Double.parseDouble(quebrador.nextToken()));
					j++;
				}
			}
		}
		catch(Exception erro)
		{
			System.out.println("Deu erro na leitura de dados");
		}

		return m;
	}

	public boolean equals (Object obj)
	{
		if (this==obj)
			return true;

		if (obj==null)
			return false;

		if (this.getClass()!=obj.getClass())
			return false;

		Leitor l = (Leitor)obj;
		if (this.arq!= l.arq)
			return false;

		return true;
    }

    public String toString()
    {
		String ret = arq;
		return ret;
	}

	public int hashCode ()
	{
		int ret=666;
		ret = ret*13/*primo*/ + this.arq.hashCode ();

		if (ret<0)
		    ret = -ret;

		return ret;
	}
}