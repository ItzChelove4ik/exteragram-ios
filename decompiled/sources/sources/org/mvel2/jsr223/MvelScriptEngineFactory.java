package org.mvel2.jsr223;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public class MvelScriptEngineFactory implements ScriptEngineFactory {
    private static final String ENGINE_NAME = "MVEL (MVFLEX Expression Language)";
    private static final String ENGINE_VERSION = "2.3";
    private static final List<String> EXTENSIONS = null;
    private static final String LANGUAGE_NAME = "mvel";
    private static final String LANGUAGE_VERSION = "2.3";
    private static final List<String> MIME_TYPES = null;
    private static final MvelScriptEngine MVEL_SCRIPT_ENGINE = null;
    private static final List<String> NAMES = null;

    static {
        ArrayList r0 = new ArrayList(1);
        r0.add(LANGUAGE_NAME);
        List<String> r02 = Collections.unmodifiableList(r0);
        NAMES = r02;
        EXTENSIONS = r02;
        ArrayList r03 = new ArrayList(1);
        r03.add("application/x-mvel");
        MIME_TYPES = Collections.unmodifiableList(r03);
        MVEL_SCRIPT_ENGINE = new MvelScriptEngine();
    }

    public MvelScriptEngineFactory() {
    }

    public String getEngineName() {
        return "MVEL (MVFLEX Expression Language)";
    }

    public String getEngineVersion() {
        return MVEL.VERSION;
    }

    public List<String> getExtensions() {
        return EXTENSIONS;
    }

    public String getLanguageName() {
        return LANGUAGE_NAME;
    }

    public String getLanguageVersion() {
        return MVEL.VERSION;
    }

    public String getMethodCallSyntax(String r1, String r2, String... r3) {
        return null;
    }

    public List<String> getMimeTypes() {
        return MIME_TYPES;
    }

    public List<String> getNames() {
        return NAMES;
    }

    public String getOutputStatement(String r1) {
        return null;
    }

    public Object getParameter(String r2) {
        if (r2.equals("javax.script.name") == false) goto L7;
        return getLanguageName();
    L7:
        if (r2.equals("javax.script.engine") == false) goto L11;
        return getEngineName();
    L11:
        if (r2.equals("javax.script.engine_version") == false) goto L15;
        return getEngineVersion();
    L15:
        if (r2.equals("javax.script.language") == false) goto L19;
        return getLanguageName();
    L19:
        if (r2.equals("javax.script.language_version") == false) goto L23;
        return getLanguageVersion();
    L23:
        if (r2.equals("THREADING") == false) goto L26;
        return "THREAD-ISOLATED";
    L26:
        return null;
    }

    public String getProgram(String... r1) {
        return null;
    }

    public ScriptEngine getScriptEngine() {
        return MVEL_SCRIPT_ENGINE;
    }
}
