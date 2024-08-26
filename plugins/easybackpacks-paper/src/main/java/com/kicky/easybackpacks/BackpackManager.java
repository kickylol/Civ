package com.kicky.easybackpacks;

import org.bukkit.inventory.ItemStack;

public interface BackpackManager extends BackpackAccess {

    void loadBackpacks();

    ItemStack makeBackpackItemStack(Backpack backpack);

}
