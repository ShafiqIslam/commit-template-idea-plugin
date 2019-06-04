package xyz.sheba.developers.commit_template;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Dialog extends DialogWrapper {

    private final Panel panel;

    Dialog(@Nullable Project project) {
        super(project);
        panel = new Panel(project);
        setTitle("Commit");
        setOKButtonText("OK");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel.getMainPanel();
    }

    Message getCommitMessage() {
        return panel.getCommitMessage();
    }

}
