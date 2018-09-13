package Algoritmo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Vector;

import Malha.Malha;
import cenarios.ScenarioII_1;
import cenarios.ScenarioII_2;
import cenarios.ScenarioII_3;
import cenarios.ScenarioII_4;
import cenarios.ScenarioII_5;
import cenarios.ScenarioII_6;
import cenarios.ScenarioI_1;
import cenarios.ScenarioI_2;
import cenarios.ScenarioI_3;
import cenarios.ScenarioI_4;
import dominio.AreaProibida;
import dominio.Meeting;
import dominio.PTP;
import dominio.Trem;

public class Algoritmo5_1 {

	/*
	 * train: Nome trecho-origem-estacao-inicial trecho-origem-estacao-final
	 * distancia-estacao-inicial estacao-destino velocidade-km-por-hora
	 * horario-de-saida-localtime
	 * 
	 * # Trem azul vai da estacao 5 para a 12, se move a 32 km-por-hora: 
	 * azul 5 5 0 12 32 2018-06-15T09:44:05
	 * 
	 * # Trem verde esta entre as estacoes 6 e 7 a 2349 unidade de distÃ¢ncia da 6 e
	 * vai para a 19 a 27 unidades de velocidade: 
	 * verde 6 7 2349 19 27 2018-06-15T09:44:00
	 * 
	 * meeting: nome-do-trem nome-do-trem estacao duracao-em-minutos
	 * 
	 * meeting: azul verde 15 45
	 * 
	 * 2007-12-03T10:15:30
	 */

