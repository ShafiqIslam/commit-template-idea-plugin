package xyz.sheba.commit_template.dto;

public class Type {
    private String name;
    private String emoji;
    private String description;
    private String code;

    public Type(String name, String emoji, String description, String code) {
        this.name = name;
        this.emoji = emoji;
        this.description = description;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getEmoji() {
        return emoji;
    }

    @Override
    public String toString() {
        return  name + " (" + description + ") " + code;
    }
}
