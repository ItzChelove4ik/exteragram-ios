package org.telegram.ui.Components.Premium;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import java.util.ArrayList;
import java.util.Random;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.GLIconSettingsView;

/* loaded from: classes7.dex */
public class StarParticlesView extends View {
    private LinearGradient clipGradient;
    private Matrix clipGradientMatrix;
    private Paint clipGradientPaint;
    public boolean doNotFling;
    public Drawable drawable;
    private boolean isLiteModeParticlesAllowed;
    private Utilities.Callback<Boolean> powerSaverCallback;
    int size;

    public static class Drawable {
        public float centerOffsetX;
        public float centerOffsetY;
        public boolean checkBounds;
        public boolean checkTime;
        public Integer color;
        public int colorKey;
        public final int count;
        public boolean distributionAlgorithm;
        private final float dt;
        public float excludeRadius;
        public RectF excludeRect;
        public boolean[] flip;
        public boolean forceMaxAlpha;
        public Utilities.CallbackReturn<Integer, Paint> getPaint;
        public boolean isCircle;
        public float k1;
        public float k2;
        public float k3;
        private int lastColor;
        private int lastParticleI;
        Matrix[] matrices;
        public long minLifeTime;
        public Paint overridePaint;
        public Paint paint;
        public ArrayList<Particle> particles;
        public boolean paused;
        public long pausedTime;
        float[][] points;
        int[] pointsCount;
        private long prevTime;
        public int randLifeTime;
        public RectF rect;
        public RectF rect2;
        public Theme.ResourcesProvider resourcesProvider;
        float[] rotationAngles;
        public boolean roundEffect;
        public int size1;
        public int size2;
        public int size3;
        public float speedScale;
        private Bitmap[] stars;
        public boolean startFromCenter;
        public boolean[] svg;
        public int type;
        public boolean useBlur;
        public boolean useGradient;
        public boolean useRotate;
        public boolean useScale;

        public class Particle {
            private int alpha;
            private float drawingX;
            private float drawingY;
            private boolean first;
            float flipProgress;
            private int i;
            float inProgress;
            public long lifeTime;
            private float randomRotate;
            private float scale;
            private int starIndex;
            final /* synthetic */ Drawable this$0;
            private float vecX;
            private float vecY;
            private float x;
            private float x2;
            private float y;
            private float y2;

