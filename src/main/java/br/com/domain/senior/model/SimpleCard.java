package br.com.domain.senior.model;

public class SimpleCard {
    private final long id;
    
    private String personId;
    
    private final byte type;
    
    private final byte[] readers255;
    
    private byte technology;
    
    public byte getTecnology() {
        return this.technology;
    }
    
    public void setTechnology(byte technology) {
        this.technology = technology;
    }
    
    public SimpleCard(long id, String personId, byte type, byte[] readers255, byte technology) {
        if (readers255 == null) {
            throw new NullPointerException("Array de leitoras não deve ser nulo. SimpleCard");
        } else if (readers255.length != 255) {
            throw new IllegalArgumentException("Deveriam ter vindo 255 leitoras. SimpleCard");
        } else if (type <= 0) {
            throw new IllegalArgumentException("O tipo do cartão deve ser um número acima de 0");
        } else if (id < 0L) {
            throw new IllegalArgumentException("O id do cartão não pode ser negativo");
        } else if (technology >= 0 && technology <= 3) {
            this.id = id;
            this.personId = splitSpace(personId);
            this.type = type;
            this.readers255 = readers255;
            this.technology = technology;
        } else {
            throw new IllegalArgumentException("Tecnologia do cartão não suportada");
        }
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public byte getType() {
        return this.type;
    }
    
    public byte[] getReaders() {
        return this.readers255;
    }
    
    private String getTypeDescription(byte typeCard) {
        switch (typeCard) {
            case 1:
                return "Empregado";
                
            case 2:
                return "Terceiro";
                
            case 3:
                return "Parceiro";
                
            case 4:
                return "Visitante, Grupo de Visitante";
                
            case 5:
                return "Outra Unidade";
                
            case 6:
                return "Provisório";
                
            case 7:
                return "Reponsável do Aluno";
                
            case 8:
                return "Crachá Mestre";
                
            case 9:
                return "Pacientes";
                
            case 10:
                return "Alunos";
                
            case 11:
                return "Acompanhante de Paciente";
                
            case 99:
                return "Outros";
                
            default:
                return "Tipo desconhecido de cartão";
        }
    }
    
    public String toString() {
        return "Id: " + this.id + "Tipo: " + this.getTypeDescription(this.type);
    }
    
    private String splitSpace(String personId) {
        return personId.replace(" ", "");
    }
    
}
