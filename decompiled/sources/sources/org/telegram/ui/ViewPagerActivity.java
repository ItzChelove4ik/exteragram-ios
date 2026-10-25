package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.config.BottomNavigationBar;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ViewPagerFixed;

/* loaded from: classes3.dex */
public abstract class ViewPagerActivity extends BaseFragment {
    protected FrameLayout contentView;
    protected final SparseArray<FragmentState> fragmentsArr;
    private int initialFragmentPosition;
    private boolean isFullyVisible;
    private boolean isResumed;
    private WindowInsetsCompat lastWindowInsets;
    private String titleOverlay;
    private Runnable titleOverlayAction;
    private int titleOverlayId;
    protected ViewPagerActivityPagerLayout viewPager;
    private float visibilityByParent;

    /* renamed from: org.telegram.ui.ViewPagerActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPagerFixed.Adapter {
        final /* synthetic */ ViewPagerActivity this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass1(ViewPagerActivity r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r3, int r4, int r5) {
            FragmentState r52 = this.this$0.fragmentsArr.get(r4);
            if (r52 == null) goto L5;
            BaseFragment r42 = r52.fragment;
        L7:
            if (FragmentState.m21146$$Nest$fgetonCreateCalled(r52) == true) goto L9;
            r42.onFragmentCreate();
            FragmentState.m21148$$Nest$fputonCreateCalled(r52, true);
        L9:
            r42.setParentLayout(this.this$0.getParentLayout());
            if (r42.getFragmentView() != null) goto L12;
            r42.performCreateView(this.val$context);
            r42.setTitleOverlayText(ViewPagerActivity.m21140$$Nest$fgettitleOverlay(this.this$0), ViewPagerActivity.m21142$$Nest$fgettitleOverlayId(this.this$0), ViewPagerActivity.m21141$$Nest$fgettitleOverlayAction(this.this$0));
        L12:
            FrameLayout r32 = (FrameLayout) r3;
            r32.removeAllViews();
            View r53 = r42.getFragmentView();
            AndroidUtilities.removeFromParent(r53);
            if (r42.hasOwnBackground() == false) goto L15;
        L17:
            r32.addView(r53, LayoutHelper.createFrame(-1, -1.0f));
            if (r42.getActionBar() == null) goto L23;
            if (r42.getActionBar().shouldAddToContainer() == false) goto L23;
            AndroidUtilities.removeFromParent(r42.getActionBar());
            r32.addView(r42.getActionBar());
        L23:
            if (ViewPagerActivity.m21139$$Nest$fgetlastWindowInsets(this.this$0) == null) goto L25;
            ViewCompat.dispatchApplyWindowInsets(r53, ViewPagerActivity.m21139$$Nest$fgetlastWindowInsets(this.this$0));
        L26:
            ViewPagerActivity.access$000(this.this$0);
            ViewPagerActivity.m21143$$Nest$mcheckFragmentsVisibility(this.this$0);
            return;
        L25:
            ViewCompat.requestApplyInsets(r32);
            goto L26
        L15:
            if (r53.getBackground() != null) goto L17;
            r53.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L17
        L5:
            BaseFragment r54 = this.this$0.createBaseFragmentAt(r4);
            FragmentState r0 = new FragmentState(r54, null);
            this.this$0.fragmentsArr.put(r4, r0);
            r42 = r54;
            r52 = r0;
            goto L7
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public boolean canScrollTo(int r1) {
            return this.this$0.canScrollToPage(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r1) {
            return new ViewPagerFragmentRootLayout(this.val$context);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return this.this$0.getFragmentsCount();
        }
    }

    public static class FragmentState {
        public final BaseFragment fragment;
        private boolean isFullyVisible;
        private boolean isInAnimation;
        private boolean isResumed;
        private float lastVisibility;
        private boolean onCreateCalled;

        /* renamed from: -$$Nest$fgetisFullyVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21144$$Nest$fgetisFullyVisible(FragmentState r0) {
            return r0.isFullyVisible;
        }

        /* renamed from: -$$Nest$fgetisResumed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21145$$Nest$fgetisResumed(FragmentState r0) {
            return r0.isResumed;
        }

        /* renamed from: -$$Nest$fgetonCreateCalled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21146$$Nest$fgetonCreateCalled(FragmentState r0) {
            return r0.onCreateCalled;
        }

        /* renamed from: -$$Nest$fputisResumed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21147$$Nest$fputisResumed(FragmentState r0, boolean r1) {
            r0.isResumed = r1;
        }

        /* renamed from: -$$Nest$fputonCreateCalled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21148$$Nest$fputonCreateCalled(FragmentState r0, boolean r1) {
            r0.onCreateCalled = r1;
        }

        private FragmentState(BaseFragment r1) {
            this.fragment = r1;
        }

        public /* synthetic */ FragmentState(BaseFragment r1, ViewPagerActivityIA r2) {
            this(r1);
        }

        public void setVisibility(float r8, float r9, boolean r10, boolean r11) {
            float r0 = this.lastVisibility;
            float r92 = r9 * r8;
            this.lastVisibility = r92;
            if (r92 <= r0) goto L5;
            boolean r1 = true;
        L7:
            if (this.isResumed == true) goto L15;
            if (r8 <= 0.0f) goto L15;
            if (r11 == false) goto L15;
            BaseFragment r4 = this.fragment;
            if (r4.fragmentView == null) goto L15;
            r4.onResume();
            this.isResumed = true;
        L15:
            if (this.isInAnimation == true) goto L26;
            if (r0 == 0.0f) goto L21;
            if (r0 != 1.0f) goto L26;
        L21:
            if (r0 == r92) goto L26;
            if (Math.abs(r0 - r92) == 1.0f) goto L26;
            this.fragment.onTransitionAnimationStart(r1, false);
            this.isInAnimation = true;
        L26:
            if (this.isInAnimation == false) goto L35;
            if (r0 == r92) goto L35;
            BaseFragment r02 = this.fragment;
            if (r1 == false) goto L32;
            float r42 = r92;
        L33:
            r02.onTransitionAnimationProgress(r1, r42);
            goto L35
        L32:
            r42 = 1.0f - r92;
        L35:
            if (this.isInAnimation == false) goto L42;
            if (r92 != 0.0f) goto L39;
        L40:
            this.fragment.onTransitionAnimationEnd(r1, false);
            this.isInAnimation = false;
            goto L42
        L39:
            if (r92 == 1.0f) goto L40;
        L42:
            if (this.isFullyVisible == true) goto L47;
            if (r92 < 1.0f) goto L47;
            this.fragment.onBecomeFullyVisible();
            this.isFullyVisible = true;
        L47:
            if (this.isFullyVisible == false) goto L55;
            if (r92 != 0.0f) goto L52;
            if (r10 == true) goto L52;
        L53:
            this.fragment.onBecomeFullyHidden();
            this.isFullyVisible = false;
        L52:
            if (r8 == 0.0f) goto L53;
        L55:
            if (this.isResumed == true) goto L57;
            return;
        L57:
            if (r92 != 0.0f) goto L60;
            if (r11 == true) goto L60;
        L61:
            this.fragment.onPause();
            this.isResumed = false;
            return;
        L60:
            if (r8 == 0.0f) goto L61;
            return;
        L5:
            r1 = false;
            goto L7
        }
    }

    public class ViewPagerActivityPagerLayout extends ViewPagerFixed {
        private final Path clipPath;
        private boolean tabletLayout;
        final /* synthetic */ ViewPagerActivity this$0;

        public ViewPagerActivityPagerLayout(ViewPagerActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.clipPath = new Path();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScrollBackward(MotionEvent r1) {
            return this.this$0.canScrollBackward(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScrollForward(MotionEvent r1) {
            return this.this$0.canScrollForward(r1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            if (this.tabletLayout == false) goto L5;
            this.clipPath.rewind();
            float r0 = AndroidUtilities.dpf2(24.0f);
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set(0.0f, AndroidUtilities.statusBarHeight, getWidth(), getHeight());
            this.clipPath.addRoundRect(r1, r0, r0, Path.Direction.CW);
            r7.save();
            r7.clipPath(this.clipPath);
        L5:
            super.dispatchDraw(r7);
            if (this.tabletLayout == false) goto L9;
            r7.restore();
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public float getAvailableTranslationX() {
            return getMeasuredWidth();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public long getManualScrollDuration() {
            return 320;
        }

        public int getTargetPosition() {
            if (this.viewPages[1] == null) goto L8;
            int r0 = this.nextPosition;
            if (r0 < 0) goto L8;
            return r0;
        L8:
            return getCurrentPosition();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onItemSelected(View r1, View r2, int r3, int r4) {
            super.onItemSelected(r1, r2, r3, r4);
            ViewPagerActivity.m21143$$Nest$mcheckFragmentsVisibility(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onScrollEnd() {
            super.onScrollEnd();
            this.this$0.onViewPagerScrollEnd();
            ViewPagerActivity.m21143$$Nest$mcheckFragmentsVisibility(this.this$0);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r2) {
            super.onTabAnimationUpdate(r2);
            this.this$0.onViewPagerTabAnimationUpdate(r2);
            ViewPagerActivity.m21143$$Nest$mcheckFragmentsVisibility(this.this$0);
            ViewPagerActivity.access$100(this.this$0);
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams r1) {
            super.setLayoutParams(r1);
        }

        public void setTabletLayout(boolean r2) {
            if (this.tabletLayout != r2) goto L5;
            return;
        L5:
            this.tabletLayout = r2;
            invalidate();
        }
    }

    public static class ViewPagerFragmentRootLayout extends FrameLayout {
        public ViewPagerFragmentRootLayout(Context r1) {
            super(r1);
        }
    }

    /* renamed from: -$$Nest$fgetlastWindowInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowInsetsCompat m21139$$Nest$fgetlastWindowInsets(ViewPagerActivity r0) {
        return r0.lastWindowInsets;
    }

    /* renamed from: -$$Nest$fgettitleOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21140$$Nest$fgettitleOverlay(ViewPagerActivity r0) {
        return r0.titleOverlay;
    }

    /* renamed from: -$$Nest$fgettitleOverlayAction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m21141$$Nest$fgettitleOverlayAction(ViewPagerActivity r0) {
        return r0.titleOverlayAction;
    }

    /* renamed from: -$$Nest$fgettitleOverlayId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21142$$Nest$fgettitleOverlayId(ViewPagerActivity r0) {
        return r0.titleOverlayId;
    }

    /* renamed from: -$$Nest$mcheckFragmentsVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21143$$Nest$mcheckFragmentsVisibility(ViewPagerActivity r0) {
        r0.checkFragmentsVisibility();
    }

    public ViewPagerActivity() {
        this.fragmentsArr = new SparseArray();
        this.initialFragmentPosition = -1;
        this.visibilityByParent = 0.0f;
    }

    public static /* synthetic */ void access$000(ViewPagerActivity r0) {
        r0.checkSystemBarColors();
    }

    public static /* synthetic */ void access$100(ViewPagerActivity r0) {
        r0.checkSystemBarColors();
    }

    private void checkFragmentsVisibility() {
        int r0 = this.fragmentsArr.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L14;
        FragmentState r2 = this.fragmentsArr.valueAt(r1);
        int r3 = this.fragmentsArr.keyAt(r1);
        if (r2 == null) goto L13;
        if (r2.fragment.fragmentView == null) goto L13;
        float r32 = this.viewPager.getPositionVisibility(r3);
        boolean r4 = this.isResumed;
        if (r4 == false) goto L11;
        float r5 = this.visibilityByParent;
    L12:
        r2.setVisibility(r32, r5, this.isFullyVisible, r4);
        goto L13
    L11:
        r5 = 0.0f;
    L13:
        r1 = r1 + 1;
        goto L3
    }

    public abstract boolean canScrollBackward(MotionEvent r1);

    public abstract boolean canScrollForward(MotionEvent r1);

    public abstract boolean canScrollToPage(int r1);

    public void clearAllHiddenFragments() {
        int r0 = this.viewPager.getCurrentPosition();
        int r1 = this.fragmentsArr.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L10;
        FragmentState r3 = this.fragmentsArr.valueAt(r2);
        if (this.fragmentsArr.keyAt(r2) == r0) goto L9;
        if (r3 == null) goto L9;
        r3.fragment.clearViews();
    L9:
        r2 = r2 + 1;
        goto L3
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void clearViews() {
        if (this.viewPager != null) goto L5;
    L9:
        int r0 = this.fragmentsArr.size();
        int r2 = 0;
    L10:
        if (r2 >= r0) goto L18;
        FragmentState r3 = this.fragmentsArr.valueAt(r2);
        if (r3 == null) goto L17;
        if (FragmentState.m21145$$Nest$fgetisResumed(r3) == false) goto L16;
        r3.fragment.onPause();
        FragmentState.m21147$$Nest$fputisResumed(r3, false);
    L16:
        r3.fragment.clearViews();
    L17:
        r2 = r2 + 1;
        goto L10
    L18:
        super.clearViews();
        return;
    L5:
        if (BottomNavigationBar.hidden() == false) goto L7;
        int r02 = 0;
    L8:
        this.initialFragmentPosition = r02;
        goto L9
    L7:
        r02 = this.viewPager.getCurrentPosition();
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ActionBar createActionBar(Context r1) {
        return null;
    }

    public abstract BaseFragment createBaseFragmentAt(int r1);

    public abstract FrameLayout createContentView(Context r1);

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r4) {
        this.hasOwnBackground = true;
        this.contentView = createContentView(r4);
        this.viewPager = new ViewPagerActivityPagerLayout(this, r4);
        if (this.initialFragmentPosition != (-1)) goto L5;
        this.initialFragmentPosition = getStartPosition();
    L5:
        this.viewPager.setPosition(this.initialFragmentPosition);
        this.viewPager.setAdapter(new AnonymousClass1(this, r4));
        this.contentView.addView(this.viewPager, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r42 = this.contentView;
        this.fragmentView = r42;
        ViewCompat.setOnApplyWindowInsetsListener(r42, new ViewPagerActivity$$ExternalSyntheticLambda0(this));
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    public void dropFragmentAtPosition(int r3) {
        FragmentState r0 = this.fragmentsArr.get(r3);
        if (r0 != null) goto L5;
    L11:
        this.fragmentsArr.remove(r3);
        return;
    L5:
        if (FragmentState.m21144$$Nest$fgetisFullyVisible(r0) == false) goto L8;
        r0.fragment.onBecomeFullyHidden();
    L8:
        if (FragmentState.m21145$$Nest$fgetisResumed(r0) == false) goto L10;
        r0.fragment.onPause();
    L10:
        r0.fragment.onFragmentDestroy();
        r0.fragment.setParentLayout(null);
        goto L11
    }

    public BaseFragment getCurrentVisibleFragment() {
        ViewPagerActivityPagerLayout r0 = this.viewPager;
        if (r0 != null) goto L5;
        return null;
    L5:
        int r02 = r0.getCurrentPosition();
        FragmentState r2 = this.fragmentsArr.get(r02);
        if (r2 != null) goto L8;
        return null;
    L8:
        return r2.fragment;
    }

    public abstract int getFragmentsCount();

    public abstract int getStartPosition();

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r0 = new ArrayList();
        int r1 = this.fragmentsArr.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L10;
        FragmentState r3 = this.fragmentsArr.valueAt(r2);
        if (r3 == null) goto L9;
        BaseFragment r32 = r3.fragment;
        if (r32.fragmentView == null) goto L9;
        r0.addAll(r32.getThemeDescriptions());
    L9:
        r2 = r2 + 1;
        goto L3
    L10:
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L9;
        if (r0.fragmentView == null) goto L9;
        return r0.isLightStatusBar();
    L9:
        return super.isLightStatusBar();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    public WindowInsetsCompat onApplyWindowInsets(View r3, WindowInsetsCompat r4) {
        this.lastWindowInsets = r4;
        int r32 = this.fragmentsArr.size();
        int r0 = 0;
    L3:
        if (r0 >= r32) goto L11;
        FragmentState r1 = this.fragmentsArr.valueAt(r0);
        if (r1 == null) goto L9;
        View r12 = r1.fragment.getFragmentView();
        if (r12 == null) goto L9;
        ViewCompat.dispatchApplyWindowInsets(r12, r4);
    L9:
        r0 = r0 + 1;
        goto L3
    L11:
        return WindowInsetsCompat.CONSUMED;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (hasShownSheet() == false) goto L7;
        if (r3 == false) goto L6;
        closeSheet();
    L6:
        return false;
    L7:
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L13;
        if (r0.onBackPressed(r3) == true) goto L13;
        return false;
    L13:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        super.onBecomeFullyHidden();
        this.visibilityByParent = 0.0f;
        this.isFullyVisible = false;
        checkFragmentsVisibility();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        this.visibilityByParent = 1.0f;
        this.isFullyVisible = true;
        checkFragmentsVisibility();
        checkSystemBarColors();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        int r0 = this.fragmentsArr.size();
        int r1 = 0;
    L3:
        SparseArray<FragmentState> r2 = this.fragmentsArr;
        if (r1 >= r0) goto L9;
        FragmentState r22 = r2.valueAt(r1);
        if (FragmentState.m21146$$Nest$fgetonCreateCalled(r22) == false) goto L8;
        r22.fragment.onFragmentDestroy();
        r22.fragment.setParentLayout(null);
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        r2.clear();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        this.isResumed = false;
        checkFragmentsVisibility();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L6;
        r0.onRequestPermissionsResultFragment(r1, r2, r3);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.isResumed = true;
        checkSystemBarColors();
        checkFragmentsVisibility();
    }

    public abstract void onViewPagerScrollEnd();

    public abstract void onViewPagerTabAnimationUpdate(boolean r1);

    public void putFragmentAtPosition(int r3, BaseFragment r4) {
        this.fragmentsArr.put(r3, new FragmentState(r4, null));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setTitleOverlayText(String r4, int r5, Runnable r6) {
        super.setTitleOverlayText(r4, r5, r6);
        this.titleOverlay = r4;
        this.titleOverlayId = r5;
        this.titleOverlayAction = r6;
        int r0 = this.fragmentsArr.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        FragmentState r2 = this.fragmentsArr.valueAt(r1);
        if (r2 == null) goto L7;
        r2.fragment.setTitleOverlayText(r4, r5, r6);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setTitleOverlayTextIfActionBarAttached(String r1, int r2, Runnable r3) {
        setTitleOverlayText(r1, r2, r3);
    }
}
