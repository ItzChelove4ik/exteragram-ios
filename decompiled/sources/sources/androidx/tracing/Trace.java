package androidx.tracing;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\tH\u0007¢\u0006\u0004\b\f\u0010\u0003J\u001f\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0012\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0015\u0010\u0011J\u001f\u0010\u0016\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0016\u0010\u0011J\u001f\u0010\u0017\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0017\u0010\u0011J\u001f\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0018\u0010\u0011J#\u0010\u001c\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00072\n\u0010\u001b\u001a\u00060\u0019j\u0002`\u001aH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u0007*\u00020\u0007H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u00020 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010$\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010&\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010%R\u0018\u0010'\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010%R\u0018\u0010(\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010%R\u001a\u0010)\u001a\u00020\u00048BX\u0082\u0004¢\u0006\f\u0012\u0004\b*\u0010\u0003\u001a\u0004\b)\u0010\u0006¨\u0006+"}, d2 = {"Landroidx/tracing/Trace;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "isEnabled", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "label", _UrlKt.FRAGMENT_ENCODE_SET, "beginSection", "(Ljava/lang/String;)V", "endSection", "methodName", _UrlKt.FRAGMENT_ENCODE_SET, "cookie", "beginAsyncSection", "(Ljava/lang/String;I)V", "endAsyncSection", "counterName", "counterValue", "setCounter", "beginAsyncSectionFallback", "endAsyncSectionFallback", "setCounterFallback", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "handleException", "(Ljava/lang/String;Ljava/lang/Exception;)V", "truncatedTraceSectionLabel", "(Ljava/lang/String;)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "traceTagApp", "J", "Ljava/lang/reflect/Method;", "isTagEnabledMethod", "Ljava/lang/reflect/Method;", "asyncTraceBeginMethod", "asyncTraceEndMethod", "traceCounterMethod", "isEnabledFallback", "isEnabledFallback$annotations", "tracing"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTrace.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Trace.android.kt\nandroidx/tracing/Trace\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,402:1\n1#2:403\n*E\n"})
/* loaded from: classes.dex */
public final class Trace {
    public static final Trace INSTANCE = null;
    private static Method asyncTraceBeginMethod;
    private static Method asyncTraceEndMethod;
    private static Method isTagEnabledMethod;
    private static Method traceCounterMethod;
    private static long traceTagApp;

    static {
        INSTANCE = new Trace();
    }

    private Trace() {
    }

