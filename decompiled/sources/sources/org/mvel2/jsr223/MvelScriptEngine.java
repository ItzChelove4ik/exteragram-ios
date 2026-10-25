package org.mvel2.jsr223;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public class MvelScriptEngine extends AbstractScriptEngine implements ScriptEngine, Compilable {
    private volatile MvelScriptEngineFactory factory;

    public MvelScriptEngine() {
    }

    private static String readFully(Reader r5) {
        char[] r1 = new char[8192];
        StringBuilder r2 = new StringBuilder();
    L13:
        int r4 = r5.read(r1, 0, 8192);     // Catch: IOException -> L10
        if (r4 <= 0) goto L9;
        r2.append(r1, 0, r4);     // Catch: IOException -> L10
        goto L13
    L9:
        return r2.toString();
    L10:
        e = move-exception;
        throw new ScriptException(e);
    }

    public CompiledScript compile(Reader r1) {
        return compile(readFully(r1));
    }

    public CompiledScript compile(String r2) {
        return new MvelCompiledScript(this, compiledScript(r2));
    }

    public Serializable compiledScript(String r1) {
        return MVEL.compileExpression(r1);
    L4:
        e = move-exception;
        throw new ScriptException(e);
    }

    public Bindings createBindings() {
        return new MvelBindings();
    }

    public Object eval(Reader r1, ScriptContext r2) {
        return eval(readFully(r1), r2);
    }

    public Object eval(String r1, ScriptContext r2) {
        return evaluate(compiledScript(r1), r2);
    }

    public Object evaluate(Serializable r1, ScriptContext r2) {
        return MVEL.executeExpression(r1, r2.getBindings(100));
    L5:
        e = move-exception;
        throw new ScriptException(e);
    }

    public ScriptEngineFactory getFactory() {
        if (this.factory != null) goto L15;
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L6:
        if (this.factory != null) goto L10;
        this.factory = new MvelScriptEngineFactory();     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
    L15:
        return this.factory;
    }
}
