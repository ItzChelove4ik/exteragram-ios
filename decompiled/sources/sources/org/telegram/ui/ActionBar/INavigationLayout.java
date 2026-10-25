package org.telegram.ui.ActionBar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.core.util.Supplier;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.List;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackButtonMenu;

/* loaded from: classes3.dex */
public interface INavigationLayout {

    /* renamed from: org.telegram.ui.ActionBar.INavigationLayout$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ActionBarLayout {
        final /* synthetic */ Supplier val$supplier;

        public AnonymousClass1(Context r1, boolean r2, Supplier r3) {
            this.val$supplier = r3;
            super(r1, r2);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarLayout, org.telegram.ui.ActionBar.INavigationLayout
        public BottomSheet getBottomSheet() {
            return (BottomSheet) this.val$supplier.get();
        }
    }

    public enum BackButtonState extends Enum<BackButtonState> {
        private static final /* synthetic */ BackButtonState[] $VALUES = null;
        public static final BackButtonState BACK = null;
        public static final BackButtonState MENU = null;

        private static /* synthetic */ BackButtonState[] $values() {
            return new BackButtonState[]{BACK, MENU};
        }

        static {
            BACK = new BackButtonState("BACK", 0);
            MENU = new BackButtonState("MENU", 1);
            $VALUES = $values();
        }

        BackButtonState(String r1, int r2) {
        }

        public static BackButtonState valueOf(String r1) {
            return (BackButtonState) Enum.valueOf(BackButtonState.class, r1);
        }

        public static BackButtonState[] values() {
            return (BackButtonState[]) $VALUES.clone();
        }
    }

    public interface INavigationLayoutDelegate {
        default boolean needAddFragmentToStack(BaseFragment r1, INavigationLayout r2) {
            return true;
        }

        boolean needCloseLastFragment(INavigationLayout r1);

        default boolean needPresentFragment(BaseFragment r1, boolean r2, boolean r3, INavigationLayout r4) {
            return true;
        }

        default boolean needPresentFragment(INavigationLayout r3, NavigationParams r4) {
            return needPresentFragment(r4.fragment, r4.removeLast, r4.noAnimation, r3);
        }

        default void onMeasureOverride(int[] r1) {
        }

        default boolean onPreIme() {
            return false;
        }

        default void onRebuildAllFragments(INavigationLayout r1, boolean r2) {
        }

        default void onThemeProgress(float r1) {
        }
    }

    public static class NavigationParams {
        public boolean checkPresentFromDelegate;
        public boolean forceRightLayout;
        public BaseFragment fragment;
        public ActionBarPopupWindow.ActionBarPopupWindowLayout menuView;
        public boolean noAnimation;
        public boolean preview;
        public boolean removeLast;

        public NavigationParams(BaseFragment r2) {
            this.checkPresentFromDelegate = true;
            this.fragment = r2;
        }

        public NavigationParams forceRightLayout() {
            this.forceRightLayout = true;
            return this;
        }

        public NavigationParams setCheckPresentFromDelegate(boolean r1) {
            this.checkPresentFromDelegate = r1;
            return this;
        }

        public NavigationParams setMenuView(ActionBarPopupWindow.ActionBarPopupWindowLayout r1) {
            this.menuView = r1;
            return this;
        }

        public NavigationParams setNoAnimation(boolean r1) {
            this.noAnimation = r1;
            return this;
        }

        public NavigationParams setPreview(boolean r1) {
            this.preview = r1;
            return this;
        }

        public NavigationParams setRemoveLast(boolean r1) {
            this.removeLast = r1;
            return this;
        }
    }

    public static class StartColorsProvider implements Theme.ResourcesProvider {
        SparseIntArray colors;
        int[] keysToSave;

