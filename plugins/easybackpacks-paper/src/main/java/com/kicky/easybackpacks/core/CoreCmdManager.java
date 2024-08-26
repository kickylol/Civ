package com.kicky.easybackpacks.core;

import com.kicky.easybackpacks.BackpackApi;
import com.kicky.easybackpacks.commands.CmdManager;
import com.kicky.easybackpacks.commands.CreateCmd;
import vg.civcraft.mc.civmodcore.commands.CommandManager;

public final class CoreCmdManager extends CommandManager implements CmdManager {

    private final BackpackApi backpackApi;

    public CoreCmdManager(BackpackApi backpackApi) {
        super(backpackApi);
        this.backpackApi = backpackApi;
        registerCommands();
    }

    public void registerCommands() {
        registerCommand(new CreateCmd(backpackApi));
    }


}
