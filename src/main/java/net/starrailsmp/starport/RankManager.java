package net.starrailsmp.starport;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.starrailsmp.starport.Data.Rank;
import net.starrailsmp.starport.Data.UUIDs;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class RankManager {

    private final JavaPlugin plugin;
    private final File file;
    private final Gson gson = new Gson();
    private final Map<UUID, Rank> ranks = new ConcurrentHashMap<>();

    public RankManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "ranks.json");
    }

    public void load() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        if (!file.exists()) {
            try {
                ranks.put(UUID.fromString(UUIDs.Moovintwo_UUID), Rank.OWNER);
                ranks.put(UUID.fromString(UUIDs.Alex_UUID), Rank.CO_OWNER);
                ranks.put(UUID.fromString(UUIDs.ThreadedFlowey_UUID), Rank.DEVELOPER);
                ranks.put(UUID.fromString(UUIDs.Deplo_UUID), Rank.ADMIN);
                ranks.put(UUID.fromString(UUIDs.Karma_UUID), Rank.MODERATOR);
                save();
                return;
            } catch (Exception e) {
                plugin.getLogger().warning("Failed to create default ranks file: " + e.getMessage());
            }
        }

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            Map<String, String> raw = gson.fromJson(reader, type);
            if (raw != null) {
                raw.forEach((k, v) -> {
                    try {
                        UUID id = UUID.fromString(k);
                        ranks.put(id, Rank.fromString(v));
                    } catch (Exception ignored) { }
                });
            }
        } catch (Exception ex) {
            plugin.getLogger().severe("Could not load ranks.json: " + ex.getMessage());
        }
    }

    public synchronized void save() {
        try (Writer writer = new FileWriter(file)) {
            Map<String, String> raw = new HashMap<>();
            ranks.forEach((k, v) -> raw.put(k.toString(), v.name()));
            gson.toJson(raw, writer);
        } catch (Exception ex) {
            plugin.getLogger().severe("Could not save ranks.json: " + ex.getMessage());
        }
    }

    public Rank getRank(UUID uuid) {
        return ranks.getOrDefault(uuid, Rank.MEMBER);
    }

    public void setRank(UUID uuid, Rank rank) {
        ranks.put(uuid, rank == null ? Rank.MEMBER : rank);
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, this::save);
    }

    public Map<UUID, Rank> getAll() {
        return Collections.unmodifiableMap(ranks);
    }
}