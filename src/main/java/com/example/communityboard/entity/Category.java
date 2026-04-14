package com.example.communityboard.entity;

public enum Category {
    EVENT("イベント"),
    NOTICE("お知らせ"),
    HELP("助け合い");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}