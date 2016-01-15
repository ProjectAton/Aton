package org.sunnycake.aton.exec;

import java.util.ArrayList;
import java.util.List;

public class ExecBuffer {
	private List<String> lines = new ArrayList<String>();
	private int maxSize;

	public ExecBuffer(int maxSize) {
		this.maxSize = maxSize;
	}

	public void push(String line) {
		lines.add(line);
		if (lines.size() > maxSize)
			lines.remove(0);
	}

	public String pop() {
		return lines.isEmpty() ? "nil" : lines.remove(maxSize);
	}

	public String retornarBuffer() {
		StringBuffer stringB = new StringBuffer();
		for (String linea : lines) {
			stringB.append(linea);
			stringB.append("\n");
		}
		String string = stringB.toString();
		return string.substring(0,string.length()-1);
	}
}
