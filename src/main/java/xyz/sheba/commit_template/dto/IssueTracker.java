package xyz.sheba.commit_template.dto;

public class IssueTracker {
    private String name;

    public IssueTracker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isNotEmpty() {
        return name != null;
    }

    @Override
    public String toString() {
        return isNotEmpty() ? name : "None";
    }
}
