package br.com.loris.messenger.domain;

public enum MessengerLogOperation {
	INPUT(1),
	OUTPUT(2);
	
	private int code;
	
	private MessengerLogOperation(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static MessengerLogOperation valueOf(int code) {
		for (MessengerLogOperation value : MessengerLogOperation.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Operation code");
	}
}
