package dominio;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.TreeSet;


public class MalhaFerroviariaComLoops {
	

	private TreeSet<Estacao> loops = new TreeSet<>();

	public MalhaFerroviariaComLoops(TreeSet<Estacao> loop) {
		this.loops.addAll(loop);
	}

	public NavigableSet<Estacao> getLoops() {
		return Collections.unmodifiableNavigableSet(loops);
	}
	
	public void setLoops(TreeSet<Estacao> loops) {
		this.loops = loops;
	}
}
