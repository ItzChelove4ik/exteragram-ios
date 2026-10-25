package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.pillstack.core.PillRegistry;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.ui.PillStackView;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import java.util.ArrayList;
import me.vkryl.android.AnimatorUtils;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class FragmentSearchField extends FrameLayout implements FactorAnimator.Target, Theme.Colorable, NotificationCenter.NotificationCenterDelegate {
    private final LinearLayout additionalIconsLayout;
    private final BoolAnimator animatorCloseIconVisible;
    private final FactorAnimator animatorSearchFiltersWidth;
    private final BoolAnimator animatorSearchIconVisible;
    private Drawable bg;
    private BlurredBackgroundDrawable blurredBackgroundDrawable;
    private boolean closeButtonForcedVisible;
    private final ImageView closeIcon;
    private final Path contentClipPath;
    private final float contentClipRadius;
    private final RectF contentClipRect;
    private final ArrayList<FiltersView.MediaFilterData> currentSearchFilters;
    public final EditTextBoldCursor editText;
    public boolean isSectionBackground;
    private boolean isWhiteBackground;
    private final AnimationNotificationsLocker notificationsLocker;
    private Runnable onCloseSearch;
    private PillStackView pillStackView;
    private final Theme.ResourcesProvider resourcesProvider;
    private final LinearLayout searchFilterLayout;
    private SearchFiltersListener searchFiltersListener;
    private final ImageView searchIcon;
    private int selectedFilterIndex;
    private boolean showPillStack;

    /* renamed from: org.telegram.ui.Components.FragmentSearchField$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextBoldCursor {
        final /* synthetic */ FragmentSearchField this$0;

        public AnonymousClass1(FragmentSearchField r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int r2, KeyEvent r3) {
            if (r2 != 67) goto L16;
            if (this.this$0.editText.length() != 0) goto L16;
            if (FragmentSearchField.m10810$$Nest$mhasRemovableFilters(this.this$0) == false) goto L16;
            if (FragmentSearchField.m10810$$Nest$mhasRemovableFilters(this.this$0) == false) goto L14;
            FiltersView.MediaFilterData r22 = (FiltersView.MediaFilterData) FragmentSearchField.m10804$$Nest$fgetcurrentSearchFilters(this.this$0).get(FragmentSearchField.m10804$$Nest$fgetcurrentSearchFilters(this.this$0).size() - 1);
            if (FragmentSearchField.m10806$$Nest$fgetsearchFiltersListener(this.this$0) == null) goto L13;
            FragmentSearchField.m10806$$Nest$fgetsearchFiltersListener(this.this$0).onSearchFilterCleared(r22);
        L13:
            this.this$0.removeSearchFilter(r22);
        L14:
            return true;
        L16:
            return super.onKeyDown(r2, r3);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setPivotX(getPaddingLeft());
            setPivotY(getMeasuredHeight() / 2.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentSearchField$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ FragmentSearchField this$0;

        public AnonymousClass2(FragmentSearchField r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            if (FragmentSearchField.m10804$$Nest$fgetcurrentSearchFilters(this.this$0).isEmpty() == false) goto L5;
        L9:
            FragmentSearchField.m10809$$Nest$mcheckCloseButtonVisible(this.this$0);
            return;
        L5:
            if (r2.length() <= 0) goto L9;
            if (FragmentSearchField.m10807$$Nest$fgetselectedFilterIndex(this.this$0) < 0) goto L9;
            FragmentSearchField.m10808$$Nest$fputselectedFilterIndex(this.this$0, -1);
            FragmentSearchField.m10811$$Nest$monFiltersChanged(this.this$0);
            goto L9
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentSearchField$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayout {
        final /* synthetic */ FragmentSearchField this$0;

        public AnonymousClass3(FragmentSearchField r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
            FragmentSearchField.m10803$$Nest$fgetanimatorSearchFiltersWidth(this.this$0).animateTo(getMeasuredWidth());
            super.onLayout(r3, r4, r5, r6, r7);
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentSearchField$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends Visibility {
        final /* synthetic */ FragmentSearchField this$0;

        public AnonymousClass4(FragmentSearchField r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.Visibility
        public Animator onAppear(ViewGroup r3, View r4, TransitionValues r5, TransitionValues r6) {
            if ((r4 instanceof ActionBarMenuItem.SearchFilterView) == false) goto L7;
            AnimatorSet r2 = new AnimatorSet();
            r2.playTogether(new Animator[]{ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(r4, View.SCALE_X, new float[]{0.5f, 1.0f}), ObjectAnimator.ofFloat(r4, View.SCALE_Y, new float[]{0.5f, 1.0f})});
            r2.setInterpolator(CubicBezierInterpolator.DEFAULT);
            return r2;
        L7:
            return ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{0.0f, 1.0f});
        }

        @Override // android.transition.Visibility
        public Animator onDisappear(ViewGroup r7, View r8, TransitionValues r9, TransitionValues r10) {
            if ((r8 instanceof ActionBarMenuItem.SearchFilterView) == false) goto L7;
            AnimatorSet r6 = new AnimatorSet();
            r6.playTogether(new Animator[]{ObjectAnimator.ofFloat(r8, View.ALPHA, new float[]{r8.getAlpha(), 0.0f}), ObjectAnimator.ofFloat(r8, View.SCALE_X, new float[]{r8.getScaleX(), 0.5f}), ObjectAnimator.ofFloat(r8, View.SCALE_Y, new float[]{r8.getScaleX(), 0.5f})});
            r6.setInterpolator(CubicBezierInterpolator.DEFAULT);
            return r6;
        L7:
            return ObjectAnimator.ofFloat(r8, View.ALPHA, new float[]{1.0f, 0.0f});
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentSearchField$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 implements Transition.TransitionListener {
        final /* synthetic */ FragmentSearchField this$0;

        public AnonymousClass5(FragmentSearchField r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
            FragmentSearchField.m10805$$Nest$fgetnotificationsLocker(this.this$0).unlock();
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
            FragmentSearchField.m10805$$Nest$fgetnotificationsLocker(this.this$0).unlock();
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
            FragmentSearchField.m10805$$Nest$fgetnotificationsLocker(this.this$0).lock();
        }
    }

    public interface SearchFiltersListener {
        void hideActionMode();

        void onSearchFilterCleared(FiltersView.MediaFilterData r1);
    }

    public static /* synthetic */ void $r8$lambda$24YV0D9Cgm5yhyO_oTUAoTb8koo(FragmentSearchField r0, PillStackView r1) {
        r0.lambda$updatePillStack$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$K3qvKTMVwz6VzjB_bLbXI_GI3Ng(FragmentSearchField r0, ActionBarMenuItem.SearchFilterView r1, View r2) {
        r0.lambda$onFiltersChanged$4(r1, r2);
    }

    /* renamed from: $r8$lambda$OWi5Q-VWia_9_ZSCiNt6aPJibxA, reason: not valid java name */
    public static /* synthetic */ void m10802$r8$lambda$OWi5QVWia_9_ZSCiNt6aPJibxA(FragmentSearchField r0, PillStackView r1) {
        r0.lambda$updatePillStack$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$uUZO5eRlNdSBn6HAkofW5Y5tjBw(FragmentSearchField r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$uehZ77z0l4mjPueaZOoPA3AP9B4(FragmentSearchField r0, PillStackView r1) {
        r0.lambda$updatePillStack$1(r1);
    }

    /* renamed from: -$$Nest$fgetanimatorSearchFiltersWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FactorAnimator m10803$$Nest$fgetanimatorSearchFiltersWidth(FragmentSearchField r0) {
        return r0.animatorSearchFiltersWidth;
    }

    /* renamed from: -$$Nest$fgetcurrentSearchFilters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10804$$Nest$fgetcurrentSearchFilters(FragmentSearchField r0) {
        return r0.currentSearchFilters;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m10805$$Nest$fgetnotificationsLocker(FragmentSearchField r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetsearchFiltersListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchFiltersListener m10806$$Nest$fgetsearchFiltersListener(FragmentSearchField r0) {
        return r0.searchFiltersListener;
    }

    /* renamed from: -$$Nest$fgetselectedFilterIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10807$$Nest$fgetselectedFilterIndex(FragmentSearchField r0) {
        return r0.selectedFilterIndex;
    }

    /* renamed from: -$$Nest$fputselectedFilterIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10808$$Nest$fputselectedFilterIndex(FragmentSearchField r0, int r1) {
        r0.selectedFilterIndex = r1;
    }

    /* renamed from: -$$Nest$mcheckCloseButtonVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10809$$Nest$mcheckCloseButtonVisible(FragmentSearchField r0) {
        r0.checkCloseButtonVisible();
    }

    /* renamed from: -$$Nest$mhasRemovableFilters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10810$$Nest$mhasRemovableFilters(FragmentSearchField r0) {
        return r0.hasRemovableFilters();
    }

    /* renamed from: -$$Nest$monFiltersChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10811$$Nest$monFiltersChanged(FragmentSearchField r0) {
        r0.onFiltersChanged();
    }

    public FragmentSearchField(Context r14, Theme.ResourcesProvider r15) {
        super(r14);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorCloseIconVisible = new BoolAnimator(0, this, r3, 380, false);
        this.animatorSearchIconVisible = new BoolAnimator(1, this, r3, 380, true);
        this.animatorSearchFiltersWidth = new FactorAnimator(2, this, AnimatorUtils.DECELERATE_INTERPOLATOR, 280);
        this.contentClipPath = new Path();
        this.contentClipRect = new RectF();
        this.contentClipRadius = AndroidUtilities.dp(20.0f);
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.currentSearchFilters = new ArrayList();
        this.resourcesProvider = r15;
        AnonymousClass1 r13 = new AnonymousClass1(this, r14);
        this.editText = r13;
        r13.setTextSize(1, 15.0f);
        r13.setTypeface(AndroidUtilities.regular());
        r13.setCursorWidth(1.5f);
        r13.setInputType(r13.getInputType() | 176);
        r13.setSingleLine(true);
        r13.setBackground(null);
        r13.setVerticalScrollBarEnabled(false);
        r13.setHorizontalScrollBarEnabled(false);
        r13.setPadding(AndroidUtilities.dp(48.0f), 0, AndroidUtilities.dp(48.0f), 0);
        r13.setClipToPadding(true);
        r13.setImeOptions(268435459);
        int r1 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r0 = 5;
    L6:
        r13.setGravity(r0 | 16);
        r13.addTextChangedListener(new AnonymousClass2(this));
        if (Build.VERSION.SDK_INT < 35) goto L9;
        r13.setLocalePreferredLineHeightForMinimumUsed(false);
    L9:
        addView(r13, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 0.0f));
        ImageView r132 = new ImageView(r14);
        this.searchIcon = r132;
        ImageView.ScaleType r02 = ImageView.ScaleType.CENTER;
        r132.setScaleType(r02);
        r132.setImageResource(R.drawable.outline_search_1_24);
        if (LocaleController.isRTL == false) goto L12;
        int r4 = 5;
    L13:
        addView(r132, LayoutHelper.createFrame(24, 24.0f, r4 | 16, 12.0f, 0.0f, 12.0f, 0.0f));
        LinearLayout r133 = new LinearLayout(r14);
        this.additionalIconsLayout = r133;
        r133.setOrientation(0);
        if (LocaleController.isRTL == false) goto L16;
        int r42 = 3;
    L17:
        addView(r133, LayoutHelper.createFrame(-2, -1.0f, r42 | 16, 32.0f, 0.0f, 32.0f, 0.0f));
        ImageView r134 = new ImageView(r14);
        this.closeIcon = r134;
        r134.setScaleType(r02);
        r134.setImageResource(R.drawable.miniplayer_close);
        r134.setVisibility(8);
        r134.setOnClickListener(new FragmentSearchField$$ExternalSyntheticLambda0(this));
        if (LocaleController.isRTL == false) goto L20;
        int r142 = 3;
    L21:
        addView(r134, LayoutHelper.createFrame(24, 24.0f, r142 | 16, 12.0f, 0.0f, 12.0f, 0.0f));
        AnonymousClass3 r135 = new AnonymousClass3(this, getContext());
        this.searchFilterLayout = r135;
        r135.setOrientation(0);
        r135.setVisibility(0);
        if (LocaleController.isRTL == false) goto L24;
        r1 = 5;
    L24:
        addView(r135, LayoutHelper.createFrame(-2, 32.0f, r1 | 16, 4.0f, 0.0f, 4.0f, 0.0f));
        setWillNotDraw(false);
        checkUi_editTextPaddings();
        updateColors();
        return;
    L20:
        r142 = 5;
        goto L21
    L16:
        r42 = 5;
        goto L17
    L12:
        r4 = 3;
        goto L13
    L5:
        r0 = 3;
        goto L6
    }

    private void checkCloseButtonVisible() {
        BoolAnimator r0 = this.animatorCloseIconVisible;
        if (this.closeButtonForcedVisible == false) goto L5;
    L8:
        boolean r3 = true;
    L9:
        r0.setValue(r3, true);
        return;
    L5:
        if (this.editText.length() > 0) goto L8;
        r3 = false;
        goto L9
    }

    private void checkUi_editTextPaddings() {
        int r0 = Math.max(((int) this.animatorSearchFiltersWidth.getFactor()) + AndroidUtilities.dp(6.0f), AndroidUtilities.dp(48.0f));
        int r1 = AndroidUtilities.dp(48.0f) + this.additionalIconsLayout.getMeasuredWidth();
        boolean r2 = LocaleController.isRTL;
        if (r2 == false) goto L5;
        int r3 = r1;
    L6:
        if (r2 == true) goto L9;
        r0 = r1;
    L9:
        Rect r12 = AndroidUtilities.rectTmp2;
        r12.set(r3, 0, this.editText.getMeasuredWidth() - r0, this.editText.getMeasuredHeight());
        this.editText.setClipBounds(r12);
        this.editText.setPadding(r3, 0, r0, 0);
        return;
    L5:
        r3 = r0;
        goto L6
    }

    private BasePill getPill(Integer r2) {
        PillRegistry.PillInfo r22 = PillRegistry.getPillInfo(r2.intValue());
        if (r22 != null) goto L5;
        return null;
    L5:
        if (r22.creator() != null) goto L7;
        return null;
    L7:
        return r22.creator().create(getContext(), this.resourcesProvider);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private int getThemedColor(int r1, float r2) {
        return Theme.multAlpha(getThemedColor(r1), r2);
    }

    private boolean hasRemovableFilters() {
        if (this.currentSearchFilters.isEmpty() == false) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.currentSearchFilters.size()) goto L13;
        if (this.currentSearchFilters.get(r0).removable == true) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$new$0(View r3) {
        if (hasRemovableFilters() == false) goto L18;
        SearchFiltersListener r32 = this.searchFiltersListener;
        if (r32 == null) goto L7;
        r32.hideActionMode();
    L7:
        int r33 = 0;
    L9:
        if (r33 >= this.currentSearchFilters.size()) goto L16;
        if (this.searchFiltersListener == null) goto L15;
        if (this.currentSearchFilters.get(r33).removable == false) goto L15;
        this.searchFiltersListener.onSearchFilterCleared(this.currentSearchFilters.get(r33));
    L15:
        r33 = r33 + 1;
        goto L9
    L16:
        clearSearchFilters();
        return;
    L18:
        Runnable r34 = this.onCloseSearch;
        if (r34 == null) goto L22;
        r34.run();
        return;
    L22:
        this.editText.getText().clear();
    }

    private /* synthetic */ void lambda$onFiltersChanged$4(ActionBarMenuItem.SearchFilterView r2, View r3) {
        int r32 = this.currentSearchFilters.indexOf(r2.getFilter());
        if (this.selectedFilterIndex == r32) goto L7;
        this.selectedFilterIndex = r32;
        onFiltersChanged();
        return;
    L7:
        if (r2.getFilter().removable == true) goto L9;
        return;
    L9:
        if (r2.isSelectedForDelete() == true) goto L12;
        r2.setSelectedForDelete(true);
        return;
    L12:
        FiltersView.MediaFilterData r22 = r2.getFilter();
        removeSearchFilter(r22);
        SearchFiltersListener r1 = this.searchFiltersListener;
        if (r1 == null) goto L17;
        r1.onSearchFilterCleared(r22);
        return;
    }

    private /* synthetic */ void lambda$updatePillStack$1(PillStackView r2) {
        if (this.pillStackView != r2) goto L6;
        r2.setVisibilityFactor(1.0f - this.animatorCloseIconVisible.getFloatValue());
        return;
    }

    private /* synthetic */ void lambda$updatePillStack$2(PillStackView r1) {
        removeView(r1);
    }

    private /* synthetic */ void lambda$updatePillStack$3(PillStackView r1) {
        removeView(r1);
    }

    private void onFiltersChanged() {
        boolean r1 = this.currentSearchFilters.isEmpty();
        this.animatorSearchIconVisible.setValue(r1, true);
        ArrayList r2 = new ArrayList(this.currentSearchFilters);
        TransitionSet r4 = new TransitionSet();
        ChangeBounds r5 = new ChangeBounds();
        r5.setDuration(150);
        r4.addTransition(new AnonymousClass4(this).setDuration(150)).addTransition(r5);
        r4.setOrdering(0);
        r4.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r4.addListener(new AnonymousClass5(this));
        TransitionManager.beginDelayedTransition(this.searchFilterLayout, r4);
        int r42 = 0;
    L4:
        if (r42 >= this.searchFilterLayout.getChildCount()) goto L9;
        if (r2.remove(((ActionBarMenuItem.SearchFilterView) this.searchFilterLayout.getChildAt(r42)).getFilter()) == true) goto L8;
        this.searchFilterLayout.removeViewAt(r42);
        r42 = r42 - 1;
    L8:
        r42 = r42 + 1;
        goto L4
    L9:
        int r43 = 0;
    L11:
        if (r43 >= r2.size()) goto L24;
        FiltersView.MediaFilterData r6 = (FiltersView.MediaFilterData) r2.get(r43);
        if (r6.reaction == null) goto L15;
        ActionBarMenuItem.SearchFilterView r7 = new ActionBarMenuItem.ReactionFilterView(getContext(), this.resourcesProvider, false);
    L16:
        r7.setGlass();
        r7.setData(r6);
        r7.setOnClickListener(new FragmentSearchField$$ExternalSyntheticLambda4(this, r7));
        LinearLayout r62 = this.searchFilterLayout;
        boolean r8 = LocaleController.isRTL;
        if (r8 == false) goto L19;
        int r13 = 6;
    L20:
        if (r8 == false) goto L22;
        int r15 = 0;
    L23:
        r62.addView(r7, LayoutHelper.createLinear(-2, -1, 0, r13, 0, r15, 0));
        r43 = r43 + 1;
        goto L11
    L22:
        r15 = 6;
        goto L23
    L19:
        r13 = 0;
        goto L20
    L15:
        r7 = new ActionBarMenuItem.SearchFilterView(getContext(), this.resourcesProvider, false);
        goto L16
    L24:
        int r22 = 0;
    L25:
        int r44 = this.searchFilterLayout.getChildCount();
        LinearLayout r63 = this.searchFilterLayout;
        if (r22 >= r44) goto L32;
        ActionBarMenuItem.SearchFilterView r45 = (ActionBarMenuItem.SearchFilterView) r63.getChildAt(r22);
        if (r22 != this.selectedFilterIndex) goto L30;
        boolean r64 = true;
    L31:
        r45.setExpanded(r64);
        r22 = r22 + 1;
        goto L25
    L30:
        r64 = false;
        goto L31
    L32:
        if (r1 == true) goto L34;
        Integer r0 = 1;
    L35:
        r63.setTag(r0);
        return;
    L34:
        r0 = null;
        goto L35
    }

    private void updatePillStack(boolean r18) {
        if (this.showPillStack == true) goto L5;
    L46:
        PillStackView r1 = this.pillStackView;
        if (r1 == null) goto L62;
        this.pillStackView = null;
        if (r18 == true) goto L51;
    L56:
        removeView(r1);
        return;
    L51:
        if (r1.getVisibility() != 0) goto L56;
        if (r1.getAlpha() <= 0.0f) goto L56;
        r1.animate().alpha(0.0f).scaleX(0.6f).scaleY(0.6f).setDuration(250).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new FragmentSearchField$$ExternalSyntheticLambda3(this, r1)).start();
        return;
    L62:
        return;
    L5:
        if (PillStackConfig.getActivePills().isEmpty() == true) goto L46;
        int r8 = 0;
        if (this.pillStackView != null) goto L14;
        PillStackView r12 = new PillStackView(getContext());
        this.pillStackView = r12;
        if (LocaleController.isRTL == false) goto L12;
        int r9 = 3;
    L13:
        addView(r12, LayoutHelper.createFrame(-2, -1.0f, r9, 6.0f, 0.0f, 6.0f, 0.0f));
        boolean r13 = true;
    L15:
        PillStackView r92 = this.pillStackView;
        if (r13 == true) goto L18;
    L21:
        r92.clearPills();
        int r14 = 0;
    L23:
        if (r8 >= PillStackConfig.getActivePills().size()) goto L31;
        Integer r10 = PillStackConfig.getActivePills().get(r8);
        BasePill r11 = getPill(r10);
        if (r11 == null) goto L29;
        r92.addPill(r11);
        if (r10.intValue() != PillStackConfig.getLastActivePillId()) goto L29;
        r14 = r92.getPillsCount() - 1;
    L29:
        r8 = r8 + 1;
        goto L23
    L31:
        if (r92.getPillsCount() == 0) goto L33;
        r92.setCurrentIndex(r14);
        return;
    L33:
        if (this.pillStackView != r92) goto L35;
        this.pillStackView = null;
    L35:
        if (r18 == true) goto L37;
    L42:
        removeView(r92);
        return;
    L37:
        if (r92.getVisibility() != 0) goto L42;
        if (r92.getAlpha() <= 0.0f) goto L42;
        r92.animate().alpha(0.0f).scaleX(0.6f).scaleY(0.6f).setDuration(250).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new FragmentSearchField$$ExternalSyntheticLambda2(this, r92)).start();
        return;
    L18:
        if (r18 == false) goto L20;
        r92.setAlpha(0.0f);
        r92.setScaleX(0.6f);
        r92.setScaleY(0.6f);
        r92.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(250).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new FragmentSearchField$$ExternalSyntheticLambda1(this, r92)).start();
        goto L21
    L20:
        r92.setVisibilityFactor(1.0f - this.animatorCloseIconVisible.getFloatValue());
        goto L21
    L12:
        r9 = 5;
        goto L13
    L14:
        r13 = false;
        goto L15
    }

    public void addSearchFilter(FiltersView.MediaFilterData r2) {
        this.currentSearchFilters.add(r2);
        this.selectedFilterIndex = this.currentSearchFilters.size() - 1;
        onFiltersChanged();
    }

    public void clearSearchFilters() {
        int r0 = 0;
    L4:
        if (r0 >= this.currentSearchFilters.size()) goto L9;
        if (this.currentSearchFilters.get(r0).removable == false) goto L8;
        this.currentSearchFilters.remove(r0);
        r0 = r0 - 1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        onFiltersChanged();
    }

    public void clearSearchFiltersWithCallback() {
        if (this.currentSearchFilters.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.searchFiltersListener == null) goto L17;
        int r0 = 0;
    L8:
        if (r0 >= this.currentSearchFilters.size()) goto L18;
        if (this.currentSearchFilters.get(r0).removable == false) goto L12;
        this.searchFiltersListener.onSearchFilterCleared(this.currentSearchFilters.get(r0));
    L12:
        r0 = r0 + 1;
        goto L8
    L18:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.pillStackLayoutChanged) goto L6;
        updatePillStack(true);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        r8.save();
        Drawable r0 = this.bg;
        if (r0 == null) goto L5;
        r0.setBounds(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        this.bg.draw(r8);
    L5:
        BlurredBackgroundDrawable r02 = this.blurredBackgroundDrawable;
        if (r02 == null) goto L8;
        r02.setBounds(getPaddingLeft() - AndroidUtilities.dp(4.0f), getPaddingTop() - AndroidUtilities.dp(4.0f), (getWidth() - getPaddingRight()) + AndroidUtilities.dp(4.0f), (getHeight() - getPaddingBottom()) + AndroidUtilities.dp(4.0f));
        this.blurredBackgroundDrawable.draw(r8);
    L9:
        super.dispatchDraw(r8);
        r8.restore();
        return;
    L8:
        this.contentClipRect.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        this.contentClipPath.reset();
        Path r03 = this.contentClipPath;
        RectF r1 = this.contentClipRect;
        float r2 = this.contentClipRadius;
        r03.addRoundRect(r1, r2, r2, Path.Direction.CW);
        r8.clipPath(this.contentClipPath);
        goto L9
    }

    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pillStackLayoutChanged);
        updatePillStack(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pillStackLayoutChanged);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L8;
        FragmentFloatingButton.setAnimatedVisibility(this.closeIcon, r2);
        float r32 = 1.0f - r2;
        this.closeIcon.setRotation(90.0f * r32);
        PillStackView r0 = this.pillStackView;
        if (r0 == null) goto L15;
        r0.setVisibilityFactor(r32);
        return;
    L15:
        return;
    L8:
        if (r1 != 1) goto L12;
        FragmentFloatingButton.setAnimatedVisibility(this.searchIcon, r2);
        return;
    L12:
        if (r1 != 2) goto L16;
        checkUi_editTextPaddings();
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        checkUi_editTextPaddings();
    }

    public void removeSearchFilter(FiltersView.MediaFilterData r2) {
        if (r2.removable == false) goto L15;
        this.currentSearchFilters.remove(r2);
        int r22 = this.selectedFilterIndex;
        if (r22 >= 0) goto L8;
    L9:
        this.selectedFilterIndex = this.currentSearchFilters.size() - 1;
    L10:
        onFiltersChanged();
        SearchFiltersListener r1 = this.searchFiltersListener;
        if (r1 == null) goto L14;
        r1.hideActionMode();
        return;
    L14:
        return;
    L8:
        if (r22 <= (this.currentSearchFilters.size() - 1)) goto L10;
    }

    public void setBlurredBackgroundVisibility(float r4) {
        int r42 = (int) (r4 * 255.0f);
        BlurredBackgroundDrawable r0 = this.blurredBackgroundDrawable;
        boolean r1 = true;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        Drawable r2 = this.bg;
        if (r2 == null) goto L13;
        int r43 = 255 - r42;
        if (r2.getAlpha() == r43) goto L13;
        this.bg.setAlpha(r43);
    L14:
        if (r1 == false) goto L17;
        invalidate();
        return;
    L17:
        return;
    L13:
        r1 = r02;
        goto L14
    L5:
        if (r0.getAlpha() == r42) goto L7;
        this.blurredBackgroundDrawable.setAlpha(r42);
        r02 = true;
        goto L8
    }

    public void setCloseButtonOnClickListener(Runnable r1) {
        this.onCloseSearch = r1;
    }

    public void setCloseButtonVisible(boolean r1) {
        this.closeButtonForcedVisible = r1;
        checkCloseButtonVisible();
    }

    public void setSearchFiltersListener(SearchFiltersListener r1) {
        this.searchFiltersListener = r1;
    }

    public void setSectionBackground() {
        this.isSectionBackground = true;
        setPadding(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
        updateColors();
    }

    public void setWhiteBackground() {
        this.isWhiteBackground = true;
        updateColors();
    }

    public void setupBlurredBackground(BlurredBackgroundDrawable r2) {
        r2.setRadius(AndroidUtilities.dp(20.0f));
        r2.setPadding(AndroidUtilities.dp(4.0f));
        this.blurredBackgroundDrawable = r2;
    }

    public void showPillStack() {
        this.showPillStack = true;
        updatePillStack(true);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        boolean r02 = r0.isDark();
    L7:
        if (this.isSectionBackground == false) goto L9;
        Drawable r03 = Theme.createRoundRectDrawableShadowed(AndroidUtilities.dp(20.0f), getThemedColor(Theme.key_windowBackgroundWhite));
    L18:
        this.bg = r03;
        ImageView r04 = this.searchIcon;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        int r3 = getThemedColor(r1, 0.6f);
        PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
        r04.setColorFilter(r3, r4);
        this.closeIcon.setColorFilter(getThemedColor(r1, 0.6f), r4);
        this.closeIcon.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector), 1, AndroidUtilities.dp(17.0f)));
        this.editText.setHintTextColor(getThemedColor(r1, 0.5f));
        this.editText.setTextColor(getThemedColor(r1));
        this.editText.setCursorColor(getThemedColor(Theme.key_groupcreate_cursor));
        BlurredBackgroundDrawable r05 = this.blurredBackgroundDrawable;
        if (r05 == null) goto L21;
        r05.updateColors();
    L21:
        int r06 = this.additionalIconsLayout.getChildCount();
        int r12 = 0;
        int r32 = 0;
    L22:
        if (r32 >= r06) goto L30;
        View r5 = this.additionalIconsLayout.getChildAt(r32);
        if ((r5 instanceof ActionBarMenuItem) == false) goto L29;
        ActionBarMenuItem r7 = (ActionBarMenuItem) r5;
        if (r7.getIconView() == null) goto L28;
        r7.getIconView().setColorFilter(getThemedColor(Theme.key_windowBackgroundWhiteBlackText, 0.6f), PorterDuff.Mode.MULTIPLY);
    L28:
        r5.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector), 1, AndroidUtilities.dp(17.0f)));
    L29:
        r32 = r32 + 1;
        goto L22
    L30:
        int r07 = this.searchFilterLayout.getChildCount();
    L31:
        if (r12 >= r07) goto L36;
        if ((this.searchFilterLayout.getChildAt(r12) instanceof ActionBarMenuItem.SearchFilterView) == false) goto L35;
        ((ActionBarMenuItem.SearchFilterView) this.searchFilterLayout.getChildAt(r12)).updateColors();
    L35:
        r12 = r12 + 1;
        goto L31
    L36:
        PillStackView r08 = this.pillStackView;
        if (r08 == null) goto L39;
        r08.updateColors();
    L39:
        invalidate();
        return;
    L9:
        int r13 = AndroidUtilities.dp(20.0f);
        if (this.isWhiteBackground == false) goto L12;
        int r09 = getThemedColor(Theme.key_windowBackgroundWhite);
    L17:
        r03 = Theme.createRoundRectDrawable(r13, r09);
        goto L18
    L12:
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        if (r02 == false) goto L15;
        float r010 = 0.07f;
    L16:
        r09 = getThemedColor(r2, r010);
        goto L17
    L15:
        r010 = 0.05f;
        goto L16
    L5:
        r02 = Theme.isCurrentThemeDark();
        goto L7
    }
}
