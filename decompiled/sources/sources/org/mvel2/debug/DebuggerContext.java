package org.mvel2.debug;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.mvel2.ast.LineLabel;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.integration.VariableResolverFactory;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class DebuggerContext {
    private Map<String, Set<Integer>> breakpoints;
    private Debugger debugger;
    private int debuggerState;

    public DebuggerContext() {
        this.debuggerState = 0;
        this.breakpoints = new HashMap();
    }

    public int checkBreak(LineLabel r2, VariableResolverFactory r3, CompiledExpression r4) {
        if (this.debuggerState != 1) goto L5;
    L9:
        Debugger r42 = this.debugger;
        if (r42 == null) goto L13;
        int r22 = r42.onBreak(new Frame(r2, r3));
        this.debuggerState = r22;
        return r22;
    L13:
        GlShader$$ExternalSyntheticBUOutline1.m("no debugger registered to handle breakpoint");
        return 0;
    L5:
        if (hasBreakpoint(r2) == true) goto L9;
        return 0;
    }

    public void clearAllBreakpoints() {
        this.breakpoints.clear();
    }

    public Map<String, Set<Integer>> getBreakpoints() {
        return this.breakpoints;
    }

    public Debugger getDebugger() {
        return this.debugger;
    }

    public int getDebuggerState() {
        return this.debuggerState;
    }

    public boolean hasBreakpoint(String r2, int r3) {
        if (this.breakpoints.containsKey(r2) == true) goto L5;
        return false;
    L5:
        if (this.breakpoints.get(r2).contains(Integer.valueOf(r3)) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasBreakpoint(LineLabel r3) {
        if (this.breakpoints.containsKey(r3.getSourceFile()) == true) goto L5;
        return false;
    L5:
        if (this.breakpoints.get(r3.getSourceFile()).contains(Integer.valueOf(r3.getLineNumber())) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasBreakpoints() {
        if (this.breakpoints.size() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasDebugger() {
        if (this.debugger == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void registerBreakpoint(String r3, int r4) {
        if (this.breakpoints.containsKey(r3) == true) goto L5;
        this.breakpoints.put(r3, new HashSet());
    L5:
        this.breakpoints.get(r3).add(Integer.valueOf(r4));
    }

    public void removeBreakpoint(String r2, int r3) {
        if (this.breakpoints.containsKey(r2) == true) goto L5;
        return;
    L5:
        this.breakpoints.get(r2).remove(Integer.valueOf(r3));
    }

    public void setBreakpoints(Map<String, Set<Integer>> r1) {
        this.breakpoints = r1;
    }

    public void setDebugger(Debugger r1) {
        this.debugger = r1;
    }

    public void setDebuggerState(int r1) {
        this.debuggerState = r1;
    }
}
