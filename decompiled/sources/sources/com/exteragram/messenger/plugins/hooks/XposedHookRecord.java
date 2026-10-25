package com.exteragram.messenger.plugins.hooks;

import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import com.sun.jna.Callback;
import de.robv.android.xposed.XC_MethodHook;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\f\u0010\u0002\u001a\b\u0018\u00010\u0003R\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0013\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0002\u001a\b\u0018\u00010\u0003R\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/exteragram/messenger/plugins/hooks/XposedHookRecord;", "Lcom/exteragram/messenger/plugins/hooks/HookRecord;", "unhookObject", "Lde/robv/android/xposed/XC_MethodHook$Unhook;", "Lde/robv/android/xposed/XC_MethodHook;", "<init>", "(Lde/robv/android/xposed/XC_MethodHook$Unhook;)V", "cleanedUp", "Ljava/util/concurrent/atomic/AtomicBoolean;", "cleanup", _UrlKt.FRAGMENT_ENCODE_SET, "matches", _UrlKt.FRAGMENT_ENCODE_SET, "criteria", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class XposedHookRecord implements HookRecord {
    public static final Companion Companion = null;
    private static final Map<AutoCloseable, Integer> callbackReferences = null;
    private final AtomicBoolean cleanedUp;
    private final XC_MethodHook.Unhook unhookObject;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H\u0002J\u0018\u0010\u000e\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H\u0002R^\u0010\u0004\u001aR\u0012\u0014\u0012\u0012 \b*\b\u0018\u00010\u0006j\u0002`\u00070\u0006j\u0002`\u0007\u0012\f\u0012\n \b*\u0004\u0018\u00010\t0\t \b*(\u0012\u0014\u0012\u0012 \b*\b\u0018\u00010\u0006j\u0002`\u00070\u0006j\u0002`\u0007\u0012\f\u0012\n \b*\u0004\u0018\u00010\t0\t\u0018\u00010\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/exteragram/messenger/plugins/hooks/XposedHookRecord$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "callbackReferences", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "kotlin.jvm.PlatformType", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "retainCallback", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "releaseCallback", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ void access$releaseCallback(Companion r0, AutoCloseable r1) {
            r0.releaseCallback(r1);
        }

        public static final /* synthetic */ void access$retainCallback(Companion r0, AutoCloseable r1) {
            r0.retainCallback(r1);
        }

        private final void releaseCallback(AutoCloseable r5) {
            if (r5 == null) goto L26;
            Map r4 = XposedHookRecord.access$getCallbackReferences$cp();
            monitor-enter(r4);
            Integer r0 = (Integer) XposedHookRecord.access$getCallbackReferences$cp().get(r5);     // Catch: Throwable -> L9
            boolean r1 = false;
            if (r0 == null) goto L11;
            int r02 = r0.intValue();     // Catch: Throwable -> L9
        L13:
            if (r02 > 1) goto L15;
            XposedHookRecord.access$getCallbackReferences$cp().remove(r5);     // Catch: Throwable -> L9
            r1 = true;
        L16:
            Unit r03 = Unit.INSTANCE;     // Catch: Throwable -> L9
            monitor-exit(r4);
            if (r1 == false) goto L25;
            UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r5);
            return;
        L25:
            return;
        L15:
            XposedHookRecord.access$getCallbackReferences$cp().put(r5, Integer.valueOf(r02 - 1));     // Catch: Throwable -> L9
            goto L16
        L11:
            r02 = 0;
        L9:
            th = move-exception;
            throw th;
        }

        private final void retainCallback(AutoCloseable r3) {
            if (r3 != null) goto L4;
            return;
        L4:
            Map r2 = XposedHookRecord.access$getCallbackReferences$cp();
            monitor-enter(r2);
            Map r0 = XposedHookRecord.access$getCallbackReferences$cp();     // Catch: Throwable -> L9
            Integer r1 = (Integer) XposedHookRecord.access$getCallbackReferences$cp().get(r3);     // Catch: Throwable -> L9
            if (r1 == null) goto L11;
            int r12 = r1.intValue();     // Catch: Throwable -> L9
        L12:
            r0.put(r3, Integer.valueOf(r12 + 1));     // Catch: Throwable -> L9
            Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L9
            monitor-exit(r2);
            return;
        L11:
            r12 = 0;
        L9:
            th = move-exception;
            throw th;
        }
    }

    static {
        Companion = new Companion(null);
        callbackReferences = Collections.synchronizedMap(new IdentityHashMap());
    }

    public XposedHookRecord(XC_MethodHook.Unhook r3) {
        this.unhookObject = r3;
        this.cleanedUp = new AtomicBoolean();
        Companion r2 = Companion;
        AutoCloseable r0 = null;
        if (r3 == null) goto L5;
        XC_MethodHook r32 = r3.getCallback();
    L7:
        if ((r32 instanceof AutoCloseable) == false) goto L9;
        r0 = (AutoCloseable) r32;
    L9:
        Companion.access$retainCallback(r2, r0);
        return;
    L5:
        r32 = null;
        goto L7
    }

    public static final /* synthetic */ Map access$getCallbackReferences$cp() {
        return callbackReferences;
    }

    @Override // com.exteragram.messenger.plugins.hooks.HookRecord
    public void cleanup() {
        if (this.cleanedUp.compareAndSet(false, true) == false) goto L42;
        XC_MethodHook.Unhook r5 = this.unhookObject;
        if (r5 == null) goto L43;
        AutoCloseable r1 = null;
        r5.unhook();     // Catch: Throwable -> L18
        Companion r2 = Companion;     // Catch: Throwable -> L13
        Object r52 = r5.getCallback();     // Catch: Throwable -> L13
        if ((r52 instanceof AutoCloseable) == false) goto L15;
        r1 = (AutoCloseable) r52;     // Catch: Throwable -> L13
    L15:
        Companion.access$releaseCallback(r2, r1);     // Catch: Throwable -> L13
        return;
    L13:
        th = move-exception;
        FileLog.e("Error during Xposed hook callback cleanup", th);
        return;
    L18:
        th = move-exception;
        FileLog.e("Error during Xposed unhook cleanup", th);     // Catch: Throwable -> L25
        r2 = Companion;     // Catch: Throwable -> L13
        Object r53 = r5.getCallback();     // Catch: Throwable -> L13
        if ((r53 instanceof AutoCloseable) == false) goto L15;
        r1 = (AutoCloseable) r53;     // Catch: Throwable -> L13
    L25:
        th = move-exception;
        Companion r3 = Companion;     // Catch: Throwable -> L29
        Object r54 = r5.getCallback();     // Catch: Throwable -> L29
        if ((r54 instanceof AutoCloseable) == false) goto L31;
        r1 = (AutoCloseable) r54;     // Catch: Throwable -> L29
    L31:
        Companion.access$releaseCallback(r3, r1);     // Catch: Throwable -> L29
    L34:
        throw th;
    L29:
        th = move-exception;
        FileLog.e("Error during Xposed hook callback cleanup", th);
        goto L34
    L43:
        return;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L13:
        return false;
    L8:
        if (Intrinsics.areEqual(XposedHookRecord.class, r5.getClass()) == false) goto L13;
        if (this.unhookObject != ((XposedHookRecord) r5).unhookObject) goto L13;
        return true;
    }

    public int hashCode() {
        XC_MethodHook.Unhook r0 = this.unhookObject;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.hashCode();
    }

    @Override // com.exteragram.messenger.plugins.hooks.HookRecord
    public boolean matches(Object r2) {
        if ((r2 instanceof XC_MethodHook.Unhook) == true) goto L5;
        return false;
    L5:
        if (this.unhookObject != r2) goto L10;
        return true;
    L10:
        return false;
    }
}
