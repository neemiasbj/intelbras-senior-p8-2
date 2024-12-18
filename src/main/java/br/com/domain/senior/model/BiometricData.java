package br.com.domain.senior.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BiometricData {
    private final BiometricTechnology technology;
    
    private short bioSecurityLevel;
    
    private final List<BiometricTemplate> templates;
    
    public BiometricData(BiometricTechnology technology) {
        if (technology == null) {
            throw new NullPointerException("Não é possível criar dados biométricos de uma tecnologia nula para uma pessoa.");
        } else {
            this.technology = technology;
            this.templates = new ArrayList<>(5);
        }
    }
    
    public BiometricTechnology getTechnology() {
        return this.technology;
    }
    
    public void setBioSecurityLevel(short level) {
        this.bioSecurityLevel = level;
    }
    
    public short getBioSecurityLevel() {
        return this.bioSecurityLevel;
    }
    
    public void addTemplate(BiometricTemplate template) {
        if (template == null) {
            throw new NullPointerException("A template biométrica não pode ser nula");
        } else if (!template.getTechnology().equals(this.getTechnology())) {
            throw new IllegalArgumentException("A template biométrica deveria ser da mesma tecnologia");
        } else {
            this.templates.add(template);
        }
    }
    
    public List<BiometricTemplate> getTemplates() {
        return Collections.unmodifiableList(this.templates);
    }
    
    public void clearTemplates() {
        this.templates.clear();
    }
    
    public String toString() {
        return String.format("BiometricData(tech: %s, numOfTemplates: %d)", this.getTechnology(), this.getTemplates().size());
    }
    
}
