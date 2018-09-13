package MalhaFerroviaria;

import java.math.BigDecimal;

import dominio.Estacao;

public enum ComposicaoDoLoop {
		LOOP_1("K1", BigDecimal.valueOf(0d),2),
		LOOP_2("K2", BigDecimal.valueOf(10d),2),
		LOOP_3("K3", BigDecimal.valueOf(20d),2),
		LOOP_4("K4", BigDecimal.valueOf(30d),2),
		LOOP_5("K5", BigDecimal.valueOf(40d),2),
		LOOP_6("K6", BigDecimal.valueOf(50d),2);

		private Estacao loop;
		
		ComposicaoDoLoop(String nomeLoop, BigDecimal posicao, Integer capacidade) {
			loop = new Estacao(nomeLoop, posicao,capacidade);
		}
		
		public Estacao getLoop() {
			return this.loop;
		}
}
