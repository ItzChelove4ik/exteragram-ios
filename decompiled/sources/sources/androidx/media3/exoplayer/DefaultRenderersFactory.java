package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Log;
import androidx.media3.decoder.ffmpeg.ExperimentalFfmpegVideoRenderer;
import androidx.media3.decoder.ffmpeg.FfmpegAudioRenderer;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.audio.MediaCodecAudioRenderer;
import androidx.media3.exoplayer.image.BitmapFactoryImageDecoder;
import androidx.media3.exoplayer.image.ImageDecoder;
import androidx.media3.exoplayer.image.ImageRenderer;
import androidx.media3.exoplayer.mediacodec.DefaultMediaCodecAdapterFactory;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.metadata.MetadataRenderer;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.text.TextRenderer;
import androidx.media3.exoplayer.video.MediaCodecVideoRenderer;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.spherical.CameraMotionRenderer;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class DefaultRenderersFactory implements RenderersFactory {
    private long allowedVideoJoiningTimeMs;
    private final DefaultMediaCodecAdapterFactory codecAdapterFactory;
    private final Context context;
    private boolean enableAudioTrackPlaybackParams;
    private boolean enableDecoderFallback;
    private boolean enableFloatOutput;
    private boolean enableMediaCodecBufferDecodeOnlyFlag;
    private boolean enableMediaCodecVideoRendererPrewarming;
    private int extensionRendererMode;
    private long lateThresholdToDropDecoderInputUs;
    private MediaCodecSelector mediaCodecSelector;
    private boolean parseAv1SampleDependencies;

    public DefaultRenderersFactory(Context r3) {
        this.context = r3;
        this.codecAdapterFactory = new DefaultMediaCodecAdapterFactory(r3);
        this.extensionRendererMode = 0;
        this.allowedVideoJoiningTimeMs = 5000;
        this.mediaCodecSelector = MediaCodecSelector.DEFAULT;
        this.lateThresholdToDropDecoderInputUs = -9223372036854775807L;
    }

    public void buildAudioRenderers(Context r16, int r17, MediaCodecSelector r18, boolean r19, AudioSink r20, Handler r21, AudioRendererEventListener r22, ArrayList<Renderer> r23) {
        r23.add(new MediaCodecAudioRenderer(r16, getCodecAdapterFactory(), r18, r19, r21, r22, r20));
        if (r17 == 0) goto L86;
        int r15 = r23.size();
        if (r17 != 2) goto L70;
        r15 = r15 - 1;
    L70:
        int r2 = r15 + 1;
        r23.add(r15, (Renderer) Class.forName("androidx.media3.decoder.midi.MidiRenderer").getConstructor(new Class[]{Context.class, Handler.class, AudioRendererEventListener.class, AudioSink.class}).newInstance(new Object[]{r16, r21, r22, r20}));     // Catch: ClassNotFoundException -> L12 Exception -> L13
        Log.i("DefaultRenderersFactory", "Loaded MidiRenderer.");     // Catch: ClassNotFoundException -> L12 Exception -> L13
    L81:
        int r0 = r2 + 1;
        r23.add(r2, (Renderer) Class.forName("androidx.media3.decoder.opus.LibopusAudioRenderer").getConstructor(new Class[]{Handler.class, AudioRendererEventListener.class, AudioSink.class}).newInstance(new Object[]{r21, r22, r20}));     // Catch: ClassNotFoundException -> L21 Exception -> L22
        Log.i("DefaultRenderersFactory", "Loaded LibopusAudioRenderer.");     // Catch: ClassNotFoundException -> L21 Exception -> L22
    L73:
        int r24 = r0 + 1;
        r23.add(r0, (Renderer) Class.forName("androidx.media3.decoder.flac.LibflacAudioRenderer").getConstructor(new Class[]{Handler.class, AudioRendererEventListener.class, AudioSink.class}).newInstance(new Object[]{r21, r22, r20}));     // Catch: ClassNotFoundException -> L30 Exception -> L31
        Log.i("DefaultRenderersFactory", "Loaded LibflacAudioRenderer.");     // Catch: ClassNotFoundException -> L30 Exception -> L31
    L64:
        int r02 = r24 + 1;
        r23.add(r24, (Renderer) FfmpegAudioRenderer.class.getConstructor(new Class[]{Handler.class, AudioRendererEventListener.class, AudioSink.class}).newInstance(new Object[]{r21, r22, r20}));     // Catch: ClassNotFoundException -> L39 Exception -> L40
        Log.i("DefaultRenderersFactory", "Loaded FfmpegAudioRenderer.");     // Catch: ClassNotFoundException -> L39 Exception -> L40
    L66:
        int r1 = r02 + 1;
        r23.add(r02, (Renderer) Class.forName("androidx.media3.decoder.iamf.LibiamfAudioRenderer").getConstructor(new Class[]{Context.class, Handler.class, AudioRendererEventListener.class, AudioSink.class}).newInstance(new Object[]{r16, r21, r22, r20}));     // Catch: ClassNotFoundException -> L48 Exception -> L49
        Log.i("DefaultRenderersFactory", "Loaded LibiamfAudioRenderer.");     // Catch: ClassNotFoundException -> L48 Exception -> L49
    L84:
        r23.add(r1, (Renderer) Class.forName("androidx.media3.decoder.mpegh.MpeghAudioRenderer").getConstructor(new Class[]{Handler.class, AudioRendererEventListener.class, AudioSink.class}).newInstance(new Object[]{r21, r22, r20}));     // Catch: Exception -> L55 ClassNotFoundException -> L63
        Log.i("DefaultRenderersFactory", "Loaded MpeghAudioRenderer.");     // Catch: Exception -> L55 ClassNotFoundException -> L63
        goto L88
    L87:
        return;
    L55:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Error instantiating MPEG-H extension", e);
        return;
    L88:
        return;
    L48:
        r02 = r1;
    L49:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Error instantiating IAMF extension", e);
        return;
    L52:
        r1 = r02;
        goto L84
    L39:
        r24 = r02;
    L40:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Error instantiating FFmpeg extension", e);
        return;
    L43:
        r02 = r24;
        goto L66
    L30:
        r0 = r24;
    L31:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Error instantiating FLAC extension", e);
        return;
    L34:
        r24 = r0;
    L21:
        r2 = r0;
    L22:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Error instantiating Opus extension", e);
        return;
    L25:
        r0 = r2;
    L12:
        r15 = r2;
    L13:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Error instantiating MIDI extension", e);
        return;
    L16:
        r2 = r15;
        goto L81
    }

    public AudioSink buildAudioSink(Context r1, boolean r2, boolean r3) {
        return new DefaultAudioSink.Builder(r1).setEnableFloatOutput(r2).setEnableAudioTrackPlaybackParams(r3).build();
    }

    public void buildCameraMotionRenderers(Context r1, int r2, ArrayList<Renderer> r3) {
        r3.add(new CameraMotionRenderer());
    }

    public void buildImageRenderers(Context r1, ArrayList<Renderer> r2) {
        buildImageRenderers(r2);
    }

    @Deprecated
    public void buildImageRenderers(ArrayList<Renderer> r3) {
        r3.add(new ImageRenderer(getImageDecoderFactory(this.context), null));
    }

    public void buildMetadataRenderers(Context r1, MetadataOutput r2, Looper r3, int r4, ArrayList<Renderer> r5) {
        r5.add(new MetadataRenderer(r2, r3));
        r5.add(new MetadataRenderer(r2, r3));
    }

    public void buildMiscellaneousRenderers(Context r1, Handler r2, int r3, ArrayList<Renderer> r4) {
    }

    public Renderer buildSecondaryVideoRenderer(Renderer r1, Context r2, int r3, MediaCodecSelector r4, boolean r5, Handler r6, VideoRendererEventListener r7, long r8) {
        if (this.enableMediaCodecVideoRendererPrewarming == true) goto L5;
        return null;
    L5:
        if (r1.getClass() != MediaCodecVideoRenderer.class) goto L13;
        MediaCodecVideoRenderer.Builder r12 = new MediaCodecVideoRenderer.Builder(r2).setCodecAdapterFactory(getCodecAdapterFactory()).setMediaCodecSelector(r4).setAllowedJoiningTimeMs(r8).setEnableDecoderFallback(r5).setEventHandler(r6).setEventListener(r7).setMaxDroppedFramesToNotify(50).experimentalSetParseAv1SampleDependencies(this.parseAv1SampleDependencies).experimentalSetLateThresholdToDropDecoderInputUs(this.lateThresholdToDropDecoderInputUs);
        if (Build.VERSION.SDK_INT < 34) goto L10;
        r12 = r12.experimentalSetEnableMediaCodecBufferDecodeOnlyFlag(this.enableMediaCodecBufferDecodeOnlyFlag);
    L10:
        return r12.build();
    L13:
        return null;
    }

    public void buildTextRenderers(Context r1, TextOutput r2, Looper r3, int r4, ArrayList<Renderer> r5) {
        r5.add(new TextRenderer(r2, r3));
    }

    public void buildVideoRenderers(Context r18, int r19, MediaCodecSelector r20, boolean r21, Handler r22, VideoRendererEventListener r23, long r24, ArrayList<Renderer> r26) {
        Class<?> r6 = Integer.TYPE;
        Class<?> r9 = Long.TYPE;
        MediaCodecVideoRenderer.Builder r2 = new MediaCodecVideoRenderer.Builder(r18).setCodecAdapterFactory(getCodecAdapterFactory()).setMediaCodecSelector(r20).setAllowedJoiningTimeMs(r24).setEnableDecoderFallback(r21).setEventHandler(r22).setEventListener(r23).setMaxDroppedFramesToNotify(50).experimentalSetParseAv1SampleDependencies(this.parseAv1SampleDependencies).experimentalSetLateThresholdToDropDecoderInputUs(this.lateThresholdToDropDecoderInputUs);
        if (Build.VERSION.SDK_INT < 34) goto L5;
        r2 = r2.experimentalSetEnableMediaCodecBufferDecodeOnlyFlag(this.enableMediaCodecBufferDecodeOnlyFlag);
    L5:
        r26.add(r2.build());
        if (r19 == 0) goto L53;
        int r0 = r26.size();
        if (r19 != 2) goto L41;
        r0 = r0 - 1;
    L41:
        char r17 = 3;
        int r15 = r0 + 1;
        r26.add(r0, (Renderer) Class.forName("androidx.media3.decoder.vp9.LibvpxVideoRenderer").getConstructor(new Class[]{r9, Handler.class, VideoRendererEventListener.class, r6}).newInstance(new Object[]{Long.valueOf(r24), r22, r23, 50}));     // Catch: ClassNotFoundException -> L18 Exception -> L20
        Log.i("DefaultRenderersFactory", "Loaded LibvpxVideoRenderer.");     // Catch: ClassNotFoundException -> L18 Exception -> L20
    L45:
        Class<?> r02 = Class.forName("androidx.media3.decoder.av1.Libgav1VideoRenderer");     // Catch: Exception -> L29 ClassNotFoundException -> L38
        Class<?>[] r1 = new Class[4];     // Catch: Exception -> L29 ClassNotFoundException -> L38
        r1[0] = r9;     // Catch: Exception -> L29 ClassNotFoundException -> L38
        r1[1] = Handler.class;     // Catch: Exception -> L29 ClassNotFoundException -> L38
        r1[2] = VideoRendererEventListener.class;     // Catch: Exception -> L29 ClassNotFoundException -> L38
        r1[r17] = r6;     // Catch: Exception -> L29 ClassNotFoundException -> L38
        Constructor<?> r03 = r02.getConstructor(r1);     // Catch: Exception -> L29 ClassNotFoundException -> L38
        Object[] r12 = new Object[4];     // Catch: Exception -> L29 ClassNotFoundException -> L38
        r12[0] = Long.valueOf(r24);     // Catch: Exception -> L29 ClassNotFoundException -> L38
        r12[1] = r22;     // Catch: Exception -> L29 ClassNotFoundException -> L38
        r12[2] = r23;     // Catch: Exception -> L29 ClassNotFoundException -> L38
        r12[r17] = 50;     // Catch: Exception -> L29 ClassNotFoundException -> L38
        Renderer r04 = (Renderer) r03.newInstance(r12);     // Catch: Exception -> L29 ClassNotFoundException -> L38
        int r13 = r15 + 1;
        r26.add(r15, r04);     // Catch: ClassNotFoundException -> L28 Exception -> L29
        Log.i("DefaultRenderersFactory", "Loaded Libgav1VideoRenderer.");     // Catch: ClassNotFoundException -> L28 Exception -> L29
    L51:
        int r152 = ExperimentalFfmpegVideoRenderer.$r8$clinit;     // Catch: Exception -> L35 ClassNotFoundException -> L40
        Class[] r153 = new Class[4];     // Catch: Exception -> L35 ClassNotFoundException -> L40
        r153[0] = r9;     // Catch: Exception -> L35 ClassNotFoundException -> L40
        r153[1] = Handler.class;     // Catch: Exception -> L35 ClassNotFoundException -> L40
        r153[2] = VideoRendererEventListener.class;     // Catch: Exception -> L35 ClassNotFoundException -> L40
        r153[r17] = r6;     // Catch: Exception -> L35 ClassNotFoundException -> L40
        Constructor r05 = ExperimentalFfmpegVideoRenderer.class.getConstructor(r153);     // Catch: Exception -> L35 ClassNotFoundException -> L40
        Object[] r62 = new Object[4];     // Catch: Exception -> L35 ClassNotFoundException -> L40
        r62[0] = Long.valueOf(r24);     // Catch: Exception -> L35 ClassNotFoundException -> L40
        r62[1] = r22;     // Catch: Exception -> L35 ClassNotFoundException -> L40
        r62[2] = r23;     // Catch: Exception -> L35 ClassNotFoundException -> L40
        r62[r17] = 50;     // Catch: Exception -> L35 ClassNotFoundException -> L40
        r26.add(r13, (Renderer) r05.newInstance(r62));     // Catch: Exception -> L35 ClassNotFoundException -> L40
        Log.i("DefaultRenderersFactory", "Loaded FfmpegVideoRenderer.");     // Catch: Exception -> L35 ClassNotFoundException -> L40
        goto L55
    L54:
        return;
    L35:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Error instantiating FFmpeg extension", e);
        return;
    L55:
        return;
    L28:
        r15 = r13;
    L29:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Error instantiating AV1 extension", e);
        return;
    L32:
        r13 = r15;
    L18:
        r0 = r15;
    L23:
        r15 = r0;
    L20:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Error instantiating VP9 extension", e);
        return;
    L19:
        r17 = 3;
        goto L23
    }

    @Override // androidx.media3.exoplayer.RenderersFactory
    public Renderer[] createRenderers(Handler r11, VideoRendererEventListener r12, AudioRendererEventListener r13, TextOutput r14, MetadataOutput r15) {
        ArrayList<Renderer> r5 = new ArrayList();
        buildVideoRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, this.enableDecoderFallback, r11, r12, this.allowedVideoJoiningTimeMs, r5);
        AudioSink r10 = buildAudioSink(this.context, this.enableFloatOutput, this.enableAudioTrackPlaybackParams);
        if (r10 == null) goto L5;
        Handler r6 = r11;
        buildAudioRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, this.enableDecoderFallback, r10, r6, r13, r5);
    L6:
        buildTextRenderers(this.context, r14, r6.getLooper(), this.extensionRendererMode, r5);
        buildMetadataRenderers(this.context, r15, r6.getLooper(), this.extensionRendererMode, r5);
        buildCameraMotionRenderers(this.context, this.extensionRendererMode, r5);
        buildImageRenderers(this.context, r5);
        buildMiscellaneousRenderers(this.context, r6, this.extensionRendererMode, r5);
        return (Renderer[]) r5.toArray(new Renderer[0]);
    L5:
        r6 = r11;
        goto L6
    }

    @Override // androidx.media3.exoplayer.RenderersFactory
    public Renderer createSecondaryRenderer(Renderer r11, Handler r12, VideoRendererEventListener r13, AudioRendererEventListener r14, TextOutput r15, MetadataOutput r16) {
        if (r11.getTrackType() == 2) goto L5;
        return null;
    L5:
        return buildSecondaryVideoRenderer(r11, this.context, this.extensionRendererMode, this.mediaCodecSelector, this.enableDecoderFallback, r12, r13, this.allowedVideoJoiningTimeMs);
    }

    public MediaCodecAdapter.Factory getCodecAdapterFactory() {
        return this.codecAdapterFactory;
    }

    public ImageDecoder.Factory getImageDecoderFactory(Context r1) {
        return new BitmapFactoryImageDecoder.Factory(r1);
    }

    public final DefaultRenderersFactory setExtensionRendererMode(int r1) {
        this.extensionRendererMode = r1;
        return this;
    }
}
