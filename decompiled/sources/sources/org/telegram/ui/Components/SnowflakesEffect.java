package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.cast.CastStatusCodes;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BatchParticlesDrawHelper;

/* loaded from: classes7.dex */
public class SnowflakesEffect {
    private final BatchParticlesDrawHelper.BatchParticlesBuffer batchParticlesBuffer;
    private final Paint batchParticlesPaint;
    private final Paint bitmapPaint;
    private int color;
    private int colorKey;
    private int forcedColor;
    private final ArrayList<Particle> freeParticles;
    private long lastAnimationTime;
    private final int maxCount;
    public boolean occupyStatusBar;
    Bitmap particleBitmap;
    private final Paint particlePaint;
    private final Paint particleThinPaint;
    private final ArrayList<Particle> particles;
    private final int viewType;

    public class Particle {
        float alpha;
        float currentTime;
        float lifeTime;
        int paintType;
        float scale;
        final /* synthetic */ SnowflakesEffect this$0;
        int type;
        float velocity;
        float vx;
        float vy;
        float x;
        float y;

        private Particle(SnowflakesEffect r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Particle(SnowflakesEffect r1, SnowflakesEffectIA r2) {
            this(r1);
        }

        public void draw(Canvas r4) {
            if (this.type == 0) goto L9;
            SnowflakesEffect r0 = this.this$0;
            if (r0.particleBitmap != null) goto L7;
            r0.particleBitmap = SnowflakesEffect.m13041$$Nest$smcreateParticlesBitmap(false);
        L7:
            SnowflakesEffect.m13038$$Nest$fgetbitmapPaint(this.this$0).setAlpha(SnowflakesEffect.m13042$$Nest$smgetAlphaComponent(this.alpha));
            r4.save();
            float r02 = this.scale;
            r4.scale(r02, r02, this.x, this.y);
            SnowflakesEffect r03 = this.this$0;
            r4.drawBitmap(r03.particleBitmap, this.x, this.y, SnowflakesEffect.m13038$$Nest$fgetbitmapPaint(r03));
            r4.restore();
            return;
        L9:
            int r04 = SnowflakesEffect.m13042$$Nest$smgetAlphaComponent(this.alpha);
            int r1 = this.paintType;
            SnowflakesEffect r2 = this.this$0;
            if (r1 != 0) goto L12;
            SnowflakesEffect.m13039$$Nest$fgetparticlePaint(r2).setAlpha(r04);
        L13:
            float r05 = this.x;
            float r12 = this.y;
            int r22 = this.paintType;
            SnowflakesEffect r3 = this.this$0;
            if (r22 != 0) goto L16;
            Paint r32 = SnowflakesEffect.m13039$$Nest$fgetparticlePaint(r3);
        L17:
            r4.drawPoint(r05, r12, r32);
            return;
        L16:
            r32 = SnowflakesEffect.m13040$$Nest$fgetparticleThinPaint(r3);
            goto L17
        L12:
            SnowflakesEffect.m13040$$Nest$fgetparticleThinPaint(r2).setAlpha(r04);
            goto L13
        }
    }

