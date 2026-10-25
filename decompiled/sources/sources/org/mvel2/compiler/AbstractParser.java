package org.mvel2.compiler;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.mvel2.CompileException;
import org.mvel2.DataConversion;
import org.mvel2.ErrorDetail;
import org.mvel2.Operator;
import org.mvel2.ParserContext;
import org.mvel2.ast.ASTNode;
import org.mvel2.ast.AssertNode;
import org.mvel2.ast.AssignmentNode;
import org.mvel2.ast.BooleanNode;
import org.mvel2.ast.DeclProtoVarNode;
import org.mvel2.ast.DeclTypedVarNode;
import org.mvel2.ast.DeepAssignmentNode;
import org.mvel2.ast.DeepOperativeAssignmentNode;
import org.mvel2.ast.DoNode;
import org.mvel2.ast.DoUntilNode;
import org.mvel2.ast.EndOfStatement;
import org.mvel2.ast.Fold;
import org.mvel2.ast.ForEachNode;
import org.mvel2.ast.ForNode;
import org.mvel2.ast.Function;
import org.mvel2.ast.IfNode;
import org.mvel2.ast.ImportNode;
import org.mvel2.ast.IndexedAssignmentNode;
import org.mvel2.ast.IndexedDeclTypedVarNode;
import org.mvel2.ast.IndexedOperativeAssign;
import org.mvel2.ast.IndexedPostFixDecNode;
import org.mvel2.ast.IndexedPostFixIncNode;
import org.mvel2.ast.IndexedPreFixDecNode;
import org.mvel2.ast.IndexedPreFixIncNode;
import org.mvel2.ast.InlineCollectionNode;
import org.mvel2.ast.InterceptorWrapper;
import org.mvel2.ast.Invert;
import org.mvel2.ast.IsDef;
import org.mvel2.ast.LineLabel;
import org.mvel2.ast.LiteralDeepPropertyNode;
import org.mvel2.ast.LiteralNode;
import org.mvel2.ast.Negation;
import org.mvel2.ast.Negation$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.NewObjectNode;
import org.mvel2.ast.NewObjectPrototype;
import org.mvel2.ast.NewPrototypeNode;
import org.mvel2.ast.OperativeAssign;
import org.mvel2.ast.OperatorNode;
import org.mvel2.ast.PostFixDecNode;
import org.mvel2.ast.PostFixIncNode;
import org.mvel2.ast.PreFixDecNode;
import org.mvel2.ast.PreFixIncNode;
import org.mvel2.ast.Proto;
import org.mvel2.ast.ProtoVarNode;
import org.mvel2.ast.RedundantCodeException;
import org.mvel2.ast.RegExMatch;
import org.mvel2.ast.ReturnNode;
import org.mvel2.ast.Sign;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Stacklang;
import org.mvel2.ast.StaticImportNode;
import org.mvel2.ast.Substatement;
import org.mvel2.ast.ThisWithNode;
import org.mvel2.ast.TypeCast;
import org.mvel2.ast.TypeDescriptor;
import org.mvel2.ast.TypedVarNode;
import org.mvel2.ast.Union;
import org.mvel2.ast.UntilNode;
import org.mvel2.ast.WhileNode;
import org.mvel2.ast.WithNode;
import org.mvel2.integration.Interceptor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ArrayTools;
import org.mvel2.util.ErrorUtil;
import org.mvel2.util.ExecutionStack;
import org.mvel2.util.FunctionParser;
import org.mvel2.util.ParseTools;
import org.mvel2.util.PropertyTools;
import org.mvel2.util.ProtoParser;
import org.mvel2.util.Soundex;

/* loaded from: classes5.dex */
public class AbstractParser implements Parser, Serializable {
    public static HashMap<String, Object> CLASS_LITERALS = null;
    private static final WeakHashMap<String, char[]> EX_PRECACHE = null;
    protected static final int GET = 2;
    protected static final int GET_OR_CREATE = 3;
    public static final int LEVEL_0_PROPERTY_ONLY = 0;
    public static final int LEVEL_1_BASIC_LANG = 1;
    public static final int LEVEL_2_MULTI_STATEMENT = 2;
    public static final int LEVEL_3_ITERATION = 3;
    public static final int LEVEL_4_ASSIGNMENT = 4;
    public static final int LEVEL_5_CONTROL_FLOW = 5;
    public static HashMap<String, Object> LITERALS = null;
    public static HashMap<String, Integer> OPERATORS = null;
    protected static final int OP_CONTINUE = 1;
    protected static final int OP_NOT_LITERAL = -3;
    protected static final int OP_OVERFLOW = -2;
    protected static final int OP_RESET_FRAME = 0;
    protected static final int OP_TERMINATE = -1;
    protected static final int REMOVE = 1;
    protected static final int SET = 0;
    protected boolean compileMode;
    protected Object ctx;
    protected int cursor;
    protected ExecutionStack dStack;
    protected boolean debugSymbols;
    protected int end;
    protected char[] expr;
    protected int fields;
    protected boolean greedy;
    protected int lastLineStart;
    protected ASTNode lastNode;
    protected boolean lastWasComment;
    protected boolean lastWasIdentifier;
    protected boolean lastWasLineLabel;
    protected int length;
    protected int line;
    protected int literalOnly;
    protected ParserContext pCtx;
    protected ExecutionStack splitAccumulator;
    protected int st;
    protected int start;
    protected ExecutionStack stk;
    protected VariableResolverFactory variableFactory;

    static {
        EX_PRECACHE = new WeakHashMap(15);
        setupParser();
    }

    public AbstractParser() {
        this.greedy = true;
        this.lastWasIdentifier = false;
        this.lastWasLineLabel = false;
        this.lastWasComment = false;
        this.compileMode = false;
        this.literalOnly = -1;
        this.lastLineStart = 0;
        this.line = 0;
        this.splitAccumulator = new ExecutionStack();
        this.debugSymbols = false;
        this.pCtx = new ParserContext();
    }

    public AbstractParser(ParserContext r3) {
        this.greedy = true;
        this.lastWasIdentifier = false;
        this.lastWasLineLabel = false;
        this.lastWasComment = false;
        this.compileMode = false;
        this.literalOnly = -1;
        this.lastLineStart = 0;
        this.line = 0;
        this.splitAccumulator = new ExecutionStack();
        this.debugSymbols = false;
        if (r3 != null) goto L6;
        r3 = new ParserContext();
    L6:
        this.pCtx = r3;
    }