            /* renamed from: -$$Nest$fgetdrawingX, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float m12063$$Nest$fgetdrawingX(Particle r0) {
                return r0.drawingX;
            }

            /* renamed from: -$$Nest$fgetdrawingY, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float m12064$$Nest$fgetdrawingY(Particle r0) {
                return r0.drawingY;
            }

            public Particle(Drawable r3) {
                this.this$0 = r3;
                this.scale = 1.0f;
                this.first = true;
                int r0 = Drawable.m12060$$Nest$fgetlastParticleI(r3);
                Drawable.m12062$$Nest$fputlastParticleI(r3, r0 + 1);
                this.i = r0;
            }

            public void draw(Canvas r11, long r12, float r14) {
                Drawable r0 = this.this$0;
                if (r0.useRotate == false) goto L5;
                int[] r1 = r0.pointsCount;
                int r2 = this.starIndex;
                int r3 = r1[r2];
                float[] r4 = r0.points[r2];
                int r5 = r3 * 2;
                this.drawingX = r4[r5];
                this.drawingY = r4[r5 + 1];
                r1[r2] = r3 + 1;
            L7:
                if (r0.excludeRect.isEmpty() == false) goto L9;
            L11:
                r11.save();
                r11.translate(this.drawingX, this.drawingY);
                float r02 = this.randomRotate;
                if (r02 == 0.0f) goto L15;
                r11.rotate(r02, Drawable.m12061$$Nest$fgetstars(this.this$0)[this.starIndex].getWidth() / 2.0f, Drawable.m12061$$Nest$fgetstars(this.this$0)[this.starIndex].getHeight() / 2.0f);
            L15:
                if (this.this$0.checkTime == true) goto L17;
            L19:
                float r122 = 0.0f;
            L20:
                float r13 = this.inProgress;
                if (r13 >= 1.0f) goto L23;
            L24:
                float r132 = AndroidUtilities.overshootInterpolator.getInterpolation(r13) * GLIconSettingsView.smallStarsSize;
                r11.scale(r132, r132, 0.0f, 0.0f);
            L25:
                Drawable r133 = this.this$0;
                if (r133.flip[this.starIndex] == false) goto L28;
                float r03 = this.flipProgress + ((Drawable.m12059$$Nest$fgetdt(r133) / 1000.0f) * Math.min(this.this$0.speedScale, 3.5f));
                this.flipProgress = r03;
                r11.scale((float) Math.cos(r03 * 3.141592653589793d), 1.0f, 0.0f, 0.0f);
            L28:
                Drawable r134 = this.this$0;
                Paint r04 = r134.overridePaint;
                if (r04 != null) goto L35;
                Utilities.CallbackReturn<Integer, Paint> r05 = r134.getPaint;
                if (r05 == null) goto L34;
                r04 = r05.run(Integer.valueOf(this.i));
                goto L35
            L34:
                r04 = r134.paint;
            L35:
                float r123 = 1.0f - r122;
                r04.setAlpha((int) ((this.alpha * r123) * r14));
                Bitmap r135 = Drawable.m12061$$Nest$fgetstars(this.this$0)[this.starIndex];
                if (this.this$0.useScale == false) goto L38;
                float r32 = ((this.scale * r123) * r14) * this.inProgress;
                r11.scale(r32, r32);
            L38:
                r11.drawBitmap(r135, -(r135.getWidth() >> 1), -(r135.getHeight() >> 1), r04);
                r11.restore();
            L40:
                if (this.this$0.paused == true) goto L51;
                float r124 = AndroidUtilities.dp(4.0f) * (Drawable.m12059$$Nest$fgetdt(this.this$0) / 660.0f);
                Drawable r136 = this.this$0;
                if (r136.flip[this.starIndex] == false) goto L44;
                float r125 = r124 * (Math.min(r136.speedScale, 3.5f) * 4.0f);
            L45:
                this.x += this.vecX * r125;
                this.y += this.vecY * r125;
                float r112 = this.inProgress;
                if (r112 == 1.0f) goto L52;
                float r113 = r112 + (Drawable.m12059$$Nest$fgetdt(this.this$0) / 200.0f);
                this.inProgress = r113;
                if (r113 <= 1.0f) goto L53;
                this.inProgress = 1.0f;
                return;
            L53:
                return;
            L52:
                return;
            L44:
                r125 = r124 * r136.speedScale;
                goto L45
            L51:
                return;
            L23:
                if (GLIconSettingsView.smallStarsSize == 1.0f) goto L25;
            L17:
                if ((this.lifeTime - r12) >= 200) goto L19;
                r122 = Utilities.clamp(1.0f - ((r4 - r12) / 150.0f), 1.0f, 0.0f);
                goto L20
            L9:
                if (this.this$0.excludeRect.contains(this.drawingX, this.drawingY) == false) goto L11;
            L5:
                this.drawingX = this.x;
                this.drawingY = this.y;
                goto L7
            }

            public void genPosition(long r17) {
                if (this.this$0.type == 28) goto L5;
                this.starIndex = Math.abs(Utilities.fastRandom.nextInt() % Drawable.m12061$$Nest$fgetstars(this.this$0).length);
            L9:
                Drawable r1 = this.this$0;
                long r5 = r17 + r1.minLifeTime;
                Random r2 = Utilities.fastRandom;
                int r7 = r1.randLifeTime;
                if (r1.flip[this.starIndex] == false) goto L12;
                int r12 = 3;
            L13:
                this.lifeTime = r5 + r2.nextInt(r7 * r12);
                this.randomRotate = 0.0f;
                float r52 = 0.6f;
                if (this.this$0.useScale == false) goto L16;
                this.scale = (Utilities.fastRandom.nextFloat() * 0.6f) + 0.4f;
            L16:
                Drawable r22 = this.this$0;
                if (r22.distributionAlgorithm == false) goto L38;
                float r23 = r22.rect.left + Math.abs(Utilities.fastRandom.nextInt() % this.this$0.rect.width());
                float r6 = this.this$0.rect.top + Math.abs(Utilities.fastRandom.nextInt() % this.this$0.rect.height());
                float r10 = 0.0f;
                int r72 = 0;
            L20:
                if (r72 >= 10) goto L37;
                float r11 = this.this$0.rect.left + Math.abs(Utilities.fastRandom.nextInt() % this.this$0.rect.width());
                float r122 = this.this$0.rect.top + Math.abs(Utilities.fastRandom.nextInt() % this.this$0.rect.height());
                float r13 = 2.1474836E9f;
                int r14 = 0;
            L23:
                if (r14 >= this.this$0.particles.size()) goto L33;
                Drawable r15 = this.this$0;
                float r172 = r52;
                if (r15.startFromCenter == false) goto L28;
                float r53 = r15.particles.get(r14).x2 - r11;
                float r152 = this.this$0.particles.get(r14).y2;
            L27:
                float r153 = r152 - r122;
                float r54 = (r53 * r53) + (r153 * r153);
                if (r54 >= r13) goto L32;
                r13 = r54;
            L32:
                r14 = r14 + 1;
                r52 = r172;
                goto L23
            L28:
                r53 = r15.particles.get(r14).x - r11;
                r152 = this.this$0.particles.get(r14).y;
                goto L27
            L33:
                float r173 = r52;
                if (r13 <= r10) goto L36;
                r23 = r11;
                r6 = r122;
                r10 = r13;
            L36:
                r72 = r72 + 1;
                r52 = r173;
                goto L20
            L37:
                float r174 = r52;
                this.x = r23;
                this.y = r6;
            L49:
                if (this.this$0.flip[this.starIndex] == false) goto L51;
                this.flipProgress = Math.abs(Utilities.fastRandom.nextFloat() * 2.0f);
            L51:
                Drawable r24 = this.this$0;
                if (r24.flip[this.starIndex] == false) goto L55;
                double r62 = Math.toRadians(280.0f - (200.0f * Utilities.fastRandom.nextFloat()));
            L58:
                this.vecX = (float) Math.cos(r62);
                this.vecY = (float) Math.sin(r62);
                if (this.this$0.svg[this.starIndex] == false) goto L61;
                this.alpha = (int) (((Utilities.fastRandom.nextInt(50) + 50) / 100.0f) * 120.0f);
            L62:
                int r25 = this.this$0.type;
                if (r25 != 6) goto L68;
                int r112 = this.starIndex;
                if (r112 == 1) goto L80;
                if (r112 != 2) goto L68;
            L80:
                this.randomRotate = (int) (((Utilities.fastRandom.nextInt() % 100) / 100.0f) * 45.0f);
            L81:
                Drawable r26 = this.this$0;
                if (r26.type == 101) goto L85;
                this.inProgress = 0.0f;
            L85:
                if (r26.startFromCenter == false) goto L87;
                float r16 = (((Utilities.fastRandom.nextFloat() * 1.2f) + r174) * Math.min(this.this$0.rect.width(), this.this$0.rect.height())) / 2.0f;
                float r27 = (this.this$0.rect.centerX() + this.this$0.centerOffsetX) + (((float) Math.cos(r62)) * r16);
                this.x = r27;
                this.x2 = r27;
                float r28 = (this.this$0.rect.centerY() + this.this$0.centerOffsetY) + (((float) Math.sin(r62)) * r16);
                this.y = r28;
                this.y2 = r28;
            L87:
                this.first = false;
                return;
            L68:
                if (r25 == 9) goto L80;
                if (r25 == 3) goto L80;
                if (r25 == 7) goto L80;
                if (r25 == 24) goto L80;
                if (r25 == 11) goto L80;
                if (r25 == 22) goto L80;
                if (r25 != 4) goto L81;
            L61:
                this.alpha = (int) (((Utilities.fastRandom.nextInt(50) + 50) / 100.0f) * 255.0f);
                goto L62
            L55:
                if (r24.startFromCenter == false) goto L57;
                r62 = (Utilities.fastRandom.nextDouble() * 3.141592653589793d) * 2.0d;
                goto L58
            L57:
                float r63 = this.y;
                float r29 = r24.rect.centerY();
                Drawable r73 = this.this$0;
                r62 = Math.atan2(r63 - (r29 + r73.centerOffsetY), this.x - (r73.rect.centerX() + this.this$0.centerOffsetX));
                goto L58
            L38:
                r174 = 0.6f;
                if (r22.isCircle == false) goto L47;
                float r55 = this.this$0.rect.width();
                float r64 = this.this$0.excludeRadius;
                float r210 = ((Math.abs(Utilities.fastRandom.nextInt() % 1000) / 1000.0f) * (r55 - r64)) + r64;
                float r56 = Math.abs(Utilities.fastRandom.nextInt() % 360);
                if (this.this$0.flip[this.starIndex] == true) goto L43;
            L45:
                float r65 = 0.0f;
            L46:
                double r102 = r210;
                double r123 = r56;
                this.x = (this.this$0.rect.centerX() + this.this$0.centerOffsetX) + ((float) (Math.sin(Math.toRadians(r123)) * r102));
                this.y = ((this.this$0.rect.centerY() + r65) + this.this$0.centerOffsetY) + ((float) (r102 * Math.cos(Math.toRadians(r123))));
                goto L49
            L43:
                if (this.first == true) goto L45;
                r210 = Math.min(r210, AndroidUtilities.dp(10.0f));
                r65 = AndroidUtilities.dp(30.0f) + 0.0f;
                goto L46
            L47:
                this.x = r22.rect.left + Math.abs(Utilities.fastRandom.nextInt() % this.this$0.rect.width());
                this.y = this.this$0.rect.top + Math.abs(Utilities.fastRandom.nextInt() % this.this$0.rect.height());
                goto L49
            L12:
                r12 = 1;
                goto L13
            L5:
                if (Utilities.fastRandom.nextFloat() >= 0.13f) goto L7;
                this.starIndex = 0;
                goto L9
            L7:
                this.starIndex = (int) Math.floor((r1 * (Drawable.m12061$$Nest$fgetstars(this.this$0).length - 2)) + 1.0f);
                goto L9
            }

            public void updatePoint() {
                Drawable r0 = this.this$0;
                int[] r1 = r0.pointsCount;
                int r2 = this.starIndex;
                int r3 = r1[r2];
                float[] r02 = r0.points[r2];
                int r4 = r3 * 2;
                r02[r4] = this.x;
                r02[r4 + 1] = this.y;
                r1[r2] = r3 + 1;
            }
        }

        /* renamed from: -$$Nest$fgetdt, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m12059$$Nest$fgetdt(Drawable r0) {
            return r0.dt;
        }

        /* renamed from: -$$Nest$fgetlastParticleI, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12060$$Nest$fgetlastParticleI(Drawable r0) {
            return r0.lastParticleI;
        }

        /* renamed from: -$$Nest$fgetstars, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Bitmap[] m12061$$Nest$fgetstars(Drawable r0) {
            return r0.stars;
        }

        /* renamed from: -$$Nest$fputlastParticleI, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12062$$Nest$fputlastParticleI(Drawable r0, int r1) {
            r0.lastParticleI = r1;
        }

        public Drawable(int r4) {
            this.rect = new RectF();
            this.rect2 = new RectF();
            this.excludeRect = new RectF();
            this.stars = new Bitmap[3];
            this.svg = new boolean[3];
            this.flip = new boolean[3];
            this.paint = new Paint();
            this.excludeRadius = 0.0f;
            this.centerOffsetX = 0.0f;
            this.centerOffsetY = 0.0f;
            this.particles = new ArrayList();
            this.speedScale = 1.0f;
            this.size1 = 14;
            this.size2 = 12;
            this.size3 = 10;
            this.k1 = 0.85f;
            this.k2 = 0.85f;
            this.k3 = 0.9f;
            this.minLifeTime = 2000;
            this.randLifeTime = 1000;
            this.dt = 1000.0f / AndroidUtilities.screenRefreshRate;
            boolean r0 = false;
            this.checkBounds = false;
            this.checkTime = true;
            this.isCircle = true;
            this.useBlur = false;
            this.forceMaxAlpha = false;
            this.roundEffect = true;
            this.type = -1;
            this.colorKey = Theme.key_premiumStartSmallStarsColor;
            this.lastParticleI = 0;
            this.count = r4;
            if (r4 >= 50) goto L5;
            r0 = true;
        L5:
            this.distributionAlgorithm = r0;
        }

        private void generateBitmaps() {
            int r2 = 6;
            int r3 = 3;
            int r4 = 43;
            if (this.type == 43) goto L5;
            int r1 = 3;
        L15:
            int r5 = 0;
            int r6 = 0;
        L16:
            if (r6 >= r1) goto L140;
            float r7 = this.k1;
            if (r6 != 0) goto L21;
            int r9 = AndroidUtilities.dp(this.size1);
        L20:
            int r12 = r9;
            int r92 = this.type;
            if (r92 != 9) goto L33;
            if (r6 != 0) goto L28;
            int r72 = R.raw.premium_object_folder;
        L31:
            this.stars[r6] = SvgHelper.getBitmap(r72, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
        L139:
            r6 = r6 + 1;
            r2 = 6;
            r3 = 3;
            r4 = 43;
            r5 = 0;
            goto L16
        L28:
            if (r6 != 1) goto L30;
            r72 = R.raw.premium_object_bubble;
            goto L31
        L30:
            r72 = R.raw.premium_object_settings;
            goto L31
        L33:
            if (r92 != 27) goto L41;
            if (r6 != 0) goto L36;
            int r73 = R.raw.filled_messages_paid;
        L39:
            this.stars[r6] = SvgHelper.getBitmap(r73, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
            goto L139
        L36:
            if (r6 != 1) goto L38;
            r73 = R.raw.filled_crown_on;
            goto L39
        L38:
            r73 = R.raw.premium_object_star2;
            goto L39
        L41:
            if (r92 != 11) goto L43;
        L133:
            if (r6 != 0) goto L135;
            int r22 = R.raw.premium_object_smile1;
        L138:
            this.stars[r6] = SvgHelper.getBitmap(r22, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
            goto L139
        L135:
            if (r6 != 1) goto L137;
            r22 = R.raw.premium_object_smile2;
            goto L138
        L137:
            r22 = R.raw.premium_object_like;
            goto L138
        L43:
            if (r92 == 4) goto L133;
            if (r92 != 22) goto L53;
            if (r6 != 0) goto L49;
            int r74 = R.raw.premium_object_user;
        L52:
            this.stars[r6] = SvgHelper.getBitmap(r74, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
            goto L139
        L49:
            if (r6 != 1) goto L51;
            r74 = R.raw.cache_photos;
            goto L52
        L51:
            r74 = R.raw.cache_profile_photos;
            goto L52
        L53:
            if (r92 != r3) goto L61;
            if (r6 != 0) goto L56;
            int r75 = R.raw.premium_object_adsbubble;
        L59:
            this.stars[r6] = SvgHelper.getBitmap(r75, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
            goto L139
        L56:
            if (r6 != 1) goto L58;
            r75 = R.raw.premium_object_like;
            goto L59
        L58:
            r75 = R.raw.premium_object_noads;
            goto L59
        L61:
            if (r92 != 7) goto L69;
            if (r6 != 0) goto L64;
            int r76 = R.raw.premium_object_video2;
        L67:
            this.stars[r6] = SvgHelper.getBitmap(r76, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
            goto L139
        L64:
            if (r6 != 1) goto L66;
            r76 = R.raw.premium_object_video;
            goto L67
        L66:
            r76 = R.raw.premium_object_user;
            goto L67
        L69:
            if (r92 != r4) goto L83;
            if (r6 != 0) goto L72;
            int r77 = R.raw.premium_object_list;
        L81:
            this.stars[r6] = SvgHelper.getBitmap(r77, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
            goto L139
        L72:
            if (r6 != 1) goto L74;
            r77 = R.raw.premium_object_math;
            goto L81
        L74:
            if (r6 != 2) goto L76;
            r77 = R.raw.premium_object_table;
            goto L81
        L76:
            if (r6 != r3) goto L78;
            r77 = R.raw.premium_object_superscript;
            goto L81
        L78:
            if (r6 != 4) goto L80;
            r77 = R.raw.premium_object_bold;
            goto L81
        L80:
            r77 = R.raw.premium_object_code;
            goto L81
        L83:
            if (r92 != 1001) goto L86;
            this.stars[r6] = SvgHelper.getBitmap(R.raw.premium_object_fire, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
            goto L139
        L86:
            if (r92 != 1002) goto L89;
            this.stars[r6] = SvgHelper.getBitmap(R.raw.premium_object_star2, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
            goto L139
        L89:
            if (r92 != 24) goto L97;
            if (r6 != 0) goto L92;
            int r78 = R.raw.premium_object_tag;
        L95:
            this.stars[r6] = SvgHelper.getBitmap(r78, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 30));
            this.svg[r6] = true;
            goto L139
        L92:
            if (r6 != 1) goto L94;
            r78 = R.raw.premium_object_check;
            goto L95
        L94:
            r78 = R.raw.premium_object_star;
            goto L95
        L97:
            if (r92 != 28) goto L101;
            if (r6 != 0) goto L104;
            this.stars[r6] = SvgHelper.getBitmap(R.raw.filled_premium_dollar, r12, r12, ColorUtils.setAlphaComponent(Theme.getColor(this.colorKey, this.resourcesProvider), 255));
            this.flip[r6] = true;
        L104:
            Bitmap r93 = Bitmap.createBitmap(r12, r12, Bitmap.Config.ARGB_8888);
            this.stars[r6] = r93;
            Canvas r10 = new Canvas(r93);
            if (this.type != r2) goto L109;
            if (r6 == 1) goto L108;
            if (r6 != 2) goto L109;
        L108:
            android.graphics.drawable.Drawable r79 = ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_premium_liststar);
            r79.setColorFilter(new PorterDuffColorFilter(Theme.getColor(this.colorKey, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
            r79.setBounds(r5, r5, r12, r12);
            r79.draw(r10);
        L109:
            Path r8 = new Path();
            float r14 = r12 >> 1;
            int r710 = (int) (r7 * r14);
            r8.moveTo(0.0f, r14);
            float r23 = r710;
            r8.lineTo(r23, r23);
            r8.lineTo(r14, 0.0f);
            float r711 = r12 - r710;
            r8.lineTo(r711, r23);
            float r32 = r12;
            r8.lineTo(r32, r14);
            r8.lineTo(r711, r711);
            r8.lineTo(r14, r32);
            r8.lineTo(r23, r711);
            r8.lineTo(0.0f, r14);
            r8.close();
            Paint r24 = new Paint();
            if (this.useGradient == true) goto L112;
            Bitmap r33 = r93;
            int r52 = 2;
            r24.setColor(getPathColor(r6));
            if (this.roundEffect == false) goto L129;
            r24.setPathEffect(new CornerPathEffect(AndroidUtilities.dpf2(this.size1 / 5.0f)));
        L129:
            r10.drawPath(r8, r24);
        L131:
            if (this.useBlur == false) goto L139;
            Utilities.stackBlurBitmap(r33, r52);
            goto L139
        L112:
            if (r12 < AndroidUtilities.dp(10.0f)) goto L114;
            r33 = r93;
            Canvas r25 = r10;
            int r42 = 255;
            r52 = 2;
            PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, r12, r12, r12 * (-2), 0.0f);
        L115:
            Paint r94 = PremiumGradient.getInstance().getMainGradientPaint();
            if (this.roundEffect == false) goto L119;
            r94.setPathEffect(new CornerPathEffect(AndroidUtilities.dpf2(this.size1 / 5.0f)));
        L119:
            if (this.forceMaxAlpha == false) goto L122;
            r94.setAlpha(r42);
        L125:
            r25.drawPath(r8, r94);
            r94.setPathEffect(null);
            r94.setAlpha(r42);
            goto L131
        L122:
            if (this.useBlur == false) goto L124;
            r94.setAlpha(60);
            goto L125
        L124:
            r94.setAlpha(120);
            goto L125
        L114:
            r33 = r93;
            r25 = r10;
            r42 = 255;
            r52 = 2;
            PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, r12, r12, r12 * (-4), 0.0f);
            goto L115
        L101:
            if (r92 != 105) goto L104;
            if (r6 != 0) goto L104;
            this.stars[r6] = SvgHelper.getBitmap(R.raw.premium_object_star2, r12, r12, getPathColor(r6));
            goto L139
        L21:
            if (r6 != 1) goto L23;
            r7 = this.k2;
            r9 = AndroidUtilities.dp(this.size2);
            goto L20
        L23:
            r7 = this.k3;
            r9 = AndroidUtilities.dp(this.size3);
            goto L20
        L140:
            return;
        L5:
            if (this.stars.length == 6) goto L8;
            this.stars = new Bitmap[6];
        L8:
            if (this.svg.length == 6) goto L11;
            this.svg = new boolean[6];
        L11:
            if (this.flip.length == 6) goto L13;
            this.flip = new boolean[6];
        L13:
            r1 = 6;
            goto L15
        }

        private void initRotationArrays() {
            int r0 = this.stars.length;
            this.matrices = new Matrix[r0];
            this.points = new float[r0][];
            this.pointsCount = new int[r0];
            this.rotationAngles = new float[r0];
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.matrices[r1] = new Matrix();
            this.points[r1] = new float[this.count * 2];
            r1 = r1 + 1;
            goto L3
        }

        public int getPathColor(int r3) {
            Integer r32 = this.color;
            if (r32 != null) goto L5;
            int r33 = this.type;
            int r0 = this.colorKey;
            if (r33 != 100) goto L11;
            return ColorUtils.setAlphaComponent(Theme.getColor(r0, this.resourcesProvider), 200);
        L11:
            return Theme.getColor(r0, this.resourcesProvider);
        L5:
            return r32.intValue();
        }

        public void init() {
            generateBitmaps();
            if (this.useRotate == false) goto L6;
            initRotationArrays();
        L6:
            if (this.particles.isEmpty() == false) goto L11;
            int r0 = 0;
        L9:
            if (r0 >= this.count) goto L13;
            this.particles.add(new Particle(this));
            r0 = r0 + 1;
            goto L9
        L13:
            return;
        }

        public void onDraw(Canvas r2) {
            onDraw(r2, 1.0f);
        }

        public void onDraw(Canvas r18, float r19) {
            long r3 = System.currentTimeMillis();
            long r5 = MathUtils.clamp(r3 - this.prevTime, 4, 50);
            int r8 = 0;
            if (this.useRotate == false) goto L17;
            float r7 = this.rect.centerX() + this.centerOffsetX;
            float r9 = this.rect.centerY() + this.centerOffsetY;
            int r10 = 0;
        L5:
            Matrix[] r11 = this.matrices;
            if (r10 >= r11.length) goto L8;
            float[] r12 = this.rotationAngles;
            float r13 = r12[r10] + ((r5 / ((r10 * 10000.0f) + 40000.0f)) * 360.0f);
            r12[r10] = r13;
            r11[r10].setRotate(r13, r7, r9);
            this.pointsCount[r10] = 0;
            r10 = r10 + 1;
            goto L5
        L8:
            int r52 = 0;
        L10:
            if (r52 >= this.particles.size()) goto L12;
            this.particles.get(r52).updatePoint();
            r52 = r52 + 1;
            goto L10
        L12:
            int r53 = 0;
        L13:
            Matrix[] r6 = this.matrices;
            if (r53 >= r6.length) goto L17;
            Matrix r92 = r6[r53];
            float[] r102 = this.points[r53];
            r92.mapPoints(r102, 0, r102, 0, this.pointsCount[r53]);
            this.pointsCount[r53] = 0;
            r53 = r53 + 1;
        L17:
            if (r8 >= this.particles.size()) goto L33;
            Particle r54 = this.particles.get(r8);
            if (this.paused == false) goto L21;
            r54.draw(r18, this.pausedTime, r19);
        L23:
            if (this.checkTime == false) goto L28;
            if (r3 <= r54.lifeTime) goto L28;
            r54.genPosition(r3);
        L28:
            if (this.checkBounds == false) goto L32;
            if (this.rect2.contains(Particle.m12063$$Nest$fgetdrawingX(r54), Particle.m12064$$Nest$fgetdrawingY(r54)) == true) goto L32;
            r54.genPosition(r3);
        L32:
            r8 = r8 + 1;
            goto L17
        L21:
            r54.draw(r18, r3, r19);
            goto L23
        L33:
            this.prevTime = r3;
        }

        public void resetPositions() {
            long r0 = System.currentTimeMillis();
            int r2 = 0;
        L4:
            if (r2 >= this.particles.size()) goto L6;
            this.particles.get(r2).genPosition(r0);
            r2 = r2 + 1;
            goto L4
        }

        public void updateColors() {
            int r0 = Theme.getColor(this.colorKey, this.resourcesProvider);
            if (this.lastColor == r0) goto L6;
            this.lastColor = r0;
            generateBitmaps();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$2Vr0BERlDokxhZwAnzErlAX8qPg(StarParticlesView r0, ValueAnimator r1) {
        r0.lambda$flingParticles$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$UH6mZNzRN_OLV9818OwswDaehT0(StarParticlesView r0, Boolean r1) {
        r0.lambda$onAttachedToWindow$0(r1);
    }

    public StarParticlesView(Context r3) {
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L6;
        int r0 = 200;
    L9:
        this(r3, r0);
        return;
    L6:
        if (SharedConfig.getDevicePerformanceClass() != 1) goto L8;
        r0 = 100;
        goto L9
    L8:
        r0 = 50;
        goto L9
    }

    public StarParticlesView(Context r1, int r2) {
        super(r1);
        this.isLiteModeParticlesAllowed = true;
        this.drawable = new Drawable(r2);
        configure();
    }

    private /* synthetic */ void lambda$flingParticles$1(ValueAnimator r1) {
        Drawable r0 = this.drawable;
        r0.speedScale = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ void lambda$onAttachedToWindow$0(Boolean r1) {
        onApplyPowerSaverMode();
    }

    private void onApplyPowerSaverMode() {
        boolean r0 = LiteMode.isEnabled(131072);
        if (this.isLiteModeParticlesAllowed == r0) goto L6;
        this.isLiteModeParticlesAllowed = r0;
        invalidate();
        return;
    }

    public void configure() {
        Drawable r1 = this.drawable;
        r1.type = 100;
        r1.roundEffect = true;
        r1.useRotate = true;
        r1.useBlur = true;
        r1.checkBounds = true;
        r1.size1 = 4;
        r1.k3 = 0.98f;
        r1.k2 = 0.98f;
        r1.k1 = 0.98f;
        r1.init();
    }

    public void flingParticles(float r9) {
        if (this.doNotFling == false) goto L6;
        return;
    L6:
        if (r9 >= 60.0f) goto L9;
        float r92 = 5.0f;
    L12:
        AnimatorSet r0 = new AnimatorSet();
        StarParticlesView$$ExternalSyntheticLambda1 r1 = new StarParticlesView$$ExternalSyntheticLambda1(this);
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{1.0f, r92});
        r2.addUpdateListener(r1);
        r2.setDuration(600);
        ValueAnimator r93 = ValueAnimator.ofFloat(new float[]{r92, 1.0f});
        r93.addUpdateListener(r1);
        r93.setDuration(2000);
        r0.playTogether(new Animator[]{r2, r93});
        r0.start();
        return;
    L9:
        if (r9 >= 180.0f) goto L11;
        r92 = 9.0f;
        goto L12
    L11:
        r92 = 15.0f;
        goto L12
    }

