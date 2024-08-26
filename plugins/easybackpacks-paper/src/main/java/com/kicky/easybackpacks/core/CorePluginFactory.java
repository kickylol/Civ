package com.kicky.easybackpacks.core;

import com.google.common.base.Preconditions;
import com.kicky.easybackpacks.*;
import com.kicky.easybackpacks.commands.CmdManager;
import org.bukkit.plugin.Plugin;

import java.util.UUID;


public class CorePluginFactory implements BackpackFactory {

    private final BackpackApi backpackApi;

    public static BackpackApi createCore(final Plugin plugin) {
        return new EasyBackpackCore(plugin);
    }

    public CorePluginFactory(final BackpackApi plugin) {
        Preconditions.checkNotNull(plugin, "plugin");
        this.backpackApi = plugin;
    }

    @Override
    public Backpack createBackpack(BackpackSize size, int shulkerId) {

        UUID uid = null;

        return new CoreBackpack(
                uid,
                size,
                shulkerId
        );
    }

    public BackpackManager createBackpackManager() {
        return new CoreBackpackManager(backpackApi, this);
    }

    public CmdManager createBackpackCommands() {
        return new CoreCmdManager(backpackApi);
    }
}
