package com.kicky.easybackpacks.core;

import com.google.common.base.Preconditions;
import com.kicky.easybackpacks.Backpack;
import com.kicky.easybackpacks.BackpackApi;
import com.kicky.easybackpacks.BackpackManager;
import com.kicky.easybackpacks.BackpackSize;
import com.kicky.easybackpacks.commands.CmdManager;
import com.kicky.easybackpacks.holder.BackpackHolder;
import com.kicky.easybackpacks.holder.BackpackInventory;
import com.kicky.easybackpacks.listener.PlayerListener;
import com.kicky.easybackpacks.listener.BackpackListener;
import io.papermc.paper.plugin.configuration.PluginMeta;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

final class EasyBackpackCore implements BackpackApi {

    private final Plugin plugin;
    private final CorePluginFactory backpackFactory;
    private final BackpackManager backpackManager;
    private CmdManager cmdManager;
    private Logger log;

    private final PlayerListener playerListener;
    private final BackpackListener backpackListener;

    public EasyBackpackCore(final Plugin plugin) {
        Preconditions.checkNotNull(plugin, "plugin");

        this.plugin = plugin;
        backpackFactory = new CorePluginFactory(this);
        backpackManager = backpackFactory.createBackpackManager();

        playerListener = new PlayerListener(this);
        backpackListener = new BackpackListener(this);

        Bukkit.getLogger().info("core created");
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("core enabled");

        cmdManager = backpackFactory.createBackpackCommands();

        getServer().getPluginManager().registerEvents(playerListener, this);
        getServer().getPluginManager().registerEvents(backpackListener, this);

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public BackpackManager getBackpackManager() {
        return null;
    }

    @Override
    public Backpack makeBackpack(BackpackHolder holder) {
        return null;
    }

    @Override
    public void openBackpackInventory(Player player, Backpack backpack) {
        backpackManager.openBackpackInventory(player, backpack);
    }

    @Override
    public void closeBackpackInventory(String uid, BackpackInventory backpackInventory, Backpack backpack, ItemStack item) {
        backpackManager.closeBackpackInventory(uid, backpackInventory, backpack, item);
    }

    @Override
    public void dropBackpackInventory(String uid, BackpackInventory backpackInventory) {
        backpackManager.dropBackpackInventory(uid, backpackInventory);
    }

    @Override
    public Backpack makeBackpack(Player player, BackpackSize backpackSize) {
        return backpackManager.makeBackpack(player, backpackSize);
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
    public List<ItemStack> getBackpackItems(String uid) {
        return backpackManager.getBackpackItems(uid);
    }

    @Override
    public Map<String, ItemStack[]> getBackpackInventoryData() {
        return backpackManager.getBackpackInventoryData();
    }

    @Override
    public boolean doesPlayerHaveBackpack(Player player) {
        return backpackManager.doesPlayerHaveBackpack(player);
    }

    @Override
    public boolean doesPlayerHaveBackpack(String uid) {
        return backpackManager.doesPlayerHaveBackpack(uid);
    }

    @Override
    public ItemStack makeBackpackItemStack(Backpack backpack) {
        return backpackManager.makeBackpackItemStack(backpack);
    }

    @Override
    public boolean getBackpackFromItemStack(ItemStack is) {
        return backpackManager.getBackpackFromItemStack(is);
    }

    @Override
    public PersistentDataContainer backpackContainer(ItemStack is) {
        return backpackManager.backpackContainer(is);
    }

    @Override
    public NamespacedKey backpackKey() {
        return backpackManager.backpackKey();
    }

    @Override
    public @NotNull File getDataFolder() {
        return plugin.getDataFolder();
    }

    @Override
    public @NotNull PluginDescriptionFile getDescription() {
        return plugin.getDescription();
    }

    @Override
    public @NotNull PluginMeta getPluginMeta() {
        return plugin.getPluginMeta();
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    @Override
    public @Nullable InputStream getResource(@NotNull String s) {
        return plugin.getResource(s);
    }

    @Override
    public void saveConfig() {
        plugin.saveConfig();
    }

    @Override
    public void saveDefaultConfig() {
        plugin.saveDefaultConfig();
    }

    @Override
    public void saveResource(@NotNull String s, boolean b) {
        plugin.saveResource(s, b);
    }

    @Override
    public void reloadConfig() {
        plugin.reloadConfig();
    }

    @Override
    public PluginLoader getPluginLoader() {
        return plugin.getPluginLoader();
    }

    @Override
    public @NotNull Server getServer() {
        return plugin.getServer();
    }

    @Override
    public boolean isEnabled() {
        return plugin.isEnabled();
    }

    @Override
    public boolean isNaggable() {
        return plugin.isNaggable();
    }

    @Override
    public void setNaggable(boolean b) {
        plugin.setNaggable(b);
    }

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String s, @Nullable String s1) {
        return plugin.getDefaultWorldGenerator(s, s1);
    }

    @Override
    public @Nullable BiomeProvider getDefaultBiomeProvider(@NotNull String s, @Nullable String s1) {
        return plugin.getDefaultBiomeProvider(s, s1);
    }

    @Override
    public @NotNull Logger getLogger() {
        return plugin.getLogger();
    }

    @Override
    public @NotNull String getName() {
        return plugin.getName();
    }

    @Override
    public @NotNull LifecycleEventManager<Plugin> getLifecycleManager() {
        return plugin.getLifecycleManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
