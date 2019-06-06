package xyz.sheba.commit_template.dto;

public class Issue {
    private IssueTracker tracker;
    private String id;

    public Issue(IssueTracker tracker, String id) {
        this.tracker = tracker;
        this.id = id;
    }

    public String getTracker() {
        return tracker.getName();
    }

    public String getId() {
        return id;
    }
}
