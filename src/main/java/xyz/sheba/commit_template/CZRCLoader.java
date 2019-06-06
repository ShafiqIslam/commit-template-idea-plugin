package xyz.sheba.commit_template;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import xyz.sheba.commit_template.dto.CZRC;
import xyz.sheba.commit_template.utils.Command;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class CZRCLoader {

    private static CZRC czrc = null;

    static CZRC load(Project project) throws IOException, ParseException {
        if(czrc != null) return czrc;

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(System.getProperty("user.home") + "/.czrc.json");
        JSONObject rc = (JSONObject) jsonParser.parse(reader);
        try {
            reader = new FileReader(project.getBasePath() + "/.czrc.json");
            JSONObject localRc = (JSONObject) jsonParser.parse(reader);

            JSONArray scopes = (JSONArray) localRc.get("scopes");

            /*File workingDirectory = VfsUtil.virtualToIoFile(project.getBaseDir());
            String recentLogCommand = "git log --all --format=%s | grep -Eo '^[a-z]+(\\(.*\\)):.*$' | sed 's/^.*(\\(.*\\)):.*$/\\1/' | sort -n | uniq";
            Command.Result result = new Command(workingDirectory, recentLogCommand).execute();
            if (result.isSuccess()) {
                scopes.sort((o1, o2) -> result.compare(o1.toString(), o2.toString()));
            }*/

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
