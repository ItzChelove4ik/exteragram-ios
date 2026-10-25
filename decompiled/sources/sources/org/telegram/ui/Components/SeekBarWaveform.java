package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.SeekBar;

/* loaded from: classes7.dex */
public class SeekBarWaveform {
    private static Paint paintInner;
    private static Paint paintOuter;
    private float alpha;
    private Path alphaPath;
    private ArrayList<Float> animatedValues;
    private AnimatedFloat appearFloat;
    private float clearProgress;
    private SeekBar.SeekBarDelegate delegate;
    public float explodeProgress;
    private boolean exploding;
    public float explosionRate;
    private float[] fromHeights;
    private int fromWidth;
    private int height;
    private float[] heights;
    private int innerColor;
    private boolean isUnread;
    private boolean loading;
    private AnimatedFloat loadingFloat;
    private Paint loadingPaint;
    private int loadingPaintColor1;
    private int loadingPaintColor2;
    private float loadingPaintWidth;
    private long loadingStart;
    private MessageObject messageObject;
    private int outerColor;
    private View parentView;
    private Particles particles;
    private Path path;
    private boolean pressed;
    private float progress;
    private boolean selected;
    private int selectedColor;
    private boolean startDraging;
    private float startX;
    private int thumbDX;
    private int thumbX;
    private float[] toHeights;
    private int toWidth;
    private float waveScaling;
    private byte[] waveformBytes;
    private int width;

    public static class Particles {
        private final int count;
        private final ArrayList<Particle> deadParticles;
        private RectF emitArea;
        private Runnable invalidate;
        private long lastTime;
        private final Paint paint;
        private final ArrayList<Particle> particles;

        public class Particle {
            float d;
            float t;
            final /* synthetic */ Particles this$0;
            float v;
            float vx;
            float vy;
            float x;
            float y;

            private Particle(Particles r1) {
                this.this$0 = r1;
            }

            public /* synthetic */ Particle(Particles r1, SeekBarWaveformIA r2) {
                this(r1);
            }
        }

        public Particles(int r3, Runnable r4) {
            this.particles = new ArrayList(50);
            this.deadParticles = new ArrayList(50);
            Paint r0 = new Paint(1);
            this.paint = r0;
            this.count = r3;
            this.invalidate = r4;
            r0.setStrokeWidth(AndroidUtilities.dp(1.33f));
        }

        public void draw(Canvas r11, float r12) {
            long r0 = System.currentTimeMillis();
            long r2 = Math.min(20, r0 - this.lastTime);
            this.lastTime = r0;
            int r02 = 0;
            int r1 = 0;
        L4:
            if (r1 >= this.particles.size()) goto L11;
            Particle r4 = this.particles.get(r1);
            float r6 = r2;
            float r5 = r4.t - (r6 / r4.d);
            r4.t = r5;
            if (r5 >= 0.0f) goto L8;
            this.deadParticles.add(r4);
            this.particles.remove(r1);
            r1 = r1 - 1;
        L9:
            r1 = r1 + 1;
            goto L4
        L8:
            float r52 = r4.x;
            float r7 = r4.vx;
            float r8 = r4.v;
            r4.x = r52 + (((r7 * r8) * r6) / 500.0f);
            float r53 = r4.y;
            float r72 = r4.vy;
            r4.y = r53 + (((r8 * r72) * r6) / 500.0f);
            r4.vy = r72 - ((AndroidUtilities.dp(0.33f) * r2) / 500.0f);
            goto L9
        L11:
            if (this.emitArea == null) goto L20;
            int r13 = Math.min(4, this.count - this.particles.size());
            int r22 = 0;
        L13:
            if (r22 >= r13) goto L20;
            if (this.deadParticles.isEmpty() == false) goto L17;
            Particle r3 = new Particle(this, null);
        L18:
            RectF r42 = this.emitArea;
            r3.x = r42.left + (r42.width() * Utilities.random.nextFloat());
            RectF r43 = this.emitArea;
            r3.y = r43.top + (r43.height() * Utilities.random.nextFloat());
            double r44 = (Utilities.random.nextInt(200) - 125) * 0.017453292519943295d;
            r3.vx = ((float) (Math.cos(r44) - Math.sin(r44))) * 0.8f;
            r3.vy = ((float) (Math.sin(r44) + Math.cos(r44))) - 0.2f;
            r3.t = 1.0f;
            r3.v = AndroidUtilities.dp((Utilities.random.nextFloat() * 7.0f) + 10.0f);
            r3.d = AndroidUtilities.lerp(MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN, 550, Utilities.random.nextFloat());
            this.particles.add(r3);
            r22 = r22 + 1;
            goto L13
        L17:
            r3 = this.deadParticles.remove(0);
        L20:
            if (r02 >= this.particles.size()) goto L22;
            Particle r14 = this.particles.get(r02);
            this.paint.setAlpha((int) ((255.0f * r12) * r14.t));
            r11.drawPoint(r14.x, r14.y, this.paint);
            r02 = r02 + 1;
            goto L20
        L22:
            Runnable r10 = this.invalidate;
            if (r10 == null) goto L34;
            r10.run();
            return;
        }

