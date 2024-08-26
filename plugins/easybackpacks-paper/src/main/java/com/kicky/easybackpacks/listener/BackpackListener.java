package com.kicky.easybackpacks.listener;

import com.kicky.easybackpacks.Backpack;
import com.kicky.easybackpacks.BackpackApi;
import com.kicky.easybackpacks.core.BackpackDataType;
import com.kicky.easybackpacks.events.BackpackCreationEvent;
import com.kicky.easybackpacks.events.BackpackInteractEvent;
import com.kicky.easybackpacks.holder.BackpackInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class BackpackListener implements Listener {

    private final BackpackApi backpackApi;

    public BackpackListener(BackpackApi backpackApi) {
        this.backpackApi = backpackApi;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBackpackCreation(BackpackCreationEvent e) {
        Player player = e.getPlayer();

        player.getInventory().addItem(backpackApi.makeBackpackItemStack(e.getBackpack()));
    }

    @EventHandler
    public void onBackpackInteract(BackpackInteractEvent e) {
        backpackApi.openBackpackInventory(e.getPlayer(), e.getBackpack());
    }

    @EventHandler
    public void onBackpackClose(InventoryCloseEvent e) {
        Inventory inventory = e.getInventory();

        if (!(inventory.getHolder(false) instanceof BackpackInventory backpackInventory)) {
            return;
        }

        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        UUID uid = e.getPlayer().getUniqueId();

        // gets backpack datatype in the players hand
        Backpack backpack = backpackApi.backpackContainer(e.getPlayer().getInventory().getItemInMainHand()).get(backpackApi.backpackKey(), new BackpackDataType());
        if (!inventory.isEmpty()) {
            // Sets player ownership over backpack and updates the data container if there is something inside of it
            backpack.setUUID(uid);
            meta.getPersistentDataContainer().set(backpackApi.backpackKey(), new BackpackDataType(), backpack);
            item.setItemMeta(meta);
        }

        backpackApi.closeBackpackInventory(e.getPlayer().getUniqueId().toString(), backpackInventory, backpack, item);
    }

}
