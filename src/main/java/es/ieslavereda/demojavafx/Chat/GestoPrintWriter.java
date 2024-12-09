package es.ieslavereda.demojavafx.Chat;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class GestoPrintWriter {
    private Set<PrintWriter> printWriterSet;
    public GestoPrintWriter() {
        this.printWriterSet = new HashSet<>();
    }
    public void addPrintWriter(PrintWriter printWriter) {
        this.printWriterSet.add(printWriter);
    }

    public Set<PrintWriter> getPrintWriterSet() {
        return printWriterSet;
    }
}
