import java.io.*;
import java.util.*;

public class Programa
{
	public static void main (String[] args)
	{
		try
		{
			Scanner ler = new Scanner(System.in);
			System.out.printf("Informe o arquivo para a leitura dos dados:\n");
    		String n = ler.next();
			Leitor l = new Leitor(n);
			Matriz m = new Matriz(l.LerMatriz());
			Gauss g = new Gauss(m);
			if(!g.verifica())
			{
				System.out.println("Sistema inválido");
			}
			else
			{
				if(m.temZeroNaDiag() == true)
					g.trocaZeros();
				System.out.println(g.resolver());
			}
		}
		catch(Exception erro)
		{
			System.out.println("Erro: " + erro.getMessage());
		}
    }
}


