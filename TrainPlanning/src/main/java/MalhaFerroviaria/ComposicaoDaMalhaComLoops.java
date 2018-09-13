package MalhaFerroviaria;

import java.util.SortedSet;
import java.util.TreeSet;

import dominio.Estacao;
import dominio.MalhaFerroviariaComLoops;


public  class ComposicaoDaMalhaComLoops {
	
	SortedSet<Estacao> loop  = new TreeSet<>();
	

	
	public static MalhaFerroviariaComLoops obterMalhaFerroviariaComLoops() {
		
		TreeSet<Estacao> loop = new TreeSet<>();
		
		// segmentos com a seguinte configuracao
		//	  0       10       20           30        40 km	
		//	  |------ | ------- | --------- | -------- |
		// inicio  	  K1        K2          K3         fim
		//  sul                                         norte
		
		// loop k1 
		loop.add(ComposicaoDoLoop.LOOP_1.getLoop());
		
		// loop k2 
		loop.add(ComposicaoDoLoop.LOOP_2.getLoop());
		
		// loop k3 
		loop.add(ComposicaoDoLoop.LOOP_3.getLoop());
		
		// loop k4
		loop.add(ComposicaoDoLoop.LOOP_4.getLoop());
				
		// loop k5 
		loop.add(ComposicaoDoLoop.LOOP_5.getLoop());

		
		// loop k6
		loop.add(ComposicaoDoLoop.LOOP_6.getLoop());
		return new MalhaFerroviariaComLoops(loop);
	}

}
