package com.kicky.easybackpacks.core;

import com.kicky.easybackpacks.Backpack;
import com.kicky.easybackpacks.BackpackSize;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.util.UUID;

/**
 *  The shulker data type.
 */

final class CoreBackpack implements Backpack {

    @Serial
    private static final long serialVersionUID = 1L;
    private UUID uid;
    private final BackpackSize backpackSize;
    private final int shulkerId;

    public CoreBackpack(
            UUID uid,
            final BackpackSize backpackSize,
            int shulkerId)
    {
        this.uid = uid;
        this.backpackSize = backpackSize;
        this.shulkerId = shulkerId;
    }


    @Override
    public UUID getUUID() {
        return uid;
    }

    @Override
    public void setUUID(UUID uuid) {
        Bukkit.getLogger().info("uuid saved");
        this.uid = uuid;
    }

    @Override
    public void removeUUID() {
        Bukkit.getLogger().info("uuid removed");
        this.uid = null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public boolean isEasyBackpack() {
        return false;
    }


    @Override
    public void markAsEasyBackpack(
        final @NotNull ItemMeta meta) {

    }

    @Override
    public int getBackpackId() {
        return shulkerId;
    }

    @Override
    public BackpackSize getBackpackSize() {
        return backpackSize;
    }

    @Override
    public void getBackpackContents() {

    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public ItemStack createItemStack() {
        return null;
    }

    @Override
    public void addBackpackContents(ItemStack[] is) {

    }
}
