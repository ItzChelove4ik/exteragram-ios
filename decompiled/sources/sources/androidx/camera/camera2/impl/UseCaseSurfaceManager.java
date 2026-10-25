package androidx.camera.camera2.impl;

import android.util.Log;
import android.view.Surface;
import androidx.camera.camera2.adapter.SessionConfigAdapter;
import androidx.camera.camera2.compat.workaround.InactiveSurfaceCloser;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.CameraSurfaceManager;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.DeferrableSurfaces;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.ListenableFutureKt;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.android.dx.io.Opcodes;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutKt;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ:\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u001e0\u00162\b\b\u0002\u0010\u001f\u001a\u00020 J\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\u000fJ\u000e\u0010\"\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010#J\u0010\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u0013H\u0016J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u0013H\u0016J\b\u0010'\u001a\u00020\u0019H\u0003J\b\u0010(\u001a\u00020\u0019H\u0003J,\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00140*2\u0006\u0010\u001f\u001a\u00020 H\u0082@¢\u0006\u0002\u0010,J\u0014\u0010-\u001a\u00020\u0010*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130*H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00128\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R \u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00168\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00188\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Landroidx/camera/camera2/impl/UseCaseSurfaceManager;", "Landroidx/camera/camera2/pipe/CameraSurfaceManager$SurfaceListener;", "threads", "Landroidx/camera/camera2/impl/UseCaseThreads;", "cameraPipe", "Landroidx/camera/camera2/pipe/CameraPipe;", "inactiveSurfaceCloser", "Landroidx/camera/camera2/compat/workaround/InactiveSurfaceCloser;", "sessionConfigAdapter", "Landroidx/camera/camera2/adapter/SessionConfigAdapter;", "<init>", "(Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/pipe/CameraPipe;Landroidx/camera/camera2/compat/workaround/InactiveSurfaceCloser;Landroidx/camera/camera2/adapter/SessionConfigAdapter;)V", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "setupDeferred", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "activeSurfaceMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/Surface;", "Landroidx/camera/core/impl/DeferrableSurface;", "configuredSurfaceMap", _UrlKt.FRAGMENT_ENCODE_SET, "stopDeferred", "Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "setupAsync", "graph", "Landroidx/camera/camera2/pipe/CameraGraph;", "surfaceToStreamMap", "Landroidx/camera/camera2/pipe/StreamId;", "timeoutMillis", _UrlKt.FRAGMENT_ENCODE_SET, "stopAsync", "awaitSetupCompletion", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSurfaceActive", "surface", "onSurfaceInactive", "setSurfaceListener", "tryClearSurfaceListener", "getSurfaces", _UrlKt.FRAGMENT_ENCODE_SET, "deferrableSurfaces", "(Ljava/util/List;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "areValid", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseSurfaceManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseSurfaceManager.kt\nandroidx/camera/camera2/impl/UseCaseSurfaceManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,269:1\n1#2:270\n119#3,4:271\n119#3,4:275\n119#3,4:279\n85#3,4:283\n129#3,4:287\n85#3,4:291\n129#3,4:295\n85#3,4:299\n*S KotlinDebug\n*F\n+ 1 UseCaseSurfaceManager.kt\nandroidx/camera/camera2/impl/UseCaseSurfaceManager\n*L\n82#1:271,4\n158#1:275,4\n195#1:279,4\n204#1:283,4\n209#1:287,4\n222#1:291,4\n227#1:295,4\n243#1:299,4\n*E\n"})
/* loaded from: classes3.dex */
public class UseCaseSurfaceManager implements CameraSurfaceManager.SurfaceListener {
    private final Map<Surface, DeferrableSurface> activeSurfaceMap;
    private final CameraPipe cameraPipe;
    private Map<Surface, ? extends DeferrableSurface> configuredSurfaceMap;
    private final InactiveSurfaceCloser inactiveSurfaceCloser;
    private final Object lock;
    private final SessionConfigAdapter sessionConfigAdapter;
    private Deferred<Boolean> setupDeferred;
    private CompletableDeferred<Unit> stopDeferred;
    private final UseCaseThreads threads;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseSurfaceManager", f = "UseCaseSurfaceManager.kt", i = {}, l = {193}, m = "awaitSetupCompletion$suspendImpl", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.UseCaseSurfaceManager$awaitSetupCompletion$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ UseCaseSurfaceManager this$0;

