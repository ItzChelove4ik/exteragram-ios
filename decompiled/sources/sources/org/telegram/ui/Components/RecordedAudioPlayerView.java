package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import java.io.File;
import kotlin.jvm.internal.ByteCompanionObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.VideoPlayer;

/* loaded from: classes7.dex */
public class RecordedAudioPlayerView extends View {
    public boolean allowDraw;
    private final Paint backgroundPaint;
    private final RectF backgroundRect;
    private final RectF badgeClickRect;
    private final RectF badgeRect;
    private final Path clipPath;
    private final Paint darkerBackgroundPaint;
    private boolean destroyed;
    public float duration;
    private final Paint handlePaint;
    private final RectF handleRect;
    private float holdProgress;
    private int lastWaveformWidth;
    public float left;
    private final RectF leftHandleClickRect;
    private boolean leftPressed;
    private final PlayPauseDrawable playPauseDrawable;
    private boolean playPressed;
    private VideoPlayer player;
    private boolean progressPressed;
    private boolean progressPressedWasPlaying;
    private final Runnable progressUpdate;
    private final Theme.ResourcesProvider resourcesProvider;
    public float right;
    private final RectF rightHandleClickRect;
    private boolean rightPressed;
    private final AnimatedFloat showBadge;
    private final AnimatedFloat showDuration;
    private final AnimatedTextView.AnimatedTextDrawable text;
    public boolean wasPlaying;
    private byte[] waveformData;
    private final Paint waveformPaint;
    private final Path waveformPath;

    /* renamed from: org.telegram.ui.Components.RecordedAudioPlayerView$1, reason: invalid class name */
    public class AnonymousClass1 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ RecordedAudioPlayerView this$0;

