package fr.kenda.friendsgroups.bungeecord.commands;

import fr.kenda.friendsgroups.bungeecord.FriendsGroups;
import fr.kenda.rushapi.bungeecord.RushAPI;
import fr.kenda.rushapi.bungeecord.users.Friends;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class FriendsCommand extends Command {

    private final ProxyServer proxy = RushAPI.getInstance().getProxy();
    private final Friends friends = RushAPI.friends;

    public FriendsCommand(FriendsGroups friends) {
        super("friends");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(RushAPI.PREFIX + ChatColor.RED + "Vous n'avez pas la permission de faire cette commande"));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length == 0) {
            player.sendMessage(new TextComponent(ChatColor.GREEN + "========================== \n" +
                    ChatColor.GOLD + "Listes des amis: \n"));
            if (friends.getListOfFriends(player).isEmpty()) {
                player.sendMessage(new TextComponent(ChatColor.RED + "Vous n'avez pas d'amis."));
            } else {
                friends.getListOfFriends(player).forEach(p -> {
                    if (isOnline(p))
                        player.sendMessage(new TextComponent(ChatColor.GREEN + "✷  " + ChatColor.AQUA + p + ChatColor.WHITE + "(" + getServerWhereIsPlayer(proxy.getPlayer(p)).getName() + ")"));
                    else
                        player.sendMessage(new TextComponent(ChatColor.RED + "✷  " + ChatColor.AQUA + p));
                });
            }
            player.sendMessage(new TextComponent(ChatColor.GREEN + "=========================="));
        }
    }

    /**
     * Check is the target player is connected on network
     *
     * @param player target player
     * @return a boolean is connected
     */
    private boolean isOnline(String player) {
        return proxy.getPlayer(player) != null;
    }

    /**
     * Return the server where the player is connected
     *
     * @return the serverInfos where is player
     */
    private ServerInfo getServerWhereIsPlayer(ProxiedPlayer player) {
        return proxy.getPlayer(player.getName()).getServer().getInfo();
    }
}
