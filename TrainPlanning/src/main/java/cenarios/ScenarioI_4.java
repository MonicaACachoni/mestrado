package cenarios;

import java.time.LocalDateTime;
import java.util.Vector;

import Malha.Malha;
import dominio.PTP;
import dominio.Trem;

public class ScenarioI_4 {

	//leva os trens ate cada estacao para inicio da fase 2
	public static void  Subroutine4_4(Vector<Trem> trens, Malha malha) {
	
	
		
		for (int i=0; i < trens.size() ; i++) 
		{
			Trem t1 = trens.get(i);
			
			if (t1.getPTP().getDistancia()>0) 
			{		
				if (malha.temCapacidade(t1.getPTP().getProxima())) 
				{
					System.out.println("cenario 4.1 - colocando todos trens em estacoes quando tem capacidade\n\n"+t1.getNome());
					PTP ptp1 = t1.getPTP();
					LocalDateTime hora = ptp1.getHorario().plusMinutes((t1.calculoTempoTremAteEstacao(ptp1.getPosicao(), malha)));
				
					//adiciona novo PTP ao trem t2
					PTP p = new PTP(ptp1.getPosicao(),0,ptp1.getProxima(),hora);
					t1.setPTP(p);
				}
			}
		}
	}
}