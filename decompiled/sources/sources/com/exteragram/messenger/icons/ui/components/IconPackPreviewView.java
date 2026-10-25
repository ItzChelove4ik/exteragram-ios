package com.exteragram.messenger.icons.ui.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import com.exteragram.messenger.icons.ExteraResources;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.IconPack;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes4.dex */
public class IconPackPreviewView extends View {
    private static final int[] DEFAULT_ICONS = null;
    private float animationProgress;
    private ValueAnimator animator;
    private boolean attached;
    private final List<Integer> availableIconIds;
    private final Runnable changeRunnable;
    private final int circCenterSize;
    private final int circInnerIconSize;
    private final int circInnerRadius;
    private final int circOuterIconSize;
    private final int circOuterRadius;
    private final int cols;
    private int count;
    private final int[] currentIconIds;
    private final Drawable[] currentIcons;
    private IconPack currentPack;
    private final int gap;
    private final int iconSize;
    private boolean isCircularMode;
    private final Drawable[] nextIcons;
    private final Random random;
    private int refreshTimeMilliseconds;
    private final int rows;
    private boolean shouldAnimate;

    /* renamed from: com.exteragram.messenger.icons.ui.components.IconPackPreviewView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ IconPackPreviewView this$0;
        final /* synthetic */ List val$nextIds;

