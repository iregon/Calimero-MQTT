package com.alessandro.calimero.lib;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Optional;

public class TelegramToTopicMatcher {
    private static ArrayList<TopicItem> items = new ArrayList<>();

    public static void addMatch(String topic, String dpt, String commandDP, String stateDP) {
        TopicItem item = new TopicItem(topic, dpt, commandDP, stateDP);
        items.add(item);
    }

    public static Optional<String> getDptFromCommandTopic(String topic) {
        return items.stream()
                .filter(topicItem -> MessageFormat.format(
                        "{0}/{1}",
                        topicItem.getBaseTopic(),
                        topicItem.getCommandDp()).equals(topic))
                .findFirst()
                .map(TopicItem::getDpt);
    }

    public static Optional<String> getStateTopicFromStateDp(String stateDp) {
        Optional<TopicItem> item = items.stream()
                .filter(topicItem -> topicItem.getStateDp().equals(stateDp))
                .findFirst();

        return item.map(topicItem -> MessageFormat.format(
                "{0}/{1}",
                topicItem.getBaseTopic(),
                topicItem.getStateDp()));
    }

}

class TopicItem {
    private String baseTopic;
    private String dpt;
    private String commandDP;
    private String stateDP;

    public TopicItem(String topic, String dpt, String commandDP, String stateDP) {
        this.baseTopic = topic;
        this.dpt = dpt;
        this.commandDP = commandDP;
        this.stateDP = stateDP;
    }

    public String getBaseTopic() {
        return baseTopic;
    }

    public String getDpt() {
        return dpt;
    }

    public String getCommandDp() {
        return commandDP;
    }

    public String getStateDp() {
        return stateDP;
    }
}