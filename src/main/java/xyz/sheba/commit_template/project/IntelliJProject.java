package xyz.sheba.commit_template.project;

import java.io.File;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;

public class IntelliJProject implements IProject {
    private Project project;

    public IntelliJProject(Project project) {
        this.project = project;
    }

    @Override
    public String getBasePath() {
        return project.getBasePath();
    }

    @Override
    public File getBaseWorkingDir() {
        return VfsUtil.virtualToIoFile(project.getBaseDir());
    }
}
