package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BlobDrawable;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Stories.recorder.CaptionContainerView;

/* loaded from: classes7.dex */
public abstract class CaptionStory extends CaptionContainerView {
    public static final int[] periods = null;
    private float amplitude;
    private final AnimatedFloat animatedAmplitude;
    private final BlobDrawable bigWaveDrawable;
    private final Path boundsPath;
    private final AnimatedFloat cancel2T;
    private final RectF cancelBounds;
    private final AnimatedFloat cancelT;
    private Text cancelText;
    private boolean cancelling;
    private final Path circlePath;
    private RoundVideoRecorder currentRecorder;
    private final Runnable doneCancel;
    private Drawable flipButton;
    private float fromX;
    private float fromY;
    private boolean hasRoundVideo;
    private final AnimatedFloat lock2T;
    private final Paint lockBackgroundPaint;
    private final RectF lockBounds;
    private final AnimatedFloat lockCancelledT;
    private final Path lockHandle;
    private final Paint lockHandlePaint;
    private final Paint lockPaint;
    private float lockProgress;
    private final RectF lockRect;
    private final Paint lockShadowPaint;
    private final AnimatedFloat lockT;
    private boolean locked;
    private Utilities.Callback<Integer> onPeriodUpdate;
    private Utilities.Callback<Integer> onPremiumHintShow;
    public ImageView periodButton;
    public CaptionContainerView.PeriodDrawable periodDrawable;
    private int periodIndex;
    private ItemOptions periodPopup;
    private boolean periodVisible;
    private final RecordDot recordPaint;
    private boolean recordTouch;
    private boolean recording;
    public ImageView roundButton;
    public ButtonBounce roundButtonBounce;
    private final Drawable roundDrawable;
    private final Paint roundPaint;
    private float slideProgress;
    private Paint slideToCancelArrowPaint;
    private Path slideToCancelArrowPath;
    private Text slideToCancelText;
    private long startTime;
    private boolean stopping;
    private final AnimatedTextView.AnimatedTextDrawable timerTextDrawable;
    private final BlobDrawable tinyWaveDrawable;
    private final Paint whitePaint;

    /* renamed from: org.telegram.ui.Stories.recorder.CaptionStory$1, reason: invalid class name */
    public class AnonymousClass1 extends RoundVideoRecorder {
        final /* synthetic */ CaptionStory this$0;

        public AnonymousClass1(CaptionStory r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Stories.recorder.RoundVideoRecorder
        public void receivedAmplitude(double r1) {
            this.this$0.setAmplitude(r1);
        }

        @Override // org.telegram.ui.Stories.recorder.RoundVideoRecorder
        public void stop() {
            super.stop();
            if (CaptionStory.m19900$$Nest$fgetrecording(this.this$0) == false) goto L6;
            CaptionStory.m19901$$Nest$mreleaseRecord(this.this$0, true, false);
            return;
        }
    }

    public class RecordDot extends Drawable {
        private float alpha;
        private float alpha2;
        boolean attachedToWindow;
        RLottieDrawable drawable;
        private boolean enterAnimation;
        private boolean isIncr;
        private long lastUpdateTime;
        private final View parent;
        boolean playing;
        private final Paint redDotPaint;
        final /* synthetic */ CaptionStory this$0;

        public RecordDot(CaptionStory r8, View r9) {
            this.this$0 = r8;
            this.redDotPaint = new Paint(1);
            this.alpha2 = 1.0f;
            this.parent = r9;
            RLottieDrawable r1 = new RLottieDrawable(R.raw.chat_audio_record_delete_3, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), false, null);
            this.drawable = r1;
            r1.setInvalidateOnProgressSet(true);
            updateColors();
        }

        public void attach() {
            this.attachedToWindow = true;
            if (this.playing == false) goto L5;
            this.drawable.start();
        L5:
            this.drawable.setMasterParent(this.parent);
        }

