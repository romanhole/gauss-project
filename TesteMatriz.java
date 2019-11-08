import Matriz.*;

public class TesteMatriz
{
	public static void main (String[] args)
	{
		try
		{
			Matriz m = new Matriz(2);
			m.incluir(0,0,0);
			m.incluir(0,1,4);
			m.incluir(0,2,20);
			m.incluir(1,0,5);    //testa incluir com valores corretos
			m.incluir(1,1,3);
			m.incluir(1,2,0);
			System.out.println("Tem zeros na diagonal principal? = " + m.temZeroNaDiag()); //testa o temZeroNaDiag()
			System.out.println("Matriz m = \n" + m); //testa toString()
			m.incluir(0,0,1);
			System.out.println("Tem zeros na diagonal principal? = " + m.temZeroNaDiag());
			System.out.println("linhas = " + m.getLinha()); //testa getLinha()
			System.out.println("colunas = " +m.getColuna()); //testa getColuna()
			System.out.println("Matriz m = \n" + m);
			System.out.println("valor no indice (1,1) = " +m.getValor(1,1)); //testa getValor
			System.out.println("Linha 1 da Matriz = " +m.getVetorLinha(1)[1]); //testa getVetorLinha
			System.out.println("Clone de m = \n" +m.clone()); //testa o clone()
			Matriz z = new Matriz(m); //testa o construtor de cópia
			System.out.println("Matriz z = \n" + z);
			z.incluir(1,0,4);
			System.out.println("Matriz z alterada= \n" + z);
			System.out.println("m = \n" + m);
			if(m.equals(z))
				System.out.println("m é equals de z");
			else
				System.out.println("m não é equals de z");
			System.out.println("hashCode = " + m.hashCode());
			m.incluir(3,4,2);   //testa incluir com valores impossíveis
			System.out.println(m);
		}
		catch(Exception erro)
		{
			System.out.println("Erro: " + erro.getMessage());
		}

		try
		{
			Matriz m = new Matriz(-1); //testa construtor com valores inválidos
		}
		catch(Exception erro)
		{
			System.out.println("Erro: " + erro.getMessage());
		}

		try
		{
			Matriz m = new Matriz(1);
			m.incluir(0,0,4);
			System.out.println(m.getValor(2, 2));  //testa getValor com valores inválidos
		}
		catch(Exception erro)
		{
			System.out.println("Erro: " + erro.getMessage());
		}

		try
		{
			Matriz m = new Matriz(1);
			m.incluir(0,0,2);
			System.out.println(m.getVetorLinha(1)); //testa getVetorLinha com valores inválidos
		}
		catch(Exception erro)
		{
			System.out.println("Erro: " + erro.getMessage());
		}
	}
}