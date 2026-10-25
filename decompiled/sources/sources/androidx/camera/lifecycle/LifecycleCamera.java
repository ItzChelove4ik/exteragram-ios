package androidx.camera.lifecycle;

import android.annotation.SuppressLint;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.LegacySessionConfig;
import androidx.camera.core.RotationProvider;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.UseCase;
import androidx.camera.core.featuregroup.impl.ResolvedFeatureGroup;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"UsesNonDefaultVisibleForTesting"})
/* loaded from: classes3.dex */
public final class LifecycleCamera implements LifecycleObserver, Camera {
    private SessionConfig mBoundSessionConfig;
    private final CameraUseCaseAdapter mCameraUseCaseAdapter;
    private volatile boolean mIsActive;
    private final LifecycleOwner mLifecycleOwner;
    private final Object mLock;
    private boolean mReleased;
    private final RotationProvider mRotationProvider;
    private boolean mSuspended;

    /* renamed from: $r8$lambda$ZjFrRY-n4_PbuhgqvDfsH48r0Ow, reason: not valid java name */
    public static /* synthetic */ void m626$r8$lambda$ZjFrRYn4_PbuhgqvDfsH48r0Ow(ResolvedFeatureGroup r1, SessionConfig r2) {
        HashSet r0 = new HashSet();
        if (r1 == null) goto L5;
        r0.addAll(r1.getFeatures());
    L5:
        r2.getFeatureSelectionListener().accept(r0);
    }

