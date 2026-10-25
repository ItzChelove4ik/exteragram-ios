package androidx.camera.camera2.adapter;

import android.media.MediaCodec;
import android.util.Log;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.internal.StreamUseCaseUtil;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 02\u00020\u0001:\u00010B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010#\u001a\u0004\u0018\u00010\u001aJ\u0006\u0010$\u001a\u00020\u0006J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u000bJ4\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\u0010\u0010*\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030+0\u0003H\u0007J\"\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0003H\u0007J\u0016\u0010-\u001a\u00020\f2\f\u0010.\u001a\b\u0012\u0002\b\u0003\u0018\u00010/H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR'\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0012\u0010\u000eR\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0010\u001a\u0004\b\u001b\u0010\u001cR!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0010\u001a\u0004\b \u0010!¨\u00061"}, d2 = {"Landroidx/camera/camera2/adapter/SessionConfigAdapter;", _UrlKt.FRAGMENT_ENCODE_SET, "useCases", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "isPrimary", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/Collection;Z)V", "surfaceToStreamUseCaseMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/DeferrableSurface;", _UrlKt.FRAGMENT_ENCODE_SET, "getSurfaceToStreamUseCaseMap", "()Ljava/util/Map;", "surfaceToStreamUseCaseMap$delegate", "Lkotlin/Lazy;", "surfaceToStreamUseHintMap", "getSurfaceToStreamUseHintMap", "surfaceToStreamUseHintMap$delegate", "validatingBuilder", "Landroidx/camera/core/impl/SessionConfig$ValidatingBuilder;", "getValidatingBuilder", "()Landroidx/camera/core/impl/SessionConfig$ValidatingBuilder;", "validatingBuilder$delegate", "sessionConfig", "Landroidx/camera/core/impl/SessionConfig;", "getSessionConfig", "()Landroidx/camera/core/impl/SessionConfig;", "sessionConfig$delegate", "deferrableSurfaces", _UrlKt.FRAGMENT_ENCODE_SET, "getDeferrableSurfaces", "()Ljava/util/List;", "deferrableSurfaces$delegate", "getValidSessionConfigOrNull", "isSessionConfigValid", "reportSurfaceInvalid", _UrlKt.FRAGMENT_ENCODE_SET, "deferrableSurface", "getSurfaceToStreamUseCaseMapping", "sessionConfigs", "useCaseConfigs", "Landroidx/camera/core/impl/UseCaseConfig;", "getSurfaceToStreamUseHintMapping", "getStreamUseHintForContainerClass", "kClass", "Ljava/lang/Class;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionConfigAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionConfigAdapter.kt\nandroidx/camera/camera2/adapter/SessionConfigAdapter\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,209:1\n85#2,4:210\n136#2,4:219\n295#3,2:214\n1761#3,3:216\n1563#3:223\n1634#3,3:224\n*S KotlinDebug\n*F\n+ 1 SessionConfigAdapter.kt\nandroidx/camera/camera2/adapter/SessionConfigAdapter\n*L\n95#1:210,4\n130#1:219,4\n101#1:214,2\n128#1:216,3\n54#1:223\n54#1:224,3\n*E\n"})
/* loaded from: classes3.dex */
public final class SessionConfigAdapter {
    public static final Companion Companion = null;
    private final Lazy deferrableSurfaces$delegate;
    private final boolean isPrimary;
    private final Lazy sessionConfig$delegate;
    private final Lazy surfaceToStreamUseCaseMap$delegate;
    private final Lazy surfaceToStreamUseHintMap$delegate;
    private final Collection<UseCase> useCases;
    private final Lazy validatingBuilder$delegate;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/adapter/SessionConfigAdapter$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getSessionConfig", "Landroidx/camera/core/impl/SessionConfig;", "Landroidx/camera/core/UseCase;", "isPrimary", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final SessionConfig getSessionConfig(UseCase r1, boolean r2) {
            if (r2 == false) goto L6;
            return r1.getSessionConfig();
        L6:
            return r1.getSecondarySessionConfig();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.adapter.SessionConfigAdapter$reportSurfaceInvalid$2", f = "SessionConfigAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.adapter.SessionConfigAdapter$reportSurfaceInvalid$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SessionConfig $sessionConfig;
        int label;

