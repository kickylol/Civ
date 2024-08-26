package com.kicky.easybackpacks;

import org.bukkit.plugin.Plugin;

public interface BackpackApi extends Plugin, BackpackAccess {

    BackpackManager getBackpackManager();
}