    public int getStarsRectWidth() {
        return AndroidUtilities.dp(140.0f);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        StarParticlesView$$ExternalSyntheticLambda0 r0 = new StarParticlesView$$ExternalSyntheticLambda0(this);
        this.powerSaverCallback = r0;
        LiteMode.addOnPowerSaverAppliedListener(r0);
        onApplyPowerSaverMode();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Utilities.Callback<Boolean> r0 = this.powerSaverCallback;
        if (r0 == null) goto L6;
        LiteMode.removeOnPowerSaverAppliedListener(r0);
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        super.onDraw(r15);
        if (this.isLiteModeParticlesAllowed == true) goto L6;
        return;
    L6:
        if (this.clipGradientPaint == null) goto L8;
        r15.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        Canvas r8 = r15;
    L9:
        this.drawable.onDraw(r8);
        if (this.clipGradientPaint == null) goto L13;
        r8.save();
        this.clipGradientMatrix.reset();
        this.clipGradientMatrix.postTranslate(0.0f, (getHeight() + 1) - AndroidUtilities.dp(12.0f));
        this.clipGradient.setLocalMatrix(this.clipGradientMatrix);
        r8.drawRect(0.0f, getHeight() - AndroidUtilities.dp(12.0f), getWidth(), getHeight(), this.clipGradientPaint);
        this.clipGradientMatrix.reset();
        this.clipGradientMatrix.postRotate(180.0f);
        this.clipGradientMatrix.postTranslate(0.0f, AndroidUtilities.dp(12.0f));
        this.clipGradient.setLocalMatrix(this.clipGradientMatrix);
        r8.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.dp(12.0f), this.clipGradientPaint);
        r8.restore();
        r8.restore();
    L13:
        if (this.drawable.paused == true) goto L16;
        invalidate();
        return;
    L16:
        return;
    L8:
        r8 = r15;
        goto L9
    }

