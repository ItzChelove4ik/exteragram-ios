package org.mvel2.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import org.mvel2.ParserContext;
import org.mvel2.ast.EndOfStatement;
import org.mvel2.ast.Negation$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Proto;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.compiler.ExecutableStatement;

/* loaded from: classes5.dex */
public class ProtoParser {
    private static ThreadLocal<Queue<DeferredTypeResolve>> deferred;
    private int cursor;
    private String deferredName;
    private int endOffset;
    private char[] expr;
    private boolean interpreted;
    private String name;
    private ParserContext pCtx;
    private String protoName;
    private ExecutionStack splitAccumulator;
    String tk1;
    String tk2;
    private Class type;

    /* renamed from: org.mvel2.util.ProtoParser$1, reason: invalid class name */
    public class AnonymousClass1 implements DeferredTypeResolve {
        final /* synthetic */ ProtoParser this$0;
        final /* synthetic */ String val$initializer;
        final /* synthetic */ String val$name;
        final /* synthetic */ Proto.Receiver val$receiver;

        public AnonymousClass1(ProtoParser r1, String r2, Proto.Receiver r3, String r4) {
            this.this$0 = r1;
            this.val$name = r2;
            this.val$receiver = r3;
            this.val$initializer = r4;
        }

        @Override // org.mvel2.util.ProtoParser.DeferredTypeResolve
        public String getName() {
            return this.val$name;
        }

        @Override // org.mvel2.util.ProtoParser.DeferredTypeResolve
        public boolean isWaitingFor(Proto r2) {
            if (this.val$name.equals(r2.getName()) == false) goto L6;
            this.val$receiver.setType(Proto.ReceiverType.PROPERTY);
            this.val$receiver.setInitValue((ExecutableStatement) ParseTools.subCompileExpression(this.val$initializer, ProtoParser.access$000(this.this$0)));
            return true;
        L6:
            return false;
        }
    }

    public interface DeferredTypeResolve {
        String getName();

        boolean isWaitingFor(Proto r1);
    }

    static {
        deferred = new ThreadLocal();
    }

    public ProtoParser(char[] r2, int r3, int r4, String r5, ParserContext r6, int r7, ExecutionStack r8) {
        this.tk1 = null;
        this.tk2 = null;
        boolean r0 = false;
        this.interpreted = false;
        this.expr = r2;
        this.cursor = r3;
        this.endOffset = r4;
        this.protoName = r5;
        this.pCtx = r6;
        if ((r7 & 16) != 0) goto L5;
        r0 = true;
    L5:
        this.interpreted = r0;
        this.splitAccumulator = r8;
    }

    public static /* synthetic */ ParserContext access$000(ProtoParser r0) {
        return r0.pCtx;
    }

    private void calculateDecl() {
        if (this.tk2 != null) goto L20;
        this.type = Object.class;
        this.name = this.tk1;
    L18:
        this.tk1 = null;
        this.tk2 = null;
        return;
    L20:
    L7:
        e = move-exception;
        if (this.interpreted == false) goto L15;
        this.type = DeferredTypeResolve.class;
        this.deferredName = this.tk1;
        this.name = this.tk2;
        goto L18
    L15:
        Negation$$ExternalSyntheticBUOutline0.m("could not resolve class: " + this.tk1, this.expr, this.cursor, e);
        return;
    L5:
        if (this.pCtx.hasProtoImport(this.tk1) == false) goto L9;
        this.type = Proto.class;     // Catch: ClassNotFoundException -> L7
    L10:
        this.name = this.tk2;     // Catch: ClassNotFoundException -> L7
        goto L18
    L9:
        this.type = ParseTools.findClass(null, this.tk1, this.pCtx);     // Catch: ClassNotFoundException -> L7
        goto L10
    }

    public static void checkForPossibleUnresolvedViolations(char[] r3, int r4, ParserContext r5) {
        if (isUnresolvedWaiting() == false) goto L37;
        Object r52 = ((LinkedHashMap) r5.getParserConfiguration().getImports()).values().toArray()[r5.size() - 1];
        if ((r52 instanceof Proto) == false) goto L38;
        Proto r53 = (Proto) r52;
        int r0 = r53.getCursorEnd();
    L7:
        r4 = r4 - 1;
        if (r4 <= r0) goto L12;
        if (ParseTools.isWhitespace(r3[r4]) == true) goto L7;
    L12:
        if (r4 <= r0) goto L16;
        if (ParseTools.isIdentifierPart(r3[r4]) == false) goto L16;
        r4 = r4 - 1;
    L16:
        if (r4 <= r0) goto L22;
        if (ParseTools.isWhitespace(r3[r4]) == true) goto L21;
        if (r3[r4] != ';') goto L22;
    L21:
        r4 = r4 - 1;
    L22:
        if (r4 == r0) goto L39;
        Sign$$ExternalSyntheticBUOutline0.m("unresolved reference (possible illegal forward-reference?): " + getNextUnresolvedWaiting(), r3, r53.getCursorStart());
        return;
    L39:
        return;
    L38:
        return;
    }

    private void enqueueReceiverForLateResolution(String r3, Proto.Receiver r4, String r5) {
        Queue<DeferredTypeResolve> r0 = deferred.get();
        if (r0 != null) goto L5;
        ThreadLocal<Queue<DeferredTypeResolve>> r02 = deferred;
        LinkedList r1 = new LinkedList();
        r02.set(r1);
        r0 = r1;
    L5:
        r0.add(new AnonymousClass1(this, r3, r4, r5));
    }

    public static String getNextUnresolvedWaiting() {
        if (deferred.get() != null) goto L5;
        return null;
    L5:
        if (deferred.get().isEmpty() == false) goto L7;
        return null;
    L7:
        return deferred.get().poll().getName();
    }

