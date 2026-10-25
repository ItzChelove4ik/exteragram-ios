package androidx.camera.camera2.pipe.config;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.core.AndroidThreads;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.internal.CameraPipeLifetime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0010¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/config/ThreadConfigModule;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraPipe$ThreadConfig;", "threadConfig", "<init>", "(Landroidx/camera/camera2/pipe/CameraPipe$ThreadConfig;)V", "Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;", "cameraPipeLifetime", "Lkotlinx/coroutines/Job;", "cameraPipeJob", "Landroidx/camera/camera2/pipe/core/Threads;", "provideThreads", "(Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;Lkotlinx/coroutines/Job;)Landroidx/camera/camera2/pipe/core/Threads;", "Landroidx/camera/camera2/pipe/CameraPipe$ThreadConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "lightweightThreadCount", "I", "backgroundThreadCount", "cameraThreadPriority", "defaultThreadPriority", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nThreadConfigModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ThreadConfigModule.kt\nandroidx/camera/camera2/pipe/config/ThreadConfigModule\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,213:1\n1#2:214\n*E\n"})
/* loaded from: classes3.dex */
public final class ThreadConfigModule {
    private final int backgroundThreadCount;
    private final int cameraThreadPriority;
    private final int defaultThreadPriority;
    private final int lightweightThreadCount;
    private final CameraPipe.ThreadConfig threadConfig;

    /* renamed from: $r8$lambda$-5P1hWeb8e3yIYEHQIrJfBt4bYw, reason: not valid java name */
    public static Executor m489$r8$lambda$5P1hWeb8e3yIYEHQIrJfBt4bYw(ThreadConfigModule r3, CameraPipeLifetime r4) {
        if (r3.threadConfig.getDefaultCameraExecutor() != null) goto L7;
        AndroidThreads r0 = AndroidThreads.INSTANCE;
        ExecutorService r32 = r0.asFixedSizeThreadPool(r0.withAndroidPriority(r0.withPrefix(r0.getFactory(), "CXCP-Camera-E"), r3.cameraThreadPriority), 1);
        r4.addShutdownAction(CameraPipeLifetime.ShutdownType.THREAD, new ThreadConfigModule$$ExternalSyntheticLambda5(r32));
        return r32;
    L7:
        return r3.threadConfig.getDefaultCameraExecutor();
    }

    /* renamed from: $r8$lambda$aLGf2lPcF3y7juP-ScX8YxK8gwo, reason: not valid java name */
    public static /* synthetic */ void m490$r8$lambda$aLGf2lPcF3y7juPScX8YxK8gwo(HandlerThread r0) {
        provideThreads$lambda$4$1(r0);
    }

    /* renamed from: $r8$lambda$jeb-5Vw5LnFgi_4xpswUjpNN2h0, reason: not valid java name */
    public static void m491$r8$lambda$jeb5Vw5LnFgi_4xpswUjpNN2h0(List r4) {
        Iterator r0 = r4.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((ExecutorService) r0.next()).shutdownNow();
        goto L4
    L6:
        Iterator r42 = r4.iterator();
    L8:
        if (r42.hasNext() == false) goto L10;
        ((ExecutorService) r42.next()).awaitTermination(1, TimeUnit.SECONDS);
        goto L8
    }

    public static void $r8$lambda$oEFYP5DGguYpNiMpq15Gj4l3Kb4(Ref.ObjectRef r2, Ref.ObjectRef r3) {
        CoroutineScopeKt.cancel$default((CoroutineScope) r2.element, null, 1, null);
        CoroutineScopeKt.cancel$default((CoroutineScope) r3.element, null, 1, null);
    }

    public static Handler $r8$lambda$wABDi6HCco0H4HmkIa31ylMsIOk(ThreadConfigModule r2, CameraPipeLifetime r3) {
        if (r2.threadConfig.getDefaultCameraHandler() != null) goto L7;
        HandlerThread r0 = new HandlerThread("CXCP-Camera-H", r2.cameraThreadPriority);
        r0.start();
        r3.addShutdownAction(CameraPipeLifetime.ShutdownType.THREAD, new ThreadConfigModule$$ExternalSyntheticLambda4(r0));
        return new Handler(r0.getLooper());
    L7:
        return r2.threadConfig.getDefaultCameraHandler();
    }

