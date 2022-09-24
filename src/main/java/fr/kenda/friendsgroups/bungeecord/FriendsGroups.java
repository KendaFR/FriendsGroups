package fr.kenda.friendsgroups.bungeecord;

import fr.kenda.friendsgroups.bungeecord.commands.FriendsCommand;
import fr.kenda.friendsgroups.bungeecord.friends.Friends;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public final class FriendsGroups extends Plugin {

    public static final String PFRIENDS = ChatColor.GOLD + "[Friends] ";
    public static final String PGROUPS = ChatColor.GOLD + "[Groupe] ";

    public static Friends friends;
    private static FriendsGroups instance;

    @Override
    public void onEnable() {
        instance = this;

        getProxy().getConsole().sendMessage(new TextComponent(PFRIENDS + ChatColor.GREEN + "plugin started"));
        friends = new Friends();

        getProxy().getPluginManager().registerCommand(this, new FriendsCommand(this));
    }

    @Override
    public void onDisable() {

    }

    public static FriendsGroups getInstance() {
        return instance;
    }
}
