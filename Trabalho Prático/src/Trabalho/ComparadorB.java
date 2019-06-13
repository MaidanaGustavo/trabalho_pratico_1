package Trabalho;

import java.util.Comparator;

public class ComparadorB implements Comparator<Conta> {

	@Override
	public int compare(Conta o1, Conta o2) {
		if(o1.getCliente().equals(o2.getCliente())){
			if(o1 instanceof Facil && o2 instanceof Corrente || o1 instanceof Facil && o2 instanceof Poupanca) {
				return -1;
			}else if(o2 instanceof Facil && o1 instanceof Corrente || o2 instanceof Facil && o1 instanceof Poupanca) {
				return 1;
			}else if(o1 instanceof Corrente && o2 instanceof  Poupanca) {
				return -1;
			}else if(o2 instanceof Corrente && o1 instanceof  Poupanca) {
				return 1;
			}else {
				return 0;
			}
		}
		return o1.getCliente().getNome().compareTo(o2.getCliente().getNome());
	}

}
