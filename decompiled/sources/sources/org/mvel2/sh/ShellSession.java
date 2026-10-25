package org.mvel2.sh;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.TreeMap;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.MVELInterpretedRuntime;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.sh.command.basic.BasicCommandSet;
import org.mvel2.sh.command.file.FileCommandSet;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.util.PropertyTools;
import org.mvel2.util.StringAppender;

/* loaded from: classes5.dex */
public class ShellSession {
    private static final String[] EMPTY = null;
    public static final String PROMPT_VAR = "$PROMPT";
    private int cdepth;
    private String commandBuffer;
    private final Map<String, Command> commands;
    private Object ctxObject;
    private Stack<Object> ctxStack;
    private int depth;
    private Map<String, String> env;
    StringAppender inBuffer;
    VariableResolverFactory lvrf;
    private boolean multi;
    private int multiIndentSize;
    private PrintStream out;
    ParserContext pCtx;
    private String prompt;
    final BufferedReader readBuffer;
    private Map<String, Object> variables;

    /* renamed from: org.mvel2.sh.ShellSession$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ShellSession this$0;
        final /* synthetic */ InputStream val$inStream;
        final /* synthetic */ RunState val$runState;
        final /* synthetic */ PrintStream val$sysPrintStream;

        public AnonymousClass1(ShellSession r1, InputStream r2, PrintStream r3, RunState r4) {
            this.this$0 = r1;
            this.val$inStream = r2;
            this.val$sysPrintStream = r3;
            this.val$runState = r4;
        }

