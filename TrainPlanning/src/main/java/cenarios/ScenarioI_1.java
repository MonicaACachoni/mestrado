package cenarios;

import java.time.LocalDateTime;
import java.util.Vector;

import Malha.Malha;
import dominio.AreaProibida;
import dominio.PTP;
import dominio.Trem;

public class ScenarioI_1 {


	// Phase 1
	// nesse cenario uma area proibida  definida
	// seja para algum reaparo ou manuten�ao da linha entre um per�odo de tempo
	// e um determinado xmin , xmax da malha ferroviaria

	// outra fun�ao deste cenario � levar todos trens para uma esta�ao, depois
	// de seu ponto inicial

	// com todos trens setados no Percurso em um esta��o finaliza-se a fase 1 do
	// algoritmo
	public static void Subroutine3_1(Vector<Trem> trens, Malha malha,AreaProibida proibida) {
	
		for (int i=0; i < trens.size() ; i++) 
		{
			Trem t1 = trens.get(i);
			if (t1.getPTP().getDistancia()==0) 	
			{
				Vector<LocalDateTime> hora = checaSeTemAreaProibida(proibida,t1);
				
				PTP ptp = t1.getPTP();
			
				//adiciona novo PTP ao trem t1
				if (hora.size()>0) 
				{ 
					PTP p = new PTP(ptp.getPosicao(),0,ptp.getProxima(),hora.get(1));
					t1.setPTP(p);
				}
			}
		}
	}


	// se tem area proibida tem um horario inicio e um horario fim os quais
	// determinado xInicio e xFim sao proibidos.
	// Para isso olhamos os valores de origem e destino do trem se sao ou
	// menores ao inicio do x ou maiores do fim do x
	// depois disso compara os valores do X no trecho da malha e se trem pode ir
	// ate a proxima estacao
	public static Vector<LocalDateTime> checaSeTemAreaProibida(AreaProibida proibida, Trem t) 
	{
		Vector<LocalDateTime> hora = new Vector<LocalDateTime>();;
		PTP ptp = t.getPTP();
		for (int i=0; i < proibida.getAreasProibidas().size() ; i++) 
		{
			if (ptp.getProxima()==proibida.getAreasProibidas().get(i).getV1()||
					ptp.getProxima()==proibida.getAreasProibidas().get(i).getV2())
			{
				hora.add(proibida.getAreasProibidas().get(i).getH1());
				hora.add(proibida.getAreasProibidas().get(i).getH2());
				return hora;
			}
			
		}
		
		return hora;
	}
}