    /* renamed from: -$$Nest$fgetbitmapPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13038$$Nest$fgetbitmapPaint(SnowflakesEffect r0) {
        return r0.bitmapPaint;
    }

    /* renamed from: -$$Nest$fgetparticlePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13039$$Nest$fgetparticlePaint(SnowflakesEffect r0) {
        return r0.particlePaint;
    }

    /* renamed from: -$$Nest$fgetparticleThinPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13040$$Nest$fgetparticleThinPaint(SnowflakesEffect r0) {
        return r0.particleThinPaint;
    }

    /* renamed from: -$$Nest$smcreateParticlesBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m13041$$Nest$smcreateParticlesBitmap(boolean r0) {
        return createParticlesBitmap(r0);
    }

    /* renamed from: -$$Nest$smgetAlphaComponent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13042$$Nest$smgetAlphaComponent(float r0) {
        return getAlphaComponent(r0);
    }

    public SnowflakesEffect(int r6) {
        this.bitmapPaint = new Paint();
        this.colorKey = Theme.key_actionBarDefaultTitle;
        this.occupyStatusBar = true;
        this.particles = new ArrayList();
        this.freeParticles = new ArrayList();
        this.viewType = r6;
        if (r6 != 0) goto L5;
        int r62 = 100;
    L6:
        this.maxCount = r62;
        Paint r1 = new Paint(1);
        this.particlePaint = r1;
        r1.setStrokeWidth(AndroidUtilities.dp(2.5f));
        Paint.Cap r2 = Paint.Cap.ROUND;
        r1.setStrokeCap(r2);
        Paint.Style r3 = Paint.Style.STROKE;
        r1.setStyle(r3);
        Paint r12 = new Paint(1);
        this.particleThinPaint = r12;
        r12.setStrokeWidth(AndroidUtilities.dp(1.0f));
        r12.setStrokeCap(r2);
        r12.setStyle(r3);
        if (BatchParticlesDrawHelper.isAvailable() == false) goto L9;
        this.batchParticlesBuffer = new BatchParticlesDrawHelper.BatchParticlesBuffer(r62);
        this.batchParticlesPaint = BatchParticlesDrawHelper.createBatchParticlesPaint(createParticlesBitmap(true));
    L10:
        updateColors();
        int r63 = 0;
    L12:
        if (r63 >= 20) goto L14;
        this.freeParticles.add(new Particle(this, null));
        r63 = r63 + 1;
        goto L12
    L14:
        return;
    L9:
        this.batchParticlesBuffer = null;
        this.batchParticlesPaint = null;
        goto L10
    L5:
        r62 = 300;
        goto L6
    }

    private static Bitmap createParticlesBitmap(boolean r29) {
        Paint r5 = new Paint(1);
        r5.setStrokeWidth(AndroidUtilities.dp(0.5f));
        r5.setStrokeCap(Paint.Cap.ROUND);
        r5.setStyle(Paint.Style.STROKE);
        r5.setColor(-1);
        if (r29 == false) goto L5;
        int r1 = AndroidUtilities.dp(20.0f);
    L6:
        Bitmap r8 = Bitmap.createBitmap(r1, AndroidUtilities.dp(10.0f), Bitmap.Config.ARGB_8888);
        Canvas r0 = new Canvas(r8);
        float r9 = AndroidUtilities.dpf2(2.0f) * 2.0f;
        float r10 = (-AndroidUtilities.dpf2(0.57f)) * 2.0f;
        float r11 = AndroidUtilities.dpf2(1.55f) * 2.0f;
        float r12 = 5.0f;
        float r13 = AndroidUtilities.dp(5.0f);
        float r2 = AndroidUtilities.dp(5.0f);
        float r132 = -1.5707964f;
        int r14 = 0;
    L8:
        if (r14 >= 6) goto L10;
        double r3 = r132;
        float r15 = r12;
        float r16 = r132;
        float r122 = ((float) Math.cos(r3)) * r9;
        Bitmap r17 = r8;
        float r7 = ((float) Math.sin(r3)) * r9;
        float r18 = r122 * 0.66f;
        float r82 = 0.66f * r7;
        r0.drawLine(r13, r2, r122 + r13, r7 + r2, r5);
        float r72 = r13;
        float r123 = r2;
        double r19 = (float) (r3 - 1.5707963267948966d);
        double r133 = r10;
        double r6 = r11;
        float r32 = (float) ((Math.cos(r19) * r133) - (Math.sin(r19) * r6));
        Canvas r4 = r0;
        float r02 = (float) ((Math.sin(r19) * r133) + (Math.cos(r19) * r6));
        float r110 = r72 + r18;
        float r22 = r123 + r82;
        r4.drawLine(r110, r22, r72 + r32, r02 + r123, r5);
        float r33 = (float) (((-Math.cos(r19)) * r133) - (Math.sin(r19) * r6));
        r0 = r4;
        r0.drawLine(r110, r22, r72 + r33, ((float) (((-Math.sin(r19)) * r133) + (Math.cos(r19) * r6))) + r123, r5);
        r132 = r16 + 1.0471976f;
        r14 = r14 + 1;
        r2 = r123;
        r12 = r15;
        r8 = r17;
        r13 = r72;
        goto L8
    L10:
        Bitmap r172 = r8;
        float r152 = r12;
        if (r29 == false) goto L13;
        Paint r111 = new Paint(1);
        r111.setStrokeWidth(AndroidUtilities.dp(1.5f));
        r111.setStrokeCap(Paint.Cap.ROUND);
        r111.setStyle(Paint.Style.STROKE);
        r111.setColor(-1);
        r0.drawPoint(AndroidUtilities.dp(15.0f), AndroidUtilities.dp(r152), r111);
    L13:
        return r172;
    L5:
        r1 = AndroidUtilities.dp(10.0f);
        goto L6
    }

    private static int getAlphaComponent(float r1) {
        return (int) (Utilities.clamp01(r1) * 255.0f);
    }

    private void updateParticles(long r9) {
        int r0 = this.particles.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L24;
        Particle r2 = this.particles.get(r1);
        float r3 = r2.currentTime;
        float r4 = r2.lifeTime;
        if (r3 < r4) goto L11;
        if (this.freeParticles.size() >= 40) goto L9;
        this.freeParticles.add(r2);
    L9:
        this.particles.remove(r1);
        r1 = r1 - 1;
        r0 = r0 - 1;
    L23:
        r1 = r1 + 1;
        goto L3
    L11:
        if (this.viewType != 0) goto L17;
        if (r3 >= 200.0f) goto L15;
        r2.alpha = AndroidUtilities.accelerateInterpolator.getInterpolation(r3 / 200.0f);
    L22:
        float r32 = r2.x;
        float r42 = r2.vx;
        float r5 = r2.velocity;
        float r6 = r9;
        r2.x = r32 + (((r42 * r5) * r6) / 500.0f);
        r2.y += ((r2.vy * r5) * r6) / 500.0f;
        r2.currentTime += r6;
        goto L23
    L15:
        r2.alpha = 1.0f - AndroidUtilities.decelerateInterpolator.getInterpolation((r3 - 200.0f) / (r4 - 200.0f));
        goto L22
    L17:
        if (r3 >= 200.0f) goto L20;
        r2.alpha = AndroidUtilities.accelerateInterpolator.getInterpolation(r3 / 200.0f);
        goto L22
    L20:
        if ((r4 - r3) >= 2000.0f) goto L22;
        r2.alpha = AndroidUtilities.decelerateInterpolator.getInterpolation((r4 - r3) / 2000.0f);
        goto L22
    }

    public void onDraw(View r17, Canvas r18) {
        if (r17 == null) goto L67;
        if (r18 != null) goto L6;
        return;
    L6:
        if (LiteMode.isEnabled(32) == false) goto L69;
        float r2 = Math.max(1, r17.getMeasuredWidth() * r17.getMeasuredHeight());
        Point r4 = AndroidUtilities.displaySize;
        float r42 = Math.max(1, r4.x * r4.y);
        float r6 = 1.0f;
        if (this.viewType != 1) goto L11;
        r6 = Math.max(0.12f, Math.min(1.0f, r2 / r42));
    L11:
        int r22 = Math.max(1, Math.round(this.maxCount * r6));
        if (this.viewType != 0) goto L14;
        int r43 = 1;
    L15:
        int r3 = Math.max(1, Math.round(r43 * r6));
        BatchParticlesDrawHelper.BatchParticlesBuffer r44 = this.batchParticlesBuffer;
        ArrayList<Particle> r5 = this.particles;
        if (r44 == null) goto L28;
        int r45 = Math.min(r22, r5.size());
        int r52 = AndroidUtilities.dp(10.0f);
        int r9 = 0;
    L18:
        if (r9 >= r45) goto L27;
        Particle r8 = this.particles.get(r9);
        float r10 = r8.x;
        float r11 = r8.y;
        int r12 = r8.type;
        float r14 = r52 / 2.0f;
        if (r12 == 0) goto L23;
        r14 = r14 * r8.scale;
    L23:
        if (r12 != 0) goto L25;
        float r15 = r52;
    L26:
        this.batchParticlesBuffer.setParticleColor(r9, ColorUtils.setAlphaComponent(this.color, getAlphaComponent(r8.alpha)));
        this.batchParticlesBuffer.setParticleVertexCords(r9, r10 - r14, r11 - r14, r10 + r14, r11 + r14);
        float r13 = r52;
        this.batchParticlesBuffer.setParticleTextureCords(r9, r15, 0.0f, r15 + r13, r13);
        r9 = r9 + 1;
        goto L18
    L25:
        r15 = 0.0f;
        goto L26
    L27:
        BatchParticlesDrawHelper.draw(r18, this.batchParticlesBuffer, r45, this.batchParticlesPaint);
    L32:
        if (this.particles.size() >= r22) goto L57;
        int r1 = 0;
    L34:
        if (r1 >= r3) goto L57;
        if (this.particles.size() >= r22) goto L56;
        if (Utilities.random.nextFloat() <= 0.7f) goto L56;
        if (this.occupyStatusBar == false) goto L42;
        int r46 = AndroidUtilities.statusBarHeight;
    L43:
        float r53 = Utilities.random.nextFloat() * r17.getMeasuredWidth();
        if (this.viewType != 0) goto L46;
        float r82 = r46 + (Utilities.random.nextFloat() * ((r17.getMeasuredHeight() - AndroidUtilities.dp(20.0f)) - r46));
    L47:
        double r122 = (Utilities.random.nextInt(40) + 70) * 0.017453292519943295d;
        float r47 = (float) Math.cos(r122);
        float r102 = (float) Math.sin(r122);
        if (this.freeParticles.isEmpty() == true) goto L50;
        Particle r112 = this.freeParticles.get(0);
        this.freeParticles.remove(0);
    L51:
        r112.x = r53;
        r112.y = r82;
        r112.vx = r47;
        r112.vy = r102;
        r112.alpha = 0.0f;
        r112.currentTime = 0.0f;
        r112.scale = Utilities.random.nextFloat() * 1.2f;
        r112.type = 0;
        r112.paintType = Utilities.random.nextInt(2);
        if (this.viewType != 0) goto L54;
        r112.lifeTime = Utilities.random.nextInt(100) + CastStatusCodes.AUTHENTICATION_FAILED;
    L55:
        r112.velocity = (Utilities.random.nextFloat() * 4.0f) + 20.0f;
        this.particles.add(r112);
        goto L56
    L54:
        r112.lifeTime = Utilities.random.nextInt(CastStatusCodes.AUTHENTICATION_FAILED) + 3000;
        goto L55
    L50:
        r112 = new Particle(this, null);
        goto L51
    L46:
        r82 = r17.getMeasuredHeight() * Utilities.random.nextFloat();
        goto L47
    L42:
        r46 = 0;
    L56:
        r1 = r1 + 1;
    L57:
        long r16 = SystemClock.elapsedRealtime();
        updateParticles(Utilities.clamp(r16 - this.lastAnimationTime, 17, 0));
        this.lastAnimationTime = r16;
        r17.invalidate();
        return;
    L28:
        int r48 = r5.size();
        int r54 = 0;
    L29:
        if (r54 >= r48) goto L32;
        this.particles.get(r54).draw(r18);
        r54 = r54 + 1;
        goto L29
    L14:
        r43 = 10;
        goto L15
    L69:
        return;
    }

    public void setForcedColor(int r1) {
        this.forcedColor = r1;
        updateColors();
    }

    public void updateColors() {
        int r0 = this.forcedColor;
        if (r0 != 0) goto L7;
        r0 = Theme.getColor(this.colorKey) & (-1644826);
    L7:
        if (this.color == r0) goto L10;
        this.color = r0;
        this.particlePaint.setColor(r0);
        this.particleThinPaint.setColor(r0);
        return;
    }
}
