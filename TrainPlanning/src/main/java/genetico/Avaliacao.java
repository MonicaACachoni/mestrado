/* Projeto de Mestrado submetido ao IC
 * Aluno: Mônica Cachoni
 * 
 * Orientador: Guilherme Telles
 * 
 * */

package genetico;

import java.util.ArrayList;

public class Avaliacao   {
	
	static int somaAvaliacoes = 0;
	static double avaliaTodos(ArrayList<Cromossomo> populacao) {
			
			System.out.println("Avaliando todos...\n");
			for(int i=0;i<populacao.size();++i) {
				somaAvaliacoes += populacao.get(i).custoCromo;
			}
			return somaAvaliacoes;	
	 }

	public static double getSomaAvaliacao() {
		
		return somaAvaliacoes;
	}
}
