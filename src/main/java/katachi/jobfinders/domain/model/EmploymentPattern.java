package katachi.jobfinders.domain.model;

public enum EmploymentPattern implements Selectable {
	UNKOWN(0, "不明"),
	REGULAR(1, "正社員"),
	CONTRACTOR(2, "契約社員"),
	TEMPORARY(3, "派遣社員"),
	PARTTIME(4, "アルバイト・パート"),
	FREE(5, "フリー"),
	OTHER(6, "その他");

	private final int code;
	private final String label;

	private EmploymentPattern(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static EmploymentPattern from(int code) {
		for (EmploymentPattern employmentPattern : values()) {
			if (employmentPattern.getCode() == code) {
				return employmentPattern;
			}
		}
		throw new IllegalArgumentException("undefined : "  + code);
	}
}
