package com.kicky.easybackpacks.holder;

import com.kicky.easybackpacks.BackpackApi;
import com.kicky.easybackpacks.BackpackSize;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;


public class BackpackInventory implements InventoryHolder {

    private final Inventory inventory;
    private final int size;

    public BackpackInventory(BackpackApi backpackApi, BackpackSize backpackSize) {
        this.size = backpackSize.toInt();
        this.inventory = backpackApi.getServer().createInventory(this, this.size, "Backpack");
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
