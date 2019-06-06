package xyz.sheba.developers.commit_template;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import org.jetbrains.annotations.NotNull;
import xyz.sheba.developers.commit_template.dto.*;
import xyz.sheba.developers.commit_template.form.AuthorComboItem;
import xyz.sheba.developers.commit_template.form.TypeComboItem;

import javax.swing.*;
import java.util.ArrayList;

public class Panel {
    private JPanel mainPanel;
    private JTextField subject;
    private JComboBox types;
    private JComboBox scopes;
    private JTextArea what;
    private JTextArea why;
    private JComboBox issues;
    private JComboBox coAuthors;
    private JTextField references;

    Panel(Project project) throws Exception {
        CZRC czrc = CZRC.load(project.getBasePath());

        for (Type type : czrc.getTypes()) {
            types.addItem(type.getComboItem());
        }
        for (Author author : czrc.getAuthors()) {
            coAuthors.addItem(author.getComboItem());
        }

        czrc.getScopesSortedByRecentUsage(VfsUtil.virtualToIoFile(project.getBaseDir()))
                .forEach(scopes::addItem);
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
    private ArrayList<Type> getTypes() {
        ArrayList<Type> types = new ArrayList<>();
        TypeComboItem typeComboItem = (TypeComboItem) this.types.getSelectedItem();
        types.add((Type) typeComboItem.getValue());
        return types;
    }

    @NotNull
    private ArrayList<String> getScopes() {
        ArrayList<String> scopes = new ArrayList<>();
        scopes.add((String) this.scopes.getSelectedItem());
        return scopes;
    }

    @NotNull
    private ArrayList<Issue> getIssues() {
        ArrayList<Issue> issues = new ArrayList<>();
        //issues.add(((String)this.issues.getSelectedItem()).trim());
        issues.add(new Issue(new IssueTracker("JIRA"), "S-123"));
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
        AuthorComboItem authorComboItem = (AuthorComboItem) this.coAuthors.getSelectedItem();
        coAuthors.add((Author) authorComboItem.getValue());
        return coAuthors;
    }
}
