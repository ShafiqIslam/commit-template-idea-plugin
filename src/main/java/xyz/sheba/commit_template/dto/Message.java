package xyz.sheba.commit_template.dto;

import org.apache.commons.lang.WordUtils;

import java.util.ArrayList;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Message {

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

    @Override
    public String toString() {
        try {
            return new Formatter().format();
        } catch (Exception e) {
            return null;
        }
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public ArrayList<String> getScopes() {
        return scopes;
    }

    public String getWhat() {
        return what;
    }

    public String getWhy() {
        return why;
    }

    public ArrayList<Issue> getIssues() {
        return issues;
    }

    public ArrayList<Author> getCoAuthors() {
        return coAuthors;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public static class Builder {
        private String subject;
        private ArrayList<Type> types;
        private ArrayList<String> scopes;
        private String what;
        private String why;
        private ArrayList<Issue> issues;
        private ArrayList<Author> coAuthors;
        private ArrayList<String> references;

        private Builder() {}

        public static Builder getInstance()
        {
            return new Builder();
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setTypes(ArrayList<Type> types) {
            this.types = types;
            return this;
        }

        public Builder setScopes(ArrayList<String> scopes) {
            this.scopes = scopes;
            return this;
        }

        public Builder setWhat(String what) {
            this.what = what;
            return this;
        }

        public Builder setWhy(String why) {
            this.why = why;
            return this;
        }

        public Builder setIssues(ArrayList<Issue> issues) {
            this.issues = issues;
            return this;
        }

        public Builder setCoAuthors(ArrayList<Author> coAuthors) {
            this.coAuthors = coAuthors;
            return this;
        }

        public Builder setReferences(ArrayList<String> references) {
            this.references = references;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

    private class Formatter {
        private StringBuilder builder;
        private CZRC czrc;
        private Message message;

        Formatter() throws Exception {
            message = Message.this;
            builder = new StringBuilder();
            czrc = CZRC.Loader.get();
        }

        String format() {
            String ls = System.lineSeparator();
            String ls2 = ls + ls;

            String subject = message.getSubject();
            subject = subject.substring(0, Math.min(subject.length(), czrc.getSubjectMaxLength()));
            builder.append(subject).append(ls2);

            builder.append("Type(s): ").append(message.getTypes().get(0)).append(ls2);

            if (message.getScopes().size() != 0) {
                builder.append("Scopes(s): ");
                for (String scope : message.getScopes()) {
                    if(isNotBlank(scope)) {
                        builder.append(scope).append(", ");
                    }
                }
            }

            builder.append(ls2);

            if(message.getWhy().length() != 0) {
                builder.append("Why:").append(ls)
                        .append(WordUtils.wrap(message.getWhy(), czrc.getBodyMaxLength()))
                        .append(ls2);
            }

            if(message.getWhat().length() != 0) {
                builder.append("What:").append(ls)
                        .append(WordUtils.wrap(message.getWhat(), czrc.getBodyMaxLength()))
                        .append(ls2);
            }

            if (message.getIssues().size() != 0) {
                builder.append("Issues:").append(ls);
                for (Issue issue : message.getIssues()) {
                    builder.append("- ").append(issue).append(ls);
                }
                builder.append(ls);
            }

            if (message.getReferences().size() != 0) {
                builder.append("References:").append(ls);
                for (String reference : message.getReferences()) {
                    if(isNotBlank(reference)) {
                        builder.append("- ").append(reference).append(ls);
                    }
                }
                builder.append(ls);
            }

            if (message.getCoAuthors().size() != 0) {
                builder.append("Co Authored By:").append(ls);
                for (Author author : message.getCoAuthors()) {
                    builder.append("- ").append(author).append(ls);
                }
                builder.append(ls);
            }

            return builder.toString().trim();
        }
    }
}