        public AnonymousClass1(RecordedAudioPlayerView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r5, int r6) {
            if (r5 == true) goto L4;
        L6:
            RecordedAudioPlayerView.m12425$$Nest$fgetplayPauseDrawable(this.this$0).setPause(r5);
            AndroidUtilities.cancelRunOnUIThread(RecordedAudioPlayerView.m12427$$Nest$fgetprogressUpdate(this.this$0));
            if (r5 == false) goto L10;
            AndroidUtilities.runOnUIThread(RecordedAudioPlayerView.m12427$$Nest$fgetprogressUpdate(this.this$0), 16);
            return;
        L10:
            return;
        L4:
            if (RecordedAudioPlayerView.m12426$$Nest$fgetplayer(this.this$0).getCurrentPosition() < 0) goto L6;
            this.this$0.wasPlaying = true;
            goto L6
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
        }
    }

    public static /* synthetic */ void $r8$lambda$vYcROd9lP0cx9l5F4857dcZ6eVM(RecordedAudioPlayerView r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetplayPauseDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PlayPauseDrawable m12425$$Nest$fgetplayPauseDrawable(RecordedAudioPlayerView r0) {
        return r0.playPauseDrawable;
    }

    /* renamed from: -$$Nest$fgetplayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m12426$$Nest$fgetplayer(RecordedAudioPlayerView r0) {
        return r0.player;
    }

    /* renamed from: -$$Nest$fgetprogressUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m12427$$Nest$fgetprogressUpdate(RecordedAudioPlayerView r0) {
        return r0.progressUpdate;
    }

    public RecordedAudioPlayerView(Context r10, Theme.ResourcesProvider r11) {
        super(r10);
        this.backgroundPaint = new Paint(1);
        this.darkerBackgroundPaint = new Paint(1);
        this.waveformPaint = new Paint(1);
        this.handlePaint = new Paint(1);
        this.left = 0.0f;
        this.right = 1.0f;
        this.wasPlaying = false;
        this.progressUpdate = new RecordedAudioPlayerView$$ExternalSyntheticLambda0(this);
        this.backgroundRect = new RectF();
        this.badgeRect = new RectF();
        this.handleRect = new RectF();
        this.clipPath = new Path();
        this.badgeClickRect = new RectF();
        this.leftHandleClickRect = new RectF();
        this.rightHandleClickRect = new RectF();
        this.waveformPath = new Path();
        CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.showDuration = new AnimatedFloat(this, 0, 340, r8);
        this.showBadge = new AnimatedFloat(this, 0, 340, r8);
        this.allowDraw = true;
        this.resourcesProvider = r11;
        PlayPauseDrawable r102 = new PlayPauseDrawable(12);
        this.playPauseDrawable = r102;
        r102.setParent(this);
        r102.setCallback(this);
        AnimatedTextView.AnimatedTextDrawable r2 = new AnimatedTextView.AnimatedTextDrawable();
        this.text = r2;
        r2.setAnimationProperties(0.5f, 0, 200, r8);
        r2.setCallback(this);
        r2.setTextSize(AndroidUtilities.dp(12.0f));
        r2.setTypeface(AndroidUtilities.bold());
        r2.setOverrideFullWidth(AndroidUtilities.displaySize.x);
    }

    private /* synthetic */ void lambda$new$0() {
        VideoPlayer r0 = this.player;
        if (r0 == null) goto L12;
        boolean r02 = r0.isPlaying();
        float r1 = this.player.getCurrentPosition() / this.player.getDuration();
        float r2 = this.left;
        if (r1 >= r2) goto L8;
        this.player.seekTo((long) (r2 * r1.getDuration()));
    L10:
        if (r02 == false) goto L12;
        AndroidUtilities.runOnUIThread(this.progressUpdate, 16);
        goto L12
    L8:
        if (r1 <= this.right) goto L10;
        r02 = false;
        setPlaying(false);
    L12:
        invalidate();
    }

    public void checkWaveform() {
        int r0 = getMeasuredWidth() - AndroidUtilities.dp(27.0f);
        if (this.lastWaveformWidth != r0) goto L5;
        return;
    L5:
        int r2 = r0 / AndroidUtilities.dp(3.0f);
        int r4 = AndroidUtilities.dp(2.0f);
        int r5 = AndroidUtilities.dp(12.0f);
        byte r7 = ByteCompanionObject.MAX_VALUE;
        byte r8 = ByteCompanionObject.MIN_VALUE;
        int r9 = 0;
    L6:
        if (r9 >= r2) goto L12;
        byte[] r10 = this.waveformData;
        if (r10 != null) goto L10;
        byte r102 = 0;
    L11:
        r7 = (byte) Math.min(r7, r102);
        r8 = (byte) Math.max(r8, r102);
        r9 = r9 + 1;
        goto L6
    L10:
        r102 = r10[(int) ((r9 / r2) * r10.length)];
        goto L11
    L12:
        this.waveformPath.rewind();
        int r92 = 0;
    L13:
        if (r92 >= r2) goto L19;
        byte[] r103 = this.waveformData;
        if (r103 != null) goto L17;
        byte r104 = 0;
    L18:
        float r105 = AndroidUtilities.lerp(r4, r5, Utilities.clamp01(AndroidUtilities.ilerp(r104, r7, r8)));
        float r11 = AndroidUtilities.dp(3.0f) * r92;
        RectF r12 = AndroidUtilities.rectTmp;
        r12.set(r11, (-r105) / 2.0f, AndroidUtilities.dp(2.0f) + r11, r105 / 2.0f);
        this.waveformPath.addRoundRect(r12, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), Path.Direction.CW);
        r92 = r92 + 1;
        goto L13
    L17:
        r104 = r103[(int) ((r92 / r2) * r103.length)];
        goto L18
    L19:
        this.lastWaveformWidth = r0;
    }

    public void destroy() {
        this.destroyed = true;
        VideoPlayer r1 = this.player;
        if (r1 == null) goto L6;
        r1.setPlayWhenReady(false);
        this.player.releasePlayer(true);
        this.player = null;
        return;
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r3) {
        super.dispatchDraw(r3);
        if (this.allowDraw == false) goto L6;
        drawIn(r3, this.backgroundRect, 1.0f);
        return;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent r10) {
        boolean r0 = this.badgeClickRect.contains(r10.getX(), r10.getY());
        if (r0 == false) goto L5;
    L7:
        boolean r3 = false;
    L8:
        if (r0 == false) goto L10;
    L12:
        boolean r4 = false;
    L13:
        if (r0 == true) goto L21;
        if (r3 == true) goto L21;
        if (r4 == true) goto L21;
        if (r10.getX() <= this.leftHandleClickRect.right) goto L21;
        if (r10.getX() >= this.rightHandleClickRect.left) goto L21;
        boolean r5 = true;
    L22:
        float r7 = 1.0f;
        if (r10.getAction() != 0) goto L47;
        this.playPressed = r0;
        this.leftPressed = r3;
        this.rightPressed = r4;
        if (r3 == true) goto L27;
        if (r4 == true) goto L27;
    L28:
        this.progressPressed = r5;
        if (r5 == false) goto L36;
        this.progressPressedWasPlaying = isPlaying();
        if (this.player == null) goto L34;
        r7 = r0.getCurrentPosition() / this.player.getDuration();
    L34:
        this.holdProgress = r7;
        setPlaying(false);
    L36:
        if (getParent() == null) goto L93;
        if (this.playPressed == false) goto L40;
    L45:
        getParent().requestDisallowInterceptTouchEvent(true);
        goto L93
    L40:
        if (this.leftPressed == true) goto L45;
        if (this.rightPressed == true) goto L45;
        if (this.progressPressed == true) goto L45;
    L93:
        if (this.playPressed == false) goto L95;
    L104:
        return true;
    L95:
        if (this.leftPressed == true) goto L104;
        if (this.rightPressed == true) goto L104;
        if (this.progressPressed == true) goto L104;
        if (super.dispatchTouchEvent(r10) == true) goto L104;
        return false;
    L27:
        this.progressPressedWasPlaying = isPlaying();
        setPlaying(false);
        goto L28
    L47:
        if (r10.getAction() != 2) goto L62;
        if (this.leftPressed == false) goto L52;
        this.left = Utilities.clamp(AndroidUtilities.ilerp(r10.getX(), this.backgroundRect.left + AndroidUtilities.dp(11.33f), this.backgroundRect.right - AndroidUtilities.dp(11.33f)), Utilities.clamp01(this.right - Math.max(1.0f / this.duration, AndroidUtilities.dp(30.0f) / (this.backgroundRect.width() - AndroidUtilities.dp(22.66f)))), 0.0f);
        invalidate();
    L60:
        this.text.setText(AndroidUtilities.formatDuration(Math.round(Math.max(1.0f, this.duration * (this.right - this.left))), false), true);
        goto L93
    L52:
        if (this.rightPressed == false) goto L55;
        this.right = Utilities.clamp(AndroidUtilities.ilerp(r10.getX(), this.backgroundRect.left + AndroidUtilities.dp(11.33f), this.backgroundRect.right - AndroidUtilities.dp(11.33f)), 1.0f, Utilities.clamp01(this.left + Math.max(1.0f / this.duration, AndroidUtilities.dp(30.0f) / (this.backgroundRect.width() - AndroidUtilities.dp(22.66f)))));
        invalidate();
        goto L60
    L55:
        if (this.progressPressed == false) goto L60;
        VideoPlayer r02 = this.player;
        if (r02 == null) goto L59;
        float r32 = Utilities.clamp(AndroidUtilities.ilerp(r10.getX(), this.backgroundRect.left + AndroidUtilities.dp(11.33f), this.backgroundRect.right - AndroidUtilities.dp(11.33f)), this.right, this.left);
        this.holdProgress = r32;
        r02.seekTo((long) (r32 * this.player.getDuration()));
    L59:
        invalidate();
        goto L60
    L62:
        if (r10.getAction() == 1) goto L66;
        if (r10.getAction() != 3) goto L93;
    L66:
        if (r10.getAction() != 1) goto L71;
        if (this.playPressed == false) goto L71;
        setPlaying(!isPlaying());
    L91:
        this.playPressed = false;
        this.leftPressed = false;
        this.rightPressed = false;
        this.progressPressed = false;
    L71:
        if (this.leftPressed == false) goto L79;
        if (this.wasPlaying == false) goto L79;
        VideoPlayer r03 = this.player;
        if (r03 == null) goto L77;
        r03.seekTo((long) (this.left * r03.getDuration()));
    L77:
        setPlaying(true);
    L79:
        if (this.rightPressed == false) goto L87;
        if (this.wasPlaying == false) goto L87;
        VideoPlayer r04 = this.player;
        if (r04 == null) goto L85;
        r04.seekTo(Math.max((long) (this.left * r04.getDuration()), ((long) (this.right * this.player.getDuration())) - 1500));
    L85:
        setPlaying(true);
    L87:
        if (this.progressPressed == false) goto L91;
        if (isPlaying() == true) goto L91;
        setPlaying(true);
    L21:
        r5 = false;
        goto L22
    L10:
        if (this.rightHandleClickRect.contains(r10.getX(), r10.getY()) == false) goto L12;
        r4 = true;
        goto L13
    L5:
        if (this.leftHandleClickRect.contains(r10.getX(), r10.getY()) == false) goto L7;
        r3 = true;
        goto L8
    }

    public void drawIn(Canvas r18, RectF r19, float r20) {
        this.backgroundPaint.setColor(Theme.getColor(Theme.key_chat_recordedVoiceBackground, this.resourcesProvider));
        this.darkerBackgroundPaint.setColor(Theme.getColor(Theme.key_chat_recordedVoiceDarkerBackground, this.resourcesProvider));
        AnimatedTextView.AnimatedTextDrawable r2 = this.text;
        int r8 = Theme.key_chat_recordedVoiceProgressInner;
        r2.setTextColor(Theme.getColor(r8, this.resourcesProvider));
        this.playPauseDrawable.setColor(Theme.getColor(Theme.key_chat_recordedVoicePlayPause, this.resourcesProvider));
        Paint r22 = this.waveformPaint;
        int r9 = Theme.key_chat_recordedVoiceProgress;
        r22.setColor(Theme.getColor(r9, this.resourcesProvider));
        this.handlePaint.setColor(Theme.getColor(r8, this.resourcesProvider));
        int r10 = (int) AndroidUtilities.lerp(r19.left + AndroidUtilities.dp(11.33f), r19.right - AndroidUtilities.dp(11.33f), Utilities.clamp01(this.left));
        int r11 = (int) AndroidUtilities.lerp(r19.left + AndroidUtilities.dp(11.33f), r19.right - AndroidUtilities.dp(11.33f), Utilities.clamp01(this.right));
        checkWaveform();
        r18.save();
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r19, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
        r18.clipPath(this.clipPath);
        r18.drawRect(r19.left, r19.top, r10 - AndroidUtilities.dp(1.33f), r19.bottom, this.darkerBackgroundPaint);
        r18.drawRect(AndroidUtilities.dp(1.33f) + r11, r19.top, r19.right, r19.bottom, this.darkerBackgroundPaint);
        r18.save();
        r18.translate(r19.left + AndroidUtilities.dp(14.0f), r19.centerY());
        this.waveformPaint.setColor(Theme.multAlpha(Theme.getColor(r9, this.resourcesProvider), 0.3f));
        r18.drawPath(this.waveformPath, this.waveformPaint);
        r18.restore();
        float r23 = r10;
        float r4 = r11;
        r18.drawRect(r23, r19.top, r4, r19.bottom, this.backgroundPaint);
        if (this.progressPressed == false) goto L6;
        float r3 = this.holdProgress;
    L10:
        float r6 = Utilities.clamp(AndroidUtilities.lerp(r19.left + AndroidUtilities.dp(13.0f), r19.right - AndroidUtilities.dp(14.0f), r3), r4, r23);
        if (r6 >= r4) goto L23;
        r18.save();
        r18.clipRect(r6, r19.top, r4, r19.bottom);
        r18.translate(r19.left + AndroidUtilities.dp(14.0f), r19.centerY());
        if (this.wasPlaying == true) goto L15;
    L20:
        this.waveformPaint.setColor(Theme.getColor(r9, this.resourcesProvider));
    L21:
        r18.drawPath(this.waveformPath, this.waveformPaint);
        r18.restore();
        goto L23
    L15:
        if (r3 >= this.left) goto L20;
        if (this.progressPressed == true) goto L20;
        this.waveformPaint.setColor(Theme.getColor(r8, this.resourcesProvider));
    L23:
        if (r6 <= r23) goto L34;
        r18.save();
        r18.clipRect(r23, r19.top, r6, r19.bottom);
        r18.translate(r19.left + AndroidUtilities.dp(14.0f), r19.centerY());
        if (isPlaying() == false) goto L27;
    L32:
        this.waveformPaint.setColor(Theme.getColor(r8, this.resourcesProvider));
    L33:
        r18.drawPath(this.waveformPath, this.waveformPaint);
        r18.restore();
        goto L34
    L27:
        if (this.wasPlaying == true) goto L32;
        if (this.progressPressed == true) goto L32;
        this.waveformPaint.setColor(Theme.getColor(r9, this.resourcesProvider));
    L34:
        this.handleRect.set(r10 - AndroidUtilities.dp(7.0f), r19.centerY() - AndroidUtilities.dp(5.33f), r10 - AndroidUtilities.dp(5.33f), r19.centerY() + AndroidUtilities.dp(5.33f));
        RectF r24 = this.handleRect;
        r18.drawRoundRect(r24, r24.width() / 2.0f, this.handleRect.width() / 2.0f, this.handlePaint);
        this.handleRect.set(AndroidUtilities.dp(5.33f) + r11, r19.centerY() - AndroidUtilities.dp(5.33f), AndroidUtilities.dp(7.0f) + r11, r19.centerY() + AndroidUtilities.dp(5.33f));
        RectF r25 = this.handleRect;
        r18.drawRoundRect(r25, r25.width() / 2.0f, this.handleRect.width() / 2.0f, this.handlePaint);
        this.leftHandleClickRect.set(r10 - AndroidUtilities.dp(24.0f), 0.0f, AndroidUtilities.dp(6.0f) + r10, getHeight());
        this.rightHandleClickRect.set(r11 - AndroidUtilities.dp(6.0f), 0.0f, AndroidUtilities.dp(24.0f) + r11, getHeight());
        float r26 = this.showBadge.set(!this.progressPressed);
        if (r26 <= 0.0f) goto L43;
        float r42 = (int) (AndroidUtilities.dp(30.0f) + this.text.getCurrentWidth());
        AnimatedFloat r13 = this.showDuration;
        if (r42 > ((r11 - r10) - AndroidUtilities.dp(8.0f))) goto L39;
        boolean r12 = true;
    L40:
        float r122 = r13.set(r12);
        float r32 = AndroidUtilities.lerp(AndroidUtilities.dp(24.0f), r42, r122);
        float r102 = r10 + r11;
        float r43 = AndroidUtilities.dp(20.0f) / 2.0f;
        this.badgeRect.set((r102 - r32) / 2.0f, r19.centerY() - r43, (r102 + r32) / 2.0f, r19.centerY() + r43);
        int r33 = this.darkerBackgroundPaint.getAlpha();
        this.darkerBackgroundPaint.setAlpha((int) (r33 * r26));
        RectF r44 = this.badgeRect;
        r18.drawRoundRect(r44, r44.height() / 2.0f, this.badgeRect.height() / 2.0f, this.darkerBackgroundPaint);
        this.darkerBackgroundPaint.setAlpha(r33);
        this.badgeClickRect.set(this.badgeRect);
        this.badgeClickRect.inset(-AndroidUtilities.dp(6.0f), -AndroidUtilities.dp(6.0f));
        r18.save();
        int r34 = AndroidUtilities.dp(12.0f);
        r18.translate(AndroidUtilities.lerp(this.badgeRect.centerX() - (r34 / 2.0f), this.badgeRect.left + AndroidUtilities.dp(6.0f), r122), this.badgeRect.centerY());
        this.playPauseDrawable.setBounds(0, (-r34) / 2, r34, r34 / 2);
        this.playPauseDrawable.setAlpha((int) (r26 * 255.0f));
        this.playPauseDrawable.draw(r18);
        r18.restore();
        if (r122 <= 0.0f) goto L43;
        r18.save();
        r18.translate(this.badgeRect.left + AndroidUtilities.dp(21.66f), this.badgeRect.centerY() - AndroidUtilities.dp(1.0f));
        this.text.setBounds(-1, -1, 1, 1);
        this.text.setAlpha((int) ((r122 * 255.0f) * r26));
        this.text.draw(r18);
        r18.restore();
        goto L43
    L39:
        r12 = false;
    L43:
        r18.restore();
        return;
    L6:
        if (this.player == null) goto L8;
        float r35 = r3.getCurrentPosition() / this.player.getDuration();
    L9:
        r3 = Utilities.clamp(r35, this.right, this.left);
        goto L10
    L8:
        r35 = 1.0f;
        goto L9
    }

    public float getAudioLeft() {
        return this.left;
    }

    public long getAudioLeftMs() {
        return (long) (this.left * getDuration());
    }

    public float getAudioRight() {
        return this.right;
    }

    public long getAudioRightMs() {
        return (long) (this.right * getDuration());
    }

    public long getDuration() {
        VideoPlayer r2 = this.player;
        if (r2 != null) goto L7;
        return 0;
    L7:
        return r2.getDuration();
    }

    public double getNewDuration() {
        return ((this.right - this.left) * getDuration()) / 1000.0d;
    }

    public void init(String r3, double r4, byte[] r6, float r7, float r8) {
        if (this.destroyed == false) goto L5;
        return;
    L5:
        this.duration = (float) r4;
        this.left = r7;
        this.right = r8;
        this.wasPlaying = false;
        this.text.setText(AndroidUtilities.formatDuration((int) Math.round(Math.max(1.0d, r4)), false), false);
        this.playPauseDrawable.setPause(false, false);
        if (this.player != null) goto L8;
        VideoPlayer r42 = new VideoPlayer();
        this.player = r42;
        r42.setDelegate(new AnonymousClass1(this));
    L8:
        this.player.preparePlayer(Uri.fromFile(new File(r3)), "other");
        this.lastWaveformWidth = 0;
        this.waveformData = r6;
        invalidate();
    }

    public boolean isPlaying() {
        VideoPlayer r0 = this.player;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isPlaying() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean needsCut() {
        if (this.left <= 0.0f) goto L5;
        return true;
    L5:
        if (this.right < 1.0f) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        float r12 = AndroidUtilities.dp(32.0f);
        this.backgroundRect.set(0.0f, (getHeight() - r12) / 2.0f, getWidth(), (getHeight() + r12) / 2.0f);
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        super.onMeasure(r4, r5);
        float r42 = AndroidUtilities.dp(32.0f);
        this.backgroundRect.set(0.0f, (getMeasuredHeight() - r42) / 2.0f, getMeasuredWidth(), (getMeasuredHeight() + r42) / 2.0f);
    }

    public void setAllowDraw(boolean r2) {
        if (this.allowDraw == r2) goto L6;
        this.allowDraw = r2;
        invalidate();
        return;
    }

    public void setPlaying(boolean r5) {
        if (this.destroyed == false) goto L6;
        r5 = false;
    L6:
        if (this.player == null) goto L13;
        float r0 = r0.getCurrentPosition() / this.player.getDuration();
        float r1 = this.left;
        if (r0 >= r1) goto L10;
    L11:
        this.player.seekTo((long) (r1 * r0.getDuration()));
    L12:
        this.player.setPlayWhenReady(r5);
        goto L13
    L10:
        if (r0 <= this.right) goto L12;
    L13:
        this.playPauseDrawable.setPause(r5);
        AndroidUtilities.cancelRunOnUIThread(this.progressUpdate);
        if (r5 == false) goto L17;
        AndroidUtilities.runOnUIThread(this.progressUpdate, 16);
        return;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.text != r2) goto L5;
        return true;
    L5:
        if (this.playPauseDrawable != r2) goto L7;
        return true;
    L7:
        if (super.verifyDrawable(r2) == true) goto L14;
        return false;
    L14:
        return true;
    }
}
