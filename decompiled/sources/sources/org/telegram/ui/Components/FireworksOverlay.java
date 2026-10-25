package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import java.util.ArrayList;
import java.util.Calendar;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
public class FireworksOverlay extends View {
    private static int[] colors;
    private static final int fallParticlesCount = 0;
    private static int[] heartColors;
    private static Drawable[] heartDrawable;
    private static Paint[] paint;
    private static final int particlesCount = 0;
    private static int[] starsColors;
    private static Drawable[] starsDrawable;
    private int fallingDownCount;
    private boolean isFebruary14;
    private long lastUpdateTime;
    private ArrayList<Particle> particles;
    private RectF rect;
    private float speedCoef;
    private boolean started;
    private boolean startedFall;
    private boolean withStars;

    /* loaded from: classes7.dex */
    public class Particle {
        byte colorType;
        byte finishedStart;
        float moveX;
        float moveY;
        short rotation;
        byte side;
        final /* synthetic */ FireworksOverlay this$0;
        byte type;
        byte typeSize;
        float x;
        byte xFinished;
        float y;

        /* renamed from: -$$Nest$mdraw, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10671$$Nest$mdraw(Particle r0, Canvas r1) {
            r0.draw(r1);
        }

        /* renamed from: -$$Nest$mupdate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10672$$Nest$mupdate(Particle r0, int r1) {
            return r0.update(r1);
        }

        private Particle(FireworksOverlay r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Particle(FireworksOverlay r1, FireworksOverlayIA r2) {
            this(r1);
        }

        private void draw(Canvas r8) {
            byte r0 = this.type;
            if (r0 != 0) goto L7;
            r8.drawCircle(this.x, this.y, AndroidUtilities.dp(this.typeSize), FireworksOverlay.m10669$$Nest$sfgetpaint()[this.colorType]);
            return;
        L7:
            if (r0 != 1) goto L11;
            FireworksOverlay.m10664$$Nest$fgetrect(this.this$0).set(this.x - AndroidUtilities.dp(this.typeSize), this.y - AndroidUtilities.dp(2.0f), this.x + AndroidUtilities.dp(this.typeSize), this.y + AndroidUtilities.dp(2.0f));
            r8.save();
            r8.rotate(this.rotation, FireworksOverlay.m10664$$Nest$fgetrect(this.this$0).centerX(), FireworksOverlay.m10664$$Nest$fgetrect(this.this$0).centerY());
            r8.drawRoundRect(FireworksOverlay.m10664$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), FireworksOverlay.m10669$$Nest$sfgetpaint()[this.colorType]);
            r8.restore();
            return;
        L11:
            if (r0 == 2) goto L13;
            return;
        L13:
            if (FireworksOverlay.m10670$$Nest$sfgetstarsDrawable() == null) goto L15;
            Drawable r02 = FireworksOverlay.m10670$$Nest$sfgetstarsDrawable()[this.colorType];
        L17:
            if (FireworksOverlay.m10668$$Nest$sfgetheartDrawable() == null) goto L19;
            r02 = FireworksOverlay.m10668$$Nest$sfgetheartDrawable()[this.colorType];
        L19:
            if (r02 == null) goto L23;
            int r2 = r02.getIntrinsicWidth() / 2;
            int r3 = r02.getIntrinsicHeight() / 2;
            float r1 = this.x;
            float r5 = this.y;
            r02.setBounds(((int) r1) - r2, ((int) r5) - r3, ((int) r1) + r2, ((int) r5) + r3);
            r8.save();
            r8.rotate(this.rotation, this.x, this.y);
            byte r12 = this.typeSize;
            r8.scale(r12 / 6.0f, r12 / 6.0f, this.x, this.y);
            r02.draw(r8);
            r8.restore();
            return;
        L23:
            return;
        L15:
            r02 = null;
            goto L17
        }

        private boolean update(int r10) {
            float r102 = r10 / 16.0f;
            float r0 = this.x;
            float r1 = this.moveX;
            this.x = r0 + (r1 * r102);
            this.y += this.moveY * r102;
            if (this.xFinished == 0) goto L13;
            float r02 = AndroidUtilities.dp(1.0f) * 0.5f;
            byte r12 = this.xFinished;
            float r6 = this.moveX;
            if (r12 != 1) goto L9;
            float r62 = r6 + ((r02 * r102) * 0.05f);
            this.moveX = r62;
            if (r62 < r02) goto L24;
            this.xFinished = 2;
        L24:
            float r03 = (-AndroidUtilities.dp(1.0f)) / 2.0f;
            float r13 = this.moveY;
            if (r13 >= r03) goto L27;
            boolean r3 = true;
        L29:
            if (r13 <= r03) goto L31;
            this.moveY = r13 + (((AndroidUtilities.dp(1.0f) / 3.0f) * r102) * FireworksOverlay.m10665$$Nest$fgetspeedCoef(this.this$0));
        L32:
            if (r3 == true) goto L34;
        L36:
            byte r04 = this.type;
            if (r04 == 1) goto L39;
            if (r04 == 2) goto L39;
        L43:
            if (this.y < FireworksOverlay.m10667$$Nest$mgetHeightForAnimation(this.this$0)) goto L45;
            return true;
        L45:
            return false;
        L39:
            short r103 = (short) (this.rotation + (r102 * 10.0f));
            this.rotation = r103;
            if (r103 <= 360) goto L43;
            this.rotation = (short) (r103 - 360);
            goto L43
        L34:
            if (this.moveY <= r03) goto L36;
            FireworksOverlay r05 = this.this$0;
            FireworksOverlay.m10666$$Nest$fputfallingDownCount(r05, FireworksOverlay.m10663$$Nest$fgetfallingDownCount(r05) + 1);
            goto L36
        L31:
            this.moveY = r13 + ((AndroidUtilities.dp(1.0f) / 3.0f) * r102);
            goto L32
        L27:
            r3 = false;
            goto L29
        L9:
            float r63 = r6 - ((r02 * r102) * 0.05f);
            this.moveX = r63;
            if (r63 > (-r02)) goto L24;
            this.xFinished = 1;
            goto L24
        L13:
            if (this.side != 0) goto L20;
            if (r1 <= 0.0f) goto L24;
            float r14 = r1 - (0.05f * r102);
            this.moveX = r14;
            if (r14 > 0.0f) goto L24;
            this.moveX = 0.0f;
            this.xFinished = this.finishedStart;
            goto L24
        L20:
            if (r1 >= 0.0f) goto L24;
            float r15 = r1 + (0.05f * r102);
            this.moveX = r15;
            if (r15 < 0.0f) goto L24;
            this.moveX = 0.0f;
            this.xFinished = this.finishedStart;
            goto L24
        }
    }

    public static /* synthetic */ void $r8$lambda$irFu7c7PyG2nFXe0BMExaTpNu_M(FireworksOverlay r0) {
        r0.lambda$onDraw$0();
    }

