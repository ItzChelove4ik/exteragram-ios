package org.telegram.ui.Components;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import androidx.core.graphics.ColorUtils;
import java.util.Random;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class CapsuleBlobDrawable extends Drawable {
    public static float MAX_SPEED = 8.2f;
    public static float MIN_SPEED = 0.8f;
    private static final TimeInterpolator SMOOTHER = null;
    private float amplitude;
    private float animateAmplitudeDiff;
    private float animateToAmplitude;
    private final Layer big;
    private float breathDepth;
    private float breathPeriodMs;
    private float breathPhase;
    private float colorProgress;
    private int colorState;
    private float cornerRadius;
    private int currentColor;
    private float curvatureBoost;
    private int fromColor;
    private float halfHeight;
    private float halfWidth;
    private float innerX;
    private float innerY;
    private long lastFrameTime;
    private int mAlpha;
    private final Runnable mInvalidateSelf;
    private float neighborCoherence;
    private final Path path;
    private float perimeter;
    private float radius;
    private boolean running;
    private final float[] sample;
    private final Layer small;
    private float straightH;
    private float straightV;
    private float targetSpacing;
    private int toColor;
    private float waveDepth;
    private float weightedArc;
    private float weightedTotal;

    public static final class Layer {
        int baseAlpha;
        float breathScale;
        int color;
        float[] depth;
        float[] depthEff;
        float[] depthNext;
        float[] depthTmp;
        private int mAlpha;
        private int n;
        float[] offset;
        float[] offsetEff;
        float[] offsetNext;
        final Paint paint;
        float phaseOffset;
        float[] progress;
        float pushMax;
        float pushMin;
        float[] px;
        float[] py;
        final Random random;
        float[] speed;
        float speedScale;
        float[] tx;
        float[] ty;
        float waveScale;

        private Layer() {
            this.color = -11318601;
            this.paint = new Paint(1);
            this.random = new Random();
            this.mAlpha = 255;
        }

        public /* synthetic */ Layer(CapsuleBlobDrawableIA r1) {
            this();
        }

        public static float clamp(float r1, float r2, float r3) {
            if (r1 >= r2) goto L6;
            return r2;
        L6:
            if (r1 <= r3) goto L8;
            return r3;
        L8:
            return r1;
        }

        public void applyColor() {
            this.paint.setColor(this.color);
            this.paint.setAlpha((this.baseAlpha * this.mAlpha) / 255);
        }

        public int count() {
            return this.n;
        }

        public void next(int r9) {
            float r1 = 0.18f / this.n;
            this.depthNext[r9] = clamp(this.depth[r9] + (((this.random.nextFloat() - 0.5f) * 2.0f) * 0.35f), 0.0f, 1.0f);
            this.offsetNext[r9] = clamp(this.offset[r9] + ((((this.random.nextFloat() - 0.5f) * 2.0f) * r1) * 0.35f), -r1, r1);
            this.speed[r9] = ((this.random.nextFloat() * 0.003f) + 0.017f) * this.speedScale;
        }

        public void resize(int r4) {
            this.n = r4;
            this.depth = new float[r4];
            this.depthNext = new float[r4];
            this.offset = new float[r4];
            this.offsetNext = new float[r4];
            this.progress = new float[r4];
            this.speed = new float[r4];
            this.px = new float[r4];
            this.py = new float[r4];
            this.tx = new float[r4];
            this.ty = new float[r4];
            this.depthEff = new float[r4];
            this.depthTmp = new float[r4];
            this.offsetEff = new float[r4];
            int r42 = 0;
        L4:
            if (r42 >= this.n) goto L6;
            this.depth[r42] = this.random.nextFloat();
            this.offset[r42] = (((this.random.nextFloat() - 0.5f) * 2.0f) * 0.18f) / this.n;
            next(r42);
            this.progress[r42] = this.random.nextFloat();
            r42 = r42 + 1;
            goto L4
        L6:
            applyColor();
        }

        public void setLayerAlpha(int r1) {
            this.mAlpha = r1;
            applyColor();
        }

        public void update(float r7) {
            int r0 = 0;
        L4:
            if (r0 >= this.n) goto L9;
            float[] r1 = this.progress;
            float r2 = r1[r0];
            float r3 = this.speed[r0];
            float r22 = r2 + ((CapsuleBlobDrawable.MIN_SPEED * r3) + ((r3 * r7) * CapsuleBlobDrawable.MAX_SPEED));
            r1[r0] = r22;
            if (r22 < 1.0f) goto L8;
            r1[r0] = 0.0f;
            this.depth[r0] = this.depthNext[r0];
            this.offset[r0] = this.offsetNext[r0];
            next(r0);
        L8:
            r0 = r0 + 1;
            goto L4
        }
    }

    public static /* synthetic */ float $r8$lambda$UdYoPCaMa_YR8vttLQa0v7_hpD4(float r3) {
        return ((r3 * r3) * r3) * ((r3 * ((6.0f * r3) - 15.0f)) + 10.0f);
    }

    public static /* synthetic */ void $r8$lambda$Uo_sZeLcY2Q7Q_XMjX5I0bm2Adw(CapsuleBlobDrawable r0) {
        r0.lambda$new$1();
    }

    static {
        SMOOTHER = new CapsuleBlobDrawable$$ExternalSyntheticLambda0();
    }

    public CapsuleBlobDrawable() {
        this.cornerRadius = AndroidUtilities.dp(18.0f);
        this.targetSpacing = AndroidUtilities.dp(22.0f);
        this.curvatureBoost = 2.4f;
        this.waveDepth = AndroidUtilities.dp(12.0f);
        this.breathDepth = AndroidUtilities.dp(1.5f);
        this.breathPeriodMs = 3600.0f;
        this.neighborCoherence = 0.25f;
        this.path = new Path();
        this.sample = new float[4];
        this.colorState = -1;
        this.colorProgress = 1.0f;
        this.mInvalidateSelf = new CapsuleBlobDrawable$$ExternalSyntheticLambda1(this);
        this.mAlpha = 255;
        Layer r1 = new Layer(null);
        this.big = r1;
        r1.speedScale = 1.0f;
        r1.phaseOffset = 0.0f;
        r1.pushMin = AndroidUtilities.dp(0.5f);
        r1.pushMax = AndroidUtilities.dp(8.5f);
        r1.waveScale = 1.0f;
        r1.breathScale = 1.0f;
        r1.baseAlpha = 61;
        Layer r0 = new Layer(null);
        this.small = r0;
        r0.speedScale = 0.82f;
        r0.phaseOffset = 0.6f;
        r0.pushMin = AndroidUtilities.dp(0.0f);
        r0.pushMax = AndroidUtilities.dp(4.25f);
        r0.waveScale = 0.55f;
        r0.breathScale = 0.55f;
        r0.baseAlpha = 128;
        setState(0);
    }

    private void applyColor(int r3) {
        this.currentColor = r3;
        Layer r0 = this.big;
        r0.color = r3;
        this.small.color = r3;
        r0.applyColor();
        this.small.applyColor();
    }

    private void arc(float r5, float r6, float r7, float[] r8) {
        double r0 = r7;
        float r72 = (float) Math.cos(r0);
        float r02 = (float) Math.sin(r0);
        float r4 = this.radius;
        r8[0] = r5 + (r4 * r72);
        r8[1] = r6 + (r4 * r02);
        r8[2] = -r02;
        r8[3] = r72;
    }

    private void drawLayer(Canvas r20, Layer r21, float r22, float r23, float r24) {
        int r2 = r21.count();
        if (r2 != 0) goto L5;
        return;
    L5:
        float r3 = r21.pushMin;
        float r4 = r21.pushMax - r3;
        float r5 = this.amplitude;
        float r32 = r3 + (r4 * r5);
        float r42 = (this.waveDepth * r21.waveScale) * ((r5 * 1.0f) + 0.0f);
        float[] r52 = r21.depthEff;
        float[] r8 = r21.depthTmp;
        float[] r9 = r21.offsetEff;
        int r11 = 0;
    L6:
        if (r11 >= r2) goto L9;
        float r12 = SMOOTHER.getInterpolation(r21.progress[r11]);
        float r14 = 1.0f - r12;
        r52[r11] = (r21.depth[r11] * r14) + (r21.depthNext[r11] * r12);
        r9[r11] = (r21.offset[r11] * r14) + (r21.offsetNext[r11] * r12);
        r11 = r11 + 1;
        goto L6
    L9:
        if (this.neighborCoherence <= 0.0f) goto L23;
        int r6 = 0;
    L11:
        if (r6 >= 2) goto L23;
        int r112 = 0;
    L13:
        if (r112 >= r2) goto L22;
        if (r112 != 0) goto L16;
        int r122 = r2 - 1;
    L17:
        int r13 = r112 + 1;
        if (r13 != r2) goto L20;
        int r142 = 0;
    L21:
        float r123 = (r52[r122] + r52[r142]) * 0.5f;
        float r143 = r52[r112];
        r8[r112] = r143 + (this.neighborCoherence * (r123 - r143));
        r112 = r13;
        goto L13
    L20:
        r142 = r13;
        goto L21
    L16:
        r122 = r112 - 1;
        goto L17
    L22:
        r6 = r6 + 1;
        float[] r18 = r8;
        r8 = r52;
        r52 = r18;
    L23:
        int r62 = 0;
    L24:
        if (r62 >= r2) goto L26;
        pointAt((r62 / r2) + r9[r62], this.sample);
        float[] r82 = this.sample;
        float r124 = r82[3];
        float r132 = -r82[2];
        float r144 = (r32 + r24) + (r52[r62] * r42);
        r21.px[r62] = (r22 + r82[0]) + (r124 * r144);
        r21.py[r62] = (r23 + r82[1]) + (r132 * r144);
        r21.tx[r62] = r82[2];
        r21.ty[r62] = r82[3];
        r62 = r62 + 1;
        goto L24
    L26:
        this.path.rewind();
        this.path.moveTo(r21.px[0], r21.py[0]);
        int r33 = 0;
    L27:
        if (r33 >= r2) goto L33;
        int r43 = r33 + 1;
        if (r43 >= r2) goto L31;
        int r53 = r43;
    L32:
        float[] r63 = r21.px;
        float r7 = r63[r53] - r63[r33];
        float[] r64 = r21.py;
        float r83 = r64[r53] - r64[r33];
        float r65 = ((float) Math.sqrt((r7 * r7) + (r83 * r83))) / 3.0f;
        Path r113 = this.path;
        float[] r72 = r21.px;
        float r84 = r72[r33];
        float[] r92 = r21.tx;
        float r125 = (r92[r33] * r65) + r84;
        float[] r85 = r21.py;
        float r133 = r85[r33];
        float[] r145 = r21.ty;
        float r134 = r133 + (r145[r33] * r65);
        float r16 = r72[r53];
        float r34 = r16 - (r92[r53] * r65);
        float r17 = r85[r53];
        r113.cubicTo(r125, r134, r34, r17 - (r145[r53] * r65), r16, r17);
        r33 = r43;
        goto L27
    L31:
        r53 = 0;
        goto L32
    L33:
        this.path.close();
        r20.drawPath(this.path, r21.paint);
    }

    private /* synthetic */ void lambda$new$1() {
        if (LiteMode.isEnabled(512) == false) goto L6;
        invalidateSelf();
        return;
    }

    private float maxOutwardExcursion() {
        Layer r0 = this.big;
        float r1 = r0.pushMax;
        float r2 = this.breathDepth;
        float r12 = r1 + (r0.breathScale * r2);
        float r3 = this.waveDepth;
        float r13 = r12 + (r0.waveScale * r3);
        Layer r5 = this.small;
        return Math.max(r13, (r5.pushMax + (r2 * r5.breathScale)) + (r3 * r5.waveScale));
    }

    private void pointAt(float r11, float[] r12) {
        float r112 = (r11 - ((float) Math.floor(r11))) * this.weightedTotal;
        float r0 = this.straightH;
        if (r112 >= r0) goto L6;
        r12[0] = (-this.innerX) + r112;
        r12[1] = -this.halfHeight;
        r12[2] = 1.0f;
        r12[3] = 0.0f;
        return;
    L6:
        float r113 = r112 - r0;
        float r1 = this.weightedArc;
        if (r113 >= r1) goto L10;
        arc(this.innerX, -this.innerY, (r113 / (this.curvatureBoost * this.radius)) - 1.5707964f, r12);
        return;
    L10:
        float r114 = r113 - r1;
        float r8 = this.straightV;
        if (r114 >= r8) goto L14;
        r12[0] = this.halfWidth;
        r12[1] = (-this.innerY) + r114;
        r12[2] = 0.0f;
        r12[3] = 1.0f;
        return;
    L14:
        float r115 = r114 - r8;
        if (r115 >= r1) goto L18;
        arc(this.innerX, this.innerY, r115 / (this.curvatureBoost * this.radius), r12);
        return;
    L18:
        float r116 = r115 - r1;
        if (r116 >= r0) goto L22;
        r12[0] = this.innerX - r116;
        r12[1] = this.halfHeight;
        r12[2] = -1.0f;
        r12[3] = 0.0f;
        return;
    L22:
        float r117 = r116 - r0;
        if (r117 >= r1) goto L26;
        arc(-this.innerX, this.innerY, (r117 / (this.curvatureBoost * this.radius)) + 1.5707964f, r12);
        return;
    L26:
        float r118 = r117 - r1;
        if (r118 >= r8) goto L30;
        r12[0] = -this.halfWidth;
        r12[1] = this.innerY - r118;
        r12[2] = 0.0f;
        r12[3] = -1.0f;
        return;
    L30:
        arc(-this.innerX, -this.innerY, ((r118 - r8) / (this.curvatureBoost * this.radius)) + 3.1415927f, r12);
    }

    private void rebuildGeometry() {
        if (getBounds().isEmpty() == true) goto L19;
        float r1 = maxOutwardExcursion() + AndroidUtilities.dp(1.0f);
        float r3 = (Math.min(r0.width(), r0.height()) / 2.0f) - AndroidUtilities.dp(2.0f);
        if (r1 <= r3) goto L8;
        r1 = Math.max(0.0f, r3);
    L8:
        this.halfWidth = (r0.width() / 2.0f) - r1;
        float r0 = (r0.height() / 2.0f) - r1;
        this.halfHeight = r0;
        float r12 = this.halfWidth;
        if (r12 >= 1.0f) goto L11;
        return;
    L11:
        if (r0 < 1.0f) goto L20;
        float r02 = Math.min(this.cornerRadius, Math.min(r12, r0));
        this.radius = r02;
        float r13 = this.halfWidth - r02;
        this.innerX = r13;
        float r2 = this.halfHeight - r02;
        this.innerY = r2;
        float r14 = r13 * 2.0f;
        this.straightH = r14;
        float r22 = r2 * 2.0f;
        this.straightV = r22;
        float r32 = (this.curvatureBoost * 1.5707964f) * r02;
        this.weightedArc = r32;
        this.weightedTotal = ((r14 * 2.0f) + (r22 * 2.0f)) + (r32 * 4.0f);
        float r15 = ((r14 * 2.0f) + (r22 * 2.0f)) + (r02 * 6.2831855f);
        this.perimeter = r15;
        int r03 = Math.max(12, Math.min(80, Math.round(r15 / this.targetSpacing)));
        if (r03 == this.big.count()) goto L18;
        this.big.resize(r03);
        this.small.resize(r03);
        return;
    L18:
        return;
    L20:
        return;
    }

    private static int resolveStateColor(int r2) {
        if (r2 == 0) goto L15;
        if (r2 == 1) goto L13;
        if (r2 == 3) goto L11;
        return Theme.getColor(Theme.key_voipgroup_topPanelGray);
    L11:
        return ColorUtils.blendARGB(ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient), Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient2), 0.5f), Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient3), 0.5f);
    L13:
        return ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_topPanelBlue1), Theme.getColor(Theme.key_voipgroup_topPanelBlue2), 0.5f);
    L15:
        return ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_topPanelGreen1), Theme.getColor(Theme.key_voipgroup_topPanelGreen2), 0.5f);
    }

    private void updateAmplitude(long r4) {
        float r0 = this.animateToAmplitude;
        float r1 = this.amplitude;
        if (r0 == r1) goto L14;
        float r2 = this.animateAmplitudeDiff;
        float r12 = r1 + (r4 * r2);
        this.amplitude = r12;
        if (r2 <= 0.0f) goto L11;
        if (r12 <= r0) goto L15;
        this.amplitude = r0;
        return;
    L15:
        return;
    L11:
        if (r12 >= r0) goto L16;
        this.amplitude = r0;
        return;
    L16:
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r12) {
        Rect r0 = getBounds();
        if (r0.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.halfWidth < 1.0f) goto L33;
        long r3 = SystemClock.elapsedRealtime();
        if (this.running == false) goto L10;
    L13:
        long r7 = Math.min(40, Math.max(0, r3 - this.lastFrameTime));
    L14:
        this.lastFrameTime = r3;
        if (LiteMode.isEnabled(512) == true) goto L17;
    L19:
        float r1 = this.colorProgress;
        if (r1 < 1.0f) goto L22;
    L27:
        float r6 = r0.exactCenterX();
        float r72 = r0.exactCenterY();
        float r02 = 1.0f - (this.amplitude * 0.7f);
        float r8 = ((this.breathDepth * this.big.breathScale) * r02) * ((((float) Math.sin(this.breathPhase)) * 0.5f) + 0.5f);
        float r13 = ((this.breathDepth * this.small.breathScale) * r02) * ((((float) Math.sin(this.breathPhase + r3.phaseOffset)) * 0.5f) + 0.5f);
        drawLayer(r12, this.big, r6, r72, r8);
        drawLayer(r12, this.small, r6, r72, r13);
        if (this.colorProgress >= 1.0f) goto L32;
        invalidateSelf();
        return;
    L32:
        return;
    L22:
        if (r7 <= 0) goto L27;
        float r14 = r1 + (r7 / 250.0f);
        this.colorProgress = r14;
        if (r14 <= 1.0f) goto L26;
        this.colorProgress = 1.0f;
    L26:
        applyColor(ColorUtils.blendARGB(this.fromColor, this.toColor, this.colorProgress));
        goto L27
    L17:
        if (r7 <= 0) goto L19;
        updateAmplitude(r7);
        this.breathPhase += (r7 / this.breathPeriodMs) * 6.2831855f;
        this.big.update(this.amplitude);
        this.small.update(this.amplitude);
        goto L19
    L10:
        if (this.colorProgress < 1.0f) goto L13;
        r7 = 0;
        goto L14
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public int getRequiredInset() {
        return ((int) maxOutwardExcursion()) + AndroidUtilities.dp(1.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        rebuildGeometry();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        if (this.mAlpha == r2) goto L6;
        this.mAlpha = r2;
        this.big.setLayerAlpha(r2);
        this.small.setLayerAlpha(r2);
        invalidateSelf();
        return;
    }

    public void setAmplitude(float r2) {
        setAmplitude(r2, false);
    }

    public void setAmplitude(float r4, boolean r5) {
        this.animateToAmplitude = r4;
        if (LiteMode.isEnabled(512) == true) goto L5;
        return;
    L5:
        float r42 = this.animateToAmplitude - this.amplitude;
        float r0 = 500.0f;
        if (r5 == false) goto L13;
        if (r42 <= 0.0f) goto L10;
        r0 = 300.0f;
    L10:
        this.animateAmplitudeDiff = r42 / ((r0 * 0.35000002f) + 100.0f);
        return;
    L13:
        if (r42 <= 0.0f) goto L15;
        r0 = 400.0f;
    L15:
        this.animateAmplitudeDiff = r42 / ((r0 * 0.55f) + 100.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.big.paint.setColorFilter(r2);
        this.small.paint.setColorFilter(r2);
        invalidateSelf();
    }

    public void setState(int r2) {
        setState(r2, false);
    }

    public void setState(int r3, boolean r4) {
        if (r3 == this.colorState) goto L5;
    L7:
        this.colorState = r3;
        int r32 = resolveStateColor(r3);
        if (r4 == true) goto L10;
    L14:
        this.colorProgress = 1.0f;
        applyColor(r32);
    L15:
        invalidateSelf();
        return;
    L10:
        if (this.currentColor == 0) goto L14;
        if (LiteMode.isEnabled(512) == false) goto L14;
        this.fromColor = this.currentColor;
        this.toColor = r32;
        this.colorProgress = 0.0f;
        goto L15
    L5:
        if (this.colorProgress < 1.0f) goto L7;
    }

    public void start() {
        if (this.running == false) goto L5;
        return;
    L5:
        this.running = true;
        this.lastFrameTime = SystemClock.elapsedRealtime();
        Choreographer60FpsContent.getInstance().addFrameCallback(this.mInvalidateSelf, 60);
    }

    public void stop() {
        if (this.running == true) goto L5;
        return;
    L5:
        this.running = false;
        Choreographer60FpsContent.getInstance().removeFrameCallback(this.mInvalidateSelf);
    }

    public void updateState(boolean r7) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
        return;
    L5:
        int r1 = r0.getCallState();
        if (r0.isSwitchingStream() == false) goto L8;
    L16:
        ChatObject.Call r12 = r0.groupCall;
        if (r12 == null) goto L32;
        TLRPC.GroupCallParticipant r13 = r12.participants.get(r0.getSelfId());
        if (r13 == null) goto L27;
        if (r13.can_self_unmute == true) goto L27;
        if (r13.muted == false) goto L27;
        if (ChatObject.canManageCalls(r0.getChat()) == true) goto L27;
    L28:
        r0.setMicMute(true, false, false);
        setState(3, r7);
        return;
    L27:
        if (r0.groupCall.call.rtmp_stream == true) goto L28;
        setState(r0.isMicMute() ? 1 : 0, r7);
        return;
    L32:
        setState(r0.isMicMute() ? 1 : 0, r7);
        return;
    L8:
        if (r1 == 1) goto L14;
        if (r1 == 2) goto L14;
        if (r1 == 6) goto L14;
        if (r1 != 5) goto L16;
    L14:
        setState(2, r7);
    }
}
