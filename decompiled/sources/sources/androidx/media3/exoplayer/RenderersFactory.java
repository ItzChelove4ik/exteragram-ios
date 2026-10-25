package androidx.media3.exoplayer;

import android.os.Handler;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* loaded from: classes4.dex */
public interface RenderersFactory {
    Renderer[] createRenderers(Handler r1, VideoRendererEventListener r2, AudioRendererEventListener r3, TextOutput r4, MetadataOutput r5);

    Renderer createSecondaryRenderer(Renderer r1, Handler r2, VideoRendererEventListener r3, AudioRendererEventListener r4, TextOutput r5, MetadataOutput r6);
}
