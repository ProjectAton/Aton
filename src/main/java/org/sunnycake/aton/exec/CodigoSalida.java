package org.sunnycake.aton.exec;

public enum CodigoSalida {
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

	CodigoSalida(String message) {
		this.message = message;
	}

	abstract boolean apply(int exitCode);

	public static CodigoSalida getFor(int exitCode) {
		for (CodigoSalida status : CodigoSalida.values())
			if (status.apply(exitCode))
				return status;
		return null;
	}

	public String message() {
		return message;
	}
}
