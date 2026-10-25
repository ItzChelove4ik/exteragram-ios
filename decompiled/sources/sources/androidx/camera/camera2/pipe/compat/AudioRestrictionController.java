package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraGraph;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001:\u0001\fJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\u000b\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraGraph;", "cameraGraph", _UrlKt.FRAGMENT_ENCODE_SET, "removeCameraGraph", "(Landroidx/camera/camera2/pipe/CameraGraph;)V", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController$Listener;", "listener", "addListener", "(Landroidx/camera/camera2/pipe/compat/AudioRestrictionController$Listener;)V", "removeListener", "Listener", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface AudioRestrictionController {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AudioRestrictionController$Listener;", _UrlKt.FRAGMENT_ENCODE_SET, "onCameraAudioRestrictionUpdated", _UrlKt.FRAGMENT_ENCODE_SET, "mode", "Landroidx/camera/camera2/pipe/AudioRestrictionMode;", "onCameraAudioRestrictionUpdated-LwUUkyU", "(I)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Listener {
        /* renamed from: onCameraAudioRestrictionUpdated-LwUUkyU */
        void mo413onCameraAudioRestrictionUpdatedLwUUkyU(int r1);
    }

    void addListener(Listener r1);

    void removeCameraGraph(CameraGraph r1);

    void removeListener(Listener r1);
}
