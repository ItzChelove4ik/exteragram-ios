package androidx.camera.camera2.adapter;

import android.util.Log;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.internal.CameraCompatibilityFilter;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.InitializationException;
import androidx.camera.core.Logger;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraRepository;
import androidx.camera.core.impl.CameraUpdateException;
import androidx.camera.core.impl.InternalCameraPresenceListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\f\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0013\u001a\u00020\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\u0010H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010H\u0016¢\u0006\u0004\b\u0019\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001d\u0010\u001cJ\u001d\u0010\u001f\u001a\u00020\t2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010H\u0016¢\u0006\u0004\b\u001f\u0010\u0014J\u000f\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020\t2\u0006\u0010#\u001a\u00020 H\u0016¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\tH\u0016¢\u0006\u0004\b&\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010'R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R*\u0010,\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b,\u0010-\u0012\u0004\b1\u0010\u000b\u001a\u0004\b.\u0010/\"\u0004\b0\u0010\u000fR4\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020302028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b4\u00105\u0012\u0004\b:\u0010\u000b\u001a\u0004\b6\u00107\"\u0004\b8\u00109R:\u0010<\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100;8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b<\u0010=\u0012\u0004\bB\u0010\u000b\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR.\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00180\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\bC\u0010D\u0012\u0004\bG\u0010\u000b\u001a\u0004\bE\u0010\u0017\"\u0004\bF\u0010\u0014R(\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00110H8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bI\u0010D\u001a\u0004\bJ\u0010\u0017\"\u0004\bK\u0010\u0014R(\u0010L\u001a\u00020 8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\bL\u0010M\u0012\u0004\bP\u0010\u000b\u001a\u0004\bN\u0010\"\"\u0004\bO\u0010%R(\u0010R\u001a\u00020Q8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\bR\u0010S\u0012\u0004\bX\u0010\u000b\u001a\u0004\bT\u0010U\"\u0004\bV\u0010W¨\u0006Y"}, d2 = {"Landroidx/camera/camera2/adapter/CameraCoordinatorAdapter;", "Landroidx/camera/core/concurrent/CameraCoordinator;", "Landroidx/camera/core/impl/InternalCameraPresenceListener;", "Landroidx/camera/camera2/pipe/CameraPipe;", "cameraPipe", "Landroidx/camera/camera2/pipe/CameraDevices;", "cameraDevices", "<init>", "(Landroidx/camera/camera2/pipe/CameraPipe;Landroidx/camera/camera2/pipe/CameraDevices;)V", _UrlKt.FRAGMENT_ENCODE_SET, "tryStartConcurrentGraph", "()V", "Landroidx/camera/core/impl/CameraRepository;", "repository", "init", "(Landroidx/camera/core/impl/CameraRepository;)V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "cameraIds", "onCamerasUpdated", "(Ljava/util/List;)V", "Landroidx/camera/core/CameraSelector;", "getConcurrentCameraSelectors", "()Ljava/util/List;", "Landroidx/camera/core/CameraInfo;", "getActiveConcurrentCameraInfos", "cameraInfo", "addPendingCameraInfo", "(Landroidx/camera/core/CameraInfo;)V", "removePendingCameraInfo", "cameraInfos", "setActiveConcurrentCameraInfos", _UrlKt.FRAGMENT_ENCODE_SET, "getCameraOperatingMode", "()I", "cameraOperatingMode", "setCameraOperatingMode", "(I)V", "shutdown", "Landroidx/camera/camera2/pipe/CameraPipe;", "Landroidx/camera/camera2/pipe/CameraDevices;", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", "cameraRepository", "Landroidx/camera/core/impl/CameraRepository;", "getCameraRepository", "()Landroidx/camera/core/impl/CameraRepository;", "setCameraRepository", "getCameraRepository$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "concurrentCameraIdsSet", "Ljava/util/Set;", "getConcurrentCameraIdsSet", "()Ljava/util/Set;", "setConcurrentCameraIdsSet", "(Ljava/util/Set;)V", "getConcurrentCameraIdsSet$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "concurrentCameraIdMap", "Ljava/util/Map;", "getConcurrentCameraIdMap", "()Ljava/util/Map;", "setConcurrentCameraIdMap", "(Ljava/util/Map;)V", "getConcurrentCameraIdMap$annotations", "activeConcurrentCameraInfosList", "Ljava/util/List;", "getActiveConcurrentCameraInfosList", "setActiveConcurrentCameraInfosList", "getActiveConcurrentCameraInfosList$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "pendingCameraIds", "getPendingCameraIds", "setPendingCameraIds", "concurrentMode", "I", "getConcurrentMode", "setConcurrentMode", "getConcurrentMode$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "concurrentModeOn", "Z", "getConcurrentModeOn", "()Z", "setConcurrentModeOn", "(Z)V", "getConcurrentModeOn$annotations", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraCoordinatorAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraCoordinatorAdapter.kt\nandroidx/camera/camera2/adapter/CameraCoordinatorAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,287:1\n1#2:288\n1#2:331\n1563#3:289\n1634#3,3:290\n1563#3:293\n1634#3,3:294\n1563#3:305\n1634#3,2:306\n1563#3:308\n1634#3,3:309\n1636#3:312\n1563#3:313\n1634#3,3:314\n1617#3,9:321\n1869#3:330\n1870#3:332\n1626#3:333\n1563#3:334\n1634#3,3:335\n119#4,4:297\n119#4,4:301\n136#4,4:317\n*S KotlinDebug\n*F\n+ 1 CameraCoordinatorAdapter.kt\nandroidx/camera/camera2/adapter/CameraCoordinatorAdapter\n*L\n199#1:331\n72#1:289\n72#1:290,3\n83#1:293\n83#1:294,3\n134#1:305\n134#1:306,2\n136#1:308\n136#1:309,3\n134#1:312\n181#1:313\n181#1:314,3\n199#1:321,9\n199#1:330\n199#1:332\n199#1:333\n209#1:334\n209#1:335,3\n85#1:297,4\n111#1:301,4\n194#1:317,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraCoordinatorAdapter implements CameraCoordinator, InternalCameraPresenceListener {
    private List<? extends CameraInfo> activeConcurrentCameraInfosList;
    private final CameraDevices cameraDevices;
    private CameraPipe cameraPipe;
    private CameraRepository cameraRepository;
    private Map<String, ? extends List<String>> concurrentCameraIdMap;
    private Set<? extends Set<CameraId>> concurrentCameraIdsSet;
    private int concurrentMode;
    private boolean concurrentModeOn;
    private final Object lock;
    private List<String> pendingCameraIds;

    public CameraCoordinatorAdapter(CameraPipe r1, CameraDevices r2) {
        this.cameraPipe = r1;
        this.cameraDevices = r2;
        this.lock = new Object();
        this.concurrentCameraIdsSet = SetsKt.emptySet();
        this.concurrentCameraIdMap = MapsKt.emptyMap();
        this.activeConcurrentCameraInfosList = CollectionsKt.emptyList();
        this.pendingCameraIds = new ArrayList();
    }

    private final void tryStartConcurrentGraph() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L15:
        th = move-exception;
        throw th;
    L5:
        if (this.activeConcurrentCameraInfosList.isEmpty() == false) goto L7;
    L74:
        monitor-exit(r0);
        return;
    L7:
        if (this.pendingCameraIds.isEmpty() == true) goto L74;
        List<? extends CameraInfo> r1 = this.activeConcurrentCameraInfosList;     // Catch: Throwable -> L15
        ArrayList r2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));     // Catch: Throwable -> L15
        Iterator<T> r12 = r1.iterator();     // Catch: Throwable -> L15
    L10:
        CameraId r5 = null;
        if (r12.hasNext() == false) goto L22;
        String r4 = CameraInfoAdapter.Companion.m8getCameraIdzjxgSG8((CameraInfo) r12.next());     // Catch: Throwable -> L15
        if (r4 == null) goto L17;
        r5 = CameraId.m227boximpl(r4);     // Catch: Throwable -> L15
    L17:
        if (r5 == null) goto L20;
        r2.add(r5.m233unboximpl());     // Catch: Throwable -> L15
        goto L10
    L20:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L15
    L22:
        if (Intrinsics.areEqual(CollectionsKt.toSet(r2), CollectionsKt.toSet(this.pendingCameraIds)) == true) goto L25;
        monitor-exit(r0);
        return;
    L25:
        this.pendingCameraIds.clear();     // Catch: Throwable -> L15
        List<? extends CameraInfo> r13 = this.activeConcurrentCameraInfosList;     // Catch: Throwable -> L15
        monitor-exit(r0);
        Object r02 = this.lock;
        monitor-enter(r02);
        CameraRepository r22 = this.cameraRepository;     // Catch: Throwable -> L35
        if (r22 != null) goto L39;
        Camera2Logger r8 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L35
        if (Logger.isErrorEnabled("CXCP") == false) goto L37;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Coordinator has not been initialized with a CameraRepository.");     // Catch: Throwable -> L35
    L37:
        monitor-exit(r02);
        return;
    L39:
        ArrayList r42 = new ArrayList();     // Catch: Throwable -> L35
        Iterator<T> r14 = r13.iterator();     // Catch: Throwable -> L35
    L41:
        if (r14.hasNext() == false) goto L51;
        CameraInternal r6 = r22.getCamera(CameraInfoAdapter.Companion.m8getCameraIdzjxgSG8((CameraInfo) r14.next()));     // Catch: Throwable -> L35 IllegalArgumentException -> L78
        if ((r6 instanceof CameraInternalAdapter) == false) goto L47;
        CameraInternalAdapter r62 = (CameraInternalAdapter) r6;     // Catch: Throwable -> L35 IllegalArgumentException -> L78
    L48:
        if (r62 == null) goto L41;
        r42.add(r62);     // Catch: Throwable -> L35
    L47:
        r62 = null;
        goto L48
    L51:
        monitor-exit(r02);
        ArrayList r03 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r42, 10));
        int r15 = r42.size();
        int r23 = 0;
    L53:
        if (r23 >= r15) goto L59;
        Object r3 = r42.get(r23);
        r23 = r23 + 1;
        CameraGraph.Config r32 = ((CameraInternalAdapter) r3).getDeferredCameraGraphConfig$camera_camera2();
        if (r32 == null) goto L57;
        r03.add(r32);
        goto L53
    L57:
        Segment$$ExternalSyntheticBUOutline1.m("Every CameraInternal instance is expected to have a deferred CameraGraph config");
        return;
    L59:
        CameraPipe r82 = this.cameraPipe;
        if (r82 == null) goto L70;
        List<CameraGraph> r83 = r82.createCameraGraphs(new CameraGraph.ConcurrentConfig(r03));
        if (r83.size() != r03.size()) goto L68;
        Iterator r84 = CollectionsKt.zip(r42, r83).iterator();
    L65:
        if (r84.hasNext() == false) goto L67;
        Pair r04 = (Pair) r84.next();
        ((CameraInternalAdapter) r04.component1()).resumeDeferredCameraGraphCreation$camera_camera2((CameraGraph) r04.component2());
        goto L65
    L67:
        return;
    L68:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L70:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return;
    L35:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.concurrent.CameraCoordinator
    public void addPendingCameraInfo(CameraInfo r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.concurrentModeOn == false) goto L16;
        List<String> r1 = this.pendingCameraIds;     // Catch: Throwable -> L9
        String r42 = CameraInfoAdapter.Companion.m8getCameraIdzjxgSG8(r4);     // Catch: Throwable -> L9
        if (r42 == null) goto L11;
        CameraId r43 = CameraId.m227boximpl(r42);     // Catch: Throwable -> L9
    L12:
        if (r43 == null) goto L15;
        r1.add(r43.m233unboximpl());     // Catch: Throwable -> L9
        tryStartConcurrentGraph();     // Catch: Throwable -> L9
        goto L16
    L15:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L9
    L11:
        r43 = null;
    L16:
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
    }

    @Override // androidx.camera.core.concurrent.CameraCoordinator
    public List<CameraInfo> getActiveConcurrentCameraInfos() {
        Object r0 = this.lock;
        monitor-enter(r0);
        ArrayList r1 = new ArrayList(this.activeConcurrentCameraInfosList);     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.concurrent.CameraCoordinator
    public int getCameraOperatingMode() {
        Object r0 = this.lock;
        monitor-enter(r0);
        int r1 = this.concurrentMode;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.concurrent.CameraCoordinator
    public List<List<CameraSelector>> getConcurrentCameraSelectors() {
        Object r0 = this.lock;
        monitor-enter(r0);
        Set<? extends Set<CameraId>> r8 = this.concurrentCameraIdsSet;     // Catch: Throwable -> L11
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r8, 10));     // Catch: Throwable -> L11
        Iterator<T> r82 = r8.iterator();     // Catch: Throwable -> L11
    L6:
        if (r82.hasNext() == false) goto L14;
        Set r3 = (Set) r82.next();     // Catch: Throwable -> L11
        ArrayList r4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r3, 10));     // Catch: Throwable -> L11
        Iterator r32 = r3.iterator();     // Catch: Throwable -> L11
    L9:
        if (r32.hasNext() == false) goto L13;
        r4.add(CameraSelector.of(new CameraIdentifier[]{CameraIdentifier.Factory.create$default(((CameraId) r32.next()).m233unboximpl(), null, null, 6, null)}));     // Catch: Throwable -> L11
        goto L9
    L13:
        r1.add(CollectionsKt.toList(r4));     // Catch: Throwable -> L11
        goto L6
    L14:
        List<List<CameraSelector>> r83 = CollectionsKt.toList(r1);     // Catch: Throwable -> L11
        monitor-exit(r0);
        return r83;
    L11:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.concurrent.CameraCoordinator
    public void init(CameraRepository r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.cameraRepository = r3;     // Catch: Throwable -> L15
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L15
        monitor-exit(r0);
        List r33 = CameraDevices.m167awaitCameraIdsSeavPBo$default(this.cameraDevices, null, 1, null);
        if (r33 == null) goto L12;
        List r34 = r33;
        List<String> r02 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r34, 10));
        Iterator r35 = r34.iterator();
    L10:
        if (r35.hasNext() == false) goto L13;
        r02.add(((CameraId) r35.next()).m233unboximpl());
    L13:
        onCamerasUpdated(r02);
        return;
    L12:
        r02 = CollectionsKt.emptyList();
    L15:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.InternalCameraPresenceListener
    public void onCamerasUpdated(List<String> r10) {
        LinkedHashSet r0 = new LinkedHashSet();
        LinkedHashMap r1 = new LinkedHashMap();
        Set r2 = CameraDevices.m169awaitConcurrentCameraIdsSeavPBo$default(this.cameraDevices, null, 1, null);     // Catch: Exception -> L47
        if (r2 != null) goto L6;
        r2 = SetsKt.emptySet();     // Catch: Exception -> L47
    L6:
        Iterator r22 = r2.iterator();     // Catch: Exception -> L47
    L7:
        if (r22.hasNext() == false) goto L39;
        Set r4 = (Set) r22.next();     // Catch: Exception -> L47
        ArrayList r5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r4, 10));     // Catch: Exception -> L47
        Iterator r6 = r4.iterator();     // Catch: Exception -> L47
    L11:
        if (r6.hasNext() == false) goto L13;
        r5.add(((CameraId) r6.next()).m233unboximpl());     // Catch: Exception -> L47
        goto L11
    L13:
        Set r52 = CollectionsKt.toSet(r5);     // Catch: Exception -> L47
        if (r10.containsAll(r52) == false) goto L15;
        List r53 = CollectionsKt.toList(r4);     // Catch: Exception -> L47
        if (r53.size() < 2) goto L7;
        String r62 = ((CameraId) r53.get(0)).m233unboximpl();     // Catch: Exception -> L47
        String r54 = ((CameraId) r53.get(1)).m233unboximpl();     // Catch: Exception -> L47
    L22:
        if (CameraCompatibilityFilter.isBackwardCompatible(r62, this.cameraDevices) == false) goto L7;
        if (CameraCompatibilityFilter.isBackwardCompatible(r54, this.cameraDevices) == false) goto L7;
        r0.add(r4);     // Catch: InitializationException -> L28 Exception -> L47
        if (r1.containsKey(r62) == true) goto L30;
        r1.put(r62, new ArrayList());     // Catch: InitializationException -> L28 Exception -> L47
    L30:
        ((List) r1.get(r62)).add(r54);     // Catch: InitializationException -> L28 Exception -> L47
        if (r1.containsKey(r54) == true) goto L33;
        r1.put(r54, new ArrayList());     // Catch: InitializationException -> L28 Exception -> L47
    L33:
        ((List) r1.get(r54)).add(r62);     // Catch: InitializationException -> L28 Exception -> L47
    L28:
        e = move-exception;
        Camera2Logger r63 = Camera2Logger.INSTANCE;     // Catch: Exception -> L47
        if (Logger.isWarnEnabled("CXCP") == false) goto L7;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Skipping incompatible concurrent pair: " + r4 + " due to " + e.getMessage());     // Catch: Exception -> L47
        goto L7
    L15:
        Camera2Logger r42 = Camera2Logger.INSTANCE;     // Catch: Exception -> L47
        if (Logger.isWarnEnabled("CXCP") == false) goto L7;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to retrieve concurrent camera: " + r52 + " from " + r10);     // Catch: Exception -> L47
        goto L7
    L39:
        Object r102 = this.lock;
        monitor-enter(r102);
        this.concurrentCameraIdsSet = r0;     // Catch: Throwable -> L44
        this.concurrentCameraIdMap = r1;     // Catch: Throwable -> L44
        Unit r9 = Unit.INSTANCE;     // Catch: Throwable -> L44
        monitor-exit(r102);
        return;
    L44:
        th = move-exception;
        throw th;
    L47:
        e = move-exception;
        throw new CameraUpdateException("Failed to retrieve concurrent camera id info for camera-pipe.", e);
    }

    @Override // androidx.camera.core.concurrent.CameraCoordinator
    public void removePendingCameraInfo(CameraInfo r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.concurrentModeOn == false) goto L16;
        List<String> r2 = this.pendingCameraIds;     // Catch: Throwable -> L9
        String r32 = CameraInfoAdapter.Companion.m8getCameraIdzjxgSG8(r3);     // Catch: Throwable -> L9
        if (r32 == null) goto L11;
        CameraId r33 = CameraId.m227boximpl(r32);     // Catch: Throwable -> L9
    L12:
        if (r33 == null) goto L15;
        r2.remove(r33.m233unboximpl());     // Catch: Throwable -> L9
        goto L16
    L15:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L9
    L11:
        r33 = null;
    L16:
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
    }

    @Override // androidx.camera.core.concurrent.CameraCoordinator
    public void setActiveConcurrentCameraInfos(List<? extends CameraInfo> r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.activeConcurrentCameraInfosList = r2;     // Catch: Throwable -> L7
        tryStartConcurrentGraph();     // Catch: Throwable -> L7
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.concurrent.CameraCoordinator
    public void setCameraOperatingMode(int r6) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.concurrentMode = r6;     // Catch: Throwable -> L29
        CameraRepository r1 = this.cameraRepository;     // Catch: Throwable -> L29
        monitor-exit(r0);
        if (r1 != null) goto L9;
        return;
    L9:
        if (r6 != 2) goto L11;
        boolean r4 = true;
    L12:
        this.concurrentModeOn = r4;
        if (r4 == true) goto L15;
        this.activeConcurrentCameraInfosList = CollectionsKt.emptyList();
    L15:
        Iterator<CameraInternal> r5 = r1.getCameras().iterator();
    L17:
        if (r5.hasNext() == false) goto L44;
        CameraInternal r12 = r5.next();
        if ((r12 instanceof CameraInternalAdapter) == false) goto L21;
        CameraInternalAdapter r13 = (CameraInternalAdapter) r12;
    L22:
        if (r13 == null) goto L17;
        if (r6 != 1) goto L24;
        r13.setCameraGraphCreationMode$camera_camera2(true);
        goto L17
    L24:
        if (r6 != 2) goto L17;
        r13.setCameraGraphCreationMode$camera_camera2(false);
        goto L17
    L21:
        r13 = null;
        goto L22
    L44:
        return;
    L11:
        r4 = false;
    L29:
        th = move-exception;
        throw th;
    }

    public void shutdown() {
        this.cameraPipe = null;
        this.concurrentModeOn = false;
        Object r2 = this.lock;
        monitor-enter(r2);
        this.cameraRepository = null;     // Catch: Throwable -> L7
        this.concurrentCameraIdsSet = SetsKt.emptySet();     // Catch: Throwable -> L7
        this.concurrentCameraIdMap = MapsKt.emptyMap();     // Catch: Throwable -> L7
        this.activeConcurrentCameraInfosList = CollectionsKt.emptyList();     // Catch: Throwable -> L7
        this.concurrentMode = 0;     // Catch: Throwable -> L7
        this.pendingCameraIds.clear();     // Catch: Throwable -> L7
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r2);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