        public void detach() {
            this.attachedToWindow = false;
            this.drawable.stop();
            this.drawable.setMasterParent(null);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r6) {
            if (this.playing == false) goto L5;
            this.drawable.setAlpha((int) ((this.alpha * 255.0f) * this.alpha2));
        L5:
            this.redDotPaint.setAlpha((int) ((this.alpha * 255.0f) * this.alpha2));
            long r0 = System.currentTimeMillis() - this.lastUpdateTime;
            if (this.enterAnimation == false) goto L9;
            this.alpha = 1.0f;
        L18:
            this.lastUpdateTime = System.currentTimeMillis();
            this.drawable.setBounds(getBounds());
            if (this.playing == false) goto L22;
            this.drawable.draw(r6);
        L22:
            if (this.playing == true) goto L24;
        L25:
            r6.drawCircle(getBounds().centerX(), getBounds().centerY(), AndroidUtilities.dp(5.0f), this.redDotPaint);
        L26:
            this.this$0.invalidate();
            return;
        L24:
            if (this.drawable.hasBitmap() == true) goto L26;
        L9:
            if (this.isIncr == false) goto L11;
        L15:
            float r2 = this.alpha + (r0 / 600.0f);
            this.alpha = r2;
            if (r2 < 1.0f) goto L18;
            this.alpha = 1.0f;
            this.isIncr = false;
            goto L18
        L11:
            if (this.playing == true) goto L15;
            float r22 = this.alpha - (r0 / 600.0f);
            this.alpha = r22;
            if (r22 > 0.0f) goto L18;
            this.alpha = 0.0f;
            this.isIncr = true;
            goto L18
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        public void playDeleteAnimation() {
            this.playing = true;
            this.drawable.setProgress(0.0f);
            if (this.attachedToWindow == false) goto L6;
            this.drawable.start();
            return;
        }

        public void reset() {
            this.playing = false;
            this.drawable.stop();
            this.drawable.setProgress(0.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            this.alpha2 = r2 / 255.0f;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void updateColors() {
            this.redDotPaint.setColor(-2406842);
            this.drawable.beginApplyLayerColors();
            this.drawable.setLayerColor("Cup Red", -2406842);
            this.drawable.setLayerColor("Box", -2406842);
            this.drawable.commitApplyLayerColors();
        }
    }

    public static /* synthetic */ void $r8$lambda$16WpzpIn_nl5pBJu3LFHmxSfQ18(CaptionStory r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$AMeujYTHkGtG3wBOBhRdojTln3o(CaptionStory r0, AlertDialog r1, int r2) {
        r0.lambda$showRemoveRoundAlert$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$AZK5eDeAgYybNxlNYED8atXsvlA(CaptionStory r0) {
        r0.lambda$new$6();
    }

    /* renamed from: $r8$lambda$L58uzWAeK4wjO-g3BN63_hGxsRY, reason: not valid java name */
    public static /* synthetic */ void m19897$r8$lambda$L58uzWAeK4wjOg3BN63_hGxsRY(CaptionStory r0, FrameLayout r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$MMRNySIbsxKxzacaZ2iTdZa_3O8(CaptionStory r0, Integer r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$mjbER5O6fQEPzvVynCBQxBn9L_8(CaptionStory r0, Integer r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$w-i_k8wSsVi8xEN0rzOtBLmtKhs, reason: not valid java name */
    public static /* synthetic */ void m19898$r8$lambda$wi_k8wSsVi8xEN0rzOtBLmtKhs(Utilities.Callback r0, int r1) {
        r0.run(Integer.valueOf(r1));
    }

    /* renamed from: $r8$lambda$x-mOSWT8fPYE3okRMoCjkUEnG5w, reason: not valid java name */
    public static /* synthetic */ void m19899$r8$lambda$xmOSWT8fPYE3okRMoCjkUEnG5w(Utilities.Callback r0, int r1) {
        r0.run(Integer.valueOf(r1));
    }

    /* renamed from: -$$Nest$fgetrecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19900$$Nest$fgetrecording(CaptionStory r0) {
        return r0.recording;
    }

    /* renamed from: -$$Nest$mreleaseRecord, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19901$$Nest$mreleaseRecord(CaptionStory r0, boolean r1, boolean r2) {
        r0.releaseRecord(r1, r2);
    }

    static {
        periods = new int[]{21600, 43200, 86400, 172800};
    }

    public CaptionStory(Context r19, FrameLayout r20, SizeNotifierFrameLayout r21, FrameLayout r22, Theme.ResourcesProvider r23, BlurringShader.BlurManager r24) {
        super(r19, r20, r21, r22, r23, r24);
        this.periodVisible = true;
        this.periodIndex = 0;
        this.recordPaint = new RecordDot(this, this);
        AnimatedTextView.AnimatedTextDrawable r10 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.timerTextDrawable = r10;
        CubicBezierInterpolator r16 = CubicBezierInterpolator.DEFAULT;
        r10.setAnimationProperties(0.16f, 0, 50, r16);
        r10.setTextSize(AndroidUtilities.dp(15.0f));
        r10.setTypeface(AndroidUtilities.bold());
        r10.setText("0:00.0");
        r10.setTextColor(-1);
        Paint r2 = new Paint(1);
        this.whitePaint = r2;
        Paint r3 = new Paint(1);
        this.roundPaint = r3;
        BlobDrawable r4 = new BlobDrawable(11, LiteMode.FLAGS_CHAT);
        this.tinyWaveDrawable = r4;
        BlobDrawable r5 = new BlobDrawable(12, LiteMode.FLAGS_CHAT);
        this.bigWaveDrawable = r5;
        r2.setColor(-1);
        r3.setColor(-15033089);
        r4.minRadius = AndroidUtilities.dp(47.0f);
        r4.maxRadius = AndroidUtilities.dp(55.0f);
        r4.generateBlob();
        r5.minRadius = AndroidUtilities.dp(47.0f);
        r5.maxRadius = AndroidUtilities.dp(55.0f);
        r5.generateBlob();
        this.roundDrawable = getContext().getResources().getDrawable(R.drawable.input_video_pressed).mutate();
        this.animatedAmplitude = new AnimatedFloat(new CaptionStory$$ExternalSyntheticLambda0(this), 0, 200, r16);
        this.circlePath = new Path();
        this.boundsPath = new Path();
        this.lockBackgroundPaint = new Paint(1);
        this.lockShadowPaint = new Paint(1);
        this.lockPaint = new Paint(1);
        Paint r0 = new Paint(1);
        this.lockHandlePaint = r0;
        r0.setStyle(Paint.Style.STROKE);
        CaptionStory$$ExternalSyntheticLambda0 r25 = new CaptionStory$$ExternalSyntheticLambda0(this);
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.lockCancelledT = new AnimatedFloat(r25, 350, r6);
        this.lockBounds = new RectF();
        this.cancelBounds = new RectF();
        this.lockRect = new RectF();
        this.lockHandle = new Path();
        this.cancelT = new AnimatedFloat(this, 0, 350, r6);
        this.cancel2T = new AnimatedFloat(new CaptionStory$$ExternalSyntheticLambda0(this), 0, 420, r6);
        this.lockT = new AnimatedFloat(this, 0, 350, r6);
        this.lock2T = new AnimatedFloat(new CaptionStory$$ExternalSyntheticLambda0(this), 0, 350, r6);
        this.doneCancel = new CaptionStory$$ExternalSyntheticLambda1(this);
        ImageView r02 = new ImageView(r19);
        this.roundButton = r02;
        this.roundButtonBounce = new ButtonBounce(r02);
        this.roundButton.setImageResource(R.drawable.input_video_story);
        this.roundButton.setBackground(Theme.createSelectorDrawable(1090519039, 1, AndroidUtilities.dp(18.0f)));
        ImageView r03 = this.roundButton;
        ImageView.ScaleType r32 = ImageView.ScaleType.CENTER;
        r03.setScaleType(r32);
        this.roundButton.setContentDescription(LocaleController.getString(R.string.AccDescrVideoMessage));
        addView(this.roundButton, LayoutHelper.createFrame(44, 44.0f, 85, 0.0f, 0.0f, 11.0f, 6.0f));
        this.roundButton.setOnClickListener(new CaptionStory$$ExternalSyntheticLambda2(this));
        ImageView r04 = new ImageView(r19);
        this.periodButton = r04;
        CaptionContainerView.PeriodDrawable r52 = new CaptionContainerView.PeriodDrawable();
        this.periodDrawable = r52;
        r04.setImageDrawable(r52);
        this.periodButton.setBackground(Theme.createSelectorDrawable(1090519039, 1, AndroidUtilities.dp(18.0f)));
        this.periodButton.setScaleType(r32);
        this.periodButton.setContentDescription(LocaleController.getString(R.string.StoryPeriodHint));
        setPeriod(86400, false);
        addView(this.periodButton, LayoutHelper.createFrame(44, 44.0f, 85, 0.0f, 0.0f, 51.0f, 6.0f));
        this.periodButton.setOnClickListener(new CaptionStory$$ExternalSyntheticLambda3(this, r20, r23));
    }

    private void checkFlipButton() {
        if (this.flipButton == null) goto L5;
        return;
    L5:
        this.flipButton = ContextCompat.getDrawable(getContext(), R.drawable.avd_flip);
    }

    private void drawLock(Canvas r17, RectF r18, float r19) {
        float r3 = this.cancel2T.get();
        float r4 = this.lock2T.get();
        AnimatedFloat r5 = this.lockCancelledT;
        if (this.slideProgress >= 0.4f) goto L5;
        boolean r6 = true;
    L6:
        float r52 = (AndroidUtilities.lerp(r5.set(r6), 0.0f, r4) * (1.0f - r3)) * r19;
        float r11 = r18.right - AndroidUtilities.dp(20.0f);
        float r32 = (AndroidUtilities.lerp(AndroidUtilities.dp(50.0f), AndroidUtilities.dp(36.0f), r4) * r52) / 2.0f;
        float r12 = 1.0f - r4;
        float r2 = AndroidUtilities.lerp(((r18.bottom - AndroidUtilities.dp(80.0f)) - r32) - ((AndroidUtilities.dp(120.0f) * this.lockProgress) * r12), r18.bottom - AndroidUtilities.dp(20.0f), 1.0f - r52);
        float r10 = (AndroidUtilities.dp(36.0f) * r52) / 2.0f;
        this.lockBounds.set(r11 - r10, r2 - r32, r10 + r11, r32 + r2);
        float r33 = AndroidUtilities.lerp(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(14.0f), r4);
        this.lockShadowPaint.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, AndroidUtilities.dp(0.66f), Theme.multAlpha(536870912, r52));
        this.lockShadowPaint.setColor(0);
        r17.drawRoundRect(this.lockBounds, r33, r33, this.lockShadowPaint);
        Paint r102 = this.backgroundBlur.getPaint(r52);
        if (r102 != null) goto L9;
        this.lockBackgroundPaint.setColor(TLObject.FLAG_30);
        this.lockBackgroundPaint.setAlpha((int) (64.0f * r52));
        r17.drawRoundRect(this.lockBounds, r33, r33, this.lockBackgroundPaint);
    L10:
        r17.save();
        r17.scale(r52, r52, r11, r2);
        this.lockPaint.setColor(Theme.multAlpha(-1, r52));
        this.lockHandlePaint.setColor(Theme.multAlpha(-1, r52 * r12));
        float r34 = AndroidUtilities.lerp(AndroidUtilities.dp(15.33f), AndroidUtilities.dp(13.0f), r4);
        float r53 = AndroidUtilities.lerp(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(13.0f), r4);
        float r22 = r2 + (AndroidUtilities.dp(4.0f) * r12);
        r17.rotate((this.lockProgress * 12.0f) * r12, r11, r22);
        float r35 = r34 / 2.0f;
        float r54 = r53 / 2.0f;
        float r15 = r22 - r54;
        this.lockRect.set(r11 - r35, r15, r35 + r11, r22 + r54);
        r17.drawRoundRect(this.lockRect, AndroidUtilities.dp(3.66f), AndroidUtilities.dp(3.66f), this.lockPaint);
        if (r4 >= 1.0f) goto L13;
        r17.save();
        r17.rotate((this.lockProgress * 12.0f) * r12, r11, r15);
        r17.translate(0.0f, r54 * r4);
        r17.scale(r12, r12, r11, r15);
        this.lockHandle.rewind();
        float r23 = AndroidUtilities.dp(4.33f);
        float r152 = r15 - AndroidUtilities.dp(3.66f);
        float r55 = r11 + r23;
        this.lockHandle.moveTo(r55, AndroidUtilities.dp(3.66f) + r152);
        this.lockHandle.lineTo(r55, r152);
        RectF r36 = AndroidUtilities.rectTmp;
        float r112 = r11 - r23;
        r36.set(r112, r152 - r23, r55, r23 + r152);
        this.lockHandle.arcTo(r36, 0.0f, -180.0f, false);
        this.lockHandle.lineTo(r112, r152 + (AndroidUtilities.dp(3.66f) * AndroidUtilities.lerp(AndroidUtilities.lerp(0.4f, 0.0f, this.lockProgress), 1.0f, r4)));
        this.lockHandlePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        r17.drawPath(this.lockHandle, this.lockHandlePaint);
        r17.restore();
    L13:
        r17.restore();
        return;
    L9:
        r17.drawRoundRect(this.lockBounds, r33, r33, r102);
        this.backgroundPaint.setAlpha((int) (51.0f * r52));
        r17.drawRoundRect(this.lockBounds, r33, r33, this.backgroundPaint);
        goto L10
    L5:
        r6 = false;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        showRemoveRoundAlert();
    }

    private /* synthetic */ void lambda$new$1(Integer r2) {
        setPeriod(r2.intValue());
        Utilities.Callback<Integer> r1 = this.onPeriodUpdate;
        if (r1 == null) goto L6;
        r1.run(r2);
        return;
    }

    private /* synthetic */ void lambda$new$2(Integer r1) {
        Utilities.Callback<Integer> r0 = this.onPremiumHintShow;
        if (r0 == null) goto L6;
        r0.run(r1);
        return;
    }

    private /* synthetic */ void lambda$new$5(FrameLayout r10, Theme.ResourcesProvider r11, View r12) {
        ItemOptions r122 = this.periodPopup;
        if (r122 != null) goto L5;
    L7:
        CaptionStory$$ExternalSyntheticLambda4 r123 = new CaptionStory$$ExternalSyntheticLambda4(this);
        boolean r0 = UserConfig.getInstance(this.currentAccount).isPremium();
        if (r0 == false) goto L10;
        CaptionStory$$ExternalSyntheticLambda5 r2 = null;
    L11:
        ItemOptions r102 = ItemOptions.makeOptions(r10, r11, this.periodButton);
        this.periodPopup = r102;
        r102.addText(LocaleController.getString("StoryPeriodHint"), 13, AndroidUtilities.dp(200.0f));
        this.periodPopup.addGap();
        int r112 = 0;
    L12:
        int[] r3 = periods;
        if (r112 >= r3.length) goto L30;
        int r32 = r3[r112];
        ItemOptions r4 = this.periodPopup;
        if (r32 != Integer.MAX_VALUE) goto L17;
        String r6 = LocaleController.getString("StoryPeriodKeep");
    L18:
        ItemOptions r42 = r4.add(0, r6, Theme.key_actionBarDefaultSubmenuItem, new CaptionStory$$ExternalSyntheticLambda6(r123, r32));
        if (r0 == false) goto L21;
    L25:
        CaptionStory$$ExternalSyntheticLambda7 r5 = null;
    L26:
        r42.putPremiumLock(r5);
        if (this.periodIndex != r112) goto L29;
        this.periodPopup.putCheck();
    L29:
        r112 = r112 + 1;
        goto L12
    L21:
        if (r32 == 86400) goto L25;
        if (r32 == Integer.MAX_VALUE) goto L25;
        r5 = new CaptionStory$$ExternalSyntheticLambda7(r2, r32);
        goto L26
    L17:
        r6 = LocaleController.formatPluralString("Hours", r32 / 3600, new Object[0]);
        goto L18
    L30:
        this.periodPopup.setDimAlpha(0).show();
        return;
    L10:
        r2 = new CaptionStory$$ExternalSyntheticLambda5(this);
        goto L11
    L5:
        if (r122.isShown() == false) goto L7;
    }

    private /* synthetic */ void lambda$new$6() {
        setCollapsed(false, Integer.MIN_VALUE);
        this.roundButton.setVisibility(0);
        this.periodButton.setVisibility(0);
    }

    private /* synthetic */ void lambda$showRemoveRoundAlert$7(AlertDialog r1, int r2) {
        removeRound();
    }

    private void releaseRecord(boolean r5, boolean r6) {
        AndroidUtilities.cancelRunOnUIThread(this.doneCancel);
        this.stopping = true;
        this.recording = false;
        setCollapsed(false, (int) ((getBounds().right - AndroidUtilities.dp(20.0f)) - ((getWidth() * 0.35f) * this.slideProgress)));
        RoundVideoRecorder r0 = this.currentRecorder;
        if (r0 == null) goto L9;
        if (r5 == true) goto L8;
        if (r6 == false) goto L7;
        r0.cancel();
        goto L8
    L7:
        r0.stop();
    L8:
        this.currentRecorder = null;
    L9:
        invalidateDrawOver2();
    }

    private boolean roundButtonTouchEvent(MotionEvent r8) {
        if (r8.getAction() != 0) goto L16;
        if (stopRecording() == false) goto L7;
        return true;
    L7:
        this.recordTouch = true;
        if (getParent() == null) goto L11;
        getParent().requestDisallowInterceptTouchEvent(true);
    L11:
        if (canRecord() == true) goto L13;
        return true;
    L13:
        AndroidUtilities.cancelRunOnUIThread(this.doneCancel);
        this.fromX = r8.getX();
        this.fromY = r8.getY();
        this.amplitude = 0.0f;
        this.slideProgress = 0.0f;
        this.cancelT.set(0.0f, true);
        this.cancel2T.set(0.0f, true);
        this.cancelling = false;
        this.stopping = false;
        this.locked = false;
        this.recordPaint.reset();
        this.recording = true;
        this.startTime = System.currentTimeMillis();
        setCollapsed(true, Integer.MAX_VALUE);
        invalidateDrawOver2();
        AnonymousClass1 r82 = new AnonymousClass1(this, getContext());
        this.currentRecorder = r82;
        putRecorder(r82);
        return true;
    L16:
        if (r8.getAction() != 2) goto L40;
        if (this.cancelling == true) goto L50;
        this.slideProgress = Utilities.clamp((this.fromX - r8.getX()) / (getWidth() * 0.35f), 1.0f, 0.0f);
        float r83 = Utilities.clamp((this.fromY - r8.getY()) / (getWidth() * 0.3f), 1.0f, 0.0f);
        this.lockProgress = r83;
        boolean r0 = this.locked;
        if (r0 == false) goto L22;
    L29:
        if (r0 == false) goto L31;
    L38:
        invalidate();
        invalidateDrawOver2();
        goto L50
    L31:
        if (this.cancelling == true) goto L38;
        if (r83 < 1.0f) goto L38;
        if (this.slideProgress >= 0.4f) goto L38;
        this.locked = true;
        performHapticFeedback(3, 1);     // Catch: Exception -> L51
        goto L38
    L22:
        if (this.cancelling == true) goto L29;
        if (this.slideProgress < 1.0f) goto L29;
        this.cancelling = true;
        this.recording = false;
        this.roundButton.setVisibility(4);
        this.periodButton.setVisibility(4);
        this.recordPaint.playDeleteAnimation();
        RoundVideoRecorder r84 = this.currentRecorder;
        if (r84 == null) goto L28;
        r84.cancel();
    L28:
        AndroidUtilities.runOnUIThread(this.doneCancel, 800);
    L50:
        return this.recordTouch;
    L40:
        if (r8.getAction() == 1) goto L44;
        if (r8.getAction() != 3) goto L50;
    L44:
        if (this.cancelling == false) goto L46;
    L48:
        this.recordTouch = false;
        goto L50
    L46:
        if (this.locked == true) goto L48;
        releaseRecord(false, false);
        goto L48
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int additionalRightMargin() {
        return 36;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void afterUpdateShownKeyboard(boolean r5) {
        ImageView r0 = this.periodButton;
        int r1 = 0;
        if (r5 == false) goto L5;
    L7:
        int r3 = 8;
    L8:
        r0.setVisibility(r3);
        ImageView r02 = this.roundButton;
        if (r5 == false) goto L12;
        r1 = 8;
    L12:
        r02.setVisibility(r1);
        if (r5 == false) goto L16;
        this.periodButton.setVisibility(8);
        return;
    L16:
        return;
    L5:
        if (this.periodVisible == false) goto L7;
        r3 = 0;
        goto L8
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void beforeUpdateShownKeyboard(boolean r3) {
        if (r3 == true) goto L9;
        ImageView r32 = this.periodButton;
        if (this.periodVisible == false) goto L6;
        int r0 = 0;
    L7:
        r32.setVisibility(r0);
        this.roundButton.setVisibility(0);
        return;
    L6:
        r0 = 8;
        goto L7
    }

    public abstract boolean canRecord();

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r9) {
        if (this.recording == false) goto L26;
        RoundVideoRecorder r0 = this.currentRecorder;
        if (r0 == null) goto L26;
        if (r0.cameraView == null) goto L26;
        Drawable r02 = this.flipButton;
        if (r02 == null) goto L26;
        RectF r3 = AndroidUtilities.rectTmp;
        r3.set(r02.getBounds());
        r3.inset(-AndroidUtilities.dp(12.0f), -AndroidUtilities.dp(12.0f));
        int r03 = 0;
    L12:
        if (r03 >= r9.getPointerCount()) goto L26;
        if (AndroidUtilities.rectTmp.contains(r9.getX(r03), r9.getY(r03)) == true) goto L16;
        r03 = r03 + 1;
        goto L12
    L16:
        if (r9.getAction() != 0) goto L18;
    L19:
        this.currentRecorder.cameraView.switchCamera();
        Drawable r04 = this.flipButton;
        if ((r04 instanceof AnimatedVectorDrawable) == false) goto L23;
        ((AnimatedVectorDrawable) r04).start();
    L23:
        if (this.recordTouch == true) goto L26;
        return true;
    L18:
        if (r9.getActionMasked() != 5) goto L23;
    L26:
        RectF r05 = AndroidUtilities.rectTmp;
        r05.set(this.roundButton.getX(), this.roundButton.getY(), this.roundButton.getX() + this.roundButton.getMeasuredWidth(), this.roundButton.getY() + this.roundButton.getMeasuredHeight());
        if (this.recordTouch == true) goto L54;
        if (this.hasRoundVideo == true) goto L36;
        if (this.keyboardShown == true) goto L36;
        if (r05.contains(r9.getX(), r9.getY()) == true) goto L54;
    L36:
        if (this.recording == false) goto L44;
        if (this.locked == false) goto L44;
        if (this.cancelBounds.contains(r9.getX(), r9.getY()) == false) goto L44;
        releaseRecord(false, true);
        this.recordTouch = false;
        return true;
    L44:
        if (this.recording == false) goto L52;
        if (this.lockBounds.contains(r9.getX(), r9.getY()) == false) goto L48;
    L49:
        releaseRecord(false, false);
        this.recordTouch = false;
        return true;
    L48:
        if (getBounds().contains(r9.getX(), r9.getY()) == true) goto L49;
    L52:
        return super.dispatchTouchEvent(r9);
    L54:
        return roundButtonTouchEvent(r9);
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void drawOver(Canvas r23, RectF r24) {
        Canvas r2 = r23;
        if (this.currentRecorder == null) goto L40;
        float r1 = this.cancelT.set(this.cancelling);
        float r9 = this.lockT.set(this.locked);
        if (this.startTime > 0) goto L7;
        this.startTime = System.currentTimeMillis();
    L7:
        float r12 = (((float) Math.sin(((System.currentTimeMillis() - this.startTime) / 900.0f) * 3.141592653589793d)) + 1.0f) / 2.0f;
        float r3 = r24.left + AndroidUtilities.dp(21.0f);
        float r4 = r24.bottom - AndroidUtilities.dp(20.0f);
        this.recordPaint.setBounds((int) (r3 - AndroidUtilities.dp(12.0f)), (int) (r4 - AndroidUtilities.dp(12.0f)), (int) (r3 + AndroidUtilities.dp(12.0f)), (int) (r4 + AndroidUtilities.dp(12.0f)));
        this.recordPaint.draw(r2);
        this.timerTextDrawable.setBounds((int) ((r24.left + AndroidUtilities.dp(33.3f)) - (AndroidUtilities.dp(10.0f) * r1)), (int) ((r24.bottom - AndroidUtilities.dp(20.0f)) - AndroidUtilities.dp(9.0f)), (int) (r24.left + AndroidUtilities.dp(133.3f)), (int) ((r24.bottom - AndroidUtilities.dp(20.0f)) + AndroidUtilities.dp(9.0f)));
        this.timerTextDrawable.setText(this.currentRecorder.sinceRecordingText());
        this.timerTextDrawable.setAlpha((int) ((1.0f - r1) * 255.0f));
        this.timerTextDrawable.draw(r2);
        float r15 = 1.0f - r9;
        float r13 = (1.0f - this.slideProgress) * r15;
        Paint r32 = this.captionBlur.getPaint(1.0f);
        if (r32 == null) goto L10;
        float r16 = 12.0f;
        Paint r132 = r32;
        float r17 = 2.0f;
        float r11 = r13;
        r23.saveLayerAlpha(r24.left, r24.top, r24.right, r24.bottom, 255, 31);
        r2 = r23;
    L12:
        if (r11 > 0.0f) goto L14;
        float r10 = 15.0f;
    L26:
        if (r9 > 0.0f) goto L28;
        Canvas r22 = r23;
    L36:
        if (r132 == null) goto L38;
        r22.drawRect(r24, r132);
        r22.restore();
    L38:
        invalidate();
        return;
    L28:
        if (this.cancelText != null) goto L30;
        this.cancelText = new Text(LocaleController.getString(R.string.CancelRound), r10, AndroidUtilities.bold());
    L30:
        this.cancelText.ellipsize((int) ((r24.width() - AndroidUtilities.dp(116.0f)) - this.timerTextDrawable.getCurrentWidth()));
        float r33 = (r24.centerX() - (this.cancelText.getWidth() / r17)) + ((r24.width() / 4.0f) * r15);
        if (r132 == null) goto L33;
        int r14 = -1;
    L34:
        r22 = r23;
        this.cancelText.draw(r22, r33, r24.centerY(), Theme.multAlpha(r14, r9), 1.0f);
        this.cancelBounds.set(r33 - AndroidUtilities.dp(r16), r24.top, (r33 + this.cancelText.getWidth()) + AndroidUtilities.dp(r16), r24.bottom);
        goto L36
    L33:
        r14 = -2130706433;
        goto L34
    L14:
        if (this.slideToCancelText != null) goto L17;
        this.slideToCancelText = new Text(LocaleController.getString(R.string.SlideToCancel2), 15.0f);
    L17:
        if (this.slideToCancelArrowPath != null) goto L19;
        Path r18 = new Path();
        this.slideToCancelArrowPath = r18;
        r18.moveTo(AndroidUtilities.dp(3.83f), 0.0f);
        this.slideToCancelArrowPath.lineTo(0.0f, AndroidUtilities.dp(5.0f));
        this.slideToCancelArrowPath.lineTo(AndroidUtilities.dp(3.83f), AndroidUtilities.dp(10.0f));
        Paint r19 = new Paint(1);
        this.slideToCancelArrowPaint = r19;
        r19.setStyle(Paint.Style.STROKE);
        this.slideToCancelArrowPaint.setStrokeCap(Paint.Cap.ROUND);
        this.slideToCancelArrowPaint.setStrokeJoin(Paint.Join.ROUND);
    L19:
        this.slideToCancelArrowPaint.setStrokeWidth(AndroidUtilities.dp(1.33f));
        this.slideToCancelText.ellipsize((int) ((r24.width() - AndroidUtilities.dp(116.0f)) - this.timerTextDrawable.getCurrentWidth()));
        float r6 = ((r24.centerX() - ((AndroidUtilities.dp(11.33f) + this.slideToCancelText.getWidth()) / r17)) - ((r24.width() / 6.0f) * AndroidUtilities.lerp(this.slideProgress, 1.0f, r9))) - ((r12 * AndroidUtilities.dp(6.0f)) * (1.0f - this.slideProgress));
        if (r132 == null) goto L22;
        int r110 = -1;
    L23:
        int r5 = Theme.multAlpha(r110, r11);
        r2.save();
        r2.translate(r6, r24.centerY() - AndroidUtilities.dp(5.0f));
        this.slideToCancelArrowPaint.setColor(r5);
        r2.drawPath(this.slideToCancelArrowPath, this.slideToCancelArrowPaint);
        r2.restore();
        r10 = 15.0f;
        this.slideToCancelText.draw(r2, r6 + AndroidUtilities.dp(11.33f), r24.centerY(), r5, 1.0f);
        goto L26
    L22:
        r110 = -2130706433;
        goto L23
    L10:
        r17 = 2.0f;
        r16 = 12.0f;
        r11 = r13;
        r132 = r32;
        goto L12
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void drawOver2(Canvas r21, RectF r22, float r23) {
        if (r23 > 0.0f) goto L5;
        return;
    L5:
        float r4 = this.cancel2T.set(this.cancelling);
        float r5 = this.lock2T.set(this.locked);
        float r6 = this.animatedAmplitude.set(this.amplitude);
        float r8 = 1.0f - r4;
        float r7 = ((AndroidUtilities.dp(41.0f) + ((AndroidUtilities.dp(30.0f) * r6) * (1.0f - this.slideProgress))) * r8) * r23;
        float r42 = AndroidUtilities.lerp((r22.right - AndroidUtilities.dp(20.0f)) - (((getWidth() * 0.35f) * this.slideProgress) * (1.0f - r5)), r22.left + AndroidUtilities.dp(20.0f), r4);
        float r9 = r22.bottom - AndroidUtilities.dp(20.0f);
        if (LiteMode.isEnabled(LiteMode.FLAGS_CHAT) == false) goto L8;
        this.tinyWaveDrawable.minRadius = AndroidUtilities.dp(47.0f);
        this.tinyWaveDrawable.maxRadius = AndroidUtilities.dp(47.0f) + (AndroidUtilities.dp(15.0f) * BlobDrawable.FORM_SMALL_MAX);
        this.bigWaveDrawable.minRadius = AndroidUtilities.dp(50.0f);
        this.bigWaveDrawable.maxRadius = AndroidUtilities.dp(50.0f) + (AndroidUtilities.dp(12.0f) * BlobDrawable.FORM_BIG_MAX);
        this.bigWaveDrawable.update(r6, 1.01f);
        this.tinyWaveDrawable.update(r6, 1.02f);
        this.bigWaveDrawable.paint.setColor(Theme.multAlpha(this.roundPaint.getColor(), 0.15f * r23));
        r21.save();
        float r62 = r7 / this.bigWaveDrawable.minRadius;
        r21.scale(r62, r62, r42, r9);
        BlobDrawable r63 = this.bigWaveDrawable;
        r63.draw(r42, r9, r21, r63.paint);
        r21.restore();
        this.tinyWaveDrawable.paint.setColor(Theme.multAlpha(this.roundPaint.getColor(), 0.3f * r23));
        r21.save();
        float r64 = r7 / this.tinyWaveDrawable.minRadius;
        r21.scale(r64, r64, r42, r9);
        BlobDrawable r65 = this.tinyWaveDrawable;
        r65.draw(r42, r9, r21, r65.paint);
        r21.restore();
    L8:
        float r66 = Math.min(r7, AndroidUtilities.dp(55.0f));
        float r13 = r23 * 255.0f;
        this.roundPaint.setAlpha((int) r13);
        r21.drawCircle(r42, r9, r66, this.roundPaint);
        r21.save();
        this.circlePath.rewind();
        Path r72 = this.circlePath;
        Path.Direction r14 = Path.Direction.CW;
        r72.addCircle(r42, r9, r66, r14);
        r21.clipPath(this.circlePath);
        Drawable r67 = this.roundDrawable;
        float r73 = (r67.getIntrinsicWidth() / 2.0f) * r8;
        if (this.stopping == false) goto L11;
        float r3 = r23;
    L12:
        int r32 = (int) (r42 - (r73 * r3));
        float r74 = (this.roundDrawable.getIntrinsicHeight() / 2.0f) * r8;
        if (this.stopping == false) goto L15;
        float r10 = r23;
    L16:
        int r75 = (int) (r9 - (r74 * r10));
        float r102 = (this.roundDrawable.getIntrinsicWidth() / 2.0f) * r8;
        if (this.stopping == false) goto L19;
        float r11 = r23;
    L20:
        int r103 = (int) ((r102 * r11) + r42);
        float r112 = (this.roundDrawable.getIntrinsicHeight() / 2.0f) * r8;
        if (this.stopping == false) goto L23;
        float r12 = r23;
    L24:
        r67.setBounds(r32, r75, r103, (int) ((r112 * r12) + r9));
        Drawable r33 = this.roundDrawable;
        float r113 = r8 * 255.0f;
        if (this.stopping == false) goto L27;
        float r68 = r23;
    L28:
        r33.setAlpha((int) (r113 * r68));
        this.roundDrawable.draw(r21);
        if (r5 <= 0.0f) goto L31;
        float r34 = ((AndroidUtilities.dpf2(19.33f) / 2.0f) * r5) * r23;
        RectF r52 = AndroidUtilities.rectTmp;
        r52.set(r42 - r34, r9 - r34, r42 + r34, r9 + r34);
        r21.drawRoundRect(r52, AndroidUtilities.dp(5.33f), AndroidUtilities.dp(5.33f), this.whitePaint);
    L31:
        r21.restore();
        drawLock(r21, r22, r23);
        if (this.cancelling == true) goto L34;
    L50:
        checkFlipButton();
        this.flipButton.setAlpha((int) (r13 * r8));
        float r35 = getTimelineHeight();
        this.flipButton.setBounds(((int) r22.left) + AndroidUtilities.dp(4.0f), (int) ((r22.top - r35) - AndroidUtilities.dp(48.0f)), (int) (r22.left + AndroidUtilities.dp(40.0f)), (int) ((r22.top - r35) - AndroidUtilities.dp(12.0f)));
        this.flipButton.draw(r21);
        return;
    L34:
        if (this.roundButton.getVisibility() != 4) goto L36;
    L39:
        r21.saveLayerAlpha(r22, (int) ((1.0f - this.keyboardT) * 255.0f), 31);
        this.boundsPath.rewind();
        this.boundsPath.addRoundRect(r22, AndroidUtilities.dp(21.0f), AndroidUtilities.dp(21.0f), r14);
        r21.clipPath(this.boundsPath);
        if (this.roundButton.getVisibility() != 4) goto L42;
    L43:
        r21.save();
        r21.translate(this.roundButton.getX() + (AndroidUtilities.dp(180.0f) * r8), this.roundButton.getY());
        this.roundButton.draw(r21);
        r21.restore();
    L45:
        if (this.periodButton.getVisibility() != 4) goto L47;
    L48:
        r21.save();
        r21.translate(this.periodButton.getX() + (AndroidUtilities.dp(180.0f) * r8), this.periodButton.getY());
        this.periodButton.draw(r21);
        r21.restore();
    L49:
        r21.restore();
        goto L50
    L47:
        if (this.collapsedT.get() <= 0.0f) goto L49;
    L42:
        if (this.collapsedT.get() <= 0.0f) goto L45;
    L36:
        if (this.periodButton.getVisibility() == 4) goto L39;
        if (this.collapsedT.get() <= 0.0f) goto L50;
    L27:
        r68 = 1.0f;
        goto L28
    L23:
        r12 = 1.0f;
        goto L24
    L19:
        r11 = 1.0f;
        goto L20
    L15:
        r10 = 1.0f;
        goto L16
    L11:
        r3 = 1.0f;
        goto L12
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int getCaptionDefaultLimit() {
        return MessagesController.getInstance(this.currentAccount).storyCaptionLengthLimitDefault;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int getCaptionPremiumLimit() {
        return MessagesController.getInstance(this.currentAccount).storyCaptionLengthLimitPremium;
    }

    public int getTimelineHeight() {
        return 0;
    }

    public void hidePeriodPopup() {
        ItemOptions r0 = this.periodPopup;
        if (r0 == null) goto L6;
        r0.dismiss();
        this.periodPopup = null;
        return;
    }

    public boolean isRecording() {
        return this.recording;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.recordPaint.attach();
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.recordPaint.detach();
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void onUpdateShowKeyboard(float r3) {
        float r1 = 1.0f - r3;
        this.periodButton.setAlpha(r1);
        this.roundButton.setAlpha(r1);
    }

    public abstract void putRecorder(RoundVideoRecorder r1);

    public abstract void removeRound();

    public void setAmplitude(double r3) {
        this.amplitude = (float) (Math.min(1800.0d, r3) / 1800.0d);
        invalidate();
    }

    public void setHasRoundVideo(boolean r3) {
        ImageView r0 = this.roundButton;
        if (r3 == false) goto L5;
        int r1 = R.drawable.input_video_story_remove;
    L6:
        r0.setImageResource(r1);
        ImageView r02 = this.roundButton;
        if (r3 == false) goto L9;
        int r12 = R.string.AccDescrRemoveRoundVideo;
    L10:
        r02.setContentDescription(LocaleController.getString(r12));
        this.hasRoundVideo = r3;
        return;
    L9:
        r12 = R.string.AccDescrVideoMessage;
        goto L10
    L5:
        r1 = R.drawable.input_video_story;
        goto L6
    }

    public void setOnPeriodUpdate(Utilities.Callback<Integer> r1) {
        this.onPeriodUpdate = r1;
    }

    public void setOnPremiumHint(Utilities.Callback<Integer> r1) {
        this.onPremiumHintShow = r1;
    }

    public void setPeriod(int r2) {
        setPeriod(r2, true);
    }

    public void setPeriod(int r5, boolean r6) {
        int r1 = 0;
    L3:
        int[] r2 = periods;
        if (r1 >= r2.length) goto L9;
        if (r2[r1] == r5) goto L11;
        r1 = r1 + 1;
    L11:
        if (this.periodIndex != r1) goto L13;
        return;
    L13:
        this.periodIndex = r1;
        this.periodDrawable.setValue(r5 / 3600, false, r6);
        return;
    L9:
        r1 = 2;
        goto L11
    }

    public void setPeriodVisible(boolean r2) {
        this.periodVisible = r2;
        ImageView r0 = this.periodButton;
        if (r2 == true) goto L5;
    L7:
        int r1 = 8;
    L8:
        r0.setVisibility(r1);
        return;
    L5:
        if (this.keyboardShown == true) goto L7;
        r1 = 0;
        goto L8
    }

    public void showRemoveRoundAlert() {
        if (this.hasRoundVideo == false) goto L10;
        TextView r0 = (TextView) new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.StoryRemoveRoundTitle)).setMessage(LocaleController.getString(R.string.StoryRemoveRoundMessage)).setPositiveButton(LocaleController.getString(R.string.Remove), new CaptionStory$$ExternalSyntheticLambda8(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show().getButton(-1);
        if (r0 == null) goto L9;
        r0.setTextColor(Theme.getColor(Theme.key_text_RedBold, this.resourcesProvider));
        return;
    L9:
        return;
    }

    public boolean stopRecording() {
        if (this.recording == false) goto L6;
        this.recordTouch = false;
        releaseRecord(false, false);
        return true;
    L6:
        return false;
    }
}
