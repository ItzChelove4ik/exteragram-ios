package org.mvel2;

import org.mvel2.ast.ASTNode;
import org.mvel2.ast.LineLabel;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.debug.Debugger;
import org.mvel2.debug.DebuggerContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.OptimizerFactory;
import org.mvel2.util.ExecutionStack;
import org.mvel2.util.PropertyTools;

/* loaded from: classes5.dex */
public class MVELRuntime {
    private static ThreadLocal<DebuggerContext> debuggerContext;

    public MVELRuntime() {
    }

    public static void clearAllBreakpoints() {
        if (hasDebuggerContext() == false) goto L6;
        debuggerContext.get().clearAllBreakpoints();
        return;
    }

    private static void ensureDebuggerContext() {
        if (debuggerContext != null) goto L6;
        debuggerContext = new ThreadLocal();
    L6:
        if (debuggerContext.get() != null) goto L9;
        debuggerContext.set(new DebuggerContext());
        return;
    }

    public static Object execute(boolean r8, CompiledExpression r9, Object r10, VariableResolverFactory r11) {
        ExecutionStack r0 = new ExecutionStack();
        ASTNode r1 = r9.getFirstNode();
        if (r1 != null) goto L100;
        return null;
    L100:
    L96:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    L11:
        e = move-exception;
        if (r1 != null) goto L90;
    L95:
        throw e;     // Catch: Throwable -> L96
    L90:
        if (r1.isOperator() == false) goto L95;
        if (r1.nextASTNode == null) goto L95;
        throw new CompileException("incomplete statement: " + r1.getName() + " (possible use of reserved keyword as identifier: " + r1.getName() + ")", r1.getExpr(), r1.getStart());     // Catch: Throwable -> L96
    L6:
        if (r1.fields != (-1)) goto L16;
        if (r8 == true) goto L101;
        r8 = hasDebuggerContext();     // Catch: NullPointerException -> L11 Throwable -> L96
        if (r8 == true) goto L101;
    L83:
        r1 = r1.nextASTNode;     // Catch: NullPointerException -> L11 Throwable -> L96
        if (r1 != null) goto L100;
        Object r82 = r0.peek();     // Catch: NullPointerException -> L11 Throwable -> L96
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r82;
    L101:
        debuggerContext.get().checkBreak((LineLabel) r1, r11, r9);     // Catch: NullPointerException -> L99 Throwable -> L96
        goto L83
    L16:
        if (r0.isEmpty() == false) goto L19;
        r0.push(r1.getReducedValueAccelerated(r10, r10, r11));     // Catch: NullPointerException -> L11 Throwable -> L96
    L19:
        if (r11.tiltFlag() == true) goto L20;
        Integer r3 = r1.getOperator();     // Catch: NullPointerException -> L11 Throwable -> L96
        int r5 = r3.intValue();     // Catch: NullPointerException -> L11 Throwable -> L96
        if (r5 == (-1)) goto L83;
        if (r5 == 37) goto L81;
        int r6 = 1;
        if (r5 == 99) goto L77;
        if (r5 == 29) goto L65;
        if (r5 == 30) goto L61;
        r0.push(r1.nextASTNode.getReducedValueAccelerated(r10, r10, r11), r3);     // Catch: NullPointerException -> L11 Throwable -> L96
    L103:
        if (r0.isReduceable() == false) goto L83;
        if (((Integer) r0.peek()).intValue() == 23) goto L38;
        r0.op();     // Catch: Exception -> L44 CompileException -> L46 ClassCastException -> L48 Throwable -> L96
        goto L103
    L38:
        r0.pop();     // Catch: Exception -> L44 CompileException -> L46 ClassCastException -> L48 Throwable -> L96
        Object r4 = r0.pop();     // Catch: Exception -> L44 CompileException -> L46 ClassCastException -> L48 Throwable -> L96
        Object r52 = r0.pop();     // Catch: Exception -> L44 CompileException -> L46 ClassCastException -> L48 Throwable -> L96
        if (PropertyTools.isEmpty(r52) == false) goto L50;
        if (PropertyTools.isEmpty(r4) == false) goto L50;
        r0.push(null);     // Catch: Exception -> L44 CompileException -> L46 ClassCastException -> L48 Throwable -> L96
    L50:
        r0.clear();     // Catch: Exception -> L44 CompileException -> L46 ClassCastException -> L48 Throwable -> L96
        if (PropertyTools.isEmpty(r52) == true) goto L53;
        r4 = r52;
    L53:
        r0.push(r4);     // Catch: Exception -> L44 CompileException -> L46 ClassCastException -> L48 Throwable -> L96
        goto L103
    L48:
        e = move-exception;
        throw new CompileException("syntax error or incomptable types", new char[0], 0, e);     // Catch: NullPointerException -> L11 Throwable -> L96
    L46:
        e = move-exception;
        throw e;     // Catch: NullPointerException -> L11 Throwable -> L96
    L44:
        e = move-exception;
        throw new CompileException("failed to compileShared sub expression", new char[0], 0, e);     // Catch: NullPointerException -> L11 Throwable -> L96
    L61:
        Object r83 = r0.pop();     // Catch: NullPointerException -> L11 Throwable -> L96
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r83;
    L65:
        if (r0.popBoolean().booleanValue() == true) goto L76;
    L66:
        if (r6 <= 0) goto L76;
        r1 = r1.nextASTNode;     // Catch: NullPointerException -> L11 Throwable -> L96
        if (r1 == null) goto L76;
        if (r1.isOperator(30) == true) goto L72;
        if (r1.isOperator(29) == false) goto L66;
        r6 = r6 + 1;     // Catch: NullPointerException -> L11 Throwable -> L96
        goto L66
    L72:
        r6 = r6 - 1;
    L76:
        r0.clear();     // Catch: NullPointerException -> L11 Throwable -> L96
        goto L83
    L77:
        r11.setTiltFlag(true);     // Catch: NullPointerException -> L11 Throwable -> L96
        Object r84 = r0.pop();     // Catch: NullPointerException -> L11 Throwable -> L96
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r84;
    L81:
        if (r1.nextASTNode == null) goto L83;
        r0.clear();     // Catch: NullPointerException -> L11 Throwable -> L96
        goto L83
    L20:
        Object r85 = r0.pop();     // Catch: NullPointerException -> L11 Throwable -> L96
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r85;
    }

    public static boolean hasBreakpoints() {
        if (hasDebuggerContext() == true) goto L5;
        return false;
    L5:
        if (debuggerContext.get().hasBreakpoints() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean hasDebuggerContext() {
        ThreadLocal<DebuggerContext> r0 = debuggerContext;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.get() == null) goto L10;
        return true;
    L10:
        return false;
    }

    public static void registerBreakpoint(String r1, int r2) {
        ensureDebuggerContext();
        debuggerContext.get().registerBreakpoint(r1, r2);
    }

    public static void removeBreakpoint(String r1, int r2) {
        if (hasDebuggerContext() == false) goto L6;
        debuggerContext.get().removeBreakpoint(r1, r2);
        return;
    }

    public static void resetDebugger() {
        debuggerContext = null;
    }

    public static void setThreadDebugger(Debugger r1) {
        ensureDebuggerContext();
        debuggerContext.get().setDebugger(r1);
    }
}
