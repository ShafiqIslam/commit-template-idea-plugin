package xyz.sheba.commit_template;

import org.jetbrains.annotations.NotNull;
import xyz.sheba.commit_template.dto.*;
import xyz.sheba.commit_template.project.IProject;
import xyz.sheba.commit_template.project.MockProject;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws Exception {
        IProject project = new MockProject("/home/shafiq/workspace/commit-template-idea-plugin"); //"E:\\Works\\Khucra\\commit-template"
        CZRC czrc = CZRC.Loader.load(project);
        /*Message m = Message.Builder.getInstance()
                .setSubject("sdfsdaf dfgdf g dfg. . .. ")
                .setTypes(getTypes())
                .setScopes(getScopes())
                .setWhat("asfd")
                .setWhy("sdfasdf")
                .setIssues(getIssueTrackers())
                .setCoAuthors(getCoAuthors())
                .setReferences(getReferences())
                .build();*/

        System.out.println(czrc.getTypes().get(0).getEmoji());
    }

    @NotNull
    private static ArrayList<Type> getTypes() {
        ArrayList<Type> types = new ArrayList<>();
        types.add(new Type("ADD", "a", "Add", ":add:"));
        return types;
    }

    @NotNull
    private static ArrayList<String> getScopes() {
        ArrayList<String> scopes = new ArrayList<>();
        scopes.add("blah");
        return scopes;
    }

    @NotNull
    private static ArrayList<Issue> getIssueTrackers() {
        ArrayList<Issue> issues = new ArrayList<>();
        issues.add(new Issue(new IssueTracker("JIRA"), "S-123"));
        return issues;
    }

    @NotNull
    private static ArrayList<String> getReferences() {
        ArrayList<String> references = new ArrayList<>();
        references.add("blah blah blah");
        return references;
    }

    @NotNull
    private static ArrayList<Author> getCoAuthors() {
        ArrayList<Author> coAuthors = new ArrayList<>();
        coAuthors.add(new Author("sss", "s@s.c"));
        return coAuthors;
    }
}
