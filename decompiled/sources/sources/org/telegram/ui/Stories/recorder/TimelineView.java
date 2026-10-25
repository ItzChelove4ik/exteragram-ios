package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import kotlin.UByte;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.Scroller;
import org.telegram.ui.Components.Text;

/* loaded from: classes7.dex */
public class TimelineView extends View {
    private Runnable askExactSeek;
    private StaticLayout audioAuthor;
    private float audioAuthorLeft;
    private final TextPaint audioAuthorPaint;
    private float audioAuthorWidth;
    private final BlurringShader.StoryBlurDrawer audioBlur;
    private final RectF audioBounds;
    private final Path audioClipPath;
    private final Paint audioDotPaint;
    private long audioDuration;
    private final Drawable audioIcon;
    private float audioLeft;
    private long audioOffset;
    private String audioPath;
    private float audioRight;
    private boolean audioSelected;
    private final AnimatedFloat audioSelectedT;
    private final AnimatedFloat audioT;
    private StaticLayout audioTitle;
    private float audioTitleLeft;
    private final TextPaint audioTitlePaint;
    private float audioTitleWidth;
    private float audioVolume;
    private final BlurringShader.StoryBlurDrawer audioWaveformBlur;
    private final BlurringShader.StoryBlurDrawer backgroundBlur;
    private final BlurringShader.BlurManager blurManager;
    private final Path collageClipPath;
    private final Paint collageFramePaint;
    private Track collageMain;
    private int collageSelected;
    private final ArrayList<Track> collageTracks;
    private final ArrayList<AudioWaveformLoader> collageWaveforms;
    private final TextPaint countTextPaint;
    private long coverEnd;
    private long coverStart;
    private TimelineDelegate delegate;
    private float dragSpeed;
    private boolean dragged;
    private boolean draggingProgress;
    private final LinearGradient ellipsizeGradient;
    private final Matrix ellipsizeMatrix;
    private final Paint ellipsizePaint;
    private int h;
    private boolean hadDragChange;
    private boolean hasAudio;
    private boolean hasRound;
    private boolean isCover;
    private int lastHeight;
    private long lastTime;
    private float lastX;
    private final AnimatedFloat loopProgress;
    private long loopProgressFrom;
    private int maxCount;
    private Runnable onHeightChange;
    private final Runnable onLongPress;
    private Runnable onTimelineClick;
    public boolean open;
    private final AnimatedFloat openT;
    private int ph;
    private int pressCollageIndex;
    private int pressHandle;
    private int pressHandleCollageIndex;
    private long pressTime;
    private int pressType;
    private final View previewContainer;
    private long progress;
    private final Paint progressShadowPaint;
    private final Paint progressWhitePaint;
    private int px;
    private int py;
    private final Paint regionCutPaint;
    private final Paint regionHandlePaint;
    private final Paint regionPaint;
    private boolean resetWaveform;
    private final Theme.ResourcesProvider resourcesProvider;
    private final RectF roundBounds;
    private final Path roundClipPath;
    private long roundDuration;
    private float roundLeft;
    private long roundOffset;
    private String roundPath;
    private float roundRight;
    private boolean roundSelected;
    private final AnimatedFloat roundSelectedT;
    private final AnimatedFloat roundT;
    private VideoThumbsLoader roundThumbs;
    private float roundVolume;
    private long scroll;
    private final Scroller scroller;
    private boolean scrolling;
    private int scrollingCollage;
    private boolean scrollingVideo;
    private final Path selectedCollageClipPath;
    private final Path selectedVideoClipPath;
    final float[] selectedVideoRadii;
    private int sw;
    private final RectF timelineBounds;
    private final Path timelineClipPath;
    private final Drawable timelineIcon;
    private final Text timelineText;
    private final AnimatedFloat timelineWaveformLoaded;
    private final AnimatedFloat timelineWaveformMax;
    private final WaveformPath timelineWaveformPath;
    private VelocityTracker velocityTracker;
    private final RectF videoBounds;
    private final Path videoClipPath;
    private final Paint videoFramePaint;
    private Track videoTrack;
    private int w;
    private int wasScrollX;
    private AudioWaveformLoader waveform;
    private boolean waveformIsLoaded;
    private final AnimatedFloat waveformMax;
    private final Paint waveformPaint;
    private final WaveformPath waveformPath;

    public class AudioWaveformLoader {
        private final AnimatedFloat animatedLoaded;
        private final int count;
        private final short[] data;
        private long duration;
        private final MediaExtractor extractor;
        private MediaFormat inputFormat;
        private int loaded;
        private final Object lock;
        private short max;
        private boolean stop;
        final /* synthetic */ TimelineView this$0;
        private FfmpegAudioWaveformLoader waveformLoader;

        public static /* synthetic */ void $r8$lambda$TSURXiSyTKGIF6i45kAMp2s0e68(AudioWaveformLoader r0) {
            r0.run();
        }

        public static /* synthetic */ void $r8$lambda$UhbYtQzqUxuzc_cBe74a2aCzrjE(AudioWaveformLoader r0, short[] r1, int r2) {
            r0.receiveData(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$biWfMeUmWNrkqCEw8hTWTIxGsu0(AudioWaveformLoader r0, short[] r1, int r2) {
            r0.lambda$run$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetanimatedLoaded, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedFloat m20585$$Nest$fgetanimatedLoaded(AudioWaveformLoader r0) {
            return r0.animatedLoaded;
        }

        public AudioWaveformLoader(TimelineView r8, String r9, int r10) {
            this.this$0 = r8;
            this.animatedLoaded = new AnimatedFloat(r8, 0, 600, CubicBezierInterpolator.EASE_OUT_QUINT);
            int r82 = 0;
            this.loaded = 0;
            this.lock = new Object();
            this.stop = false;
            MediaExtractor r0 = new MediaExtractor();
            this.extractor = r0;
            String r2 = null;
            r0.setDataSource(r9);     // Catch: Exception -> L10
            int r02 = r0.getTrackCount();     // Catch: Exception -> L10
        L4:
            if (r82 >= r02) goto L13;
            MediaFormat r3 = this.extractor.getTrackFormat(r82);     // Catch: Exception -> L10
            r2 = r3.getString("mime");     // Catch: Exception -> L10
            if (r2 == null) goto L12;
            if (r2.startsWith("audio/") == false) goto L12;
            this.extractor.selectTrack(r82);     // Catch: Exception -> L10
            this.inputFormat = r3;     // Catch: Exception -> L10
        L12:
            r82 = r82 + 1;     // Catch: Exception -> L10
        L13:
            MediaFormat r83 = this.inputFormat;     // Catch: Exception -> L10
            if (r83 == null) goto L19;
            this.duration = r83.getLong("durationUs") / 1000000;     // Catch: Exception -> L10
        L19:
            if (TimelineView.m20582$$Nest$fgetvideoTrack(r8) == null) goto L22;
            long r5 = TimelineView.m20582$$Nest$fgetvideoTrack(r8).duration;
        L28:
            int r84 = Math.min(Math.round((((this.duration * 1000) / Math.min(r5, r8.getMaxScrollDuration())) * r10) / Math.round(AndroidUtilities.dpf2(3.3333f))), 4000);
            this.count = r84;
            this.data = new short[r84];
            if (this.duration > 0) goto L31;
            return;
        L31:
            if (this.inputFormat != null) goto L33;
            return;
        L33:
            if ("audio/mpeg".equals(r2) == false) goto L35;
        L42:
            this.waveformLoader = new FfmpegAudioWaveformLoader(r9, r84, new TimelineView$AudioWaveformLoader$$ExternalSyntheticLambda0(this));
            return;
        L35:
            if ("audio/mp3".equals(r2) == true) goto L42;
            if ("audio/mp4a".equals(r2) == true) goto L42;
            if (MediaController.AUDIO_MIME_TYPE.equals(r2) == true) goto L42;
            Utilities.phoneBookQueue.postRunnable(new TimelineView$AudioWaveformLoader$$ExternalSyntheticLambda1(this));
            return;
        L22:
            if (TimelineView.m20575$$Nest$fgetcollageTracks(r8).isEmpty() == true) goto L25;
            r5 = TimelineView.m20584$$Nest$mgetBaseDuration(r8);
            goto L28
        L25:
            if (TimelineView.m20579$$Nest$fgethasRound(r8) == false) goto L27;
            r5 = TimelineView.m20581$$Nest$fgetroundDuration(r8);
            goto L28
        L27:
            r5 = this.duration * 1000;
        L10:
            e = move-exception;
            FileLog.e(e);
            goto L19
        }

        private /* synthetic */ void lambda$run$0(short[] r1, int r2) {
            receiveData(r1, r2);
        }

        private void receiveData(short[] r6, int r7) {
            int r0 = 0;
        L3:
            if (r0 >= r7) goto L11;
            int r1 = this.loaded;
            int r2 = r1 + r0;
            short[] r3 = this.data;
            if (r2 >= r3.length) goto L11;
            r3[r1 + r0] = r6[r0];
            short r12 = this.max;
            short r22 = r6[r0];
            if (r12 >= r22) goto L10;
            this.max = r22;
        L10:
            r0 = r0 + 1;
        L11:
            this.loaded += r7;
            this.this$0.invalidate();
        }

        private void run() {
            int r1 = this.inputFormat.getInteger("sample-rate");     // Catch: Exception -> L71
            int r12 = Math.round(((this.duration * r1) / this.count) / 5.0f);     // Catch: Exception -> L71
            MediaCodec r3 = MediaCodec.createDecoderByType(this.inputFormat.getString("mime"));     // Catch: Exception -> L71
            if (r3 != null) goto L6;
            return;
        L6:
            r3.configure(this.inputFormat, null, null, 0);     // Catch: Exception -> L71
            r3.start();     // Catch: Exception -> L71
            r3.getInputBuffers();     // Catch: Exception -> L71
            r3.getOutputBuffers();     // Catch: Exception -> L71
            short[] r2 = new short[32];     // Catch: Exception -> L71
            boolean r122 = false;
            int r14 = 0;
            int r15 = 0;
            int r16 = 0;
            short r17 = 0;
            int r13 = -1;
        L7:
            MediaCodec.BufferInfo r4 = new MediaCodec.BufferInfo();     // Catch: Exception -> L71
            int r42 = r3.dequeueInputBuffer(2500);     // Catch: Exception -> L71
            if (r42 < 0) goto L13;
            int r8 = this.extractor.readSampleData(r3.getInputBuffer(r42), 0);     // Catch: Exception -> L71
            if (r8 >= 0) goto L12;
            long r11 = 2500;
            r3.queueInputBuffer(r42, 0, 0, 0, 4);     // Catch: Exception -> L71
            MediaCodec.BufferInfo r22 = r4;
            boolean r20 = true;
        L14:
            if (r13 < 0) goto L16;
            r3.getOutputBuffer(r13).position(0);     // Catch: Exception -> L71
        L16:
            MediaCodec.BufferInfo r7 = r22;
            r13 = r3.dequeueOutputBuffer(r7, r11);     // Catch: Exception -> L71
        L18:
            if (r13 == (-1)) goto L54;
            if (r20 == true) goto L54;
            if (r13 < 0) goto L51;
            ByteBuffer r5 = r3.getOutputBuffer(r13);     // Catch: Exception -> L71
            if (r5 != null) goto L24;
        L47:
            r3.releaseOutputBuffer(r13, false);     // Catch: Exception -> L71
            if ((r7.flags & 4) == 0) goto L53;
            r122 = true;
        L55:
            Object r43 = this.lock;     // Catch: Exception -> L71
            monitor-enter(r43);     // Catch: Exception -> L71
        L61:
            th = move-exception;
            throw th;     // Catch: Exception -> L71
        L58:
            if (this.stop == true) goto L59;
            monitor-exit(r43);     // Catch: Throwable -> L61
            if (r122 == true) goto L67;
            if (r14 < this.count) goto L7;
        L67:
            r3.stop();     // Catch: Exception -> L71
            r3.release();     // Catch: Exception -> L71
            this.extractor.release();     // Catch: Exception -> L71
            return;
        L59:
            monitor-exit(r43);     // Catch: Throwable -> L61
        L53:
            r13 = r3.dequeueOutputBuffer(r7, r11);     // Catch: Exception -> L71
            goto L18
        L24:
            if (r7.size <= 0) goto L47;
            int r6 = r16;
        L27:
            if (r5.remaining() <= 0) goto L45;
            byte r82 = r5.get();     // Catch: Exception -> L71
            short r83 = (short) ((r82 & UByte.MAX_VALUE) | ((r5.get() & UByte.MAX_VALUE) << 8));     // Catch: Exception -> L71
            if (r6 < r12) goto L39;
            r2[r14 - r15] = r17;     // Catch: Exception -> L71
            r14 = r14 + 1;     // Catch: Exception -> L71
            int r62 = r14 - r15;     // Catch: Exception -> L71
            if (r62 < r2.length) goto L33;
        L34:
            short[] r9 = new short[r2.length];     // Catch: Exception -> L71
            AndroidUtilities.runOnUIThread(new TimelineView$AudioWaveformLoader$$ExternalSyntheticLambda2(this, r2, r62));     // Catch: Exception -> L71
            r2 = r9;
            r15 = r14;
        L36:
            if (r14 >= this.data.length) goto L37;
            r6 = 0;
            short r92 = 0;
        L40:
            if (r92 >= r83) goto L42;
            r17 = r83;
        L43:
            r6 = r6 + 1;     // Catch: Exception -> L71
            if (r5.remaining() < 8) goto L45;
            r5.position(r5.position() + 8);     // Catch: Exception -> L71
            goto L27
        L42:
            r17 = r92;
            goto L43
        L37:
            r16 = 0;
            r17 = 0;
            goto L47
        L33:
            if (r14 < this.count) goto L36;
        L39:
            r92 = r17;
        L45:
            r16 = r6;
            goto L47
        L51:
            if (r13 != (-3)) goto L53;
            r3.getOutputBuffers();     // Catch: Exception -> L71
        L54:
            r122 = r20;
            goto L55
        L12:
            r20 = r122;
            r11 = 2500;
            r22 = r4;
            r3.queueInputBuffer(r42, 0, r8, this.extractor.getSampleTime(), 0);     // Catch: Exception -> L71
            this.extractor.advance();     // Catch: Exception -> L71
            goto L14
        L13:
            r22 = r4;
            r20 = r122;
            r11 = 2500;
        L71:
            e = move-exception;
            FileLog.e(e);
        }

        public void destroy() {
            FfmpegAudioWaveformLoader r0 = this.waveformLoader;
            if (r0 == null) goto L5;
            r0.destroy();
        L5:
            Utilities.phoneBookQueue.cancelRunnable(new TimelineView$AudioWaveformLoader$$ExternalSyntheticLambda1(this));
            Object r02 = this.lock;
            monitor-enter(r02);
            this.stop = true;     // Catch: Throwable -> L11
            monitor-exit(r02);     // Catch: Throwable -> L11
            return;
        L11:
            th = move-exception;
            throw th;
        }

        public short getBar(int r1) {
            return this.data[r1];
        }

        public int getCount() {
            return this.count;
        }

        public int getLoadedCount() {
            return this.loaded;
        }

        public short getMaxBar() {
            return this.max;
        }
    }

    public interface TimelineDelegate {
        default void onAudioLeftChange(float r1) {
        }

        default void onAudioOffsetChange(long r1) {
        }

        default void onAudioRemove() {
        }

        default void onAudioRightChange(float r1) {
        }

        default void onAudioVolumeChange(float r1) {
        }

        default void onProgressChange(long r1, boolean r3) {
        }

        default void onProgressDragChange(boolean r1) {
        }

        default void onRoundLeftChange(float r1) {
        }

        default void onRoundOffsetChange(long r1) {
        }

        default void onRoundRemove() {
        }

        default void onRoundRightChange(float r1) {
        }

        default void onRoundSelectChange(boolean r1) {
        }

        default void onRoundVolumeChange(float r1) {
        }

        default void onVideoLeftChange(int r1, float r2) {
        }

        void onVideoLeftChange(boolean r1, float r2);

        default void onVideoOffsetChange(int r1, long r2) {
        }

        default void onVideoRightChange(int r1, float r2) {
        }

        default void onVideoRightChange(boolean r1, float r2) {
        }

        default void onVideoSelected(int r1) {
        }

        default void onVideoVolumeChange(float r1) {
        }

        default void onVideoVolumeChange(int r1, float r2) {
        }
    }

    public class Track {
        final RectF bounds;
        long duration;
        int index;
        boolean isRound;
        float left;
        long offset;
        String path;
        float right;
        private final AnimatedFloat selectedT;
        final /* synthetic */ TimelineView this$0;
        VideoThumbsLoader thumbs;
        float volume;

        /* renamed from: $r8$lambda$d-reSgUC-p8wTxe1xSryvPcN5Fs, reason: not valid java name */
        public static /* synthetic */ void m20586$r8$lambda$dreSgUCp8wTxe1xSryvPcN5Fs(Track r0) {
            r0.lambda$setupThumbs$0();
        }

        /* renamed from: -$$Nest$fgetselectedT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedFloat m20587$$Nest$fgetselectedT(Track r0) {
            return r0.selectedT;
        }

        /* renamed from: -$$Nest$msetupThumbs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20588$$Nest$msetupThumbs(Track r0, boolean r1) {
            r0.setupThumbs(r1);
        }

        /* renamed from: -$$Nest$msetupWaveform, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20589$$Nest$msetupWaveform(Track r0, boolean r1) {
            r0.setupWaveform(r1);
        }

        private Track(TimelineView r5) {
            this.this$0 = r5;
            this.bounds = new RectF();
            this.selectedT = new AnimatedFloat(r5, 360, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        public /* synthetic */ Track(TimelineView r1, TimelineViewIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$setupThumbs$0() {
            VideoThumbsLoader r0 = this.thumbs;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getDuration() <= 0) goto L9;
            this.duration = this.thumbs.getDuration();
            this.this$0.sortCollage();
            return;
        }

        private void setupThumbs(boolean r18) {
            if (this.this$0.getMeasuredWidth() <= 0) goto L16;
            VideoThumbsLoader r1 = this.thumbs;
            if (r1 == null) goto L8;
            if (r18 == true) goto L8;
            return;
        L8:
            Long r2 = null;
            if (r1 == null) goto L11;
            r1.destroy();
            this.thumbs = null;
        L11:
            TimelineView r4 = this.this$0;
            boolean r5 = this.isRound;
            String r6 = this.path;
            int r7 = (TimelineView.m20583$$Nest$fgetw(r4) - TimelineView.m20580$$Nest$fgetpx(this.this$0)) - TimelineView.m20580$$Nest$fgetpx(this.this$0);
            int r8 = AndroidUtilities.dp(38.0f);
            long r9 = this.duration;
            if (r9 <= 2) goto L14;
            r2 = Long.valueOf(r9);
        L14:
            this.thumbs = new VideoThumbsLoader(r4, r5, r6, r7, r8, r2, this.this$0.getMaxScrollDuration(), TimelineView.m20578$$Nest$fgetcoverStart(this.this$0), TimelineView.m20577$$Nest$fgetcoverEnd(this.this$0), new TimelineView$Track$$ExternalSyntheticLambda0(this));
            return;
        }

        private void setupWaveform(boolean r5) {
            int r0 = this.index;
            if (r0 >= 0) goto L5;
            return;
        L5:
            if (r0 >= TimelineView.m20576$$Nest$fgetcollageWaveforms(this.this$0).size()) goto L18;
            AudioWaveformLoader r02 = (AudioWaveformLoader) TimelineView.m20576$$Nest$fgetcollageWaveforms(this.this$0).get(this.index);
            if (this.this$0.getMeasuredWidth() <= 0) goto L17;
            if (r02 == null) goto L12;
            if (r5 == true) goto L12;
            return;
        L12:
            if (r02 == null) goto L14;
            r02.destroy();
        L14:
            TimelineView r03 = this.this$0;
            AudioWaveformLoader r52 = new AudioWaveformLoader(r03, this.path, (r03.getMeasuredWidth() - this.this$0.getPaddingLeft()) - this.this$0.getPaddingRight());
            TimelineView.m20576$$Nest$fgetcollageWaveforms(this.this$0).set(this.index, r52);
            return;
        L17:
            return;
        }
    }

    public class VideoThumbsLoader {
        private final Paint bitmapPaint;
        private Path clipPath;
        private int count;
        private boolean destroyed;
        private long duration;
        private volatile int frameHeight;
        private volatile long frameIterator;
        private volatile int frameWidth;
        private final ArrayList<BitmapFrame> frames;
        private final boolean isRound;
        private boolean loading;
        private MediaMetadataRetriever metadataRetriever;
        private long nextFrame;
        final /* synthetic */ TimelineView this$0;

        public class BitmapFrame {
            private final AnimatedFloat alpha;
            public Bitmap bitmap;
            final /* synthetic */ VideoThumbsLoader this$1;

            public BitmapFrame(VideoThumbsLoader r9, Bitmap r10) {
                this.this$1 = r9;
                this.alpha = new AnimatedFloat(0.0f, r9.this$0, 0, 240, CubicBezierInterpolator.EASE_OUT_QUINT);
                this.bitmap = r10;
            }

            public float getAlpha() {
                return this.alpha.set(1.0f);
            }
        }

        public static /* synthetic */ void $r8$lambda$D8eoBfSXy1CdFOUAUnsZ92A_e_I(VideoThumbsLoader r0, String r1, Long r2, long r3, long r5, int r7, long r8, int r10, Runnable r11) {
            r0.lambda$new$0(r1, r2, r3, r5, r7, r8, r10, r11);
        }

        public static /* synthetic */ void $r8$lambda$MiVREcKV9CnhhVhI8qrTQeMa_fU(VideoThumbsLoader r0) {
            r0.retrieveFrame();
        }

        public static /* synthetic */ void $r8$lambda$bgfkFpYlQX_Ec32XfRrc_g8kn3U(VideoThumbsLoader r0, Bitmap r1) {
            r0.lambda$retrieveFrame$1(r1);
        }

        /* renamed from: -$$Nest$fgetcount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20590$$Nest$fgetcount(VideoThumbsLoader r0) {
            return r0.count;
        }

        /* renamed from: -$$Nest$fgetframes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m20591$$Nest$fgetframes(VideoThumbsLoader r0) {
            return r0.frames;
        }

        public VideoThumbsLoader(TimelineView r14, boolean r15, String r16, int r17, int r18, Long r19, long r20, long r22, long r24, Runnable r26) {
            this.this$0 = r14;
            this.frames = new ArrayList();
            this.loading = false;
            this.bitmapPaint = new Paint(3);
            this.isRound = r15;
            this.metadataRetriever = new MediaMetadataRetriever();
            Utilities.themeQueue.postRunnable(new TimelineView$VideoThumbsLoader$$ExternalSyntheticLambda1(this, r16, r19, r22, r24, r18, r20, r17, r26));
        }

        private /* synthetic */ void lambda$new$0(String r11, Long r12, long r13, long r15, int r17, long r18, int r20, Runnable r21) {
            long r4 = this.this$0.getMaxScrollDuration();
            int r6 = 0;
            this.metadataRetriever.setDataSource(r11);     // Catch: Exception -> L6
            String r112 = this.metadataRetriever.extractMetadata(9);     // Catch: Exception -> L6
            if (r112 == null) goto L8;
            r4 = Long.parseLong(r112);     // Catch: Exception -> L6
            this.duration = r4;     // Catch: Exception -> L6
        L8:
            String r113 = this.metadataRetriever.extractMetadata(18);     // Catch: Exception -> L6
            if (r113 == null) goto L12;
            int r114 = Integer.parseInt(r113);     // Catch: Exception -> L6
        L45:
            String r0 = this.metadataRetriever.extractMetadata(19);     // Catch: Exception -> L16
            if (r0 == null) goto L18;
            r6 = Integer.parseInt(r0);     // Catch: Exception -> L16
        L18:
            String r02 = this.metadataRetriever.extractMetadata(24);     // Catch: Exception -> L16
            if (r02 == null) goto L27;
            int r03 = Integer.parseInt(r02);     // Catch: Exception -> L16
            if (r03 != 90) goto L24;
        L25:
            int r9 = r6;
            r6 = r114;
            r114 = r9;
            goto L27
        L24:
            if (r03 == 270) goto L25;
        L27:
            if (r12 == null) goto L30;
            r4 = r12.longValue();
            this.duration = r4;
        L30:
            if (r13 != (-1)) goto L32;
        L34:
            if (r114 == 0) goto L37;
            if (r6 == 0) goto L37;
            float r115 = r114 / r6;
        L38:
            float r116 = Utilities.clamp(r115, 1.3333334f, 0.5625f);
            this.frameHeight = Math.max(1, r17);
            this.frameWidth = Math.max(1, (int) Math.ceil(r17 * r116));
            int r117 = (int) Math.ceil(((Math.max(r4, r18) / r18) * r20) / this.frameWidth);
            this.count = r117;
            this.frameIterator = (long) (r4 / r117);
            this.nextFrame = -this.frameIterator;
            if (r13 == (-1)) goto L41;
            this.nextFrame = r13 - this.frameIterator;
        L41:
            load();
            if (r21 == null) goto L49;
            AndroidUtilities.runOnUIThread(r21);
            return;
        L49:
            return;
        L37:
            r115 = 1.0f;
            goto L38
        L32:
            if (r15 == (-1)) goto L34;
            r4 = r15 - r13;
        L16:
            e = move-exception;
            int r92 = r6;
            r6 = r114;
            Exception r118 = e;
            int r04 = r92;
        L26:
            this.metadataRetriever = null;
            FileLog.e(r118);
            r114 = r6;
            r6 = r04;
            goto L27
        L12:
            r114 = 0;
        L6:
            e = move-exception;
            r118 = e;
            r04 = 0;
            goto L26
        }

        private /* synthetic */ void lambda$retrieveFrame$1(Bitmap r1) {
            receiveFrame(r1);
        }

        private void receiveFrame(Bitmap r3) {
            if (this.loading == true) goto L5;
            return;
        L5:
            if (this.destroyed == true) goto L10;
            this.frames.add(new BitmapFrame(this, r3));
            this.loading = false;
            this.this$0.invalidate();
            return;
        }

        private void retrieveFrame() {
            MediaMetadataRetriever r0 = this.metadataRetriever;
            if (r0 != null) goto L5;
            return;
        L5:
            Bitmap r1 = null;
            r1 = r0.getFrameAtTime(this.nextFrame * 1000, 2);     // Catch: Exception -> L13
            if (r1 == null) goto L19;
            Bitmap r02 = Bitmap.createBitmap(this.frameWidth, this.frameHeight, Bitmap.Config.ARGB_8888);     // Catch: Exception -> L13
            Canvas r2 = new Canvas(r02);     // Catch: Exception -> L13
            float r3 = Math.max(this.frameWidth / r1.getWidth(), this.frameHeight / r1.getHeight());     // Catch: Exception -> L13
            Rect r4 = new Rect(0, 0, r1.getWidth(), r1.getHeight());     // Catch: Exception -> L13
            Rect r5 = new Rect((int) ((r02.getWidth() - (r1.getWidth() * r3)) / 2.0f), (int) ((r02.getHeight() - (r1.getHeight() * r3)) / 2.0f), (int) ((r02.getWidth() + (r1.getWidth() * r3)) / 2.0f), (int) ((r02.getHeight() + (r1.getHeight() * r3)) / 2.0f));     // Catch: Exception -> L13
            if (this.isRound == true) goto L11;
        L16:
            r2.drawBitmap(r1, r4, r5, this.bitmapPaint);     // Catch: Exception -> L13
            r1.recycle();     // Catch: Exception -> L13
            r1 = r02;
            goto L19
        L11:
            if (this.clipPath != null) goto L15;
            this.clipPath = new Path();     // Catch: Exception -> L13
        L15:
            this.clipPath.rewind();     // Catch: Exception -> L13
            this.clipPath.addCircle(this.frameWidth / 2.0f, this.frameHeight / 2.0f, Math.min(this.frameWidth, this.frameHeight) / 2.0f, Path.Direction.CW);     // Catch: Exception -> L13
            r2.clipPath(this.clipPath);     // Catch: Exception -> L13
        L19:
            AndroidUtilities.runOnUIThread(new TimelineView$VideoThumbsLoader$$ExternalSyntheticLambda2(this, r1));
            return;
        L13:
            e = move-exception;
            FileLog.e(e);
            goto L19
        }

        public void destroy() {
            this.destroyed = true;
            Utilities.themeQueue.cancelRunnable(new TimelineView$VideoThumbsLoader$$ExternalSyntheticLambda0(this));
            ArrayList<BitmapFrame> r0 = this.frames;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L7;
            BitmapFrame r3 = r0.get(r2);
            r2 = r2 + 1;
            Bitmap r32 = r3.bitmap;
            if (r32 == null) goto L3;
            r32.recycle();
            goto L3
        L7:
            this.frames.clear();
            MediaMetadataRetriever r02 = this.metadataRetriever;
            if (r02 == null) goto L21;
            r02.release();     // Catch: Exception -> L11
            return;
        L11:
            e = move-exception;
            this.metadataRetriever = null;
            FileLog.e(e);
            return;
        }

        public long getDuration() {
            return this.duration;
        }

        public int getFrameWidth() {
            return this.frameWidth;
        }

        public void load() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.metadataRetriever != null) goto L7;
            return;
        L7:
            if (this.frames.size() >= this.count) goto L13;
            this.loading = true;
            this.nextFrame += this.frameIterator;
            Utilities.themeQueue.cancelRunnable(new TimelineView$VideoThumbsLoader$$ExternalSyntheticLambda0(this));
            Utilities.themeQueue.postRunnable(new TimelineView$VideoThumbsLoader$$ExternalSyntheticLambda0(this));
            return;
        }
    }

