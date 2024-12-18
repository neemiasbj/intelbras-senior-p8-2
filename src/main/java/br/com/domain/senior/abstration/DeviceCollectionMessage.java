package br.com.domain.senior.abstration;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import br.com.domain.senior.model.BiometricTechnology;
import br.com.domain.senior.model.CardReaderDevice;
import br.com.domain.senior.model.InputDevice;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.model.ModuleController;
import br.com.domain.senior.model.OutputDevice;

public abstract class DeviceCollectionMessage extends HeaderMessage {
    private final Collection<ManagerDevice> mngDevices = new ArrayList<>();
    
    private final Collection<BiometricTechnology> bioTechs = new ArrayList<>();
    
    protected DeviceCollectionMessage(byte msgNumber, byte messageType, byte messageId) {
        super(msgNumber, messageType, messageId);
    }
    
    public final Collection<BiometricTechnology> getBioTechnologies() {
        return this.bioTechs;
    }
    
    public final Collection<ManagerDevice> getDevices() {
        return this.mngDevices;
    }
    
    public final ByteBuffer encode() {
        return null;
    }
    
    public final void addBioTechnology(BiometricTechnology bioTechnology) {
        if (bioTechnology == null) {
            throw new NullPointerException("Não pode armazenar uma tecnologia biométrica nula.");
        } else {
            this.bioTechs.add(bioTechnology);
        }
    }
    
    public final void addDevice(Device device, int parentId) throws IllegalStateException {
        ManagerDevice found;
        if (device.getClass().equals(ManagerDevice.class)) {
            found = (ManagerDevice) device;
            this.mngDevices.add(found);
            device.setParent(found);
        } else if (device.getClass().equals(ModuleController.class)) {
            found = (ManagerDevice) this.findById(parentId);
            if (found == null) {
                throw new IllegalStateException("Não há manager device com id=" + parentId
                                                + ". Não foi possivel encontrar o dispositivo pai desse módulo controlador");
            }
            
            found.getControllerModules().add((ModuleController) device);
            device.setParent(found);
        } else {
            found = (ManagerDevice) this.findById(parentId);
            if (found == null) {
                throw new IllegalStateException("Não foi possivel encontrar o dispositivo pai. ParentId=" + parentId);
            }
            
            if (device.getClass().equals(CardReaderDevice.class)) {
                found.addCardReaderDevice((CardReaderDevice) device);
            } else if (device.getClass().equals(InputDevice.class)) {
                found.addInputDevice((InputDevice) device);
            } else if (device.getClass().equals(OutputDevice.class)) {
                found.addOutputDevice((OutputDevice) device);
            }
            
            device.setParent(found);
        }
        
    }
    
    public final BiometricTechnology findBioTechnologyById(int id) {
        Iterator<BiometricTechnology> var2 = this.bioTechs.iterator();
        
        BiometricTechnology bioTech;
        do {
            if (!var2.hasNext()) {
                return null;
            }
            
            bioTech = var2.next();
        } while (bioTech.getId() != id);
        
        return bioTech;
    }
    
    public final ModuleController findById(int id) {
        Iterator<ManagerDevice> var2 = this.mngDevices.iterator();
        
        while (var2.hasNext()) {
            ManagerDevice manager = var2.next();
            if (manager.getId() == id) {
                return manager;
            }
            
            Collection<ModuleController> controllerModules = manager.getControllerModules();
            Iterator<ModuleController> var5 = controllerModules.iterator();
            
            while (var5.hasNext()) {
                ModuleController controllerModule = var5.next();
                if (controllerModule.getId() == id) {
                    return controllerModule;
                }
            }
        }
        
        return null;
    }
    
}
