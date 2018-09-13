package cenarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

import Malha.Malha;
import dominio.PTP;
import dominio.Trem;

public class ScenarioII_4 {

	static Trem tremN1;

	
	
	// 
	public static void  ScenarioII_4_(Vector<Trem> trens, Malha M) {
			
		for (int i=0; i < trens.size()-1 ; i++) 
		{
			Trem t1 = trens.get(i);
			PTP ptp1 = t1.getPTP();
			int atual1 = ptp1.getPosicao();

			
			//chega se tem alguma parada programada pro trem naquela estação
			if (t1.getTie().size()>0)
			{
				LocalDateTime hora = t1.getTie().get(0).getH2();
				
				int disp = M.espacoDisponivelEstacao(atual1);
				
				for (int j=i; j < trens.size()-1 ; j++) 
				{
					Trem t2 = trens.get(j);
					PTP ptp2 = t2.getPTP();
					int local2 = ptp2.getProxima();
					if (atual1==local2 && disp==0) {
						//pega os vizinhos de t1 para ver se tem espaço disponivel pra ele ficar parado
						ArrayList<Integer> estacao = M.neighborStation(atual1);
						
						for(int l = 0; l < estacao.size(); ++l) {

				           	int e = M.espacoDisponivelEstacao(l);
				           	
				           	// colocar o trem que está em espera em algum dos vizinhos
				           	if (e>0) {
				           		LocalDateTime hnew = ptp1.getHorario().plusDays(t1.calculoTempoTremAteEstacao(e, M));
				           		PTP p = new PTP(e,0,ptp1.getProxima(),hnew);
								t1.setPTP(p);
								break;
				           	}
				           	
						
						}
					}
				}
			
			
			}
		}	
	}
}
