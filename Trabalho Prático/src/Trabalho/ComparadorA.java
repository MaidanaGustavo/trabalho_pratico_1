package Trabalho;

import java.util.Comparator;

public class ComparadorA implements Comparator<Conta> {

	@Override
	public int compare(Conta o1, Conta o2) {
		if(o1.getAgencia().equals(o2.getAgencia())) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}else {
			return o1.getAgencia().getNumero().compareTo(o2.getAgencia().getNumero());
		}
		
	}

}
