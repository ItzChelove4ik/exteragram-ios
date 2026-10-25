package org.telegram.ui.Components.Premium;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class MatrixParticlesDrawable {
    Bitmap[] bitmaps;
    Rect drawingRect;
    RectF excludeRect;
    MatrixTextParticle[][] matrixTextParticles;
    Paint paint;
    ArrayList<Particle>[] particles;
    int size;

    public class MatrixTextParticle {
        int index;
        long lastUpdateTime;
        int nextIndex;
        long nextUpdateTime;
        final /* synthetic */ MatrixParticlesDrawable this$0;

        private MatrixTextParticle(MatrixParticlesDrawable r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ MatrixTextParticle(MatrixParticlesDrawable r1, MatrixParticlesDrawableIA r2) {
            this(r1);
        }

        public void draw(Canvas r9, float r10, float r11, long r12, float r14) {
            if ((this.nextUpdateTime - r12) >= 150) goto L8;
            float r0 = Utilities.clamp(1.0f - ((r0 - r12) / 150.0f), 1.0f, 0.0f);
            this.this$0.paint.setAlpha((int) (((1.0f - r0) * r14) * 255.0f));
            MatrixParticlesDrawable r2 = this.this$0;
            r9.drawBitmap(r2.bitmaps[this.index], r10, r11, r2.paint);
            this.this$0.paint.setAlpha((int) ((r14 * r0) * 255.0f));
            MatrixParticlesDrawable r142 = this.this$0;
            r9.drawBitmap(r142.bitmaps[this.nextIndex], r10, r11, r142.paint);
            this.this$0.paint.setAlpha(255);
            if (r0 < 1.0f) goto L10;
            this.index = this.nextIndex;
            this.lastUpdateTime = r12;
            this.nextIndex = Math.abs(Utilities.fastRandom.nextInt() % 16);
            this.nextUpdateTime = (r12 + Math.abs(Utilities.fastRandom.nextInt() % 300)) + 150;
            return;
        L10:
            return;
        L8:
            this.this$0.paint.setAlpha((int) (r14 * 255.0f));
            MatrixParticlesDrawable r122 = this.this$0;
            r9.drawBitmap(r122.bitmaps[this.index], r10, r11, r122.paint);
        }

        public void init(long r3) {
            this.index = Math.abs(Utilities.fastRandom.nextInt() % 16);
            this.nextIndex = Math.abs(Utilities.fastRandom.nextInt() % 16);
            this.lastUpdateTime = r3;
            this.nextUpdateTime = (r3 + Math.abs(Utilities.fastRandom.nextInt() % 300)) + 150;
        }
    }

    public class Particle {
        int len;
        final /* synthetic */ MatrixParticlesDrawable this$0;
        long time;
        int y;

        private Particle(MatrixParticlesDrawable r1) {
            this.this$0 = r1;
            this.len = 5;
        }

        public /* synthetic */ Particle(MatrixParticlesDrawable r1, MatrixParticlesDrawableIA r2) {
            this(r1);
        }

        public void init(int r2, long r3) {
            this.y = Math.abs(Utilities.fastRandom.nextInt() % r2);
            this.time = r3;
            this.len = Math.abs(Utilities.fastRandom.nextInt() % 6) + 4;
        }

        public void reset(long r2) {
            this.y = 0;
            this.time = r2;
            this.len = Math.abs(Utilities.fastRandom.nextInt() % 6) + 4;
        }
    }

    public MatrixParticlesDrawable() {
        this.excludeRect = new RectF();
        this.bitmaps = new Bitmap[16];
        this.drawingRect = new Rect();
        this.paint = new Paint();
    }

    public void init() {
        this.size = AndroidUtilities.dp(16.0f);
        TextPaint r0 = new TextPaint(65);
        r0.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
        r0.setTextSize(this.size);
        r0.setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_premiumStartSmallStarsColor2), 30));
        r0.setTextAlign(Paint.Align.CENTER);
        int r1 = 0;
    L4:
        if (r1 >= 16) goto L10;
        if (r1 >= 10) goto L8;
        int r2 = r1 + 48;
    L9:
        Bitmap[] r3 = this.bitmaps;
        int r4 = this.size;
        r3[r1] = Bitmap.createBitmap(r4, r4, Bitmap.Config.ARGB_8888);
        new Canvas(this.bitmaps[r1]).drawText(Character.toString((char) r2), r4 >> 1, this.size, r0);
        r1 = r1 + 1;
        goto L4
    L8:
        r2 = r1 + 55;
        goto L9
    }

    public void onDraw(Canvas r19) {
        int r1 = this.drawingRect.width() / this.size;
        int r2 = this.drawingRect.height() / this.size;
        if (r1 == 0) goto L54;
        if (r2 == 0) goto L63;
        long r7 = System.currentTimeMillis();
        ArrayList<Particle>[] r3 = this.particles;
        MatrixParticlesDrawableIA r10 = null;
        int r11 = 0;
        if (r3 != null) goto L9;
    L10:
        this.particles = new ArrayList[r1 + 1];
        int r32 = 0;
    L11:
        if (r32 > r1) goto L13;
        this.particles[r32] = new ArrayList();
        Particle r4 = new Particle(this, null);
        r4.init(r2, r7);
        this.particles[r32].add(r4);
        r32 = r32 + 1;
    L13:
        MatrixTextParticle[][] r33 = this.matrixTextParticles;
        if (r33 != null) goto L16;
    L19:
        this.matrixTextParticles = new MatrixTextParticle[r1 + 1][];
        int r34 = 0;
    L20:
        if (r34 > r1) goto L25;
        this.matrixTextParticles[r34] = new MatrixTextParticle[r2 + 1];
        int r42 = 0;
    L22:
        if (r42 > r2) goto L24;
        this.matrixTextParticles[r34][r42] = new MatrixTextParticle(this, null);
        this.matrixTextParticles[r34][r42].init(r7);
        r42 = r42 + 1;
        goto L22
    L24:
        r34 = r34 + 1;
    L25:
        int r12 = 0;
    L26:
        if (r12 > r1) goto L64;
        ArrayList<Particle> r13 = this.particles[r12];
        int r35 = r11;
    L29:
        if (r35 >= r13.size()) goto L53;
        Particle r14 = r13.get(r35);
        if ((r7 - r14.time) <= 50) goto L45;
        int r43 = r14.y + 1;
        r14.y = r43;
        r14.time = r7;
        if ((r43 - r14.len) < r2) goto L39;
        if (r13.size() != 1) goto L37;
        r14.reset(r7);
        goto L39
    L37:
        r13.remove(r14);
        r35 = r35 - 1;
    L39:
        if (r14.y <= r14.len) goto L45;
        if (r35 != (r13.size() - 1)) goto L45;
        if (Math.abs(Utilities.fastRandom.nextInt(4)) != 0) goto L45;
        Particle r44 = new Particle(this, r10);
        r44.reset(r7);
        r13.add(r44);
    L45:
        int r16 = r35;
        int r36 = Math.min(r14.y, r2 + 1);
        int r45 = Math.max(r11, r14.y - r14.len);
    L46:
        if (r45 >= r36) goto L52;
        int r5 = this.size;
        float r6 = r5 * r12;
        float r52 = r5 * r45;
        if (this.excludeRect.contains(r6, r52) == true) goto L50;
        float r9 = Utilities.clamp(((1.0f - ((r14.y - r45) / (r14.len - 1))) * 0.8f) + 0.2f, 1.0f, 0.0f);
        int r102 = r36;
        int r112 = r45;
        this.matrixTextParticles[r12][r45].draw(r19, r6, r52, r7, r9);
    L51:
        r45 = r112 + 1;
        r36 = r102;
        goto L46
    L50:
        r102 = r36;
        r112 = r45;
        goto L51
    L52:
        r35 = r16 + 1;
        r10 = null;
        r11 = 0;
        goto L29
    L53:
        r12 = r12 + 1;
        r10 = null;
        r11 = 0;
        goto L26
    L64:
        return;
    L16:
        if (r33.length != (r1 + 1)) goto L19;
        if (r33[0].length == (r2 + 1)) goto L25;
    L9:
        if (r3.length == (r1 + 1)) goto L13;
    L63:
        return;
    }
}