        @Override // java.lang.Runnable
        public void run() {
            byte[] r0 = new byte[25];
        L18:
            int r1 = this.val$inStream.read(r0);     // Catch: Exception -> L17
            if (r1 > 0) goto L5;
            if (this.val$runState.isRunning() == true) goto L18;
        L5:
            int r2 = 0;
        L6:
            PrintStream r3 = this.val$sysPrintStream;
            if (r2 >= r1) goto L9;
            r3.print((char) r0[r2]);     // Catch: Exception -> L17
            r2 = r2 + 1;     // Catch: Exception -> L17
            goto L6
        L9:
            r3.flush();     // Catch: Exception -> L17
        L12:
            this.val$sysPrintStream.flush();
            boolean r02 = ShellSession.access$000(this.this$0);
            ShellSession r12 = this.this$0;
            if (r02 == true) goto L15;
            ShellSession.access$102(r12, ShellSession.access$202(r12, String.valueOf(TemplateRuntime.eval((String) ShellSession.access$300(r12).get(ShellSession.PROMPT_VAR), ShellSession.access$400(this.this$0)))).length());
            ShellSession.access$500(this.this$0).append(ShellSession.access$200(this.this$0));
            return;
        L15:
            ShellSession.access$500(r12).append(">").append(this.this$0.indent((ShellSession.access$100(r1) - 1) + (ShellSession.access$600(this.this$0) * 4)));
        }
    }

    /* renamed from: org.mvel2.sh.ShellSession$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ ShellSession this$0;
        final /* synthetic */ OutputStream val$outStream;
        final /* synthetic */ Process val$p;
        final /* synthetic */ RunState val$runState;
        final /* synthetic */ PrintStream val$sysPrintStream;

        /* renamed from: org.mvel2.sh.ShellSession$2$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnonymousClass2 this$1;

            public AnonymousClass1(AnonymousClass2 r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
            L15:
                if (this.this$1.val$runState.isRunning() == false) goto L12;
            L4:
                String r0 = this.this$1.this$0.readBuffer.readLine();     // Catch: Exception -> L14
                if (r0 == null) goto L15;
                if (this.this$1.val$runState.isRunning() == false) goto L11;
                char[] r02 = r0.toCharArray();     // Catch: Exception -> L14
                int r1 = r02.length;     // Catch: Exception -> L14
                int r2 = 0;
            L9:
                if (r2 >= r1) goto L4;
                char r3 = r02[r2];     // Catch: Exception -> L14
                this.this$1.val$outStream.write((byte) r3);     // Catch: Exception -> L14
                r2 = r2 + 1;     // Catch: Exception -> L14
                goto L9
            L11:
                this.this$1.val$runState.getSession().setCommandBuffer(r0);     // Catch: Exception -> L14
                goto L15
            L12:
                this.this$1.val$outStream.write(10);     // Catch: Exception -> L14
                this.this$1.val$outStream.flush();     // Catch: Exception -> L14
                return;
            }
        }

        public AnonymousClass2(ShellSession r1, RunState r2, OutputStream r3, Process r4, PrintStream r5) {
            this.this$0 = r1;
            this.val$runState = r2;
            this.val$outStream = r3;
            this.val$p = r4;
            this.val$sysPrintStream = r5;
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread r0 = new Thread(new AnonymousClass1(this));
            r0.setPriority(1);
            r0.start();
            this.val$p.waitFor();     // Catch: InterruptedException -> L7
        L4:
            this.val$sysPrintStream.flush();
            this.val$runState.setRunning(false);
            r0.join();     // Catch: InterruptedException -> L8
            return;
        }
    }

    public static final class RunState {
        private boolean running;
        private ShellSession session;

        public RunState(ShellSession r2) {
            this.running = true;
            this.session = r2;
        }

        public ShellSession getSession() {
            return this.session;
        }

        public boolean isRunning() {
            return this.running;
        }

        public void setRunning(boolean r1) {
            this.running = r1;
        }

        public void setSession(ShellSession r1) {
            this.session = r1;
        }
    }

    static {
        EMPTY = new String[0];
    }

    public ShellSession() {
        TreeMap r0 = new TreeMap();
        this.commands = r0;
        this.ctxStack = new Stack();
        this.pCtx = new ParserContext();
        this.multi = false;
        this.multiIndentSize = 0;
        this.out = System.out;
        this.inBuffer = new StringAppender();
        this.readBuffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Starting session...");
        this.variables = new HashMap();
        this.env = new HashMap();
        r0.putAll(new BasicCommandSet().load());
        r0.putAll(new FileCommandSet().load());
        this.env.put(PROMPT_VAR, DefaultEnvironment.PROMPT);
        this.env.put("$OS_NAME", System.getProperty("os.name"));
        this.env.put("$OS_VERSION", System.getProperty("os.version"));
        this.env.put("$JAVA_VERSION", PropertyTools.getJavaVersion());
        this.env.put("$CWD", new File(".").getAbsolutePath());
        this.env.put("$COMMAND_PASSTRU", "false");
        this.env.put("$PRINTOUTPUT", "true");
        this.env.put("$ECHO", "false");
        this.env.put("$SHOW_TRACES", "true");
        this.env.put("$USE_OPTIMIZER_ALWAYS", "false");
        this.env.put("$PATH", _UrlKt.FRAGMENT_ENCODE_SET);
        ResourceBundle r02 = ResourceBundle.getBundle(".mvelsh.properties");     // Catch: MissingResourceException -> L8
        Enumeration<String> r2 = r02.getKeys();     // Catch: MissingResourceException -> L8
    L4:
        if (r2.hasMoreElements() == false) goto L11;
        Map<String, String> r4 = this.env;     // Catch: MissingResourceException -> L8
        String r5 = r2.nextElement();     // Catch: MissingResourceException -> L8
        r4.put(r5, r02.getString(r5));     // Catch: MissingResourceException -> L8
    L11:
        this.lvrf = new MapVariableResolverFactory(this.variables, new MapVariableResolverFactory(this.env));
        return;
    L8:
        System.out.println("No config file found.  Loading default config.");
        if (PropertyTools.contains(System.getProperty("os.name").toLowerCase(), "windows") == true) goto L11;
        this.env.put("$PATH", "/bin:/usr/bin:/sbin:/usr/sbin");
        goto L11
    }

    public ShellSession(String r1) {
        this();
        exec(r1);
    }

    private void _exec() {
        PrintStream r5 = System.out;
        if ("true".equals(this.env.get("$ECHO")) == false) goto L5;
        this.out.println(">" + this.commandBuffer);
        this.out.flush();
    L5:
        String[] r6 = this.inBuffer.append(this.commandBuffer).toString().split("\\s");
        if (r6.length != 0) goto L8;
    L17:
        this.commandBuffer = null;
    L22:
        e = move-exception;
        if ("true".equals(this.env.get("$COMMAND_PASSTHRU")) == false) goto L57;
        String r0 = r6[0];
        if (r0.startsWith("./") == false) goto L43;
        r0 = new File(this.env.get("$CWD")).getAbsolutePath() + r0.substring(r0.indexOf(47));
        String[] r2 = {r0};
    L42:
        String r10 = r0;
        String[] r11 = r2;
        int r12 = r11.length;
        boolean r02 = false;
        int r13 = 0;
    L45:
        if (r13 >= r12) goto L54;
        File r3 = new File(r11[r13] + "/" + r10);
        if (r3.exists() == false) goto L53;
        if (r3.isFile() == false) goto L53;
        String[] r03 = new String[r6.length];
        r03[0] = r3.getAbsolutePath();
        System.arraycopy(r6, 1, r03, 1, r6.length - 1);
        Process r4 = Runtime.getRuntime().exec(r03);     // Catch: Exception -> L63
        OutputStream r32 = r4.getOutputStream();     // Catch: Exception -> L63
        InputStream r04 = r4.getInputStream();     // Catch: Exception -> L63
        r4.getErrorStream();     // Catch: Exception -> L63
        RunState r22 = new RunState(this);     // Catch: Exception -> L63
        Thread r14 = new Thread(new AnonymousClass1(this, r04, r5, r22));     // Catch: Exception -> L63
        Thread r15 = new Thread(new AnonymousClass2(this, r22, r32, r4, r5));     // Catch: Exception -> L63
        r14.setPriority(1);     // Catch: Exception -> L63
        r14.start();     // Catch: Exception -> L63
        r15.setPriority(1);     // Catch: Exception -> L63
        r15.start();     // Catch: Exception -> L63
        r15.join();     // Catch: Exception -> L63
        r14.notify();     // Catch: Exception -> L63
    L52:
        r02 = true;
    L53:
        r13 = r13 + 1;
        goto L45
    L54:
        if (r02 == false) goto L57;
        this.inBuffer.reset();
        return;
    L43:
        r2 = this.env.get("$PATH").split("(:|;)");
    L57:
        ByteArrayOutputStream r05 = new ByteArrayOutputStream();
        PrintStream r23 = new PrintStream(r05);
        e.printStackTrace(r23);
        r23.flush();
        this.env.put("$LAST_STACK_TRACE", new String(r05.toByteArray()));
        boolean r24 = Boolean.parseBoolean(this.env.get("$SHOW_TRACE"));
        PrintStream r33 = this.out;
        if (r24 == false) goto L60;
        r33.println(this.env.get("$LAST_STACK_TRACE"));
    L61:
        this.inBuffer.reset();
        return;
    L60:
        r33.println(e.toString());
        goto L61
    L19:
        if (shouldDefer(this.inBuffer) == false) goto L24;
        this.multi = true;     // Catch: Exception -> L22
        return;
    L24:
        this.multi = false;     // Catch: Exception -> L22
        if (Boolean.parseBoolean(this.env.get("$USE_OPTIMIZER_ALWAYS")) == false) goto L27;
        Object r06 = MVEL.executeExpression(MVEL.compileExpression(this.inBuffer.toString()), this.ctxObject, this.lvrf);     // Catch: Exception -> L22
    L28:
        if (r06 != null) goto L30;
    L35:
        this.inBuffer.reset();
        return;
    L30:
        if ("true".equals(this.env.get("$PRINTOUTPUT")) == false) goto L35;
        boolean r25 = r06.getClass().isArray();
        PrintStream r34 = this.out;
        if (r25 == false) goto L34;
        r34.println(Arrays.toString((Object[]) r06));
        goto L35
    L34:
        r34.println(String.valueOf(r06));
        goto L35
    L27:
        r06 = new MVELInterpretedRuntime(this.inBuffer.toString(), this.ctxObject, this.lvrf, this.pCtx).parse();     // Catch: Exception -> L22
        goto L28
    L8:
        if (this.commands.containsKey(r6[0]) == false) goto L17;
        this.commandBuffer = null;
        if (r6.length <= 1) goto L12;
        int r07 = r6.length - 1;
        String[] r26 = new String[r07];
        System.arraycopy(r6, 1, r26, 0, r07);
    L64:
        this.commands.get(r6[0]).execute(this, r26);     // Catch: CommandException -> L15
    L15:
        e = move-exception;
        this.out.append("Error: ").append(e.getMessage()).append("\n");
        goto L35
    L12:
        r26 = EMPTY;
        goto L64
    }

    public static /* synthetic */ boolean access$000(ShellSession r0) {
        return r0.multi;
    }

    public static /* synthetic */ int access$100(ShellSession r0) {
        return r0.multiIndentSize;
    }

    public static /* synthetic */ int access$102(ShellSession r0, int r1) {
        r0.multiIndentSize = r1;
        return r1;
    }

    public static /* synthetic */ String access$200(ShellSession r0) {
        return r0.prompt;
    }

    public static /* synthetic */ String access$202(ShellSession r0, String r1) {
        r0.prompt = r1;
        return r1;
    }

    public static /* synthetic */ Map access$300(ShellSession r0) {
        return r0.env;
    }

    public static /* synthetic */ Map access$400(ShellSession r0) {
        return r0.variables;
    }

    public static /* synthetic */ PrintStream access$500(ShellSession r0) {
        return r0.out;
    }

    public static /* synthetic */ int access$600(ShellSession r0) {
        return r0.depth;
    }

    public void exec(String r5) {
        String[] r52 = r5.split("\n");
        int r0 = r52.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        String r2 = r52[r1];
        this.inBuffer.append(r2);
        _exec();
        r1 = r1 + 1;
        goto L3
    }

    public String getCommandBuffer() {
        return this.commandBuffer;
    }

    public Map<String, Command> getCommands() {
        return this.commands;
    }

    public Object getCtxObject() {
        return this.ctxObject;
    }

    public Map<String, String> getEnv() {
        return this.env;
    }

    public Map<String, Object> getVariables() {
        return this.variables;
    }

    public String indent(int r3) {
        StringBuffer r2 = new StringBuffer();
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L6;
        r2.append(" ");
        r0 = r0 + 1;
        goto L3
    L6:
        return r2.toString();
    }

    public boolean popCtxObject() {
        if (this.ctxStack.isEmpty() == true) goto L6;
        this.ctxObject = this.ctxStack.pop();
        return true;
    L6:
        return false;
    }

    public void printPrompt() {
        if (this.multi == true) goto L6;
        String r0 = String.valueOf(TemplateRuntime.eval(this.env.get(PROMPT_VAR), this.variables));
        this.prompt = r0;
        this.multiIndentSize = r0.length();
        this.out.append(this.prompt);
        return;
    L6:
        this.out.append(">").append(indent((this.multiIndentSize - 1) + (this.depth * 4)));
    }

    public boolean pushCtxObject(Object r3) {
        Object r0 = this.ctxObject;
        if (r0 == r3) goto L6;
        this.ctxStack.push(r0);
        this.ctxObject = r3;
        return true;
    L6:
        return false;
    }

    public void run() {
        BufferedReader r0 = new BufferedReader(new InputStreamReader(System.in));
    L11:
        printPrompt();     // Catch: Exception -> L8
        if (this.commandBuffer != null) goto L6;
        this.commandBuffer = r0.readLine();     // Catch: Exception -> L8
    L6:
        _exec();     // Catch: Exception -> L8
    L8:
        e = move-exception;
        e.printStackTrace();
        System.out.println("unexpected exception. exiting.");
    }

    public void setCommandBuffer(String r1) {
        this.commandBuffer = r1;
    }

    public void setCtxObject(Object r1) {
        pushCtxObject(r1);
    }

    public boolean shouldDefer(StringAppender r8) {
        int r0 = r8.length();
        char[] r1 = new char[r0];
        r8.getChars(0, r8.length(), r1, 0);
        this.cdepth = 0;
        this.depth = 0;
        int r82 = 0;
    L4:
        if (r82 >= r0) goto L27;
        char r4 = r1[r82];
        if (r4 == '*') goto L20;
        if (r4 != '/') goto L9;
        int r42 = r82 + 1;
        if (r42 >= r0) goto L25;
        if (r1[r42] != '*') goto L25;
        this.cdepth++;
    L25:
        r82 = r82 + 1;
        goto L4
    L9:
        if (r4 != '{') goto L11;
        this.depth++;
        goto L25
    L11:
        if (r4 != '}') goto L25;
        this.depth--;
        goto L25
    L20:
        int r43 = r82 + 1;
        if (r43 >= r0) goto L25;
        if (r1[r43] != '/') goto L25;
        this.cdepth--;
        goto L25
    L27:
        if ((this.depth + this.cdepth) <= 0) goto L29;
        return true;
    L29:
        return false;
    }
}
