package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.enums.CommandType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class MainCommand implements CommandExecutor {

    SimpleCurrencies plugin;

    public MainCommand(SimpleCurrencies main){
        plugin = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("simplecurrencies.admin")){
                player.sendMessage("You do not have the required permissions to use this command!");
                return true;
            }else {
                if (args.length == 0) {
                    player.sendMessage("You must provide an operation, currency, player, and amount!");
                    return false;
                } else {
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (player1.getName().equals(args[2])) {
                            return handleCommand(player1, args, player);
                        }
                    }
                    for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                        if (offlinePlayer.getName().equals(args[2]) && offlinePlayer.hasPlayedBefore()) {
                            return handleCommand(offlinePlayer, args, player);
                        }
                    }
                    return false;
                }
            }
        }else{
            return true;
        }
    }

    public boolean handleCommand(Player playerToEdit, String[] args, Player player){
        if(checkCommandType(args[0]) == CommandType.ADD){
            if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency, player, and amount");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player and amount!");
                return false;
            }else if(checkArgsLength(args) == 3){
                player.sendMessage("Please provide an amount!");
                return false;
            }else{
                FileConfiguration config = plugin.getConfig();
                if(checkCurrencyType(args[1])){
                    SimpleCurrencies.addCurrency(args[1], playerToEdit, Integer.parseInt(args[3]));
                    player.sendMessage("Success!");
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or it isn't enabled. Please check the config!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.SET){
            if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency, player, and amount");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player and amount!");
                return false;
            }else if(checkArgsLength(args) == 3){
                player.sendMessage("Please provide an amount!");
                return false;
            }else{
                FileConfiguration config = plugin.getConfig();
                if(checkCurrencyType(args[1])){
                    SimpleCurrencies.setCurrency(args[1], playerToEdit, Integer.parseInt(args[3]));
                    player.sendMessage("Success!");
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or it isn't enabled. Please check the config!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.REMOVE){
            if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency, player, and amount");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player and amount!");
                return false;
            }else if(checkArgsLength(args) == 3){
                player.sendMessage("Please provide an amount!");
                return false;
            }else{
                FileConfiguration config = plugin.getConfig();
                if(checkCurrencyType(args[1])){
                    SimpleCurrencies.removeCurrency(args[1], playerToEdit, Integer.parseInt(args[3]));
                    player.sendMessage("Success!");
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or it isn't enabled. Please check the config!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.CLEAR){
            if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency and a player");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player!");
                return false;
            }else{
                FileConfiguration config = plugin.getConfig();
                if(checkCurrencyType(args[1])){
                    SimpleCurrencies.clearCurrency(args[1], playerToEdit);
                    plugin.saveConfig();
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or it isn't enabled. Please check the config!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.RELOAD){
            plugin.reloadConfig();
        }
        return false;
    }


    public boolean checkCurrencyType(String stringToCheck) {
        return plugin.getConfig().getBoolean("currencies." + stringToCheck.toLowerCase() + ".enabled");
    }

    public CommandType checkCommandType(String stringToCheck){
        if(stringToCheck.equalsIgnoreCase("add")){
            return CommandType.ADD;
        }
        if(stringToCheck.equalsIgnoreCase("set")){
            return CommandType.SET;
        }
        if(stringToCheck.equalsIgnoreCase("remove")){
            return CommandType.REMOVE;
        }
        if(stringToCheck.equalsIgnoreCase("clear")){
            return CommandType.CLEAR;
        }
        if(stringToCheck.equalsIgnoreCase("reload")){
            return CommandType.RELOAD;
        }
        return CommandType.UNKNOWN;
    }
    public int checkArgsLength(String[] args){
        return args.length;
    }

    public boolean handleCommand(OfflinePlayer playerToEdit, String[] args, Player player){
        if(checkCommandType(args[0]) == CommandType.ADD){
            if(checkArgsLength(args) == 0){
            }else if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency, player, and amount");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player and amount!");
                return false;
            }else if(checkArgsLength(args) == 3){
                player.sendMessage("Please provide an amount!");
                return false;
            }else{
                FileConfiguration config = plugin.getConfig();
                if(checkCurrencyType(args[1])){
                    SimpleCurrencies.addCurrency(args[1], playerToEdit, Integer.parseInt(args[3]));
                    player.sendMessage("Success!");
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or it isn't enabled. Please check the config!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.SET){
            if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency, player, and amount");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player and amount!");
                return false;
            }else if(checkArgsLength(args) == 3){
                player.sendMessage("Please provide an amount!");
                return false;
            }else{
                FileConfiguration config = plugin.getConfig();
                if(checkCurrencyType(args[1])){
                    SimpleCurrencies.setCurrency(args[1], playerToEdit, Integer.parseInt(args[3]));
                    player.sendMessage("Success!");
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or it isn't enabled. Please check the config!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.REMOVE){
            if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency, player, and amount");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player and amount!");
                return false;
            }else if(checkArgsLength(args) == 3){
                player.sendMessage("Please provide an amount!");
                return false;
            }else{
                FileConfiguration config = plugin.getConfig();
                if(checkCurrencyType(args[1])){
                    SimpleCurrencies.removeCurrency(args[1], playerToEdit, Integer.parseInt(args[3]));
                    player.sendMessage("Success!");
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or it isn't enabled. Please check the config!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.CLEAR){
            if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency and a player");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player!");
                return false;
            }else{
                FileConfiguration config = plugin.getConfig();
                if(checkCurrencyType(args[1])){
                    SimpleCurrencies.clearCurrency(args[1], playerToEdit);
                    plugin.saveConfig();
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or it isn't enabled. Please check the config!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.RELOAD){
            plugin.reloadConfig();
        }
        return false;
    }
}