        public StartColorsProvider() {
            this.colors = new SparseIntArray();
            this.keysToSave = new int[]{Theme.key_chat_outBubble, Theme.key_chat_outBubbleGradient1, Theme.key_chat_outBubbleGradient2, Theme.key_chat_outBubbleGradient3, Theme.key_chat_outBubbleGradientAnimated, Theme.key_chat_outBubbleShadow};
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getColor(int r2) {
            int r0 = this.colors.indexOfKey(r2);
            if (r0 < 0) goto L7;
            return this.colors.valueAt(r0);
        L7:
            return Theme.getColor(r2);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getCurrentColor(int r1) {
            return this.colors.get(r1);
        }

        public void saveColors(Theme.ResourcesProvider r7) {
            this.colors.clear();
            int[] r0 = this.keysToSave;
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            int r3 = r0[r2];
            this.colors.put(r3, r7.getCurrentColor(r3));
            r2 = r2 + 1;
            goto L3
        }
    }

    /* loaded from: classes6.dex */
    public static class ThemeAnimationSettings {
        public final int accentId;
        public Runnable afterAnimationRunnable;
        public Runnable afterStartDescriptionsAddedRunnable;
        public onAnimationProgress animationProgress;
        public boolean applyTheme;
        public boolean applyTrulyTheme;
        public Runnable beforeAnimationRunnable;
        public long duration;
        public final boolean instant;
        public final boolean nightTheme;
        public boolean onlyTopFragment;
        public Theme.ResourcesProvider resourcesProvider;
        public final Theme.ThemeInfo theme;

        public interface onAnimationProgress {
            void setProgress(float r1);
        }

        public ThemeAnimationSettings(Theme.ThemeInfo r3, int r4, boolean r5, boolean r6) {
            this.applyTheme = true;
            this.applyTrulyTheme = true;
            this.duration = 200;
            this.theme = r3;
            this.accentId = r4;
            this.nightTheme = r5;
            this.instant = r6;
        }
    }

    static INavigationLayout newLayout(Context r1, boolean r2) {
        return new ActionBarLayout(r1, r2);
    }

    static INavigationLayout newLayout(Context r1, boolean r2, Supplier<BottomSheet> r3) {
        return new AnonymousClass1(r1, r2, r3);
    }

    default boolean addFragmentToStack(BaseFragment r2) {
        return addFragmentToStack(r2, -1);
    }

    boolean addFragmentToStack(BaseFragment r1, int r2);

    boolean allowSwipe();

    void animateThemedValues(ThemeAnimationSettings r1, Runnable r2);

    default void animateThemedValues(Theme.ThemeInfo r2, int r3, boolean r4, boolean r5) {
        animateThemedValues(new ThemeAnimationSettings(r2, r3, r4, r5), null);
    }

    default void animateThemedValues(Theme.ThemeInfo r2, int r3, boolean r4, boolean r5, Runnable r6) {
        animateThemedValues(new ThemeAnimationSettings(r2, r3, r4, r5), r6);
    }

    boolean checkTransitionAnimation();

    default void closeLastFragment() {
        closeLastFragment(true);
    }

    void closeLastFragment(boolean r1);

    void closeLastFragment(boolean r1, boolean r2);

    default void dismissDialogs() {
        List<BaseFragment> r1 = getFragmentStack();
        if (r1.isEmpty() == true) goto L6;
        r1.get(r1.size() - 1).dismissCurrentDialog();
        return;
    }

    void drawCurrentPreviewFragment(Canvas r1, Drawable r2);

    void drawHeaderShadow(Canvas r1, int r2);

    void drawHeaderShadow(Canvas r1, int r2, int r3);

    void expandPreviewFragment();

    default <T extends BaseFragment> T findFragment(Class<T> r5) {
        if (getFragmentStack().isEmpty() == false) goto L5;
        return null;
    L5:
        int r0 = getFragmentStack().size() - 1;
    L6:
        if (r0 < 0) goto L18;
        T r2 = (T) getFragmentStack().get(r0);
        if (r2 == null) goto L17;
        if (r2.isFinishing() == true) goto L17;
        if (r2.isRemovingFromStack() == true) goto L17;
        if (r5.isInstance(r2) == false) goto L17;
        return r2;
    L17:
        r0 = r0 - 1;
        goto L6
    L18:
        return null;
    }

    void finishPreviewFragment();

    default BaseFragment getBackgroundFragment() {
        if (getFragmentStack().size() > 1) goto L7;
        return null;
    L7:
        return getFragmentStack().get(getFragmentStack().size() - 2);
    }

    default BottomSheet getBottomSheet() {
        return null;
    }

    int getBottomTabsHeight(boolean r1);

    float getCurrentPreviewFragmentAlpha();

    List<BaseFragment> getFragmentStack();

    BaseFragment getLastFragment();

    MessageDrawable getMessageDrawableOutMediaStart();

    MessageDrawable getMessageDrawableOutStart();

    FrameLayout getOverlayContainerView();

    default Activity getParentActivity() {
        Context r1 = getView().getContext();
        if ((r1 instanceof Activity) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("NavigationLayout added in non-activity context!");
        return null;
    L5:
        return (Activity) r1;
    }

    List<BackButtonMenu.PulledDialog> getPulledDialogs();

    default BaseFragment getSafeLastFragment() {
        List<BaseFragment> r4 = getFragmentStack();
        if (r4 != null) goto L5;
    L18:
        return null;
    L5:
        if (r4.isEmpty() == true) goto L18;
        int r1 = r4.size() - 1;
    L8:
        if (r1 < 0) goto L18;
        BaseFragment r2 = r4.get(r1);
        if (r2 == null) goto L17;
        if (r2.isFinishing() == true) goto L17;
        if (r2.isRemovingFromStack() == true) goto L17;
        return r2;
    L17:
        r1 = r1 - 1;
        goto L8
    }

    float getThemeAnimationValue();

    /* JADX WARN: Multi-variable type inference failed */
    default ViewGroup getView() {
        if ((this instanceof ViewGroup) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("You should override getView() if you're not inheriting from it.");
        return null;
    L5:
        return (ViewGroup) this;
    }

    Window getWindow();

    default boolean hasIntegratedBlurInPreview() {
        return false;
    }

    default boolean isActionBarInCrossfade() {
        return false;
    }

    boolean isInBubbleMode();

    boolean isInPassivePreviewMode();

    boolean isInPreviewMode();

    boolean isIncomingFragmentTouchable();

    boolean isLayersLayout();

    boolean isPreviewOpenAnimationInProgress();

    boolean isRightLayout();

    boolean isSheet();

    boolean isSwipeInProgress();

    boolean isTransitionAnimationInProgress();

    void movePreviewFragment(float r1);

    void onBackPressed();

    void onLowMemory();

    void onPause();

    void onResume();

    default boolean presentFragment(BaseFragment r2) {
        return presentFragment(new NavigationParams(r2));
    }

    default boolean presentFragment(BaseFragment r2, boolean r3) {
        return presentFragment(new NavigationParams(r2).setRemoveLast(r3));
    }

    @Deprecated
    default boolean presentFragment(BaseFragment r2, boolean r3, boolean r4, boolean r5, boolean r6) {
        return presentFragment(new NavigationParams(r2).setRemoveLast(r3).setNoAnimation(r4).setCheckPresentFromDelegate(r5).setPreview(r6));
    }

    @Deprecated
    default boolean presentFragment(BaseFragment r2, boolean r3, boolean r4, boolean r5, boolean r6, ActionBarPopupWindow.ActionBarPopupWindowLayout r7) {
        return presentFragment(new NavigationParams(r2).setRemoveLast(r3).setNoAnimation(r4).setCheckPresentFromDelegate(r5).setPreview(r6).setMenuView(r7));
    }

    boolean presentFragment(NavigationParams r1);

    default boolean presentFragmentAsPreview(BaseFragment r2) {
        return presentFragment(new NavigationParams(r2).setPreview(true));
    }

    default boolean presentFragmentAsPreviewWithMenu(BaseFragment r2, ActionBarPopupWindow.ActionBarPopupWindowLayout r3) {
        return presentFragment(new NavigationParams(r2).setPreview(true).setMenuView(r3));
    }

    @Deprecated
    void rebuildAllFragmentViews(boolean r1, boolean r2);

    default void rebuildFragments(int r2) {
        if ((r2 & 2) == 0) goto L6;
        showLastFragment();
        return;
    L6:
        boolean r0 = true;
        if ((r2 & 1) != 0) goto L10;
        r0 = false;
    L10:
        rebuildAllFragmentViews(r0, r0);
    }

    void removeAllFragments();

    default void removeFragmentFromStack(int r2) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r2 >= getFragmentStack().size()) goto L9;
        removeFragmentFromStack(getFragmentStack().get(r2));
        return;
    }

    default void removeFragmentFromStack(BaseFragment r2) {
        removeFragmentFromStack(r2, false);
    }

    void removeFragmentFromStack(BaseFragment r1, boolean r2);

    void resumeDelayedFragmentAnimation();

    void setBackgroundView(View r1);

    void setDelegate(INavigationLayoutDelegate r1);

    void setDrawerLayoutContainer(DrawerLayoutContainer r1);

    void setFragmentPanTranslationOffset(int r1);

    void setFragmentStack(List<BaseFragment> r1);

    void setHighlightActionButtons(boolean r1);

    void setInBubbleMode(boolean r1);

    void setIsSheet(boolean r1);

    void setNavigationBarColor(int r1);

    void setPulledDialogs(List<BackButtonMenu.PulledDialog> r1);

    void setRemoveActionBarExtraHeight(boolean r1);

    void setUseAlphaAnimations(boolean r1);

    void setWindow(Window r1);

    @Deprecated
    void showLastFragment();

    void startActivityForResult(Intent r1, int r2);

    void updateTitleOverlay();
}
