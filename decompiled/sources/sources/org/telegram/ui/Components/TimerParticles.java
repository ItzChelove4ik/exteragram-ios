package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class TimerParticles {
    public boolean big;
    private ArrayList<Particle> freeParticles;
    private boolean hasLast;
    private long lastAnimationTime;
    private float lastCx;
    private float lastCy;
    private ArrayList<Particle> particles;
    private final int particlesCount;

    public static class Particle {
        float alpha;
        float currentTime;
        float lifeTime;
        float velocity;
        float vx;
        float vy;
        float x;
        float y;

        private Particle() {
        }

        public /* synthetic */ Particle(TimerParticlesIA r1) {
            this();
        }
    }

    public TimerParticles() {
        this(40);
    }

    public TimerParticles(int r5) {
        this.particles = new ArrayList();
        this.freeParticles = new ArrayList();
        this.particlesCount = r5;
        int r0 = 0;
    L3:
        if (r0 >= r5) goto L5;
        this.freeParticles.add(new Particle(null));
        r0 = r0 + 1;
        goto L3
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
        r2.x = r32 + (((r42 * r5) * r6) / 200.0f);
        r2.y += ((r2.vy * r5) * r6) / 200.0f;
        r2.currentTime += r6;
    L11:
        r1 = r1 + 1;
        goto L3
    L7:
        if (this.freeParticles.size() >= this.particlesCount) goto L9;
        this.freeParticles.add(r2);
    L9:
        this.particles.remove(r1);
        r1 = r1 - 1;
        r0 = r0 - 1;
        goto L11
    }

    public void draw(Canvas r21, Paint r22, RectF r23, float r24, float r25) {
        int r2 = this.particles.size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L5;
        Particle r5 = this.particles.get(r4);
        r22.setAlpha((int) ((r5.alpha * 255.0f) * r25));
        r21.drawPoint(r5.x, r5.y, r22);
        r4 = r4 + 1;
        goto L3
    L5:
        double r42 = 0.017453292519943295d;
        double r1 = (r24 - 90.0f) * 0.017453292519943295d;
        double r6 = Math.sin(r1);
        double r12 = -Math.cos(r1);
        double r11 = r23.width() / 2.0f;
        float r8 = (float) (((-r12) * r11) + r23.centerX());
        float r9 = (float) ((r11 * r6) + r23.centerY());
        int r10 = Utilities.clamp(this.freeParticles.size() / 12, 3, 1);
        int r112 = 0;
    L6:
        if (r112 >= r10) goto L25;
        if (this.freeParticles.isEmpty() == true) goto L10;
        Particle r13 = this.freeParticles.get(r3);
        this.freeParticles.remove(r3);
    L12:
        if (this.big == true) goto L14;
    L16:
        r13.x = r8;
        r13.y = r9;
    L17:
        double r14 = (Utilities.random.nextInt(140) - 70) * r42;
        if (r14 >= 0.0d) goto L20;
        r14 = r14 + 6.283185307179586d;
    L20:
        r13.vx = (float) ((Math.cos(r14) * r6) - (Math.sin(r14) * r12));
        r13.vy = (float) ((Math.sin(r14) * r6) + (Math.cos(r14) * r12));
        r13.alpha = 1.0f;
        r13.currentTime = 0.0f;
        if (this.big == false) goto L23;
        r13.lifeTime = Utilities.random.nextInt(200) + MediaError.DetailedErrorCode.TEXT_UNKNOWN;
        r13.velocity = (Utilities.random.nextFloat() * 20.0f) + 30.0f;
    L24:
        this.particles.add(r13);
        r112 = r112 + 1;
        r3 = 0;
        r42 = 0.017453292519943295d;
        goto L6
    L23:
        r13.lifeTime = Utilities.random.nextInt(100) + MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        r13.velocity = (Utilities.random.nextFloat() * 4.0f) + 20.0f;
        goto L24
    L14:
        if (this.hasLast == false) goto L16;
        float r15 = (r112 + 1) / r10;
        r13.x = AndroidUtilities.lerp(this.lastCx, r8, r15);
        r13.y = AndroidUtilities.lerp(this.lastCy, r9, r15);
        goto L17
    L10:
        r13 = new Particle(null);
        goto L12
    L25:
        this.hasLast = true;
        this.lastCx = r8;
        this.lastCy = r9;
        long r16 = SystemClock.elapsedRealtime();
        updateParticles(Math.min(20, r16 - this.lastAnimationTime));
        this.lastAnimationTime = r16;
    }
}
