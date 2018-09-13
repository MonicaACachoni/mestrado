package cenarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

import Malha.Malha;
import dominio.PTP;
import dominio.Trem;

public class ScenarioII_6 {
	
	public static void  ScenarioII_6_(Vector<Trem> T, Malha M) {
		
		for (int i=0; i < T.size()-1 ; i++) 
		{
			Trem t1 = T.get(i);
			PTP ptp1 = t1.getPTP();
			int proxima = ptp1.getProxima();
			int terminal = t1.getDestino();
			if (proxima==terminal)
			{
				int disp = M.espacoDisponivelEstacao(terminal);
				if (disp>0)
				{
					LocalDateTime hora = ptp1.getHorario().plusMinutes((t1.calculoTempoTremAteEstacao(ptp1.getPosicao(), M)));
				
					//adiciona novo PTP ao trem t2
					PTP p = new PTP(ptp1.getProxima(),hora);
					t1.setPTP(p);
					M.entrar(t1, ptp1.getProxima());
					M.sair(t1, t1.getPercursoDoTrem().get(t1.getPercursoDoTrem().size()-1).getPosicao());
				}
				else 
				{
					ArrayList<Trem> trens = M.getOcupacao(proxima);
					
					LocalDateTime hora2 = trens.get(0).getPercursoDoTrem().get( trens.get(0).getPercursoDoTrem().size()-1).getHorario();
		
					
					//adiciona novo PTP ao trem t2
					PTP p = new PTP(ptp1.getPosicao(),0,ptp1.getProxima(),hora2);
					t1.setPTP(p);
					
				}
			}
			

			
			
					
		}
	}

}
