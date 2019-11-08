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
		if(mt == null) //validação
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
		int vezes = 1; //variável que armazena o número de vezes que há igualdade nas divisões entre as linhas i e j
		double[] algo = new double[m.getLinha()]; //vetor que armazena o resultado das divisões entre as linhas i e j
		for(int i=0; i<m.getLinha()-1; i++)  //indice da linha
		{
			for(int k=1; k < m.getLinha() - i; k++) //indice para percorrer as linhas abaixo da atual
		  	{
		   		for(int j=0; j<m.getLinha(); j++)  //indice da coluna
		        	algo[j] = m.getValor(i,j)/m.getValor(i+k,j);  //armazena o valor da divisão entre a linha i e as demais abaixo
		      	for (int n = 1; n < m.getLinha(); n++)
		      	{
		        	if(algo[n] == algo[0]) //caso o valor seja igual ao de indice 0, adiciona no vezes
			    		vezes++;
				}
		      	if(vezes == m.getLinha()) //caso o vezes for igual a quantidade de linhas, significa que todas as divisões resultaram no mesmo valor
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
		int contadorMaximo = 0; //variável que armazena o contador que será comparado com o número máximo de vezes que pode-se fazer a troca de linhas
		while(m.temZeroNaDiag() == true && contadorMaximo <=m.getLinha()) //enquanto tem zero na diagonal e o contador não ultrapassou o número máximo de troca de linhas
		{
			for(int i=0; i<m.getLinha(); i++) //indice de linhas
			{
				if(m.getValor(i, i) == 0)
				{
					if(i != m.getLinha()-1) //caso não seja a última linha
					{
						double[] essaLinha = m.getVetorLinha(i);  //cria um vetor com os valores da linha com zero na diagonal
						double[] deBaixo = m.getVetorLinha(i+1);  //cria um vetor com os valores da linha abaixo a linha com zero na diagonal
						for(int j=0; j<m.getColuna(); j++) //indice de colunas
						{
							m.incluir(i+1, j, essaLinha[j]);      //troca as linhas
							m.incluir(i, j, deBaixo[j]);
						}
					}
					else                   //caso seja a última linha
					{
						double[] essaLinha = m.getVetorLinha(i); //cria um vetor com os valores da linha com zero na diagonal
						double[] deCima = m.getVetorLinha(i-1);  //cria um vetor com os valores da linha de cima a linha com zero na diagonal
						for(int j=0; j<m.getColuna(); j++) //indice de colunas
						{
							m.incluir(i, j, deCima[j]);
							m.incluir(i-1, j, essaLinha[j]);     //troca as linhas
						}
					}
					contadorMaximo++;  //soma ao contador máximo a cada troca de linha
				}
			}
		}
		if(contadorMaximo >m.getLinha())  //se o contador máximo for maior que o número máximo de troca de linhas, lança excessão, pois é impossível de resolver
			throw new Exception("Loop, sistema inválido");
	}

	/**
	    Tornar todos os números da diagonal da matriz em números 1.
	    Divide toda a linha pelo elemento a ser tornado 1.
	    @param linha o indice da linha a ser modificada.
	    @throws Exception se o incluir der erro
    */
	protected void tornarUm(int linha) throws Exception
	{
		double divisor = m.getValor(linha, linha); //pega o valor que tem que tem
		if(divisor != 1) //caso o valor já não seja 1
		{
			double[] essaLinha = m.getVetorLinha(linha); //cria um vetor da linha a ser modificada
			for(int j=0; j<m.getColuna(); j++) //indice de colunas
				m.incluir(linha, j, essaLinha[j]/divisor); //divide a linha inteira pelo valor a se tornar 1
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
		for(int i=0; i<m.getLinha(); i++) //indice de linhas
		{
			if(i != linhaAnterior && m.getValor(i,coluna) != 0) //caso o indice não seja igual ao indice da linha anterior e o valor já não seja zero
			{
				double multiplicador = (m.getValor(i, coluna))*(-1);  //pega o valor a se tornar zero e multiplica por -1 e armazena no multiplicador
				double[] ultimaLinha = m.getVetorLinha(linhaAnterior); //cria um vetor da linha anterior
				double[] essaLinha = m.getVetorLinha(i); //cria um vetor da linha a ser modificada
				for(int j=0; j<m.getColuna(); j++) //indice de colunas
				{
					m.incluir(i, j, ultimaLinha[j]*multiplicador + essaLinha[j]); //multiplica todos os seus elementos pelo número que desejamos zerar com o sinal trocado, então soma estes valores aos valores da linha onde desejamos implantar 0
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
		for(int i=0; i<m.getLinha(); i++) //indice de linhas
		{
			this.tornarUm(i);
			this.tornarZero(i, i);    //faz cada método para cada linha
		}
		for(int i=0; i<m.getLinha(); i++)
			str += i + 1 + "ª incógnita = " + m.getValor(i, m.getColuna()-1) + "\n"; //retorna uma string com as incógnitas em ordem
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
		if (! this.m.equals(g.m))   //compara os objetos da classe Matriz
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
		return ret;       //retorna a representação grafica do sistema
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
