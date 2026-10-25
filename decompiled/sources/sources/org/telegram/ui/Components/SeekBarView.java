package org.telegram.ui.Components;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Pair;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.ui.MaterialSliderUiHelper;
import com.google.android.material.slider.Slider;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AudioPlayerAlert;

/* loaded from: classes7.dex */
public class SeekBarView extends FrameLayout {
    private static Path tmpPath;
    private static float[] tmpRadii;
    private final float TIMESTAMP_GAP;
    private AnimatedFloat animatedThumbX;
    private float bufferedProgress;
    boolean captured;
    private float currentRadius;
    private int currentTimestamp;
    private int customInnerColor;
    public SeekBarViewDelegate delegate;
    private boolean hasBufferedProgress;
    private boolean hasCustomInnerColor;
    private Drawable hoverDrawable;
    private boolean ignoreMaterialSliderChanges;
    private Paint innerPaint1;
    private CharSequence lastCaption;
    private long lastDuration;
    private int lastTimestamp;
    private int lastTimestampLabelWidth;
    private long lastTimestampUpdate;
    private long lastTimestampsAppearingUpdate;
    private long lastUpdateTime;
    int lastValue;
    private float lastWidth;
    private int lineWidthDp;
    private Slider materialSlider;
    private float minProgress;
    private Paint outerPaint1;
    private boolean pressed;
    private boolean pressedDelayed;
    private int[] pressedState;
    private float progressToSet;
    private RectF rect;
    private boolean reportChanges;
    private final Theme.ResourcesProvider resourcesProvider;
    private final SeekBarAccessibilityDelegate seekBarAccessibilityDelegate;
    private int selectorWidth;
    private int separatorsCount;
    float sx;
    float sy;
    private final AudioPlayerAlert.ClippingTextViewSwitcher textViewSwitcher;
    private int thumbDX;
    private int thumbSize;
    private int thumbX;
    private int timestampChangeDirection;
    private float timestampChangeT;
    private int timestampIndex;
    private StaticLayout[] timestampLabel;
    private TextPaint timestampLabelPaint;
    private ArrayList<Pair<Float, CharSequence>> timestamps;
    private float timestampsAppearing;
    private float transitionProgress;
    private int transitionThumbX;
    private boolean twoSided;

