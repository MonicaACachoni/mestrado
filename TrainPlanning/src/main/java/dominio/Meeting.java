package dominio;

import java.time.LocalDateTime;

public class Meeting {
	
	Trem t1;
	Trem t2;
	int estacao;
	LocalDateTime h1;
	LocalDateTime h2;
	public Meeting (Trem t1, Trem t2, int estacao, LocalDateTime horaInicio, LocalDateTime horaFim) {
			super();
			this.t1 = t1;
			this.t2 = t2;
			this.estacao = estacao;
			this.h1 = horaInicio;
			this.h2 = horaFim;
		}

		public Trem getT1() {
			return t1;
		}

		public void setT1(Trem t1) {
			this.t1 = t1;
		}

		public Trem getT2() {
			return t2;
		}

		public void setT2(Trem t2) {
			this.t2 = t2;
		}

		public int getEstacao() {
			return estacao;
		}

		public void setEstacao(int estacao) {
			this.estacao = estacao;
		}

		public LocalDateTime getH1() {
			return h1;
		}

		public void setH1(LocalDateTime h1) {
			this.h1 = h1;
		}

		public LocalDateTime getH2() {
			return h2;
		}

		public void setH2(LocalDateTime h2) {
			this.h2 = h2;
		}
		
	}

