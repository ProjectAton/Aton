package org.sunnycake.aton.exec;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * BUffer de salida para la sesión SSH
 * @author camilo
 */
public class ExecBuffer {

    private final Queue<String> lines = new ConcurrentLinkedQueue<>();
    private final int maxSize;

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
        if (lines.isEmpty()) {
            return "Error";
        }
        StringBuilder stringB = new StringBuilder();
        for (String linea : lines) {
            stringB.append(linea);
            stringB.append("\n");
        }
        String string = stringB.toString();
        return string.substring(0, string.length() - 1);
    }
}