    @Override // android.view.View
    public void onMeasure(int r6, int r7) {
        super.onMeasure(r6, r7);
        int r62 = (getMeasuredWidth() << 16) + getMeasuredHeight();
        this.drawable.rect.set(0.0f, 0.0f, getStarsRectWidth(), AndroidUtilities.dp(140.0f));
        this.drawable.rect.offset((getMeasuredWidth() - this.drawable.rect.width()) / 2.0f, (getMeasuredHeight() - this.drawable.rect.height()) / 2.0f);
        this.drawable.rect2.set(-AndroidUtilities.dp(15.0f), -AndroidUtilities.dp(15.0f), getMeasuredWidth() + AndroidUtilities.dp(15.0f), getMeasuredHeight() + AndroidUtilities.dp(15.0f));
        if (this.size == r62) goto L6;
        this.size = r62;
        this.drawable.resetPositions();
        return;
    }

    public void setClipWithGradient() {
        Paint r0 = new Paint(1);
        this.clipGradientPaint = r0;
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        LinearGradient r3 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(12.0f), new int[]{16777215, -1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.clipGradient = r3;
        this.clipGradientPaint.setShader(r3);
        this.clipGradientMatrix = new Matrix();
    }

    public void setPaused(boolean r8) {
        Drawable r0 = this.drawable;
        if (r8 != r0.paused) goto L5;
        return;
    L5:
        r0.paused = r8;
        if (r8 == false) goto L9;
        r0.pausedTime = System.currentTimeMillis();
        return;
    L9:
        int r82 = 0;
    L11:
        if (r82 >= this.drawable.particles.size()) goto L13;
        this.drawable.particles.get(r82).lifeTime += System.currentTimeMillis() - this.drawable.pausedTime;
        r82 = r82 + 1;
        goto L11
    L13:
        invalidate();
    }
}
