package xyz.sheba.commit_template.ui;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import xyz.sheba.commit_template.dto.Message;
import xyz.sheba.commit_template.dto.*;
import xyz.sheba.commit_template.project.IntelliJProject;
import xyz.sheba.commit_template.utils.StringUtils;

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
        IntelliJProject intelliJProject = new IntelliJProject(project);
        CZRC czrc = CZRC.Loader.load(intelliJProject);
        czrc.getTypes().forEach(types::addItem);
        scopes.addItem("None");
        coAuthors.addItem(new Author(null, null));
        issueTrackers.addItem(new IssueTracker(null));
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
        String scope = (String) this.scopes.getSelectedItem();
        if(!scope.equals("None")) scopes.add(scope);
        return scopes;
    }

    @NotNull
    private ArrayList<Issue> getIssueTrackers() {
        ArrayList<Issue> issues = new ArrayList<>();
        IssueTracker tracker = (IssueTracker) this.issueTrackers.getSelectedItem();
        if(tracker.isNotEmpty()) issues.add(new Issue(tracker, this.issueId.getText().trim()));
        return issues;
    }

    @NotNull
    private ArrayList<String> getReferences() {
        ArrayList<String> references = new ArrayList<>();
        if (StringUtils.isNotBlank(this.references.getText().trim()))
            references.add(this.references.getText().trim());
        return references;
    }

    @NotNull
    private ArrayList<Author> getCoAuthors() {
        ArrayList<Author> coAuthors = new ArrayList<>();
        Author author = (Author) this.coAuthors.getSelectedItem();
        if(author.isNotEmpty()) coAuthors.add(author);
        return coAuthors;
    }
}
