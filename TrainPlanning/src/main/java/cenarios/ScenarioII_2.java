package cenarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

import Malha.Malha;
import dominio.PTP;
import dominio.Trem;

public class ScenarioII_2 {
	// Cenario da Phase 2
	public static void  ScenarioII_2_(Vector<Trem> trens, Malha M) {
		for (int i=0; i < trens.size()-1 ; i++) 
		{
			Trem t1 = trens.get(i); //incumbente
			PTP ptp1 = t1.getPTP();
			LocalDateTime hatual =  ptp1.getHorario();
			int proxima = ptp1.getProxima();
			LocalDateTime hnew = ptp1.getHorario().plusMinutes(t1.calculoTempoTremAteEstacao(proxima, M));
			
			//intervalo do incumbemte será [hatual,hnew]
			ArrayList<Trem> opostos = trensOpostos(hatual,hnew,trens,t1,M);
			
			System.out.println("imcumbente = "+t1+"  opostos= "+opostos);
			// checar todos trens em sentido oposto q devam chegar na estacao proxima nesse intervalo

			//atualizar PTP do imcumbente com o valor do ultimo trem a chegar em k
			if(opostos.size()!=0)
			{
				
				Trem last = opostos.get(opostos.size()-1);
				PTP lPtp = last.getPTP();
				LocalDateTime hora = lPtp.getHorario().plusMinutes(last.calculoTempoTremAteEstacao(proxima, M));
							
				//adiciona novo PTP ao trem t1
				PTP p = new PTP(ptp1.getPosicao(),0,ptp1.getProxima(),hora);
				
				t1.setPTP(p);
			}
			
		}
	}
	
    public static ArrayList<Trem> trensOpostos(LocalDateTime intervalo1, LocalDateTime intervalo2, Vector<Trem> t, Trem incumbente, Malha M) {
    	
    	ArrayList<Trem> trens = new ArrayList<>();
   // 	LocalDateTime h0 = incumbente.getPercursoDoTrem().get(incumbente.getPercursoDoTrem().size()-1).getHorario();
	//	int prox0 = incumbente.getPercursoDoTrem().get(incumbente.getPercursoDoTrem().size()-1).getProxima();
		int atual0 = incumbente.getPercursoDoTrem().get(incumbente.getPercursoDoTrem().size()-1).getPosicao();
		
    	for (int i=0; i < t.size()-1 ; i++) 
		{
    		Trem t1 = t.get(i);
    		if (t1.equals(incumbente)) 
    			continue;
    		 
    		LocalDateTime h1 = t1.getPercursoDoTrem().get(t1.getPercursoDoTrem().size()-1).getHorario();
    		PTP ptp1 = t1.getPTP();
			LocalDateTime h2 = ptp1.getHorario().plusMinutes((t1.calculoTempoTremAteEstacao(ptp1.getProxima(), M)));

    		int prox1 = t1.getPercursoDoTrem().get(t1.getPercursoDoTrem().size()-1).getProxima();
    	//	int atual1 = t1.getPercursoDoTrem().get(t1.getPercursoDoTrem().size()-1).getPosicao();
    		
    		if ((atual0==prox1)&&(h1.compareTo(intervalo1)>=0)&&(h2.compareTo(intervalo2)<=0))
    		{ 
    			trens.add(t1);
    		}
		}
    	
    	return trens;    	
    }
	
	
	

}
	