	// arquivo-de-malha arquivo-de-estacoes arquivo-de-trens arquivo-de-encontros
	// C:\Users\Monica\Dropbox\workspaceMonica\TrainPlanning\src\main\java\entradasDoSistema\malha1.txt
    // C:\Users\Monica\Dropbox\workspaceMonica\TrainPlanning\src\main\java\entradasDoSistema\estacoes1.txt
	// C:\Users\Monica\Dropbox\workspaceMonica\TrainPlanning\src\main\java\entradasDoSistema\trem.txt
	// C:\Users\Monica\Dropbox\workspaceMonica\TrainPlanning\src\main\java\entradasDoSistema\meeting1.txt
	
	
	//1 C:\Users\Monica\Dropbox\workspaceMonica\TrainPlanning\src\main\java\entradasDoSistema\malha1.txt
	//2 C:\Users\Monica\Dropbox\workspaceMonica\TrainPlanning\src\main\java\entradasDoSistema\estacoes1.txt 
	//3 C:\Users\Monica\Dropbox\workspaceMonica\TrainPlanning\src\main\java\entradasDoSistema\trem.txt  
	//4 C:\Users\Monica\Dropbox\workspaceMonica\TrainPlanning\src\main\java\entradasDoSistema\meeting1.txt 
	//5 C:\Users\Monica\Dropbox\workspaceMonica\TrainPlanning\src\main\java\entradasDoSistema\areasProibidas.txt
	public static void main(String[] args) throws IOException {

		// Carrega a malha:
		Malha M = new Malha(args[0], args[1]);

		// Carrega os trens:
		Vector<Trem> T = new Vector<Trem>();
		Scanner s = new Scanner(new BufferedReader(new FileReader(args[2])));

		// train: verde 6 7 2349 19 27 2018-06-15T09:44:00
		while (s.hasNext()) {
			Trem t = new Trem(s.next(), // nome
					(int) s.nextInt(), // e1
					(int) s.nextInt(), // e2
					(int) s.nextInt(), // dist1
					(int) s.nextInt(), //
					(int) s.nextInt(), //
					LocalDateTime.parse(s.next()), //hora
					(int) s.nextInt() //
					);

			T.add(t);
			PTP p = t.getPTP();

	 		if (p.getDistancia() == 0) 
	 		{
				M.entrar(t, p.getPosicao());
				System.out.println("Trem " + t.getNome()+" na estação "+ p.getPosicao());
	 		}
	 		else
	 		{
	 			M.entrarTrecho(t, p.getPosicao(),p.getProxima());
	 		}
		 
		}
		s.close();

	
		
		// Carrega os encontros planejados:
		

		s = new Scanner(new BufferedReader(new FileReader(args[3])));

		while (s.hasNext()) {
			String nome1 = s.next();
			String nome2 = s.next();
			Trem t1 = null, t2 = null, t;

			for (int i = 0; i < T.size(); i++) {
				t = T.get(i);
				if (nome1.equals(t.getNome())) {
					t1 = t;
				}
				if (nome2.equals(t.getNome())) {
					t2 = t;
				}
				if (t1!=null && t2!=null)
				{
					Meeting tie = new Meeting(t1, t2, s.nextInt(), LocalDateTime.parse(s.next()), LocalDateTime.parse(s.next()));
					t1.setTie(t1.getTie().size(), tie);
					t1 = null; t2 = null;
				}
			}

			
		}

		s.close();
		//carrega areas proibidas
		AreaProibida Proibida = new AreaProibida();

		s = new Scanner(new BufferedReader(new FileReader(args[4])));

		while (s.hasNext()) {
			int a1 = s.nextInt();
			int a2 = s.nextInt();
			LocalDateTime h1 = LocalDateTime.parse(s.next());
			LocalDateTime h2 = LocalDateTime.parse(s.next());

			Proibida.areaProibida(a1, a2, h1, h2);
		}

		s.close();
	     
//		
//		// if Scenario I.1 occurs, call Subroutine 3.1
		System.out.println("##################################################################################\n");
		System.out.println("[Log] [Scenario I.1] : \n");
		ScenarioI_1.Subroutine3_1(T, M,Proibida);
		System.out.println(T +"\n");
//
//		// if Scenario I.2 occurs, call Subroutine 3.2
		System.out.println("##################################################################################\n");
		System.out.println("[Log] [Scenario I.2] : \n");
		ScenarioI_2.Scenario1_2_(T, M);
		System.out.println(T +"\n");
//		 
//		// if Scenario I.3 occurs, call Subroutine 3.2
		System.out.println("##################################################################################\n");	
		System.out.println("[Log] [Scenario I.3] : \n");
		ScenarioI_3.Subroutine3_3(T, M);
		System.out.println(T +"\n");
//		 
//		// if Scenario I.4 occurs, call Subroutine 3.4
		System.out.println("##################################################################################\n");
		System.out.println("[Log] [Scenario I.4] : \n");
		ScenarioI_4.Subroutine4_4(T, M);
		System.out.println(T +"\n\n\n");
		 
		// if Scenario II.1 occurs, call Subroutine 3.4
		System.out.println("##################################################################################\n");
		System.out.println("[Log] [Scenario II.1] : \n");
		ScenarioII_1.Scenario2_1(T, M);
		System.out.println(T +"\n\n\n");
//		
		// if Scenario II.2 occurs, call Subroutine 3.2
		System.out.println("##################################################################################\n");
		System.out.println("[Log] [Scenario II.2] : \n");
		ScenarioII_2.ScenarioII_2_(T, M);
		
		
		System.out.println(T +"\n\n\n");
		// if Scenario II.3 occurs, call Subroutine 3.4
		System.out.println("##################################################################################\n");
		System.out.println("[Log] [Scenario II.3] : \n");
		ScenarioII_3.ScenarioII_3(T, M);
		System.out.println(T +"\n\n\n");
			
		// if Scenario II.4 occurs, call Subroutine 3.4
		System.out.println("##################################################################################\n");
		System.out.println("[Log] [Scenario II.4] : \n");
		ScenarioII_4.ScenarioII_4_(T, M);
	    System.out.println(T +"\n\n\n");
//		
//		// if Scenario II.5 occurs, call Subroutine 3.4
		System.out.println("##################################################################################\n");
		System.out.println("[Log] [Scenario II.5] : \n");
		ScenarioII_5.ScenarioII_5_(T, M);
	    System.out.println(T +"\n\n\n");	
//
//		// if Scenario II.6 occurs, call Subroutine 3.6
		System.out.println("##################################################################################\n");
		System.out.println("[Log] [Scenario II.6] : \n");
		ScenarioII_6.ScenarioII_6_(T, M);
	    System.out.println(T +"\n\n\n");
//	    
	} // main

} // classe
