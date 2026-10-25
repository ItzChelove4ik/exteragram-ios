package androidx.camera.camera2.pipe;

import android.view.Surface;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u00012\u00020\u0002R \u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\f\u001a\u00020\t8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/RequestMetadata;", "Landroidx/camera/camera2/pipe/Metadata;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "getStreams", "()Ljava/util/Map;", "streams", _UrlKt.FRAGMENT_ENCODE_SET, "getRepeating", "()Z", "repeating", "Landroidx/camera/camera2/pipe/Request;", "getRequest", "()Landroidx/camera/camera2/pipe/Request;", PluginsConstants.REQUEST, "Landroidx/camera/camera2/pipe/RequestNumber;", "getRequestNumber-my6kx4g", "()J", "requestNumber", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface RequestMetadata extends Metadata, UnsafeWrapper {
    boolean getRepeating();

    Request getRequest();

    /* renamed from: getRequestNumber-my6kx4g */
    long mo67getRequestNumbermy6kx4g();

    Map<StreamId, Surface> getStreams();
}
