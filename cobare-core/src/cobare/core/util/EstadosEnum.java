package cobare.core.util;

public enum EstadosEnum {
	
	CODE_NULL(1),
	CODE_AC(2),
	CODE_AL(3),
	CODE_AP(4),
	CODE_AM(5),
	CODE_BA(6),
	CODE_CE(7),
	CODE_DF(8),
	CODE_ES(9),
	CODE_GO(10),
	CODE_MA(11),
	CODE_MT(12),
	CODE_MS(13),
	CODE_MG(14),
	CODE_PA(15),
	CODE_PB(16),
	CODE_PR(17),
	CODE_PE(18),
	CODE_PI(19),
	CODE_RJ(20),
	CODE_RN(21),
	CODE_RS(22),
	CODE_RO(23),
	CODE_RR(24),
	CODE_SC(25),
	CODE_SP(26),
	CODE_SE(27),
	CODE_TO(28);
	
	private final int codigo;
	EstadosEnum(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}

}
