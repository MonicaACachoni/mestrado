package cenarios;

import java.time.LocalDateTime;
import java.util.Vector;

import Malha.Malha;
import Malha.StatusTrens;
import dominio.PTP;
import dominio.Trem;

public class ScenarioII_1 {	

	public static void  Scenario2_1(Vector<Trem> trens, Malha malha) {
		
		
		//verifica se tem dois trens no mesmo trecho da malha em sentido contrario (colisao frontal)
		
		for (int i=0; i < trens.size() ; i++) {
			
			Trem t1 = trens.get(i);
			
				for (int j=1; j < trens.size() ; j++) {
					Trem t2 = trens.get(j);
					if (t1.getVelocidade() < t2.getVelocidade())
					{
						
					
						if (malha.statusDePosicaoEntreDoisTrens(t1,t2)==StatusTrens.MesmaEstacao)
						{   
							System.out.println("Cenario de colisao Traseira\n"); 
							
								
						
								PTP ptp2 = t2.getPTP();
								LocalDateTime hora2 = ptp2.getHorario().plusMinutes((t2.calculoTempoTremAteEstacao(ptp2.getPosicao(), malha)));
							
								//adiciona novo PTP ao trem t2
								PTP p2 = new PTP(ptp2.getPosicao(),0,ptp2.getProxima(),hora2);
								t2.setPTP(p2);
								
								
								PTP ptp1 = t1.getPTP();
								LocalDateTime hora = hora2.plusMinutes((t1.calculoTempoTremAteEstacao(ptp1.getPosicao(), malha)));
							
								//adiciona novo PTP ao trem t1
								PTP p = new PTP(ptp1.getPosicao(),0,ptp1.getProxima(),hora);
								t1.setPTP(p);
								
							
						}
					}
				}
			}
		}			
}		

	