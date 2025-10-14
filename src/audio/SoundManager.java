package audio;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoundManager {
    private static final Map<String, Clip> CACHE = new ConcurrentHashMap<>();

    public static void play(String resourcePath) {
        try {
            Clip c = CACHE.computeIfAbsent(resourcePath, SoundManager::loadClip);
            if (c == null) return;
            if (c.isRunning()) c.stop();
            c.setFramePosition(0);
            c.start();
        } catch (Exception e) {
            System.err.println("[Sound] Play failed: " + resourcePath + " -> " + e.getMessage());
        }
    }

    private static Clip loadClip(String path) {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            if (in == null) throw new IllegalArgumentException("Resource not found: " + path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(in);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            return clip;
        } catch (Exception e) {
            System.err.println("[Sound] Load failed: " + path + " -> " + e.getMessage());
            return null;
        }
    }
<<<<<<< HEAD

=======
>>>>>>> 08ef35672740096ed7b936b4a608b7b7b3f48039
    public static void playLoop(String resourcePath) {
        try {
            Clip c = CACHE.computeIfAbsent(resourcePath, SoundManager::loadClip);
            if (c == null) return;
<<<<<<< HEAD
            stopAll();
=======
            stopAll(); // Stoppe tout avant de jouer la nouvelle musique
>>>>>>> 08ef35672740096ed7b936b4a608b7b7b3f48039
            c.setFramePosition(0);
            c.loop(Clip.LOOP_CONTINUOUSLY);
            c.start();
        } catch (Exception e) {
            System.err.println("[Sound] Loop failed: " + resourcePath + " -> " + e.getMessage());
        }
    }

<<<<<<< HEAD
    public static void stop(String resourcePath) {
        try {
            Clip c = CACHE.get(resourcePath);
            if (c != null && c.isRunning()) {
                c.stop();
                c.setFramePosition(0);
            }
        } catch (Exception e) {
            System.err.println("[Sound] Stop failed: " + resourcePath + " -> " + e.getMessage());
        }
    }

=======
>>>>>>> 08ef35672740096ed7b936b4a608b7b7b3f48039
    public static void stopAll() {
        for (Clip c : CACHE.values()) {
            if (c.isRunning()) c.stop();
        }
    }
<<<<<<< HEAD
=======

>>>>>>> 08ef35672740096ed7b936b4a608b7b7b3f48039
}
