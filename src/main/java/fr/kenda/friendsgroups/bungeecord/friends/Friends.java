package fr.kenda.friendsgroups.bungeecord.friends;

import fr.kenda.rushapi.bungeecord.sql.Sql;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Friends extends Sql {

    private final Connection connection = getConnection();

    /**
     * Get the list of friends to player
     *
     * @param player target player
     * @return a list of string (friends)
     */
    public List<String> getListOfFriends(ProxiedPlayer player) {
        List<String> friends = new ArrayList<>();
        try {
            PreparedStatement state = connection.prepareStatement("SELECT playername FROM friends INNER JOIN player ON player.id = friends.id_friends Where player.id IN  (SELECT id_friends FROM friends INNER JOIN player ON player.id = friends.id_player WHERE player.playername = ?)");
            state.setString(1, player.getName());
            ResultSet result = state.executeQuery();
            while (result.next()) {
                friends.add(result.getString("playername"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return friends;
    }
}
