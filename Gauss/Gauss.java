package Gauss;
import Matriz.*;

/**
A classe Gauss representa m�todo de resolu��o das equa��es lidas no arquivo.

Inst�ncias desta classe permitem a reliza��o das equa��es.
Nela encontramos m�todos para verificar a possibilidade de resolu��o das equa��es,
fazer cada parte da solu��o separadamente, como trocar zeros, tornar um e tornar zero.
@author Rafael Romanhole Borrozino.
@since 2019.
*/
public class Gauss
{
	/**
		m � um atributo da classe Matriz que armazena a matriz/sistema a ser resolvido
	*/
	protected Matriz m = null;

	/**
	    Constroi uma nova inst�ncia da classe Gauss.
	    Para tanto, deve ser fornecido um objeto da classe Matriz que ser� utilizado
	    como os sistemas a serem resolvidos.
	    @param mt o objeto da classe Matriz a ser utilizado como
	    os sistemas a serem resolvidos.
	    @throws Exception se o objeto da classe Matriz for null.
    */
	public Gauss (Matriz mt) throws Exception
	{
		if(mt == null) //valida��o
			throw new Exception("Matriz nula");
		m = new Matriz(mt);
	}

	/**
		Verifica que para nenhuma linha i, as divis�es entre as linhas i e j resultem exatamente no mesmo valor.
		Resulta true, caso as divis�es n�o resultem exatamente no mesmo valor, ou false,
		caso contr�rio.
		@return a indica��o da possibilidade de resolu��o dos sistemas.
		@throws Exception caso desse erro no getValor.
    */
	public boolean verifica () throws Exception
	{
		int vezes = 1; //vari�vel que armazena o n�mero de vezes que h� igualdade nas divis�es entre as linhas i e j
		double[] algo = new double[m.getLinha()]; //vetor que armazena o resultado das divis�es entre as linhas i e j
		for(int i=0; i<m.getLinha()-1; i++)  //indice da linha
		{
			for(int k=1; k < m.getLinha() - i; k++) //indice para percorrer as linhas abaixo da atual
		  	{
		   		for(int j=0; j<m.getLinha(); j++)  //indice da coluna
		        	algo[j] = m.getValor(i,j)/m.getValor(i+k,j);  //armazena o valor da divis�o entre a linha i e as demais abaixo
		      	for (int n = 1; n < m.getLinha(); n++)
		      	{
		        	if(algo[n] == algo[0]) //caso o valor seja igual ao de indice 0, adiciona no vezes
			    		vezes++;
				}
		      	if(vezes == m.getLinha()) //caso o vezes for igual a quantidade de linhas, significa que todas as divis�es resultaram no mesmo valor
		        	return false;
		        vezes = 1;
			}
		}
		return true;
	}

	/**
	    Caso tenha zero nas diagonais, ele trocas as linhas para que eles saiam da diagonal.
	    Troca as linhas da matriz para que n�o tenhas zero nas diagonais.
	    @throws Exception caso ele troque de linhas mais vezes que o n�mero de linhas,
	    				  pois nesse caso os sistemas s�o imposs�veis de resolver.
    */
	public void trocaZeros() throws Exception
	{
		int contadorMaximo = 0; //vari�vel que armazena o contador que ser� comparado com o n�mero m�ximo de vezes que pode-se fazer a troca de linhas
		while(m.temZeroNaDiag() == true && contadorMaximo <=m.getLinha()) //enquanto tem zero na diagonal e o contador n�o ultrapassou o n�mero m�ximo de troca de linhas
		{
			for(int i=0; i<m.getLinha(); i++) //indice de linhas
			{
				if(m.getValor(i, i) == 0)
				{
					if(i != m.getLinha()-1) //caso n�o seja a �ltima linha
					{
						double[] essaLinha = m.getVetorLinha(i);  //cria um vetor com os valores da linha com zero na diagonal
						double[] deBaixo = m.getVetorLinha(i+1);  //cria um vetor com os valores da linha abaixo a linha com zero na diagonal
						for(int j=0; j<m.getColuna(); j++) //indice de colunas
						{
							m.incluir(i+1, j, essaLinha[j]);      //troca as linhas
							m.incluir(i, j, deBaixo[j]);
						}
					}
					else                   //caso seja a �ltima linha
					{
						double[] essaLinha = m.getVetorLinha(i); //cria um vetor com os valores da linha com zero na diagonal
						double[] deCima = m.getVetorLinha(i-1);  //cria um vetor com os valores da linha de cima a linha com zero na diagonal
						for(int j=0; j<m.getColuna(); j++) //indice de colunas
						{
							m.incluir(i, j, deCima[j]);
							m.incluir(i-1, j, essaLinha[j]);     //troca as linhas
						}
					}
					contadorMaximo++;  //soma ao contador m�ximo a cada troca de linha
				}
			}
		}
		if(contadorMaximo >m.getLinha())  //se o contador m�ximo for maior que o n�mero m�ximo de troca de linhas, lan�a excess�o, pois � imposs�vel de resolver
			throw new Exception("Loop, sistema inv�lido");
	}

