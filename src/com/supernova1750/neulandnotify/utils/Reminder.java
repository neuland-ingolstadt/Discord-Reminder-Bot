package com.supernova1750.neulandnotify.utils;

import com.supernova1750.neulandnotify.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.concurrent.Task;

import java.awt.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder {

    private static final String title = "Ka";
    private static final String description = "Ka";

    private static Timer task;

    public static void startScheduler() {
        task = new Timer();
        TimerTask t = new TimerTask() {
            public void run() {
                TextChannel textChannel = Bot.jda.getTextChannelById(Bot.reminder);
                if (textChannel != null) {

                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle(title);
                    builder.setColor(Color.MAGENTA);
                    builder.setDescription(description);
                    builder.setAuthor("Reminder Bot");

                    textChannel.sendMessage(builder.build()).queue();
                } else {
                    TextChannel tx = Bot.jda.getTextChannelById(Bot.debugCh);
                    tx.sendMessage("Error while trying to find text channel").queue();
                    System.out.println();
                }
                task.schedule(this, getNextDate());
            }
        };

        task.schedule(t, getNextDate());
    }

    public static Date getNextDate() {
        TemporalAdjuster ta;

        if(Bot.day == LocalDate.now().getDayOfWeek() && LocalTime.now().until(Bot.time, ChronoUnit.MINUTES) > 0) {
            ta = TemporalAdjusters.nextOrSame(Bot.day);
        } else {
            ta = TemporalAdjusters.next(Bot.day);
        }
        return java.util.Date.from(LocalDate.now().with(ta).atTime(Bot.time).atZone(ZoneId.systemDefault()).toInstant());
    }
}
