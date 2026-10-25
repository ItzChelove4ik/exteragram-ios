package androidx.media3.exoplayer.video;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Pair;
import android.util.SparseArray;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Format;
import androidx.media3.common.VideoCompositorSettings;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.video.VideoSink;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final class PlaybackVideoGraphWrapper implements VideoGraph.Listener {
    private static final Executor NO_OP_EXECUTOR = null;
    private final Clock clock;
    private ImmutableList<Object> compositionEffects;
    private VideoCompositorSettings compositorSettings;
    private final Context context;
    private Pair<Surface, Size> currentSurfaceAndSize;
    private final VideoSink defaultVideoSink;
    private final boolean enablePlaylistMode;
    private long finalFramePresentationTimeUs;
    private HandlerWrapper handler;
    private boolean hasSignaledEndOfVideoGraphOutputStream;
    private final SparseArray<InputVideoSink> inputVideoSinks;
    private boolean isInputSdrToneMapped;
    private long lastOutputFramePresentationTimeUs;
    private final CopyOnWriteArraySet<Listener> listeners;
    private int outputStreamFirstFrameReleaseInstruction;
    private long outputStreamStartPositionUs;
    private int pendingFlushCount;
    private TimedValueQueue<StreamChangeInfo> pendingStreamChanges;
    private int registeredVideoInputCount;
    private boolean requestOpenGlToneMapping;
    private int state;
    private int totalVideoInputCount;
    private final VideoSink.VideoFrameHandler videoFrameHandler;
    private VideoFrameMetadataListener videoFrameMetadataListener;
    private final VideoGraph.Factory videoGraphFactory;
    private Format videoGraphOutputFormat;

    /* renamed from: androidx.media3.exoplayer.video.PlaybackVideoGraphWrapper$1, reason: invalid class name */
    public class AnonymousClass1 implements VideoSink.VideoFrameHandler {
        final /* synthetic */ PlaybackVideoGraphWrapper this$0;

        public AnonymousClass1(PlaybackVideoGraphWrapper r1) {
            this.this$0 = r1;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink.VideoFrameHandler
        public void render(long r1) {
            PlaybackVideoGraphWrapper.access$600(this.this$0);
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkStateNotNull(null));
            throw null;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink.VideoFrameHandler
        public void skip() {
            PlaybackVideoGraphWrapper.access$600(this.this$0);
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkStateNotNull(null));
            throw null;
        }
    }

    public static final class Builder {
        private boolean built;
        private Clock clock;
        private final Context context;
        private boolean enablePlaylistMode;
        private boolean enableReplayableCache;
        private final VideoFrameReleaseControl videoFrameReleaseControl;
        private VideoGraph.Factory videoGraphFactory;

        public Builder(Context r1, VideoFrameReleaseControl r2) {
            this.context = r1.getApplicationContext();
            this.videoFrameReleaseControl = r2;
            this.clock = Clock.DEFAULT;
        }

        public static /* synthetic */ Context access$100(Builder r0) {
            return r0.context;
        }

        public static /* synthetic */ VideoGraph.Factory access$200(Builder r0) {
            return r0.videoGraphFactory;
        }

        public static /* synthetic */ boolean access$300(Builder r0) {
            return r0.enablePlaylistMode;
        }

        public static /* synthetic */ Clock access$400(Builder r0) {
            return r0.clock;
        }

        public static /* synthetic */ VideoFrameReleaseControl access$500(Builder r0) {
            return r0.videoFrameReleaseControl;
        }

        public PlaybackVideoGraphWrapper build() {
            Assertions.checkState(!this.built);
            if (this.videoGraphFactory != null) goto L5;
            this.videoGraphFactory = new ReflectiveSingleInputVideoGraphFactory(this.enableReplayableCache);
        L5:
            PlaybackVideoGraphWrapper r0 = new PlaybackVideoGraphWrapper(this, null);
            this.built = true;
            return r0;
        }

        public Builder setClock(Clock r1) {
            this.clock = r1;
            return this;
        }

        public Builder setEnablePlaylistMode(boolean r1) {
            this.enablePlaylistMode = r1;
            return this;
        }
    }

    public final class InputVideoSink implements VideoSink, Listener {
        private long inputBufferTimestampAdjustmentUs;
        private Format inputFormat;
        private final int inputIndex;
        private int inputType;
        private boolean isInitialized;
        private long lastFramePresentationTimeUs;
        private VideoSink.Listener listener;
        private Executor listenerExecutor;
        private boolean signaledEndOfStream;
        final /* synthetic */ PlaybackVideoGraphWrapper this$0;
        private ImmutableList<Object> videoEffects;
        private final int videoFrameProcessorMaxPendingFrameCount;

        public InputVideoSink(PlaybackVideoGraphWrapper r1, Context r2, int r3) {
            this.this$0 = r1;
            this.inputIndex = r3;
            this.videoFrameProcessorMaxPendingFrameCount = Util.getMaxPendingFramesCountForMediaCodecDecoders(r2);
            this.videoEffects = ImmutableList.of();
            this.lastFramePresentationTimeUs = -9223372036854775807L;
            this.listener = VideoSink.Listener.NO_OP;
            this.listenerExecutor = PlaybackVideoGraphWrapper.access$800();
        }

        private void registerInputStream(Format r3) {
            r3.buildUpon().setColorInfo(PlaybackVideoGraphWrapper.access$2600(this.this$0, r3.colorInfo)).build();
            PlaybackVideoGraphWrapper.access$600(this.this$0);
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkNotNull(null));
            throw null;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void allowReleaseFirstFrameBeforeStarted() {
            if (PlaybackVideoGraphWrapper.access$1800(this.this$0).size() != 0) goto L6;
            PlaybackVideoGraphWrapper.access$1900(this.this$0);
            return;
        L6:
            TimedValueQueue r0 = new TimedValueQueue();
            boolean r2 = true;
        L7:
            int r3 = PlaybackVideoGraphWrapper.access$1800(this.this$0).size();
            PlaybackVideoGraphWrapper r4 = this.this$0;
            if (r3 <= 0) goto L19;
            StreamChangeInfo r32 = (StreamChangeInfo) Assertions.checkNotNull((StreamChangeInfo) PlaybackVideoGraphWrapper.access$1800(r4).pollFirst());
            if (r2 == false) goto L18;
            int r22 = r32.firstFrameReleaseInstruction;
            if (r22 == 0) goto L16;
            if (r22 == 1) goto L16;
            PlaybackVideoGraphWrapper.access$1900(this.this$0);
        L17:
            r2 = false;
        L16:
            r32 = new StreamChangeInfo(r32.startPositionUs, 0, r32.fromTimestampUs);
        L18:
            r0.add(r32.fromTimestampUs, r32);
            goto L7
        L19:
            PlaybackVideoGraphWrapper.access$1802(r4, r0);
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void clearOutputSurfaceInfo() {
            this.this$0.clearOutputSurfaceInfo();
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void flush(boolean r3) {
            if (isInitialized() == true) goto L6;
            this.lastFramePresentationTimeUs = -9223372036854775807L;
            PlaybackVideoGraphWrapper.access$1200(this.this$0, r3);
            this.signaledEndOfStream = false;
            return;
        L6:
            PlaybackVideoGraphWrapper.access$600(this.this$0);
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkNotNull(null));
            throw null;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public Surface getInputSurface() {
            Assertions.checkState(isInitialized());
            PlaybackVideoGraphWrapper.access$600(this.this$0);
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkNotNull(null));
            throw null;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public boolean handleInputFrame(long r1, VideoSink.VideoFrameHandler r3) {
            Assertions.checkState(isInitialized());
            if (PlaybackVideoGraphWrapper.access$2300(this.this$0) == true) goto L6;
            return false;
        L6:
            PlaybackVideoGraphWrapper.access$600(this.this$0);
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkNotNull(null));
            throw null;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public boolean initialize(Format r3) {
            Assertions.checkState(!isInitialized());
            boolean r32 = PlaybackVideoGraphWrapper.access$1000(this.this$0, r3, this.inputIndex);
            this.isInitialized = r32;
            return r32;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public boolean isEnded() {
            if (isInitialized() == true) goto L5;
            return false;
        L5:
            if (PlaybackVideoGraphWrapper.access$1600(this.this$0) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public boolean isInitialized() {
            return this.isInitialized;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public boolean isReady(boolean r2) {
            PlaybackVideoGraphWrapper r0 = this.this$0;
            if (r2 == true) goto L5;
        L7:
            boolean r1 = false;
        L9:
            return PlaybackVideoGraphWrapper.access$1300(r0, r1);
        L5:
            if (isInitialized() == false) goto L7;
            r1 = true;
            goto L9
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void join(boolean r2) {
            if (PlaybackVideoGraphWrapper.access$900(this.this$0) == false) goto L6;
            PlaybackVideoGraphWrapper.access$2500(this.this$0, r2);
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void onInputStreamChanged(int r7, Format r8, long r9, int r11, List<Object> r12) {
            Assertions.checkState(isInitialized());
            this.videoEffects = ImmutableList.copyOf(r12);
            this.inputType = r7;
            this.inputFormat = r8;
            PlaybackVideoGraphWrapper.access$1402(this.this$0, -9223372036854775807L);
            boolean r122 = false;
            PlaybackVideoGraphWrapper.access$1702(this.this$0, false);
            registerInputStream(r8);
            if (this.lastFramePresentationTimeUs != (-9223372036854775807L)) goto L6;
            r122 = true;
        L6:
            if (PlaybackVideoGraphWrapper.access$900(this.this$0) == false) goto L8;
        L12:
            if (r122 == false) goto L15;
            long r72 = -4611686018427387904L;
        L14:
            long r4 = r72;
            PlaybackVideoGraphWrapper.access$1800(this.this$0).add(r4, new StreamChangeInfo(this.inputBufferTimestampAdjustmentUs + r9, r11, r4));
            return;
        L15:
            r72 = this.lastFramePresentationTimeUs + 1;
            goto L14
        L8:
            if (this.inputIndex != 0) goto L11;
            if (r122 == true) goto L12;
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void redraw() {
            if (isInitialized() == true) goto L5;
            return;
        L5:
            PlaybackVideoGraphWrapper.access$1100(this.this$0);
            PlaybackVideoGraphWrapper.access$1200(this.this$0, false);
            PlaybackVideoGraphWrapper.access$600(this.this$0);
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkNotNull(null));
            throw null;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void release() {
            this.this$0.release();
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void render(long r4, long r6) {
            PlaybackVideoGraphWrapper.access$2400(this.this$0, r4 + this.inputBufferTimestampAdjustmentUs, r6);
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void setBufferTimestampAdjustmentUs(long r1) {
            this.inputBufferTimestampAdjustmentUs = r1;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void setChangeFrameRateStrategy(int r2) {
            if (this.inputIndex != 0) goto L6;
            PlaybackVideoGraphWrapper.access$2200(this.this$0, r2);
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void setListener(VideoSink.Listener r1, Executor r2) {
            this.listener = r1;
            this.listenerExecutor = r2;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void setOutputSurfaceInfo(Surface r1, Size r2) {
            this.this$0.setOutputSurfaceInfo(r1, r2);
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void setPlaybackSpeed(float r2) {
            if (this.inputIndex != 0) goto L6;
            PlaybackVideoGraphWrapper.access$2100(this.this$0, r2);
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void setVideoEffects(List<Object> r2) {
            if (this.videoEffects.equals(r2) == true) goto L10;
            this.videoEffects = ImmutableList.copyOf(r2);
            Format r22 = this.inputFormat;
            if (r22 == null) goto L9;
            registerInputStream(r22);
            return;
        L9:
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void setVideoFrameMetadataListener(VideoFrameMetadataListener r2) {
            if (this.inputIndex != 0) goto L6;
            PlaybackVideoGraphWrapper.access$2000(this.this$0, r2);
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void signalEndOfCurrentInputStream() {
            PlaybackVideoGraphWrapper.access$1402(this.this$0, this.lastFramePresentationTimeUs);
            if (PlaybackVideoGraphWrapper.access$1100(this.this$0) < PlaybackVideoGraphWrapper.access$1400(this.this$0)) goto L6;
            PlaybackVideoGraphWrapper.access$1500(this.this$0);
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void startRendering() {
            if (PlaybackVideoGraphWrapper.access$900(this.this$0) == false) goto L6;
            this.this$0.startRendering();
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink
        public void stopRendering() {
            if (PlaybackVideoGraphWrapper.access$900(this.this$0) == false) goto L6;
            this.this$0.stopRendering();
            return;
        }
    }

    public interface Listener {
    }

    public static final class ReflectiveDefaultVideoFrameProcessorFactory implements VideoFrameProcessor.Factory {
        private static final Supplier<Class<?>> DEFAULT_VIDEO_FRAME_PROCESSOR_FACTORY_BUILDER_CLASS = null;
        private final boolean enableReplayableCache;

        public static /* synthetic */ Class $r8$lambda$gadNd84MrDyg9TV6bUHeSpXwIW0() {
            return Class.forName("androidx.media3.effect.DefaultVideoFrameProcessor$Factory$Builder");
        L4:
            e = move-exception;
            UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
            return null;
        }

        static {
            DEFAULT_VIDEO_FRAME_PROCESSOR_FACTORY_BUILDER_CLASS = Suppliers.memoize(new PlaybackVideoGraphWrapper$ReflectiveDefaultVideoFrameProcessorFactory$$ExternalSyntheticLambda0());
        }

        public ReflectiveDefaultVideoFrameProcessorFactory(boolean r1) {
            this.enableReplayableCache = r1;
        }
    }

    public static final class ReflectiveSingleInputVideoGraphFactory implements VideoGraph.Factory {
        private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public ReflectiveSingleInputVideoGraphFactory(boolean r2) {
            this.videoFrameProcessorFactory = new ReflectiveDefaultVideoFrameProcessorFactory(r2);
        }

        @Override // androidx.media3.common.VideoGraph.Factory
        public VideoGraph create(Context r12, ColorInfo r13, DebugViewProvider r14, VideoGraph.Listener r15, Executor r16, long r17, boolean r19) {
            ((VideoGraph.Factory) Class.forName("androidx.media3.effect.SingleInputVideoGraph$Factory").getConstructor(new Class[]{VideoFrameProcessor.Factory.class}).newInstance(new Object[]{this.videoFrameProcessorFactory})).create(r12, r13, r14, r15, r16, r17, r19);
            return null;
        L6:
            e = move-exception;
            UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
            return null;
        }
    }

    public static final class StreamChangeInfo {
        public final int firstFrameReleaseInstruction;
        public final long fromTimestampUs;
        public final long startPositionUs;

        public StreamChangeInfo(long r1, int r3, long r4) {
            this.startPositionUs = r1;
            this.firstFrameReleaseInstruction = r3;
            this.fromTimestampUs = r4;
        }
    }

    public static /* synthetic */ void $r8$lambda$B1jGiGsd2XT6qDaCbp1_iYOfTl8(Runnable r0) {
    }

    public static /* synthetic */ void $r8$lambda$yb_uyj2eC9p1Xveodi0xzzHKIaU(PlaybackVideoGraphWrapper r1) {
        r1.pendingFlushCount--;
    }

    static {
        NO_OP_EXECUTOR = new PlaybackVideoGraphWrapper$$ExternalSyntheticLambda1();
    }

    private PlaybackVideoGraphWrapper(Builder r3) {
        this.context = Builder.access$100(r3);
        this.pendingStreamChanges = new TimedValueQueue();
        this.videoGraphFactory = (VideoGraph.Factory) Assertions.checkStateNotNull(Builder.access$200(r3));
        this.inputVideoSinks = new SparseArray();
        this.compositionEffects = ImmutableList.of();
        this.compositorSettings = VideoCompositorSettings.DEFAULT;
        this.enablePlaylistMode = Builder.access$300(r3);
        Clock r0 = Builder.access$400(r3);
        this.clock = r0;
        this.defaultVideoSink = new DefaultVideoSink(Builder.access$500(r3), r0);
        this.videoFrameHandler = new AnonymousClass1(this);
        this.listeners = new CopyOnWriteArraySet();
        this.videoGraphOutputFormat = new Format.Builder().build();
        this.outputStreamStartPositionUs = -9223372036854775807L;
        this.lastOutputFramePresentationTimeUs = -9223372036854775807L;
        this.finalFramePresentationTimeUs = -9223372036854775807L;
        this.totalVideoInputCount = -1;
        this.state = 0;
    }

    public /* synthetic */ PlaybackVideoGraphWrapper(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static /* synthetic */ boolean access$1000(PlaybackVideoGraphWrapper r0, Format r1, int r2) {
        return r0.registerInput(r1, r2);
    }

    public static /* synthetic */ long access$1100(PlaybackVideoGraphWrapper r2) {
        return r2.lastOutputFramePresentationTimeUs;
    }

    public static /* synthetic */ void access$1200(PlaybackVideoGraphWrapper r0, boolean r1) {
        r0.flush(r1);
    }

    public static /* synthetic */ boolean access$1300(PlaybackVideoGraphWrapper r0, boolean r1) {
        return r0.isReady(r1);
    }

    public static /* synthetic */ long access$1400(PlaybackVideoGraphWrapper r2) {
        return r2.finalFramePresentationTimeUs;
    }

    public static /* synthetic */ long access$1402(PlaybackVideoGraphWrapper r0, long r1) {
        r0.finalFramePresentationTimeUs = r1;
        return r1;
    }

    public static /* synthetic */ void access$1500(PlaybackVideoGraphWrapper r0) {
        r0.signalEndOfVideoGraphOutputStream();
    }

    public static /* synthetic */ boolean access$1600(PlaybackVideoGraphWrapper r0) {
        return r0.isEnded();
    }

    public static /* synthetic */ boolean access$1702(PlaybackVideoGraphWrapper r0, boolean r1) {
        r0.hasSignaledEndOfVideoGraphOutputStream = r1;
        return r1;
    }

    public static /* synthetic */ TimedValueQueue access$1800(PlaybackVideoGraphWrapper r0) {
        return r0.pendingStreamChanges;
    }

    public static /* synthetic */ TimedValueQueue access$1802(PlaybackVideoGraphWrapper r0, TimedValueQueue r1) {
        r0.pendingStreamChanges = r1;
        return r1;
    }

    public static /* synthetic */ void access$1900(PlaybackVideoGraphWrapper r0) {
        r0.allowReleaseFirstFrameBeforeStarted();
    }

    public static /* synthetic */ void access$2000(PlaybackVideoGraphWrapper r0, VideoFrameMetadataListener r1) {
        r0.setVideoFrameMetadataListener(r1);
    }

    public static /* synthetic */ void access$2100(PlaybackVideoGraphWrapper r0, float r1) {
        r0.setPlaybackSpeed(r1);
    }

    public static /* synthetic */ void access$2200(PlaybackVideoGraphWrapper r0, int r1) {
        r0.setChangeFrameRateStrategy(r1);
    }

    public static /* synthetic */ boolean access$2300(PlaybackVideoGraphWrapper r0) {
        return r0.shouldRenderToInputVideoSink();
    }

    public static /* synthetic */ void access$2400(PlaybackVideoGraphWrapper r0, long r1, long r3) {
        r0.render(r1, r3);
    }

    public static /* synthetic */ void access$2500(PlaybackVideoGraphWrapper r0, boolean r1) {
        r0.joinPlayback(r1);
    }

    public static /* synthetic */ ColorInfo access$2600(PlaybackVideoGraphWrapper r0, ColorInfo r1) {
        return r0.getAdjustedInputColorInfo(r1);
    }

    public static /* synthetic */ VideoGraph access$600(PlaybackVideoGraphWrapper r0) {
        r0.getClass();
        return null;
    }

    public static /* synthetic */ Executor access$800() {
        return NO_OP_EXECUTOR;
    }

    public static /* synthetic */ boolean access$900(PlaybackVideoGraphWrapper r0) {
        return r0.enablePlaylistMode;
    }

    private void allowReleaseFirstFrameBeforeStarted() {
        this.defaultVideoSink.allowReleaseFirstFrameBeforeStarted();
    }

    private void flush(boolean r3) {
        if (isInitialized() == true) goto L5;
        return;
    L5:
        this.pendingFlushCount++;
        this.defaultVideoSink.flush(r3);
    L6:
        int r32 = this.pendingStreamChanges.size();
        TimedValueQueue<StreamChangeInfo> r0 = this.pendingStreamChanges;
        if (r32 <= 1) goto L10;
        r0.pollFirst();
        goto L6
    L10:
        if (r0.size() != 1) goto L12;
        StreamChangeInfo r33 = (StreamChangeInfo) Assertions.checkNotNull(this.pendingStreamChanges.pollFirst());
        this.outputStreamStartPositionUs = r33.startPositionUs;
        this.outputStreamFirstFrameReleaseInstruction = r33.firstFrameReleaseInstruction;
        onOutputStreamChanged();
    L12:
        this.lastOutputFramePresentationTimeUs = -9223372036854775807L;
        this.finalFramePresentationTimeUs = -9223372036854775807L;
        this.hasSignaledEndOfVideoGraphOutputStream = false;
        ((HandlerWrapper) Assertions.checkStateNotNull(this.handler)).post(new PlaybackVideoGraphWrapper$$ExternalSyntheticLambda0(this));
    }

    private ColorInfo getAdjustedInputColorInfo(ColorInfo r2) {
        if (r2 == null) goto L10;
        if (r2.isDataSpaceValid() == false) goto L10;
        if (this.isInputSdrToneMapped == true) goto L10;
        return r2;
    L10:
        return ColorInfo.SDR_BT709_LIMITED;
    }

    private boolean isEnded() {
        if (this.pendingFlushCount == 0) goto L5;
        return false;
    L5:
        if (this.hasSignaledEndOfVideoGraphOutputStream == true) goto L7;
        return false;
    L7:
        if (this.defaultVideoSink.isEnded() == false) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean isInitialized() {
        if (this.state != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private boolean isReady(boolean r2) {
        VideoSink r0 = this.defaultVideoSink;
        if (r2 == true) goto L5;
    L7:
        boolean r1 = false;
    L9:
        return r0.isReady(r1);
    L5:
        if (this.pendingFlushCount != 0) goto L7;
        r1 = true;
        goto L9
    }

    private void joinPlayback(boolean r1) {
        this.defaultVideoSink.join(r1);
    }

    private void maybeSetOutputSurfaceInfo(Surface r1, int r2, int r3) {
    }

    private void onOutputStreamChanged() {
        this.defaultVideoSink.onInputStreamChanged(1, this.videoGraphOutputFormat, this.outputStreamStartPositionUs, this.outputStreamFirstFrameReleaseInstruction, ImmutableList.of());
    }

    private boolean registerInput(Format r12, int r13) {
        boolean r0 = false;
        if (r13 != 0) goto L33;
        if (this.state != 0) goto L7;
        r0 = true;
    L7:
        Assertions.checkState(r0);
        ColorInfo r132 = getAdjustedInputColorInfo(r12.colorInfo);
    L19:
        e = move-exception;
        throw new VideoSink.VideoSinkException(e, r12);
    L9:
        if (this.requestOpenGlToneMapping == false) goto L11;
        r132 = ColorInfo.SDR_BT709_LIMITED;     // Catch: GlUtil.GlException -> L19
    L18:
        ColorInfo r4 = r132;
        HandlerWrapper r122 = this.clock.createHandler((Looper) Assertions.checkStateNotNull(Looper.myLooper()), null);
        this.handler = r122;
        VideoGraph.Factory r2 = this.videoGraphFactory;
        Context r3 = this.context;
        DebugViewProvider r5 = DebugViewProvider.NONE;
        Objects.requireNonNull(r122);
        r2.create(r3, r4, r5, this, new PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2(r122), 0, false);
        throw null;
    L11:
        if (r132.colorTransfer != 7) goto L22;
        if (Build.VERSION.SDK_INT >= 34) goto L22;
        if (GlUtil.isBt2020PqExtensionSupported() == false) goto L22;
        r132 = r132.buildUpon().setColorTransfer(6).build();     // Catch: GlUtil.GlException -> L19
    L22:
        if (GlUtil.isColorTransferSupported(r132.colorTransfer) == true) goto L18;
        if (Build.VERSION.SDK_INT < 29) goto L18;
        Log.w("PlaybackVidGraphWrapper", Util.formatInvariant("Color transfer %d is not supported. Falling back to OpenGl tone mapping.", new Object[]{Integer.valueOf(r132.colorTransfer)}));     // Catch: GlUtil.GlException -> L19
        r132 = ColorInfo.SDR_BT709_LIMITED;     // Catch: GlUtil.GlException -> L19
        goto L18
    L33:
        if (isInitialized() == true) goto L35;
        return false;
    L35:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkNotNull(null));
        throw null;
    }

    private void render(long r1, long r3) {
        this.defaultVideoSink.render(r1, r3);
    }

    private void setChangeFrameRateStrategy(int r1) {
        this.defaultVideoSink.setChangeFrameRateStrategy(r1);
    }

    private void setPlaybackSpeed(float r1) {
        this.defaultVideoSink.setPlaybackSpeed(r1);
    }

    private void setVideoFrameMetadataListener(VideoFrameMetadataListener r1) {
        this.videoFrameMetadataListener = r1;
        this.defaultVideoSink.setVideoFrameMetadataListener(r1);
    }

    private boolean shouldRenderToInputVideoSink() {
        int r0 = this.totalVideoInputCount;
        if (r0 != (-1)) goto L5;
        return false;
    L5:
        if (r0 != this.registeredVideoInputCount) goto L10;
        return true;
    L10:
        return false;
    }

    private void signalEndOfVideoGraphOutputStream() {
        this.defaultVideoSink.signalEndOfCurrentInputStream();
        this.hasSignaledEndOfVideoGraphOutputStream = true;
    }

    public void addListener(Listener r1) {
        this.listeners.add(r1);
    }

    public void clearOutputSurfaceInfo() {
        Size r0 = Size.UNKNOWN;
        maybeSetOutputSurfaceInfo(null, r0.getWidth(), r0.getHeight());
        this.currentSurfaceAndSize = null;
    }

    public VideoSink getSink(int r3) {
        if (Util.contains(this.inputVideoSinks, r3) == true) goto L5;
        InputVideoSink r0 = new InputVideoSink(this, this.context, r3);
        if (r3 != 0) goto L9;
        addListener(r0);
    L9:
        this.inputVideoSinks.put(r3, r0);
        return r0;
    L5:
        return this.inputVideoSinks.get(r3);
    }

    public void release() {
        if (this.state != 2) goto L5;
        return;
    L5:
        HandlerWrapper r0 = this.handler;
        if (r0 == null) goto L8;
        r0.removeCallbacksAndMessages(null);
    L8:
        this.currentSurfaceAndSize = null;
        this.state = 2;
    }

    public void setOutputSurfaceInfo(Surface r2, Size r3) {
        Pair<Surface, Size> r0 = this.currentSurfaceAndSize;
        if (r0 != null) goto L5;
    L9:
        this.currentSurfaceAndSize = Pair.create(r2, r3);
        maybeSetOutputSurfaceInfo(r2, r3.getWidth(), r3.getHeight());
        return;
    L5:
        if (((Surface) r0.first).equals(r2) == false) goto L9;
        if (((Size) this.currentSurfaceAndSize.second).equals(r3) == false) goto L9;
    }

    public void setTotalVideoInputCount(int r1) {
        this.totalVideoInputCount = r1;
    }

    public void startRendering() {
        this.defaultVideoSink.startRendering();
    }

    public void stopRendering() {
        this.defaultVideoSink.stopRendering();
    }
}