    /* renamed from: $r8$lambda$y2vD8fun5cW9oN2O-RgqAjxTVG0, reason: not valid java name */
    public static /* synthetic */ void m492$r8$lambda$y2vD8fun5cW9oN2ORgqAjxTVG0(ExecutorService r0) {
        provideThreads$lambda$5$0(r0);
    }

    public ThreadConfigModule(CameraPipe.ThreadConfig r2) {
        this.threadConfig = r2;
        this.lightweightThreadCount = Math.max(4, Runtime.getRuntime().availableProcessors() - 2);
        this.backgroundThreadCount = 4;
        this.cameraThreadPriority = -3;
        this.defaultThreadPriority = -1;
    }

    private static final void provideThreads$lambda$4$1(HandlerThread r2) {
        r2.quit();
        r2.join(1000);
    }

    private static final void provideThreads$lambda$5$0(ExecutorService r3) {
        r3.shutdownNow();
        r3.awaitTermination(1, TimeUnit.SECONDS);
    }

    /* JADX WARN: Type inference failed for: r13v12, types: [T, kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Type inference failed for: r13v4, types: [T, kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Type inference failed for: r13v7, types: [T, kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Type inference failed for: r14v2, types: [T, kotlinx.coroutines.CoroutineScope] */
    public final Threads provideThreads(CameraPipeLifetime r14, Job r15) {
        ArrayList r0 = new ArrayList();
        Executor r1 = this.threadConfig.getDefaultBlockingExecutor();
        if (r1 != null) goto L5;
        AndroidThreads r12 = AndroidThreads.INSTANCE;
        r1 = r12.asScheduledThreadPool(r12.withAndroidPriority(r12.withPrefix(r12.getFactory(), "CXCP-IO-"), this.defaultThreadPriority), 8);
        r0.add(r1);
    L5:
        Executor r5 = r1;
        CoroutineDispatcher r6 = ExecutorsKt.from(r5);
        Executor r13 = this.threadConfig.getDefaultBackgroundExecutor();
        if (r13 != null) goto L8;
        AndroidThreads r16 = AndroidThreads.INSTANCE;
        r13 = r16.asScheduledThreadPool(r16.withAndroidPriority(r16.withPrefix(r16.getFactory(), "CXCP-BG-"), this.defaultThreadPriority), this.backgroundThreadCount);
        r0.add(r13);
    L8:
        Executor r7 = r13;
        CoroutineDispatcher r8 = ExecutorsKt.from(r7);
        Executor r17 = this.threadConfig.getDefaultLightweightExecutor();
        if (r17 != null) goto L11;
        AndroidThreads r18 = AndroidThreads.INSTANCE;
        r17 = r18.asScheduledThreadPool(r18.withAndroidPriority(r18.withPrefix(r18.getFactory(), "CXCP-"), this.cameraThreadPriority), this.lightweightThreadCount);
        r0.add(r17);
    L11:
        Executor r9 = r17;
        CoroutineDispatcher r10 = ExecutorsKt.from(r9);
        r14.addShutdownAction(CameraPipeLifetime.ShutdownType.THREAD, new ThreadConfigModule$$ExternalSyntheticLambda0(r0));
        Function0<Handler> r02 = this.threadConfig.getDefaultCameraHandlerFn();
        if (r02 != null) goto L14;
        r02 = new ThreadConfigModule$$ExternalSyntheticLambda1(this, r14);
    L14:
        Function0<Handler> r11 = r02;
        ThreadConfigModule$$ExternalSyntheticLambda2 r122 = new ThreadConfigModule$$ExternalSyntheticLambda2(this, r14);
        Ref.ObjectRef r03 = new Ref.ObjectRef();
        Ref.ObjectRef r19 = new Ref.ObjectRef();
        if (this.threadConfig.getTestOnlyScope() == null) goto L17;
        r03.element = this.threadConfig.getTestOnlyScope();
        r19.element = this.threadConfig.getTestOnlyScope();
    L19:
        return new Threads((CoroutineScope) r03.element, (CoroutineScope) r19.element, r5, r6, r7, r8, r9, r10, r11, r122);
    L17:
        r03.element = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob(r15).plus(r10).plus(new CoroutineName("CXCP")));
        r19.element = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob(r15).plus(new CoroutineName("CXCP-Dispatch")));
        r14.addShutdownAction(CameraPipeLifetime.ShutdownType.SCOPE, new ThreadConfigModule$$ExternalSyntheticLambda3(r03, r19));
        goto L19
    }
}
