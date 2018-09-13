package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class AreaProibida {
	

	public class Area {
		
		// Positions in the railway
		private int v1; //vertice 1
		private int v2; //vertice 2
		LocalDateTime h1;
		LocalDateTime h2;
		
		public Area(int v1,int v2,LocalDateTime h1, LocalDateTime h2) {
			super();

			this.v1 = v1;
			this.v2 = v2;
			this.h1 = h1;
			this.h2 = h2;
		}

		public int getV1() {
			return v1;
		}

		public void setV1(int v1) {
			this.v1 = v1;
		}

		public int getV2() {
			return v2;
		}

		public void setV2(int v2) {
			this.v2 = v2;
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

	
	private ArrayList<Area> areasProibidas = new ArrayList<Area>();
	

	
	public void areaProibida(int v1,int v2,LocalDateTime h1, LocalDateTime h2) {
		areasProibidas.add(new Area(v1,v2,h1,h2)); 
	}
	
	public ArrayList<Area> getAreasProibidas() {
		return areasProibidas;
	}

	public void setAreasProibidas(ArrayList<Area> areasProibidas) {
		this.areasProibidas = areasProibidas;
	}


		
	
}
