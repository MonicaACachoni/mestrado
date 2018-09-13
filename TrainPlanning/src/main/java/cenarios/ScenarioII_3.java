package cenarios;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Vector;

import Malha.Malha;
import dominio.PTP;
import dominio.Trem;



public class ScenarioII_3 {

	//este cenario verifica se antes de sair um trem tera outro no caminho
	public static void ScenarioII_3(Vector<Trem> trens, Malha m) { 
		
		for (int i=0; i < trens.size()-1 ; i++) 
		{
			Trem t1 = trens.get(i);
			PTP ptp1 = t1.getPTP();
			LocalDateTime hora1 = ptp1.getHorario();
			
			for (int j=1; j < trens.size() ; j++) 
			{
				Trem t2 = trens.get(j);
				PTP ptp2 = t2.getPTP();
				LocalDateTime hora2 = ptp2.getHorario().plusDays(t2.calculoTempoTremAteEstacao(t2.getPTP().getProxima(), m));
				
				if((hora2.compareTo(hora1)>=1)&&(t1.getPTP().getPosicao()==t2.getPTP().getProxima()))
				{
					System.out.println("teste cenario 2 acontece\n");
					
					hora1 = hora1.plus(Duration.between(hora2, hora1));
					
					PTP p = new PTP(ptp1.getPosicao(),0,ptp1.getProxima(),hora1);
					t1.setPTP(p);
				}
			}
		}
			
		

		return;	
	}
	
	

}
		
