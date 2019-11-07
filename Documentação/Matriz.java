/**
A classe Matriz representa o sistema de equações armazenados em matrizes.

Instâncias desta classe permitem a relização da manutenção e acesso a matriz.
Nela encontramos, não só, métodos para incluir, verificar se a zero na diagonal,
mas também métodos que permitem consultá-la.
@author Rafael Romanhole Borrozino.
@since 2019.
*/
public class Matriz implements Cloneable
{
	/**
		m[][] é uma variável que armazena os valores da matriz
		linhas é uma variável que armazena a quantidade de linhas
		colunas é uma variável que armazena a quantidade de colunas
	*/
	protected double m[][] = null;
	protected int linhas, colunas = 0;

	/**
	    Constroi uma nova instância da classe Matriz.
	    Para tanto, deve ser fornecido um inteiro que será utilizado
	    como quantidade de linhas da matriz.
	    @param linhas o número inteiro a ser utilizado como quantidade de linhas da matriz.
	    @throws Exception se o parâmetro linhas for negativa ou zero.
    */
	public Matriz (int linhas) throws Exception
	{
		if(linhas < 1)
			throw new Exception("Número de linhas inválido");

		this.linhas = linhas;
		this.colunas = linhas + 1;
		m = new double[this.linhas][this.colunas];
	}

	/**
	    Verifica a presença de zeros na diagonal da matriz.
	    Resulta true, caso exista algum zero na diagonal, ou false,
	    caso contrário.
	    @return a indicação da presença de zeros na diagonal da matriz.
    */
	public boolean temZeroNaDiag()
	{
		for(int i=0; i<linhas; i++)
		{
			if(m[i][i] == 0)
				return true;
		}
		return false;
	}

	/**
	    Inclui um novo valor passado por parâmetro a uma posição
	    também passada por parâmetro.
	    @param i o indice da linha.
	    @param j o indice da coluna.
	    @param val o valor a ser incluído.
	    @throws Exception caso os indices sejam menores que zero ou excedam
	    o seus respectivos tamanhos.
    */
	public void incluir(int i, int j, double val) throws Exception
	{
		if(i<0 || j<0 || i>linhas || j>colunas)
			throw new Exception("valores inválidos para inclusão");
		this.m[i][j] = val;
	}

	/**Obtem o número de linhas da matriz, ou seja, o número de equações.
	    Resulta a quantidade de linhas da instância à qual este método for aplicado.
	    @return o número de linhas da matriz, ou seja, o número de equações do sistema.
    */
	public int getLinha()
	{
		return this.linhas;
	}

	/**Obtem o número de colunas da matriz, ou seja, o número de variáveis mais o resultado.
		Resulta a quantidade de colunas da instância à qual este método for aplicado.
		@return o número de colunas da matriz, ou seja, o número de variáveis mais o resultado.
    */
	public int getColuna()
	{
		return this.colunas;
	}

	/**Obtem o valor de uma posição passada pelos parâmetros.
		Resulta o valor na posição da matriz passada pelos parâmetros.
		@param i indice da linha.
		@param j indice da coluna.
		@return o valor na posição da matriz passada pelos parâmetros.
    */
	public double getValor(int i, int j)
	{
		return m[i][j];
	}

	/**Obtem um vetor com os valores de uma linha da matriz passada por parâmetro.
			Resulta um vetor com os valores de uma linha passada pelos parâmetros.
			@param qualLinha indice da linha
			@return um vetor com os valores de uma linha passada pelos parâmetros.
    */
	public double[] getVetorLinha (int qualLinha)
	{
		return m[qualLinha].clone();
	}

	/**
	    Clona uma matriz.
	    Produz e resulta uma cópia da matriz representada pela instância
	    à qual o método for aplicado.
	    @return a cópia da matriz representada pela instância à qual
	            o método for aplicado.
    */
	public Object clone()
	{
		Matriz ret = null;
		try
		{
			ret = new Matriz(this);
		}
		catch(Exception erro)
		{}
		return ret;
	}

	/**
	    Constroi uma cópia da instância da classe Matriz dada.
	    Para tanto, deve ser fornecida uma instancia da classe Matriz para ser
	    utilizada como modelo para a construção da nova instância criada.
	    @param modelo a instância da classe Matriz a ser usada como modelo.
	    @throws Exception se o modelo for null.
    */
	public Matriz (Matriz mt) throws Exception
	{
		if(mt == null)
			throw new Exception("matriz nula");
		this.linhas = mt.linhas;
		this.colunas = mt.colunas;
		this.m = new double[this.linhas][this.colunas];
		this.m = mt.m;
	}

	/**
	    Verifica a igualdade entre duas matrizes.
	    Verifica se o Object fornecido como parâmetro representa uma
	    matriz igual àquela representada pela instância à qual este
	    método for aplicado, resultando true em caso afirmativo,
	    ou false, caso contrário.
	    @param  obj o objeto a ser comparado com a instância à qual esse método
	            for aplicado.
	    @return true, caso o Object fornecido ao método e a instância chamante do
	            método representarem matrizes iguais, ou false, caso contrário.
    */
	public boolean equals (Object obj)
	{
		if (this==obj)
			return true;

		if (obj==null)
			return false;

		if (this.getClass()!=obj.getClass())
			return false;

		Matriz mt = (Matriz)obj;
		if (this.linhas != mt.linhas || this.colunas != mt.colunas)
			return false;
		for(int i=0; i<this.linhas; i++)
		{
			for(int j=0; j<this.colunas; j++)
			{
				if(this.m[i][j] != mt.m[i][j])
					return false;
			}
		}

		return true;
	}

	/**
	    Gera uma representação textual de todo conteúdo da Matriz.
	    Produz e resulta um String com todos os valores da matriz
	    em suas respectivas posições.
	    @return um String contendo todo o conteúdo da Matriz.
    */
	public String toString()
	{
		String ret = "";

		for(int i=0; i < this.linhas; i++)
		{
			for(int j=0; j < this.colunas; j++)
				ret += m[i][j] + " ";
			ret += "\n";
		}

		return ret;
	}

	/**
		Calcula o código de espalhamento (ou código de hash) de uma Matriz.
		Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
		hashcode) de uma Matriz representado pela instância à qual o método for aplicado.
		@return o código de espalhamento da matriz chamante do método.
    */
	public int hashCode ()
	{
		int ret=666;
		ret = ret*13 + Integer.valueOf(this.linhas).hashCode();
		ret = ret*13 + Integer.valueOf(this.colunas).hashCode();
		for(int i=0; i<linhas; i++)
		{
			for(int j=0; j<colunas; j++)
				ret = ret*13 + Double.valueOf(m[i][j]).hashCode();
		}

		if (ret<0)
			ret = -ret;

		return ret;
	}
}