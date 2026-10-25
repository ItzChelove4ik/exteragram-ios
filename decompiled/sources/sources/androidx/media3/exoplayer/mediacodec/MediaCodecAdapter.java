package androidx.media3.exoplayer.mediacodec;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.decoder.CryptoInfo;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public interface MediaCodecAdapter {

    public static final class Configuration {
        public final MediaCodecInfo codecInfo;
        public final MediaCrypto crypto;
        public final Format format;
        public final LoudnessCodecController loudnessCodecController;
        public final MediaFormat mediaFormat;
        public final Surface surface;

        private Configuration(MediaCodecInfo r1, MediaFormat r2, Format r3, Surface r4, MediaCrypto r5, LoudnessCodecController r6) {
            this.codecInfo = r1;
            this.mediaFormat = r2;
            this.format = r3;
            this.surface = r4;
            this.crypto = r5;
            this.loudnessCodecController = r6;
        }

        public static Configuration createForAudioDecoding(MediaCodecInfo r7, MediaFormat r8, Format r9, MediaCrypto r10, LoudnessCodecController r11) {
            return new Configuration(r7, r8, r9, null, r10, r11);
        }

        public static Configuration createForVideoDecoding(MediaCodecInfo r7, MediaFormat r8, Format r9, Surface r10, MediaCrypto r11) {
            return new Configuration(r7, r8, r9, r10, r11, null);
        }
    }

    public interface Factory {

        @Deprecated
        public static final Factory DEFAULT = null;

        static {
            DEFAULT = new DefaultMediaCodecAdapterFactory();
        }

        static Factory getDefault(Context r1) {
            return new DefaultMediaCodecAdapterFactory(r1);
        }

        MediaCodecAdapter createAdapter(Configuration r1);
    }

    public interface OnBufferAvailableListener {
        void onInputBufferAvailable();

        void onOutputBufferAvailable();
    }

    public interface OnFrameRenderedListener {
        void onFrameRendered(MediaCodecAdapter r1, long r2, long r4);
    }

    int dequeueInputBufferIndex();

    int dequeueOutputBufferIndex(MediaCodec.BufferInfo r1);

    void detachOutputSurface();

    void flush();

    ByteBuffer getInputBuffer(int r1);

    ByteBuffer getOutputBuffer(int r1);

    MediaFormat getOutputFormat();

    boolean needsReconfiguration();

    void queueInputBuffer(int r1, int r2, int r3, long r4, int r6);

    void queueSecureInputBuffer(int r1, int r2, CryptoInfo r3, long r4, int r6);

    default boolean registerOnBufferAvailableListener(OnBufferAvailableListener r1) {
        return false;
    }

    void release();

    void releaseOutputBuffer(int r1, long r2);

    void releaseOutputBuffer(int r1, boolean r2);

    void setOnFrameRenderedListener(OnFrameRenderedListener r1, Handler r2);

    void setOutputSurface(Surface r1);

    void setParameters(Bundle r1);

    void setVideoScalingMode(int r1);
}
