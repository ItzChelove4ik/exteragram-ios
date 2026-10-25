package com.android.dx.command.dexer;

import com.android.dex.Dex;
import com.android.dex.DexException;
import com.android.dex.util.FileUtils;
import com.android.dx.cf.code.SimException;
import com.android.dx.cf.direct.ClassPathOpener;
import com.android.dx.cf.direct.DirectClassFile;
import com.android.dx.cf.direct.StdAttributeFactory;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.command.UsageException;
import com.android.dx.dex.DexOptions;
import com.android.dx.dex.cf.CfOptions;
import com.android.dx.dex.cf.CfTranslator;
import com.android.dx.dex.file.ClassDefItem;
import com.android.dx.dex.file.DexFile;
import com.android.dx.dex.file.EncodedMethod;
import com.android.dx.merge.CollisionPolicy;
import com.android.dx.merge.DexMerger;
import com.android.dx.rop.annotation.Annotation;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.Type;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import okhttp3.internal.url._UrlKt;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class Main {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Attributes.Name CREATED_BY = null;
    private static final String DEX_EXTENSION = ".dex";
    private static final String DEX_PREFIX = "classes";
    private static final String IN_RE_CORE_CLASSES = "Ill-advised or mistaken usage of a core class (java.* or javax.*)\nwhen not building a core library.\n\nThis is often due to inadvertently including a core library file\nin your application's project, when using an IDE (such as\nEclipse). If you are sure you're not intentionally defining a\ncore class, then this is the most likely explanation of what's\ngoing on.\n\nHowever, you might actually be trying to define a class in a core\nnamespace, the source of which you may have taken, for example,\nfrom a non-Android virtual machine project. This will most\nassuredly not work. At a minimum, it jeopardizes the\ncompatibility of your app with future versions of the platform.\nIt is also often of questionable legality.\n\nIf you really intend to build a core library -- which is only\nappropriate as part of creating a full virtual machine\ndistribution, as opposed to compiling an application -- then use\nthe \"--core-library\" option to suppress this error message.\n\nIf you go ahead and use \"--core-library\" but are in fact\nbuilding an application, then be forewarned that your application\nwill still fail to build or run, at some point. Please be\nprepared for angry customers who find, for example, that your\napplication ceases to function once they upgrade their operating\nsystem. You will be to blame for this problem.\n\nIf you are legitimately using some code that happens to be in a\ncore package, then the easiest safe alternative you have is to\nrepackage that code. That is, move the classes in question into\nyour own package namespace. This means that they will never be in\nconflict with core system classes. JarJar is a tool that may help\nyou in this endeavor. If you find that you cannot do this, then\nthat is an indication that the path you are on will ultimately\nlead to pain, suffering, grief, and lamentation.\n";
    private static final String[] JAVAX_CORE = null;
    private static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";
    private static final int MAX_FIELD_ADDED_DURING_DEX_CREATION = 9;
    private static final int MAX_METHOD_ADDED_DURING_DEX_CREATION = 2;
    private List<Future<Boolean>> addToDexFutures;
    private volatile boolean anyFilesProcessed;
    private Arguments args;
    private ExecutorService classDefItemConsumer;
    private ExecutorService classTranslatorPool;
    private Set<String> classesInMainDex;
    private final DxContext context;
    private ExecutorService dexOutPool;
    private List<byte[]> dexOutputArrays;
    private List<Future<byte[]>> dexOutputFutures;
    private Object dexRotationLock;
    private AtomicInteger errors;
    private OutputStreamWriter humanOutWriter;
    private final List<byte[]> libraryDexBuffers;
    private int maxFieldIdsInProcess;
    private int maxMethodIdsInProcess;
    private long minimumFileAge;
    private DexFile outputDex;
    private TreeMap<String, byte[]> outputResources;

    /* renamed from: com.android.dx.command.dexer.Main$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Arguments {
        private static final String INCREMENTAL_OPTION = "--incremental";
        private static final String INPUT_LIST_OPTION = "--input-list";
        private static final String MAIN_DEX_LIST_OPTION = "--main-dex-list";
        private static final String MINIMAL_MAIN_DEX_OPTION = "--minimal-main-dex";
        private static final String MULTI_DEX_OPTION = "--multi-dex";
        private static final String NUM_THREADS_OPTION = "--num-threads";
        public boolean allowAllInterfaceMethodInvokes;
        public CfOptions cfOptions;
        public final DxContext context;
        public boolean coreLibrary;
        public boolean debug;
        public DexOptions dexOptions;
        public String dontOptimizeListFile;
        public int dumpWidth;
        public boolean emptyOk;
        public String[] fileNames;
        public boolean forceJumbo;
        public String humanOutName;
        public boolean incremental;
        private List<String> inputList;
        public boolean jarOutput;
        public boolean keepClassesInJar;
        public boolean localInfo;
        public String mainDexListFile;
        public int maxNumberOfIdxPerDex;
        public String methodToDump;
        public int minSdkVersion;
        public boolean minimalMainDex;
        public boolean multiDex;
        public int numThreads;
        public boolean optimize;
        public String optimizeListFile;
        public String outName;
        private boolean outputIsDirectDex;
        private boolean outputIsDirectory;
        public int positionInfo;
        public boolean statistics;
        public boolean strictNameCheck;
        public boolean verbose;
        public boolean verboseDump;
        public boolean warnings;

        public static class ArgumentsParser {
            private final String[] arguments;
            private String current;
            private int index;
            private String lastValue;

            public ArgumentsParser(String[] r1) {
                this.arguments = r1;
                this.index = 0;
            }

            private boolean getNextValue() {
                int r0 = this.index;
                String[] r1 = this.arguments;
                if (r0 < r1.length) goto L6;
                return false;
            L6:
                this.current = r1[r0];
                this.index = r0 + 1;
                return true;
            }

            public String getCurrent() {
                return this.current;
            }

            public String getLastValue() {
                return this.lastValue;
            }

            public boolean getNext() {
                int r0 = this.index;
                String[] r1 = this.arguments;
                if (r0 < r1.length) goto L5;
                return false;
            L5:
                String r02 = r1[r0];
                this.current = r02;
                if (r02.equals("--") == false) goto L8;
            L12:
                return false;
            L8:
                if (this.current.startsWith("--") == false) goto L12;
                this.index++;
                return true;
            }

            public String[] getRemaining() {
                String[] r0 = this.arguments;
                int r1 = r0.length;
                int r4 = this.index;
                int r12 = r1 - r4;
                String[] r2 = new String[r12];
                if (r12 <= 0) goto L5;
                System.arraycopy(r0, r4, r2, 0, r12);
            L5:
                return r2;
            }

            public boolean isArg(String r5) {
                int r0 = r5.length();
                if (r0 <= 0) goto L20;
                int r1 = r0 - 1;
                if (r5.charAt(r1) != '=') goto L20;
                if (this.current.startsWith(r5) == false) goto L10;
                this.lastValue = this.current.substring(r0);
                return true;
            L10:
                String r52 = r5.substring(0, r1);
                if (this.current.equals(r52) == true) goto L13;
                return false;
            L13:
                if (getNextValue() == false) goto L16;
                this.lastValue = this.current;
                return true;
            L16:
                System.err.println("Missing value after parameter ".concat(r52));
                throw new UsageException();
            L20:
                return this.current.equals(r5);
            }
        }

        public Arguments() {
            this(new DxContext());
        }

        public Arguments(DxContext r5) {
            this.debug = false;
            this.warnings = true;
            this.verbose = false;
            this.verboseDump = false;
            this.coreLibrary = false;
            this.methodToDump = null;
            this.dumpWidth = 0;
            this.outName = null;
            this.humanOutName = null;
            this.strictNameCheck = true;
            this.emptyOk = false;
            this.jarOutput = false;
            this.keepClassesInJar = false;
            this.minSdkVersion = 13;
            this.positionInfo = 2;
            this.localInfo = true;
            this.incremental = false;
            this.forceJumbo = false;
            this.allowAllInterfaceMethodInvokes = false;
            this.optimize = true;
            this.optimizeListFile = null;
            this.dontOptimizeListFile = null;
            this.numThreads = 1;
            this.multiDex = false;
            this.mainDexListFile = null;
            this.minimalMainDex = false;
            this.maxNumberOfIdxPerDex = 65536;
            this.inputList = null;
            this.outputIsDirectory = false;
            this.outputIsDirectDex = false;
            this.context = r5;
        }

        public static /* synthetic */ void access$000(Arguments r0, String[] r1) {
            r0.parse(r1);
        }

        private void parse(String[] r3) {
            ArgumentsParser r0 = new ArgumentsParser(r3);
            parseFlags(r0);
            this.fileNames = r0.getRemaining();
            List<String> r32 = this.inputList;
            if (r32 != null) goto L5;
        L7:
            int r33 = this.fileNames.length;
            boolean r02 = this.emptyOk;
            if (r33 != 0) goto L13;
            if (r02 == true) goto L16;
            this.context.err.println("no input files specified");
            throw new UsageException();
        L16:
            if (this.humanOutName == null) goto L18;
        L20:
            String r34 = this.mainDexListFile;
            if (r34 == null) goto L28;
            if (this.multiDex == true) goto L28;
            this.context.err.println("--main-dex-list is only supported in combination with --multi-dex");
            throw new UsageException();
        L28:
            if (this.minimalMainDex == false) goto L35;
            if (r34 != null) goto L31;
        L33:
            this.context.err.println("--minimal-main-dex is only supported in combination with --multi-dex and --main-dex-list");
            throw new UsageException();
        L31:
            if (this.multiDex == false) goto L33;
        L35:
            boolean r35 = this.multiDex;
            if (r35 == true) goto L38;
        L42:
            if (r35 == false) goto L49;
            if (this.outputIsDirectDex == false) goto L49;
            this.context.err.println("Unsupported output \"" + this.outName + "\". --multi-dex supports only archive or directory output");
            throw new UsageException();
        L49:
            if (this.outputIsDirectory == false) goto L52;
            if (r35 == true) goto L52;
            this.outName = new File(this.outName, "classes.dex").getPath();
        L52:
            makeOptionsObjects();
            return;
        L38:
            if (this.incremental == false) goto L42;
            this.context.err.println("--incremental is not supported with --multi-dex");
            throw new UsageException();
        L18:
            if (this.methodToDump == null) goto L20;
            this.humanOutName = "-";
            goto L20
        L13:
            if (r02 == false) goto L16;
            this.context.out.println("ignoring input files");
            goto L16
        L5:
            if (r32.isEmpty() == true) goto L7;
            this.inputList.addAll(Arrays.asList(this.fileNames));
            List<String> r36 = this.inputList;
            this.fileNames = (String[]) r36.toArray(new String[r36.size()]);
            goto L7
        }

        private void parseFlags(ArgumentsParser r5) {
        L3:
            if (r5.getNext() == false) goto L132;
            if (r5.isArg("--debug") == true) goto L6;
            if (r5.isArg("--no-warning") == true) goto L9;
            if (r5.isArg("--verbose") == true) goto L12;
            if (r5.isArg("--verbose-dump") == true) goto L15;
            if (r5.isArg("--no-files") == true) goto L18;
            if (r5.isArg("--no-optimize") == true) goto L21;
            if (r5.isArg("--no-strict") == true) goto L24;
            if (r5.isArg("--core-library") == true) goto L27;
            if (r5.isArg("--statistics") == true) goto L30;
            if (r5.isArg("--optimize-list=") == true) goto L34;
            if (r5.isArg("--no-optimize-list=") == true) goto L41;
            if (r5.isArg("--keep-classes") == true) goto L47;
            if (r5.isArg("--output=") == true) goto L50;
            if (r5.isArg("--dump-to=") == true) goto L66;
            if (r5.isArg("--dump-width=") == true) goto L69;
            if (r5.isArg("--dump-method=") == true) goto L72;
            if (r5.isArg("--positions=") == true) goto L75;
            if (r5.isArg("--no-locals") == true) goto L88;
            if (r5.isArg("--num-threads=") == true) goto L91;
            if (r5.isArg(INCREMENTAL_OPTION) == true) goto L94;
            if (r5.isArg("--force-jumbo") == true) goto L97;
            if (r5.isArg(MULTI_DEX_OPTION) == true) goto L100;
            if (r5.isArg("--main-dex-list=") == true) goto L103;
            if (r5.isArg(MINIMAL_MAIN_DEX_OPTION) == true) goto L106;
            if (r5.isArg("--set-max-idx-number=") == true) goto L109;
            if (r5.isArg("--input-list=") == true) goto L112;
            if (r5.isArg("--min-sdk-version=") == true) goto L119;
            if (r5.isArg("--allow-all-interface-method-invokes") == false) goto L130;
            this.allowAllInterfaceMethodInvokes = true;
            goto L3
        L130:
            this.context.err.println("unknown option: " + r5.getCurrent());
            throw new UsageException();
        L119:
            String r0 = r5.getLastValue();
            int r2 = Integer.parseInt(r0);     // Catch: NumberFormatException -> L122
        L123:
            if (r2 < 1) goto L125;
            this.minSdkVersion = r2;
            goto L3
        L125:
            System.err.println("improper min-sdk-version option: " + r0);
            throw new UsageException();
        L122:
            r2 = -1;
            goto L123
        L112:
            File r02 = new File(r5.getLastValue());
            this.inputList = new ArrayList();     // Catch: IOException -> L115
            Main.access$1100(r02.getAbsolutePath(), this.inputList);     // Catch: IOException -> L115
        L115:
            this.context.err.println("Unable to read input list file: " + r02.getName());
            throw new UsageException();
        L109:
            this.maxNumberOfIdxPerDex = Integer.parseInt(r5.getLastValue());
            goto L3
        L106:
            this.minimalMainDex = true;
            goto L3
        L103:
            this.mainDexListFile = r5.getLastValue();
            goto L3
        L100:
            this.multiDex = true;
            goto L3
        L97:
            this.forceJumbo = true;
            goto L3
        L94:
            this.incremental = true;
            goto L3
        L91:
            this.numThreads = Integer.parseInt(r5.getLastValue());
            goto L3
        L88:
            this.localInfo = false;
            goto L3
        L75:
            String r03 = r5.getLastValue().intern();
            if (r03 == "none") goto L77;
            if (r03 == "important") goto L80;
            if (r03 != "lines") goto L84;
            this.positionInfo = 2;
            goto L3
        L84:
            this.context.err.println("unknown positions option: " + r03);
            throw new UsageException();
        L80:
            this.positionInfo = 3;
            goto L3
        L77:
            this.positionInfo = 1;
            goto L3
        L72:
            this.methodToDump = r5.getLastValue();
            this.jarOutput = false;
            goto L3
        L69:
            this.dumpWidth = Integer.parseInt(r5.getLastValue());
            goto L3
        L66:
            this.humanOutName = r5.getLastValue();
            goto L3
        L50:
            this.outName = r5.getLastValue();
            if (new File(this.outName).isDirectory() == true) goto L52;
            if (FileUtils.hasArchiveSuffix(this.outName) == true) goto L55;
            if (this.outName.endsWith(Main.DEX_EXTENSION) == false) goto L59;
        L63:
            this.jarOutput = false;
            this.outputIsDirectDex = true;
            goto L3
        L59:
            if (this.outName.equals("-") == true) goto L63;
            this.context.err.println("unknown output extension: " + this.outName);
            throw new UsageException();
        L55:
            this.jarOutput = true;
            goto L3
        L52:
            this.jarOutput = false;
            this.outputIsDirectory = true;
            goto L3
        L47:
            this.keepClassesInJar = true;
            goto L3
        L41:
            if (this.dontOptimizeListFile != null) goto L43;
            this.optimize = true;
            this.dontOptimizeListFile = r5.getLastValue();
            goto L3
        L43:
            this.context.err.println("--optimize-list and --no-optimize-list are incompatible.");
            throw new UsageException();
        L34:
            if (this.dontOptimizeListFile != null) goto L36;
            this.optimize = true;
            this.optimizeListFile = r5.getLastValue();
            goto L3
        L36:
            this.context.err.println("--optimize-list and --no-optimize-list are incompatible.");
            throw new UsageException();
        L30:
            this.statistics = true;
            goto L3
        L27:
            this.coreLibrary = true;
            goto L3
        L24:
            this.strictNameCheck = false;
            goto L3
        L21:
            this.optimize = false;
            goto L3
        L18:
            this.emptyOk = true;
            goto L3
        L15:
            this.verboseDump = true;
            goto L3
        L12:
            this.verbose = true;
            goto L3
        L9:
            this.warnings = false;
            goto L3
        L6:
            this.debug = true;
            goto L3
        }

        public void makeOptionsObjects() {
            CfOptions r0 = new CfOptions();
            this.cfOptions = r0;
            r0.positionInfo = this.positionInfo;
            r0.localInfo = this.localInfo;
            r0.strictNameCheck = this.strictNameCheck;
            r0.optimize = this.optimize;
            r0.optimizeListFile = this.optimizeListFile;
            r0.dontOptimizeListFile = this.dontOptimizeListFile;
            r0.statistics = this.statistics;
            boolean r1 = this.warnings;
            DxContext r2 = this.context;
            if (r1 == false) goto L5;
            r0.warn = r2.err;
        L6:
            DexOptions r02 = new DexOptions(this.context.err);
            this.dexOptions = r02;
            r02.minSdkVersion = this.minSdkVersion;
            r02.forceJumbo = this.forceJumbo;
            r02.allowAllInterfaceMethodInvokes = this.allowAllInterfaceMethodInvokes;
            return;
        L5:
            r0.warn = r2.noop;
            goto L6
        }

        public void parseFlags(String[] r2) {
            parseFlags(new ArgumentsParser(r2));
        }
    }

    public class BestEffortMainDexListFilter implements ClassPathOpener.FileNameFilter {
        Map<String, List<String>> map;
        final /* synthetic */ Main this$0;

        public BestEffortMainDexListFilter(Main r5) {
            this.this$0 = r5;
            this.map = new HashMap();
            Iterator r52 = Main.access$1000(r5).iterator();
        L4:
            if (r52.hasNext() == false) goto L9;
            String r0 = Main.access$900((String) r52.next());
            String r1 = getSimpleName(r0);
            List<String> r2 = this.map.get(r1);
            if (r2 != null) goto L8;
            r2 = new ArrayList(1);
            this.map.put(r1, r2);
        L8:
            r2.add(r0);
            goto L4
        }

        private String getSimpleName(String r1) {
            int r0 = r1.lastIndexOf(47);
            if (r0 >= 0) goto L5;
            return r1;
        L5:
            return r1.substring(r0 + 1);
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.FileNameFilter
        public boolean accept(String r3) {
            if (r3.endsWith(".class") == false) goto L14;
            String r32 = Main.access$900(r3);
            String r0 = getSimpleName(r32);
            List<String> r2 = this.map.get(r0);
            if (r2 == null) goto L12;
            Iterator<String> r22 = r2.iterator();
        L8:
            if (r22.hasNext() == false) goto L18;
            if (r32.endsWith(r22.next()) == false) goto L8;
            return true;
        L18:
            return false;
        L12:
            return false;
        L14:
            return true;
        }
    }

    public class ClassDefItemConsumer implements Callable<Boolean> {
        Future<ClassDefItem> futureClazz;
        int maxFieldIdsInClass;
        int maxMethodIdsInClass;
        String name;
        final /* synthetic */ Main this$0;

        private ClassDefItemConsumer(Main r1, String r2, Future<ClassDefItem> r3, int r4, int r5) {
            this.this$0 = r1;
            this.name = r2;
            this.futureClazz = r3;
            this.maxMethodIdsInClass = r4;
            this.maxFieldIdsInClass = r5;
        }

        public /* synthetic */ ClassDefItemConsumer(Main r1, String r2, Future r3, int r4, int r5, AnonymousClass1 r6) {
            this(r1, r2, r3, r4, r5);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            ClassDefItem r0 = this.futureClazz.get();     // Catch: Throwable -> L5 ExecutionException -> L7
            if (r0 == null) goto L9;
            Main.access$2800(this.this$0, r0);     // Catch: Throwable -> L5 ExecutionException -> L7
            Main.access$2900(this.this$0, true);     // Catch: Throwable -> L5 ExecutionException -> L7
        L9:
            Boolean r02 = Boolean.TRUE;     // Catch: Throwable -> L5 ExecutionException -> L7
        L11:
            if (Main.access$1400(this.this$0).multiDex == false) goto L20;
            Object r1 = Main.access$1700(this.this$0);
            monitor-enter(r1);
            Main r2 = this.this$0;     // Catch: Throwable -> L17
            Main.access$1902(r2, Main.access$1900(r2) - this.maxMethodIdsInClass);     // Catch: Throwable -> L17
            Main r22 = this.this$0;     // Catch: Throwable -> L17
            Main.access$2002(r22, Main.access$2000(r22) - this.maxFieldIdsInClass);     // Catch: Throwable -> L17
            Main.access$1700(this.this$0).notifyAll();     // Catch: Throwable -> L17
            monitor-exit(r1);     // Catch: Throwable -> L17
            return r02;
        L17:
            th = move-exception;
            throw th;
        L20:
            return r02;
        L7:
            e = move-exception;
            Throwable r12 = e.getCause();     // Catch: Throwable -> L5
            if ((r12 instanceof Exception) == true) goto L24;
            throw e;     // Catch: Throwable -> L5
        L24:
            throw ((Exception) r12);     // Catch: Throwable -> L5
        L5:
            th = move-exception;
            if (Main.access$1400(this.this$0).multiDex == true) goto L28;
        L35:
            throw th;
        L28:
            monitor-enter(Main.access$1700(this.this$0));
            Main r23 = this.this$0;     // Catch: Throwable -> L32
            Main.access$1902(r23, Main.access$1900(r23) - this.maxMethodIdsInClass);     // Catch: Throwable -> L32
            Main r24 = this.this$0;     // Catch: Throwable -> L32
            Main.access$2002(r24, Main.access$2000(r24) - this.maxFieldIdsInClass);     // Catch: Throwable -> L32
            Main.access$1700(this.this$0).notifyAll();     // Catch: Throwable -> L32
        L32:
            th = move-exception;
            throw th;
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Boolean call() {
            return call();
        }
    }

    public class ClassParserTask implements Callable<DirectClassFile> {
        byte[] bytes;
        String name;
        final /* synthetic */ Main this$0;

        private ClassParserTask(Main r1, String r2, byte[] r3) {
            this.this$0 = r1;
            this.name = r2;
            this.bytes = r3;
        }

        public /* synthetic */ ClassParserTask(Main r1, String r2, byte[] r3, AnonymousClass1 r4) {
            this(r1, r2, r3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public DirectClassFile call() {
            return Main.access$1600(this.this$0, this.name, this.bytes);
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ DirectClassFile call() {
            return call();
        }
    }

    public class ClassTranslatorTask implements Callable<ClassDefItem> {
        byte[] bytes;
        DirectClassFile classFile;
        String name;
        final /* synthetic */ Main this$0;

        private ClassTranslatorTask(Main r1, String r2, byte[] r3, DirectClassFile r4) {
            this.this$0 = r1;
            this.name = r2;
            this.bytes = r3;
            this.classFile = r4;
        }

        public /* synthetic */ ClassTranslatorTask(Main r1, String r2, byte[] r3, DirectClassFile r4, AnonymousClass1 r5) {
            this(r1, r2, r3, r4);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ClassDefItem call() {
            return Main.access$2700(this.this$0, this.bytes, this.classFile);
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ ClassDefItem call() {
            return call();
        }
    }

    public class DexWriter implements Callable<byte[]> {
        private final DexFile dexFile;
        final /* synthetic */ Main this$0;

        private DexWriter(Main r1, DexFile r2) {
            this.this$0 = r1;
            this.dexFile = r2;
        }

        public /* synthetic */ DexWriter(Main r1, DexFile r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ byte[] call() {
            return call2();
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: call, reason: avoid collision after fix types in other method */
        public byte[] call2() {
            return Main.access$3000(this.this$0, this.dexFile);
        }
    }

    public class DirectClassFileConsumer implements Callable<Boolean> {
        byte[] bytes;
        Future<DirectClassFile> dcff;
        String name;
        final /* synthetic */ Main this$0;

        private DirectClassFileConsumer(Main r1, String r2, byte[] r3, Future<DirectClassFile> r4) {
            this.this$0 = r1;
            this.name = r2;
            this.bytes = r3;
            this.dcff = r4;
        }

        public /* synthetic */ DirectClassFileConsumer(Main r1, String r2, byte[] r3, Future r4, AnonymousClass1 r5) {
            this(r1, r2, r3, r4);
        }

        public static /* synthetic */ Boolean access$700(DirectClassFileConsumer r0, DirectClassFile r1) {
            return r0.call(r1);
        }

        private Boolean call(DirectClassFile r15) {
            if (Main.access$1400(this.this$0).multiDex == false) goto L45;
            int r0 = r15.getConstantPool().size();
            int r1 = (r15.getMethods().size() + r0) + 2;
            int r02 = (r0 + r15.getFields().size()) + 9;
            Object r2 = Main.access$1700(this.this$0);
            monitor-enter(r2);
            DexFile r3 = Main.access$1800(this.this$0);     // Catch: Throwable -> L16
            monitor-enter(r3);     // Catch: Throwable -> L16
            int r4 = Main.access$1800(this.this$0).getMethodIds().items().size();     // Catch: Throwable -> L39
            int r5 = Main.access$1800(this.this$0).getFieldIds().items().size();     // Catch: Throwable -> L39
            monitor-exit(r3);     // Catch: Throwable -> L39
        L12:
            if (((r4 + r1) + Main.access$1900(this.this$0)) > Main.access$1400(this.this$0).maxNumberOfIdxPerDex) goto L19;
            if (((r5 + r02) + Main.access$2000(this.this$0)) > Main.access$1400(this.this$0).maxNumberOfIdxPerDex) goto L19;
        L26:
            Main r32 = this.this$0;     // Catch: Throwable -> L16
            Main.access$1902(r32, Main.access$1900(r32) + r1);     // Catch: Throwable -> L16
            Main r33 = this.this$0;     // Catch: Throwable -> L16
            Main.access$2002(r33, Main.access$2000(r33) + r02);     // Catch: Throwable -> L16
            monitor-exit(r2);     // Catch: Throwable -> L16
            int r7 = r02;
            int r6 = r1;
        L46:
            Main.access$2600(this.this$0).add(Main.access$2500(this.this$0).submit(new ClassDefItemConsumer(this.this$0, this.name, Main.access$2300(this.this$0).submit(new ClassTranslatorTask(this.this$0, this.name, this.bytes, r15, null)), r6, r7, null)));
            return Boolean.TRUE;
        L19:
            if (Main.access$1900(this.this$0) <= 0) goto L21;
        L52:
            Main.access$1700(this.this$0).wait();     // Catch: Throwable -> L16 InterruptedException -> L48
        L30:
            DexFile r34 = Main.access$1800(this.this$0);     // Catch: Throwable -> L16
            monitor-enter(r34);     // Catch: Throwable -> L16
            r4 = Main.access$1800(this.this$0).getMethodIds().items().size();     // Catch: Throwable -> L35
            r5 = Main.access$1800(this.this$0).getFieldIds().items().size();     // Catch: Throwable -> L35
            monitor-exit(r34);     // Catch: Throwable -> L35
        L35:
            th = move-exception;
            throw th;     // Catch: Throwable -> L16
        L21:
            if (Main.access$2000(this.this$0) > 0) goto L52;
            if (Main.access$1800(this.this$0).getClassDefs().items().size() <= 0) goto L26;
            Main.access$2100(this.this$0);     // Catch: Throwable -> L16
        L39:
            th = move-exception;
            throw th;     // Catch: Throwable -> L16
        L16:
            th = move-exception;
            throw th;
        L45:
            r6 = 0;
            r7 = 0;
            goto L46
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            return call(this.dcff.get());
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Boolean call() {
            return call();
        }
    }

    public class FileBytesConsumer implements ClassPathOpener.Consumer {
        final /* synthetic */ Main this$0;

        private FileBytesConsumer(Main r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ FileBytesConsumer(Main r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.Consumer
        public void onException(Exception r4) {
            if ((r4 instanceof StopProcessing) == true) goto L17;
            if ((r4 instanceof SimException) == false) goto L7;
            Main.access$1300(this.this$0).err.println("\nEXCEPTION FROM SIMULATION:");
            Main.access$1300(this.this$0).err.println(r4.getMessage() + "\n");
            Main.access$1300(this.this$0).err.println(((SimException) r4).getContext());
        L14:
            Main.access$1500(this.this$0).incrementAndGet();
            return;
        L7:
            boolean r0 = r4 instanceof ParseException;
            Main r1 = this.this$0;
            if (r0 == false) goto L13;
            Main.access$1300(r1).err.println("\nPARSE ERROR:");
            ParseException r42 = (ParseException) r4;
            boolean r02 = Main.access$1400(this.this$0).debug;
            Main r12 = this.this$0;
            if (r02 == false) goto L12;
            r42.printStackTrace(Main.access$1300(r12).err);
            goto L14
        L12:
            r42.printContext(Main.access$1300(r12).err);
            goto L14
        L13:
            Main.access$1300(r1).err.println("\nUNEXPECTED TOP-LEVEL EXCEPTION:");
            r4.printStackTrace(Main.access$1300(this.this$0).err);
            goto L14
        L17:
            throw ((StopProcessing) r4);
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.Consumer
        public void onProcessArchiveStart(File r3) {
            if (Main.access$1400(this.this$0).verbose == false) goto L6;
            Main.access$1300(this.this$0).out.println("processing archive " + r3 + "...");
            return;
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.Consumer
        public boolean processFileBytes(String r1, long r2, byte[] r4) {
            return Main.access$1200(this.this$0, r1, r2, r4);
        }
    }

    public class MainDexListFilter implements ClassPathOpener.FileNameFilter {
        final /* synthetic */ Main this$0;

        private MainDexListFilter(Main r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ MainDexListFilter(Main r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.FileNameFilter
        public boolean accept(String r2) {
            if (r2.endsWith(".class") == false) goto L6;
            String r22 = Main.access$900(r2);
            return Main.access$1000(this.this$0).contains(r22);
        L6:
            return true;
        }
    }

    public static class NotFilter implements ClassPathOpener.FileNameFilter {
        private final ClassPathOpener.FileNameFilter filter;

        private NotFilter(ClassPathOpener.FileNameFilter r1) {
            this.filter = r1;
        }

        public /* synthetic */ NotFilter(ClassPathOpener.FileNameFilter r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.FileNameFilter
        public boolean accept(String r1) {
            return !this.filter.accept(r1);
        }
    }

    public static class RemoveModuleInfoFilter implements ClassPathOpener.FileNameFilter {
        protected final ClassPathOpener.FileNameFilter delegate;

        public RemoveModuleInfoFilter(ClassPathOpener.FileNameFilter r1) {
            this.delegate = r1;
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.FileNameFilter
        public boolean accept(String r1) {
            if (this.delegate.accept(r1) == true) goto L5;
            return false;
        L5:
            if ("module-info.class".equals(r1) == true) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public static class StopProcessing extends RuntimeException {
        private StopProcessing() {
        }

        public /* synthetic */ StopProcessing(AnonymousClass1 r1) {
            this();
        }
    }

    static {
        CREATED_BY = new Attributes.Name("Created-By");
        JAVAX_CORE = new String[]{"accessibility", "crypto", "imageio", "management", "naming", "net", "print", "rmi", "security", "sip", "sound", "sql", "swing", "transaction", "xml"};
    }

    public Main(DxContext r3) {
        this.errors = new AtomicInteger(0);
        this.libraryDexBuffers = new ArrayList();
        this.addToDexFutures = new ArrayList();
        this.dexOutputFutures = new ArrayList();
        this.dexRotationLock = new Object();
        this.maxMethodIdsInProcess = 0;
        this.maxFieldIdsInProcess = 0;
        this.minimumFileAge = 0;
        this.classesInMainDex = null;
        this.dexOutputArrays = new ArrayList();
        this.humanOutWriter = null;
        this.context = r3;
    }

    public static /* synthetic */ Set access$1000(Main r0) {
        return r0.classesInMainDex;
    }

    public static /* synthetic */ void access$1100(String r0, Collection r1) {
        readPathsFromFile(r0, r1);
    }

    public static /* synthetic */ boolean access$1200(Main r0, String r1, long r2, byte[] r4) {
        return r0.processFileBytes(r1, r2, r4);
    }

    public static /* synthetic */ DxContext access$1300(Main r0) {
        return r0.context;
    }

    public static /* synthetic */ Arguments access$1400(Main r0) {
        return r0.args;
    }

    public static /* synthetic */ AtomicInteger access$1500(Main r0) {
        return r0.errors;
    }

    public static /* synthetic */ DirectClassFile access$1600(Main r0, String r1, byte[] r2) {
        return r0.parseClass(r1, r2);
    }

    public static /* synthetic */ Object access$1700(Main r0) {
        return r0.dexRotationLock;
    }

    public static /* synthetic */ DexFile access$1800(Main r0) {
        return r0.outputDex;
    }

    public static /* synthetic */ int access$1900(Main r0) {
        return r0.maxMethodIdsInProcess;
    }

    public static /* synthetic */ int access$1902(Main r0, int r1) {
        r0.maxMethodIdsInProcess = r1;
        return r1;
    }

    public static /* synthetic */ int access$2000(Main r0) {
        return r0.maxFieldIdsInProcess;
    }

    public static /* synthetic */ int access$2002(Main r0, int r1) {
        r0.maxFieldIdsInProcess = r1;
        return r1;
    }

    public static /* synthetic */ void access$2100(Main r0) {
        r0.rotateDexFile();
    }

    public static /* synthetic */ ExecutorService access$2300(Main r0) {
        return r0.classTranslatorPool;
    }

    public static /* synthetic */ ExecutorService access$2500(Main r0) {
        return r0.classDefItemConsumer;
    }

    public static /* synthetic */ List access$2600(Main r0) {
        return r0.addToDexFutures;
    }

    public static /* synthetic */ ClassDefItem access$2700(Main r0, byte[] r1, DirectClassFile r2) {
        return r0.translateClass(r1, r2);
    }

    public static /* synthetic */ boolean access$2800(Main r0, ClassDefItem r1) {
        return r0.addClassToDex(r1);
    }

    public static /* synthetic */ void access$2900(Main r0, boolean r1) {
        r0.updateStatus(r1);
    }

    public static /* synthetic */ byte[] access$3000(Main r0, DexFile r1) {
        return r0.writeDex(r1);
    }

    public static /* synthetic */ String access$900(String r0) {
        return fixPath(r0);
    }

    private boolean addClassToDex(ClassDefItem r2) {
        DexFile r0 = this.outputDex;
        monitor-enter(r0);
        this.outputDex.add(r2);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return true;
    L8:
        th = move-exception;
        throw th;
    }

    private void checkClassName(String r4) {
        if (r4.startsWith("java/") == false) goto L5;
    L11:
        this.context.err.println("\ntrouble processing \"" + r4 + "\":\n\nIll-advised or mistaken usage of a core class (java.* or javax.*)\nwhen not building a core library.\n\nThis is often due to inadvertently including a core library file\nin your application's project, when using an IDE (such as\nEclipse). If you are sure you're not intentionally defining a\ncore class, then this is the most likely explanation of what's\ngoing on.\n\nHowever, you might actually be trying to define a class in a core\nnamespace, the source of which you may have taken, for example,\nfrom a non-Android virtual machine project. This will most\nassuredly not work. At a minimum, it jeopardizes the\ncompatibility of your app with future versions of the platform.\nIt is also often of questionable legality.\n\nIf you really intend to build a core library -- which is only\nappropriate as part of creating a full virtual machine\ndistribution, as opposed to compiling an application -- then use\nthe \"--core-library\" option to suppress this error message.\n\nIf you go ahead and use \"--core-library\" but are in fact\nbuilding an application, then be forewarned that your application\nwill still fail to build or run, at some point. Please be\nprepared for angry customers who find, for example, that your\napplication ceases to function once they upgrade their operating\nsystem. You will be to blame for this problem.\n\nIf you are legitimately using some code that happens to be in a\ncore package, then the easiest safe alternative you have is to\nrepackage that code. That is, move the classes in question into\nyour own package namespace. This means that they will never be in\nconflict with core system classes. JarJar is a tool that may help\nyou in this endeavor. If you find that you cannot do this, then\nthat is an indication that the path you are on will ultimately\nlead to pain, suffering, grief, and lamentation.\n");
        this.errors.incrementAndGet();
        throw new StopProcessing(null);
    L5:
        if (r4.startsWith("javax/") == false) goto L10;
        int r0 = r4.indexOf(47, 6);
        if (r0 == (-1)) goto L11;
        if (Arrays.binarySearch(JAVAX_CORE, r4.substring(6, r0)) >= 0) goto L11;
        return;
    }

    public static void clearInternTables() {
        Prototype.clearInternTable();
        RegisterSpec.clearInternTable();
        CstType.clearInternTable();
        Type.clearInternTable();
    }

    private void closeOutput(OutputStream r1) {
        if (r1 == null) goto L9;
        r1.flush();
        if (r1 == this.context.out) goto L8;
        r1.close();
        return;
    L8:
        return;
    }

    private void createDexFile() {
        DexFile r0 = new DexFile(this.args.dexOptions);
        this.outputDex = r0;
        int r2 = this.args.dumpWidth;
        if (r2 == 0) goto L6;
        r0.setDumpWidth(r2);
        return;
    }

    private boolean createJar(String r10) {
        Manifest r0 = makeManifest();     // Catch: Exception -> L15
        OutputStream r102 = openOutput(r10);     // Catch: Exception -> L15
        JarOutputStream r1 = new JarOutputStream(r102, r0);     // Catch: Exception -> L15
        Iterator<Map.Entry<String, byte[]>> r02 = this.outputResources.entrySet().iterator();     // Catch: Throwable -> L9
    L4:
        if (r02.hasNext() == false) goto L13;
        Map.Entry<String, byte[]> r2 = r02.next();     // Catch: Throwable -> L9
        String r3 = r2.getKey();     // Catch: Throwable -> L9
        byte[] r22 = r2.getValue();     // Catch: Throwable -> L9
        JarEntry r4 = new JarEntry(r3);     // Catch: Throwable -> L9
        int r5 = r22.length;     // Catch: Throwable -> L9
        if (this.args.verbose == false) goto L11;
        this.context.out.println("writing " + r3 + "; size " + r5 + "...");     // Catch: Throwable -> L9
    L11:
        r4.setSize(r5);     // Catch: Throwable -> L9
        r1.putNextEntry(r4);     // Catch: Throwable -> L9
        r1.write(r22);     // Catch: Throwable -> L9
        r1.closeEntry();     // Catch: Throwable -> L9
        goto L4
    L13:
        r1.finish();     // Catch: Exception -> L15
        r1.flush();     // Catch: Exception -> L15
        closeOutput(r102);     // Catch: Exception -> L15
        return true;
    L9:
        th = move-exception;
        r1.finish();     // Catch: Exception -> L15
        r1.flush();     // Catch: Exception -> L15
        closeOutput(r102);     // Catch: Exception -> L15
        throw th;     // Catch: Exception -> L15
    L15:
        e = move-exception;
        boolean r03 = this.args.debug;
        DxContext r12 = this.context;
        if (r03 == false) goto L22;
        r12.err.println("\ntrouble writing output:");
        e.printStackTrace(this.context.err);
        return false;
    L22:
        r12.err.println("\ntrouble writing output: " + e.getMessage());
        return false;
    }

    private void dumpMethod(DexFile r11, String r12, OutputStreamWriter r13) {
        boolean r0 = r12.endsWith("*");
        int r2 = r12.lastIndexOf(46);
        if (r2 > 0) goto L5;
    L49:
        this.context.err.println("bogus fully-qualified method name: ".concat(r12));
        return;
    L5:
        if (r2 == (r12.length() - 1)) goto L49;
        String r1 = r12.substring(0, r2).replace('.', '/');
        String r22 = r12.substring(r2 + 1);
        ClassDefItem r112 = r11.getClassOrNull(r1);
        if (r112 != null) goto L11;
        this.context.err.println("no such class: " + r1);
        return;
    L11:
        if (r0 == false) goto L13;
        r22 = r22.substring(0, r22.length() - 1);
    L13:
        ArrayList<EncodedMethod> r14 = r112.getMethods();
        TreeMap r4 = new TreeMap();
        int r5 = r14.size();
        int r6 = 0;
    L14:
        if (r6 >= r5) goto L24;
        EncodedMethod r7 = r14.get(r6);
        r6 = r6 + 1;
        EncodedMethod r72 = r7;
        String r8 = r72.getName().getString();
        if (r0 == true) goto L18;
    L19:
        if (r0 == true) goto L14;
        if (r8.equals(r22) == false) goto L14;
    L22:
        r4.put(r72.getRef().getNat(), r72);
        goto L14
    L18:
        if (r8.startsWith(r22) == true) goto L22;
    L24:
        if (r4.size() != 0) goto L27;
        this.context.err.println("no such method: ".concat(r12));
        return;
    L27:
        PrintWriter r122 = new PrintWriter(r13);
        Iterator r132 = r4.values().iterator();
    L29:
        if (r132.hasNext() == false) goto L47;
        EncodedMethod r02 = (EncodedMethod) r132.next();
        r02.debugPrint(r122, this.args.verboseDump);
        CstString r15 = r112.getSourceFile();
        if (r15 == null) goto L33;
        r122.println("  source file: " + r15.toQuoted());
    L33:
        Annotations r16 = r112.getMethodAnnotations(r02.getRef());
        AnnotationsList r03 = r112.getParameterAnnotations(r02.getRef());
        if (r16 == null) goto L39;
        r122.println("  method annotations:");
        Iterator<Annotation> r17 = r16.getAnnotations().iterator();
    L37:
        if (r17.hasNext() == false) goto L39;
        r122.println("    " + r17.next());
    L39:
        if (r03 == null) goto L29;
        r122.println("  parameter annotations:");
        int r18 = r03.size();
        int r23 = 0;
    L41:
        if (r23 >= r18) goto L29;
        r122.println("    parameter " + r23);
        Iterator<Annotation> r42 = r03.get(r23).getAnnotations().iterator();
    L44:
        if (r42.hasNext() == false) goto L46;
        r122.println("      " + r42.next());
        goto L44
    L46:
        r23 = r23 + 1;
        goto L41
    L47:
        r122.flush();
    }

    private static String fixPath(String r2) {
        if (File.separatorChar != '\\') goto L5;
        r2 = r2.replace('\\', '/');
    L5:
        int r0 = r2.lastIndexOf("/./");
        if (r0 == (-1)) goto L10;
        return r2.substring(r0 + 3);
    L10:
        if (r2.startsWith("./") == true) goto L12;
        return r2;
    L12:
        return r2.substring(2);
    }

    private static String getDexFileName(int r2) {
        if (r2 != 0) goto L6;
        return "classes.dex";
    L6:
        return DEX_PREFIX + (r2 + 1) + DEX_EXTENSION;
    }

    public static void main(String[] r2) {
        DxContext r0 = new DxContext();
        Arguments r1 = new Arguments(r0);
        Arguments.access$000(r1, r2);
        int r22 = new Main(r0).runDx(r1);
        if (r22 == 0) goto L6;
        System.exit(r22);
        return;
    }

    private Manifest makeManifest() {
        byte[] r0 = this.outputResources.get(MANIFEST_NAME);
        if (r0 != null) goto L5;
        Manifest r4 = new Manifest();
        Attributes r02 = r4.getMainAttributes();
        r02.put(Attributes.Name.MANIFEST_VERSION, "1.0");
    L6:
        Attributes.Name r1 = CREATED_BY;
        String r2 = r02.getValue(r1);
        if (r2 != null) goto L9;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        r02.put(r1, r22.concat("dx 1.16"));
        r02.putValue("Dex-Location", "classes.dex");
        return r4;
    L9:
        r22 = r2.concat(" + ");
        goto L10
    L5:
        Manifest r23 = new Manifest(new ByteArrayInputStream(r0));
        r02 = r23.getMainAttributes();
        this.outputResources.remove(MANIFEST_NAME);
        r4 = r23;
        goto L6
    }

    private byte[] mergeIncremental(byte[] r3, File r4) {
        if (r3 == null) goto L5;
        Dex r1 = new Dex(r3);
    L7:
        if (r4.exists() == false) goto L9;
        Dex r32 = new Dex(r4);
    L10:
        if (r1 != null) goto L13;
        if (r32 != null) goto L13;
        return null;
    L13:
        if (r1 != null) goto L15;
        r1 = r32;
    L18:
        ByteArrayOutputStream r2 = new ByteArrayOutputStream();
        r1.writeTo(r2);
        return r2.toByteArray();
    L15:
        if (r32 == null) goto L18;
        r1 = new DexMerger(new Dex[]{r1, r32}, CollisionPolicy.KEEP_FIRST, this.context).merge();
        goto L18
    L9:
        r32 = null;
        goto L10
    L5:
        r1 = null;
        goto L7
    }

    private byte[] mergeLibraryDexBuffers(byte[] r4) {
        ArrayList r0 = new ArrayList();
        if (r4 == null) goto L5;
        r0.add(new Dex(r4));
    L5:
        Iterator<byte[]> r42 = this.libraryDexBuffers.iterator();
    L7:
        if (r42.hasNext() == false) goto L10;
        r0.add(new Dex(r42.next()));
        goto L7
    L10:
        if (r0.isEmpty() == false) goto L14;
        return null;
    L14:
        return new DexMerger((Dex[]) r0.toArray(new Dex[r0.size()]), CollisionPolicy.FAIL, this.context).merge().getBytes();
    }

    private OutputStream openOutput(String r2) {
        if (r2.equals("-") == true) goto L10;
        if (r2.startsWith("-.") == true) goto L10;
        return new FileOutputStream(r2);
    L10:
        return this.context.out;
    }

    private DirectClassFile parseClass(String r2, byte[] r3) {
        DirectClassFile r0 = new DirectClassFile(r3, r2, this.args.cfOptions.strictNameCheck);
        r0.setAttributeFactory(StdAttributeFactory.THE_ONE);
        r0.getMagic();
        return r0;
    }

    private boolean processAllFiles() {
        createDexFile();
        if (this.args.jarOutput == false) goto L5;
        this.outputResources = new TreeMap();
    L5:
        this.anyFilesProcessed = false;
        String[] r0 = this.args.fileNames;
        Arrays.sort(r0);
        int r3 = this.args.numThreads;
        this.classTranslatorPool = new ThreadPoolExecutor(r3, r3, 0, TimeUnit.SECONDS, new ArrayBlockingQueue(this.args.numThreads * 2, true), new ThreadPoolExecutor.CallerRunsPolicy());
        this.classDefItemConsumer = Executors.newSingleThreadExecutor();
        Arguments r2 = this.args;     // Catch: StopProcessing -> L97
        if (r2.mainDexListFile != null) goto L9;
        RemoveModuleInfoFilter r22 = new RemoveModuleInfoFilter(ClassPathOpener.acceptAll);     // Catch: StopProcessing -> L97
        int r32 = 0;
    L44:
        if (r32 >= r0.length) goto L105;
        processOne(r0[r32], r22);     // Catch: StopProcessing -> L97
        r32 = r32 + 1;
        goto L44
    L9:
        if (r2.strictNameCheck == false) goto L11;
        ClassPathOpener.FileNameFilter r23 = new MainDexListFilter(this, null);     // Catch: StopProcessing -> L97
    L12:
        int r4 = 0;
    L14:
        if (r4 >= r0.length) goto L17;
        processOne(r0[r4], r23);     // Catch: StopProcessing -> L97
        r4 = r4 + 1;     // Catch: StopProcessing -> L97
        goto L14
    L17:
        if (this.dexOutputFutures.size() > 0) goto L41;
        if (this.args.minimalMainDex == false) goto L36;
        Object r42 = this.dexRotationLock;     // Catch: StopProcessing -> L97
        monitor-enter(r42);     // Catch: StopProcessing -> L97
    L100:
    L30:
        th = move-exception;
        throw th;     // Catch: StopProcessing -> L97
    L23:
        if (this.maxMethodIdsInProcess <= 0) goto L25;
    L98:
        this.dexRotationLock.wait();     // Catch: InterruptedException -> L96 Throwable -> L30
        goto L100
    L25:
        if (this.maxFieldIdsInProcess > 0) goto L98;
        monitor-exit(r42);     // Catch: Throwable -> L30
        rotateDexFile();     // Catch: StopProcessing -> L97
        goto L36
    L36:
        RemoveModuleInfoFilter r43 = new RemoveModuleInfoFilter(new NotFilter(r23, null));     // Catch: StopProcessing -> L97
        int r24 = 0;
    L38:
        if (r24 >= r0.length) goto L105;
        processOne(r0[r24], r43);     // Catch: StopProcessing -> L97
        r24 = r24 + 1;     // Catch: StopProcessing -> L97
        goto L38
    L41:
        throw new DexException("Too many classes in --main-dex-list, main dex capacity exceeded");     // Catch: StopProcessing -> L97
    L11:
        r23 = new BestEffortMainDexListFilter(this);     // Catch: StopProcessing -> L97
    L105:
        this.classTranslatorPool.shutdown();     // Catch: Exception -> L53 InterruptedException -> L55
        ExecutorService r02 = this.classTranslatorPool;     // Catch: Exception -> L53 InterruptedException -> L55
        TimeUnit r25 = TimeUnit.SECONDS;     // Catch: Exception -> L53 InterruptedException -> L55
        r02.awaitTermination(600, r25);     // Catch: Exception -> L53 InterruptedException -> L55
        this.classDefItemConsumer.shutdown();     // Catch: Exception -> L53 InterruptedException -> L55
        this.classDefItemConsumer.awaitTermination(600, r25);     // Catch: Exception -> L53 InterruptedException -> L55
        Iterator<Future<Boolean>> r26 = this.addToDexFutures.iterator();     // Catch: Exception -> L53 InterruptedException -> L55
    L49:
        if (r26.hasNext() == false) goto L67;
        r26.next().get();     // Catch: Exception -> L53 InterruptedException -> L55 ExecutionException -> L57
    L57:
        e = move-exception;
        if (this.errors.incrementAndGet() >= 10) goto L66;
        boolean r33 = this.args.debug;     // Catch: Exception -> L53 InterruptedException -> L55
        DxContext r44 = this.context;
        if (r33 == true) goto L63;
        r44.err.println("Uncaught translation error: " + e.getCause());     // Catch: Exception -> L53 InterruptedException -> L55
        goto L49
    L63:
        r44.err.println("Uncaught translation error:");     // Catch: Exception -> L53 InterruptedException -> L55
        e.getCause().printStackTrace(this.context.err);     // Catch: Exception -> L53 InterruptedException -> L55
        goto L49
    L66:
        throw new InterruptedException("Too many errors");     // Catch: Exception -> L53 InterruptedException -> L55
    L67:
        int r03 = this.errors.get();
        if (r03 == 0) goto L76;
        PrintStream r11 = this.context.err;
        StringBuilder r27 = new StringBuilder();
        r27.append(r03);
        r27.append(" error");
        if (r03 != 1) goto L72;
        String r04 = _UrlKt.FRAGMENT_ENCODE_SET;
    L73:
        r27.append(r04);
        r27.append("; aborting");
        r11.println(r27.toString());
        return false;
    L72:
        r04 = "s";
        goto L73
    L76:
        if (this.args.incremental == false) goto L81;
        if (this.anyFilesProcessed == true) goto L81;
        return true;
    L81:
        if (this.anyFilesProcessed == false) goto L83;
    L86:
        Arguments r05 = this.args;
        if (r05.optimize == true) goto L89;
    L91:
        return true;
    L89:
        if (r05.statistics == false) goto L91;
        DxContext r112 = this.context;
        r112.codeStatistics.dumpStatistics(r112.out);
        goto L91
    L83:
        if (this.args.emptyOk == true) goto L86;
        this.context.err.println("no classfiles specified");
        return false;
    L55:
        e = move-exception;
        this.classTranslatorPool.shutdownNow();
        this.classDefItemConsumer.shutdownNow();
        Make$Map$$ExternalSyntheticBUOutline0.m("Translation has been interrupted", e);
        return false;
    L53:
        e = move-exception;
        this.classTranslatorPool.shutdownNow();
        this.classDefItemConsumer.shutdownNow();
        e.printStackTrace(this.context.out);
        Make$Map$$ExternalSyntheticBUOutline0.m("Unexpected exception in translator thread.", e);
        return false;
    }

    private boolean processClass(String r8, byte[] r9) {
        if (this.args.coreLibrary == true) goto L14;
        checkClassName(r8);
    L14:
        DirectClassFileConsumer.access$700(new DirectClassFileConsumer(this, r8, r9, null, null), new ClassParserTask(this, r8, r9, null).call());     // Catch: Exception -> L8 ParseException -> L11
        return true;
    L11:
        e = move-exception;
        throw e;
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Exception parsing classes", e);
        return false;
    }

    private boolean processFileBytes(String r9, long r10, byte[] r12) {
        boolean r0 = r9.endsWith(".class");
        boolean r1 = r9.equals("classes.dex");
        if (this.outputResources == null) goto L5;
        boolean r2 = true;
    L6:
        if (r0 == true) goto L14;
        if (r1 == true) goto L14;
        if (r2 == true) goto L14;
        if (this.args.verbose == false) goto L12;
        this.context.out.println("ignored resource ".concat(r9));
    L12:
        return false;
    L14:
        if (this.args.verbose == false) goto L16;
        this.context.out.println("processing " + r9 + "...");
    L16:
        String r92 = fixPath(r9);
        if (r0 == false) goto L34;
        if (r2 == false) goto L30;
        if (this.args.keepClassesInJar == false) goto L30;
        TreeMap<String, byte[]> r02 = this.outputResources;
        monitor-enter(r02);
        this.outputResources.put(r92, r12);     // Catch: Throwable -> L26
        monitor-exit(r02);     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        throw th;
    L30:
        if (r10 >= this.minimumFileAge) goto L32;
        return true;
    L32:
        processClass(r92, r12);
        return false;
    L34:
        if (r1 == false) goto L43;
        List<byte[]> r102 = this.libraryDexBuffers;
        monitor-enter(r102);
        this.libraryDexBuffers.add(r12);     // Catch: Throwable -> L40
        monitor-exit(r102);     // Catch: Throwable -> L40
        return true;
    L40:
        th = move-exception;
        throw th;
    L43:
        TreeMap<String, byte[]> r103 = this.outputResources;
        monitor-enter(r103);
        this.outputResources.put(r92, r12);     // Catch: Throwable -> L48
        monitor-exit(r103);     // Catch: Throwable -> L48
        return true;
    L48:
        th = move-exception;
        throw th;
    L5:
        r2 = false;
        goto L6
    }

    private void processOne(String r4, ClassPathOpener.FileNameFilter r5) {
        if (new ClassPathOpener(r4, true, r5, new FileBytesConsumer(this, null)).process() == false) goto L6;
        updateStatus(true);
        return;
    }

    private static void readPathsFromFile(String r2, Collection<String> r3) {
        BufferedReader r0 = null;
        BufferedReader r22 = new BufferedReader(new FileReader(r2));     // Catch: Throwable -> L12
    L18:
        String r02 = r22.readLine();     // Catch: Throwable -> L8
        if (r02 == null) goto L10;
        r3.add(fixPath(r02));     // Catch: Throwable -> L8
        goto L18
    L10:
        r22.close();
        return;
    L8:
        th = th;
        r0 = r22;
    L13:
        if (r0 == null) goto L15;
        r0.close();
    L15:
        throw th;
    L12:
        th = th;
        goto L13
    }

    private void rotateDexFile() {
        DexFile r0 = this.outputDex;
        if (r0 == null) goto L8;
        ExecutorService r1 = this.dexOutPool;
        if (r1 == null) goto L7;
        this.dexOutputFutures.add(r1.submit(new DexWriter(this, r0, null)));
        goto L8
    L7:
        this.dexOutputArrays.add(writeDex(r0));
    L8:
        createDexFile();
    }

    public static int run(Arguments r2) {
        return new Main(new DxContext()).runDx(r2);
    }

    private int runMonoDex() {
        Arguments r0 = this.args;
        if (r0.incremental == true) goto L5;
        File r02 = null;
    L13:
        if (processAllFiles() == true) goto L17;
        return 1;
    L17:
        if (this.args.incremental == false) goto L22;
        if (this.anyFilesProcessed == true) goto L22;
        return 0;
    L22:
        if (this.outputDex.isEmpty() == true) goto L24;
    L27:
        byte[] r1 = writeDex(this.outputDex);
        if (r1 != null) goto L32;
        return 2;
    L32:
        if (this.args.incremental == false) goto L34;
        r1 = mergeIncremental(r1, r02);
    L34:
        byte[] r03 = mergeLibraryDexBuffers(r1);
        Arguments r12 = this.args;
        if (r12.jarOutput == false) goto L43;
        this.outputDex = null;
        if (r03 == null) goto L40;
        this.outputResources.put("classes.dex", r03);
    L40:
        if (createJar(this.args.outName) == true) goto L47;
        return 3;
    L47:
        return 0;
    L43:
        if (r03 == null) goto L47;
        String r13 = r12.outName;
        if (r13 == null) goto L47;
        OutputStream r14 = openOutput(r13);
        r14.write(r03);
        closeOutput(r14);
        goto L47
    L24:
        if (this.args.humanOutName != null) goto L27;
        r1 = null;
        goto L32
    L5:
        if (r0.outName != null) goto L8;
        this.context.err.println("error: no incremental output name specified");
        return -1;
    L8:
        r02 = new File(this.args.outName);
        if (r02.exists() == false) goto L13;
        this.minimumFileAge = r02.lastModified();
        goto L13
    }

    private int runMultiDex() {
        if (this.args.mainDexListFile == null) goto L5;
        HashSet r0 = new HashSet();
        this.classesInMainDex = r0;
        readPathsFromFile(this.args.mainDexListFile, r0);
    L5:
        this.dexOutPool = Executors.newFixedThreadPool(this.args.numThreads);
        if (processAllFiles() == true) goto L10;
        return 1;
    L10:
        if (this.libraryDexBuffers.isEmpty() == false) goto L51;
        DexFile r02 = this.outputDex;
        if (r02 == null) goto L54;
        this.dexOutputFutures.add(this.dexOutPool.submit(new DexWriter(this, r02, null)));
        this.outputDex = null;
    L54:
        this.dexOutPool.shutdown();     // Catch: Exception -> L46 InterruptedException -> L48
        if (this.dexOutPool.awaitTermination(600, TimeUnit.SECONDS) == false) goto L45;
        Iterator<Future<byte[]>> r1 = this.dexOutputFutures.iterator();     // Catch: Exception -> L46 InterruptedException -> L48
    L19:
        if (r1.hasNext() == false) goto L22;
        this.dexOutputArrays.add(r1.next().get());     // Catch: Exception -> L46 InterruptedException -> L48
        goto L19
    L22:
        Arguments r12 = this.args;
        if (r12.jarOutput == false) goto L33;
        int r13 = 0;
    L26:
        if (r13 >= this.dexOutputArrays.size()) goto L29;
        this.outputResources.put(getDexFileName(r13), this.dexOutputArrays.get(r13));
        r13 = r13 + 1;
        goto L26
    L29:
        if (createJar(this.args.outName) == true) goto L43;
        return 3;
    L43:
        return 0;
    L33:
        if (r12.outName == null) goto L43;
        File r14 = new File(this.args.outName);
        int r2 = 0;
    L36:
        if (r2 >= this.dexOutputArrays.size()) goto L43;
        FileOutputStream r3 = new FileOutputStream(new File(r14, getDexFileName(r2)));
        r3.write(this.dexOutputArrays.get(r2));     // Catch: Throwable -> L40
        closeOutput(r3);
        r2 = r2 + 1;
    L40:
        th = move-exception;
        closeOutput(r3);
        throw th;
    L45:
        throw new RuntimeException("Timed out waiting for dex writer threads.");     // Catch: Exception -> L46 InterruptedException -> L48
    L48:
        this.dexOutPool.shutdownNow();
        GlShader$$ExternalSyntheticBUOutline1.m("A dex writer thread has been interrupted.");
        return 0;
    L46:
        this.dexOutPool.shutdownNow();
        GlShader$$ExternalSyntheticBUOutline1.m("Unexpected exception in dex writer thread");
        return 0;
    L51:
        throw new DexException("Library dex files are not supported in multi-dex mode");
    }

    private ClassDefItem translateClass(byte[] r7, DirectClassFile r8) {
        DxContext r0 = this.context;     // Catch: ParseException -> L4
        Arguments r1 = this.args;     // Catch: ParseException -> L4
        return CfTranslator.translate(r0, r8, r7, r1.cfOptions, r1.dexOptions, this.outputDex);
    L4:
        e = move-exception;
        this.context.err.println("\ntrouble processing:");
        boolean r82 = this.args.debug;
        DxContext r02 = this.context;
        if (r82 == false) goto L8;
        e.printStackTrace(r02.err);
    L9:
        this.errors.incrementAndGet();
        return null;
    L8:
        e.printContext(r02.err);
        goto L9
    }

    private void updateStatus(boolean r2) {
        this.anyFilesProcessed = r2 | this.anyFilesProcessed;
    }

    private byte[] writeDex(DexFile r4) {
        Arguments r1 = this.args;     // Catch: Throwable -> L6
        if (r1.methodToDump == null) goto L8;
        r4.toDex(null, false);     // Catch: Throwable -> L6
        dumpMethod(r4, this.args.methodToDump, this.humanOutWriter);     // Catch: Throwable -> L6
        byte[] r12 = null;
    L10:
        if (this.args.statistics == false) goto L12;
        this.context.out.println(r4.getStatistics().toHuman());     // Catch: Throwable -> L6
    L12:
        OutputStreamWriter r42 = this.humanOutWriter;     // Catch: Exception -> L16
        if (r42 == null) goto L18;
        r42.flush();     // Catch: Exception -> L16
        return r12;
    L18:
        return r12;
    L8:
        r12 = r4.toDex(this.humanOutWriter, r1.verboseDump);     // Catch: Throwable -> L6
    L16:
        e = move-exception;
        boolean r13 = this.args.debug;
        DxContext r2 = this.context;
        if (r13 == false) goto L26;
        r2.err.println("\ntrouble writing output:");
        e.printStackTrace(this.context.err);
    L27:
        return null;
    L26:
        r2.err.println("\ntrouble writing output: " + e.getMessage());
    L6:
        th = move-exception;
        OutputStreamWriter r14 = this.humanOutWriter;     // Catch: Exception -> L16
        if (r14 == null) goto L22;
        r14.flush();     // Catch: Exception -> L16
    L22:
        throw th;     // Catch: Exception -> L16
    }

    public int runDx(Arguments r3) {
        this.errors.set(0);
        this.libraryDexBuffers.clear();
        this.args = r3;
        r3.makeOptionsObjects();
        String r32 = this.args.humanOutName;
        if (r32 == null) goto L5;
        OutputStream r33 = openOutput(r32);
        this.humanOutWriter = new OutputStreamWriter(r33);
    L18:
    L11:
        th = move-exception;
        closeOutput(r33);
        throw th;
    L7:
        if (this.args.multiDex == false) goto L13;
        int r0 = runMultiDex();     // Catch: Throwable -> L11
        closeOutput(r33);
        return r0;
    L13:
        int r02 = runMonoDex();     // Catch: Throwable -> L11
        closeOutput(r33);
        return r02;
    L5:
        r33 = null;
        goto L18
    }
}
