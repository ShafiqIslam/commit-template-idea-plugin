package xyz.sheba.commit_template.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import xyz.sheba.commit_template.dto.Message;

import javax.swing.*;

public class Dialog extends DialogWrapper {

    private final Panel panel;

    public Dialog(@Nullable Project project) throws Exception {
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

    public Message getCommitMessage() {
        return panel.getCommitMessage();
    }
}
