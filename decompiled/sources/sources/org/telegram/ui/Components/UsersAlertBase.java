package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.TextWatcherImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.GroupCallTextCell;
import org.telegram.ui.Cells.GroupCallUserCell;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public abstract class UsersAlertBase extends BottomSheet {
    public static final Property<UsersAlertBase, Float> COLOR_PROGRESS = null;
    private int backgroundColor;
    private float colorProgress;
    private boolean drawTitle;
    protected StickerEmptyView emptyView;
    protected FlickerLoadingView flickerLoadingView;
    protected FrameLayout frameLayout;
    protected boolean isEmptyViewVisible;
    protected int keyActionBarUnscrolled;
    protected int keyInviteMembersBackground;
    protected int keyLastSeenText;
    protected int keyLastSeenTextUnscrolled;
    protected int keyListSelector;
    protected int keyListViewBackground;
    protected int keyNameText;
    protected int keyScrollUp;
    protected int keySearchBackground;
    protected int keySearchIcon;
    protected int keySearchIconUnscrolled;
    protected int keySearchPlaceholder;
    protected int keySearchText;
    protected final FillLastLinearLayoutManager layoutManager;
    protected RecyclerListView listView;
    protected RecyclerView.Adapter listViewAdapter;
    protected boolean needSnapToTop;
    private RectF rect;
    protected int scrollOffsetY;
    protected RecyclerView.Adapter searchListViewAdapter;
    protected SearchField searchView;
    protected View shadow;
    protected AnimatorSet shadowAnimation;
    protected Drawable shadowDrawable;
    private TextView titleView;

    /* renamed from: org.telegram.ui.Components.UsersAlertBase$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ UsersAlertBase this$0;

        public AnonymousClass1(UsersAlertBase r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean emptyViewIsVisible() {
            if (getAdapter() != null) goto L6;
            return false;
        L6:
            if (this.this$0.isEmptyViewVisible == true) goto L8;
        L11:
            return false;
        L8:
            if (getAdapter().getItemCount() > 2) goto L11;
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            getLocationInWindow(new int[2]);
        }
    }

    /* renamed from: org.telegram.ui.Components.UsersAlertBase$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ UsersAlertBase this$0;

        public AnonymousClass2(UsersAlertBase r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            if (r3 != 0) goto L15;
            UsersAlertBase r22 = this.this$0;
            if (r22.needSnapToTop == true) goto L6;
            return;
        L6:
            if (((r22.scrollOffsetY + UsersAlertBase.access$000(r22)) + AndroidUtilities.dp(13.0f)) < (AndroidUtilities.statusBarHeight * 2)) goto L8;
            return;
        L8:
            if (this.this$0.listView.canScrollVertically(1) == false) goto L18;
            RecyclerListView.Holder r23 = (RecyclerListView.Holder) this.this$0.listView.findViewHolderForAdapterPosition(0);
            if (r23 != null) goto L12;
            return;
        L12:
            if (r23.itemView.getTop() <= 0) goto L20;
            this.this$0.listView.smoothScrollBy(0, r23.itemView.getTop());
            return;
        L20:
            return;
        L18:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            this.this$0.updateLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.UsersAlertBase$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimationProperties.FloatProperty<UsersAlertBase> {
        public AnonymousClass3(String r1) {
            super(r1);
        }

        public Float get(UsersAlertBase r1) {
            return Float.valueOf(UsersAlertBase.m13500$$Nest$mgetColorProgress(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((UsersAlertBase) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(UsersAlertBase r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(UsersAlertBase r1, float r2) {
            r1.setColorProgress(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.UsersAlertBase$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ UsersAlertBase this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass4(UsersAlertBase r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            AnimatorSet r0 = this.this$0.shadowAnimation;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.equals(r2) == false) goto L9;
            this.this$0.shadowAnimation = null;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            AnimatorSet r0 = this.this$0.shadowAnimation;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.equals(r2) == true) goto L7;
            return;
        L7:
            if (this.val$show == true) goto L9;
            this.this$0.shadow.setVisibility(4);
        L9:
            this.this$0.shadowAnimation = null;
        }
    }

    /* renamed from: org.telegram.ui.Components.UsersAlertBase$5, reason: invalid class name */
    public class AnonymousClass5 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ UsersAlertBase this$0;
        final /* synthetic */ int val$from;

        public AnonymousClass5(UsersAlertBase r1, int r2) {
            this.this$0 = r1;
            this.val$from = r2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.this$0.listView.getViewTreeObserver().removeOnPreDrawListener(this);
            int r0 = this.this$0.listView.getChildCount();
            AnimatorSet r1 = new AnimatorSet();
            int r3 = 0;
        L4:
            if (r3 >= r0) goto L16;
            View r5 = this.this$0.listView.getChildAt(r3);
            int r6 = this.this$0.listView.getChildAdapterPosition(r5);
            if (r6 < this.val$from) goto L15;
            if (r6 == 1) goto L10;
        L14:
            r5.setAlpha(0.0f);
            int r62 = (int) ((Math.min(this.this$0.listView.getMeasuredHeight(), Math.max(0, r5.getTop())) / this.this$0.listView.getMeasuredHeight()) * 100.0f);
            ObjectAnimator r52 = ObjectAnimator.ofFloat(r5, View.ALPHA, new float[]{0.0f, 1.0f});
            r52.setStartDelay(r62);
            r52.setDuration(200);
            r1.playTogether(new Animator[]{r52});
            goto L15
        L10:
            if (this.this$0.listView.getAdapter() != this.this$0.searchListViewAdapter) goto L14;
            if ((r5 instanceof GraySectionCell) == false) goto L14;
            r5 = ((GraySectionCell) r5).getTextView();
        L15:
            r3 = r3 + 1;
            goto L4
        L16:
            r1.start();
            return true;
        }
    }

    public class ContainerView extends FrameLayout {
        private boolean ignoreLayout;
        float snapToTopOffset;
        private Boolean statusBarOpen;
        final /* synthetic */ UsersAlertBase this$0;
        ValueAnimator valueAnimator;

        /* renamed from: org.telegram.ui.Components.UsersAlertBase$ContainerView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ ContainerView this$1;

            public AnonymousClass1(ContainerView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                super.onAnimationEnd(r2);
                ContainerView r22 = this.this$1;
                r22.snapToTopOffset = 0.0f;
                r22.setTranslationY(0.0f);
                this.this$1.valueAnimator = null;
            }
        }

        /* renamed from: $r8$lambda$sxu2Q8auMAFU-JY1FtuCiIssSEY, reason: not valid java name */
        public static /* synthetic */ void m13501$r8$lambda$sxu2Q8auMAFUJY1FtuCiIssSEY(ContainerView r0, ValueAnimator r1) {
            r0.lambda$onMeasure$0(r1);
        }

        public ContainerView(UsersAlertBase r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.ignoreLayout = false;
        }

        private /* synthetic */ void lambda$onMeasure$0(ValueAnimator r1) {
            float r12 = ((Float) r1.getAnimatedValue()).floatValue();
            this.snapToTopOffset = r12;
            setTranslationY(r12);
        }

        private void updateLightStatusBar(boolean r7) {
            Boolean r0 = this.statusBarOpen;
            if (r0 != null) goto L5;
        L7:
            boolean r2 = false;
            if (AndroidUtilities.computePerceivedBrightness(UsersAlertBase.access$1500(this.this$0, Theme.key_dialogBackground)) <= 0.721f) goto L10;
            boolean r02 = true;
        L12:
            if (AndroidUtilities.computePerceivedBrightness(Theme.blendOver(UsersAlertBase.access$1600(this.this$0, Theme.key_actionBarDefault), 855638016)) <= 0.721f) goto L14;
            r2 = true;
        L14:
            this.statusBarOpen = Boolean.valueOf(r7);
            if (r7 == true) goto L18;
            r02 = r2;
        L18:
            AndroidUtilities.setLightStatusBar(this.this$0.getWindow(), r02);
            return;
        L10:
            r02 = false;
            goto L12
        L5:
            if (r0.booleanValue() != r7) goto L7;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            r5.save();
            r5.clipRect(0, getPaddingTop(), getMeasuredWidth(), getMeasuredHeight());
            super.dispatchDraw(r5);
            r5.restore();
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            r11.save();
            UsersAlertBase r0 = this.this$0;
            int r1 = (r0.scrollOffsetY - UsersAlertBase.access$400(r0)) + AndroidUtilities.dp(6.0f);
            UsersAlertBase r02 = this.this$0;
            int r2 = (r02.scrollOffsetY - UsersAlertBase.access$500(r02)) - AndroidUtilities.dp(13.0f);
            int r03 = (getMeasuredHeight() + AndroidUtilities.dp(50.0f)) + UsersAlertBase.access$600(this.this$0);
            int r3 = AndroidUtilities.statusBarHeight;
            int r22 = r2 + r3;
            int r12 = r1 + r3;
            int r04 = r03 - r3;
            float r32 = (UsersAlertBase.access$700(this.this$0) + r22) + getTranslationY();
            int r4 = AndroidUtilities.statusBarHeight;
            if (r32 >= (r4 * 2)) goto L5;
            int r33 = (int) Math.min(r4, (((r4 * 2) - r22) - UsersAlertBase.access$800(this.this$0)) - getTranslationY());
            r22 = r22 - r33;
            r04 = r04 + r33;
            float r34 = 1.0f - Math.min(1.0f, (r33 * 2) / AndroidUtilities.statusBarHeight);
        L6:
            float r42 = (UsersAlertBase.access$900(this.this$0) + r22) + getTranslationY();
            int r6 = AndroidUtilities.statusBarHeight;
            boolean r7 = false;
            if (r42 >= r6) goto L9;
            int r43 = (int) Math.min(r6, ((r6 - r22) - UsersAlertBase.access$1000(this.this$0)) - getTranslationY());
        L10:
            this.this$0.shadowDrawable.setBounds(0, r22, getMeasuredWidth(), r04);
            this.this$0.shadowDrawable.draw(r11);
            if (UsersAlertBase.m13498$$Nest$fgetdrawTitle(this.this$0) == false) goto L13;
        L16:
            if (r43 <= 0) goto L19;
            Theme.dialogs_onlineCirclePaint.setColor(UsersAlertBase.m13497$$Nest$fgetbackgroundColor(this.this$0));
        L19:
            if (r43 <= (AndroidUtilities.statusBarHeight / 2)) goto L21;
            r7 = true;
        L21:
            updateLightStatusBar(r7);
            r11.restore();
            return;
        L13:
            if (r34 == 1.0f) goto L15;
            Theme.dialogs_onlineCirclePaint.setColor(UsersAlertBase.m13497$$Nest$fgetbackgroundColor(this.this$0));
            UsersAlertBase.m13499$$Nest$fgetrect(this.this$0).set(UsersAlertBase.access$1100(this.this$0), UsersAlertBase.access$1200(this.this$0) + r22, getMeasuredWidth() - UsersAlertBase.access$1300(this.this$0), (UsersAlertBase.access$1400(this.this$0) + r22) + AndroidUtilities.dp(24.0f));
            r11.drawRoundRect(UsersAlertBase.m13499$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(12.0f) * r34, AndroidUtilities.dp(12.0f) * r34, Theme.dialogs_onlineCirclePaint);
        L15:
            int r05 = AndroidUtilities.dp(36.0f);
            UsersAlertBase.m13499$$Nest$fgetrect(this.this$0).set((getMeasuredWidth() - r05) / 2, r12, (getMeasuredWidth() + r05) / 2, r12 + AndroidUtilities.dp(4.0f));
            Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(this.this$0.keyScrollUp));
            r11.drawRoundRect(UsersAlertBase.m13499$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
            goto L16
        L9:
            r43 = 0;
            goto L10
        L5:
            r34 = 1.0f;
            goto L6
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (r4.getAction() != 0) goto L9;
            float r0 = r4.getY();
            UsersAlertBase r1 = this.this$0;
            if (r0 >= r1.scrollOffsetY) goto L9;
            r1.dismiss();
            return true;
        L9:
            return super.onInterceptTouchEvent(r4);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            this.this$0.updateLayout();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            int r92 = View.MeasureSpec.getSize(r9);
            this.ignoreLayout = true;
            setPadding(UsersAlertBase.access$100(this.this$0), AndroidUtilities.statusBarHeight, UsersAlertBase.access$200(this.this$0), 0);
            this.ignoreLayout = false;
            int r1 = r92 - getPaddingTop();
            if (UsersAlertBase.access$300(this.this$0) == false) goto L13;
            int r12 = AndroidUtilities.dp(8.0f);
            this.this$0.setAllowNestedScroll(false);
            int r2 = this.this$0.scrollOffsetY;
            if (r2 == 0) goto L11;
            float r22 = r2;
            this.snapToTopOffset = r22;
            setTranslationY(r22);
            ValueAnimator r23 = this.valueAnimator;
            if (r23 == null) goto L9;
            r23.removeAllListeners();
            this.valueAnimator.cancel();
        L9:
            ValueAnimator r24 = ValueAnimator.ofFloat(new float[]{this.snapToTopOffset, 0.0f});
            this.valueAnimator = r24;
            r24.addUpdateListener(new UsersAlertBase$ContainerView$$ExternalSyntheticLambda0(this));
            this.valueAnimator.setDuration(250);
            this.valueAnimator.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
            this.valueAnimator.addListener(new AnonymousClass1(this));
            this.valueAnimator.start();
        L15:
            if (this.this$0.listView.getPaddingTop() == r12) goto L17;
            this.ignoreLayout = true;
            this.this$0.listView.setPadding(0, r12, 0, 0);
            this.ignoreLayout = false;
        L17:
            super.onMeasure(r8, View.MeasureSpec.makeMeasureSpec(r92, TLObject.FLAG_30));
            return;
        L11:
            if (this.valueAnimator == null) goto L15;
            setTranslationY(this.snapToTopOffset);
            goto L15
        L13:
            r12 = this.this$0.measurePadding(r1);
            this.this$0.setAllowNestedScroll(true);
            goto L15
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            invalidate();
        }
    }

    public class SearchField extends FragmentSearchField {
        protected EditTextBoldCursor searchEditText;
        final /* synthetic */ UsersAlertBase this$0;

        /* renamed from: org.telegram.ui.Components.UsersAlertBase$SearchField$1, reason: invalid class name */
        public class AnonymousClass1 implements TextWatcherImpl {
            final /* synthetic */ SearchField this$1;
            final /* synthetic */ UsersAlertBase val$this$0;

            public AnonymousClass1(SearchField r1, UsersAlertBase r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r5) {
                String r52 = this.this$1.searchEditText.getText().toString();
                if (this.this$1.this$0.listView.getAdapter() != null) goto L5;
                int r0 = 0;
            L6:
                this.this$1.this$0.search(r52);
                if (TextUtils.isEmpty(r52) == false) goto L15;
                RecyclerListView r53 = this.this$1.this$0.listView;
                if (r53 == null) goto L15;
                RecyclerView.Adapter r54 = r53.getAdapter();
                UsersAlertBase r2 = this.this$1.this$0;
                if (r54 == r2.listViewAdapter) goto L15;
                r2.listView.setAnimateEmptyView(false, 0);
                UsersAlertBase r55 = this.this$1.this$0;
                r55.listView.setAdapter(r55.listViewAdapter);
                this.this$1.this$0.listView.setAnimateEmptyView(true, 0);
                if (r0 != 0) goto L15;
                this.this$1.this$0.showItemsAnimated(0);
            L15:
                this.this$1.this$0.flickerLoadingView.setVisibility(0);
                return;
            L5:
                r0 = this.this$1.this$0.listView.getAdapter().getItemCount();
                goto L6
            }
        }

        public static /* synthetic */ boolean $r8$lambda$KgZxPNg1c_zbtVoiZyLM6eHCGWo(SearchField r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$new$0(r1, r2, r3);
        }

        public SearchField(UsersAlertBase r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
            EditTextBoldCursor r22 = this.editText;
            this.searchEditText = r22;
            r22.setImeOptions(268435459);
            this.searchEditText.setHint(LocaleController.getString(R.string.VoipGroupSearchMembers));
            this.searchEditText.addTextChangedListener(new AnonymousClass1(this, r1));
            this.searchEditText.setOnEditorActionListener(new UsersAlertBase$SearchField$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ boolean lambda$new$0(TextView r1, int r2, KeyEvent r3) {
            if (r3 != null) goto L4;
            return false;
        L4:
            if (r3.getAction() != 1) goto L8;
            if (r3.getKeyCode() != 84) goto L8;
        L11:
            AndroidUtilities.hideKeyboard(this.searchEditText);
            return false;
        L8:
            if (r3.getAction() == 0) goto L10;
            return false;
        L10:
            if (r3.getKeyCode() == 66) goto L11;
            return false;
        }

        public void closeSearch() {
            AndroidUtilities.hideKeyboard(this.searchEditText);
        }

        @Override // org.telegram.ui.Components.FragmentSearchField, org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            this.this$0.onSearchViewTouched(r3, this.searchEditText);
            return super.onInterceptTouchEvent(r3);
        }
    }

    /* renamed from: -$$Nest$fgetbackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13497$$Nest$fgetbackgroundColor(UsersAlertBase r0) {
        return r0.backgroundColor;
    }

    /* renamed from: -$$Nest$fgetdrawTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13498$$Nest$fgetdrawTitle(UsersAlertBase r0) {
        return r0.drawTitle;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m13499$$Nest$fgetrect(UsersAlertBase r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$mgetColorProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13500$$Nest$mgetColorProgress(UsersAlertBase r0) {
        return r0.getColorProgress();
    }

    static {
        COLOR_PROGRESS = new AnonymousClass3("colorProgress");
    }

    public UsersAlertBase(Context r10, boolean r11, int r12, Theme.ResourcesProvider r13) {
        super(r10, r11, r13);
        this.rect = new RectF();
        this.needSnapToTop = true;
        this.isEmptyViewVisible = true;
        this.keyScrollUp = Theme.key_sheet_scrollUp;
        this.keyListSelector = Theme.key_listSelector;
        this.keySearchBackground = Theme.key_dialogSearchBackground;
        int r0 = Theme.key_windowBackgroundWhite;
        this.keyInviteMembersBackground = r0;
        this.keyListViewBackground = r0;
        this.keyActionBarUnscrolled = r0;
        this.keyNameText = Theme.key_windowBackgroundWhiteBlackText;
        int r02 = Theme.key_windowBackgroundWhiteGrayText;
        this.keyLastSeenText = r02;
        this.keyLastSeenTextUnscrolled = r02;
        this.keySearchPlaceholder = Theme.key_dialogSearchHint;
        this.keySearchText = Theme.key_dialogSearchText;
        int r03 = Theme.key_dialogSearchIcon;
        this.keySearchIcon = r03;
        this.keySearchIconUnscrolled = r03;
        this.drawTitle = true;
        this.resourcesProvider = r13;
        updateColorKeys();
        setDimBehindAlpha(75);
        this.currentAccount = r12;
        this.shadowDrawable = r10.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        ContainerView r122 = createContainerView(r10);
        this.containerView = r122;
        r122.setWillNotDraw(false);
        this.containerView.setClipChildren(false);
        ViewGroup r123 = this.containerView;
        int r1 = this.backgroundPaddingLeft;
        r123.setPadding(r1, 0, r1, 0);
        this.frameLayout = new FrameLayout(r10);
        SearchField r124 = new SearchField(this, r10, r13);
        this.searchView = r124;
        r124.setWhiteBackground();
        this.searchView.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        this.frameLayout.addView(this.searchView, LayoutHelper.createFrame(-1, 48.0f, 51, 7.0f, 7.0f, 7.0f, 7.0f));
        FlickerLoadingView r125 = new FlickerLoadingView(r10);
        this.flickerLoadingView = r125;
        r125.setViewType(6);
        this.flickerLoadingView.showDate(false);
        this.flickerLoadingView.setUseHeaderOffset(true);
        StickerEmptyView r126 = new StickerEmptyView(r10, this.flickerLoadingView, 1);
        this.emptyView = r126;
        r126.addView(this.flickerLoadingView, 0, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 2.0f, 0.0f, 0.0f));
        this.emptyView.title.setText(LocaleController.getString(R.string.NoResult));
        this.emptyView.subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        this.emptyView.setVisibility(8);
        this.emptyView.setAnimateLayoutChange(true);
        this.emptyView.showProgress(true, false);
        this.emptyView.setColors(this.keyNameText, this.keyLastSeenText, this.keyInviteMembersBackground, this.keySearchBackground);
        this.containerView.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 62.0f, 0.0f, 0.0f));
        AnonymousClass1 r127 = new AnonymousClass1(this, r10, r13);
        this.listView = r127;
        r127.setOverScrollMode(2);
        this.listView.setTag(13);
        this.listView.setPadding(0, 0, 0, AndroidUtilities.dp(48.0f));
        this.listView.setClipToPadding(false);
        this.listView.setHideIfEmpty(false);
        this.listView.setSelectorDrawableColor(Theme.getColor(this.keyListSelector, r13));
        FillLastLinearLayoutManager r14 = new FillLastLinearLayoutManager(getContext(), 1, false, AndroidUtilities.dp(8.0f), this.listView);
        this.layoutManager = r14;
        r14.setBind(false);
        this.listView.setLayoutManager(r14);
        this.listView.setHorizontalScrollBarEnabled(false);
        this.listView.setVerticalScrollBarEnabled(false);
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        this.listView.setOnScrollListener(new AnonymousClass2(this));
        FrameLayout.LayoutParams r128 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 51);
        r128.topMargin = AndroidUtilities.dp(58.0f);
        View r132 = new View(r10);
        this.shadow = r132;
        r132.setAlpha(0.0f);
        this.shadow.setTag(1);
        this.containerView.addView(this.shadow, r128);
        this.containerView.addView(this.frameLayout, LayoutHelper.createFrame(-1, 58, 51));
        setColorProgress(0.0f);
        this.listView.setEmptyView(this.emptyView);
        this.listView.setAnimateEmptyView(true, 0);
    }

    public static /* synthetic */ int access$000(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$100(UsersAlertBase r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1000(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1100(UsersAlertBase r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1200(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1300(UsersAlertBase r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1400(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1500(UsersAlertBase r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1600(UsersAlertBase r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$200(UsersAlertBase r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ boolean access$300(UsersAlertBase r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$400(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$500(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$600(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$700(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$800(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$900(UsersAlertBase r0) {
        return r0.backgroundPaddingTop;
    }

    private float getColorProgress() {
        return this.colorProgress;
    }

    private void runShadowAnimation(boolean r8) {
        if (r8 == true) goto L4;
    L5:
        if (r8 == false) goto L7;
        return;
    L7:
        if (this.shadow.getTag() != null) goto L25;
    L8:
        View r0 = this.shadow;
        if (r8 == false) goto L11;
        Integer r2 = null;
    L12:
        r0.setTag(r2);
        if (r8 == false) goto L15;
        this.shadow.setVisibility(0);
    L15:
        AnimatorSet r22 = this.shadowAnimation;
        if (r22 == null) goto L18;
        r22.cancel();
    L18:
        AnimatorSet r23 = new AnimatorSet();
        this.shadowAnimation = r23;
        View r3 = this.shadow;
        Property r4 = View.ALPHA;
        if (r8 == false) goto L21;
        float r5 = 1.0f;
    L22:
        r23.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r4, new float[]{r5})});
        this.shadowAnimation.setDuration(150);
        this.shadowAnimation.addListener(new AnonymousClass4(this, r8));
        this.shadowAnimation.start();
        return;
    L21:
        r5 = 0.0f;
        goto L22
    L11:
        r2 = 1;
        goto L12
    L25:
        return;
    L4:
        if (this.shadow.getTag() != null) goto L8;
        goto L5
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    public ContainerView createContainerView(Context r2) {
        return new ContainerView(this, r2);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        AndroidUtilities.hideKeyboard(this.searchView.searchEditText);
        super.dismiss();
    }

    public int measurePadding(int r1) {
        return (r1 - ((r1 / 5) * 3)) + AndroidUtilities.dp(8.0f);
    }

    public abstract void onSearchViewTouched(MotionEvent r1, EditTextBoldCursor r2);

    public void search(String r1) {
    }

    public void setColorProgress(float r6) {
        this.colorProgress = r6;
        this.backgroundColor = AndroidUtilities.getOffsetColor(Theme.getColor(this.keyInviteMembersBackground, this.resourcesProvider), Theme.getColor(this.keyListViewBackground, this.resourcesProvider), r6, 1.0f);
        this.shadowDrawable.setColorFilter(new PorterDuffColorFilter(this.backgroundColor, PorterDuff.Mode.MULTIPLY));
        fixNavigationBar(this.backgroundColor);
        int r0 = this.backgroundColor;
        this.navBarColor = r0;
        this.listView.setGlowColor(r0);
        int r02 = AndroidUtilities.getOffsetColor(Theme.getColor(this.keyLastSeenTextUnscrolled), Theme.getColor(this.keyLastSeenText), r6, 1.0f);
        int r62 = AndroidUtilities.getOffsetColor(Theme.getColor(this.keySearchIconUnscrolled), Theme.getColor(this.keySearchIcon), r6, 1.0f);
        int r1 = this.listView.getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L15;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof GroupCallTextCell) == false) goto L8;
        ((GroupCallTextCell) r3).setColors(r02, r02);
    L14:
        r2 = r2 + 1;
        goto L3
    L8:
        if ((r3 instanceof GroupCallUserCell) == false) goto L14;
        GroupCallUserCell r32 = (GroupCallUserCell) r3;
        if (this.shadow.getTag() == null) goto L12;
        int r4 = this.keySearchIcon;
    L13:
        r32.setGrayIconColor(r4, r62);
        goto L14
    L12:
        r4 = this.keySearchIconUnscrolled;
        goto L13
    L15:
        this.containerView.invalidate();
        this.listView.invalidate();
        this.container.invalidate();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void setTitle(CharSequence r10) {
        if (this.titleView != null) goto L9;
        TextView r0 = new TextView(getContext());
        this.titleView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        this.titleView.setTextSize(1, 20.0f);
        this.titleView.setTypeface(AndroidUtilities.bold());
        this.titleView.setLines(1);
        this.titleView.setMaxLines(1);
        this.titleView.setSingleLine(true);
        TextView r02 = this.titleView;
        if (LocaleController.isRTL == false) goto L7;
        int r1 = 5;
    L8:
        r02.setGravity(r1 | 16);
        this.titleView.setEllipsize(TextUtils.TruncateAt.END);
        this.frameLayout.addView(this.titleView, LayoutHelper.createFrame(-1, 36.0f, 51, 16.0f, 0.0f, 0.0f, 0.0f));
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.searchView.getLayoutParams();
        r03.topMargin = AndroidUtilities.dp(30.0f);
        ViewGroup.LayoutParams r04 = this.frameLayout.getLayoutParams();
        r04.height = AndroidUtilities.dp(94.0f);
        goto L9
    L7:
        r1 = 3;
    L9:
        this.titleView.setText(r10);
    }

    public void setTranslationY(int r2) {
        this.listView.setTopGlowOffset(r2);
        float r22 = r2;
        this.frameLayout.setTranslationY(r22);
        this.emptyView.setTranslationY(r22);
        this.containerView.invalidate();
    }

    public void showItemsAnimated(int r3) {
        if (isShowing() == true) goto L5;
        return;
    L5:
        this.listView.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass5(this, r3));
    }

    public void updateColorKeys() {
    }

    @SuppressLint({"NewApi"})
    public void updateLayout() {
        if (this.listView.getChildCount() <= 0) goto L26;
        RecyclerView.ViewHolder r0 = this.listView.findViewHolderForAdapterPosition(0);
        if (r0 == null) goto L8;
        int r2 = r0.itemView.getTop() - AndroidUtilities.dp(8.0f);
    L9:
        if (r2 <= 0) goto L14;
        if (r0 == null) goto L14;
        if (r0.getAdapterPosition() != 0) goto L14;
        int r3 = r2;
    L15:
        if (r2 < 0) goto L20;
        if (r0 == null) goto L20;
        if (r0.getAdapterPosition() != 0) goto L20;
        runShadowAnimation(false);
    L22:
        if (this.scrollOffsetY == r2) goto L25;
        this.scrollOffsetY = r2;
        setTranslationY(r2);
        return;
    L25:
        return;
    L20:
        runShadowAnimation(true);
        r2 = r3;
    L14:
        r3 = 0;
        goto L15
    L8:
        r2 = 0;
        goto L9
    }
}