    private ASTNode _captureBlock(ASTNode r12, char[] r13, boolean r14, int r15) {
        skipWhitespace();
        if (r15 == 48) goto L68;
        if (r15 != 100) goto L7;
        int r0 = this.cursor;
        captureToNextTokenJunction();
        int r2 = this.cursor;
        if (r2 == this.end) goto L65;
        String r02 = ParseTools.createStringTrimmed(r13, r0, r2 - r0);
        if (ParseTools.isReservedWord(r02) == false) goto L60;
    L63:
        Sign$$ExternalSyntheticBUOutline0.m("illegal function name or use of reserved word", r13, this.cursor);
        return null;
    L60:
        if (ParseTools.isNotValidNameorLabel(r02) == true) goto L63;
        int r22 = this.cursor;
        FunctionParser r03 = new FunctionParser(r02, r22, this.end - r22, r13, this.fields, this.pCtx, this.splitAccumulator);
        Function r1 = r03.parse();
        this.cursor = r03.getCursor();
        this.lastNode = r1;
        return r1;
    L65:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", r13, r0);
        return null;
    L7:
        if (r15 != 101) goto L9;
        int r04 = nextNonBlank();
        this.cursor = r04;
        if (r13[r04] != '{') goto L53;
        int r23 = r04 + 1;
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r13, r23, this.end, '{', this.pCtx);
        Stacklang r05 = new Stacklang(r13, r23, this.cursor - r23, this.fields, this.pCtx);
        this.cursor++;
        this.lastNode = r05;
        return r05;
    L53:
        Sign$$ExternalSyntheticBUOutline0.m("expected '{' but found: " + r13[this.cursor], r13, this.cursor);
        return null;
    L9:
        if (r14 == false) goto L15;
        int r4 = this.cursor;
        if (r13[r4] != '(') goto L13;
        int r8 = ParseTools.balancedCaptureWithLineAccounting(r13, r4, this.end, '(', this.pCtx);
        int r42 = r4 + 1;
        this.cursor = r8 + 1;
    L16:
        skipWhitespace();
        int r9 = this.cursor;
        int r10 = this.end;
        if (r9 < r10) goto L19;
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", r13, r10);
        return null;
    L19:
        if (r13[r9] != '{') goto L21;
        int r6 = ParseTools.balancedCaptureWithLineAccounting(r13, r9, r10, '{', this.pCtx);
        this.cursor = r6;
    L23:
        if (r15 != 2048) goto L34;
        IfNode r7 = (IfNode) r12;
        if (r12 == null) goto L32;
        if (r14 == true) goto L30;
        int r24 = trimRight(r9 + 1);
        this.st = r24;
        return r7.setElseBlock(r13, r24, trimLeft(r6) - this.st, this.pCtx);
    L30:
        return r7.setElseIf((IfNode) createBlockToken(r42, r8, trimRight(r9 + 1), trimLeft(r6), r15));
    L32:
        return createBlockToken(r42, r8, r9 + 1, r6, r15);
    L34:
        if (r15 != 65536) goto L46;
        this.cursor++;
        skipWhitespace();
        this.st = this.cursor;
        captureToNextTokenJunction();
        int r72 = this.st;
        String r43 = new String(r13, r72, this.cursor - r72);
        if ("while".equals(r43) == false) goto L40;
        skipWhitespace();
        int r3 = this.cursor;
        int r25 = ParseTools.balancedCaptureWithLineAccounting(r13, r3, this.end, '(', this.pCtx);
        this.cursor = r25;
        return createBlockToken(r3 + 1, r25, trimRight(r9 + 1), trimLeft(r6), r15);
    L40:
        if ("until".equals(r43) == false) goto L43;
        skipWhitespace();
        int r32 = this.cursor;
        int r26 = ParseTools.balancedCaptureWithLineAccounting(r13, r32, this.end, '(', this.pCtx);
        this.cursor = r26;
        return createBlockToken(r32 + 1, r26, trimRight(r9 + 1), trimLeft(r6), 131072);
    L43:
        Sign$$ExternalSyntheticBUOutline0.m("expected 'while' or 'until' but encountered: ".concat(r43), r13, this.cursor);
        return null;
    L46:
        return createBlockToken(r42, r8, trimRight(r9 + 1), trimLeft(r6), r15);
    L21:
        r9 = r9 - 1;
        captureToEOSorEOL();
        r6 = this.cursor + 1;
        goto L23
    L13:
        Sign$$ExternalSyntheticBUOutline0.m("expected '(' but encountered: " + r13[this.cursor], r13, this.cursor);
        return null;
    L15:
        r8 = 0;
        r42 = 0;
        goto L16
    L68:
        if (ProtoParser.isUnresolvedWaiting() == false) goto L70;
        ProtoParser.checkForPossibleUnresolvedViolations(r13, this.cursor, this.pCtx);
    L70:
        int r06 = this.cursor;
        captureToNextTokenJunction();
        String r07 = ParseTools.createStringTrimmed(r13, r06, this.cursor - r06);
        if (ParseTools.isReservedWord(r07) == false) goto L73;
    L80:
        Sign$$ExternalSyntheticBUOutline0.m("illegal prototype name or use of reserved word", r13, this.cursor);
        return null;
    L73:
        if (ParseTools.isNotValidNameorLabel(r07) == true) goto L80;
        int r27 = nextNonBlank();
        this.cursor = r27;
        if (r13[r27] != '{') goto L78;
        int r28 = r27 + 1;
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r13, r28, this.end, '{', this.pCtx);
        ProtoParser r08 = new ProtoParser(r13, r28, this.cursor, r07, this.pCtx, this.fields, this.splitAccumulator);
        Proto r16 = r08.parse();
        this.pCtx.addImport(r16);
        r16.setCursorPosition(r28, this.cursor);
        this.cursor = r08.getCursor();
        ProtoParser.notifyForLateResolution(r16);
        this.lastNode = r16;
        return r16;
    L78:
        Sign$$ExternalSyntheticBUOutline0.m("expected '{' but found: " + r13[this.cursor], r13, this.cursor);
        return null;
    }

    private static int asInt(Object r0) {
        return ((Integer) r0).intValue();
    }

    private ASTNode captureCodeBlock(int r9) {
        ASTNode r3 = null;
        if (r9 != 2048) goto L5;
        boolean r4 = true;
        ASTNode r0 = null;
    L11:
        if (r3 == null) goto L22;
        captureToNextTokenJunction();
        skipWhitespace();
        char[] r42 = this.expr;
        int r5 = this.cursor;
        char r6 = r42[r5];
        if (r6 != '{') goto L15;
    L21:
        r4 = false;
        goto L22
    L15:
        if (r6 != 'i') goto L21;
        int r52 = r5 + 1;
        this.cursor = r52;
        if (r42[r52] != 'f') goto L21;
        int r53 = incNextNonBlank();
        this.cursor = r53;
        if (r42[r53] != '(') goto L21;
        r4 = true;
    L22:
        r3 = _captureBlock(r3, this.expr, r4, r9);
        if (((IfNode) r3).getElseBlock() != null) goto L24;
        if (r0 != null) goto L28;
        r0 = r3;
    L28:
        int r54 = this.cursor;
        if (r54 == this.end) goto L34;
        if (this.expr[r54] == ';') goto L34;
        this.cursor = r54 + 1;
    L34:
        if (ifThenElseBlockContinues() == true) goto L11;
        return r0;
    L24:
        this.cursor++;
        return r0;
    L5:
        if (r9 == 65536) goto L8;
        captureToNextTokenJunction();
        skipWhitespace();
        return _captureBlock(null, this.expr, true, r9);
    L8:
        skipWhitespace();
        return _captureBlock(null, this.expr, false, r9);
    }

    private ASTNode createBlockToken(int r15, int r16, int r17, int r18, int r19) {
        this.lastWasIdentifier = false;
        this.cursor++;
        if (isStatementNotManuallyTerminated() == false) goto L5;
        this.splitAccumulator.add(new EndOfStatement(this.pCtx));
    L5:
        int r9 = r16 - r15;
        int r3 = r18 - r17;
        if (r3 >= 0) goto L8;
        int r11 = 0;
    L10:
        if (r19 == 2048) goto L46;
        if (r19 == 4096) goto L44;
        if (r19 == 16384) goto L42;
        if (r19 == 32768) goto L40;
        if (r19 == 65536) goto L38;
        if (r19 == 131072) goto L36;
        if (r19 != 262144) goto L24;
        int r1 = r15;
    L26:
        if (r1 >= r16) goto L44;
        char[] r7 = this.expr;
        char r2 = r7[r1];
        if (r2 == ';') goto L30;
        if (r2 == ':') goto L44;
        r1 = r1 + 1;
        goto L26
    L30:
        return new ForNode(r7, r15, r9, r17, r11, this.fields, this.pCtx);
    L24:
        return new WithNode(this.expr, r15, r9, r17, r11, this.fields, this.pCtx);
    L36:
        return new DoUntilNode(this.expr, r15, r9, r17, r11, this.pCtx);
    L38:
        return new DoNode(this.expr, r15, r9, r17, r11, this.fields, this.pCtx);
    L40:
        return new WhileNode(this.expr, r15, r9, r17, r11, this.fields, this.pCtx);
    L42:
        return new UntilNode(this.expr, r15, r9, r17, r11, this.fields, this.pCtx);
    L44:
        return new ForEachNode(this.expr, r15, r9, r17, r11, this.fields, this.pCtx);
    L46:
        return new IfNode(this.expr, r15, r9, r17, r11, this.fields, this.pCtx);
    L8:
        r11 = r3;
        goto L10
    }

    private ASTNode createOperator(char[] r4, int r5, int r6) {
        this.lastWasIdentifier = false;
        OperatorNode r0 = new OperatorNode(OPERATORS.get(new String(r4, r5, r6 - r5)), r4, r5, this.pCtx);
        this.lastNode = r0;
        return r0;
    }

    private ASTNode createPropertyToken(int r12, int r13) {
        if (ParseTools.isPropertyOnly(this.expr, r12, r13) == false) goto L31;
        ParserContext r0 = this.pCtx;
        if (r0 != null) goto L7;
    L18:
        HashMap<String, Object> r02 = LITERALS;
        String r2 = new String(this.expr, r12, r13 - r12);
        if (r02.containsKey(r2) == false) goto L23;
        this.lastWasIdentifier = true;
        LiteralNode r122 = new LiteralNode(LITERALS.get(r2), this.pCtx);
        this.lastNode = r122;
        return r122;
    L23:
        if (OPERATORS.containsKey(r2) == false) goto L27;
        this.lastWasIdentifier = false;
        OperatorNode r132 = new OperatorNode(OPERATORS.get(r2), this.expr, r12, this.pCtx);
        this.lastNode = r132;
        return r132;
    L27:
        if (this.lastWasIdentifier == false) goto L31;
        return procTypedNode(true);
    L7:
        if (r0.hasImports() == false) goto L18;
        int r03 = ArrayTools.findFirst('.', r12, r13 - r12, this.expr);
        if (r03 == (-1)) goto L14;
        String r22 = new String(this.expr, r12, r03 - r12);
        if (this.pCtx.hasImport(r22) == false) goto L18;
        this.lastWasIdentifier = true;
        LiteralDeepPropertyNode r4 = new LiteralDeepPropertyNode(this.expr, r03 + 1, (r13 - r03) - 1, this.fields, this.pCtx.getImport(r22), this.pCtx);
        this.lastNode = r4;
        return r4;
    L14:
        ParserContext r04 = this.pCtx;
        String r23 = new String(this.expr, r12, this.cursor - r12);
        if (r04.hasImport(r23) == false) goto L18;
        this.lastWasIdentifier = true;
        LiteralNode r123 = new LiteralNode(this.pCtx.getStaticOrClassImport(r23), this.pCtx);
        this.lastNode = r123;
        return r123;
    L31:
        if (this.pCtx != null) goto L33;
    L41:
        this.lastWasIdentifier = true;
        ASTNode r05 = new ASTNode(this.expr, trimRight(r12), trimLeft(r13) - r12, this.fields, this.pCtx);
        this.lastNode = r05;
        return r05;
    L33:
        if (ParseTools.isArrayType(this.expr, r12, r13) == false) goto L41;
        if (this.pCtx.hasImport(new String(this.expr, r12, (this.cursor - r12) - 2)) == false) goto L41;
        this.lastWasIdentifier = true;
        TypeDescriptor r133 = new TypeDescriptor(this.expr, r12, this.cursor - r12, this.fields);
        LiteralNode r06 = new LiteralNode(r133.getClassReference(this.pCtx), this.pCtx);     // Catch: ClassNotFoundException -> L39
        this.lastNode = r06;     // Catch: ClassNotFoundException -> L39
        return r06;
    L39:
        Sign$$ExternalSyntheticBUOutline0.m("could not resolve class: " + r133.getClassName(), this.expr, r12);
        return null;
    }

    private void dreduce() {
        this.stk.copy2(this.dStack);
        this.stk.op();
    }

    public static boolean isArithmeticOperator(int r1) {
        if (r1 != (-1)) goto L5;
        return false;
    L5:
        if (r1 >= 6) goto L10;
        return true;
    L10:
        return false;
    }

    public static HashMap<String, Integer> loadLanguageFeaturesByLevel(int r5) {
        HashMap<String, Integer> r3 = new HashMap();
        switch(r5) {
            case 0: goto L11;
            case 1: goto L10;
            case 2: goto L9;
            case 3: goto L8;
            case 4: goto L7;
            case 5: goto L6;
            case 6: goto L5;
            default: goto L4;
        };
    L4:
        return r3;
    L5:
        r3.put("proto", 48);
    L6:
        r3.put("if", 39);
        r3.put("else", 40);
        r3.put("?", 29);
        r3.put(PluginsConstants.Settings.TYPE_SWITCH, 44);
        r3.put("function", 100);
        r3.put("def", 100);
        r3.put("stacklang", 101);
    L7:
        r3.put("=", 31);
        r3.put("var", 98);
        r3.put("+=", 52);
        r3.put("-=", 53);
        r3.put("/=", 55);
        r3.put("%=", 56);
    L8:
        r3.put("foreach", 38);
        r3.put("while", 41);
        r3.put("until", 42);
        r3.put("for", 43);
        r3.put("do", 45);
    L9:
        r3.put("return", 99);
        r3.put(";", 37);
    L10:
        r3.put("+", 0);
        r3.put("-", 1);
        r3.put("*", 2);
        r3.put("**", 5);
        r3.put("/", 3);
        r3.put("%", 4);
        r3.put("==", 18);
        r3.put("!=", 19);
        r3.put(">", 15);
        r3.put(">=", 17);
        r3.put("<", 14);
        r3.put("<=", 16);
        r3.put("&&", 21);
        r3.put("and", 21);
        r3.put("||", 22);
        r3.put(PluginsConstants.HookFilterTypes.OR, 23);
        r3.put("~=", 24);
        r3.put("instanceof", 25);
        r3.put("is", 25);
        r3.put("contains", 26);
        r3.put("soundslike", 27);
        r3.put("strsim", 28);
        r3.put("convertable_to", 36);
        r3.put("isdef", 47);
        r3.put("#", 20);
        r3.put("&", 6);
        r3.put("|", 7);
        r3.put("^", 8);
        r3.put("<<", 10);
        r3.put("<<<", 12);
        r3.put(">>", 9);
        r3.put(">>>", 11);
        r3.put("new", 34);
        r3.put("in", 35);
        r3.put("with", 46);
        r3.put("assert", 97);
        r3.put("import", 96);
        r3.put("import_static", 95);
        r3.put("++", 50);
        r3.put("--", 51);
    L11:
        r3.put(":", 30);
        return r3;
    }

    private ASTNode procTypedNode(boolean r14) {
    L3:
        if ((this.lastNode.getLiteralValue() instanceof String) == false) goto L7;
        char[] r0 = ((String) this.lastNode.getLiteralValue()).toCharArray();
        this.lastNode.setLiteralValue(TypeDescriptor.getClassReference(this.pCtx, new TypeDescriptor(r0, 0, r0.length, 0)));     // Catch: Exception -> L45
        this.lastNode.discard();     // Catch: Exception -> L45
    L7:
        if (this.lastNode.isLiteral() == false) goto L15;
        if ((this.lastNode.getLiteralValue() instanceof Class) == false) goto L15;
        this.lastNode.discard();
        captureToEOS();
        if (r14 == false) goto L13;
        ExecutionStack r02 = this.splitAccumulator;
        char[] r3 = this.expr;
        int r4 = this.st;
        String r2 = new String(r3, r4, this.cursor - r4);
        char[] r32 = this.expr;
        int r42 = this.st;
        r02.add(new DeclTypedVarNode(r2, r32, r42, this.cursor - r42, (Class) this.lastNode.getLiteralValue(), this.fields | 128, this.pCtx));
    L34:
        skipWhitespace();
        int r03 = this.cursor;
        if (r03 >= this.end) goto L40;
        if (this.expr[r03] != ',') goto L40;
        int r04 = r03 + 1;
        this.cursor = r04;
        this.st = r04;
        this.splitAccumulator.add(new EndOfStatement(this.pCtx));
    L40:
        return (ASTNode) this.splitAccumulator.pop();
    L13:
        captureToEOS();
        this.splitAccumulator.add(new TypedVarNode(this.expr, this.st, (this.cursor - r3) - 1, this.fields | 128, (Class) this.lastNode.getLiteralValue(), this.pCtx));
    L15:
        if ((this.lastNode instanceof Proto) == false) goto L21;
        captureToEOS();
        ExecutionStack r05 = this.splitAccumulator;
        if (r14 == false) goto L19;
        char[] r33 = this.expr;
        int r43 = this.st;
        r05.add(new DeclProtoVarNode(new String(r33, r43, this.cursor - r43), (Proto) this.lastNode, this.fields | 128, this.pCtx));
        goto L34
    L19:
        char[] r7 = this.expr;
        int r8 = this.st;
        r05.add(new ProtoVarNode(r7, r8, this.cursor - r8, this.fields | 128, (Proto) this.lastNode, this.pCtx));
        goto L34
    L21:
        if ((this.fields & 16) != 0) goto L43;
        if ((this.stk.peek() instanceof Class) == false) goto L29;
        captureToEOS();
        ExecutionStack r06 = this.splitAccumulator;
        if (r14 == false) goto L27;
        char[] r34 = this.expr;
        int r44 = this.st;
        String r22 = new String(r34, r44, this.cursor - r44);
        char[] r35 = this.expr;
        int r45 = this.st;
        r06.add(new DeclTypedVarNode(r22, r35, r45, this.cursor - r45, (Class) this.stk.pop(), this.fields | 128, this.pCtx));
        goto L34
    L27:
        char[] r36 = this.expr;
        int r46 = this.st;
        r06.add(new TypedVarNode(r36, r46, this.cursor - r46, this.fields | 128, (Class) this.stk.pop(), this.pCtx));
        goto L34
    L29:
        if ((this.stk.peek() instanceof Proto) == false) goto L41;
        captureToEOS();
        ExecutionStack r07 = this.splitAccumulator;
        if (r14 == false) goto L33;
        char[] r37 = this.expr;
        int r47 = this.st;
        r07.add(new DeclProtoVarNode(new String(r37, r47, this.cursor - r47), (Proto) this.stk.pop(), this.fields | 128, this.pCtx));
        goto L34
    L33:
        char[] r72 = this.expr;
        int r82 = this.st;
        r07.add(new ProtoVarNode(r72, r82, this.cursor - r82, this.fields | 128, (Proto) this.stk.pop(), this.pCtx));
        goto L34
    L41:
        Sign$$ExternalSyntheticBUOutline0.m("unknown class or illegal statement: " + this.lastNode.getLiteralValue(), this.expr, this.cursor);
        return null;
    L43:
        Sign$$ExternalSyntheticBUOutline0.m("unknown class or illegal statement: " + this.lastNode.getLiteralValue(), this.expr, this.cursor);
        return null;
    }

    private void reduce(int r1, int r2, int r3) {
        switch(r2) {
            case 6: goto L18;
            case 7: goto L16;
            case 8: goto L14;
            case 9: goto L12;
            case 10: goto L10;
            case 11: goto L8;
            case 12: goto L4;
            default: goto L3;
        };
    L3:
        return;
    L4:
        if (r1 >= 0) goto L6;
        r1 = r1 * (-1);
    L6:
        this.stk.push(Integer.valueOf(r1 << r3));
        return;
    L8:
        this.stk.push(Integer.valueOf(r1 >>> r3));
        return;
    L10:
        this.stk.push(Integer.valueOf(r1 << r3));
        return;
    L12:
        this.stk.push(Integer.valueOf(r1 >> r3));
        return;
    L14:
        this.stk.push(Integer.valueOf(r1 ^ r3));
        return;
    L16:
        this.stk.push(Integer.valueOf(r1 | r3));
        return;
    L18:
        this.stk.push(Integer.valueOf(r1 & r3));
    }

    private void reduce(int r1, int r2, long r3) {
        switch(r2) {
            case 6: goto L18;
            case 7: goto L16;
            case 8: goto L14;
            case 9: goto L12;
            case 10: goto L10;
            case 11: goto L8;
            case 12: goto L4;
            default: goto L3;
        };
    L3:
        return;
    L4:
        if (r1 >= 0) goto L6;
        r1 = r1 * (-1);
    L6:
        this.stk.push(Integer.valueOf(r1 << ((int) r3)));
        return;
    L8:
        this.stk.push(Integer.valueOf(r1 >>> ((int) r3)));
        return;
    L10:
        this.stk.push(Integer.valueOf(r1 << ((int) r3)));
        return;
    L12:
        this.stk.push(Integer.valueOf(r1 >> ((int) r3)));
        return;
    L14:
        this.stk.push(Long.valueOf(r1 ^ r3));
        return;
    L16:
        this.stk.push(Long.valueOf(r1 | r3));
        return;
    L18:
        this.stk.push(Long.valueOf(r1 & r3));
    }

    private void reduce(long r3, int r5, int r6) {
        switch(r5) {
            case 6: goto L19;
            case 7: goto L17;
            case 8: goto L15;
            case 9: goto L13;
            case 10: goto L11;
            case 11: goto L9;
            case 12: goto L5;
            default: goto L3;
        };
    L3:
        return;
    L9:
        this.stk.push(Long.valueOf(r3 >>> r6));
        return;
    L11:
        this.stk.push(Long.valueOf(r3 << r6));
        return;
    L13:
        this.stk.push(Long.valueOf(r3 >> r6));
        return;
    L15:
        this.stk.push(Long.valueOf(r3 ^ r6));
        return;
    L17:
        this.stk.push(Long.valueOf(r3 | r6));
        return;
    L19:
        this.stk.push(Long.valueOf(r3 & r6));
        return;
    L5:
        if (r3 >= 0) goto L7;
        r3 = r3 * (-1);
    L7:
        this.stk.push(Long.valueOf(r3 << r6));
    }

    private void reduce(long r3, int r5, long r6) {
        switch(r5) {
            case 6: goto L19;
            case 7: goto L17;
            case 8: goto L15;
            case 9: goto L13;
            case 10: goto L11;
            case 11: goto L9;
            case 12: goto L5;
            default: goto L3;
        };
    L3:
        return;
    L9:
        this.stk.push(Long.valueOf(r3 >>> ((int) r6)));
        return;
    L11:
        this.stk.push(Long.valueOf(r3 << ((int) r6)));
        return;
    L13:
        this.stk.push(Long.valueOf(r3 >> ((int) r6)));
        return;
    L15:
        this.stk.push(Long.valueOf(r3 ^ r6));
        return;
    L17:
        this.stk.push(Long.valueOf(r3 | r6));
        return;
    L19:
        this.stk.push(Long.valueOf(r3 & r6));
        return;
    L5:
        if (r3 >= 0) goto L7;
        r3 = r3 * (-1);
    L7:
        this.stk.push(Long.valueOf(r3 << ((int) r6)));
    }

    private void reduceNumeric(int r9) {
        Object r0 = this.stk.peek2();
        Object r1 = this.stk.pop2();
        if ((r0 instanceof Integer) == false) goto L11;
        if ((r1 instanceof Integer) == false) goto L8;
        reduce(((Integer) r0).intValue(), r9, ((Integer) r1).intValue());
        return;
    L8:
        reduce(((Integer) r0).intValue(), r9, ((Long) r1).longValue());
        return;
    L11:
        if ((r1 instanceof Integer) == false) goto L14;
        reduce(((Long) r0).longValue(), r9, ((Integer) r1).intValue());
        return;
    L14:
        reduce(((Long) r0).longValue(), r9, ((Long) r1).longValue());
    }

    public static void setLanguageLevel(int r1) {
        OPERATORS.clear();
        OPERATORS.putAll(loadLanguageFeaturesByLevel(r1));
    }

    public static void setupParser() {
        HashMap<String, Object> r0 = LITERALS;
        if (r0 != null) goto L5;
    L8:
        LITERALS = new HashMap();
        CLASS_LITERALS = new HashMap();
        OPERATORS = new HashMap();
        CLASS_LITERALS.put("System", System.class);
        CLASS_LITERALS.put("String", String.class);
        CLASS_LITERALS.put("CharSequence", CharSequence.class);
        CLASS_LITERALS.put("Integer", Integer.class);
        CLASS_LITERALS.put("int", Integer.TYPE);
        CLASS_LITERALS.put("Long", Long.class);
        CLASS_LITERALS.put("long", Long.TYPE);
        CLASS_LITERALS.put("Boolean", Boolean.class);
        CLASS_LITERALS.put("boolean", Boolean.TYPE);
        CLASS_LITERALS.put("Short", Short.class);
        CLASS_LITERALS.put("short", Short.TYPE);
        CLASS_LITERALS.put("Character", Character.class);
        CLASS_LITERALS.put("char", Character.TYPE);
        CLASS_LITERALS.put("Double", Double.class);
        CLASS_LITERALS.put("double", Double.TYPE);
        CLASS_LITERALS.put("Float", Float.class);
        CLASS_LITERALS.put("float", Float.TYPE);
        CLASS_LITERALS.put("Byte", Byte.class);
        CLASS_LITERALS.put("byte", Byte.TYPE);
        CLASS_LITERALS.put("Math", Math.class);
        CLASS_LITERALS.put("Void", Void.class);
        CLASS_LITERALS.put("Object", Object.class);
        CLASS_LITERALS.put("Number", Number.class);
        CLASS_LITERALS.put("Class", Class.class);
        CLASS_LITERALS.put("ClassLoader", ClassLoader.class);
        CLASS_LITERALS.put("Runtime", Runtime.class);
        CLASS_LITERALS.put("Thread", Thread.class);
        CLASS_LITERALS.put("Exception", Exception.class);
        CLASS_LITERALS.put("Array", Array.class);
        CLASS_LITERALS.put("StringBuilder", StringBuilder.class);
        LITERALS.putAll(CLASS_LITERALS);
        LITERALS.put("true", Boolean.TRUE);
        LITERALS.put("false", Boolean.FALSE);
        LITERALS.put("null", null);
        LITERALS.put("nil", null);
        LITERALS.put("empty", BlankLiteral.INSTANCE);
        if (Boolean.getBoolean("mvel.future.lang.support") == false) goto L11;
        int r02 = 6;
    L12:
        setLanguageLevel(r02);
        return;
    L11:
        r02 = 5;
        goto L12
    L5:
        if (r0.isEmpty() == true) goto L8;
    }

    private char[] subArray(int r5, int r6) {
        int r0 = 0;
        if (r5 >= r6) goto L5;
        int r62 = r6 - r5;
        char[] r1 = new char[r62];
    L7:
        if (r0 == r62) goto L9;
        r1[r0] = this.expr[r0 + r5];
        r0 = r0 + 1;
        goto L7
    L9:
        return r1;
    L5:
        return new char[0];
    }

    public void addFatalError(String r5) {
        this.pCtx.addError(new ErrorDetail(this.expr, this.st, true, r5));
    }

    public void addFatalError(String r4, int r5) {
        this.pCtx.addError(new ErrorDetail(this.expr, r5, true, r4));
    }

    public int arithmeticFunctionReduction(int r10) {
        ASTNode r0 = nextToken();
        if (r0 == null) goto L101;
        Integer r2 = r0.getOperator();
        int r3 = r2.intValue();
        if (isArithmeticOperator(r3) == false) goto L96;
        int[] r4 = Operator.PTABLE;
        if (r4[r3] <= r4[r10]) goto L96;
        this.stk.xswap();
        ASTNode r102 = nextToken();
        if (this.compileMode == true) goto L11;
    L14:
        ExecutionStack r02 = this.dStack;
        Object r5 = this.ctx;
        r02.push(r2, r102.getReducedValue(r5, r5, this.variableFactory));
        int r103 = r3;
    L15:
        ASTNode r03 = nextToken();
        if (r03 == null) goto L35;
        Integer r104 = r03.getOperator();
        int r6 = r104.intValue();
        if (r6 == (-1)) goto L33;
        if (r6 == 37) goto L33;
        int[] r7 = Operator.PTABLE;
        if (r7[r6] <= r7[r3]) goto L33;
        if (this.dStack.isReduceable() == false) goto L25;
        this.stk.copyx2(this.dStack);
    L25:
        ASTNode r04 = nextToken();
        if (this.compileMode == true) goto L28;
    L31:
        ExecutionStack r22 = this.dStack;
        Object r32 = this.ctx;
        r22.push(r104, r04.getReducedValue(r32, r32, this.variableFactory));
        r103 = r6;
    L32:
        r3 = r103;
        goto L15
    L28:
        if (r04.isLiteral() == true) goto L31;
        this.splitAccumulator.push(r04, new OperatorNode(r104, this.expr, this.st, this.pCtx));
        return -2;
    L33:
        r103 = r6;
    L35:
        if (r03 == null) goto L90;
        if (r103 == (-1)) goto L90;
        if (r103 == 37) goto L90;
        int[] r23 = Operator.PTABLE;
        if (r23[r103] == r23[r3]) goto L41;
    L54:
        if (this.dStack.size() <= 1) goto L56;
        dreduce();
        goto L54
    L56:
        Integer r05 = r03.getOperator();
        r3 = r05.intValue();
    L58:
        if (this.stk.size() == 1) goto L66;
        if ((this.stk.peek2() instanceof Integer) == false) goto L66;
        r103 = ((Integer) this.stk.peek2()).intValue();
        int[] r24 = Operator.PTABLE;
        if (r103 >= r24.length) goto L66;
        if (r24[r103] < r24[r3]) goto L66;
        this.stk.xswap_op();
    L66:
        ASTNode r25 = nextToken();
        if (r25 == null) goto L15;
        if (r3 == 21) goto L85;
        if (r3 == 22) goto L80;
        if (this.compileMode == true) goto L75;
    L78:
        ExecutionStack r52 = this.stk;
        Object r62 = this.ctx;
        r52.push(r05, r25.getReducedValue(r62, r62, this.variableFactory));
        goto L15
    L75:
        if (r25.isLiteral() == true) goto L78;
        this.stk.push(r05, r25);
        return -3;
    L80:
        if (this.stk.peekBoolean().booleanValue() == false) goto L82;
        return -1;
    L82:
        this.splitAccumulator.add(r25);
        return 22;
    L85:
        if (this.stk.peekBoolean().booleanValue() == true) goto L87;
        return -1;
    L87:
        this.splitAccumulator.add(r25);
        return 21;
    L41:
        if (this.dStack.isEmpty() == true) goto L44;
        dreduce();
    L46:
        ASTNode r06 = nextToken();
        if (this.compileMode == true) goto L49;
    L52:
        ExecutionStack r26 = this.dStack;
        Integer r33 = Integer.valueOf(r103);
        Object r53 = this.ctx;
        r26.push(r33, r06.getReducedValue(r53, r53, this.variableFactory));
        goto L32
    L49:
        if (r06.isLiteral() == true) goto L52;
        this.splitAccumulator.push(r06, new OperatorNode(Integer.valueOf(r103), this.expr, this.st, this.pCtx));
        return -2;
    L44:
        if (this.stk.isReduceable() == false) goto L46;
        this.stk.xswap_op();
    L90:
        if (this.dStack.size() <= 1) goto L93;
        dreduce();
    L93:
        if (this.stk.isReduceable() == false) goto L101;
        this.stk.xswap();
        goto L101
    L11:
        if (r102.isLiteral() == true) goto L14;
        this.splitAccumulator.push(r102, new OperatorNode(r2, this.expr, this.st, this.pCtx));
        return -2;
    L96:
        if (r0.isOperator() == false) goto L98;
        reduce();
        this.splitAccumulator.push(r0);
        goto L101
    L98:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected token: " + r0.getName(), this.expr, this.st);
        return 0;
    L101:
        if (this.stk.isReduceable() == false) goto L105;
    L102:
        reduce();
        if (this.stk.isReduceable() == false) goto L105;
        this.stk.xswap();
    L105:
        return 0;
    }

    public void captureIdentifier() {
        int r0 = this.cursor;
        if (r0 == this.end) goto L17;
        boolean r02 = false;
    L5:
        int r1 = this.cursor;
        if (r1 == this.end) goto L22;
        char r12 = this.expr[r1];
        if (r12 == ';') goto L23;
        if (ParseTools.isIdentifierPart(r12) == false) goto L11;
        this.cursor++;
        r02 = true;
        goto L5
    L11:
        if (r02 == true) goto L16;
        Sign$$ExternalSyntheticBUOutline0.m("unexpected symbol (was expecting an identifier): " + this.expr[this.cursor], this.expr, this.cursor);
        return;
    L16:
        return;
    L23:
        return;
    L22:
        return;
    L17:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement: EOF", this.expr, r0);
    }

    public void captureToEOS() {
    L2:
        int r0 = this.cursor;
        int r1 = this.end;
        if (r0 == r1) goto L26;
        char[] r2 = this.expr;
        char r3 = r2[r0];
        if (r3 != '\"') goto L7;
    L24:
        this.cursor = ParseTools.captureStringLiteral(r3, r2, r0, r1);
    L25:
        this.cursor++;
        goto L2
    L7:
        if (r3 == ',') goto L35;
        if (r3 == ';') goto L36;
        if (r3 != '[') goto L13;
    L21:
        int r02 = ParseTools.balancedCaptureWithLineAccounting(r2, r0, r1, r3, this.pCtx);
        this.cursor = r02;
        if (r02 < this.end) goto L25;
        return;
    L13:
        if (r3 == '{') goto L21;
        if (r3 == '}') goto L37;
        if (r3 == '\'') goto L24;
        if (r3 == '(') goto L21;
    L37:
        return;
    L36:
        return;
    L35:
        return;
    }

    public void captureToEOSorEOL() {
    L2:
        int r0 = this.cursor;
        if (r0 == this.end) goto L11;
        char r1 = this.expr[r0];
        if (r1 == '\n') goto L16;
        if (r1 == '\r') goto L17;
        if (r1 == ';') goto L18;
        this.cursor = r0 + 1;
        goto L2
    L18:
        return;
    L17:
        return;
    L16:
        return;
    }

    public void captureToEOT() {
        skipWhitespace();
    L3:
        char[] r0 = this.expr;
        int r1 = this.cursor;
        char r2 = r0[r1];
        if (r2 != '\"') goto L6;
        this.cursor = ParseTools.captureStringLiteral(Typography.quote, r0, r1, this.end);
    L40:
        int r02 = this.cursor + 1;
        this.cursor = r02;
        if (r02 < this.end) goto L3;
        return;
    L6:
        if (r2 == ';') goto L42;
        if (r2 == '=') goto L54;
        if (r2 != '[') goto L12;
    L34:
        int r03 = ParseTools.balancedCaptureWithLineAccounting(r0, r1, this.end, r2, this.pCtx);
        this.cursor = r03;
        if (r03 != (-1)) goto L40;
        Sign$$ExternalSyntheticBUOutline0.m("unbalanced braces", this.expr, r03);
        return;
    L12:
        if (r2 != '.') goto L14;
        this.cursor = r1 + 1;
        skipWhitespace();
        this.cursor--;
        goto L40
    L14:
        if (r2 == '/') goto L55;
        if (r2 == '{') goto L34;
        if (r2 == '|') goto L56;
        switch(r2) {
            case 37: goto L57;
            case 38: goto L57;
            case 39: goto L32;
            case 40: goto L34;
            default: goto L20;
        };
    L20:
        switch(r2) {
            case 42: goto L58;
            case 43: goto L58;
            case 44: goto L58;
            default: goto L22;
        };
    L22:
        if (ParseTools.isWhitespace(r2) == false) goto L40;
        skipWhitespace();
        int r04 = this.cursor;
        int r12 = this.end;
        if (r04 >= r12) goto L30;
        if (this.expr[r04] != '.') goto L30;
        if (r04 == r12) goto L29;
        this.cursor = r04 + 1;
    L29:
        skipWhitespace();
    L30:
        trimWhitespace();
        return;
    L58:
        return;
    L32:
        this.cursor = ParseTools.captureStringLiteral('\'', r0, r1, this.end);
        goto L40
    L57:
        return;
    L56:
        return;
    L55:
        return;
    L54:
        return;
    }

    public void captureToNextTokenJunction() {
    L2:
        int r0 = this.cursor;
        int r1 = this.end;
        if (r0 == r1) goto L19;
        char[] r2 = this.expr;
        char r3 = r2[r0];
        if (r3 == '(') goto L31;
        if (r3 == '/') goto L16;
        if (r3 != '[') goto L10;
    L18:
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r2, r0, r1, '[', this.pCtx) + 1;
        goto L2
    L10:
        if (r3 == '{') goto L32;
        if (ParseTools.isWhitespace(r3) == true) goto L33;
        this.cursor++;
        goto L2
    L33:
        return;
    L32:
        return;
    L16:
        if (r2[r0 + 1] != '*') goto L18;
        return;
    L31:
        return;
    }

    public void expectEOS() {
        skipWhitespace();
        int r0 = this.cursor;
        if (r0 == this.end) goto L46;
        char r02 = this.expr[r0];
        if (r02 != ';') goto L7;
        return;
    L7:
        if (r02 == '!') goto L38;
        if (r02 == '&') goto L35;
        if (r02 == '-') goto L32;
        if (r02 == '/') goto L32;
        if (r02 == '|') goto L29;
        if (r02 == '*') goto L32;
        if (r02 == '+') goto L32;
        switch(r02) {
            case 60: goto L48;
            case 61: goto L22;
            case 62: goto L48;
            default: goto L40;
        };
    L22:
        char r03 = lookAhead();
        if (r03 == '*') goto L49;
        if (r03 == '+') goto L50;
        if (r03 == '-') goto L51;
        if (r03 != '=') goto L40;
        return;
    L51:
        return;
    L50:
        return;
    L49:
        return;
    L40:
        StringBuilder r1 = new StringBuilder("expected end of statement but encountered: ");
        int r2 = this.cursor;
        if (r2 != this.end) goto L43;
        Object r22 = "<end of stream>";
    L44:
        r1.append(r22);
        throw new CompileException(r1.toString(), this.expr, this.cursor);
    L43:
        r22 = Character.valueOf(this.expr[r2]);
        goto L44
    L48:
        return;
    L29:
        if (lookAhead() != '|') goto L40;
        return;
    L32:
        if (lookAhead() != '=') goto L40;
        return;
    L35:
        if (lookAhead() != '&') goto L40;
        return;
    L38:
        if (lookAhead() != '=') goto L40;
        return;
    }

    public void expectNextChar_IW(char r4) {
        nextNonBlank();
        int r0 = this.cursor;
        int r1 = this.end;
        char[] r2 = this.expr;
        if (r0 != r1) goto L5;
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", r2, this.st);
        return;
    L5:
        if (r2[r0] != r4) goto L7;
        return;
    L7:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected character ('" + this.expr[this.cursor] + "'); was expecting: " + r4, this.expr, this.st);
    }

    @Override // org.mvel2.compiler.Parser
    public int getCursor() {
        return this.cursor;
    }

    @Override // org.mvel2.compiler.Parser
    public char[] getExpression() {
        return this.expr;
    }

    public ASTNode handleSubstatement(Substatement r3) {
        if (r3.getStatement() != null) goto L5;
    L8:
        return r3;
    L5:
        if (r3.getStatement().isLiteralOnly() == false) goto L8;
        return new LiteralNode(r3.getStatement().getValue(null, null, null), this.pCtx);
    }

    public ASTNode handleUnion(ASTNode r11) {
        if (this.cursor == this.end) goto L17;
        skipWhitespace();
        int r0 = this.cursor;
        if (r0 >= this.end) goto L13;
        char r1 = this.expr[r0];
        if (r1 != '.') goto L9;
        r0 = r0 + 1;
    L11:
        int r5 = r0;
    L14:
        if (r5 == (-1)) goto L17;
        captureToEOT();
        Union r3 = new Union(this.expr, r5, this.cursor - r5, this.fields, r11, this.pCtx);
        this.lastNode = r3;
        return r3;
    L9:
        if (r1 == '[') goto L11;
    L13:
        r5 = -1;
    L17:
        this.lastNode = r11;
        return r11;
    }

    public boolean ifThenElseBlockContinues() {
        int r0 = this.cursor;
        if ((r0 + 4) < this.end) goto L5;
    L22:
        return false;
    L5:
        if (this.expr[r0] == ';') goto L7;
        this.cursor = r0 - 1;
    L7:
        skipWhitespace();
        int r02 = this.cursor;
        if ((r02 + 4) >= this.end) goto L22;
        char[] r1 = this.expr;
        if (r1[r02] != 'e') goto L22;
        if (r1[r02 + 1] != 'l') goto L22;
        if (r1[r02 + 2] != 's') goto L22;
        if (r1[r02 + 3] != 'e') goto L22;
        if (ParseTools.isWhitespace(r1[r02 + 4]) == false) goto L20;
    L21:
        return true;
    L20:
        if (this.expr[this.cursor + 4] != '{') goto L22;
        goto L21
    }

    public int incNextNonBlank() {
        this.cursor++;
        return nextNonBlank();
    }

    public boolean isNextIdentifier() {
    L2:
        int r0 = this.cursor;
        if (r0 == this.end) goto L7;
        if (ParseTools.isWhitespace(this.expr[r0]) == false) goto L7;
        this.cursor++;
    L7:
        int r02 = this.cursor;
        if (r02 != this.end) goto L10;
        return false;
    L10:
        if (ParseTools.isIdentifierPart(this.expr[r02]) == false) goto L16;
        return true;
    L16:
        return false;
    }

    public boolean isNextIdentifierOrLiteral() {
        int r0 = this.cursor;
        if (r0 != this.end) goto L6;
        return false;
    L6:
        if (r0 == this.end) goto L11;
        if (ParseTools.isWhitespace(this.expr[r0]) == false) goto L11;
        r0 = r0 + 1;
    L11:
        if (r0 != this.end) goto L13;
        return false;
    L13:
        char r3 = this.expr[r0];
        if (ParseTools.isIdentifierPart(r3) == false) goto L16;
        return true;
    L16:
        if (ParseTools.isDigit(r3) == false) goto L18;
        return true;
    L18:
        if (r3 != '\'') goto L20;
        return true;
    L20:
        if (r3 == '\"') goto L29;
        return false;
    L29:
        return true;
    }

    public boolean isStatementNotManuallyTerminated() {
        int r0 = this.cursor;
        if (r0 < this.end) goto L6;
        return false;
    L6:
        if (r0 == this.end) goto L11;
        if (ParseTools.isWhitespace(this.expr[r0]) == false) goto L11;
        r0 = r0 + 1;
    L11:
        if (r0 != this.end) goto L13;
        return true;
    L13:
        if (this.expr[r0] != ';') goto L20;
        return false;
    L20:
        return true;
    }

    public boolean lastNonWhite(char r4) {
        int r0 = this.cursor - 1;
    L4:
        if (ParseTools.isWhitespace(this.expr[r0]) == false) goto L7;
        r0 = r0 - 1;
        goto L4
    L7:
        if (r4 != this.expr[r0]) goto L9;
        return true;
    L9:
        return false;
    }

    public char lookAhead() {
        int r0 = this.cursor;
        if ((r0 + 1) != this.end) goto L5;
        return 0;
    L5:
        return this.expr[r0 + 1];
    }

    public char lookAhead(int r4) {
        int r0 = this.cursor;
        if ((r0 + r4) < this.end) goto L7;
        return 0;
    L7:
        return this.expr[r0 + r4];
    }

    public char lookBehind() {
        int r0 = this.cursor;
        if (r0 != this.start) goto L7;
        return 0;
    L7:
        return this.expr[r0 - 1];
    }

    public char lookToLast() {
        int r0 = this.cursor;
        if (r0 != this.start) goto L7;
        return 0;
    L7:
        if (r0 == this.start) goto L11;
        r0 = r0 - 1;
        if (ParseTools.isWhitespace(this.expr[r0]) == true) goto L7;
    L11:
        return this.expr[r0];
    }

    public int nextNonBlank() {
        int r0 = this.cursor;
        if ((r0 + 1) < this.end) goto L5;
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", this.expr, this.st);
        return 0;
    L5:
        if (r0 == this.end) goto L9;
        if (ParseTools.isWhitespace(this.expr[r0]) == false) goto L9;
        r0 = r0 + 1;
    L9:
        return r0;
    }

    public ASTNode nextToken() {
    L16:
        e = e;
        ASTNode r16 = null;
    L733:
        Negation$$ExternalSyntheticBUOutline0.m("unexpected end of statement", this.expr, this.cursor, e);
        return r16;
    L14:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("unexpected end of statement", this.expr, this.cursor, e);
        return null;
    L18:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("badly formatted number: " + e.getMessage(), this.expr, this.st, e);
        return null;
    L12:
        e = move-exception;
        throw ErrorUtil.rewriteIfNeeded(e, this.expr, this.cursor);
    L745:
    L78:
        e = e;
        r16 = null;
        goto L733
    L5:
        if (this.splitAccumulator.isEmpty() == true) goto L22;
        ASTNode r0 = (ASTNode) this.splitAccumulator.pop();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        this.lastNode = r0;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        if (this.cursor >= this.end) goto L9;
    L20:
        return r0;
    L9:
        if ((r0 instanceof EndOfStatement) == false) goto L20;
        return nextToken();
    L22:
        if (this.cursor < this.end) goto L26;
        return null;
    L26:
        if ((this.fields & 16) == 0) goto L29;
        this.debugSymbols = this.pCtx.isDebugSymbols();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
    L29:
        int r5 = 1;
        if (this.debugSymbols == true) goto L32;
    L50:
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r02 = 0;
        int r6 = 0;
    L51:
        int r7 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r7 == this.end) goto L723;
        if (ParseTools.isIdentifierPart(this.expr[r7]) == false) goto L63;
        this.cursor += r5;
    L56:
        int r03 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        if (r03 == this.end) goto L62;
        if (ParseTools.isIdentifierPart(this.expr[r03]) == false) goto L62;
        this.cursor += r5;
    L62:
        r02 = r5;
    L63:
        int r17 = r5;
        int r21 = 3;
        if (r02 == 0) goto L439;
        HashMap<String, Integer> r13 = OPERATORS;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r72 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r8 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        String r15 = new String(r72, r8, this.cursor - r8);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r13.containsKey(r15) == true) goto L68;
    L188:
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r73 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r9 = this.end;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r73 == r9) goto L193;
        char[] r10 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r10[r73] != '(') goto L193;
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r10, r73, r9, '(', this.pCtx) + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L193:
        int r74 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r92 = this.end;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r74 == r92) goto L437;
        char[] r102 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char r12 = r102[r74];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r12 == '[') goto L435;
        if (r12 == '^') goto L422;
        if (r12 == '~') goto L418;
        if (r12 == 187) goto L422;
        if (r12 == '{') goto L413;
        if (r12 == '|') goto L422;
        if (r12 == 171) goto L422;
        if (r12 == 172) goto L422;
        switch(r12) {
            case 33: goto L437;
            case 34: goto L437;
            case 35: goto L437;
            default: goto L211;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L211:
        switch(r12) {
            case 37: goto L422;
            case 38: goto L422;
            case 39: goto L437;
            case 40: goto L412;
            default: goto L213;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L213:
        switch(r12) {
            case 42: goto L422;
            case 43: goto L378;
            case 44: goto L437;
            case 45: goto L342;
            case 46: goto L340;
            case 47: goto L422;
            default: goto L214;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L214:
        switch(r12) {
            case 58: goto L437;
            case 59: goto L437;
            case 60: goto L324;
            case 61: goto L270;
            case 62: goto L239;
            case 63: goto L234;
            default: goto L215;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L239:
        if (lookAhead() != '>') goto L437;
        if (lookAhead(2) != '=') goto L254;
        String r04 = new String(this.expr, this.st, trimLeft(this.cursor) - this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r3 = this.cursor + 3;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r3;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r3;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r6 == 0) goto L246;
        char[] r05 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r32 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        DeepAssignmentNode r24 = new DeepAssignmentNode(r05, r32, this.cursor - r32, this.fields, 9, r15, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r24;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r24;
    L246:
        ParserContext r33 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r33 == null) goto L252;
        int r93 = r33.variableIndexOf(r04);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r93 == (-1)) goto L252;
        char[] r52 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r62 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedOperativeAssign r4 = new IndexedOperativeAssign(r52, r62, this.cursor - r62, 9, r93, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r4;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r4;
    L252:
        char[] r34 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r42 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        OperativeAssign r23 = new OperativeAssign(r04, r34, r42, this.cursor - r42, 9, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r23;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r23;
    L254:
        String r30 = r15;
        if (lookAhead(2) != '>') goto L437;
        if (lookAhead(r21) != '=') goto L437;
        String r06 = new String(this.expr, this.st, trimLeft(this.cursor) - this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r35 = this.cursor + 4;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r35;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r35;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r6 == 0) goto L262;
        char[] r07 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r36 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        DeepAssignmentNode r242 = new DeepAssignmentNode(r07, r36, this.cursor - r36, this.fields, 11, r30, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r242;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r242;
    L262:
        ParserContext r37 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r37 == null) goto L268;
        int r94 = r37.variableIndexOf(r06);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r94 == (-1)) goto L268;
        char[] r53 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r63 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedOperativeAssign r43 = new IndexedOperativeAssign(r53, r63, this.cursor - r63, 11, r94, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r43;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r43;
    L268:
        char[] r38 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r44 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        OperativeAssign r172 = new OperativeAssign(r06, r38, r44, this.cursor - r44, 11, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r172;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r172;
    L270:
        String r08 = r15;
        if (lookAhead() != '+') goto L286;
        String r09 = new String(this.expr, this.st, trimLeft(this.cursor) - this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r39 = this.cursor + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r39;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r39;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (isNextIdentifierOrLiteral() == false) goto L284;
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ParserContext r310 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r310 == null) goto L281;
        int r95 = r310.variableIndexOf(r09);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r95 == (-1)) goto L281;
        char[] r54 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r64 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedOperativeAssign r45 = new IndexedOperativeAssign(r54, r64, this.cursor - r64, 0, r95, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r45;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r45;
    L281:
        char[] r311 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r46 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        OperativeAssign r243 = new OperativeAssign(r09, r311, r46, this.cursor - r46, 0, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r243;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r243;
    L284:
        throw new CompileException("unexpected symbol '" + this.expr[this.cursor] + "'", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L286:
        if (lookAhead() != '-') goto L300;
        String r010 = new String(this.expr, this.st, trimLeft(this.cursor) - this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r312 = this.cursor + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r312;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r312;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (isNextIdentifierOrLiteral() == false) goto L298;
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ParserContext r313 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r313 == null) goto L295;
        int r96 = r313.variableIndexOf(r010);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r96 == (-1)) goto L295;
        char[] r55 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r65 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedOperativeAssign r47 = new IndexedOperativeAssign(r55, r65, this.cursor - r65, 1, r96, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r47;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r47;
    L295:
        char[] r314 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r48 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        OperativeAssign r244 = new OperativeAssign(r010, r314, r48, this.cursor - r48, 1, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r244;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r244;
    L298:
        throw new CompileException("unexpected symbol '" + this.expr[this.cursor] + "'", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L300:
        if (this.greedy == false) goto L437;
        if (lookAhead() == '=') goto L437;
        this.cursor++;
        if (r6 == 0) goto L308;
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r82 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r97 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        DeepAssignmentNode r75 = new DeepAssignmentNode(r82, r97, this.cursor - r97, this.fields | 128, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r75;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r75;
    L308:
        if (this.lastWasIdentifier == true) goto L310;
        ParserContext r315 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r315 == null) goto L322;
        int r98 = r315.variableIndexOf(r08);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r98 == (-1)) goto L322;
        if (this.pCtx.isIndexAllocation() == false) goto L322;
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r56 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r66 = trimRight(this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r66;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedAssignmentNode r49 = new IndexedAssignmentNode(r56, r66, trimLeft(this.cursor) - this.st, 128, r98, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r98 != (-1)) goto L320;
        ParserContext r011 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        String r316 = r49.getVarName();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        r011.addIndexedInput(r316);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        r49.setRegister(this.pCtx.variableIndexOf(r316));     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L320:
        this.lastNode = r49;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r49;
    L322:
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r410 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r57 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        AssignmentNode r317 = new AssignmentNode(r410, r57, this.cursor - r57, this.fields | 128, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r317;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r317;
    L310:
        return procTypedNode(false);
    L324:
        String r012 = r15;
        if (lookAhead() != '<') goto L437;
        if (lookAhead(2) != '=') goto L437;
        String r318 = new String(this.expr, this.st, trimLeft(this.cursor) - this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r411 = this.cursor + 3;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r411;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r411;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r6 == 0) goto L332;
        char[] r319 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r412 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        DeepAssignmentNode r245 = new DeepAssignmentNode(r319, r412, this.cursor - r412, this.fields, 10, r012, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r245;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r245;
    L332:
        ParserContext r013 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r013 == null) goto L338;
        int r99 = r013.variableIndexOf(r318);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r99 == (-1)) goto L338;
        char[] r58 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r67 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedOperativeAssign r413 = new IndexedOperativeAssign(r58, r67, this.cursor - r67, 10, r99, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r413;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r413;
    L338:
        char[] r014 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r414 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        OperativeAssign r22 = new OperativeAssign(r318, r014, r414, this.cursor - r414, 10, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r22;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r22;
    L234:
        if (lookToLast() != '.') goto L236;
    L237:
        this.cursor++;
        r6 = r17;
        goto L193
    L236:
        if (this.cursor != this.start) goto L437;
    L215:
        if (r74 == r92) goto L437;
        if (ParseTools.isIdentifierPart(r12) == false) goto L227;
        if (r6 == 0) goto L437;
        this.cursor++;
    L221:
        int r76 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r76 == this.end) goto L226;
        if (ParseTools.isIdentifierPart(this.expr[r76]) == false) goto L226;
        this.cursor++;
    L226:
        String r302 = r15;
    L436:
        r15 = r302;
        r17 = 1;
    L341:
        r21 = 3;
        goto L193
    L227:
        int r77 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if ((r77 + 1) != this.end) goto L230;
    L232:
        this.cursor++;
        goto L226
    L230:
        if (ParseTools.isIdentifierPart(this.expr[r77 + 1]) == false) goto L232;
    L342:
        char r015 = lookAhead();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r015 == '-') goto L370;
        if (r015 == '=') goto L358;
        if (ParseTools.isDigit(lookAhead()) == false) goto L437;
        int r016 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r016 <= r17) goto L437;
        char[] r320 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r320[r016 - 1] == 'E') goto L354;
        if (r320[r016 - 1] != 'e') goto L437;
    L354:
        if (ParseTools.isDigit(r320[r016 - 2]) == false) goto L437;
        this.cursor++;
    L356:
        r02 = 1;
    L357:
        r5 = 1;
        goto L51
    L358:
        String r415 = new String(this.expr, this.st, trimLeft(this.cursor) - this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r017 = this.cursor + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r017;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r017;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r6 == 0) goto L362;
        char[] r416 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r59 = trimRight(this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r59;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        DeepOperativeAssignmentNode r321 = new DeepOperativeAssignmentNode(r416, r59, trimLeft(this.cursor) - this.st, this.fields, 1, r415, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r321;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r321;
    L362:
        ParserContext r018 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r018 == null) goto L368;
        int r019 = r018.variableIndexOf(r415);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r019 == (-1)) goto L368;
        char[] r322 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r417 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedOperativeAssign r173 = new IndexedOperativeAssign(r322, r417, this.cursor - r417, 1, r019, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r173;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r173;
    L368:
        char[] r510 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r68 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        OperativeAssign r323 = new OperativeAssign(r415, r510, r68, this.cursor - r68, 1, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r323;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r323;
    L370:
        String r020 = new String(subArray(this.st, trimLeft(this.cursor)));     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ParserContext r324 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r324 == null) goto L375;
        int r325 = r324.variableIndexOf(r020);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r325 == (-1)) goto L375;
        this.lastNode = new IndexedPostFixDecNode(r325, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L376:
        this.cursor += 2;
        expectEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return this.lastNode;
    L375:
        this.lastNode = new PostFixDecNode(r020, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L376
    L378:
        char r326 = lookAhead();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r326 == '+') goto L404;
        if (r326 == '=') goto L392;
        if (ParseTools.isDigit(lookAhead()) == false) goto L437;
        int r327 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r327 <= 1) goto L437;
        char[] r511 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r511[r327 - 1] == 'E') goto L390;
        if (r511[r327 - 1] != 'e') goto L437;
    L390:
        if (ParseTools.isDigit(r511[r327 - 2]) == false) goto L437;
        this.cursor++;
        goto L357
    L392:
        char[] r021 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r328 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        String r303 = ParseTools.createStringTrimmed(r021, r328, this.cursor - r328);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r022 = this.cursor + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r022;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r022;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r6 == 0) goto L396;
        char[] r023 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r329 = trimRight(this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r329;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        DeepOperativeAssignmentNode r246 = new DeepOperativeAssignmentNode(r023, r329, trimLeft(this.cursor) - this.st, this.fields, 0, r303, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r246;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r246;
    L396:
        ParserContext r330 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r330 == null) goto L402;
        int r331 = r330.variableIndexOf(r303);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r331 == (-1)) goto L402;
        char[] r418 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r512 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedAssignmentNode r247 = new IndexedAssignmentNode(r418, r512, this.cursor - r512, this.fields, 0, r303, r331, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r247;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r247;
    L402:
        char[] r024 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r332 = trimRight(this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r332;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        OperativeAssign r248 = new OperativeAssign(r303, r024, r332, trimLeft(this.cursor) - this.st, 0, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r248;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r248;
    L404:
        String r025 = new String(subArray(this.st, trimLeft(this.cursor)));     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ParserContext r333 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r333 == null) goto L409;
        int r334 = r333.variableIndexOf(r025);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r334 == (-1)) goto L409;
        this.lastNode = new IndexedPostFixIncNode(r334, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L410:
        this.cursor += 2;
        expectEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return this.lastNode;
    L409:
        this.lastNode = new PostFixIncNode(r025, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L410
    L340:
        this.cursor = r74 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        r6 = r17;
        r15 = r15;
        goto L341
    L412:
        r302 = r15;
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r102, r74, r92, '(', this.pCtx) + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L436
    L413:
        r302 = r15;
        if (r6 == 0) goto L437;
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r102, r74, r92, '{', this.pCtx) + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L436
    L418:
        if (lookAhead() != '=') goto L437;
        int r513 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r026 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r69 = r026 - r513;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r027 = r026 + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r027;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r027;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r419 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r78 = this.fields;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r83 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        RegExMatch r335 = new RegExMatch(r419, r513, r69, r78, r83, this.cursor - r83, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r335;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r335;
    L422:
        if (lookAhead() != '=') goto L437;
        String r028 = new String(this.expr, this.st, trimLeft(this.cursor) - this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r336 = this.cursor + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r336;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r336;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r6 == 0) goto L427;
        char[] r337 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r420 = trimRight(this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r420;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        DeepOperativeAssignmentNode r249 = new DeepOperativeAssignmentNode(r337, r420, trimLeft(this.cursor) - this.st, this.fields, ParseTools.opLookup(r12), r028, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r249;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r249;
    L427:
        ParserContext r338 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r338 == null) goto L433;
        int r910 = r338.variableIndexOf(r028);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r910 == (-1)) goto L433;
        char[] r514 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r610 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedOperativeAssign r421 = new IndexedOperativeAssign(r514, r610, this.cursor - r610, ParseTools.opLookup(r12), r910, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r421;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r421;
    L433:
        char[] r339 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r422 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        OperativeAssign r2410 = new OperativeAssign(r028, r339, r422, this.cursor - r422, ParseTools.opLookup(r12), this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r2410;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r2410;
    L435:
        r302 = r15;
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r102, r74, r92, '[', this.pCtx) + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L437:
        trimWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createPropertyToken(this.st, this.cursor);
    L68:
        if (Character.isDigit(this.expr[this.st]) == true) goto L188;
        int r79 = OPERATORS.get(r15).intValue();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r79 == 26) goto L186;
        if (r79 == 34) goto L139;
        switch(r79) {
            case 38: goto L138;
            case 39: goto L136;
            case 40: goto L134;
            case 41: goto L132;
            case 42: goto L130;
            case 43: goto L128;
            default: goto L73;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L73:
        switch(r79) {
            case 45: goto L126;
            case 46: goto L124;
            case 47: goto L121;
            case 48: goto L120;
            default: goto L74;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L74:
        switch(r79) {
            case 95: goto L117;
            case 96: goto L110;
            case 97: goto L108;
            case 98: goto L84;
            case 99: goto L82;
            case 100: goto L80;
            case 101: goto L77;
            default: goto L188;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L84:
        this.st = this.cursor + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L85:
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r340 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r515 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r515 == r340) goto L94;
        char[] r710 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r710[r515] != '=') goto L94;
        int r516 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r516;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r340 != r516) goto L357;
        throw new CompileException("illegal use of reserved word: var", r710, r516);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L94:
        char[] r711 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r84 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        String r517 = new String(r711, r84, r340 - r84);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ParserContext r712 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r712 == null) goto L99;
        int r713 = r712.variableIndexOf(r517);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r713 == (-1)) goto L99;
        ExecutionStack r518 = this.splitAccumulator;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r85 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IndexedDeclTypedVarNode r18 = new IndexedDeclTypedVarNode(r713, r85, r340 - r85, Object.class, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r18;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        r518.add(r18);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L100:
        int r341 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r341 == this.end) goto L107;
        if (this.expr[r341] != ',') goto L107;
        this.cursor = r341 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L107:
        return (ASTNode) this.splitAccumulator.pop();
    L99:
        ExecutionStack r714 = this.splitAccumulator;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r86 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r911 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        DeclTypedVarNode r182 = new DeclTypedVarNode(r517, r86, r911, r340 - r911, Object.class, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r182;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        r714.add(r182);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L100
    L77:
        return captureCodeBlock(101);
    L80:
        ASTNode r029 = captureCodeBlock(100);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r029;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = this.cursor + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r029;
    L82:
        int r030 = trimRight(this.cursor);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r030;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r030;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r423 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r519 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ReturnNode r342 = new ReturnNode(r423, r519, this.cursor - r519, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r342;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r342;
    L108:
        int r031 = trimRight(this.cursor);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r031;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r031;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r424 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r520 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r032 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r032 - 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        AssertNode r343 = new AssertNode(r424, r520, r032 - r520, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r343;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r343;
    L110:
        int r033 = trimRight(this.cursor);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r033;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r033;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r344 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r425 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ImportNode r034 = new ImportNode(r344, r425, this.cursor - r425, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        boolean r345 = r034.isPackageImport();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ParserContext r426 = this.pCtx;
        if (r345 == false) goto L114;
        r426.addPackageImport(r034.getPackageImport());     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L115:
        this.lastNode = r034;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r034;
    L114:
        r426.addImport(r034.getImportClass().getSimpleName(), r034.getImportClass());     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L115
    L117:
        int r035 = trimRight(this.cursor);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r035;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r035;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOS();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        StaticImportNode r036 = new StaticImportNode(this.expr, this.st, trimLeft(this.cursor) - this.st, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.pCtx.addImport(r036.getMethod().getName(), r036.getMethod());     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r036;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r036;
    L120:
        return captureCodeBlock(48);
    L121:
        int r037 = trimRight(this.cursor);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r037;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r037;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToNextTokenJunction();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r346 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r427 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        IsDef r038 = new IsDef(r346, r427, this.cursor - r427, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r038;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r038;
    L124:
        return captureCodeBlock(8192);
    L126:
        return captureCodeBlock(65536);
    L128:
        return captureCodeBlock(262144);
    L130:
        return captureCodeBlock(16384);
    L132:
        return captureCodeBlock(32768);
    L134:
        throw new CompileException("else without if", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L136:
        return captureCodeBlock(2048);
    L138:
        return captureCodeBlock(4096);
    L139:
        char[] r039 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r428 = trimRight(this.cursor);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r428;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r428;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (ParseTools.isIdentifierPart(r039[r428]) == false) goto L185;
    L141:
        captureToNextTokenJunction();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r040 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r429 = this.end;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r040 >= r429) goto L145;
        if (this.expr[r040] == '[') goto L141;
    L145:
        if (r040 < r429) goto L147;
    L149:
        TypeDescriptor r041 = new TypeDescriptor(this.expr, this.st, trimLeft(this.cursor) - this.st, this.fields);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (this.pCtx.getFunctions().containsKey(r041.getClassName()) == false) goto L154;
        ParserContext r430 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        NewObjectPrototype r347 = new NewObjectPrototype(r430, r430.getFunction(r041.getClassName()));     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r347;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r347;
    L154:
        if (this.pCtx.hasProtoImport(r041.getClassName()) == false) goto L157;
        NewPrototypeNode r348 = new NewPrototypeNode(r041, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r348;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r348;
    L157:
        this.lastNode = new NewObjectNode(r041, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r431 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r431 == this.end) goto L179;
        if (this.expr[r431] != '{') goto L179;
        if (((NewObjectNode) this.lastNode).getTypeDescr().isUndimensionedArray() == false) goto L177;
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        Class r349 = this.lastNode.getEgressType();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r349 == null) goto L742;
    L166:
        Class r87 = r349;
        char[] r042 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r042, this.st, this.end, r042[this.cursor], this.pCtx) + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (tokenContinues() == false) goto L174;
        char[] r432 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r521 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = new InlineCollectionNode(r432, r521, this.cursor - r521, this.fields, r87, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        Union r350 = new Union(this.expr, this.st + 1, this.cursor, this.fields, this.lastNode, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r350;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r350;
    L174:
        char[] r433 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r522 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        InlineCollectionNode r351 = new InlineCollectionNode(r433, r522, this.cursor - r522, this.fields, r87, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r351;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r351;
    L742:
        r349 = TypeDescriptor.getClassReference(this.pCtx, r041);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L167 RedundantCodeException -> L737
    L167:
        e = move-exception;
        throw new CompileException("could not instantiate class", this.expr, this.st, e);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L177:
        throw new CompileException("conflicting syntax: dimensioned array with initializer block", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L179:
        if (((NewObjectNode) this.lastNode).getTypeDescr().isUndimensionedArray() == true) goto L183;
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return this.lastNode;
    L183:
        throw new CompileException("array initializer expected", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L147:
        if (lastNonWhite(']') == true) goto L149;
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L149
    L185:
        throw new CompileException("unexpected character (expected identifier): " + this.expr[this.cursor], this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L186:
        this.lastWasIdentifier = false;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        OperatorNode r043 = new OperatorNode(26, this.expr, this.st, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r043;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r043;
    L439:
        char[] r715 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r88 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char r912 = r715[r88];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r912 == '[') goto L714;
        if (r912 == ']') goto L713;
        if (r912 == '^') goto L710;
        switch(r912) {
            case 33: goto L678;
            case 34: goto L672;
            case 35: goto L710;
            default: goto L445;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L445:
        switch(r912) {
            case 37: goto L710;
            case 38: goto L666;
            case 39: goto L672;
            case 40: goto L582;
            case 41: goto L713;
            case 42: goto L578;
            case 43: goto L566;
            default: goto L446;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L446:
        switch(r912) {
            case 45: goto L519;
            case 46: goto L514;
            case 47: goto L710;
            default: goto L447;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L447:
        switch(r912) {
            case 58: goto L710;
            case 59: goto L512;
            case 60: goto L500;
            case 61: goto L498;
            case 62: goto L485;
            case 63: goto L483;
            case 64: goto L472;
            default: goto L448;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L448:
        switch(r912) {
            case 123: goto L714;
            case 124: goto L466;
            case 125: goto L713;
            case 126: goto L450;
            default: goto L449;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L449:
        this.cursor = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L357
    L450:
        this.cursor = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if ((r88 - 1) != 0) goto L455;
        if (ParseTools.isIdentifierPart(lookBehind()) == false) goto L455;
    L458:
        char[] r044 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r352 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char r434 = r044[r352];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r434 != '(') goto L462;
        this.cursor = r352 - 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r352;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r523 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r611 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        Invert r435 = new Invert(r523, r611, this.cursor - r611, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r435;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r435;
    L462:
        if (r434 != '=') goto L465;
        this.cursor = r352 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L465:
        return createOperator(r044, this.st, this.cursor);
    L455:
        if (ParseTools.isDigit(this.expr[this.cursor]) == false) goto L458;
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r436 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r524 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        Invert r353 = new Invert(r436, r524, this.cursor - r524, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r353;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r353;
    L466:
        int r045 = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r045;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r715[r045] != '|') goto L471;
        int r046 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r89 = r88 + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r89;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r715, r046, r89);
    L471:
        return createOperator(r715, this.st, r045);
    L483:
        if (r88 != this.start) goto L710;
        this.cursor = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L357
    L472:
        this.st++;
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ParserContext r047 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r047 != null) goto L475;
    L480:
        StringBuilder r354 = new StringBuilder();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        r354.append("reference to undefined interceptor: ");     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r525 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r612 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        r354.append(new String(r525, r612, this.cursor - r612));     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        throw new CompileException(r354.toString(), this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L475:
        if (r047.getInterceptors() == null) goto L480;
        Map<String, Interceptor> r048 = this.pCtx.getInterceptors();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r437 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r526 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        String r355 = new String(r437, r526, this.cursor - r526);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r048.containsKey(r355) == false) goto L480;
        InterceptorWrapper r049 = new InterceptorWrapper(this.pCtx.getInterceptors().get(r355), nextToken(), this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r049;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r049;
    L485:
        char r050 = r715[r88 + 1];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r050 != '=') goto L488;
        int r051 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r810 = r88 + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r810;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r715, r051, r810);
    L488:
        if (r050 == '>') goto L491;
        int r052 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r811 = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r811;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r715, r052, r811);
    L491:
        int r053 = r88 + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r053;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r715[r053] != '>') goto L495;
        this.cursor = r88 + 3;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L495:
        return createOperator(r715, this.st, this.cursor);
    L498:
        int r054 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r812 = r88 + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r812;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r715, r054, r812);
    L500:
        int r055 = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r055;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char r356 = r715[r055];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r356 != '<') goto L507;
        int r056 = r88 + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r056;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r715[r056] != '<') goto L506;
        this.cursor = r88 + 3;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L506:
        return createOperator(r715, this.st, this.cursor);
    L507:
        if (r356 != '=') goto L511;
        int r057 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r813 = r88 + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r813;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r715, r057, r813);
    L511:
        return createOperator(r715, this.st, r055);
    L512:
        this.cursor = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastWasIdentifier = false;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        EndOfStatement r058 = new EndOfStatement(this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r058;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r058;
    L514:
        int r814 = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r814;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (ParseTools.isDigit(r715[r814]) == true) goto L356;
        expectNextChar_IW('{');     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r815 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r913 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r059 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r103 = (r059 - r913) - 1;
        int r11 = r059 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r060 = ParseTools.balancedCaptureWithLineAccounting(r815, r059, this.end, '{', this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r060 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ThisWithNode r716 = new ThisWithNode(r815, r913, r103, r11, r060 - 2, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r716;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r716;
    L519:
        char r357 = lookAhead();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r527 = this.cursor;
        if (r357 == '-') goto L523;
        if (r527 == this.start) goto L540;
        ASTNode r358 = this.lastNode;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r358 == null) goto L544;
        if ((r358 instanceof BooleanNode) == true) goto L540;
        if (r358.isOperator() == true) goto L540;
    L544:
        if (this.cursor == this.start) goto L552;
        ASTNode r359 = this.lastNode;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r359 == null) goto L552;
        if ((r359 instanceof BooleanNode) == true) goto L552;
        if (r359.isOperator() == true) goto L552;
    L553:
        char[] r061 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r360 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r438 = this.cursor + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r438;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r061, r360, r438);
    L552:
        if (ParseTools.isDigit(lookAhead()) == false) goto L553;
        int r361 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if ((r361 - 1) == this.start) goto L558;
    L564:
        this.cursor++;
        goto L357
    L558:
        if (ParseTools.isDigit(this.expr[r361 - 1]) == true) goto L563;
        if (ParseTools.isDigit(lookAhead()) == true) goto L564;
    L563:
        throw new CompileException("not a statement", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L540:
        if (ParseTools.isDigit(lookAhead()) == true) goto L544;
        this.cursor++;
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r439 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r528 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return new Sign(r439, r528, this.cursor - r528, this.fields, this.pCtx);
    L523:
        this.cursor = r527 + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureIdentifier();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        String r062 = new String(subArray(this.st, this.cursor));     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ParserContext r362 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r362 == null) goto L529;
        int r363 = r362.variableIndexOf(r062);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r363 == (-1)) goto L529;
        IndexedPreFixDecNode r063 = new IndexedPreFixDecNode(r363, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r063;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r063;
    L529:
        PreFixDecNode r364 = new PreFixDecNode(r062, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r364;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r364;
    L566:
        if (lookAhead() != '+') goto L575;
        this.cursor += 2;
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureIdentifier();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        String r064 = new String(subArray(this.st, this.cursor));     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ParserContext r365 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r365 == null) goto L573;
        int r366 = r365.variableIndexOf(r064);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r366 == (-1)) goto L573;
        IndexedPreFixIncNode r065 = new IndexedPreFixIncNode(r366, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r065;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r065;
    L573:
        PreFixIncNode r367 = new PreFixIncNode(r064, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r367;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r367;
    L575:
        char[] r066 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r368 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r440 = this.cursor + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r440;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r066, r368, r440);
    L578:
        if (lookAhead() != '*') goto L580;
        this.cursor++;
    L580:
        char[] r067 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r369 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r441 = this.cursor + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r441;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r067, r369, r441);
    L582:
        this.cursor = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r068 = 1;
        boolean r370 = true;
    L583:
        int r613 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r717 = this.end;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r613 == r717) goto L637;
        if (r068 == 0) goto L637;
        char[] r914 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char r112 = r914[r613];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r112 != '\"') goto L589;
        this.cursor = ParseTools.captureStringLiteral(Typography.quote, r914, r613, r717);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L636:
        this.cursor++;
        goto L583
    L589:
        if (r112 == 'i') goto L604;
        switch(r112) {
            case 39: goto L602;
            case 40: goto L601;
            case 41: goto L600;
            default: goto L592;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L600:
        r068 = r068 - 1;
        goto L636
    L601:
        r068 = r068 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L636
    L602:
        this.cursor = ParseTools.captureStringLiteral('\'', r914, r613, r717);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L636
    L592:
        if (r112 == '.') goto L636;
        if (r112 == '[') goto L636;
        if (r112 == ']') goto L636;
        if (ParseTools.isIdentifierPart(r112) == true) goto L636;
        if (this.expr[this.cursor] == '.') goto L636;
        r370 = false;
        goto L636
    L604:
        if (r068 != 1) goto L636;
        if (ParseTools.isWhitespace(lookBehind()) == false) goto L636;
        if (lookAhead() != 'n') goto L636;
        if (ParseTools.isWhitespace(lookAhead(2)) == false) goto L636;
        int r371 = r068;
    L612:
        int r442 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r529 = this.end;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r442 == r529) goto L634;
        char[] r614 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char r718 = r614[r442];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r718 == '\"') goto L631;
        switch(r718) {
            case 39: goto L630;
            case 40: goto L629;
            case 41: goto L618;
            default: goto L632;
        };     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L618:
        r371 = r371 - 1;
        if (r371 >= r068) goto L632;
        this.cursor = r442 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r530 = trimRight(this.st + 1);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r615 = (this.cursor - 1) - r530;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (tokenContinues() == false) goto L627;
        this.lastNode = new Fold(this.expr, r530, r615, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r069 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r372 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r372;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r069[r372] != '.') goto L625;
        this.st = r372 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L625:
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r443 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r531 = trimRight(this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r531;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        Union r373 = new Union(r443, r531, this.cursor - r531, this.fields, this.lastNode, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r373;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r373;
    L627:
        Fold r374 = new Fold(this.expr, r530, r615, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r374;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r374;
    L629:
        r371 = r371 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L632
    L630:
        this.cursor = ParseTools.captureStringLiteral('\'', r614, r442, r529);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L632:
        this.cursor++;
        goto L612
    L631:
        this.cursor = ParseTools.captureStringLiteral(Typography.quote, r614, r442, r529);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        goto L632
    L634:
        throw new CompileException("unterminated projection; closing parathesis required", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L637:
        if (r068 != 0) goto L665;
        if (r370 == false) goto L662;
        char[] r375 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r444 = trimRight(this.st + 1);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        TypeDescriptor r070 = new TypeDescriptor(r375, r444, trimLeft(this.cursor - 1) - r444, this.fields);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r070.isClass() == false) goto L662;
        Class r222 = TypeDescriptor.getClassReference(this.pCtx, r070);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        if (r222 == null) goto L662;
        int r071 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
    L645:
        char[] r376 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        if (r071 >= r376.length) goto L662;
        char r377 = r376[r071];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        if (r377 == ' ') goto L661;
        if (r377 == '\t') goto L661;
        if (ParseTools.isIdentifierPart(r377) == true) goto L659;
        char r072 = this.expr[r071];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        if (r072 == '\'') goto L659;
        if (r072 == '\"') goto L659;
        if (r072 != '(') goto L662;
    L659:
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        char[] r073 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        int r378 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        TypeCast r183 = new TypeCast(r073, r378, this.cursor - r378, r222, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        this.lastNode = r183;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 ClassNotFoundException -> L739 RedundantCodeException -> L737
        return r183;
    L661:
        r071 = r071 + 1;
    L662:
        char[] r445 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r532 = trimRight(this.st + 1);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r532;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return handleUnion(handleSubstatement(new Substatement(r445, r532, trimLeft(this.cursor - 1) - this.st, this.fields, this.pCtx)));
    L665:
        throw new CompileException("unbalanced braces in expression: (" + r068 + "):", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L666:
        int r074 = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r074;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r715[r074] != '&') goto L671;
        int r075 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r816 = r88 + 2;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r816;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r715, r075, r816);
    L671:
        return createOperator(r715, this.st, r074);
    L672:
        int r379 = this.st + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = ParseTools.captureStringLiteral(r912, r715, r88, this.end);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = new LiteralNode(ParseTools.handleStringEscapes(ParseTools.subset(r715, r379, (r4 - this.st) - 1)), String.class, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor++;
        boolean r076 = tokenContinues();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        ASTNode r380 = this.lastNode;
        if (r076 == false) goto L677;
        ASTNode r077 = handleUnion(r380);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r077;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r077;
    L677:
        return r380;
    L678:
        this.cursor = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (isNextIdentifier() == false) goto L697;
        ASTNode r078 = this.lastNode;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r078 != null) goto L684;
    L688:
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r446 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r533 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        String r381 = new String(r446, r533, this.cursor - r533);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if ("new".equals(r381) == false) goto L691;
    L695:
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r447 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r534 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        Negation r382 = new Negation(r447, r534, this.cursor - r534, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r382;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r382;
    L691:
        if ("isdef".equals(r381) == true) goto L695;
        char[] r448 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r535 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        Negation r383 = new Negation(r448, r535, this.cursor - r535, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r383;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r383;
    L684:
        if (r078.isOperator() == true) goto L688;
        throw new CompileException("unexpected operator '!'", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L697:
        char[] r079 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r449 = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char r536 = r079[r449];     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r536 != '(') goto L702;
        this.cursor = r449 - 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = r449;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r616 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r719 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        Negation r537 = new Negation(r616, r719, this.cursor - r719, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r537;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r537;
    L702:
        if (r536 != '!') goto L705;
        this.cursor = r449 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return nextToken();
    L705:
        if (r536 != '=') goto L709;
        int r384 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r450 = r449 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r450;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r079, r384, r450);
    L709:
        throw new CompileException("unexpected operator '!'", r079, this.st, null);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L710:
        int r080 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r817 = r88 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.cursor = r817;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return createOperator(r715, r080, r817);
    L713:
        throw new CompileException("unbalanced braces", r715, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L714:
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r715, r88, this.end, r912, this.pCtx) + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (tokenContinues() == false) goto L721;
        char[] r451 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r538 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = new InlineCollectionNode(r451, r538, this.cursor - r538, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.st = this.cursor;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        captureToEOT();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        char[] r452 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r081 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r452[r081] != '.') goto L719;
        this.st = r081 + 1;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
    L719:
        int r539 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        Union r385 = new Union(r452, r539, this.cursor - r539, this.fields, this.lastNode, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r385;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r385;
    L721:
        char[] r453 = this.expr;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        int r540 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        InlineCollectionNode r386 = new InlineCollectionNode(r453, r540, this.cursor - r540, this.fields, this.pCtx);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        this.lastNode = r386;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        return r386;
    L723:
        int r082 = this.st;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 NumberFormatException -> L18 StringIndexOutOfBoundsException -> L78 RedundantCodeException -> L737
        if (r082 != r7) goto L728;
        return null;
    L728:
        return createPropertyToken(r082, r7);
    L32:
        if (this.lastWasLineLabel == false) goto L34;
        this.lastWasLineLabel = false;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        this.lastWasComment = false;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        goto L50
    L34:
        if (this.pCtx.getSourceFile() == null) goto L48;
        ParserContext r083 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        if (r083.isLineMapped(r083.getSourceFile()) == true) goto L38;
        ParserContext r084 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        r084.initLineMapping(r084.getSourceFile(), this.expr);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
    L38:
        skipWhitespace();     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        if (this.cursor < this.end) goto L41;
        return null;
    L41:
        ParserContext r085 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        int r086 = r085.getLineFor(r085.getSourceFile(), this.cursor);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        ParserContext r617 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        if (r617.isVisitedLine(r617.getSourceFile(), this.pCtx.setLineCount(r086)) == true) goto L50;
        if (this.pCtx.isBlockSymbols() == true) goto L50;
        this.lastWasLineLabel = true;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        ParserContext r454 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        r454.visitLine(r454.getSourceFile(), r086);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        ParserContext r455 = this.pCtx;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        LineLabel r087 = r455.setLastLineLabel(new LineLabel(r455.getSourceFile(), r086, this.pCtx));     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        this.lastNode = r087;     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
        return r087;
    L48:
        throw new CompileException("unable to produce debugging symbols: source name must be provided.", this.expr, this.st);     // Catch: CompileException -> L12 ArrayIndexOutOfBoundsException -> L14 StringIndexOutOfBoundsException -> L16 NumberFormatException -> L18 RedundantCodeException -> L737
    L738:
        return nextToken();
    }

    public ASTNode nextTokenSkipSymbols() {
        ASTNode r0 = nextToken();
        if (r0 != null) goto L5;
    L8:
        return r0;
    L5:
        if (r0.getFields() != (-1)) goto L8;
        return nextToken();
    }

    public void reduce() {
        int r0 = ((Integer) this.stk.pop()).intValue();     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        if (r0 == 0) goto L66;
        boolean r1 = true;
        if (r0 == 1) goto L66;
        if (r0 == 2) goto L66;
        if (r0 == 3) goto L66;
        if (r0 == 4) goto L66;
        if (r0 == 5) goto L66;
        if (r0 != 36) goto L17;
        ExecutionStack r02 = this.stk;     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        r02.push(Boolean.valueOf(DataConversion.canConvert(r02.peek2().getClass(), (Class) this.stk.pop2())));     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L17:
        switch(r0) {
            case 14: goto L66;
            case 15: goto L66;
            case 16: goto L66;
            case 17: goto L66;
            case 18: goto L66;
            case 19: goto L66;
            case 20: goto L66;
            case 21: goto L56;
            case 22: goto L48;
            case 23: goto L36;
            case 24: goto L34;
            case 25: goto L32;
            case 26: goto L30;
            case 27: goto L28;
            case 28: goto L26;
            default: goto L18;
        };     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
    L18:
        reduceNumeric(r0);     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L26:
        ExecutionStack r03 = this.stk;     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        r03.push(Float.valueOf(ParseTools.similarity(String.valueOf(r03.pop()), String.valueOf(this.stk.pop()))));     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L28:
        ExecutionStack r04 = this.stk;     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        r04.push(Boolean.valueOf(Soundex.soundex(String.valueOf(r04.pop())).equals(Soundex.soundex(String.valueOf(this.stk.pop())))));     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L30:
        ExecutionStack r05 = this.stk;     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        r05.push(Boolean.valueOf(ParseTools.containsCheck(r05.peek2(), this.stk.pop2())));     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L32:
        ExecutionStack r06 = this.stk;     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        r06.push(Boolean.valueOf(((Class) r06.pop()).isInstance(this.stk.pop())));     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L34:
        ExecutionStack r07 = this.stk;     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        r07.push(Boolean.valueOf(Pattern.compile(String.valueOf(r07.pop())).matcher(String.valueOf(this.stk.pop())).matches()));     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L36:
        Object r08 = this.stk.pop();     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        Object r12 = this.stk.pop();     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        if (PropertyTools.isEmpty(r12) == true) goto L39;
    L43:
        this.stk.clear();     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        ExecutionStack r2 = this.stk;     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        if (PropertyTools.isEmpty(r12) == true) goto L46;
        r08 = r12;
    L46:
        r2.push(r08);     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L39:
        if (PropertyTools.isEmpty(r08) == false) goto L43;
        this.stk.push(null);     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L48:
        Object r09 = this.stk.pop();     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        ExecutionStack r3 = this.stk;     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        if (((Boolean) r3.pop()).booleanValue() == false) goto L51;
    L54:
        r3.push(Boolean.valueOf(r1));     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L51:
        if (((Boolean) r09).booleanValue() == true) goto L54;
        r1 = false;
        goto L54
    L56:
        Object r010 = this.stk.pop();     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        ExecutionStack r32 = this.stk;     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        if (((Boolean) r32.pop()).booleanValue() == true) goto L59;
    L61:
        r1 = false;
    L62:
        r32.push(Boolean.valueOf(r1));     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L59:
        if (((Boolean) r010).booleanValue() == false) goto L61;
    L66:
        this.stk.op(r0);     // Catch: Exception -> L20 ArithmeticException -> L22 ClassCastException -> L24
        return;
    L22:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("arithmetic error: " + e.getMessage(), this.expr, this.st, e);
        return;
    L24:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("syntax error or incompatable types", this.expr, this.st, e);
        return;
    L20:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("failed to subEval expression", this.expr, this.st, e);
    }

    public void setExpression(String r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.isEmpty() == true) goto L35;
        WeakHashMap<String, char[]> r0 = EX_PRECACHE;
        monitor-enter(r0);
        char[] r1 = r0.get(r5);     // Catch: Throwable -> L15
        this.expr = r1;     // Catch: Throwable -> L15
        if (r1 != null) goto L23;
        int r12 = 0;
    L11:
        if (r12 >= this.length) goto L17;
        if (ParseTools.isWhitespace(r5.charAt(r12)) == false) goto L17;
        r12 = r12 + 1;     // Catch: Throwable -> L15
    L17:
        int r2 = r5.length() - 1;
    L18:
        if (r2 <= 0) goto L22;
        if (ParseTools.isWhitespace(r5.charAt(r2)) == false) goto L22;
        r2 = r2 - 1;
    L22:
        char[] r13 = r5.substring(r12, r2 + 1).toCharArray();     // Catch: Throwable -> L15
        this.expr = r13;     // Catch: Throwable -> L15
        int r22 = r13.length;     // Catch: Throwable -> L15
        this.length = r22;     // Catch: Throwable -> L15
        this.end = r22;     // Catch: Throwable -> L15
        EX_PRECACHE.put(r5, r13);     // Catch: Throwable -> L15
    L24:
        monitor-exit(r0);     // Catch: Throwable -> L15
        return;
    L23:
        int r52 = r1.length;     // Catch: Throwable -> L15
        this.length = r52;     // Catch: Throwable -> L15
        this.end = r52;     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        throw th;
    }

    public void setExpression(char[] r2) {
        this.expr = r2;
        int r22 = r2.length;
        this.length = r22;
        this.end = r22;
    L3:
        int r23 = this.start;
        if (r23 >= this.length) goto L8;
        if (ParseTools.isWhitespace(this.expr[r23]) == false) goto L8;
        this.start++;
    L8:
        int r24 = this.length;
        if (r24 == 0) goto L13;
        if (ParseTools.isWhitespace(this.expr[r24 - 1]) == false) goto L19;
        this.length--;
        goto L8
    L19:
        return;
    }

    public void skipWhitespace() {
    L2:
        int r0 = this.cursor;
        int r1 = this.end;
        if (r0 == r1) goto L45;
        char[] r2 = this.expr;
        char r3 = r2[r0];
        if (r3 == '\n') goto L43;
        if (r3 != '\r') goto L9;
    L44:
        this.cursor = r0 + 1;
        goto L2
    L9:
        if (r3 != '/') goto L40;
        if ((r0 + 1) == r1) goto L40;
        char r32 = r2[r0 + 1];
        if (r32 != '*') goto L15;
        int r12 = r1 - 1;
        this.cursor = r0 + 1;
    L27:
        int r22 = this.cursor;
        if (r22 == r12) goto L34;
        char[] r33 = this.expr;
        if (r33[r22] != '*') goto L33;
        if (r33[r22 + 1] == '/') goto L34;
    L33:
        this.cursor = r22 + 1;
    L34:
        if (r22 == r12) goto L37;
        this.cursor = r22 + 2;
    L37:
        if (r0 >= this.cursor) goto L2;
        this.expr[r0] = ' ';
        r0 = r0 + 1;
        goto L37
    L15:
        if (r32 != '/') goto L69;
        this.cursor = r0 + 1;
        r2[r0] = ' ';
    L18:
        int r02 = this.cursor;
        int r13 = this.end;
        if (r02 == r13) goto L23;
        char[] r23 = this.expr;
        if (r23[r02] == '\n') goto L23;
        this.cursor = r02 + 1;
        r23[r02] = ' ';
    L23:
        if (r02 == r13) goto L25;
        this.cursor = r02 + 1;
    L25:
        this.line++;
        this.lastLineStart = this.cursor;
        goto L2
    L69:
        return;
    L40:
        if (ParseTools.isWhitespace(r3) == false) goto L70;
        this.cursor++;
        goto L2
    L70:
        return;
    L43:
        this.line++;
        this.lastLineStart = r0;
        goto L44
    }

    public boolean tokenContinues() {
        int r0 = this.cursor;
        if (r0 != this.end) goto L5;
        return false;
    L5:
        char r02 = this.expr[r0];
        if (r02 != '.') goto L8;
    L20:
        return true;
    L8:
        if (r02 == '[') goto L20;
        if (ParseTools.isWhitespace(r02) == false) goto L19;
        int r03 = this.cursor;
        skipWhitespace();
        int r5 = this.cursor;
        if (r5 == this.end) goto L18;
        char r52 = this.expr[r5];
        if (r52 == '.') goto L17;
        if (r52 != '[') goto L18;
    L17:
        return true;
    L18:
        this.cursor = r03;
    L19:
        return false;
    }

    public int trimLeft(int r3) {
        int r0 = this.end;
        if (r3 <= r0) goto L5;
        r3 = r0;
    L5:
        if (r3 <= 0) goto L13;
        if (r3 < this.st) goto L13;
        int r1 = r3 - 1;
        if (ParseTools.isWhitespace(this.expr[r1]) == true) goto L12;
        if (this.expr[r1] != ';') goto L13;
    L12:
        r3 = r3 - 1;
    L13:
        return r3;
    }

    public int trimRight(int r2) {
    L3:
        if (r2 == this.end) goto L7;
        if (ParseTools.isWhitespace(this.expr[r2]) == false) goto L7;
        r2 = r2 + 1;
    L7:
        return r2;
    }

    public void trimWhitespace() {
    L2:
        int r0 = this.cursor;
        if (r0 == 0) goto L7;
        if (ParseTools.isWhitespace(this.expr[r0 - 1]) == false) goto L10;
        this.cursor--;
        goto L2
    L10:
        return;
    }
}
