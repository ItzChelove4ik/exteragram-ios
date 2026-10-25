package androidx.camera.core.impl;

import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class CameraRepository implements InternalCameraPresenceListener {
    private CameraFactory mCameraFactory;
    private final Map<String, CameraInternal> mCameras;
    private final Object mCamerasLock;
    private CallbackToFutureAdapter.Completer<Void> mDeinitCompleter;
    private ListenableFuture<Void> mDeinitFuture;
    private final Set<CameraInternal> mReleasingCameras;

    public static /* synthetic */ void $r8$lambda$6tNk7BHQV_7i2HLwF1CZkzV1hrM(CameraRepository r2, CameraInternal r3) {
        Object r0 = r2.mCamerasLock;
        monitor-enter(r0);
        r2.mReleasingCameras.remove(r3);     // Catch: Throwable -> L7
        if (r2.mReleasingCameras.isEmpty() == false) goto L9;
        Preconditions.checkNotNull(r2.mDeinitCompleter);     // Catch: Throwable -> L7
        r2.mDeinitCompleter.set(null);     // Catch: Throwable -> L7
        r2.mDeinitCompleter = null;     // Catch: Throwable -> L7
        r2.mDeinitFuture = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ Object $r8$lambda$EVR2_LFGuegIUMqvZY9O12S_ZVM(CameraRepository r1, CallbackToFutureAdapter.Completer r2) {
        Object r0 = r1.mCamerasLock;
        monitor-enter(r0);
        r1.mDeinitCompleter = r2;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return "CameraRepository-deinit";
    L8:
        th = move-exception;
        throw th;
    }

    public CameraRepository() {
        this.mCamerasLock = new Object();
        this.mCameras = new LinkedHashMap();
        this.mReleasingCameras = new HashSet();
    }

    public ListenableFuture<Void> deinit() {
        Object r0 = this.mCamerasLock;
        monitor-enter(r0);
        boolean r1 = this.mCameras.isEmpty();     // Catch: Throwable -> L10
        ListenableFuture<Void> r2 = this.mDeinitFuture;
        if (r1 == false) goto L14;
        if (r2 == null) goto L9;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r2;
    L9:
        r2 = Futures.immediateFuture(null);     // Catch: Throwable -> L10
        goto L12
    L14:
        if (r2 != null) goto L16;
        r2 = CallbackToFutureAdapter.getFuture(new CameraRepository$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L10
        this.mDeinitFuture = r2;     // Catch: Throwable -> L10
    L16:
        this.mReleasingCameras.addAll(this.mCameras.values());     // Catch: Throwable -> L10
        Iterator<CameraInternal> r12 = this.mCameras.values().iterator();     // Catch: Throwable -> L10
    L18:
        if (r12.hasNext() == false) goto L20;
        CameraInternal r3 = r12.next();     // Catch: Throwable -> L10
        r3.release().addListener(new CameraRepository$$ExternalSyntheticLambda1(this, r3), CameraXExecutors.directExecutor());     // Catch: Throwable -> L10
        goto L18
    L20:
        this.mCameras.clear();     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r2;
    L10:
        th = move-exception;
        throw th;
    }

    public CameraInternal getCamera(String r4) {
        Object r1 = this.mCamerasLock;
        monitor-enter(r1);
        CameraInternal r3 = this.mCameras.get(r4);     // Catch: Throwable -> L8
        if (r3 == null) goto L11;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return r3;
    L11:
        throw new IllegalArgumentException("Invalid camera: " + r4);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public LinkedHashSet<CameraInternal> getCameras() {
        Object r0 = this.mCamerasLock;
        monitor-enter(r0);
        LinkedHashSet<CameraInternal> r1 = new LinkedHashSet(this.mCameras.values());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void init(CameraFactory r7) {
        this.mCameraFactory = r7;
        Object r0 = this.mCamerasLock;
        monitor-enter(r0);
        Iterator<String> r1 = r7.getAvailableCameraIds().iterator();     // Catch: Throwable -> L11 CameraUnavailableException -> L13
    L5:
        if (r1.hasNext() == false) goto L15;
        String r2 = r1.next();     // Catch: Throwable -> L11 CameraUnavailableException -> L13
        Logger.d("CameraRepository", "Added camera: " + r2);     // Catch: Throwable -> L11 CameraUnavailableException -> L13
        CameraInternal r22 = this.mCameras.put(r2, r7.getCamera(r2));     // Catch: Throwable -> L11 CameraUnavailableException -> L13
        if (r22 == null) goto L5;
        r22.release();     // Catch: Throwable -> L11 CameraUnavailableException -> L13
        goto L5
    L15:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L13:
        e = move-exception;
        throw new InitializationException(e);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.InternalCameraPresenceListener
    public void onCamerasUpdated(List<String> r7) {
        HashMap r0 = new HashMap();
        Object r1 = this.mCamerasLock;
        monitor-enter(r1);
        HashSet r2 = new HashSet(r7);     // Catch: Throwable -> L38
        r2.removeAll(this.mCameras.keySet());     // Catch: Throwable -> L38
        monitor-exit(r1);     // Catch: Throwable -> L38
        Iterator r12 = r2.iterator();     // Catch: CameraUnavailableException -> L35
    L7:
        if (r12.hasNext() == false) goto L11;
        String r22 = (String) r12.next();     // Catch: CameraUnavailableException -> L35
        r0.put(r22, this.mCameraFactory.getCamera(r22));     // Catch: CameraUnavailableException -> L35
        goto L7
    L11:
        Object r13 = this.mCamerasLock;
        monitor-enter(r13);
        HashSet r23 = new HashSet(this.mCameras.keySet());     // Catch: Throwable -> L17
        r23.removeAll(r7);     // Catch: Throwable -> L17
        ArrayList r3 = new ArrayList();     // Catch: Throwable -> L17
        Iterator r24 = r23.iterator();     // Catch: Throwable -> L17
    L15:
        if (r24.hasNext() == false) goto L19;
        String r4 = (String) r24.next();     // Catch: Throwable -> L17
        r3.add(this.mCameras.get(r4));     // Catch: Throwable -> L17
        goto L15
    L19:
        LinkedHashMap r25 = new LinkedHashMap();     // Catch: Throwable -> L17
        Iterator<String> r72 = r7.iterator();     // Catch: Throwable -> L17
    L21:
        if (r72.hasNext() == false) goto L26;
        String r42 = r72.next();     // Catch: Throwable -> L17
        if (this.mCameras.containsKey(r42) == true) goto L24;
        r25.put(r42, (CameraInternal) r0.get(r42));     // Catch: Throwable -> L17
        goto L21
    L24:
        r25.put(r42, this.mCameras.get(r42));     // Catch: Throwable -> L17
        goto L21
    L26:
        this.mCameras.clear();     // Catch: Throwable -> L17
        this.mCameras.putAll(r25);     // Catch: Throwable -> L17
        int r6 = r3.size();     // Catch: Throwable -> L17
        int r73 = 0;
    L27:
        if (r73 >= r6) goto L31;
        Object r02 = r3.get(r73);     // Catch: Throwable -> L17
        r73 = r73 + 1;     // Catch: Throwable -> L17
        CameraInternal r03 = (CameraInternal) r02;     // Catch: Throwable -> L17
        if (r03 == null) goto L27;
        r03.onRemoved();     // Catch: Throwable -> L17
        goto L27
    L31:
        monitor-exit(r13);     // Catch: Throwable -> L17
        return;
    L17:
        th = move-exception;
        throw th;
    L35:
        e = move-exception;
        throw new CameraUpdateException("Failed to create CameraInternal", e);
    L38:
        th = move-exception;
        throw th;
    }
}