    public static boolean isUnresolvedWaiting() {
        if (deferred.get() != null) goto L5;
        return false;
    L5:
        if (deferred.get().isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static void notifyForLateResolution(Proto r5) {
        if (deferred.get() == null) goto L14;
        Queue<DeferredTypeResolve> r0 = deferred.get();
        HashSet r1 = new HashSet();
        Iterator<DeferredTypeResolve> r2 = r0.iterator();
    L6:
        if (r2.hasNext() == false) goto L10;
        DeferredTypeResolve r3 = r2.next();
        if (r3.isWaitingFor(r5) == false) goto L6;
        r1.add(r3);
        goto L6
    L10:
        Iterator r52 = r1.iterator();
    L12:
        if (r52.hasNext() == false) goto L21;
        r0.remove((DeferredTypeResolve) r52.next());
        goto L12
    L21:
        return;
    }

    public int getCursor() {
        return this.cursor;
    }

    public Proto parse() {
        Proto r0 = new Proto(this.protoName, this.pCtx);
    L3:
        int r1 = this.cursor;
        if (r1 >= this.endOffset) goto L75;
        int r12 = ParseTools.skipWhitespace(this.expr, r1);
        this.cursor = r12;
        if (this.tk2 != null) goto L31;
    L7:
        int r2 = this.cursor;
        if (r2 >= this.endOffset) goto L12;
        if (ParseTools.isIdentifierPart(this.expr[r2]) == false) goto L12;
        this.cursor++;
    L12:
        int r22 = this.cursor;
        if (r22 <= r12) goto L30;
        String r4 = new String(this.expr, r12, r22 - r12);
        this.tk1 = r4;
        if ("def".equals(r4) == true) goto L18;
        if ("function".equals(this.tk1) == false) goto L30;
    L18:
        int r13 = this.cursor + 1;
        this.cursor = r13;
        int r14 = ParseTools.skipWhitespace(this.expr, r13);
        this.cursor = r14;
    L19:
        int r23 = this.cursor;
        if (r23 >= this.endOffset) goto L24;
        if (ParseTools.isIdentifierPart(this.expr[r23]) == false) goto L24;
        this.cursor++;
    L24:
        int r24 = this.cursor;
        char[] r42 = this.expr;
        if (r14 == r24) goto L27;
        FunctionParser r5 = new FunctionParser(new String(r42, r14, r24 - r14), this.cursor, this.endOffset, this.expr, 0, this.pCtx, null);
        r0.declareReceiver(r5.getName(), r5.parse());
        this.cursor = r5.getCursor() + 1;
        this.tk1 = null;
        goto L3
    L27:
        Sign$$ExternalSyntheticBUOutline0.m("attempt to declare an anonymous function as a prototype member", r42, r14);
        return null;
    L30:
        this.cursor = ParseTools.skipWhitespace(this.expr, this.cursor);
    L31:
        int r25 = this.cursor;
        if (r25 > this.endOffset) goto L74;
        char[] r15 = this.expr;
        char r43 = r15[r25];
        if (r43 != ';') goto L36;
        this.cursor = r25 + 1;
        calculateDecl();
        if (this.interpreted == false) goto L73;
        if (this.type != DeferredTypeResolve.class) goto L73;
        enqueueReceiverForLateResolution(this.deferredName, r0.declareReceiver(this.name, Proto.ReceiverType.DEFERRED, null), null);
    L73:
        r0.declareReceiver(this.name, this.type, null);
        goto L3
    L36:
        if (r43 != '=') goto L37;
        int r26 = r25 + 1;
        this.cursor = r26;
        int r16 = ParseTools.skipWhitespace(r15, r26);
        this.cursor = r16;
    L46:
        int r27 = this.cursor;
        int r44 = this.endOffset;
        if (r27 >= r44) goto L62;
        char[] r7 = this.expr;
        char r8 = r7[r27];
        if (r8 == '\"') goto L60;
        if (r8 == ';') goto L62;
        if (r8 == '[') goto L60;
        if (r8 == '{') goto L60;
        if (r8 == '\'') goto L60;
        if (r8 == '(') goto L60;
    L61:
        this.cursor++;
    L60:
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r7, r27, r44, r8, this.pCtx);
    L62:
        calculateDecl();
        char[] r45 = this.expr;
        int r6 = this.cursor;
        this.cursor = r6 + 1;
        String r28 = new String(r45, r16, r6 - r16);
        if (this.interpreted == false) goto L67;
        if (this.type != DeferredTypeResolve.class) goto L67;
        enqueueReceiverForLateResolution(this.deferredName, r0.declareReceiver(this.name, Proto.ReceiverType.DEFERRED, null), r28);
    L67:
        r0.declareReceiver(this.name, this.type, (ExecutableStatement) ParseTools.subCompileExpression(r28, this.pCtx));
    L37:
        int r17 = this.cursor;
        if (r17 >= this.endOffset) goto L42;
        if (ParseTools.isIdentifierPart(this.expr[r17]) == false) goto L42;
        this.cursor++;
    L42:
        int r18 = this.cursor;
        if (r18 <= r25) goto L3;
        this.tk2 = new String(this.expr, r25, r18 - r25);
        goto L3
    L74:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement in proto declaration: " + this.protoName, this.expr, r12);
        return null;
    L75:
        int r19 = r1 + 1;
        this.cursor = r19;
        if (this.splitAccumulator != null) goto L78;
    L80:
        return r0;
    L78:
        if (ParseTools.isStatementNotManuallyTerminated(this.expr, r19) == false) goto L80;
        this.splitAccumulator.add(new EndOfStatement(this.pCtx));
        goto L80
    }
}
