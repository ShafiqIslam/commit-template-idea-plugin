package xyz.sheba.developers.commit_template;

import org.apache.commons.lang.WordUtils;

import java.util.ArrayList;

import static org.apache.commons.lang.StringUtils.isNotBlank;

class Message {
    private static final int MAX_LINE_LENGTH = 72;

    private String subject;
    private ArrayList types;
    private ArrayList<String> scopes;
    private String what;
    private String why;
    private ArrayList issues;
    private ArrayList coAuthors;
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
        return this.format();
    }

    static class Builder {
        private String subject;
        private ArrayList types;
        private ArrayList<String> scopes;
        private String what;
        private String why;
        private ArrayList issues;
        private ArrayList coAuthors;
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

        Builder setTypes(ArrayList types) {
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

        Builder setIssues(ArrayList issues) {
            this.issues = issues;
            return this;
        }

        Builder setCoAuthors(ArrayList coAuthors) {
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