package org.mvel2;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.mvel2.ast.Function;
import org.mvel2.ast.LineLabel;
import org.mvel2.ast.Proto;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.Parser;
import org.mvel2.integration.Interceptor;
import org.mvel2.util.LineMapper;
import org.mvel2.util.MethodStub;
import org.mvel2.util.ReflectionUtil;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class ParserContext implements Serializable {
    private boolean blockSymbols;
    private boolean compiled;
    private transient Map<String, CompiledExpression> compiledExpressionCache;
    private boolean debugSymbols;
    private transient List<ErrorDetail> errorList;
    private Object evaluationContext;
    private boolean executableCodeReached;
    private boolean fatalError;
    private boolean functionContext;
    private HashMap<String, Function> globalFunctions;
    private boolean indexAllocation;
    private ArrayList<String> indexedInputs;
    private ArrayList<String> indexedLocals;
    private Map<String, Class> inputs;
    private LineLabel lastLineLabel;
    private transient Type[] lastTypeParameters;
    private int lineCount;
    private int lineOffset;
    private boolean optimizationMode;
    private ParserContext parent;
    private ParserConfiguration parserConfiguration;
    private boolean retainParserState;
    private transient Map<String, Class> returnTypeCache;
    private transient Parser rootParser;
    private String sourceFile;
    private transient Map<String, LineMapper.LineLookup> sourceLineLookups;
    private boolean strictTypeEnforcement;
    private boolean strongTyping;
    private transient HashMap<String, Map<String, Type>> typeParameters;
    private ArrayList<Set<String>> variableVisibility;
    private HashMap<String, Class> variables;
    protected boolean variablesEscape;
    private transient Map<String, Set<Integer>> visitedLines;

    /* renamed from: org.mvel2.ParserContext$1, reason: invalid class name */
    public class AnonymousClass1 extends ParserContext {
        final /* synthetic */ ParserContext this$0;

        public AnonymousClass1(ParserContext r1, ParserConfiguration r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.mvel2.ParserContext
        public void addVariable(String r2, Class r3) {
            if (ParserContext.access$100(ParserContext.access$000(this.this$0)) == null) goto L7;
            if (ParserContext.access$100(ParserContext.access$000(this.this$0)).containsKey(r2) == false) goto L7;
        L10:
            this.variablesEscape = true;
        L11:
            super.addVariable(r2, r3);
            return;
        L7:
            if (ParserContext.access$200(ParserContext.access$000(this.this$0)) == null) goto L11;
            if (ParserContext.access$200(ParserContext.access$000(this.this$0)).containsKey(r2) == false) goto L11;
            goto L10
        }

        @Override // org.mvel2.ParserContext
        public void addVariable(String r2, Class r3, boolean r4) {
            if (ParserContext.access$100(ParserContext.access$000(this.this$0)) == null) goto L7;
            if (ParserContext.access$100(ParserContext.access$000(this.this$0)).containsKey(r2) == false) goto L7;
        L10:
            this.variablesEscape = true;
        L11:
            super.addVariable(r2, r3, r4);
            return;
        L7:
            if (ParserContext.access$200(ParserContext.access$000(this.this$0)) == null) goto L11;
            if (ParserContext.access$200(ParserContext.access$000(this.this$0)).containsKey(r2) == false) goto L11;
            goto L10
        }

        @Override // org.mvel2.ParserContext
        public Class getVarOrInputType(String r2) {
            if (ParserContext.access$100(ParserContext.access$000(this.this$0)) == null) goto L7;
            if (ParserContext.access$100(ParserContext.access$000(this.this$0)).containsKey(r2) == false) goto L7;
        L10:
            this.variablesEscape = true;
        L12:
            return super.getVarOrInputType(r2);
        L7:
            if (ParserContext.access$200(ParserContext.access$000(this.this$0)) == null) goto L12;
            if (ParserContext.access$200(ParserContext.access$000(this.this$0)).containsKey(r2) == false) goto L12;
            goto L10
        }
    }

    public ParserContext() {
        this.lineCount = 1;
        this.functionContext = false;
        this.compiled = false;
        this.strictTypeEnforcement = false;
        this.strongTyping = false;
        this.optimizationMode = false;
        this.fatalError = false;
        this.retainParserState = false;
        this.debugSymbols = false;
        this.blockSymbols = false;
        this.executableCodeReached = false;
        this.indexAllocation = false;
        this.variablesEscape = false;
        this.parserConfiguration = new ParserConfiguration();
    }

    public ParserContext(Map<String, Object> r2, Map<String, Interceptor> r3, String r4) {
        this.lineCount = 1;
        this.functionContext = false;
        this.compiled = false;
        this.strictTypeEnforcement = false;
        this.strongTyping = false;
        this.optimizationMode = false;
        this.fatalError = false;
        this.retainParserState = false;
        this.debugSymbols = false;
        this.blockSymbols = false;
        this.executableCodeReached = false;
        this.indexAllocation = false;
        this.variablesEscape = false;
        this.sourceFile = r4;
        this.parserConfiguration = new ParserConfiguration(r2, r3);
    }

    public ParserContext(ParserConfiguration r2) {
        this.lineCount = 1;
        this.functionContext = false;
        this.compiled = false;
        this.strictTypeEnforcement = false;
        this.strongTyping = false;
        this.optimizationMode = false;
        this.fatalError = false;
        this.retainParserState = false;
        this.debugSymbols = false;
        this.blockSymbols = false;
        this.executableCodeReached = false;
        this.indexAllocation = false;
        this.variablesEscape = false;
        this.parserConfiguration = r2;
    }

    public ParserContext(ParserConfiguration r1, Object r2) {
        this(r1);
        this.evaluationContext = r2;
    }

    public ParserContext(ParserConfiguration r1, ParserContext r2, boolean r3) {
        this(r1);
        this.parent = r2;
        this.functionContext = r3;
    }

    public ParserContext(Parser r1) {
        this();
        this.rootParser = r1;
    }

    public ParserContext(boolean r1) {
        this();
        this.debugSymbols = r1;
    }

    public static /* synthetic */ ParserContext access$000(ParserContext r0) {
        return r0.parent;
    }

    public static /* synthetic */ HashMap access$100(ParserContext r0) {
        return r0.variables;
    }

    public static /* synthetic */ Map access$200(ParserContext r0) {
        return r0.inputs;
    }

    public static ParserContext create() {
        return new ParserContext();
    }

    private void initIndexedVariables() {
        if (this.indexedInputs != null) goto L6;
        this.indexedInputs = new ArrayList();
    L6:
        if (this.indexedLocals != null) goto L9;
        this.indexedLocals = new ArrayList();
        return;
    }

    private void initVariableVisibility() {
        if (this.variableVisibility != null) goto L6;
        this.variableVisibility = new ArrayList();
        return;
    }

    public void addError(ErrorDetail r5) {
        List<ErrorDetail> r0 = this.errorList;
        if (r0 != null) goto L5;
        this.errorList = new ArrayList();
    L16:
        if (r5.isCritical() == false) goto L18;
        this.fatalError = true;
    L18:
        this.errorList.add(r5);
        return;
    L5:
        Iterator<ErrorDetail> r02 = r0.iterator();
    L7:
        if (r02.hasNext() == false) goto L16;
        ErrorDetail r1 = r02.next();
        if (r1.getMessage().equals(r5.getMessage()) == false) goto L7;
        if (r1.getColumn() != r5.getColumn()) goto L7;
        if (r1.getLineNumber() != r5.getLineNumber()) goto L7;
    }

    public void addImport(Class r2) {
        addImport(r2.getSimpleName(), r2);
    }

    public void addImport(String r1, Class r2) {
        this.parserConfiguration.addImport(r1, r2);
    }

    public void addImport(String r2, Method r3) {
        addImport(r2, new MethodStub(r3));
    }

    public void addImport(String r1, MethodStub r2) {
        this.parserConfiguration.addImport(r1, r2);
    }

    public void addImport(Proto r2) {
        this.parserConfiguration.addImport(r2.getName(), r2);
    }

    public void addIndexedInput(String r2) {
        initIndexedVariables();
        if (this.indexedInputs.contains(r2) == true) goto L6;
        this.indexedInputs.add(r2);
        return;
    }

    public void addIndexedInput(String[] r5) {
        initIndexedVariables();
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        String r2 = r5[r1];
        if (this.indexedInputs.contains(r2) == true) goto L7;
        this.indexedInputs.add(r2);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public void addIndexedInputs(Collection<String> r3) {
        if (r3 == null) goto L10;
        initIndexedVariables();
        Iterator<String> r32 = r3.iterator();
    L6:
        if (r32.hasNext() == false) goto L16;
        String r0 = r32.next();
        if (this.indexedInputs.contains(r0) == true) goto L6;
        this.indexedInputs.add(r0);
        goto L6
    L16:
        return;
    }

    public void addIndexedLocals(Collection<String> r3) {
        if (r3 == null) goto L10;
        initIndexedVariables();
        Iterator<String> r32 = r3.iterator();
    L6:
        if (r32.hasNext() == false) goto L16;
        String r0 = r32.next();
        if (this.indexedLocals.contains(r0) == true) goto L6;
        this.indexedLocals.add(r0);
        goto L6
    L16:
        return;
    }

    public void addIndexedLocals(String[] r5) {
        initIndexedVariables();
        ArrayList<String> r52 = this.indexedLocals;
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        String r2 = r52.get(r1);
        r1 = r1 + 1;
        String r22 = r2;
        if (this.indexedLocals.contains(r22) == true) goto L3;
        this.indexedLocals.add(r22);
        goto L3
    }

    public void addInput(String r2, Class r3) {
        if (this.inputs != null) goto L6;
        this.inputs = new LinkedHashMap();
    L6:
        if (this.inputs.containsKey(r2) == true) goto L16;
        HashMap<String, Class> r0 = this.variables;
        if (r0 != null) goto L10;
    L12:
        if (r3 != null) goto L14;
        r3 = Object.class;
    L14:
        this.inputs.put(r2, r3);
        return;
    L10:
        if (r0.containsKey(r2) == false) goto L12;
        return;
    }

    public void addInput(String r3, Class r4, Class[] r5) {
        if (r4 != null) goto L4;
        r4 = Object.class;
    L4:
        addInput(r3, r4);
        if (this.typeParameters != null) goto L8;
        this.typeParameters = new LinkedHashMap();
    L8:
        if (this.typeParameters.get(r3) != null) goto L10;
        this.typeParameters.put(r3, new LinkedHashMap());
    L10:
        Map<String, Type> r2 = this.typeParameters.get(r3);
        if (r5.length != r4.getTypeParameters().length) goto L17;
        TypeVariable[] r32 = r4.getTypeParameters();
        int r42 = 0;
    L14:
        if (r42 >= r5.length) goto L16;
        r2.put(r32[r42].getName(), r5[r42]);
        r42 = r42 + 1;
        goto L14
    L16:
        return;
    L17:
        GlShader$$ExternalSyntheticBUOutline1.m("wrong number of type parameters for: ".concat(r4.getName()));
    }

    public void addInputs(Map<String, Class> r3) {
        if (r3 == null) goto L8;
        Iterator<Map.Entry<String, Class>> r32 = r3.entrySet().iterator();
    L6:
        if (r32.hasNext() == false) goto L10;
        Map.Entry<String, Class> r0 = r32.next();
        addInput(r0.getKey(), r0.getValue());
        goto L6
    L10:
        return;
    }

    public void addPackageImport(String r1) {
        this.parserConfiguration.addPackageImport(r1);
    }

    public void addTypeParameters(Map<String, Map<String, Type>> r6) {
        if (r6 != null) goto L5;
        return;
    L5:
        if (this.typeParameters != null) goto L7;
        r6 = new HashMap();
    L7:
        Iterator<Map.Entry<String, Map<String, Type>>> r5 = r6.entrySet().iterator();
    L9:
        if (r5.hasNext() == false) goto L18;
        Map.Entry<String, Map<String, Type>> r0 = r5.next();
        HashMap r1 = new HashMap();
        Iterator<Map.Entry<String, Type>> r2 = r0.getValue().entrySet().iterator();
    L12:
        if (r2.hasNext() == false) goto L14;
        Map.Entry<String, Type> r3 = r2.next();
        r1.put(r3.getKey(), r3.getValue());
        goto L12
    L14:
        r6.put(r0.getKey(), r1);
        goto L9
    }

    public void addVariable(String r2, Class r3) {
        initializeTables();
        if (this.variables.containsKey(r2) == false) goto L5;
        return;
    L5:
        if (this.inputs.containsKey(r2) == true) goto L12;
        if (r3 != null) goto L9;
        r3 = Object.class;
    L9:
        this.variables.put(r2, r3);
        makeVisible(r2);
        return;
    }

    public void addVariable(String r2, Class r3, boolean r4) {
        initializeTables();
        if (this.variables.containsKey(r2) == false) goto L8;
        if (r4 == false) goto L8;
        MVEL$$ExternalSyntheticBUOutline0.m("statically-typed variable already defined in scope: ", r2);
        return;
    L8:
        if (r3 != null) goto L10;
        r3 = Object.class;
    L10:
        this.variables.put(r2, r3);
        makeVisible(r2);
    }

    public void addVariables(Map<String, Class> r3) {
        if (r3 == null) goto L8;
        initializeTables();
        Iterator<Map.Entry<String, Class>> r32 = r3.entrySet().iterator();
    L6:
        if (r32.hasNext() == false) goto L10;
        Map.Entry<String, Class> r0 = r32.next();
        addVariable(r0.getKey(), r0.getValue());
        goto L6
    L10:
        return;
    }

    public ParserContext createColoringSubcontext() {
        if (this.parent == null) goto L6;
        AnonymousClass1 r0 = new AnonymousClass1(this, this.parserConfiguration);
        r0.initializeTables();
        r0.sourceFile = this.sourceFile;
        r0.inputs = this.inputs;
        r0.variables = this.variables;
        r0.indexedInputs = this.indexedInputs;
        r0.typeParameters = this.typeParameters;
        r0.sourceLineLookups = this.sourceLineLookups;
        r0.lastLineLabel = this.lastLineLabel;
        r0.variableVisibility = this.variableVisibility;
        r0.globalFunctions = this.globalFunctions;
        r0.lastTypeParameters = this.lastTypeParameters;
        r0.errorList = this.errorList;
        r0.rootParser = this.rootParser;
        r0.lineCount = this.lineCount;
        r0.lineOffset = this.lineOffset;
        r0.compiled = this.compiled;
        r0.strictTypeEnforcement = this.strictTypeEnforcement;
        r0.strongTyping = this.strongTyping;
        r0.fatalError = this.fatalError;
        r0.retainParserState = this.retainParserState;
        r0.debugSymbols = this.debugSymbols;
        r0.blockSymbols = this.blockSymbols;
        r0.executableCodeReached = this.executableCodeReached;
        r0.indexAllocation = this.indexAllocation;
        return r0;
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("create a subContext first");
        return null;
    }

    public ParserContext createSubcontext() {
        ParserContext r0 = new ParserContext(this.parserConfiguration);
        r0.sourceFile = this.sourceFile;
        r0.parent = this;
        r0.addInputs(this.inputs);
        r0.addVariables(this.variables);
        r0.addIndexedInputs(this.indexedInputs);
        r0.addTypeParameters(this.typeParameters);
        r0.sourceLineLookups = this.sourceLineLookups;
        r0.lastLineLabel = this.lastLineLabel;
        r0.variableVisibility = this.variableVisibility;
        r0.globalFunctions = this.globalFunctions;
        r0.lastTypeParameters = this.lastTypeParameters;
        r0.errorList = this.errorList;
        r0.rootParser = this.rootParser;
        r0.lineCount = this.lineCount;
        r0.lineOffset = this.lineOffset;
        r0.compiled = this.compiled;
        r0.strictTypeEnforcement = this.strictTypeEnforcement;
        r0.strongTyping = this.strongTyping;
        r0.fatalError = this.fatalError;
        r0.retainParserState = this.retainParserState;
        r0.debugSymbols = this.debugSymbols;
        r0.blockSymbols = this.blockSymbols;
        r0.executableCodeReached = this.executableCodeReached;
        r0.indexAllocation = this.indexAllocation;
        return r0;
    }

    public void declareFunction(Function r2) {
        if (this.globalFunctions != null) goto L5;
        this.globalFunctions = new LinkedHashMap();
    L5:
        this.globalFunctions.put(r2.getName(), r2);
    }

    public ClassLoader getClassLoader() {
        return this.parserConfiguration.getClassLoader();
    }

    public Map<String, CompiledExpression> getCompiledExpressionCache() {
        if (this.compiledExpressionCache != null) goto L6;
        this.compiledExpressionCache = new HashMap();
    L6:
        return this.compiledExpressionCache;
    }

    public List<ErrorDetail> getErrorList() {
        List<ErrorDetail> r0 = this.errorList;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return Collections.EMPTY_LIST;
    }

    public Object getEvaluationContext() {
        return this.evaluationContext;
    }

    public Function getFunction(String r1) {
        HashMap<String, Function> r0 = this.globalFunctions;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get(r1);
    }

    public Map getFunctions() {
        HashMap<String, Function> r0 = this.globalFunctions;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return Collections.EMPTY_MAP;
    }

    public Class getImport(String r1) {
        return this.parserConfiguration.getImport(r1);
    }

    public Map<String, Object> getImports() {
        return this.parserConfiguration.getImports();
    }

    public ArrayList<String> getIndexedInputs() {
        initIndexedVariables();
        return this.indexedInputs;
    }

    public String[] getIndexedVarNames() {
        ArrayList<String> r0 = this.indexedInputs;
        if (r0 == null) goto L5;
        String[] r02 = new String[r0.size()];
        this.indexedInputs.toArray(r02);
        return r02;
    L5:
        return new String[0];
    }

    public Map<String, Class> getInputs() {
        return this.inputs;
    }

    public Map<String, Interceptor> getInterceptors() {
        return this.parserConfiguration.getInterceptors();
    }

    public LineLabel getLastLineLabel() {
        return this.lastLineLabel;
    }

    public Type[] getLastTypeParameters() {
        return this.lastTypeParameters;
    }

    public int getLineCount() {
        return this.lineCount;
    }

    public int getLineFor(String r2, int r3) {
        Map<String, LineMapper.LineLookup> r0 = this.sourceLineLookups;
        if (r0 != null) goto L5;
        return -1;
    L5:
        if (r0.containsKey(r2) == true) goto L7;
        return -1;
    L7:
        return this.sourceLineLookups.get(r2).getLineFromCursor(r3);
    }

    public int getLineOffset() {
        return this.lineOffset;
    }

    public ParserConfiguration getParserConfiguration() {
        return this.parserConfiguration;
    }

    public Proto getProtoImport(String r1) {
        return (Proto) this.parserConfiguration.getImports().get(r1);
    }

    public Map<String, Class> getReturnTypeCache() {
        if (this.returnTypeCache != null) goto L6;
        this.returnTypeCache = new HashMap();
    L6:
        return this.returnTypeCache;
    }

    public Parser getRootParser() {
        return this.rootParser;
    }

    public String getSourceFile() {
        return this.sourceFile;
    }

    public MethodStub getStaticImport(String r1) {
        return this.parserConfiguration.getStaticImport(r1);
    }

    public Object getStaticOrClassImport(String r1) {
        return this.parserConfiguration.getStaticOrClassImport(r1);
    }

    public Map<String, Type> getTypeParameters(String r1) {
        HashMap<String, Map<String, Type>> r0 = this.typeParameters;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get(r1);
    }

    public Type[] getTypeParametersAsArray(String r4) {
        HashMap<String, Class> r0 = this.variables;
        if (r0 != null) goto L5;
    L8:
        Class r02 = this.inputs.get(r4);
    L7:
        Class r03 = r02;
        if (r03 != null) goto L12;
        return null;
    L12:
        TypeVariable[] r04 = r03.getTypeParameters();
        Type[] r2 = new Type[r04.length];
        Map<String, Type> r3 = getTypeParameters(r4);
        if (r3 != null) goto L15;
        return null;
    L15:
        int r42 = 0;
    L17:
        if (r42 >= r04.length) goto L19;
        r2[r42] = r3.get(r04[r42].toString());
        r42 = r42 + 1;
        goto L17
    L19:
        return r2;
    L5:
        if (r0.containsKey(r4) == false) goto L8;
        r02 = this.variables.get(r4);
        goto L7
    }

    public Class getVarOrInputType(String r2) {
        HashMap<String, Class> r0 = this.variables;
        if (r0 != null) goto L5;
    L8:
        Map<String, Class> r02 = this.inputs;
        if (r02 != null) goto L11;
        return Object.class;
    L11:
        if (r02.containsKey(r2) == true) goto L13;
        return Object.class;
    L13:
        return this.inputs.get(r2);
    L5:
        if (r0.containsKey(r2) == false) goto L8;
        return this.variables.get(r2);
    }

    public Class getVarOrInputTypeOrNull(String r2) {
        HashMap<String, Class> r0 = this.variables;
        if (r0 != null) goto L5;
    L8:
        Map<String, Class> r02 = this.inputs;
        if (r02 != null) goto L11;
        return null;
    L11:
        if (r02.containsKey(r2) == true) goto L13;
        return null;
    L13:
        return this.inputs.get(r2);
    L5:
        if (r0.containsKey(r2) == false) goto L8;
        return this.variables.get(r2);
    }

    public Set<String> getVariableScope() {
        ArrayList<Set<String>> r0 = this.variableVisibility;
        if (r0 != null) goto L5;
    L8:
        GlShader$$ExternalSyntheticBUOutline1.m("no context");
        return null;
    L5:
        if (r0.isEmpty() == true) goto L8;
        return this.variableVisibility.get(r1.size() - 1);
    }

    public HashMap<String, Class> getVariables() {
        return this.variables;
    }

    public boolean hasFunction() {
        HashMap<String, Function> r0 = this.globalFunctions;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.size() == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasFunction(String r1) {
        HashMap<String, Function> r0 = this.globalFunctions;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.containsKey(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasImport(String r1) {
        return this.parserConfiguration.hasImport(r1);
    }

    public boolean hasImports() {
        return this.parserConfiguration.hasImports();
    }

    public boolean hasIndexedInputs() {
        ArrayList<String> r0 = this.indexedInputs;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.size() == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasProtoImport(String r1) {
        Object r0 = this.parserConfiguration.getImports().get(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0 instanceof Proto) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasVarOrInput(String r2) {
        HashMap<String, Class> r0 = this.variables;
        if (r0 != null) goto L5;
    L6:
        Map<String, Class> r1 = this.inputs;
        if (r1 != null) goto L9;
        return false;
    L9:
        if (r1.containsKey(r2) == false) goto L15;
        return true;
    L15:
        return false;
    L5:
        if (r0.containsKey(r2) == false) goto L6;
        return true;
    }

    public int incrementLineCount(int r2) {
        int r0 = this.lineCount + r2;
        this.lineCount = r0;
        return r0;
    }

    public void initLineMapping(String r2, char[] r3) {
        if (this.sourceLineLookups != null) goto L5;
        this.sourceLineLookups = new HashMap();
    L5:
        this.sourceLineLookups.put(r2, new LineMapper(r3).map());
    }

    public void initializeTables() {
        if (this.variables != null) goto L6;
        this.variables = new LinkedHashMap();
    L6:
        if (this.inputs != null) goto L9;
        this.inputs = new LinkedHashMap();
    L9:
        if (this.variableVisibility != null) goto L34;
        pushVariableScope();
        Set<String> r0 = getVariableScope();
        r0.addAll(this.variables.keySet());
        r0.addAll(this.inputs.keySet());
        r0.addAll(this.parserConfiguration.getImports().keySet());
        if (this.inputs.containsKey("this") == false) goto L42;
        Class r8 = this.inputs.get("this");
        Field[] r1 = r8.getFields();
        int r2 = r1.length;
        int r4 = 0;
    L13:
        if (r4 >= r2) goto L18;
        Field r5 = r1[r4];
        if ((r5.getModifiers() & 9) == 0) goto L17;
        r0.add(r5.getName());
    L17:
        r4 = r4 + 1;
        goto L13
    L18:
        Method[] r82 = r8.getMethods();
        int r12 = r82.length;
        int r22 = 0;
    L19:
        if (r22 >= r12) goto L43;
        Method r42 = r82[r22];
        if ((r42.getModifiers() & 1) == 0) goto L33;
        if (r42.getName().startsWith("get") == false) goto L25;
    L32:
        String r43 = ReflectionUtil.getPropertyFromAccessor(r42.getName());
        r0.add(r43);
        r0.add(r43.substring(0, 1).toUpperCase() + r43.substring(1));
        goto L33
    L25:
        if (r42.getName().startsWith("is") == true) goto L27;
    L31:
        r0.add(r42.getName());
        goto L33
    L27:
        if (r42.getReturnType().equals(Boolean.TYPE) == true) goto L32;
        if (r42.getReturnType().equals(Boolean.class) == false) goto L31;
    L33:
        r22 = r22 + 1;
        goto L19
    L43:
        return;
    L42:
        return;
    }

    public boolean isAllowBootstrapBypass() {
        return this.parserConfiguration.isAllowBootstrapBypass();
    }

    public boolean isBlockSymbols() {
        return this.blockSymbols;
    }

    public boolean isCompiled() {
        return this.compiled;
    }

    public boolean isDebugSymbols() {
        return this.debugSymbols;
    }

    public boolean isExecutableCodeReached() {
        return this.executableCodeReached;
    }

    public boolean isFatalError() {
        return this.fatalError;
    }

    public boolean isFunctionContext() {
        return this.functionContext;
    }

    public boolean isIndexAllocation() {
        return this.indexAllocation;
    }

    public boolean isLineMapped(String r1) {
        Map<String, LineMapper.LineLookup> r0 = this.sourceLineLookups;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.containsKey(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isOptimizerNotified() {
        return this.optimizationMode;
    }

    public boolean isRetainParserState() {
        return this.retainParserState;
    }

    public boolean isStrictTypeEnforcement() {
        return this.strictTypeEnforcement;
    }

    public boolean isStrongTyping() {
        return this.strongTyping;
    }

    public boolean isVariableVisible(String r5) {
        ArrayList<Set<String>> r0 = this.variableVisibility;
        if (r0 != null) goto L5;
    L21:
        return false;
    L5:
        if (r0.isEmpty() == true) goto L21;
        if (AbstractParser.LITERALS.containsKey(r5) == false) goto L10;
    L20:
        return true;
    L10:
        if (hasImport(r5) == true) goto L20;
        int r02 = this.variableVisibility.size() - 1;
    L14:
        if (this.variableVisibility.get(r02).contains(r5) == true) goto L15;
        int r3 = r02 - 1;
        if (r02 == 0) goto L18;
        r02 = r3;
        goto L14
    L18:
        return false;
    L15:
        return true;
    }

    public boolean isVariablesEscape() {
        return this.variablesEscape;
    }

    public boolean isVisitedLine(String r2, int r3) {
        Map<String, Set<Integer>> r0 = this.visitedLines;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.containsKey(r2) == true) goto L7;
        return false;
    L7:
        if (this.visitedLines.get(r2).contains(Integer.valueOf(r3)) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public void makeVisible(String r2) {
        ArrayList<Set<String>> r0 = this.variableVisibility;
        if (r0 != null) goto L5;
    L8:
        GlShader$$ExternalSyntheticBUOutline1.m("no context");
        return;
    L5:
        if (r0.isEmpty() == true) goto L8;
        getVariableScope().add(r2);
    }

    public void optimizationNotify() {
        this.optimizationMode = true;
    }

    public void popVariableScope() {
        ArrayList<Set<String>> r0 = this.variableVisibility;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L9;
        this.variableVisibility.remove(r0.size() - 1);
        setLastTypeParameters(null);
        return;
    }

    public void processTables() {
        Iterator<String> r0 = this.variables.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        String r1 = r0.next();
        this.inputs.remove(r1);
        goto L4
    }

    public void pushVariableScope() {
        initVariableVisibility();
        this.variableVisibility.add(new HashSet());
    }

    public void setAllowBootstrapBypass(boolean r1) {
        this.parserConfiguration.setAllowBootstrapBypass(r1);
    }

    public void setBlockSymbols(boolean r1) {
        this.blockSymbols = r1;
    }

    public void setCompiled(boolean r1) {
        this.compiled = r1;
    }

    public void setDebugSymbols(boolean r1) {
        this.debugSymbols = r1;
    }

    public void setErrorList(List<ErrorDetail> r1) {
        this.errorList = r1;
    }

    public void setExecutableCodeReached(boolean r1) {
        this.executableCodeReached = r1;
    }

    public void setFatalError(boolean r1) {
        this.fatalError = r1;
    }

    public void setImports(Map<String, Object> r4) {
        if (r4 == null) goto L18;
        Iterator<Map.Entry<String, Object>> r42 = r4.entrySet().iterator();
    L6:
        if (r42.hasNext() == false) goto L29;
        Map.Entry<String, Object> r0 = r42.next();
        Object r1 = r0.getValue();
        if ((r1 instanceof Class) == true) goto L9;
        if ((r1 instanceof Method) == true) goto L12;
        if ((r1 instanceof MethodStub) == false) goto L17;
        addImport(r0.getKey(), (MethodStub) r1);
        goto L6
    L17:
        throw new RuntimeException("invalid element in imports map: " + r0.getKey() + " (" + r1 + ")");
    L12:
        addImport(r0.getKey(), (Method) r1);
        goto L6
    L9:
        addImport(r0.getKey(), (Class) r1);
        goto L6
    L29:
        return;
    }

    public void setIndexAllocation(boolean r1) {
        this.indexAllocation = r1;
    }

    public void setInputs(Map<String, Class> r1) {
        this.inputs = r1;
    }

    public void setInterceptors(Map<String, Interceptor> r1) {
        this.parserConfiguration.setInterceptors(r1);
    }

    public LineLabel setLastLineLabel(LineLabel r1) {
        this.lastLineLabel = r1;
        return r1;
    }

    public void setLastTypeParameters(Type[] r1) {
        this.lastTypeParameters = r1;
    }

    public void setLineAndOffset(int r1, int r2) {
        this.lineOffset = r2;
    }

    public int setLineCount(int r1) {
        this.lineCount = r1;
        return r1;
    }

    public void setLineOffset(int r1) {
        this.lineOffset = r1;
    }

    public void setRetainParserState(boolean r1) {
        this.retainParserState = r1;
    }

    public void setRootParser(Parser r1) {
        this.rootParser = r1;
    }

    public void setSourceFile(String r1) {
        if (r1 == null) goto L5;
        this.sourceFile = r1;
        return;
    }

    public void setStrictTypeEnforcement(boolean r1) {
        this.strictTypeEnforcement = r1;
    }

    public void setStrongTyping(boolean r1) {
        this.strongTyping = r1;
        if (r1 == false) goto L6;
        this.strictTypeEnforcement = true;
        return;
    }

    public void setVariables(HashMap<String, Class> r1) {
        this.variables = r1;
    }

    public ParserContext stronglyTyped() {
        setStrongTyping(true);
        return this;
    }

    public int variableIndexOf(String r4) {
        ArrayList<String> r0 = this.indexedInputs;
        if (r0 == null) goto L13;
        int r02 = r0.indexOf(r4);
        if (r02 != (-1)) goto L12;
        ArrayList<String> r2 = this.indexedLocals;
        if (r2 == null) goto L12;
        int r42 = r2.indexOf(r4);
        if (r42 != (-1)) goto L11;
        return r42;
    L11:
        return r42 + this.indexedInputs.size();
    L12:
        return r02;
    L13:
        return -1;
    }

    public void visitLine(String r3, int r4) {
        if (this.visitedLines != null) goto L6;
        this.visitedLines = new HashMap();
    L6:
        if (this.visitedLines.containsKey(r3) == true) goto L8;
        this.visitedLines.put(r3, new TreeSet());
    L8:
        this.visitedLines.get(r3).add(Integer.valueOf(r4));
    }

    public ParserContext withImport(Class r1) {
        addImport(r1);
        return this;
    }

    public ParserContext withIndexedVars(String[] r2) {
        ArrayList<String> r0 = new ArrayList();
        this.indexedInputs = r0;
        Collections.addAll(r0, r2);
        return this;
    }

    public ParserContext withInput(String r1, Class r2) {
        addInput(r1, r2);
        return this;
    }

    public ParserContext withInputs(Map<String, Class> r1) {
        setInputs(r1);
        return this;
    }

    public ParserContext withTypeParameters(Map<String, Map<String, Type>> r1) {
        addTypeParameters(r1);
        return this;
    }
}
