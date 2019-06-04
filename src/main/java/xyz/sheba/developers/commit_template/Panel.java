package xyz.sheba.developers.commit_template;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;

public class Panel {
    private JPanel mainPanel;
    private JTextField subject;
    private JComboBox types;
    private JComboBox scopes;
    private JTextArea what;
    private JTextArea why;
    private JTextField issues;
    private JTextField coAuthors;
    private JTextField references;

    Panel(Project project) {
        for (ChangeType type : ChangeType.values()) {
            types.addItem(type);
        }
        /*File workingDirectory = VfsUtil.virtualToIoFile(project.getBaseDir());
        Command.Result result = new Command(workingDirectory, "git log --all --format=%s | grep -Eo '^[a-z]+(\\(.*\\)):.*$' | sed 's/^.*(\\(.*\\)):.*$/\\1/' | sort -n | uniq").execute();
        if (result.isSuccess()) {
            result.getOutput().forEach(scopes::addItem);
        }*/
    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    Message getCommitMessage() {
        return Message.Builder.getInstance()
                .setSubject(subject.getText().trim())
                .setTypes(getTypes())
                .setScopes(getScopes())
                .setWhat(what.getText().trim())
                .setWhy(why.getText().trim())
                .setIssues(getIssues())
                .setCoAuthors(getCoAuthors())
                .setReferences(getReferences())
                .build();
    }

    @NotNull
    private ArrayList<ChangeType> getTypes() {
        ArrayList<ChangeType> types = new ArrayList<>();
        types.add((ChangeType) this.types.getSelectedItem());
        return types;
    }

    @NotNull
    private ArrayList<String> getScopes() {
        ArrayList<String> scopes = new ArrayList<>();
        scopes.add((String) this.scopes.getSelectedItem());
        return scopes;
    }

    @NotNull
    private ArrayList<String> getIssues() {
        ArrayList<String> issues = new ArrayList<>();
        issues.add(this.issues.getText().trim());
        return issues;
    }

    @NotNull
    private ArrayList<String> getReferences() {
        ArrayList<String> references = new ArrayList<>();
        references.add(this.references.getText().trim());
        return references;
    }

    @NotNull
    private ArrayList<String> getCoAuthors() {
        ArrayList<String> coAuthors = new ArrayList<>();
        coAuthors.add(this.coAuthors.getText().trim());
        return coAuthors;
    }

}
