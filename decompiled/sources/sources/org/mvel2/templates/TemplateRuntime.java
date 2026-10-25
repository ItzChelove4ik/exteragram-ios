package org.mvel2.templates;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.templates.res.Node;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.templates.util.TemplateTools;
import org.mvel2.templates.util.io.StandardOutputStream;
import org.mvel2.templates.util.io.StringAppenderStream;
import org.mvel2.templates.util.io.StringBuilderStream;
import org.mvel2.util.ExecutionStack;
import org.mvel2.util.StringAppender;

/* loaded from: classes5.dex */
public class TemplateRuntime {
    private String baseDir;
    private TemplateRegistry namedTemplateRegistry;
    private ExecutionStack relPath;
    private Node rootNode;
    private char[] template;

    public TemplateRuntime(char[] r1, TemplateRegistry r2, Node r3, String r4) {
        this.template = r1;
        this.namedTemplateRegistry = r2;
        this.rootNode = r3;
        this.baseDir = r4;
    }

    public static Object eval(File r0, Object r1, VariableResolverFactory r2, TemplateRegistry r3) {
        return execute(TemplateCompiler.compileTemplate(TemplateTools.readInFile(r0)), r1, r2, r3);
    }

    public static Object eval(InputStream r2) {
        return eval(r2, null, new ImmutableDefaultFactory(), null);
    }

    public static Object eval(InputStream r2, Object r3) {
        return eval(r2, r3, new ImmutableDefaultFactory(), null);
    }

    public static Object eval(InputStream r1, Object r2, Map r3) {
        return eval(r1, r2, new MapVariableResolverFactory(r3), null);
    }

    public static Object eval(InputStream r1, Object r2, Map r3, TemplateRegistry r4) {
        return execute(TemplateCompiler.compileTemplate(TemplateTools.readStream(r1)), r2, new MapVariableResolverFactory(r3), r4);
    }

    public static Object eval(InputStream r1, Object r2, VariableResolverFactory r3) {
        return eval(r1, r2, r3, null);
    }

    public static Object eval(InputStream r0, Object r1, VariableResolverFactory r2, TemplateRegistry r3) {
        return execute(TemplateCompiler.compileTemplate(TemplateTools.readStream(r0)), r1, r2, r3);
    }

    public static Object eval(String r0, Object r1) {
        return execute(TemplateCompiler.compileTemplate(r0), r1);
    }

    public static Object eval(String r1, Object r2, Map r3) {
        return execute(TemplateCompiler.compileTemplate(r1), r2, new MapVariableResolverFactory(r3));
    }

    public static Object eval(String r1, Object r2, Map r3, TemplateRegistry r4) {
        return execute(TemplateCompiler.compileTemplate(r1), r2, new MapVariableResolverFactory(r3), r4);
    }

    public static Object eval(String r0, Object r1, VariableResolverFactory r2) {
        return execute(TemplateCompiler.compileTemplate(r0), r1, r2);
    }

    public static Object eval(String r0, Object r1, VariableResolverFactory r2, TemplateRegistry r3) {
        return execute(TemplateCompiler.compileTemplate(r0), r1, r2, r3);
    }

    public static Object eval(String r1, Map r2) {
        return execute(TemplateCompiler.compileTemplate(r1), null, new MapVariableResolverFactory(r2));
    }

    public static Object eval(String r1, Map r2, TemplateRegistry r3) {
        return execute(TemplateCompiler.compileTemplate(r1), null, new MapVariableResolverFactory(r2), r3);
    }

    public static void eval(InputStream r0, Object r1, VariableResolverFactory r2, TemplateRegistry r3, OutputStream r4) {
        execute(TemplateCompiler.compileTemplate(TemplateTools.readStream(r0)), r1, r2, r3, r4);
    }

    public static void eval(String r1, Object r2, Map r3, OutputStream r4) {
        execute(TemplateCompiler.compileTemplate(r1), r2, new MapVariableResolverFactory(r3), null, r4);
    }

    public static void eval(String r1, Object r2, Map r3, TemplateRegistry r4, OutputStream r5) {
        execute(TemplateCompiler.compileTemplate(r1), r2, new MapVariableResolverFactory(r3), r4, r5);
    }

    public static void eval(String r1, Object r2, VariableResolverFactory r3, OutputStream r4) {
        execute(TemplateCompiler.compileTemplate(r1), r2, r3, null, r4);
    }

