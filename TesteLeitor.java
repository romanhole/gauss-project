import java.io.*;
import java.util.*;
import Leitor.*;
public class TesteLeitor
{
	public static void main (String[] args)
	{
		try
		{
			Scanner ler = new Scanner(System.in);
			System.out.printf("Informe o arquivo para a leitura dos dados:\n");
			String n = ler.next();                                   //com o nome de arquivo certo
			Leitor l = new Leitor(n);
			Leitor lIgual = new Leitor(n);
			Leitor lDiferente= new Leitor("outra string");
			if(l.equals(lIgual) == true)
				System.out.println("l e lIgual são equals");
			else
				System.out.println("l e lIgual não são equals");       //verifica o equals
			if(l.equals(lDiferente) == true)
				System.out.println("l e lDiferente são equals");
			else
				System.out.println("l e lDiferente não são equals");
			System.out.println(l.lerMatriz());//verifica o lerMatriz()
			System.out.println(l.hashCode()); //verifica hashCode()
			System.out.println(l);            //verifica o toString()




			ler = new Scanner(System.in);
			System.out.printf("Informe o arquivo para a leitura dos dados:\n");
			n = ler.next();                                  //com nome de arquivo errado
			l = new Leitor(n);
			System.out.println(l.lerMatriz());
		}
		catch(Exception erro)
		{
			System.out.println("Erro: " + erro.getMessage());
		}
	}
}