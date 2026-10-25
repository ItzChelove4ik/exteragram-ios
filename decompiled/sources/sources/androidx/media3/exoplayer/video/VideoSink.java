package androidx.media3.exoplayer.video;

import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Size;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public interface VideoSink {

    public interface Listener {
        public static final Listener NO_OP = null;

        /* renamed from: androidx.media3.exoplayer.video.VideoSink$Listener$1, reason: invalid class name */
        public class AnonymousClass1 implements Listener {
            public AnonymousClass1() {
            }
        }

        static {
            NO_OP = new AnonymousClass1();
        }

        default void onFirstFrameRendered() {
        }

        default void onFrameAvailableForRendering() {
        }

        default void onFrameDropped() {
        }

        default void onVideoSizeChanged(VideoSize r1) {
        }
    }

    public interface VideoFrameHandler {
        void render(long r1);

        void skip();
    }

    public static final class VideoSinkException extends Exception {
        public final Format format;

        public VideoSinkException(Throwable r1, Format r2) {
            super(r1);
            this.format = r2;
        }
    }

    void allowReleaseFirstFrameBeforeStarted();

    void clearOutputSurfaceInfo();

    void flush(boolean r1);

    Surface getInputSurface();

    boolean handleInputFrame(long r1, VideoFrameHandler r3);

    boolean initialize(Format r1);

    boolean isEnded();

    boolean isInitialized();

    boolean isReady(boolean r1);

    void join(boolean r1);

    void onInputStreamChanged(int r1, Format r2, long r3, int r5, List<Object> r6);

    void redraw();

    void release();

    void render(long r1, long r3);

    void setBufferTimestampAdjustmentUs(long r1);

    void setChangeFrameRateStrategy(int r1);

    void setListener(Listener r1, Executor r2);

    void setOutputSurfaceInfo(Surface r1, Size r2);

    void setPlaybackSpeed(float r1);

    void setVideoEffects(List<Object> r1);

    void setVideoFrameMetadataListener(VideoFrameMetadataListener r1);

    void signalEndOfCurrentInputStream();

    void startRendering();

    void stopRendering();
}
