package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraphId;", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;)V", "toString", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraGraphId {
    public static final Companion Companion = null;
    private static final AtomicInt cameraGraphIds = null;
    private final String name;

    @kotlin.Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraphId$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "cameraGraphIds", "Lkotlinx/atomicfu/AtomicInt;", "nextId", "Landroidx/camera/camera2/pipe/CameraGraphId;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final CameraGraphId nextId() {
            return new CameraGraphId("CameraGraph-" + CameraGraphId.access$getCameraGraphIds$cp().incrementAndGet(), null);
        }
    }

    static {
        Companion = new Companion(null);
        cameraGraphIds = AtomicFU.atomic(0);
    }

    private CameraGraphId(String r1) {
        this.name = r1;
    }

    public /* synthetic */ CameraGraphId(String r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    public static final /* synthetic */ AtomicInt access$getCameraGraphIds$cp() {
        return cameraGraphIds;
    }

    public String toString() {
        return this.name;
    }
}