        public Particles setColor(int r2) {
            this.paint.setColor(r2);
            return this;
        }

        public Particles setEmitArea(RectF r1) {
            this.emitArea = r1;
            return this;
        }
    }

    public SeekBarWaveform(Context r8) {
        this.thumbX = 0;
        this.thumbDX = 0;
        this.startDraging = false;
        this.pressed = false;
        this.alpha = 1.0f;
        this.clearProgress = 1.0f;
        this.appearFloat = new AnimatedFloat(125, 600, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.waveScaling = 1.0f;
        this.loadingFloat = new AnimatedFloat(150, CubicBezierInterpolator.DEFAULT);
        this.exploding = false;
        if (paintInner != null) goto L6;
        paintInner = new Paint(1);
        paintOuter = new Paint(1);
        Paint r7 = paintInner;
        Paint.Style r82 = Paint.Style.FILL;
        r7.setStyle(r82);
        paintOuter.setStyle(r82);
        return;
    }

    private void addBar(Path r9, float r10, float r11) {
        float r1 = AndroidUtilities.dpf2(2.0f);
        int r2 = (this.height - AndroidUtilities.dp(14.0f)) / 2;
        float r112 = r11 * this.waveScaling;
        RectF r8 = AndroidUtilities.rectTmp;
        float r0 = r1 / 2.0f;
        r8.set((AndroidUtilities.dpf2(1.0f) + r10) - r0, (AndroidUtilities.dp(7.0f) + r2) + ((-r112) - r0), (r10 + AndroidUtilities.dpf2(1.0f)) + r0, (r2 + AndroidUtilities.dp(7.0f)) + (r112 + r0));
        r9.addRoundRect(r8, r1, r1, Path.Direction.CW);
    }

    private float[] calculateHeights(int r19) {
        byte[] r2 = this.waveformBytes;
        if (r2 == null) goto L26;
        if (r19 <= 0) goto L34;
        float[] r3 = new float[r19];
        int r4 = 5;
        int r22 = (r2.length * 8) / 5;
        float r5 = r22 / r19;
        float r10 = 0.0f;
        int r8 = 0;
        int r9 = 0;
        int r11 = 0;
    L7:
        if (r8 >= r22) goto L25;
        if (r8 != r9) goto L24;
        int r12 = r9;
        int r13 = 0;
    L11:
        if (r9 != r12) goto L13;
        r10 = r10 + r5;
        r12 = (int) r10;
        r13 = r13 + 1;
        goto L11
    L13:
        int r92 = r8 * 5;
        int r14 = r92 / 8;
        int r93 = r92 - (r14 * 8);
        int r16 = 5 - (8 - r93);
        byte r7 = (byte) ((this.waveformBytes[r14] >> r93) & ((2 << (Math.min(r4, r15) - 1)) - 1));
        if (r16 <= 0) goto L18;
        int r142 = r14 + 1;
        byte[] r94 = this.waveformBytes;
        if (r142 >= r94.length) goto L18;
        r7 = (byte) (((byte) (r7 << r16)) | (r94[r142] & ((2 << (4 - r15)) - 1)));
    L18:
        int r42 = 0;
    L19:
        if (r42 >= r13) goto L23;
        if (r11 >= r19) goto L25;
        r3[r11] = Math.max(0.0f, (r7 * 7) / 31.0f);
        r42 = r42 + 1;
        r11 = r11 + 1;
        goto L19
    L23:
        r9 = r12;
    L24:
        r8 = r8 + 1;
        r4 = 5;
    L25:
        return r3;
    L34:
        return null;
    L26:
        return null;
    }

    private void drawFill(Canvas r22, float r23) {
        float r1 = AndroidUtilities.dpf2(2.0f);
        MessageObject r2 = this.messageObject;
        if (r2 != null) goto L5;
    L11:
        boolean r24 = false;
    L12:
        this.isUnread = r24;
        Paint r5 = paintInner;
        if (r24 == false) goto L16;
        int r25 = this.outerColor;
    L19:
        r5.setColor(r25);
        paintOuter.setColor(this.outerColor);
        this.loadingFloat.setParent(this.parentView);
        boolean r26 = MediaController.getInstance().isPlayingMessage(this.messageObject);
        AnimatedFloat r52 = this.loadingFloat;
        if (this.loading == false) goto L23;
        if (r26 == true) goto L23;
        float r27 = 1.0f;
    L24:
        float r28 = r52.set(r27);
        Paint r53 = paintInner;
        r53.setColor(ColorUtils.blendARGB(r53.getColor(), this.innerColor, r28));
        float r8 = 1.0f - r28;
        paintOuter.setAlpha((int) ((r5.getAlpha() * r8) * r23));
        paintInner.setAlpha((int) (r5.getAlpha() * r23));
        r22.drawRect(0.0f, 0.0f, this.width + r1, this.height, paintInner);
        if (r28 >= 1.0f) goto L28;
        r22.drawRect(0.0f, 0.0f, (this.progress * (this.width + r1)) * r8, this.height, paintOuter);
    L28:
        if (r28 > 0.0f) goto L30;
        return;
    L30:
        if (this.loadingPaint == null) goto L38;
        if (Math.abs(this.loadingPaintWidth - this.width) > AndroidUtilities.dp(8.0f)) goto L38;
        if (this.loadingPaintColor1 != this.innerColor) goto L38;
        if (this.loadingPaintColor2 != this.outerColor) goto L38;
    L41:
        this.loadingPaint.setAlpha((int) ((r28 * 255.0f) * r23));
        r22.save();
        float r12 = ((((float) Math.pow((SystemClock.elapsedRealtime() - this.loadingStart) / 270.0f, 0.75d)) % 1.6f) - 0.6f) * this.loadingPaintWidth;
        r22.translate(r12, 0.0f);
        r22.drawRect(-r12, 0.0f, (this.width + 5) - r12, this.height, this.loadingPaint);
        r22.restore();
        View r0 = this.parentView;
        if (r0 == null) goto L46;
        r0.invalidate();
        return;
    L46:
        return;
    L38:
        if (this.loadingPaint != null) goto L40;
        this.loadingPaint = new Paint(1);
    L40:
        this.loadingPaintColor1 = this.innerColor;
        this.loadingPaintColor2 = this.outerColor;
        Paint r13 = this.loadingPaint;
        float r82 = this.width;
        this.loadingPaintWidth = r82;
        int r3 = this.loadingPaintColor1;
        r13.setShader(new LinearGradient(0.0f, 0.0f, r82, 0.0f, new int[]{r3, this.loadingPaintColor2, r3}, new float[]{0.0f, 0.2f, 0.4f}, Shader.TileMode.CLAMP));
    L23:
        r27 = 0.0f;
        goto L24
    L16:
        if (this.selected == false) goto L18;
        r25 = this.selectedColor;
        goto L19
    L18:
        r25 = this.innerColor;
        goto L19
    L5:
        if (r2.isContentUnread() == false) goto L11;
        if (this.messageObject.isOut() == true) goto L11;
        if (this.progress > 0.0f) goto L11;
        r24 = true;
        goto L12
    }

    public void draw(Canvas r21, View r22) {
        if (this.waveformBytes == null) goto L122;
        int r2 = this.width;
        if (r2 != 0) goto L7;
        return;
    L7:
        if (this.alpha <= 0.0f) goto L125;
        float r23 = r2 / AndroidUtilities.dpf2(3.0f);
        if (r23 <= 0.1f) goto L126;
        float r5 = this.clearProgress;
        if (r5 == 1.0f) goto L18;
        float r52 = r5 + 0.10666667f;
        this.clearProgress = r52;
        if (r52 <= 1.0f) goto L17;
        this.clearProgress = 1.0f;
        goto L18
    L17:
        r22.invalidate();
    L18:
        float r53 = this.appearFloat.set(1.0f);
        Path r7 = this.path;
        if (r7 != null) goto L21;
        this.path = new Path();
    L22:
        Path r72 = this.alphaPath;
        if (r72 != null) goto L25;
        this.alphaPath = new Path();
    L26:
        SeekBar.SeekBarDelegate r73 = this.delegate;
        if (r73 != null) goto L29;
    L31:
        boolean r74 = false;
    L32:
        float[] r10 = this.fromHeights;
        if (r10 == null) goto L71;
        float[] r11 = this.toHeights;
        if (r11 == null) goto L71;
        int r12 = this.width;
        int r13 = this.fromWidth;
        float r122 = (r12 - r13) / (this.toWidth - r13);
        int r102 = Math.max(r10.length, r11.length);
        int r112 = Math.min(this.fromHeights.length, this.toHeights.length);
        float[] r132 = this.fromHeights;
        int r14 = r132.length;
        float[] r15 = this.toHeights;
        float r16 = 3.0f;
        if (r14 >= r15.length) goto L39;
        float[] r3 = r132;
    L41:
        if (r132.length >= r15.length) goto L43;
        float[] r9 = r15;
    L45:
        if (r132.length < r15.length) goto L48;
        r122 = 1.0f - r122;
    L48:
        int r133 = -1;
        float r152 = 0.0f;
        int r142 = 0;
    L49:
        if (r142 >= r102) goto L70;
        float r4 = r142;
        float r8 = r53;
        float r19 = r23;
        int r54 = MathUtils.clamp((int) Math.floor((r4 / r102) * r112), 0, r112 - 1);
        if (r133 >= r54) goto L61;
        float r42 = AndroidUtilities.lerp(r54, r4, r122) * AndroidUtilities.dpf2(3.0f);
        if (r74 == false) goto L55;
        int r6 = (r3.length - 1) - r54;
    L56:
        float r62 = r3[r6];
        if (r74 == false) goto L59;
        int r134 = (r9.length - 1) - r142;
    L60:
        addBar(this.path, r42, AndroidUtilities.dpf2(AndroidUtilities.lerp(r62, r9[r134], r122)));
        r133 = r54;
    L69:
        r142 = r142 + 1;
        r53 = r8;
        r23 = r19;
        goto L49
    L59:
        r134 = r142;
        goto L60
    L55:
        r6 = r54;
        goto L56
    L61:
        float r43 = AndroidUtilities.lerp(r54, r4, r122) * AndroidUtilities.dpf2(3.0f);
        if (r74 == false) goto L64;
        r54 = (r3.length - 1) - r54;
    L64:
        float r55 = r3[r54];
        if (r74 == false) goto L67;
        int r63 = (r9.length - 1) - r142;
    L68:
        addBar(this.alphaPath, r43, AndroidUtilities.dpf2(AndroidUtilities.lerp(r55, r9[r63], r122)));
        r152 = r122;
        goto L69
    L67:
        r63 = r142;
        goto L68
    L70:
        float r192 = r23;
        float r82 = r53;
    L85:
        if (this.exploding == false) goto L87;
    L90:
        r21.save();
        float r44 = 0.0f;
        r21.clipRect(0.0f, 0.0f, (AndroidUtilities.dpf2(r16) * r192) * (1.0f - (this.explodeProgress * this.explosionRate)), this.height);
    L92:
        if (r152 <= r44) goto L94;
        r21.save();
        r21.clipPath(this.alphaPath);
        drawFill(r21, r152 * this.alpha);
        r21.restore();
    L94:
        r21.save();
        r21.clipPath(this.path);
        drawFill(r21, this.alpha);
        r21.restore();
        if (this.exploding == false) goto L97;
    L98:
        r21.restore();
        if (this.particles != null) goto L101;
        this.particles = new Particles(250, new SeekBarWaveform$$ExternalSyntheticLambda0(this));
    L101:
        float r24 = this.explodeProgress;
        if (r24 >= 0.99f) goto L112;
        float[] r32 = this.heights;
        if (r32 == null) goto L112;
        int r25 = (int) (r192 * (1.0f - r24));
        if (r74 == false) goto L108;
        r25 = (int) ((r192 - 1.0f) - r25);
    L108:
        if (r25 < 0) goto L112;
        if (r25 >= r32.length) goto L112;
        float r26 = AndroidUtilities.dpf2(this.heights[r25]) * MathUtils.clamp((r82 * r192) - r25, 0.0f, 1.0f);
        RectF r33 = AndroidUtilities.rectTmp;
        float r45 = ((1.0f - this.explodeProgress) * r192) * AndroidUtilities.dpf2(r16);
        float r64 = AndroidUtilities.dpf2(2.0f);
        int r75 = (this.height - AndroidUtilities.dp(14.0f)) / 2;
        float r27 = r26 * this.waveScaling;
        float r65 = r64 / 2.0f;
        r33.set((AndroidUtilities.dpf2(1.0f) + r45) - r65, (AndroidUtilities.dp(7.0f) + r75) + ((-r27) - r65), (r45 + AndroidUtilities.dpf2(1.0f)) + r65, (r75 + AndroidUtilities.dp(7.0f)) + (r27 + r65));
    L113:
        this.particles.setColor(this.outerColor).setEmitArea(r33).draw(r21, this.explosionRate);
        return;
    L112:
        r33 = null;
        goto L113
    L97:
        if (this.explosionRate > 0.0f) goto L98;
        return;
    L87:
        if (this.explosionRate > 0.0f) goto L90;
        r44 = 0.0f;
        goto L92
    L43:
        r9 = r132;
        goto L45
    L39:
        r3 = r15;
    L71:
        r192 = r23;
        r16 = 3.0f;
        r82 = r53;
        int r28 = 0;
        if (this.heights == null) goto L83;
    L73:
        float r34 = r28;
        if (r34 >= r192) goto L83;
        if (r28 >= this.heights.length) goto L83;
        float r46 = AndroidUtilities.dpf2(3.0f) * r34;
        float r56 = MathUtils.clamp((r82 * r192) - r34, 0.0f, 1.0f);
        float[] r35 = this.heights;
        if (r74 == false) goto L81;
        int r92 = (r35.length - 1) - r28;
    L82:
        addBar(this.path, r46, (AndroidUtilities.dpf2(r35[r92]) * r56) - (AndroidUtilities.dpf2(1.0f) * (1.0f - r56)));
        r28 = r28 + 1;
        goto L73
    L81:
        r92 = r28;
    L83:
        r152 = 0.0f;
        goto L85
    L29:
        if (r73.reverseWaveform() == false) goto L31;
        r74 = true;
        goto L32
    L25:
        r72.reset();
        goto L26
    L21:
        r7.reset();
        goto L22
    L126:
        return;
    L125:
        return;
    }

    public void explodeAt(float r2) {
        this.exploding = true;
        this.explodeProgress = r2;
        invalidate();
    }

    public float getProgress() {
        return this.thumbX / this.width;
    }

    public void invalidate() {
        View r0 = this.parentView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public boolean isDragging() {
        return this.pressed;
    }

    public boolean isStartDraging() {
        return this.startDraging;
    }

    public boolean onTouch(int r4, float r5, float r6) {
        if (this.delegate.isSeekBarDragAllowed() == true) goto L7;
        this.progress = 1.0f;
        return false;
    L7:
        if (r4 == 0) goto L9;
        if (r4 == 1) goto L47;
        if (r4 == 3) goto L47;
        if (r4 == 2) goto L25;
    L54:
        return false;
    L25:
        if (this.pressed == false) goto L54;
        if (this.startDraging == false) goto L35;
        int r42 = (int) (r5 - this.thumbDX);
        this.thumbX = r42;
        if (r42 >= 0) goto L31;
        this.thumbX = 0;
    L34:
        this.progress = this.thumbX / this.width;
        goto L35
    L31:
        int r62 = this.width;
        if (r42 <= r62) goto L34;
        this.thumbX = r62;
    L35:
        float r43 = this.startX;
        if (r43 != (-1.0f)) goto L38;
    L45:
        return true;
    L38:
        if (Math.abs(r5 - r43) <= AndroidUtilities.getPixelsInCM(0.2f, true)) goto L45;
        View r44 = this.parentView;
        if (r44 != null) goto L42;
    L44:
        this.startDraging = true;
        this.startX = -1.0f;
        goto L45
    L42:
        if (r44.getParent() == null) goto L44;
        this.parentView.getParent().requestDisallowInterceptTouchEvent(true);
    L47:
        if (this.pressed == false) goto L54;
        if (r4 != 1) goto L52;
        SeekBar.SeekBarDelegate r45 = this.delegate;
        if (r45 == null) goto L52;
        r45.onSeekBarDrag(this.thumbX / this.width);
    L52:
        this.pressed = false;
        this.delegate.onSeekBarReleased();
        return true;
    L9:
        if (0.0f > r5) goto L54;
        if (r5 > this.width) goto L54;
        if (r6 < 0.0f) goto L54;
        if (r6 > this.height) goto L54;
        this.startX = r5;
        this.pressed = true;
        this.thumbDX = (int) (r5 - this.thumbX);
        this.startDraging = false;
        this.delegate.onSeekBarPressed();
        return true;
    }

    public void setAlpha(float r1) {
        this.alpha = r1;
    }

    public void setColors(int r1, int r2, int r3) {
        this.innerColor = r1;
        this.outerColor = r2;
        this.selectedColor = r3;
    }

    public void setDelegate(SeekBar.SeekBarDelegate r1) {
        this.delegate = r1;
    }

    public void setExplosionRate(float r1) {
        this.explosionRate = r1;
        invalidate();
    }

    public void setLoading(boolean r3) {
        if (this.loading == true) goto L8;
        if (r3 == false) goto L8;
        if (this.loadingFloat.get() > 0.0f) goto L8;
        this.loadingStart = SystemClock.elapsedRealtime();
    L8:
        this.loading = r3;
        View r2 = this.parentView;
        if (r2 == null) goto L12;
        r2.invalidate();
        return;
    }

    public void setMessageObject(MessageObject r3) {
        if (this.animatedValues == null) goto L10;
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L10;
        if (r3 == null) goto L10;
        if (r0.getId() == r3.getId()) goto L10;
        this.animatedValues.clear();
    L10:
        this.messageObject = r3;
    }

    public void setParentView(View r2) {
        this.parentView = r2;
        this.loadingFloat.setParent(r2);
        this.appearFloat.setParent(r2);
    }

    public void setProgress(float r2) {
        setProgress(r2, false);
    }

    public void setProgress(float r4, boolean r5) {
        if (this.delegate.isSeekBarDragAllowed() == true) goto L6;
        this.progress = 1.0f;
        return;
    L6:
        boolean r0 = this.isUnread;
        if (r0 == false) goto L9;
        float r2 = 1.0f;
    L10:
        this.progress = r2;
        if (r0 == false) goto L13;
        int r02 = this.width;
    L14:
        if (r5 == false) goto L19;
        if (r02 == 0) goto L19;
        if (r4 != 0.0f) goto L19;
        this.clearProgress = 0.0f;
    L21:
        int r42 = (int) Math.ceil(this.width * r4);
        this.thumbX = r42;
        if (r42 >= 0) goto L25;
        this.thumbX = 0;
        return;
    L25:
        int r52 = this.width;
        if (r42 <= r52) goto L29;
        this.thumbX = r52;
        return;
    L29:
        return;
    L19:
        if (r5 == true) goto L21;
        this.clearProgress = 1.0f;
        goto L21
    L13:
        r02 = this.thumbX;
        goto L14
    L9:
        r2 = r4;
        goto L10
    }

    public void setSelected(boolean r1) {
        this.selected = r1;
    }

    public void setSent() {
        this.appearFloat.set(0.0f, true);
        View r3 = this.parentView;
        if (r3 == null) goto L6;
        r3.invalidate();
        return;
    }

    public void setSize(int r1, int r2) {
        setSize(r1, r2, r1, r1);
    }

    public void setSize(int r3, int r4, int r5, int r6) {
        this.width = r3;
        this.height = r4;
        float[] r42 = this.heights;
        if (r42 != null) goto L5;
    L6:
        this.heights = calculateHeights((int) (this.width / AndroidUtilities.dpf2(3.0f)));
    L7:
        if (r5 != r6) goto L9;
    L14:
        if (r5 != r6) goto L17;
        this.toHeights = null;
        this.fromHeights = null;
        return;
    L17:
        return;
    L9:
        if (this.fromWidth == r5) goto L11;
    L12:
        this.fromWidth = r5;
        this.toWidth = r6;
        this.fromHeights = calculateHeights((int) (r5 / AndroidUtilities.dpf2(3.0f)));
        this.toHeights = calculateHeights((int) (this.toWidth / AndroidUtilities.dpf2(3.0f)));
        return;
    L11:
        if (this.toWidth == r6) goto L14;
    L5:
        if (r42.length == ((int) (r3 / AndroidUtilities.dpf2(3.0f)))) goto L7;
        goto L6
    }

    public void setWaveform(byte[] r2) {
        this.waveformBytes = r2;
        this.heights = calculateHeights((int) (this.width / AndroidUtilities.dpf2(3.0f)));
        if (this.delegate.isSeekBarDragAllowed() == true) goto L6;
        this.progress = 1.0f;
        return;
    }
}
