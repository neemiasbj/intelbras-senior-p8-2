package br.com.domain.senior.model;

public final class BiometricTemplate {
    private BiometricTechnology technology;
    
    private byte[] template;
    
    public BiometricTemplate(BiometricTechnology bioTech, byte[] template) {
        this.setTechnology(bioTech);
        this.setTemplate(template);
    }
    
    private void setTechnology(BiometricTechnology bioTech) {
        if (bioTech == null) {
            throw new NullPointerException("A tecnologia da template biométrica não pode ser nula");
        } else {
            this.technology = bioTech;
        }
    }
    
    private void setTemplate(byte[] template) {
        if (template == null) {
            throw new NullPointerException("A template biométrica não pode ser nula.");
        } else {
            int length = template.length;
            byte[] templateCopy = new byte[length];
            System.arraycopy(template, 0, templateCopy, 0, length);
            this.template = templateCopy;
        }
    }
    
    public BiometricTechnology getTechnology() {
        return this.technology;
    }
    
    public byte[] getTemplate() {
        int length = this.template.length;
        byte[] templateCopy = new byte[length];
        System.arraycopy(this.template, 0, templateCopy, 0, length);
        return templateCopy;
    }
    
    public String toString() {
        // int maxValue = true;
        StringBuilder sb = new StringBuilder();
        byte[] data = this.getTemplate();
        int minor = Math.min(data.length, 15);
        
        for (int i = 0; i < minor; ++i) {
            sb.append(String.format("%02X ", data[i]));
        }
        
        if (data.length > 15) {
            sb.append("...");
        }
        
        return sb.toString();
    }
    
}
