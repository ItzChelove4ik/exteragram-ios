package androidx.media3.common;

import android.graphics.SurfaceTexture;

/* loaded from: classes3.dex */
public interface VideoListener {
    boolean onSurfaceDestroyed(SurfaceTexture r1);

    void onSurfaceTextureUpdated(SurfaceTexture r1);
}