    /* renamed from: -$$Nest$fgetfallingDownCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10663$$Nest$fgetfallingDownCount(FireworksOverlay r0) {
        return r0.fallingDownCount;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m10664$$Nest$fgetrect(FireworksOverlay r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgetspeedCoef, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10665$$Nest$fgetspeedCoef(FireworksOverlay r0) {
        return r0.speedCoef;
    }

    /* renamed from: -$$Nest$fputfallingDownCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10666$$Nest$fputfallingDownCount(FireworksOverlay r0, int r1) {
        r0.fallingDownCount = r1;
    }

    /* renamed from: -$$Nest$mgetHeightForAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10667$$Nest$mgetHeightForAnimation(FireworksOverlay r0) {
        return r0.getHeightForAnimation();
    }

    /* renamed from: -$$Nest$sfgetheartDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable[] m10668$$Nest$sfgetheartDrawable() {
        return heartDrawable;
    }

    /* renamed from: -$$Nest$sfgetpaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint[] m10669$$Nest$sfgetpaint() {
        return paint;
    }

    /* renamed from: -$$Nest$sfgetstarsDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable[] m10670$$Nest$sfgetstarsDrawable() {
        return starsDrawable;
    }

    static {
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L5;
        int r0 = 50;
    L6:
        particlesCount = r0;
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L9;
        int r02 = 20;
    L10:
        fallParticlesCount = r02;
        int[] r03 = {-13845272, -6421296, -79102, -187561, -14185218, -10897300};
        colors = r03;
        heartColors = new int[]{-1944197, -10498574, -9623, -2399389, -1870160};
        starsColors = new int[]{-14778113, -15677815, -42601, -26844, -13639175};
        paint = new Paint[r03.length];
        int r04 = 0;
    L11:
        Paint[] r1 = paint;
        if (r04 >= r1.length) goto L14;
        r1[r04] = new Paint(1);
        paint[r04].setColor(colors[r04]);
        r04 = r04 + 1;
        goto L11
    L14:
        return;
    L9:
        r02 = 30;
        goto L10
    L5:
        r0 = 60;
        goto L6
    }

    public FireworksOverlay(Context r3) {
        super(r3);
        this.rect = new RectF();
        this.speedCoef = 1.0f;
        this.particles = new ArrayList(particlesCount + fallParticlesCount);
    }

    private Particle createParticle(boolean r8) {
        Particle r0 = new Particle(this, null);
        byte r1 = (byte) Utilities.random.nextInt(2);     // Catch: Exception -> L7
        r0.type = r1;     // Catch: Exception -> L7
        if (this.isFebruary14 == false) goto L10;
        if (r1 != 0) goto L10;
        r0.type = 2;     // Catch: Exception -> L7
        r0.colorType = (byte) Utilities.random.nextInt(heartColors.length);     // Catch: Exception -> L7
    L15:
        r0.side = (byte) Utilities.random.nextInt(2);     // Catch: Exception -> L7
        r0.finishedStart = (byte) (Utilities.random.nextInt(2) + 1);     // Catch: Exception -> L7
        byte r12 = r0.type;     // Catch: Exception -> L7
        if (r12 == 0) goto L20;
        if (r12 == 2) goto L20;
        r0.typeSize = (byte) ((Utilities.random.nextFloat() * 4.0f) + 4.0f);     // Catch: Exception -> L7
    L22:
        if (r8 == false) goto L25;
        r0.y = ((-Utilities.random.nextFloat()) * getHeightForAnimation()) * 1.2f;     // Catch: Exception -> L7
        r0.x = AndroidUtilities.dp(5.0f) + Utilities.random.nextInt(Math.max(1, getWidthForAnimation() - AndroidUtilities.dp(10.0f)));     // Catch: Exception -> L7
        r0.xFinished = r0.finishedStart;     // Catch: Exception -> L7
        return r0;
    L25:
        int r82 = AndroidUtilities.dp(Utilities.random.nextInt(10) + 4);     // Catch: Exception -> L7
        int r5 = getHeightForAnimation() / 4;     // Catch: Exception -> L7
        if (r0.side != 0) goto L28;
        r0.x = -r82;     // Catch: Exception -> L7
    L30:
        if (r0.side != 0) goto L32;
        int r7 = 1;
    L33:
        r0.moveX = r7 * (AndroidUtilities.dp(1.2f) + (Utilities.random.nextFloat() * AndroidUtilities.dp(4.0f)));     // Catch: Exception -> L7
        r0.moveY = -(AndroidUtilities.dp(4.0f) + (Utilities.random.nextFloat() * AndroidUtilities.dp(4.0f)));     // Catch: Exception -> L7
        r0.y = (r5 / 2) + Utilities.random.nextInt(Math.max(1, r5 * 2));     // Catch: Exception -> L7
        return r0;
    L32:
        r7 = -1;
        goto L33
    L28:
        r0.x = getWidthForAnimation() + r82;     // Catch: Exception -> L7
    L20:
        r0.typeSize = (byte) ((Utilities.random.nextFloat() * 2.0f) + 4.0f);     // Catch: Exception -> L7
    L10:
        if (this.withStars == true) goto L12;
    L14:
        r0.colorType = (byte) Utilities.random.nextInt(colors.length);     // Catch: Exception -> L7
        goto L15
    L12:
        if (Utilities.random.nextBoolean() == false) goto L14;
        r0.type = 2;     // Catch: Exception -> L7
        r0.colorType = (byte) Utilities.random.nextInt(starsColors.length);     // Catch: Exception -> L7
    L7:
        e = move-exception;
        FileLog.e(e);
        return r0;
    }

    private int getHeightForAnimation() {
        if (getMeasuredHeight() != 0) goto L7;
        return ((View) getParent()).getHeight();
    L7:
        return getMeasuredHeight();
    }

    private int getWidthForAnimation() {
        if (getMeasuredWidth() != 0) goto L7;
        return ((View) getParent()).getWidth();
    L7:
        return getMeasuredWidth();
    }

    private /* synthetic */ void lambda$onDraw$0() {
        if (this.started == true) goto L6;
        setLayerType(0, null);
        return;
    }

