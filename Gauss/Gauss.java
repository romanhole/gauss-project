package Gauss;
import Matriz.*;

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
	/**
		m é um atributo da classe Matriz que armazena a matriz/sistema a ser resolvido
	*/
	protected Matriz m = null;

	/**
	    Constroi uma nova instância da classe Gauss.
	    Para tanto, deve ser fornecido um objeto da classe Matriz que será utilizado
	    como os sistemas a serem resolvidos.
	    @param mt o objeto da classe Matriz a ser utilizado como
	    os sistemas a serem resolvidos.
	    @throws Exception se o objeto da classe Matriz for null.
    */
	public Gauss (Matriz mt) throws Exception
	{
		if(mt == null)
			throw new Exception("Matriz nula");
		m = new Matriz(mt);
	}

	/**
		Verifica que para nenhuma linha i, as divisões entre as linhas i e j resultem exatamente no mesmo valor.
		Resulta true, caso as divisões não resultem exatamente no mesmo valor, ou false,
		caso contrário.
		@return a indicação da possibilidade de resolução dos sistemas.
		@throws Exception caso desse erro no getValor.
    */
	public boolean verifica () throws Exception
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

	/**
	    Caso tenha zero nas diagonais, ele trocas as linhas para que eles saiam da diagonal.
	    Troca as linhas da matriz para que não tenhas zero nas diagonais.
	    @throws Exception caso ele troque de linhas mais vezes que o número de linhas,
	    				  pois nesse caso os sistemas são impossíveis de resolver.
    */
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

	/**
	    Tornar todos os números da diagonal da matriz em números 1.
	    Divide toda a linha pelo elemento a ser tornado 1.
	    @param linha o indice da linha a ser modificada.
	    @throws Exception se o incluir der erro
    */
	protected void tornarUm(int linha) throws Exception
	{
		double divisor = m.getValor(linha, linha);
		if(divisor != 1)
		{
			double[] essaLinha = m.getVetorLinha(linha);
			for(int j=0; j<m.getColuna(); j++)
				m.incluir(linha, j, essaLinha[j]/divisor);
		}
	}

	/**
		Tornar todos os demais elementos da coluna em 0.
		Pega a linha onde de implantou 1 por último, multiplica
		todos os seus elementos pelo número que desejamos zerar
		com o sinal trocado, então soma estes valores aos valores
		da linha onde desejamos implantar 0.
		@param linhaAnterior o indice da linha que foi modificada por último.
		@param coluna o indice da coluna a ser modificada
		@throws Exception se o incluir der erro
    */
	protected void tornarZero(int linhaAnterior, int coluna) throws Exception
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

	/**
	    Obtem uma string com os reultados das incógnitas em ordem.
	    Resulta em uma string com os valores das incógnitas em ordem.
	    @return uma string com os valores das incógnitas em ordem
	    @throws Exception der erro nos métodos tornarUm ou tornarZero
    */
	public String resolver() throws Exception
	{
		String str = "";
		for(int i=0; i<m.getLinha(); i++)
		{
			this.tornarUm(i);
			this.tornarZero(i, i);
		}
		for(int i=0; i<m.getLinha(); i++)
			str += i + 1 + "ª incógnita = " + m.getValor(i, m.getColuna()-1) + "\n";
		return str;
	}

	/**
	    Verifica a igualdade entre dois gauss.
	    Verifica se o Object fornecido como parâmetro representa um
	    gauss igual àquele representado pela instância à qual este
	    método for aplicado, resultando true em caso afirmativo,
	    ou false, caso contrário.
	    @param  obj o objeto a ser comparado com a instância à qual esse método
	            for aplicado.
	    @return true, caso o Object fornecido ao método e a instância chamante do
	            método representarem gauss iguais, ou false, caso contrário.
    */
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

	/**
	    Gera uma representação textual de todo conteúdo do Gauss.
	    Produz e resulta um String com os valores do objeto da
	    classe Matriz.
	    @return um String contendo todo o conteúdo da do Gauss.
    */
	public String toString()
	{
		String ret = "";
		ret = m.toString();
		return ret;
	}

	/**
		Calcula o código de espalhamento (ou código de hash) do Gauss.
		Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
		hashcode) do Gauss representado pela instância à qual o método for aplicado.
		@return o código de espalhamento do gauss chamante do método.
    */
	public int hashCode ()
	{
		int ret=666;
		ret = ret*13/*primo*/ + this.m.hashCode ();

		if (ret<0)
			ret = -ret;

		return ret;
	}
}