    @JvmStatic
    public static final void beginAsyncSection(String r2, int r3) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        TraceApi29Impl.INSTANCE.beginAsyncSection(INSTANCE.truncatedTraceSectionLabel(r2), r3);
        return;
    L6:
        Trace r0 = INSTANCE;
        r0.beginAsyncSectionFallback(r0.truncatedTraceSectionLabel(r2), r3);
    }

    private final void beginAsyncSectionFallback(String r6, int r7) {
    L6:
        e = move-exception;
        handleException("asyncTraceBegin", e);
        return;
    L4:
        if (asyncTraceBeginMethod != null) goto L8;
        asyncTraceBeginMethod = android.os.Trace.class.getMethod("asyncTraceBegin", new Class[]{Long.TYPE, String.class, Integer.TYPE});     // Catch: Exception -> L6
    L8:
        Method r1 = asyncTraceBeginMethod;     // Catch: Exception -> L6
        if (r1 == null) goto L13;
        r1.invoke(null, new Object[]{Long.valueOf(traceTagApp), r6, Integer.valueOf(r7)});     // Catch: Exception -> L6
        return;
    L13:
        throw new IllegalArgumentException("Required value was null.");     // Catch: Exception -> L6
    }

    @JvmStatic
    public static final void beginSection(String r1) {
        android.os.Trace.beginSection(INSTANCE.truncatedTraceSectionLabel(r1));
    }

    @JvmStatic
    public static final void endAsyncSection(String r2, int r3) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        TraceApi29Impl.INSTANCE.endAsyncSection(INSTANCE.truncatedTraceSectionLabel(r2), r3);
        return;
    L6:
        Trace r0 = INSTANCE;
        r0.endAsyncSectionFallback(r0.truncatedTraceSectionLabel(r2), r3);
    }

    private final void endAsyncSectionFallback(String r6, int r7) {
    L6:
        e = move-exception;
        handleException("asyncTraceEnd", e);
        return;
    L4:
        if (asyncTraceEndMethod != null) goto L8;
        asyncTraceEndMethod = android.os.Trace.class.getMethod("asyncTraceEnd", new Class[]{Long.TYPE, String.class, Integer.TYPE});     // Catch: Exception -> L6
    L8:
        Method r1 = asyncTraceEndMethod;     // Catch: Exception -> L6
        if (r1 == null) goto L13;
        r1.invoke(null, new Object[]{Long.valueOf(traceTagApp), r6, Integer.valueOf(r7)});     // Catch: Exception -> L6
        return;
    L13:
        throw new IllegalArgumentException("Required value was null.");     // Catch: Exception -> L6
    }

    @JvmStatic
    public static final void endSection() {
        android.os.Trace.endSection();
    }

    private final void handleException(String r2, Exception r3) {
        if ((r3 instanceof InvocationTargetException) == false) goto L9;
        Throwable r1 = ((InvocationTargetException) r3).getCause();
        if ((r1 instanceof RuntimeException) == true) goto L8;
        HttpUrl$$ExternalSyntheticBUOutline0.m(r1);
        return;
    L8:
        throw r1;
    L9:
        Log.v("Trace", "Unable to call " + r2 + " via reflection", r3);
    }

    @JvmStatic
    public static final boolean isEnabled() {
        if (Build.VERSION.SDK_INT < 29) goto L7;
        return TraceApi29Impl.INSTANCE.isEnabled();
    L7:
        return INSTANCE.isEnabledFallback();
    }

    private final boolean isEnabledFallback() {
    L6:
        e = move-exception;
        handleException("isTagEnabled", e);
        return false;
    L4:
        if (isTagEnabledMethod != null) goto L8;
        traceTagApp = android.os.Trace.class.getField("TRACE_TAG_APP").getLong(null);     // Catch: Exception -> L6
        isTagEnabledMethod = android.os.Trace.class.getMethod("isTagEnabled", new Class[]{Long.TYPE});     // Catch: Exception -> L6
    L8:
        Method r1 = isTagEnabledMethod;     // Catch: Exception -> L6
        if (r1 == null) goto L13;
        return ((Boolean) r1.invoke(null, new Object[]{Long.valueOf(traceTagApp)})).booleanValue();
    L13:
        throw new IllegalArgumentException("Required value was null.");     // Catch: Exception -> L6
    }

    @JvmStatic
    public static final void setCounter(String r2, int r3) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        TraceApi29Impl.INSTANCE.setCounter(INSTANCE.truncatedTraceSectionLabel(r2), r3);
        return;
    L6:
        Trace r0 = INSTANCE;
        r0.setCounterFallback(r0.truncatedTraceSectionLabel(r2), r3);
    }

    private final void setCounterFallback(String r6, int r7) {
    L6:
        e = move-exception;
        handleException("traceCounter", e);
        return;
    L4:
        if (traceCounterMethod != null) goto L8;
        traceCounterMethod = android.os.Trace.class.getMethod("traceCounter", new Class[]{Long.TYPE, String.class, Integer.TYPE});     // Catch: Exception -> L6
    L8:
        Method r1 = traceCounterMethod;     // Catch: Exception -> L6
        if (r1 == null) goto L13;
        r1.invoke(null, new Object[]{Long.valueOf(traceTagApp), r6, Integer.valueOf(r7)});     // Catch: Exception -> L6
        return;
    L13:
        throw new IllegalArgumentException("Required value was null.");     // Catch: Exception -> L6
    }

    private final String truncatedTraceSectionLabel(String r2) {
        if (r2.length() > 127) goto L5;
        String r1 = r2;
    L6:
        if (r1 == null) goto L8;
        return r1;
    L8:
        return r2.substring(0, 127);
    L5:
        r1 = null;
        goto L6
    }
}
