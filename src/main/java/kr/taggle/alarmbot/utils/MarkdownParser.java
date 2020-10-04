package kr.taggle.alarmbot.utils;

public class MarkdownParser {
    private MarkdownParser() {
    }

    public static String toChannel(String text) {
        return "<!channel> " + text;
    }

    public static String toItalic(String text) {
        return String.format("_%s_", text);
    }
}
