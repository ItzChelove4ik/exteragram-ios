package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.LinearLayoutCompat;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    public static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        public ActionMenuPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder r1, boolean r2) {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder r1) {
            return false;
        }
    }

    public static class LayoutParams extends LinearLayoutCompat.LayoutParams {

        @ViewDebug.ExportedProperty
        public int cellsUsed;

        @ViewDebug.ExportedProperty
        public boolean expandable;
        boolean expanded;

        @ViewDebug.ExportedProperty
        public int extraPixels;

        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;

        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.isOverflowButton = false;
        }

        public LayoutParams(Context r1, AttributeSet r2) {
            super(r1, r2);
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
        }

        public LayoutParams(LayoutParams r1) {
            super(r1);
            this.isOverflowButton = r1.isOverflowButton;
        }
    }

    public class MenuBuilderCallback implements MenuBuilder.Callback {
        final /* synthetic */ ActionMenuView this$0;

        public MenuBuilderCallback(ActionMenuView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder r1, MenuItem r2) {
            OnMenuItemClickListener r0 = this.this$0.mOnMenuItemClickListener;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.onMenuItemClick(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder r1) {
            MenuBuilder.Callback r0 = this.this$0.mMenuBuilderCallback;
            if (r0 == null) goto L6;
            r0.onMenuModeChange(r1);
            return;
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem r1);
    }

    public ActionMenuView(Context r2) {
        this(r2, null);
    }

    public ActionMenuView(Context r3, AttributeSet r4) {
        super(r3, r4);
        setBaselineAligned(false);
        float r0 = r3.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * r0);
        this.mGeneratedItemPadding = (int) (r0 * 4.0f);
        this.mPopupContext = r3;
        this.mPopupTheme = 0;
    }

    public static int measureChildForCells(View r5, int r6, int r7, int r8, int r9) {
        LayoutParams r0 = (LayoutParams) r5.getLayoutParams();
        int r82 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r8) - r9, View.MeasureSpec.getMode(r8));
        if ((r5 instanceof ActionMenuItemView) == false) goto L5;
        ActionMenuItemView r92 = (ActionMenuItemView) r5;
    L6:
        boolean r1 = false;
        if (r92 != null) goto L9;
    L11:
        boolean r93 = false;
    L12:
        if (r7 <= 0) goto L23;
        int r3 = 2;
        if (r93 == false) goto L16;
        if (r7 < 2) goto L23;
    L16:
        r5.measure(View.MeasureSpec.makeMeasureSpec(r7 * r6, Integer.MIN_VALUE), r82);
        int r72 = r5.getMeasuredWidth();
        int r4 = r72 / r6;
        if ((r72 % r6) == 0) goto L19;
        r4 = r4 + 1;
    L19:
        if (r93 == false) goto L22;
        if (r4 >= 2) goto L22;
    L25:
        if (r0.isOverflowButton == true) goto L28;
        if (r93 == false) goto L28;
        r1 = true;
    L28:
        r0.expandable = r1;
        r0.cellsUsed = r3;
        r5.measure(View.MeasureSpec.makeMeasureSpec(r6 * r3, TLObject.FLAG_30), r82);
        return r3;
    L22:
        r3 = r4;
    L23:
        r3 = 0;
        goto L25
    L9:
        if (r92.hasText() == false) goto L11;
        r93 = true;
        goto L12
    L5:
        r92 = null;
        goto L6
    }

    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v34, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v48 */
    private void onMeasureExactFormat(int r31, int r32) {
        int r1 = View.MeasureSpec.getMode(r32);
        int r2 = View.MeasureSpec.getSize(r31);
        int r3 = View.MeasureSpec.getSize(r32);
        int r4 = getPaddingLeft() + getPaddingRight();
        int r5 = getPaddingTop() + getPaddingBottom();
        int r6 = ViewGroup.getChildMeasureSpec(r32, r5, -2);
        int r22 = r2 - r4;
        int r42 = this.mMinCellSize;
        int r7 = r22 / r42;
        int r8 = r22 % r42;
        if (r7 != 0) goto L6;
        setMeasuredDimension(r22, 0);
        return;
    L6:
        int r43 = r42 + (r8 / r7);
        int r82 = getChildCount();
        int r10 = 0;
        int r12 = 0;
        boolean r13 = false;
        int r14 = 0;
        int r15 = 0;
        int r16 = 0;
        long r17 = 0;
    L7:
        if (r12 >= r82) goto L35;
        View r11 = getChildAt(r12);
        int r20 = r3;
        if (r11.getVisibility() != 8) goto L11;
        int r21 = r43;
    L34:
        r12 = r12 + 1;
        r3 = r20;
        r43 = r21;
        goto L7
    L11:
        boolean r33 = r11 instanceof ActionMenuItemView;
        r14 = r14 + 1;
        if (r33 == false) goto L14;
        int r9 = this.mGeneratedItemPadding;
        boolean r212 = r33;
        ?? r34 = 0;
        r11.setPadding(r9, 0, r9, 0);
    L15:
        LayoutParams r92 = (LayoutParams) r11.getLayoutParams();
        r92.expanded = r34;
        r92.extraPixels = r34;
        r92.cellsUsed = r34;
        r92.expandable = r34;
        ((LinearLayout.LayoutParams) r92).leftMargin = r34;
        ((LinearLayout.LayoutParams) r92).rightMargin = r34;
        if (r212 == true) goto L18;
    L20:
        boolean r35 = false;
    L21:
        r92.preventEdgeOffset = r35;
        if (r92.isOverflowButton == false) goto L24;
        int r36 = 1;
    L25:
        int r37 = measureChildForCells(r11, r43, r36, r6, r5);
        r15 = Math.max(r15, r37);
        r21 = r43;
        if (r92.expandable == false) goto L29;
        r16 = r16 + 1;
    L29:
        if (r92.isOverflowButton == false) goto L31;
        r13 = true;
    L31:
        r7 = r7 - r37;
        r10 = Math.max(r10, r11.getMeasuredHeight());
        if (r37 != 1) goto L34;
        r17 = r17 | (1 << r12);
        goto L34
    L24:
        r36 = r7;
        goto L25
    L18:
        if (((ActionMenuItemView) r11).hasText() == false) goto L20;
        r35 = true;
        goto L21
    L14:
        r212 = r33;
        r34 = 0;
        goto L15
    L35:
        int r202 = r3;
        int r213 = r43;
        char r38 = 2;
        if (r13 == false) goto L39;
        if (r14 != 2) goto L39;
        boolean r44 = true;
    L40:
        boolean r52 = false;
    L41:
        if (r16 <= 0) goto L76;
        if (r7 <= 0) goto L76;
        int r93 = Integer.MAX_VALUE;
        long r25 = 0;
        char r222 = r38;
        int r39 = 0;
        int r112 = 0;
        long r23 = 1;
    L44:
        if (r112 >= r82) goto L54;
        LayoutParams r122 = (LayoutParams) getChildAt(r112).getLayoutParams();
        boolean r27 = r44;
        if (r122.expandable == false) goto L53;
        int r45 = r122.cellsUsed;
        if (r45 >= r93) goto L51;
        r25 = 1 << r112;
        r93 = r45;
        r39 = 1;
        goto L53
    L51:
        if (r45 != r93) goto L53;
        r25 = r25 | (1 << r112);
        r39 = r39 + 1;
    L53:
        r112 = r112 + 1;
        r44 = r27;
        goto L44
    L54:
        boolean r272 = r44;
        r17 = r17 | r25;
        if (r39 > r7) goto L78;
        int r94 = r93 + 1;
        int r310 = 0;
    L58:
        if (r310 >= r82) goto L75;
        View r46 = getChildAt(r310);
        LayoutParams r53 = (LayoutParams) r46.getLayoutParams();
        long r113 = 1 << r310;
        if ((r25 & r113) == 0) goto L62;
        if (r272 == true) goto L67;
    L72:
        int r232 = r310;
        boolean r19 = true;
    L73:
        r53.cellsUsed++;
        r53.expanded = r19;
        r7 = r7 - 1;
    L74:
        r310 = r232 + 1;
        goto L58
    L67:
        if (r53.preventEdgeOffset == false) goto L72;
        if (r7 != 1) goto L71;
        int r123 = this.mGeneratedItemPadding;
        r19 = true;
        r232 = r310;
        r46.setPadding(r123 + r213, 0, r123, 0);
        goto L73
    L71:
        r232 = r310;
        r19 = true;
        goto L73
    L62:
        if (r53.cellsUsed != r94) goto L64;
        r17 = r17 | r113;
    L64:
        r232 = r310;
        goto L74
    L75:
        r38 = r222;
        r44 = r272;
        r52 = true;
    L78:
        if (r13 == true) goto L81;
        if (r14 != 1) goto L81;
        boolean r311 = true;
    L82:
        if (r7 > 0) goto L84;
    L90:
        int r312 = 0;
    L135:
        if (r52 == false) goto L143;
        int r95 = r312;
    L137:
        if (r95 >= r82) goto L143;
        View r313 = getChildAt(r95);
        LayoutParams r54 = (LayoutParams) r313.getLayoutParams();
        if (r54.expanded == false) goto L142;
        r313.measure(View.MeasureSpec.makeMeasureSpec((r54.cellsUsed * r213) + r54.extraPixels, TLObject.FLAG_30), r6);
    L142:
        r95 = r95 + 1;
    L143:
        if (r1 == 1073741824) goto L145;
        int r314 = r10;
    L146:
        setMeasuredDimension(r22, r314);
        return;
    L145:
        r314 = r202;
        goto L146
    L84:
        if (r17 == 0) goto L90;
        if (r7 < (r14 - 1)) goto L91;
        if (r311 == true) goto L91;
        if (r15 <= 1) goto L90;
    L91:
        float r47 = Long.bitCount(r17);
        if (r311 == false) goto L94;
        r312 = 0;
    L106:
        if (r47 <= 0.0f) goto L108;
        int r48 = (int) ((r7 * r213) / r47);
    L109:
        boolean r72 = r52;
        int r55 = r312;
    L110:
        if (r55 >= r82) goto L133;
        if ((r17 & (1 << r55)) == 0) goto L132;
        View r96 = getChildAt(r55);
        LayoutParams r114 = (LayoutParams) r96.getLayoutParams();
        if ((r96 instanceof ActionMenuItemView) == false) goto L124;
        r114.extraPixels = r48;
        r114.expanded = true;
        if (r55 == 0) goto L119;
    L121:
        r72 = true;
        goto L132
    L119:
        if (r114.preventEdgeOffset == true) goto L121;
        ((LinearLayout.LayoutParams) r114).leftMargin = (-r48) / 2;
        goto L121
    L124:
        if (r114.isOverflowButton == false) goto L127;
        r114.extraPixels = r48;
        r114.expanded = true;
        ((LinearLayout.LayoutParams) r114).rightMargin = (-r48) / 2;
        r72 = true;
        goto L132
    L127:
        if (r55 == 0) goto L130;
        ((LinearLayout.LayoutParams) r114).leftMargin = r48 / 2;
    L130:
        if (r55 == (r82 - 1)) goto L132;
        ((LinearLayout.LayoutParams) r114).rightMargin = r48 / 2;
    L132:
        r55 = r55 + 1;
        goto L110
    L133:
        r52 = r72;
        goto L135
    L108:
        r48 = r312;
        goto L109
    L94:
        if ((r17 & r23) == 0) goto L98;
        r312 = 0;
        if (((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset == true) goto L99;
        r47 = r47 - 0.5f;
    L99:
        int r115 = r82 - 1;
        if ((r17 & (1 << r115)) == 0) goto L106;
        if (((LayoutParams) getChildAt(r115).getLayoutParams()).preventEdgeOffset == true) goto L106;
        r47 = r47 - 0.5f;
        goto L106
    L98:
        r312 = 0;
    L81:
        r311 = false;
    L76:
        r23 = 1;
    L39:
        r44 = false;
        goto L40
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        return r1 instanceof LayoutParams;
    }

    public void dismissPopupMenus() {
        ActionMenuPresenter r0 = this.mPresenter;
        if (r0 == null) goto L6;
        r0.dismissPopupMenus();
        return;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r1) {
        return false;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams r1 = new LayoutParams(-2, -2);
        ((LinearLayout.LayoutParams) r1).gravity = 16;
        return r1;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayoutCompat.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        if (r1 == null) goto L12;
        if ((r1 instanceof LayoutParams) == false) goto L6;
        LayoutParams r0 = new LayoutParams((LayoutParams) r1);
    L8:
        if (((LinearLayout.LayoutParams) r0).gravity > 0) goto L10;
        ((LinearLayout.LayoutParams) r0).gravity = 16;
    L10:
        return r0;
    L6:
        r0 = new LayoutParams(r1);
        goto L8
    L12:
        return generateDefaultLayoutParams();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayoutCompat.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayoutCompat.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams r1 = generateDefaultLayoutParams();
        r1.isOverflowButton = true;
        return r1;
    }

    public Menu getMenu() {
        if (this.mMenu != null) goto L10;
        Context r0 = getContext();
        MenuBuilder r1 = new MenuBuilder(r0);
        this.mMenu = r1;
        r1.setCallback(new MenuBuilderCallback(this));
        ActionMenuPresenter r12 = new ActionMenuPresenter(r0);
        this.mPresenter = r12;
        r12.setReserveOverflow(true);
        ActionMenuPresenter r02 = this.mPresenter;
        MenuPresenter.Callback r13 = this.mActionMenuPresenterCallback;
        if (r13 != null) goto L8;
        r13 = new ActionMenuPresenterCallback();
    L8:
        r02.setCallback(r13);
        this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
        this.mPresenter.setMenuView(this);
    L10:
        return this.mMenu;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.mPresenter.getOverflowIcon();
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public boolean hasSupportDividerBeforeChildAt(int r4) {
        boolean r0 = false;
        if (r4 != 0) goto L5;
        return false;
    L5:
        KeyEvent.Callback r1 = getChildAt(r4 - 1);
        KeyEvent.Callback r2 = getChildAt(r4);
        if (r4 < getChildCount()) goto L8;
    L10:
        if (r4 > 0) goto L12;
    L15:
        return r0;
    L12:
        if ((r2 instanceof ActionMenuChildView) == false) goto L15;
        return ((ActionMenuChildView) r2).needsDividerBefore() | r0;
    L8:
        if ((r1 instanceof ActionMenuChildView) == false) goto L10;
        r0 = ((ActionMenuChildView) r1).needsDividerAfter();
        goto L10
    }

    public boolean hideOverflowMenu() {
        ActionMenuPresenter r0 = this.mPresenter;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.hideOverflowMenu() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder r1) {
        this.mMenu = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.ItemInvoker
    public boolean invokeItem(MenuItemImpl r2) {
        return this.mMenu.performItemAction(r2, 0);
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuPresenter r0 = this.mPresenter;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isOverflowMenuShowPending() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuPresenter r0 = this.mPresenter;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isOverflowMenuShowing() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        ActionMenuPresenter r22 = this.mPresenter;
        if (r22 == null) goto L8;
        r22.updateMenuView(false);
        if (this.mPresenter.isOverflowMenuShowing() == false) goto L9;
        this.mPresenter.hideOverflowMenu();
        this.mPresenter.showOverflowMenu();
        return;
    L9:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissPopupMenus();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r18, int r19, int r20, int r21, int r22) {
        if (this.mFormatItems == true) goto L6;
        super.onLayout(r18, r19, r20, r21, r22);
        return;
    L6:
        int r1 = getChildCount();
        int r2 = (r22 - r20) / 2;
        int r3 = getDividerWidth();
        int r4 = r21 - r19;
        int r5 = (r4 - getPaddingRight()) - getPaddingLeft();
        boolean r6 = ViewUtils.isLayoutRtl(this);
        int r8 = 0;
        int r9 = 0;
        int r10 = 0;
    L8:
        if (r8 >= r1) goto L24;
        View r13 = getChildAt(r8);
        if (r13.getVisibility() == 8) goto L23;
        LayoutParams r11 = (LayoutParams) r13.getLayoutParams();
        if (r11.isOverflowButton == false) goto L22;
        int r92 = r13.getMeasuredWidth();
        if (hasSupportDividerBeforeChildAt(r8) == false) goto L17;
        r92 = r92 + r3;
    L17:
        int r14 = r13.getMeasuredHeight();
        if (r6 == false) goto L20;
        int r15 = getPaddingLeft() + ((LinearLayout.LayoutParams) r11).leftMargin;
        int r112 = r15 + r92;
    L21:
        int r7 = r2 - (r14 / 2);
        r13.layout(r15, r7, r112, r14 + r7);
        r5 = r5 - r92;
        r9 = 1;
        goto L23
    L20:
        r112 = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) r11).rightMargin;
        r15 = r112 - r92;
        goto L21
    L22:
        r5 = r5 - ((r13.getMeasuredWidth() + ((LinearLayout.LayoutParams) r11).leftMargin) + ((LinearLayout.LayoutParams) r11).rightMargin);
        hasSupportDividerBeforeChildAt(r8);
        r10 = r10 + 1;
    L23:
        r8 = r8 + 1;
        goto L8
    L24:
        if (r1 != 1) goto L28;
        if (r9 != 0) goto L28;
        View r0 = getChildAt(0);
        int r12 = r0.getMeasuredWidth();
        int r32 = r0.getMeasuredHeight();
        int r42 = (r4 / 2) - (r12 / 2);
        int r23 = r2 - (r32 / 2);
        r0.layout(r42, r23, r12 + r42, r32 + r23);
        return;
    L28:
        int r102 = r10 - (r9 ^ 1);
        if (r102 <= 0) goto L32;
        int r33 = r5 / r102;
    L33:
        int r34 = Math.max(0, r33);
        if (r6 == false) goto L44;
        int r52 = getWidth() - getPaddingRight();
        int r72 = 0;
    L36:
        if (r72 >= r1) goto L53;
        View r43 = getChildAt(r72);
        LayoutParams r62 = (LayoutParams) r43.getLayoutParams();
        if (r43.getVisibility() == 8) goto L43;
        if (r62.isOverflowButton == true) goto L43;
        int r53 = r52 - ((LinearLayout.LayoutParams) r62).rightMargin;
        int r82 = r43.getMeasuredWidth();
        int r93 = r43.getMeasuredHeight();
        int r103 = r2 - (r93 / 2);
        r43.layout(r53 - r82, r103, r53, r93 + r103);
        r52 = r53 - ((r82 + ((LinearLayout.LayoutParams) r62).leftMargin) + r34);
    L43:
        r72 = r72 + 1;
        goto L36
    L53:
        return;
    L44:
        int r54 = getPaddingLeft();
        int r73 = 0;
    L45:
        if (r73 >= r1) goto L66;
        View r44 = getChildAt(r73);
        LayoutParams r63 = (LayoutParams) r44.getLayoutParams();
        if (r44.getVisibility() == 8) goto L52;
        if (r63.isOverflowButton == true) goto L52;
        int r55 = r54 + ((LinearLayout.LayoutParams) r63).leftMargin;
        int r83 = r44.getMeasuredWidth();
        int r94 = r44.getMeasuredHeight();
        int r104 = r2 - (r94 / 2);
        r44.layout(r55, r104, r55 + r83, r94 + r104);
        r54 = r55 + ((r83 + ((LinearLayout.LayoutParams) r63).rightMargin) + r34);
    L52:
        r73 = r73 + 1;
        goto L45
    L66:
        return;
    L32:
        r33 = 0;
        goto L33
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int r6, int r7) {
        boolean r0 = this.mFormatItems;
        if (View.MeasureSpec.getMode(r6) != 1073741824) goto L5;
        boolean r1 = true;
    L6:
        this.mFormatItems = r1;
        if (r0 == r1) goto L9;
        this.mFormatItemsWidth = 0;
    L9:
        int r02 = View.MeasureSpec.getSize(r6);
        if (this.mFormatItems == false) goto L16;
        MenuBuilder r12 = this.mMenu;
        if (r12 == null) goto L16;
        if (r02 == this.mFormatItemsWidth) goto L16;
        this.mFormatItemsWidth = r02;
        r12.onItemsChanged(true);
    L16:
        int r03 = getChildCount();
        if (this.mFormatItems == false) goto L21;
        if (r03 <= 0) goto L21;
        onMeasureExactFormat(r6, r7);
        return;
    L21:
        int r13 = 0;
    L22:
        if (r13 >= r03) goto L24;
        LayoutParams r2 = (LayoutParams) getChildAt(r13).getLayoutParams();
        ((LinearLayout.LayoutParams) r2).rightMargin = 0;
        ((LinearLayout.LayoutParams) r2).leftMargin = 0;
        r13 = r13 + 1;
        goto L22
    L24:
        super.onMeasure(r6, r7);
        return;
    L5:
        r1 = false;
        goto L6
    }

    public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    public void setExpandedActionViewsExclusive(boolean r1) {
        this.mPresenter.setExpandedActionViewsExclusive(r1);
    }

    public void setMenuCallbacks(MenuPresenter.Callback r1, MenuBuilder.Callback r2) {
        this.mActionMenuPresenterCallback = r1;
        this.mMenuBuilderCallback = r2;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener r1) {
        this.mOnMenuItemClickListener = r1;
    }

    public void setOverflowIcon(Drawable r1) {
        getMenu();
        this.mPresenter.setOverflowIcon(r1);
    }

    public void setOverflowReserved(boolean r1) {
        this.mReserveOverflow = r1;
    }

    public void setPopupTheme(int r3) {
        if (this.mPopupTheme == r3) goto L10;
        this.mPopupTheme = r3;
        if (r3 != 0) goto L8;
        this.mPopupContext = getContext();
        return;
    L8:
        this.mPopupContext = new ContextThemeWrapper(getContext(), r3);
        return;
    }

    public void setPresenter(ActionMenuPresenter r1) {
        this.mPresenter = r1;
        r1.setMenuView(this);
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter r0 = this.mPresenter;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.showOverflowMenu() == false) goto L10;
        return true;
    L10:
        return false;
    }
}
