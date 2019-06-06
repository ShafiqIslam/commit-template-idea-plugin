package xyz.sheba.commit_template;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import xyz.sheba.commit_template.dto.*;

import javax.swing.*;
import java.util.ArrayList;

public class Panel {
    private JPanel mainPanel;
    private JTextField subject;
    private JComboBox types;
    private JComboBox scopes;
    private JTextArea what;
    private JTextArea why;
    private JComboBox issueTrackers;
    private JComboBox coAuthors;
    private JTextField references;
    private JTextField issueId;

    Panel(Project project) throws Exception {
        CZRC czrc = CZRCLoader.load(project);
        czrc.getTypes().forEach(types::addItem);
        czrc.getAuthors().forEach(coAuthors::addItem);
        czrc.getIssueTrackers().forEach(issueTrackers::addItem);
        czrc.getScopes().forEach(scopes::addItem);
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
                .setIssues(getIssueTrackers())
                .setCoAuthors(getCoAuthors())
                .setReferences(getReferences())
                .build();
    }

    @NotNull
    private ArrayList<Type> getTypes() {
        ArrayList<Type> types = new ArrayList<>();
        types.add((Type) this.types.getSelectedItem());
        return types;
    }

    @NotNull
    private ArrayList<String> getScopes() {
        ArrayList<String> scopes = new ArrayList<>();
        scopes.add((String) this.scopes.getSelectedItem());
        return scopes;
    }

    @NotNull
    private ArrayList<Issue> getIssueTrackers() {
        ArrayList<Issue> issues = new ArrayList<>();
        issues.add(new Issue((IssueTracker) this.issueTrackers.getSelectedItem(), this.issueId.getText().trim()));
        return issues;
    }

    @NotNull
    private ArrayList<String> getReferences() {
        ArrayList<String> references = new ArrayList<>();
        references.add(this.references.getText().trim());
        return references;
    }

    @NotNull
    private ArrayList<Author> getCoAuthors() {
        ArrayList<Author> coAuthors = new ArrayList<>();
        coAuthors.add((Author) this.coAuthors.getSelectedItem());
        return coAuthors;
    }
}
