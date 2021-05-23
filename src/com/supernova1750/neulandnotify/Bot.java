package com.supernova1750.neulandnotify;

import com.supernova1750.neulandnotify.utils.ConfigLoading;
import com.supernova1750.neulandnotify.utils.Reminder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.security.auth.login.LoginException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Bot {
    public static String BOT_TOKEN = "";

    public static JDA jda;

    public static HashMap<String, String> properties = new HashMap<>();

    public static String prefix;
    public static long reminder;
    public static long debugCh;
    public static long pingTime = 10L;
    public static LocalTime time;
    public static DayOfWeek day;

    /* Properties: BOT_TOKEN, Prefix */
    public static void main(String[] args) throws LoginException, InterruptedException {
        ConfigLoading.loadConfig();

        if(args.length > 0) {
            System.exit(0);
        }

        prefix = properties.get("prefix");
        BOT_TOKEN = properties.get("botToken");

        try {
            debugCh = Long.parseLong(properties.get("debugChannel"));
        } catch (NumberFormatException e) {
            debugCh = -1L;
        }

        try {
            reminder = Long.parseLong(properties.get("reminderChannel"));
        } catch (NumberFormatException e) {
            reminder = -1L;
        }

        String[] localDayOfWeekTime = properties.get("time").split(":");
        if(localDayOfWeekTime.length != 3) {
            throw new RuntimeException("Invalid LocalDayOfWeekTime config");
        }

        day = DayOfWeek.valueOf(localDayOfWeekTime[0]);
        time = LocalTime.of(Integer.parseInt(localDayOfWeekTime[1]), Integer.parseInt(localDayOfWeekTime[2]));

        System.out.println(Reminder.getNextDate().toString());
        /* Discord bot connection*/
        jda = JDABuilder.createDefault(BOT_TOKEN).build().awaitReady();

        Reminder.startScheduler();
//
//        /**Listeners*/
//        jda.addEventListener(new MessageReceivedListener());
    }
}
