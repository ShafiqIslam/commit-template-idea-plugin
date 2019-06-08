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

                /*String recentLogCommand = "git log --all --format=%s | grep -Eo '^[a-z]+(\\(.*\\)):.*$' | sed 's/^.*(\\(.*\\)):.*$/\\1/' | sort -n | uniq";
                Command.Result result = new Command(project.getBaseWorkingDir(), recentLogCommand).execute();
                if (result.isSuccess()) {
                    scopes.sort((o1, o2) -> result.compare(o1.toString(), o2.toString()));
                }*/

                rc.put("scopes", scopes);
            } catch (Exception ignored) {}

            czrc = new CZRC(rc);
            return czrc;
        }

        public static CZRC get() throws Exception {
            if(czrc != null) return czrc;

            throw new Exception("Czrc is not loaded.");
        }
    }

    private CZRC(JSONObject source) {
        this.source = source;
        this.setBodyMaxLength().setSubjectMaxLength().setTypes()
                .setAuthors().setIssueTrackers().setScopes();
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
}
