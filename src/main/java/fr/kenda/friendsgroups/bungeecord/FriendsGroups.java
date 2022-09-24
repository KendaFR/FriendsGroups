package fr.kenda.friendsgroups.bungeecord;

import fr.kenda.friendsgroups.bungeecord.commands.FriendsCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public final class FriendsGroups extends Plugin {

    public static final String PFRIENDS = ChatColor.GOLD + "[Friends] ";
    public static final String PGROUPS = ChatColor.GOLD + "[Groupe] ";

    private static FriendsGroups instance;

    @Override
    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerCommand(this, new FriendsCommand(this));
    }

    @Override
    public void onDisable() {

    }

    public static FriendsGroups getInstance() {
        return instance;
    }
}
