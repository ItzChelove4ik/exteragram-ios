package androidx.camera.camera2.impl;

import android.util.Log;
import android.view.Surface;
import androidx.camera.camera2.adapter.SessionConfigAdapter;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.TimeoutCancellationException;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseSurfaceManager$setupAsync$1$deferred$1", f = "UseCaseSurfaceManager.kt", i = {0}, l = {97}, m = "invokeSuspend", n = {"$this$async"}, s = {"L$0"}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseSurfaceManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseSurfaceManager.kt\nandroidx/camera/camera2/impl/UseCaseSurfaceManager$setupAsync$1$deferred$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,269:1\n129#2,4:270\n119#2,4:274\n102#2,4:278\n85#2,4:289\n102#2,4:294\n119#2,4:298\n1208#3,2:282\n1236#3,4:284\n216#4:288\n217#4:293\n*S KotlinDebug\n*F\n+ 1 UseCaseSurfaceManager.kt\nandroidx/camera/camera2/impl/UseCaseSurfaceManager$setupAsync$1$deferred$1\n*L\n99#1:270,4\n103#1:274,4\n109#1:278,4\n128#1:289,4\n132#1:294,4\n135#1:298,4\n117#1:282,2\n117#1:284,4\n125#1:288\n125#1:293\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseSurfaceManager$setupAsync$1$deferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ List<DeferrableSurface> $deferrableSurfaces;
    final /* synthetic */ CameraGraph $graph;
    final /* synthetic */ SessionConfigAdapter $sessionConfigAdapter;
    final /* synthetic */ Map<DeferrableSurface, StreamId> $surfaceToStreamMap;
    final /* synthetic */ long $timeoutMillis;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ UseCaseSurfaceManager this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public UseCaseSurfaceManager$setupAsync$1$deferred$1(SessionConfigAdapter r1, UseCaseSurfaceManager r2, List<? extends DeferrableSurface> r3, long r4, Map<DeferrableSurface, StreamId> r6, CameraGraph r7, Continuation<? super UseCaseSurfaceManager$setupAsync$1$deferred$1> r8) {
        this.$sessionConfigAdapter = r1;
        this.this$0 = r2;
        this.$deferrableSurfaces = r3;
        this.$timeoutMillis = r4;
        this.$surfaceToStreamMap = r6;
        this.$graph = r7;
        super(2, r8);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r10, Continuation<?> r11) {
        UseCaseSurfaceManager$setupAsync$1$deferred$1 r0 = new UseCaseSurfaceManager$setupAsync$1$deferred$1(this.$sessionConfigAdapter, this.this$0, this.$deferrableSurfaces, this.$timeoutMillis, this.$surfaceToStreamMap, this.$graph, r11);
        r0.L$0 = r10;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Boolean> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Boolean> r2) {
        return ((UseCaseSurfaceManager$setupAsync$1$deferred$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L12;
        if (r1 != 1) goto L10;
        CoroutineScope r02 = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(r11);     // Catch: DeferrableSurface.SurfaceClosedException -> L8 TimeoutCancellationException -> L64
    L18:
        List r112 = (List) r11;     // Catch: DeferrableSurface.SurfaceClosedException -> L8 TimeoutCancellationException -> L64
    L20:
        if (CoroutineScopeKt.isActive(r02) == true) goto L22;
    L59:
        Camera2Logger r10 = Camera2Logger.INSTANCE;
        if (Logger.isInfoEnabled("CXCP") == false) goto L63;
        Log.i(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to get Surfaces: isActive=" + CoroutineScopeKt.isActive(r02) + ", surfaces=" + r112);
    L63:
        return Boxing.boxBoolean(false);
    L22:
        if (r112.isEmpty() == true) goto L59;
        if (UseCaseSurfaceManager.access$areValid(this.this$0, r112) == false) goto L54;
        Object r03 = UseCaseSurfaceManager.access$getLock$p(this.this$0);
        UseCaseSurfaceManager r12 = this.this$0;
        List<DeferrableSurface> r2 = this.$deferrableSurfaces;
        monitor-enter(r03);
        List<DeferrableSurface> r4 = r2;     // Catch: Throwable -> L34
        LinkedHashMap r6 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r4, 10)), 16));     // Catch: Throwable -> L34
        Iterator<T> r42 = r4.iterator();     // Catch: Throwable -> L34
    L30:
        if (r42.hasNext() == false) goto L38;
        Object r5 = r42.next();     // Catch: Throwable -> L34
        Object r7 = r112.get(r2.indexOf((DeferrableSurface) r5));     // Catch: Throwable -> L34
        if (r7 == null) goto L37;
        r6.put((Surface) r7, r5);     // Catch: Throwable -> L34
        goto L30
    L37:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L34
    L38:
        UseCaseSurfaceManager.access$setConfiguredSurfaceMap$p(r12, r6);     // Catch: Throwable -> L34
        UseCaseSurfaceManager.access$setSurfaceListener(r12);     // Catch: Throwable -> L34
        Unit r13 = Unit.INSTANCE;     // Catch: Throwable -> L34
        monitor-exit(r03);
        Map<DeferrableSurface, StreamId> r04 = this.$surfaceToStreamMap;
        List<DeferrableSurface> r14 = this.$deferrableSurfaces;
        CameraGraph r22 = this.$graph;
        UseCaseSurfaceManager r102 = this.this$0;
        Iterator<Map.Entry<DeferrableSurface, StreamId>> r05 = r04.entrySet().iterator();
    L42:
        if (r05.hasNext() == false) goto L47;
        Map.Entry<DeferrableSurface, StreamId> r43 = r05.next();
        int r52 = r43.getValue().m407unboximpl();
        Surface r62 = (Surface) r112.get(r14.indexOf(r43.getKey()));
        Camera2Logger r72 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L46;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Configured " + r62 + " for " + StreamId.m406toStringimpl(r52));
    L46:
        r22.mo226setSurfaceNYG5g8E(r52, r62);
        UseCaseSurfaceManager.access$getInactiveSurfaceCloser$p(r102).mo34configurehB7JTeY(r52, r43.getKey(), r22);
        goto L42
    L47:
        Camera2Logger r103 = Camera2Logger.INSTANCE;
        if (Logger.isInfoEnabled("CXCP") == false) goto L51;
        Log.i(Camera2Logger.access$getTRUNCATED_TAG$p(), "Surface setup complete");
    L51:
        return Boxing.boxBoolean(true);
    L34:
        th = move-exception;
        throw th;
    L54:
        Camera2Logger r06 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L57;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Surface setup failed: Some Surfaces are invalid");
    L57:
        this.$sessionConfigAdapter.reportSurfaceInvalid(this.$deferrableSurfaces.get(r112.indexOf(null)));
        return Boxing.boxBoolean(false);
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L12:
        ResultKt.throwOnFailure(r11);
        CoroutineScope r113 = (CoroutineScope) this.L$0;
        if (this.$sessionConfigAdapter.isSessionConfigValid() == false) goto L74;
        UseCaseSurfaceManager r15 = this.this$0;     // Catch: DeferrableSurface.SurfaceClosedException -> L8 TimeoutCancellationException -> L64
        List<DeferrableSurface> r53 = this.$deferrableSurfaces;     // Catch: DeferrableSurface.SurfaceClosedException -> L8 TimeoutCancellationException -> L64
        long r63 = this.$timeoutMillis;     // Catch: DeferrableSurface.SurfaceClosedException -> L8 TimeoutCancellationException -> L64
        this.L$0 = r113;     // Catch: DeferrableSurface.SurfaceClosedException -> L8 TimeoutCancellationException -> L64
        this.label = 1;     // Catch: DeferrableSurface.SurfaceClosedException -> L8 TimeoutCancellationException -> L64
        Object r16 = UseCaseSurfaceManager.access$getSurfaces(r15, r53, r63, this);     // Catch: DeferrableSurface.SurfaceClosedException -> L8 TimeoutCancellationException -> L64
        if (r16 != r0) goto L17;
        return r0;
    L17:
        r02 = r113;
        r11 = r16;
        goto L18
    L74:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L8:
        e = move-exception;
        Camera2Logger r07 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L72;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to get Surfaces: Surfaces closed", e);
    L72:
        this.$sessionConfigAdapter.reportSurfaceInvalid(e.getDeferrableSurface());
        return Boxing.boxBoolean(false);
    L64:
        Camera2Logger r114 = Camera2Logger.INSTANCE;
        long r104 = this.$timeoutMillis;
        if (Logger.isWarnEnabled("CXCP") == false) goto L68;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to get Surfaces within " + r104 + " ms");
    L68:
        return Boxing.boxBoolean(false);
    }
}
