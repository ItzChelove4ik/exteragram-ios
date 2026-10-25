package androidx.media3.exoplayer.video;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.view.Display;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.Timeline;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.ScrubbingModeParameters;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecDecoderException;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.video.PlaybackVideoGraphWrapper;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.VideoSink;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.MoreExecutors;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.PriorityQueue;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.Typography;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.MediaController;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public class MediaCodecVideoRenderer extends MediaCodecRenderer implements VideoFrameReleaseControl.FrameTimingEvaluator {
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = null;
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private final Av1SampleDependencyParser av1SampleDependencyParser;
    private int buffersInCodecCount;
    private int changeFrameRateStrategy;
    private boolean codecHandlesHdr10PlusOutOfBandMetadata;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private int consecutiveDroppedInputBufferCount;
    private final Context context;
    private VideoSize decodedVideoSize;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private Surface displaySurface;
    private final PriorityQueue<Long> droppedDecoderInputBufferTimestamps;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private final boolean enableMediaCodecBufferDecodeOnlyFlag;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private VideoFrameMetadataListener frameMetadataListener;
    private boolean hasSetVideoSink;
    private boolean haveReportedFirstFrameRenderedForCurrentSurface;
    private boolean isFlushRequired;
    private long lastFrameReleaseTimeNs;
    private final int maxDroppedFramesToNotify;
    private final long minEarlyUsToDropDecoderInput;
    private int nextVideoSinkFirstFrameReleaseInstruction;
    private Size outputResolution;
    private final boolean ownsVideoSink;
    private boolean pendingVideoSinkInputStreamChange;
    private long periodDurationUs;
    private PlaceholderSurface placeholderSurface;
    private int rendererPriority;
    private VideoSize reportedVideoSize;
    private int scalingMode;
    private ScrubbingModeParameters scrubbingModeParameters;
    private long startPositionUs;
    private long totalVideoFrameProcessingOffsetUs;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;
    private List<Object> videoEffects;
    private int videoFrameProcessingOffsetCount;
    private final VideoFrameReleaseControl videoFrameReleaseControl;
    private final VideoFrameReleaseEarlyTimeForecaster videoFrameReleaseEarlyTimeForecaster;
    private final VideoFrameReleaseControl.FrameReleaseInfo videoFrameReleaseInfo;
    private VideoSink videoSink;

    /* renamed from: androidx.media3.exoplayer.video.MediaCodecVideoRenderer$1, reason: invalid class name */
    public class AnonymousClass1 implements VideoSink.Listener {
        final /* synthetic */ MediaCodecVideoRenderer this$0;

        public AnonymousClass1(MediaCodecVideoRenderer r1) {
            this.this$0 = r1;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink.Listener
        public void onFirstFrameRendered() {
            if (MediaCodecVideoRenderer.access$1400(this.this$0) == null) goto L6;
            MediaCodecVideoRenderer.access$1500(this.this$0);
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink.Listener
        public void onFrameAvailableForRendering() {
            Renderer.WakeupListener r0 = MediaCodecVideoRenderer.access$1300(this.this$0);
            if (r0 == null) goto L6;
            r0.onWakeup();
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink.Listener
        public void onFrameDropped() {
            if (MediaCodecVideoRenderer.access$1400(this.this$0) == null) goto L6;
            this.this$0.updateDroppedBufferCounters(0, 1);
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink.Listener
        public void onVideoSizeChanged(VideoSize r1) {
        }
    }

    /* renamed from: androidx.media3.exoplayer.video.MediaCodecVideoRenderer$2, reason: invalid class name */
    public class AnonymousClass2 implements VideoSink.VideoFrameHandler {
        final /* synthetic */ MediaCodecVideoRenderer this$0;
        final /* synthetic */ int val$bufferIndex;
        final /* synthetic */ MediaCodecAdapter val$codec;
        final /* synthetic */ long val$presentationTimeUs;

        public AnonymousClass2(MediaCodecVideoRenderer r1, MediaCodecAdapter r2, int r3, long r4) {
            this.this$0 = r1;
            this.val$codec = r2;
            this.val$bufferIndex = r3;
            this.val$presentationTimeUs = r4;
        }

        @Override // androidx.media3.exoplayer.video.VideoSink.VideoFrameHandler
        public void render(long r8) {
            MediaCodecVideoRenderer.access$1800(this.this$0, this.val$codec, this.val$bufferIndex, this.val$presentationTimeUs, r8);
        }

        @Override // androidx.media3.exoplayer.video.VideoSink.VideoFrameHandler
        public void skip() {
            this.this$0.skipOutputBuffer(this.val$codec, this.val$bufferIndex, this.val$presentationTimeUs);
        }
    }

    public static final class Api26 {
        public static boolean doesDisplaySupportDolbyVision(Context r5) {
            DisplayManager r52 = (DisplayManager) r5.getSystemService("display");
            if (r52 == null) goto L5;
            Display r53 = r52.getDisplay(0);
        L6:
            if (r53 != null) goto L8;
        L15:
            return false;
        L8:
            if (r53.isHdr() == false) goto L15;
            int[] r54 = r53.getHdrCapabilities().getSupportedHdrTypes();
            int r1 = r54.length;
            int r2 = 0;
        L10:
            if (r2 >= r1) goto L15;
            if (r54[r2] == 1) goto L13;
            r2 = r2 + 1;
            goto L10
        L13:
            return true;
        L5:
            r53 = null;
            goto L6
        }
    }

    public static final class Builder {
        private long allowedJoiningTimeMs;
        private float assumedMinimumCodecOperatingRate;
        private boolean buildCalled;
        private MediaCodecAdapter.Factory codecAdapterFactory;
        private final Context context;
        private boolean enableDecoderFallback;
        private boolean enableMediaCodecBufferDecodeOnlyFlag;
        private Handler eventHandler;
        private VideoRendererEventListener eventListener;
        private long lateThresholdToDropDecoderInputUs;
        private int maxDroppedFramesToNotify;
        private MediaCodecSelector mediaCodecSelector;
        private boolean parseAv1SampleDependencies;
        private VideoSink videoSink;

        public Builder(Context r3) {
            this.context = r3;
            this.mediaCodecSelector = MediaCodecSelector.DEFAULT;
            this.codecAdapterFactory = MediaCodecAdapter.Factory.getDefault(r3);
            this.assumedMinimumCodecOperatingRate = 30.0f;
            this.lateThresholdToDropDecoderInputUs = -9223372036854775807L;
        }

        public static /* synthetic */ MediaCodecAdapter.Factory access$000(Builder r0) {
            return r0.codecAdapterFactory;
        }

        public static /* synthetic */ MediaCodecSelector access$100(Builder r0) {
            return r0.mediaCodecSelector;
        }

        public static /* synthetic */ boolean access$1000(Builder r0) {
            return r0.parseAv1SampleDependencies;
        }

        public static /* synthetic */ long access$1100(Builder r2) {
            return r2.lateThresholdToDropDecoderInputUs;
        }

        public static /* synthetic */ boolean access$1200(Builder r0) {
            return r0.enableMediaCodecBufferDecodeOnlyFlag;
        }

        public static /* synthetic */ boolean access$200(Builder r0) {
            return r0.enableDecoderFallback;
        }

        public static /* synthetic */ float access$300(Builder r0) {
            return r0.assumedMinimumCodecOperatingRate;
        }

        public static /* synthetic */ Context access$400(Builder r0) {
            return r0.context;
        }

        public static /* synthetic */ int access$500(Builder r0) {
            return r0.maxDroppedFramesToNotify;
        }

        public static /* synthetic */ VideoSink access$600(Builder r0) {
            return r0.videoSink;
        }

        public static /* synthetic */ Handler access$700(Builder r0) {
            return r0.eventHandler;
        }

        public static /* synthetic */ VideoRendererEventListener access$800(Builder r0) {
            return r0.eventListener;
        }

        public static /* synthetic */ long access$900(Builder r2) {
            return r2.allowedJoiningTimeMs;
        }

        public MediaCodecVideoRenderer build() {
            Assertions.checkState(!this.buildCalled);
            Handler r0 = this.eventHandler;
            if (r0 == null) goto L5;
        L6:
            if (r0 != null) goto L8;
        L10:
            boolean r02 = false;
        L11:
            Assertions.checkState(r02);
            this.buildCalled = true;
            return new MediaCodecVideoRenderer(this);
        L8:
            if (this.eventListener == null) goto L10;
        L9:
            r02 = true;
            goto L11
        L5:
            if (this.eventListener == null) goto L9;
            goto L6
        }

        public Builder experimentalSetEnableMediaCodecBufferDecodeOnlyFlag(boolean r1) {
            this.enableMediaCodecBufferDecodeOnlyFlag = r1;
            return this;
        }

        public Builder experimentalSetLateThresholdToDropDecoderInputUs(long r1) {
            this.lateThresholdToDropDecoderInputUs = r1;
            return this;
        }

        public Builder experimentalSetParseAv1SampleDependencies(boolean r1) {
            this.parseAv1SampleDependencies = r1;
            return this;
        }

        public Builder setAllowedJoiningTimeMs(long r1) {
            this.allowedJoiningTimeMs = r1;
            return this;
        }

        public Builder setCodecAdapterFactory(MediaCodecAdapter.Factory r1) {
            this.codecAdapterFactory = r1;
            return this;
        }

        public Builder setEnableDecoderFallback(boolean r1) {
            this.enableDecoderFallback = r1;
            return this;
        }

        public Builder setEventHandler(Handler r1) {
            this.eventHandler = r1;
            return this;
        }

        public Builder setEventListener(VideoRendererEventListener r1) {
            this.eventListener = r1;
            return this;
        }

        public Builder setMaxDroppedFramesToNotify(int r1) {
            this.maxDroppedFramesToNotify = r1;
            return this;
        }

        public Builder setMediaCodecSelector(MediaCodecSelector r1) {
            this.mediaCodecSelector = r1;
            return this;
        }
    }

    public static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int r1, int r2, int r3) {
            this.width = r1;
            this.height = r2;
            this.inputSize = r3;
        }
    }

    public final class OnFrameRenderedListenerV23 implements MediaCodecAdapter.OnFrameRenderedListener, Handler.Callback {
        private final Handler handler;
        final /* synthetic */ MediaCodecVideoRenderer this$0;

        public OnFrameRenderedListenerV23(MediaCodecVideoRenderer r1, MediaCodecAdapter r2) {
            this.this$0 = r1;
            Handler r12 = Util.createHandlerForCurrentLooper(this);
            this.handler = r12;
            r2.setOnFrameRenderedListener(this, r12);
        }

        private void handleFrameRendered(long r3) {
            MediaCodecVideoRenderer r0 = this.this$0;
            if (this == r0.tunnelingOnFrameRenderedListener) goto L5;
            return;
        L5:
            if (MediaCodecVideoRenderer.access$1900(r0) == null) goto L19;
            MediaCodecVideoRenderer r1 = this.this$0;
            if (r3 != LongCompanionObject.MAX_VALUE) goto L16;
            MediaCodecVideoRenderer.access$2000(r1);
            return;
        L16:
            r1.onProcessedTunneledBuffer(r3);     // Catch: ExoPlaybackException -> L13
            return;
        L13:
            e = move-exception;
            MediaCodecVideoRenderer.access$2100(this.this$0, e);
            return;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message r3) {
            if (r3.what == 0) goto L6;
            return false;
        L6:
            handleFrameRendered(Util.toLong(r3.arg1, r3.arg2));
            return true;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.OnFrameRenderedListener
        public void onFrameRendered(MediaCodecAdapter r1, long r2, long r4) {
            if (Build.VERSION.SDK_INT >= 30) goto L6;
            Message r12 = Message.obtain(this.handler, 0, (int) (r2 >> 32), (int) r2);
            this.handler.sendMessageAtFrontOfQueue(r12);
            return;
        L6:
            handleFrameRendered(r2);
        }
    }

    static {
        STANDARD_LONG_EDGE_VIDEO_PX = new int[]{1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    }

    public MediaCodecVideoRenderer(Builder r7) {
        super(2, Builder.access$000(r7), Builder.access$100(r7), Builder.access$200(r7), Builder.access$300(r7));
        Context r6 = Builder.access$400(r7).getApplicationContext();
        this.context = r6;
        this.maxDroppedFramesToNotify = Builder.access$500(r7);
        this.videoSink = Builder.access$600(r7);
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(Builder.access$700(r7), Builder.access$800(r7));
        if (this.videoSink != null) goto L5;
        boolean r1 = true;
    L6:
        this.ownsVideoSink = r1;
        this.videoFrameReleaseControl = new VideoFrameReleaseControl(r6, this, Builder.access$900(r7));
        this.videoFrameReleaseInfo = new VideoFrameReleaseControl.FrameReleaseInfo();
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.outputResolution = Size.UNKNOWN;
        this.scalingMode = 1;
        this.changeFrameRateStrategy = 0;
        this.decodedVideoSize = VideoSize.UNKNOWN;
        this.tunnelingAudioSessionId = 0;
        this.reportedVideoSize = null;
        this.rendererPriority = -1000;
        this.startPositionUs = -9223372036854775807L;
        this.periodDurationUs = -9223372036854775807L;
        if (Builder.access$1000(r7) == false) goto L9;
        Av1SampleDependencyParser r3 = new Av1SampleDependencyParser();
    L10:
        this.av1SampleDependencyParser = r3;
        this.droppedDecoderInputBufferTimestamps = new PriorityQueue();
        if (Builder.access$1100(r7) == (-9223372036854775807L)) goto L13;
        this.minEarlyUsToDropDecoderInput = -Builder.access$1100(r7);
        this.videoFrameReleaseEarlyTimeForecaster = new VideoFrameReleaseEarlyTimeForecaster(1.0f);
    L14:
        this.enableMediaCodecBufferDecodeOnlyFlag = Builder.access$1200(r7);
        this.scrubbingModeParameters = null;
        return;
    L13:
        this.minEarlyUsToDropDecoderInput = -9223372036854775807L;
        this.videoFrameReleaseEarlyTimeForecaster = null;
        goto L14
    L9:
        r3 = null;
        goto L10
    L5:
        r1 = false;
        goto L6
    }

    public static /* synthetic */ Renderer.WakeupListener access$1300(MediaCodecVideoRenderer r0) {
        return r0.getWakeupListener();
    }

    public static /* synthetic */ Surface access$1400(MediaCodecVideoRenderer r0) {
        return r0.displaySurface;
    }

    public static /* synthetic */ void access$1500(MediaCodecVideoRenderer r0) {
        r0.notifyRenderedFirstFrame();
    }

    public static /* synthetic */ void access$1800(MediaCodecVideoRenderer r0, MediaCodecAdapter r1, int r2, long r3, long r5) {
        r0.renderOutputBuffer(r1, r2, r3, r5);
    }

    public static /* synthetic */ MediaCodecAdapter access$1900(MediaCodecVideoRenderer r0) {
        return r0.getCodec();
    }

    public static /* synthetic */ void access$2000(MediaCodecVideoRenderer r0) {
        r0.onProcessedTunneledEndOfStream();
    }

    public static /* synthetic */ void access$2100(MediaCodecVideoRenderer r0, ExoPlaybackException r1) {
        r0.setPendingPlaybackException(r1);
    }

    @RequiresNonNull({"videoSink"})
    private void configureVideoSink() {
        this.videoSink.setListener(new AnonymousClass1(this), MoreExecutors.directExecutor());
        VideoFrameMetadataListener r0 = this.frameMetadataListener;
        if (r0 == null) goto L6;
        this.videoSink.setVideoFrameMetadataListener(r0);
    L6:
        if (this.displaySurface != null) goto L8;
    L10:
        this.videoSink.setChangeFrameRateStrategy(this.changeFrameRateStrategy);
        this.videoSink.setPlaybackSpeed(getPlaybackSpeed());
        List<Object> r02 = this.videoEffects;
        if (r02 == null) goto L14;
        this.videoSink.setVideoEffects(r02);
        return;
    L14:
        return;
    L8:
        if (this.outputResolution.equals(Size.UNKNOWN) == true) goto L10;
        this.videoSink.setOutputSurfaceInfo(this.displaySurface, this.outputResolution);
        goto L10
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Build.MANUFACTURER);
    }

    private static boolean evaluateDeviceNeedsSetOutputSurfaceWorkaround() {
        int r0 = Build.VERSION.SDK_INT;
        char r1 = 7;
        if (r0 > 28) goto L43;
        String r11 = Build.DEVICE;
        r11.getClass();
        switch(r11.hashCode()) {
            case -1339091551: goto L36;
            case -1220081023: goto L32;
            case -1220066608: goto L28;
            case -1012436106: goto L24;
            case -760312546: goto L20;
            case -64886864: goto L16;
            case 3415681: goto L12;
            case 825323514: goto L8;
            default: goto L6;
        };
    L6:
        boolean r112 = -1;
    L39:
        switch(r112) {
            case 0: goto L41;
            case 1: goto L41;
            case 2: goto L41;
            case 3: goto L41;
            case 4: goto L41;
            case 5: goto L41;
            case 6: goto L41;
            case 7: goto L41;
            default: goto L43;
        };
    L41:
        return true;
    L8:
        if (r11.equals("machuca") == false) goto L6;
        r112 = 7;
        goto L39
    L12:
        if (r11.equals("once") == false) goto L6;
        r112 = 6;
        goto L39
    L16:
        if (r11.equals("magnolia") == false) goto L6;
        r112 = 5;
        goto L39
    L20:
        if (r11.equals("aquaman") == false) goto L6;
        r112 = 4;
        goto L39
    L24:
        if (r11.equals("oneday") == false) goto L6;
        r112 = 3;
        goto L39
    L28:
        if (r11.equals("dangalUHD") == false) goto L6;
        r112 = 2;
        goto L39
    L32:
        if (r11.equals("dangalFHD") == false) goto L6;
        r112 = true;
        goto L39
    L36:
        if (r11.equals("dangal") == false) goto L6;
        r112 = false;
    L43:
        if (r0 <= 27) goto L45;
    L47:
        String r12 = Build.MODEL;
        r12.getClass();
        switch(r12.hashCode()) {
            case -349662828: goto L83;
            case -321033677: goto L79;
            case 2006354: goto L75;
            case 2006367: goto L71;
            case 2006371: goto L67;
            case 1785421873: goto L63;
            case 1785421876: goto L59;
            case 1798172390: goto L55;
            case 2119412532: goto L51;
            default: goto L49;
        };
    L49:
        boolean r13 = -1;
    L86:
        switch(r13) {
            case 0: goto L657;
            case 1: goto L657;
            case 2: goto L657;
            case 3: goto L657;
            case 4: goto L657;
            case 5: goto L657;
            case 6: goto L657;
            case 7: goto L657;
            case 8: goto L657;
            default: goto L88;
        };
    L657:
        return true;
    L88:
        if (r0 > 26) goto L656;
        String r02 = Build.DEVICE;
        r02.getClass();
        switch(r02.hashCode()) {
            case -2144781245: goto L648;
            case -2144781185: goto L644;
            case -2144781160: goto L640;
            case -2097309513: goto L636;
            case -2022874474: goto L632;
            case -1978993182: goto L628;
            case -1978990237: goto L624;
            case -1936688988: goto L621;
            case -1936688066: goto L617;
            case -1936688065: goto L613;
            case -1931988508: goto L609;
            case -1885099851: goto L605;
            case -1696512866: goto L601;
            case -1680025915: goto L597;
            case -1615810839: goto L593;
            case -1600724499: goto L589;
            case -1554255044: goto L585;
            case -1481772737: goto L581;
            case -1481772730: goto L577;
            case -1481772729: goto L573;
            case -1320080169: goto L569;
            case -1217592143: goto L565;
            case -1180384755: goto L561;
            case -1139198265: goto L557;
            case -1052835013: goto L553;
            case -993250464: goto L549;
            case -993250458: goto L545;
            case -965403638: goto L541;
            case -958336948: goto L537;
            case -879245230: goto L533;
            case -842500323: goto L529;
            case -821392978: goto L525;
            case -797483286: goto L521;
            case -794946968: goto L517;
            case -788334647: goto L513;
            case -782144577: goto L509;
            case -575125681: goto L505;
            case -521118391: goto L501;
            case -430914369: goto L497;
            case -290434366: goto L493;
            case -282781963: goto L489;
            case -277133239: goto L485;
            case -173639913: goto L481;
            case -56598463: goto L477;
            case 2126: goto L473;
            case 2564: goto L469;
            case 2715: goto L465;
            case 2719: goto L461;
            case 3091: goto L457;
            case 3483: goto L453;
            case 73405: goto L449;
            case 75537: goto L445;
            case 75739: goto L441;
            case 76779: goto L437;
            case 78669: goto L433;
            case 79305: goto L429;
            case 80618: goto L425;
            case 88274: goto L421;
            case 98846: goto L417;
            case 98848: goto L413;
            case 99329: goto L409;
            case 101481: goto L405;
            case 1513190: goto L401;
            case 1514184: goto L397;
            case 1514185: goto L393;
            case 2133089: goto L389;
            case 2133091: goto L385;
            case 2133120: goto L381;
            case 2133151: goto L377;
            case 2133182: goto L373;
            case 2133184: goto L369;
            case 2436959: goto L365;
            case 2463773: goto L361;
            case 2464648: goto L357;
            case 2689555: goto L353;
            case 3154429: goto L349;
            case 3284551: goto L345;
            case 3351335: goto L341;
            case 3386211: goto L337;
            case 41325051: goto L333;
            case 51349633: goto L329;
            case 51350594: goto L325;
            case 55178625: goto L321;
            case 61542055: goto L317;
            case 65355429: goto L313;
            case 66214468: goto L309;
            case 66214470: goto L305;
            case 66214473: goto L301;
            case 66215429: goto L297;
            case 66215431: goto L293;
            case 66215433: goto L289;
            case 66216390: goto L285;
            case 76402249: goto L281;
            case 76404105: goto L277;
            case 76404911: goto L273;
            case 80963634: goto L269;
            case 82882791: goto L265;
            case 98715550: goto L261;
            case 101370885: goto L257;
            case 102844228: goto L253;
            case 165221241: goto L249;
            case 182191441: goto L245;
            case 245388979: goto L241;
            case 287431619: goto L237;
            case 307593612: goto L233;
            case 308517133: goto L229;
            case 316215098: goto L225;
            case 316215116: goto L221;
            case 316246811: goto L217;
            case 316246818: goto L213;
            case 407160593: goto L209;
            case 507412548: goto L205;
            case 793982701: goto L201;
            case 794038622: goto L197;
            case 794040393: goto L193;
            case 835649806: goto L189;
            case 917340916: goto L185;
            case 958008161: goto L181;
            case 1060579533: goto L177;
            case 1150207623: goto L173;
            case 1176899427: goto L169;
            case 1280332038: goto L165;
            case 1306947716: goto L161;
            case 1349174697: goto L157;
            case 1522194893: goto L153;
            case 1691543273: goto L149;
            case 1691544261: goto L145;
            case 1709443163: goto L141;
            case 1865889110: goto L137;
            case 1906253259: goto L133;
            case 1977196784: goto L129;
            case 2006372676: goto L125;
            case 2019281702: goto L121;
            case 2029784656: goto L117;
            case 2030379515: goto L113;
            case 2033393791: goto L109;
            case 2047190025: goto L105;
            case 2047252157: goto L101;
            case 2048319463: goto L97;
            case 2048855701: goto L93;
            default: goto L91;
        };
    L91:
        r1 = 65535;
    L651:
        switch(r1) {
            case 0: goto L655;
            case 1: goto L655;
            case 2: goto L655;
            case 3: goto L655;
            case 4: goto L655;
            case 5: goto L655;
            case 6: goto L655;
            case 7: goto L655;
            case 8: goto L655;
            case 9: goto L655;
            case 10: goto L655;
            case 11: goto L655;
            case 12: goto L655;
            case 13: goto L655;
            case 14: goto L655;
            case 15: goto L655;
            case 16: goto L655;
            case 17: goto L655;
            case 18: goto L655;
            case 19: goto L655;
            case 20: goto L655;
            case 21: goto L655;
            case 22: goto L655;
            case 23: goto L655;
            case 24: goto L655;
            case 25: goto L655;
            case 26: goto L655;
            case 27: goto L655;
            case 28: goto L655;
            case 29: goto L655;
            case 30: goto L655;
            case 31: goto L655;
            case 32: goto L655;
            case 33: goto L655;
            case 34: goto L655;
            case 35: goto L655;
            case 36: goto L655;
            case 37: goto L655;
            case 38: goto L655;
            case 39: goto L655;
            case 40: goto L655;
            case 41: goto L655;
            case 42: goto L655;
            case 43: goto L655;
            case 44: goto L655;
            case 45: goto L655;
            case 46: goto L655;
            case 47: goto L655;
            case 48: goto L655;
            case 49: goto L655;
            case 50: goto L655;
            case 51: goto L655;
            case 52: goto L655;
            case 53: goto L655;
            case 54: goto L655;
            case 55: goto L655;
            case 56: goto L655;
            case 57: goto L655;
            case 58: goto L655;
            case 59: goto L655;
            case 60: goto L655;
            case 61: goto L655;
            case 62: goto L655;
            case 63: goto L655;
            case 64: goto L655;
            case 65: goto L655;
            case 66: goto L655;
            case 67: goto L655;
            case 68: goto L655;
            case 69: goto L655;
            case 70: goto L655;
            case 71: goto L655;
            case 72: goto L655;
            case 73: goto L655;
            case 74: goto L655;
            case 75: goto L655;
            case 76: goto L655;
            case 77: goto L655;
            case 78: goto L655;
            case 79: goto L655;
            case 80: goto L655;
            case 81: goto L655;
            case 82: goto L655;
            case 83: goto L655;
            case 84: goto L655;
            case 85: goto L655;
            case 86: goto L655;
            case 87: goto L655;
            case 88: goto L655;
            case 89: goto L655;
            case 90: goto L655;
            case 91: goto L655;
            case 92: goto L655;
            case 93: goto L655;
            case 94: goto L655;
            case 95: goto L655;
            case 96: goto L655;
            case 97: goto L655;
            case 98: goto L655;
            case 99: goto L655;
            case 100: goto L655;
            case 101: goto L655;
            case 102: goto L655;
            case 103: goto L655;
            case 104: goto L655;
            case 105: goto L655;
            case 106: goto L655;
            case 107: goto L655;
            case 108: goto L655;
            case 109: goto L655;
            case 110: goto L655;
            case 111: goto L655;
            case 112: goto L655;
            case 113: goto L655;
            case 114: goto L655;
            case 115: goto L655;
            case 116: goto L655;
            case 117: goto L655;
            case 118: goto L655;
            case 119: goto L655;
            case 120: goto L655;
            case 121: goto L655;
            case 122: goto L655;
            case 123: goto L655;
            case 124: goto L655;
            case 125: goto L655;
            case 126: goto L655;
            case 127: goto L655;
            case 128: goto L655;
            case 129: goto L655;
            case 130: goto L655;
            case 131: goto L655;
            case 132: goto L655;
            case 133: goto L655;
            case 134: goto L655;
            case 135: goto L655;
            case 136: goto L655;
            case 137: goto L655;
            case 138: goto L655;
            case 139: goto L655;
            default: goto L652;
        };
    L652:
        r12.getClass();
        if (r12.equals("JSN-L21") == false) goto L656;
    L655:
        return true;
    L93:
        if (r02.equals("HWWAS-H") == false) goto L91;
        r1 = 139;
        goto L651
    L97:
        if (r02.equals("HWVNS-H") == false) goto L91;
        r1 = 138;
        goto L651
    L101:
        if (r02.equals("ELUGA_Prim") == false) goto L91;
        r1 = 137;
        goto L651
    L105:
        if (r02.equals("ELUGA_Note") == false) goto L91;
        r1 = 136;
        goto L651
    L109:
        if (r02.equals("ASUS_X00AD_2") == false) goto L91;
        r1 = 135;
        goto L651
    L113:
        if (r02.equals("HWCAM-H") == false) goto L91;
        r1 = 134;
        goto L651
    L117:
        if (r02.equals("HWBLN-H") == false) goto L91;
        r1 = 133;
        goto L651
    L121:
        if (r02.equals("DM-01K") == false) goto L91;
        r1 = 132;
        goto L651
    L125:
        if (r02.equals("BRAVIA_ATV3_4K") == false) goto L91;
        r1 = 131;
        goto L651
    L129:
        if (r02.equals("Infinix-X572") == false) goto L91;
        r1 = 130;
        goto L651
    L133:
        if (r02.equals("PB2-670M") == false) goto L91;
        r1 = 129;
        goto L651
    L137:
        if (r02.equals("santoni") == false) goto L91;
        r1 = 128;
        goto L651
    L141:
        if (r02.equals("iball8735_9806") == false) goto L91;
        r1 = 127;
        goto L651
    L145:
        if (r02.equals("CPH1715") == false) goto L91;
        r1 = '~';
        goto L651
    L149:
        if (r02.equals("CPH1609") == false) goto L91;
        r1 = '}';
        goto L651
    L153:
        if (r02.equals("woods_f") == false) goto L91;
        r1 = '|';
        goto L651
    L157:
        if (r02.equals("htc_e56ml_dtul") == false) goto L91;
        r1 = '{';
        goto L651
    L161:
        if (r02.equals("EverStar_S") == false) goto L91;
        r1 = 'z';
        goto L651
    L165:
        if (r02.equals("hwALE-H") == false) goto L91;
        r1 = 'y';
        goto L651
    L169:
        if (r02.equals("itel_S41") == false) goto L91;
        r1 = 'x';
        goto L651
    L173:
        if (r02.equals("LS-5017") == false) goto L91;
        r1 = 'w';
        goto L651
    L177:
        if (r02.equals("panell_d") == false) goto L91;
        r1 = 'v';
        goto L651
    L181:
        if (r02.equals("j2xlteins") == false) goto L91;
        r1 = 'u';
        goto L651
    L185:
        if (r02.equals("A7000plus") == false) goto L91;
        r1 = 't';
        goto L651
    L189:
        if (r02.equals("manning") == false) goto L91;
        r1 = 's';
        goto L651
    L193:
        if (r02.equals("GIONEE_WBL7519") == false) goto L91;
        r1 = 'r';
        goto L651
    L197:
        if (r02.equals("GIONEE_WBL7365") == false) goto L91;
        r1 = 'q';
        goto L651
    L201:
        if (r02.equals("GIONEE_WBL5708") == false) goto L91;
        r1 = 'p';
        goto L651
    L205:
        if (r02.equals("QM16XE_U") == false) goto L91;
        r1 = 'o';
        goto L651
    L209:
        if (r02.equals("Pixi5-10_4G") == false) goto L91;
        r1 = 'n';
        goto L651
    L213:
        if (r02.equals("TB3-850M") == false) goto L91;
        r1 = 'm';
        goto L651
    L217:
        if (r02.equals("TB3-850F") == false) goto L91;
        r1 = 'l';
        goto L651
    L221:
        if (r02.equals("TB3-730X") == false) goto L91;
        r1 = 'k';
        goto L651
    L225:
        if (r02.equals("TB3-730F") == false) goto L91;
        r1 = 'j';
        goto L651
    L229:
        if (r02.equals("A7020a48") == false) goto L91;
        r1 = 'i';
        goto L651
    L233:
        if (r02.equals("A7010a48") == false) goto L91;
        r1 = 'h';
        goto L651
    L237:
        if (r02.equals("griffin") == false) goto L91;
        r1 = 'g';
        goto L651
    L241:
        if (r02.equals("marino_f") == false) goto L91;
        r1 = 'f';
        goto L651
    L245:
        if (r02.equals("CPY83_I00") == false) goto L91;
        r1 = 'e';
        goto L651
    L249:
        if (r02.equals("A2016a40") == false) goto L91;
        r1 = 'd';
        goto L651
    L253:
        if (r02.equals("le_x6") == false) goto L91;
        r1 = 'c';
        goto L651
    L257:
        if (r02.equals("l5460") == false) goto L91;
        r1 = 'b';
        goto L651
    L261:
        if (r02.equals("i9031") == false) goto L91;
        r1 = 'a';
        goto L651
    L265:
        if (r02.equals("X3_HK") == false) goto L91;
        r1 = '`';
        goto L651
    L269:
        if (r02.equals("V23GB") == false) goto L91;
        r1 = '_';
        goto L651
    L273:
        if (r02.equals("Q4310") == false) goto L91;
        r1 = '^';
        goto L651
    L277:
        if (r02.equals("Q4260") == false) goto L91;
        r1 = ']';
        goto L651
    L281:
        if (r02.equals("PRO7S") == false) goto L91;
        r1 = '\\';
        goto L651
    L285:
        if (r02.equals("F3311") == false) goto L91;
        r1 = '[';
        goto L651
    L289:
        if (r02.equals("F3215") == false) goto L91;
        r1 = 'Z';
        goto L651
    L293:
        if (r02.equals("F3213") == false) goto L91;
        r1 = 'Y';
        goto L651
    L297:
        if (r02.equals("F3211") == false) goto L91;
        r1 = 'X';
        goto L651
    L301:
        if (r02.equals("F3116") == false) goto L91;
        r1 = 'W';
        goto L651
    L305:
        if (r02.equals("F3113") == false) goto L91;
        r1 = 'V';
        goto L651
    L309:
        if (r02.equals("F3111") == false) goto L91;
        r1 = 'U';
        goto L651
    L313:
        if (r02.equals("E5643") == false) goto L91;
        r1 = 'T';
        goto L651
    L317:
        if (r02.equals("A1601") == false) goto L91;
        r1 = 'S';
        goto L651
    L321:
        if (r02.equals("Aura_Note_2") == false) goto L91;
        r1 = 'R';
        goto L651
    L325:
        if (r02.equals("602LV") == false) goto L91;
        r1 = 'Q';
        goto L651
    L329:
        if (r02.equals("601LV") == false) goto L91;
        r1 = 'P';
        goto L651
    L333:
        if (r02.equals("MEIZU_M5") == false) goto L91;
        r1 = 'O';
        goto L651
    L337:
        if (r02.equals("p212") == false) goto L91;
        r1 = 'N';
        goto L651
    L341:
        if (r02.equals("mido") == false) goto L91;
        r1 = 'M';
        goto L651
    L345:
        if (r02.equals("kate") == false) goto L91;
        r1 = 'L';
        goto L651
    L349:
        if (r02.equals("fugu") == false) goto L91;
        r1 = 'K';
        goto L651
    L353:
        if (r02.equals("XE2X") == false) goto L91;
        r1 = 'J';
        goto L651
    L357:
        if (r02.equals("Q427") == false) goto L91;
        r1 = 'I';
        goto L651
    L361:
        if (r02.equals("Q350") == false) goto L91;
        r1 = 'H';
        goto L651
    L365:
        if (r02.equals("P681") == false) goto L91;
        r1 = 'G';
        goto L651
    L369:
        if (r02.equals("F04J") == false) goto L91;
        r1 = 'F';
        goto L651
    L373:
        if (r02.equals("F04H") == false) goto L91;
        r1 = 'E';
        goto L651
    L377:
        if (r02.equals("F03H") == false) goto L91;
        r1 = 'D';
        goto L651
    L381:
        if (r02.equals("F02H") == false) goto L91;
        r1 = 'C';
        goto L651
    L385:
        if (r02.equals("F01J") == false) goto L91;
        r1 = 'B';
        goto L651
    L389:
        if (r02.equals("F01H") == false) goto L91;
        r1 = 'A';
        goto L651
    L393:
        if (r02.equals("1714") == false) goto L91;
        r1 = '@';
        goto L651
    L397:
        if (r02.equals("1713") == false) goto L91;
        r1 = '?';
        goto L651
    L401:
        if (r02.equals("1601") == false) goto L91;
        r1 = Typography.greater;
        goto L651
    L405:
        if (r02.equals("flo") == false) goto L91;
        r1 = SignatureVisitor.INSTANCEOF;
        goto L651
    L409:
        if (r02.equals("deb") == false) goto L91;
        r1 = Typography.less;
        goto L651
    L413:
        if (r02.equals("cv3") == false) goto L91;
        r1 = ';';
        goto L651
    L417:
        if (r02.equals("cv1") == false) goto L91;
        r1 = ':';
        goto L651
    L421:
        if (r02.equals("Z80") == false) goto L91;
        r1 = '9';
        goto L651
    L425:
        if (r02.equals("QX1") == false) goto L91;
        r1 = '8';
        goto L651
    L429:
        if (r02.equals("PLE") == false) goto L91;
        r1 = '7';
        goto L651
    L433:
        if (r02.equals("P85") == false) goto L91;
        r1 = '6';
        goto L651
    L437:
        if (r02.equals("MX6") == false) goto L91;
        r1 = '5';
        goto L651
    L441:
        if (r02.equals("M5c") == false) goto L91;
        r1 = '4';
        goto L651
    L445:
        if (r02.equals("M04") == false) goto L91;
        r1 = '3';
        goto L651
    L449:
        if (r02.equals("JGZ") == false) goto L91;
        r1 = '2';
        goto L651
    L453:
        if (r02.equals("mh") == false) goto L91;
        r1 = '1';
        goto L651
    L457:
        if (r02.equals("b5") == false) goto L91;
        r1 = '0';
        goto L651
    L461:
        if (r02.equals("V5") == false) goto L91;
        r1 = '/';
        goto L651
    L465:
        if (r02.equals("V1") == false) goto L91;
        r1 = '.';
        goto L651
    L469:
        if (r02.equals("Q5") == false) goto L91;
        r1 = SignatureVisitor.SUPER;
        goto L651
    L473:
        if (r02.equals("C1") == false) goto L91;
        r1 = ',';
        goto L651
    L477:
        if (r02.equals("woods_fn") == false) goto L91;
        r1 = SignatureVisitor.EXTENDS;
        goto L651
    L481:
        if (r02.equals("ELUGA_A3_Pro") == false) goto L91;
        r1 = '*';
        goto L651
    L485:
        if (r02.equals("Z12_PRO") == false) goto L91;
        r1 = ')';
        goto L651
    L489:
        if (r02.equals("BLACK-1X") == false) goto L91;
        r1 = '(';
        goto L651
    L493:
        if (r02.equals("taido_row") == false) goto L91;
        r1 = '\'';
        goto L651
    L497:
        if (r02.equals("Pixi4-7_3G") == false) goto L91;
        r1 = Typography.amp;
        goto L651
    L501:
        if (r02.equals("GIONEE_GBL7360") == false) goto L91;
        r1 = '%';
        goto L651
    L505:
        if (r02.equals("GiONEE_CBL7513") == false) goto L91;
        r1 = Typography.dollar;
        goto L651
    L509:
        if (r02.equals("OnePlus5T") == false) goto L91;
        r1 = '#';
        goto L651
    L513:
        if (r02.equals("whyred") == false) goto L91;
        r1 = Typography.quote;
        goto L651
    L517:
        if (r02.equals("watson") == false) goto L91;
        r1 = '!';
        goto L651
    L521:
        if (r02.equals("SVP-DTV15") == false) goto L91;
        r1 = ' ';
        goto L651
    L525:
        if (r02.equals("A7000-a") == false) goto L91;
        r1 = 31;
        goto L651
    L529:
        if (r02.equals("nicklaus_f") == false) goto L91;
        r1 = 30;
        goto L651
    L533:
        if (r02.equals("tcl_eu") == false) goto L91;
        r1 = 29;
        goto L651
    L537:
        if (r02.equals("ELUGA_Ray_X") == false) goto L91;
        r1 = 28;
        goto L651
    L541:
        if (r02.equals("s905x018") == false) goto L91;
        r1 = 27;
        goto L651
    L545:
        if (r02.equals("A10-70L") == false) goto L91;
        r1 = 26;
        goto L651
    L549:
        if (r02.equals("A10-70F") == false) goto L91;
        r1 = 25;
        goto L651
    L553:
        if (r02.equals("namath") == false) goto L91;
        r1 = 24;
        goto L651
    L557:
        if (r02.equals("Slate_Pro") == false) goto L91;
        r1 = 23;
        goto L651
    L561:
        if (r02.equals("iris60") == false) goto L91;
        r1 = 22;
        goto L651
    L565:
        if (r02.equals("BRAVIA_ATV2") == false) goto L91;
        r1 = 21;
        goto L651
    L569:
        if (r02.equals("GiONEE_GBL7319") == false) goto L91;
        r1 = 20;
        goto L651
    L573:
        if (r02.equals("panell_dt") == false) goto L91;
        r1 = 19;
        goto L651
    L577:
        if (r02.equals("panell_ds") == false) goto L91;
        r1 = 18;
        goto L651
    L581:
        if (r02.equals("panell_dl") == false) goto L91;
        r1 = 17;
        goto L651
    L585:
        if (r02.equals("vernee_M5") == false) goto L91;
        r1 = 16;
        goto L651
    L589:
        if (r02.equals("pacificrim") == false) goto L91;
        r1 = 15;
        goto L651
    L593:
        if (r02.equals("Phantom6") == false) goto L91;
        r1 = 14;
        goto L651
    L597:
        if (r02.equals("ComioS1") == false) goto L91;
        r1 = '\r';
        goto L651
    L601:
        if (r02.equals("XT1663") == false) goto L91;
        r1 = '\f';
        goto L651
    L605:
        if (r02.equals("RAIJIN") == false) goto L91;
        r1 = 11;
        goto L651
    L609:
        if (r02.equals("AquaPowerM") == false) goto L91;
        r1 = '\n';
        goto L651
    L613:
        if (r02.equals("PGN611") == false) goto L91;
        r1 = '\t';
        goto L651
    L617:
        if (r02.equals("PGN610") == false) goto L91;
        r1 = '\b';
        goto L651
    L621:
        if (r02.equals("PGN528") == true) goto L651;
    L624:
        if (r02.equals("NX573J") == false) goto L91;
        r1 = 6;
        goto L651
    L628:
        if (r02.equals("NX541J") == false) goto L91;
        r1 = 5;
        goto L651
    L632:
        if (r02.equals("CP8676_I02") == false) goto L91;
        r1 = 4;
        goto L651
    L636:
        if (r02.equals("K50a40") == false) goto L91;
        r1 = 3;
        goto L651
    L640:
        if (r02.equals("GIONEE_SWW1631") == false) goto L91;
        r1 = 2;
        goto L651
    L644:
        if (r02.equals("GIONEE_SWW1627") == false) goto L91;
        r1 = 1;
        goto L651
    L648:
        if (r02.equals("GIONEE_SWW1609") == false) goto L91;
        r1 = 0;
    L656:
        return false;
    L51:
        if (r12.equals("AFTEUFF014") == false) goto L49;
        r13 = 8;
        goto L86
    L55:
        if (r12.equals("AFTSO001") == false) goto L49;
        r13 = 7;
        goto L86
    L59:
        if (r12.equals("AFTEU014") == false) goto L49;
        r13 = 6;
        goto L86
    L63:
        if (r12.equals("AFTEU011") == false) goto L49;
        r13 = 5;
        goto L86
    L67:
        if (r12.equals("AFTR") == false) goto L49;
        r13 = 4;
        goto L86
    L71:
        if (r12.equals("AFTN") == false) goto L49;
        r13 = 3;
        goto L86
    L75:
        if (r12.equals("AFTA") == false) goto L49;
        r13 = 2;
        goto L86
    L79:
        if (r12.equals("AFTKMST12") == false) goto L49;
        r13 = true;
        goto L86
    L83:
        if (r12.equals("AFTJMST12") == false) goto L49;
        r13 = false;
        goto L86
    L45:
        if ("HWEML".equals(Build.DEVICE) == false) goto L47;
        return true;
    }

    public static int getCodecMaxInputSize(MediaCodecInfo r10, Format r11) {
        int r0 = r11.width;
        int r1 = r11.height;
        if (r0 == (-1)) goto L72;
        if (r1 == (-1)) goto L72;
        String r3 = (String) Assertions.checkNotNull(r11.sampleMimeType);
        char r7 = 1;
        if ("video/dolby-vision".equals(r3) == false) goto L20;
        Pair<Integer, Integer> r112 = MediaCodecUtil.getCodecProfileAndLevel(r11);
        if (r112 == null) goto L19;
        int r113 = ((Integer) r112.first).intValue();
        if (r113 == 512) goto L18;
        if (r113 == 1) goto L18;
        if (r113 == 2) goto L18;
        if (r113 != 1024) goto L19;
        r3 = "video/av01";
    L18:
        r3 = MediaController.VIDEO_MIME_TYPE;
    L19:
        r3 = "video/hevc";
    L20:
        r3.getClass();
        switch(r3.hashCode()) {
            case -1664118616: goto L47;
            case -1662735862: goto L44;
            case -1662541442: goto L40;
            case 1187890754: goto L36;
            case 1331836730: goto L32;
            case 1599127256: goto L28;
            case 1599127257: goto L24;
            default: goto L22;
        };
    L22:
        r7 = 65535;
    L50:
        switch(r7) {
            case 0: goto L71;
            case 1: goto L71;
            case 2: goto L69;
            case 3: goto L71;
            case 4: goto L54;
            case 5: goto L71;
            case 6: goto L53;
            default: goto L51;
        };
    L51:
        return -1;
    L54:
        String r114 = Build.MODEL;
        if ("BRAVIA 4K 2015".equals(r114) == false) goto L57;
    L67:
        return -1;
    L57:
        if ("Amazon".equals(Build.MANUFACTURER) == false) goto L66;
        if ("KFSOWI".equals(r114) == true) goto L67;
        if ("AFTS".equals(r114) == false) goto L66;
        if (r10.secure == true) goto L67;
    L66:
        return getMaxSampleSize((Util.ceilDivide(r0, 16) * Util.ceilDivide(r1, 16)) * 256, 2);
    L53:
        return getMaxSampleSize(r0 * r1, 4);
    L69:
        return Math.max(TLObject.FLAG_21, getMaxSampleSize(r0 * r1, 2));
    L71:
        return getMaxSampleSize(r0 * r1, 2);
    L24:
        if (r3.equals("video/x-vnd.on2.vp9") == false) goto L22;
        r7 = 6;
        goto L50
    L28:
        if (r3.equals("video/x-vnd.on2.vp8") == false) goto L22;
        r7 = 5;
        goto L50
    L32:
        if (r3.equals(MediaController.VIDEO_MIME_TYPE) == false) goto L22;
        r7 = 4;
        goto L50
    L36:
        if (r3.equals("video/mp4v-es") == false) goto L22;
        r7 = 3;
        goto L50
    L40:
        if (r3.equals("video/hevc") == false) goto L22;
        r7 = 2;
        goto L50
    L44:
        if (r3.equals("video/av01") == true) goto L50;
    L47:
        if (r3.equals("video/3gpp") == false) goto L22;
        r7 = 0;
    L72:
        return -1;
    }

    private static Point getCodecMaxSize(MediaCodecInfo r13, Format r14) {
        int r0 = r14.height;
        int r1 = r14.width;
        int r2 = 0;
        if (r0 <= r1) goto L5;
        boolean r3 = true;
    L6:
        if (r3 == false) goto L8;
        int r4 = r0;
    L9:
        if (r3 == false) goto L11;
        r0 = r1;
    L11:
        float r12 = r0 / r4;
        int[] r5 = STANDARD_LONG_EDGE_VIDEO_PX;
        int r6 = r5.length;
    L13:
        if (r2 >= r6) goto L30;
        int r8 = r5[r2];
        int r9 = (int) (r8 * r12);
        if (r8 <= r4) goto L30;
        if (r9 <= r0) goto L30;
        if (r3 == false) goto L20;
        int r7 = r9;
    L21:
        if (r3 == true) goto L24;
        r8 = r9;
    L24:
        Point r72 = r13.alignVideoSizeV21(r7, r8);
        float r82 = r14.frameRate;
        if (r72 == null) goto L29;
        if (r13.isVideoSizeAndRateSupportedV21(r72.x, r72.y, r82) == false) goto L29;
        return r72;
    L29:
        r2 = r2 + 1;
        goto L13
    L20:
        r7 = r8;
    L30:
        return null;
    L8:
        r4 = r1;
        goto L9
    L5:
        r3 = false;
        goto L6
    }

    private static List<MediaCodecInfo> getDecoderInfos(Context r3, MediaCodecSelector r4, Format r5, boolean r6, boolean r7) {
        String r0 = r5.sampleMimeType;
        if (r0 != null) goto L7;
        return ImmutableList.of();
    L7:
        if (Build.VERSION.SDK_INT < 26) goto L16;
        if ("video/dolby-vision".equals(r0) == false) goto L16;
        if (Api26.doesDisplaySupportDolbyVision(r3) == true) goto L16;
        List<MediaCodecInfo> r32 = MediaCodecUtil.getAlternativeDecoderInfos(r4, r5, r6, r7);
        if (r32.isEmpty() == true) goto L16;
        return r32;
    L16:
        return MediaCodecUtil.getDecoderInfosSoftMatch(r4, r5, r6, r7);
    }

    public static int getMaxInputSize(MediaCodecInfo r3, Format r4) {
        if (r4.maxInputSize == (-1)) goto L10;
        int r32 = r4.initializationData.size();
        int r0 = 0;
        int r1 = 0;
    L5:
        if (r0 >= r32) goto L8;
        r1 = r1 + r4.initializationData.get(r0).length;
        r0 = r0 + 1;
        goto L5
    L8:
        return r4.maxInputSize + r1;
    L10:
        return getCodecMaxInputSize(r3, r4);
    }

    private static int getMaxSampleSize(int r0, int r1) {
        return (r0 * 3) / (r1 * 2);
    }

    private Surface getSurfaceForCodec(MediaCodecInfo r3) {
        VideoSink r0 = this.videoSink;
        if (r0 != null) goto L5;
        Surface r02 = this.displaySurface;
        if (r02 == null) goto L10;
        return r02;
    L10:
        if (shouldUseDetachedSurface(r3) == false) goto L13;
        return null;
    L13:
        Assertions.checkState(shouldUsePlaceholderSurface(r3));
        PlaceholderSurface r03 = this.placeholderSurface;
        if (r03 == null) goto L19;
        if (r03.secure == r3.secure) goto L19;
        releasePlaceholderSurface();
    L19:
        if (this.placeholderSurface != null) goto L22;
        this.placeholderSurface = PlaceholderSurface.newInstance(this.context, r3.secure);
    L22:
        return this.placeholderSurface;
    L5:
        return r0.getInputSurface();
    }

    private boolean hasSurfaceForCodec(MediaCodecInfo r2) {
        if (this.videoSink != null) goto L15;
        Surface r0 = this.displaySurface;
        if (r0 == null) goto L9;
        if (r0.isValid() == false) goto L9;
        return true;
    L9:
        if (shouldUseDetachedSurface(r2) == false) goto L11;
        return true;
    L11:
        if (shouldUsePlaceholderSurface(r2) == true) goto L19;
        return false;
    L19:
        return true;
    L15:
        return true;
    }

    private boolean isBufferBeforeStartTime(DecoderInputBuffer r3) {
        if (r3.timeUs >= getLastResetPositionUs()) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isBufferProbablyLastSample(DecoderInputBuffer r7) {
        if (hasReadStreamToEnd() == false) goto L5;
    L15:
        return true;
    L5:
        if (r7.isLastSample() == true) goto L15;
        if (this.periodDurationUs != (-9223372036854775807L)) goto L10;
        return true;
    L10:
        long r2 = r7.timeUs - getOutputStreamOffsetUs();
        if ((this.periodDurationUs - r2) > 100000) goto L13;
        return true;
    L13:
        return false;
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames <= 0) goto L6;
        long r0 = getClock().elapsedRealtime();
        long r2 = r0 - this.droppedFrameAccumulationStartTimeMs;
        this.eventDispatcher.droppedFrames(this.droppedFrames, r2);
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = r0;
        return;
    }

    private void maybeNotifyRenderedFirstFrame() {
        if (this.videoFrameReleaseControl.onFrameReleasedIsFirstFrame() == true) goto L5;
        return;
    L5:
        if (this.displaySurface == null) goto L9;
        notifyRenderedFirstFrame();
        return;
    }

    private void maybeNotifyVideoFrameProcessingOffset() {
        int r0 = this.videoFrameProcessingOffsetCount;
        if (r0 == 0) goto L6;
        this.eventDispatcher.reportVideoFrameProcessingOffset(this.totalVideoFrameProcessingOffsetUs, r0);
        this.totalVideoFrameProcessingOffsetUs = 0;
        this.videoFrameProcessingOffsetCount = 0;
        return;
    }

    private void maybeNotifyVideoSizeChanged(VideoSize r2) {
        if (r2.equals(VideoSize.UNKNOWN) == false) goto L5;
        return;
    L5:
        if (r2.equals(this.reportedVideoSize) == true) goto L9;
        this.reportedVideoSize = r2;
        this.eventDispatcher.videoSizeChanged(r2);
        return;
    }

    private void maybeRenotifyRenderedFirstFrame() {
        Surface r0 = this.displaySurface;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.haveReportedFirstFrameRenderedForCurrentSurface == false) goto L9;
        this.eventDispatcher.renderedFirstFrame(r0);
        return;
    }

    private void maybeRenotifyVideoSizeChanged() {
        VideoSize r0 = this.reportedVideoSize;
        if (r0 == null) goto L6;
        this.eventDispatcher.videoSizeChanged(r0);
        return;
    }

    private void maybeSetKeyAllowFrameDrop(MediaFormat r2) {
        if (this.videoSink != null) goto L5;
        return;
    L5:
        if (Util.isFrameDropAllowedOnSurfaceInput(this.context) == true) goto L9;
        r2.setInteger("allow-frame-drop", 0);
        return;
    }

    private void maybeSetupTunnelingForFirstFrame() {
        if (this.tunneling == false) goto L11;
        MediaCodecAdapter r0 = getCodec();
        if (r0 == null) goto L13;
        this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(this, r0);
        if (Build.VERSION.SDK_INT < 33) goto L12;
        Bundle r3 = new Bundle();
        r3.putInt("tunnel-peek", 1);
        r0.setParameters(r3);
        return;
    L12:
        return;
    L13:
        return;
    }

    private void notifyFrameMetadataListener(long r8, long r10, Format r12) {
        VideoFrameMetadataListener r0 = this.frameMetadataListener;
        if (r0 == null) goto L6;
        r0.onVideoFrameAboutToBeRendered(r8, r10, r12, getCodecOutputMediaFormat());
        return;
    }

    @RequiresNonNull({"displaySurface"})
    private void notifyRenderedFirstFrame() {
        this.eventDispatcher.renderedFirstFrame(this.displaySurface);
        this.haveReportedFirstFrameRenderedForCurrentSurface = true;
    }

    private void onProcessedTunneledEndOfStream() {
        setPendingOutputEndOfStream();
    }

    private void releaseFrame(MediaCodecAdapter r11, int r12, long r13, Format r15) {
        long r4 = this.videoFrameReleaseInfo.getReleaseTimeNs();
        long r8 = this.videoFrameReleaseInfo.getEarlyUs();
        if (shouldSkipBuffersWithIdenticalReleaseTime() == true) goto L5;
    L7:
        MediaCodecVideoRenderer r1 = this;
        r1.notifyFrameMetadataListener(r13, r4, r15);
        r1.renderOutputBufferV21(r11, r12, r13, r4);
        r4 = r4;
    L8:
        r1.updateVideoFrameProcessingOffsetCounters(r8);
        r1.lastFrameReleaseTimeNs = r4;
        return;
    L5:
        if (r4 != this.lastFrameReleaseTimeNs) goto L7;
        skipOutputBuffer(r11, r12, r13);
        r1 = this;
        goto L8
    }

    private void releasePlaceholderSurface() {
        PlaceholderSurface r0 = this.placeholderSurface;
        if (r0 == null) goto L6;
        r0.release();
        this.placeholderSurface = null;
        return;
    }

    private void renderOutputBuffer(MediaCodecAdapter r1, int r2, long r3, long r5) {
        renderOutputBufferV21(r1, r2, r3, r5);
    }

    private static void setHdr10PlusInfoV29(MediaCodecAdapter r2, byte[] r3) {
        Bundle r0 = new Bundle();
        r0.putByteArray("hdr10-plus-info", r3);
        r2.setParameters(r0);
    }

    private void setOutput(Object r6) {
        if ((r6 instanceof Surface) == false) goto L5;
        Surface r62 = (Surface) r6;
    L7:
        if (this.displaySurface == r62) goto L34;
        this.displaySurface = r62;
        if (this.videoSink != null) goto L11;
        this.videoFrameReleaseControl.setOutputSurface(r62);
    L11:
        this.haveReportedFirstFrameRenderedForCurrentSurface = false;
        int r0 = getState();
        MediaCodecAdapter r2 = getCodec();
        if (r2 != null) goto L14;
    L21:
        if (r62 == null) goto L23;
        maybeRenotifyVideoSizeChanged();
    L27:
        if (r0 != 2) goto L32;
        VideoSink r63 = this.videoSink;
        if (r63 == null) goto L31;
        r63.join(true);
        goto L32
    L31:
        this.videoFrameReleaseControl.join(true);
    L32:
        maybeSetupTunnelingForFirstFrame();
        return;
    L23:
        this.reportedVideoSize = null;
        VideoSink r64 = this.videoSink;
        if (r64 == null) goto L27;
        r64.clearOutputSurfaceInfo();
        goto L27
    L14:
        if (this.videoSink != null) goto L21;
        MediaCodecInfo r3 = (MediaCodecInfo) Assertions.checkNotNull(getCodecInfo());
        if (hasSurfaceForCodec(r3) == true) goto L18;
    L20:
        releaseCodec();
        maybeInitCodecOrBypass();
        goto L21
    L18:
        if (this.codecNeedsSetOutputSurfaceWorkaround == true) goto L20;
        setOutputSurface(r2, getSurfaceForCodec(r3));
        goto L21
    L34:
        if (r62 == null) goto L37;
        maybeRenotifyVideoSizeChanged();
        maybeRenotifyRenderedFirstFrame();
        return;
    L37:
        return;
    L5:
        r62 = null;
        goto L7
    }

    private void setOutputSurface(MediaCodecAdapter r2, Surface r3) {
        if (r3 == null) goto L6;
        setOutputSurfaceV23(r2, r3);
        return;
    L6:
        if (Build.VERSION.SDK_INT < 35) goto L9;
        detachOutputSurfaceV35(r2);
        return;
    L9:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    private static int supportsFormatInternal(Context r10, MediaCodecSelector r11, Format r12) {
        int r1 = 0;
        if (MimeTypes.isVideo(r12.sampleMimeType) == true) goto L7;
        return RendererCapabilities.create(0);
    L7:
        if (r12.drmInitData == null) goto L9;
        boolean r0 = true;
    L10:
        List<MediaCodecInfo> r3 = getDecoderInfos(r10, r11, r12, r0, false);
        if (r0 == false) goto L16;
        if (r3.isEmpty() == false) goto L16;
        r3 = getDecoderInfos(r10, r11, r12, false, false);
    L16:
        if (r3.isEmpty() == false) goto L20;
        return RendererCapabilities.create(1);
    L20:
        if (MediaCodecRenderer.supportsFormatDrm(r12) == false) goto L22;
        MediaCodecInfo r4 = r3.get(0);
        boolean r5 = r4.isFormatSupported(r12);
        if (r5 == true) goto L32;
        int r6 = 1;
    L27:
        if (r6 >= r3.size()) goto L32;
        MediaCodecInfo r7 = r3.get(r6);
        if (r7.isFormatSupported(r12) == true) goto L30;
        r6 = r6 + 1;
        goto L27
    L30:
        boolean r32 = false;
        r5 = true;
        r4 = r7;
    L33:
        if (r5 == false) goto L35;
        int r62 = 4;
    L37:
        if (r4.isSeamlessAdaptationSupported(r12) == false) goto L39;
        int r72 = 16;
    L41:
        if (r4.hardwareAccelerated == false) goto L43;
        int r42 = 64;
    L44:
        if (r32 == false) goto L46;
        int r33 = 128;
    L48:
        if (Build.VERSION.SDK_INT >= 26) goto L50;
    L54:
        if (r5 == false) goto L63;
        List<MediaCodecInfo> r102 = getDecoderInfos(r10, r11, r12, r0, true);
        if (r102.isEmpty() == true) goto L63;
        MediaCodecInfo r103 = MediaCodecUtil.getDecoderInfosSortedByFormatSupport(r102, r12).get(0);
        if (r103.isFormatSupported(r12) == false) goto L63;
        if (r103.isSeamlessAdaptationSupported(r12) == false) goto L63;
        r1 = 32;
    L63:
        return RendererCapabilities.create(r62, r72, r1, r42, r33);
    L50:
        if ("video/dolby-vision".equals(r12.sampleMimeType) == false) goto L54;
        if (Api26.doesDisplaySupportDolbyVision(r10) == true) goto L54;
        r33 = 256;
        goto L54
    L46:
        r33 = 0;
        goto L48
    L43:
        r42 = 0;
        goto L44
    L39:
        r72 = 8;
        goto L41
    L35:
        r62 = 3;
    L32:
        r32 = true;
        goto L33
    L22:
        return RendererCapabilities.create(2);
    L9:
        r0 = false;
        goto L10
    }

    private void updateCodecImportance() {
        MediaCodecAdapter r0 = getCodec();
        if (r0 != null) goto L6;
        return;
    L6:
        if (Build.VERSION.SDK_INT < 35) goto L9;
        Bundle r1 = new Bundle();
        r1.putInt("importance", Math.max(0, -this.rendererPriority));
        r0.setParameters(r1);
        return;
    }

    private void updateDroppedBufferCountersWithInputBuffers(long r5) {
        int r1 = 0;
    L3:
        Long r2 = this.droppedDecoderInputBufferTimestamps.peek();
        if (r2 == null) goto L8;
        if (r2.longValue() >= r5) goto L8;
        r1 = r1 + 1;
        this.droppedDecoderInputBufferTimestamps.poll();
    L8:
        updateDroppedBufferCounters(r1, 0);
    }

    private void updatePeriodDurationUs(MediaSource.MediaPeriodId r3) {
        Timeline r0 = getTimeline();
        if (r0.isEmpty() == false) goto L6;
        this.periodDurationUs = -9223372036854775807L;
        return;
    L6:
        this.periodDurationUs = r0.getPeriodByUid(((MediaSource.MediaPeriodId) Assertions.checkNotNull(r3)).periodUid, new Timeline.Period()).getDurationUs();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo r9, Format r10, Format r11) {
        DecoderReuseEvaluation r0 = r9.canReuseCodec(r10, r11);
        int r1 = r0.discardReasons;
        CodecMaxValues r8 = (CodecMaxValues) Assertions.checkNotNull(this.codecMaxValues);
        if (r11.width <= r8.width) goto L5;
    L6:
        r1 = r1 | 256;
    L8:
        if (getMaxInputSize(r9, r11) <= r8.inputSize) goto L10;
        r1 = r1 | 64;
    L10:
        int r7 = r1;
        String r3 = r9.name;
        if (r7 == 0) goto L14;
        int r82 = 0;
    L16:
        return new DecoderReuseEvaluation(r3, r10, r11, r82, r7);
    L14:
        r82 = r0.result;
        goto L16
    L5:
        if (r11.height <= r8.height) goto L8;
        goto L6
    }

    public void changeVideoSinkInputStream(VideoSink r9, int r10, Format r11, int r12) {
        List<Object> r0 = this.videoEffects;
        if (r0 == null) goto L5;
    L4:
        List<Object> r7 = r0;
        r9.onInputStreamChanged(r10, r11, getOutputStreamStartPositionUs(), r12, r7);
        return;
    L5:
        r0 = ImmutableList.of();
        goto L4
    }

    public boolean codecNeedsSetOutputSurfaceWorkaround(String r1) {
        if (r1.startsWith("OMX.google") == false) goto L7;
        return false;
    L7:
        monitor-enter(MediaCodecVideoRenderer.class);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (evaluatedDeviceNeedsSetOutputSurfaceWorkaround == true) goto L13;
        deviceNeedsSetOutputSurfaceWorkaround = evaluateDeviceNeedsSetOutputSurfaceWorkaround();     // Catch: Throwable -> L11
        evaluatedDeviceNeedsSetOutputSurfaceWorkaround = true;     // Catch: Throwable -> L11
    L13:
        monitor-exit(MediaCodecVideoRenderer.class);     // Catch: Throwable -> L11
        return deviceNeedsSetOutputSurfaceWorkaround;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public MediaCodecDecoderException createDecoderException(Throwable r2, MediaCodecInfo r3) {
        return new MediaCodecVideoDecoderException(r2, r3, this.displaySurface);
    }

    public PlaybackVideoGraphWrapper createPlaybackVideoGraphWrapper(Context r2, VideoFrameReleaseControl r3) {
        return new PlaybackVideoGraphWrapper.Builder(r2, r3).setEnablePlaylistMode(true).setClock(getClock()).build();
    }

    public void detachOutputSurfaceV35(MediaCodecAdapter r1) {
        r1.detachOutputSurface();
    }

    public void dropOutputBuffer(MediaCodecAdapter r1, int r2, long r3) {
        TraceUtil.beginSection("dropVideoBuffer");
        r1.releaseOutputBuffer(r2, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(0, 1);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void enableMayRenderStartOfStream() {
        VideoSink r0 = this.videoSink;
        if (r0 == null) goto L13;
        int r1 = this.nextVideoSinkFirstFrameReleaseInstruction;
        if (r1 != 0) goto L7;
    L11:
        this.nextVideoSinkFirstFrameReleaseInstruction = 0;
        return;
    L7:
        if (r1 == 1) goto L11;
        r0.allowReleaseFirstFrameBeforeStarted();
        return;
    L13:
        this.videoFrameReleaseControl.allowReleaseFirstFrameBeforeStarted();
    }

    public long getBufferTimestampAdjustmentUs() {
        return -this.startPositionUs;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public int getCodecBufferFlags(DecoderInputBuffer r3) {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        return 0;
    L5:
        if (this.enableMediaCodecBufferDecodeOnlyFlag == true) goto L13;
        ScrubbingModeParameters r0 = this.scrubbingModeParameters;
        if (r0 == null) goto L11;
        if (r0.useDecodeOnlyFlag == true) goto L13;
    L11:
        if (this.tunneling == true) goto L13;
        return 0;
    L13:
        if (isBufferBeforeStartTime(r3) == true) goto L15;
        return 0;
    L15:
        if (isBufferProbablyLastSample(r3) == true) goto L22;
        return 32;
    L22:
        return 0;
    }

    public CodecMaxValues getCodecMaxValues(MediaCodecInfo r12, Format r13, Format[] r14) {
        int r11 = r13.width;
        int r0 = r13.height;
        int r1 = getMaxInputSize(r12, r13);
        if (r14.length != 1) goto L10;
        if (r1 == (-1)) goto L9;
        int r122 = getCodecMaxInputSize(r12, r13);
        if (r122 == (-1)) goto L9;
        r1 = Math.min((int) (r1 * 1.5f), r122);
    L9:
        return new CodecMaxValues(r11, r0, r1);
    L10:
        int r2 = r14.length;
        int r6 = 0;
        boolean r7 = false;
    L11:
        if (r6 >= r2) goto L28;
        Format r8 = r14[r6];
        if (r13.colorInfo == null) goto L18;
        if (r8.colorInfo != null) goto L18;
        r8 = r8.buildUpon().setColorInfo(r13.colorInfo).build();
    L18:
        if (r12.canReuseCodec(r13, r8).result == 0) goto L27;
        int r9 = r8.width;
        if (r9 != (-1)) goto L22;
    L25:
        boolean r10 = true;
    L26:
        r7 = r7 | r10;
        r11 = Math.max(r11, r9);
        r0 = Math.max(r0, r8.height);
        r1 = Math.max(r1, getMaxInputSize(r12, r8));
        goto L27
    L22:
        if (r8.height == (-1)) goto L25;
        r10 = false;
    L27:
        r6 = r6 + 1;
        goto L11
    L28:
        if (r7 == false) goto L33;
        Log.w("MediaCodecVideoRenderer", "Resolutions unknown. Codec max resolution: " + r11 + "x" + r0);
        Point r142 = getCodecMaxSize(r12, r13);
        if (r142 == null) goto L33;
        r11 = Math.max(r11, r142.x);
        r0 = Math.max(r0, r142.y);
        r1 = Math.max(r1, getCodecMaxInputSize(r12, r13.buildUpon().setWidth(r11).setHeight(r0).build()));
        Log.w("MediaCodecVideoRenderer", "Codec max resolution adjusted to: " + r11 + "x" + r0);
    L33:
        return new CodecMaxValues(r11, r0, r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean getCodecNeedsEosPropagation() {
        return false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public float getCodecOperatingRateV23(float r7, Format r8, Format[] r9) {
        int r0 = r9.length;
        int r2 = 0;
        float r3 = -1.0f;
    L3:
        if (r2 >= r0) goto L9;
        float r4 = r9[r2].frameRate;
        if (r4 == (-1.0f)) goto L7;
        r3 = Math.max(r3, r4);
    L7:
        r2 = r2 + 1;
        goto L3
    L9:
        if (r3 != (-1.0f)) goto L11;
        float r32 = -1.0f;
    L13:
        if (this.scrubbingModeParameters == null) goto L20;
        MediaCodecInfo r6 = getCodecInfo();
        if (r6 == null) goto L20;
        float r62 = r6.getMaxSupportedFrameRate(r8.width, r8.height);
        if (r32 != (-1.0f)) goto L19;
        return r62;
    L19:
        return Math.max(r32, r62);
    L20:
        return r32;
    L11:
        r32 = r3 * r7;
        goto L13
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector r2, Format r3, boolean r4) {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(this.context, r2, r3, r4, this.tunneling), r3);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo r8, Format r9, MediaCrypto r10, float r11) {
        String r2 = r8.codecMimeType;
        CodecMaxValues r3 = getCodecMaxValues(r8, r9, getStreamFormats());
        this.codecMaxValues = r3;
        boolean r5 = this.deviceNeedsNoPostProcessWorkaround;
        if (this.tunneling == false) goto L6;
        int r0 = this.tunnelingAudioSessionId;
    L7:
        MediaFormat r7 = getMediaFormat(r9, r2, r3, r11, r5, r0);
        Surface r92 = getSurfaceForCodec(r8);
        maybeSetKeyAllowFrameDrop(r7);
        return MediaCodecAdapter.Configuration.createForVideoDecoding(r8, r7, r9, r92, r10);
    L6:
        r0 = 0;
        goto L7
    }

    @SuppressLint({"InlinedApi"})
    public MediaFormat getMediaFormat(Format r3, String r4, CodecMaxValues r5, float r6, boolean r7, int r8) {
        MediaFormat r0 = new MediaFormat();
        r0.setString("mime", r4);
        r0.setInteger("width", r3.width);
        r0.setInteger("height", r3.height);
        MediaFormatUtil.setCsdBuffers(r0, r3.initializationData);
        MediaFormatUtil.maybeSetFloat(r0, "frame-rate", r3.frameRate);
        MediaFormatUtil.maybeSetInteger(r0, "rotation-degrees", r3.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(r0, r3.colorInfo);
        if ("video/dolby-vision".equals(r3.sampleMimeType) == false) goto L7;
        Pair<Integer, Integer> r32 = MediaCodecUtil.getCodecProfileAndLevel(r3);
        if (r32 == null) goto L7;
        MediaFormatUtil.maybeSetInteger(r0, "profile", ((Integer) r32.first).intValue());
    L7:
        r0.setInteger("max-width", r5.width);
        r0.setInteger("max-height", r5.height);
        MediaFormatUtil.maybeSetInteger(r0, "max-input-size", r5.inputSize);
        r0.setInteger(PluginsConstants.MenuItemProperties.PRIORITY, 0);
        if (r6 == (-1.0f)) goto L11;
        r0.setFloat("operating-rate", r6);
    L11:
        if (r7 == false) goto L13;
        r0.setInteger("no-post-process", 1);
        r0.setInteger("auto-frc", 0);
    L13:
        if (r8 == 0) goto L16;
        r0.setFeatureEnabled("tunneled-playback", true);
        r0.setInteger("audio-session-id", r8);
    L16:
        if (Build.VERSION.SDK_INT < 35) goto L18;
        r0.setInteger("importance", Math.max(0, -this.rendererPriority));
    L18:
        return r0;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "MediaCodecVideoRenderer";
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    @TargetApi(29)
    public void handleInputBufferSupplementalData(DecoderInputBuffer r8) {
        if (this.codecHandlesHdr10PlusOutOfBandMetadata == false) goto L25;
        ByteBuffer r82 = (ByteBuffer) Assertions.checkNotNull(r8.supplementalData);
        if (r82.remaining() < 7) goto L19;
        byte r0 = r82.get();
        short r1 = r82.getShort();
        short r2 = r82.getShort();
        byte r3 = r82.get();
        byte r4 = r82.get();
        r82.position(0);
        if (r0 == (-75)) goto L10;
        return;
    L10:
        if (r1 == 60) goto L12;
        return;
    L12:
        if (r2 == 1) goto L14;
        return;
    L14:
        if (r3 != 4) goto L23;
        if (r4 == 0) goto L17;
        if (r4 == 1) goto L17;
        return;
    L17:
        byte[] r02 = new byte[r82.remaining()];
        r82.get(r02);
        r82.position(0);
        setHdr10PlusInfoV29((MediaCodecAdapter) Assertions.checkNotNull(getCodec()), r02);
        return;
    L23:
        return;
    L19:
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int r3, Object r4) {
        boolean r0 = true;
        if (r3 != 1) goto L5;
        setOutput(r4);
        return;
    L5:
        if (r3 != 7) goto L7;
        VideoFrameMetadataListener r32 = (VideoFrameMetadataListener) Assertions.checkNotNull(r4);
        this.frameMetadataListener = r32;
        VideoSink r2 = this.videoSink;
        if (r2 == null) goto L77;
        r2.setVideoFrameMetadataListener(r32);
        return;
    L77:
        return;
    L7:
        if (r3 != 10) goto L9;
        int r33 = ((Integer) Assertions.checkNotNull(r4)).intValue();
        if (this.tunnelingAudioSessionId == r33) goto L75;
        this.tunnelingAudioSessionId = r33;
        if (this.tunneling == false) goto L76;
        releaseCodec();
        return;
    L76:
        return;
    L75:
        return;
    L9:
        if (r3 != 4) goto L11;
        this.scalingMode = ((Integer) Assertions.checkNotNull(r4)).intValue();
        MediaCodecAdapter r34 = getCodec();
        if (r34 == null) goto L74;
        r34.setVideoScalingMode(this.scalingMode);
        return;
    L74:
        return;
    L11:
        if (r3 != 5) goto L13;
        int r35 = ((Integer) Assertions.checkNotNull(r4)).intValue();
        this.changeFrameRateStrategy = r35;
        VideoSink r42 = this.videoSink;
        if (r42 == null) goto L52;
        r42.setChangeFrameRateStrategy(r35);
        return;
    L52:
        this.videoFrameReleaseControl.setChangeFrameRateStrategy(r35);
        return;
    L13:
        if (r3 != 13) goto L15;
        setVideoEffects((List) Assertions.checkNotNull(r4));
        return;
    L15:
        if (r3 == 14) goto L38;
        switch(r3) {
            case 16: goto L36;
            case 17: goto L34;
            case 18: goto L19;
            default: goto L17;
        };
    L17:
        super.handleMessage(r3, r4);
        return;
    L19:
        ScrubbingModeParameters r36 = this.scrubbingModeParameters;
        if (r36 != null) goto L22;
    L24:
        boolean r37 = false;
    L25:
        ScrubbingModeParameters r43 = (ScrubbingModeParameters) r4;
        this.scrubbingModeParameters = r43;
        if (r43 != null) goto L28;
    L30:
        r0 = false;
    L31:
        if (r37 == r0) goto L70;
        updateCodecOperatingRate();
        return;
    L70:
        return;
    L28:
        if (r43.shouldIncreaseCodecOperatingRate == false) goto L30;
    L22:
        if (r36.shouldIncreaseCodecOperatingRate == false) goto L24;
        r37 = true;
        goto L25
    L34:
        Surface r38 = this.displaySurface;
        setOutput(null);
        ((MediaCodecVideoRenderer) Assertions.checkNotNull(r4)).handleMessage(1, r38);
        return;
    L36:
        this.rendererPriority = ((Integer) Assertions.checkNotNull(r4)).intValue();
        updateCodecImportance();
        return;
    L38:
        Size r39 = (Size) Assertions.checkNotNull(r4);
        if (r39.getWidth() != 0) goto L41;
        return;
    L41:
        if (r39.getHeight() == 0) goto L72;
        this.outputResolution = r39;
        VideoSink r44 = this.videoSink;
        if (r44 == null) goto L73;
        r44.setOutputSurfaceInfo((Surface) Assertions.checkStateNotNull(this.displaySurface), r39);
        return;
    L73:
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        if (super.isEnded() == false) goto L10;
        VideoSink r1 = this.videoSink;
        if (r1 != null) goto L7;
        return true;
    L7:
        if (r1.isEnded() == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        boolean r0 = super.isReady();
        VideoSink r1 = this.videoSink;
        if (r1 != null) goto L5;
        if (r0 == false) goto L14;
        if (getCodec() != null) goto L10;
        return true;
    L10:
        if (this.tunneling == false) goto L14;
        return true;
    L14:
        return this.videoFrameReleaseControl.isReady(r0);
    L5:
        return r1.isReady(r0);
    }

    public boolean maybeDropBuffersToKeyframe(long r4, boolean r6) {
        int r42 = skipSource(r4);
        if (r42 != 0) goto L5;
        return false;
    L5:
        DecoderCounters r0 = this.decoderCounters;
        if (r6 == false) goto L8;
        int r62 = r0.skippedInputBufferCount + r42;
        r0.skippedInputBufferCount = r62;
        r0.skippedOutputBufferCount += this.buffersInCodecCount;
        r0.skippedInputBufferCount = r62 + this.droppedDecoderInputBufferTimestamps.size();
    L9:
        flushOrReinitializeCodec();
        VideoSink r3 = this.videoSink;
        if (r3 == null) goto L12;
        r3.flush(false);
    L12:
        return true;
    L8:
        r0.droppedToKeyframeCount++;
        updateDroppedBufferCounters(r42 + this.droppedDecoderInputBufferTimestamps.size(), this.buffersInCodecCount);
        goto L9
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean maybeInitializeProcessingPipeline(Format r3) {
        VideoSink r0 = this.videoSink;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.isInitialized() == true) goto L15;
        return this.videoSink.initialize(r3);
    L8:
        e = move-exception;
        throw this.createRendererException(e, r3, 7000);
    L15:
        return true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecError(Exception r3) {
        Log.e("MediaCodecVideoRenderer", "Video codec error", r3);
        this.eventDispatcher.videoCodecError(r3);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecInitialized(String r1, MediaCodecAdapter.Configuration r2, long r3, long r5) {
        this.eventDispatcher.decoderInitialized(r1, r3, r5);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(r1);
        this.codecHandlesHdr10PlusOutOfBandMetadata = ((MediaCodecInfo) Assertions.checkNotNull(getCodecInfo())).isHdr10PlusOutOfBandMetadataSupported();
        maybeSetupTunnelingForFirstFrame();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecReleased(String r1) {
        this.eventDispatcher.decoderReleased(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.reportedVideoSize = null;
        this.periodDurationUs = -9223372036854775807L;
        maybeSetupTunnelingForFirstFrame();
        this.haveReportedFirstFrameRenderedForCurrentSurface = false;
        this.tunnelingOnFrameRenderedListener = null;
        this.isFlushRequired = true;
        super.onDisabled();     // Catch: Throwable -> L6
        this.eventDispatcher.disabled(this.decoderCounters);
        this.eventDispatcher.videoSizeChanged(VideoSize.UNKNOWN);
        return;
    L6:
        th = move-exception;
        this.eventDispatcher.disabled(this.decoderCounters);
        this.eventDispatcher.videoSizeChanged(VideoSize.UNKNOWN);
        throw th;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean r4, boolean r5) {
        super.onEnabled(r4, r5);
        boolean r42 = getConfiguration().tunneling;
        if (r42 == true) goto L5;
    L8:
        boolean r2 = true;
    L9:
        Assertions.checkState(r2);
        if (this.tunneling == r42) goto L12;
        this.tunneling = r42;
        releaseCodec();
    L12:
        this.eventDispatcher.enabled(this.decoderCounters);
        if (this.hasSetVideoSink == true) goto L21;
        if (this.videoEffects != null) goto L17;
    L19:
        this.hasSetVideoSink = true;
        goto L21
    L17:
        if (this.videoSink != null) goto L19;
        PlaybackVideoGraphWrapper r43 = createPlaybackVideoGraphWrapper(this.context, this.videoFrameReleaseControl);
        r43.setTotalVideoInputCount(1);
        this.videoSink = r43.getSink(0);
    L21:
        if (this.videoSink == null) goto L24;
        configureVideoSink();
        this.nextVideoSinkFirstFrameReleaseInstruction = !r5 ? 1 : 0;
        experimentalEnableProcessedStreamChangedAtStart();
        return;
    L24:
        this.videoFrameReleaseControl.setClock(getClock());
        this.videoFrameReleaseControl.onStreamChanged(!r5 ? 1 : 0);
        return;
    L5:
        if (this.tunnelingAudioSessionId != 0) goto L8;
        r2 = false;
        goto L9
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onInit() {
        super.onInit();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder r3) {
        DecoderReuseEvaluation r0 = super.onInputFormatChanged(r3);
        this.eventDispatcher.inputFormatChanged((Format) Assertions.checkNotNull(r3.format), r0);
        VideoFrameReleaseEarlyTimeForecaster r2 = this.videoFrameReleaseEarlyTimeForecaster;
        if (r2 == null) goto L5;
        r2.reset();
    L5:
        return r0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onOutputFormatChanged(Format r9, MediaFormat r10) {
        MediaCodecAdapter r0 = getCodec();
        if (r0 == null) goto L6;
        r0.setVideoScalingMode(this.scalingMode);
    L6:
        if (this.tunneling == false) goto L8;
        int r102 = r9.width;
        int r02 = r9.height;
    L25:
        float r3 = r9.pixelWidthHeightRatio;
        int r4 = r9.rotationDegrees;
        if (r4 != 90) goto L28;
    L29:
        r3 = 1.0f / r3;
        int r7 = r02;
        r02 = r102;
        r102 = r7;
    L30:
        this.decodedVideoSize = new VideoSize(r102, r02, r3);
        VideoSink r42 = this.videoSink;
        if (r42 != null) goto L33;
    L35:
        this.videoFrameReleaseControl.setFrameRate(r9.frameRate);
    L36:
        this.pendingVideoSinkInputStreamChange = false;
        return;
    L33:
        if (this.pendingVideoSinkInputStreamChange == false) goto L35;
        changeVideoSinkInputStream(r42, 1, r9.buildUpon().setWidth(r102).setHeight(r02).setPixelWidthHeightRatio(r3).build(), this.nextVideoSinkFirstFrameReleaseInstruction);
        this.nextVideoSinkFirstFrameReleaseInstruction = 2;
        goto L36
    L28:
        if (r4 != 270) goto L30;
    L8:
        Assertions.checkNotNull(r10);
        if (r10.containsKey("crop-right") == true) goto L11;
    L17:
        boolean r32 = false;
    L18:
        if (r32 == false) goto L20;
        int r03 = (r10.getInteger("crop-right") - r10.getInteger("crop-left")) + 1;
    L21:
        if (r32 == false) goto L23;
        int r103 = (r10.getInteger("crop-bottom") - r10.getInteger("crop-top")) + 1;
    L24:
        int r72 = r03;
        r02 = r103;
        r102 = r72;
        goto L25
    L23:
        r103 = r10.getInteger("height");
        goto L24
    L20:
        r03 = r10.getInteger("width");
        goto L21
    L11:
        if (r10.containsKey("crop-left") == false) goto L17;
        if (r10.containsKey("crop-bottom") == false) goto L17;
        if (r10.containsKey("crop-top") == false) goto L17;
        r32 = true;
        goto L18
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long r3, boolean r5) {
        VideoSink r0 = this.videoSink;
        if (r0 == null) goto L6;
        if (r5 == true) goto L6;
        r0.flush(true);
    L6:
        super.onPositionReset(r3, r5);
        if (this.videoSink != null) goto L9;
        this.videoFrameReleaseControl.reset();
    L9:
        VideoFrameReleaseEarlyTimeForecaster r32 = this.videoFrameReleaseEarlyTimeForecaster;
        if (r32 == null) goto L13;
        r32.reset();
    L13:
        if (r5 == false) goto L18;
        VideoSink r4 = this.videoSink;
        if (r4 == null) goto L17;
        r4.join(false);
        goto L18
    L17:
        this.videoFrameReleaseControl.join(false);
    L18:
        maybeSetupTunnelingForFirstFrame();
        this.consecutiveDroppedFrameCount = 0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onProcessedOutputBuffer(long r1) {
        super.onProcessedOutputBuffer(r1);
        if (this.tunneling == true) goto L6;
        this.buffersInCodecCount--;
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onProcessedStreamChange() {
        super.onProcessedStreamChange();
        VideoSink r0 = this.videoSink;
        if (r0 == null) goto L8;
        r0.signalEndOfCurrentInputStream();
        if (this.startPositionUs != (-9223372036854775807L)) goto L7;
        this.startPositionUs = getOutputStreamStartPositionUs();
    L7:
        this.videoSink.setBufferTimestampAdjustmentUs(getBufferTimestampAdjustmentUs());
    L9:
        this.pendingVideoSinkInputStreamChange = true;
        maybeSetupTunnelingForFirstFrame();
        return;
    L8:
        this.videoFrameReleaseControl.onStreamChanged(2);
        goto L9
    }

    public void onProcessedTunneledBuffer(long r3) {
        updateOutputFormatForTime(r3);
        maybeNotifyVideoSizeChanged(this.decodedVideoSize);
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(r3);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onQueueInputBuffer(DecoderInputBuffer r3) {
        if (this.av1SampleDependencyParser != null) goto L5;
    L9:
        this.consecutiveDroppedInputBufferCount = 0;
        int r32 = getCodecBufferFlags(r3);
        if (Build.VERSION.SDK_INT < 34) goto L14;
        if ((r32 & 32) == 0) goto L14;
        return;
    L14:
        if (this.tunneling == true) goto L18;
        this.buffersInCodecCount++;
        return;
    L18:
        return;
    L5:
        if (((MediaCodecInfo) Assertions.checkNotNull(getCodecInfo())).mimeType.equals("video/av01") == false) goto L9;
        ByteBuffer r0 = r3.data;
        if (r0 == null) goto L9;
        this.av1SampleDependencyParser.queueInputBuffer(r0);
        goto L9
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onRelease() {
        super.onRelease();
        VideoSink r0 = this.videoSink;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.ownsVideoSink == false) goto L9;
        r0.release();
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onReset() {
        super.onReset();     // Catch: Throwable -> L6
        this.hasSetVideoSink = false;
        this.startPositionUs = -9223372036854775807L;
        releasePlaceholderSurface();
        return;
    L6:
        th = move-exception;
        this.hasSetVideoSink = false;
        this.startPositionUs = -9223372036854775807L;
        releasePlaceholderSurface();
        throw th;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = getClock().elapsedRealtime();
        this.totalVideoFrameProcessingOffsetUs = 0;
        this.videoFrameProcessingOffsetCount = 0;
        VideoSink r0 = this.videoSink;
        if (r0 == null) goto L6;
        r0.startRendering();
        return;
    L6:
        this.videoFrameReleaseControl.onStarted();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onStopped() {
        maybeNotifyDroppedFrames();
        maybeNotifyVideoFrameProcessingOffset();
        VideoSink r0 = this.videoSink;
        if (r0 == null) goto L5;
        r0.stopRendering();
    L6:
        VideoFrameReleaseEarlyTimeForecaster r02 = this.videoFrameReleaseEarlyTimeForecaster;
        if (r02 == null) goto L9;
        r02.reset();
    L9:
        super.onStopped();
        return;
    L5:
        this.videoFrameReleaseControl.onStopped();
        goto L6
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] r1, long r2, long r4, MediaSource.MediaPeriodId r6) {
        super.onStreamChanged(r1, r2, r4, r6);
        updatePeriodDurationUs(r6);
        VideoFrameReleaseEarlyTimeForecaster r0 = this.videoFrameReleaseEarlyTimeForecaster;
        if (r0 == null) goto L6;
        r0.reset();
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean processOutputBuffer(long r19, long r21, MediaCodecAdapter r23, ByteBuffer r24, int r25, int r26, int r27, long r28, boolean r30, boolean r31, Format r32) {
        Assertions.checkNotNull(r23);
        long r4 = r28 - getOutputStreamOffsetUs();
        updateDroppedBufferCountersWithInputBuffers(r28);
        VideoSink r8 = this.videoSink;
        if (r8 == null) goto L10;
        if (r30 == false) goto L9;
        if (r31 == true) goto L9;
        skipOutputBuffer(r23, r25, r4);
        return true;
    L9:
        return r8.handleInputFrame(r28, new AnonymousClass2(this, r23, r25, r4));
    L10:
        int r0 = this.videoFrameReleaseControl.getFrameReleaseAction(r28, r19, r21, getOutputStreamStartPositionUs(), r30, r31, this.videoFrameReleaseInfo);
        VideoFrameReleaseEarlyTimeForecaster r3 = this.videoFrameReleaseEarlyTimeForecaster;
        if (r3 == null) goto L15;
        if (r0 == 5) goto L15;
        if (r0 == 4) goto L15;
        r3.onVideoFrameProcessed(r28, this.videoFrameReleaseInfo.getEarlyUs());
    L15:
        if (r0 == 0) goto L34;
        if (r0 != 1) goto L18;
        releaseFrame((MediaCodecAdapter) Assertions.checkStateNotNull(r23), r25, r4, r32);
        return true;
    L18:
        if (r0 != 2) goto L20;
        dropOutputBuffer(r23, r25, r4);
        updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
        return true;
    L20:
        if (r0 == 3) goto L28;
        if (r0 == 4) goto L26;
        if (r0 == 5) goto L36;
        Segment$$ExternalSyntheticBUOutline1.m(String.valueOf(r0));
        return false;
    L36:
        return false;
    L26:
        return false;
    L28:
        skipOutputBuffer(r23, r25, r4);
        updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
        return true;
    L34:
        long r02 = getClock().nanoTime();
        notifyFrameMetadataListener(r4, r02, r32);
        renderOutputBuffer(r23, r25, r4, r02);
        updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
        return true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public void render(long r2, long r4) {
        VideoSink r0 = this.videoSink;
        if (r0 != null) goto L11;
    L9:
        super.render(r2, r4);
        return;
    L11:
        r0.render(r2, r4);     // Catch: VideoSink.VideoSinkException -> L6
    L6:
        e = move-exception;
        throw createRendererException(e, e.format, 7001);
    }

    public void renderOutputBufferV21(MediaCodecAdapter r1, int r2, long r3, long r5) {
        TraceUtil.beginSection("releaseOutputBuffer");
        r1.releaseOutputBuffer(r2, r5);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        if (this.videoSink != null) goto L6;
        maybeNotifyVideoSizeChanged(this.decodedVideoSize);
        maybeNotifyRenderedFirstFrame();
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void renderToEndOfStream() {
        VideoSink r0 = this.videoSink;
        if (r0 == null) goto L6;
        r0.signalEndOfCurrentInputStream();
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void resetCodecStateForFlush() {
        super.resetCodecStateForFlush();
        this.droppedDecoderInputBufferTimestamps.clear();
        this.buffersInCodecCount = 0;
        this.consecutiveDroppedInputBufferCount = 0;
        this.isFlushRequired = false;
        Av1SampleDependencyParser r1 = this.av1SampleDependencyParser;
        if (r1 == null) goto L6;
        r1.reset();
        return;
    }

    public void setOutputSurfaceV23(MediaCodecAdapter r1, Surface r2) {
        r1.setOutputSurface(r2);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        th.printStackTrace();
        throw new SurfaceNotValidException(th);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public void setPlaybackSpeed(float r1, float r2) {
        super.setPlaybackSpeed(r1, r2);
        VideoSink r22 = this.videoSink;
        if (r22 == null) goto L5;
        r22.setPlaybackSpeed(r1);
    L6:
        VideoFrameReleaseEarlyTimeForecaster r0 = this.videoFrameReleaseEarlyTimeForecaster;
        if (r0 == null) goto L10;
        r0.setPlaybackSpeed(r1);
        return;
    L10:
        return;
    L5:
        this.videoFrameReleaseControl.setPlaybackSpeed(r1);
        goto L6
    }

    public void setVideoEffects(List<Object> r2) {
        if (r2.equals(VideoFrameProcessor.REDRAW) == false) goto L11;
        VideoSink r22 = this.videoSink;
        if (r22 != null) goto L7;
        return;
    L7:
        if (r22.isInitialized() == false) goto L17;
        this.videoSink.redraw();
        return;
    L17:
        return;
    L11:
        this.videoEffects = r2;
        VideoSink r1 = this.videoSink;
        if (r1 == null) goto L16;
        r1.setVideoEffects(r2);
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean shouldDiscardDecoderInputBuffer(DecoderInputBuffer r9) {
        boolean r1 = false;
        if (isBufferProbablyLastSample(r9) == false) goto L5;
        return false;
    L5:
        boolean r0 = isBufferBeforeStartTime(r9);
        VideoFrameReleaseEarlyTimeForecaster r2 = this.videoFrameReleaseEarlyTimeForecaster;
        if (r2 == null) goto L12;
        long r4 = r2.predictEarlyUs(r9.timeUs);
        if (r4 == (-9223372036854775807L)) goto L12;
        if (r4 >= this.minEarlyUsToDropDecoderInput) goto L12;
        boolean r22 = true;
    L13:
        if (r0 == true) goto L17;
        if (r22 == true) goto L17;
        return false;
    L17:
        if (r9.hasSupplementalData() == false) goto L20;
        return false;
    L20:
        if (r9.notDependedOn() == false) goto L24;
        r9.clear();
    L22:
        r1 = true;
    L45:
        if (r1 == false) goto L50;
        if (r0 == false) goto L49;
        this.decoderCounters.skippedInputBufferCount++;
        return r1;
    L49:
        this.droppedDecoderInputBufferTimestamps.add(Long.valueOf(r9.timeUs));
        this.consecutiveDroppedInputBufferCount++;
    L50:
        return r1;
    L24:
        if (this.av1SampleDependencyParser == null) goto L45;
        if (((MediaCodecInfo) Assertions.checkNotNull(getCodecInfo())).mimeType.equals("video/av01") == false) goto L45;
        ByteBuffer r23 = r9.data;
        if (r23 == null) goto L45;
        if (r0 == false) goto L31;
    L34:
        boolean r42 = true;
    L35:
        ByteBuffer r24 = r23.asReadOnlyBuffer();
        r24.flip();
        int r43 = this.av1SampleDependencyParser.sampleLimitAfterSkippingNonReferenceFrame(r24, r42);
        if (r43 != 0) goto L39;
        r9.clear();
        goto L22
    L39:
        if (r43 == r24.limit()) goto L45;
        if ((((CodecMaxValues) Assertions.checkNotNull(this.codecMaxValues)).inputSize + r43) >= r24.capacity()) goto L45;
        if (r9.isEncrypted() == true) goto L45;
        ((ByteBuffer) Assertions.checkNotNull(r9.data)).position(r43);
        goto L22
    L31:
        if (this.consecutiveDroppedInputBufferCount <= 0) goto L34;
        r42 = false;
    L12:
        r22 = false;
        goto L13
    }

    public boolean shouldDropBuffersToKeyframe(long r1, long r3, boolean r5) {
        if (r1 >= (-500000)) goto L7;
        if (r5 == true) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldDropFrame(long r1, long r3, boolean r5) {
        return shouldDropOutputBuffer(r1, r3, r5);
    }

    public boolean shouldDropOutputBuffer(long r1, long r3, boolean r5) {
        if (r1 >= (-30000)) goto L7;
        if (r5 == true) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public final boolean shouldFlushCodec() {
        Format r0 = getCodecInputFormat();
        ScrubbingModeParameters r1 = this.scrubbingModeParameters;
        if (r1 != null) goto L7;
        return super.shouldFlushCodec();
    L7:
        if (r1.allowSkippingMediaCodecFlush == true) goto L9;
        return true;
    L9:
        if (this.isFlushRequired == false) goto L11;
        return true;
    L11:
        if (this.tunneling == true) goto L25;
        if (r0 == null) goto L16;
        if (r0.maxNumReorderSamples <= 0) goto L16;
        return true;
    L16:
        if (hasSkippedFlushAndWaitingForEarlierFrame() == false) goto L18;
        return true;
    L18:
        if (getLastBufferInStreamPresentationTimeUs() != (-9223372036854775807L)) goto L28;
        return false;
    L28:
        return true;
    L25:
        return true;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldForceReleaseFrame(long r1, long r3) {
        return shouldForceRenderOutputBuffer(r1, r3);
    }

    public boolean shouldForceRenderOutputBuffer(long r3, long r5) {
        if (r3 < (-30000)) goto L5;
        return false;
    L5:
        if (r5 <= 100000) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldIgnoreFrame(long r7, long r9, long r11, boolean r13, boolean r14) {
        if (this.videoSink == null) goto L8;
        if (this.ownsVideoSink == false) goto L8;
        r9 = r9 - getBufferTimestampAdjustmentUs();
    L8:
        if (shouldDropBuffersToKeyframe(r7, r11, r13) == true) goto L10;
        return false;
    L10:
        if (maybeDropBuffersToKeyframe(r9, r14) == false) goto L15;
        return true;
    L15:
        return false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean shouldInitCodec(MediaCodecInfo r1) {
        return hasSurfaceForCodec(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public final boolean shouldReleaseCodecInsteadOfFlushing() {
        MediaCodecInfo r0 = getCodecInfo();
        if (this.videoSink == null) goto L12;
        if (r0 == null) goto L12;
        if (r0.name.equals("c2.mtk.avc.decoder") == false) goto L8;
        return true;
    L8:
        if (r0.name.equals("c2.mtk.hevc.decoder") == false) goto L12;
        return true;
    L12:
        return super.shouldReleaseCodecInsteadOfFlushing();
    }

    public boolean shouldSkipBuffersWithIdenticalReleaseTime() {
        return true;
    }

    public boolean shouldUseDetachedSurface(MediaCodecInfo r2) {
        if (Build.VERSION.SDK_INT >= 35) goto L5;
        return false;
    L5:
        if (r2.detachedSurfaceSupported == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean shouldUsePlaceholderSurface(MediaCodecInfo r2) {
        if (this.tunneling == false) goto L5;
        return false;
    L5:
        if (codecNeedsSetOutputSurfaceWorkaround(r2.name) == false) goto L7;
        return false;
    L7:
        if (r2.secure == true) goto L9;
        return true;
    L9:
        if (PlaceholderSurface.isSecureSupported(this.context) == false) goto L16;
        return true;
    L16:
        return false;
    }

    public void skipOutputBuffer(MediaCodecAdapter r1, int r2, long r3) {
        TraceUtil.beginSection("skipVideoBuffer");
        r1.releaseOutputBuffer(r2, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public int supportsFormat(MediaCodecSelector r1, Format r2) {
        return supportsFormatInternal(this.context, r1, r2);
    }

    public void updateDroppedBufferCounters(int r3, int r4) {
        DecoderCounters r0 = this.decoderCounters;
        r0.droppedInputBufferCount += r3;
        int r32 = r3 + r4;
        r0.droppedBufferCount += r32;
        this.droppedFrames += r32;
        int r42 = this.consecutiveDroppedFrameCount + r32;
        this.consecutiveDroppedFrameCount = r42;
        r0.maxConsecutiveDroppedBufferCount = Math.max(r42, r0.maxConsecutiveDroppedBufferCount);
        int r33 = this.maxDroppedFramesToNotify;
        if (r33 > 0) goto L5;
        return;
    L5:
        if (this.droppedFrames < r33) goto L9;
        maybeNotifyDroppedFrames();
        return;
    }

    public void updateVideoFrameProcessingOffsetCounters(long r3) {
        this.decoderCounters.addVideoFrameProcessingOffset(r3);
        this.totalVideoFrameProcessingOffsetUs += r3;
        this.videoFrameProcessingOffsetCount++;
    }
}
