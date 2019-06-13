package excecoes;

public class ValorAcimadoPermitidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5;
	private boolean premium;
	public boolean isPremium() {
		return premium;
	}
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		double valorPermitido=0;
		if(this.premium) {
			valorPermitido = 2500;
		}else {
			valorPermitido = 200;
		}
		return "Valor acima do permitido! "+"\n"+"Limite maximo permitido: R$"+valorPermitido;
	}
	

}
