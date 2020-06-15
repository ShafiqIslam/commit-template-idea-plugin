package xyz.sheba.commit_template.project;

import com.intellij.openapi.project.Project;

public class IntelliJProject implements IProject {
    private Project project;

    public IntelliJProject(Project project) {
        this.project = project;
    }

    @Override
    public String getBasePath() {
        return project.getBasePath();
    }
}
