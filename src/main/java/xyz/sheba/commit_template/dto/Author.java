package xyz.sheba.commit_template.dto;

public class Author {
    private String name;
    private String email;

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public boolean isNotEmpty() {
        return name != null;
    }

    @Override
    public String toString() {
        if(name == null) return "No One";
        return name + "<" + email + ">";
    }
}
