package xyz.sheba.commit_template.dto;

public class Issue {
    private IssueTracker tracker;
    private String id;

    public Issue(IssueTracker tracker, String id) {
        this.tracker = tracker;
        this.id = id;
    }

    IssueTracker getTracker() {
        return tracker;
    }

    String getId() {
        return id;
    }

    @Override
    public String toString() {
        return tracker.getName() + ": " + id;
    }
}
