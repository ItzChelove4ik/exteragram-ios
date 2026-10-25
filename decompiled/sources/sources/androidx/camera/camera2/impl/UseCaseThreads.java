package androidx.camera.camera2.impl;

import android.os.Handler;
import android.os.Looper;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.OkHttpClient$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u0013R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0017\u0010\"\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010\u0015R\u0014\u0010$\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010\u0016R\"\u0010%\u001a\u00020\u00028\u0006@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010\u0010\u001a\u0004\b&\u0010\u0012\"\u0004\b'\u0010(¨\u0006)"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseThreads;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;", "scope", "Ljava/util/concurrent/Executor;", "backgroundExecutor", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "<init>", "(Lkotlinx/coroutines/CoroutineScope;Ljava/util/concurrent/Executor;Lkotlinx/coroutines/CoroutineDispatcher;)V", _UrlKt.FRAGMENT_ENCODE_SET, "isOnSequentialThread", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "checkOnSequentialThread", "()V", "Lkotlinx/coroutines/CoroutineScope;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "Ljava/util/concurrent/Executor;", "getBackgroundExecutor", "()Ljava/util/concurrent/Executor;", "Lkotlinx/coroutines/CoroutineDispatcher;", "getBackgroundDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "Landroid/os/Handler;", "mainHandler", "Landroid/os/Handler;", "getMainHandler", "()Landroid/os/Handler;", "sequentialExecutorDelegate", "Ljava/lang/ThreadLocal;", "isSequentialThread", "Ljava/lang/ThreadLocal;", "sequentialExecutor", "getSequentialExecutor", "sequentialDispatcher", "sequentialScope", "getSequentialScope", "setSequentialScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseThreads.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,200:1\n1#2:201\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseThreads {
    private final CoroutineDispatcher backgroundDispatcher;
    private final Executor backgroundExecutor;
    private final ThreadLocal<Boolean> isSequentialThread;
    private final Handler mainHandler;
    private final CoroutineScope scope;
    private final CoroutineDispatcher sequentialDispatcher;
    private final Executor sequentialExecutor;
    private final Executor sequentialExecutorDelegate;
    private CoroutineScope sequentialScope;

    /* renamed from: $r8$lambda$Sy8e0BZ-W_MxonrOBkbBkAohDiY, reason: not valid java name */
    public static /* synthetic */ void m106$r8$lambda$Sy8e0BZW_MxonrOBkbBkAohDiY(UseCaseThreads r0, Runnable r1) {
        sequentialExecutor$lambda$0$0(r0, r1);
    }

    /* renamed from: $r8$lambda$YsXDVD6TU3Ace-IWj-1R_tsob-M, reason: not valid java name */
    public static void m107$r8$lambda$YsXDVD6TU3AceIWj1R_tsobM(UseCaseThreads r2, Runnable r3) {
        r2.sequentialExecutorDelegate.execute(new UseCaseThreads$$ExternalSyntheticLambda1(r2, r3));
    }

    public UseCaseThreads(CoroutineScope r2, Executor r3, CoroutineDispatcher r4) {
        this.scope = r2;
        this.backgroundExecutor = r3;
        this.backgroundDispatcher = r4;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.sequentialExecutorDelegate = CameraXExecutors.newSequentialExecutor(r3);
        this.isSequentialThread = new ThreadLocal();
        UseCaseThreads$$ExternalSyntheticLambda0 r32 = new UseCaseThreads$$ExternalSyntheticLambda0(this);
        this.sequentialExecutor = r32;
        CoroutineDispatcher r33 = ExecutorsKt.from(r32);
        this.sequentialDispatcher = r33;
        this.sequentialScope = CoroutineScopeKt.CoroutineScope(r2.getCoroutineContext().plus(SupervisorKt.SupervisorJob$default(null, 1, null)).plus(r33));
    }

    private static final void sequentialExecutor$lambda$0$0(UseCaseThreads r2, Runnable r3) {
        r2.isSequentialThread.set(Boolean.TRUE);
        r3.run();     // Catch: Throwable -> L6
        r2.isSequentialThread.remove();
        return;
    L6:
        th = move-exception;
        r2.isSequentialThread.remove();
        throw th;
    }

    public final void checkOnSequentialThread() {
        if (isOnSequentialThread() == false) goto L5;
        return;
    L5:
        OkHttpClient$$ExternalSyntheticBUOutline0.m("Thread check failed: This method must be called from the UseCaseThreads sequential scope. Current thread: ", Thread.currentThread().getName());
    }

    public final CoroutineScope getScope() {
        return this.scope;
    }

    public final Executor getSequentialExecutor() {
        return this.sequentialExecutor;
    }

    public final CoroutineScope getSequentialScope() {
        return this.sequentialScope;
    }

    public final boolean isOnSequentialThread() {
        return Intrinsics.areEqual(this.isSequentialThread.get(), Boolean.TRUE);
    }
}