    public static class WaveformPath extends Path {
        private float lastAudioHeight;
        private float lastAudioSelected;
        private float lastBottom;
        private float lastLeft;
        private float lastMaxBar;
        private float lastRight;
        private long lastScrollDuration;
        private float lastStart;
        private ArrayList<Integer> lastWaveformCounts;
        private ArrayList<Float> lastWaveformLoaded;
        private final int ph;
        private final float[] waveformRadii;

        public WaveformPath() {
            this.ph = AndroidUtilities.dp(10.0f);
            this.waveformRadii = new float[]{r2, r2, r2, r2, 0.0f, 0.0f, 0.0f, 0.0f};
            float r2 = AndroidUtilities.dp(2.0f);
        }

        private boolean eqCount(ArrayList<Integer> r5, ArrayList<AudioWaveformLoader> r6) {
            if (r5 != null) goto L7;
            if (r6 != null) goto L7;
            return true;
        L7:
            if (r5 == null) goto L24;
            if (r6 == null) goto L24;
            if (r5.size() == r6.size()) goto L13;
            return false;
        L13:
            int r1 = 0;
        L15:
            if (r1 >= r5.size()) goto L23;
            int r2 = r5.get(r1).intValue();
            if (r6.get(r1) != null) goto L19;
            int r3 = 0;
        L20:
            if (r2 != r3) goto L21;
            r1 = r1 + 1;
            goto L15
        L21:
            return false;
        L19:
            r3 = r6.get(r1).getCount();
            goto L20
        L23:
            return true;
        L24:
            return false;
        }

        private boolean eqLoadedCounts(ArrayList<Float> r6, ArrayList<AudioWaveformLoader> r7) {
            if (r6 != null) goto L7;
            if (r7 != null) goto L7;
            return true;
        L7:
            if (r6 == null) goto L25;
            if (r7 == null) goto L25;
            if (r6.size() == r7.size()) goto L13;
            return false;
        L13:
            int r1 = 0;
        L15:
            if (r1 >= r6.size()) goto L24;
            float r2 = r6.get(r1).floatValue();
            if (r7.get(r1) != null) goto L19;
            float r3 = 0.0f;
        L21:
            if (r2 != r3) goto L22;
            r1 = r1 + 1;
            goto L15
        L22:
            return false;
        L19:
            r3 = AudioWaveformLoader.m20585$$Nest$fgetanimatedLoaded(r7.get(r1)).set(r7.get(r1).getLoadedCount());
            goto L21
        L24:
            return true;
        L25:
            return false;
        }

        public static int getMaxBar(ArrayList<AudioWaveformLoader> r3) {
            int r0 = 0;
            if (r3 != null) goto L5;
            return 0;
        L5:
            int r1 = 0;
        L7:
            if (r0 >= r3.size()) goto L13;
            if (r3.get(r0) == null) goto L12;
            r1 = r1 + r3.get(r0).getMaxBar();
        L12:
            r0 = r0 + 1;
            goto L7
        L13:
            return r1;
        }

        private void layout(float r13, float r14, float r15, float r16, float r17, float r18, float r19, float r20, AudioWaveformLoader r21) {
            rewind();
            float r2 = Math.round(AndroidUtilities.dpf2(3.3333f));
            int r3 = r21.getCount();
            int r4 = Math.max(0, (int) (((r14 - this.ph) - r13) / r2));
            int r32 = Math.min(r3 - 1, (int) Math.ceil(((this.ph + r15) - r13) / r2));
        L3:
            if (r4 > r32) goto L25;
            float r5 = r4;
            float r6 = ((r5 * r2) + r13) + AndroidUtilities.dp(2.0f);
            short r9 = r21.getBar(r4);
            if (r17 > 0.0f) goto L7;
            float r92 = 0.0f;
        L9:
            if (r5 >= r20) goto L14;
            if ((r4 + 1) <= r20) goto L14;
            r92 = r92 * (r20 - r5);
        L17:
            if (r6 >= r14) goto L19;
        L20:
            r92 = r92 * r16;
            if (r92 <= 0.0f) goto L24;
        L23:
            float r52 = Math.max(r92, AndroidUtilities.lerp(AndroidUtilities.dpf2(0.66f), AndroidUtilities.dpf2(1.5f), r16));
            RectF r93 = AndroidUtilities.rectTmp;
            r93.set(r6, AndroidUtilities.lerp(r19 - r52, r19 - ((r18 + r52) / 2.0f), r16), AndroidUtilities.dpf2(1.66f) + r6, AndroidUtilities.lerp(r19, r19 - ((r18 - r52) / 2.0f), r16));
            addRoundRect(r93, this.waveformRadii, Path.Direction.CW);
        L24:
            r4 = r4 + 1;
            goto L3
        L19:
            if (r6 <= r15) goto L23;
        L14:
            if (r5 <= r20) goto L17;
            r92 = 0.0f;
            goto L17
        L7:
            r92 = ((r9 / r17) * r18) * 0.6f;
            goto L9
        }

        private void layout(float r18, float r19, float r20, float r21, float r22, float r23, float r24, ArrayList<Float> r25, ArrayList<AudioWaveformLoader> r26) {
            rewind();
            float r5 = Math.round(AndroidUtilities.dpf2(3.3333f));
            int r7 = 0;
            int r8 = 0;
        L4:
            if (r7 >= r26.size()) goto L9;
            if (r26.get(r7) == null) goto L8;
            r8 = Math.max(r8, r26.get(r7).getCount());
        L8:
            r7 = r7 + 1;
            goto L4
        L9:
            int r72 = Math.max(0, (int) (((r19 - this.ph) - r18) / r5));
            int r82 = Math.min(r8 - 1, (int) Math.ceil(((r20 + this.ph) - r18) / r5));
        L10:
            if (r72 > r82) goto L43;
            float r9 = r72;
            float r10 = (r18 + (r9 * r5)) + AndroidUtilities.dp(2.0f);
            int r12 = 0;
            int r13 = 0;
        L13:
            if (r12 >= r26.size()) goto L31;
            if (r26.get(r12) != null) goto L17;
        L20:
            short r14 = 0;
        L22:
            if (r9 >= r25.get(r12).floatValue()) goto L27;
            if ((r72 + 1) <= r25.get(r12).floatValue()) goto L27;
            r14 = (short) (r14 * (r25.get(r12).floatValue() - r9));
        L29:
            r13 = r13 + r14;
            r12 = r12 + 1;
        L27:
            if (r9 <= r25.get(r12).floatValue()) goto L29;
            r14 = 0;
            goto L29
        L17:
            if (r72 >= r26.get(r12).getCount()) goto L20;
            r14 = r26.get(r12).getBar(r72);
            goto L22
        L31:
            if (r22 > 0.0f) goto L33;
            float r122 = 0.0f;
        L35:
            if (r10 >= r19) goto L37;
        L38:
            r122 = r122 * r21;
            if (r122 <= 0.0f) goto L42;
        L41:
            float r92 = Math.max(r122, AndroidUtilities.lerp(AndroidUtilities.dpf2(0.66f), AndroidUtilities.dpf2(1.5f), r21));
            RectF r123 = AndroidUtilities.rectTmp;
            r123.set(r10, AndroidUtilities.lerp(r24 - r92, r24 - ((r23 + r92) / 2.0f), r21), AndroidUtilities.dpf2(1.66f) + r10, AndroidUtilities.lerp(r24, r24 - ((r23 - r92) / 2.0f), r21));
            addRoundRect(r123, this.waveformRadii, Path.Direction.CW);
        L42:
            r72 = r72 + 1;
            goto L10
        L37:
            if (r10 <= r20) goto L41;
        L33:
            r122 = ((r13 / r22) * r23) * 0.6f;
            goto L35
        }

        public void check(float r11, float r12, float r13, float r14, float r15, float r16, float r17, ArrayList<AudioWaveformLoader> r18) {
            if (r18 != null) goto L5;
        L52:
            rewind();
            return;
        L5:
            if (r18.isEmpty() == true) goto L52;
            if (Math.abs(this.lastAudioHeight - r15) <= 1.0f) goto L10;
        L27:
            ArrayList<Integer> r0 = this.lastWaveformCounts;
            if (r0 != null) goto L30;
            this.lastWaveformCounts = new ArrayList();
        L31:
            int r02 = 0;
            int r1 = 0;
        L33:
            if (r1 >= r18.size()) goto L39;
            ArrayList<Integer> r2 = this.lastWaveformCounts;
            if (r18.get(r1) != null) goto L37;
            int r8 = 0;
        L38:
            r2.add(Integer.valueOf(r8));
            r1 = r1 + 1;
            goto L33
        L37:
            r8 = r18.get(r1).getCount();
            goto L38
        L39:
            ArrayList<Float> r19 = this.lastWaveformLoaded;
            if (r19 != null) goto L42;
            this.lastWaveformLoaded = new ArrayList();
        L44:
            if (r02 >= r18.size()) goto L50;
            ArrayList<Float> r110 = this.lastWaveformLoaded;
            if (r18.get(r02) != null) goto L48;
            float r22 = 0.0f;
        L49:
            r110.add(Float.valueOf(r22));
            r02 = r02 + 1;
            goto L44
        L48:
            r22 = AudioWaveformLoader.m20585$$Nest$fgetanimatedLoaded(r18.get(r02)).set(r18.get(r02).getLoadedCount());
            goto L49
        L50:
            this.lastStart = r11;
            this.lastLeft = r12;
            this.lastRight = r13;
            this.lastAudioSelected = r14;
            this.lastMaxBar = r16;
            this.lastAudioHeight = r15;
            this.lastBottom = r17;
            layout(r11, r12, r13, r14, r16, r15, r17, this.lastWaveformLoaded, r18);
            return;
        L42:
            r19.clear();
            goto L44
        L30:
            r0.clear();
            goto L31
        L10:
            if (Math.abs(this.lastMaxBar - r16) > 0.01f) goto L27;
            if (Math.abs(this.lastAudioSelected - r14) > 0.1f) goto L27;
            if (Math.abs(this.lastBottom - r17) > 1.0f) goto L27;
            if (Math.abs(this.lastStart - r11) > 1.0f) goto L27;
            if (Math.abs(this.lastLeft - r12) > 1.0f) goto L27;
            if (Math.abs(this.lastRight - r13) > 1.0f) goto L27;
            if (eqCount(this.lastWaveformCounts, r18) == true) goto L27;
            if (eqLoadedCounts(this.lastWaveformLoaded, r18) == true) goto L27;
        }

        public void check(float r11, float r12, float r13, float r14, long r15, float r17, float r18, float r19, AudioWaveformLoader r20) {
            if (r20 != null) goto L6;
            rewind();
            return;
        L6:
            float r0 = AudioWaveformLoader.m20585$$Nest$fgetanimatedLoaded(r20).set(r20.getLoadedCount());
            if (this.lastScrollDuration == r15) goto L9;
        L37:
            ArrayList<Integer> r1 = this.lastWaveformCounts;
            if (r1 != null) goto L40;
            this.lastWaveformCounts = new ArrayList();
        L41:
            this.lastWaveformCounts.add(Integer.valueOf(r20.getCount()));
            ArrayList<Float> r16 = this.lastWaveformLoaded;
            if (r16 != null) goto L44;
            this.lastWaveformLoaded = new ArrayList();
        L45:
            this.lastWaveformLoaded.add(Float.valueOf(r0));
            this.lastStart = r11;
            this.lastLeft = r12;
            this.lastRight = r13;
            this.lastAudioSelected = r14;
            this.lastMaxBar = r18;
            this.lastAudioHeight = r17;
            this.lastBottom = r19;
            layout(r11, r12, r13, r14, r18, r17, r19, AudioWaveformLoader.m20585$$Nest$fgetanimatedLoaded(r20).set(r20.getLoadedCount()), r20);
            return;
        L44:
            r16.clear();
            goto L45
        L40:
            r1.clear();
            goto L41
        L9:
            if (Math.abs(this.lastAudioHeight - r17) > 1.0f) goto L37;
            if (Math.abs(this.lastMaxBar - r18) > 0.01f) goto L37;
            if (Math.abs(this.lastAudioSelected - r14) > 0.1f) goto L37;
            if (Math.abs(this.lastBottom - r19) > 1.0f) goto L37;
            if (Math.abs(this.lastStart - r11) > 1.0f) goto L37;
            if (Math.abs(this.lastLeft - r12) > 1.0f) goto L37;
            if (Math.abs(this.lastRight - r13) > 1.0f) goto L37;
            ArrayList<Integer> r110 = this.lastWaveformCounts;
            if (r110 == null) goto L37;
            if (r110.size() != 1) goto L37;
            ArrayList<Float> r111 = this.lastWaveformLoaded;
            if (r111 != null) goto L29;
        L32:
            float r112 = 0.0f;
        L34:
            if (Math.abs(r112 - r0) > 0.01f) goto L37;
            return;
        L29:
            if (r111.isEmpty() == true) goto L32;
            r112 = this.lastWaveformLoaded.get(0).floatValue();
            goto L34
        }
    }

