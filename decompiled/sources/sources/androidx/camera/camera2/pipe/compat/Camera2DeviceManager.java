package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.graph.GraphListener;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001JK\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\nH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00112\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00112\b\b\u0002\u0010\u0015\u001a\u00020\bH&¢\u0006\u0004\b\u0016\u0010\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2DeviceManager;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "sharedCameraIds", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "graphListener", _UrlKt.FRAGMENT_ENCODE_SET, "isPrewarm", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "isForegroundObserver", "Landroidx/camera/camera2/pipe/compat/VirtualCamera;", "open-zDSwpeU", "(Ljava/lang/String;Ljava/util/List;Landroidx/camera/camera2/pipe/graph/GraphListener;ZLkotlin/jvm/functions/Function1;)Landroidx/camera/camera2/pipe/compat/VirtualCamera;", "open", "Lkotlinx/coroutines/Deferred;", "close-EfqyGwQ", "(Ljava/lang/String;)Lkotlinx/coroutines/Deferred;", "close", "forceCancelOpen", "closeAll", "(Z)Lkotlinx/coroutines/Deferred;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface Camera2DeviceManager {
    /* renamed from: close-EfqyGwQ, reason: not valid java name */
    Deferred<Unit> mo445closeEfqyGwQ(String r1);

    Deferred<Unit> closeAll(boolean r1);

    /* renamed from: open-zDSwpeU, reason: not valid java name */
    VirtualCamera mo446openzDSwpeU(String r1, List<CameraId> r2, GraphListener r3, boolean r4, Function1<? super Unit, Boolean> r5);
}