    private void loadHeartDrawables() {
        if (heartDrawable != null) goto L9;
        heartDrawable = new Drawable[heartColors.length];
        int r4 = 0;
    L6:
        Drawable[] r0 = heartDrawable;
        if (r4 >= r0.length) goto L11;
        r0[r4] = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.heart_confetti).mutate();
        heartDrawable[r4].setColorFilter(new PorterDuffColorFilter(heartColors[r4], PorterDuff.Mode.MULTIPLY));
        r4 = r4 + 1;
        goto L6
    L11:
        return;
    }

    private void loadStarsDrawables() {
        if (starsDrawable != null) goto L9;
        starsDrawable = new Drawable[starsColors.length];
        int r4 = 0;
    L6:
        Drawable[] r0 = starsDrawable;
        if (r4 >= r0.length) goto L11;
        r0[r4] = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.msg_settings_premium).mutate();
        starsDrawable[r4].setColorFilter(new PorterDuffColorFilter(starsColors[r4], PorterDuff.Mode.MULTIPLY));
        r4 = r4 + 1;
        goto L6
    L11:
        return;
    }

    private void startFall() {
        if (this.startedFall == true) goto L9;
        this.startedFall = true;
        int r1 = 0;
    L7:
        if (r1 >= fallParticlesCount) goto L11;
        this.particles.add(createParticle(true));
        r1 = r1 + 1;
        goto L7
    L11:
        return;
    }

    public boolean isStarted() {
        return this.started;
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        long r0 = SystemClock.elapsedRealtime();
        int r2 = (int) (r0 - this.lastUpdateTime);
        this.lastUpdateTime = r0;
        if (r2 <= 18) goto L5;
        r2 = 16;
    L5:
        int r02 = this.particles.size();
        int r3 = 0;
    L6:
        if (r3 >= r02) goto L12;
        Particle r4 = this.particles.get(r3);
        Particle.m10671$$Nest$mdraw(r4, r6);
        if (Particle.m10672$$Nest$mupdate(r4, r2) == false) goto L10;
        this.particles.remove(r3);
        r3 = r3 - 1;
        r02 = r02 - 1;
    L10:
        r3 = r3 + 1;
        goto L6
    L12:
        if (this.fallingDownCount < (particlesCount / 2)) goto L19;
        if (this.speedCoef <= 0.2f) goto L19;
        startFall();
        float r62 = this.speedCoef - ((r2 / 16.0f) * 0.15f);
        this.speedCoef = r62;
        if (r62 >= 0.2f) goto L19;
        this.speedCoef = 0.2f;
    L19:
        if (this.particles.isEmpty() == true) goto L22;
        invalidate();
        return;
    L22:
        this.started = false;
        AndroidUtilities.runOnUIThread(new FireworksOverlay$$ExternalSyntheticLambda0(this));
        onStop();
    }

    public void onStop() {
    }

    public void start() {
        start(false);
    }

    public void start(boolean r7) {
        this.withStars = r7;
        setLayerType(2, null);
        boolean r0 = true;
        this.started = true;
        this.startedFall = false;
        this.fallingDownCount = 0;
        this.speedCoef = 1.0f;
        Calendar r3 = Calendar.getInstance();
        r3.setTimeInMillis(System.currentTimeMillis());
        int r4 = r3.get(5);
        if (r3.get(2) == 1) goto L5;
    L9:
        r0 = false;
    L10:
        this.isFebruary14 = r0;
        if (r0 == false) goto L13;
        loadHeartDrawables();
    L15:
        int r72 = particlesCount;
        int r73 = Utilities.clamp(r72 - this.particles.size(), r72, r72 / 3);
        int r02 = 0;
    L16:
        if (r02 >= r73) goto L18;
        this.particles.add(createParticle(false));
        r02 = r02 + 1;
        goto L16
    L18:
        invalidate();
        return;
    L13:
        if (r7 == false) goto L15;
        loadStarsDrawables();
        goto L15
    L5:
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L10;
        if (r4 != 14) goto L9;
        goto L9
    }
}