        public AnonymousClass1(IconPackPreviewView r1, List r2) {
            this.this$0 = r1;
            this.val$nextIds = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            if (IconPackPreviewView.m1449$$Nest$fgetanimator(this.this$0) != r5) goto L6;
            IconPackPreviewView.m1455$$Nest$fputanimator(this.this$0, null);
        L6:
            if (this.cancelled == false) goto L8;
            return;
        L8:
            if (IconPackPreviewView.m1450$$Nest$fgetattached(this.this$0) == false) goto L24;
            int r0 = 0;
        L12:
            if (r0 >= IconPackPreviewView.m1452$$Nest$fgetcurrentIcons(this.this$0).length) goto L18;
            int r2 = this.val$nextIds.size();
            IconPackPreviewView r3 = this.this$0;
            if (r0 >= r2) goto L16;
            IconPackPreviewView.m1451$$Nest$fgetcurrentIconIds(r3)[r0] = ((Integer) this.val$nextIds.get(r0)).intValue();
        L17:
            IconPackPreviewView.m1452$$Nest$fgetcurrentIcons(this.this$0)[r0] = IconPackPreviewView.m1453$$Nest$fgetnextIcons(this.this$0)[r0];
            IconPackPreviewView.m1453$$Nest$fgetnextIcons(this.this$0)[r0] = null;
            r0 = r0 + 1;
            goto L12
        L16:
            IconPackPreviewView.m1451$$Nest$fgetcurrentIconIds(r3)[r0] = 0;
            goto L17
        L18:
            IconPackPreviewView.m1454$$Nest$fputanimationProgress(this.this$0, 0.0f);
            this.this$0.invalidate();
            IconPackPreviewView.m1456$$Nest$mscheduleNext(this.this$0);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$5d9zIL87q6VlgvZ9VCnwqztMveU(IconPackPreviewView r0, IconPack r1, boolean r2, Drawable[] r3, List r4, int[] r5) {
        r0.lambda$loadIcons$0(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$K1rUdr1VhEe2Tscr8I1N6dNDQ9E(IconPackPreviewView r0, List r1, int r2, int[] r3, IconPack r4, boolean r5) {
        r0.lambda$loadIcons$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$anQMzBtDkODpNgmohQ7LG6jrfWA(IconPackPreviewView r0) {
        r0.animateToNext();
    }

    public static /* synthetic */ void $r8$lambda$icQBdcR_Kirao_mcuOyzACmdSRs(IconPackPreviewView r0, ValueAnimator r1) {
        r0.lambda$startTransition$2(r1);
    }

    /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m1449$$Nest$fgetanimator(IconPackPreviewView r0) {
        return r0.animator;
    }

    /* renamed from: -$$Nest$fgetattached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1450$$Nest$fgetattached(IconPackPreviewView r0) {
        return r0.attached;
    }

    /* renamed from: -$$Nest$fgetcurrentIconIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m1451$$Nest$fgetcurrentIconIds(IconPackPreviewView r0) {
        return r0.currentIconIds;
    }

    /* renamed from: -$$Nest$fgetcurrentIcons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable[] m1452$$Nest$fgetcurrentIcons(IconPackPreviewView r0) {
        return r0.currentIcons;
    }

    /* renamed from: -$$Nest$fgetnextIcons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable[] m1453$$Nest$fgetnextIcons(IconPackPreviewView r0) {
        return r0.nextIcons;
    }

    /* renamed from: -$$Nest$fputanimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1454$$Nest$fputanimationProgress(IconPackPreviewView r0, float r1) {
        r0.animationProgress = r1;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1455$$Nest$fputanimator(IconPackPreviewView r0, ValueAnimator r1) {
        r0.animator = r1;
    }

    /* renamed from: -$$Nest$mscheduleNext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1456$$Nest$mscheduleNext(IconPackPreviewView r0) {
        r0.scheduleNext();
    }

    static {
        DEFAULT_ICONS = new int[]{R.drawable.msg_sticker, R.drawable.msg_link2, R.drawable.msg_pin, R.drawable.msg_photos};
    }

    public IconPackPreviewView(Context r2) {
        super(r2);
        this.availableIconIds = new ArrayList();
        this.currentIcons = new Drawable[20];
        this.nextIcons = new Drawable[20];
        this.currentIconIds = new int[20];
        this.random = new Random();
        this.rows = 2;
        this.cols = 2;
        this.count = 4;
        this.iconSize = AndroidUtilities.dp(20.0f);
        this.gap = AndroidUtilities.dp(4.0f);
        this.isCircularMode = false;
        this.circInnerRadius = AndroidUtilities.dp(46.0f);
        this.circInnerIconSize = AndroidUtilities.dp(24.0f);
        this.circOuterRadius = AndroidUtilities.dp(72.0f);
        this.circOuterIconSize = AndroidUtilities.dp(20.0f);
        this.circCenterSize = AndroidUtilities.dp(36.0f);
        this.animationProgress = 0.0f;
        this.refreshTimeMilliseconds = 5000;
        this.changeRunnable = new IconPackPreviewView$$ExternalSyntheticLambda1(this);
    }

    private void animateToNext() {
        if (this.attached == true) goto L5;
        return;
    L5:
        if (this.shouldAnimate == false) goto L10;
        loadIcons(true);
        return;
    }

    private void drawCircularLayout(Canvas r18) {
        IconPackPreviewView r0 = this;
        int r7 = r0.getWidth() / 2;
        int r8 = r0.getHeight() / 2;
        int r5 = r0.circCenterSize;
        r0.drawIcon(r18, 0, r7 - (r5 / 2), r8 - (r5 / 2), r5, 1.0f);
        int r9 = 0;
        int r1 = 0;
    L4:
        if (r1 >= 6) goto L9;
        int r2 = 1 + r1;
        if (r2 >= r0.currentIcons.length) goto L9;
        double r3 = Math.toRadians(r1 * 60.0d);
        int r12 = ((int) (r7 + (r0.circInnerRadius * Math.cos(r3)))) - (r0.circInnerIconSize / 2);
        int r32 = (int) (r8 + (r0.circInnerRadius * Math.sin(r3)));
        int r52 = r0.circInnerIconSize;
        r0.drawIcon(r18, r2, r12, r32 - (r52 / 2), r52, 0.7f);
        r1 = r2;
    L9:
        if (r9 >= 6) goto L14;
        int r22 = 7 + r9;
        if (r22 >= r0.currentIcons.length) goto L20;
        double r33 = Math.toRadians((r9 * 60.0d) + 30.0d);
        int r13 = ((int) (r7 + (r0.circOuterRadius * Math.cos(r33)))) - (r0.circOuterIconSize / 2);
        int r34 = (int) (r8 + (r0.circOuterRadius * Math.sin(r33)));
        int r53 = r0.circOuterIconSize;
        r0.drawIcon(r18, r22, r13, r34 - (r53 / 2), r53, 0.3f);
        r9 = r9 + 1;
        r0 = this;
        goto L9
    L20:
        return;
    }

    private void drawIcon(Canvas r4, int r5, int r6, int r7, int r8, float r9) {
        if (r5 < this.currentIcons.length) goto L5;
        return;
    L5:
        r4.save();
        r4.translate(r6, r7);
        Drawable r62 = this.currentIcons[r5];
        Drawable r52 = this.nextIcons[r5];
        if (r62 == null) goto L8;
        r62.setBounds(0, 0, r8, r8);
        r62.setAlpha((int) (((1.0f - this.animationProgress) * r9) * 255.0f));
        r62.draw(r4);
    L8:
        if (r52 == null) goto L10;
        r52.setBounds(0, 0, r8, r8);
        r52.setAlpha((int) ((this.animationProgress * r9) * 255.0f));
        r52.draw(r4);
    L10:
        r4.restore();
    }

    private Drawable getIconDrawableInternal(IconPack r3, int r4) {
        Drawable r0 = null;
        if (r4 != 0) goto L6;
        return null;
    L6:
        if (r3.isBase() == false) goto L13;
        SparseIntArray r32 = r3.getPreinstalledMap();
        if (r32 == null) goto L10;
        int r33 = r32.get(r4, -1);
    L11:
        if (r33 == (-1)) goto L14;
        r0 = getResources().getDrawable(r33);
    L14:
        if (r0 == null) goto L16;
    L21:
        if (r0 == null) goto L26;
        Drawable r02 = r0.mutate();
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
        return r02;
    L26:
        return r0;
    L16:
        if ((getResources() instanceof ExteraResources) == true) goto L24;
        r0 = getResources().getDrawable(r4);
        goto L21
    L24:
        r0 = ((ExteraResources) getResources()).getOriginalDrawable(r4);     // Catch: Exception -> L19
    L19:
        r0 = getResources().getDrawable(r4);
        goto L21
    L10:
        r33 = -1;
        goto L11
    L13:
        r0 = IconManager.INSTANCE.getPackIconDrawable(r3, r4);
        goto L14
    }

    private /* synthetic */ void lambda$loadIcons$0(IconPack r2, boolean r3, Drawable[] r4, List r5, int[] r6) {
        if (this.currentPack == r2) goto L5;
        return;
    L5:
        int r22 = 0;
        if (r3 == false) goto L9;
        Drawable[] r32 = this.nextIcons;
        System.arraycopy(r4, 0, r32, 0, r32.length);
        startTransition(r5);
        return;
    L9:
        Drawable[] r33 = this.currentIcons;
        if (r22 >= r33.length) goto L12;
        this.currentIconIds[r22] = r6[r22];
        r33[r22] = r4[r22];
        r22 = r22 + 1;
        goto L9
    L12:
        invalidate();
        if (this.attached == true) goto L15;
    L18:
        removeCallbacks(this.changeRunnable);
        return;
    L15:
        if (this.shouldAnimate == false) goto L18;
        scheduleNext();
    }

    private /* synthetic */ void lambda$loadIcons$1(List r8, int r9, int[] r10, IconPack r11, boolean r12) {
        List<Integer> r5 = pickRandomIconIdsInternal(r8, r9, r10);
        int[] r82 = this.currentIconIds;
        Drawable[] r4 = new Drawable[r82.length];
        int[] r6 = new int[r82.length];
        int r92 = 0;
    L4:
        if (r92 >= this.currentIconIds.length) goto L15;
        if (r92 >= r5.size()) goto L9;
        int r102 = r5.get(r92).intValue();
        r6[r92] = r102;
        r4[r92] = getIconDrawableInternal(r11, r102);
    L14:
        r92 = r92 + 1;
        goto L4
    L9:
        if (r5.isEmpty() == false) goto L11;
    L13:
        r6[r92] = 0;
        r4[r92] = null;
        goto L14
    L11:
        if (this.isCircularMode == false) goto L13;
        int r103 = r5.get(r92 % r5.size()).intValue();
        r6[r92] = r103;
        r4[r92] = getIconDrawableInternal(r11, r103);
        goto L14
    L15:
        AndroidUtilities.runOnUIThread(new IconPackPreviewView$$ExternalSyntheticLambda2(this, r11, r12, r4, r5, r6));
    }

    private /* synthetic */ void lambda$startTransition$2(ValueAnimator r1) {
        this.animationProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void loadIcons(boolean r9) {
        IconPack r5 = this.currentPack;
        ArrayList r2 = new ArrayList(this.availableIconIds);
        if (r9 == false) goto L6;
        int[] r0 = (int[]) this.currentIconIds.clone();
    L5:
        int[] r4 = r0;
        boolean r02 = r5.isBase();
        int r1 = this.count;
        if (r02 == true) goto L10;
        r1 = Math.min(r1, r2.size());
    L10:
        Utilities.globalQueue.postRunnable(new IconPackPreviewView$$ExternalSyntheticLambda0(this, r2, r1, r4, r5, r9));
        return;
    L6:
        r0 = null;
        goto L5
    }

    private List<Integer> pickRandomIconIdsInternal(List<Integer> r7, int r8, int[] r9) {
        ArrayList r0 = new ArrayList();
        if (r7.isEmpty() == true) goto L33;
        ArrayList r1 = new ArrayList(r7);
        int r2 = 0;
        if (r9 == null) goto L11;
        int r3 = r9.length;
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L11;
        r1.remove(Integer.valueOf(r9[r4]));
        r4 = r4 + 1;
    L11:
        if (r1.isEmpty() == false) goto L13;
        r1.addAll(r7);
    L13:
        HashSet r72 = new HashSet();
        int r92 = r1.size();
        int r32 = 0;
    L15:
        if (r72.size() >= r8) goto L22;
        if (r72.size() >= r92) goto L22;
        if (r32 >= (r8 * 4)) goto L22;
        r72.add(Integer.valueOf(this.random.nextInt(r92)));
        r32 = r32 + 1;
    L22:
        if (r72.size() < r8) goto L24;
    L29:
        Iterator r6 = r72.iterator();
    L31:
        if (r6.hasNext() == false) goto L33;
        r0.add((Integer) r1.get(((Integer) r6.next()).intValue()));
        goto L31
    L24:
        if (r72.size() >= r92) goto L29;
    L25:
        if (r2 >= r92) goto L29;
        if (r72.size() >= r8) goto L29;
        r72.add(Integer.valueOf(r2));
        r2 = r2 + 1;
    L33:
        return r0;
    }

    private static boolean sameIconSource(IconPack r2, IconPack r3) {
        if (r2.getId().equals(r3.getId()) == true) goto L5;
        return false;
    L5:
        Map<String, String> r22 = r2.getIcons();
        Map<String, String> r32 = r3.getIcons();
        if (r22 != r32) goto L8;
        return true;
    L8:
        if (r22.isEmpty() == true) goto L10;
    L12:
        return false;
    L10:
        if (r32.isEmpty() == false) goto L12;
        return true;
    }

    private void scheduleNext() {
        removeCallbacks(this.changeRunnable);
        if (this.attached == true) goto L5;
        return;
    L5:
        if (this.shouldAnimate == false) goto L9;
        postDelayed(this.changeRunnable, this.refreshTimeMilliseconds);
        return;
    }

    private void startTransition(List<Integer> r4) {
        if (this.attached == true) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = r02;
        r02.setDuration(300);
        this.animator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animator.addUpdateListener(new IconPackPreviewView$$ExternalSyntheticLambda3(this));
        this.animator.addListener(new AnonymousClass1(this, r4));
        this.animator.start();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        if (this.shouldAnimate == false) goto L6;
        scheduleNext();
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        this.attached = false;
        removeCallbacks(this.changeRunnable);
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        this.animator = null;
        r0.cancel();
    L5:
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        if (this.isCircularMode == false) goto L6;
        drawCircularLayout(r14);
        return;
    L6:
        int r7 = 0;
        if (this.count != 4) goto L28;
        Drawable[] r1 = this.currentIcons;
        int r2 = r1.length;
        int r3 = 0;
    L9:
        if (r7 >= r2) goto L15;
        if (r1[r7] == null) goto L13;
        r3 = r3 + 1;
    L13:
        r7 = r7 + 1;
        goto L9
    L15:
        if (r3 != 1) goto L19;
        int r5 = AndroidUtilities.dp(24.0f);
        drawIcon(r14, 0, (getWidth() - r5) / 2, (getHeight() - r5) / 2, r5, 1.0f);
        return;
    L19:
        if (r3 != 2) goto L23;
        int r52 = AndroidUtilities.dp(20.0f);
        int r72 = AndroidUtilities.dp(4.0f);
        int r4 = (getHeight() - r52) / 2;
        drawIcon(r14, 0, 0, r4, r52, 1.0f);
        drawIcon(r14, 1, r52 + r72, r4, r52, 1.0f);
        return;
    L23:
        if (r3 != 3) goto L26;
        int r53 = AndroidUtilities.dp(20.0f);
        int r73 = AndroidUtilities.dp(4.0f);
        drawIcon(r14, 0, 0, 0, r53, 1.0f);
        int r32 = r53 + r73;
        drawIcon(r14, 1, r32, 0, r53, 1.0f);
        drawIcon(r14, 2, (getWidth() - r53) / 2, r32, r53, 1.0f);
        return;
    L26:
        int r54 = AndroidUtilities.dp(20.0f);
        int r74 = r54 + AndroidUtilities.dp(4.0f);
        drawIcon(r14, 0, 0, 0, r54, 1.0f);
        drawIcon(r14, 1, r74, 0, r54, 1.0f);
        drawIcon(r14, 2, 0, r74, r54, 1.0f);
        drawIcon(r14, 3, r74, r74, r54, 1.0f);
        return;
    L28:
        int r9 = getPaddingLeft();
        int r10 = getPaddingTop();
        int r12 = 0;
        int r11 = 0;
    L29:
        if (r11 >= 2) goto L37;
        int r22 = r12;
        int r122 = 0;
    L31:
        if (r122 >= 2) goto L36;
        if (r22 >= this.currentIcons.length) goto L36;
        int r55 = this.iconSize;
        int r13 = this.gap;
        drawIcon(r14, r22, ((r55 + r13) * r122) + r9, r10 + ((r13 + r55) * r11), r55, 1.0f);
        r22 = r22 + 1;
        r122 = r122 + 1;
    L36:
        r11 = r11 + 1;
        r12 = r22;
        goto L29
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        if (this.isCircularMode == false) goto L6;
        int r0 = ((this.circOuterRadius + (this.circOuterIconSize / 2)) * 2) + AndroidUtilities.dp(8.0f);
        setMeasuredDimension(View.getDefaultSize(getSuggestedMinimumWidth(), r4), View.resolveSize(r0, r5));
        return;
    L6:
        int r02 = (((this.iconSize * 2) + this.gap) + getPaddingLeft()) + getPaddingRight();
        int r1 = (((this.iconSize * 2) + this.gap) + getPaddingTop()) + getPaddingBottom();
        setMeasuredDimension(View.resolveSize(r02, r4), View.resolveSize(r1, r5));
    }

    public void setCircularMode(boolean r1) {
        this.isCircularMode = r1;
        if (r1 == false) goto L5;
        this.count = 13;
    L5:
        requestLayout();
        IconPack r12 = this.currentPack;
        if (r12 == null) goto L9;
        setIconPack(r12);
        return;
    }

    public void setIconPack(IconPack r11) {
        IconPack r0 = this.currentPack;
        if (r0 != null) goto L5;
    L11:
        this.currentPack = r11;
        this.availableIconIds.clear();
        if (r11.isBase() == false) goto L17;
        int[] r112 = DEFAULT_ICONS;
        int r02 = r112.length;
        int r2 = 0;
    L14:
        if (r2 >= r02) goto L16;
        this.availableIconIds.add(Integer.valueOf(r112[r2]));
        r2 = r2 + 1;
        goto L14
    L16:
        this.shouldAnimate = false;
    L45:
        loadIcons(false);
        return;
    L17:
        ArrayList r03 = new ArrayList();
        HashSet r22 = new HashSet();
        Iterator<Map.Entry<String, String>> r113 = r11.getIcons().entrySet().iterator();
    L19:
        if (r113.hasNext() == false) goto L23;
        Map.Entry<String, String> r3 = r113.next();
        String r4 = r3.getKey();
        String r32 = r3.getValue();
        if (r22.contains(r32) == true) goto L19;
        r22.add(r32);
        r03.add(r4);
        goto L19
    L23:
        boolean r33 = true;
        if (r03.isEmpty() == false) goto L26;
        this.availableIconIds.add(Integer.valueOf(R.drawable.msg_media));
    L41:
        if (r22.size() >= (this.count * 2)) goto L44;
        r33 = false;
    L44:
        this.shouldAnimate = r33;
        goto L45
    L26:
        ConcurrentHashMap<String, Integer> r114 = IconManager.INSTANCE.getSystemIcons();
        if (r114.isEmpty() == true) goto L33;
        int r42 = r03.size();
        int r5 = 0;
    L29:
        if (r5 >= r42) goto L41;
        Object r6 = r03.get(r5);
        r5 = r5 + 1;
        Integer r62 = r114.get((String) r6);
        if (r62 == null) goto L29;
        this.availableIconIds.add(r62);
        goto L29
    L33:
        int r115 = r03.size();
        int r43 = 0;
        int r52 = 0;
    L34:
        if (r52 >= r115) goto L41;
        Object r63 = r03.get(r52);
        r52 = r52 + 1;
        int r64 = getResources().getIdentifier((String) r63, "drawable", getContext().getPackageName());
        if (r64 == 0) goto L38;
        this.availableIconIds.add(Integer.valueOf(r64));
    L38:
        r43 = r43 + 1;
        if (r43 <= 30) goto L34;
    L5:
        if (sameIconSource(r0, r11) == false) goto L11;
        if (this.availableIconIds.isEmpty() == true) goto L11;
        if (this.availableIconIds.size() < this.count) goto L11;
    }

    public void setRefreshTime(int r1) {
        this.refreshTimeMilliseconds = r1;
    }
}
