package androidx.camera.camera2.pipe.core;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/core/Token;", _UrlKt.FRAGMENT_ENCODE_SET, "released", _UrlKt.FRAGMENT_ENCODE_SET, "getReleased", "()Z", BuildConfig.BUILD_TYPE, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface Token {
    boolean getReleased();

    boolean release();
}
