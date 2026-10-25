package org.telegram.ui.Components.Premium;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public abstract class HelloParticles {
    private static final String[] hellos = null;

    public static class Drawable {
        private float bitmapScale;
        private HashMap<String, Bitmap> bitmaps;
        public final int count;
        private final float dt;
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
        private TextPaint textPaint;

        public class Particle {
            private int alpha;
            private Bitmap bitmap;
            private long duration;
            private int h;
            float inProgress;
            private int l;
            private float scale;
            private boolean set;
            private StaticLayout staticLayout;
            final /* synthetic */ Drawable this$0;
            private float vecX;
            private float vecY;
            private int w;
            private float x;
            private float y;

            private Particle(Drawable r1) {
                this.this$0 = r1;
            }

            public /* synthetic */ Particle(Drawable r1, HelloParticlesIA r2) {
                this(r1);
            }

            public void draw(Canvas r4, int r5, long r6) {
                Drawable r52 = this.this$0;
                if (r52.paused == true) goto L10;
                float r62 = this.inProgress;
                if (r62 == 1.0f) goto L10;
                float r63 = r62 + (Drawable.m11955$$Nest$fgetdt(r52) / this.duration);
                this.inProgress = r63;
                if (r63 <= 1.0f) goto L10;
                this.inProgress = 1.0f;
            L10:
                if (this.bitmap == null) goto L13;
                r4.save();
                float r7 = 1.0f - (((float) Math.pow(this.inProgress - 0.5f, 2.0d)) * 4.0f);
                float r53 = (this.scale / Drawable.m11953$$Nest$fgetbitmapScale(this.this$0)) * ((0.4f * r7) + 0.7f);
                r4.translate(this.x - (this.w / 2.0f), this.y - (this.h / 2.0f));
                r4.scale(r53, r53, this.w / 2.0f, this.h / 2.0f);
                Drawable.m11956$$Nest$fgetpaint(this.this$0).setAlpha((int) (this.alpha * r7));
                r4.drawBitmap(this.bitmap, 0.0f, 0.0f, Drawable.m11956$$Nest$fgetpaint(this.this$0));
                r4.restore();
                return;
            }

            public void genPosition(long r17, int r19, boolean r20) {
                this.duration = Math.abs(Utilities.fastRandom.nextLong() % 2250) + 2250;
                this.scale = (Math.abs(Utilities.fastRandom.nextFloat()) * 0.45f) + 0.6f;
                String r5 = HelloParticles.m11952$$Nest$sfgethellos()[Math.abs(Utilities.fastRandom.nextInt() % HelloParticles.m11952$$Nest$sfgethellos().length)];
                if (r5.length() <= 7) goto L6;
                this.scale *= 0.6f;
            L8:
                StaticLayout r4 = new StaticLayout(r5, Drawable.m11957$$Nest$fgettextPaint(this.this$0), AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                this.staticLayout = r4;
                int r2 = 0;
                if (r4.getLineCount() > 0) goto L11;
                this.h = 0;
                this.w = 0;
                this.l = 0;
            L12:
                Bitmap r1 = (Bitmap) Drawable.m11954$$Nest$fgetbitmaps(this.this$0).get(r5);
                this.bitmap = r1;
                if (r1 != null) goto L15;
                this.bitmap = Bitmap.createBitmap(Math.max(1, this.w - Math.max(0, this.l)), Math.max(1, this.h), Bitmap.Config.ARGB_8888);
                Canvas r12 = new Canvas(this.bitmap);
                r12.translate(-this.l, 0.0f);
                this.staticLayout.draw(r12);
                Drawable.m11954$$Nest$fgetbitmaps(this.this$0).put(r5, this.bitmap);
            L15:
                RectF r13 = this.this$0.rect;
                float r52 = r13.left;
                int r6 = this.w;
                float r53 = r52 + (r6 / 4.0f);
                float r7 = r13.right - (r6 / 4.0f);
                if ((r19 % 2) != 0) goto L18;
                r7 = r13.centerX() - (this.w / 2.0f);
            L19:
                float r72 = r7 - r53;
                float r14 = Math.abs(Utilities.fastRandom.nextInt() % r72) + r53;
                float r62 = this.this$0.rect.top + Math.abs(Utilities.fastRandom.nextInt() % this.this$0.rect.height());
                int r8 = 0;
                float r9 = 0.0f;
            L21:
                if (r8 >= 10) goto L36;
                float r10 = Math.abs(Utilities.fastRandom.nextInt() % r72) + r53;
                float r11 = this.this$0.rect.top + Math.abs(Utilities.fastRandom.nextInt() % this.this$0.rect.height());
                float r122 = 2.1474836E9f;
                int r132 = r2;
            L24:
                if (r132 >= this.this$0.particles.size()) goto L33;
                Particle r142 = this.this$0.particles.get(r132);
                if (r142.set == false) goto L31;
                float r22 = Math.min(Math.abs((r142.x + ((r142.w * (this.scale / Drawable.m11953$$Nest$fgetbitmapScale(this.this$0))) * 1.1f)) - r10), Math.abs(r142.x - r10));
                float r3 = r142.y - r11;
                float r23 = (r22 * r22) + (r3 * r3);
                if (r23 >= r122) goto L31;
                r122 = r23;
            L31:
                r132 = r132 + 1;
                goto L24
            L33:
                if (r122 <= r9) goto L35;
                r14 = r10;
                r62 = r11;
                r9 = r122;
            L35:
                r8 = r8 + 1;
                r2 = 0;
                goto L21
            L36:
                this.x = r14;
                this.y = r62;
                double r15 = Math.atan2(r14 - this.this$0.rect.centerX(), this.y - this.this$0.rect.centerY());
                this.vecX = (float) Math.sin(r15);
                this.vecY = (float) Math.cos(r15);
                this.alpha = (int) (((Utilities.fastRandom.nextInt(50) + 50) / 100.0f) * 255.0f);
                if (r20 == false) goto L39;
                float r32 = Math.abs((Utilities.fastRandom.nextFloat() % 1.0f) * 0.9f);
            L40:
                this.inProgress = r32;
                this.set = true;
                return;
            L39:
                r32 = 0.0f;
                goto L40
            L18:
                r53 = (this.w / 2.0f) + r13.centerX();
                goto L19
            L11:
                this.l = (int) this.staticLayout.getLineLeft(0);
                this.w = (int) this.staticLayout.getLineWidth(0);
                this.h = this.staticLayout.getHeight();
                goto L12
            L6:
                if (r5.length() <= 5) goto L8;
                this.scale *= 0.75f;
                goto L8
            }
        }

        /* renamed from: -$$Nest$fgetbitmapScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11953$$Nest$fgetbitmapScale(Drawable r0) {
            return r0.bitmapScale;
        }

        /* renamed from: -$$Nest$fgetbitmaps, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HashMap m11954$$Nest$fgetbitmaps(Drawable r0) {
            return r0.bitmaps;
        }

        /* renamed from: -$$Nest$fgetdt, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11955$$Nest$fgetdt(Drawable r0) {
            return r0.dt;
        }

        /* renamed from: -$$Nest$fgetpaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m11956$$Nest$fgetpaint(Drawable r0) {
            return r0.paint;
        }

        /* renamed from: -$$Nest$fgettextPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextPaint m11957$$Nest$fgettextPaint(Drawable r0) {
            return r0.textPaint;
        }

        public Drawable(int r5) {
            this.textPaint = new TextPaint(1);
            this.bitmapScale = 1.0f;
            this.bitmaps = new HashMap();
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
            this.count = r5;
            this.textPaint.setTypeface(AndroidUtilities.bold());
            this.textPaint.setColor(-1);
            int r52 = SharedConfig.getDevicePerformanceClass();
            if (r52 == 0) goto L7;
            if (r52 == 1) goto L6;
            this.bitmapScale = 0.75f;
        L8:
            this.textPaint.setTextSize(AndroidUtilities.dp(this.bitmapScale * 24.0f));
            this.paint.setColor(-1);
            return;
        L6:
            this.bitmapScale = 0.5f;
            goto L8
        L7:
            this.bitmapScale = 0.25f;
            goto L8
        }

        public void init() {
            if (this.particles.isEmpty() == false) goto L8;
            int r0 = 0;
        L6:
            if (r0 >= this.count) goto L10;
            this.particles.add(new Particle(this, null));
            r0 = r0 + 1;
            goto L6
        L10:
            return;
        }

        public void onDraw(Canvas r8) {
            long r0 = System.currentTimeMillis();
            int r3 = 0;
        L4:
            if (r3 >= this.particles.size()) goto L13;
            Particle r4 = this.particles.get(r3);
            if (this.paused == false) goto L8;
            r4.draw(r8, r3, this.pausedTime);
        L10:
            if (r4.inProgress < 1.0f) goto L12;
            r4.genPosition(r0, r3, false);
        L12:
            r3 = r3 + 1;
            goto L4
        L8:
            r4.draw(r8, r3, r0);
            goto L10
        }

        public void recycle() {
            Iterator<Bitmap> r0 = this.bitmaps.values().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            r0.next().recycle();
            goto L4
        L6:
            this.bitmaps.clear();
        }

        public void resetPositions() {
            long r0 = System.currentTimeMillis();
            int r2 = 0;
        L4:
            if (r2 >= this.particles.size()) goto L6;
            this.particles.get(r2).genPosition(r0, r2, true);
            r2 = r2 + 1;
            goto L4
        }
    }

    /* renamed from: -$$Nest$sfgethellos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String[] m11952$$Nest$sfgethellos() {
        return hellos;
    }

    static {
        hellos = new String[]{"Hello", "Привіт", "Привет", "Bonjour", "Hola", "Ciao", "Olá", "여보세요", "你好", "Salve", "Sveiki", "Halo", "გამარჯობა", "Hallå", "Salam", "Tere", "Dia dhuit", "こんにちは", "Сайн уу", "Bongu", "Ahoj", "γεια", "Zdravo", "नमस्ते", "Habari", "Hallo", "ជំរាបសួរ", "مرحبًا", "ನಮಸ್ಕಾರ", "Салам", "Silav li wir", "سڵاو", "Kif inti", "Talofa", "Thobela", "हॅलो", "ሰላም", "Здраво", "ഹലോ", "ہیلو", "ꯍꯦꯜꯂꯣ", "Alô", "வணக்கம்", "Mhoro", "Moni", "Alo", "สวัสดี", "Salom", "Բարեւ"};
    }
}
