package androidx.camera.camera2.pipe.graph;

import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.graph.GraphLoop;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH&¢\u0006\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/graph/Result3AStateListener;", "Landroidx/camera/camera2/pipe/graph/GraphLoop$Listener;", "onRequestSequenceCreated", _UrlKt.FRAGMENT_ENCODE_SET, "requestNumber", "Landroidx/camera/camera2/pipe/RequestNumber;", "onRequestSequenceCreated-DThHKJ0", "(J)V", PluginsConstants.UPDATE, _UrlKt.FRAGMENT_ENCODE_SET, "frameMetadata", "Landroidx/camera/camera2/pipe/FrameMetadata;", "update-voP-kFw", "(JLandroidx/camera/camera2/pipe/FrameMetadata;)Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface Result3AStateListener extends GraphLoop.Listener {
    /* renamed from: onRequestSequenceCreated-DThHKJ0, reason: not valid java name */
    void mo532onRequestSequenceCreatedDThHKJ0(long r1);

    /* renamed from: update-voP-kFw, reason: not valid java name */
    boolean mo533updatevoPkFw(long r1, FrameMetadata r3);
}
