package org.telegram.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import java.util.HashSet;
import java.util.Set;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import me.vkryl.android.animator.ListAnimator;
import me.vkryl.android.util.ClickHelper;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedLinearLayout;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.glass.GlassTabView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class MainTabsLayout extends AnimatedLinearLayout {
    private static final int[] PASS_PADDINGS_DP = null;
    private static final float[] PASS_TEXT_SIZES_DP = null;
    private float animatedLongSelectedViewCenterX;
    private float animatedLongSelectedViewOffsetX;
    private final BoolAnimator animatorIsScaled;
    private int biggestTabTextWidth;
    private final ClickHelper clickHelper;
    final Paint dividerPaint;
    private boolean drawCustomSelector;
    private boolean drawTopDivider;
    private boolean fillAvailableWidth;
    private boolean isInLongPress;
    private View lastLongSelectedView;
    private float lastLongSelectedViewCenterX;
    private float lastLongSelectedViewWidth;
    private boolean manuallyStartedLongMove;
    private int maxWidthPx;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Runnable restoreDrawSelector;
    final SpringAnimation scaleX;
    final SpringAnimation scaleY;
    final SpringAnimation selectedTabPositionOffsetX;
    final SpringAnimation selectedTabPositionX;
    final Paint selectorPaint;
    private boolean swipeSelectionEnabled;
    private int[] tabsLeftPos;
    private float[] tabsTextWidth;
    private float[] tabsTextWidthWithMargin;
    private int[] tabsWeight;
    private int[] tabsWidth;
    private final Set<View> tabsWithIgnoreClick;
    private long touchStartTime;
    private View touchStartView;
    private float touchStartX;
    private float touchStartY;
    private int visibleChildCount;

    /* renamed from: org.telegram.ui.MainTabsLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends FloatPropertyCompat<MainTabsLayout> {
        final /* synthetic */ MainTabsLayout this$0;

        public AnonymousClass1(MainTabsLayout r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(MainTabsLayout r1) {
            return getValue2(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(MainTabsLayout r1) {
            return MainTabsLayout.m16042$$Nest$fgetanimatedLongSelectedViewOffsetX(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(MainTabsLayout r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(MainTabsLayout r1, float r2) {
            MainTabsLayout.m16046$$Nest$fputanimatedLongSelectedViewOffsetX(r1, r2);
            r1.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.MainTabsLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends FloatPropertyCompat<MainTabsLayout> {
        final /* synthetic */ MainTabsLayout this$0;

        public AnonymousClass2(MainTabsLayout r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(MainTabsLayout r1) {
            return getValue2(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(MainTabsLayout r1) {
            return MainTabsLayout.m16041$$Nest$fgetanimatedLongSelectedViewCenterX(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(MainTabsLayout r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(MainTabsLayout r1, float r2) {
            MainTabsLayout.m16045$$Nest$fputanimatedLongSelectedViewCenterX(r1, r2);
            r1.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.MainTabsLayout$3, reason: invalid class name */
    public class AnonymousClass3 implements ClickHelper.Delegate {
        final /* synthetic */ MainTabsLayout this$0;

        public AnonymousClass3(MainTabsLayout r1) {
            this.this$0 = r1;
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public boolean needCancelTouchBySlopMove() {
            return false;
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public boolean needClickAt(View r2, float r3, float r4) {
            MainTabsLayout.m16047$$Nest$fputlastLongSelectedView(this.this$0, null);
            View r22 = MainTabsLayout.findChildUnder(this.this$0, r3, r4);
            if (r22 != null) goto L5;
            return false;
        L5:
            if (MainTabsLayout.m16044$$Nest$fgettabsWithIgnoreClick(this.this$0).contains(r22) == true) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public boolean needLongPress(float r1, float r2) {
            return false;
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onClickAt(View r1, float r2, float r3) {
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onClickTouchDown(View r1, float r2, float r3) {
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onClickTouchMove(View r2, float r3, float r4) {
            if (MainTabsLayout.m16043$$Nest$fgetisInLongPress(this.this$0) == false) goto L6;
            MainTabsLayout.m16049$$Nest$mcheckPivot(this.this$0, r2, r3, r4);
            return;
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onClickTouchUp(View r1, float r2, float r3) {
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onLongPressCancelled(View r1, float r2, float r3) {
            MainTabsLayout.m16050$$Nest$mfinishTabsLongMove(this.this$0, r2, r3, false);
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onLongPressFinish(View r1, float r2, float r3) {
            MainTabsLayout.m16050$$Nest$mfinishTabsLongMove(this.this$0, r2, r3, true);
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public void onLongPressMove(View r1, MotionEvent r2, float r3, float r4, float r5, float r6) {
            MainTabsLayout.m16049$$Nest$mcheckPivot(this.this$0, r1, r3, r4);
            MainTabsLayout.m16048$$Nest$mcheckLongMove(this.this$0, r3, r4, false, false);
            this.this$0.invalidate();
        }

        @Override // me.vkryl.android.util.ClickHelper.Delegate
        public boolean onLongPressRequestedAt(View r1, float r2, float r3) {
            MainTabsLayout.m16051$$Nest$mstartTabsLongMove(this.this$0, r2, r3);
            return true;
        }
    }

    public interface Tab {
        float measureTextWidth(float r1);

        void setTextSizeDp(float r1);
    }

    public static /* synthetic */ void $r8$lambda$3K9rtMKwBGeChrZc82onftJwu7Q(MainTabsLayout r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$K_KW9QReU2uya6Op8fx7h1cSc3k(MainTabsLayout r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetanimatedLongSelectedViewCenterX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m16041$$Nest$fgetanimatedLongSelectedViewCenterX(MainTabsLayout r0) {
        return r0.animatedLongSelectedViewCenterX;
    }

    /* renamed from: -$$Nest$fgetanimatedLongSelectedViewOffsetX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m16042$$Nest$fgetanimatedLongSelectedViewOffsetX(MainTabsLayout r0) {
        return r0.animatedLongSelectedViewOffsetX;
    }

    /* renamed from: -$$Nest$fgetisInLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16043$$Nest$fgetisInLongPress(MainTabsLayout r0) {
        return r0.isInLongPress;
    }

    /* renamed from: -$$Nest$fgettabsWithIgnoreClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Set m16044$$Nest$fgettabsWithIgnoreClick(MainTabsLayout r0) {
        return r0.tabsWithIgnoreClick;
    }

    /* renamed from: -$$Nest$fputanimatedLongSelectedViewCenterX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16045$$Nest$fputanimatedLongSelectedViewCenterX(MainTabsLayout r0, float r1) {
        r0.animatedLongSelectedViewCenterX = r1;
    }

    /* renamed from: -$$Nest$fputanimatedLongSelectedViewOffsetX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16046$$Nest$fputanimatedLongSelectedViewOffsetX(MainTabsLayout r0, float r1) {
        r0.animatedLongSelectedViewOffsetX = r1;
    }

    /* renamed from: -$$Nest$fputlastLongSelectedView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16047$$Nest$fputlastLongSelectedView(MainTabsLayout r0, View r1) {
        r0.lastLongSelectedView = r1;
    }

    /* renamed from: -$$Nest$mcheckLongMove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16048$$Nest$mcheckLongMove(MainTabsLayout r0, float r1, float r2, boolean r3, boolean r4) {
        r0.checkLongMove(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mcheckPivot, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16049$$Nest$mcheckPivot(MainTabsLayout r0, View r1, float r2, float r3) {
        r0.checkPivot(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mfinishTabsLongMove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16050$$Nest$mfinishTabsLongMove(MainTabsLayout r0, float r1, float r2, boolean r3) {
        r0.finishTabsLongMove(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mstartTabsLongMove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16051$$Nest$mstartTabsLongMove(MainTabsLayout r0, float r1, float r2) {
        r0.startTabsLongMove(r1, r2);
    }

    static {
        PASS_TEXT_SIZES_DP = new float[]{12.0f, 12.0f, 10.0f};
        PASS_PADDINGS_DP = new int[]{16, 8, 4};
    }

    public MainTabsLayout(Context r10, Theme.ResourcesProvider r11) {
        super(r10);
        this.swipeSelectionEnabled = true;
        this.restoreDrawSelector = new MainTabsLayout$$ExternalSyntheticLambda0(this);
        this.selectorPaint = new Paint(1);
        Paint r0 = new Paint(1);
        this.dividerPaint = r0;
        SpringAnimation r102 = new SpringAnimation(this, DynamicAnimation.SCALE_X, 1.0f);
        this.scaleX = r102;
        SpringAnimation r1 = new SpringAnimation(this, DynamicAnimation.SCALE_Y, 1.0f);
        this.scaleY = r1;
        SpringAnimation r3 = new SpringAnimation(this, new AnonymousClass1(this, "selectedTabPositionOffsetX"));
        this.selectedTabPositionOffsetX = r3;
        SpringAnimation r4 = new SpringAnimation(this, new AnonymousClass2(this, "selectedTabPositionX"));
        this.selectedTabPositionX = r4;
        r3.setSpring(new SpringForce(1.0f).setStiffness(1500.0f).setDampingRatio(0.75f));
        r102.setSpring(new SpringForce(1.0f).setStiffness(250.0f).setDampingRatio(0.25f));
        r1.setSpring(new SpringForce(1.0f).setStiffness(250.0f).setDampingRatio(0.25f));
        r4.setSpring(new SpringForce(1.0f).setStiffness(1500.0f).setDampingRatio(0.75f));
        this.tabsWithIgnoreClick = new HashSet();
        this.animatorIsScaled = new BoolAnimator(0, new MainTabsLayout$$ExternalSyntheticLambda1(this), CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.clickHelper = new ClickHelper(new AnonymousClass3(this));
        this.resourcesProvider = r11;
        r0.setStrokeWidth(1.0f);
    }

    private void applyPassTextSize(int r5) {
        float r52 = PASS_TEXT_SIZES_DP[r5];
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        KeyEvent.Callback r2 = getChildAt(r1);
        if ((r2 instanceof Tab) == false) goto L7;
        ((Tab) r2).setTextSizeDp(r52);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private void checkLayerType() {
        if (Math.abs(getScaleX() - 1.0f) < 1.0E-4f) goto L5;
    L7:
        int r0 = 2;
    L9:
        if (getLayerType() == r0) goto L12;
        setLayerType(r0, null);
        invalidate();
        return;
    L12:
        return;
    L5:
        if (Math.abs(getScaleY() - 1.0f) >= 1.0E-4f) goto L7;
        r0 = 0;
        goto L9
    }

    private void checkLongMove(float r4, float r5, boolean r6, boolean r7) {
        float r42 = clampXToChildrenCenters(r4, this);
        View r52 = findNearestVisibleChildByX(r42, this);
        if (r6 == false) goto L11;
        View r62 = findSelectedTab();
        if (r62 == null) goto L10;
        float r1 = r62.getX() + (r62.getWidth() / 2.0f);
        this.animatedLongSelectedViewCenterX = r1;
        this.animatedLongSelectedViewOffsetX = r1 - r42;
        this.selectedTabPositionOffsetX.animateToFinalPosition(0.0f);
        if (r62 == r52) goto L10;
        if (r52 == null) goto L10;
        r52.performClick();
    L10:
        this.selectedTabPositionX.cancel();
    L11:
        if (r7 == true) goto L13;
        this.animatedLongSelectedViewCenterX = r42;
        invalidate();
    L13:
        if (r52 == null) goto L22;
        this.lastLongSelectedView = r52;
        setTabSelected(r52, true);
        if (r7 == false) goto L23;
        float r43 = r52.getWidth();
        float r53 = r52.getX() + (r43 / 2.0f);
        if (this.lastLongSelectedViewWidth == r43) goto L19;
    L20:
        this.selectedTabPositionX.animateToFinalPosition(r53);
        return;
    L19:
        if (this.lastLongSelectedViewCenterX != r53) goto L20;
        return;
    L23:
        return;
    }

    private void checkPivot(View r5, float r6, float r7) {
        float r4 = r5.getWidth();
        float r0 = r5.getHeight();
        if (r4 > 0.0f) goto L5;
        return;
    L5:
        if (r0 <= 0.0f) goto L14;
        float r42 = r4 * 0.5f;
        float r02 = r0 * 0.5f;
        float r62 = r6 - r42;
        float r72 = r7 - r02;
        float r2 = r62 / r42;
        float r3 = r72 / r02;
        float r22 = (float) Math.sqrt((r2 * r2) + (r3 * r3));
        if (r22 <= 1.0E-4f) goto L10;
        float r32 = ((1.5f * r22) / (0.5f + r22)) / r22;
        float r63 = (r62 * r32) + r42;
        float r73 = (r72 * r32) + r02;
    L11:
        float r43 = AndroidUtilities.lerp(r42, r63, 0.95f);
        float r64 = AndroidUtilities.lerp(r02, r73, 2.83f);
        r5.setPivotX(r43);
        r5.setPivotY(r64);
        return;
    L10:
        r63 = r42;
        r73 = r02;
        goto L11
    }

    private void checkVisualWidth() {
        int r0 = getEntriesCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        ListAnimator.Entry<AnimatedLinearLayout.Holder> r2 = getEntry(r1);
        float r3 = r2.getRectF().width();
        ((GlassTabView) r2.item.view).setVisualWidth(r3);
        r1 = r1 + 1;
        goto L3
    }

    private static float clampXToChildrenCenters(float r6, ViewGroup r7) {
        if (r7 != null) goto L4;
    L30:
        return r6;
    L4:
        if (r7.getChildCount() == 0) goto L30;
        int r0 = 0;
        float r3 = -3.4028235E38f;
        float r2 = Float.MAX_VALUE;
        boolean r1 = false;
    L8:
        if (r0 >= r7.getChildCount()) goto L22;
        View r4 = r7.getChildAt(r0);
        if (r4 == null) goto L21;
        if (r4.getVisibility() != 0) goto L21;
        float r12 = r4.getX() + (r4.getWidth() * 0.5f);
        if (r12 >= r2) goto L18;
        r2 = r12;
    L18:
        if (r12 <= r3) goto L20;
        r3 = r12;
    L20:
        r1 = true;
    L21:
        r0 = r0 + 1;
        goto L8
    L22:
        if (r1 == false) goto L30;
        if (r6 >= r2) goto L28;
        return r2;
    L28:
        if (r6 <= r3) goto L30;
        return r3;
    }

    public static View findChildUnder(ViewGroup r3, float r4, float r5) {
        int r0 = r3.getChildCount() - 1;
    L3:
        if (r0 < 0) goto L17;
        View r1 = r3.getChildAt(r0);
        if (r1.getVisibility() != 0) goto L16;
        if (r4 < r1.getLeft()) goto L16;
        if (r4 > r1.getRight()) goto L16;
        if (r5 < r1.getTop()) goto L16;
        if (r5 > r1.getBottom()) goto L16;
        return r1;
    L16:
        r0 = r0 - 1;
        goto L3
    L17:
        return null;
    }

    private static View findNearestVisibleChildByX(float r7, ViewGroup r8) {
        View r0 = null;
        if (r8 != null) goto L5;
    L19:
        return r0;
    L5:
        if (r8.getChildCount() == 0) goto L19;
        float r1 = Float.MAX_VALUE;
        int r2 = 0;
    L9:
        if (r2 >= r8.getChildCount()) goto L19;
        View r3 = r8.getChildAt(r2);
        if (r3 == null) goto L18;
        if (r3.getVisibility() != 0) goto L18;
        float r4 = Math.abs((r3.getX() + (r3.getWidth() * 0.5f)) - r7);
        if (r4 >= r1) goto L18;
        r0 = r3;
        r1 = r4;
    L18:
        r2 = r2 + 1;
        goto L9
    }

    private View findSelectedTab() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L13;
        View r2 = getChildAt(r1);
        if (r2.getVisibility() != 0) goto L12;
        if ((r2 instanceof GlassTabView) == false) goto L12;
        if (((GlassTabView) r2).isTabSelected() == false) goto L12;
        return r2;
    L12:
        r1 = r1 + 1;
        goto L3
    L13:
        return null;
    }

    private void finishTabsLongMove(float r5, float r6, boolean r7) {
        checkPivot(this, r5, r6);
        checkLongMove(r5, r6, false, true);
        this.isInLongPress = false;
        this.manuallyStartedLongMove = false;
        AndroidUtilities.runOnUIThread(this.restoreDrawSelector, 450);
        if (r7 == false) goto L7;
        View r52 = this.lastLongSelectedView;
        if (r52 == null) goto L7;
        r52.performClick();
    L7:
        this.lastLongSelectedView = null;
        invalidate();
        this.animatorIsScaled.setValue(false, true);
    }

    private static float getCenterX(View r2) {
        return r2.getX() + (r2.getWidth() * 0.5f);
    }

    private static float getInterpolatedWidthByX(float r8, ViewGroup r9) {
        if (r9 != null) goto L5;
    L44:
        return 0.0f;
    L5:
        if (r9.getChildCount() == 0) goto L44;
        View r1 = null;
        int r3 = 0;
        View r2 = null;
    L9:
        if (r3 >= r9.getChildCount()) goto L28;
        View r4 = r9.getChildAt(r3);
        if (r4 == null) goto L27;
        if (r4.getVisibility() != 0) goto L27;
        float r5 = r4.getX() + (r4.getWidth() * 0.5f);
        if (r5 > r8) goto L22;
        if (r1 != null) goto L19;
    L20:
        r1 = r4;
        goto L22
    L19:
        if (r5 > getCenterX(r1)) goto L20;
    L22:
        if (r5 < r8) goto L27;
        if (r2 != null) goto L25;
    L26:
        r2 = r4;
        goto L27
    L25:
        if (r5 < getCenterX(r2)) goto L26;
    L27:
        r3 = r3 + 1;
        goto L9
    L28:
        if (r1 != null) goto L31;
        if (r2 != null) goto L31;
        return 0.0f;
    L31:
        if (r1 != null) goto L35;
        int r82 = r2.getWidth();
    L34:
        return r82;
    L35:
        if (r2 != null) goto L37;
        r82 = r1.getWidth();
        goto L34
    L37:
        float r92 = getCenterX(r1);
        float r0 = getCenterX(r2);
        if (r1 != r2) goto L40;
    L43:
        r82 = r1.getWidth();
        goto L34
    L40:
        if (r92 == r0) goto L43;
        r82 = AndroidUtilities.lerp(r1.getWidth(), r2.getWidth(), (r8 - r92) / (r0 - r92));
        goto L34
    }

    private /* synthetic */ void lambda$new$0() {
        setSkipDrawSelector(false);
    }

    private /* synthetic */ void lambda$new$1(int r1, float r2, float r3, FactorAnimator r4) {
        setScaleX(AndroidUtilities.lerp(1.0f, 1.019f, r2));
        setScaleY(AndroidUtilities.lerp(1.0f, 1.019f, r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void measureTabTexts(float r8) {
        int r0 = getChildCount();
        float[] r1 = this.tabsTextWidth;
        if (r1 != null) goto L5;
    L6:
        this.tabsTextWidth = new float[r0];
        this.tabsTextWidthWithMargin = new float[r0];
        this.tabsWeight = new int[r0];
        this.tabsLeftPos = new int[r0];
        this.tabsWidth = new int[r0];
    L7:
        int r12 = 0;
        int r3 = 0;
        float r4 = 0.0f;
    L8:
        if (r12 >= r0) goto L18;
        View r5 = getChildAt(r12);
        if (isViewVisible(r5) == true) goto L13;
        this.tabsTextWidth[r12] = -1.0f;
    L17:
        r12 = r12 + 1;
        goto L8
    L13:
        if ((r5 instanceof Tab) == false) goto L15;
        float r52 = ((Tab) r5).measureTextWidth(r8);
    L16:
        this.tabsTextWidth[r12] = r52;
        r4 = Math.max(r4, r52);
        r3 = r3 + 1;
        goto L17
    L15:
        r52 = 0.0f;
        goto L16
    L18:
        this.biggestTabTextWidth = (int) Math.ceil(r4);
        this.visibleChildCount = r3;
        return;
    L5:
        if (r1.length >= r0) goto L7;
        goto L6
    }

    private void setSkipDrawSelector(boolean r5) {
        this.drawCustomSelector = r5;
        if (r5 == false) goto L5;
        this.selectorPaint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_glass_tabSelected, this.resourcesProvider), 0.09f));
    L5:
        int r0 = getChildCount();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L14;
        View r2 = getChildAt(r1);
        if (r2.getVisibility() != 0) goto L13;
        if ((r2 instanceof GlassTabView) == false) goto L13;
        ((GlassTabView) r2).setSkipDrawSelector(r5);
    L13:
        r1 = r1 + 1;
        goto L6
    L14:
        invalidate();
    }

    private void startTabsLongMove(float r3, float r4) {
        if (this.swipeSelectionEnabled == true) goto L5;
        return;
    L5:
        checkPivot(this, r3, r4);
        this.isInLongPress = true;
        AndroidUtilities.cancelRunOnUIThread(this.restoreDrawSelector);
        setSkipDrawSelector(true);
        checkLongMove(r3, r4, true, false);
        invalidate();
        this.animatorIsScaled.setValue(true, true);
    }

    public void addTabToIgnoreClick(View r1) {
        this.tabsWithIgnoreClick.add(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r14) {
        if (this.drawCustomSelector == false) goto L5;
        float r1 = this.animatedLongSelectedViewCenterX + this.animatedLongSelectedViewOffsetX;
        float r2 = getInterpolatedWidthByX(r1, this);
        float r3 = (getHeight() - getPaddingTop()) - getPaddingBottom();
        float r22 = r2 / 2.0f;
        float r10 = r3 / 2.0f;
        r14.drawRoundRect(r1 - r22, (getHeight() - r3) / 2.0f, r1 + r22, (getHeight() + r3) / 2.0f, r10, r10, this.selectorPaint);
    L5:
        super.dispatchDraw(r14);
        if (this.drawTopDivider == false) goto L9;
        this.dividerPaint.setColor(Theme.getDividerColor(this.resourcesProvider));
        r14.drawLine(0.0f, 1.0f, getMeasuredWidth(), 1.0f, this.dividerPaint);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r9) {
        if (this.swipeSelectionEnabled == false) goto L38;
        int r0 = r9.getActionMasked();
        boolean r1 = false;
        if (r0 != 0) goto L8;
        this.touchStartX = r9.getX();
        this.touchStartY = r9.getY();
        this.touchStartTime = r9.getEventTime();
        this.touchStartView = findChildUnder(this, this.touchStartX, this.touchStartY);
        this.manuallyStartedLongMove = false;
        goto L38
    L8:
        if (r0 == 2) goto L10;
        if (r0 == 1) goto L32;
        if (r0 != 3) goto L38;
    L32:
        if (this.manuallyStartedLongMove == false) goto L38;
        float r2 = r9.getX();
        float r92 = r9.getY();
        if (r0 != 1) goto L36;
        r1 = true;
    L36:
        finishTabsLongMove(r2, r92, r1);
        return true;
    L10:
        if (this.manuallyStartedLongMove == false) goto L13;
        checkPivot(this, r9.getX(), r9.getY());
        checkLongMove(r9.getX(), r9.getY(), false, false);
        invalidate();
        return true;
    L13:
        View r02 = this.touchStartView;
        if ((r02 instanceof GlassTabView) == false) goto L38;
        if (((GlassTabView) r02).isTabSelected() == false) goto L38;
        if ((r9.getEventTime() - this.touchStartTime) >= ViewConfiguration.getLongPressTimeout()) goto L38;
        float r03 = r9.getX() - this.touchStartX;
        float r22 = r9.getY() - this.touchStartY;
        if (Math.abs(r03) <= Math.max(AndroidUtilities.dp(24.0f), AndroidUtilities.getPixelsInCM(0.3f, true))) goto L38;
        if (Math.abs(r03) <= Math.abs(r22)) goto L38;
        this.clickHelper.cancel(this, r9.getX(), r9.getY());
        this.touchStartView.cancelLongPress();
        this.touchStartView.setPressed(false);
        if (getParent() == null) goto L26;
        getParent().requestDisallowInterceptTouchEvent(true);
    L26:
        performHapticFeedback(0);
        this.manuallyStartedLongMove = true;
        startTabsLongMove(this.touchStartX, this.touchStartY);
        checkPivot(this, r9.getX(), r9.getY());
        checkLongMove(r9.getX(), r9.getY(), false, false);
        return true;
    L38:
        this.clickHelper.onTouchEvent(this, r9);
        return super.dispatchTouchEvent(r9);
    }

    @Override // org.telegram.ui.Components.AnimatedLinearLayout
    public void onItemsChanged() {
        super.onItemsChanged();
        checkVisualWidth();
    }

    @Override // org.telegram.ui.Components.AnimatedLinearLayout, android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        checkVisualWidth();
    }

    @Override // org.telegram.ui.Components.AnimatedLinearLayout, android.widget.LinearLayout, android.view.View
    public void onMeasure(int r17, int r18) {
        int r1 = View.MeasureSpec.getSize(r17);
        int r2 = View.MeasureSpec.getSize(r18);
        int r3 = (r2 - getPaddingTop()) - getPaddingBottom();
        int r4 = this.maxWidthPx;
        if (r4 <= 0) goto L6;
        if (r1 <= r4) goto L6;
        r1 = r4;
    L6:
        int r12 = (r1 - getPaddingLeft()) - getPaddingRight();
        int r42 = Math.min(AndroidUtilities.dp(320.0f), r12);
        int r5 = PASS_TEXT_SIZES_DP.length - 1;
        float r8 = -1.0f;
        int r9 = 0;
    L7:
        float[] r10 = PASS_TEXT_SIZES_DP;
        float r122 = 0.0f;
        if (r9 >= r10.length) goto L26;
        float r11 = r10[r9];
        if (r11 == r8) goto L12;
        measureTabTexts(r11);
        r8 = r10[r9];
    L12:
        int r102 = AndroidUtilities.dp(PASS_PADDINGS_DP[r9]);
        int r112 = getChildCount();
        float r14 = 0.0f;
        int r13 = 0;
    L13:
        if (r13 >= r112) goto L19;
        if (isViewVisible(getChildAt(r13)) == false) goto L18;
        r14 = r14 + (this.tabsTextWidth[r13] + (r102 * 2));
    L18:
        r13 = r13 + 1;
        goto L13
    L19:
        int r172 = 0;
        if (r14 <= r12) goto L24;
        if (r9 == (PASS_TEXT_SIZES_DP.length - 1)) goto L24;
        r9 = r9 + 1;
    L24:
        r5 = r9;
    L27:
        applyPassTextSize(r5);
        int r52 = AndroidUtilities.dp(PASS_PADDINGS_DP[r5]);
        if (this.fillAvailableWidth == false) goto L38;
        int r7 = this.visibleChildCount;
        if (r7 <= 0) goto L38;
        float r15 = r12 / r7;
        int r43 = getChildCount();
        int r53 = r172;
    L32:
        if (r53 >= r43) goto L92;
        if (isViewVisible(getChildAt(r53)) == true) goto L36;
        float[] r6 = this.tabsTextWidth;
        this.tabsTextWidthWithMargin[r53] = 0.0f;
        r6[r53] = 0.0f;
        this.tabsWeight[r53] = r172;
    L37:
        r53 = r53 + 1;
        goto L32
    L36:
        this.tabsTextWidthWithMargin[r53] = r15;
        this.tabsWeight[r53] = r172;
    L92:
        int r16 = getChildCount();
        int r44 = r172;
        int r54 = r44;
    L93:
        if (r44 >= r16) goto L99;
        if (isViewVisible(getChildAt(r44)) == false) goto L98;
        this.tabsWidth[r44] = Math.round(this.tabsTextWidthWithMargin[r44]);
        this.tabsLeftPos[r44] = r54;
        r54 = r54 + this.tabsWidth[r44];
    L98:
        r44 = r44 + 1;
        goto L93
    L99:
        setMeasuredDimension((r54 + getPaddingLeft()) + getPaddingRight(), r2);
        int r19 = getChildCount();
        int r72 = r172;
    L100:
        if (r72 >= r19) goto L102;
        getChildAt(r72).measure(View.MeasureSpec.makeMeasureSpec(this.tabsWidth[r72], TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r3, TLObject.FLAG_30));
        r72 = r72 + 1;
        goto L100
    L102:
        calculateTotalSizesAfterMeasure();
        return;
    L38:
        int r55 = r52 * 2;
        int r73 = (r12 / Math.max(1, this.visibleChildCount)) - r55;
        int r82 = getChildCount();
        int r92 = r172;
        int r113 = r92;
        float r103 = 0.0f;
    L39:
        if (r92 >= r82) goto L50;
        if (isViewVisible(getChildAt(r92)) == true) goto L43;
        float[] r132 = this.tabsTextWidth;
        this.tabsTextWidthWithMargin[r92] = 0.0f;
        r132[r92] = 0.0f;
        this.tabsWeight[r92] = r172;
    L48:
        r92 = r92 + 1;
        goto L39
    L43:
        float[] r133 = this.tabsTextWidthWithMargin;
        float r142 = this.tabsTextWidth[r92] + r55;
        r133[r92] = r142;
        int[] r134 = this.tabsWeight;
        if (r142 <= (r73 + r55)) goto L46;
        int r152 = r172;
    L47:
        r134[r92] = r152;
        r103 = r103 + r142;
        r113 = r113 + r152;
        goto L48
    L46:
        r152 = 1;
        goto L47
    L50:
        if (this.visibleChildCount <= 0) goto L76;
        float r56 = this.biggestTabTextWidth + (AndroidUtilities.dp(16.0f) * 2);
        float r62 = r42 / this.visibleChildCount;
        if (r56 >= r62) goto L61;
        int r63 = getChildCount();
        int r74 = r172;
    L54:
        if (r74 >= r63) goto L59;
        if (isViewVisible(getChildAt(r74)) == false) goto L58;
        this.tabsTextWidthWithMargin[r74] = r56;
        r122 = r122 + r56;
        this.tabsWeight[r74] = r172;
    L58:
        r74 = r74 + 1;
        goto L54
    L59:
        r113 = -1;
    L60:
        r103 = r122;
        goto L76
    L61:
        int r57 = getChildCount();
        int r75 = r172;
    L62:
        if (r75 >= r57) goto L69;
        if (isViewVisible(getChildAt(r75)) == false) goto L68;
        if ((this.tabsTextWidth[r75] + (AndroidUtilities.dp(13.0f) * 2)) > r62) goto L76;
    L68:
        r75 = r75 + 1;
        goto L62
    L69:
        int r58 = getChildCount();
        int r76 = r172;
    L70:
        if (r76 >= r58) goto L75;
        if (isViewVisible(getChildAt(r76)) == false) goto L74;
        this.tabsTextWidthWithMargin[r76] = r62;
        r122 = r122 + r62;
        this.tabsWeight[r76] = r172;
    L74:
        r76 = r76 + 1;
        goto L70
    L75:
        r113 = r172;
    L76:
        if (r113 != 0) goto L81;
        int r59 = getChildCount();
        int r64 = r172;
    L78:
        if (r64 >= r59) goto L80;
        this.tabsWeight[r64] = isViewVisible(getChildAt(r64)) ? 1 : 0;
        r64 = r64 + 1;
        goto L78
    L80:
        r113 = this.visibleChildCount;
    L81:
        float r110 = r12;
        if (r103 <= r110) goto L86;
        float r111 = r110 / r103;
        int r45 = getChildCount();
        int r510 = r172;
    L84:
        if (r510 >= r45) goto L92;
        float[] r65 = this.tabsTextWidthWithMargin;
        r65[r510] = r65[r510] * r111;
        r510 = r510 + 1;
        goto L84
    L86:
        float r114 = r42;
        if (r103 >= r114) goto L92;
        if (r113 <= 0) goto L92;
        float r115 = (r114 - r103) / r113;
        int r46 = getChildCount();
        int r511 = r172;
    L90:
        if (r511 >= r46) goto L92;
        float[] r66 = this.tabsTextWidthWithMargin;
        r66[r511] = r66[r511] + (this.tabsWeight[r511] * r115);
        r511 = r511 + 1;
        goto L90
    L26:
        r172 = 0;
        goto L27
    }

    @Override // org.telegram.ui.Components.AnimatedLinearLayout
    public void setChildVisibilityFactor(View r2, float r3) {
        float r1 = AndroidUtilities.lerp(0.7f, 1.0f, r3);
        r2.setAlpha(r3);
        r2.setScaleX(r1);
        r2.setScaleY(r1);
    }

    public void setDrawTopDivider(boolean r2) {
        if (this.drawTopDivider == r2) goto L6;
        this.drawTopDivider = r2;
        invalidate();
        return;
    }

    public void setFillAvailableWidth(boolean r2) {
        if (this.fillAvailableWidth == r2) goto L6;
        this.fillAvailableWidth = r2;
        requestLayout();
        return;
    }

    public void setMaxWidth(int r2) {
        if (this.maxWidthPx == r2) goto L6;
        this.maxWidthPx = r2;
        requestLayout();
        return;
    }

    @Override // android.view.View
    public void setScaleX(float r1) {
        super.setScaleX(r1);
        checkLayerType();
    }

    @Override // android.view.View
    public void setScaleY(float r1) {
        super.setScaleY(r1);
        checkLayerType();
    }

    public void setSwipeSelectionEnabled(boolean r1) {
        this.swipeSelectionEnabled = r1;
    }

    public void setTabSelected(View r6, boolean r7) {
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        View r3 = getChildAt(r2);
        if ((r3 instanceof GlassTabView) == false) goto L11;
        GlassTabView r4 = (GlassTabView) r3;
        if (r3 != r6) goto L9;
        boolean r32 = true;
    L10:
        r4.setSelected(r32, r7);
        goto L11
    L9:
        r32 = false;
    L11:
        r2 = r2 + 1;
        goto L3
    }
}
