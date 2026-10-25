package org.mvel2.templates;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.CompileException;
import org.mvel2.ParserContext;
import org.mvel2.templates.res.CodeNode;
import org.mvel2.templates.res.CommentNode;
import org.mvel2.templates.res.CompiledCodeNode;
import org.mvel2.templates.res.CompiledDeclareNode;
import org.mvel2.templates.res.CompiledEvalNode;
import org.mvel2.templates.res.CompiledExpressionNode;
import org.mvel2.templates.res.CompiledForEachNode;
import org.mvel2.templates.res.CompiledIfNode;
import org.mvel2.templates.res.CompiledIncludeNode;
import org.mvel2.templates.res.CompiledNamedIncludeNode;
import org.mvel2.templates.res.CompiledTerminalExpressionNode;
import org.mvel2.templates.res.DeclareNode;
import org.mvel2.templates.res.EndNode;
import org.mvel2.templates.res.EvalNode;
import org.mvel2.templates.res.ExpressionNode;
import org.mvel2.templates.res.ForEachNode;
import org.mvel2.templates.res.IfNode;
import org.mvel2.templates.res.IncludeNode;
import org.mvel2.templates.res.NamedIncludeNode;
import org.mvel2.templates.res.Node;
import org.mvel2.templates.res.TerminalExpressionNode;
import org.mvel2.templates.res.TerminalNode;
import org.mvel2.templates.res.TextNode;
import org.mvel2.templates.util.TemplateTools;
import org.mvel2.util.ExecutionStack;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class TemplateCompiler {
    private static final Map<String, Integer> OPCODES = null;
    private boolean codeCache;
    private int colStart;
    private int cursor;
    private Map<String, Class<? extends Node>> customNodes;
    private int lastTextRangeEnding;
    private int length;
    private int line;
    private ParserContext parserContext;
    private int start;
    private char[] template;

    static {
        HashMap r0 = new HashMap();
        OPCODES = r0;
        r0.put("if", 1);
        r0.put("else", 2);
        r0.put("elseif", 2);
        r0.put("end", 10);
        r0.put("foreach", 3);
        r0.put("includeNamed", 51);
        r0.put("include", 50);
        r0.put("comment", 52);
        r0.put("code", 53);
        r0.put("eval", 55);
        r0.put("declare", 54);
        r0.put("stop", 70);
    }

    public TemplateCompiler(CharSequence r2) {
        this.codeCache = false;
        char[] r22 = r2.toString().toCharArray();
        this.template = r22;
        this.length = r22.length;
    }

    public TemplateCompiler(CharSequence r2, Map<String, Class<? extends Node>> r3) {
        this.codeCache = false;
        char[] r22 = r2.toString().toCharArray();
        this.template = r22;
        this.length = r22.length;
        this.customNodes = r3;
    }

    public TemplateCompiler(CharSequence r2, Map<String, Class<? extends Node>> r3, boolean r4) {
        this.codeCache = false;
        char[] r22 = r2.toString().toCharArray();
        this.template = r22;
        this.length = r22.length;
        this.customNodes = r3;
        this.codeCache = r4;
    }

    public TemplateCompiler(CharSequence r2, Map<String, Class<? extends Node>> r3, boolean r4, ParserContext r5) {
        this.codeCache = false;
        char[] r22 = r2.toString().toCharArray();
        this.template = r22;
        this.length = r22.length;
        this.customNodes = r3;
        this.codeCache = r4;
        this.parserContext = r5;
    }

    public TemplateCompiler(CharSequence r2, boolean r3) {
        this.codeCache = false;
        char[] r22 = r2.toString().toCharArray();
        this.template = r22;
        this.length = r22.length;
        this.codeCache = r3;
    }

    public TemplateCompiler(CharSequence r2, boolean r3, ParserContext r4) {
        this.codeCache = false;
        char[] r22 = r2.toString().toCharArray();
        this.template = r22;
        this.length = r22.length;
        this.codeCache = r3;
        this.parserContext = r4;
    }

    public TemplateCompiler(String r2) {
        this.codeCache = false;
        char[] r22 = r2.toCharArray();
        this.template = r22;
        this.length = r22.length;
    }

    public TemplateCompiler(String r2, Map<String, Class<? extends Node>> r3) {
        this.codeCache = false;
        char[] r22 = r2.toCharArray();
        this.template = r22;
        this.length = r22.length;
        this.customNodes = r3;
    }

    public TemplateCompiler(String r2, Map<String, Class<? extends Node>> r3, boolean r4) {
        this.codeCache = false;
        char[] r22 = r2.toCharArray();
        this.template = r22;
        this.length = r22.length;
        this.customNodes = r3;
        this.codeCache = r4;
    }

    public TemplateCompiler(String r2, Map<String, Class<? extends Node>> r3, boolean r4, ParserContext r5) {
        this.codeCache = false;
        char[] r22 = r2.toCharArray();
        this.template = r22;
        this.length = r22.length;
        this.customNodes = r3;
        this.codeCache = r4;
        this.parserContext = r5;
    }

    public TemplateCompiler(String r2, boolean r3) {
        this.codeCache = false;
        char[] r22 = r2.toCharArray();
        this.template = r22;
        this.length = r22.length;
        this.codeCache = r3;
    }

    public TemplateCompiler(char[] r2) {
        this.codeCache = false;
        this.template = r2;
        this.length = r2.length;
    }

    public TemplateCompiler(char[] r2, Map<String, Class<? extends Node>> r3) {
        this.codeCache = false;
        this.template = r2;
        this.length = r2.length;
        this.customNodes = r3;
    }

    public TemplateCompiler(char[] r2, Map<String, Class<? extends Node>> r3, boolean r4) {
        this.codeCache = false;
        this.template = r2;
        this.length = r2.length;
        this.customNodes = r3;
        this.codeCache = r4;
    }

    public TemplateCompiler(char[] r2, Map<String, Class<? extends Node>> r3, boolean r4, ParserContext r5) {
        this.codeCache = false;
        this.template = r2;
        this.length = r2.length;
        this.customNodes = r3;
        this.codeCache = r4;
        this.parserContext = r5;
    }

    public TemplateCompiler(char[] r2, boolean r3) {
        this.codeCache = false;
        this.template = r2;
        this.length = r2.length;
        this.codeCache = r3;
    }

    public TemplateCompiler(char[] r2, boolean r3, ParserContext r4) {
        this.codeCache = false;
        this.template = r2;
        this.length = r2.length;
        this.codeCache = r3;
        this.parserContext = r4;
    }

    private char[] capture() {
        int r0 = this.cursor - this.start;
        char[] r1 = new char[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = this.template[this.start + r2];
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private int captureOrbInternal() {
        ParserContext r0 = new ParserContext();     // Catch: CompileException -> L4
        char[] r1 = this.template;     // Catch: CompileException -> L4
        int r2 = this.cursor;     // Catch: CompileException -> L4
        this.start = r2;     // Catch: CompileException -> L4
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(r1, r2, this.length, '{', r0);     // Catch: CompileException -> L4
        this.line += r0.getLineCount();
        int r02 = this.start + 1;     // Catch: CompileException -> L4
        this.start = this.cursor + 1;     // Catch: CompileException -> L4
        return r02;
    L4:
        e = move-exception;
        e.setLineNumber(this.line);
        e.setColumn((this.cursor - this.colStart) + 1);
        throw e;
    }

    private int captureOrbToken() {
        int r0 = this.cursor + 1;
        this.cursor = r0;
    L3:
        int r1 = this.cursor;
        if (r1 == this.length) goto L8;
        if (ParseTools.isIdentifierPart(this.template[r1]) == false) goto L8;
        this.cursor++;
    L8:
        int r12 = this.cursor;
        if (r12 != this.length) goto L11;
        return -1;
    L11:
        if (this.template[r12] != '{') goto L17;
        return r0;
    L17:
        return -1;
    }

    public static CompiledTemplate compileTemplate(File r1) {
        return compileTemplate(r1, ParserContext.create());
    }

    public static CompiledTemplate compileTemplate(File r3, Map<String, Class<? extends Node>> r4) {
        return new TemplateCompiler(TemplateTools.readInFile(r3), r4, true, ParserContext.create()).compile();
    }

    public static CompiledTemplate compileTemplate(File r2, Map<String, Class<? extends Node>> r3, ParserContext r4) {
        return new TemplateCompiler(TemplateTools.readInFile(r2), r3, true, r4).compile();
    }

    public static CompiledTemplate compileTemplate(File r1, ParserContext r2) {
        return compileTemplate(r1, null, r2);
    }

    public static CompiledTemplate compileTemplate(InputStream r1) {
        return compileTemplate(r1, ParserContext.create());
    }

    public static CompiledTemplate compileTemplate(InputStream r3, Map<String, Class<? extends Node>> r4) {
        return new TemplateCompiler(TemplateTools.readStream(r3), r4, true, ParserContext.create()).compile();
    }

    public static CompiledTemplate compileTemplate(InputStream r2, Map<String, Class<? extends Node>> r3, ParserContext r4) {
        return new TemplateCompiler(TemplateTools.readStream(r2), r3, true, r4).compile();
    }

    public static CompiledTemplate compileTemplate(InputStream r1, ParserContext r2) {
        return compileTemplate(r1, null, r2);
    }

    public static CompiledTemplate compileTemplate(CharSequence r3) {
        return new TemplateCompiler(r3, true, ParserContext.create()).compile();
    }

    public static CompiledTemplate compileTemplate(CharSequence r3, Map<String, Class<? extends Node>> r4) {
        return new TemplateCompiler(r3, r4, true, ParserContext.create()).compile();
    }

    public static CompiledTemplate compileTemplate(CharSequence r2, Map<String, Class<? extends Node>> r3, ParserContext r4) {
        return new TemplateCompiler(r2, r3, true, r4).compile();
    }

    public static CompiledTemplate compileTemplate(CharSequence r2, ParserContext r3) {
        return new TemplateCompiler(r2, true, r3).compile();
    }

    public static CompiledTemplate compileTemplate(String r3) {
        return new TemplateCompiler(r3, true, ParserContext.create()).compile();
    }

    public static CompiledTemplate compileTemplate(String r3, Map<String, Class<? extends Node>> r4) {
        return new TemplateCompiler(r3, r4, true, ParserContext.create()).compile();
    }

    public static CompiledTemplate compileTemplate(String r2, Map<String, Class<? extends Node>> r3, ParserContext r4) {
        return new TemplateCompiler(r2, r3, true, r4).compile();
    }

    public static CompiledTemplate compileTemplate(String r2, ParserContext r3) {
        return new TemplateCompiler(r2, true, r3).compile();
    }

    public static CompiledTemplate compileTemplate(char[] r3) {
        return new TemplateCompiler(r3, true, ParserContext.create()).compile();
    }

    public static CompiledTemplate compileTemplate(char[] r3, Map<String, Class<? extends Node>> r4) {
        return new TemplateCompiler(r3, r4, true, ParserContext.create()).compile();
    }

    public static CompiledTemplate compileTemplate(char[] r2, Map<String, Class<? extends Node>> r3, ParserContext r4) {
        return new TemplateCompiler(r2, r3, true, r4).compile();
    }

    public static CompiledTemplate compileTemplate(char[] r2, ParserContext r3) {
        return new TemplateCompiler(r2, true, r3).compile();
    }

    private boolean isNext(char r3) {
        int r0 = this.cursor;
        if (r0 != this.length) goto L5;
        return false;
    L5:
        if (this.template[r0 + 1] != r3) goto L9;
        return true;
    L9:
        return false;
    }

    private Node markTextNode(Node r4) {
        int r0 = r4.getEnd();
        int r1 = this.lastTextRangeEnding;
        if (r0 <= r1) goto L5;
        r1 = r4.getEnd();
    L5:
        int r02 = this.start;
        if (r1 >= r02) goto L9;
        int r03 = r02 - 1;
        this.lastTextRangeEnding = r03;
        TextNode r2 = new TextNode(r1, r03);
        r4.next = r2;
        return r2;
    L9:
        return r4;
    }

    public CompiledTemplate compile() {
        return new CompiledTemplate(this.template, compileFrom(null, new ExecutionStack()));
    }

    public Node compileFrom(Node r19, ExecutionStack r20) {
        this.line = 1;
        if (r19 != null) goto L6;
        Node r5 = new TextNode(0, 0);
    L5:
        Node r6 = r5;
    L145:
        int r8 = this.cursor;     // Catch: RuntimeException -> L20
        if (r8 >= this.length) goto L110;
        char r9 = this.template[r8];     // Catch: RuntimeException -> L20
        if (r9 == '\n') goto L106;
        if (r9 == '$') goto L18;
        if (r9 == '@') goto L18;
    L107:
        this.cursor++;
    L18:
        if (isNext(r9) == false) goto L22;
        int r82 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.cursor = r82;     // Catch: RuntimeException -> L20
        this.start = r82;     // Catch: RuntimeException -> L20
        r5 = markTextNode(r5);     // Catch: RuntimeException -> L20
        r5.setEnd(r5.getEnd() + 1);     // Catch: RuntimeException -> L20
        int r83 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.cursor = r83;     // Catch: RuntimeException -> L20
        this.lastTextRangeEnding = r83;     // Catch: RuntimeException -> L20
        this.start = r83;     // Catch: RuntimeException -> L20
        goto L145
    L22:
        int r84 = captureOrbToken();     // Catch: RuntimeException -> L20
        if (r84 == (-1)) goto L107;
        this.start = r84;     // Catch: RuntimeException -> L20
        Map<String, Integer> r85 = OPCODES;     // Catch: RuntimeException -> L20
        String r13 = new String(capture());     // Catch: RuntimeException -> L20
        Integer r86 = r85.get(r13);     // Catch: RuntimeException -> L20
        if (r86 != null) goto L27;
        int r87 = 0;
    L28:
        if (r87 != 1) goto L30;
        Node r52 = markTextNode(r5);     // Catch: RuntimeException -> L20
        if (this.codeCache == false) goto L104;
        Node r11 = new CompiledIfNode(this.start, r13, this.template, captureOrbInternal(), this.start, this.parserContext);     // Catch: RuntimeException -> L20
    L105:
        r52.next = r11;     // Catch: RuntimeException -> L20
        r20.push(r11);     // Catch: RuntimeException -> L20
        r11.setTerminus(new TerminalNode());     // Catch: RuntimeException -> L20
    L42:
        r5 = r11;
        goto L107
    L104:
        r11 = new IfNode(this.start, r13, this.template, captureOrbInternal(), this.start);     // Catch: RuntimeException -> L20
        goto L105
    L30:
        if (r87 == 2) goto L93;
        if (r87 == 3) goto L87;
        if (r87 == 10) goto L84;
        switch(r87) {
            case 50: goto L79;
            case 51: goto L74;
            case 52: goto L73;
            case 53: goto L68;
            case 54: goto L63;
            case 55: goto L58;
            default: goto L36;
        };     // Catch: RuntimeException -> L20
    L58:
        Node r53 = markTextNode(r5);     // Catch: RuntimeException -> L20
        if (this.codeCache == false) goto L61;
        int r12 = this.start;     // Catch: RuntimeException -> L20
        char[] r14 = this.template;     // Catch: RuntimeException -> L20
        int r15 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r88 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r88;     // Catch: RuntimeException -> L20
        r11 = new CompiledEvalNode(r12, r13, r14, r15, r88, this.parserContext);     // Catch: RuntimeException -> L20
    L62:
        r53.next = r11;     // Catch: RuntimeException -> L20
        goto L42
    L61:
        int r122 = this.start;     // Catch: RuntimeException -> L20
        char[] r142 = this.template;     // Catch: RuntimeException -> L20
        int r152 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r89 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r89;     // Catch: RuntimeException -> L20
        r11 = new EvalNode(r122, r13, r142, r152, r89);     // Catch: RuntimeException -> L20
        goto L62
    L63:
        Node r54 = markTextNode(r5);     // Catch: RuntimeException -> L20
        if (this.codeCache == false) goto L66;
        int r123 = this.start;     // Catch: RuntimeException -> L20
        char[] r143 = this.template;     // Catch: RuntimeException -> L20
        int r153 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r810 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r810;     // Catch: RuntimeException -> L20
        r11 = new CompiledDeclareNode(r123, r13, r143, r153, r810, this.parserContext);     // Catch: RuntimeException -> L20
    L67:
        r54.next = r11;     // Catch: RuntimeException -> L20
        r20.push(r11);     // Catch: RuntimeException -> L20
        r11.setTerminus(new TerminalNode());     // Catch: RuntimeException -> L20
        goto L42
    L66:
        int r124 = this.start;     // Catch: RuntimeException -> L20
        char[] r144 = this.template;     // Catch: RuntimeException -> L20
        int r154 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r811 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r811;     // Catch: RuntimeException -> L20
        r11 = new DeclareNode(r124, r13, r144, r154, r811);     // Catch: RuntimeException -> L20
        goto L67
    L68:
        Node r55 = markTextNode(r5);     // Catch: RuntimeException -> L20
        if (this.codeCache == false) goto L71;
        int r125 = this.start;     // Catch: RuntimeException -> L20
        char[] r145 = this.template;     // Catch: RuntimeException -> L20
        int r155 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r812 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r812;     // Catch: RuntimeException -> L20
        r11 = new CompiledCodeNode(r125, r13, r145, r155, r812, this.parserContext);     // Catch: RuntimeException -> L20
    L72:
        r55.next = r11;     // Catch: RuntimeException -> L20
        goto L42
    L71:
        int r126 = this.start;     // Catch: RuntimeException -> L20
        char[] r146 = this.template;     // Catch: RuntimeException -> L20
        int r156 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r813 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r813;     // Catch: RuntimeException -> L20
        r11 = new CodeNode(r126, r13, r146, r156, r813);     // Catch: RuntimeException -> L20
        goto L72
    L73:
        Node r56 = markTextNode(r5);     // Catch: RuntimeException -> L20
        int r127 = this.start;     // Catch: RuntimeException -> L20
        char[] r147 = this.template;     // Catch: RuntimeException -> L20
        int r157 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r814 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r814;     // Catch: RuntimeException -> L20
        r11 = new CommentNode(r127, r13, r147, r157, r814);     // Catch: RuntimeException -> L20
        r56.next = r11;     // Catch: RuntimeException -> L20
        goto L42
    L74:
        Node r57 = markTextNode(r5);     // Catch: RuntimeException -> L20
        if (this.codeCache == false) goto L77;
        int r128 = this.start;     // Catch: RuntimeException -> L20
        char[] r148 = this.template;     // Catch: RuntimeException -> L20
        int r158 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r815 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r815;     // Catch: RuntimeException -> L20
        r11 = new CompiledNamedIncludeNode(r128, r13, r148, r158, r815, this.parserContext);     // Catch: RuntimeException -> L20
    L78:
        r57.next = r11;     // Catch: RuntimeException -> L20
        goto L42
    L77:
        int r129 = this.start;     // Catch: RuntimeException -> L20
        char[] r149 = this.template;     // Catch: RuntimeException -> L20
        int r159 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r816 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r816;     // Catch: RuntimeException -> L20
        r11 = new NamedIncludeNode(r129, r13, r149, r159, r816);     // Catch: RuntimeException -> L20
        goto L78
    L79:
        Node r58 = markTextNode(r5);     // Catch: RuntimeException -> L20
        if (this.codeCache == false) goto L82;
        int r1210 = this.start;     // Catch: RuntimeException -> L20
        char[] r1410 = this.template;     // Catch: RuntimeException -> L20
        int r1510 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r817 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r817;     // Catch: RuntimeException -> L20
        r11 = new CompiledIncludeNode(r1210, r13, r1410, r1510, r817, this.parserContext);     // Catch: RuntimeException -> L20
    L83:
        r58.next = r11;     // Catch: RuntimeException -> L20
        goto L42
    L82:
        int r1211 = this.start;     // Catch: RuntimeException -> L20
        char[] r1411 = this.template;     // Catch: RuntimeException -> L20
        int r1511 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r818 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r818;     // Catch: RuntimeException -> L20
        r11 = new IncludeNode(r1211, r13, r1411, r1511, r818);     // Catch: RuntimeException -> L20
        goto L83
    L36:
        if (r13.length() != 0) goto L43;
        Node r59 = markTextNode(r5);     // Catch: RuntimeException -> L20
        if (this.codeCache == false) goto L40;
        int r1212 = this.start;     // Catch: RuntimeException -> L20
        char[] r1412 = this.template;     // Catch: RuntimeException -> L20
        int r1512 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r819 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r819;     // Catch: RuntimeException -> L20
        r11 = new CompiledExpressionNode(r1212, r13, r1412, r1512, r819, this.parserContext);     // Catch: RuntimeException -> L20
    L41:
        r59.next = r11;     // Catch: RuntimeException -> L20
        goto L42
    L40:
        int r1213 = this.start;     // Catch: RuntimeException -> L20
        char[] r1413 = this.template;     // Catch: RuntimeException -> L20
        int r1513 = captureOrbInternal();     // Catch: RuntimeException -> L20
        int r820 = this.cursor + 1;     // Catch: RuntimeException -> L20
        this.start = r820;     // Catch: RuntimeException -> L20
        r11 = new ExpressionNode(r1213, r13, r1413, r1513, r820);     // Catch: RuntimeException -> L20
        goto L41
    L43:
        Map<String, Class<? extends Node>> r821 = this.customNodes;     // Catch: RuntimeException -> L20
        if (r821 == null) goto L57;
        if (r821.containsKey(r13) == false) goto L57;
        Class<? extends Node> r822 = this.customNodes.get(r13);     // Catch: RuntimeException -> L20
        Node r510 = markTextNode(r5);     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        Node r92 = r822.newInstance();     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        r510.next = r92;     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        r92.setBegin(this.start);     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        r92.setName(r13);     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        r92.setCStart(captureOrbInternal());     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        int r511 = this.cursor + 1;     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        this.start = r511;     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        r92.setCEnd(r511);     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        r92.setEnd(r92.getCEnd());     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        r92.setContents(ParseTools.subset(this.template, r92.getCStart(), (r92.getCEnd() - r92.getCStart()) - 1));     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
        if (r92.isOpenNode() == false) goto L51;
        r20.push(r92);     // Catch: RuntimeException -> L20 IllegalAccessException -> L52 InstantiationException -> L54
    L51:
        r5 = r92;
        goto L107
    L53:
        throw new RuntimeException("unable to instantiate custom node class: " + r822.getName());     // Catch: RuntimeException -> L20
    L55:
        throw new RuntimeException("unable to instantiate custom node class: " + r822.getName());     // Catch: RuntimeException -> L20
    L57:
        throw new RuntimeException("unknown token type: " + r13);     // Catch: RuntimeException -> L20
    L84:
        Node r512 = markTextNode(r5);     // Catch: RuntimeException -> L20
        Node r823 = (Node) r20.pop();     // Catch: RuntimeException -> L20
        r92 = r823.getTerminus();     // Catch: RuntimeException -> L20
        r92.setCStart(captureOrbInternal());     // Catch: RuntimeException -> L20
        int r10 = this.start;     // Catch: RuntimeException -> L20
        this.lastTextRangeEnding = r10;     // Catch: RuntimeException -> L20
        r92.setEnd(r10 - 1);     // Catch: RuntimeException -> L20
        r92.calculateContents(this.template);     // Catch: RuntimeException -> L20
        if (r823.demarcate(r92, this.template) == false) goto L51;
        r512.next = r92;     // Catch: RuntimeException -> L20
        goto L51
    L87:
        Node r513 = markTextNode(r5);     // Catch: RuntimeException -> L20
        if (this.codeCache == false) goto L90;
        r11 = new CompiledForEachNode(this.start, r13, this.template, captureOrbInternal(), this.start, this.parserContext);     // Catch: RuntimeException -> L20
    L91:
        r513.next = r11;     // Catch: RuntimeException -> L20
        r20.push(r11);     // Catch: RuntimeException -> L20
        r11.setTerminus(new TerminalNode());     // Catch: RuntimeException -> L20
        goto L42
    L90:
        r11 = new ForEachNode(this.start, r13, this.template, captureOrbInternal(), this.start);     // Catch: RuntimeException -> L20
        goto L91
    L93:
        if (r20.isEmpty() == true) goto L107;
        if ((r20.peek() instanceof IfNode) == false) goto L107;
        Node r514 = markTextNode(r5);     // Catch: RuntimeException -> L20
        IfNode r824 = (IfNode) r20.pop();     // Catch: RuntimeException -> L20
        r514.next = r824.getTerminus();     // Catch: RuntimeException -> L20
        r824.demarcate(r824.getTerminus(), this.template);     // Catch: RuntimeException -> L20
        if (this.codeCache == false) goto L99;
        r11 = new CompiledIfNode(this.start, r13, this.template, captureOrbInternal(), this.start, this.parserContext);     // Catch: RuntimeException -> L20
    L100:
        r824.next = r11;     // Catch: RuntimeException -> L20
        r11.setTerminus(r824.getTerminus());     // Catch: RuntimeException -> L20
        r20.push(r11);     // Catch: RuntimeException -> L20
        goto L42
    L99:
        r11 = new IfNode(this.start, r13, this.template, captureOrbInternal(), this.start);     // Catch: RuntimeException -> L20
        goto L100
    L27:
        r87 = r86.intValue();     // Catch: RuntimeException -> L20
        goto L28
    L106:
        this.line++;
        this.colStart = r8 + 1;     // Catch: RuntimeException -> L20
        goto L107
    L110:
        if (r20.isEmpty() == false) goto L133;
        int r0 = this.start;
        char[] r2 = this.template;
        if (r0 >= r2.length) goto L114;
        TextNode r4 = new TextNode(r0, r2.length);
        r5.next = r4;
        r5 = r4;
    L114:
        r5.next = new EndNode();
        Node r02 = r6;
    L116:
        if (r02.getLength() != 0) goto L120;
        r02 = r02.getNext();
        if (r02 != null) goto L116;
    L120:
        if (r02 != null) goto L122;
    L132:
        return r6;
    L122:
        if (r02.getLength() != (this.template.length - 1)) goto L132;
        if ((r02 instanceof ExpressionNode) == true) goto L126;
        return r02;
    L126:
        if (this.codeCache == false) goto L130;
        return new CompiledTerminalExpressionNode(r02, this.parserContext);
    L130:
        return new TerminalExpressionNode(r02);
    L133:
        CompileException r22 = new CompileException("unclosed @" + ((Node) r20.peek()).getName() + "{} block. expected @end{}", this.template, this.cursor);
        r22.setColumn(this.cursor - this.colStart);
        r22.setLineNumber(this.line);
        throw r22;
    L20:
        e = move-exception;
        CompileException r23 = new CompileException(e.getMessage(), this.template, this.cursor, e);
        r23.setExpr(this.template);
        if ((e instanceof CompileException) == false) goto L143;
        CompileException r03 = (CompileException) e;
        if (r03.getCursor() == (-1)) goto L143;
        r23.setCursor(r03.getCursor());
        if (r03.getColumn() != (-1)) goto L142;
        r23.setColumn(r23.getCursor() - this.colStart);
        goto L143
    L142:
        r23.setColumn(r03.getColumn());
    L143:
        r23.setLineNumber(this.line);
        throw r23;
    L6:
        r5 = r19;
        goto L5
    }

    public ParserContext getParserContext() {
        return this.parserContext;
    }
}
