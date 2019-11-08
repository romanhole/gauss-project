import Gauss.*;
import Matriz.*;
import Leitor.*;
import java.io.*;
import java.util.*;

public class TesteGauss
{
	public static void main (String[] args)
	{
		try
		{
			Scanner ler = new Scanner(System.in);
			System.out.printf("Informe o arquivo para a leitura dos dados:\n");
			String n = ler.next();            //testo com arquivo com problema na verificação
			Leitor l = new Leitor(n);
			Matriz m = new Matriz(l.lerMatriz());
			Gauss g = new Gauss(m);
			System.out.println("\né solucionável: " + g.verifica());
		}
		catch(Exception erro)
		{
			System.out.println("\nErro: " + erro.getMessage());
		}

		try
		{
			Matriz m = null;
			Gauss g = new Gauss(m);            //testo com matriz nula
		}
		catch(Exception erro)
		{
			System.out.println("\nErro: " + erro.getMessage());
		}

		try
		{
			Scanner ler = new Scanner(System.in);
			System.out.printf("\nInforme o arquivo para a leitura dos dados:\n");
			String n = ler.next();           //testa com arquivo com problema no trocaZeros()
			Leitor l = new Leitor(n);
			Matriz m = new Matriz(l.lerMatriz());
			Gauss g = new Gauss(m);
			System.out.println("\né solucionável: " + g.verifica());
			System.out.println("\nsistema = \n" + g);
			g.trocaZeros();
			System.out.println("\nsistema depois da troca de zeros = \n" + g);
		}
		catch(Exception erro)
		{
			System.out.println("\nErro: " + erro.getMessage());
		}
		try
		{
			Scanner ler = new Scanner(System.in);
			System.out.printf("\nInforme o arquivo para a leitura dos dados:\n");
			String n = ler.next();       //testa com arquivo funcional
			Leitor l = new Leitor(n);
			Matriz m = new Matriz(l.lerMatriz());
			Gauss g = new Gauss(m);
			System.out.println("\né solucionável: " + g.verifica());
			System.out.println("\nsistema = \n" + g);
			g.trocaZeros();  //tira os zeros da diagonal
			System.out.println("\nsistema depois da troca de zeros = \n" + g);
			System.out.println(g.resolver());  //printa a solução das equações
			Matriz m2 = new Matriz(2);
			m2.incluir(0,0,0);
			m2.incluir(0,1,4);
			m2.incluir(0,2,20);
			m2.incluir(1,0,5);
			m2.incluir(1,1,3);
			m2.incluir(1,2,0);
			Gauss g2 = new Gauss(m2);
			if(g.equals(g2))
				System.out.println("g é equals de g2");
			else
				System.out.println("g não é equals de g2");
			if(g.equals(g))  //comparar instancias iguais
				System.out.println("g é equals de g");
			else
				System.out.println("g não é equals de g");
			System.out.println(g.hashCode());
		}
		catch(Exception erro)
		{
			System.out.println("\nErro: " + erro.getMessage());
		}
	}
}