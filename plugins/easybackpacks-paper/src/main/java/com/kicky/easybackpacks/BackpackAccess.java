package com.kicky.easybackpacks;

import com.kicky.easybackpacks.holder.BackpackHolder;
import com.kicky.easybackpacks.holder.BackpackInventory;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BackpackAccess {

    Backpack makeBackpack(BackpackHolder holder);

    void openBackpackInventory(Player player, Backpack backpack);

    void closeBackpackInventory(String uid, BackpackInventory backpackInventory, Backpack backpack, ItemStack item);

    void dropBackpackInventory(String uid, BackpackInventory backpackInventory);

    Backpack makeBackpack(Player player, BackpackSize backpackSize);

    Backpack getBackpack(Player player);

    Backpack getBackpack(UUID uid);

    Collection<Backpack> getBackpacks();

    List<ItemStack> getBackpackItems(String uid);

    Map<String, ItemStack[]> getBackpackInventoryData();

    boolean doesPlayerHaveBackpack(Player player);

    boolean doesPlayerHaveBackpack(String uid);

    ItemStack makeBackpackItemStack(Backpack backpack);

    boolean getBackpackFromItemStack(ItemStack is);

    PersistentDataContainer backpackContainer(ItemStack is);

    NamespacedKey backpackKey();

}
