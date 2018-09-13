package Malha;

public enum StatusTrens {
	
	ColisaoTraseira("Mesmo Trecho, Mesma Direcao", 1),
	ColisaoFrontal("Mesmo Trecho, Direcao Contraria", 2),
	EstAdj("Estacoes Adjacentes", 3),
	MesmaEstacao("Mesma Estacao", 4),
	Afastamento("Afastamento", 5),
	Outros("Outra coisa", 6);

	public int status;
	
	private StatusTrens(String nome, Integer status) {
		this.status = status;
	}
	
}
