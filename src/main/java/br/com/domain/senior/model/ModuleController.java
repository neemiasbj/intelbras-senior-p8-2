package br.com.domain.senior.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.domain.senior.abstration.Device;
import br.com.domain.senior.abstration.Event;

public class ModuleController extends Device {
    private final Collection<Event> events = new ArrayList<>();
    
    private final List<ModuleController> controllerModules = new ArrayList<>();
    
    private final Collection<CardReaderDevice> cardReaderDevices = new ArrayList<>();
    
    private final Collection<InputDevice> inputDevices = new ArrayList<>();
    
    private final Collection<OutputDevice> outputDevices = new ArrayList<>();
    
    public void addEvent(Event event) {
        if (event != null) {
            this.events.add(event);
        }
        
    }
    
    public CardReaderDevice getCardReaderById(int cardReaderId) {
        Iterator<CardReaderDevice> var2 = this.cardReaderDevices.iterator();
        
        CardReaderDevice reader;
        do {
            if (!var2.hasNext()) {
                return null;
            }
            
            reader = var2.next();
        } while (reader.getId() != cardReaderId);
        
        return reader;
    }
    
    public Collection<CardReaderDevice> getCardReaderDevices() {
        return this.cardReaderDevices;
    }
    
    public Collection<Event> getEvents() {
        return this.events;
    }
    
    public Collection<InputDevice> getInputDevices() {
        return this.inputDevices;
    }
    
    public Collection<OutputDevice> getOutputDevices() {
        return this.outputDevices;
    }
    
    public Collection<ModuleController> getControllerModules() {
        return this.controllerModules;
    }
    
    public void addCardReaderDevice(CardReaderDevice device) {
        if (device == null) {
            throw new NullPointerException("O dispositivo não pode ser nulo.");
        } else {
            this.cardReaderDevices.add(device);
        }
    }
    
    public void addInputDevice(InputDevice device) {
        if (device == null) {
            throw new NullPointerException("O dispositivo não pode ser nulo.");
        } else {
            this.inputDevices.add(device);
        }
    }
    
    public void addOutputDevice(OutputDevice device) {
        if (device == null) {
            throw new NullPointerException("O dispositivo não pode ser nulo.");
        } else {
            this.outputDevices.add(device);
        }
    }
    
    public void removeModuleController(ModuleController controller) {
        if (controller == null) {
            throw new NullPointerException("O dispositivo não pode ser nulo.");
        } else {
            this.controllerModules.remove(controller);
        }
    }
    
    public void removeCardReaderDevice(CardReaderDevice reader) {
        if (reader == null) {
            throw new NullPointerException("O dispositivo não pode ser nulo.");
        } else {
            this.cardReaderDevices.remove(reader);
        }
    }
    
    public void removeInputDevice(InputDevice input) {
        if (input == null) {
            throw new NullPointerException("O dispositivo não pode ser nulo.");
        } else {
            this.inputDevices.remove(input);
        }
    }
    
    public void removeOutputDevice(OutputDevice output) {
        if (output == null) {
            throw new NullPointerException("O dispositivo não pode ser nulo.");
        } else {
            this.outputDevices.remove(output);
        }
    }
    
    public CardReaderDevice findDirectionAccess(String direction) {
        CardReaderDevice foundDevice = cardReaderDevices.stream().filter(c -> c.getReaderDirection().valueOfString(direction)).findFirst()
                                                        .orElse(null);
        
        if (foundDevice == null && !cardReaderDevices.isEmpty()) {
            return cardReaderDevices.iterator().next();
        }
        
        return foundDevice;
    }
    
}
