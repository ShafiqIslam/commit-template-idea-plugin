package xyz.sheba.commit_template.dto;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import xyz.sheba.commit_template.project.IProject;

public class CZRC {

    private JSONObject source;

    private int subjectMaxLength;
    private int bodyMaxLength;
    private ArrayList<Type> types;
    private ArrayList<Author> authors;
    private ArrayList<IssueTracker> issueTrackers;
    private ArrayList<String> scopes;
    private JSONObject sectionHeaders;
    private String inlineSeparator;
    private String bullet;
    private String issueTrackerIdSeparator;
    private JSONObject authorEmailTags;

    int getSubjectMaxLength() {
        return subjectMaxLength;
    }

    int getBodyMaxLength() {
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

    JSONObject getSectionHeaders() {
        return sectionHeaders;
    }

    String getInlineSeparator() {
        return inlineSeparator;
    }

    String getBullet() {
        return bullet;
    }

    String getIssueTrackerIdSeparator() {
        return issueTrackerIdSeparator;
    }

    JSONObject getAuthorEmailTags() {
        return authorEmailTags;
    }

    public static class Loader {

        private static CZRC czrc = null;

        public static CZRC load(IProject project) throws IOException, ParseException {
            if(czrc != null) return czrc;

            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(System.getProperty("user.home") + "/.czrc.json");
            JSONObject rc = (JSONObject) jsonParser.parse(reader);

            try {
                reader = new FileReader(project.getBasePath() + "/.czrc.json");
                JSONObject localRc = (JSONObject) jsonParser.parse(reader);
                JSONArray scopes = (JSONArray) localRc.get("scopes");
                rc.put("scopes", scopes);
            } catch (Exception ignored) {}

            czrc = new CZRC(rc);
            return czrc;
        }

        static CZRC get() throws Exception {
            if(czrc != null) return czrc;

            throw new Exception("Czrc is not loaded.");
        }
    }

    private CZRC(JSONObject source) {
        this.source = source;
        this.setBodyMaxLength().setSubjectMaxLength().setTypes().setAuthors()
                .setIssueTrackers().setScopes().setHeaders().setInlineSeparator()
                .setBullet().setIssueTrackerIdSeparator().setAuthorEmailTags();
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

    private CZRC setHeaders() {
        this.sectionHeaders = (JSONObject) this.source.get("section_headers");
        return this;
    }

    private CZRC setInlineSeparator() {
        this.inlineSeparator = (String)this.source.get("inline_separator");
        return this;
    }

    private CZRC setBullet() {
        this.bullet = (String)this.source.get("bullet");
        return this;
    }

    private CZRC setIssueTrackerIdSeparator() {
        this.issueTrackerIdSeparator = (String)this.source.get("issue_tracker_id_separator");
        return this;
    }

    private CZRC setAuthorEmailTags() {
        this.authorEmailTags = (JSONObject) this.source.get("author_email_tag");
        return this;
    }
}
