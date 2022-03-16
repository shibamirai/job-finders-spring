package katachi.jobfinders.domain.model;

public enum Gender implements Selectable {
	NONE(0, "非公表"),
	MALE(1, "男性"),
	FEMALE(2, "女性");

	private final int code;
	private final String label;

	private Gender(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static Gender from(int code) {
		for (Gender gender : values()) {
			if (gender.getCode() == code) {
				return gender;
			}
		}
		throw new IllegalArgumentException("undefined : "  + code);
	}
}
