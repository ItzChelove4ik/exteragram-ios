package androidx.appcompat.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class ActionBarContextView extends AbsActionBarView {
    private View mClose;
    private View mCloseButton;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    /* renamed from: androidx.appcompat.widget.ActionBarContextView$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ ActionBarContextView this$0;
        final /* synthetic */ ActionMode val$mode;

        public AnonymousClass1(ActionBarContextView r1, ActionMode r2) {
            this.this$0 = r1;
            this.val$mode = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            this.val$mode.finish();
        }
    }

    public ActionBarContextView(Context r2) {
        this(r2, null);
    }

    public ActionBarContextView(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.actionModeStyle);
    }

    public ActionBarContextView(Context r3, AttributeSet r4, int r5) {
        super(r3, r4, r5);
        TintTypedArray r32 = TintTypedArray.obtainStyledAttributes(r3, r4, R$styleable.ActionMode, r5, 0);
        setBackground(r32.getDrawable(R$styleable.ActionMode_background));
        this.mTitleStyleRes = r32.getResourceId(R$styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = r32.getResourceId(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = r32.getLayoutDimension(R$styleable.ActionMode_height, 0);
        this.mCloseItemLayout = r32.getResourceId(R$styleable.ActionMode_closeItemLayout, R$layout.abc_action_mode_close_item_material);
        r32.recycle();
    }

    private void initTitle() {
        if (this.mTitleLayout != null) goto L10;
        LayoutInflater.from(getContext()).inflate(R$layout.abc_action_bar_title_item, this);
        LinearLayout r0 = (LinearLayout) getChildAt(getChildCount() - 1);
        this.mTitleLayout = r0;
        this.mTitleView = (TextView) r0.findViewById(R$id.action_bar_title);
        this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(R$id.action_bar_subtitle);
        if (this.mTitleStyleRes == 0) goto L8;
        this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
    L8:
        if (this.mSubtitleStyleRes == 0) goto L10;
        this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
    L10:
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean r02 = TextUtils.isEmpty(this.mTitle);
        boolean r1 = TextUtils.isEmpty(this.mSubtitle);
        TextView r2 = this.mSubtitleView;
        int r3 = 8;
        if (r1 == true) goto L13;
        int r5 = 0;
    L14:
        r2.setVisibility(r5);
        LinearLayout r22 = this.mTitleLayout;
        if (r02 == false) goto L17;
        if (r1 == false) goto L17;
    L18:
        r22.setVisibility(r3);
        if (this.mTitleLayout.getParent() != null) goto L22;
        addView(this.mTitleLayout);
        return;
    L22:
        return;
    L17:
        r3 = 0;
        goto L18
    L13:
        r5 = 8;
        goto L14
    }

    public void closeMode() {
        if (this.mClose != null) goto L6;
        killMode();
        return;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet r2) {
        return new ViewGroup.MarginLayoutParams(getContext(), r2);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void initForMode(ActionMode r4) {
        View r0 = this.mClose;
        if (r0 != null) goto L6;
        View r02 = LayoutInflater.from(getContext()).inflate(this.mCloseItemLayout, this, false);
        this.mClose = r02;
        addView(r02);
    L8:
        View r03 = this.mClose.findViewById(R$id.action_mode_close_button);
        this.mCloseButton = r03;
        r03.setOnClickListener(new AnonymousClass1(this, r4));
        MenuBuilder r42 = (MenuBuilder) r4.getMenu();
        ActionMenuPresenter r04 = this.mActionMenuPresenter;
        if (r04 == null) goto L11;
        r04.dismissPopupMenus();
    L11:
        ActionMenuPresenter r05 = new ActionMenuPresenter(getContext());
        this.mActionMenuPresenter = r05;
        r05.setReserveOverflow(true);
        ViewGroup.LayoutParams r06 = new ViewGroup.LayoutParams(-2, -1);
        r42.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        ActionMenuView r43 = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
        this.mMenuView = r43;
        r43.setBackground(null);
        addView(this.mMenuView, r06);
        return;
    L6:
        if (r0.getParent() != null) goto L8;
        addView(this.mClose);
        goto L8
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }

    public void killMode() {
        removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
        this.mActionMenuPresenter = null;
        View r1 = this.mCloseButton;
        if (r1 == null) goto L6;
        r1.setOnClickListener(null);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter r0 = this.mActionMenuPresenter;
        if (r0 == null) goto L6;
        r0.hideOverflowMenu();
        this.mActionMenuPresenter.hideSubMenus();
        return;
    }

    @Override // androidx.appcompat.widget.AbsActionBarView, android.view.View
    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent r1) {
        return super.onHoverEvent(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
        boolean r5 = ViewUtils.isLayoutRtl(this);
        if (r5 == false) goto L5;
        int r1 = (r13 - r11) - getPaddingRight();
    L6:
        int r3 = getPaddingTop();
        int r4 = ((r14 - r12) - getPaddingTop()) - getPaddingBottom();
        View r2 = this.mClose;
        if (r2 != null) goto L9;
    L19:
        int r22 = r1;
        LinearLayout r15 = this.mTitleLayout;
        if (r15 != null) goto L22;
    L26:
        View r16 = this.mCustomView;
        if (r16 == null) goto L29;
        positionChild(r16, r22, r3, r4, r5);
    L29:
        if (r5 == false) goto L32;
        int r17 = getPaddingLeft();
    L31:
        int r23 = r17;
        ActionMenuView r18 = this.mMenuView;
        if (r18 == null) goto L37;
        positionChild(r18, r23, r3, r4, !r5);
        return;
    L37:
        return;
    L32:
        r17 = (r13 - r11) - getPaddingRight();
        goto L31
    L22:
        if (this.mCustomView != null) goto L26;
        if (r15.getVisibility() == 8) goto L26;
        r22 = r22 + positionChild(this.mTitleLayout, r22, r3, r4, r5);
        goto L26
    L9:
        if (r2.getVisibility() == 8) goto L19;
        ViewGroup.MarginLayoutParams r24 = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
        if (r5 == false) goto L13;
        int r7 = r24.rightMargin;
    L14:
        if (r5 == false) goto L17;
        int r25 = r24.leftMargin;
    L16:
        int r8 = r25;
        int r26 = AbsActionBarView.next(r1, r7, r5);
        r1 = AbsActionBarView.next(r26 + positionChild(this.mClose, r26, r3, r4, r5), r8, r5);
        goto L19
    L17:
        r25 = r24.rightMargin;
        goto L16
    L13:
        r7 = r24.leftMargin;
        goto L14
    L5:
        r1 = getPaddingLeft();
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r11, int r12) {
        int r0 = View.MeasureSpec.getMode(r11);
        int r1 = TLObject.FLAG_30;
        if (r0 == 1073741824) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        return;
    L5:
        if (View.MeasureSpec.getMode(r12) == 0) goto L63;
        int r112 = View.MeasureSpec.getSize(r11);
        int r02 = this.mContentHeight;
        if (r02 > 0) goto L10;
        r02 = View.MeasureSpec.getSize(r12);
    L10:
        int r122 = getPaddingTop() + getPaddingBottom();
        int r2 = (r112 - getPaddingLeft()) - getPaddingRight();
        int r3 = r02 - r122;
        int r5 = View.MeasureSpec.makeMeasureSpec(r3, Integer.MIN_VALUE);
        View r6 = this.mClose;
        int r7 = 0;
        if (r6 == null) goto L13;
        int r22 = measureChildView(r6, r2, r5, 0);
        ViewGroup.MarginLayoutParams r62 = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
        r2 = r22 - (r62.leftMargin + r62.rightMargin);
    L13:
        ActionMenuView r63 = this.mMenuView;
        if (r63 != null) goto L16;
    L18:
        LinearLayout r64 = this.mTitleLayout;
        if (r64 != null) goto L21;
    L36:
        View r52 = this.mCustomView;
        if (r52 == null) goto L52;
        ViewGroup.LayoutParams r53 = r52.getLayoutParams();
        int r65 = r53.width;
        if (r65 == (-2)) goto L41;
        int r9 = 1073741824;
    L42:
        if (r65 < 0) goto L44;
        r2 = Math.min(r65, r2);
    L44:
        int r54 = r53.height;
        if (r54 != (-2)) goto L48;
        r1 = Integer.MIN_VALUE;
    L48:
        if (r54 < 0) goto L50;
        r3 = Math.min(r54, r3);
    L50:
        this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(r2, r9), View.MeasureSpec.makeMeasureSpec(r3, r1));
        goto L52
    L41:
        r9 = Integer.MIN_VALUE;
    L52:
        if (this.mContentHeight > 0) goto L61;
        int r03 = getChildCount();
        int r13 = 0;
    L54:
        if (r7 >= r03) goto L59;
        int r23 = getChildAt(r7).getMeasuredHeight() + r122;
        if (r23 <= r13) goto L58;
        r13 = r23;
    L58:
        r7 = r7 + 1;
        goto L54
    L59:
        setMeasuredDimension(r112, r13);
        return;
    L61:
        setMeasuredDimension(r112, r02);
        return;
    L21:
        if (this.mCustomView != null) goto L36;
        if (this.mTitleOptional == false) goto L35;
        this.mTitleLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), r5);
        int r55 = this.mTitleLayout.getMeasuredWidth();
        if (r55 > r2) goto L27;
        boolean r66 = true;
    L28:
        if (r66 == false) goto L30;
        r2 = r2 - r55;
    L30:
        LinearLayout r56 = this.mTitleLayout;
        if (r66 == false) goto L33;
        int r67 = 0;
    L34:
        r56.setVisibility(r67);
        goto L36
    L33:
        r67 = 8;
        goto L34
    L27:
        r66 = false;
        goto L28
    L35:
        r2 = measureChildView(r64, r2, r5, 0);
        goto L36
    L16:
        if (r63.getParent() != this) goto L18;
        r2 = measureChildView(this.mMenuView, r2, r5, 0);
        goto L18
    L63:
        Segment$$ExternalSyntheticBUOutline1.m(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
    }

    @Override // androidx.appcompat.widget.AbsActionBarView, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent r1) {
        return super.onTouchEvent(r1);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public void setContentHeight(int r1) {
        this.mContentHeight = r1;
    }

    public void setCustomView(View r2) {
        View r0 = this.mCustomView;
        if (r0 == null) goto L5;
        removeView(r0);
    L5:
        this.mCustomView = r2;
        if (r2 == null) goto L10;
        LinearLayout r02 = this.mTitleLayout;
        if (r02 == null) goto L10;
        removeView(r02);
        this.mTitleLayout = null;
    L10:
        if (r2 == null) goto L12;
        addView(r2);
    L12:
        requestLayout();
    }

    public void setSubtitle(CharSequence r1) {
        this.mSubtitle = r1;
        initTitle();
    }

    public void setTitle(CharSequence r1) {
        this.mTitle = r1;
        initTitle();
        ViewCompat.setAccessibilityPaneTitle(this, r1);
    }

    public void setTitleOptional(boolean r2) {
        if (r2 == this.mTitleOptional) goto L5;
        requestLayout();
    L5:
        this.mTitleOptional = r2;
    }

    @Override // androidx.appcompat.widget.AbsActionBarView, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int r1) {
        super.setVisibility(r1);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat setupAnimatorToVisibility(int r1, long r2) {
        return super.setupAnimatorToVisibility(r1, r2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter r0 = this.mActionMenuPresenter;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.showOverflowMenu();
    }
}