	/**
	    Tornar todos os n�meros da diagonal da matriz em n�meros 1.
	    Divide toda a linha pelo elemento a ser tornado 1.
	    @param linha o indice da linha a ser modificada.
	    @throws Exception se o incluir der erro
    */
	protected void tornarUm(int linha) throws Exception
	{
		double divisor = m.getValor(linha, linha); //pega o valor que tem que tem
		if(divisor != 1) //caso o valor j� n�o seja 1
		{
			double[] essaLinha = m.getVetorLinha(linha); //cria um vetor da linha a ser modificada
			for(int j=0; j<m.getColuna(); j++) //indice de colunas
				m.incluir(linha, j, essaLinha[j]/divisor); //divide a linha inteira pelo valor a se tornar 1
		}
	}

	/**
		Tornar todos os demais elementos da coluna em 0.
		Pega a linha onde de implantou 1 por �ltimo, multiplica
		todos os seus elementos pelo n�mero que desejamos zerar
		com o sinal trocado, ent�o soma estes valores aos valores
		da linha onde desejamos implantar 0.
		@param linhaAnterior o indice da linha que foi modificada por �ltimo.
		@param coluna o indice da coluna a ser modificada
		@throws Exception se o incluir der erro
    */
	protected void tornarZero(int linhaAnterior, int coluna) throws Exception
	{
		for(int i=0; i<m.getLinha(); i++) //indice de linhas
		{
			if(i != linhaAnterior && m.getValor(i,coluna) != 0) //caso o indice n�o seja igual ao indice da linha anterior e o valor j� n�o seja zero
			{
				double multiplicador = (m.getValor(i, coluna))*(-1);  //pega o valor a se tornar zero e multiplica por -1 e armazena no multiplicador
				double[] ultimaLinha = m.getVetorLinha(linhaAnterior); //cria um vetor da linha anterior
				double[] essaLinha = m.getVetorLinha(i); //cria um vetor da linha a ser modificada
				for(int j=0; j<m.getColuna(); j++) //indice de colunas
				{
					m.incluir(i, j, ultimaLinha[j]*multiplicador + essaLinha[j]); //multiplica todos os seus elementos pelo n�mero que desejamos zerar com o sinal trocado, ent�o soma estes valores aos valores da linha onde desejamos implantar 0
				}
			}
		}
	}

	/**
	    Obtem uma string com os reultados das inc�gnitas em ordem.
	    Resulta em uma string com os valores das inc�gnitas em ordem.
	    @return uma string com os valores das inc�gnitas em ordem
	    @throws Exception der erro nos m�todos tornarUm ou tornarZero
    */
	public String resolver() throws Exception
	{
		String str = "";
		for(int i=0; i<m.getLinha(); i++) //indice de linhas
		{
			this.tornarUm(i);
			this.tornarZero(i, i);    //faz cada m�todo para cada linha
		}
		for(int i=0; i<m.getLinha(); i++)
			str += i + 1 + "� inc�gnita = " + m.getValor(i, m.getColuna()-1) + "\n"; //retorna uma string com as inc�gnitas em ordem
		return str;
	}

	/**
	    Verifica a igualdade entre dois gauss.
	    Verifica se o Object fornecido como par�metro representa um
	    gauss igual �quele representado pela inst�ncia � qual este
	    m�todo for aplicado, resultando true em caso afirmativo,
	    ou false, caso contr�rio.
	    @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
	            for aplicado.
	    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
	            m�todo representarem gauss iguais, ou false, caso contr�rio.
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
		if (! this.m.equals(g.m))   //compara os objetos da classe Matriz
			return false;

		return true;
	}

	/**
	    Gera uma representa��o textual de todo conte�do do Gauss.
	    Produz e resulta um String com os valores do objeto da
	    classe Matriz.
	    @return um String contendo todo o conte�do da do Gauss.
    */
	public String toString()
	{
		String ret = "";
		ret = m.toString();
		return ret;       //retorna a representa��o grafica do sistema
	}

	/**
		Calcula o c�digo de espalhamento (ou c�digo de hash) do Gauss.
		Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
		hashcode) do Gauss representado pela inst�ncia � qual o m�todo for aplicado.
		@return o c�digo de espalhamento do gauss chamante do m�todo.
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