    public static void eval(String r0, Object r1, VariableResolverFactory r2, TemplateRegistry r3, OutputStream r4) {
        execute(TemplateCompiler.compileTemplate(r0), r1, r2, r3, r4);
    }

    public static void eval(String r0, Object r1, VariableResolverFactory r2, TemplateRegistry r3, TemplateOutputStream r4) {
        execute(TemplateCompiler.compileTemplate(r0), r1, r2, r3, r4);
    }

    public static void eval(String r1, Object r2, VariableResolverFactory r3, TemplateOutputStream r4) {
        execute(TemplateCompiler.compileTemplate(r1), r2, r3, null, r4);
    }

    public static void eval(String r1, Map r2, OutputStream r3) {
        execute(TemplateCompiler.compileTemplate(r1), null, new MapVariableResolverFactory(r2), null, r3);
    }

    public static void eval(String r1, Map r2, TemplateRegistry r3, OutputStream r4) {
        execute(TemplateCompiler.compileTemplate(r1), null, new MapVariableResolverFactory(r2), r3, r4);
    }

    public static void eval(String r1, Map r2, TemplateRegistry r3, TemplateOutputStream r4) {
        execute(TemplateCompiler.compileTemplate(r1), null, new MapVariableResolverFactory(r2), r3, r4);
    }

    public static Object execute(CompiledTemplate r6) {
        return execute(r6.getRoot(), r6.getTemplate(), new StringAppender(), null, new ImmutableDefaultFactory(), null);
    }

    public static Object execute(CompiledTemplate r6, Object r7) {
        return execute(r6.getRoot(), r6.getTemplate(), new StringAppender(), r7, new ImmutableDefaultFactory(), null);
    }

    public static Object execute(CompiledTemplate r6, Object r7, Map r8) {
        return execute(r6.getRoot(), r6.getTemplate(), new StringBuilder(), r7, new MapVariableResolverFactory(r8), null);
    }

    public static Object execute(CompiledTemplate r6, Object r7, Map r8, TemplateRegistry r9) {
        return execute(r6.getRoot(), r6.getTemplate(), new StringBuilder(), r7, new MapVariableResolverFactory(r8), r9);
    }

    public static Object execute(CompiledTemplate r6, Object r7, VariableResolverFactory r8) {
        return execute(r6.getRoot(), r6.getTemplate(), new StringBuilder(), r7, r8, null);
    }

    public static Object execute(CompiledTemplate r7, Object r8, VariableResolverFactory r9, String r10) {
        return execute(r7.getRoot(), r7.getTemplate(), new StringBuilder(), r8, r9, null, r10);
    }

    public static Object execute(CompiledTemplate r6, Object r7, VariableResolverFactory r8, TemplateRegistry r9) {
        return execute(r6.getRoot(), r6.getTemplate(), new StringBuilder(), r7, r8, r9);
    }

    public static Object execute(CompiledTemplate r6, Object r7, VariableResolverFactory r8, TemplateRegistry r9, OutputStream r10) {
        return execute(r6.getRoot(), r6.getTemplate(), new StandardOutputStream(r10), r7, r8, r9);
    }

    public static Object execute(CompiledTemplate r7, Object r8, VariableResolverFactory r9, TemplateRegistry r10, String r11) {
        return execute(r7.getRoot(), r7.getTemplate(), new StringBuilder(), r8, r9, r10, r11);
    }

    public static Object execute(CompiledTemplate r6, Object r7, VariableResolverFactory r8, TemplateRegistry r9, TemplateOutputStream r10) {
        return execute(r6.getRoot(), r6.getTemplate(), r10, r7, r8, r9);
    }

    public static Object execute(CompiledTemplate r7, Object r8, VariableResolverFactory r9, TemplateRegistry r10, TemplateOutputStream r11, String r12) {
        return execute(r7.getRoot(), r7.getTemplate(), r11, r8, r9, r10, r12);
    }

    public static Object execute(CompiledTemplate r6, Object r7, TemplateRegistry r8) {
        return execute(r6.getRoot(), r6.getTemplate(), new StringBuilder(), r7, null, r8);
    }

    public static Object execute(CompiledTemplate r6, Map r7) {
        return execute(r6.getRoot(), r6.getTemplate(), new StringBuilder(), null, new MapVariableResolverFactory(r7), null);
    }

