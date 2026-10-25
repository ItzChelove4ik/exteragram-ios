package androidx.camera.camera2.pipe;

import android.view.Surface;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraGraph.Session;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\bg\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00060\u0003j\u0002`\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH&¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00028\u0000H¦@¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0014\u001a\u00020\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0016\u001a\u00020\u00158&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001aÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraphBase;", "Landroidx/camera/camera2/pipe/CameraGraph$Session;", "TSession", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", _UrlKt.FRAGMENT_ENCODE_SET, "start", "()V", "Landroidx/camera/camera2/pipe/StreamId;", "stream", "Landroid/view/Surface;", "surface", "setSurface-NYG5g8E", "(ILandroid/view/Surface;)V", "setSurface", "acquireSession", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/StreamGraph;", "getStreams", "()Landroidx/camera/camera2/pipe/StreamGraph;", "streams", _UrlKt.FRAGMENT_ENCODE_SET, "isForeground", "()Z", "setForeground", "(Z)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraGraphBase<TSession extends CameraGraph.Session> extends AutoCloseable {
    Object acquireSession(Continuation<? super TSession> r1);

    StreamGraph getStreams();

    void setForeground(boolean r1);

    /* renamed from: setSurface-NYG5g8E, reason: not valid java name */
    void mo226setSurfaceNYG5g8E(int r1, Surface r2);

    void start();
}
