package xyz.sheba.commit_template.project;

import java.io.File;

public class MockProject implements IProject {
    String path;

    public MockProject(String path) {
        this.path = path;
    }

    @Override
    public String getBasePath() {
        return path;
    }

    @Override
    public File getBaseWorkingDir() {
        return new File(path);
    }
}
