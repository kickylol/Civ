package com.kicky.easybackpacks.holder;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerHolder implements BackpackHolder {

    private final Player player;
    private boolean hasBackpack;

    public PlayerHolder(Player player) {
        Preconditions.checkNotNull(player, "player");
        this.player = player;
    }

    public void setHasBackpack(boolean hasBackpack) {
        this.hasBackpack = hasBackpack;
    }


    @Override
    public String getName() {
        return "";
    }

    @Override
    public Location getLocation() {
        return null;
    }
}
