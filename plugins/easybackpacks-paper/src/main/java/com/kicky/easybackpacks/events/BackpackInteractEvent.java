package com.kicky.easybackpacks.events;

import com.kicky.easybackpacks.Backpack;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BackpackInteractEvent extends Event implements Cancellable {

    private final Backpack backpack;
    private final Player player;
    private boolean cancelled;
    private static final HandlerList handlers = new HandlerList();

    public BackpackInteractEvent(Backpack backpack, Player player) {
        this.backpack = backpack;
        this.player = player;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
