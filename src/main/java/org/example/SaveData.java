package org.example;

public class SaveData {
    private Hero player;
    private int locationId;

    public SaveData(Hero player, int locationId) {
        this.player = player;
        this.locationId = locationId;
    }


    public Hero getPlayer() { return this.player; }
    public String getPlayerName() { return player.getName(); }
    public String getPlayerClass() { return player.getHero_class(); }
    public int getHealth() { return player.getHealth(); }
    public int getLocationId() { return locationId; }
}
