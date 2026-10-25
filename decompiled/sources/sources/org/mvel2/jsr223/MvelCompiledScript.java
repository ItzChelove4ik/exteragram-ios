package org.mvel2.jsr223;

import java.io.Serializable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;

/* loaded from: classes5.dex */
public class MvelCompiledScript extends CompiledScript {
    private final Serializable compiledScript;
    private final MvelScriptEngine scriptEngine;

    public MvelCompiledScript(MvelScriptEngine r1, Serializable r2) {
        this.scriptEngine = r1;
        this.compiledScript = r2;
    }

    public Object eval(ScriptContext r2) {
        return this.scriptEngine.evaluate(this.compiledScript, r2);
    }

    public ScriptEngine getEngine() {
        return this.scriptEngine;
    }
}
