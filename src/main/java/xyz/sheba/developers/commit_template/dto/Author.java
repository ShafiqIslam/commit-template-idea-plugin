package xyz.sheba.developers.commit_template.dto;

import xyz.sheba.developers.commit_template.form.AuthorComboItem;
import xyz.sheba.developers.commit_template.form.ComboItem;
import xyz.sheba.developers.commit_template.form.HasComboItem;

public class Author implements HasComboItem {
    private String name;
    private String email;

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public ComboItem getComboItem() {
        return new AuthorComboItem(this, name);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
