public class Matriz implements Cloneable
{
	private double m[][] = null;
	private int linhas, colunas = 0;

	public Matriz (int linhas) throws Exception
	{
		if(linhas < 1)
			throw new Exception("Número de linhas inválido");

		this.linhas = linhas;
		this.colunas = linhas + 1;
		m = new double[this.linhas][this.linhas + 1];
	}

	public void incluir(int i, int j, double val)
	{
		this.m[i][j] = val;
	}

	public int getLinha()
	{
		return this.linhas;
	}

	public int getColuna()
	{
		return this.colunas;
	}

	public double[][] getMatriz()
	{
		return m;
	}

	public double getValor(int i, int j)
	{
		return m[i][j];
	}

	public double[] getVetorLinha (int qualLinha)
	{
		double[] linha = new double[this.colunas];
		for(int j=0; j<this.colunas; j++)
		{
			linha[j] = m[qualLinha][j];
		}
		return linha;
	}

	public boolean temZeroNaDiag()
	{
		for(int i=0; i<linhas; i++)
		{
			if(m[i][i] == 0)
				return true;
		}
		return false;
	}

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

	public Matriz (Matriz mt) throws Exception
	{
		if(mt == null)
			throw new Exception("matriz nula");
		this.linhas = mt.linhas;
		this.colunas = mt.colunas;
		this.m = new double[this.linhas][this.colunas];
		this.m = mt.m;
	}

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

	public String toString()
	{
		String ret = "";
		System.out.println("linhas " + this.linhas);
		System.out.println("colunas " + this.colunas);

		for(int i=0; i < this.linhas; i++)
		{
			for(int j=0; j < this.colunas; j++)
				ret += m[i][j] + " ";
			ret += "\n";
		}

		return ret;
	}

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