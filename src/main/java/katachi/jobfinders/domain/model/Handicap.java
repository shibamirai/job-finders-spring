package katachi.jobfinders.domain.model;

public enum Handicap implements Selectable {
	NONE(0, "非公表"),
	MENTAL(1, "精神"),
	PHYSICAL(2, "身体"),
	EDUCATIONAL(3, "療育");

	private final int code;
	private final String label;

	private Handicap(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static Handicap from(int code) {
		for (Handicap handicap : values()) {
			if (handicap.getCode() == code) {
				return handicap;
			}
		}
		throw new IllegalArgumentException("undefined : "  + code);
	}
}
