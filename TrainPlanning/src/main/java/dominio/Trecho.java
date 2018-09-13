package dominio;

import java.util.ArrayList;
import java.util.Vector;

public class Trecho {
	int e1;
	int e2;
	
	// Para cada estacao de 0 a n-1 temos uma lista de trens nela:
	ArrayList<Trem> trens= new ArrayList<Trem>();
	
	public Trecho(int e1, int e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}

	public Trecho(int e1, int e2,Trem t) {
		super();
		this.e1 = e1;
		this.e2 = e2;
		trens.add(t);
	}
	
	public int getE1() {
		return e1;
	}

	public int getE2() {
		return e2;
	}

	public ArrayList<Trem> getTrens() {
		return trens;
	}

	
	
	//metodo pra adicionar trem ao trecho

	
}
