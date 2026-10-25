package androidx.camera.camera2.pipe.core;

import android.os.Process;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import okhttp3.internal.url._UrlKt;
import okio.SegmentedByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\t\u001a\u00020\b*\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\r\u001a\u00020\b*\u00020\b2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0011\u001a\u00020\u0010*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0014\u001a\u00020\u0013*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/pipe/core/AndroidThreads;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "androidPriority", "androidToJavaPriority", "(I)I", "Ljava/util/concurrent/ThreadFactory;", "withAndroidPriority", "(Ljava/util/concurrent/ThreadFactory;I)Ljava/util/concurrent/ThreadFactory;", _UrlKt.FRAGMENT_ENCODE_SET, "namePrefix", "withPrefix", "(Ljava/util/concurrent/ThreadFactory;Ljava/lang/String;)Ljava/util/concurrent/ThreadFactory;", "threads", "Ljava/util/concurrent/ExecutorService;", "asFixedSizeThreadPool", "(Ljava/util/concurrent/ThreadFactory;I)Ljava/util/concurrent/ExecutorService;", "Ljava/util/concurrent/ScheduledExecutorService;", "asScheduledThreadPool", "(Ljava/util/concurrent/ThreadFactory;I)Ljava/util/concurrent/ScheduledExecutorService;", _UrlKt.FRAGMENT_ENCODE_SET, "NICE_VALUES", "[I", PluginsConstants.Settings.FACTORY, "Ljava/util/concurrent/ThreadFactory;", "getFactory", "()Ljava/util/concurrent/ThreadFactory;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAndroidThreads.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidThreads.kt\nandroidx/camera/camera2/pipe/core/AndroidThreads\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,115:1\n1#2:116\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidThreads {
    public static final AndroidThreads INSTANCE = null;
    private static final int[] NICE_VALUES = null;
    private static final ThreadFactory factory = null;

    /* renamed from: $r8$lambda$-5dskTImH9PRNFfZUGKLfp0vmrk, reason: not valid java name */
    public static /* synthetic */ void m493$r8$lambda$5dskTImH9PRNFfZUGKLfp0vmrk(int r0, Runnable r1) {
        withAndroidPriority$lambda$0$0(r0, r1);
    }

    public static Thread $r8$lambda$faLdKNGt__5T6WxQVV9GLghIoxw(ThreadFactory r1, String r2, AtomicInt r3, Runnable r4) {
        Thread r12 = r1.newThread(r4);
        r12.setName(r2 + StringsKt.padStart(String.valueOf(r3.incrementAndGet()), 2, '0'));
        return r12;
    }

    /* renamed from: $r8$lambda$jRA0i05wL10-EV15Dmt8_svRyC4, reason: not valid java name */
    public static Thread m494$r8$lambda$jRA0i05wL10EV15Dmt8_svRyC4(int r2, ThreadFactory r3, Runnable r4) {
        int r0 = INSTANCE.androidToJavaPriority(r2);
        Thread r22 = r3.newThread(new AndroidThreads$$ExternalSyntheticLambda2(r2, r4));
        r22.setPriority(r0);
        return r22;
    }

    static {
        INSTANCE = new AndroidThreads();
        NICE_VALUES = new int[]{19, 16, 13, 10, 0, -2, -4, -5, -6, -8};
        factory = Executors.defaultThreadFactory();
    }

    private AndroidThreads() {
    }

    private final int androidToJavaPriority(int r3) {
        int r2 = NICE_VALUES.length;
        int r0 = 0;
    L3:
        if (r0 >= r2) goto L9;
        if (r3 >= NICE_VALUES[r0]) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        return r0 + 1;
    L9:
        return 10;
    }

    private static final void withAndroidPriority$lambda$0$0(int r0, Runnable r1) {
        Process.setThreadPriority(r0);
        r1.run();
    }

    public final ExecutorService asFixedSizeThreadPool(ThreadFactory r1, int r2) {
        if (r2 > 0) goto L4;
        SegmentedByteString$$ExternalSyntheticBUOutline0.m("Threads (", r2, ") must be > 0");
        return null;
    L4:
        return Executors.newFixedThreadPool(r2, r1);
    }

    public final ScheduledExecutorService asScheduledThreadPool(ThreadFactory r1, int r2) {
        if (r2 > 0) goto L4;
        SegmentedByteString$$ExternalSyntheticBUOutline0.m("Threads (", r2, ") must be > 0");
        return null;
    L4:
        return Executors.newScheduledThreadPool(r2, r1);
    }

    public final ThreadFactory getFactory() {
        return factory;
    }

    public final ThreadFactory withAndroidPriority(ThreadFactory r1, int r2) {
        return new AndroidThreads$$ExternalSyntheticLambda0(r2, r1);
    }

    public final ThreadFactory withPrefix(ThreadFactory r2, String r3) {
        return new AndroidThreads$$ExternalSyntheticLambda1(r2, r3, AtomicFU.atomic(0));
    }
}
