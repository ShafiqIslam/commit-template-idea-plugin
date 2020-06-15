package xyz.sheba.commit_template.project;

public class MockProject implements IProject {
    String path;

    public MockProject(String path) {
        this.path = path;
    }

    @Override
    public String getBasePath() {
        return path;
    }
}
