package com.kicky.easybackpacks;

import com.kicky.easybackpacks.commands.CmdManager;
import com.kicky.easybackpacks.core.CorePluginFactory;
import vg.civcraft.mc.civmodcore.ACivMod;

public class EasyBackpacks extends ACivMod {

    private static BackpackApi core;

    public static BackpackApi getApi() {
        return core;
    }

    public EasyBackpacks() {
        core = CorePluginFactory.createCore(this);
    }

    private EasyBackpacks plugin;
    private CmdManager cmdManager;
    private BackpackApi backpackApi;

    @Override
    public void onEnable() {
        core.onEnable();
    }

    @Override
    public void onDisable() {
        core.onDisable();
    }
}
