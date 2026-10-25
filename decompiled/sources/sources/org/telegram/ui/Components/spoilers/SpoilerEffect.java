package org.telegram.ui.Components.spoilers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.google.android.gms.cast.CastStatusCodes;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.spoilers.SpoilerEffectBitmapFactory;

/* loaded from: classes3.dex */
public class SpoilerEffect extends Drawable {
    public static final float[] ALPHAS = null;
    public static final int MAX_PARTICLES_PER_ENTITY = 0;
    public static final int PARTICLES_PER_CHARACTER = 0;
    private static WeakHashMap<Layout, ArrayList<RectF>> lazyLayoutLines;
    private static final float[][] particlePoints = null;
    private static final Path tempPath = null;
    private static Paint xRefPaint;
    private int bitmapSize;
    private final RectF boundsFWithInset;
    private ColorFilter colorFilter;
    private final float[] halfStrokeWidths;
    public boolean insideQuote;
    private boolean invalidateParent;
    private boolean isLowDevice;
    private int lastColor;
    private long lastDrawTime;
    private int mAlpha;
    private View mParent;
    private int maxParticles;
    private Runnable onRippleEndCallback;
    private final Paint[] particlePaints;
    private final float[] particleRands;
    private final ArrayList<Particle> particles;
    private final Stack<Particle> particlesPool;
    private final int[] renderCount;
    private boolean reverseAnimator;
    private ValueAnimator rippleAnimator;
    private TimeInterpolator rippleInterpolator;
    private float rippleMaxRadius;
    private float rippleProgress;
    private float rippleX;
    private float rippleY;
    private boolean shouldInvalidateColor;
    private boolean suppressUpdates;
    private RectF visibleRect;

    /* renamed from: org.telegram.ui.Components.spoilers.SpoilerEffect$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ SpoilerEffect this$0;

        public AnonymousClass1(SpoilerEffect r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            Iterator r42 = SpoilerEffect.m13770$$Nest$fgetparticles(this.this$0).iterator();
        L4:
            if (r42.hasNext() == false) goto L10;
            Particle r0 = (Particle) r42.next();
            if (SpoilerEffect.m13771$$Nest$fgetparticlesPool(this.this$0).size() >= SpoilerEffect.m13768$$Nest$fgetmaxParticles(this.this$0)) goto L8;
            SpoilerEffect.m13771$$Nest$fgetparticlesPool(this.this$0).push(r0);
        L8:
            r42.remove();
            goto L4
        L10:
            if (SpoilerEffect.m13769$$Nest$fgetonRippleEndCallback(this.this$0) == null) goto L12;
            SpoilerEffect.m13769$$Nest$fgetonRippleEndCallback(this.this$0).run();
            SpoilerEffect.m13772$$Nest$fputonRippleEndCallback(this.this$0, null);
        L12:
            SpoilerEffect.m13773$$Nest$fputrippleAnimator(this.this$0, null);
            this.this$0.invalidateSelf();
        }
    }

    /* renamed from: org.telegram.ui.Components.spoilers.SpoilerEffect$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends Path {
        final /* synthetic */ int val$mostleft;
        final /* synthetic */ int val$mostright;
        final /* synthetic */ ArrayList val$quoteBlocks;
        final /* synthetic */ List val$spoilers;
        final /* synthetic */ Stack val$spoilersPool;
        final /* synthetic */ Layout val$textLayout;
        final /* synthetic */ View val$v;

        public AnonymousClass2(View r1, Layout r2, Stack r3, List r4, int r5, int r6, ArrayList r7) {
            this.val$v = r1;
            this.val$textLayout = r2;
            this.val$spoilersPool = r3;
            this.val$spoilers = r4;
            this.val$mostleft = r5;
            this.val$mostright = r6;
            this.val$quoteBlocks = r7;
        }