    /* renamed from: org.telegram.ui.Components.SeekBarView$1, reason: invalid class name */
    public class AnonymousClass1 extends AudioPlayerAlert.ClippingTextViewSwitcher {
        final /* synthetic */ SeekBarView this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass1(SeekBarView r1, Context r2, Context r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.AudioPlayerAlert.ClippingTextViewSwitcher
        public TextView createTextView() {
            MarqueeTextView r0 = new MarqueeTextView(this.val$context);
            r0.setTextColor(SeekBarView.m12614$$Nest$mgetThemedColor(this.this$0, Theme.key_player_time));
            r0.setTextSize(1, 12.0f);
            r0.setEllipsize(TextUtils.TruncateAt.END);
            r0.setSingleLine(true);
            r0.setPadding(AndroidUtilities.dp(0.0f), 0, AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f));
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.Components.SeekBarView$2, reason: invalid class name */
    public class AnonymousClass2 extends FloatSeekBarAccessibilityDelegate {
        final /* synthetic */ SeekBarView this$0;

        public AnonymousClass2(SeekBarView r1, boolean r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
        public CharSequence getContentDescription(View r1) {
            SeekBarViewDelegate r0 = this.this$0.delegate;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getContentDescription();
        }

        @Override // org.telegram.ui.Components.FloatSeekBarAccessibilityDelegate
        public float getDelta() {
            int r0 = this.this$0.delegate.getStepsCount();
            if (r0 <= 0) goto L7;
            return 1.0f / r0;
        L7:
            return super.getDelta();
        }

        @Override // org.telegram.ui.Components.FloatSeekBarAccessibilityDelegate
        public float getProgress() {
            return this.this$0.getProgress();
        }

        @Override // org.telegram.ui.Components.FloatSeekBarAccessibilityDelegate
        public void setProgress(float r3) {
            SeekBarView.m12611$$Nest$fputpressed(this.this$0, true);
            this.this$0.setProgress(r3);
            SeekBarView.m12616$$Nest$msetSeekBarDrag(this.this$0, true, r3);
            SeekBarView.m12611$$Nest$fputpressed(this.this$0, false);
        }
    }

    /* renamed from: org.telegram.ui.Components.SeekBarView$3, reason: invalid class name */
    public class AnonymousClass3 implements Slider.OnSliderTouchListener {
        final /* synthetic */ SeekBarView this$0;

        /* renamed from: $r8$lambda$IxWK0lorK1O35zEq4d1-TfR0TS8, reason: not valid java name */
        public static /* synthetic */ void m12617$r8$lambda$IxWK0lorK1O35zEq4d1TfR0TS8(AnonymousClass3 r0) {
            r0.lambda$onStopTrackingTouch$0();
        }

        public AnonymousClass3(SeekBarView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onStopTrackingTouch$0() {
            SeekBarView.m12612$$Nest$fputpressedDelayed(this.this$0, false);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.android.material.slider.Slider.OnSliderTouchListener
        public void onStartTrackingTouch(Slider r2) {
            SeekBarView r22 = this.this$0;
            SeekBarView.m12612$$Nest$fputpressedDelayed(r22, true);
            SeekBarView.m12611$$Nest$fputpressed(r22, true);
            SeekBarViewDelegate r1 = this.this$0.delegate;
            if (r1 == null) goto L6;
            r1.onSeekBarPressed(true);
            return;
        }

        @Override // com.google.android.material.slider.Slider.OnSliderTouchListener, com.google.android.material.slider.BaseOnSliderTouchListener
        public /* bridge */ /* synthetic */ void onStartTrackingTouch(Slider r1) {
            onStartTrackingTouch(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.android.material.slider.Slider.OnSliderTouchListener
        public void onStopTrackingTouch(Slider r3) {
            float r32 = SeekBarView.m12613$$Nest$mgetProgressFromMaterialSliderValue(this.this$0, r3.getValue());
            SeekBarView.m12615$$Nest$msetProgressFromMaterialSlider(this.this$0, r32);
            SeekBarView.m12616$$Nest$msetSeekBarDrag(this.this$0, true, r32);
            SeekBarViewDelegate r33 = this.this$0.delegate;
            if (r33 == null) goto L5;
            r33.onSeekBarPressed(false);
        L5:
            SeekBarView.m12611$$Nest$fputpressed(this.this$0, false);
            AndroidUtilities.runOnUIThread(new SeekBarView$3$$ExternalSyntheticLambda0(this), 50);
        }

        @Override // com.google.android.material.slider.Slider.OnSliderTouchListener, com.google.android.material.slider.BaseOnSliderTouchListener
        public /* bridge */ /* synthetic */ void onStopTrackingTouch(Slider r1) {
            onStopTrackingTouch(r1);
        }
    }

    public interface SeekBarViewDelegate {
        default CharSequence getContentDescription() {
            return null;
        }

        default int getStepsCount() {
            return 0;
        }

        default boolean needVisuallyDivideSteps() {
            return false;
        }

        void onSeekBarDrag(boolean r1, float r2);

        default void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: $r8$lambda$-4Ep46_pD3AXPheIftp2auBw0Fg, reason: not valid java name */
    public static /* synthetic */ void m12610$r8$lambda$4Ep46_pD3AXPheIftp2auBw0Fg(SeekBarView r0, Slider r1, float r2, boolean r3) {
        r0.lambda$initMaterialSlider$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$UWCpDRp6wKjpYikLmP0gGYmoW5A(SeekBarView r0) {
        r0.lambda$onTouch$0();
    }

    public static /* synthetic */ int $r8$lambda$krRqn86cNzSUGZ63XNeyzAeauiE(Pair r2, Pair r3) {
        if (((Float) r2.first).floatValue() <= ((Float) r3.first).floatValue()) goto L7;
        return 1;
    L7:
        if (((Float) r3.first).floatValue() <= ((Float) r2.first).floatValue()) goto L10;
        return -1;
    L10:
        return 0;
    }

    /* renamed from: -$$Nest$fputpressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12611$$Nest$fputpressed(SeekBarView r0, boolean r1) {
        r0.pressed = r1;
    }

    /* renamed from: -$$Nest$fputpressedDelayed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12612$$Nest$fputpressedDelayed(SeekBarView r0, boolean r1) {
        r0.pressedDelayed = r1;
    }

    /* renamed from: -$$Nest$mgetProgressFromMaterialSliderValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12613$$Nest$mgetProgressFromMaterialSliderValue(SeekBarView r0, float r1) {
        return r0.getProgressFromMaterialSliderValue(r1);
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12614$$Nest$mgetThemedColor(SeekBarView r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$msetProgressFromMaterialSlider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12615$$Nest$msetProgressFromMaterialSlider(SeekBarView r0, float r1) {
        r0.setProgressFromMaterialSlider(r1);
    }

    /* renamed from: -$$Nest$msetSeekBarDrag, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12616$$Nest$msetSeekBarDrag(SeekBarView r0, boolean r1, float r2) {
        r0.setSeekBarDrag(r1, r2);
    }

    public SeekBarView(Context r2) {
        this(r2, null);
    }

    public SeekBarView(Context r2, Theme.ResourcesProvider r3) {
        this(r2, false, r3);
    }

    public SeekBarView(Context r8, boolean r9, Theme.ResourcesProvider r10) {
        super(r8);
        this.animatedThumbX = new AnimatedFloat(this, 0, 60, CubicBezierInterpolator.EASE_OUT);
        this.progressToSet = -100.0f;
        this.minProgress = -1.0f;
        this.pressedState = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        this.transitionProgress = 1.0f;
        this.lineWidthDp = 3;
        this.timestampsAppearing = 0.0f;
        this.TIMESTAMP_GAP = 1.0f;
        this.currentTimestamp = -1;
        this.lastTimestamp = -1;
        this.timestampChangeT = 1.0f;
        this.lastWidth = -1.0f;
        this.rect = new RectF();
        this.timestampIndex = -1;
        this.resourcesProvider = r10;
        setWillNotDraw(false);
        this.innerPaint1 = new Paint(1);
        Paint r102 = new Paint(1);
        this.outerPaint1 = r102;
        int r3 = Theme.key_player_progress;
        r102.setColor(getThemedColor(r3));
        this.selectorWidth = AndroidUtilities.dp(32.0f);
        this.thumbSize = AndroidUtilities.dp(24.0f);
        this.currentRadius = AndroidUtilities.dp(6.0f);
        Drawable r103 = Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(getThemedColor(r3), 40), 1, AndroidUtilities.dp(16.0f));
        this.hoverDrawable = r103;
        r103.setCallback(this);
        this.hoverDrawable.setVisible(true, false);
        AnonymousClass1 r7 = new AnonymousClass1(this, r8, r8);
        this.textViewSwitcher = r7;
        r7.setIsCenter();
        addView(r7, LayoutHelper.createFrame(-1, -2.0f));
        updateMaterialSliderState();
        setImportantForAccessibility(1);
        AnonymousClass2 r72 = new AnonymousClass2(this, r9);
        this.seekBarAccessibilityDelegate = r72;
        setAccessibilityDelegate(r72);
    }

    private boolean canUseMaterialSlider() {
        if (ExteraConfig.getNewSliderStyle() == true) goto L6;
        return false;
    L6:
        if (this.twoSided == false) goto L8;
    L21:
        return false;
    L8:
        if (this.minProgress > 0.0f) goto L21;
        if (this.hasBufferedProgress == true) goto L21;
        if (this.lineWidthDp != 3) goto L21;
        ArrayList<Pair<Float, CharSequence>> r3 = this.timestamps;
        if (r3 != null) goto L17;
        return true;
    L17:
        if (r3.isEmpty() == true) goto L22;
        return false;
    L22:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v2 */
    private void drawProgressBar(Canvas r26, RectF r27, Paint r28) {
        SeekBarView r0 = this;
        float r5 = AndroidUtilities.dp(2.0f);
        ArrayList<Pair<Float, CharSequence>> r6 = r0.timestamps;
        if (r6 != null) goto L5;
    L86:
        r26.drawRoundRect(r27, r5, r5, r28);
        return;
    L5:
        if (r6.isEmpty() == true) goto L86;
        float r62 = r27.bottom;
        float r63 = r0.selectorWidth / 2.0f;
        float r7 = r0.getMeasuredWidth() - (r0.selectorWidth / 2.0f);
        AndroidUtilities.rectTmp.set(r27);
        float r8 = AndroidUtilities.dp(r0.timestampsAppearing * 1.0f) / 2.0f;
        if (tmpPath != null) goto L10;
        tmpPath = new Path();
    L10:
        tmpPath.reset();
        float r4 = AndroidUtilities.dp(4.0f) / (r7 - r63);
        int r11 = 0;
    L11:
        int r13 = -1;
        if (r11 >= r0.timestamps.size()) goto L17;
        if (((Float) r0.timestamps.get(r11).first).floatValue() >= r4) goto L18;
        r11 = r11 + 1;
    L18:
        if (r11 >= 0) goto L20;
        r11 = 0;
    L20:
        boolean r14 = true;
        int r12 = r0.timestamps.size() - 1;
    L21:
        if (r12 < 0) goto L26;
        if ((1.0f - ((Float) r0.timestamps.get(r12).first).floatValue()) >= r4) goto L24;
        r12 = r12 - 1;
        goto L21
    L24:
        r13 = r12 + 1;
    L26:
        if (r13 >= 0) goto L28;
        r13 = r0.timestamps.size();
    L28:
        int r122 = r11;
    L29:
        if (r122 > r13) goto L84;
        float r15 = 0.0f;
        if (r122 != r11) goto L33;
        float r9 = 0.0f;
        char r16 = 0;
    L34:
        if (r122 != r13) goto L36;
        float r10 = 1.0f;
    L37:
        if (r122 == r13) goto L44;
        if (r122 == 0) goto L44;
        ?? r17 = r14;
        if (r122 >= (r0.timestamps.size() - 1)) goto L45;
        if ((((Float) r0.timestamps.get(r122).first).floatValue() - r9) > r4) goto L45;
        r122 = r122 + 1;
        r10 = ((Float) r0.timestamps.get(r122).first).floatValue();
        r14 = r17 == true ? 1 : 0;
    L45:
        RectF r142 = AndroidUtilities.rectTmp;
        float r92 = AndroidUtilities.lerp(r63, r7, r9);
        if (r122 <= 0) goto L48;
        float r18 = r8;
    L49:
        r142.left = r92 + r18;
        float r93 = AndroidUtilities.lerp(r63, r7, r10);
        if (r122 >= r13) goto L52;
        r15 = r8;
    L52:
        float r94 = r93 - r15;
        r142.right = r94;
        float r102 = r27.right;
        if (r94 <= r102) goto L55;
        char r95 = r17;
    L56:
        if (r95 == 0) goto L58;
        r142.right = r102;
    L58:
        float r103 = r142.right;
        float r152 = r27.left;
        if (r103 < r152) goto L83;
        if (r142.left >= r152) goto L65;
        r142.left = r152;
    L65:
        if (tmpRadii != null) goto L68;
        tmpRadii = new float[8];
    L68:
        if (r122 == r11) goto L78;
        if (r95 == 0) goto L73;
        char r24 = 4;
        if (r142.left >= r27.left) goto L79;
    L74:
        float r104 = r0.timestampsAppearing;
        if (r122 < r13) goto L77;
        float[] r153 = tmpRadii;
        float r20 = (0.7f * r5) * r104;
        r153[7] = r20;
        r153[6] = r20;
        r153[r17] = r20;
        r153[r16] = r20;
        r153[5] = r5;
        r153[r24] = r5;
        r153[3] = r5;
        r153[2] = r5;
    L80:
        tmpPath.addRoundRect(r142, tmpRadii, Path.Direction.CW);
        if (r95 == 0) goto L83;
    L77:
        float[] r154 = tmpRadii;
        float r202 = (0.7f * r5) * r104;
        r154[5] = r202;
        r154[r24] = r202;
        r154[3] = r202;
        r154[2] = r202;
        r154[7] = r202;
        r154[6] = r202;
        r154[r17] = r202;
        r154[r16] = r202;
    L79:
        float[] r105 = tmpRadii;
        r105[7] = r5;
        r105[6] = r5;
        r105[r17] = r5;
        r105[r16] = r5;
        float r203 = (0.7f * r5) * r0.timestampsAppearing;
        r105[5] = r203;
        r105[r24] = r203;
        r105[3] = r203;
        r105[2] = r203;
        goto L80
    L73:
        r24 = 4;
        goto L74
    L78:
        r24 = 4;
    L83:
        r122 = r122 + 1;
        r0 = this;
        r14 = r17;
        goto L29
    L55:
        r95 = r16;
        goto L56
    L48:
        r18 = 0.0f;
    L44:
        r17 = r14;
        goto L45
    L36:
        r10 = ((Float) r0.timestamps.get(r122).first).floatValue();
        goto L37
    L33:
        r16 = 0;
        r9 = ((Float) r0.timestamps.get(r122 - 1).first).floatValue();
    L84:
        r26.drawPath(tmpPath, r28);
        return;
    L17:
        r11 = -1;
        goto L18
    }

    private void drawTimestampLabel(Canvas r15) {
        ArrayList<Pair<Float, CharSequence>> r0 = this.timestamps;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L88;
        float r02 = getProgress();
        int r1 = this.timestamps.size() - 1;
    L9:
        if (r1 < 0) goto L14;
        if ((((Float) this.timestamps.get(r1).first).floatValue() - 0.001f) <= r02) goto L15;
        r1 = r1 - 1;
    L15:
        setTimestampIndex(r1);
        if (this.timestampLabel != null) goto L18;
        this.timestampLabel = new StaticLayout[2];
    L18:
        float r03 = this.selectorWidth / 2.0f;
        if (this.lastDuration <= 600000) goto L21;
        int r5 = AndroidUtilities.dp(42.0f);
    L22:
        float r04 = r03 + r5;
        float r52 = getMeasuredWidth() - (this.selectorWidth / 2.0f);
        if (this.lastDuration <= 600000) goto L25;
        int r6 = AndroidUtilities.dp(42.0f);
    L26:
        float r53 = Math.abs(r04 - (r52 - r6)) - AndroidUtilities.dp(66.0f);
        float r62 = this.lastWidth;
        if (r62 > 0.0f) goto L29;
    L36:
        this.lastWidth = r53;
        if (r1 == this.currentTimestamp) goto L62;
        StaticLayout[] r63 = this.timestampLabel;
        r63[1] = r63[0];
        if (this.pressed == false) goto L42;
        AndroidUtilities.vibrateCursor(this);
    L42:
        if (r1 >= 0) goto L44;
    L49:
        this.timestampLabel[0] = null;
    L50:
        this.timestampChangeT = 0.0f;
        if (r1 != (-1)) goto L53;
        this.timestampChangeDirection = -1;
    L60:
        this.lastTimestamp = this.currentTimestamp;
        this.currentTimestamp = r1;
        goto L62
    L53:
        int r54 = this.currentTimestamp;
        if (r54 != (-1)) goto L56;
        this.timestampChangeDirection = 1;
        goto L60
    L56:
        if (r1 >= r54) goto L58;
        this.timestampChangeDirection = -1;
        goto L60
    L58:
        if (r1 <= r54) goto L60;
        this.timestampChangeDirection = 1;
        goto L60
    L44:
        if (r1 >= this.timestamps.size()) goto L49;
        CharSequence r8 = (CharSequence) this.timestamps.get(r1).second;
        StaticLayout[] r10 = this.timestampLabel;
        if (r8 != null) goto L48;
        r10[0] = null;
        goto L50
    L48:
        r10[0] = makeStaticLayout(r8, (int) r53);
    L62:
        if (this.timestampChangeT >= 1.0f) goto L69;
        long r102 = Math.min(17, Math.abs(SystemClock.elapsedRealtime() - this.lastTimestampUpdate));
        if (this.timestamps.size() <= 8) goto L66;
        float r12 = 160.0f;
    L67:
        this.timestampChangeT = Math.min(this.timestampChangeT + (r102 / r12), 1.0f);
        invalidate();
        this.lastTimestampUpdate = SystemClock.elapsedRealtime();
        goto L69
    L66:
        r12 = 220.0f;
    L69:
        if (this.timestampsAppearing >= 1.0f) goto L71;
        this.timestampsAppearing = Math.min(this.timestampsAppearing + (Math.min(17, Math.abs(SystemClock.elapsedRealtime() - this.lastTimestampUpdate)) / 200.0f), 1.0f);
        invalidate();
        this.lastTimestampsAppearingUpdate = SystemClock.elapsedRealtime();
    L71:
        float r13 = CubicBezierInterpolator.DEFAULT.getInterpolation(this.timestampChangeT);
        r15.save();
        r15.translate(r04 + AndroidUtilities.dp(25.0f), (getMeasuredHeight() / 2.0f) + AndroidUtilities.dp(14.0f));
        this.timestampLabelPaint.setColor(getThemedColor(Theme.key_player_time));
        if (this.timestampLabel[1] == null) goto L78;
        r15.save();
        if (this.timestampChangeDirection == 0) goto L76;
        r15.translate(AndroidUtilities.dp(8.0f) + ((AndroidUtilities.dp(16.0f) * (-this.timestampChangeDirection)) * r13), 0.0f);
    L76:
        r15.translate(0.0f, (-this.timestampLabel[1].getHeight()) / 2.0f);
        this.timestampLabelPaint.setAlpha((int) (((1.0f - r13) * 255.0f) * this.timestampsAppearing));
        r15.restore();
    L78:
        if (this.timestampLabel[0] == null) goto L83;
        r15.save();
        if (this.timestampChangeDirection == 0) goto L82;
        r15.translate(AndroidUtilities.dp(8.0f) + ((AndroidUtilities.dp(16.0f) * this.timestampChangeDirection) * (1.0f - r13)), 0.0f);
    L82:
        r15.translate(0.0f, (-this.timestampLabel[0].getHeight()) / 2.0f);
        this.timestampLabelPaint.setAlpha((int) ((r13 * 255.0f) * this.timestampsAppearing));
        r15.restore();
    L83:
        r15.restore();
        return;
    L29:
        if (Math.abs(r62 - r53) <= 0.01f) goto L36;
        StaticLayout[] r64 = this.timestampLabel;
        StaticLayout r82 = r64[0];
        if (r82 == null) goto L33;
        r64[0] = makeStaticLayout(r82.getText(), (int) r53);
    L33:
        StaticLayout[] r65 = this.timestampLabel;
        StaticLayout r83 = r65[1];
        if (r83 == null) goto L36;
        r65[1] = makeStaticLayout(r83.getText(), (int) r53);
        goto L36
    L25:
        r6 = 0;
        goto L26
    L21:
        r5 = 0;
        goto L22
    L14:
        r1 = -1;
        goto L15
    }

    private int getInnerTrackColor() {
        if (this.hasCustomInnerColor == false) goto L7;
        return this.customInnerColor;
    L7:
        return getThemedColor(Theme.key_player_progressBackground);
    }

    private int getMaterialSliderStepsCount() {
        int r0 = this.separatorsCount;
        if (r0 > 1) goto L5;
        SeekBarViewDelegate r2 = this.delegate;
        if (r2 != null) goto L9;
        return 0;
    L9:
        return Math.max(r2.getStepsCount(), 0);
    L5:
        return r0 - 1;
    }

    private float getMaterialSliderValueFromProgress(float r1, int r2) {
        float r0 = Utilities.clamp01(r1);
        if (r2 > 0) goto L5;
        return r0;
    L5:
        return Math.round(r0 * r2);
    }

    private float getProgressFromMaterialSliderValue(float r2) {
        int r1 = getMaterialSliderStepsCount();
        if (r1 <= 0) goto L7;
        return Utilities.clamp(r2 / r1, 1.0f, 0.0f);
    L7:
        return Utilities.clamp01(r2);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private int getTimestampLabelWidth() {
        float r0 = this.selectorWidth / 2.0f;
        int r3 = 0;
        if (this.lastDuration <= 600000) goto L5;
        int r2 = AndroidUtilities.dp(42.0f);
    L6:
        float r02 = r0 + r2;
        float r22 = getMeasuredWidth() - (this.selectorWidth / 2.0f);
        if (this.lastDuration <= 600000) goto L10;
        r3 = AndroidUtilities.dp(42.0f);
    L10:
        return (int) (Math.abs(r02 - (r22 - r3)) - AndroidUtilities.dp(66.0f));
    L5:
        r2 = 0;
        goto L6
    }

    private void initMaterialSlider(Context r3) {
        Slider r32 = MaterialSliderUiHelper.create(r3);
        this.materialSlider = r32;
        r32.setImportantForAccessibility(2);
        this.materialSlider.setFocusable(false);
        this.materialSlider.setFocusableInTouchMode(false);
        this.materialSlider.setValueFrom(0.0f);
        this.materialSlider.setValueTo(1.0f);
        MaterialSliderUiHelper.applyContinuousStyle(this.materialSlider);
        this.materialSlider.addOnChangeListener(new SeekBarView$$ExternalSyntheticLambda1(this));
        this.materialSlider.addOnSliderTouchListener(new AnonymousClass3(this));
        updateMaterialSliderColors();
        this.materialSlider.setVisibility(8);
        this.materialSlider.setEnabled(isEnabled());
        addView(this.materialSlider, LayoutHelper.createFrame(-1, -1, 16));
    }

    private boolean isUsingMaterialSlider() {
        Slider r0 = this.materialSlider;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getVisibility() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$initMaterialSlider$2(Slider r1, float r2, boolean r3) {
        if (this.ignoreMaterialSliderChanges == true) goto L10;
        if (r3 == false) goto L12;
        float r12 = getProgressFromMaterialSliderValue(r2);
        setProgressFromMaterialSlider(getProgressFromMaterialSliderValue(r2));
        if (this.reportChanges == false) goto L11;
        setSeekBarDrag(false, r12);
        return;
    L11:
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$onTouch$0() {
        this.pressedDelayed = false;
    }

    private StaticLayout makeStaticLayout(CharSequence r4, int r5) {
        if (this.timestampLabelPaint != null) goto L5;
        TextPaint r0 = new TextPaint(1);
        this.timestampLabelPaint = r0;
        r0.setTextSize(AndroidUtilities.dp(12.0f));
    L5:
        this.timestampLabelPaint.setColor(getThemedColor(Theme.key_player_time));
        if (r4 != null) goto L9;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return StaticLayout.Builder.obtain(r4, 0, r4.length(), this.timestampLabelPaint, r5).setMaxLines(1).setAlignment(Layout.Alignment.ALIGN_CENTER).setEllipsize(TextUtils.TruncateAt.END).setEllipsizedWidth(Math.min(AndroidUtilities.dp(400.0f), r5)).build();
    }

    private int minThumbX() {
        return Math.max((int) (this.minProgress * (getMeasuredWidth() - this.selectorWidth)), 0);
    }

    private void setMaterialSliderValue(float r2) {
        this.ignoreMaterialSliderChanges = true;
        MaterialSliderUiHelper.setValue(this.materialSlider, r2);
        this.ignoreMaterialSliderChanges = false;
    }

    private void setProgressFromMaterialSlider(float r3) {
        if (getMeasuredWidth() > this.selectorWidth) goto L6;
        this.progressToSet = r3;
        return;
    L6:
        int r32 = (int) Math.ceil((getMeasuredWidth() - this.selectorWidth) * Utilities.clamp01(r3));
        this.thumbX = r32;
        if (r32 >= minThumbX()) goto L10;
        this.thumbX = minThumbX();
    L12:
        invalidate();
        return;
    L10:
        if (this.thumbX <= (getMeasuredWidth() - this.selectorWidth)) goto L12;
        this.thumbX = getMeasuredWidth() - this.selectorWidth;
        goto L12
    }

    private void setSeekBarDrag(boolean r3, float r4) {
        SeekBarViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onSeekBarDrag(r3, r4);
    L6:
        if (this.separatorsCount <= 1) goto L14;
        int r42 = Math.round((r0 - 1) * r4);
        if (r3 == false) goto L10;
    L12:
        this.lastValue = r42;
        return;
    L10:
        if (r42 == this.lastValue) goto L12;
        AndroidUtilities.vibrateCursor(this);
        goto L12
    }

    private void setTimestampIndex(int r2) {
        if (this.timestampIndex == r2) goto L10;
        this.timestampIndex = r2;
        if (r2 >= 0) goto L7;
        return;
    L7:
        if (r2 >= this.timestamps.size()) goto L12;
        CharSequence r22 = (CharSequence) this.timestamps.get(this.timestampIndex).second;
        this.textViewSwitcher.setText(r22);
        return;
    L12:
        return;
    }

    private void updateMaterialSliderColors() {
        Slider r0 = this.materialSlider;
        if (r0 != null) goto L5;
        return;
    L5:
        MaterialSliderUiHelper.applyColors(r0, this.outerPaint1.getColor(), getInnerTrackColor());
    }

    private void updateMaterialSliderProgress(float r2) {
        if (this.materialSlider != null) goto L5;
        return;
    L5:
        setMaterialSliderValue(getMaterialSliderValueFromProgress(r2, getMaterialSliderStepsCount()));
    }

    private void updateMaterialSliderState() {
        boolean r0 = canUseMaterialSlider();
        if (r0 == true) goto L5;
    L7:
        Slider r1 = this.materialSlider;
        if (r1 == null) goto L50;
        int r2 = 8;
        if (r0 == false) goto L13;
        int r4 = 0;
    L15:
        if (r1.getVisibility() == r4) goto L21;
        this.materialSlider.setVisibility(r4);
        AudioPlayerAlert.ClippingTextViewSwitcher r12 = this.textViewSwitcher;
        if (r0 == true) goto L20;
        r2 = 0;
    L20:
        r12.setVisibility(r2);
    L21:
        if (r0 == false) goto L47;
        int r02 = getMaterialSliderStepsCount();
        float r13 = 1.0f;
        if (r02 <= 0) goto L25;
        float r22 = r02;
    L27:
        if (r02 > 0) goto L31;
        r13 = 0.0f;
    L31:
        if (r02 != 0) goto L36;
        if (Math.abs(this.materialSlider.getStepSize()) <= 1.0E-4f) goto L36;
        this.materialSlider.setStepSize(0.0f);
    L36:
        if (this.materialSlider.getValue() <= r22) goto L39;
        setMaterialSliderValue(r22);
    L39:
        if (Math.abs(this.materialSlider.getValueTo() - r22) <= 1.0E-4f) goto L41;
        this.materialSlider.setValueTo(r22);
    L41:
        updateMaterialSliderColors();
        updateMaterialSliderProgress(getProgress());
        if (r02 > 0) goto L44;
        return;
    L44:
        if (Math.abs(this.materialSlider.getStepSize() - r13) <= 1.0E-4f) goto L49;
        this.materialSlider.setStepSize(r13);
        return;
    L49:
        return;
    L25:
        r22 = 1.0f;
        goto L27
    L47:
        return;
    L13:
        r4 = 8;
        goto L15
    L50:
        return;
    L5:
        if (this.materialSlider != null) goto L7;
        initMaterialSlider(getContext());
        goto L7
    }

    public void clearTimestamps() {
        this.timestamps = null;
        this.currentTimestamp = -1;
        this.timestampsAppearing = 0.0f;
        StaticLayout[] r1 = this.timestampLabel;
        if (r1 == null) goto L5;
        r1[1] = null;
        r1[0] = null;
    L5:
        this.lastCaption = null;
        this.lastDuration = -1;
        updateMaterialSliderState();
    }

    public float getProgress() {
        if (getMeasuredWidth() != 0) goto L7;
        return this.progressToSet;
    L7:
        return this.thumbX / (getMeasuredWidth() - this.selectorWidth);
    }

    public SeekBarAccessibilityDelegate getSeekBarAccessibilityDelegate() {
        return this.seekBarAccessibilityDelegate;
    }

    public boolean isDragging() {
        return this.pressed;
    }

    public boolean isTwoSided() {
        return this.twoSided;
    }

    @Override // android.view.View
    public void onDraw(Canvas r17) {
        Canvas r1 = r17;
        updateMaterialSliderState();
        if (isUsingMaterialSlider() == true) goto L70;
        int r2 = this.thumbX;
        boolean r9 = true;
        if (this.twoSided == false) goto L8;
    L12:
        SeekBarViewDelegate r3 = this.delegate;
        if (r3 != null) goto L15;
    L11:
        int r11 = r2;
        int r12 = (getMeasuredHeight() - this.thumbSize) / 2;
        this.innerPaint1.setColor(getInnerTrackColor());
        float r22 = getMeasuredHeight() / 2.0f;
        float r4 = this.selectorWidth / 2.0f;
        float r5 = getMeasuredWidth() - (this.selectorWidth / 2);
        float r6 = r22 - (AndroidUtilities.dp(this.lineWidthDp) / 2.0f);
        float r23 = r22 + (AndroidUtilities.dp(this.lineWidthDp) / 2.0f);
        this.rect.set(r4, r6, r5, r23);
        drawProgressBar(r1, this.rect, this.innerPaint1);
        if (this.bufferedProgress <= 0.0f) goto L20;
        this.innerPaint1.setColor(getThemedColor(Theme.key_player_progressCachedBackground));
        this.rect.set(r4, r6, (this.selectorWidth / 2.0f) + (this.bufferedProgress * (getMeasuredWidth() - this.selectorWidth)), r23);
        drawProgressBar(r1, this.rect, this.innerPaint1);
    L20:
        float r14 = 6.0f;
        if (this.twoSided == false) goto L26;
        r1.drawRoundRect((getMeasuredWidth() / 2) - AndroidUtilities.dp(1.0f), (getMeasuredHeight() / 2) - AndroidUtilities.dp(6.0f), (getMeasuredWidth() / 2) + AndroidUtilities.dp(1.0f), (getMeasuredHeight() / 2) + AndroidUtilities.dp(6.0f), 100.0f, 100.0f, this.outerPaint1);
        if (r11 <= ((getMeasuredWidth() - this.selectorWidth) / 2)) goto L25;
        r1 = r17;
        r1.drawRoundRect(getMeasuredWidth() / 2, (getMeasuredHeight() / 2) - AndroidUtilities.dp(1.0f), (this.selectorWidth / 2) + r11, (getMeasuredHeight() / 2) + AndroidUtilities.dp(1.0f), 100.0f, 100.0f, this.outerPaint1);
    L31:
        if (this.hoverDrawable == null) goto L34;
        int r24 = ((this.selectorWidth / 2) + r11) - AndroidUtilities.dp(16.0f);
        int r42 = ((this.thumbSize / 2) + r12) - AndroidUtilities.dp(16.0f);
        this.hoverDrawable.setBounds(r24, r42, AndroidUtilities.dp(32.0f) + r24, AndroidUtilities.dp(32.0f) + r42);
        this.hoverDrawable.draw(r1);
    L34:
        if (this.pressed == false) goto L36;
        r14 = 8.0f;
    L36:
        int r25 = AndroidUtilities.dp(r14);
        long r32 = SystemClock.elapsedRealtime() - this.lastUpdateTime;
        if (r32 <= 18) goto L39;
        r32 = 16;
    L39:
        float r52 = this.currentRadius;
        float r26 = r25;
        if (r52 != r26) goto L42;
        boolean r27 = false;
    L51:
        float r53 = this.transitionProgress;
        if (r53 >= 1.0f) goto L57;
        float r54 = r53 + (r32 / 225.0f);
        this.transitionProgress = r54;
        if (r54 < 1.0f) goto L58;
        this.transitionProgress = 1.0f;
    L58:
        float r28 = this.transitionProgress;
        if (r28 >= 1.0f) goto L64;
        float r10 = 1.0f - Easings.easeInQuad.getInterpolation(Math.min(1.0f, r28 * 3.0f));
        float r29 = Easings.easeOutQuad.getInterpolation(this.transitionProgress);
        if (r10 <= 0.0f) goto L63;
        r1.drawCircle(this.transitionThumbX + (this.selectorWidth / 2), (this.thumbSize / 2) + r12, this.currentRadius * r10, this.outerPaint1);
    L63:
        r1.drawCircle(r11 + (this.selectorWidth / 2), r12 + (this.thumbSize / 2), this.currentRadius * r29, this.outerPaint1);
    L65:
        drawTimestampLabel(r17);
        if (r9 == false) goto L69;
        postInvalidateOnAnimation();
        return;
    L69:
        return;
    L64:
        r1.drawCircle(r11 + (this.selectorWidth / 2), r12 + (this.thumbSize / 2), this.currentRadius, this.outerPaint1);
    L57:
        r9 = r27;
        goto L58
    L42:
        if (r52 >= r26) goto L46;
        float r55 = r52 + (AndroidUtilities.dp(1.0f) * (r32 / 60.0f));
        this.currentRadius = r55;
        if (r55 <= r26) goto L49;
        this.currentRadius = r26;
    L49:
        r27 = true;
        goto L51
    L46:
        float r56 = r52 - (AndroidUtilities.dp(1.0f) * (r32 / 60.0f));
        this.currentRadius = r56;
        if (r56 >= r26) goto L49;
        this.currentRadius = r26;
        goto L49
    L25:
        r1 = r17;
        r1.drawRoundRect((r2 / 2) + r11, (getMeasuredHeight() / 2) - AndroidUtilities.dp(1.0f), getMeasuredWidth() / 2, (getMeasuredHeight() / 2) + AndroidUtilities.dp(1.0f), 100.0f, 100.0f, this.outerPaint1);
        goto L31
    L26:
        float r33 = this.minProgress;
        RectF r8 = this.rect;
        if (r33 < 0.0f) goto L29;
        float r57 = r5 - r4;
        r8.set((r33 * r57) + r4, r6, r11 + r4, r23);
        drawProgressBar(r1, this.rect, this.outerPaint1);
        int r34 = this.outerPaint1.getAlpha();
        this.rect.set(r4, r6, (this.minProgress * r57) + r4, r23);
        this.outerPaint1.setAlpha((int) (r34 * 0.5f));
        drawProgressBar(r1, this.rect, this.outerPaint1);
        this.outerPaint1.setAlpha(r34);
        goto L31
    L29:
        r8.set(r4, r6, r11 + r4, r23);
        drawProgressBar(r1, this.rect, this.outerPaint1);
        goto L31
    L15:
        if (r3.needVisuallyDivideSteps() == false) goto L11;
        float r210 = Math.round(r2 / r3) * ((getMeasuredWidth() - this.selectorWidth) / (this.delegate.getStepsCount() - 1.0f));
    L10:
        r2 = (int) r210;
        goto L11
    L8:
        if (this.separatorsCount <= 1) goto L12;
        r210 = this.animatedThumbX.set(Math.round(r2 / r3) * ((getMeasuredWidth() - this.selectorWidth) / (this.separatorsCount - 1.0f)));
        goto L10
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        updateMaterialSliderState();
        if (isUsingMaterialSlider() == false) goto L7;
        return false;
    L7:
        return onTouch(r2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        super.onLayout(r3, r4, r5, r6, r7);
        int r32 = ((getMeasuredHeight() / 2) + AndroidUtilities.dp(14.0f)) + (this.textViewSwitcher.getMeasuredHeight() / 2);
        int r42 = this.selectorWidth / 2;
        if (this.lastDuration <= 600000) goto L5;
        int r52 = AndroidUtilities.dp(42.0f);
    L6:
        int r43 = ((r42 + r52) + AndroidUtilities.dp(25.0f)) + AndroidUtilities.dp(8.0f);
        AudioPlayerAlert.ClippingTextViewSwitcher r53 = this.textViewSwitcher;
        r53.layout(r43, r32 - r53.getMeasuredHeight(), this.textViewSwitcher.getMeasuredWidth() + r43, r32);
        return;
    L5:
        r52 = 0;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        updateMaterialSliderState();
        super.onMeasure(r2, r3);
        int r22 = getTimestampLabelWidth();
        this.lastTimestampLabelWidth = r22;
        this.textViewSwitcher.measure(View.MeasureSpec.makeMeasureSpec(r22, TLObject.FLAG_30), 0);
        if (this.progressToSet != (-100.0f)) goto L5;
        return;
    L5:
        if (getMeasuredWidth() <= 0) goto L9;
        setProgress(this.progressToSet);
        this.progressToSet = -100.0f;
        return;
    }

    public boolean onTouch(MotionEvent r10) {
        if (isEnabled() == true) goto L6;
        return false;
    L6:
        if (r10.getAction() != 0) goto L10;
        this.sx = r10.getX();
        this.sy = r10.getY();
        return true;
    L10:
        if (r10.getAction() != 1) goto L12;
    L64:
        this.captured = false;
        if (r10.getAction() != 1) goto L80;
        ViewConfiguration r0 = ViewConfiguration.get(getContext());
        if (Math.abs(r10.getY() - this.sy) >= r0.getScaledTouchSlop()) goto L80;
        int r02 = (getMeasuredHeight() - this.thumbSize) / 2;
        if ((this.thumbX - r02) <= r10.getX()) goto L71;
    L72:
        int r03 = ((int) r10.getX()) - (this.thumbSize / 2);
        this.thumbX = r03;
        if (r03 >= minThumbX()) goto L76;
        this.thumbX = minThumbX();
    L78:
        this.thumbDX = (int) (r10.getX() - this.thumbX);
        this.pressedDelayed = true;
        this.pressed = true;
        goto L80
    L76:
        if (this.thumbX <= (getMeasuredWidth() - this.selectorWidth)) goto L78;
        this.thumbX = getMeasuredWidth() - this.selectorWidth;
        goto L78
    L71:
        if (r10.getX() <= ((this.thumbX + this.thumbSize) + r02)) goto L78;
    L80:
        if (this.pressed == true) goto L82;
    L95:
        return false;
    L82:
        if (r10.getAction() == 1) goto L84;
    L90:
        Drawable r102 = this.hoverDrawable;
        if (r102 == null) goto L93;
        r102.setState(StateSet.NOTHING);
    L93:
        this.delegate.onSeekBarPressed(false);
        this.pressed = false;
        AndroidUtilities.runOnUIThread(new SeekBarView$$ExternalSyntheticLambda2(this), 50);
        invalidate();
        return true;
    L84:
        if (this.twoSided == false) goto L89;
        float r103 = (getMeasuredWidth() - this.selectorWidth) / 2;
        int r04 = this.thumbX;
        if (r04 < r103) goto L88;
        setSeekBarDrag(false, (r04 - r103) / r103);
        goto L90
    L88:
        setSeekBarDrag(false, -Math.max(0.01f, 1.0f - ((r103 - r04) / r103)));
        goto L90
    L89:
        setSeekBarDrag(true, this.thumbX / (getMeasuredWidth() - this.selectorWidth));
        goto L90
    L12:
        if (r10.getAction() == 3) goto L64;
        if (r10.getAction() != 2) goto L95;
        if (this.captured == true) goto L43;
        ViewConfiguration r05 = ViewConfiguration.get(getContext());
        if (Math.abs(r10.getY() - this.sy) <= r05.getScaledTouchSlop()) goto L22;
        return false;
    L22:
        if (Math.abs(r10.getX() - this.sx) <= r05.getScaledTouchSlop()) goto L95;
        this.captured = true;
        getParent().requestDisallowInterceptTouchEvent(true);
        int r06 = (getMeasuredHeight() - this.thumbSize) / 2;
        if (r10.getY() < 0.0f) goto L95;
        if (r10.getY() > getMeasuredHeight()) goto L95;
        if ((this.thumbX - r06) <= r10.getX()) goto L30;
    L31:
        int r07 = ((int) r10.getX()) - (this.thumbSize / 2);
        this.thumbX = r07;
        if (r07 >= minThumbX()) goto L35;
        this.thumbX = minThumbX();
    L37:
        this.thumbDX = (int) (r10.getX() - this.thumbX);
        this.pressedDelayed = true;
        this.pressed = true;
        this.delegate.onSeekBarPressed(true);
        Drawable r08 = this.hoverDrawable;
        if (r08 == null) goto L40;
        r08.setState(this.pressedState);
        this.hoverDrawable.setHotspot(r10.getX(), r10.getY());
    L40:
        invalidate();
        return true;
    L35:
        if (this.thumbX <= (getMeasuredWidth() - this.selectorWidth)) goto L37;
        this.thumbX = getMeasuredWidth() - this.selectorWidth;
        goto L37
    L30:
        if (r10.getX() <= ((this.thumbX + this.thumbSize) + r06)) goto L37;
    L43:
        if (this.pressed == false) goto L95;
        int r09 = (int) (r10.getX() - this.thumbDX);
        this.thumbX = r09;
        if (r09 >= minThumbX()) goto L48;
        this.thumbX = minThumbX();
    L51:
        if (this.reportChanges == true) goto L53;
    L59:
        Drawable r010 = this.hoverDrawable;
        if (r010 == null) goto L62;
        r010.setHotspot(r10.getX(), r10.getY());
    L62:
        invalidate();
        return true;
    L53:
        if (this.twoSided == false) goto L58;
        float r011 = (getMeasuredWidth() - this.selectorWidth) / 2;
        int r5 = this.thumbX;
        if (r5 < r011) goto L57;
        setSeekBarDrag(false, (r5 - r011) / r011);
        goto L59
    L57:
        setSeekBarDrag(false, -Math.max(0.01f, 1.0f - ((r011 - r5) / r011)));
        goto L59
    L58:
        setSeekBarDrag(false, this.thumbX / (getMeasuredWidth() - this.selectorWidth));
        goto L59
    L48:
        if (this.thumbX <= (getMeasuredWidth() - this.selectorWidth)) goto L51;
        this.thumbX = getMeasuredWidth() - this.selectorWidth;
        goto L51
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        updateMaterialSliderState();
        if (isUsingMaterialSlider() == false) goto L7;
        return false;
    L7:
        return onTouch(r2);
    }

    public void setBufferedProgress(float r2) {
        if (r2 <= 0.0f) goto L5;
        boolean r0 = true;
    L6:
        this.hasBufferedProgress = r0;
        this.bufferedProgress = r2;
        updateMaterialSliderState();
        invalidate();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void setDelegate(SeekBarViewDelegate r1) {
        this.delegate = r1;
        updateMaterialSliderState();
    }

    @Override // android.view.View
    public void setEnabled(boolean r1) {
        super.setEnabled(r1);
        Slider r0 = this.materialSlider;
        if (r0 == null) goto L6;
        r0.setEnabled(r1);
        return;
    }

    public void setInnerColor(int r2) {
        this.hasCustomInnerColor = true;
        this.customInnerColor = r2;
        this.innerPaint1.setColor(r2);
        updateMaterialSliderColors();
    }

    public void setLineWidth(int r1) {
        this.lineWidthDp = r1;
        updateMaterialSliderState();
    }

    public void setMinProgress(float r2) {
        this.minProgress = r2;
        float r22 = getProgress();
        float r0 = this.minProgress;
        if (r22 >= r0) goto L5;
        setProgress(r0, false);
    L5:
        updateMaterialSliderState();
        invalidate();
    }

    public void setOuterColor(int r3) {
        this.outerPaint1.setColor(r3);
        Drawable r0 = this.hoverDrawable;
        if (r0 == null) goto L5;
        Theme.setSelectorDrawableColor(r0, ColorUtils.setAlphaComponent(r3, 40), true);
    L5:
        updateMaterialSliderColors();
    }

    public void setProgress(float r2) {
        setProgress(r2, false);
    }

    public void setProgress(float r5, boolean r6) {
        if (getMeasuredWidth() != 0) goto L6;
        this.progressToSet = r5;
        updateMaterialSliderProgress(r5);
        return;
    L6:
        this.progressToSet = -100.0f;
        if (this.twoSided == false) goto L12;
        float r0 = (getMeasuredWidth() - this.selectorWidth) / 2;
        if (r5 >= 0.0f) goto L11;
        double r2 = Math.ceil(r0 + ((-(r5 + 1.0f)) * r0));
    L13:
        int r52 = (int) r2;
        int r02 = this.thumbX;
        if (r02 == r52) goto L25;
        if (r6 == false) goto L17;
        this.transitionThumbX = r02;
        this.transitionProgress = 0.0f;
    L17:
        this.thumbX = r52;
        if (r52 >= minThumbX()) goto L21;
        this.thumbX = minThumbX();
    L23:
        updateMaterialSliderProgress(getProgress());
        invalidate();
        return;
    L21:
        if (this.thumbX <= (getMeasuredWidth() - this.selectorWidth)) goto L23;
        this.thumbX = getMeasuredWidth() - this.selectorWidth;
        goto L23
    L25:
        return;
    L11:
        r2 = Math.ceil(r0 + (r5 * r0));
        goto L13
    L12:
        r2 = Math.ceil((getMeasuredWidth() - this.selectorWidth) * r5);
        goto L13
    }

    public void setReportChanges(boolean r1) {
        this.reportChanges = r1;
    }

    public void setSeparatorsCount(int r1) {
        this.separatorsCount = r1;
        updateMaterialSliderState();
    }

    public void setTwoSided(boolean r1) {
        this.twoSided = r1;
        updateMaterialSliderState();
    }

    public void updateTimestamps(MessageObject r11, Long r12) {
        if (r11 != null) goto L6;
        clearTimestamps();
        return;
    L6:
        if (r12 != null) goto L9;
        r12 = Long.valueOf(((long) r11.getDuration()) * 1000);
    L9:
        if (r12.longValue() >= 0) goto L12;
        clearTimestamps();
        return;
    L12:
        CharSequence r2 = r11.caption;
        if (r11.isYouTubeVideo() == false) goto L21;
        if (r11.youtubeDescription != null) goto L19;
        String r22 = r11.messageOwner.media.webpage.description;
        if (r22 == null) goto L19;
        r11.youtubeDescription = SpannableString.valueOf(r22);
        MessageObject.addUrlsByPattern(r11.isOut(), r11.youtubeDescription, false, 3, (int) r12.longValue(), false);
    L19:
        r2 = r11.youtubeDescription;
    L21:
        if (r2 == this.lastCaption) goto L23;
        boolean r112 = true;
    L24:
        if (r112 == false) goto L26;
    L28:
        this.lastCaption = r2;
        this.lastDuration = r12.longValue() * 10;
        if (getTimestampLabelWidth() == this.lastTimestampLabelWidth) goto L32;
        requestLayout();
    L32:
        if ((r2 instanceof Spanned) == true) goto L38;
        this.timestamps = null;
        this.currentTimestamp = -1;
        this.timestampsAppearing = 0.0f;
        StaticLayout[] r113 = this.timestampLabel;
        if (r113 == null) goto L36;
        r113[1] = null;
        r113[0] = null;
    L36:
        updateMaterialSliderState();
        return;
    L38:
        Spanned r23 = (Spanned) r2;
        URLSpanNoUnderline[] r24 = (URLSpanNoUnderline[]) r23.getSpans(0, r23.length(), URLSpanNoUnderline.class);     // Catch: Exception -> L63
        this.timestamps = new ArrayList();
        if (r112 == false) goto L44;
        this.timestampsAppearing = 0.0f;
    L44:
        if (this.timestampLabelPaint != null) goto L46;
        TextPaint r114 = new TextPaint(1);
        this.timestampLabelPaint = r114;
        r114.setTextSize(AndroidUtilities.dp(12.0f));
        this.timestampLabelPaint.setColor(-1);
    L46:
        int r115 = 0;
    L48:
        if (r115 >= r24.length) goto L61;
        URLSpanNoUnderline r3 = r24[r115];
        if (r3 == null) goto L60;
        if (r3.getURL() == null) goto L60;
        if (r3.label == null) goto L60;
        if (r3.getURL().startsWith("audio?") == false) goto L60;
        if (Utilities.parseInt(r3.getURL().substring(6)) < 0) goto L60;
        SpannableStringBuilder r6 = new SpannableStringBuilder(r3.label);
        Emoji.replaceEmoji(r6, this.timestampLabelPaint.getFontMetricsInt(), false);
        this.timestamps.add(new Pair(Float.valueOf((r5 * 1000) / r12.longValue()), r6));
    L60:
        r115 = r115 + 1;
        goto L48
    L61:
        Collections.sort(this.timestamps, new SeekBarView$$ExternalSyntheticLambda0());
        updateMaterialSliderState();
        return;
    L63:
        e = move-exception;
        FileLog.e(e);
        this.timestamps = null;
        this.currentTimestamp = -1;
        this.timestampsAppearing = 0.0f;
        StaticLayout[] r116 = this.timestampLabel;
        if (r116 == null) goto L67;
        r116[1] = null;
        r116[0] = null;
    L67:
        updateMaterialSliderState();
        return;
    L26:
        if (this.lastDuration != r12.longValue()) goto L28;
        return;
    L23:
        r112 = false;
        goto L24
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.hoverDrawable) goto L11;
        return false;
    L11:
        return true;
    }
}
