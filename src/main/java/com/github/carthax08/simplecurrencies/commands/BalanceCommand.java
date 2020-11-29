package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BalanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0){
                player.sendMessage("Please provide a currency!");
                return true;
            }else{
                if(SimpleCurrencies.checkCurrency(args[0].toLowerCase())){
                    player.sendMessage(ChatColor.GREEN + args[0].toLowerCase() + ":" + ChatColor.RESET + SimpleCurrencies.getCurrency(args[0].toLowerCase(), player));
                    return true;
                }else{
                    player.sendMessage("Please provide a currency that exists!");
                    return true;
                }
            }
        }else{
            sender.sendMessage("You have to be a player to run this command!");
            return true;
        }
    }
}