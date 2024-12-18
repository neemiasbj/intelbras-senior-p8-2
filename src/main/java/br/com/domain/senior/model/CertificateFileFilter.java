package br.com.domain.senior.model;

import java.io.File;
import java.util.Locale;

import javax.swing.filechooser.FileFilter;

public class CertificateFileFilter extends FileFilter {
    public String getDescription() {
        return "Certificado digital (*.cer)";
    }
    
    public boolean accept(File f) {
        return f.getAbsoluteFile().toString().toUpperCase(Locale.getDefault()).endsWith(".CER") || f.isDirectory();
    }
    
}
