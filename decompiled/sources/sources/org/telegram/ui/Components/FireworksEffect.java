package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class FireworksEffect {
    final float angleDiff;
    private ArrayList<Particle> freeParticles;
    private long lastAnimationTime;
    private Paint particlePaint;
    private ArrayList<Particle> particles;

    public class Particle {
        float alpha;
        int color;
        float currentTime;
        float lifeTime;
        float scale;
        final /* synthetic */ FireworksEffect this$0;
        int type;
        float velocity;
        float vx;
        float vy;
        float x;
        float y;

        private Particle(FireworksEffect r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Particle(FireworksEffect r1, FireworksEffectIA r2) {
            this(r1);
        }

        public void draw(Canvas r4) {
            if (this.type == 0) goto L5;
            return;
        L5:
            FireworksEffect.m10662$$Nest$fgetparticlePaint(this.this$0).setColor(this.color);
            FireworksEffect.m10662$$Nest$fgetparticlePaint(this.this$0).setStrokeWidth(AndroidUtilities.dp(1.5f) * this.scale);
            FireworksEffect.m10662$$Nest$fgetparticlePaint(this.this$0).setAlpha((int) (this.alpha * 255.0f));
            r4.drawPoint(this.x, this.y, FireworksEffect.m10662$$Nest$fgetparticlePaint(this.this$0));
        }
    }

    /* renamed from: -$$Nest$fgetparticlePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m10662$$Nest$fgetparticlePaint(FireworksEffect r0) {
        return r0.particlePaint;
    }

    public FireworksEffect() {
        this.angleDiff = 1.0471976f;
        this.particles = new ArrayList();
        this.freeParticles = new ArrayList();
        Paint r0 = new Paint(1);
        this.particlePaint = r0;
        r0.setStrokeWidth(AndroidUtilities.dp(1.5f));
        this.particlePaint.setColor(Theme.getColor(Theme.key_actionBarDefaultTitle) & (-1644826));
        this.particlePaint.setStrokeCap(Paint.Cap.ROUND);
        this.particlePaint.setStyle(Paint.Style.STROKE);
        int r02 = 0;
    L4:
        if (r02 >= 20) goto L6;
        this.freeParticles.add(new Particle(this, null));
        r02 = r02 + 1;
        goto L4
    }

    private void updateParticles(long r9) {
        int r0 = this.particles.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        Particle r2 = this.particles.get(r1);
        float r3 = r2.currentTime;
        float r4 = r2.lifeTime;
        if (r3 >= r4) goto L7;
        r2.alpha = 1.0f - AndroidUtilities.decelerateInterpolator.getInterpolation(r3 / r4);
        float r32 = r2.x;
        float r42 = r2.vx;
        float r5 = r2.velocity;
        float r6 = r9;
        r2.x = r32 + (((r42 * r5) * r6) / 500.0f);
        float r33 = r2.y;
        float r43 = r2.vy;
        r2.y = r33 + (((r5 * r43) * r6) / 500.0f);
        r2.vy = r43 + (r6 / 100.0f);
        r2.currentTime += r6;
    L11:
        r1 = r1 + 1;
        goto L3
    L7:
        if (this.freeParticles.size() >= 40) goto L9;
        this.freeParticles.add(r2);
    L9:
        this.particles.remove(r1);
        r1 = r1 - 1;
        r0 = r0 - 1;
        goto L11
    }

    public void onDraw(View r12, Canvas r13) {
        if (r12 == null) goto L38;
        if (r13 == null) goto L39;
        int r0 = this.particles.size();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L9;
        this.particles.get(r2).draw(r13);
        r2 = r2 + 1;
        goto L6
    L9:
        if (Utilities.random.nextBoolean() == true) goto L11;
    L32:
        long r02 = System.currentTimeMillis();
        updateParticles(Math.min(17, r02 - this.lastAnimationTime));
        this.lastAnimationTime = r02;
        r12.invalidate();
        return;
    L11:
        if ((this.particles.size() + 8) >= 150) goto L32;
        int r132 = AndroidUtilities.statusBarHeight;
        float r22 = Utilities.random.nextFloat() * r12.getMeasuredWidth();
        float r3 = r132 + (Utilities.random.nextFloat() * ((r12.getMeasuredHeight() - AndroidUtilities.dp(20.0f)) - r132));
        int r133 = Utilities.random.nextInt(4);
        if (r133 != 0) goto L15;
        int r134 = -13357350;
    L25:
        int r4 = 0;
    L26:
        if (r4 >= 8) goto L32;
        double r9 = (Utilities.random.nextInt(270) - 225) * 0.017453292519943295d;
        float r5 = (float) Math.cos(r9);
        float r7 = (float) Math.sin(r9);
        if (this.freeParticles.isEmpty() == true) goto L30;
        Particle r8 = this.freeParticles.get(0);
        this.freeParticles.remove(0);
    L31:
        r8.x = r22;
        r8.y = r3;
        r8.vx = r5 * 1.5f;
        r8.vy = r7;
        r8.color = r134;
        r8.alpha = 1.0f;
        r8.currentTime = 0.0f;
        r8.scale = Math.max(1.0f, Utilities.random.nextFloat() * 1.5f);
        r8.type = 0;
        r8.lifeTime = Utilities.random.nextInt(1000) + 1000;
        r8.velocity = (Utilities.random.nextFloat() * 4.0f) + 20.0f;
        this.particles.add(r8);
        r4 = r4 + 1;
        goto L26
    L30:
        r8 = new Particle(this, null);
        goto L31
    L15:
        if (r133 != 1) goto L17;
        r134 = -843755;
        goto L25
    L17:
        if (r133 != 2) goto L19;
        r134 = -207021;
        goto L25
    L19:
        if (r133 == 3) goto L21;
        r134 = -5752;
        goto L25
    L21:
        r134 = -15088582;
        goto L25
    L39:
        return;
    }
}
