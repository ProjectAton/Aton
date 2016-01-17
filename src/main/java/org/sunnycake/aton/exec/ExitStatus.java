package org.sunnycake.aton.exec;

public enum ExitStatus {
	SUCCESS("Hecho") {

		@Override
		boolean apply(int exitCode) {
			return exitCode == 0;
		}

	},
	ERROR("Hecho con error") {

		@Override
		boolean apply(int exitCode) {
			return exitCode > 0;
		}

	},
	NONE("Hecho con estado de salida") {

		@Override
		boolean apply(int exitCode) {
			return exitCode < 0;
		}
	};

	private String message;

	ExitStatus(String message) {
		this.message = message;
	}

	abstract boolean apply(int exitCode);

	public static ExitStatus getFor(int exitCode) {
		for (ExitStatus status : ExitStatus.values())
			if (status.apply(exitCode))
				return status;
		return null;
	}

	public String message() {
		return message;
	}
}