        public AnonymousClass2(SessionConfig r1, Continuation<? super AnonymousClass2> r2) {
            this.$sessionConfig = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.$sessionConfig, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L11;
            ResultKt.throwOnFailure(r2);
            SessionConfig r22 = this.$sessionConfig;
            if (r22 == null) goto L10;
            SessionConfig.ErrorListener r23 = r22.getErrorListener();
            if (r23 == null) goto L10;
            r23.onError(this.$sessionConfig, SessionConfig.SessionError.SESSION_ERROR_SURFACE_NEEDS_RESET);
        L10:
            return Unit.INSTANCE;
        L11:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* renamed from: $r8$lambda$-icGDqvAHKSdZE3aOVJMyOoUyFk, reason: not valid java name */
    public static List m20$r8$lambda$icGDqvAHKSdZE3aOVJMyOoUyFk(SessionConfigAdapter r3) {
        if (r3.getValidatingBuilder().isValid() == false) goto L12;
        SessionConfig.OutputConfig r0 = r3.getSessionConfig().getPostviewOutputConfig();
        if (r0 == null) goto L11;
        ArrayList r1 = new ArrayList();
        r1.addAll(r3.getSessionConfig().getSurfaces());
        r1.add(r0.getSurface());
        List r02 = Collections.unmodifiableList(r1);
        if (r02 == null) goto L11;
        return r02;
    L11:
        return r3.getSessionConfig().getSurfaces();
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    }

    public static SessionConfig.ValidatingBuilder $r8$lambda$MWNVpTDD7KkQSD9XEE8XNPfWPZc(SessionConfigAdapter r5) {
        SessionConfig.ValidatingBuilder r0 = new SessionConfig.ValidatingBuilder();
        Iterator<UseCase> r1 = r5.useCases.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        UseCase r2 = r1.next();
        r0.add(Companion.getSessionConfig(r2, r5.isPrimary));
        goto L4
    L6:
        return r0;
    }

    /* renamed from: $r8$lambda$g_aOe16a-DUtVM_sJpjR-JZDPhs, reason: not valid java name */
    public static Map m21$r8$lambda$g_aOe16aDUtVM_sJpjRJZDPhs(SessionConfigAdapter r5) {
        Collection<UseCase> r0 = r5.useCases;
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
        Iterator<T> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L7;
        UseCase r2 = (UseCase) r02.next();
        r1.add(Companion.getSessionConfig(r2, r5.isPrimary));
        goto L4
    L7:
        return r5.getSurfaceToStreamUseHintMapping(r1);
    }

    public static SessionConfig $r8$lambda$h5igSN6lhSEpYqjILIQEMI6OvfE(SessionConfigAdapter r1) {
        if (r1.getValidatingBuilder().isValid() == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L5:
        return r1.getValidatingBuilder().build();
    }

    /* renamed from: $r8$lambda$nnJYHDr2I66xowM4-43xaBstCic, reason: not valid java name */
    public static Map m22$r8$lambda$nnJYHDr2I66xowM443xaBstCic(SessionConfigAdapter r6) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        Iterator<UseCase> r2 = r6.useCases.iterator();
    L4:
        if (r2.hasNext() == false) goto L7;
        UseCase r3 = r2.next();
        r0.add(Companion.getSessionConfig(r3, r6.isPrimary));
        r1.add(r3.getCurrentConfig());
        goto L4
    L7:
        return r6.getSurfaceToStreamUseCaseMapping(r0, r1);
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SessionConfigAdapter(Collection<? extends UseCase> r1, boolean r2) {
        this.useCases = r1;
        this.isPrimary = r2;
        this.surfaceToStreamUseCaseMap$delegate = LazyKt.lazy(new SessionConfigAdapter$$ExternalSyntheticLambda0(this));
        this.surfaceToStreamUseHintMap$delegate = LazyKt.lazy(new SessionConfigAdapter$$ExternalSyntheticLambda1(this));
        this.validatingBuilder$delegate = LazyKt.lazy(new SessionConfigAdapter$$ExternalSyntheticLambda2(this));
        this.sessionConfig$delegate = LazyKt.lazy(new SessionConfigAdapter$$ExternalSyntheticLambda3(this));
        this.deferrableSurfaces$delegate = LazyKt.lazy(new SessionConfigAdapter$$ExternalSyntheticLambda4(this));
    }

    public /* synthetic */ SessionConfigAdapter(Collection r1, boolean r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = true;
    L5:
        this(r1, r2);
    }

    private final SessionConfig getSessionConfig() {
        return (SessionConfig) this.sessionConfig$delegate.getValue();
    }

    private final long getStreamUseHintForContainerClass(Class<?> r1) {
        if (Intrinsics.areEqual(r1, MediaCodec.class) == false) goto L7;
        return OutputStream.StreamUseHint.Companion.m359getVIDEO_RECORD4VYZOf8();
    L7:
        return OutputStream.StreamUseHint.Companion.m358getDEFAULT4VYZOf8();
    }

    private final SessionConfig.ValidatingBuilder getValidatingBuilder() {
        return (SessionConfig.ValidatingBuilder) this.validatingBuilder$delegate.getValue();
    }

    public final List<DeferrableSurface> getDeferrableSurfaces() {
        return (List) this.deferrableSurfaces$delegate.getValue();
    }

    public final Map<DeferrableSurface, Long> getSurfaceToStreamUseCaseMap() {
        return (Map) this.surfaceToStreamUseCaseMap$delegate.getValue();
    }

    public final Map<DeferrableSurface, Long> getSurfaceToStreamUseCaseMapping(Collection<SessionConfig> r3, Collection<? extends UseCaseConfig<?>> r4) {
        Collection<SessionConfig> r2 = r3;
        if (r2.isEmpty() == true) goto L15;
        Iterator<T> r22 = r2.iterator();
    L7:
        if (r22.hasNext() == false) goto L15;
        if (((SessionConfig) r22.next()).getTemplateType() != 5) goto L7;
        Camera2Logger r23 = Camera2Logger.INSTANCE;
        if (Logger.isErrorEnabled("CXCP") == false) goto L14;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "ZSL in populateSurfaceToStreamUseCaseMapping()");
    L14:
        return MapsKt.emptyMap();
    L15:
        LinkedHashMap r24 = new LinkedHashMap();
        StreamUseCaseUtil.INSTANCE.populateSurfaceToStreamUseCaseMapping(r3, r4, r24);
        return r24;
    }

    public final Map<DeferrableSurface, Long> getSurfaceToStreamUseHintMap() {
        return (Map) this.surfaceToStreamUseHintMap$delegate.getValue();
    }

    public final Map<DeferrableSurface, Long> getSurfaceToStreamUseHintMapping(Collection<SessionConfig> r7) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<SessionConfig> r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L14;
        SessionConfig r1 = r72.next();
        Iterator<DeferrableSurface> r2 = r1.getSurfaces().iterator();
    L7:
        if (r2.hasNext() == false) goto L4;
        DeferrableSurface r3 = r2.next();
        Config r4 = r1.getImplementationOptions();
        Config.Option<Long> r5 = Camera2ImplConfig.STREAM_USE_HINT_OPTION;
        if (r4.containsOption(r5) == false) goto L13;
        if (r1.getImplementationOptions().retrieveOption(r5) == null) goto L13;
        r0.put(r3, r1.getImplementationOptions().retrieveOption(r5));
    L13:
        r0.put(r3, Long.valueOf(getStreamUseHintForContainerClass(r3.getContainerClass())));
        goto L7
    L14:
        return r0;
    }

    public final SessionConfig getValidSessionConfigOrNull() {
        if (isSessionConfigValid() == true) goto L5;
        return null;
    L5:
        return getSessionConfig();
    }

    public final boolean isSessionConfigValid() {
        return getValidatingBuilder().isValid();
    }

    public final void reportSurfaceInvalid(DeferrableSurface r10) {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unavailable " + r10 + ", notify SessionConfig invalid");
    L5:
        Iterator<T> r02 = this.useCases.iterator();
    L7:
        if (r02.hasNext() == false) goto L11;
        Object r1 = r02.next();
        if (Companion.getSessionConfig((UseCase) r1, this.isPrimary).getSurfaces().contains(r10) == false) goto L7;
    L12:
        UseCase r12 = (UseCase) r1;
        if (r12 == null) goto L15;
        SessionConfig r9 = r12.getSessionConfig();
    L16:
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().getImmediate()), null, null, new AnonymousClass2(r9, null), 3, null);
        return;
    L15:
        r9 = null;
        goto L16
    L11:
        r1 = null;
        goto L12
    }
}
