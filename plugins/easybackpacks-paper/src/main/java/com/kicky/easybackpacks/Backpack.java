package com.kicky.easybackpacks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.Serializable;
import java.util.UUID;

public interface Backpack extends Serializable {

    UUID getUUID();

    void setUUID(UUID uuid);

    void removeUUID();

    Player getPlayer();

    boolean isEasyBackpack();

    void markAsEasyBackpack(ItemMeta meta);

    int getBackpackId();

    BackpackSize getBackpackSize();

    void getBackpackContents();

    Location getLocation();

    ItemStack createItemStack();

    void addBackpackContents(ItemStack[] is);

}
