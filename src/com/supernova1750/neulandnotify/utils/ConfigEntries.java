package com.supernova1750.neulandnotify.utils;

import java.util.HashMap;
import java.util.Map;

public enum ConfigEntries {
    DEBUG_CHANNEL(0,"debugChannel",""),
    REMINDER_CHANNEL(1, "reminderChannel", ""),
    BOT_TOKEN(2,"botToken",""),
    PREFIX(3,"prefix","!"),
    TIME(4,"time","MONDAY:00:00");


    public final int id;
    public final String name;
    public final String value;

    private static final Map<Integer, ConfigEntries> CONFIG_ENTRIES_MAP;

    static {
        CONFIG_ENTRIES_MAP = new HashMap<>();

        for (ConfigEntries value : values()) {
            CONFIG_ENTRIES_MAP.put(value.id, value);
        }
    }

    ConfigEntries(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public static ConfigEntries getById(int id) {
        return CONFIG_ENTRIES_MAP.get(id);
    }
}
