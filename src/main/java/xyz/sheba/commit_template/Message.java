package xyz.sheba.commit_template;

import org.apache.commons.lang.WordUtils;
import xyz.sheba.commit_template.dto.Issue;
import xyz.sheba.commit_template.dto.Type;
import xyz.sheba.commit_template.dto.Author;

import java.util.ArrayList;

class Message {
    private static final int MAX_LINE_LENGTH = 72;

    private String subject;
    private ArrayList<Type> types;
    private ArrayList<String> scopes;
    private String what;
    private String why;
    private ArrayList<Issue> issues;
    private ArrayList<Author> coAuthors;
    private ArrayList<String> references;

    private Message(Builder builder) {
        this.subject = builder.subject;
        this.types = builder.types;
        this.scopes = builder.scopes;
        this.what = builder.what;
        this.why = builder.why;
        this.issues = builder.issues;
        this.coAuthors = builder.coAuthors;
        this.references = builder.references;
    }

    private String format() {
        String ls = System.lineSeparator();
        StringBuilder builder = new StringBuilder();

        builder.append(subject);
        builder.append(ls)
                .append(types.get(0));

        if (scopes.size() != 0) {
            builder.append(ls);
            for (String scope : scopes) {
                builder.append(ls).append(scope);
            }
        }

        builder.append(": ")
                .append(WordUtils.wrap(what, MAX_LINE_LENGTH))
                .append(": ")
                .append(WordUtils.wrap(why, MAX_LINE_LENGTH))
                .append(ls);

        if (references.size() != 0) {
            builder.append(ls);
            for (String reference : references) {
                builder.append(ls).append(reference);
            }
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        System.out.println(this.types.get(0).toString());
        return this.types.get(0).toString();
        //return this.format();
    }

    static class Builder {
        private String subject;
        private ArrayList<Type> types;
        private ArrayList<String> scopes;
        private String what;
        private String why;
        private ArrayList<Issue> issues;
        private ArrayList<Author> coAuthors;
        private ArrayList<String> references;

        private Builder() {}

        static Builder getInstance()
        {
            return new Builder();
        }

        Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        Builder setTypes(ArrayList<Type> types) {
            this.types = types;
            return this;
        }

        Builder setScopes(ArrayList<String> scopes) {
            this.scopes = scopes;
            return this;
        }

        Builder setWhat(String what) {
            this.what = what;
            return this;
        }

        Builder setWhy(String why) {
            this.why = why;
            return this;
        }

        Builder setIssues(ArrayList<Issue> issues) {
            this.issues = issues;
            return this;
        }

        Builder setCoAuthors(ArrayList<Author> coAuthors) {
            this.coAuthors = coAuthors;
            return this;
        }

        Builder setReferences(ArrayList<String> references) {
            this.references = references;
            return this;
        }

        Message build() {
            return new Message(this);
        }
    }
}