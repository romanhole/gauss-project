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
			String n = ler.next();
			Leitor l = new Leitor(n);
			Matriz m = new Matriz(l.lerMatriz());
			Gauss g = new Gauss(m);
			System.out.println("é solucionável: " + g.verifica());
		}
		catch(Exception erro)
		{
			System.out.println("Erro: " + erro.getMessage());
		}
	}
}