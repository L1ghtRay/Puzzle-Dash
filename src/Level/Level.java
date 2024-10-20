package Level;

public class Level {
    int levelID;
    String levelName;
    String levelSeed;

    Level(int levelID, String levelName) {
        this.levelID = levelID;
        this.levelName = levelName;
    }

    void setLevelSeed(String levelSeed) {
        this.levelSeed = levelSeed;
    }
}
