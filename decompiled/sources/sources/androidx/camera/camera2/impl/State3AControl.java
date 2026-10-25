package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.util.Log;
import androidx.camera.camera2.adapter.SessionConfigAdapter;
import androidx.camera.camera2.compat.workaround.AutoFlashAEModeDisabler;
import androidx.camera.camera2.impl.UseCaseManager;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.SessionConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001TB!\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\u00020\u000e2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u001c\u001a\u00020\u00122\n\u0010\u001b\u001a\u00060\u0019j\u0002`\u001aH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ)\u0010\"\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\"\u0010#J\u0017\u0010%\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000eH\u0002¢\u0006\u0004\b%\u0010&J\u001b\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010'\u001a\u00020\u000e¢\u0006\u0004\b(\u0010)J\u001b\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010'\u001a\u00020\u001f¢\u0006\u0004\b*\u0010+J\u001d\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010'\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b,\u0010-J\u001d\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010'\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b.\u0010-J\u000f\u0010/\u001a\u00020\u0012H\u0016¢\u0006\u0004\b/\u00100J\u001d\u00102\u001a\u00020\u00122\f\u00101\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016¢\u0006\u0004\b2\u00103J\r\u00104\u001a\u00020\u000e¢\u0006\u0004\b4\u00105R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u00106\u001a\u0004\b7\u00108R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u00109R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0018\u0010?\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010@R \u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120B0A8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010E\u001a\u00020\u00158\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\u000e8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010I\u001a\u00020\u000e8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bI\u0010HR\u0016\u0010J\u001a\u00020\u001f8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010L\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bL\u0010MR\u0018\u0010N\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bN\u0010MR(\u0010S\u001a\u0004\u0018\u00010>2\b\u0010'\u001a\u0004\u0018\u00010>8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010R¨\u0006U"}, d2 = {"Landroidx/camera/camera2/impl/State3AControl;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "Landroidx/camera/camera2/impl/UseCaseManager$RunningUseCasesChangeListener;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "Landroidx/camera/camera2/compat/workaround/AutoFlashAEModeDisabler;", "aeModeDisabler", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;Landroidx/camera/camera2/compat/workaround/AutoFlashAEModeDisabler;Landroidx/camera/camera2/impl/UseCaseThreads;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "useCases", _UrlKt.FRAGMENT_ENCODE_SET, "calculateTemplateFromUseCases", "(Ljava/util/Set;)I", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.UPDATE, "()Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "myRevision", "applyUpdate", "(J)V", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "failAllPendingSignals", "(Ljava/lang/Exception;)V", "flashMode", _UrlKt.FRAGMENT_ENCODE_SET, "tryExternalFlashAeMode", "preferredAeMode", "getFinalPreferredAeMode", "(IZLjava/lang/Integer;)I", "template", "getDefaultAfMode", "(I)I", "value", "setFlashModeAsync", "(I)Lkotlinx/coroutines/Deferred;", "setTryExternalFlashAeModeAsync", "(Z)Lkotlinx/coroutines/Deferred;", "setPreferredAeModeAsync", "(Ljava/lang/Integer;)Lkotlinx/coroutines/Deferred;", "setPreferredFocusModeAsync", "reset", "()V", "runningUseCases", "onRunningUseCasesChanged", "(Ljava/util/Set;)V", "getFinalSupportedAeMode", "()I", "Landroidx/camera/camera2/impl/CameraProperties;", "getCameraProperties", "()Landroidx/camera/camera2/impl/CameraProperties;", "Landroidx/camera/camera2/compat/workaround/AutoFlashAEModeDisabler;", "Landroidx/camera/camera2/impl/UseCaseThreads;", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "_requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CompletableDeferred;", "pendingSignals", "Ljava/util/List;", "currentRevision", "J", "_flashMode", "I", "_template", "_tryExternalFlashAeMode", "Z", "_preferredAeMode", "Ljava/lang/Integer;", "_preferredFocusMode", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "setRequestControl", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "requestControl", "StateSnapshot", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nState3AControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 State3AControl.kt\nandroidx/camera/camera2/impl/State3AControl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,328:1\n1#2:329\n194#3:330\n194#3:331\n1869#4,2:332\n1869#4,2:342\n1869#4,2:344\n85#5,4:334\n85#5,4:338\n*S KotlinDebug\n*F\n+ 1 State3AControl.kt\nandroidx/camera/camera2/impl/State3AControl\n*L\n126#1:330\n172#1:331\n255#1:332,2\n237#1:342,2\n239#1:344,2\n294#1:334,4\n298#1:338,4\n*E\n"})
/* loaded from: classes3.dex */
public final class State3AControl implements UseCaseCameraControl, UseCaseManager.RunningUseCasesChangeListener {
    private int _flashMode;
    private Integer _preferredAeMode;
    private Integer _preferredFocusMode;
    private UseCaseCameraRequestControl _requestControl;
    private int _template;
    private boolean _tryExternalFlashAeMode;
    private final AutoFlashAEModeDisabler aeModeDisabler;
    private final CameraProperties cameraProperties;
    private long currentRevision;
    private final Object lock;
    private final List<CompletableDeferred<Unit>> pendingSignals;
    private final UseCaseThreads threads;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0082\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0011\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u000fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0015\u0010\u000fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0019\u001a\u0004\b\u001c\u0010\u001b¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/impl/State3AControl$StateSnapshot;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "flashMode", "template", _UrlKt.FRAGMENT_ENCODE_SET, "tryExternalFlashAeMode", "preferredAeMode", "preferredFocusMode", "<init>", "(IIZLjava/lang/Integer;Ljava/lang/Integer;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "I", "getFlashMode", "getTemplate", "Z", "getTryExternalFlashAeMode", "()Z", "Ljava/lang/Integer;", "getPreferredAeMode", "()Ljava/lang/Integer;", "getPreferredFocusMode", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class StateSnapshot {
        private final int flashMode;
        private final Integer preferredAeMode;
        private final Integer preferredFocusMode;
        private final int template;
        private final boolean tryExternalFlashAeMode;

        public StateSnapshot(int r1, int r2, boolean r3, Integer r4, Integer r5) {
            this.flashMode = r1;
            this.template = r2;
            this.tryExternalFlashAeMode = r3;
            this.preferredAeMode = r4;
            this.preferredFocusMode = r5;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof StateSnapshot) == true) goto L8;
            return false;
        L8:
            StateSnapshot r52 = (StateSnapshot) r5;
            if (this.flashMode == r52.flashMode) goto L12;
            return false;
        L12:
            if (this.template == r52.template) goto L15;
            return false;
        L15:
            if (this.tryExternalFlashAeMode == r52.tryExternalFlashAeMode) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.preferredAeMode, r52.preferredAeMode) == true) goto L21;
            return false;
        L21:
            if (Intrinsics.areEqual(this.preferredFocusMode, r52.preferredFocusMode) == true) goto L23;
            return false;
        L23:
            return true;
        }

        public final int getFlashMode() {
            return this.flashMode;
        }

        public final Integer getPreferredAeMode() {
            return this.preferredAeMode;
        }

        public final Integer getPreferredFocusMode() {
            return this.preferredFocusMode;
        }

        public final int getTemplate() {
            return this.template;
        }

        public final boolean getTryExternalFlashAeMode() {
            return this.tryExternalFlashAeMode;
        }

        public int hashCode() {
            int r0 = ((((Integer.hashCode(this.flashMode) * 31) + Integer.hashCode(this.template)) * 31) + Boolean.hashCode(this.tryExternalFlashAeMode)) * 31;
            Integer r1 = this.preferredAeMode;
            int r2 = 0;
            if (r1 != null) goto L5;
            int r12 = 0;
        L6:
            int r02 = (r0 + r12) * 31;
            Integer r3 = this.preferredFocusMode;
            if (r3 == null) goto L11;
            r2 = r3.hashCode();
        L11:
            return r02 + r2;
        L5:
            r12 = r1.hashCode();
            goto L6
        }

        public String toString() {
            return "StateSnapshot(flashMode=" + this.flashMode + ", template=" + this.template + ", tryExternalFlashAeMode=" + this.tryExternalFlashAeMode + ", preferredAeMode=" + this.preferredAeMode + ", preferredFocusMode=" + this.preferredFocusMode + ')';
        }
    }

    public static Unit $r8$lambda$k1tXCgViVcdvfHU16cTrszMiFg8(List r2, State3AControl r3, Throwable r4) {
        if (r4 == null) goto L7;
        Iterator r0 = r2.iterator();
    L5:
        if (r0.hasNext() == false) goto L11;
        ((CompletableDeferred) r0.next()).completeExceptionally(r4);
    L11:
        Object r42 = r3.lock;
        monitor-enter(r42);
        r3.pendingSignals.removeAll(r2);     // Catch: Throwable -> L17
        monitor-exit(r42);
        return Unit.INSTANCE;
    L17:
        th = move-exception;
        throw th;
    L7:
        Iterator r43 = r2.iterator();
    L9:
        if (r43.hasNext() == false) goto L11;
        ((CompletableDeferred) r43.next()).complete(Unit.INSTANCE);
        goto L9
    }

    public State3AControl(CameraProperties r1, AutoFlashAEModeDisabler r2, UseCaseThreads r3) {
        this.cameraProperties = r1;
        this.aeModeDisabler = r2;
        this.threads = r3;
        this.lock = new Object();
        this.pendingSignals = new ArrayList();
        this._flashMode = 2;
        this._template = 1;
    }

    public static final /* synthetic */ void access$applyUpdate(State3AControl r0, long r1) {
        r0.applyUpdate(r1);
    }

    public static final /* synthetic */ int access$calculateTemplateFromUseCases(State3AControl r0, Set r1) {
        return r0.calculateTemplateFromUseCases(r1);
    }

    public static final /* synthetic */ Object access$getLock$p(State3AControl r0) {
        return r0.lock;
    }

    public static final /* synthetic */ int access$get_template$p(State3AControl r0) {
        return r0._template;
    }

    public static final /* synthetic */ void access$set_template$p(State3AControl r0, int r1) {
        r0._template = r1;
    }

    public static final /* synthetic */ Deferred access$update(State3AControl r0) {
        return r0.update();
    }

    private final void applyUpdate(long r8) {
        UseCaseCameraRequestControl r0 = getRequestControl();
        if (r0 != null) goto L6;
        failAllPendingSignals(new CameraControl.OperationCanceledException("Camera is not active."));
        return;
    L6:
        Object r1 = this.lock;
        monitor-enter(r1);
        if (r8 != this.currentRevision) goto L12;
        boolean r82 = true;
    L13:
        monitor-exit(r1);
        if (r82 == true) goto L16;
        return;
    L16:
        Object r83 = this.lock;
        monitor-enter(r83);
        StateSnapshot r12 = new StateSnapshot(this._flashMode, this._template, this._tryExternalFlashAeMode, this._preferredAeMode, this._preferredFocusMode);     // Catch: Throwable -> L39
        monitor-exit(r83);
        int r84 = getFinalPreferredAeMode(r12.getFlashMode(), r12.getTryExternalFlashAeMode(), r12.getPreferredAeMode());
        Integer r2 = r12.getPreferredFocusMode();
        if (r2 == null) goto L23;
        int r13 = r2.intValue();
    L49:
        Deferred r85 = UseCaseCameraRequestControl.submitParameters$default(r0, MapsKt.mapOf(new Pair[]{TuplesKt.to(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(CameraMetadataIntegrationKt.getSupportedAeMode(this.cameraProperties.getMetadata(), r84))), TuplesKt.to(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(CameraMetadataIntegrationKt.getSupportedAfMode(this.cameraProperties.getMetadata(), r13))), TuplesKt.to(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(CameraMetadataIntegrationKt.getSupportedAwbMode(this.cameraProperties.getMetadata(), 1)))}), null, null, 6, null);     // Catch: Exception -> L31
        Object r9 = this.lock;     // Catch: Exception -> L31
        monitor-enter(r9);     // Catch: Exception -> L31
        List r02 = CollectionsKt.toList(this.pendingSignals);     // Catch: Throwable -> L33
        monitor-exit(r9);     // Catch: Exception -> L31
        r85.invokeOnCompletion(new State3AControl$$ExternalSyntheticLambda0(r02, this));     // Catch: Exception -> L31
        return;
    L33:
        th = move-exception;
        throw th;     // Catch: Exception -> L31
    L31:
        e = move-exception;
        failAllPendingSignals(e);
        return;
    L23:
        r13 = getDefaultAfMode(r12.getTemplate());
    L39:
        th = move-exception;
        throw th;
    L12:
        r82 = false;
    L43:
        th = move-exception;
        throw th;
    }

    private final int calculateTemplateFromUseCases(Set<? extends UseCase> r4) {
        Integer r2 = null;
        SessionConfig r3 = new SessionConfigAdapter(r4, false, 2, null).getValidSessionConfigOrNull();
        if (r3 == null) goto L12;
        CaptureConfig r32 = r3.getRepeatingCaptureConfig();
        if (r32 == null) goto L14;
        Integer r33 = Integer.valueOf(r32.getTemplateType());
        if (r33.intValue() == (-1)) goto L9;
        r2 = r33;
    L9:
        if (r2 != null) goto L11;
        return 1;
    L11:
        return r2.intValue();
    L14:
        return 1;
    L12:
        return 1;
    }

    private final void failAllPendingSignals(Exception r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        List r1 = CollectionsKt.toList(this.pendingSignals);     // Catch: Throwable -> L11
        this.pendingSignals.clear();     // Catch: Throwable -> L11
        monitor-exit(r0);
        Iterator r2 = r1.iterator();
    L8:
        if (r2.hasNext() == false) goto L10;
        ((CompletableDeferred) r2.next()).completeExceptionally(r3);
        goto L8
    L10:
        return;
    L11:
        th = move-exception;
        throw th;
    }

    private final int getDefaultAfMode(int r2) {
        if (r2 != 1) goto L5;
        return 4;
    L5:
        if (r2 == 3) goto L7;
        return 4;
    L7:
        return 3;
    }

    private final int getFinalPreferredAeMode(int r1, boolean r2, Integer r3) {
        if (r3 == null) goto L4;
        int r12 = r3.intValue();
    L11:
        if (r2 == true) goto L13;
    L18:
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L21;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "State3AControl.getFinalPreferredAeMode: preferAeMode = " + r12);
    L21:
        return r12;
    L13:
        if (CameraMetadataIntegrationKt.isExternalFlashAeModeSupported(this.cameraProperties.getMetadata()) == false) goto L18;
        Camera2Logger r02 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "State3AControl.invalidate: trying external flash AE mode.");
    L17:
        r12 = 5;
        goto L18
    L4:
        if (r1 != 0) goto L6;
        r12 = this.aeModeDisabler.getCorrectedAeMode(2);
        goto L11
    L6:
        if (r1 == 1) goto L8;
        r12 = 1;
        goto L11
    L8:
        r12 = 3;
        goto L11
    }

    private final Deferred<Unit> update() {
        CompletableDeferred<Unit> r0 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        Ref.LongRef r2 = new Ref.LongRef();
        Object r3 = this.lock;
        monitor-enter(r3);
        this.pendingSignals.add(r0);     // Catch: Throwable -> L8
        long r4 = this.currentRevision + 1;     // Catch: Throwable -> L8
        this.currentRevision = r4;     // Catch: Throwable -> L8
        r2.element = r4;     // Catch: Throwable -> L8
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r3);
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new State3AControl$update$$inlined$confineLaunch$1(null, this, r2), 3, null);
        return r0;
    L8:
        th = move-exception;
        throw th;
    }

    public final int getFinalSupportedAeMode() {
        Object r0 = this.lock;
        monitor-enter(r0);
        int r5 = CameraMetadataIntegrationKt.getSupportedAeMode(this.cameraProperties.getMetadata(), getFinalPreferredAeMode(this._flashMode, this._tryExternalFlashAeMode, this._preferredAeMode));     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r5;
    L7:
        th = move-exception;
        throw th;
    }

    public UseCaseCameraRequestControl getRequestControl() {
        return this._requestControl;
    }

    @Override // androidx.camera.camera2.impl.UseCaseManager.RunningUseCasesChangeListener
    public void onRunningUseCasesChanged(Set<? extends UseCase> r8) {
        Set r82 = CollectionsKt.toSet(r8);
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new State3AControl$onRunningUseCasesChanged$$inlined$confineLaunch$1(null, r82, this), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void reset() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this._tryExternalFlashAeMode = false;     // Catch: Throwable -> L9
        this._preferredAeMode = null;     // Catch: Throwable -> L9
        this._preferredFocusMode = null;     // Catch: Throwable -> L9
        this._flashMode = 2;     // Catch: Throwable -> L9
        this._template = 1;     // Catch: Throwable -> L9
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        update();
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final Deferred<Unit> setFlashModeAsync(int r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this._flashMode = r2;     // Catch: Throwable -> L8
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r0);
        return update();
    L8:
        th = move-exception;
        throw th;
    }

    public final Deferred<Unit> setPreferredAeModeAsync(Integer r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this._preferredAeMode = r2;     // Catch: Throwable -> L8
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r0);
        return update();
    L8:
        th = move-exception;
        throw th;
    }

    public final Deferred<Unit> setPreferredFocusModeAsync(Integer r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this._preferredFocusMode = r2;     // Catch: Throwable -> L8
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r0);
        return update();
    L8:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void setRequestControl(UseCaseCameraRequestControl r1) {
        this._requestControl = r1;
        update();
    }

    public final Deferred<Unit> setTryExternalFlashAeModeAsync(boolean r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this._tryExternalFlashAeMode = r2;     // Catch: Throwable -> L8
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r0);
        return update();
    L8:
        th = move-exception;
        throw th;
    }
}
