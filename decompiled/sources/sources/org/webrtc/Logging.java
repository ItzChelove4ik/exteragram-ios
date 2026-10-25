package org.webrtc;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes6.dex */
public class Logging {
    private static final Logger fallbackLogger = null;
    private static Loggable loggable;
    private static Severity loggableSeverity;
    private static volatile boolean loggingEnabled;

    /* renamed from: org.webrtc.Logging$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$Logging$Severity = null;

        static {
            int[] r0 = new int[Severity.values().length];
            $SwitchMap$org$webrtc$Logging$Severity = r0;
            r0[Severity.LS_ERROR.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$org$webrtc$Logging$Severity[Severity.LS_WARNING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$org$webrtc$Logging$Severity[Severity.LS_INFO.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public enum Severity extends Enum<Severity> {
        private static final /* synthetic */ Severity[] $VALUES = null;
        public static final Severity LS_ERROR = null;
        public static final Severity LS_INFO = null;
        public static final Severity LS_NONE = null;
        public static final Severity LS_VERBOSE = null;
        public static final Severity LS_WARNING = null;

        private static /* synthetic */ Severity[] $values() {
            return new Severity[]{LS_VERBOSE, LS_INFO, LS_WARNING, LS_ERROR, LS_NONE};
        }

        static {
            LS_VERBOSE = new Severity("LS_VERBOSE", 0);
            LS_INFO = new Severity("LS_INFO", 1);
            LS_WARNING = new Severity("LS_WARNING", 2);
            LS_ERROR = new Severity("LS_ERROR", 3);
            LS_NONE = new Severity("LS_NONE", 4);
            $VALUES = $values();
        }

        Severity(String r1, int r2) {
        }

        public static Severity valueOf(String r1) {
            return (Severity) Enum.valueOf(Severity.class, r1);
        }

