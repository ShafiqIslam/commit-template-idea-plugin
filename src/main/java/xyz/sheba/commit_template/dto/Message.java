package xyz.sheba.commit_template.dto;

import org.apache.commons.lang.WordUtils;
import org.json.simple.JSONObject;
import xyz.sheba.commit_template.utils.StringUtils;
import java.util.ArrayList;

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
            e.printStackTrace();
            return null;
        }
    }

    private String getSubject() {
        return subject;
    }

    private ArrayList<Type> getTypes() {
        return types;
    }

    private ArrayList<String> getScopes() {
        return scopes;
    }

    private String getWhat() {
        return what;
    }

    private String getWhy() {
        return why;
    }

    private ArrayList<Issue> getIssues() {
        return issues;
    }

    private ArrayList<Author> getCoAuthors() {
        return coAuthors;
    }

    private ArrayList<String> getReferences() {
        return references;
    }

    private boolean hasScopes() {
        return scopes != null && scopes.size() != 0;
    }

    private boolean hasWhat() {
        return what != null && what.length() != 0;
    }

    private boolean hasWhy() {
        return why != null && why.length() != 0;
    }

    private boolean hasIssues() {
        return issues != null && issues.size() != 0;
    }

    private boolean hasReferences() {
        return references != null && references.size() != 0;
    }

    private boolean hasCoAuthors() {
        return coAuthors != null && coAuthors.size() != 0;
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
        private CZRC czrc;
        private JSONObject headers;
        private Message message;

        Formatter() throws Exception {
            message = Message.this;
            czrc = CZRC.Loader.get();
            headers = czrc.getSectionHeaders();
        }

        String format() {
            System.out.println();
            String ls = System.lineSeparator();
            String ls2 = ls + ls;
            StringBuilder builder = new StringBuilder();

            builder.append(formatSubject()).append(ls2);

            builder.append(headers.get("types")).append(formatTypes()).append(ls2);

            if (message.hasScopes()) {
                builder.append(headers.get("scopes")).append(formatScopes()).append(ls2);
            }

            if(message.hasWhy()) {
                builder.append(headers.get("why")).append(formatWhy()).append(ls2);
            }

            if(message.hasWhat()) {
                builder.append(headers.get("what")).append(formatWhat()).append(ls2);
            }

            if (message.hasIssues()) {
                builder.append(headers.get("issues")).append(formatIssues()).append(ls);
            }

            if (message.hasReferences()) {
                builder.append(headers.get("references")).append(formatReferences()).append(ls);
            }

            if (message.hasCoAuthors()) {
                builder.append(headers.get("co_authors")).append(formatCoAuthors()).append(ls);
            }

            return builder.toString().trim();
        }

        private String formatSubject() {
            String subject = StringUtils.strip(message.getSubject(), ". ");
            subject = StringUtils.capitalize(subject);
            subject = message.getTypes().get(0).getEmoji() + " " + subject;
            return StringUtils.truncate(subject, czrc.getSubjectMaxLength());
        }

        private String formatTypes() {
            return message.getTypes().get(0).getName();
        }

        private String formatScopes() {
            StringBuilder builder = new StringBuilder();
            for (String scope : message.getScopes()) {
                if(StringUtils.isNotBlank(scope)) {
                    builder.append(scope).append(czrc.getInlineSeparator());
                }
            }

            return wrapBody(StringUtils.strip(builder.toString(), czrc.getInlineSeparator()));
        }

        private String formatWhy() {
            return wrapBody(message.getWhy());
        }

        private String formatWhat() {
            return wrapBody(message.getWhat());
        }

        private String formatIssues() {
            StringBuilder builder = new StringBuilder();
            for (Issue issue : message.getIssues()) {
                builder.append(czrc.getBullet())
                        .append(issue.getTracker().getName())
                        .append(czrc.getIssueTrackerIdSeparator())
                        .append(issue.getId())
                        .append(System.lineSeparator());
            }
            return wrapBody(builder.toString());
        }

        private String formatReferences() {
            StringBuilder builder = new StringBuilder();
            for (String reference : message.getReferences()) {
                if(StringUtils.isNotBlank(reference)) {
                    builder.append(czrc.getBullet()).append(reference).append(System.lineSeparator());
                }
            }
            return wrapBody(builder.toString());
        }
        private String formatCoAuthors() {
            StringBuilder builder = new StringBuilder();
            for (Author author : message.getCoAuthors()) {
                builder.append(czrc.getBullet())
                        .append(author.getName())
                        .append(czrc.getAuthorEmailTags().get("start"))
                        .append(author.getEmail())
                        .append(czrc.getAuthorEmailTags().get("end"))
                        .append(System.lineSeparator());
            }
            return builder.toString();
        }

        private String wrapBody(String body) {
            return WordUtils.wrap(body, czrc.getBodyMaxLength());
        }
    }
}