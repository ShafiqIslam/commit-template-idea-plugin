package xyz.sheba.commit_template.dto;

public class IssueTracker {
    private String name;

    public IssueTracker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