        public static Severity[] values() {
            return (Severity[]) $VALUES.clone();
        }
    }

    @Deprecated
    public enum TraceLevel extends Enum<TraceLevel> {
        private static final /* synthetic */ TraceLevel[] $VALUES = null;
        public static final TraceLevel TRACE_ALL = null;
        public static final TraceLevel TRACE_APICALL = null;
        public static final TraceLevel TRACE_CRITICAL = null;
        public static final TraceLevel TRACE_DEBUG = null;
        public static final TraceLevel TRACE_DEFAULT = null;
        public static final TraceLevel TRACE_ERROR = null;
        public static final TraceLevel TRACE_INFO = null;
        public static final TraceLevel TRACE_MEMORY = null;
        public static final TraceLevel TRACE_MODULECALL = null;
        public static final TraceLevel TRACE_NONE = null;
        public static final TraceLevel TRACE_STATEINFO = null;
        public static final TraceLevel TRACE_STREAM = null;
        public static final TraceLevel TRACE_TERSEINFO = null;
        public static final TraceLevel TRACE_TIMER = null;
        public static final TraceLevel TRACE_WARNING = null;
        public final int level;

        private static /* synthetic */ TraceLevel[] $values() {
            return new TraceLevel[]{TRACE_NONE, TRACE_STATEINFO, TRACE_WARNING, TRACE_ERROR, TRACE_CRITICAL, TRACE_APICALL, TRACE_DEFAULT, TRACE_MODULECALL, TRACE_MEMORY, TRACE_TIMER, TRACE_STREAM, TRACE_DEBUG, TRACE_INFO, TRACE_TERSEINFO, TRACE_ALL};
        }

        static {
            TRACE_NONE = new TraceLevel("TRACE_NONE", 0, 0);
            TRACE_STATEINFO = new TraceLevel("TRACE_STATEINFO", 1, 1);
            TRACE_WARNING = new TraceLevel("TRACE_WARNING", 2, 2);
            TRACE_ERROR = new TraceLevel("TRACE_ERROR", 3, 4);
            TRACE_CRITICAL = new TraceLevel("TRACE_CRITICAL", 4, 8);
            TRACE_APICALL = new TraceLevel("TRACE_APICALL", 5, 16);
            TRACE_DEFAULT = new TraceLevel("TRACE_DEFAULT", 6, 255);
            TRACE_MODULECALL = new TraceLevel("TRACE_MODULECALL", 7, 32);
            TRACE_MEMORY = new TraceLevel("TRACE_MEMORY", 8, 256);
            TRACE_TIMER = new TraceLevel("TRACE_TIMER", 9, 512);
            TRACE_STREAM = new TraceLevel("TRACE_STREAM", 10, 1024);
            TRACE_DEBUG = new TraceLevel("TRACE_DEBUG", 11, 2048);
            TRACE_INFO = new TraceLevel("TRACE_INFO", 12, 4096);
            TRACE_TERSEINFO = new TraceLevel("TRACE_TERSEINFO", 13, 8192);
            TRACE_ALL = new TraceLevel("TRACE_ALL", 14, 65535);
            $VALUES = $values();
        }

        TraceLevel(String r1, int r2, int r3) {
            this.level = r3;
        }

        public static TraceLevel valueOf(String r1) {
            return (TraceLevel) Enum.valueOf(TraceLevel.class, r1);
        }

        public static TraceLevel[] values() {
            return (TraceLevel[]) $VALUES.clone();
        }
    }

    static {
        fallbackLogger = createFallbackLogger();
    }

    public Logging() {
    }

    private static Logger createFallbackLogger() {
        Logger r0 = Logger.getLogger("org.webrtc.Logging");
        r0.setLevel(Level.ALL);
        return r0;
    }

    public static void d(String r1, String r2) {
        log(Severity.LS_INFO, r1, r2);
    }

    public static void deleteInjectedLoggable() {
        loggable = null;
    }

    public static void e(String r1, String r2) {
        log(Severity.LS_ERROR, r1, r2);
    }

    public static void e(String r1, String r2, Throwable r3) {
        Severity r0 = Severity.LS_ERROR;
        log(r0, r1, r2);
        log(r0, r1, r3.toString());
        log(r0, r1, getStackTraceString(r3));
    }

    public static void enableLogThreads() {
        nativeEnableLogThreads();
    }

    public static void enableLogTimeStamps() {
        nativeEnableLogTimeStamps();
    }

    public static synchronized void enableLogToDebugOutput(Severity r2) {
        monitor-enter(Logging.class);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (loggable != null) goto L12;
        nativeEnableLogToDebugOutput(r2.ordinal());     // Catch: Throwable -> L9
        loggingEnabled = true;     // Catch: Throwable -> L9
        monitor-exit(Logging.class);
        return;
    L12:
        throw new IllegalStateException("Logging to native debug output not supported while Loggable is injected. Delete the Loggable before calling this method.");     // Catch: Throwable -> L9
    }

    @Deprecated
    public static void enableTracing(String r0, EnumSet<TraceLevel> r1) {
    }

    private static String getStackTraceString(Throwable r2) {
        if (r2 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        StringWriter r0 = new StringWriter();
        r2.printStackTrace(new PrintWriter(r0));
        return r0.toString();
    }

    public static void injectLoggable(Loggable r0, Severity r1) {
        if (r0 == null) goto L5;
        loggable = r0;
        loggableSeverity = r1;
        return;
    }

    public static void log(Severity r2, String r3, String r4) {
        if (r3 == null) goto L27;
        if (r4 == null) goto L27;
        if (loggable == null) goto L12;
        if (r2.ordinal() >= loggableSeverity.ordinal()) goto L9;
        return;
    L9:
        loggable.onLogMessage(r4, r2, r3);
        return;
    L12:
        if (loggingEnabled == false) goto L15;
        nativeLog(r2.ordinal(), r3, r4);
        return;
    L15:
        int r22 = AnonymousClass1.$SwitchMap$org$webrtc$Logging$Severity[r2.ordinal()];
        if (r22 != 1) goto L18;
        Level r23 = Level.SEVERE;
    L25:
        fallbackLogger.log(r23, r3 + ": " + r4);
        return;
    L18:
        if (r22 != 2) goto L20;
        r23 = Level.WARNING;
        goto L25
    L20:
        if (r22 == 3) goto L22;
        r23 = Level.FINE;
        goto L25
    L22:
        r23 = Level.INFO;
    L27:
        f$$ExternalSyntheticBUOutline1.m("Logging tag or message may not be null.");
    }

    private static native void nativeEnableLogThreads();

    private static native void nativeEnableLogTimeStamps();

    private static native void nativeEnableLogToDebugOutput(int r0);

    private static native void nativeLog(int r0, String r1, String r2);

    public static void v(String r1, String r2) {
        log(Severity.LS_VERBOSE, r1, r2);
    }

    public static void w(String r1, String r2) {
        log(Severity.LS_WARNING, r1, r2);
    }

    public static void w(String r1, String r2, Throwable r3) {
        Severity r0 = Severity.LS_WARNING;
        log(r0, r1, r2);
        log(r0, r1, r3.toString());
        log(r0, r1, getStackTraceString(r3));
    }
}
