/* Projeto de Mestrado submetido ao IC
 * Aluno: Mônica Cachoni
 * 
 * Orientador: Guilherme Telles
 * 
 * */

package genetico;

import java.util.ArrayList;
import java.util.Random;

public class Selecao {

	public static int roleta(ArrayList<Cromossomo> populacao) {
		//selecao do indice de pais aleatoriamente
	    	int indice;
	    	Random rg = new Random();
	        indice = rg.nextInt(populacao.size());
	    	System.out.println("indice escolhido = " + indice);
	    	return indice;
	    }
}