    public static Object execute(Node r2, char[] r3, StringBuilder r4, Object r5, VariableResolverFactory r6, TemplateRegistry r7) {
        return new TemplateRuntime(r3, r7, r2, ".").execute(r4, r5, r6);
    }

    public static Object execute(Node r1, char[] r2, StringBuilder r3, Object r4, VariableResolverFactory r5, TemplateRegistry r6, String r7) {
        return new TemplateRuntime(r2, r6, r1, r7).execute(r3, r4, r5);
    }

    public static Object execute(Node r2, char[] r3, TemplateOutputStream r4, Object r5, VariableResolverFactory r6, TemplateRegistry r7) {
        return new TemplateRuntime(r3, r7, r2, ".").execute(r4, r5, r6);
    }

    public static Object execute(Node r1, char[] r2, TemplateOutputStream r3, Object r4, VariableResolverFactory r5, TemplateRegistry r6, String r7) {
        return new TemplateRuntime(r2, r6, r1, r7).execute(r3, r4, r5);
    }

    public static Object execute(Node r2, char[] r3, StringAppender r4, Object r5, VariableResolverFactory r6, TemplateRegistry r7) {
        return new TemplateRuntime(r3, r7, r2, ".").execute(r4, r5, r6);
    }

    public static void execute(CompiledTemplate r6, OutputStream r7) {
        execute(r6.getRoot(), r6.getTemplate(), new StandardOutputStream(r7), null, new ImmutableDefaultFactory(), null);
    }

    public static void execute(CompiledTemplate r6, Object r7, OutputStream r8) {
        execute(r6.getRoot(), r6.getTemplate(), new StandardOutputStream(r8), r7, new ImmutableDefaultFactory(), null);
    }

    public static void execute(CompiledTemplate r6, Object r7, Map r8, OutputStream r9) {
        execute(r6.getRoot(), r6.getTemplate(), new StandardOutputStream(r9), r7, new MapVariableResolverFactory(r8), null);
    }

    public static void execute(CompiledTemplate r6, Object r7, Map r8, TemplateRegistry r9, OutputStream r10) {
        execute(r6.getRoot(), r6.getTemplate(), new StandardOutputStream(r10), r7, new MapVariableResolverFactory(r8), r9);
    }

    public static void execute(CompiledTemplate r6, Object r7, VariableResolverFactory r8, OutputStream r9) {
        execute(r6.getRoot(), r6.getTemplate(), new StandardOutputStream(r9), r7, r8, null);
    }

    public static void execute(CompiledTemplate r7, Object r8, VariableResolverFactory r9, OutputStream r10, String r11) {
        execute(r7.getRoot(), r7.getTemplate(), new StandardOutputStream(r10), r8, r9, null, r11);
    }

    public static void execute(CompiledTemplate r6, Object r7, TemplateRegistry r8, OutputStream r9) {
        execute(r6.getRoot(), r6.getTemplate(), new StandardOutputStream(r9), r7, null, r8);
    }

    public static void execute(CompiledTemplate r6, Map r7, OutputStream r8) {
        execute(r6.getRoot(), r6.getTemplate(), new StandardOutputStream(r8), null, new MapVariableResolverFactory(r7), null);
    }

    public Object execute(StringBuilder r2, Object r3, VariableResolverFactory r4) {
        return execute(new StringBuilderStream(r2), r3, r4);
    }

    public Object execute(TemplateOutputStream r2, Object r3, VariableResolverFactory r4) {
        return this.rootNode.eval(this, r2, r3, r4);
    }

    public Object execute(StringAppender r2, Object r3, VariableResolverFactory r4) {
        return execute(new StringAppenderStream(r2), r3, r4);
    }

    public TemplateRegistry getNamedTemplateRegistry() {
        return this.namedTemplateRegistry;
    }

    public ExecutionStack getRelPath() {
        if (this.relPath != null) goto L6;
        ExecutionStack r0 = new ExecutionStack();
        this.relPath = r0;
        r0.push(this.baseDir);
    L6:
        return this.relPath;
    }

    public Node getRootNode() {
        return this.rootNode;
    }

    public char[] getTemplate() {
        return this.template;
    }

    public void setNamedTemplateRegistry(TemplateRegistry r1) {
        this.namedTemplateRegistry = r1;
    }

    public void setRootNode(Node r1) {
        this.rootNode = r1;
    }

    public void setTemplate(char[] r1) {
        this.template = r1;
    }
}
