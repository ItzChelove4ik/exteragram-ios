package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraGraphId;
import androidx.camera.camera2.pipe.ConcurrentCameraGraphs;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencers;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "lock", "sequencers", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/ConcurrentCameraGraphs;", "Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencer;", "pending", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraGraphId;", "getSequencer", "cameraGraphId", "concurrentCameraGraphs", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConcurrentSessionSequencers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConcurrentSessionSequencers.kt\nandroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencers\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,115:1\n1761#2,3:116\n*S KotlinDebug\n*F\n+ 1 ConcurrentSessionSequencers.kt\nandroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencers\n*L\n56#1:116,3\n*E\n"})
/* loaded from: classes3.dex */
public final class ConcurrentSessionSequencers {
    private final Object lock;
    private final Set<CameraGraphId> pending;
    private final Map<ConcurrentCameraGraphs, ConcurrentSessionSequencer> sequencers;

    public ConcurrentSessionSequencers() {
        this.lock = new Object();
        this.sequencers = new LinkedHashMap();
        this.pending = new LinkedHashSet();
    }

    public final ConcurrentSessionSequencer getSequencer(CameraGraphId r4, ConcurrentCameraGraphs r5) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.sequencers.containsKey(r5) == false) goto L30;
        this.pending.remove(r4);     // Catch: Throwable -> L11
        Set<CameraGraphId> r42 = r5.getCameraGraphIds();     // Catch: Throwable -> L11
        if (r42 != null) goto L9;
    L13:
        Iterator<T> r43 = r42.iterator();     // Catch: Throwable -> L11
    L15:
        if (r43.hasNext() == false) goto L23;
        CameraGraphId r1 = (CameraGraphId) r43.next();     // Catch: Throwable -> L11
        if (this.pending.contains(r1) == false) goto L15;
        ConcurrentSessionSequencer r3 = this.sequencers.get(r5);     // Catch: Throwable -> L11
        if (r3 == null) goto L22;
        ConcurrentSessionSequencer r32 = r3;     // Catch: Throwable -> L11
    L26:
        monitor-exit(r0);
        return r32;
    L22:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L11
    L23:
        ConcurrentSessionSequencer r33 = this.sequencers.remove(r5);     // Catch: Throwable -> L11
        if (r33 == null) goto L29;
        r32 = r33;     // Catch: Throwable -> L11
        goto L26
    L29:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L11
    L9:
        if (r42.isEmpty() == false) goto L13;
    L30:
        ConcurrentSessionSequencer r12 = new ConcurrentSessionSequencer();     // Catch: Throwable -> L11
        this.sequencers.put(r5, r12);     // Catch: Throwable -> L11
        this.pending.addAll(SetsKt.minus(r5.getCameraGraphIds(), r4));     // Catch: Throwable -> L11
        monitor-exit(r0);
        return r12;
    }
}
