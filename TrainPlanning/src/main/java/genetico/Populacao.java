/* Projeto de Mestrado submetido ao IC
 * Aluno: Mônica Cachoni
 * 
 * Orientador: Guilherme Telles
 * 
 * */


package genetico;
import java.util.*;

import javax.swing.SingleSelectionModel;

import org.omg.Messaging.SyncScopeHelper;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Populacao {
	
    ArrayList<Cromossomo> populacao = new ArrayList<Cromossomo>();
    ArrayList<Cromossomo> nova_populacao = new ArrayList<Cromossomo>();
   
   static double somaAvaliacoes;
   static double chance_mutacao;
   static int numero_geracoes;
   static int tamanho_populacao;
   
   
   public void geracao() {
	nova_populacao=new ArrayList<Cromossomo>();
	int i,pai1,pai2;
	int pontoCorte1=2,pontoCorte2=2;
	
	System.out.println("Calculando nova geracao...\n");

	for(i=0;i<populacao.size();++i) {

		pai1=Selecao.roleta(populacao);
		pai2=Selecao.roleta(populacao);
		Cromossomo.crossoverDoisPontosMatriz(pontoCorte1, pontoCorte2, populacao.get(pai1), populacao.get(pai2));

	}
	moduloPopulacao();
   }
   

	void inicializaPopulacao() {
			for(int i=0;i<Populacao.tamanho_populacao;++i) {	
				this.populacao.add(new Cromossomo()); 
			}
	 }
   
   public void moduloPopulacao() {
        populacao.removeAll(populacao);
        populacao.addAll(nova_populacao);	
   }
 
   public void executa() {

	inicializaPopulacao();
	
	System.out.println("populacao="+ populacao.toString());
	
	for ( int i=0;i<Populacao.numero_geracoes;++i) {
		System.out.println("Geracao "+i+"\n");
		somaAvaliacoes = Avaliacao.avaliaTodos(populacao);
		System.out.println("SomaAvaliações = "+ somaAvaliacoes);
		geracao();
		moduloPopulacao();
	}
		
   }



/****************/
   /* Construtores */
   /****************/

   public Populacao(int num_geracoes,int tam_populacao, double prob_mut) {
   	Populacao.chance_mutacao=prob_mut;
   	Populacao.numero_geracoes=num_geracoes;
   	Populacao.tamanho_populacao=tam_populacao;
   }

   public Populacao(int tam_populacao, double prob_mut) {
   	this(60,tam_populacao,prob_mut);
   }
   
   public Populacao(double prob_mut) {
   	this(30,50,prob_mut);
   }
   
   public Populacao() {
   	this(60,50,0.001);
   } 
}