        @Override // android.graphics.Path
        public void addRect(float r12, float r13, float r14, float r15, Path.Direction r16) {
            SpoilerEffect.m13774$$Nest$smaddSpoilerRangeInternal(this.val$v, this.val$textLayout, r12, r13, r14, r15, this.val$spoilersPool, this.val$spoilers, this.val$mostleft, this.val$mostright, this.val$quoteBlocks);
        }
    }

    /* renamed from: org.telegram.ui.Components.spoilers.SpoilerEffect$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends ReplacementSpan {
        final /* synthetic */ ReplacementSpan val$e;

        public AnonymousClass3(ReplacementSpan r1) {
            this.val$e = r1;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return this.val$e.getSize(r1, r2, r3, r4, r5);
        }
    }

    /* loaded from: classes7.dex */
    public static class Particle {
        private int alpha;
        private float currentTime;
        private float lifeTime;
        private float vecX;
        private float vecY;
        private float velocity;
        private float x;
        private float y;

        /* renamed from: -$$Nest$fgetalpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13775$$Nest$fgetalpha(Particle r0) {
            return r0.alpha;
        }

        /* renamed from: -$$Nest$fgetcurrentTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13776$$Nest$fgetcurrentTime(Particle r0) {
            return r0.currentTime;
        }

        /* renamed from: -$$Nest$fgetlifeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13777$$Nest$fgetlifeTime(Particle r0) {
            return r0.lifeTime;
        }

        /* renamed from: -$$Nest$fgetvecX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13778$$Nest$fgetvecX(Particle r0) {
            return r0.vecX;
        }

        /* renamed from: -$$Nest$fgetvecY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13779$$Nest$fgetvecY(Particle r0) {
            return r0.vecY;
        }

        /* renamed from: -$$Nest$fgetvelocity, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13780$$Nest$fgetvelocity(Particle r0) {
            return r0.velocity;
        }

        /* renamed from: -$$Nest$fgetx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13781$$Nest$fgetx(Particle r0) {
            return r0.x;
        }

        /* renamed from: -$$Nest$fgety, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13782$$Nest$fgety(Particle r0) {
            return r0.y;
        }

        /* renamed from: -$$Nest$fputalpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13783$$Nest$fputalpha(Particle r0, int r1) {
            r0.alpha = r1;
        }

        /* renamed from: -$$Nest$fputcurrentTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13784$$Nest$fputcurrentTime(Particle r0, float r1) {
            r0.currentTime = r1;
        }

        /* renamed from: -$$Nest$fputlifeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13785$$Nest$fputlifeTime(Particle r0, float r1) {
            r0.lifeTime = r1;
        }

        /* renamed from: -$$Nest$fputvecX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13786$$Nest$fputvecX(Particle r0, float r1) {
            r0.vecX = r1;
        }

        /* renamed from: -$$Nest$fputvecY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13787$$Nest$fputvecY(Particle r0, float r1) {
            r0.vecY = r1;
        }

        /* renamed from: -$$Nest$fputvelocity, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13788$$Nest$fputvelocity(Particle r0, float r1) {
            r0.velocity = r1;
        }

        /* renamed from: -$$Nest$fputx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13789$$Nest$fputx(Particle r0, float r1) {
            r0.x = r1;
        }

        /* renamed from: -$$Nest$fputy, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13790$$Nest$fputy(Particle r0, float r1) {
            r0.y = r1;
        }

        private Particle() {
        }

        public /* synthetic */ Particle(SpoilerEffectIA r1) {
            this();
        }
    }

    public static /* synthetic */ float $r8$lambda$DlWWdeTSSSqeRn6DubqCDzjPfOU(float r0) {
        return r0;
    }

    public static /* synthetic */ void $r8$lambda$N8ytR3ySi21HT7mRWRap1udeyGg(SpoilerEffect r0, int r1, ValueAnimator r2) {
        r0.lambda$startRipple$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetmaxParticles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13768$$Nest$fgetmaxParticles(SpoilerEffect r0) {
        return r0.maxParticles;
    }

    /* renamed from: -$$Nest$fgetonRippleEndCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m13769$$Nest$fgetonRippleEndCallback(SpoilerEffect r0) {
        return r0.onRippleEndCallback;
    }

    /* renamed from: -$$Nest$fgetparticles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13770$$Nest$fgetparticles(SpoilerEffect r0) {
        return r0.particles;
    }

    /* renamed from: -$$Nest$fgetparticlesPool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Stack m13771$$Nest$fgetparticlesPool(SpoilerEffect r0) {
        return r0.particlesPool;
    }

    /* renamed from: -$$Nest$fputonRippleEndCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13772$$Nest$fputonRippleEndCallback(SpoilerEffect r0, Runnable r1) {
        r0.onRippleEndCallback = r1;
    }

    /* renamed from: -$$Nest$fputrippleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13773$$Nest$fputrippleAnimator(SpoilerEffect r0, ValueAnimator r1) {
        r0.rippleAnimator = r1;
    }

    /* renamed from: -$$Nest$smaddSpoilerRangeInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13774$$Nest$smaddSpoilerRangeInternal(View r0, Layout r1, float r2, float r3, float r4, float r5, Stack r6, List r7, int r8, int r9, ArrayList r10) {
        addSpoilerRangeInternal(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    static {
        int r0 = measureMaxParticlesCount();
        MAX_PARTICLES_PER_ENTITY = r0;
        PARTICLES_PER_CHARACTER = measureParticlesPerCharacter();
        float[] r1 = {0.3f, 0.6f, 1.0f};
        ALPHAS = r1;
        particlePoints = (float[][]) Array.newInstance(Float.TYPE, new int[]{r1.length, r0 * 5});
        tempPath = new Path();
    }

    public SpoilerEffect() {
        float[] r0 = ALPHAS;
        this.particlePaints = new Paint[r0.length];
        this.halfStrokeWidths = new float[r0.length];
        this.particlesPool = new Stack();
        this.particleRands = new float[14];
        this.renderCount = new int[r0.length];
        this.particles = new ArrayList();
        this.rippleProgress = -1.0f;
        this.mAlpha = 255;
        this.rippleInterpolator = new SpoilerEffect$$ExternalSyntheticLambda0();
        this.boundsFWithInset = new RectF();
        int r1 = 0;
    L4:
        if (r1 >= ALPHAS.length) goto L11;
        this.particlePaints[r1] = new Paint();
        Paint[] r2 = this.particlePaints;
        if (r1 != 0) goto L8;
        r2[r1].setStrokeWidth(AndroidUtilities.dp(1.4f));
        this.particlePaints[r1].setStyle(Paint.Style.STROKE);
        this.particlePaints[r1].setStrokeCap(Paint.Cap.ROUND);
    L9:
        this.halfStrokeWidths[r1] = this.particlePaints[r1].getStrokeWidth() * 0.5f;
        r1 = r1 + 1;
        goto L4
    L8:
        r2[r1].setStrokeWidth(AndroidUtilities.dp(1.2f));
        this.particlePaints[r1].setStyle(Paint.Style.STROKE);
        this.particlePaints[r1].setStrokeCap(Paint.Cap.ROUND);
        goto L9
    L11:
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L13;
        boolean r12 = true;
    L14:
        this.isLowDevice = r12;
        setColor(0);
        return;
    L13:
        r12 = false;
        goto L14
    }

    private static void addSpoilerRangeInternal(View r4, Layout r5, float r6, float r7, float r8, float r9, Stack<SpoilerEffect> r10, List<SpoilerEffect> r11, int r12, int r13, ArrayList<QuoteSpan.Block> r14) {
        int r0 = 0;
        if (r10 != null) goto L5;
    L8:
        SpoilerEffect r102 = new SpoilerEffect();
    L9:
        r102.insideQuote = false;
        if (r14 == null) goto L20;
        float r1 = (r7 + r9) / 2.0f;
    L13:
        if (r0 >= r14.size()) goto L20;
        QuoteSpan.Block r2 = r14.get(r0);
        if (r1 < r2.top) goto L19;
        if (r1 > r2.bottom) goto L19;
        r102.insideQuote = true;
    L19:
        r0 = r0 + 1;
    L20:
        r102.setRippleProgress(-1.0f);
        int r62 = (int) Math.max(r6, r12);
        int r72 = (int) r7;
        if (r13 > 0) goto L23;
        float r122 = 2.1474836E9f;
    L24:
        r102.setBounds(r62, r72, (int) Math.min(r8, r122), (int) r9);
        r102.setColor(r5.getPaint().getColor());
        r102.setRippleInterpolator(Easings.easeInQuad);
        r102.updateMaxParticles();
        if (r4 == null) goto L27;
        r102.setParentView(r4);
    L27:
        r11.add(r102);
        return;
    L23:
        r122 = r13;
        goto L24
    L5:
        if (r10.isEmpty() == true) goto L8;
        r102 = r10.remove(0);
        goto L9
    }

    private static void addSpoilerRangesInternal(View r8, Layout r9, int r10, int r11, int r12, int r13, Stack<SpoilerEffect> r14, List<SpoilerEffect> r15, ArrayList<QuoteSpan.Block> r16) {
        r9.getSelectionPath(r12, r13, new AnonymousClass2(r8, r9, r14, r15, r10, r11, r16));
    }

    public static void addSpoilers(View r14, Layout r15, int r16, int r17, Spanned r18, Stack<SpoilerEffect> r19, List<SpoilerEffect> r20, ArrayList<QuoteSpan.Block> r21) {
        if (r15 == null) goto L32;
        TextStyleSpan[] r10 = (TextStyleSpan[]) r18.getSpans(0, r15.getText().length(), TextStyleSpan.class);
        int r11 = 0;
    L7:
        if (r11 >= Math.min(100, r10.length)) goto L22;
        if (r10[r11].isSpoiler() == false) goto L20;
        int r4 = r18.getSpanStart(r10[r11]);
        int r5 = r18.getSpanEnd(r10[r11]);
        if (r16 != (-1)) goto L17;
        if (r17 != (-1)) goto L17;
        int r0 = r15.getLineForOffset(r4);
        int r2 = r15.getLineForOffset(r5);
        int r3 = Integer.MAX_VALUE;
        int r6 = Integer.MIN_VALUE;
    L14:
        if (r0 > r2) goto L16;
        r3 = Math.min(r3, (int) r15.getLineLeft(r0));
        r6 = Math.max(r6, (int) r15.getLineRight(r0));
        r0 = r0 + 1;
        goto L14
    L16:
        int r22 = r3;
        int r32 = r6;
        View r02 = r14;
        Layout r1 = r15;
        List<SpoilerEffect> r7 = r20;
        ArrayList<QuoteSpan.Block> r8 = r21;
        Stack<SpoilerEffect> r62 = r19;
    L18:
        addSpoilerRangesInternal(r02, r1, r22, r32, r4, r5, r62, r7, r8);
    L17:
        r22 = r16;
        r32 = r17;
        r02 = r14;
        r1 = r15;
        r62 = r19;
        r7 = r20;
        r8 = r21;
    L20:
        r11 = r11 + 1;
        goto L7
    L22:
        if ((r14 instanceof TextView) == false) goto L30;
        if (r19 == null) goto L31;
        r19.clear();
        return;
    L31:
        return;
    L30:
        return;
    }

    public static void addSpoilers(View r9, Layout r10, int r11, int r12, Stack<SpoilerEffect> r13, List<SpoilerEffect> r14) {
        if ((r10.getText() instanceof Spanned) == false) goto L6;
        addSpoilers(r9, r10, r11, r12, (Spanned) r10.getText(), r13, r14, null);
        return;
    }

    public static void addSpoilers(View r8, Layout r9, Spanned r10, Stack<SpoilerEffect> r11, List<SpoilerEffect> r12) {
        if (r9 != null) goto L4;
        return;
    L4:
        addSpoilers(r8, r9, -1, -1, r10, r11, r12, null);
    }

    public static void addSpoilers(View r1, Layout r2, Stack<SpoilerEffect> r3, List<SpoilerEffect> r4) {
        if ((r2.getText() instanceof Spanned) == false) goto L6;
        addSpoilers(r1, r2, (Spanned) r2.getText(), r3, r4);
        return;
    }

    public static void addSpoilers(TextView r9, Stack<SpoilerEffect> r10, List<SpoilerEffect> r11) {
        int r0 = r9.getMeasuredWidth();
        Layout r2 = r9.getLayout();
        if (r0 <= 0) goto L5;
    L4:
        int r4 = r0;
        addSpoilers(r9, r2, 0, r4, (Spanned) r9.getText(), r10, r11, null);
        return;
    L5:
        r0 = -2;
        goto L4
    }

    public static void addSpoilers(TextView r9, Stack<SpoilerEffect> r10, List<SpoilerEffect> r11, ArrayList<QuoteSpan.Block> r12) {
        int r0 = r9.getMeasuredWidth();
        Layout r2 = r9.getLayout();
        if (r0 <= 0) goto L5;
    L4:
        int r4 = r0;
        addSpoilers(r9, r2, 0, r4, (Spanned) r9.getText(), r10, r11, r12);
        return;
    L5:
        r0 = -2;
        goto L4
    }

    public static void clipOutCanvas(Canvas r8, List<SpoilerEffect> r9) {
        if (r9.isEmpty() == false) goto L5;
        return;
    L5:
        tempPath.rewind();
        int r0 = 0;
    L7:
        if (r0 >= r9.size()) goto L9;
        Rect r1 = r9.get(r0).getBounds();
        tempPath.addRect(r1.left, r1.top, r1.right, r1.bottom, Path.Direction.CW);
        r0 = r0 + 1;
        goto L7
    L9:
        r8.clipPath(tempPath, Region.Op.DIFFERENCE);
    }

    private /* synthetic */ void lambda$startRipple$1(int r2, ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.rippleProgress = r32;
        setAlpha((int) (r2 * (1.0f - r32)));
        this.shouldInvalidateColor = true;
        invalidateSelf();
    }

    public static void layoutDrawMaybe(Layout r10, Canvas r11) {
        if ((r11 instanceof SizeNotifierFrameLayout.SimplerCanvas) == false) goto L18;
        int r0 = r10.getPaint().getAlpha();
        r10.getPaint().setAlpha((int) (r0 * 0.4f));
        if (lazyLayoutLines != null) goto L7;
        lazyLayoutLines = new WeakHashMap();
    L7:
        ArrayList<RectF> r1 = lazyLayoutLines.get(r10);
        int r2 = 0;
        if (r1 != null) goto L14;
        r1 = new ArrayList();
        int r3 = r10.getLineCount();
        int r4 = 0;
    L10:
        if (r4 >= r3) goto L12;
        r1.add(new RectF(r10.getLineLeft(r4), r10.getLineTop(r4), r10.getLineRight(r4), r10.getLineBottom(r4)));
        r4 = r4 + 1;
        goto L10
    L12:
        lazyLayoutLines.put(r10, r1);
    L14:
        if (r2 >= r1.size()) goto L16;
        r11.drawRect(r1.get(r2), r10.getPaint());
        r2 = r2 + 1;
        goto L14
    L16:
        r10.getPaint().setAlpha(r0);
        return;
    L18:
        r10.draw(r11);
    }

    private static int measureMaxParticlesCount() {
        if (SharedConfig.getDevicePerformanceClass() == 2) goto L6;
        return 100;
    L6:
        return 150;
    }

    private static int measureParticlesPerCharacter() {
        if (SharedConfig.getDevicePerformanceClass() == 2) goto L6;
        return 10;
    L6:
        return 30;
    }

    @SuppressLint({"WrongConstant"})
    public static void renderWithRipple(View r22, boolean r23, int r24, int r25, AtomicReference<Layout> r26, int r27, Layout r28, List<SpoilerEffect> r29, Canvas r30, boolean r31) {
        if (r29 != null) goto L5;
    L87:
        layoutDrawMaybe(r28, r30);
        return;
    L5:
        if (r29.isEmpty() == true) goto L87;
        Layout r6 = r26.get();
        int r13 = 0;
        if (r6 != null) goto L10;
    L15:
        SpannableStringBuilder r15 = new SpannableStringBuilder(r28.getText());
        if ((r28.getText() instanceof Spanned) == false) goto L29;
        Spanned r62 = (Spanned) r28.getText();
        TextStyleSpan[] r7 = (TextStyleSpan[]) r62.getSpans(0, r62.length(), TextStyleSpan.class);
        int r8 = 0;
    L19:
        if (r8 >= Math.min(100, r7.length)) goto L29;
        TextStyleSpan r9 = r7[r8];
        if (r9.isSpoiler() == false) goto L26;
        int r10 = r62.getSpanStart(r9);
        int r11 = r62.getSpanEnd(r9);
        ReplacementSpan[] r14 = (ReplacementSpan[]) r62.getSpans(r10, r11, ReplacementSpan.class);
        int r12 = r14.length;
    L23:
        if (r13 >= r12) goto L25;
        TextStyleSpan[] r16 = r7;
        ReplacementSpan r72 = r14[r13];
        r15.setSpan(new AnonymousClass3(r72), r62.getSpanStart(r72), r62.getSpanEnd(r72), r62.getSpanFlags(r9));
        r15.removeSpan(r72);
        r13 = r13 + 1;
        r7 = r16;
        r8 = r8;
        r12 = r12;
        r14 = r14;
        goto L23
    L25:
        TextStyleSpan[] r162 = r7;
        int r17 = r8;
        r15.setSpan(new ForegroundColorSpan(0), r10, r11, r62.getSpanFlags(r9));
        r15.removeSpan(r9);
    L27:
        r8 = r17 + 1;
        r7 = r162;
        r13 = 0;
        goto L19
    L26:
        r162 = r7;
        r17 = r8;
    L29:
        if (r27 != 1) goto L32;
        r6 = new StaticLayout(r15, r28.getPaint(), r28.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, AndroidUtilities.dp(1.66f), false);
    L33:
        r26.set(r6);
    L35:
        if (r29.isEmpty() == true) goto L37;
        r30.save();
        r30.translate(0.0f, r25);
        r6.draw(r30);
        r30.restore();
    L39:
        if (r29.isEmpty() == true) goto L97;
        tempPath.rewind();
        Iterator<SpoilerEffect> r63 = r29.iterator();
    L42:
        if (r63.hasNext() == false) goto L44;
        Rect r73 = r63.next().getBounds();
        tempPath.addRect(r73.left, r73.top, r73.right, r73.bottom, Path.Direction.CW);
        goto L42
    L44:
        int r82 = 0;
        if (r29.isEmpty() == true) goto L53;
        if (r29.get(0).rippleProgress == (-1.0f)) goto L53;
        r30.save();
        Path r64 = tempPath;
        r30.clipPath(r64);
        r64.rewind();
        if (r29.isEmpty() == true) goto L51;
        r29.get(0).getRipplePath(r64);
    L51:
        r30.clipPath(r64);
        r30.translate(0.0f, -r22.getPaddingTop());
        layoutDrawMaybe(r28, r30);
        r30.restore();
        r82 = 0;
    L53:
        if (r29.get(r82).rippleProgress == (-1.0f)) goto L55;
        boolean r132 = true;
    L56:
        if (r132 == false) goto L63;
        int r65 = r22.getMeasuredWidth();
        if (r31 == true) goto L60;
    L62:
        r30.saveLayer(0.0f, 0.0f, r65, r22.getMeasuredHeight(), null, 31);
    L64:
        r30.translate(0.0f, -r22.getPaddingTop());
        Iterator<SpoilerEffect> r66 = r29.iterator();
    L66:
        if (r66.hasNext() == false) goto L80;
        SpoilerEffect r74 = r66.next();
        r74.setInvalidateParent(r23);
        if (r74.getParentView() == r22) goto L71;
        r74.setParentView(r22);
    L71:
        if (r74.shouldInvalidateColor() == true) goto L73;
        r74.setColor(r24);
    L79:
        r74.draw(r30);
        goto L66
    L73:
        if (r27 != 1) goto L76;
        TextPaint r92 = r28.getPaint();
    L77:
        r74.setColor(ColorUtils.blendARGB(r24, r92.getColor(), Math.max(0.0f, r74.getRippleProgress())));
        goto L79
    L76:
        r92 = Theme.chat_msgTextPaint;
        goto L77
    L80:
        if (r132 == false) goto L85;
        Path r0 = tempPath;
        r0.rewind();
        r29.get(0).getRipplePath(r0);
        if (xRefPaint != null) goto L84;
        Paint r1 = new Paint(1);
        xRefPaint = r1;
        r1.setColor(-16777216);
        xRefPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    L84:
        r30.drawPath(r0, xRefPaint);
    L85:
        r30.restore();
        return;
    L60:
        if ((r22.getParent() instanceof View) == false) goto L62;
        r65 = ((View) r22.getParent()).getMeasuredWidth();
        goto L62
    L63:
        r30.save();
        goto L64
    L55:
        r132 = false;
        goto L56
    L97:
        return;
    L37:
        layoutDrawMaybe(r28, r30);
        goto L39
    L32:
        r6 = StaticLayout.Builder.obtain(r15, 0, r15.length(), r28.getPaint(), r28.getWidth()).setBreakStrategy(1).setHyphenationFrequency(0).setAlignment(r28.getAlignment()).setLineSpacing(r28.getSpacingAdd(), r28.getSpacingMultiplier()).build();
        goto L33
    L10:
        if (r28.getText().toString().equals(r6.getText().toString()) == false) goto L15;
        if (r28.getWidth() != r6.getWidth()) goto L15;
        if (r28.getHeight() == r6.getHeight()) goto L35;
        goto L15
    }

    public void addPoints(SpoilerEffectBitmapFactory.PointsBuffer[] r31, Rect r32) {
        if (r31 == null) goto L107;
        int r3 = r31.length;
        float[] r4 = ALPHAS;
        if (r3 != r4.length) goto L120;
        long r5 = System.currentTimeMillis();
        int r33 = (int) Math.min(r5 - this.lastDrawTime, 34);
        this.lastDrawTime = r5;
        ArrayList<Particle> r52 = this.particles;
        Stack<Particle> r6 = this.particlesPool;
        int r7 = this.maxParticles;
        int r42 = r4.length;
        Rect r8 = getBounds();
        float r9 = r8.left;
        float r10 = r8.top;
        float r11 = r8.width();
        float r82 = r8.height();
        RectF r12 = this.boundsFWithInset;
        float r13 = r12.left;
        float r14 = r12.top;
        float r15 = r12.right;
        float r122 = r12.bottom;
        float r16 = AndroidUtilities.dpf2(1.0f);
        float r1 = r32.left - r16;
        float r17 = r32.top - r16;
        float r18 = r32.right + r16;
        float r2 = r32.bottom + r16;
        float r34 = r33;
        float r162 = r34 / 500.0f;
        int r19 = r52.size();
        int r22 = 0;
    L9:
        if (r22 >= r19) goto L34;
        int r23 = r19;
        Particle r110 = r52.get(r22);
        float r24 = r34;
        float r222 = r82;
        float r35 = Math.min(Particle.m13776$$Nest$fgetcurrentTime(r110) + r24, Particle.m13777$$Nest$fgetlifeTime(r110));
        Particle.m13784$$Nest$fputcurrentTime(r110, r35);
        float r83 = Particle.m13781$$Nest$fgetx(r110);
        float r25 = Particle.m13782$$Nest$fgety(r110);
        if (r83 >= r13) goto L13;
    L20:
        boolean r26 = true;
    L22:
        if (r35 >= Particle.m13777$$Nest$fgetlifeTime(r110)) goto L27;
        if (r26 == true) goto L27;
        float r36 = Particle.m13780$$Nest$fgetvelocity(r110) * r162;
        Particle.m13789$$Nest$fputx(r110, r83 + (Particle.m13778$$Nest$fgetvecX(r110) * r36));
        Particle.m13790$$Nest$fputy(r110, r25 + (Particle.m13779$$Nest$fgetvecY(r110) * r36));
        r19 = r23;
    L33:
        r22 = r22 + 1;
        r82 = r222;
        r34 = r24;
    L27:
        if (r6.size() >= r7) goto L29;
        r6.push(r110);
    L29:
        int r111 = r23 - 1;
        if (r22 == r111) goto L32;
        r52.set(r22, r52.get(r111));
    L32:
        r52.remove(r111);
        r19 = r23 - 1;
        r22 = r22 - 1;
        goto L33
    L13:
        if (r83 > r15) goto L20;
        if (r25 < r14) goto L20;
        if (r25 > r122) goto L20;
        r26 = false;
        goto L22
    L34:
        float r223 = r82;
        int r112 = r52.size();
        if (r112 >= r7) goto L64;
        int r72 = r7 - r112;
        float r84 = -1.0f;
        Arrays.fill(this.particleRands, 0, Math.min(r72, 14), -1.0f);
        int r113 = 0;
        int r27 = 0;
    L37:
        if (r113 >= r72) goto L63;
        float r37 = this.particleRands[r27];
        if (r37 != r84) goto L41;
        r37 = Utilities.fastRandom.nextFloat();
        this.particleRands[r27] = r37;
    L41:
        int r28 = r27 + 1;
        if (r28 != 14) goto L45;
        r28 = 0;
    L45:
        if (r6.isEmpty() == true) goto L47;
        int r242 = r113;
        Particle r85 = r6.pop();
    L48:
        int r114 = 0;
    L49:
        Particle.m13789$$Nest$fputx(r85, r9 + (Utilities.fastRandom.nextFloat() * r11));
        Particle.m13790$$Nest$fputy(r85, (Utilities.fastRandom.nextFloat() * r223) + r10);
        r114 = r114 + 1;
        if (Particle.m13781$$Nest$fgetx(r85) >= r13) goto L52;
    L57:
        int r252 = r28;
        if (r114 >= 4) goto L61;
        r28 = r252;
    L61:
        double r115 = ((r37 * 3.141592653589793d) * 2.0d) - 3.141592653589793d;
        Particle.m13786$$Nest$fputvecX(r85, (float) Math.cos(r115));
        Particle.m13787$$Nest$fputvecY(r85, (float) Math.sin(r115));
        Particle.m13784$$Nest$fputcurrentTime(r85, 0.0f);
        Particle.m13785$$Nest$fputlifeTime(r85, Utilities.fastRandom.nextInt(CastStatusCodes.AUTHENTICATION_FAILED) + 1000);
        Particle.m13788$$Nest$fputvelocity(r85, (r37 * 6.0f) + 4.0f);
        Particle.m13783$$Nest$fputalpha(r85, Utilities.fastRandom.nextInt(r42));
        r52.add(r85);
        r113 = r242 + 1;
        r27 = r252;
        r84 = -1.0f;
        goto L37
    L52:
        if (Particle.m13781$$Nest$fgetx(r85) > r15) goto L57;
        if (Particle.m13782$$Nest$fgety(r85) < r14) goto L57;
        if (Particle.m13782$$Nest$fgety(r85) > r122) goto L57;
        r252 = r28;
        goto L61
    L47:
        r242 = r113;
        r85 = new Particle(null);
        goto L48
    L63:
        r112 = r52.size();
    L64:
        int r29 = 0;
    L65:
        if (r29 >= r42) goto L67;
        this.renderCount[r29] = 0;
        r29 = r29 + 1;
        goto L65
    L67:
        int r210 = this.bitmapSize;
        int r38 = 0;
    L68:
        if (r38 >= r112) goto L104;
        Particle r62 = r52.get(r38);
        float r73 = Particle.m13781$$Nest$fgetx(r62);
        float r86 = Particle.m13782$$Nest$fgety(r62);
        if (r73 >= r1) goto L72;
    L77:
        int r163 = r112;
    L103:
        r38 = r38 + 1;
        r112 = r163;
        goto L68
    L72:
        if (r73 > r18) goto L77;
        if (r86 < r17) goto L77;
        if (r86 > r2) goto L77;
        int r63 = Particle.m13775$$Nest$fgetalpha(r62);
        float[] r92 = particlePoints[r63];
        int[] r102 = this.renderCount;
        int r116 = r102[r63];
        int r123 = r116 + 1;
        if (r123 >= r92.length) goto L77;
        r92[r116] = r73;
        r92[r123] = r86;
        int r124 = r116 + 2;
        float r132 = this.halfStrokeWidths[r63];
        if (r73 >= r132) goto L86;
        int r142 = r116 + 3;
        if (r142 >= r92.length) goto L86;
        r92[r124] = r210 + r73;
        r92[r142] = r86;
        r124 = r116 + 4;
    L86:
        float r117 = r210;
        float r143 = r117 - r132;
        if (r73 <= r143) goto L91;
        int r152 = r124 + 1;
        r163 = r112;
        if (r152 >= r92.length) goto L93;
        r92[r124] = r73 - r117;
        r92[r152] = r86;
        r124 = r124 + 2;
    L93:
        if (r86 >= r132) goto L98;
        int r118 = r124 + 1;
        if (r118 >= r92.length) goto L98;
        r92[r124] = r73;
        r92[r118] = r86 + r117;
        r124 = r124 + 2;
    L98:
        if (r86 <= r143) goto L102;
        int r119 = r124 + 1;
        if (r119 >= r92.length) goto L102;
        r92[r124] = r73;
        r92[r119] = r86 - r117;
        r124 = r124 + 2;
    L102:
        r102[r63] = r124;
        goto L103
    L91:
        r163 = r112;
        goto L93
    L104:
        int r120 = 0;
    L105:
        if (r120 >= r42) goto L121;
        r31[r120].addPoints(particlePoints[r120], 0, this.renderCount[r120]);
        r120 = r120 + 1;
        goto L105
    L121:
        return;
    L120:
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r4) {
        Rect r0 = getBounds();
        if (r0.isEmpty() == true) goto L10;
        Paint r1 = SpoilerEffectBitmapFactory.getInstance().getPaint();
        r1.setColorFilter(this.colorFilter);
        r4.drawRect(r0, r1);
        if (LiteMode.isEnabled(128) == false) goto L9;
        Choreographer60FpsContent.getInstance().postInvalidateDrawable(this);
        SpoilerEffectBitmapFactory.getInstance().checkUpdate(r0);
        return;
    L9:
        return;
    }

    public void drawPoints(Canvas r4, SpoilerEffectBitmapFactory.PointsBuffer[] r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.length != ALPHAS.length) goto L12;
        int r0 = 0;
    L8:
        if (r0 >= ALPHAS.length) goto L13;
        r5[r0].draw(r4, this.particlePaints[r0]);
        r0 = r0 + 1;
        goto L8
    L13:
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public View getParentView() {
        return this.mParent;
    }

    public void getRipplePath(Path r6) {
        r6.addCircle(this.rippleX, this.rippleY, this.rippleMaxRadius * MathUtils.clamp(this.rippleProgress, 0.0f, 1.0f), Path.Direction.CW);
    }

    public float getRippleProgress() {
        return this.rippleProgress;
    }

    public boolean hasRipplePath() {
        if (this.rippleMaxRadius > 0.0f) goto L5;
        return false;
    L5:
        if (this.rippleProgress <= 0.0f) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        View r0 = this.mParent;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParent() == null) goto L11;
        if (this.invalidateParent == false) goto L11;
        ((View) r0.getParent()).invalidate();
        return;
    L11:
        if ((r0 instanceof BaseCell) == false) goto L14;
        ((BaseCell) r0).invalidateLite();
        return;
    L14:
        r0.invalidate();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r2) {
        super.onBoundsChange(r2);
        this.boundsFWithInset.set(r2);
        this.boundsFWithInset.inset(0.0f, AndroidUtilities.dp(2.5f));
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r5) {
        this.mAlpha = r5;
        int r0 = 0;
    L3:
        float[] r1 = ALPHAS;
        if (r0 >= r1.length) goto L6;
        this.particlePaints[r0].setAlpha((int) (r1[r0] * r5));
        r0 = r0 + 1;
        goto L3
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r2, int r3, int r4, int r5) {
        super.setBounds(r2, r3, r4, r5);
        Iterator<Particle> r22 = this.particles.iterator();
    L4:
        if (r22.hasNext() == false) goto L11;
        Particle r32 = r22.next();
        if (getBounds().contains((int) Particle.m13781$$Nest$fgetx(r32), (int) Particle.m13782$$Nest$fgety(r32)) == true) goto L9;
        r22.remove();
    L9:
        if (this.particlesPool.size() >= this.maxParticles) goto L4;
        this.particlesPool.push(r32);
        goto L4
    }

    public void setColor(int r5) {
        if (this.lastColor == r5) goto L11;
        int r0 = 0;
    L5:
        float[] r1 = ALPHAS;
        if (r0 >= r1.length) goto L8;
        this.particlePaints[r0].setColor(ColorUtils.setAlphaComponent(r5, (int) (this.mAlpha * r1[r0])));
        r0 = r0 + 1;
        goto L5
    L8:
        this.colorFilter = new PorterDuffColorFilter(r5, PorterDuff.Mode.SRC_IN);
        this.lastColor = r5;
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r4) {
        Paint[] r3 = this.particlePaints;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].setColorFilter(r4);
        r1 = r1 + 1;
        goto L3
    }

    public void setInvalidateParent(boolean r1) {
        this.invalidateParent = r1;
    }

    public void setMaxParticlesCount(int r4) {
        this.maxParticles = r4;
    L4:
        if ((this.particlesPool.size() + this.particles.size()) >= r4) goto L6;
        this.particlesPool.push(new Particle(null));
        goto L4
    }

    public void setOnRippleEndCallback(Runnable r1) {
        this.onRippleEndCallback = r1;
    }

    public void setParentView(View r1) {
        this.mParent = r1;
    }

    public void setRippleInterpolator(TimeInterpolator r1) {
        this.rippleInterpolator = r1;
    }

    public void setRippleProgress(float r2) {
        this.rippleProgress = r2;
        if (r2 != (-1.0f)) goto L7;
        ValueAnimator r22 = this.rippleAnimator;
        if (r22 == null) goto L7;
        r22.cancel();
    L7:
        this.shouldInvalidateColor = true;
    }

    public void setSize(int r1) {
        this.bitmapSize = r1;
    }

    public void setSuppressUpdates(boolean r1) {
        this.suppressUpdates = r1;
        invalidateSelf();
    }

    public void setVisibleBounds(float r3, float r4, float r5, float r6) {
        if (this.visibleRect != null) goto L5;
        this.visibleRect = new RectF();
    L5:
        RectF r0 = this.visibleRect;
        if (r0.left == r3) goto L8;
    L15:
        r0.left = r3;
        r0.top = r4;
        r0.right = r5;
        r0.bottom = r6;
        invalidateSelf();
        return;
    L8:
        if (r0.right != r5) goto L15;
        if (r0.top != r4) goto L15;
        if (r0.bottom != r6) goto L15;
    }

    public boolean shouldInvalidateColor() {
        boolean r0 = this.shouldInvalidateColor;
        this.shouldInvalidateColor = false;
        return r0;
    }

    public void startRipple(float r2, float r3, float r4) {
        startRipple(r2, r3, r4, false);
    }

    public void startRipple(float r3, float r4, float r5, boolean r6) {
        this.rippleX = r3;
        this.rippleY = r4;
        this.rippleMaxRadius = r5;
        float r32 = 0.0f;
        if (r6 == false) goto L5;
        float r52 = 1.0f;
    L6:
        this.rippleProgress = r52;
        this.reverseAnimator = r6;
        ValueAnimator r53 = this.rippleAnimator;
        if (r53 == null) goto L10;
        r53.cancel();
    L10:
        if (this.reverseAnimator == false) goto L12;
        int r54 = 255;
    L13:
        float r1 = this.rippleProgress;
        if (r6 == true) goto L17;
        r32 = 1.0f;
    L17:
        ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{r1, r32}).setDuration((long) MathUtils.clamp(this.rippleMaxRadius * 0.3f, 250.0f, 550.0f));
        this.rippleAnimator = r33;
        r33.setInterpolator(this.rippleInterpolator);
        this.rippleAnimator.addUpdateListener(new SpoilerEffect$$ExternalSyntheticLambda1(this, r54));
        this.rippleAnimator.addListener(new AnonymousClass1(this));
        this.rippleAnimator.start();
        invalidateSelf();
        return;
    L12:
        r54 = this.particlePaints[ALPHAS.length - 1].getAlpha();
        goto L13
    L5:
        r52 = 0.0f;
        goto L6
    }

    public void updateMaxParticles() {
        int r0 = getBounds().width() / AndroidUtilities.dp(6.0f);
        int r1 = PARTICLES_PER_CHARACTER;
        setMaxParticlesCount(MathUtils.clamp(r0 * r1, r1, MAX_PARTICLES_PER_ENTITY));
    }
}
