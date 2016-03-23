import java.awt.*;
import java.applet.*;

class Reina {
		// atributos
	private int fila;
	private int columna;
	private Reina vecina;

		// constructor
	Reina (int columna_n, Reina vecina_izq)
	{
		columna = columna_n;
		vecina = vecina_izq;
	}

	private boolean puedeAtacar (int fila_n, int columna_n) 
	{
		int dc;
		
		dc = columna - columna_n;
		if ((fila == fila_n) ||	(fila + dc == fila_n) || (fila - dc == fila_n))
			return true;
		if (vecina != null)
			return vecina.puedeAtacar (fila_n, columna_n);
		return false;
	}

	public boolean primera () 
	{
		fila = 1;
		if ((vecina != null) && vecina.primera ())
			return pruebaOAvanza ();
		return true;
	}

	public boolean pruebaOAvanza ()
	{
		if ((vecina != null) && vecina.puedeAtacar (fila, columna))
			return siguiente ();
		return true;
	}
	
	public boolean siguiente()
	{
		if (fila == 8)
		{
			if (!((vecina != null) && (vecina.siguiente ())))
				return false;
			fila = 0;
		}
		fila = fila + 1;
		return pruebaOAvanza ();
	}

	public void despliega (Graphics g) 
	{
		if (vecina != null)
			vecina.despliega (g);
		g.setColor(Color.white);
		g.fillOval(((fila - 1) * 50)+10, ((columna - 1) * 50)+10, 25, 25);
	}

}

public class ReinaSol extends Applet {

	private Reina ultimaReina;

	public void init ()
	{
		int i;
		ultimaReina = null;
		
		for (i = 0; i < 8; i++) 
			ultimaReina = new Reina (i+1, ultimaReina);
	}

	public void paint (Graphics g) 
	{
		for (int i = 0; i < 8; i+=2) 
			for (int j = 0; j < 8; j+=2) 
			{
				g.fillRect(50 * (j+1), 50 * i, 50, 50);
				g.fillRect(50 * j, 50 * (i+1), 50, 50);
			}
		if ((ultimaReina != null) && ultimaReina.primera ())
			ultimaReina.despliega (g);
	}
}