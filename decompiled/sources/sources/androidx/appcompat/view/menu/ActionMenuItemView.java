package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ForwardingListener;
import androidx.appcompat.widget.TooltipCompat;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class ActionMenuItemView extends AppCompatTextView implements MenuView.ItemView, View.OnClickListener, ActionMenuView.ActionMenuChildView {
    private boolean mAllowTextWithIcon;
    private boolean mExpandedFormat;
    private ForwardingListener mForwardingListener;
    private Drawable mIcon;
    MenuItemImpl mItemData;
    MenuBuilder.ItemInvoker mItemInvoker;
    private int mMaxIconSize;
    private int mMinWidth;
    PopupCallback mPopupCallback;
    private int mSavedPaddingLeft;
    private CharSequence mTitle;

    public class ActionMenuItemForwardingListener extends ForwardingListener {
        final /* synthetic */ ActionMenuItemView this$0;

        public ActionMenuItemForwardingListener(ActionMenuItemView r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public ShowableListMenu getPopup() {
            PopupCallback r0 = this.this$0.mPopupCallback;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getPopup();
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public boolean onForwardingStarted() {
            ActionMenuItemView r0 = this.this$0;
            MenuBuilder.ItemInvoker r1 = r0.mItemInvoker;
            if (r1 != null) goto L5;
        L12:
            return false;
        L5:
            if (r1.invokeItem(r0.mItemData) == false) goto L12;
            ShowableListMenu r3 = getPopup();
            if (r3 == null) goto L12;
            if (r3.isShowing() == false) goto L12;
            return true;
        }
    }

    public static abstract class PopupCallback {
        public PopupCallback() {
        }

        public abstract ShowableListMenu getPopup();
    }

    public ActionMenuItemView(Context r2) {
        this(r2, null);
    }

    public ActionMenuItemView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public ActionMenuItemView(Context r4, AttributeSet r5, int r6) {
        super(r4, r5, r6);
        Resources r0 = r4.getResources();
        this.mAllowTextWithIcon = shouldAllowTextWithIcon();
        TypedArray r42 = r4.obtainStyledAttributes(r5, R$styleable.ActionMenuItemView, r6, 0);
        this.mMinWidth = r42.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        r42.recycle();
        this.mMaxIconSize = (int) ((r0.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.mSavedPaddingLeft = -1;
        setSaveEnabled(false);
    }

    private boolean shouldAllowTextWithIcon() {
        Configuration r4 = getContext().getResources().getConfiguration();
        int r0 = r4.screenWidthDp;
        int r1 = r4.screenHeightDp;
        if (r0 < 480) goto L5;
        return true;
    L5:
        if (r0 < 640) goto L8;
        if (r1 < 480) goto L8;
        return true;
    L8:
        if (r4.orientation == 2) goto L15;
        return false;
    L15:
        return true;
    }

    private void updateTextButtonVisibility() {
        boolean r1 = true;
        boolean r0 = !TextUtils.isEmpty(this.mTitle);
        if (this.mIcon != null) goto L5;
    L12:
        boolean r02 = r0 & r1;
        CharSequence r12 = null;
        if (r02 == false) goto L15;
        CharSequence r2 = this.mTitle;
    L16:
        setText(r2);
        CharSequence r22 = this.mItemData.getContentDescription();
        if (TextUtils.isEmpty(r22) == false) goto L22;
        if (r02 == false) goto L20;
        CharSequence r23 = null;
    L21:
        setContentDescription(r23);
    L23:
        CharSequence r24 = this.mItemData.getTooltipText();
        if (TextUtils.isEmpty(r24) == false) goto L30;
        if (r02 == true) goto L28;
        r12 = this.mItemData.getTitle();
    L28:
        TooltipCompat.setTooltipText(this, r12);
        return;
    L30:
        TooltipCompat.setTooltipText(this, r24);
        return;
    L20:
        r23 = this.mItemData.getTitle();
        goto L21
    L22:
        setContentDescription(r22);
        goto L23
    L15:
        r2 = null;
        goto L16
    L5:
        if (this.mItemData.showsTextAsAction() == true) goto L7;
    L11:
        r1 = false;
        goto L12
    L7:
        if (this.mAllowTextWithIcon == true) goto L12;
        if (this.mExpandedFormat == false) goto L11;
        goto L11
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl r1, int r2) {
        this.mItemData = r1;
        setIcon(r1.getIcon());
        setTitle(r1.getTitleForItemView(this));
        setId(r1.getItemId());
        if (r1.isVisible() == false) goto L5;
        int r22 = 0;
    L6:
        setVisibility(r22);
        setEnabled(r1.isEnabled());
        if (r1.hasSubMenu() == true) goto L9;
        return;
    L9:
        if (this.mForwardingListener != null) goto L13;
        this.mForwardingListener = new ActionMenuItemForwardingListener(this);
        return;
    L13:
        return;
    L5:
        r22 = 8;
        goto L6
    }

    @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
    public boolean needsDividerAfter() {
        return hasText();
    }

    @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
    public boolean needsDividerBefore() {
        if (hasText() == true) goto L5;
        return false;
    L5:
        if (this.mItemData.getIcon() != null) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View r1) {
        MenuBuilder.ItemInvoker r12 = this.mItemInvoker;
        if (r12 == null) goto L6;
        r12.invokeItem(this.mItemData);
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        this.mAllowTextWithIcon = shouldAllowTextWithIcon();
        updateTextButtonVisibility();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int r6, int r7) {
        boolean r0 = hasText();
        if (r0 == false) goto L7;
        int r1 = this.mSavedPaddingLeft;
        if (r1 < 0) goto L7;
        super.setPadding(r1, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    L7:
        super.onMeasure(r6, r7);
        int r12 = View.MeasureSpec.getMode(r6);
        int r62 = View.MeasureSpec.getSize(r6);
        int r2 = getMeasuredWidth();
        int r3 = this.mMinWidth;
        if (r12 != Integer.MIN_VALUE) goto L11;
        r3 = Math.min(r62, r3);
    L11:
        if (r12 != 1073741824) goto L13;
    L16:
        if (r0 == false) goto L18;
        return;
    L18:
        if (this.mIcon == null) goto L22;
        super.setPadding((getMeasuredWidth() - this.mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        return;
    L22:
        return;
    L13:
        if (this.mMinWidth <= 0) goto L16;
        if (r2 >= r3) goto L16;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r3, TLObject.FLAG_30), r7);
        goto L16
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable r1) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        if (this.mItemData.hasSubMenu() == false) goto L11;
        ForwardingListener r0 = this.mForwardingListener;
        if (r0 == null) goto L11;
        if (r0.onTouch(this, r2) == false) goto L11;
        return true;
    L11:
        return super.onTouchEvent(r2);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return true;
    }

    public void setCheckable(boolean r1) {
    }

    public void setChecked(boolean r1) {
    }

    public void setExpandedFormat(boolean r2) {
        if (this.mExpandedFormat == r2) goto L8;
        this.mExpandedFormat = r2;
        MenuItemImpl r1 = this.mItemData;
        if (r1 == null) goto L9;
        r1.actionFormatChanged();
        return;
    L9:
        return;
    }

    public void setIcon(Drawable r5) {
        this.mIcon = r5;
        if (r5 == null) goto L11;
        int r0 = r5.getIntrinsicWidth();
        int r1 = r5.getIntrinsicHeight();
        int r2 = this.mMaxIconSize;
        if (r0 <= r2) goto L7;
        r1 = (int) (r1 * (r2 / r0));
        r0 = r2;
    L7:
        if (r1 <= r2) goto L9;
        r0 = (int) (r0 * (r2 / r1));
    L10:
        r5.setBounds(0, 0, r0, r2);
        goto L11
    L9:
        r2 = r1;
    L11:
        setCompoundDrawables(r5, null, null, null);
        updateTextButtonVisibility();
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker r1) {
        this.mItemInvoker = r1;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int r1, int r2, int r3, int r4) {
        this.mSavedPaddingLeft = r1;
        super.setPadding(r1, r2, r3, r4);
    }

    public void setPopupCallback(PopupCallback r1) {
        this.mPopupCallback = r1;
    }

    public void setTitle(CharSequence r1) {
        this.mTitle = r1;
        updateTextButtonVisibility();
    }
}
