package org.telegram.ui.Components.Premium;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class SpeedLineParticles$Drawable {
    public final int count;
    private final float dt;
    private int lastColor;
    private float[] lines;
    public long minLifeTime;
    private Paint paint;
    ArrayList<Particle> particles;
    public boolean paused;
    long pausedTime;
    public RectF rect;
    public RectF screenRect;
    public int size1;
    public int size2;
    public int size3;
    public float speedScale;

    public class Particle {
        private int alpha;
        float inProgress;
        private long lifeTime;
        final /* synthetic */ SpeedLineParticles$Drawable this$0;
        private float vecX;
        private float vecY;
        private float x;
        private float y;

        /* renamed from: -$$Nest$fgetlifeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m12056$$Nest$fgetlifeTime(Particle r2) {
            return r2.lifeTime;
        }

        /* renamed from: -$$Nest$fgetx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m12057$$Nest$fgetx(Particle r0) {
            return r0.x;
        }

        /* renamed from: -$$Nest$fgety, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m12058$$Nest$fgety(Particle r0) {
            return r0.y;
        }

        private Particle(SpeedLineParticles$Drawable r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Particle(SpeedLineParticles$Drawable r1, SpeedLineParticlesIA r2) {
            this(r1);
        }

        public void draw(Canvas r4, int r5, long r6) {
            int r52 = r5 * 4;
            SpeedLineParticles$Drawable.m12055$$Nest$fgetlines(this.this$0)[r52] = this.x;
            SpeedLineParticles$Drawable.m12055$$Nest$fgetlines(this.this$0)[r52 + 1] = this.y;
            SpeedLineParticles$Drawable.m12055$$Nest$fgetlines(this.this$0)[r52 + 2] = this.x + (AndroidUtilities.dp(30.0f) * this.vecX);
            SpeedLineParticles$Drawable.m12055$$Nest$fgetlines(this.this$0)[r52 + 3] = this.y + (AndroidUtilities.dp(30.0f) * this.vecY);
            if (this.this$0.paused == true) goto L10;
            float r42 = AndroidUtilities.dp(4.0f) * (SpeedLineParticles$Drawable.m12054$$Nest$fgetdt(this.this$0) / 660.0f);
            SpeedLineParticles$Drawable r53 = this.this$0;
            float r43 = r42 * r53.speedScale;
            this.x += this.vecX * r43;
            this.y += this.vecY * r43;
            float r44 = this.inProgress;
            if (r44 == 1.0f) goto L11;
            float r45 = r44 + (SpeedLineParticles$Drawable.m12054$$Nest$fgetdt(r53) / 200.0f);
            this.inProgress = r45;
            if (r45 <= 1.0f) goto L12;
            this.inProgress = 1.0f;
            return;
        L12:
            return;
        L11:
            return;
        }

        public void genPosition(long r3, boolean r5) {
            this.lifeTime = (r3 + this.this$0.minLifeTime) + Utilities.fastRandom.nextInt(1000);
            SpeedLineParticles$Drawable r32 = this.this$0;
            if (r5 == false) goto L5;
            RectF r33 = r32.screenRect;
        L6:
            float r4 = r33.left + Math.abs(Utilities.fastRandom.nextInt() % r33.width());
            float r52 = r33.top + Math.abs(Utilities.fastRandom.nextInt() % r33.height());
            this.x = r4;
            this.y = r52;
            double r34 = Math.atan2(r4 - this.this$0.rect.centerX(), this.y - this.this$0.rect.centerY());
            this.vecX = (float) Math.sin(r34);
            this.vecY = (float) Math.cos(r34);
            this.alpha = (int) (((Utilities.fastRandom.nextInt(50) + 50) / 100.0f) * 255.0f);
            this.inProgress = 0.0f;
            return;
        L5:
            r33 = r32.rect;
            goto L6
        }
    }

    /* renamed from: -$$Nest$fgetdt, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12054$$Nest$fgetdt(SpeedLineParticles$Drawable r0) {
        return r0.dt;
    }

    /* renamed from: -$$Nest$fgetlines, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m12055$$Nest$fgetlines(SpeedLineParticles$Drawable r0) {
        return r0.lines;
    }

    public SpeedLineParticles$Drawable(int r3) {
        this.rect = new RectF();
        this.screenRect = new RectF();
        this.paint = new Paint();
        this.particles = new ArrayList();
        this.speedScale = 1.0f;
        this.size1 = 14;
        this.size2 = 12;
        this.size3 = 10;
        this.minLifeTime = 2000;
        this.dt = 1000.0f / AndroidUtilities.screenRefreshRate;
        this.count = r3;
        this.lines = new float[r3 * 4];
    }

    public void init() {
        if (this.particles.isEmpty() == false) goto L8;
        int r0 = 0;
    L6:
        if (r0 >= this.count) goto L8;
        this.particles.add(new Particle(this, null));
        r0 = r0 + 1;
    L8:
        updateColors();
    }

    public void onDraw(Canvas r9) {
        long r0 = System.currentTimeMillis();
        int r3 = 0;
    L4:
        if (r3 >= this.particles.size()) goto L15;
        Particle r4 = this.particles.get(r3);
        if (this.paused == false) goto L8;
        r4.draw(r9, r3, this.pausedTime);
    L10:
        if (r0 <= Particle.m12056$$Nest$fgetlifeTime(r4)) goto L12;
    L13:
        r4.genPosition(r0, false);
    L14:
        r3 = r3 + 1;
        goto L4
    L12:
        if (this.screenRect.contains(Particle.m12057$$Nest$fgetx(r4), Particle.m12058$$Nest$fgety(r4)) == true) goto L14;
    L8:
        r4.draw(r9, r3, r0);
        goto L10
    L15:
        r9.drawLines(this.lines, this.paint);
    }

    public void resetPositions() {
        long r0 = System.currentTimeMillis();
        int r2 = 0;
    L4:
        if (r2 >= this.particles.size()) goto L6;
        this.particles.get(r2).genPosition(r0, true);
        r2 = r2 + 1;
        goto L4
    }

    public void updateColors() {
        int r0 = ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_premiumStartSmallStarsColor2), 80);
        if (this.lastColor == r0) goto L6;
        this.lastColor = r0;
        this.paint.setColor(r0);
        return;
    }
}
