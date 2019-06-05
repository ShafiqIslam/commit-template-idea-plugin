package xyz.sheba.developers.commit_template.dto;

public class Type {
    private String name;
    private String emoji;
    private String description;
    private String code;

    Type(String name, String emoji, String description, String code) {
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

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                ", emoji='" + emoji + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
