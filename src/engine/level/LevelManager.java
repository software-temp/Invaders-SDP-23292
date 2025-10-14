package engine.level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LevelManager {

    private static final String MAPS_FILE = "res/maps/maps.json";
    private List<Level> levels;

    public LevelManager() {
        try {
            loadLevels();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the levels from the maps.json file.
     * @throws IOException If the file cannot be read.
     */
    private void loadLevels() throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(MAPS_FILE)));

        // Here you would use a JSON parsing library like Gson or Jackson
        // to deserialize the jsonContent into a List<Level>.
        // For example, with Gson:
        // com.google.gson.Gson gson = new com.google.gson.Gson();
        // this.levels = gson.fromJson(jsonContent, new com.google.gson.reflect.TypeToken<List<Level>>(){}.getType());
    }

    /**
     * Gets the settings for a specific level.
     *
     * @param levelNumber The level number to get the settings for.
     * @return The Level object for the specified level number, or null if not found.
     */
    public Level getLevel(int levelNumber) {
        if (levels == null) {
            return null;
        }
        for (Level level : levels) {
            if (level.getLevel() == levelNumber) {
                return level;
            }
        }
        return null;
    }
}
