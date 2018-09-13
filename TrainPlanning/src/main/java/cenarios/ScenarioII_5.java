package cenarios;

import java.time.LocalDateTime;
import java.util.Vector;

import Malha.Malha;
import dominio.PTP;
import dominio.Trem;


//Planned Meet ( conhecida como Tie nos meios ferroviários
// 2 trens planejam se encontram por algum motivo
public class ScenarioII_5 {
	static Trem tremN1;
	
	public static void ScenarioII_5_(Vector<Trem> trens, Malha M) {
		
		
		for (int i=0; i < trens.size()-1 ; i++) 
		{
			Trem t1 = trens.get(i);
			PTP ptp1 = t1.getPTP();
			
			//chega se tem alguma parada programada pro trem naquela estação
			if (t1.getTie().size()>0 && t1.getTie().get(0).getEstacao()==ptp1.getPosicao())
			{
				LocalDateTime hora = t1.getTie().get(0).getH2();
				
				PTP p = new PTP(ptp1.getPosicao(),0,ptp1.getProxima(),hora);
				t1.setPTP(p);
			
			}
	
		}
	
	
	}
}
	

