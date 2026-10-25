package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TintTypedArray;

/* loaded from: classes3.dex */
public class ListMenuItemView extends LinearLayout implements MenuView.ItemView, AbsListView.SelectionBoundsAdjuster {
    private Drawable mBackground;
    private CheckBox mCheckBox;
    private LinearLayout mContent;
    private boolean mForceShowIcon;
    private ImageView mGroupDivider;
    private boolean mHasListDivider;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    private MenuItemImpl mItemData;
    private boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private Drawable mSubMenuArrow;
    private ImageView mSubMenuArrowView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;

    public ListMenuItemView(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.listMenuViewStyle);
    }

    public ListMenuItemView(Context r4, AttributeSet r5, int r6) {
        super(r4, r5);
        TintTypedArray r52 = TintTypedArray.obtainStyledAttributes(getContext(), r5, R$styleable.MenuView, r6, 0);
        this.mBackground = r52.getDrawable(R$styleable.MenuView_android_itemBackground);
        this.mTextAppearance = r52.getResourceId(R$styleable.MenuView_android_itemTextAppearance, -1);
        this.mPreserveIconSpacing = r52.getBoolean(R$styleable.MenuView_preserveIconSpacing, false);
        this.mTextAppearanceContext = r4;
        this.mSubMenuArrow = r52.getDrawable(R$styleable.MenuView_subMenuArrow);
        TypedArray r42 = r4.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, R$attr.dropDownListViewStyle, 0);
        this.mHasListDivider = r42.hasValue(0);
        r52.recycle();
        r42.recycle();
    }

    private void addContentView(View r2) {
        addContentView(r2, -1);
    }

    private void addContentView(View r2, int r3) {
        LinearLayout r0 = this.mContent;
        if (r0 == null) goto L6;
        r0.addView(r2, r3);
        return;
    L6:
        addView(r2, r3);
    }

    private LayoutInflater getInflater() {
        if (this.mInflater != null) goto L6;
        this.mInflater = LayoutInflater.from(getContext());
    L6:
        return this.mInflater;
    }

    private void insertCheckBox() {
        CheckBox r0 = (CheckBox) getInflater().inflate(R$layout.abc_list_menu_item_checkbox, this, false);
        this.mCheckBox = r0;
        addContentView(r0);
    }

    private void insertIconView() {
        ImageView r0 = (ImageView) getInflater().inflate(R$layout.abc_list_menu_item_icon, this, false);
        this.mIconView = r0;
        addContentView(r0, 0);
    }

    private void insertRadioButton() {
        RadioButton r0 = (RadioButton) getInflater().inflate(R$layout.abc_list_menu_item_radio, this, false);
        this.mRadioButton = r0;
        addContentView(r0);
    }

    private void setSubMenuArrowVisible(boolean r1) {
        ImageView r0 = this.mSubMenuArrowView;
        if (r0 == null) goto L9;
        if (r1 == false) goto L6;
        int r12 = 0;
    L7:
        r0.setVisibility(r12);
        return;
    L6:
        r12 = 8;
        goto L7
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect r4) {
        ImageView r0 = this.mGroupDivider;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getVisibility() != 0) goto L9;
        LinearLayout.LayoutParams r02 = (LinearLayout.LayoutParams) this.mGroupDivider.getLayoutParams();
        r4.top += (this.mGroupDivider.getHeight() + r02.topMargin) + r02.bottomMargin;
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl r2, int r3) {
        this.mItemData = r2;
        if (r2.isVisible() == false) goto L5;
        int r32 = 0;
    L6:
        setVisibility(r32);
        setTitle(r2.getTitleForItemView(this));
        setCheckable(r2.isCheckable());
        setShortcut(r2.shouldShowShortcut(), r2.getShortcut());
        setIcon(r2.getIcon());
        setEnabled(r2.isEnabled());
        setSubMenuArrowVisible(r2.hasSubMenu());
        setContentDescription(r2.getContentDescription());
        return;
    L5:
        r32 = 8;
        goto L6
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setBackground(this.mBackground);
        TextView r0 = (TextView) findViewById(R$id.title);
        this.mTitleView = r0;
        int r1 = this.mTextAppearance;
        if (r1 == (-1)) goto L5;
        r0.setTextAppearance(this.mTextAppearanceContext, r1);
    L5:
        this.mShortcutView = (TextView) findViewById(R$id.shortcut);
        ImageView r02 = (ImageView) findViewById(R$id.submenuarrow);
        this.mSubMenuArrowView = r02;
        if (r02 == null) goto L8;
        r02.setImageDrawable(this.mSubMenuArrow);
    L8:
        this.mGroupDivider = (ImageView) findViewById(R$id.group_divider);
        this.mContent = (LinearLayout) findViewById(R$id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        if (this.mIconView != null) goto L5;
    L11:
        super.onMeasure(r4, r5);
        return;
    L5:
        if (this.mPreserveIconSpacing == false) goto L11;
        ViewGroup.LayoutParams r0 = getLayoutParams();
        LinearLayout.LayoutParams r1 = (LinearLayout.LayoutParams) this.mIconView.getLayoutParams();
        int r02 = r0.height;
        if (r02 <= 0) goto L11;
        if (r1.width > 0) goto L11;
        r1.width = r02;
        goto L11
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setCheckable(boolean r4) {
        if (r4 == true) goto L9;
        if (this.mRadioButton != null) goto L9;
        if (this.mCheckBox != null) goto L9;
        return;
    L9:
        if (this.mItemData.isExclusiveCheckable() == false) goto L15;
        if (this.mRadioButton != null) goto L13;
        insertRadioButton();
    L13:
        CompoundButton r0 = this.mRadioButton;
        View r1 = this.mCheckBox;
    L19:
        if (r4 == false) goto L28;
        r0.setChecked(this.mItemData.isChecked());
        if (r0.getVisibility() == 0) goto L23;
        r0.setVisibility(0);
    L23:
        if (r1 != null) goto L25;
        return;
    L25:
        if (r1.getVisibility() == 8) goto L36;
        r1.setVisibility(8);
        return;
    L36:
        return;
    L28:
        CheckBox r42 = this.mCheckBox;
        if (r42 == null) goto L31;
        r42.setVisibility(8);
    L31:
        RadioButton r3 = this.mRadioButton;
        if (r3 == null) goto L37;
        r3.setVisibility(8);
        return;
    L37:
        return;
    L15:
        if (this.mCheckBox != null) goto L17;
        insertCheckBox();
    L17:
        r0 = this.mCheckBox;
        r1 = this.mRadioButton;
        goto L19
    }

    public void setChecked(boolean r2) {
        if (this.mItemData.isExclusiveCheckable() == false) goto L9;
        if (this.mRadioButton != null) goto L7;
        insertRadioButton();
    L7:
        CompoundButton r1 = this.mRadioButton;
    L12:
        r1.setChecked(r2);
        return;
    L9:
        if (this.mCheckBox != null) goto L11;
        insertCheckBox();
    L11:
        r1 = this.mCheckBox;
        goto L12
    }

    public void setForceShowIcon(boolean r1) {
        this.mForceShowIcon = r1;
        this.mPreserveIconSpacing = r1;
    }

    public void setGroupDividerEnabled(boolean r2) {
        ImageView r0 = this.mGroupDivider;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mHasListDivider == true) goto L8;
        if (r2 == false) goto L8;
        int r1 = 0;
    L9:
        r0.setVisibility(r1);
        return;
    L8:
        r1 = 8;
        goto L9
    }

    public void setIcon(Drawable r5) {
        if (this.mItemData.shouldShowIcon() == false) goto L5;
    L8:
        boolean r0 = true;
    L9:
        if (r0 == false) goto L11;
    L13:
        ImageView r2 = this.mIconView;
        if (r2 != null) goto L19;
        if (r5 != null) goto L19;
        if (this.mPreserveIconSpacing == true) goto L19;
        return;
    L19:
        if (r2 != null) goto L21;
        insertIconView();
    L21:
        if (r5 == null) goto L23;
    L27:
        ImageView r22 = this.mIconView;
        if (r0 == true) goto L31;
        r5 = null;
    L31:
        r22.setImageDrawable(r5);
        if (this.mIconView.getVisibility() == 0) goto L35;
        this.mIconView.setVisibility(0);
        return;
    L35:
        return;
    L23:
        if (this.mPreserveIconSpacing == true) goto L27;
        this.mIconView.setVisibility(8);
        return;
    L11:
        if (this.mPreserveIconSpacing == true) goto L13;
        return;
    L5:
        if (this.mForceShowIcon == true) goto L8;
        r0 = false;
        goto L9
    }

    public void setShortcut(boolean r2, char r3) {
        if (r2 == true) goto L4;
    L6:
        int r22 = 8;
    L7:
        if (r22 != 0) goto L10;
        this.mShortcutView.setText(this.mItemData.getShortcutLabel());
    L10:
        if (this.mShortcutView.getVisibility() == r22) goto L13;
        this.mShortcutView.setVisibility(r22);
        return;
    L13:
        return;
    L4:
        if (this.mItemData.shouldShowShortcut() == false) goto L6;
        r22 = 0;
        goto L7
    }

    public void setTitle(CharSequence r2) {
        TextView r0 = this.mTitleView;
        if (r2 == null) goto L9;
        r0.setText(r2);
        if (this.mTitleView.getVisibility() == 0) goto L12;
        this.mTitleView.setVisibility(0);
        return;
    L12:
        return;
    L9:
        if (r0.getVisibility() == 8) goto L13;
        this.mTitleView.setVisibility(8);
        return;
    }
}
