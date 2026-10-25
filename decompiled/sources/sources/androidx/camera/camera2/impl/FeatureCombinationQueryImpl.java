package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.adapter.ZslControlNoOpImpl;
import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.workaround.TemplateParamsQuirkOverride;
import androidx.camera.camera2.config.CameraConfig;
import androidx.camera.camera2.impl.CameraGraphConfigProvider;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.ConfigQueryResult;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.StreamFormat;
import androidx.camera.core.Logger;
import androidx.camera.core.featuregroup.impl.FeatureCombinationQuery;
import androidx.camera.core.impl.SessionConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/impl/FeatureCombinationQueryImpl;", "Landroidx/camera/core/featuregroup/impl/FeatureCombinationQuery;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraPipe", "Landroidx/camera/camera2/pipe/CameraPipe;", "cameraQuirks", "Landroidx/camera/camera2/compat/quirk/CameraQuirks;", "<init>", "(Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/camera2/pipe/CameraPipe;Landroidx/camera/camera2/compat/quirk/CameraQuirks;)V", "isSupported", _UrlKt.FRAGMENT_ENCODE_SET, "sessionConfig", "Landroidx/camera/core/impl/SessionConfig;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeatureCombinationQueryImpl implements FeatureCombinationQuery {
    private final CameraMetadata cameraMetadata;
    private final CameraPipe cameraPipe;
    private final CameraQuirks cameraQuirks;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FeatureCombinationQueryImpl$isSupported$1", f = "FeatureCombinationQueryImpl.kt", i = {}, l = {59}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nFeatureCombinationQueryImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FeatureCombinationQueryImpl.kt\nandroidx/camera/camera2/impl/FeatureCombinationQueryImpl$isSupported$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,76:1\n85#2,2:77\n88#2:87\n1563#3:79\n1634#3,2:80\n1563#3:82\n1634#3,3:83\n1636#3:86\n*S KotlinDebug\n*F\n+ 1 FeatureCombinationQueryImpl.kt\nandroidx/camera/camera2/impl/FeatureCombinationQueryImpl$isSupported$1\n*L\n60#1:77,2\n60#1:87\n62#1:79\n62#1:80,2\n63#1:82\n63#1:83,3\n62#1:86\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.FeatureCombinationQueryImpl$isSupported$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ CameraGraphConfigProvider.CameraGraphCreationResult $creationResult;
        int label;
        final /* synthetic */ FeatureCombinationQueryImpl this$0;

        public AnonymousClass1(FeatureCombinationQueryImpl r1, CameraGraphConfigProvider.CameraGraphCreationResult r2, Continuation<? super AnonymousClass1> r3) {
            this.this$0 = r1;
            this.$creationResult = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new AnonymousClass1(this.this$0, this.$creationResult, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Boolean> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Boolean> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r11) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r11);
        L11:
            CameraGraphConfigProvider.CameraGraphCreationResult r10 = this.$creationResult;
            ConfigQueryResult r112 = (ConfigQueryResult) r11;
            int r02 = r112.m252unboximpl();
            Camera2Logger r12 = Camera2Logger.INSTANCE;
            if (Logger.isDebugEnabled("CXCP") == false) goto L23;
            String r13 = Camera2Logger.access$getTRUNCATED_TAG$p();
            List<CameraStream.Config> r2 = r10.getConfig().getStreams();
            ArrayList r3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r2, 10));
            Iterator<T> r22 = r2.iterator();
        L15:
            if (r22.hasNext() == false) goto L21;
            List<OutputStream.Config> r5 = ((CameraStream.Config) r22.next()).getOutputs();
            ArrayList r6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r5, 10));
            Iterator<T> r52 = r5.iterator();
        L18:
            if (r52.hasNext() == false) goto L20;
            OutputStream.Config r7 = (OutputStream.Config) r52.next();
            r6.add("size=" + r7.getSize() + ", format=" + StreamFormat.m395toStringimpl(r7.m318getFormat8FPWQzE()) + ", dynamicRangeProfile" + r7.m317getDynamicRangeProfileOoVcG5w());
            goto L18
        L20:
            r3.add(r6);
            goto L15
        L21:
            Log.d(r13, "FeatureCombinationQueryImpl#isSupported: result = " + ConfigQueryResult.m251toStringimpl(r02) + " for sessionParameters = " + r10.getConfig().getSessionParameters() + " and streams = " + r3);
        L23:
            return Boxing.boxBoolean(ConfigQueryResult.m249equalsimpl0(r112.m252unboximpl(), ConfigQueryResult.Companion.m253getSUPPORTEDXp6DSB4()));
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r11);
            CameraPipe r113 = FeatureCombinationQueryImpl.access$getCameraPipe$p(this.this$0);
            CameraGraph.Config r14 = this.$creationResult.getConfig();
            this.label = 1;
            r11 = r113.mo238isConfigSupportedNpXggIU(r14, this);
            if (r11 != r0) goto L11;
            return r0;
        }
    }

    public FeatureCombinationQueryImpl(CameraMetadata r1, CameraPipe r2, CameraQuirks r3) {
        this.cameraMetadata = r1;
        this.cameraPipe = r2;
        this.cameraQuirks = r3;
    }

    public static final /* synthetic */ CameraPipe access$getCameraPipe$p(FeatureCombinationQueryImpl r0) {
        return r0.cameraPipe;
    }

    @Override // androidx.camera.core.featuregroup.impl.FeatureCombinationQuery
    public boolean isSupported(SessionConfig r14) {
        return ((Boolean) BuildersKt.runBlocking$default(null, new AnonymousClass1(this, CameraGraphConfigProvider.m53create79VDu0o$default(new CameraGraphConfigProvider(new CameraCallbackMap(), new ComboRequestListener(), new CameraConfig(this.cameraMetadata.mo237getCameraDz_R5H8(), null), this.cameraQuirks, new ZslControlNoOpImpl(), new TemplateParamsQuirkOverride(this.cameraQuirks.getQuirks()), this.cameraMetadata, null, null, 384, null), CameraGraph.OperatingMode.Companion.m222getNORMAL2uNL3no(), r14, true, null, null, null, null, 120, null), null), 1, null)).booleanValue();
    }
}
