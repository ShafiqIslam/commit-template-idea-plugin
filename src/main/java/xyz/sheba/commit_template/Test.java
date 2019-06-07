package xyz.sheba.commit_template;

import org.jetbrains.annotations.NotNull;
import xyz.sheba.commit_template.dto.*;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        Message m = Message.Builder.getInstance()
                /*.setSubject("sdfsdaf")
                .setTypes()
                .setScopes(getScopes())
                .setWhat(what.getText().trim())
                .setWhy(why.getText().trim())
                .setIssues(getIssueTrackers())
                .setCoAuthors(getCoAuthors())
                .setReferences(getReferences())*/
                .build();

        System.out.println(m);
    }

   /* @NotNull
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
    }*/
}
