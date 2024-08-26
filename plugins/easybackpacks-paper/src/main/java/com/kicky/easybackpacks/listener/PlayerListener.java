package com.kicky.easybackpacks.listener;

import com.kicky.easybackpacks.Backpack;
import com.kicky.easybackpacks.BackpackApi;
import com.kicky.easybackpacks.core.BackpackDataType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PlayerListener implements Listener {

    private final BackpackApi backpackApi;

    public PlayerListener(BackpackApi backpackApi) {
        this.backpackApi = backpackApi;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!e.hasItem())
            return;
        if (e.getItem().getType() != Material.SHULKER_BOX)
            return;
        if (!e.getItem().hasItemMeta())
            return;

        if(backpackApi.getBackpackFromItemStack(e.getItem())) {
            if (e.getAction().isRightClick()) {
                Backpack info = backpackApi.backpackContainer(e.getItem()).get(backpackApi.backpackKey(), new BackpackDataType());

                backpackApi.openBackpackInventory(e.getPlayer(), info);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDrop(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getType() != Material.SHULKER_BOX)
            return;
        if (!e.getItemDrop().getItemStack().hasItemMeta())
            return;

        if(backpackApi.getBackpackFromItemStack(e.getItemDrop().getItemStack())) {
            Backpack info = backpackApi.backpackContainer(e.getItemDrop().getItemStack()).get(backpackApi.backpackKey(), new BackpackDataType());
            if (info.getUUID() != null) {
                e.getPlayer().sendMessage("You cannot drop a backpack with items!");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Bukkit.getLogger().info("player ded");
        List<ItemStack> drops = e.getDrops();
        String uid = e.getPlayer().getUniqueId().toString();

        for (ItemStack item : drops) {
            if (backpackApi.getBackpackFromItemStack(item)) {
                Bukkit.getLogger().info("backpack detected");
                if (backpackApi.doesPlayerHaveBackpack(uid)) {
                    Bukkit.getLogger().info("player had backpack");
                    Backpack info = backpackApi.backpackContainer(item).get(backpackApi.backpackKey(), new BackpackDataType());
                    ItemMeta meta = item.getItemMeta();
                    List<ItemStack> backpackItems = backpackApi.getBackpackItems(uid);
                    info.removeUUID();
                    meta.getPersistentDataContainer().set(backpackApi.backpackKey(), new BackpackDataType(), info);
                    item.setItemMeta(meta);
                    backpackApi.getBackpackInventoryData().remove(uid);
                    drops.addAll(backpackItems);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockDispensed(BlockDispenseEvent e) {
        if (e.getItem().getType() != Material.SHULKER_BOX)
            return;
        if (!e.getItem().hasItemMeta())
            return;

        if(backpackApi.getBackpackFromItemStack(e.getItem())) {
            e.setCancelled(true);
        }
    }
}
