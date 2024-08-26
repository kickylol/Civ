package com.kicky.easybackpacks.core;

import com.google.common.base.Preconditions;
import com.kicky.easybackpacks.*;
import com.kicky.easybackpacks.events.BackpackCreationEvent;
import com.kicky.easybackpacks.holder.BackpackHolder;
import com.kicky.easybackpacks.holder.BackpackInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.*;

final class CoreBackpackManager implements BackpackManager {

    private final BackpackApi backpackApi;
    private final BackpackFactory backpackFactory;

    private final Map<String, ItemStack[]> backpackInventoryData = new HashMap<String, ItemStack[]>();


    public CoreBackpackManager(final BackpackApi backpackApi, final BackpackFactory backpackFactory) {
        Preconditions.checkNotNull(backpackApi, "shulkerApi");
        Preconditions.checkNotNull(backpackFactory, "backpackFactory");

        this.backpackApi = backpackApi;
        this.backpackFactory = backpackFactory;
    }

    @Override
    public void loadBackpacks() {

    }

    @Override
    public void openBackpackInventory(Player player, Backpack backpack) {

        BackpackInventory backpackInventory = new BackpackInventory(backpackApi, backpack.getBackpackSize());

        if (backpackInventoryData.containsKey(player.getUniqueId().toString()) && (backpack.getUUID().equals(player.getUniqueId()))) {
            backpackInventory.getInventory().setContents(backpackInventoryData.get(player.getUniqueId().toString()));
            player.openInventory(backpackInventory.getInventory());
            Bukkit.getLogger().info("inventory open with key");
            return;
        }
        player.openInventory(backpackInventory.getInventory());
        Bukkit.getLogger().info("inventory open");
    }

    @Override
    public void closeBackpackInventory(String uid, BackpackInventory backpackInventory, Backpack backpack, ItemStack item) {

        ItemMeta meta = item.getItemMeta();

        if (backpackInventory.getInventory().isEmpty()){
            backpackInventoryData.remove(uid);
            backpack.removeUUID();
            meta.getPersistentDataContainer().set(backpackApi.backpackKey(), new BackpackDataType(), backpack);
            item.setItemMeta(meta);
            return;
        }

        backpackInventoryData.put(uid, backpackInventory.getInventory().getContents());
    }

    @Override
    public void dropBackpackInventory(String uid, BackpackInventory backpackInventory) {

    }

    @Override
    public List<ItemStack> getBackpackItems(String uid) {
        ItemStack[] itemStacksArray = backpackInventoryData.get(uid);
        if (itemStacksArray != null) {
            return Arrays.asList(itemStacksArray);
        } else {
            return Collections.emptyList(); // Return an empty list if the uid does not exist
        }
    }

    @Override
    public Map<String, ItemStack[]> getBackpackInventoryData() {
        return backpackInventoryData;
    }

    @Override
    public Backpack makeBackpack(BackpackHolder holder) {
        return null;
    }


    @Override
    public Backpack makeBackpack(Player player, BackpackSize backpackSize) {

        final Backpack backpack = backpackFactory.createBackpack(backpackSize, createUniqueShulkerId());

        BackpackCreationEvent e = new BackpackCreationEvent(backpack, player);
        Bukkit.getPluginManager().callEvent(e);
        if (e.isCancelled()) {
            return null;
        }

        return backpack;
    }

    @Override
    public Backpack getBackpack(Player player) {
        return null;
    }

    @Override
    public Backpack getBackpack(UUID uid) {
        return null;
    }

    @Override
    public Collection<Backpack> getBackpacks() {
        return List.of();
    }

    @Override
    public boolean doesPlayerHaveBackpack(Player player) {
        return backpackInventoryData.containsKey(player.getUniqueId().toString());
    }

    @Override
    public boolean doesPlayerHaveBackpack(String uid) {
        return backpackInventoryData.containsKey(uid);
    }

    @Override
    public ItemStack makeBackpackItemStack(Backpack backpack) {
        ItemStack easyBackpack = new ItemStack(Material.SHULKER_BOX);
        ItemMeta meta = easyBackpack.getItemMeta();
        BackpackSize backpackSize = backpack.getBackpackSize();

        if (backpackSize == BackpackSize.SMALL) {
            meta.setDisplayName(ChatColor.GOLD + "Small Backpack");
        }
        if (backpackSize == BackpackSize.MEDIUM) {
            meta.setDisplayName(ChatColor.GOLD + "Medium Backpack");
        }
        if (backpackSize == BackpackSize.LARGE) {
            meta.setDisplayName(ChatColor.GOLD + "Large Backpack");
        }

        NamespacedKey key = new NamespacedKey(backpackApi, "easy_backpack");
        meta.getPersistentDataContainer().set(key, new BackpackDataType(), backpack);

        easyBackpack.setItemMeta(meta);
        return easyBackpack;
    }

    @Override
    public boolean getBackpackFromItemStack(ItemStack is) {
        return backpackContainer(is).has(backpackKey(), new BackpackDataType());
    }

    @Override
    public PersistentDataContainer backpackContainer(ItemStack is) {
        return is.getItemMeta().getPersistentDataContainer();
    }

    @Override
    public NamespacedKey backpackKey() {
        return new NamespacedKey(backpackApi, "easy_backpack");
    }

    private int createUniqueShulkerId() {
        Random rand = new Random();
        int shulkerId = 0;

        while(shulkerId == 0) {
            shulkerId = rand.nextInt(Integer.MAX_VALUE >> 1);
        }

        return shulkerId;
    }

}
