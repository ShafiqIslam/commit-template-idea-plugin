package xyz.sheba.developers.commit_template.dto;

import xyz.sheba.developers.commit_template.form.HasComboItem;
import xyz.sheba.developers.commit_template.form.TypeComboItem;
import xyz.sheba.developers.commit_template.utils.StringUtils;

public class Type implements HasComboItem {
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

    public TypeComboItem getComboItem() throws Exception {
        CZRC czrc = CZRC.get();
        String comboText = StringUtils.padRight(name, czrc.getTypesMaxNameLength())
                // + StringUtils.padRight(emoji, czrc.getTypesMaxEmojiLength())
                + description.trim();
        return new TypeComboItem(this, comboText);
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
