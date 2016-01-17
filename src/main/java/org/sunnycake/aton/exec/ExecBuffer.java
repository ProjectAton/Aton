package org.sunnycake.aton.exec;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ExecBuffer {
	private Queue<String> lines = new ConcurrentLinkedQueue<String>();
	private int maxSize;

	public ExecBuffer(int maxSize) {
		this.maxSize = maxSize;
	}

	public void push(String line) {
		lines.add(line);
	}

	public String pop() {
		return lines.isEmpty() ? "nil" : lines.poll();
	}

	public String retornarBuffer() {
		if (lines.size() == 0)
			return "Error";
		StringBuffer stringB = new StringBuffer();
		for (String linea : lines) {
			stringB.append(linea);
			stringB.append("\n");
		}
		String string = stringB.toString();
		return string.substring(0,string.length()-1);
	}
}
