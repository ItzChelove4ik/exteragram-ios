package androidx.camera.lifecycle;

import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.Logger;
import androidx.camera.core.RotationProvider;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public final class LifecycleCameraRepository {
    private final ArrayDeque<LifecycleOwner> mActiveLifecycleOwners;
    CameraCoordinator mCameraCoordinator;
    private final Map<Key, LifecycleCamera> mCameraMap;
    private final int mDeviceId;
    private final Map<LifecycleCameraRepositoryObserver, Set<Key>> mLifecycleObserverMap;
    private final Object mLock;

    public static abstract class Key {
        public Key() {
        }

        public static Key create(LifecycleOwner r1, CameraIdentifier r2) {
            return new AutoValue_LifecycleCameraRepository_Key(System.identityHashCode(r1), r2);
        }

        public abstract CameraIdentifier getCameraIdentifier();

        public abstract int getLifecycleOwnerHash();
    }

    public static class LifecycleCameraRepositoryObserver implements LifecycleObserver {
        private final LifecycleCameraRepository mLifecycleCameraRepository;
        private final LifecycleOwner mLifecycleOwner;

        public LifecycleCameraRepositoryObserver(LifecycleOwner r1, LifecycleCameraRepository r2) {
            this.mLifecycleOwner = r1;
            this.mLifecycleCameraRepository = r2;
        }

        public LifecycleOwner getLifecycleOwner() {
            return this.mLifecycleOwner;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy(LifecycleOwner r1) {
            this.mLifecycleCameraRepository.unregisterLifecycle(r1);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart(LifecycleOwner r1) {
            this.mLifecycleCameraRepository.setActive(r1);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop(LifecycleOwner r1) {
            this.mLifecycleCameraRepository.setInactive(r1);
        }
    }

    public LifecycleCameraRepository(int r2) {
        this.mLock = new Object();
        this.mCameraMap = new HashMap();
        this.mLifecycleObserverMap = new HashMap();
        this.mActiveLifecycleOwners = new ArrayDeque();
        this.mDeviceId = r2;
    }

    private LifecycleCameraRepositoryObserver getLifecycleCameraRepositoryObserver(LifecycleOwner r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Iterator<LifecycleCameraRepositoryObserver> r3 = this.mLifecycleObserverMap.keySet().iterator();     // Catch: Throwable -> L11
    L6:
        if (r3.hasNext() == false) goto L14;
        LifecycleCameraRepositoryObserver r1 = r3.next();     // Catch: Throwable -> L11
        if (r4.equals(r1.getLifecycleOwner()) == false) goto L6;
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r1;
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return null;
    L11:
        th = move-exception;
        throw th;
    }

    private boolean hasUseCaseBound(LifecycleOwner r5) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        LifecycleCameraRepositoryObserver r52 = getLifecycleCameraRepositoryObserver(r5);     // Catch: Throwable -> L8
        if (r52 != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return false;
    L10:
        Iterator<Key> r53 = this.mLifecycleObserverMap.get(r52).iterator();     // Catch: Throwable -> L8
    L12:
        if (r53.hasNext() == false) goto L18;
        Key r2 = r53.next();     // Catch: Throwable -> L8
        if (((LifecycleCamera) Preconditions.checkNotNull(this.mCameraMap.get(r2))).getUseCases().isEmpty() == true) goto L12;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return true;
    L18:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return false;
    L8:
        th = move-exception;
        throw th;
    }

    private void pruneStaleLifecycleCameras(LifecycleOwner r4) {
        LifecycleCameraRepositoryObserver r42 = getLifecycleCameraRepositoryObserver(r4);
        if (r42 == null) goto L28;
        HashSet r0 = new HashSet();
        Set<Key> r43 = this.mLifecycleObserverMap.get(r42);
        Objects.requireNonNull(r43);
        Iterator<Key> r44 = r43.iterator();
    L7:
        if (r44.hasNext() == false) goto L14;
        Key r1 = r44.next();
        LifecycleCamera r2 = this.mCameraMap.get(r1);
        if (r2 == null) goto L7;
        if (r2.getCameraUseCaseAdapter().isRemoved() == false) goto L7;
        r0.add(r1);
        goto L7
    L14:
        if (r0.isEmpty() == true) goto L19;
        Logger.w("LifecycleCameraRepository", "Removing " + r0.size() + " stale LifecycleCamera(s).");
        Iterator r45 = r0.iterator();
    L17:
        if (r45.hasNext() == false) goto L29;
        LifecycleCamera r02 = this.mCameraMap.get((Key) r45.next());
        Objects.requireNonNull(r02);
        unregisterCamera(r02);
        goto L17
    L29:
        return;
    L19:
        return;
    }

    private void registerCamera(LifecycleCamera r7) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        LifecycleOwner r1 = r7.getLifecycleOwner();     // Catch: Throwable -> L7
        Key r2 = Key.create(r1, r7.getCameraUseCaseAdapter().getAdapterIdentifier());     // Catch: Throwable -> L7
        LifecycleCameraRepositoryObserver r3 = getLifecycleCameraRepositoryObserver(r1);     // Catch: Throwable -> L7
        if (r3 == null) goto L9;
        Set<Key> r4 = this.mLifecycleObserverMap.get(r3);     // Catch: Throwable -> L7
    L10:
        r4.add(r2);     // Catch: Throwable -> L7
        this.mCameraMap.put(r2, r7);     // Catch: Throwable -> L7
        if (r3 != null) goto L13;
        LifecycleCameraRepositoryObserver r72 = new LifecycleCameraRepositoryObserver(r1, this);     // Catch: Throwable -> L7
        this.mLifecycleObserverMap.put(r72, r4);     // Catch: Throwable -> L7
        r1.getLifecycle().addObserver(r72);     // Catch: Throwable -> L7
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        r4 = new HashSet();     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    private void suspendUseCases(LifecycleOwner r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        LifecycleCameraRepositoryObserver r42 = getLifecycleCameraRepositoryObserver(r4);     // Catch: Throwable -> L8
        if (r42 != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        Iterator<Key> r43 = this.mLifecycleObserverMap.get(r42).iterator();     // Catch: Throwable -> L8
    L12:
        if (r43.hasNext() == false) goto L14;
        Key r1 = r43.next();     // Catch: Throwable -> L8
        ((LifecycleCamera) Preconditions.checkNotNull(this.mCameraMap.get(r1))).suspend();     // Catch: Throwable -> L8
        goto L12
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private void unregisterCamera(LifecycleCamera r7) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        LifecycleOwner r1 = r7.getLifecycleOwner();     // Catch: Throwable -> L12
        Key r72 = Key.create(r1, r7.getCameraUseCaseAdapter().getAdapterIdentifier());     // Catch: Throwable -> L12
        this.mCameraMap.remove(r72);     // Catch: Throwable -> L12
        HashSet r2 = new HashSet();     // Catch: Throwable -> L12
        Iterator<LifecycleCameraRepositoryObserver> r3 = this.mLifecycleObserverMap.keySet().iterator();     // Catch: Throwable -> L12
    L6:
        if (r3.hasNext() == false) goto L14;
        LifecycleCameraRepositoryObserver r4 = r3.next();     // Catch: Throwable -> L12
        if (r1.equals(r4.getLifecycleOwner()) == false) goto L6;
        Set<Key> r5 = this.mLifecycleObserverMap.get(r4);     // Catch: Throwable -> L12
        r5.remove(r72);     // Catch: Throwable -> L12
        if (r5.isEmpty() == false) goto L6;
        r2.add(r4.getLifecycleOwner());     // Catch: Throwable -> L12
        goto L6
    L14:
        Iterator r73 = r2.iterator();     // Catch: Throwable -> L12
    L16:
        if (r73.hasNext() == false) goto L18;
        unregisterLifecycle((LifecycleOwner) r73.next());     // Catch: Throwable -> L12
        goto L16
    L18:
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L12:
        th = move-exception;
        throw th;
    }

    private void unsuspendUseCases(LifecycleOwner r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        LifecycleCameraRepositoryObserver r42 = getLifecycleCameraRepositoryObserver(r4);     // Catch: Throwable -> L10
        Iterator<Key> r43 = this.mLifecycleObserverMap.get(r42).iterator();     // Catch: Throwable -> L10
    L6:
        if (r43.hasNext() == false) goto L12;
        Key r1 = r43.next();     // Catch: Throwable -> L10
        LifecycleCamera r12 = this.mCameraMap.get(r1);     // Catch: Throwable -> L10
        if (((LifecycleCamera) Preconditions.checkNotNull(r12)).getUseCases().isEmpty() == true) goto L6;
        r12.unsuspend();     // Catch: Throwable -> L10
        goto L6
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public void bindToLifecycleCamera(LifecycleCamera r5, SessionConfig r6, CameraCoordinator r7) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Preconditions.checkArgument(!r6.getUseCases().isEmpty());     // Catch: Throwable -> L8
        this.mCameraCoordinator = r7;     // Catch: Throwable -> L8
        LifecycleOwner r72 = r5.getLifecycleOwner();     // Catch: Throwable -> L8
        pruneStaleLifecycleCameras(r72);     // Catch: Throwable -> L8
        LifecycleCameraRepositoryObserver r1 = getLifecycleCameraRepositoryObserver(r72);     // Catch: Throwable -> L8
        if (r1 != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        Set<Key> r12 = this.mLifecycleObserverMap.get(r1);     // Catch: Throwable -> L8
        CameraCoordinator r2 = this.mCameraCoordinator;     // Catch: Throwable -> L8
        if (r2 != null) goto L13;
    L14:
        Iterator<Key> r13 = r12.iterator();     // Catch: Throwable -> L8
    L16:
        if (r13.hasNext() == false) goto L40;
        LifecycleCamera r22 = (LifecycleCamera) Preconditions.checkNotNull(this.mCameraMap.get(r13.next()));     // Catch: Throwable -> L8
        if (r22.equals(r5) == true) goto L16;
        if (r22.getUseCases().isEmpty() == true) goto L16;
        if (r22.isLegacySessionConfigBound() == true) goto L27;
        if (r6.isLegacy() == true) goto L27;
        r22.unbindAll();     // Catch: Throwable -> L8
    L27:
        throw new IllegalArgumentException("Multiple LifecycleCameras with use cases are registered to the same LifecycleOwner. Please unbind first.");     // Catch: Throwable -> L8
    L40:
        r5.bind(r6);     // Catch: Throwable -> L8 CameraUseCaseAdapter.CameraException -> L34
    L30:
        if (r72.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED) == false) goto L32;
        setActive(r72);     // Catch: Throwable -> L8
    L32:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L34:
        e = move-exception;
        throw new IllegalArgumentException(e);     // Catch: Throwable -> L8
    L13:
        if (r2.getCameraOperatingMode() == 2) goto L40;
    L8:
        th = move-exception;
        throw th;
    }

    public LifecycleCamera createLifecycleCamera(LifecycleOwner r4, CameraUseCaseAdapter r5, RotationProvider r6) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Key r1 = Key.create(r4, r5.getAdapterIdentifier());     // Catch: Throwable -> L11
        if (this.mCameraMap.get(r1) != null) goto L7;
        boolean r12 = true;
    L8:
        Preconditions.checkArgument(r12, "LifecycleCamera already exists for the given LifecycleOwner and set of cameras");     // Catch: Throwable -> L11
        LifecycleCamera r13 = new LifecycleCamera(r4, r5, r6);     // Catch: Throwable -> L11
        if (r5.getUseCases().isEmpty() == false) goto L14;
        r13.suspend();     // Catch: Throwable -> L11
    L14:
        if (r4.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED) goto L17;
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r13;
    L17:
        registerCamera(r13);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r13;
    L7:
        r12 = false;
    L11:
        th = move-exception;
        throw th;
    }

    public LifecycleCamera getLifecycleCamera(LifecycleOwner r2, CameraIdentifier r3) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Key r22 = Key.create(r2, r3);     // Catch: Throwable -> L11
        LifecycleCamera r23 = this.mCameraMap.get(r22);     // Catch: Throwable -> L11
        if (r23 != null) goto L7;
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r23;
    L7:
        if (r23.getCameraUseCaseAdapter().isRemoved() == false) goto L13;
        unregisterCamera(r23);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return null;
    L11:
        th = move-exception;
        throw th;
    }

    public Collection<LifecycleCamera> getLifecycleCameras() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Collection<LifecycleCamera> r1 = Collections.unmodifiableCollection(this.mCameraMap.values());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void removeLifecycleCameras(Set<Key> r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        if (r4 == null) goto L18;
    L8:
        Iterator<Key> r42 = r4.iterator();     // Catch: Throwable -> L6
    L10:
        if (r42.hasNext() == false) goto L14;
        Key r1 = r42.next();     // Catch: Throwable -> L6
        if (this.mCameraMap.containsKey(r1) == false) goto L10;
        unregisterCamera(this.mCameraMap.get(r1));     // Catch: Throwable -> L6
        goto L10
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        throw th;
    L18:
        r4 = this.mCameraMap.keySet();     // Catch: Throwable -> L6
        goto L8
    }

    public void setActive(LifecycleOwner r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (hasUseCaseBound(r4) == true) goto L11;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L11:
        if (this.mActiveLifecycleOwners.isEmpty() == false) goto L13;
        this.mActiveLifecycleOwners.push(r4);     // Catch: Throwable -> L8
    L20:
        unsuspendUseCases(r4);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L13:
        CameraCoordinator r1 = this.mCameraCoordinator;     // Catch: Throwable -> L8
        if (r1 != null) goto L16;
    L17:
        LifecycleOwner r12 = this.mActiveLifecycleOwners.peek();     // Catch: Throwable -> L8
        if (r4.equals(r12) == true) goto L20;
        suspendUseCases(r12);     // Catch: Throwable -> L8
        this.mActiveLifecycleOwners.remove(r4);     // Catch: Throwable -> L8
        this.mActiveLifecycleOwners.push(r4);     // Catch: Throwable -> L8
        goto L20
    L16:
        if (r1.getCameraOperatingMode() == 2) goto L20;
        goto L17
    }

    public void setInactive(LifecycleOwner r3) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mActiveLifecycleOwners.remove(r3);     // Catch: Throwable -> L7
        suspendUseCases(r3);     // Catch: Throwable -> L7
        if (this.mActiveLifecycleOwners.isEmpty() == true) goto L9;
        unsuspendUseCases(this.mActiveLifecycleOwners.peek());     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void unbindAll(Set<Key> r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        if (r4 == null) goto L18;
    L8:
        Iterator<Key> r42 = r4.iterator();     // Catch: Throwable -> L6
    L10:
        if (r42.hasNext() == false) goto L14;
        Key r1 = r42.next();     // Catch: Throwable -> L6
        LifecycleCamera r12 = this.mCameraMap.get(r1);     // Catch: Throwable -> L6
        if (r12 == null) goto L10;
        r12.unbindAll();     // Catch: Throwable -> L6
        setInactive(r12.getLifecycleOwner());     // Catch: Throwable -> L6
        goto L10
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        throw th;
    L18:
        r4 = this.mCameraMap.keySet();     // Catch: Throwable -> L6
        goto L8
    }

    public void unregisterLifecycle(LifecycleOwner r5) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        LifecycleCameraRepositoryObserver r1 = getLifecycleCameraRepositoryObserver(r5);     // Catch: Throwable -> L8
        if (r1 != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        setInactive(r5);     // Catch: Throwable -> L8
        Iterator<Key> r52 = this.mLifecycleObserverMap.get(r1).iterator();     // Catch: Throwable -> L8
    L12:
        if (r52.hasNext() == false) goto L14;
        Key r2 = r52.next();     // Catch: Throwable -> L8
        this.mCameraMap.remove(r2);     // Catch: Throwable -> L8
        goto L12
    L14:
        this.mLifecycleObserverMap.remove(r1);     // Catch: Throwable -> L8
        r1.getLifecycleOwner().getLifecycle().removeObserver(r1);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
