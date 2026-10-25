package androidx.camera.camera2.impl;

import android.view.Surface;
import androidx.camera.camera2.pipe.Metadata;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.RequestNumber;
import androidx.camera.camera2.pipe.RequestTemplate;
import androidx.camera.camera2.pipe.StreamId;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000O\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0005\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006J+\u0010\b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\b\u0010\tJ)\u0010\r\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0002*\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R&\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001c\u001a\u00020\u001b8\u0016X\u0096D¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010!\u001a\u00020 8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001a\u0010&\u001a\u00020%8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"androidx/camera/camera2/impl/CapturePipelineImpl$emptyRequestMetadata$1", "Landroidx/camera/camera2/pipe/RequestMetadata;", "T", "Landroidx/camera/camera2/pipe/Metadata$Key;", PluginsConstants.Settings.KEY, "get", "(Landroidx/camera/camera2/pipe/Metadata$Key;)Ljava/lang/Object;", PluginsConstants.Settings.DEFAULT, "getOrDefault", "(Landroidx/camera/camera2/pipe/Metadata$Key;Ljava/lang/Object;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/RequestTemplate;", "template", "I", "getTemplate-fGx8uWA", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "streams", "Ljava/util/Map;", "getStreams", "()Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "repeating", "Z", "getRepeating", "()Z", "Landroidx/camera/camera2/pipe/Request;", PluginsConstants.REQUEST, "Landroidx/camera/camera2/pipe/Request;", "getRequest", "()Landroidx/camera/camera2/pipe/Request;", "Landroidx/camera/camera2/pipe/RequestNumber;", "requestNumber", "J", "getRequestNumber-my6kx4g", "()J", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CapturePipelineImpl$emptyRequestMetadata$1 implements RequestMetadata {
    private final boolean repeating;
    private final Request request;
    private final long requestNumber;
    private final Map<StreamId, Surface> streams;
    private final int template;

    public CapturePipelineImpl$emptyRequestMetadata$1() {
        this.template = RequestTemplate.m371constructorimpl(0);
        this.streams = MapsKt.emptyMap();
        this.repeating = true;
        this.request = new Request(CollectionsKt.emptyList(), null, null, null, null, null, 62, null);
        this.requestNumber = RequestNumber.m365constructorimpl(0);
    }

    @Override // androidx.camera.camera2.pipe.Metadata
    public <T> T get(Metadata.Key<T> r1) {
        return null;
    }

    @Override // androidx.camera.camera2.pipe.Metadata
    public <T> T getOrDefault(Metadata.Key<T> r1, T r2) {
        return r2;
    }

    @Override // androidx.camera.camera2.pipe.RequestMetadata
    public boolean getRepeating() {
        return this.repeating;
    }

    @Override // androidx.camera.camera2.pipe.RequestMetadata
    public Request getRequest() {
        return this.request;
    }

    @Override // androidx.camera.camera2.pipe.RequestMetadata
    /* renamed from: getRequestNumber-my6kx4g, reason: not valid java name */
    public long mo67getRequestNumbermy6kx4g() {
        return this.requestNumber;
    }

    @Override // androidx.camera.camera2.pipe.RequestMetadata
    public Map<StreamId, Surface> getStreams() {
        return this.streams;
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r1) {
        return null;
    }
}
