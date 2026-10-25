package androidx.camera.lifecycle;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0007\u0010\bR \u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/camera/lifecycle/LifecycleCameraRepositories;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "deviceId", "Landroidx/camera/lifecycle/LifecycleCameraRepository;", "getInstance$camera_lifecycle", "(I)Landroidx/camera/lifecycle/LifecycleCameraRepository;", "getInstance", _UrlKt.FRAGMENT_ENCODE_SET, "repositoryMap", "Ljava/util/Map;", "camera-lifecycle"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLifecycleCameraRepositories.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LifecycleCameraRepositories.kt\nandroidx/camera/lifecycle/LifecycleCameraRepositories\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,57:1\n384#2,7:58\n1#3:65\n*S KotlinDebug\n*F\n+ 1 LifecycleCameraRepositories.kt\nandroidx/camera/lifecycle/LifecycleCameraRepositories\n*L\n43#1:58,7\n*E\n"})
/* loaded from: classes3.dex */
public final class LifecycleCameraRepositories {
    public static final LifecycleCameraRepositories INSTANCE = null;
    private static final Map<Integer, LifecycleCameraRepository> repositoryMap = null;

    static {
        INSTANCE = new LifecycleCameraRepositories();
        repositoryMap = new LinkedHashMap();
    }

    private LifecycleCameraRepositories() {
    }

    @JvmStatic
    public static final LifecycleCameraRepository getInstance$camera_lifecycle(int r3) {
        Map<Integer, LifecycleCameraRepository> r0 = repositoryMap;
        monitor-enter(r0);
        Integer r1 = Integer.valueOf(r3);     // Catch: Throwable -> L7
        LifecycleCameraRepository r2 = r0.get(r1);     // Catch: Throwable -> L7
        if (r2 != null) goto L9;
        r2 = new LifecycleCameraRepository(r3);     // Catch: Throwable -> L7
        r0.put(r1, r2);     // Catch: Throwable -> L7
    L9:
        LifecycleCameraRepository r22 = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }
}