        public AnonymousClass1(UseCaseSurfaceManager r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return UseCaseSurfaceManager.awaitSetupCompletion$suspendImpl(this.this$0, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseSurfaceManager", f = "UseCaseSurfaceManager.kt", i = {}, l = {Opcodes.CONST_METHOD_HANDLE}, m = "getSurfaces", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.UseCaseSurfaceManager$getSurfaces$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00931 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ UseCaseSurfaceManager this$0;

        public C00931(UseCaseSurfaceManager r1, Continuation<? super C00931> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            this.result = r4;
            this.label |= Integer.MIN_VALUE;
            return UseCaseSurfaceManager.access$getSurfaces(this.this$0, null, 0, this);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00040\u0001*\u00020\u0005H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/Surface;", "kotlin.jvm.PlatformType", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseSurfaceManager$getSurfaces$2", f = "UseCaseSurfaceManager.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nUseCaseSurfaceManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseSurfaceManager.kt\nandroidx/camera/camera2/impl/UseCaseSurfaceManager$getSurfaces$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,269:1\n1563#2:270\n1634#2,3:271\n*S KotlinDebug\n*F\n+ 1 UseCaseSurfaceManager.kt\nandroidx/camera/camera2/impl/UseCaseSurfaceManager$getSurfaces$2\n*L\n256#1:270\n256#1:271,3\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.UseCaseSurfaceManager$getSurfaces$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<Surface>>, Object> {
        final /* synthetic */ List<DeferrableSurface> $deferrableSurfaces;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(List<? extends DeferrableSurface> r1, Continuation<? super AnonymousClass2> r2) {
            this.$deferrableSurfaces = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.$deferrableSurfaces, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super List<Surface>> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super List<Surface>> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r5);
            return r5;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r5);
            List<DeferrableSurface> r52 = this.$deferrableSurfaces;
            ArrayList r12 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r52, 10));
            Iterator<T> r53 = r52.iterator();
        L11:
            if (r53.hasNext() == false) goto L13;
            r12.add(Futures.nonCancellationPropagating(((DeferrableSurface) r53.next()).getSurface()));
            goto L11
        L13:
            ListenableFuture r54 = Futures.successfulAsList(r12);
            this.label = 1;
            Object r4 = ListenableFutureKt.await(r54, this);
            if (r4 != r0) goto L16;
            return r0;
        L16:
            return r4;
        }
    }

    public static /* synthetic */ Unit $r8$lambda$WqGGjk2MKSoMCSgph_xn1w_vBKc(List r0, Throwable r1) {
        return setupAsync$lambda$0$3$0(r0, r1);
    }

    public UseCaseSurfaceManager(UseCaseThreads r1, CameraPipe r2, InactiveSurfaceCloser r3, SessionConfigAdapter r4) {
        this.threads = r1;
        this.cameraPipe = r2;
        this.inactiveSurfaceCloser = r3;
        this.sessionConfigAdapter = r4;
        this.lock = new Object();
        this.activeSurfaceMap = new LinkedHashMap();
    }

    public static final /* synthetic */ boolean access$areValid(UseCaseSurfaceManager r0, List r1) {
        return r0.areValid(r1);
    }

    public static final /* synthetic */ InactiveSurfaceCloser access$getInactiveSurfaceCloser$p(UseCaseSurfaceManager r0) {
        return r0.inactiveSurfaceCloser;
    }

    public static final /* synthetic */ Object access$getLock$p(UseCaseSurfaceManager r0) {
        return r0.lock;
    }

    public static final /* synthetic */ Object access$getSurfaces(UseCaseSurfaceManager r0, List r1, long r2, Continuation r4) {
        return r0.getSurfaces(r1, r2, r4);
    }

    public static final /* synthetic */ void access$setConfiguredSurfaceMap$p(UseCaseSurfaceManager r0, Map r1) {
        r0.configuredSurfaceMap = r1;
    }

    public static final /* synthetic */ void access$setSurfaceListener(UseCaseSurfaceManager r0) {
        r0.setSurfaceListener();
    }

    private final boolean areValid(List<? extends Surface> r1) {
        if (r1.isEmpty() == false) goto L5;
        return false;
    L5:
        if (r1.contains(null) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static /* synthetic */ Object awaitSetupCompletion$suspendImpl(UseCaseSurfaceManager r5, Continuation<? super Boolean> r6) {
        if ((r6 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L13;
        ResultKt.throwOnFailure(r62);     // Catch: CancellationException -> L27
        return r62;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r62);
        Object r63 = r5.lock;
        monitor-enter(r63);
        Deferred<Boolean> r22 = r5.setupDeferred;     // Catch: Throwable -> L32
        if (r22 != null) goto L20;
    L34:
        Boolean r52 = Boxing.boxBoolean(false);     // Catch: Throwable -> L32
        monitor-exit(r63);
        return r52;
    L20:
        if (r5.stopDeferred != null) goto L34;
        monitor-exit(r63);
        r0.label = 1;     // Catch: CancellationException -> L27
        Object r53 = r22.await(r0);     // Catch: CancellationException -> L27
        if (r53 != r12) goto L26;
        return r12;
    L26:
        return r53;
    L32:
        th = move-exception;
        throw th;
    L27:
        Camera2Logger r54 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L31;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Surface setup was cancelled");
    L31:
        return Boxing.boxBoolean(false);
    L7:
        r0 = new AnonymousClass1(r5, r6);
        goto L8
    }

    private final Object getSurfaces(List<? extends DeferrableSurface> r5, long r6, Continuation<? super List<? extends Surface>> r8) {
        if ((r8 instanceof C00931) == false) goto L7;
        C00931 r0 = (C00931) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r4 = r0.result;
        Object r82 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r0.label;
        if (r12 == 0) goto L14;
        if (r12 != 1) goto L12;
        ResultKt.throwOnFailure(r4);
    L17:
        List r42 = (List) r4;
        if (r42 == null) goto L20;
        return r42;
    L20:
        return CollectionsKt.emptyList();
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r4);
        AnonymousClass2 r43 = new AnonymousClass2(r5, null);
        r0.label = 1;
        r4 = TimeoutKt.withTimeoutOrNull(r6, r43, r0);
        if (r4 != r82) goto L17;
        return r82;
    L7:
        r0 = new C00931(this, r8);
        goto L8
    }

    private final void setSurfaceListener() {
        this.cameraPipe.cameraSurfaceManager().addListener(this);
    }

    public static /* synthetic */ Deferred setupAsync$default(UseCaseSurfaceManager r6, CameraGraph r7, SessionConfigAdapter r8, Map r9, long r10, int r12, Object r13) {
        if (r13 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: setupAsync");
        return null;
    L4:
        if ((r12 & 8) == 0) goto L7;
        r10 = 5000;
    L7:
        return r6.setupAsync(r7, r8, r9, r10);
    }

    private static final Unit setupAsync$lambda$0$3$0(List r0, Throwable r1) {
        DeferrableSurfaces.decrementAll(r0);
        return Unit.INSTANCE;
    }

    private final void tryClearSurfaceListener() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.activeSurfaceMap.isEmpty() == true) goto L7;
    L16:
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r0);
        return;
    L7:
        if (this.configuredSurfaceMap != null) goto L16;
        Camera2Logger r1 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L11
        if (Logger.isDebugEnabled("CXCP") == false) goto L13;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), this + " remove surface listener");     // Catch: Throwable -> L11
    L13:
        this.cameraPipe.cameraSurfaceManager().removeListener(this);     // Catch: Throwable -> L11
        CompletableDeferred<Unit> r42 = this.stopDeferred;     // Catch: Throwable -> L11
        if (r42 == null) goto L16;
        r42.complete(Unit.INSTANCE);     // Catch: Throwable -> L11
        goto L16
    }

    public Object awaitSetupCompletion(Continuation<? super Boolean> r1) {
        return awaitSetupCompletion$suspendImpl(this, r1);
    }

    @Override // androidx.camera.camera2.pipe.CameraSurfaceManager.SurfaceListener
    public void onSurfaceActive(Surface r7) {
        Object r2 = this.lock;
        monitor-enter(r2);
        Map<Surface, ? extends DeferrableSurface> r3 = this.configuredSurfaceMap;     // Catch: Throwable -> L13
        if (r3 == null) goto L24;
        DeferrableSurface r32 = r3.get(r7);     // Catch: Throwable -> L13
        if (r32 == null) goto L24;
        if (this.activeSurfaceMap.containsKey(r7) == true) goto L23;
        Camera2Logger r4 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L13
        if (Logger.isDebugEnabled("CXCP") == false) goto L15;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "SurfaceActive " + r32 + " in " + this);     // Catch: Throwable -> L13
    L15:
        this.activeSurfaceMap.put(r7, r32);     // Catch: Throwable -> L13
        r32.incrementUseCount();     // Catch: Throwable -> L13 DeferrableSurface.SurfaceClosedException -> L18
    L18:
        e = move-exception;
        Camera2Logger r33 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L13
        if (Logger.isWarnEnabled("CXCP") == false) goto L22;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Error when " + r7 + " going to increase the use count.", e);     // Catch: Throwable -> L13
    L22:
        this.sessionConfigAdapter.reportSurfaceInvalid(e.getDeferrableSurface());     // Catch: Throwable -> L13
    L23:
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L13
    L24:
        monitor-exit(r2);
        return;
    L13:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.CameraSurfaceManager.SurfaceListener
    public void onSurfaceInactive(Surface r7) {
        Object r2 = this.lock;
        monitor-enter(r2);
        DeferrableSurface r3 = this.activeSurfaceMap.remove(r7);     // Catch: Throwable -> L9
        if (r3 == null) goto L19;
        Camera2Logger r4 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L9
        if (Logger.isDebugEnabled("CXCP") == false) goto L11;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "SurfaceInactive " + r3 + " in " + this);     // Catch: Throwable -> L9
    L11:
        this.inactiveSurfaceCloser.onSurfaceInactive(r3);     // Catch: Throwable -> L9
        r3.decrementUseCount();     // Catch: Throwable -> L9 IllegalStateException -> L14
    L18:
        tryClearSurfaceListener();     // Catch: Throwable -> L9
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L9
        goto L19
    L14:
        e = move-exception;
        Camera2Logger r32 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L9
        if (Logger.isWarnEnabled("CXCP") == false) goto L18;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Error when " + r7 + " going to decrease the use count.", e);     // Catch: Throwable -> L9
    L19:
        monitor-exit(r2);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final Deferred<Boolean> setupAsync(CameraGraph r17, SessionConfigAdapter r18, Map<DeferrableSurface, StreamId> r19, long r20) {
        Object r9 = this.lock;
        monitor-enter(r9);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.setupDeferred != null) goto L27;
        if (this.stopDeferred != null) goto L25;
        if (this.configuredSurfaceMap != null) goto L23;
        List<DeferrableSurface> r3 = r18.getDeferrableSurfaces();     // Catch: Throwable -> L13
        DeferrableSurfaces.incrementAll(r3);     // Catch: Throwable -> L13 DeferrableSurface.SurfaceClosedException -> L15
        Deferred<Boolean> r0 = BuildersKt.async$default(this.threads.getScope(), null, null, new UseCaseSurfaceManager$setupAsync$1$deferred$1(r18, this, r3, r20, r19, r17, null), 3, null);     // Catch: Throwable -> L13
        r0.invokeOnCompletion(new UseCaseSurfaceManager$$ExternalSyntheticLambda0(r3));     // Catch: Throwable -> L13
        this.setupDeferred = r0;     // Catch: Throwable -> L13
    L20:
        monitor-exit(r9);
        return r0;
    L15:
        e = move-exception;
        Camera2Logger r1 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L13
        if (Logger.isWarnEnabled("CXCP") == false) goto L19;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to increment DeferrableSurfaces: Surfaces closed");     // Catch: Throwable -> L13
    L19:
        BuildersKt.launch$default(this.threads.getScope(), null, null, new UseCaseSurfaceManager$setupAsync$1$4(r18, e, null), 3, null);     // Catch: Throwable -> L13
        r0 = CompletableDeferredKt.CompletableDeferred(Boolean.FALSE);     // Catch: Throwable -> L13
        goto L20
    L23:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L13
    L25:
        throw new IllegalStateException("Surfaces being setup after stopped!");     // Catch: Throwable -> L13
    L27:
        throw new IllegalStateException("Surfaces should only be set up once!");     // Catch: Throwable -> L13
    }

    public final Deferred<Unit> stopAsync() {
        Object r0 = this.lock;
        monitor-enter(r0);
        CompletableDeferred<Unit> r1 = this.stopDeferred;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        Camera2Logger r4 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L9
        if (Logger.isWarnEnabled("CXCP") == false) goto L15;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseSurfaceManager is already stopping!");     // Catch: Throwable -> L9
    L15:
        monitor-exit(r0);
        return r1;
    L11:
        Deferred<Boolean> r12 = this.setupDeferred;     // Catch: Throwable -> L9
        if (r12 == null) goto L14;
        Job.DefaultImpls.cancel$default(r12, null, 1, null);     // Catch: Throwable -> L9
    L14:
        this.inactiveSurfaceCloser.closeAll();     // Catch: Throwable -> L9
        this.configuredSurfaceMap = null;     // Catch: Throwable -> L9
        r1 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);     // Catch: Throwable -> L9
        this.stopDeferred = r1;     // Catch: Throwable -> L9
        tryClearSurfaceListener();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }
}
