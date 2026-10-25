package androidx.camera.core.impl;

import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraPresenceListener;
import androidx.camera.core.CameraState;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \\2\u00020\u0001:\u0003]^\\B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\u00020\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b\f\u0010\rJ+\u0010\u0011\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ%\u0010 \u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\"\u0010\u0016J\u000f\u0010#\u001a\u00020\u000bH\u0002¢\u0006\u0004\b#\u0010\u001cJ\u001d\u0010%\u001a\u00020\u000b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\t0\u000eH\u0002¢\u0006\u0004\b%\u0010&J\u001d\u0010(\u001a\u00020\u000b2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\t0\u000eH\u0002¢\u0006\u0004\b(\u0010&J%\u0010/\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020)2\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-¢\u0006\u0004\b/\u00100J\r\u00101\u001a\u00020\u000b¢\u0006\u0004\b1\u0010\u001cJ\u0015\u00104\u001a\u00020\u000b2\u0006\u00103\u001a\u000202¢\u0006\u0004\b4\u00105J\u001d\u00108\u001a\u00020\u000b2\u0006\u00103\u001a\u0002062\u0006\u00107\u001a\u00020\u0002¢\u0006\u0004\b8\u00109J\u0015\u0010:\u001a\u00020\u000b2\u0006\u00103\u001a\u000206¢\u0006\u0004\b:\u0010;R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010<R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010=R\u0014\u0010>\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010?R\u001c\u0010B\u001a\b\u0012\u0002\b\u0003\u0018\u00010A8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010,\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010DR\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010ER$\u0010G\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0018\u00010F8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0018\u0010*\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010IR\u0018\u0010K\u001a\u00060JR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010LR\u001c\u0010M\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010QR\u001a\u0010S\u001a\b\u0012\u0004\u0012\u0002020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\u001a\u0010V\u001a\b\u0012\u0004\u0012\u00020U0R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010TR&\u0010Z\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0X0W8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\bZ\u0010[¨\u0006_"}, d2 = {"Landroidx/camera/core/impl/CameraPresenceProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/concurrent/Executor;", "backgroundExecutor", "Ljava/util/concurrent/ScheduledExecutorService;", "scheduledExecutor", "<init>", "(Ljava/util/concurrent/Executor;Ljava/util/concurrent/ScheduledExecutorService;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/CameraIdentifier;", "newFilteredIdentifiers", _UrlKt.FRAGMENT_ENCODE_SET, "processFilteredCameraIdUpdate", "(Ljava/util/List;)V", _UrlKt.FRAGMENT_ENCODE_SET, "addedCameras", "removedCameras", "notifyPublicListeners", "(Ljava/util/Set;Ljava/util/Set;)V", _UrlKt.FRAGMENT_ENCODE_SET, "systemCameraId", "conditionallySetupCameraStateObserver", "(Ljava/lang/String;)V", "Landroidx/camera/core/impl/CameraInfoInternal;", "cameraInfoInternal", "setupCameraStateObserver", "(Landroidx/camera/core/impl/CameraInfoInternal;)V", "triggerRefreshWithRetries", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "attemptsLeft", "initialIds", "scheduleRetryAttempt", "(ILjava/util/List;)V", "removeCameraStateObserver", "clearAllCameraStateObservers", "addedIds", "notifyPublicCamerasAdded", "(Ljava/util/Set;)V", "removedIds", "notifyPublicCamerasRemoved", "Landroidx/camera/core/impl/CameraValidator;", "cameraValidator", "Landroidx/camera/core/impl/CameraFactory;", "cameraFactory", "Landroidx/camera/core/impl/CameraRepository;", "cameraRepository", "startup", "(Landroidx/camera/core/impl/CameraValidator;Landroidx/camera/core/impl/CameraFactory;Landroidx/camera/core/impl/CameraRepository;)V", "shutdown", "Landroidx/camera/core/impl/InternalCameraPresenceListener;", "listener", "addDependentInternalListener", "(Landroidx/camera/core/impl/InternalCameraPresenceListener;)V", "Landroidx/camera/core/CameraPresenceListener;", "executor", "addCameraPresenceListener", "(Landroidx/camera/core/CameraPresenceListener;Ljava/util/concurrent/Executor;)V", "removeCameraPresenceListener", "(Landroidx/camera/core/CameraPresenceListener;)V", "Ljava/util/concurrent/Executor;", "Ljava/util/concurrent/ScheduledExecutorService;", "observerLock", "Ljava/lang/Object;", "retryLock", "Ljava/util/concurrent/ScheduledFuture;", "retryScanFuture", "Ljava/util/concurrent/ScheduledFuture;", "Landroidx/camera/core/impl/CameraFactory;", "Landroidx/camera/core/impl/CameraRepository;", "Landroidx/camera/core/impl/Observable;", "sourcePresenceObservable", "Landroidx/camera/core/impl/Observable;", "Landroidx/camera/core/impl/CameraValidator;", "Landroidx/camera/core/impl/CameraPresenceProvider$SourceObservableObserver;", "sourceObserver", "Landroidx/camera/core/impl/CameraPresenceProvider$SourceObservableObserver;", "currentFilteredIds", "Ljava/util/List;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isMonitoring", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "dependentInternalListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/camera/core/impl/CameraPresenceProvider$ListenerWrapper;", "publicApiListeners", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/lifecycle/Observer;", "Landroidx/camera/core/CameraState;", "cameraStateObservers", "Ljava/util/Map;", "Companion", "ListenerWrapper", "SourceObservableObserver", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraPresenceProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraPresenceProvider.kt\nandroidx/camera/core/impl/CameraPresenceProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,478:1\n1563#2:479\n1634#2,3:480\n1563#2:483\n1634#2,3:484\n1869#2,2:487\n1869#2,2:489\n1869#2,2:491\n1563#2:493\n1634#2,3:494\n1869#2,2:497\n1869#2,2:499\n1869#2,2:501\n1617#2,9:503\n1869#2:512\n1870#2:514\n1626#2:515\n1869#2,2:518\n1869#2,2:520\n1869#2,2:522\n295#2,2:524\n1#3:513\n216#4,2:516\n*S KotlinDebug\n*F\n+ 1 CameraPresenceProvider.kt\nandroidx/camera/core/impl/CameraPresenceProvider\n*L\n89#1:479\n89#1:480,3\n221#1:483\n221#1:484,3\n225#1:487,2\n238#1:489,2\n246#1:491,2\n251#1:493\n251#1:494,3\n254#1:497,2\n265#1:499,2\n266#1:501,2\n427#1:503,9\n427#1:512\n427#1:514\n427#1:515\n461#1:518,2\n467#1:520,2\n95#1:522,2\n433#1:524,2\n427#1:513\n429#1:516,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraPresenceProvider {
    public static final Companion Companion = null;
    private final Executor backgroundExecutor;
    private CameraFactory cameraFactory;
    private CameraRepository cameraRepository;
    private final Map<String, Observer<CameraState>> cameraStateObservers;
    private CameraValidator cameraValidator;
    private volatile List<CameraIdentifier> currentFilteredIds;
    private final CopyOnWriteArrayList<InternalCameraPresenceListener> dependentInternalListeners;
    private final AtomicBoolean isMonitoring;
    private final Object observerLock;
    private final CopyOnWriteArrayList<ListenerWrapper> publicApiListeners;
    private final Object retryLock;
    private ScheduledFuture<?> retryScanFuture;
    private final ScheduledExecutorService scheduledExecutor;
    private final SourceObservableObserver sourceObserver;
    private Observable<List<CameraIdentifier>> sourcePresenceObservable;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/camera/core/impl/CameraPresenceProvider$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_SCAN_RETRIES", _UrlKt.FRAGMENT_ENCODE_SET, "RETRY_DELAY_MS", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/camera/core/impl/CameraPresenceProvider$ListenerWrapper;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/CameraPresenceListener;", "listener", "Ljava/util/concurrent/Executor;", "executor", "<init>", "(Landroidx/camera/core/CameraPresenceListener;Ljava/util/concurrent/Executor;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/core/CameraPresenceListener;", "getListener", "()Landroidx/camera/core/CameraPresenceListener;", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ListenerWrapper {
        private final Executor executor;
        private final CameraPresenceListener listener;

        public ListenerWrapper(CameraPresenceListener r1, Executor r2) {
            this.listener = r1;
            this.executor = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ListenerWrapper) == true) goto L8;
            return false;
        L8:
            ListenerWrapper r52 = (ListenerWrapper) r5;
            if (Intrinsics.areEqual(this.listener, r52.listener) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.executor, r52.executor) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final Executor getExecutor() {
            return this.executor;
        }

        public final CameraPresenceListener getListener() {
            return this.listener;
        }

        public int hashCode() {
            return (this.listener.hashCode() * 31) + this.executor.hashCode();
        }

        public String toString() {
            return "ListenerWrapper(listener=" + this.listener + ", executor=" + this.executor + ')';
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Landroidx/camera/core/impl/CameraPresenceProvider$SourceObservableObserver;", "Landroidx/camera/core/impl/Observable$Observer;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/CameraIdentifier;", "<init>", "(Landroidx/camera/core/impl/CameraPresenceProvider;)V", "onNewData", _UrlKt.FRAGMENT_ENCODE_SET, "rawCameraIdentifiers", "onError", "t", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraPresenceProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraPresenceProvider.kt\nandroidx/camera/core/impl/CameraPresenceProvider$SourceObservableObserver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,478:1\n1563#2:479\n1634#2,3:480\n1563#2:483\n1634#2,3:484\n1563#2:487\n1634#2,3:488\n*S KotlinDebug\n*F\n+ 1 CameraPresenceProvider.kt\nandroidx/camera/core/impl/CameraPresenceProvider$SourceObservableObserver\n*L\n137#1:479\n137#1:480,3\n144#1:483\n144#1:484,3\n179#1:487\n179#1:488,3\n*E\n"})
    public final class SourceObservableObserver implements Observable.Observer<List<? extends CameraIdentifier>> {
        final /* synthetic */ CameraPresenceProvider this$0;

        public SourceObservableObserver(CameraPresenceProvider r1) {
            this.this$0 = r1;
        }

        @Override // androidx.camera.core.impl.Observable.Observer
        public void onError(Throwable r3) {
            if (CameraPresenceProvider.access$isMonitoring$p(this.this$0).get() == false) goto L10;
            Logger.e("CameraPresencePrvdr", "Error from source camera presence observable. Triggering refresh.", r3);
            Observable r2 = CameraPresenceProvider.access$getSourcePresenceObservable$p(this.this$0);
            if (r2 == null) goto L9;
            r2.fetchData();
            return;
        L9:
            return;
        }

        @Override // androidx.camera.core.impl.Observable.Observer
        public /* bridge */ /* synthetic */ void onNewData(List<? extends CameraIdentifier> r1) {
            onNewData2(r1);
        }

        /* renamed from: onNewData, reason: avoid collision after fix types in other method */
        public void onNewData2(List<CameraIdentifier> r12) {
            if (CameraPresenceProvider.access$isMonitoring$p(this.this$0).get() == false) goto L56;
            CameraFactory r0 = CameraPresenceProvider.access$getCameraFactory$p(this.this$0);
            if (r0 == null) goto L57;
            CameraRepository r1 = CameraPresenceProvider.access$getCameraRepository$p(this.this$0);
            if (r1 == null) goto L58;
            CameraValidator r2 = CameraPresenceProvider.access$getCameraValidator$p(this.this$0);
            if (r2 != null) goto L15;
            return;
        L15:
            if (r12 == null) goto L20;
            List<CameraIdentifier> r122 = r12;
            List<String> r4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r122, 10));
            Iterator<T> r123 = r122.iterator();
        L18:
            if (r123.hasNext() == false) goto L22;
            r4.add(((CameraIdentifier) r123.next()).getInternalId());
        L22:
            if ((r0 instanceof CameraFactory.Interrogator) == false) goto L51;
            List r124 = CameraPresenceProvider.access$getCurrentFilteredIds$p(this.this$0);     // Catch: Exception -> L27
            List<String> r8 = ((CameraFactory.Interrogator) r0).getAvailableCameraIds(r4);     // Catch: Exception -> L27
            ArrayList r9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r8, 10));     // Catch: Exception -> L27
            Iterator<T> r82 = r8.iterator();     // Catch: Exception -> L27
        L25:
            if (r82.hasNext() == false) goto L29;
            r9.add(CameraIdentifier.Factory.create$default((String) r82.next(), null, null, 6, null));     // Catch: Exception -> L27
            goto L25
        L29:
            Set<CameraIdentifier> r125 = SetsKt.minus(CollectionsKt.toSet(r124), CollectionsKt.toSet(r9));     // Catch: Exception -> L27
            if (r125.isEmpty() == true) goto L51;
            if (r2.isChangeInvalid(r1.getCameras(), r125) == false) goto L51;
            Logger.w("CameraPresencePrvdr", "Camera removal update invalid. Aborting.");     // Catch: Exception -> L27
            return;
        L27:
            e = move-exception;
            Logger.w("CameraPresencePrvdr", "Failed to interrogate camera factory. Falling back to full update.", e);
        L51:
            r0.onCameraIdsUpdated(r4);     // Catch: Exception -> L46
            Set<String> r126 = r0.getAvailableCameraIds();
            ArrayList r02 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r126, 10));
            Iterator<T> r127 = r126.iterator();
        L39:
            if (r127.hasNext() == false) goto L42;
            r02.add(CameraIdentifier.Factory.create$default((String) r127.next(), null, null, 6, null));
            goto L39
        L42:
            if (Intrinsics.areEqual(r02, CameraPresenceProvider.access$getCurrentFilteredIds$p(this.this$0)) == false) goto L44;
            return;
        L44:
            CameraPresenceProvider.access$processFilteredCameraIdUpdate(this.this$0, r02);
            return;
        L46:
            e = move-exception;
            Logger.w("CameraPresencePrvdr", "CameraFactory failed to update. The camera list may be stale until the next update.", e);
            return;
        L20:
            r4 = CollectionsKt.emptyList();
            goto L22
        L58:
            return;
        L57:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$51iRTvG9nLL8ikpWexPoKIPCpD4(CameraInfoInternal r0, Observer r1) {
        setupCameraStateObserver$lambda$0$1(r0, r1);
    }

    public static void $r8$lambda$BscuJl6k0EvV8pGpFPAQYqSaQDA(CameraPresenceProvider r1, CameraPresenceListener r2) {
        Set<CameraIdentifier> r12 = CollectionsKt.toSet(r1.currentFilteredIds);
        if (r12.isEmpty() == true) goto L6;
        r2.onCamerasAdded(r12);
        return;
    }

    public static /* synthetic */ void $r8$lambda$CMjAF6F7y3XQjKNB_LfwCTl3rzk(ListenerWrapper r0, Set r1) {
        notifyPublicCamerasRemoved$lambda$0$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$DrkEXVnEYz8kluOrg5rj41y3Cas(CameraInternal r0, Observer r1) {
        removeCameraStateObserver$lambda$0$0(r0, r1);
    }

    /* renamed from: $r8$lambda$JU6f07PZ72IZthRn-OxF4PINzoI, reason: not valid java name */
    public static boolean m596$r8$lambda$JU6f07PZ72IZthRnOxF4PINzoI(CameraPresenceListener r0, ListenerWrapper r1) {
        return Intrinsics.areEqual(r1.getListener(), r0);
    }

    /* renamed from: $r8$lambda$JuGR6QCPogNqVPIA0bnDaAcAw-o, reason: not valid java name */
    public static /* synthetic */ void m597$r8$lambda$JuGR6QCPogNqVPIA0bnDaAcAwo(CameraPresenceProvider r0) {
        setupCameraStateObserver$lambda$0$0$0(r0);
    }

    public static /* synthetic */ void $r8$lambda$NKb5tywml7fLh72E80xwb7S7jCQ(CameraPresenceProvider r0, String r1, CameraState r2) {
        setupCameraStateObserver$lambda$0$0(r0, r1, r2);
    }

    public static void $r8$lambda$Oh_I9sg_rHhTkSn3Z1HhlVaPC6E(CameraPresenceProvider r2, List r3, int r4) {
        r2.backgroundExecutor.execute(new CameraPresenceProvider$$ExternalSyntheticLambda11(r2, r3, r4));
    }

    public static /* synthetic */ void $r8$lambda$nayhqDvZFdl3bDjg8uQf2cjlMg8(ListenerWrapper r0, Set r1) {
        notifyPublicCamerasAdded$lambda$0$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$ougxnBvQAPKxbEvMJlj_ZPCmIxY(CameraPresenceProvider r0, List r1, int r2) {
        scheduleRetryAttempt$lambda$0$0(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$t0eoGS01UsRMi5eVUUzlGpqblZ0(List r0, Observer r1, String r2) {
        clearAllCameraStateObservers$lambda$2$0(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$te2u7cAesgfp-nEhgwA1yKuyRHo, reason: not valid java name */
    public static void m598$r8$lambda$te2u7cAesgfpnEhgwA1yKuyRHo(CameraPresenceProvider r2) {
        Iterator<T> r0 = r2.currentFilteredIds.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r2.conditionallySetupCameraStateObserver(((CameraIdentifier) r0.next()).getInternalId());
        goto L4
    }

    static {
        Companion = new Companion(null);
    }

    public CameraPresenceProvider(Executor r1, ScheduledExecutorService r2) {
        this.backgroundExecutor = r1;
        this.scheduledExecutor = r2;
        this.observerLock = new Object();
        this.retryLock = new Object();
        this.sourceObserver = new SourceObservableObserver(this);
        this.currentFilteredIds = CollectionsKt.emptyList();
        this.isMonitoring = new AtomicBoolean(false);
        this.dependentInternalListeners = new CopyOnWriteArrayList();
        this.publicApiListeners = new CopyOnWriteArrayList();
        this.cameraStateObservers = new LinkedHashMap();
    }

    public static final /* synthetic */ CameraFactory access$getCameraFactory$p(CameraPresenceProvider r0) {
        return r0.cameraFactory;
    }

    public static final /* synthetic */ CameraRepository access$getCameraRepository$p(CameraPresenceProvider r0) {
        return r0.cameraRepository;
    }

    public static final /* synthetic */ CameraValidator access$getCameraValidator$p(CameraPresenceProvider r0) {
        return r0.cameraValidator;
    }

    public static final /* synthetic */ List access$getCurrentFilteredIds$p(CameraPresenceProvider r0) {
        return r0.currentFilteredIds;
    }

    public static final /* synthetic */ Observable access$getSourcePresenceObservable$p(CameraPresenceProvider r0) {
        return r0.sourcePresenceObservable;
    }

    public static final /* synthetic */ AtomicBoolean access$isMonitoring$p(CameraPresenceProvider r0) {
        return r0.isMonitoring;
    }

    public static final /* synthetic */ void access$processFilteredCameraIdUpdate(CameraPresenceProvider r0, List r1) {
        r0.processFilteredCameraIdUpdate(r1);
    }

    private final void clearAllCameraStateObservers() {
        Object r0 = this.observerLock;
        monitor-enter(r0);
    L26:
        th = move-exception;
        throw th;
    L5:
        if (this.cameraStateObservers.isEmpty() == false) goto L8;
        monitor-exit(r0);
        return;
    L8:
        Map r1 = MapsKt.toMap(this.cameraStateObservers);     // Catch: Throwable -> L26
        this.cameraStateObservers.clear();     // Catch: Throwable -> L26
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L26
        monitor-exit(r0);
        CameraRepository r5 = this.cameraRepository;
        if (r5 == null) goto L25;
        LinkedHashSet<CameraInternal> r52 = r5.getCameras();
        ArrayList r02 = new ArrayList();
        Iterator<T> r53 = r52.iterator();
    L14:
        if (r53.hasNext() == false) goto L21;
        CameraInternal r22 = (CameraInternal) r53.next();
        if (r22 == null) goto L18;
        CameraInfoInternal r23 = r22.getCameraInfoInternal();
    L19:
        if (r23 == null) goto L14;
        r02.add(r23);
        goto L14
    L18:
        r23 = null;
        goto L19
    L21:
        Logger.d("CameraPresencePrvdr", "Clearing all " + r1.size() + " state observers.");
        Iterator r54 = r1.entrySet().iterator();
    L23:
        if (r54.hasNext() == false) goto L37;
        Map.Entry r12 = (Map.Entry) r54.next();
        String r24 = (String) r12.getKey();
        CameraXExecutors.mainThreadExecutor().execute(new CameraPresenceProvider$$ExternalSyntheticLambda8(r02, (Observer) r12.getValue(), r24));
        goto L23
    L37:
        return;
    }

    private static final void clearAllCameraStateObservers$lambda$2$0(List r2, Observer r3, String r4) {
        Iterator r22 = r2.iterator();     // Catch: IllegalArgumentException -> L15
    L4:
        if (r22.hasNext() == false) goto L8;
        Object r0 = r22.next();     // Catch: IllegalArgumentException -> L15
        if (Intrinsics.areEqual(((CameraInfoInternal) r0).getCameraId(), r4) == false) goto L4;
    L9:
        CameraInfoInternal r02 = (CameraInfoInternal) r0;     // Catch: IllegalArgumentException -> L15
        if (r02 == null) goto L21;
        LiveData<CameraState> r23 = r02.getCameraState();     // Catch: IllegalArgumentException -> L15
        if (r23 == null) goto L22;
        r23.removeObserver(r3);     // Catch: IllegalArgumentException -> L15
        return;
    L22:
        return;
    L21:
        return;
    L8:
        r0 = null;
    }

    private final void conditionallySetupCameraStateObserver(String r2) {
        CameraRepository r0 = this.cameraRepository;
        if (r0 != null) goto L9;
        return;
    L9:
        setupCameraStateObserver(r0.getCamera(r2).getCameraInfoInternal());     // Catch: IllegalArgumentException -> L7
        return;
    L7:
        Logger.w("CameraPresencePrvdr", "CameraInternal not found for " + r2 + ". Cannot setup state observer.");
    }

    private final void notifyPublicCamerasAdded(Set<CameraIdentifier> r4) {
        Iterator<T> r3 = this.publicApiListeners.iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        ListenerWrapper r0 = (ListenerWrapper) r3.next();
        r0.getExecutor().execute(new CameraPresenceProvider$$ExternalSyntheticLambda2(r0, r4));
        goto L4
    }

    private static final void notifyPublicCamerasAdded$lambda$0$0(ListenerWrapper r0, Set r1) {
        r0.getListener().onCamerasAdded(r1);
    }

    private final void notifyPublicCamerasRemoved(Set<CameraIdentifier> r4) {
        Iterator<T> r3 = this.publicApiListeners.iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        ListenerWrapper r0 = (ListenerWrapper) r3.next();
        r0.getExecutor().execute(new CameraPresenceProvider$$ExternalSyntheticLambda0(r0, r4));
        goto L4
    }

    private static final void notifyPublicCamerasRemoved$lambda$0$0(ListenerWrapper r0, Set r1) {
        r0.getListener().onCamerasRemoved(r1);
    }

    private final void notifyPublicListeners(Set<CameraIdentifier> r5, Set<CameraIdentifier> r6) {
        if (r5.isEmpty() == true) goto L6;
        Logger.i("CameraPresencePrvdr", "Notifying " + r5.size() + " cameras added.");
        notifyPublicCamerasAdded(r5);
    L6:
        if (r6.isEmpty() == true) goto L9;
        Logger.i("CameraPresencePrvdr", "Notifying " + r6.size() + " cameras removed.");
        notifyPublicCamerasRemoved(r6);
        return;
    }

    private final void processFilteredCameraIdUpdate(List<CameraIdentifier> r10) {
        List r0 = CollectionsKt.toList(this.currentFilteredIds);
        if (Intrinsics.areEqual(r10, r0) == true) goto L59;
        Object r1 = this.retryLock;
        monitor-enter(r1);
    L10:
        th = move-exception;
        throw th;
    L8:
        if (this.retryScanFuture == null) goto L12;
        Logger.d("CameraPresencePrvdr", "Camera list updated. Cancelling any pending retries.");     // Catch: Throwable -> L10
        this.retryScanFuture.cancel(false);     // Catch: Throwable -> L10
        this.retryScanFuture = null;     // Catch: Throwable -> L10
    L12:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r1);
        List r02 = r0;
        Set r12 = CollectionsKt.toSet(r02);
        List<CameraIdentifier> r22 = r10;
        Set r3 = CollectionsKt.toSet(r22);
        Set<CameraIdentifier> r4 = SetsKt.minus(r3, r12);
        Set<CameraIdentifier> r13 = SetsKt.minus(r12, r3);
        ArrayList r32 = new ArrayList();
        ArrayList r5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r22, 10));
        Iterator<T> r23 = r22.iterator();
    L16:
        if (r23.hasNext() == false) goto L66;
        r5.add(((CameraIdentifier) r23.next()).getInternalId());
        goto L16
    L66:
        Iterator<T> r24 = r13.iterator();     // Catch: Exception -> L22
    L20:
        if (r24.hasNext() == false) goto L24;
        removeCameraStateObserver(((CameraIdentifier) r24.next()).getInternalId());     // Catch: Exception -> L22
        goto L20
    L24:
        CameraRepository r25 = this.cameraRepository;     // Catch: Exception -> L22
        if (r25 == null) goto L28;
        Logger.d("CameraPresencePrvdr", "Updating CameraRepository...");     // Catch: Exception -> L22
        r25.onCamerasUpdated(r5);     // Catch: Exception -> L22
        r32.add(r25);     // Catch: Exception -> L22
        Logger.d("CameraPresencePrvdr", "CameraRepository updated successfully.");     // Catch: Exception -> L22
    L28:
        if (this.dependentInternalListeners.isEmpty() == true) goto L33;
        Logger.d("CameraPresencePrvdr", "Updating " + this.dependentInternalListeners.size() + " dependent listeners...");     // Catch: Exception -> L22
        Iterator<T> r26 = this.dependentInternalListeners.iterator();     // Catch: Exception -> L22
    L31:
        if (r26.hasNext() == false) goto L33;
        InternalCameraPresenceListener r7 = (InternalCameraPresenceListener) r26.next();     // Catch: Exception -> L22
        r7.onCamerasUpdated(r5);     // Catch: Exception -> L22
        r32.add(r7);     // Catch: Exception -> L22
    L33:
        this.currentFilteredIds = r10;     // Catch: Exception -> L22
        Iterator<T> r102 = r4.iterator();     // Catch: Exception -> L22
    L35:
        if (r102.hasNext() == false) goto L37;
        conditionallySetupCameraStateObserver(((CameraIdentifier) r102.next()).getInternalId());     // Catch: Exception -> L22
        goto L35
    L37:
        notifyPublicListeners(r4, r13);     // Catch: Exception -> L22
        return;
    L22:
        e = move-exception;
        Logger.e("CameraPresencePrvdr", "A core module failed to update. Rolling back changes.", e);
        ArrayList r103 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r02, 10));
        Iterator r03 = r02.iterator();
    L41:
        if (r03.hasNext() == false) goto L43;
        r103.add(((CameraIdentifier) r03.next()).getInternalId());
        goto L41
    L43:
        Iterator r04 = CollectionsKt.asReversedMutable(r32).iterator();
    L45:
        if (r04.hasNext() == false) goto L51;
        InternalCameraPresenceListener r27 = (InternalCameraPresenceListener) r04.next();
        r27.onCamerasUpdated(r103);     // Catch: Exception -> L49
    L49:
        e = move-exception;
        Logger.e("CameraPresencePrvdr", "Failed to rollback listener: " + r27, e);
        goto L45
    L51:
        Iterator<T> r104 = r13.iterator();
    L53:
        if (r104.hasNext() == false) goto L55;
        conditionallySetupCameraStateObserver(((CameraIdentifier) r104.next()).getInternalId());
        goto L53
    L55:
        Iterator<T> r105 = r4.iterator();
    L57:
        if (r105.hasNext() == false) goto L78;
        removeCameraStateObserver(((CameraIdentifier) r105.next()).getInternalId());
        goto L57
    L78:
        return;
    }

    private final void removeCameraStateObserver(String r6) {
        Object r1 = this.observerLock;
        monitor-enter(r1);
        Observer<CameraState> r2 = this.cameraStateObservers.remove(r6);     // Catch: Throwable -> L9
        CameraRepository r5 = this.cameraRepository;     // Catch: Throwable -> L9
        if (r2 == null) goto L11;
        if (r5 == null) goto L11;
        CameraXExecutors.mainThreadExecutor().execute(new CameraPresenceProvider$$ExternalSyntheticLambda1(r5.getCamera(r6), r2));     // Catch: Throwable -> L9 IllegalArgumentException -> L16
        Logger.d("CameraPresencePrvdr", "Removed state observer for: " + r6);     // Catch: Throwable -> L9 IllegalArgumentException -> L16
    L11:
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r1);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private static final void removeCameraStateObserver$lambda$0$0(CameraInternal r0, Observer r1) {
        r0.getCameraInfoInternal().getCameraState().removeObserver(r1);
    }

    private final void scheduleRetryAttempt(int r5, List<CameraIdentifier> r6) {
        if (r5 > 0) goto L4;
    L12:
        if (r5 > 0) goto L15;
        Logger.w("CameraPresencePrvdr", "Exhausted all retries for camera list refresh.");
        return;
    L15:
        return;
    L4:
        if (this.isMonitoring.get() == false) goto L12;
        if (r5 != 3) goto L9;
        long r0 = 0;
    L10:
        this.retryScanFuture = this.scheduledExecutor.schedule(new CameraPresenceProvider$$ExternalSyntheticLambda9(this, r6, r5), r0, TimeUnit.MILLISECONDS);
        return;
    L9:
        r0 = 400;
        goto L10
    }

    private static final void scheduleRetryAttempt$lambda$0$0(CameraPresenceProvider r2, List r3, int r4) {
        if (r2.isMonitoring.get() == true) goto L5;
        return;
    L5:
        if (Intrinsics.areEqual(r2.currentFilteredIds, r3) == false) goto L13;
        Logger.d("CameraPresencePrvdr", "Triggering refresh. Attempts left: " + r4);
        Observable<List<CameraIdentifier>> r0 = r2.sourcePresenceObservable;
        if (r0 == null) goto L10;
        r0.fetchData();
    L10:
        r2.scheduleRetryAttempt(r4 - 1, r3);
        return;
    }

    private final void setupCameraStateObserver(CameraInfoInternal r7) {
        String r1 = r7.getCameraId();
        if (this.isMonitoring.get() == true) goto L5;
        return;
    L5:
        Object r2 = this.observerLock;
        monitor-enter(r2);
    L14:
        th = move-exception;
        throw th;
    L8:
        if (this.cameraStateObservers.containsKey(r1) == false) goto L11;
        monitor-exit(r2);
        return;
    L11:
        CameraPresenceProvider$$ExternalSyntheticLambda4 r3 = new CameraPresenceProvider$$ExternalSyntheticLambda4(this, r1);     // Catch: Throwable -> L14
        CameraXExecutors.mainThreadExecutor().execute(new CameraPresenceProvider$$ExternalSyntheticLambda5(r7, r3));     // Catch: Throwable -> L14
        this.cameraStateObservers.put(r1, r3);     // Catch: Throwable -> L14
        Logger.d("CameraPresencePrvdr", "Registered state observer for camera: " + r1);     // Catch: Throwable -> L14
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(r2);
    }

    private static final void setupCameraStateObserver$lambda$0$0(CameraPresenceProvider r3, String r4, CameraState r5) {
        if (r3.isMonitoring.get() == true) goto L7;
        Logger.d("CameraPresencePrvdr", "Ignore camera state change handling since already stop monitoring");
        return;
    L7:
        if (r5.getError() == null) goto L14;
        StringBuilder r0 = new StringBuilder("Camera ");
        r0.append(r4);
        r0.append(" state changed to ");
        r0.append(r5.getType());
        r0.append(" with error: ");
        CameraState.StateError r42 = r5.getError();
        if (r42 == null) goto L11;
        Integer r43 = Integer.valueOf(r42.getCode());
    L12:
        r0.append(r43);
        r0.append(". Triggering refresh.");
        Logger.w("CameraPresencePrvdr", r0.toString());
        r3.backgroundExecutor.execute(new CameraPresenceProvider$$ExternalSyntheticLambda7(r3));
        return;
    L11:
        r43 = null;
        goto L12
    }

    private static final void setupCameraStateObserver$lambda$0$0$0(CameraPresenceProvider r0) {
        r0.triggerRefreshWithRetries();
    }

    private static final void setupCameraStateObserver$lambda$0$1(CameraInfoInternal r0, Observer r1) {
        r0.getCameraState().observeForever(r1);
    }

    private final void triggerRefreshWithRetries() {
        Object r0 = this.retryLock;
        monitor-enter(r0);
        ScheduledFuture<?> r1 = this.retryScanFuture;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.cancel(false);     // Catch: Throwable -> L7
    L9:
        Logger.d("CameraPresencePrvdr", "Starting new refresh-with-retries sequence.");     // Catch: Throwable -> L7
        scheduleRetryAttempt(3, this.currentFilteredIds);     // Catch: Throwable -> L7
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void addCameraPresenceListener(CameraPresenceListener r3, Executor r4) {
        ListenerWrapper r0 = new ListenerWrapper(r3, r4);
        this.publicApiListeners.add(r0);
        r4.execute(new CameraPresenceProvider$$ExternalSyntheticLambda3(this, r3));
    }

    public final void addDependentInternalListener(InternalCameraPresenceListener r1) {
        this.dependentInternalListeners.add(r1);
    }

    public final void removeCameraPresenceListener(CameraPresenceListener r2) {
        CollectionsKt.removeAll(this.publicApiListeners, new CameraPresenceProvider$$ExternalSyntheticLambda6(r2));
    }

    public final void shutdown() {
        if (this.isMonitoring.getAndSet(false) == true) goto L6;
        Logger.d("CameraPresencePrvdr", "Shutdown called when not monitoring. Ignoring.");
        return;
    L6:
        Logger.i("CameraPresencePrvdr", "Shutting down CameraPresenceProvider monitoring.");
        Object r0 = this.retryLock;
        monitor-enter(r0);
        ScheduledFuture<?> r2 = this.retryScanFuture;     // Catch: Throwable -> L11
        if (r2 == null) goto L13;
        r2.cancel(false);     // Catch: Throwable -> L11
    L13:
        this.retryScanFuture = null;     // Catch: Throwable -> L11
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r0);
        Observable<List<CameraIdentifier>> r02 = this.sourcePresenceObservable;
        if (r02 == null) goto L18;
        r02.removeObserver(this.sourceObserver);
    L18:
        clearAllCameraStateObservers();
        this.cameraValidator = null;
        this.dependentInternalListeners.clear();
        this.publicApiListeners.clear();
        this.currentFilteredIds = CollectionsKt.emptyList();
        this.cameraFactory = null;
        this.cameraRepository = null;
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public final void startup(CameraValidator r5, CameraFactory r6, CameraRepository r7) {
        if (this.isMonitoring.compareAndSet(false, true) == false) goto L15;
        Logger.i("CameraPresencePrvdr", "Starting CameraPresenceProvider monitoring.");
        this.cameraValidator = r5;
        Set<String> r52 = r6.getAvailableCameraIds();
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r52, 10));
        Iterator<T> r53 = r52.iterator();
    L7:
        if (r53.hasNext() == false) goto L9;
        r0.add(CameraIdentifier.Factory.create$default((String) r53.next(), null, null, 6, null));
        goto L7
    L9:
        this.currentFilteredIds = r0;
        this.cameraFactory = r6;
        this.cameraRepository = r7;
        this.sourcePresenceObservable = r6.getCameraPresenceSource();
        this.backgroundExecutor.execute(new CameraPresenceProvider$$ExternalSyntheticLambda10(this));
        Observable<List<CameraIdentifier>> r54 = this.sourcePresenceObservable;
        if (r54 == null) goto L14;
        r54.addObserver(CameraXExecutors.newSequentialExecutor(this.backgroundExecutor), this.sourceObserver);
        return;
    L14:
        return;
    }
}