    public static /* synthetic */ void $r8$lambda$7dOKl1hvlk8EM9X3D86MkwnuF_I(TimelineView r0, ViewGroup r1, Theme.ResourcesProvider r2, BlurringShader.BlurManager r3, View r4) {
        r0.lambda$new$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$A5fAlnWusref9dIDSy0FrWcdj68(TimelineView r0, Float r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$AUApdWJhpBOrBZNv-PmN_JPS4Bk, reason: not valid java name */
    public static /* synthetic */ void m20570$r8$lambda$AUApdWJhpBOrBZNvPmN_JPS4Bk(TimelineView r0, Float r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$BGRgPiwErcqd9MSNMmcPKQzg3wo(TimelineView r0, Float r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ int $r8$lambda$CcgHyMQkOAL1CJP_zrgPRlX6yxQ(Track r2, Track r3) {
        return (int) (r3.duration - r2.duration);
    }

    public static /* synthetic */ void $r8$lambda$FGA0UzlPNwG5QYiZk9rJatCrb80(TimelineView r0) {
        r0.lambda$setupRoundThumbs$8();
    }

    /* renamed from: $r8$lambda$Z7c-zzNuVIfgqNyyqQoHHReVzbI, reason: not valid java name */
    public static /* synthetic */ void m20571$r8$lambda$Z7czzNuVIfgqNyyqQoHHReVzbI(TimelineView r0) {
        r0.lambda$new$3();
    }

    /* renamed from: $r8$lambda$i-kU5uH9yuBjIjAvd2m-xihk0sY, reason: not valid java name */
    public static /* synthetic */ void m20572$r8$lambda$ikU5uH9yuBjIjAvd2mxihk0sY(TimelineView r0, long r1) {
        r0.lambda$setProgressAt$9(r1);
    }

    /* renamed from: $r8$lambda$nNPIy3aLmPG1tBxtW4jG6Sr-sbs, reason: not valid java name */
    public static /* synthetic */ void m20573$r8$lambda$nNPIy3aLmPG1tBxtW4jG6Srsbs(TimelineView r0, Track r1, Float r2) {
        r0.lambda$new$5(r1, r2);
    }

    /* renamed from: $r8$lambda$tuzdNCqKhJP6ePnGRK_DM-QNjHQ, reason: not valid java name */
    public static /* synthetic */ void m20574$r8$lambda$tuzdNCqKhJP6ePnGRK_DMQNjHQ(TimelineView r0) {
        r0.lambda$new$1();
    }

    /* renamed from: -$$Nest$fgetcollageTracks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20575$$Nest$fgetcollageTracks(TimelineView r0) {
        return r0.collageTracks;
    }

    /* renamed from: -$$Nest$fgetcollageWaveforms, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20576$$Nest$fgetcollageWaveforms(TimelineView r0) {
        return r0.collageWaveforms;
    }

    /* renamed from: -$$Nest$fgetcoverEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m20577$$Nest$fgetcoverEnd(TimelineView r2) {
        return r2.coverEnd;
    }

    /* renamed from: -$$Nest$fgetcoverStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m20578$$Nest$fgetcoverStart(TimelineView r2) {
        return r2.coverStart;
    }

    /* renamed from: -$$Nest$fgethasRound, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20579$$Nest$fgethasRound(TimelineView r0) {
        return r0.hasRound;
    }

    /* renamed from: -$$Nest$fgetpx, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20580$$Nest$fgetpx(TimelineView r0) {
        return r0.px;
    }

    /* renamed from: -$$Nest$fgetroundDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m20581$$Nest$fgetroundDuration(TimelineView r2) {
        return r2.roundDuration;
    }

    /* renamed from: -$$Nest$fgetvideoTrack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Track m20582$$Nest$fgetvideoTrack(TimelineView r0) {
        return r0.videoTrack;
    }

    /* renamed from: -$$Nest$fgetw, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20583$$Nest$fgetw(TimelineView r0) {
        return r0.w;
    }

    /* renamed from: -$$Nest$mgetBaseDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m20584$$Nest$mgetBaseDuration(TimelineView r2) {
        return r2.getBaseDuration();
    }

    public TimelineView(Context r26, ViewGroup r27, View r28, Theme.ResourcesProvider r29, BlurringShader.BlurManager r30) {
        super(r26);
        this.collageSelected = 0;
        this.collageWaveforms = new ArrayList();
        this.collageTracks = new ArrayList();
        this.collageFramePaint = new Paint(3);
        this.collageClipPath = new Path();
        this.selectedCollageClipPath = new Path();
        this.maxCount = 1;
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.roundT = new AnimatedFloat(this, 0, 360, r6);
        this.roundSelectedT = new AnimatedFloat(this, 360, r6);
        this.audioT = new AnimatedFloat(this, 0, 360, r6);
        this.audioSelectedT = new AnimatedFloat(this, 360, r6);
        this.waveformMax = new AnimatedFloat(this, 0, 360, r6);
        this.timelineWaveformLoaded = new AnimatedFloat(this, 0, 600, r6);
        this.timelineWaveformMax = new AnimatedFloat(this, 0, 360, r6);
        this.openT = new AnimatedFloat(this, 0, 320, r6);
        this.open = true;
        this.timelineBounds = new RectF();
        this.timelineClipPath = new Path();
        this.timelineWaveformPath = new WaveformPath();
        this.videoBounds = new RectF();
        this.videoFramePaint = new Paint(3);
        this.videoClipPath = new Path();
        this.selectedVideoClipPath = new Path();
        this.roundBounds = new RectF();
        this.roundClipPath = new Path();
        Paint r12 = new Paint(1);
        this.regionPaint = r12;
        Paint r13 = new Paint(1);
        this.regionCutPaint = r13;
        Paint r14 = new Paint(1);
        this.regionHandlePaint = r14;
        Paint r15 = new Paint(1);
        this.progressShadowPaint = r15;
        Paint r0 = new Paint(1);
        this.progressWhitePaint = r0;
        TextPaint r2 = new TextPaint(1);
        this.countTextPaint = r2;
        this.audioBounds = new RectF();
        this.audioClipPath = new Path();
        Paint r3 = new Paint(1);
        this.waveformPaint = r3;
        this.waveformPath = new WaveformPath();
        Paint r4 = new Paint(1);
        this.audioDotPaint = r4;
        TextPaint r5 = new TextPaint(1);
        this.audioAuthorPaint = r5;
        TextPaint r7 = new TextPaint(1);
        this.audioTitlePaint = r7;
        LinearGradient r16 = new LinearGradient(0.0f, 0.0f, 16.0f, 0.0f, new int[]{16777215, -1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.ellipsizeGradient = r16;
        this.ellipsizeMatrix = new Matrix();
        Paint r9 = new Paint(1);
        this.ellipsizePaint = r9;
        this.scroller = new Scroller(getContext());
        this.coverStart = -1;
        this.coverEnd = -1;
        this.loopProgress = new AnimatedFloat(0.0f, this, 0, 340, r6);
        this.loopProgressFrom = -1;
        this.pressHandle = -1;
        this.pressHandleCollageIndex = -1;
        this.pressType = -1;
        this.pressCollageIndex = -1;
        this.dragSpeed = 1.0f;
        this.scrollingVideo = true;
        this.scrollingCollage = -1;
        this.scrolling = false;
        this.selectedVideoRadii = new float[8];
        this.previewContainer = r28;
        this.resourcesProvider = r29;
        r4.setColor(Integer.MAX_VALUE);
        r5.setTextSize(AndroidUtilities.dp(12.0f));
        r5.setTypeface(AndroidUtilities.bold());
        r5.setColor(-1);
        r7.setTextSize(AndroidUtilities.dp(12.0f));
        r7.setColor(-1);
        r3.setColor(1090519039);
        r9.setShader(r16);
        r9.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        r12.setColor(-1);
        r12.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, AndroidUtilities.dp(1.0f), 436207616);
        r2.setTextSize(AndroidUtilities.dp(12.0f));
        r2.setColor(-1);
        r2.setShadowLayer(AndroidUtilities.dp(2.0f), 0.0f, AndroidUtilities.dp(2.0f), TLObject.FLAG_30);
        r2.setTypeface(AndroidUtilities.bold());
        r13.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r14.setColor(-16777216);
        r0.setColor(-1);
        r15.setColor(637534208);
        this.timelineText = new Text(LocaleController.getString(R.string.StoryTimeline), 12.0f, AndroidUtilities.bold());
        Drawable r02 = getContext().getResources().getDrawable(R.drawable.timeline).mutate();
        this.timelineIcon = r02;
        PorterDuff.Mode r42 = PorterDuff.Mode.SRC_IN;
        r02.setColorFilter(new PorterDuffColorFilter(-1, r42));
        Drawable r03 = getContext().getResources().getDrawable(R.drawable.filled_widget_music).mutate();
        this.audioIcon = r03;
        r03.setColorFilter(new PorterDuffColorFilter(-1, r42));
        this.blurManager = r30;
        this.backgroundBlur = new BlurringShader.StoryBlurDrawer(r30, this, 0);
        this.audioBlur = new BlurringShader.StoryBlurDrawer(r30, this, 3);
        this.audioWaveformBlur = new BlurringShader.StoryBlurDrawer(r30, this, 4);
        this.onLongPress = new TimelineView$$ExternalSyntheticLambda0(this, r27, r29, r30, r28);
    }

    private int detectHandle(MotionEvent r23) {
        float r1 = r23.getX();
        float r2 = r23.getY();
        long r3 = Math.min(getBaseDuration(), getMaxScrollDuration());
        float r5 = Utilities.clamp(this.progress, getBaseDuration(), 0);
        Track r6 = this.collageMain;
        if (r6 == null) goto L6;
        float r7 = r6.offset + (r6.left * r6.duration);
    L10:
        float r32 = r3;
        float r4 = (this.px + this.ph) + (this.sw * (((r5 + r7) - this.scroll) / r32));
        int r62 = 0;
        if (this.isCover == true) goto L18;
        if (r1 < (r4 - AndroidUtilities.dp(12.0f))) goto L18;
        if (r1 > (r4 + AndroidUtilities.dp(12.0f))) goto L18;
        return 0;
    L18:
        if (this.videoTrack != null) goto L20;
    L22:
        boolean r42 = false;
    L23:
        float r9 = 4.0f;
        if (this.collageTracks.isEmpty() == false) goto L26;
    L30:
        boolean r8 = false;
    L32:
        if (this.hasRound == false) goto L45;
        float r10 = (((this.h - this.py) - getVideoHeight()) - AndroidUtilities.dp(4.0f)) - getCollageHeight();
        if (this.collageTracks.isEmpty() == false) goto L36;
        float r11 = 0.0f;
    L38:
        if (r2 <= ((((r10 - AndroidUtilities.dp(r11)) - getRoundHeight()) - AndroidUtilities.dp(4.0f)) - AndroidUtilities.dp(2.0f))) goto L45;
        float r102 = (((this.h - this.py) - getVideoHeight()) - AndroidUtilities.dp(2.0f)) - getCollageHeight();
        if (this.collageTracks.isEmpty() == false) goto L43;
        r9 = 0.0f;
    L43:
        if (r2 >= (r102 - AndroidUtilities.dp(r9))) goto L45;
        boolean r92 = true;
    L46:
        if (r8 == true) goto L48;
        int r232 = 1;
        int r20 = -1;
        if (r42 == false) goto L116;
        int r12 = this.px;
        int r22 = this.ph;
        Track r63 = this.videoTrack;
        float r72 = r63.left;
        long r82 = r63.duration;
        long r103 = this.scroll;
        int r122 = this.sw;
        float r52 = (r12 + r22) + ((((r72 * r82) - r103) / r32) * r122);
        float r13 = (r12 + r22) + ((((r63.right * r82) - r103) / r32) * r122);
        if (this.isCover == false) goto L96;
        if (getBaseDuration() >= getMaxScrollDuration()) goto L89;
    L94:
        return 4;
    L89:
        if (r1 >= (r52 - AndroidUtilities.dp(15.0f))) goto L91;
    L93:
        return 1;
    L91:
        if (r1 > (r13 + AndroidUtilities.dp(15.0f))) goto L93;
    L96:
        if (r1 < (r52 - AndroidUtilities.dp(15.0f))) goto L102;
        if (r1 > (AndroidUtilities.dp(5.0f) + r52)) goto L102;
        return 2;
    L102:
        if (r1 < (r13 - AndroidUtilities.dp(5.0f))) goto L108;
        if (r1 > (AndroidUtilities.dp(15.0f) + r13)) goto L108;
        return 3;
    L108:
        if (r1 >= r52) goto L110;
    L183:
        Track r14 = this.videoTrack;
        if (r14 != null) goto L186;
    L189:
        return r20;
    L186:
        if (r14.duration <= getMaxScrollDuration()) goto L189;
        if (r42 == false) goto L189;
        return r232;
    L110:
        if (r1 > r13) goto L183;
        Track r15 = this.videoTrack;
        if (r15.left <= 0.01f) goto L114;
    L115:
        return 4;
    L114:
        if (r15.right >= 0.99f) goto L183;
    L116:
        if (r92 == false) goto L149;
        int r16 = this.px;
        int r24 = this.ph;
        long r64 = this.roundOffset;
        float r93 = this.roundLeft;
        long r104 = this.roundDuration;
        long r123 = this.scroll;
        float r83 = ((r64 + (r93 * r104)) - r123) / r32;
        int r94 = this.sw;
        float r53 = (r16 + r24) + (r83 * r94);
        float r17 = (r16 + r24) + ((((r64 + (this.roundRight * r104)) - r123) / r32) * r94);
        if (this.roundSelected == true) goto L122;
        if (this.videoTrack == null) goto L122;
    L144:
        if (r1 < r53) goto L183;
        if (r1 > r17) goto L183;
        return 9;
    L122:
        if (r1 < (r53 - AndroidUtilities.dp(15.0f))) goto L128;
        if (r1 > (AndroidUtilities.dp(5.0f) + r53)) goto L128;
        return 10;
    L128:
        if (r1 < (r17 - AndroidUtilities.dp(5.0f))) goto L134;
        if (r1 > (AndroidUtilities.dp(15.0f) + r17)) goto L134;
        return 11;
    L134:
        if (r1 >= r53) goto L136;
    L142:
        int r18 = this.px;
        int r25 = this.ph;
        long r73 = this.roundOffset;
        long r95 = this.scroll;
        int r124 = this.sw;
        r53 = (r18 + r25) + (((r73 - r95) / r32) * r124);
        r17 = (r18 + r25) + ((((r73 + this.roundDuration) - r95) / r32) * r124);
        goto L144
    L136:
        if (r1 > r17) goto L142;
        if (this.videoTrack != null) goto L141;
        return 12;
    L141:
        return 9;
    L149:
        if (this.hasAudio == false) goto L183;
        int r19 = this.px;
        int r26 = this.ph;
        long r65 = this.audioOffset;
        float r96 = this.audioLeft;
        long r105 = this.audioDuration;
        long r125 = this.scroll;
        float r84 = ((r65 + (r96 * r105)) - r125) / r32;
        int r97 = this.sw;
        float r54 = (r19 + r26) + (r84 * r97);
        float r110 = (r19 + r26) + ((((r65 + (this.audioRight * r105)) - r125) / r32) * r97);
        if (this.audioSelected == true) goto L157;
        if (this.videoTrack != null) goto L179;
        if (this.hasRound == false) goto L157;
    L179:
        if (r1 < r54) goto L183;
        if (r1 > r110) goto L183;
        return 5;
    L157:
        if (r1 < (r54 - AndroidUtilities.dp(15.0f))) goto L163;
        if (r1 > (AndroidUtilities.dp(5.0f) + r54)) goto L163;
        return 6;
    L163:
        if (r1 < (r110 - AndroidUtilities.dp(5.0f))) goto L169;
        if (r1 > (AndroidUtilities.dp(15.0f) + r110)) goto L169;
        return 7;
    L169:
        if (r1 >= r54) goto L171;
    L177:
        int r111 = this.px;
        int r27 = this.ph;
        long r74 = this.audioOffset;
        long r98 = this.scroll;
        int r126 = this.sw;
        r54 = (r111 + r27) + (((r74 - r98) / r32) * r126);
        r110 = (r111 + r27) + ((((r74 + this.audioDuration) - r98) / r32) * r126);
        goto L179
    L171:
        if (r1 > r110) goto L177;
        if (this.videoTrack != null) goto L176;
        return 8;
    L176:
        return 5;
    L48:
        if (r62 >= this.collageTracks.size()) goto L81;
        Track r85 = this.collageTracks.get(r62);
        RectF r99 = AndroidUtilities.rectTmp;
        r99.set(r85.bounds);
        r99.inset(-AndroidUtilities.dp(2.0f), -AndroidUtilities.dp(2.0f));
        if (r99.contains(r1, r2) == true) goto L51;
        r62 = r62 + 1;
        goto L48
    L51:
        int r28 = this.px;
        int r43 = this.ph;
        long r106 = r85.offset;
        int r910 = this.sw;
        float r55 = (r28 + r43) + ((r106 / r32) * r910);
        float r132 = r85.left;
        long r142 = r85.duration;
        float r75 = (r28 + r43) + (((r106 + (r132 * r142)) / r32) * r910);
        float r112 = (r28 + r43) + (((r106 + (r85.right * r142)) / r32) * r910);
        float r29 = (r28 + r43) + (((r106 + r142) / r32) * r910);
        this.pressHandleCollageIndex = r62;
        if (r1 < (r75 - AndroidUtilities.dp(15.0f))) goto L58;
        if (r1 > (AndroidUtilities.dp(5.0f) + r75)) goto L58;
        return 13;
    L58:
        if (r1 < (r112 - AndroidUtilities.dp(5.0f))) goto L64;
        if (r1 > (AndroidUtilities.dp(15.0f) + r112)) goto L64;
        return 14;
    L64:
        if (r1 < r75) goto L74;
        if (r1 > r112) goto L74;
        if (r85.left <= 0.01f) goto L70;
        return 15;
    L70:
        if (r85.right >= 0.99f) goto L74;
        return 15;
    L74:
        if (r1 >= r55) goto L76;
    L79:
        return -1;
    L76:
        if (r1 > r29) goto L79;
        return 16;
    L81:
        r232 = 1;
        r20 = -1;
        goto L183
    L36:
        r11 = 4.0f;
    L45:
        r92 = false;
        goto L46
    L26:
        if (r2 <= ((((((this.h - this.py) - getVideoHeight()) - AndroidUtilities.dp(4.0f)) - getCollageHeight()) - AndroidUtilities.dp(4.0f)) - AndroidUtilities.dp(2.0f))) goto L30;
        if (r2 >= (((this.h - this.py) - getVideoHeight()) - AndroidUtilities.dp(2.0f))) goto L30;
        r8 = true;
        goto L32
    L20:
        if (r2 <= (((this.h - this.py) - getVideoHeight()) - AndroidUtilities.dp(2.0f))) goto L22;
        r42 = true;
        goto L23
    L6:
        if (this.videoTrack != null) goto L8;
        long r66 = this.audioOffset;
    L9:
        r7 = r66;
        goto L10
    L8:
        r66 = 0;
        goto L9
    }

    private void drawProgress(Canvas r9, float r10, float r11, long r12, float r14) {
        if (this.isCover == false) goto L5;
        return;
    L5:
        long r0 = Math.min(getBaseDuration(), getMaxScrollDuration());
        float r122 = Utilities.clamp(r12, getBaseDuration(), 0);
        Track r13 = this.collageMain;
        if (r13 == null) goto L9;
        float r2 = r13.offset + (r13.left * r13.duration);
    L13:
        float r132 = (this.px + this.ph) + (this.sw * (((r122 + r2) - this.scroll) / r0));
        float r123 = (((r11 - r10) / 2.0f) / 2.0f) * (1.0f - r14);
        float r102 = r10 + r123;
        float r112 = r11 - r123;
        this.progressShadowPaint.setAlpha((int) (38.0f * r14));
        this.progressWhitePaint.setAlpha((int) (r14 * 255.0f));
        RectF r124 = AndroidUtilities.rectTmp;
        r124.set(r132 - AndroidUtilities.dpf2(1.5f), r102, AndroidUtilities.dpf2(1.5f) + r132, r112);
        r124.inset(-AndroidUtilities.dpf2(0.66f), -AndroidUtilities.dpf2(0.66f));
        r9.drawRoundRect(r124, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.progressShadowPaint);
        r124.set(r132 - AndroidUtilities.dpf2(1.5f), r102, r132 + AndroidUtilities.dpf2(1.5f), r112);
        r9.drawRoundRect(r124, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.progressWhitePaint);
        return;
    L9:
        if (this.videoTrack != null) goto L11;
        long r22 = this.audioOffset;
    L12:
        r2 = r22;
        goto L13
    L11:
        r22 = 0;
        goto L12
    }

    private void drawRegion(Canvas r15, Paint r16, float r17, float r18, float r19, float r20, float r21) {
        if (r21 > 0.0f) goto L5;
        return;
    L5:
        RectF r9 = AndroidUtilities.rectTmp;
        float r10 = 10.0f;
        r9.set(r19 - AndroidUtilities.dp(10.0f), r17, r20 + AndroidUtilities.dp(10.0f), r18);
        r15.saveLayerAlpha(0.0f, 0.0f, this.w, this.h, 255, 31);
        int r2 = (int) (255.0f * r21);
        this.regionPaint.setAlpha(r2);
        r15.drawRoundRect(r9, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.regionPaint);
        if (this.isCover == false) goto L8;
        float r1 = 2.5f;
    L9:
        r9.inset(AndroidUtilities.dp(r1), AndroidUtilities.dp(2.0f));
        if (this.isCover == false) goto L12;
        r15.drawRoundRect(r9, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.regionCutPaint);
    L13:
        float r12 = AndroidUtilities.dp(2.0f);
        float r5 = AndroidUtilities.dp(10.0f);
        if (r16 == null) goto L16;
        Paint r6 = r16;
    L17:
        this.regionHandlePaint.setAlpha(255);
        r6.setAlpha(r2);
        if (this.isCover == false) goto L20;
        float r22 = 2.0f;
    L21:
        float r23 = r19 - ((AndroidUtilities.dp(r22) - r12) / 2.0f);
        float r7 = r17 + r18;
        float r8 = (r7 - r5) / 2.0f;
        if (this.isCover == false) goto L24;
        float r11 = 2.0f;
    L25:
        float r72 = (r7 + r5) / 2.0f;
        r9.set(r23, r8, r19 - ((AndroidUtilities.dp(r11) + r12) / 2.0f), r72);
        if (this.isCover == true) goto L33;
        r15.drawRoundRect(r9, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), r6);
        if (r16 == null) goto L33;
        if (this.isCover == true) goto L33;
        this.regionHandlePaint.setAlpha((int) (r21 * 48.0f));
        r15.drawRoundRect(r9, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), this.regionHandlePaint);
    L33:
        if (this.isCover == false) goto L35;
        float r24 = 2.5f;
    L36:
        float r25 = r20 + ((AndroidUtilities.dp(r24) - r12) / 2.0f);
        if (this.isCover == false) goto L39;
        r10 = 2.5f;
    L39:
        r9.set(r25, r8, r20 + ((AndroidUtilities.dp(r10) + r12) / 2.0f), r72);
        if (this.isCover == true) goto L44;
        r15.drawRoundRect(r9, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), r6);
        if (r16 == null) goto L44;
        this.regionHandlePaint.setAlpha((int) (r21 * 48.0f));
        r15.drawRoundRect(r9, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), this.regionHandlePaint);
    L44:
        r15.restore();
        return;
    L35:
        r24 = 10.0f;
        goto L36
    L24:
        r11 = 10.0f;
        goto L25
    L20:
        r22 = 10.0f;
        goto L21
    L16:
        r6 = this.regionHandlePaint;
        goto L17
    L12:
        r15.drawRect(r9, this.regionCutPaint);
        goto L13
    L8:
        r1 = 10.0f;
        goto L9
    }

    private float getAudioHeight() {
        float r2 = this.audioSelectedT.set(this.audioSelected);
        return AndroidUtilities.lerp(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(38.0f), r2);
    }

    private long getBaseDuration() {
        Track r0 = this.videoTrack;
        if (r0 != null) goto L5;
        Track r02 = this.collageMain;
        if (r02 == null) goto L11;
        return Math.max(1, r02.duration);
    L11:
        if (this.hasRound == false) goto L15;
        return Math.max(1, this.roundDuration);
    L15:
        return Math.max(1, this.audioDuration);
    L5:
        return Math.max(1, r0.duration);
    }

    private float getCollageHeight() {
        if (this.collageTracks.isEmpty() == false) goto L5;
        return 0.0f;
    L5:
        int r0 = 0;
        float r2 = 0.0f;
    L7:
        if (r0 >= this.collageTracks.size()) goto L12;
        if (r2 <= 0.0f) goto L11;
        r2 = r2 + AndroidUtilities.dp(4.0f);
    L11:
        float r3 = Track.m20587$$Nest$fgetselectedT(this.collageTracks.get(r0)).get();
        r2 = r2 + AndroidUtilities.lerp(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(38.0f), r3);
        r0 = r0 + 1;
        goto L7
    L12:
        return r2;
    }

    private float getRoundHeight() {
        if (this.hasRound == true) goto L6;
        return 0.0f;
    L6:
        float r2 = this.roundSelectedT.set(this.roundSelected);
        return AndroidUtilities.lerp(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(38.0f), r2);
    }

    private float getVideoHeight() {
        Track r2 = this.videoTrack;
        if (r2 != null) goto L6;
        return 0.0f;
    L6:
        float r22 = Track.m20587$$Nest$fgetselectedT(r2).get();
        return AndroidUtilities.lerp(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(38.0f), r22);
    }

    public static int heightDp() {
        return 388;
    }

    private /* synthetic */ void lambda$new$0(Float r2) {
        this.audioVolume = r2.floatValue();
        TimelineDelegate r1 = this.delegate;
        if (r1 == null) goto L6;
        r1.onAudioVolumeChange(r2.floatValue());
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        TimelineDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onAudioRemove();
        return;
    }

    private /* synthetic */ void lambda$new$2(Float r2) {
        this.roundVolume = r2.floatValue();
        TimelineDelegate r1 = this.delegate;
        if (r1 == null) goto L6;
        r1.onRoundVolumeChange(r2.floatValue());
        return;
    }

    private /* synthetic */ void lambda$new$3() {
        TimelineDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onRoundRemove();
        return;
    }

    private /* synthetic */ void lambda$new$4(Float r3) {
        Track r0 = this.videoTrack;
        r0.volume = r3.floatValue();
        TimelineDelegate r2 = this.delegate;
        if (r2 == null) goto L6;
        r2.onVideoVolumeChange(r3.floatValue());
        return;
    }

    private /* synthetic */ void lambda$new$5(Track r2, Float r3) {
        r2.volume = r3.floatValue();
        TimelineDelegate r1 = this.delegate;
        if (r1 == null) goto L6;
        r1.onVideoVolumeChange(r2.index, r3.floatValue());
        return;
    }

    private /* synthetic */ void lambda$new$6(ViewGroup r17, Theme.ResourcesProvider r18, BlurringShader.BlurManager r19, View r20) {
        int r4 = this.pressType;
        if (r4 != 2) goto L10;
        if (this.hasAudio == false) goto L10;
        SliderView r42 = new SliderView(getContext(), 0).setMinMax(0.0f, 1.5f).setValue(this.audioVolume).setOnValueChange(new TimelineView$$ExternalSyntheticLambda4(this));
        long r9 = Math.min(getBaseDuration(), getMaxScrollDuration());
        int r5 = this.w;
        int r13 = this.px;
        int r14 = this.ph;
        ItemOptions.makeOptions(r17, r18, this).addView(r42).addSpaceGap().add(R.drawable.msg_delete, LocaleController.getString(R.string.StoryAudioRemove), new TimelineView$$ExternalSyntheticLambda5(this)).setGravity(5).forceTop(true).translate((-(this.w - Math.min((r5 - r13) - r14, (r13 + r14) + ((((this.audioOffset - this.scroll) + (AndroidUtilities.lerp(this.audioRight, 1.0f, this.audioSelectedT.get()) * this.audioDuration)) / r9) * this.sw)))) + AndroidUtilities.dp(18.0f), this.audioBounds.top).show().setBlurBackground(r19, -r20.getX(), -r20.getY());
        performHapticFeedback(0, 1);     // Catch: Exception -> L31
    L32:
        return;
    L10:
        if (r4 == 1) goto L12;
    L16:
        if (r4 != 0) goto L23;
        if (this.videoTrack == null) goto L23;
        ItemOptions.makeOptions(r17, r18, this).addView(new SliderView(getContext(), 0).setMinMax(0.0f, 1.5f).setValue(this.videoTrack.volume).setOnValueChange(new TimelineView$$ExternalSyntheticLambda8(this))).setGravity(5).forceTop(true).translate(AndroidUtilities.dp(18.0f), this.videoBounds.top).show().setBlurBackground(r19, -r20.getX(), -r20.getY());
        performHapticFeedback(0, 1);     // Catch: Exception -> L31
    L23:
        if (r4 != 3) goto L35;
        int r43 = this.pressCollageIndex;
        if (r43 >= 0) goto L27;
        return;
    L27:
        if (r43 >= this.collageTracks.size()) goto L37;
        Track r44 = this.collageTracks.get(this.pressCollageIndex);
        ItemOptions.makeOptions(r17, r18, this).addView(new SliderView(getContext(), 0).setMinMax(0.0f, 1.5f).setValue(r44.volume).setOnValueChange(new TimelineView$$ExternalSyntheticLambda9(this, r44))).setGravity(5).forceTop(true).translate(AndroidUtilities.dp(18.0f), r44.bounds.top).show().setBlurBackground(r19, -r20.getX(), -r20.getY());
        performHapticFeedback(0, 1);     // Catch: Exception -> L31
        goto L32
    L37:
        return;
    L35:
        return;
    L12:
        if (this.hasRound == false) goto L16;
        SliderView r45 = new SliderView(getContext(), 0).setMinMax(0.0f, 1.5f).setValue(this.roundVolume).setOnValueChange(new TimelineView$$ExternalSyntheticLambda6(this));
        long r92 = Math.min(getBaseDuration(), getMaxScrollDuration());
        int r52 = this.w;
        int r11 = this.px;
        int r12 = this.ph;
        ItemOptions.makeOptions(r17, r18, this).addView(r45).addSpaceGap().add(R.drawable.msg_delete, LocaleController.getString(R.string.StoryRoundRemove), new TimelineView$$ExternalSyntheticLambda7(this)).setGravity(5).forceTop(true).translate((-(this.w - Math.min((r52 - r11) - r12, (r11 + r12) + ((((this.roundOffset - this.scroll) + (AndroidUtilities.lerp(this.roundRight, 1.0f, this.roundSelectedT.get()) * this.roundDuration)) / r92) * this.sw)))) + AndroidUtilities.dp(18.0f), this.roundBounds.top).show().setBlurBackground(r19, -r20.getX(), -r20.getY());
        performHapticFeedback(0, 1);     // Catch: Exception -> L31
    }

    private /* synthetic */ void lambda$setProgressAt$9(long r2) {
        TimelineDelegate r1 = this.delegate;
        if (r1 == null) goto L6;
        r1.onProgressChange(r2, false);
        return;
    }

    private /* synthetic */ void lambda$setupRoundThumbs$8() {
        VideoThumbsLoader r0 = this.roundThumbs;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getDuration() <= 0) goto L9;
        this.roundDuration = this.roundThumbs.getDuration();
        return;
    }

    private long maxSelectDuration() {
        return this.maxCount * 59000;
    }

    private long minAudioSelect() {
        return (long) Math.max(1000.0f, Math.min(getBaseDuration(), 59000) * 0.15f);
    }

    private void moveAudioOffset(float r19) {
        Track r2 = this.videoTrack;
        if (r2 != null) goto L10;
        if (this.hasRound == true) goto L10;
        long r5 = this.audioOffset;
        long r1 = Utilities.clamp(r5 + ((long) r19), 0, -(this.audioDuration - Math.min(getBaseDuration(), getMaxScrollDuration())));
        this.audioOffset = r1;
        float r12 = r1 - r5;
        this.audioLeft = Utilities.clamp(this.audioLeft - (r12 / this.audioDuration), 1.0f, 0.0f);
        this.audioRight = Utilities.clamp(this.audioRight - (r12 / this.audioDuration), 1.0f, 0.0f);
        TimelineDelegate r13 = this.delegate;
        if (r13 == null) goto L42;
        r13.onAudioLeftChange(this.audioLeft);
        this.delegate.onAudioRightChange(this.audioRight);
    L42:
        invalidate();
        TimelineDelegate r14 = this.delegate;
        if (r14 == null) goto L45;
        r14.onAudioOffsetChange(this.audioOffset + ((long) (this.audioLeft * this.audioDuration)));
    L45:
        boolean r15 = this.dragged;
        if (r15 == true) goto L63;
        TimelineDelegate r3 = this.delegate;
        if (r3 == null) goto L63;
        r3.onProgressDragChange(true);
        Track r32 = this.videoTrack;
        if (r32 == null) goto L53;
        long r10 = this.audioOffset + ((long) (this.audioLeft * this.audioDuration));
        float r4 = r32.right;
        long r52 = r32.duration;
        long r33 = Utilities.clamp(r10, (long) (r4 * r52), (long) (r32.left * r52));
    L57:
        if (this.videoTrack != null) goto L59;
    L61:
        TimelineDelegate r16 = this.delegate;
        this.progress = r33;
        r16.onProgressChange(r33, false);
        return;
    L59:
        if (Math.abs(this.progress - r33) <= 400) goto L61;
        this.loopProgressFrom = this.progress;
        this.loopProgress.set(1.0f, true);
        goto L61
    L53:
        if (this.hasRound == false) goto L55;
        long r102 = this.audioOffset + ((long) (this.audioLeft * this.audioDuration));
        float r34 = this.roundRight;
        long r42 = this.roundDuration;
        r33 = Utilities.clamp(r102, (long) (r34 * r42), (long) (this.roundLeft * r42));
        goto L57
    L55:
        float r35 = this.audioLeft;
        long r122 = this.audioDuration;
        r33 = Utilities.clamp((long) (r35 * r122), r122, 0);
    L63:
        if (r15 == false) goto L65;
    L66:
        Track r17 = this.videoTrack;
        if (r17 == null) goto L70;
        long r7 = this.audioOffset + ((long) (this.audioLeft * this.audioDuration));
        float r36 = r17.right;
        long r43 = r17.duration;
        this.progress = Utilities.clamp(r7, (long) (r36 * r43), (long) (r17.left * r43));
    L74:
        TimelineDelegate r18 = this.delegate;
        if (r18 == null) goto L79;
        r18.onProgressChange(this.progress, false);
        return;
    L79:
        return;
    L70:
        if (this.hasRound == false) goto L73;
        if (r17 == null) goto L73;
        long r72 = this.audioOffset + ((long) (this.audioLeft * this.audioDuration));
        float r37 = this.roundRight;
        long r44 = r17.duration;
        this.progress = Utilities.clamp(r72, (long) (r37 * r44), (long) (this.roundLeft * r44));
    L73:
        float r110 = this.audioLeft;
        long r53 = this.audioDuration;
        this.progress = Utilities.clamp((long) (r110 * r53), r53, 0);
        goto L74
    L65:
        if (this.scrolling == true) goto L66;
        return;
    L10:
        if (this.audioSelected == false) goto L41;
        if (r2 == null) goto L14;
        float r54 = r2.left;
        long r6 = r2.duration;
    L13:
        float r55 = r54 * r6;
        if (r2 == null) goto L18;
        float r62 = r2.right;
        long r73 = r2.duration;
    L17:
        float r63 = r62 * r73;
        if (r2 == null) goto L21;
        float r74 = (r2.right - r2.left) * r2.duration;
    L22:
        float r22 = this.audioRight;
        long r8 = this.audioDuration;
        long r103 = (long) (r63 - (r8 * r22));
        float r123 = this.audioLeft;
        long r132 = (long) (r55 - (r8 * r123));
        float r23 = Math.min(r22 - r123, r74 / r8);
        long r75 = this.audioOffset;
        long r38 = (long) r19;
        if ((r75 + r38) <= r103) goto L32;
        float r111 = Utilities.clamp(((r63 - r75) - r38) / this.audioDuration, 1.0f, r23);
        this.audioRight = r111;
        float r112 = Utilities.clamp(r111 - r23, 1.0f, 0.0f);
        this.audioLeft = r112;
        float r24 = this.audioRight;
        long r76 = this.audioDuration;
        long r104 = (long) (r63 - (r24 * r76));
        long r113 = (long) (r55 - (r112 * r76));
        if (r104 >= r113) goto L27;
        long r142 = r113;
        long r162 = r104;
    L28:
        this.audioOffset = Utilities.clamp(this.audioOffset + r38, r142, r162);
        TimelineDelegate r114 = this.delegate;
        if (r114 == null) goto L42;
        r114.onAudioLeftChange(this.audioLeft);
        this.delegate.onAudioRightChange(this.audioRight);
        goto L42
    L27:
        r162 = r113;
        r142 = r104;
        goto L28
    L32:
        if ((r75 + r38) >= r132) goto L40;
        float r115 = Utilities.clamp(((r55 - r75) - r38) / this.audioDuration, 1.0f - r23, 0.0f);
        this.audioLeft = r115;
        float r116 = Utilities.clamp(r115 + r23, 1.0f, 0.0f);
        this.audioRight = r116;
        long r77 = this.audioDuration;
        long r117 = (long) (r63 - (r116 * r77));
        long r56 = (long) (r55 - (this.audioLeft * r77));
        if (r117 >= r56) goto L36;
        long r143 = r117;
        long r124 = r56;
    L37:
        this.audioOffset = Utilities.clamp(this.audioOffset + r38, r124, r143);
        TimelineDelegate r118 = this.delegate;
        if (r118 == null) goto L42;
        r118.onAudioLeftChange(this.audioLeft);
        this.delegate.onAudioRightChange(this.audioRight);
        goto L42
    L36:
        r124 = r117;
        r143 = r56;
        goto L37
    L40:
        this.audioOffset = r75 + r38;
        goto L42
    L21:
        r74 = this.roundDuration * (this.roundRight - this.roundLeft);
        goto L22
    L18:
        r62 = this.roundRight;
        r73 = this.roundDuration;
        goto L17
    L14:
        r54 = this.roundLeft;
        r6 = this.roundDuration;
        goto L13
    L41:
        long r105 = this.audioOffset + ((long) r19);
        float r119 = getBaseDuration();
        long r25 = this.audioDuration;
        this.audioOffset = Utilities.clamp(r105, (long) (r119 - (r25 * this.audioRight)), (long) ((-this.audioLeft) * r25));
        goto L42
    }

    private void moveCollageOffset(Track r18, float r19) {
        if (r18 == null) goto L65;
        Track r3 = this.collageMain;
        if (r3 == r18) goto L31;
        if (r3 == null) goto L31;
        if (this.collageSelected != this.collageTracks.indexOf(r18)) goto L30;
        Track r32 = this.collageMain;
        long r5 = r32.duration;
        float r8 = r18.right;
        long r9 = r18.duration;
        long r11 = (long) ((r5 * 1.0f) - (r9 * r8));
        float r14 = r18.left;
        long r13 = (long) ((r5 * 0.0f) - (r9 * r14));
        float r33 = Math.min(r8 - r14, ((r32.right - r32.left) * r5) / r9);
        long r52 = r18.offset;
        long r7 = (long) r19;
        if ((r52 + r7) <= r11) goto L21;
        float r2 = Utilities.clamp((((this.collageMain.right * r2.duration) - r52) - r7) / r18.duration, 1.0f, r33);
        r18.right = r2;
        float r22 = Utilities.clamp(r2 - r33, 1.0f, 0.0f);
        r18.left = r22;
        Track r34 = this.collageMain;
        float r53 = r34.right;
        long r92 = r34.duration;
        float r6 = r18.right;
        long r112 = r18.duration;
        long r54 = (long) ((r53 * r92) - (r6 * r112));
        long r23 = (long) ((r34.left * r92) - (r22 * r112));
        if (r54 >= r23) goto L16;
        long r12 = r23;
        long r142 = r54;
    L17:
        r18.offset = Utilities.clamp(r18.offset + r7, r12, r142);
        TimelineDelegate r24 = this.delegate;
        if (r24 == null) goto L31;
        r24.onVideoLeftChange(r18.index, r18.left);
        this.delegate.onVideoRightChange(r18.index, r18.right);
        goto L31
    L16:
        r142 = r23;
        r12 = r54;
        goto L17
    L21:
        if ((r52 + r7) >= r13) goto L29;
        float r25 = Utilities.clamp((((this.collageMain.left * r2.duration) - r52) - r7) / r18.duration, 1.0f - r33, 0.0f);
        r18.left = r25;
        float r26 = Utilities.clamp(r25 + r33, 1.0f, 0.0f);
        r18.right = r26;
        Track r35 = this.collageMain;
        float r55 = r35.right;
        long r93 = r35.duration;
        long r113 = r18.duration;
        long r56 = (long) ((r55 * r93) - (r26 * r113));
        long r27 = (long) ((r35.left * r93) - (r18.left * r113));
        if (r56 >= r27) goto L25;
        long r122 = r27;
        long r143 = r56;
    L26:
        r18.offset = Utilities.clamp(r18.offset + r7, r122, r143);
        TimelineDelegate r28 = this.delegate;
        if (r28 == null) goto L31;
        r28.onVideoLeftChange(r18.index, r18.left);
        this.delegate.onVideoRightChange(r18.index, r18.right);
        goto L31
    L25:
        r143 = r27;
        r122 = r56;
        goto L26
    L29:
        r18.offset = r52 + r7;
        goto L31
    L30:
        long r72 = r18.offset + ((long) r19);
        float r29 = getBaseDuration();
        long r57 = r18.duration;
        r18.offset = Utilities.clamp(r72, (long) (r29 - (r57 * r18.right)), (long) ((-r18.left) * r57));
    L31:
        invalidate();
        TimelineDelegate r210 = this.delegate;
        if (r210 == null) goto L34;
        r210.onVideoOffsetChange(r18.index, r18.offset);
    L34:
        boolean r211 = this.dragged;
        if (r211 == true) goto L51;
        TimelineDelegate r58 = this.delegate;
        if (r58 == null) goto L51;
        r58.onProgressDragChange(true);
        Track r59 = this.collageMain;
        if (r59 == r18) goto L42;
        if (r59 == null) goto L42;
        long r10 = r18.offset + ((long) (r18.left * r18.duration));
        float r62 = r59.right;
        long r73 = r59.duration;
        long r510 = Utilities.clamp(r10, (long) (r62 * r73), (long) (r59.left * r73));
    L43:
        Track r74 = this.collageMain;
        if (r74 == r18) goto L49;
        if (r74 == null) goto L49;
        if (Math.abs(this.progress - r510) <= 400) goto L49;
        this.loopProgressFrom = this.progress;
        this.loopProgress.set(1.0f, true);
    L49:
        TimelineDelegate r1 = this.delegate;
        this.progress = r510;
        r1.onProgressChange(r510, false);
        return;
    L42:
        float r511 = r18.left;
        long r82 = r18.duration;
        r510 = Utilities.clamp((long) (r511 * r82), r82, 0);
    L51:
        if (r211 == false) goto L53;
    L54:
        Track r212 = this.collageMain;
        if (r212 == r18) goto L58;
        if (r212 == null) goto L58;
        long r83 = r18.offset + ((long) (r18.left * r18.duration));
        float r15 = r212.right;
        long r4 = r212.duration;
        this.progress = Utilities.clamp(r83, (long) (r15 * r4), (long) (r212.left * r4));
    L59:
        TimelineDelegate r16 = this.delegate;
        if (r16 == null) goto L64;
        r16.onProgressChange(this.progress, false);
        return;
    L64:
        return;
    L58:
        float r213 = r18.left;
        long r63 = r18.duration;
        this.progress = Utilities.clamp((long) (r213 * r63), r63, 0);
        goto L59
    L53:
        if (this.scrolling == true) goto L54;
        return;
    }

    private void moveRoundOffset(float r23) {
        Track r2 = this.videoTrack;
        if (r2 != null) goto L8;
        long r5 = this.roundOffset;
        long r1 = Utilities.clamp(r5 + ((long) r23), 0, -(this.roundDuration - Math.min(getBaseDuration(), getMaxScrollDuration())));
        this.roundOffset = r1;
        float r12 = r1 - r5;
        this.roundLeft = Utilities.clamp(this.roundLeft - (r12 / this.roundDuration), 1.0f, 0.0f);
        this.roundRight = Utilities.clamp(this.roundRight - (r12 / this.roundDuration), 1.0f, 0.0f);
        TimelineDelegate r13 = this.delegate;
        if (r13 == null) goto L29;
        r13.onRoundLeftChange(this.roundLeft);
        this.delegate.onRoundRightChange(this.roundRight);
    L29:
        invalidate();
        TimelineDelegate r14 = this.delegate;
        if (r14 == null) goto L32;
        r14.onRoundOffsetChange(this.roundOffset + ((long) (this.roundLeft * this.roundDuration)));
    L32:
        boolean r15 = this.dragged;
        if (r15 == true) goto L47;
        TimelineDelegate r3 = this.delegate;
        if (r3 == null) goto L47;
        r3.onProgressDragChange(true);
        Track r32 = this.videoTrack;
        if (r32 == null) goto L39;
        long r8 = this.roundOffset + ((long) (this.roundLeft * this.roundDuration));
        float r4 = r32.right;
        long r52 = r32.duration;
        long r33 = Utilities.clamp(r8, (long) (r4 * r52), (long) (r32.left * r52));
    L41:
        if (this.videoTrack != null) goto L43;
    L45:
        TimelineDelegate r16 = this.delegate;
        this.progress = r33;
        r16.onProgressChange(r33, false);
        return;
    L43:
        if (Math.abs(this.progress - r33) <= 400) goto L45;
        this.loopProgressFrom = this.progress;
        this.loopProgress.set(1.0f, true);
        goto L45
    L39:
        float r34 = this.roundLeft;
        long r6 = this.roundDuration;
        r33 = Utilities.clamp((long) (r34 * r6), r6, 0);
    L47:
        if (r15 == false) goto L49;
    L50:
        Track r17 = this.videoTrack;
        if (r17 == null) goto L53;
        long r7 = this.roundOffset + ((long) (this.roundLeft * this.roundDuration));
        float r35 = r17.right;
        long r42 = r17.duration;
        this.progress = Utilities.clamp(r7, (long) (r35 * r42), (long) (r17.left * r42));
    L54:
        TimelineDelegate r18 = this.delegate;
        if (r18 == null) goto L59;
        r18.onProgressChange(this.progress, false);
        return;
    L59:
        return;
    L53:
        float r19 = this.roundLeft;
        long r53 = this.roundDuration;
        this.progress = Utilities.clamp((long) (r19 * r53), r53, 0);
        goto L54
    L49:
        if (this.scrolling == true) goto L50;
        return;
    L8:
        if (this.roundSelected == false) goto L28;
        float r54 = r2.right;
        long r62 = r2.duration;
        float r9 = this.roundRight;
        long r10 = this.roundDuration;
        long r122 = (long) ((r62 * r54) - (r10 * r9));
        float r22 = r2.left;
        float r142 = this.roundLeft;
        long r36 = (long) ((r62 * r22) - (r10 * r142));
        float r24 = Math.min(r9 - r142, ((r54 - r22) * r62) / r10);
        long r55 = this.roundOffset;
        long r72 = (long) r23;
        if ((r55 + r72) <= r122) goto L19;
        float r110 = Utilities.clamp((((this.videoTrack.right * r1.duration) - r55) - r72) / this.roundDuration, 1.0f, r24);
        this.roundRight = r110;
        float r111 = Utilities.clamp(r110 - r24, 1.0f, 0.0f);
        this.roundLeft = r111;
        Track r25 = this.videoTrack;
        float r37 = r25.right;
        long r43 = r25.duration;
        float r63 = this.roundRight;
        long r92 = this.roundDuration;
        long r11 = (long) ((r37 * r43) - (r63 * r92));
        long r112 = (long) ((r25.left * r43) - (r111 * r92));
        if (r11 >= r112) goto L14;
        long r182 = r112;
        long r20 = r11;
    L15:
        this.roundOffset = Utilities.clamp(this.roundOffset + r72, r182, r20);
        TimelineDelegate r113 = this.delegate;
        if (r113 == null) goto L29;
        r113.onRoundLeftChange(this.roundLeft);
        this.delegate.onRoundRightChange(this.roundRight);
        goto L29
    L14:
        r20 = r112;
        r182 = r11;
        goto L15
    L19:
        if ((r55 + r72) >= r36) goto L27;
        float r38 = Utilities.clamp((((this.videoTrack.left * r1.duration) - r55) - r72) / this.roundDuration, 1.0f - r24, 0.0f);
        this.roundLeft = r38;
        float r114 = Utilities.clamp(r38 + r24, 1.0f, 0.0f);
        this.roundRight = r114;
        Track r26 = this.videoTrack;
        float r39 = r26.right;
        long r44 = r26.duration;
        long r93 = this.roundDuration;
        long r115 = (long) ((r39 * r44) - (r114 * r93));
        long r116 = (long) ((r26.left * r44) - (this.roundLeft * r93));
        if (r115 >= r116) goto L23;
        long r183 = r116;
        long r202 = r115;
    L24:
        this.roundOffset = Utilities.clamp(this.roundOffset + r72, r183, r202);
        TimelineDelegate r117 = this.delegate;
        if (r117 == null) goto L29;
        r117.onRoundLeftChange(this.roundLeft);
        this.delegate.onRoundRightChange(this.roundRight);
        goto L29
    L23:
        r202 = r116;
        r183 = r115;
        goto L24
    L27:
        this.roundOffset = r55 + r72;
        goto L29
    L28:
        long r64 = this.roundOffset + ((long) r23);
        float r118 = getBaseDuration();
        long r27 = this.roundDuration;
        this.roundOffset = Utilities.clamp(r64, (long) (r118 - (r27 * this.roundRight)), (long) ((-this.roundLeft) * r27));
        goto L29
    }

    private boolean setProgressAt(float r11, boolean r12) {
        if (this.videoTrack == null) goto L5;
    L9:
        long r2 = Math.min(getBaseDuration(), getMaxScrollDuration());
        float r112 = ((r11 - this.px) - this.ph) / this.sw;
        Track r0 = this.collageMain;
        if (r0 == null) goto L12;
        long r6 = (long) (r0.offset + (r0.left * r0.duration));
    L13:
        float r113 = r112 * r2;
        if (r0 == null) goto L17;
    L20:
        long r22 = (long) Utilities.clamp((r113 - r6) + this.scroll, getBaseDuration(), 0.0f);
        Track r114 = this.videoTrack;
        if (r114 == null) goto L27;
        float r02 = r22;
        long r62 = r114.duration;
        if ((r02 / r62) >= r114.left) goto L25;
    L26:
        return false;
    L25:
        if ((r02 / r62) > r114.right) goto L26;
    L27:
        Track r03 = this.collageMain;
        if (r03 == null) goto L35;
        if (r22 >= 0) goto L32;
    L33:
        return false;
    L32:
        if (r22 >= ((long) ((r03.right - r03.left) * r03.duration))) goto L33;
    L35:
        if (this.hasAudio == false) goto L44;
        if (r114 != null) goto L44;
        if (this.collageTracks.isEmpty() == false) goto L44;
        float r115 = r22;
        long r4 = this.audioDuration;
        if ((r115 / r4) >= this.audioLeft) goto L42;
    L43:
        return false;
    L42:
        if ((r115 / r4) > this.audioRight) goto L43;
    L44:
        this.progress = r22;
        invalidate();
        TimelineDelegate r116 = this.delegate;
        if (r116 == null) goto L47;
        r116.onProgressChange(r22, r12);
    L47:
        Runnable r117 = this.askExactSeek;
        if (r117 == null) goto L50;
        AndroidUtilities.cancelRunOnUIThread(r117);
        this.askExactSeek = null;
    L50:
        if (r12 == false) goto L54;
        TimelineView$$ExternalSyntheticLambda3 r118 = new TimelineView$$ExternalSyntheticLambda3(this, r22);
        this.askExactSeek = r118;
        AndroidUtilities.runOnUIThread(r118, 150);
        return true;
    L54:
        return true;
    L17:
        if (this.videoTrack != null) goto L19;
        r6 = this.audioOffset;
        goto L20
    L19:
        r6 = 0;
        goto L20
    L12:
        r6 = 0;
        goto L13
    L5:
        if (this.hasAudio == true) goto L9;
        if (this.collageTracks.isEmpty() == false) goto L9;
        return false;
    }

    private void setupAudioWaveform() {
        if (getMeasuredWidth() > 0) goto L5;
        return;
    L5:
        if (this.waveform != null) goto L7;
    L9:
        this.waveform = new AudioWaveformLoader(this, this.audioPath, (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        this.waveformIsLoaded = false;
        this.waveformMax.set(1.0f, true);
        return;
    L7:
        if (this.resetWaveform == true) goto L9;
    }

    private void setupRoundThumbs() {
        if (getMeasuredWidth() > 0) goto L5;
        return;
    L5:
        if (this.roundThumbs != null) goto L24;
        Track r0 = this.videoTrack;
        if (r0 != null) goto L9;
    L11:
        String r4 = this.roundPath;
        int r02 = this.w;
        int r2 = this.px;
        int r5 = (r02 - r2) - r2;
        int r6 = AndroidUtilities.dp(38.0f);
        long r22 = this.roundDuration;
        if (r22 <= 2) goto L15;
        Long r03 = Long.valueOf(r22);
    L14:
        Long r7 = r03;
        Track r04 = this.videoTrack;
        if (r04 == null) goto L20;
        long r23 = r04.duration;
    L19:
        long r8 = r23;
        this.roundThumbs = new VideoThumbsLoader(this, false, r4, r5, r6, r7, r8, -1, -1, new TimelineView$$ExternalSyntheticLambda1(this));
        return;
    L20:
        r23 = getMaxScrollDuration();
        goto L19
    L15:
        r03 = null;
        goto L14
    L9:
        if (r0.duration >= 1) goto L11;
        return;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.scroller.computeScrollOffset() == false) goto L15;
        int r0 = this.scroller.getCurrX();
        long r1 = Math.min(getBaseDuration(), getMaxScrollDuration());
        if (this.scrollingVideo == false) goto L8;
        this.scroll = (long) Math.max(0.0f, (((r0 - this.px) - this.ph) / this.sw) * r1);
    L12:
        invalidate();
        this.wasScrollX = r0;
        return;
    L8:
        if (this.audioSelected == true) goto L11;
        this.scroller.abortAnimation();
        return;
    L11:
        int r3 = this.px;
        int r5 = this.ph;
        int r6 = this.sw;
        float r12 = r1;
        moveAudioOffset(((((r0 - r3) - r5) / r6) * r12) - ((((this.wasScrollX - r3) - r5) / r6) * r12));
        goto L12
    L15:
        if (this.scrolling == false) goto L20;
        this.scrolling = false;
        TimelineDelegate r8 = this.delegate;
        if (r8 == null) goto L21;
        r8.onProgressDragChange(false);
        return;
    L21:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    public void dispatchDraw(Canvas r58) {
        TimelineView r0 = this;
        Paint r9 = r0.backgroundBlur.getPaint(1.0f);
        float r7 = r0.openT.set(r0.open);
        long r10 = Math.min(r0.getBaseDuration(), r0.getMaxScrollDuration());
        if (r7 >= 1.0f) goto L24;
        float r25 = 1.0f;
        r0.timelineBounds.set(r0.px, (r0.h - r0.py) - AndroidUtilities.dp(28.0f), r0.w - r0.px, r0.h - r0.py);
        r0.timelineClipPath.rewind();
        r0.timelineClipPath.addRoundRect(r0.timelineBounds, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
        r58.saveLayerAlpha(r0.timelineBounds, (int) ((1.0f - r7) * 255.0f), 31);
        r58.clipPath(r0.timelineClipPath);
        if (r0.blurManager.hasRenderNode() == false) goto L7;
        r0.backgroundBlur.drawRect(r58);
        r58.drawColor(855638016);
    L11:
        if (r0.collageWaveforms.isEmpty() == true) goto L18;
        BlurringShader.BlurManager r4 = r0.blurManager;
        if (r4 == null) goto L18;
        if (r4.hasRenderNode() == false) goto L18;
        float r32 = r0.timelineWaveformMax.set(WaveformPath.getMaxBar(r0.collageWaveforms));
        WaveformPath r42 = r0.timelineWaveformPath;
        RectF r5 = r0.timelineBounds;
        r42.check((r0.px + r0.ph) + (((r0.audioOffset - r0.scroll) / r10) * r0.sw), r5.left, r5.right, 0.0f, AndroidUtilities.dp(28.0f), r32, r0.timelineBounds.bottom, r0.collageWaveforms);
        r58.saveLayerAlpha(r0.timelineBounds, 102, 31);
        r58.clipPath(r0.timelineWaveformPath);
        r0.audioWaveformBlur.drawRect(r58);
        r58.restore();
    L23:
        float r2 = ((r0.timelineText.getCurrentWidth() + AndroidUtilities.dp(3.66f)) + r0.timelineIcon.getIntrinsicWidth()) / 2.0f;
        int r3 = (int) (r0.timelineBounds.centerX() - r2);
        int r43 = (int) r0.timelineBounds.centerY();
        Drawable r52 = r0.timelineIcon;
        r52.setBounds(r3, r43 - (r52.getIntrinsicHeight() / 2), r0.timelineIcon.getIntrinsicWidth() + r3, (r0.timelineIcon.getIntrinsicHeight() / 2) + r43);
        r0.timelineIcon.setAlpha(191);
        r0.timelineIcon.draw(r58);
        r0.timelineText.draw(r58, ((r0.timelineBounds.centerX() - r2) + r0.timelineIcon.getIntrinsicWidth()) + AndroidUtilities.dp(3.66f), r43, -1, 0.75f);
        r58.restore();
    L26:
        if (r7 <= 0.0f) goto L431;
        if (r7 >= 1.0f) goto L29;
        int r8 = 1;
        float r12 = 0.0f;
        Canvas r1 = r58;
        r1.saveLayerAlpha(0.0f, 0.0f, r0.getWidth(), r0.getHeight(), (int) (r7 * 255.0f), 31);
        boolean r27 = true;
    L30:
        Track r22 = r0.videoTrack;
        if (r22 == null) goto L33;
        float r33 = r25;
    L34:
        if (r22 == null) goto L42;
        AnimatedFloat r23 = Track.m20587$$Nest$fgetselectedT(r22);
        if (r0.audioSelected == false) goto L38;
    L40:
        boolean r44 = 0;
    L41:
        float r24 = r23.set(r44);
    L43:
        float r45 = r0.h - r0.py;
        float r53 = AndroidUtilities.dp(4.0f);
        float r322 = r24;
        float r31 = r33;
        if (r0.videoTrack == null) goto L106;
        r1.save();
        float r6 = r0.getVideoHeight();
        Track r15 = r0.videoTrack;
        int r34 = r8;
        float r82 = r15.left;
        long r72 = r15.duration;
        float r37 = r12;
        float r122 = ((r72 * r82) * r322) + r37;
        float r13 = ((r15.right * r72) * r322) + r37;
        if (r72 > 0) goto L48;
        float r26 = r37;
    L49:
        int r35 = r0.ph;
        float r28 = r26 - r35;
        if (r72 > 0) goto L52;
        float r152 = r53;
        float r40 = r6;
        float r54 = r37;
    L53:
        float r55 = r54 + r35;
        r0.videoBounds.set(r28, r45 - r40, r55, r45);
        float r46 = r45 - (r40 + (r152 * r31));
        RectF r36 = r0.videoBounds;
        float r62 = (r36.top * r322) + r37;
        float r38 = (r36.bottom * r322) + r37;
        r0.videoClipPath.rewind();
        float r422 = r38;
        r0.videoClipPath.addRoundRect(r0.videoBounds, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
        r1.clipPath(r0.videoClipPath);
        VideoThumbsLoader r29 = r0.videoTrack.thumbs;
        if (r29 == null) goto L92;
        int r210 = r29.getFrameWidth();
        float r73 = r210;
        float r432 = r46;
        int r211 = (int) Math.max(0.0d, Math.floor((r28 - r0.px) / r73));
        float r14 = r62;
        int r39 = (int) Math.min(VideoThumbsLoader.m20590$$Nest$fgetcount(r0.videoTrack.thumbs), Math.ceil(((r55 - r28) - r0.px) / r73) + 1.0d);
        int r47 = (int) r0.videoBounds.top;
        if (VideoThumbsLoader.m20591$$Nest$fgetframes(r0.videoTrack.thumbs).size() < r39) goto L58;
        int r56 = r34;
    L59:
        if (r210 == 0) goto L64;
        if (r56 == 0) goto L64;
        if (r0.videoTrack.isRound == true) goto L64;
        int r63 = r34;
    L66:
        if (r63 != 0) goto L68;
    L73:
        if (r63 == 0) goto L75;
    L81:
        if (r210 == 0) goto L90;
        float r48 = r28;
        int r212 = r211;
    L84:
        if (r212 >= Math.min(VideoThumbsLoader.m20591$$Nest$fgetframes(r0.videoTrack.thumbs).size(), r39)) goto L90;
        VideoThumbsLoader.BitmapFrame r64 = (VideoThumbsLoader.BitmapFrame) VideoThumbsLoader.m20591$$Nest$fgetframes(r0.videoTrack.thumbs).get(r212);
        if (r64.bitmap == null) goto L88;
        int r442 = r212;
        r0.videoFramePaint.setAlpha((int) (r64.getAlpha() * 255.0f));
        r1.drawBitmap(r64.bitmap, r48, r47 - ((int) ((r2.getHeight() - r40) / 2.0f)), r0.videoFramePaint);
    L89:
        r48 = r48 + r73;
        r212 = r442 + 1;
        goto L84
    L88:
        r442 = r212;
    L90:
        if (r56 != 0) goto L93;
        r0.videoTrack.thumbs.load();
    L93:
        r0.selectedVideoClipPath.rewind();
        if (r0.isCover == true) goto L104;
        RectF r213 = AndroidUtilities.rectTmp;
        int r310 = r0.px;
        int r49 = r0.ph;
        float r57 = r310 + r49;
        Track r65 = r0.videoTrack;
        float r74 = r65.left;
        long r311 = r65.duration;
        float r59 = r311 * r74;
        long r75 = r0.scroll;
        float r510 = r59 - r75;
        float r511 = r10;
        float r472 = r510 / r511;
        int r512 = r0.sw;
        float r492 = r122;
        float r123 = r57 + (r472 * r512);
        if (r74 > r37) goto L98;
        float r443 = r123;
        int r124 = r49;
    L99:
        float r125 = r443 - r124;
        float r444 = r13;
        int r132 = r0.h;
        int r133 = r0.py;
        float r134 = (r132 - r133) - r40;
        float r50 = r14;
        float r66 = r65.right;
        float r142 = (r310 + r49) + ((((r311 * r66) - r75) / r511) * r512);
        if (r66 < r25) goto L102;
        int r312 = r49;
    L103:
        r213.set(r125, r134, r142 + r312, r132 - r133);
        r0.selectedVideoClipPath.addRoundRect(r213, r0.selectedVideoRadii, Path.Direction.CW);
        r1.clipPath(r0.selectedVideoClipPath, Region.Op.DIFFERENCE);
        r1.drawColor(1342177280);
    L105:
        r1.restore();
        r45 = r432;
    L108:
        if (r0.collageTracks.isEmpty() == true) goto L181;
        r0.getCollageHeight();
        int r313 = 0;
    L111:
        if (r313 >= r0.collageTracks.size()) goto L179;
        Track r214 = r0.collageTracks.get(r313);
        AnimatedFloat r513 = Track.m20587$$Nest$fgetselectedT(r214);
        if (r0.audioSelected == false) goto L115;
    L119:
        boolean r67 = 0;
    L120:
        float r514 = r513.set(r67);
        Track r68 = r0.collageMain;
        int r76 = r0.px;
        if (r214 == r68) goto L123;
        float r83 = r10;
        float r69 = (r76 + r0.ph) + ((((r214.offset - r0.scroll) + (AndroidUtilities.lerp(r214.left, r37, r514) * r214.duration)) / r83) * r0.sw);
        float r77 = (r0.px + r0.ph) + ((((r214.offset - r0.scroll) + (AndroidUtilities.lerp(r214.right, r25, r514) * r214.duration)) / r83) * r0.sw);
        int r433 = r313;
        float r610 = r69;
    L124:
        r1.save();
        float r314 = AndroidUtilities.lerp(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(38.0f), r514);
        RectF r84 = r214.bounds;
        int r126 = r0.ph;
        r84.set(r610 - r126, r45 - r314, r77 + r126, r45);
        RectF r611 = r214.bounds;
        r50 = r50 + (r611.top * r514);
        r422 = r422 + (r611.bottom * r514);
        long r612 = r214.offset;
        float r127 = r214.left;
        long r135 = r214.duration;
        r492 = r492 + ((r612 + (r127 * r135)) * r514);
        r444 = r444 + ((r612 + (r214.right * r135)) * r514);
        r0.collageClipPath.rewind();
        r0.collageClipPath.addRoundRect(r214.bounds, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
        r1.clipPath(r0.collageClipPath);
        VideoThumbsLoader r315 = r214.thumbs;
        if (r315 == null) goto L165;
        long r515 = r214.duration;
        if (r515 > 0) goto L129;
        VideoThumbsLoader r143 = r315;
        float r85 = r45;
        float r316 = 0.0f;
    L130:
        int r410 = r0.ph;
        float r317 = r316 - r410;
        if (r515 > 0) goto L133;
        float r516 = 0.0f;
    L134:
        float r517 = r516 + r410;
        int r411 = r143.getFrameWidth();
        float r518 = r317 - ((r0.px + r0.ph) + (((r214.offset - r0.scroll) / r10) * r0.sw));
        float r613 = r411;
        double r128 = Math.floor(r518 / r613);
        float r519 = r317;
        int r78 = (int) Math.max(0.0d, r128);
        int r318 = (int) Math.min(VideoThumbsLoader.m20590$$Nest$fgetcount(r214.thumbs), Math.ceil((r517 - r519) / r613) + 1.0d);
        int r412 = (int) r214.bounds.top;
        if (VideoThumbsLoader.m20591$$Nest$fgetframes(r214.thumbs).size() < r318) goto L137;
        int r129 = r34;
    L139:
        if (r129 == 0) goto L147;
        int r136 = r78;
    L142:
        if (r136 >= Math.min(VideoThumbsLoader.m20591$$Nest$fgetframes(r214.thumbs).size(), r318)) goto L147;
        if (((VideoThumbsLoader.BitmapFrame) VideoThumbsLoader.m20591$$Nest$fgetframes(r214.thumbs).get(r136)).bitmap == null) goto L145;
        r136 = r136 + 1;
        goto L142
    L145:
        int r413 = 0;
    L148:
        if (r413 == 0) goto L150;
    L156:
        if (r411 != 0) goto L158;
    L163:
        if (r129 != 0) goto L166;
        r214.thumbs.load();
    L166:
        r0.selectedCollageClipPath.rewind();
        if (r0.isCover == true) goto L177;
        RectF r319 = AndroidUtilities.rectTmp;
        int r414 = r0.px;
        int r520 = r0.ph;
        float r614 = r414 + r520;
        float r79 = r214.left;
        long r1210 = r214.duration;
        long r415 = r0.scroll;
        long r615 = r214.offset;
        float r51 = r85;
        float r86 = r10;
        float r144 = (((r1210 * r79) - r415) + r615) / r86;
        int r87 = r0.sw;
        float r145 = r614 + (r87 * r144);
        if (r79 > 0.0f) goto L171;
        float r473 = r145;
        int r146 = r520;
    L172:
        float r147 = r473 - r146;
        float r474 = r152;
        RectF r153 = r214.bounds;
        Paint r482 = r9;
        float r92 = r153.top;
        long r532 = r10;
        float r215 = r214.right;
        float r102 = (r414 + r520) + (((((r1210 * r215) - r415) + r615) / r86) * r87);
        if (r215 < 1.0f) goto L175;
        int r216 = r520;
    L176:
        r319.set(r147, r92, r102 + r216, r153.bottom);
        r0.selectedCollageClipPath.addRoundRect(r319, r0.selectedVideoRadii, Path.Direction.CW);
        r1.clipPath(r0.selectedCollageClipPath, Region.Op.DIFFERENCE);
        r1.drawColor(1342177280);
    L178:
        r1.restore();
        r45 = r51 - (r314 + (r474 * 1.0f));
        r313 = r433 + 1;
        r152 = r474;
        r9 = r482;
        r10 = r532;
        r25 = 1.0f;
        r37 = 0.0f;
        goto L111
    L175:
        r216 = 0;
        goto L176
    L171:
        r473 = r145;
        r146 = 0;
        goto L172
    L177:
        r51 = r85;
        r482 = r9;
        r532 = r10;
        r474 = r152;
    L158:
        if (r78 >= Math.min(VideoThumbsLoader.m20591$$Nest$fgetframes(r214.thumbs).size(), r318)) goto L163;
        VideoThumbsLoader.BitmapFrame r416 = (VideoThumbsLoader.BitmapFrame) VideoThumbsLoader.m20591$$Nest$fgetframes(r214.thumbs).get(r78);
        if (r416.bitmap == null) goto L162;
        r0.collageFramePaint.setAlpha((int) (r416.getAlpha() * 255.0f));
        r1.drawBitmap(r416.bitmap, r519, r412 - ((int) ((r4.getHeight() - r314) / 2.0f)), r0.collageFramePaint);
    L162:
        r519 = r519 + r613;
        r78 = r78 + 1;
        goto L158
    L150:
        if (r0.blurManager.hasRenderNode() == false) goto L153;
        r0.backgroundBlur.drawRect(r1);
        r1.drawColor(855638016);
        goto L156
    L153:
        if (r9 != null) goto L155;
        r1.drawColor(TLObject.FLAG_30);
        goto L156
    L155:
        r1.drawRect(r214.bounds, r9);
        r1.drawColor(855638016);
    L147:
        r413 = r129;
        goto L148
    L137:
        r129 = 0;
        goto L139
    L133:
        r516 = ((((r214.offset + r515) - r0.scroll) / r10) * r0.sw) + (r0.px + r410);
        goto L134
    L129:
        r143 = r315;
        r85 = r45;
        r316 = (((r214.offset - r0.scroll) / r10) * r0.sw) + (r0.px + r0.ph);
        goto L130
    L165:
        r85 = r45;
        goto L166
    L123:
        float r88 = r76 + r0.ph;
        long r1211 = r214.offset;
        long r616 = r0.scroll;
        float r710 = r10;
        r433 = r313;
        int r320 = r0.sw;
        float r617 = (r320 * ((r1211 - r616) / r710)) + r88;
        r77 = (r76 + r6) + ((((r1211 - r616) + r214.duration) / r710) * r320);
        r610 = r617;
        goto L124
    L115:
        if (r0.roundSelected == true) goto L119;
        if (r0.collageSelected != r313) goto L119;
        r67 = r34;
        goto L120
    L179:
        float r89 = 1.0f;
    L180:
        Paint r483 = r9;
        long r533 = r10;
        float r475 = r152;
        float r93 = r0.roundT.set(r0.hasRound);
        AnimatedFloat r217 = r0.roundSelectedT;
        if (r0.hasRound == true) goto L185;
    L187:
        boolean r321 = 0;
    L188:
        float r218 = r217.set(r321);
        float r323 = r0.getRoundHeight() * r93;
        if (r0.videoTrack == null) goto L191;
    L196:
        float r521 = r218;
    L197:
        float r522 = r521 * r93;
        if (r93 <= 0.0f) goto L259;
        long r618 = r0.roundOffset;
        float r11 = r0.roundLeft;
        long r1212 = r0.roundDuration;
        r492 = r492 + ((r618 + (r1212 * r11)) * r522);
        r444 = r444 + ((r618 + (r0.roundRight * r1212)) * r522);
        Track r103 = r0.videoTrack;
        int r148 = r0.px;
        if (r103 == null) goto L202;
        float r711 = r533;
        float r104 = (r148 + r0.ph) + ((((r618 - r0.scroll) + (AndroidUtilities.lerp(r11, 0.0f, r218) * r0.roundDuration)) / r711) * r0.sw);
        float r154 = r522;
        float r619 = (r0.px + r0.ph) + ((((r0.roundOffset - r0.scroll) + (AndroidUtilities.lerp(r0.roundRight, 1.0f, r218) * r0.roundDuration)) / r711) * r0.sw);
        float r41 = r218;
        float r16 = r323;
        float r324 = r104;
        long r105 = r533;
    L203:
        RectF r219 = r0.roundBounds;
        int r523 = r0.ph;
        r219.set(r324 - r523, r45 - r16, r619 + r523, r45);
        float r417 = r45 - (r16 + (r475 * r93));
        RectF r220 = r0.roundBounds;
        r50 = r50 + (r220.top * r154);
        r422 = r422 + (r220.bottom * r154);
        r0.roundClipPath.rewind();
        r0.roundClipPath.addRoundRect(r0.roundBounds, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
        r1.save();
        r1.clipPath(r0.roundClipPath);
        VideoThumbsLoader r221 = r0.roundThumbs;
        if (r221 == null) goto L250;
        long r524 = r0.roundDuration;
        if (r524 > 0) goto L208;
        float r325 = 0.0f;
    L209:
        int r712 = r0.ph;
        float r326 = r325 - r712;
        if (r524 > 0) goto L212;
        float r525 = 0.0f;
    L213:
        float r526 = r525 + r712;
        int r222 = r221.getFrameWidth();
        Track r620 = r0.videoTrack;
        int r713 = r0.px;
        if (r620 == null) goto L216;
        float r621 = (r713 + r0.ph) + (((r0.roundOffset - r0.scroll) / r105) * r0.sw);
    L217:
        float r714 = r222;
        int r622 = (int) Math.max(0.0d, Math.floor((r326 - r621) / r714));
        int r527 = (int) Math.min(VideoThumbsLoader.m20590$$Nest$fgetcount(r0.roundThumbs), Math.ceil((r526 - r326) / r714) + 1.0d);
        int r1213 = (int) r0.roundBounds.top;
        if (VideoThumbsLoader.m20591$$Nest$fgetframes(r0.roundThumbs).size() < r527) goto L220;
        int r137 = r34;
    L221:
        if (r137 == 0) goto L229;
        int r149 = r622;
    L224:
        if (r149 >= Math.min(VideoThumbsLoader.m20591$$Nest$fgetframes(r0.roundThumbs).size(), r527)) goto L229;
        if (((VideoThumbsLoader.BitmapFrame) VideoThumbsLoader.m20591$$Nest$fgetframes(r0.roundThumbs).get(r149)).bitmap == null) goto L227;
        r149 = r149 + 1;
        goto L224
    L227:
        int r1410 = 0;
    L230:
        if (r1410 == 0) goto L232;
    L234:
        int r19 = r222;
        Paint r223 = r483;
    L239:
        if (r19 != 0) goto L241;
    L247:
        r483 = r223;
        if (r137 != 0) goto L250;
        r0.roundThumbs.load();
    L241:
        if (r622 >= Math.min(VideoThumbsLoader.m20591$$Nest$fgetframes(r0.roundThumbs).size(), r527)) goto L247;
        VideoThumbsLoader.BitmapFrame r1411 = (VideoThumbsLoader.BitmapFrame) VideoThumbsLoader.m20591$$Nest$fgetframes(r0.roundThumbs).get(r622);
        if (r1411.bitmap == null) goto L245;
        Paint r484 = r223;
        r0.videoFramePaint.setAlpha((int) (r1411.getAlpha() * 255.0f));
        r1.drawBitmap(r1411.bitmap, r326, r1213 - ((int) ((r2.getHeight() - r16) / 2.0f)), r0.videoFramePaint);
    L246:
        r326 = r326 + r714;
        r622 = r622 + 1;
        r223 = r484;
        goto L241
    L245:
        r484 = r223;
        goto L246
    L232:
        if (r0.blurManager.hasRenderNode() == false) goto L236;
        r0.backgroundBlur.drawRect(r1);
        r1.drawColor(855638016);
        goto L234
    L236:
        if (r483 != null) goto L238;
        r1.drawColor(TLObject.FLAG_30);
        goto L234
    L238:
        r19 = r222;
        r223 = r483;
        r1.drawRect(r0.roundBounds, r223);
        r1.drawColor(855638016);
    L229:
        r1410 = r137;
        goto L230
    L220:
        r137 = 0;
        goto L221
    L216:
        r621 = r713;
        goto L217
    L212:
        r525 = ((((r0.roundOffset + r524) - r0.scroll) / r105) * r0.sw) + (r0.px + r712);
        goto L213
    L208:
        r325 = (r0.px + r0.ph) + (((r0.roundOffset - r0.scroll) / r105) * r0.sw);
    L250:
        r0.selectedVideoClipPath.rewind();
        RectF r224 = AndroidUtilities.rectTmp;
        int r327 = r0.px;
        int r528 = r0.ph;
        float r623 = r327 + r528;
        float r715 = r0.roundLeft;
        long r1214 = r0.roundDuration;
        long r328 = r0.scroll;
        long r624 = r0.roundOffset;
        float r1412 = ((r1214 * r715) - r328) + r624;
        float r1413 = r105;
        float r292 = r1412 / r1413;
        int r1414 = r0.sw;
        float r155 = r623 + (r292 * r1414);
        if (r715 > 0.0f) goto L253;
        float r192 = r155;
        int r156 = r528;
    L254:
        float r20 = 1.0f - r41;
        float r157 = (r192 - r156) - (r528 * r20);
        float r293 = r89;
        RectF r810 = r0.roundBounds;
        float r452 = r93;
        float r94 = r810.top;
        r533 = r105;
        float r112 = r0.roundRight;
        float r106 = (r327 + r528) + (((((r1214 * r112) - r328) + r624) / r1413) * r1414);
        if (r112 < 1.0f) goto L257;
        int r329 = r528;
    L258:
        r224.set(r157, r94, (r106 + r329) + (r528 * r20), r810.bottom);
        r0.selectedVideoClipPath.addRoundRect(r224, r0.selectedVideoRadii, Path.Direction.CW);
        r1.clipPath(r0.selectedVideoClipPath, Region.Op.DIFFERENCE);
        r1.drawColor(1342177280);
        r1.restore();
        r45 = r417;
    L260:
        float r811 = r0.audioT.set(r0.hasAudio);
        AnimatedFloat r225 = r0.audioSelectedT;
        if (r0.hasAudio == true) goto L263;
    L265:
        boolean r330 = 0;
    L266:
        float r1415 = r225.set(r330);
        float r226 = r0.getAudioHeight() * r811;
        if (r0.videoTrack == null) goto L269;
    L274:
        float r331 = r1415;
    L275:
        float r332 = r331 * r811;
        if (r811 <= 0.0f) goto L348;
        long r529 = r0.audioOffset;
        float r95 = r0.audioLeft;
        long r107 = r0.audioDuration;
        r492 = r492 + ((r529 + (r95 * r107)) * r332);
        r444 = r444 + ((r529 + (r0.audioRight * r107)) * r332);
        Paint r530 = r0.audioBlur.getPaint(r811);
        r1.save();
        if (r0.videoTrack == null) goto L280;
    L283:
        float r158 = r226;
        float r162 = r332;
        long r227 = r533;
        float r96 = r227;
        float r625 = (r0.px + r0.ph) + ((((r0.audioOffset - r0.scroll) + (AndroidUtilities.lerp(r0.audioLeft, 0.0f, r1415) * r0.audioDuration)) / r96) * r0.sw);
        float r716 = (r0.px + r0.ph) + ((((r0.audioOffset - r0.scroll) + (AndroidUtilities.lerp(r0.audioRight, 1.0f, r1415) * r0.audioDuration)) / r96) * r0.sw);
        float r1215 = r625;
    L285:
        float r138 = r716;
        RectF r626 = r0.audioBounds;
        int r717 = r0.ph;
        r626.set(r1215 - r717, r45 - r158, r717 + r138, r45);
        RectF r418 = r0.audioBounds;
        r50 = r50 + (r418.top * r162);
        r422 = r422 + (r418.bottom * r162);
        r0.audioClipPath.rewind();
        r0.audioClipPath.addRoundRect(r0.audioBounds, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
        r1.clipPath(r0.audioClipPath);
        BlurringShader.BlurManager r419 = r0.blurManager;
        if (r419 == null) goto L293;
        if (r419.hasRenderNode() == false) goto L293;
        r0.backgroundBlur.drawRect(r1);
        r1.drawColor(Theme.multAlpha(855638016, r811));
    L297:
        if (r0.waveform == null) goto L308;
        BlurringShader.BlurManager r420 = r0.blurManager;
        if (r420 == null) goto L308;
        if (r420.hasRenderNode() == false) goto L308;
        float r421 = r0.waveformMax.set(r0.waveform.getMaxBar(), !r0.waveformIsLoaded);
        if (r0.waveform.getLoadedCount() <= 0) goto L305;
        boolean r531 = r34;
    L306:
        r0.waveformIsLoaded = r531;
        float r17 = r158;
        double r382 = 0.0d;
        long r159 = r227;
        r0.waveformPath.check((r0.px + r0.ph) + (((r0.audioOffset - r0.scroll) / r227) * r0.sw), r1215, r138, r1415, r159, r17, r421, r0.audioBounds.bottom, r0.waveform);
        long r108 = r159;
        r1.saveLayerAlpha(r0.audioBounds, 102, 31);
        r1.clipPath(r0.waveformPath);
        r0.audioWaveformBlur.drawRect(r1);
        r1.restore();
    L320:
        if (r1415 >= 1.0f) goto L345;
        int r228 = r0.px;
        int r333 = r0.ph;
        long r534 = r108;
        float r718 = r0.audioOffset - r0.scroll;
        float r97 = r0.audioLeft;
        long r109 = r0.audioDuration;
        float r535 = r534;
        int r627 = r0.sw;
        float r423 = (r228 + r333) + (((r718 + (r97 * r109)) / r535) * r627);
        float r98 = (r228 + r333) + ((((r5 - r12) + (r0.audioRight * r109)) / r535) * r627);
        float r229 = (Math.max(r228, r423) + Math.min(r0.w - r0.px, r98)) / 2.0f;
        float r1010 = r0.audioBounds.centerY();
        float r334 = Math.max(0.0f, (Math.min(r0.w - r0.px, r98) - Math.max(r0.px, r423)) - AndroidUtilities.dp(24.0f));
        float r536 = AndroidUtilities.dpf2(13.0f);
        if (r0.audioAuthor == null) goto L324;
    L326:
        float r628 = ((AndroidUtilities.dpf2(3.11f) + r0.audioAuthorWidth) + AndroidUtilities.dpf2(9.66f)) + r0.audioTitleWidth;
    L327:
        float r537 = r536 + r628;
        if (r537 >= r334) goto L330;
        int r113 = r34;
    L331:
        float r230 = r229 - (Math.min(r537, r334) / 2.0f);
        r0.audioIcon.setBounds((int) r230, (int) (r1010 - (AndroidUtilities.dp(13.0f) / 2.0f)), (int) (AndroidUtilities.dp(13.0f) + r230), (int) ((AndroidUtilities.dp(13.0f) / 2.0f) + r1010));
        float r1216 = 1.0f - r1415;
        float r139 = r1216 * 255.0f;
        r0.audioIcon.setAlpha((int) r139);
        r0.audioIcon.draw(r1);
        float r1510 = r230 + AndroidUtilities.dpf2(16.11f);
        int r163 = r113;
        float r1310 = r31;
        float r552 = r322;
        long r114 = r534;
        r1.saveLayerAlpha(0.0f, 0.0f, r0.w, r0.h, 255, 31);
        float r424 = Math.min(r98, r0.w) - AndroidUtilities.dp(12.0f);
        r1.clipRect(r1510, 0.0f, r424, r0.h);
        if (r0.audioAuthor == null) goto L335;
        r1.save();
        r1.translate(r1510 - r0.audioAuthorLeft, r1010 - (r0.audioAuthor.getHeight() / 2.0f));
        r0.audioAuthorPaint.setAlpha((int) (r139 * r811));
        r0.audioAuthor.draw(r1);
        r1.restore();
        r1510 = r1510 + r0.audioAuthorWidth;
    L335:
        if (r0.audioAuthor == null) goto L340;
        if (r0.audioTitle == null) goto L340;
        float r1511 = r1510 + AndroidUtilities.dpf2(3.66f);
        int r231 = r0.audioDotPaint.getAlpha();
        r0.audioDotPaint.setAlpha((int) (r231 * r1216));
        r1.drawCircle(AndroidUtilities.dp(1.0f) + r1511, r1010, AndroidUtilities.dp(1.0f), r0.audioDotPaint);
        r0.audioDotPaint.setAlpha(r231);
        r1510 = (r1511 + AndroidUtilities.dpf2(2.0f)) + AndroidUtilities.dpf2(4.0f);
    L340:
        if (r0.audioTitle == null) goto L342;
        r1.save();
        r1.translate(r1510 - r0.audioTitleLeft, r1010 - (r0.audioTitle.getHeight() / 2.0f));
        r0.audioTitlePaint.setAlpha((int) (r139 * r811));
        r0.audioTitle.draw(r1);
        r1.restore();
    L342:
        if (r163 != 0) goto L344;
        r0.ellipsizeMatrix.reset();
        r0.ellipsizeMatrix.postScale(AndroidUtilities.dpf2(8.0f) / 16.0f, 1.0f);
        r0.ellipsizeMatrix.postTranslate(r424 - AndroidUtilities.dp(8.0f), 0.0f);
        r0.ellipsizeGradient.setLocalMatrix(r0.ellipsizeMatrix);
        RectF r335 = r0.audioBounds;
        r1.drawRect(r424 - AndroidUtilities.dp(8.0f), r335.top, r424, r335.bottom, r0.ellipsizePaint);
    L344:
        r58.restore();
    L346:
        r58.restore();
    L347:
        float r425 = r422;
        float r336 = r50;
        int r18 = r0.px;
        int r232 = r0.ph;
        long r629 = r0.scroll;
        float r99 = r114;
        int r1011 = r0.sw;
        float r538 = (r18 + r232) + (((r492 - r629) / r99) * r1011);
        float r630 = (r18 + r232) + (((r444 - r629) / r99) * r1011);
        if (r0.collageTracks.isEmpty() == true) goto L354;
        float r719 = r336;
        float r1012 = r293;
    L352:
        float r110 = r452;
    L360:
        if (r811 > r382) goto L368;
        if (r110 > r382) goto L368;
        if (r1310 > r382) goto L368;
        if (r293 > r382) goto L368;
    L429:
        if (r27 == false) goto L433;
        r58.restore();
    L433:
        if (r0.dragged == false) goto L491;
        float r812 = 1.0f / (1000.0f / AndroidUtilities.screenRefreshRate);
        float r233 = r0.dragSpeed;
        long r337 = (long) ((((AndroidUtilities.dp(32.0f) / r0.sw) * r114) * r812) * r233);
        if (r0.isCover == false) goto L437;
        r0.dragSpeed = r233 + (r812 * 0.25f);
    L437:
        int r111 = r0.pressHandle;
        int r539 = -1;
        if (r111 != 4) goto L457;
        Track r234 = r0.videoTrack;
        if (r234 == null) goto L457;
        float r115 = r234.left;
        long r631 = r0.scroll;
        long r910 = r234.duration;
        if (r115 >= (r631 / r910)) goto L445;
    L448:
        long r116 = r539 * r337;
        r0.scroll = Utilities.clamp(r631 + r116, r910 - r114, 0);
        r0.progress += r116;
        Track r235 = r0.videoTrack;
        float r117 = (r3 - r631) / r235.duration;
        if (r117 <= 0.0f) goto L451;
        float r1311 = 1.0f;
        float r118 = Math.min(1.0f - r235.right, r117);
    L452:
        Track r236 = r0.videoTrack;
        r236.left = Utilities.clamp(r236.left + r118, r1311, 0.0f);
        Track r237 = r0.videoTrack;
        r237.right = Utilities.clamp(r237.right + r118, r1311, 0.0f);
        TimelineDelegate r119 = r0.delegate;
        if (r119 == null) goto L455;
        r119.onVideoLeftChange(false, r0.videoTrack.left);
        r0.delegate.onVideoRightChange(false, r0.videoTrack.right);
    L455:
        r0.invalidate();
    L492:
        int r120 = r0.getContentHeight();
        if (r0.lastHeight == r120) goto L518;
        r0.lastHeight = r120;
        Runnable r02 = r0.onHeightChange;
        if (r02 == null) goto L519;
        r02.run();
        return;
    L519:
        return;
    L518:
        return;
    L451:
        r1311 = 1.0f;
        r118 = Math.max(0.0f - r235.left, r117);
        goto L452
    L445:
        if (r234.right <= ((r631 + r114) / r910)) goto L447;
        r539 = r34;
        goto L448
    L447:
        r0.dragSpeed = 1.0f;
        r539 = 0;
    L457:
        if (r111 != 8) goto L490;
        float r121 = r0.audioLeft;
        long r632 = r0.audioOffset;
        float r238 = (-r632) + 100;
        long r813 = r0.audioDuration;
        if (r121 >= (r238 / r813)) goto L462;
    L465:
        if (r539 != 0) goto L467;
        r0.dragSpeed = 1.0f;
        goto L492
    L467:
        if (r0.audioSelected == false) goto L472;
        Track r239 = r0.videoTrack;
        if (r239 == null) goto L472;
        long r1217 = r632 - (r539 * r337);
        float r338 = r239.right;
        long r426 = r239.duration;
        r0.audioOffset = Utilities.clamp(r1217, (long) ((r338 * r426) - (r121 * r813)), (long) ((r239.left * r426) - (r0.audioRight * r813)));
    L477:
        float r130 = (-(r0.audioOffset - r632)) / r0.audioDuration;
        if (r130 <= 0.0f) goto L480;
        float r131 = Math.min(1.0f - r0.audioRight, r130);
    L482:
        if (r0.videoTrack != null) goto L484;
        float r240 = r0.progress;
        long r339 = r0.audioDuration;
        float r1218 = 0.0f;
        r0.progress = (long) Utilities.clamp(r240 + (r339 * r131), r339, 0.0f);
    L485:
        r0.audioLeft = Utilities.clamp(r0.audioLeft + r131, 1.0f, r1218);
        r0.audioRight = Utilities.clamp(r0.audioRight + r131, 1.0f, r1218);
        TimelineDelegate r140 = r0.delegate;
        if (r140 == null) goto L488;
        r140.onAudioLeftChange(r0.audioLeft);
        r0.delegate.onAudioRightChange(r0.audioRight);
        r0.delegate.onProgressChange(r0.progress, false);
    L488:
        r0.invalidate();
        goto L492
    L484:
        r1218 = 0.0f;
        goto L485
    L480:
        r131 = Math.max(0.0f - r0.audioLeft, r130);
    L472:
        if (r0.roundSelected == true) goto L474;
    L476:
        r0.audioOffset = Utilities.clamp(r632 - (r539 * r337), 0, -(r813 - Math.min(r0.getBaseDuration(), r0.getMaxScrollDuration())));
        goto L477
    L474:
        if (r0.hasRound == false) goto L476;
        long r1219 = r632 - (r539 * r337);
        float r241 = r0.roundRight;
        long r340 = r0.roundDuration;
        r0.audioOffset = Utilities.clamp(r1219, (long) ((r241 * r340) - (r121 * r813)), (long) ((r0.roundLeft * r340) - (r0.audioRight * r813)));
        goto L477
    L462:
        if (r0.audioRight < ((((-r632) + r114) - 100) / r813)) goto L464;
        r539 = r34;
        goto L465
    L464:
        r0.dragSpeed = 1.0f;
        r539 = 0;
        goto L465
    L490:
        r0.dragSpeed = 1.0f;
        goto L492
    L491:
        r0.dragSpeed = 1.0f;
    L368:
        if (r0.videoTrack == null) goto L370;
    L375:
        float r141 = 1.0f;
    L376:
        Paint r242 = r483;
        r0.drawRegion(r58, r242, r719, r425, r538, r630, r141 * r1012);
        Track r150 = r0.videoTrack;
        if (r150 == null) goto L389;
        if (r0.hasAudio == true) goto L383;
        if (r0.hasRound == false) goto L389;
    L383:
        if (r1415 <= 0.0f) goto L385;
    L386:
        float r540 = (r3 - r4) - r40;
        float r427 = r0.h - r0.py;
        int r341 = r0.ph;
        int r633 = r0.px;
        float r814 = r150.left;
        long r1312 = r150.duration;
        long r243 = r0.scroll;
        int r428 = r0.sw;
        float r720 = (r341 + r633) + ((((r814 * r1312) - r243) / r99) * r428);
        float r634 = (r341 + r633) + ((((r150.right * r1312) - r243) / r99) * r428);
        r0 = this;
        Canvas r151 = r58;
        r0.drawRegion(r151, r242, r540, r427, r720, r634, 0.8f);
    L387:
        TimelineView r635 = r0;
        if (r635.maxCount <= 1) goto L408;
        Track r03 = r635.videoTrack;
        if (r03 == null) goto L407;
        long r244 = r03.duration;
        float r541 = r03.right;
        float r04 = r03.left;
        long r721 = (long) (r244 * (r541 - r04));
        if (r721 <= 68999) goto L407;
        float r1416 = r635.px + r635.ph;
        long r429 = r635.scroll;
        float r05 = ((r04 * r244) - r429) / r99;
        int r06 = r635.sw;
        float r1417 = r1416 + (r06 * r05);
        float r722 = (r4 + r13) + ((((r244 * r541) - r429) / r99) * r06);
        r151.save();
        int r07 = r635.h;
        int r245 = r635.py;
        r151.clipRect(r1417, (r07 - r245) - r40, r722, r07 - r245);
        r635.regionPaint.setAlpha((int) (AndroidUtilities.lerp(0.8f, 1.0f, r552) * 255.0f));
        r34 = 1;
        long r723 = 59000;
        int r1313 = (int) Math.min(r635.maxCount - 1, r721 / 59000);
        int r1418 = 1;
    L401:
        if (r1418 > r1313) goto L406;
        if (Math.min(r723, r721 - (r1418 * r723)) < 1000) goto L406;
        float r08 = r635.px + r635.ph;
        Track r430 = r635.videoTrack;
        float r09 = r08 + ((((((long) (r430.duration * r430.left)) + r2) - r635.scroll) / r99) * r635.sw);
        Canvas r562 = r151;
        r562.drawRect(r09, (r635.h - r635.py) - r40, AndroidUtilities.dp(1.0f) + r09, r635.h - r635.py, r635.regionPaint);
        r151 = r562;
        StringBuilder r246 = new StringBuilder("#");
        r1418 = r1418 + 1;
        r246.append(r1418);
        r151.drawText(r246.toString(), r09 + AndroidUtilities.dp(2.0f), ((r635.h - r635.py) - r40) + AndroidUtilities.dp(14.0f), r635.countTextPaint);
        r723 = 59000;
    L406:
        r151.restore();
    L409:
        float r724 = r635.loopProgress.set(0.0f);
        float r247 = ((r635.h - r635.getContentHeight()) + r635.py) - AndroidUtilities.dpf2(2.3f);
        float r342 = AndroidUtilities.dpf2(4.3f) + (r635.h - r635.py);
        if (r724 <= 0.0f) goto L427;
        long r431 = r635.loopProgressFrom;
        if (r431 != (-1)) goto L426;
        Track r010 = r635.videoTrack;
        if (r010 == null) goto L418;
        float r434 = r010.duration * r010.right;
    L417:
        r431 = (long) r434;
        goto L426
    L418:
        Track r011 = r635.collageMain;
        if (r011 == null) goto L422;
        r434 = r011.duration * (r011.right - r011.left);
        goto L417
    L422:
        if (r635.hasRound == false) goto L425;
        float r012 = r635.roundDuration;
        float r435 = r635.roundRight;
    L424:
        r431 = (long) (r012 * r435);
        goto L426
    L425:
        r012 = r635.audioDuration;
        r435 = r635.audioRight;
    L426:
        r0 = this;
        r0.drawProgress(r151, r247, r342, r431, r724 * r1012);
    L428:
        r0.drawProgress(r58, r247, r342, r0.progress, (1.0f - r724) * r1012);
        goto L429
    L427:
        r0 = r635;
    L407:
        r34 = 1;
        goto L409
    L408:
        r34 = 1;
        goto L409
    L385:
        if (r41 > 0.0f) goto L386;
    L389:
        if (r0.collageMain != null) goto L391;
    L393:
        r151 = r58;
        goto L387
    L391:
        if (r0.collageTracks.size() <= r34) goto L393;
        Track r160 = r0.collageMain;
        RectF r343 = r160.bounds;
        float r436 = r343.top;
        float r344 = r343.bottom;
        float r725 = r0.ph + r0.px;
        long r1314 = r160.offset;
        float r1512 = r160.left;
        long r248 = r160.duration;
        long r437 = r0.scroll;
        int r636 = r0.sw;
        float r726 = r725 + ((((r1314 + (r1512 * r248)) - r437) / r99) * r636);
        float r637 = (r5 + r6) + ((((r1314 + (r160.right * r248)) - r437) / r99) * r636);
        r0 = this;
        r151 = r58;
        r0.drawRegion(r151, r242, r436, r344, r726, r637, 0.8f);
        goto L387
    L370:
        if (r0.hasRound == true) goto L375;
        if (r0.collageTracks.isEmpty() == false) goto L375;
        r141 = AndroidUtilities.lerp(0.6f, 1.0f, r1415) * r811;
        goto L376
    L354:
        if (r0.hasAudio == true) goto L356;
    L358:
        r110 = r452;
        r1012 = Math.max(r1310, r110);
        r719 = r336;
        goto L360
    L356:
        if (r0.videoTrack != null) goto L358;
        r719 = r336;
        r1012 = r811;
        goto L352
    L330:
        r113 = 0;
        goto L331
    L324:
        if (r0.audioTitle != null) goto L326;
        r628 = 0.0f;
        goto L327
    L345:
        r114 = r108;
        r1310 = r31;
        r552 = r322;
        goto L346
    L305:
        r531 = 0;
    L308:
        r108 = r227;
        r382 = 0.0d;
        if (r0.waveform == null) goto L320;
        if (r530 == null) goto L320;
        Paint r249 = r0.audioWaveformBlur.getPaint(r811 * 0.4f);
        if (r249 != null) goto L314;
        r249 = r0.waveformPaint;
        r249.setAlpha((int) (64.0f * r811));
    L314:
        float r182 = r0.waveformMax.set(r0.waveform.getMaxBar(), !r0.waveformIsLoaded);
        if (r0.waveform.getLoadedCount() <= 0) goto L317;
        boolean r345 = r34;
    L318:
        r0.waveformIsLoaded = r345;
        r0.waveformPath.check((r0.px + r0.ph) + (((r0.audioOffset - r0.scroll) / r108) * r0.sw), r1215, r138, r1415, r108, r158, r182, r0.audioBounds.bottom, r0.waveform);
        r108 = r108;
        r1.drawPath(r0.waveformPath, r249);
        goto L320
    L317:
        r345 = 0;
    L293:
        if (r530 != null) goto L295;
        r1.drawColor(Theme.multAlpha(TLObject.FLAG_30, r811));
        goto L297
    L295:
        r1.drawRect(r0.audioBounds, r530);
        r1.drawColor(Theme.multAlpha(855638016, r811));
        goto L297
    L280:
        if (r0.hasRound == true) goto L283;
        if (r0.collageTracks.isEmpty() == false) goto L283;
        float r911 = r0.px + r0.ph;
        r158 = r226;
        r162 = r332;
        r227 = r533;
        float r638 = r227;
        float r193 = (r0.audioOffset - r0.scroll) / r638;
        int r639 = r0.sw;
        r716 = (r6 + r7) + ((((r10 - r12) + r0.audioDuration) / r638) * r639);
        r1215 = r911 + (r193 * r639);
        goto L285
    L348:
        r1310 = r31;
        r552 = r322;
        r114 = r533;
        r382 = 0.0d;
        goto L347
    L269:
        if (r0.hasRound == true) goto L274;
        if (r0.collageTracks.isEmpty() == false) goto L274;
        r331 = 1.0f;
        goto L275
    L263:
        if (r0.audioSelected == false) goto L265;
        r330 = r34;
        goto L266
    L257:
        r329 = 0;
        goto L258
    L253:
        r192 = r155;
        r156 = 0;
        goto L254
    L202:
        r154 = r522;
        r105 = r533;
        r41 = r218;
        float r250 = r148 + r0.ph;
        r16 = r323;
        long r251 = r0.scroll;
        float r346 = r105;
        float r252 = (r618 - r251) / r346;
        int r253 = r0.sw;
        r324 = r250 + (r253 * r252);
        r619 = ((((r618 - r251) + r1212) / r346) * r253) + (r148 + r5);
        goto L203
    L259:
        r41 = r218;
        r293 = r89;
        r452 = r93;
        goto L260
    L191:
        if (r0.hasAudio == true) goto L196;
        if (r0.collageTracks.isEmpty() == false) goto L196;
        r521 = 1.0f;
        goto L197
    L185:
        if (r0.roundSelected == false) goto L187;
        r321 = r34;
        goto L188
    L181:
        r89 = 0.0f;
        goto L180
    L102:
        r312 = 0;
        goto L103
    L98:
        r443 = r123;
        r124 = 0;
        goto L99
    L104:
        r492 = r122;
        r444 = r13;
        r50 = r14;
        goto L105
    L75:
        if (r0.blurManager.hasRenderNode() == false) goto L78;
        r0.backgroundBlur.drawRect(r1);
        r1.drawColor(855638016);
        goto L81
    L78:
        if (r9 != null) goto L80;
        r1.drawColor(TLObject.FLAG_30);
        goto L81
    L80:
        r1.drawRect(r0.videoBounds, r9);
        r1.drawColor(855638016);
    L68:
        if (r211 >= Math.min(VideoThumbsLoader.m20591$$Nest$fgetframes(r0.videoTrack.thumbs).size(), r39)) goto L73;
        if (((VideoThumbsLoader.BitmapFrame) VideoThumbsLoader.m20591$$Nest$fgetframes(r0.videoTrack.thumbs).get(r211)).bitmap == null) goto L71;
        r211 = r211 + 1;
        goto L68
    L71:
        r63 = 0;
    L64:
        r63 = 0;
        goto L66
    L58:
        r56 = 0;
        goto L59
    L92:
        r432 = r46;
        r14 = r62;
        goto L93
    L52:
        r152 = r53;
        r40 = r6;
        r54 = (((r72 - r0.scroll) / r10) * r0.sw) + (r0.px + r35);
        goto L53
    L48:
        r26 = (r0.px + r0.ph) - ((r0.scroll / r10) * r0.sw);
        goto L49
    L106:
        r152 = r53;
        r34 = r8;
        r37 = r12;
        r40 = r37;
        r422 = r40;
        r444 = r422;
        r492 = r444;
        r50 = r492;
        goto L108
    L38:
        if (r0.roundSelected == true) goto L40;
        r44 = r8;
        goto L41
    L42:
        r24 = r12;
        goto L43
    L33:
        r33 = r12;
        goto L34
    L29:
        r12 = 0.0f;
        r8 = 1;
        r1 = r58;
        r27 = false;
        goto L30
    L431:
        r34 = 1;
        r114 = r10;
    L18:
        if (r0.collageWaveforms.isEmpty() == true) goto L23;
        Paint r438 = r0.audioWaveformBlur.getPaint(0.4f);
        if (r438 != null) goto L22;
        r438 = r0.waveformPaint;
        r438.setAlpha(64);
    L22:
        float r3210 = r0.timelineWaveformMax.set(WaveformPath.getMaxBar(r0.collageWaveforms));
        WaveformPath r254 = r0.timelineWaveformPath;
        RectF r347 = r0.timelineBounds;
        r254.check((r0.px + r0.ph) + (((r0.audioOffset - r0.scroll) / r10) * r0.sw), r347.left, r347.right, 0.0f, AndroidUtilities.dp(28.0f), r3210, r0.timelineBounds.bottom, r0.collageWaveforms);
        r58.drawPath(r0.timelineWaveformPath, r438);
        goto L23
    L7:
        if (r9 != null) goto L9;
        r58.drawColor(TLObject.FLAG_30);
        goto L11
    L9:
        r58.drawRect(r0.timelineBounds, r9);
        r58.drawColor(855638016);
        goto L11
    L24:
        r25 = 1.0f;
        goto L26
    }

    public int getContentHeight() {
        float r0 = this.py;
        float r2 = 0.0f;
        if (this.videoTrack == null) goto L5;
        float r1 = getVideoHeight() + AndroidUtilities.dp(4.0f);
    L6:
        float r02 = r0 + r1;
        if (this.collageTracks.isEmpty() == false) goto L9;
        float r12 = 0.0f;
    L10:
        float r03 = r02 + r12;
        if (this.hasRound == false) goto L13;
        float r13 = getRoundHeight() + AndroidUtilities.dp(4.0f);
    L14:
        float r04 = r03 + r13;
        if (this.hasAudio == false) goto L18;
        r2 = AndroidUtilities.dp(4.0f) + getAudioHeight();
    L18:
        return (int) ((r04 + r2) + this.py);
    L13:
        r13 = 0.0f;
        goto L14
    L9:
        r12 = getCollageHeight() + AndroidUtilities.dp(4.0f);
        goto L10
    L5:
        r1 = 0.0f;
        goto L6
    }

    public int getMaxCount() {
        return this.maxCount;
    }

    public long getMaxScrollDuration() {
        if (this.collageTracks.isEmpty() == true) goto L5;
        return 70000;
    L5:
        return Math.max(120000, (long) (maxSelectDuration() * 1.5f));
    }

    public int getTimelineHeight() {
        return AndroidUtilities.lerp((this.py + AndroidUtilities.dp(28.0f)) + this.py, getContentHeight(), this.openT.get());
    }

    public boolean isDragging() {
        return this.dragged;
    }

    public void normalizeScrollByVideo() {
        long r0 = Math.min(getBaseDuration(), getMaxScrollDuration());
        Track r2 = this.videoTrack;
        float r3 = (r2.right + r2.left) / 2.0f;
        long r5 = r2.duration;
        this.scroll = Utilities.clamp((long) ((r3 * r5) - (r0 / 2.0f)), r5 - r0, 0);
        invalidate();
    }

    public boolean onBackPressed() {
        boolean r1 = false;
        if (this.audioSelected == false) goto L11;
        this.audioSelected = false;
        r1 = true;
        if (this.hasRound == false) goto L11;
        if (this.videoTrack != null) goto L11;
        this.roundSelected = true;
        TimelineDelegate r2 = this.delegate;
        if (r2 == null) goto L11;
        r2.onRoundSelectChange(true);
    L11:
        return r1;
    }

    @Override // android.view.View
    public void onMeasure(int r5, int r6) {
        this.audioAuthorPaint.setTextSize(AndroidUtilities.dp(12.0f));
        this.audioTitlePaint.setTextSize(AndroidUtilities.dp(12.0f));
        int r62 = AndroidUtilities.dp(12.0f);
        this.px = r62;
        int r2 = AndroidUtilities.dp(5.0f);
        this.py = r2;
        setPadding(r62, r2, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(5.0f));
        int r52 = View.MeasureSpec.getSize(r5);
        this.w = r52;
        int r63 = AndroidUtilities.dp(heightDp());
        this.h = r63;
        setMeasuredDimension(r52, r63);
        int r53 = AndroidUtilities.dp(10.0f);
        this.ph = r53;
        this.sw = (this.w - (r53 * 2)) - (this.px * 2);
        Track r54 = this.videoTrack;
        if (r54 == null) goto L10;
        if (r54.path == null) goto L10;
        if (r54.thumbs != null) goto L10;
        Track.m20588$$Nest$msetupThumbs(r54, false);
    L10:
        if (this.collageTracks.isEmpty() == true) goto L19;
        ArrayList<Track> r55 = this.collageTracks;
        int r0 = r55.size();
        int r1 = 0;
    L12:
        if (r1 >= r0) goto L19;
        Track r22 = r55.get(r1);
        r1 = r1 + 1;
        Track r23 = r22;
        if (r23.path == null) goto L12;
        if (r23.thumbs != null) goto L12;
        Track.m20588$$Nest$msetupThumbs(r23, false);
        Track.m20589$$Nest$msetupWaveform(r23, false);
    L19:
        if (this.audioPath != null) goto L21;
        return;
    L21:
        if (this.waveform != null) goto L32;
        setupAudioWaveform();
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r23) {
        boolean r2 = false;
        if (this.videoTrack == null) goto L5;
    L11:
        float r1 = this.h - getTimelineHeight();
        if (r23.getAction() == 0) goto L14;
    L16:
        long r3 = System.currentTimeMillis();
        if (r23.getAction() != 0) goto L91;
        Runnable r12 = this.askExactSeek;
        if (r12 == null) goto L21;
        AndroidUtilities.cancelRunOnUIThread(r12);
        this.askExactSeek = null;
    L21:
        this.scroller.abortAnimation();
        this.pressHandleCollageIndex = -1;
        this.pressHandle = detectHandle(r23);
        this.pressType = -1;
        this.pressCollageIndex = -1;
        int r13 = this.h - this.py;
        if (this.open == false) goto L24;
    L26:
        float r4 = 4.0f;
        float r6 = 2.0f;
        if (this.pressType != (-1)) goto L37;
        if (this.videoTrack == null) goto L37;
        float r14 = r13;
        if (r23.getY() < r14) goto L33;
    L35:
        r13 = (int) (r14 - (getVideoHeight() + AndroidUtilities.dp(4.0f)));
        goto L37
    L33:
        if (r23.getY() <= ((r14 - getVideoHeight()) - AndroidUtilities.dp(2.0f))) goto L35;
        this.pressType = 0;
    L37:
        if (this.pressType == (-1)) goto L39;
    L50:
        float r17 = r4;
        float r18 = r6;
    L52:
        if (this.pressType != (-1)) goto L62;
        if (this.hasRound == false) goto L62;
        float r15 = r13;
        if (r23.getY() < r15) goto L58;
    L60:
        r13 = (int) (r15 - (getRoundHeight() + AndroidUtilities.dp(r17)));
        goto L62
    L58:
        if (r23.getY() <= ((r15 - getRoundHeight()) - AndroidUtilities.dp(r18))) goto L60;
        this.pressType = 1;
    L62:
        if (this.pressType == (-1)) goto L64;
    L71:
        this.pressTime = System.currentTimeMillis();
        int r16 = this.pressHandle;
        if (r16 == 0) goto L77;
        if (r16 == (-1)) goto L77;
        if (r16 == 1) goto L77;
        boolean r32 = false;
    L78:
        this.draggingProgress = r32;
        this.hadDragChange = false;
        if (r16 == 1) goto L86;
        if (r16 == 5) goto L86;
        if (r16 == 8) goto L86;
        VelocityTracker r19 = this.velocityTracker;
        if (r19 == null) goto L87;
        r19.recycle();
        this.velocityTracker = null;
    L87:
        this.dragSpeed = 1.0f;
        this.dragged = false;
        this.lastX = r23.getX();
        if (this.isCover == true) goto L574;
        AndroidUtilities.cancelRunOnUIThread(this.onLongPress);
        AndroidUtilities.runOnUIThread(this.onLongPress, ViewConfiguration.getLongPressTimeout());
    L574:
        this.lastTime = System.currentTimeMillis();
        return true;
    L86:
        this.velocityTracker = VelocityTracker.obtain();
    L77:
        r32 = true;
        goto L78
    L64:
        if (this.hasAudio == false) goto L71;
        float r110 = r13;
        if (r23.getY() < r110) goto L68;
    L70:
        getAudioHeight();
        AndroidUtilities.dp(r17);
        goto L71
    L68:
        if (r23.getY() <= ((r110 - getAudioHeight()) - AndroidUtilities.dp(r18))) goto L70;
        this.pressType = 2;
        goto L70
    L39:
        if (this.collageTracks.isEmpty() == true) goto L50;
        int r33 = 0;
    L42:
        if (r33 >= this.collageTracks.size()) goto L50;
        r17 = r4;
        float r42 = AndroidUtilities.lerp(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(38.0f), Track.m20587$$Nest$fgetselectedT(this.collageTracks.get(r33)).get());
        float r152 = r13;
        if (r23.getY() >= r152) goto L48;
        r18 = r6;
        if (r23.getY() <= ((r152 - r42) - AndroidUtilities.dp(r18))) goto L49;
        this.pressType = 3;
        this.pressCollageIndex = r33;
    L49:
        r13 = (int) (r152 - (r42 + AndroidUtilities.dp(r17)));
        r33 = r33 + 1;
        r4 = r17;
        r6 = r18;
        goto L42
    L48:
        r18 = r6;
        goto L49
    L24:
        if (this.timelineBounds.contains(r23.getX(), r23.getY()) == false) goto L26;
        this.pressType = 10;
        this.pressHandle = -1;
        goto L26
    L91:
        if (r23.getAction() != 2) goto L413;
        float r111 = r23.getX() - this.lastX;
        if (this.open == false) goto L401;
        if (this.dragged == false) goto L97;
    L98:
        long r10 = Math.min(getBaseDuration(), getMaxScrollDuration());
        Track r142 = this.videoTrack;
        if (r142 != null) goto L101;
    L106:
        if (r142 == null) goto L149;
        int r153 = this.pressHandle;
        if (r153 == 2) goto L111;
        if (r153 == 3) goto L111;
        if (r153 != 4) goto L149;
    L111:
        float r34 = r10;
        long r102 = r142.duration;
        float r112 = (r111 / this.sw) * (r34 / r102);
        if (r153 != 2) goto L121;
        r142.left = Utilities.clamp(r142.left + r112, r142.right - (1000.0f / r102), 0.0f);
        TimelineDelegate r113 = this.delegate;
        if (r113 == null) goto L116;
        r113.onVideoLeftChange(false, this.videoTrack.left);
    L116:
        Track r114 = this.videoTrack;
        float r35 = r114.right - r114.left;
        float r115 = maxSelectDuration();
        Track r43 = this.videoTrack;
        if (r35 <= (r115 / r43.duration)) goto L138;
        r43.right = Math.min(1.0f, r43.left + (maxSelectDuration() / this.videoTrack.duration));
        TimelineDelegate r116 = this.delegate;
        if (r116 == null) goto L138;
        r116.onVideoRightChange(false, this.videoTrack.right);
    L138:
        long r36 = this.progress;
        Track r5 = this.videoTrack;
        long r62 = r5.duration;
        float r117 = r36 / r62;
        float r8 = r5.left;
        if (r117 >= r8) goto L141;
    L142:
        long r37 = (long) (r8 * r62);
        this.progress = r37;
        TimelineDelegate r118 = this.delegate;
        if (r118 == null) goto L145;
        r118.onProgressChange(r37, false);
    L145:
        invalidate();
        if (this.dragged == true) goto L148;
        this.dragSpeed = 1.0f;
    L148:
        this.dragged = true;
        this.draggingProgress = false;
    L399:
        this.lastX = r23.getX();
        goto L401
    L141:
        if ((r36 / r62) <= r5.right) goto L145;
    L121:
        if (r153 != 3) goto L130;
        r142.right = Utilities.clamp(r142.right + r112, 1.0f, r142.left + (1000.0f / r102));
        TimelineDelegate r119 = this.delegate;
        if (r119 == null) goto L125;
        r119.onVideoRightChange(false, this.videoTrack.right);
    L125:
        Track r120 = this.videoTrack;
        float r38 = r120.right - r120.left;
        float r121 = maxSelectDuration();
        Track r44 = this.videoTrack;
        if (r38 <= (r121 / r44.duration)) goto L138;
        r44.left = Math.max(0.0f, r44.right - (maxSelectDuration() / this.videoTrack.duration));
        TimelineDelegate r122 = this.delegate;
        if (r122 == null) goto L138;
        r122.onVideoLeftChange(false, this.videoTrack.left);
        goto L138
    L130:
        if (r153 != 4) goto L138;
        if (r112 <= 0.0f) goto L134;
        float r123 = Math.min(1.0f - r142.right, r112);
    L135:
        Track r39 = this.videoTrack;
        float r45 = r39.left + r123;
        r39.left = r45;
        r39.right += r123;
        TimelineDelegate r124 = this.delegate;
        if (r124 == null) goto L138;
        r124.onVideoLeftChange(false, r45);
        this.delegate.onVideoRightChange(false, this.videoTrack.right);
        goto L138
    L134:
        r123 = Math.max(-r142.left, r112);
    L149:
        int r82 = this.pressHandle;
        if (r82 == 6) goto L326;
        if (r82 == 7) goto L326;
        if (r82 == 8) goto L326;
        if (r82 == 10) goto L261;
        if (r82 == 11) goto L261;
        if (r82 == 12) goto L261;
        int r63 = this.pressHandleCollageIndex;
        if (r63 >= 0) goto L164;
    L224:
        int r52 = this.pressHandle;
        if (r52 != 5) goto L231;
        moveAudioOffset((r111 / this.sw) * r10);
        if (this.dragged == true) goto L229;
        this.dragSpeed = 1.0f;
    L229:
        this.dragged = true;
        this.draggingProgress = false;
        goto L399
    L231:
        if (r52 != 9) goto L236;
        moveRoundOffset((r111 / this.sw) * r10);
        if (this.dragged == true) goto L235;
        this.dragSpeed = 1.0f;
    L235:
        this.dragged = true;
        this.draggingProgress = false;
        goto L399
    L236:
        int r53 = this.pressHandleCollageIndex;
        if (r53 < 0) goto L247;
        if (r53 >= this.collageTracks.size()) goto L247;
        if (this.pressHandle != 16) goto L247;
        moveCollageOffset(this.collageTracks.get(this.pressHandleCollageIndex), (r111 / this.sw) * r10);
        if (this.dragged == true) goto L245;
        this.dragSpeed = 1.0f;
    L245:
        this.dragged = true;
        this.draggingProgress = false;
    L247:
        if (this.draggingProgress == false) goto L399;
        float r125 = r23.getX();
        if ((r3 - this.lastTime) >= 350) goto L251;
        r2 = true;
    L251:
        setProgressAt(r125, r2);
        if (this.dragged == true) goto L256;
        TimelineDelegate r126 = this.delegate;
        if (r126 == null) goto L256;
        boolean r46 = true;
        r126.onProgressDragChange(true);
    L258:
        if (this.dragged == true) goto L260;
        this.dragSpeed = 1.0f;
    L260:
        this.dragged = r46;
    L256:
        r46 = true;
        goto L258
    L164:
        if (r63 >= this.collageTracks.size()) goto L224;
        int r64 = this.pressHandle;
        if (r64 == 13) goto L169;
        if (r64 == 14) goto L169;
        if (r64 != 15) goto L224;
    L169:
        Track r310 = this.collageTracks.get(this.pressHandleCollageIndex);
        float r127 = (r111 / this.sw) * (r10 / r310.duration);
        int r47 = this.pressHandle;
        if (r47 != 13) goto L190;
        float r48 = r310.right - (minAudioSelect() / r310.duration);
        float r54 = Math.max(0, this.scroll - r310.offset) / r310.duration;
        if (r310 != this.collageMain) goto L180;
        float r192 = 0.0f;
        r54 = Math.max(r54, r310.right - (maxSelectDuration() / r310.duration));
        if (this.hadDragChange == false) goto L176;
    L181:
        float r65 = r310.left;
        float r49 = Utilities.clamp(r65 + r127, r48, r54);
        r310.left = r49;
        if (Math.abs(r65 - r49) <= 0.01f) goto L184;
        this.hadDragChange = true;
    L184:
        TimelineDelegate r410 = this.delegate;
        if (r410 == null) goto L187;
        r410.onVideoOffsetChange(r310.index, r310.offset);
    L187:
        TimelineDelegate r411 = this.delegate;
        if (r411 == null) goto L208;
        r411.onVideoLeftChange(r310.index, r310.left);
    L208:
        if (this.pressHandle != 15) goto L219;
        float r412 = Math.max(0, this.scroll - r310.offset) / r310.duration;
        float r55 = Math.min(1.0f, Math.max(0, (this.scroll - r310.offset) + r10) / r310.duration);
        if (r127 <= r192) goto L212;
        float r128 = Math.min(r55 - r310.right, r127);
    L213:
        float r413 = r310.left + r128;
        r310.left = r413;
        r310.right += r128;
        TimelineDelegate r129 = this.delegate;
        if (r129 == null) goto L216;
        r129.onVideoLeftChange(r310.index, r413);
        this.delegate.onVideoOffsetChange(r310.index, r310.offset);
        this.delegate.onVideoRightChange(r310.index, r310.right);
    L216:
        TimelineDelegate r130 = this.delegate;
        if (r130 == null) goto L219;
        boolean r414 = true;
        r130.onProgressDragChange(true);
    L220:
        invalidate();
        if (this.dragged == true) goto L223;
        this.dragSpeed = 1.0f;
    L223:
        this.dragged = r414;
        this.draggingProgress = false;
        goto L399
    L212:
        r128 = Math.max(r412 - r310.left, r127);
    L219:
        r414 = true;
        goto L220
    L176:
        if (r127 >= 0.0f) goto L181;
        if (r310.left > (r310.right - (maxSelectDuration() / r310.duration))) goto L181;
        this.pressHandle = 15;
        goto L181
    L180:
        r192 = 0.0f;
        goto L181
    L190:
        r192 = 0.0f;
        if (r47 != 14) goto L208;
        float r415 = Math.min(1.0f, Math.max(0, (this.scroll - r310.offset) + r10) / r310.duration);
        float r56 = r310.left + (minAudioSelect() / r310.duration);
        if (r310 != this.collageMain) goto L201;
        r415 = Math.min(r415, r310.left + (maxSelectDuration() / r310.duration));
        if (this.hadDragChange == true) goto L201;
        if (r127 <= 0.0f) goto L201;
        if (r310.right < (r310.left + (maxSelectDuration() / r310.duration))) goto L201;
        this.pressHandle = 15;
    L201:
        float r66 = r310.right;
        float r416 = Utilities.clamp(r66 + r127, r415, r56);
        r310.right = r416;
        if (Math.abs(r66 - r416) <= 0.01f) goto L204;
        this.hadDragChange = true;
    L204:
        TimelineDelegate r417 = this.delegate;
        if (r417 == null) goto L208;
        r417.onVideoRightChange(r310.index, r310.right);
    L261:
        float r131 = (r111 / this.sw) * (r10 / this.roundDuration);
        if (r82 != 10) goto L285;
        float r311 = this.roundRight - (minAudioSelect() / this.roundDuration);
        float r418 = Math.max(0, this.scroll - this.roundOffset);
        long r57 = this.roundDuration;
        float r419 = r418 / r57;
        Track r7 = this.videoTrack;
        if (r7 == null) goto L266;
        float r420 = Math.max(r419, (((r7.left * r7.duration) + this.scroll) - this.roundOffset) / r57);
    L276:
        float r67 = this.roundLeft;
        float r312 = Utilities.clamp(r67 + r131, r311, r420);
        this.roundLeft = r312;
        if (Math.abs(r67 - r312) <= 0.01f) goto L279;
        this.hadDragChange = true;
    L279:
        TimelineDelegate r313 = this.delegate;
        if (r313 == null) goto L282;
        r313.onRoundOffsetChange(this.roundOffset + ((long) (this.roundLeft * this.roundDuration)));
    L282:
        TimelineDelegate r314 = this.delegate;
        if (r314 == null) goto L306;
        r314.onRoundLeftChange(this.roundLeft);
    L306:
        if (this.pressHandle != 12) goto L318;
        float r315 = Math.max(0, this.scroll - this.roundOffset) / this.roundDuration;
        float r421 = Math.min(1.0f, Math.max(0, (this.scroll - this.roundOffset) + r10) / this.roundDuration);
        if (r131 <= 0.0f) goto L310;
        float r132 = Math.min(r421 - this.roundRight, r131);
    L311:
        float r316 = this.roundLeft + r132;
        this.roundLeft = r316;
        this.roundRight += r132;
        TimelineDelegate r133 = this.delegate;
        if (r133 == null) goto L314;
        r133.onRoundLeftChange(r316);
        this.delegate.onRoundOffsetChange(this.roundOffset + ((long) (this.roundLeft * this.roundDuration)));
        this.delegate.onRoundRightChange(this.roundRight);
    L314:
        TimelineDelegate r134 = this.delegate;
        if (r134 == null) goto L318;
        r134.onProgressDragChange(true);
        goto L318
    L310:
        r132 = Math.max(r315 - this.roundLeft, r131);
    L318:
        if (this.videoTrack != null) goto L322;
        this.progress = (long) (this.roundLeft * this.roundDuration);
        TimelineDelegate r135 = this.delegate;
        if (r135 == null) goto L322;
        r135.onProgressDragChange(true);
        this.delegate.onProgressChange(this.progress, false);
    L322:
        invalidate();
        if (this.dragged == true) goto L325;
        this.dragSpeed = 1.0f;
    L325:
        this.dragged = true;
        this.draggingProgress = false;
        goto L399
    L266:
        Track r72 = this.collageMain;
        if (r72 == null) goto L269;
        r420 = Math.max(r419, (((r72.left * r72.duration) + this.scroll) - this.roundOffset) / r57);
        goto L276
    L269:
        r420 = Math.max(r419, this.roundRight - (maxSelectDuration() / this.roundDuration));
        if (this.hadDragChange == true) goto L276;
        if (r131 >= 0.0f) goto L276;
        if (this.roundLeft > (this.roundRight - (maxSelectDuration() / this.roundDuration))) goto L276;
        this.pressHandle = 8;
        goto L276
    L285:
        if (r82 != 11) goto L306;
        float r317 = Math.min(1.0f, Math.max(0, (this.scroll - this.roundOffset) + r10) / this.roundDuration);
        float r422 = this.roundLeft;
        float r68 = minAudioSelect();
        long r73 = this.roundDuration;
        float r423 = r422 + (r68 / r73);
        Track r69 = this.videoTrack;
        if (r69 == null) goto L289;
        r317 = Math.min(r317, (((r69.right * r69.duration) + this.scroll) - this.roundOffset) / r73);
    L289:
        Track r610 = this.collageMain;
        if (r610 == null) goto L292;
        float r318 = Math.min(r317, (((r610.right * r610.duration) + this.scroll) - this.roundOffset) / this.roundDuration);
    L299:
        float r611 = this.roundRight;
        float r319 = Utilities.clamp(r611 + r131, r318, r423);
        this.roundRight = r319;
        if (Math.abs(r611 - r319) <= 0.01f) goto L302;
        this.hadDragChange = true;
    L302:
        TimelineDelegate r320 = this.delegate;
        if (r320 == null) goto L306;
        r320.onRoundRightChange(this.roundRight);
        goto L306
    L292:
        r318 = Math.min(r317, this.roundLeft + (maxSelectDuration() / this.roundDuration));
        if (this.hadDragChange == true) goto L299;
        if (r131 <= 0.0f) goto L299;
        if (this.roundRight < (this.roundLeft + (maxSelectDuration() / this.roundDuration))) goto L299;
        this.pressHandle = 8;
    L326:
        float r136 = (r111 / this.sw) * (r10 / this.audioDuration);
        if (r82 != 6) goto L353;
        float r321 = this.audioRight - (minAudioSelect() / this.audioDuration);
        float r424 = Math.max(0, this.scroll - this.audioOffset);
        long r612 = this.audioDuration;
        float r425 = r424 / r612;
        Track r83 = this.videoTrack;
        if (r83 == null) goto L331;
        float r426 = Math.max(r425, (((r83.left * r83.duration) + this.scroll) - this.audioOffset) / r612);
    L344:
        float r613 = this.audioLeft;
        float r322 = Utilities.clamp(r613 + r136, r321, r426);
        this.audioLeft = r322;
        if (Math.abs(r613 - r322) <= 0.01f) goto L347;
        this.hadDragChange = true;
    L347:
        TimelineDelegate r323 = this.delegate;
        if (r323 == null) goto L350;
        r323.onAudioOffsetChange(this.audioOffset + ((long) (this.audioLeft * this.audioDuration)));
    L350:
        TimelineDelegate r324 = this.delegate;
        if (r324 == null) goto L377;
        r324.onAudioLeftChange(this.audioLeft);
    L377:
        if (this.pressHandle != 8) goto L389;
        float r325 = Math.max(0, this.scroll - this.audioOffset) / this.audioDuration;
        float r427 = Math.min(1.0f, Math.max(0, (this.scroll - this.audioOffset) + r10) / this.audioDuration);
        if (r136 <= 0.0f) goto L381;
        float r137 = Math.min(Math.max(0.0f, r427 - this.audioRight), r136);
    L382:
        float r326 = this.audioLeft + r137;
        this.audioLeft = r326;
        this.audioRight += r137;
        TimelineDelegate r138 = this.delegate;
        if (r138 == null) goto L385;
        r138.onAudioLeftChange(r326);
        this.delegate.onAudioOffsetChange(this.audioOffset + ((long) (this.audioLeft * this.audioDuration)));
        this.delegate.onAudioRightChange(this.audioRight);
    L385:
        TimelineDelegate r139 = this.delegate;
        if (r139 == null) goto L389;
        r139.onProgressDragChange(true);
        goto L389
    L381:
        r137 = Math.max(Math.min(0.0f, r325 - this.audioLeft), r136);
    L389:
        if (this.videoTrack == null) goto L391;
    L395:
        invalidate();
        if (this.dragged == true) goto L398;
        this.dragSpeed = 1.0f;
    L398:
        this.dragged = true;
        this.draggingProgress = false;
        goto L399
    L391:
        if (this.hasRound == true) goto L395;
        this.progress = (long) (this.audioLeft * this.audioDuration);
        TimelineDelegate r140 = this.delegate;
        if (r140 == null) goto L395;
        r140.onProgressDragChange(true);
        this.delegate.onProgressChange(this.progress, false);
        goto L395
    L331:
        Track r84 = this.collageMain;
        if (r84 == null) goto L335;
        r426 = Math.max(r425, (((r84.left * r84.duration) + this.scroll) - this.audioOffset) / r612);
        goto L344
    L335:
        if (this.hasRound == false) goto L337;
        r426 = Math.max(r425, (((this.roundLeft * this.roundDuration) + this.scroll) - this.audioOffset) / r612);
        goto L344
    L337:
        r426 = Math.max(r425, this.audioRight - (maxSelectDuration() / this.audioDuration));
        if (this.hadDragChange == true) goto L344;
        if (r136 >= 0.0f) goto L344;
        if (this.audioLeft > (this.audioRight - (maxSelectDuration() / this.audioDuration))) goto L344;
        this.pressHandle = 8;
        goto L344
    L353:
        if (r82 != 7) goto L377;
        float r327 = Math.min(1.0f, Math.max(0, (this.scroll - this.audioOffset) + r10) / this.audioDuration);
        float r428 = this.audioLeft;
        float r614 = minAudioSelect();
        long r74 = this.audioDuration;
        float r429 = r428 + (r614 / r74);
        Track r615 = this.videoTrack;
        if (r615 == null) goto L357;
        float r328 = Math.min(r327, (((r615.right * r615.duration) + this.scroll) - this.audioOffset) / r74);
    L370:
        float r616 = this.audioRight;
        float r329 = Utilities.clamp(r616 + r136, r328, r429);
        this.audioRight = r329;
        if (Math.abs(r616 - r329) <= 0.01f) goto L373;
        this.hadDragChange = true;
    L373:
        TimelineDelegate r330 = this.delegate;
        if (r330 == null) goto L377;
        r330.onAudioRightChange(this.audioRight);
        goto L377
    L357:
        Track r617 = this.collageMain;
        if (r617 == null) goto L361;
        r328 = Math.min(r327, (((r617.right * r617.duration) + this.scroll) - this.audioOffset) / r74);
        goto L370
    L361:
        if (this.hasRound == false) goto L363;
        r328 = Math.min(r327, (((this.roundRight * this.roundDuration) + this.scroll) - this.audioOffset) / r74);
        goto L370
    L363:
        r328 = Math.min(r327, this.audioLeft + (maxSelectDuration() / this.audioDuration));
        if (this.hadDragChange == true) goto L370;
        if (r136 <= 0.0f) goto L370;
        if (this.audioRight < (this.audioLeft + (maxSelectDuration() / this.audioDuration))) goto L370;
        this.pressHandle = 8;
        goto L370
    L101:
        if (this.pressHandle != 1) goto L106;
        this.scroll = (long) Utilities.clamp(this.scroll - ((r111 / this.sw) * r10), r142.duration - r10, 0.0f);
        invalidate();
        if (this.dragged == true) goto L105;
        this.dragSpeed = 1.0f;
    L105:
        this.dragged = true;
        this.draggingProgress = false;
        goto L399
    L97:
        if (Math.abs(r111) > AndroidUtilities.touchSlop) goto L98;
    L401:
        if (this.dragged == false) goto L403;
        AndroidUtilities.cancelRunOnUIThread(this.onLongPress);
    L403:
        int r141 = this.pressHandle;
        if (r141 != 1) goto L406;
    L409:
        VelocityTracker r143 = this.velocityTracker;
        if (r143 == null) goto L574;
        r143.addMovement(r23);
        goto L574
    L406:
        if (r141 == 5) goto L409;
        if (r141 != 8) goto L574;
    L413:
        if (r23.getAction() != 1) goto L415;
    L416:
        AndroidUtilities.cancelRunOnUIThread(this.onLongPress);
        this.scroller.abortAnimation();
        if (r23.getAction() == 1) goto L419;
    L561:
        boolean r430 = true;
    L562:
        Runnable r144 = this.askExactSeek;
        if (r144 == null) goto L566;
        AndroidUtilities.cancelRunOnUIThread(r144);
        this.askExactSeek = null;
    L566:
        if (this.dragged == false) goto L571;
        if (r430 == false) goto L571;
        TimelineDelegate r145 = this.delegate;
        if (r145 == null) goto L571;
        r145.onProgressDragChange(false);
    L571:
        this.dragSpeed = 1.0f;
        this.dragged = false;
        this.draggingProgress = false;
        this.pressTime = -1;
        this.pressHandle = -1;
        VelocityTracker r146 = this.velocityTracker;
        if (r146 == null) goto L574;
        r146.recycle();
        this.velocityTracker = null;
        goto L574
    L419:
        if ((System.currentTimeMillis() - this.pressTime) > ViewConfiguration.getTapTimeout()) goto L423;
        if (this.dragged == true) goto L423;
    L425:
        if (this.open == true) goto L432;
        if (this.pressType != 10) goto L561;
        Runnable r147 = this.onTimelineClick;
        if (r147 == null) goto L561;
        r147.run();
        goto L561
    L432:
        if (this.isCover == true) goto L434;
    L439:
        int r148 = this.pressType;
        if (r148 == 3) goto L442;
    L457:
        if (r148 != 2) goto L465;
        if (this.audioSelected == true) goto L465;
        this.audioSelected = true;
        this.roundSelected = false;
        TimelineDelegate r149 = this.delegate;
        if (r149 == null) goto L463;
        r149.onRoundSelectChange(false);
    L463:
        invalidate();
    L465:
        if (r148 == 1) goto L467;
    L472:
        if (r148 == 2) goto L480;
        if (this.audioSelected == false) goto L480;
        this.audioSelected = false;
        this.roundSelected = false;
        TimelineDelegate r150 = this.delegate;
        if (r150 == null) goto L478;
        r150.onRoundSelectChange(false);
    L478:
        invalidate();
    L480:
        if (r148 != 1) goto L482;
    L487:
        long r431 = this.progress;
        if (setProgressAt(r23.getX(), false) == false) goto L561;
        if (Math.abs(this.progress - r431) <= 400) goto L561;
        this.loopProgressFrom = r431;
        this.loopProgress.set(1.0f, true);
        invalidate();
        goto L561
    L482:
        if (this.roundSelected == false) goto L487;
        this.audioSelected = false;
        this.roundSelected = false;
        TimelineDelegate r151 = this.delegate;
        if (r151 == null) goto L486;
        r151.onRoundSelectChange(false);
    L486:
        invalidate();
        goto L561
    L467:
        if (this.roundSelected == true) goto L472;
        this.audioSelected = false;
        this.roundSelected = true;
        TimelineDelegate r154 = this.delegate;
        if (r154 == null) goto L471;
        r154.onRoundSelectChange(true);
    L471:
        invalidate();
        goto L561
    L442:
        if (this.audioSelected == false) goto L444;
    L447:
        int r432 = -1;
    L448:
        int r58 = this.pressCollageIndex;
        if (r432 == r58) goto L457;
        this.audioSelected = false;
        this.roundSelected = false;
        this.collageSelected = r58;
        if (this.delegate == null) goto L456;
        if (r58 < 0) goto L456;
        if (r58 >= this.collageTracks.size()) goto L456;
        this.delegate.onVideoSelected(this.collageTracks.get(this.pressCollageIndex).index);
    L456:
        invalidate();
        goto L561
    L444:
        if (this.roundSelected == true) goto L447;
        r432 = this.collageSelected;
        goto L448
    L434:
        if (this.videoTrack == null) goto L439;
        long r331 = (long) Utilities.clamp(((((r23.getX() - this.px) - this.ph) / this.sw) * Math.min(getBaseDuration(), getMaxScrollDuration())) + this.scroll, getBaseDuration(), 0.0f);
        Track r155 = this.videoTrack;
        float r59 = r155.right - r155.left;
        r155.left = (r331 / getBaseDuration()) * (1.0f - r59);
        Track r156 = this.videoTrack;
        float r332 = r156.left;
        r156.right = r59 + r332;
        TimelineDelegate r157 = this.delegate;
        if (r157 == null) goto L438;
        r157.onVideoLeftChange(true, r332);
        this.delegate.onVideoRightChange(true, this.videoTrack.right);
    L438:
        invalidate();
    L423:
        if (this.open == false) goto L425;
        int r158 = this.pressHandle;
        int r433 = 1000;
        if (r158 != 16) goto L505;
        VelocityTracker r618 = this.velocityTracker;
        if (r618 == null) goto L505;
        r618.computeCurrentVelocity(1000);
        int r159 = (int) this.velocityTracker.getXVelocity();
        this.scrollingVideo = true;
        if (this.videoTrack != null) goto L499;
    L502:
        boolean r160 = true;
    L503:
        r430 = r160;
        goto L562
    L499:
        if (Math.abs(r159) <= AndroidUtilities.dp(100.0f)) goto L502;
        long r333 = Math.min(this.videoTrack.duration, getMaxScrollDuration());
        int r103 = this.px;
        float r75 = r333;
        int r85 = this.sw;
        int r619 = (int) (r103 + ((this.scroll / r75) * r85));
        this.scrolling = true;
        Scroller r510 = this.scroller;
        this.wasScrollX = r619;
        r510.fling(r619, 0, -r159, 0, r103, (int) (r103 + (((this.videoTrack.duration - r333) / r75) * r85)), 0, 0);
    L501:
        r160 = false;
    L505:
        if (r158 != 1) goto L514;
        VelocityTracker r76 = this.velocityTracker;
        if (r76 == null) goto L514;
        r76.computeCurrentVelocity(1000);
        int r161 = (int) this.velocityTracker.getXVelocity();
        this.scrollingVideo = true;
        if (this.videoTrack == null) goto L502;
        if (Math.abs(r161) <= AndroidUtilities.dp(100.0f)) goto L502;
        long r334 = Math.min(this.videoTrack.duration, getMaxScrollDuration());
        int r104 = this.px;
        float r77 = r334;
        int r86 = this.sw;
        int r620 = (int) (r104 + ((this.scroll / r77) * r86));
        this.scrolling = true;
        Scroller r511 = this.scroller;
        this.wasScrollX = r620;
        r511.fling(r620, 0, -r161, 0, r104, (int) (r104 + (((this.videoTrack.duration - r334) / r77) * r86)), 0, 0);
    L514:
        if (r158 == 5) goto L522;
        if (r158 != 8) goto L540;
        if (this.dragged == false) goto L522;
    L540:
        if (r158 == 9) goto L546;
        if (r158 != 12) goto L561;
        if (this.dragged == true) goto L561;
    L546:
        if (this.roundSelected == false) goto L561;
        VelocityTracker r162 = this.velocityTracker;
        if (r162 == null) goto L561;
        if (this.videoTrack != null) goto L553;
        r433 = 1500;
    L553:
        r162.computeCurrentVelocity(r433);
        int r78 = (int) this.velocityTracker.getXVelocity();
        this.scrollingVideo = false;
        if (Math.abs(r78) <= AndroidUtilities.dp(100.0f)) goto L561;
        float r335 = Math.min(getBaseDuration(), getMaxScrollDuration());
        int r512 = (int) ((this.px + this.ph) + ((this.roundOffset / r335) * this.sw));
        Track r163 = this.videoTrack;
        if (r163 == null) goto L559;
        float r434 = r163.right;
        long r87 = r163.duration;
        long r1410 = (long) ((r434 * r87) - 0);
        long r88 = (long) ((r163.left * r87) - this.roundDuration);
    L560:
        this.scrolling = true;
        Scroller r435 = this.scroller;
        this.wasScrollX = r512;
        int r164 = this.px;
        int r621 = this.ph;
        int r9 = this.sw;
        r435.fling(r512, 0, r78, 0, (int) ((r164 + r621) + ((r88 / r335) * r9)), (int) ((r164 + r621) + ((r1410 / r335) * r9)), 0, 0);
        r430 = false;
        goto L562
    L559:
        r88 = -(this.roundDuration - Math.min(getBaseDuration(), getMaxScrollDuration()));
        r1410 = 0;
    L522:
        if (this.audioSelected == false) goto L540;
        VelocityTracker r513 = this.velocityTracker;
        if (r513 == null) goto L540;
        if (this.videoTrack != null) goto L529;
        r433 = 1500;
    L529:
        r513.computeCurrentVelocity(r433);
        int r79 = (int) this.velocityTracker.getXVelocity();
        this.scrollingVideo = false;
        if (Math.abs(r79) <= AndroidUtilities.dp(100.0f)) goto L502;
        float r336 = Math.min(getBaseDuration(), getMaxScrollDuration());
        int r514 = (int) ((this.px + this.ph) + ((this.audioOffset / r336) * this.sw));
        Track r165 = this.videoTrack;
        if (r165 == null) goto L536;
        float r436 = r165.right;
        long r89 = r165.duration;
        long r105 = this.audioDuration;
        long r1411 = (long) ((r436 * r89) - 0);
        float r166 = r165.left;
    L534:
        long r810 = (long) ((r166 * r89) - r105);
        boolean r437 = true;
    L539:
        this.scrolling = r437;
        Scroller r438 = this.scroller;
        this.wasScrollX = r514;
        int r167 = this.px;
        int r622 = this.ph;
        int r92 = this.sw;
        r438.fling(r514, 0, r79, 0, (int) ((r167 + r622) + ((r810 / r336) * r92)), (int) ((r167 + r622) + ((r1411 / r336) * r92)), 0, 0);
        goto L501
    L536:
        if (this.hasRound == false) goto L538;
        float r168 = this.roundRight;
        r89 = this.roundDuration;
        r105 = this.audioDuration;
        r1411 = (long) ((r168 * r89) - 0);
        r166 = this.roundLeft;
        goto L534
    L538:
        r810 = -(this.audioDuration - Math.min(getBaseDuration(), getMaxScrollDuration()));
        r437 = true;
        r1411 = 0;
        goto L539
    L415:
        if (r23.getAction() != 3) goto L574;
    L14:
        if (r23.getY() >= r1) goto L16;
        return false;
    L5:
        if (this.collageTracks.isEmpty() == false) goto L11;
        if (this.hasAudio == true) goto L11;
        if (this.hasRound == true) goto L11;
        return false;
    }

    public void selectRound(boolean r3) {
        boolean r0 = true;
        if (r3 == true) goto L5;
    L7:
        this.roundSelected = false;
        if (this.hasAudio == true) goto L10;
    L12:
        r0 = false;
    L13:
        this.audioSelected = r0;
    L14:
        invalidate();
        return;
    L10:
        if (this.videoTrack != null) goto L12;
    L5:
        if (this.hasRound == false) goto L7;
        this.roundSelected = true;
        this.audioSelected = false;
        goto L14
    }

    public void setAudio(String r13, String r14, String r15, long r16, long r18, float r20, float r21, float r22, boolean r23) {
        if (TextUtils.equals(this.audioPath, r13) == true) goto L8;
        AudioWaveformLoader r4 = this.waveform;
        if (r4 == null) goto L7;
        r4.destroy();
        this.waveform = null;
        this.waveformIsLoaded = false;
    L7:
        this.audioPath = r13;
        setupAudioWaveform();
    L8:
        this.audioPath = r13;
        boolean r0 = TextUtils.isEmpty(r13);
        this.hasAudio = !r0;
        if (r0 == false) goto L11;
        this.audioSelected = false;
        String r02 = null;
        String r42 = null;
    L13:
        if (TextUtils.isEmpty(r02) == false) goto L16;
        r02 = null;
    L16:
        if (TextUtils.isEmpty(r42) == false) goto L19;
        r42 = null;
    L19:
        if (this.hasAudio == false) goto L42;
        this.audioDuration = r16;
        this.audioOffset = r18 - ((long) (r16 * r20));
        this.audioLeft = r20;
        this.audioRight = r21;
        this.audioVolume = r22;
        float r1 = 0.0f;
        if (r02 == null) goto L31;
        StaticLayout r2 = new StaticLayout(r02, this.audioAuthorPaint, 99999, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.audioAuthor = r2;
        if (r2.getLineCount() <= 0) goto L25;
        float r03 = this.audioAuthor.getLineWidth(0);
    L26:
        this.audioAuthorWidth = r03;
        if (this.audioAuthor.getLineCount() <= 0) goto L29;
        float r04 = this.audioAuthor.getLineLeft(0);
    L30:
        this.audioAuthorLeft = r04;
    L32:
        if (r42 == null) goto L41;
        StaticLayout r05 = new StaticLayout(r42, this.audioTitlePaint, 99999, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.audioTitle = r05;
        if (r05.getLineCount() <= 0) goto L36;
        float r06 = this.audioTitle.getLineWidth(0);
    L37:
        this.audioTitleWidth = r06;
        if (this.audioTitle.getLineCount() <= 0) goto L40;
        r1 = this.audioTitle.getLineLeft(0);
    L40:
        this.audioTitleLeft = r1;
        goto L42
    L36:
        r06 = 0.0f;
        goto L37
    L41:
        this.audioTitleWidth = 0.0f;
        this.audioTitle = null;
        goto L42
    L29:
        r04 = 0.0f;
        goto L30
    L25:
        r03 = 0.0f;
        goto L26
    L31:
        this.audioAuthorWidth = 0.0f;
        this.audioAuthor = null;
    L42:
        if (r23 == true) goto L44;
        this.audioT.set(this.hasAudio, true);
    L44:
        invalidate();
        return;
    L11:
        r02 = r14;
        r42 = r15;
        goto L13
    }

    public void setCollage(ArrayList<StoryEntry> r8) {
        int r1 = 0;
    L3:
        int r2 = this.collageTracks.size();
        ArrayList<Track> r3 = this.collageTracks;
        if (r1 >= r2) goto L11;
        Track r22 = r3.get(r1);
        if (r22 == null) goto L10;
        VideoThumbsLoader r23 = r22.thumbs;
        if (r23 == null) goto L10;
        r23.destroy();
    L10:
        r1 = r1 + 1;
        goto L3
    L11:
        r3.clear();
        int r12 = 0;
    L12:
        int r24 = this.collageWaveforms.size();
        ArrayList<AudioWaveformLoader> r32 = this.collageWaveforms;
        if (r12 >= r24) goto L18;
        AudioWaveformLoader r25 = r32.get(r12);
        if (r25 == null) goto L17;
        r25.destroy();
    L17:
        r12 = r12 + 1;
        goto L12
    L18:
        r32.clear();
        this.timelineWaveformMax.set(1.0f, true);
        if (r8 == null) goto L27;
        int r13 = 0;
    L22:
        if (r13 >= r8.size()) goto L27;
        this.collageWaveforms.add(null);
        StoryEntry r26 = r8.get(r13);
        if (r26.isVideo == false) goto L26;
        Track r4 = new Track(this, null);
        r4.index = r13;
        r4.isRound = false;
        r4.path = r26.file.getAbsolutePath();
        r4.duration = r26.duration;
        r4.offset = r26.videoOffset;
        r4.volume = r26.videoVolume;
        r4.left = r26.videoLeft;
        r4.right = r26.videoRight;
        Track.m20588$$Nest$msetupThumbs(r4, false);
        Track.m20589$$Nest$msetupWaveform(r4, false);
        this.collageTracks.add(r4);
    L26:
        r13 = r13 + 1;
    L27:
        sortCollage();
        this.collageSelected = 0;
    }

    public void setCover() {
        this.isCover = true;
    }

    public void setCoverVideo(long r1, long r3) {
        this.coverStart = r1;
        this.coverEnd = r3;
        Track r0 = this.videoTrack;
        if (r0 == null) goto L6;
        Track.m20588$$Nest$msetupThumbs(r0, true);
        return;
    }

    public void setDelegate(TimelineDelegate r1) {
        this.delegate = r1;
    }

    public void setMaxCount(int r1) {
        this.maxCount = r1;
    }

    public void setOnHeightChange(Runnable r1) {
        this.onHeightChange = r1;
    }

    public void setOnTimelineClick(Runnable r1) {
        this.onTimelineClick = r1;
    }

    public void setOpen(boolean r2, boolean r3) {
        if (this.open != r2) goto L6;
        if (r3 == false) goto L6;
        return;
    L6:
        this.open = r2;
        if (r3 == true) goto L9;
        this.openT.set(r2, true);
    L9:
        invalidate();
    }

    public void setProgress(long r12) {
        Track r0 = this.videoTrack;
        if (r0 == null) goto L11;
        if (r12 >= this.progress) goto L11;
        float r6 = r12;
        long r7 = r0.duration;
        if (r6 > ((r7 * r0.left) + 240.0f)) goto L11;
        if ((r4 + 240) < (r7 * r0.right)) goto L11;
    L30:
        this.loopProgressFrom = -1;
        this.loopProgress.set(1.0f, true);
    L31:
        this.progress = r12;
        invalidate();
        return;
    L11:
        if (this.hasAudio == false) goto L22;
        if (this.hasRound == true) goto L22;
        if (r0 != null) goto L22;
        if (r12 >= this.progress) goto L22;
        float r62 = r12;
        long r72 = this.audioDuration;
        if (r62 > ((r72 * this.audioLeft) + 240.0f)) goto L22;
        if ((r4 + 240) >= (r72 * this.audioRight)) goto L30;
    L22:
        if (this.hasRound == false) goto L31;
        if (r0 != null) goto L31;
        if (r12 >= this.progress) goto L31;
        float r02 = r12;
        long r63 = this.roundDuration;
        if (r02 > ((r63 * this.audioLeft) + 240.0f)) goto L31;
        if ((r4 + 240) < (r63 * this.audioRight)) goto L31;
        goto L30
    }

    public void setRound(String r9, long r10, long r12, float r14, float r15, float r16, boolean r17) {
        if (TextUtils.equals(this.roundPath, r9) == false) goto L5;
        return;
    L5:
        VideoThumbsLoader r1 = this.roundThumbs;
        if (r1 == null) goto L8;
        r1.destroy();
        this.roundThumbs = null;
    L8:
        long r3 = this.roundDuration;
        boolean r13 = false;
        if (r9 == null) goto L13;
        this.roundPath = r9;
        this.roundDuration = r10;
        this.roundOffset = r12 - ((long) (r10 * r14));
        this.roundLeft = r14;
        this.roundRight = r15;
        this.roundVolume = r16;
        setupRoundThumbs();
        if (this.videoTrack != null) goto L15;
        this.audioSelected = false;
        this.roundSelected = true;
    L15:
        if (this.roundPath == null) goto L17;
        r13 = true;
    L17:
        this.hasRound = r13;
        if (r3 == r10) goto L25;
        if (this.videoTrack != null) goto L25;
        if (this.waveform == null) goto L25;
        this.resetWaveform = true;
        setupAudioWaveform();
    L25:
        if (this.hasAudio == true) goto L27;
    L31:
        if (r17 == true) goto L33;
        this.roundSelectedT.set(this.roundSelected, true);
        this.audioSelectedT.set(this.audioSelected, true);
        this.roundT.set(this.hasRound, true);
    L33:
        invalidate();
        return;
    L27:
        if (this.hasRound == false) goto L31;
        if (this.videoTrack != null) goto L31;
        this.audioLeft = 0.0f;
        this.audioRight = Utilities.clamp(r10 / this.audioDuration, 1.0f, 0.0f);
        goto L31
    L13:
        this.roundPath = null;
        this.roundDuration = 1;
        this.roundSelected = false;
        goto L15
    }

    public void setRoundNull(boolean r11) {
        setRound(null, 0, 0, 0.0f, 0.0f, 0.0f, r11);
    }

    public void setVideo(boolean r6, String r7, long r8, float r10) {
        Track r0 = this.videoTrack;
        if (r0 != null) goto L5;
        String r02 = null;
    L7:
        if (TextUtils.equals(r02, r7) == false) goto L9;
        return;
    L9:
        Track r03 = this.videoTrack;
        if (r03 == null) goto L16;
        VideoThumbsLoader r04 = r03.thumbs;
        if (r04 == null) goto L14;
        r04.destroy();
        this.videoTrack.thumbs = null;
    L14:
        this.videoTrack = null;
    L16:
        if (r7 == null) goto L18;
        this.scroll = 0;
        Track r4 = new Track(this, null);
        this.videoTrack = r4;
        r4.isRound = r6;
        r4.path = r7;
        r4.duration = r8;
        r4.volume = r10;
        Track.m20588$$Nest$msetupThumbs(r4, false);
    L20:
        if (this.hasRound == true) goto L22;
        this.roundSelected = false;
    L22:
        this.progress = 0;
        invalidate();
        return;
    L18:
        this.videoTrack = null;
        this.scroll = 0;
        goto L20
    L5:
        r02 = r0.path;
        goto L7
    }

    public void setVideoLeft(float r2) {
        Track r0 = this.videoTrack;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.left = r2;
        invalidate();
    }

    public void setVideoRight(float r2) {
        Track r0 = this.videoTrack;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.right = r2;
        invalidate();
    }

    public void sortCollage() {
        Collections.sort(this.collageTracks, new TimelineView$$ExternalSyntheticLambda2());
        if (this.collageTracks.isEmpty() == false) goto L5;
        Track r0 = null;
    L6:
        this.collageMain = r0;
        if (r0 == null) goto L10;
        long r02 = r0.offset;
        return;
    L10:
        return;
    L5:
        r0 = this.collageTracks.get(0);
        goto L6
    }
}
