package com.eter.docker.controller.response.impl;

import com.eter.docker.controller.response.AvailableCommands;
import com.eter.docker.qualifier.Active;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Active
public class AvailableCommandsImpl implements AvailableCommands {

    @Value("#{PropertySplitter.map('${docker.interface.commands}')}")
    private Map<String, String> commands;

    public Map<String, String> getCommands() {
        return commands;
    }


    public void setCommands(Map<String, String> commands) {
        this.commands = commands;
    }
}