    public LifecycleCamera(LifecycleOwner r2, CameraUseCaseAdapter r3, RotationProvider r4) {
        this.mLock = new Object();
        this.mIsActive = false;
        this.mSuspended = false;
        this.mReleased = false;
        this.mBoundSessionConfig = null;
        this.mLifecycleOwner = r2;
        this.mCameraUseCaseAdapter = r3;
        this.mRotationProvider = r4;
        if (r2.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED) == false) goto L5;
        r3.attachUseCases();
    L6:
        r2.getLifecycle().addObserver(this);
        return;
    L5:
        r3.detachUseCases();
        goto L6
    }

    private void updateUseCasesRotationProvider(List<UseCase> r2, RotationProvider r3) {
        Iterator<UseCase> r1 = r2.iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        UseCase r22 = r1.next();
        if (r22.isAutoRotationSupported() == false) goto L4;
        r22.setRotationProvider(r3);
        goto L4
    }

    public void bind(SessionConfig r6) {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.mBoundSessionConfig != null) goto L9;
        this.mBoundSessionConfig = r6;     // Catch: Throwable -> L7
    L20:
        CameraUseCaseAdapter r1 = this.mCameraUseCaseAdapter;     // Catch: Throwable -> L7
        r6.getViewPort();     // Catch: Throwable -> L7
        r1.setViewPort(null);     // Catch: Throwable -> L7
        this.mCameraUseCaseAdapter.setEffects(r6.getEffects());     // Catch: Throwable -> L7
        this.mCameraUseCaseAdapter.setSessionType(r6.getSessionType());     // Catch: Throwable -> L7
        this.mCameraUseCaseAdapter.setFrameRate(r6.getFrameRateRange());     // Catch: Throwable -> L7
        if (r6.isAutoRotationEnabled() == false) goto L23;
        updateUseCasesRotationProvider(r6.getUseCases(), this.mRotationProvider);     // Catch: Throwable -> L7
    L23:
        ResolvedFeatureGroup r12 = ResolvedFeatureGroup.resolveFeatureGroup(r6, (CameraInfoInternal) getCameraInfo());     // Catch: Throwable -> L7
        r6.getFeatureSelectionListenerExecutor().execute(new LifecycleCamera$$ExternalSyntheticLambda0(r12, r6));     // Catch: Throwable -> L7
        this.mCameraUseCaseAdapter.addUseCases(r6.getUseCases(), r12);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        boolean r13 = r6.isLegacy();     // Catch: Throwable -> L7
        SessionConfig r3 = this.mBoundSessionConfig;
        if (r13 == false) goto L18;
        if (r3.isLegacy() == false) goto L16;
        ArrayList r14 = new ArrayList(this.mBoundSessionConfig.getUseCases());     // Catch: Throwable -> L7
        r14.addAll(r6.getUseCases());     // Catch: Throwable -> L7
        r6.getViewPort();     // Catch: Throwable -> L7
        this.mBoundSessionConfig = new LegacySessionConfig(r14, null, r6.getEffects());     // Catch: Throwable -> L7
        goto L20
    L16:
        throw new IllegalStateException("Cannot bind use cases when a SessionConfig is already bound to this LifecycleOwner. Please unbind first");     // Catch: Throwable -> L7
    L18:
        if (r3.isLegacy() == true) goto L27;
        this.mBoundSessionConfig = r6;     // Catch: Throwable -> L7
        CameraUseCaseAdapter r15 = this.mCameraUseCaseAdapter;     // Catch: Throwable -> L7
        r15.removeUseCases(r15.getUseCases());     // Catch: Throwable -> L7
        goto L20
    L27:
        throw new IllegalStateException("Cannot bind the SessionConfig when use cases are bound to this LifecycleOwner already. Please unbind first");     // Catch: Throwable -> L7
    }

    @Override // androidx.camera.core.Camera
    public CameraControl getCameraControl() {
        return this.mCameraUseCaseAdapter.getCameraControl();
    }

    @Override // androidx.camera.core.Camera
    public CameraInfo getCameraInfo() {
        return this.mCameraUseCaseAdapter.getCameraInfo();
    }

    public CameraUseCaseAdapter getCameraUseCaseAdapter() {
        return this.mCameraUseCaseAdapter;
    }

    public LifecycleOwner getLifecycleOwner() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        LifecycleOwner r1 = this.mLifecycleOwner;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public List<UseCase> getUseCases() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        List<UseCase> r1 = Collections.unmodifiableList(this.mCameraUseCaseAdapter.getUseCases());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean isBound(UseCase r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        boolean r1 = this.mCameraUseCaseAdapter.getUseCases().contains(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean isLegacySessionConfigBound() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        SessionConfig r1 = this.mBoundSessionConfig;     // Catch: Throwable -> L10
        if (r1 != null) goto L7;
        boolean r12 = false;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r12;
    L7:
        r12 = r1.isLegacy();     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner r2) {
        Object r22 = this.mLock;
        monitor-enter(r22);
        CameraUseCaseAdapter r1 = this.mCameraUseCaseAdapter;     // Catch: Throwable -> L7
        r1.removeUseCases(r1.getUseCases());     // Catch: Throwable -> L7
        monitor-exit(r22);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(LifecycleOwner r1) {
        this.mCameraUseCaseAdapter.setActiveResumingMode(false);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(LifecycleOwner r1) {
        this.mCameraUseCaseAdapter.setActiveResumingMode(true);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(LifecycleOwner r2) {
        Object r22 = this.mLock;
        monitor-enter(r22);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mSuspended == false) goto L7;
    L11:
        monitor-exit(r22);     // Catch: Throwable -> L9
        return;
    L7:
        if (this.mReleased == true) goto L11;
        this.mCameraUseCaseAdapter.attachUseCases();     // Catch: Throwable -> L9
        this.mIsActive = true;     // Catch: Throwable -> L9
        goto L11
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(LifecycleOwner r2) {
        Object r22 = this.mLock;
        monitor-enter(r22);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mSuspended == false) goto L7;
    L11:
        monitor-exit(r22);     // Catch: Throwable -> L9
        return;
    L7:
        if (this.mReleased == true) goto L11;
        this.mCameraUseCaseAdapter.detachUseCases();     // Catch: Throwable -> L9
        this.mIsActive = false;     // Catch: Throwable -> L9
        goto L11
    }

    public void suspend() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.mSuspended == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        onStop(this.mLifecycleOwner);     // Catch: Throwable -> L8
        this.mSuspended = true;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    }

    public void unbindAll() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        List<UseCase> r1 = this.mCameraUseCaseAdapter.getUseCases();     // Catch: Throwable -> L7
        this.mCameraUseCaseAdapter.removeUseCases(r1);     // Catch: Throwable -> L7
        updateUseCasesRotationProvider(r1, null);     // Catch: Throwable -> L7
        this.mBoundSessionConfig = null;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void unsuspend() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.mSuspended == true) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.mSuspended = false;     // Catch: Throwable -> L8
        if (this.mLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED) == false) goto L13;
        onStart(this.mLifecycleOwner);     // Catch: Throwable -> L8
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L8
    }
}
