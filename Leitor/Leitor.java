package Leitor;
import java.io.*;
import java.util.*;
import Matriz.*;

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
	/**
		arq é um atributo que armazena o nome do arquivo que será lido
	*/
	protected static String arq = "";

	/**
	    Constroi uma nova instância da classe Leitor.
	    Para tanto, deve ser fornecido uma string que será utilizado
	    como nome do arquivo a ser pesquisado no FileReader.
	    @param nomeArquivo a string a ser utilizado como parâmetro fo FileReader.
	    @throws Exception se a string for null.
    */
	public Leitor(String nomeArquivo) throws Exception
	{
		if (nomeArquivo == null)
			throw new Exception ("nome de arquivo é nulo");
		this.arq = nomeArquivo;
	}

	/**
	    Obtem uma matriz a partir da leitura do arquivo lido.
	    Resulta em um objeto da classe matriz.
	    @return um objeto da classe matriz
	    @throws Exception quando o nome do arquivo está errado
    */
	public Matriz LerMatriz() throws Exception
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

	/**
	    Verifica a igualdade entre dois leitores.
	    Verifica se o Object fornecido como parâmetro representa um
	    leitor igual àquele representado pela instância à qual este
	    método for aplicado, resultando true em caso afirmativo,
	    ou false, caso contrário.
	    @param  obj o objeto a ser comparado com a instância à qual esse método
	            for aplicado.
	    @return true, caso o Object fornecido ao método e a instância chamante do
	            método representarem leitores iguais, ou false, caso contrário.
    */
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

	/**
	    Gera uma representação textual de todo conteúdo do Leitor.
	    Produz e resulta um String com o nome do arquivo.
	    @return um String contendo todo o conteúdo do Leitor.
    */
    public String toString()
    {
		String ret = arq;
		return ret;
	}

	/**
	    Calcula o código de espalhamento (ou código de hash) do Leitor.
	    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
	    hashcode) do Leitor representado pela instância à qual o método for aplicado.
	    @return o código de espalhamento do leitor chamante do método.
    */
	public int hashCode ()
	{
		int ret=666;
		ret = ret*13/*primo*/ + this.arq.hashCode ();

		if (ret<0)
		    ret = -ret;

		return ret;
	}
}