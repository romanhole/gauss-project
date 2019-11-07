/**
A classe Matriz representa o sistema de equa��es armazenados em matrizes.

Inst�ncias desta classe permitem a reliza��o da manuten��o e acesso a matriz.
Nela encontramos, n�o s�, m�todos para incluir, verificar se a zero na diagonal,
mas tamb�m m�todos que permitem consult�-la.
@author Rafael Romanhole Borrozino.
@since 2019.
*/
public class Matriz implements Cloneable
{
	/**
		m[][] � uma vari�vel que armazena os valores da matriz
		linhas � uma vari�vel que armazena a quantidade de linhas
		colunas � uma vari�vel que armazena a quantidade de colunas
	*/
	protected double m[][] = null;
	protected int linhas, colunas = 0;

	/**
	    Constroi uma nova inst�ncia da classe Matriz.
	    Para tanto, deve ser fornecido um inteiro que ser� utilizado
	    como quantidade de linhas da matriz.
	    @param linhas o n�mero inteiro a ser utilizado como quantidade de linhas da matriz.
	    @throws Exception se o par�metro linhas for negativa ou zero.
    */
	public Matriz (int linhas) throws Exception
	{
		if(linhas < 1)
			throw new Exception("N�mero de linhas inv�lido");

		this.linhas = linhas;
		this.colunas = linhas + 1;
		m = new double[this.linhas][this.colunas];
	}

	/**
	    Verifica a presen�a de zeros na diagonal da matriz.
	    Resulta true, caso exista algum zero na diagonal, ou false,
	    caso contr�rio.
	    @return a indica��o da presen�a de zeros na diagonal da matriz.
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
	    Inclui um novo valor passado por par�metro a uma posi��o
	    tamb�m passada por par�metro.
	    @param i o indice da linha.
	    @param j o indice da coluna.
	    @param val o valor a ser inclu�do.
	    @throws Exception caso os indices sejam menores que zero ou excedam
	    o seus respectivos tamanhos.
    */
	public void incluir(int i, int j, double val) throws Exception
	{
		if(i<0 || j<0 || i>linhas || j>colunas)
			throw new Exception("valores inv�lidos para inclus�o");
		this.m[i][j] = val;
	}

	/**Obtem o n�mero de linhas da matriz, ou seja, o n�mero de equa��es.
	    Resulta a quantidade de linhas da inst�ncia � qual este m�todo for aplicado.
	    @return o n�mero de linhas da matriz, ou seja, o n�mero de equa��es do sistema.
    */
	public int getLinha()
	{
		return this.linhas;
	}

	/**Obtem o n�mero de colunas da matriz, ou seja, o n�mero de vari�veis mais o resultado.
		Resulta a quantidade de colunas da inst�ncia � qual este m�todo for aplicado.
		@return o n�mero de colunas da matriz, ou seja, o n�mero de vari�veis mais o resultado.
    */
	public int getColuna()
	{
		return this.colunas;
	}

	/**Obtem o valor de uma posi��o passada pelos par�metros.
		Resulta o valor na posi��o da matriz passada pelos par�metros.
		@param i indice da linha.
		@param j indice da coluna.
		@return o valor na posi��o da matriz passada pelos par�metros.
    */
	public double getValor(int i, int j)
	{
		return m[i][j];
	}

	/**Obtem um vetor com os valores de uma linha da matriz passada por par�metro.
			Resulta um vetor com os valores de uma linha passada pelos par�metros.
			@param qualLinha indice da linha
			@return um vetor com os valores de uma linha passada pelos par�metros.
    */
	public double[] getVetorLinha (int qualLinha)
	{
		return m[qualLinha].clone();
	}

	/**
	    Clona uma matriz.
	    Produz e resulta uma c�pia da matriz representada pela inst�ncia
	    � qual o m�todo for aplicado.
	    @return a c�pia da matriz representada pela inst�ncia � qual
	            o m�todo for aplicado.
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
	    Constroi uma c�pia da inst�ncia da classe Matriz dada.
	    Para tanto, deve ser fornecida uma instancia da classe Matriz para ser
	    utilizada como modelo para a constru��o da nova inst�ncia criada.
	    @param modelo a inst�ncia da classe Matriz a ser usada como modelo.
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
	    Verifica se o Object fornecido como par�metro representa uma
	    matriz igual �quela representada pela inst�ncia � qual este
	    m�todo for aplicado, resultando true em caso afirmativo,
	    ou false, caso contr�rio.
	    @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
	            for aplicado.
	    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
	            m�todo representarem matrizes iguais, ou false, caso contr�rio.
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
	    Gera uma representa��o textual de todo conte�do da Matriz.
	    Produz e resulta um String com todos os valores da matriz
	    em suas respectivas posi��es.
	    @return um String contendo todo o conte�do da Matriz.
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
		Calcula o c�digo de espalhamento (ou c�digo de hash) de uma Matriz.
		Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
		hashcode) de uma Matriz representado pela inst�ncia � qual o m�todo for aplicado.
		@return o c�digo de espalhamento da matriz chamante do m�todo.
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