package androidx.camera.camera2.pipe;

import android.view.Surface;
import com.google.android.gms.cast.MediaError;
import java.util.Map;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001:\u0001\u0013J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\r\u001a\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tH&¢\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\u00068&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CameraController;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "start", "()V", "close", _UrlKt.FRAGMENT_ENCODE_SET, "awaitClosed", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "surfaceMap", "updateSurfaceMap", "(Ljava/util/Map;)V", "isForeground", "()Z", "setForeground", "(Z)V", "ControllerState", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraController {

    @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\b&\u0018\u00002\u00020\u0001:\u0007\u0004\u0005\u0006\u0007\b\t\nB\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/CameraController$ControllerState;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "STARTED", "STOPPING", "STOPPED", "DISCONNECTED", MediaError.ERROR_TYPE_ERROR, "CLOSING", "CLOSED", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class ControllerState {

        @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/CameraController$ControllerState$CLOSED;", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class CLOSED extends ControllerState {
            public static final CLOSED INSTANCE = null;

            static {
                INSTANCE = new CLOSED();
            }

            private CLOSED() {
            }
        }

        @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/CameraController$ControllerState$CLOSING;", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class CLOSING extends ControllerState {
            public static final CLOSING INSTANCE = null;

            static {
                INSTANCE = new CLOSING();
            }

            private CLOSING() {
            }
        }

        @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/CameraController$ControllerState$DISCONNECTED;", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class DISCONNECTED extends ControllerState {
            public static final DISCONNECTED INSTANCE = null;

            static {
                INSTANCE = new DISCONNECTED();
            }

            private DISCONNECTED() {
            }
        }

        @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/CameraController$ControllerState$ERROR;", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class ERROR extends ControllerState {
            public static final ERROR INSTANCE = null;

            static {
                INSTANCE = new ERROR();
            }

            private ERROR() {
            }
        }

        @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/CameraController$ControllerState$STARTED;", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class STARTED extends ControllerState {
            public static final STARTED INSTANCE = null;

            static {
                INSTANCE = new STARTED();
            }

            private STARTED() {
            }
        }

        @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/CameraController$ControllerState$STOPPED;", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class STOPPED extends ControllerState {
            public static final STOPPED INSTANCE = null;

            static {
                INSTANCE = new STOPPED();
            }

            private STOPPED() {
            }
        }

        @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/CameraController$ControllerState$STOPPING;", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class STOPPING extends ControllerState {
            public static final STOPPING INSTANCE = null;

            static {
                INSTANCE = new STOPPING();
            }

            private STOPPING() {
            }
        }

        public ControllerState() {
        }
    }

    Object awaitClosed(Continuation<? super Boolean> r1);

    void close();

    void setForeground(boolean r1);

    void start();

    void updateSurfaceMap(Map<StreamId, ? extends Surface> r1);
}
