package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.adapter.SessionConfigAdapter;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.SessionConfig;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$updateRepeatingRequestAsync$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {428}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$updateRepeatingRequestAsync$1$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,742:1\n85#2,4:743\n85#2,4:747\n85#2,4:752\n85#2,4:756\n1#3:751\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$updateRepeatingRequestAsync$1$1\n*L\n404#1:743,4\n410#1:747,4\n416#1:752,4\n427#1:756,4\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$updateRepeatingRequestAsync$1$1 extends SuspendLambda implements Function1<Continuation<? super Deferred<? extends Unit>>, Object> {
    final /* synthetic */ boolean $isPrimary;
    final /* synthetic */ Collection<UseCase> $runningUseCases;
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public UseCaseCameraRequestControlImpl$updateRepeatingRequestAsync$1$1(Collection<? extends UseCase> r1, boolean r2, UseCaseCameraRequestControlImpl r3, Continuation<? super UseCaseCameraRequestControlImpl$updateRepeatingRequestAsync$1$1> r4) {
        this.$runningUseCases = r1;
        this.$isPrimary = r2;
        this.this$0 = r3;
        super(1, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r4) {
        return new UseCaseCameraRequestControlImpl$updateRepeatingRequestAsync$1$1(this.$runningUseCases, this.$isPrimary, this.this$0, r4);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Deferred<? extends Unit>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Deferred<Unit>> r1) {
        return ((UseCaseCameraRequestControlImpl$updateRepeatingRequestAsync$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L9;
        if (r1 != 1) goto L7;
        ResultKt.throwOnFailure(r8);
        return r8;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r8);
        Camera2Logger r82 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L12;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl: Building SessionConfig...");
    L12:
        SessionConfig r12 = new SessionConfigAdapter(this.$runningUseCases, this.$isPrimary).getValidSessionConfigOrNull();
        if (r12 != null) goto L19;
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Using default SessionConfig");
    L17:
        SessionConfig.Builder r13 = new SessionConfig.Builder();
        r13.setTemplateType(1);
        r12 = r13.build();
    L19:
        if (Logger.isDebugEnabled("CXCP") == false) goto L21;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl: SessionConfig built. Updating state...");
    L21:
        UseCaseCameraRequestControlImpl.access$getInfoBundleMap$p(this.this$0).put(UseCaseCameraRequestControl.Type.SESSION_CONFIG, UseCaseCameraRequestControlImpl.Companion.access$toInfoBundle(UseCaseCameraRequestControlImpl.Companion, r12, UseCaseCameraRequestControlImpl.access$getThreads$p(this.this$0).getSequentialExecutor()));
        Set<StreamId> r14 = UseCaseCameraRequestControlImpl.access$getUseCaseGraphContext$p(this.this$0).getStreamIdsFromSurfaces(r12.getRepeatingCaptureConfig().getSurfaces());
        if (Logger.isDebugEnabled("CXCP") == false) goto L24;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl: State update processing.");
    L24:
        UseCaseCameraRequestControlImpl r83 = this.this$0;
        UseCaseCameraRequestControlImpl.InfoBundle r3 = UseCaseCameraRequestControlImpl.access$merge(r83, UseCaseCameraRequestControlImpl.access$getInfoBundleMap$p(r83));
        this.label = 1;
        Object r7 = UseCaseCameraRequestControlImpl.access$updateCameraStateAsync(r83, r3, r14, this);
        if (r7 != r0) goto L27;
        return r0;
    L27:
        return r7;
    }
}
