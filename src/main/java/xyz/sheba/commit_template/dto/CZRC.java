package xyz.sheba.commit_template.dto;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CZRC {

    private JSONObject source;

    private int subjectMaxLength;
    private int bodyMaxLength;
    private ArrayList<Type> types;
    private ArrayList<Author> authors;
    private ArrayList<IssueTracker> issueTrackers;
    private ArrayList<String> scopes;
    private int typesMaxNameLength = 0;
    private int typesMaxEmojiLength = 0;

    public int getSubjectMaxLength() {
        return subjectMaxLength;
    }

    public int getBodyMaxLength() {
        return bodyMaxLength;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public ArrayList<IssueTracker> getIssueTrackers() {
        return issueTrackers;
    }

    public ArrayList<String> getScopes() {
        return scopes;
    }

    public int getTypesMaxNameLength() {
        return typesMaxNameLength;
    }

    public int getTypesMaxEmojiLength() {
        return typesMaxEmojiLength;
    }

    public CZRC(JSONObject source) {
        this.source = source;
        this.setBodyMaxLength().setSubjectMaxLength().setTypes()
                .setAuthors().setIssueTrackers().setScopes()
                .setTypeLengths();
    }

    private CZRC setSubjectMaxLength() {
        this.subjectMaxLength = ((Long)this.source.get("subject_max_length")).intValue();
        return this;
    }

    private CZRC setBodyMaxLength() {
        this.bodyMaxLength = ((Long)this.source.get("body_max_length")).intValue();
        return this;
    }

    private CZRC setTypes() {
        JSONArray types_raw = (JSONArray)this.source.get("types");
        types = new ArrayList<>();
        types_raw.forEach(raw_type -> {
            JSONObject type = (JSONObject) raw_type;
            String name = (String) type.get("name");
            String emoji = (String) type.get("emoji");
            String description = (String) type.get("description");
            String code = (String) type.get("code");
            types.add(new Type(name, emoji, description, code));
        });
        return this;
    }

    private CZRC setAuthors() {
        JSONArray authorsRaw = (JSONArray)this.source.get("authors");
        authors = new ArrayList<>();
        authorsRaw.forEach(authorRaw -> {
            JSONObject author = (JSONObject) authorRaw;
            String name = (String) author.get("name");
            String email = (String) author.get("email");
            authors.add(new Author(name, email));
        });
        return this;
    }

    private CZRC setIssueTrackers() {
        JSONArray issueTrackersRaw = (JSONArray)this.source.get("issue_trackers");
        issueTrackers = new ArrayList<>();
        issueTrackersRaw.forEach(issueTracker -> issueTrackers.add(new IssueTracker((String) issueTracker)));
        return this;
    }

    private CZRC setScopes() {
        JSONArray scopesRaw = (JSONArray)this.source.get("scopes");
        scopes = new ArrayList<>();
        if(scopesRaw != null) scopesRaw.forEach(scope -> scopes.add((String) scope));
        return this;
    }

    private CZRC setTypeLengths() {
        for (Type type : types) {
            typesMaxNameLength = Math.max(type.getName().length(), typesMaxNameLength);
            typesMaxEmojiLength = Math.max(type.getEmoji().length(), typesMaxEmojiLength);
        }
        return this;
    }
}
