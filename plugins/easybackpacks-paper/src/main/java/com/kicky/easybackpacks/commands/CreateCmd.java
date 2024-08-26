package com.kicky.easybackpacks.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.kicky.easybackpacks.BackpackApi;
import com.kicky.easybackpacks.BackpackSize;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CreateCmd extends BaseCommand {

    private final BackpackApi backpackApi;

    public CreateCmd(BackpackApi backpackApi) {
        this.backpackApi = backpackApi;
    }

    @CommandAlias("createshulker")
    @CommandPermission("easybackpacks.op")
    @Syntax("<size>")
    @Description("Generates a new shulker in your hand")
    @CommandCompletion("@ES_sizes")
    public void onCommand(CommandSender sender, @Single String size) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return;
        }

        Player player = (Player) sender;
        UUID uid = player.getUniqueId();

        BackpackSize backpackSize = BackpackSize.fromString(size);
        if (backpackSize == null) {
            player.sendMessage("Invalid backpack size.");
            return;
        }
        backpackApi.makeBackpack(player, backpackSize);
        player.sendMessage("A backpack has been created.");

    }
}
