/* Projeto de Mestrado submetido ao IC
 * Aluno: Mônica Cachoni
 * 
 * Orientador: Guilherme Telles
 * 
 * */

package genetico;

import java.util.Random;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Cromossomo {
	Table<Integer, Integer, Byte> cromo =  HashBasedTable.create();
	Integer custoCromo;
	
	public static  Cromossomo crossoverUmPonto(Cromossomo pai1,Cromossomo pai2) {
		Cromossomo retorno = null;

		return (retorno);
	}

	public Cromossomo crossoverDoisPontos(Cromossomo outroPai) {
	
		Cromossomo retorno = null;

		return (retorno);
	}

	public Cromossomo crossoverUniforme(Cromossomo outroPai) {
		String aux1 = "";
		Cromossomo retorno = null;

		return (retorno);
	}
	
	
	public static Cromossomo mutacao(double chance, Cromossomo filho) {
		   int i;


		return filho;
		}

	static void crossoverDoisPontosMatriz(int k1,int k2,Cromossomo plan1, Cromossomo plan2){
		
		

		  Table<Integer, Integer, Byte> filho1 =  HashBasedTable.create();
	      Table<Integer, Integer, Byte> filho2 =  HashBasedTable.create();
	      for ( int i= 0; i < k1; i++)
	        {
	        	for ( int j= 0;j < k2; j++)
	            {
	        		if ((i<k1)&&(j<k1)&&(i<k2)&&(j<k2))
	        		{
	        			//System.out.println("morrendo 1 2 3 ..."+ plan1.cromo.get(i, j));
	        			filho1.put(i, j,plan1.cromo.get(i, j));
	        			filho2.put(i, j,plan2.cromo.get(i, j));
	        		}
	        		else
	        		{
	        			filho1.put(i, j,plan2.cromo.get(i, j));
	        			filho2.put(i, j,plan1.cromo.get(i, j));
	           		}
	
	            }
	        }
      
	      System.out.println("Filho 1 = "+filho1.toString());
	      System.out.println("Filho 2 = "+filho2.toString());
	}
	
	@Override
	public String toString() {
		return "Cromossomo [custoCromo=" + custoCromo + "]";
	}

	void inicializaCromossomo(int trens,int vias) {
	    for ( int i= 0; i < trens; i++)
	    {
	    	for ( int j= 0;j < vias; j++)
	        {
	    		Random rg = new Random();
	            Byte bin = (byte) rg.nextInt(2);
	            custoCromo = rg.nextInt(10);
	            this.cromo.put(i, j, bin);
	        }
	    }
	    //System.out.println("Cromossomo gerado = "+ cromo.toString());
	}
	
	
	 
	/****************/
	/* Construtores */
	/****************/

	public Cromossomo(int trens,int vias) {
		inicializaCromossomo(trens,vias);
	}

	public Cromossomo() {
		this(4,4);
	}

}