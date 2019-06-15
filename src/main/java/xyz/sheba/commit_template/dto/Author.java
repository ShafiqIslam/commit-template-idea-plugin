package xyz.sheba.commit_template.dto;

public class Author {
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

    public boolean isNotEmpty() {
        return name != null;
    }

    @Override
    public String toString() {
        return (name != null) ? name : "No One";
    }
}
