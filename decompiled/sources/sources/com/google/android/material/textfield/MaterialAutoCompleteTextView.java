package com.google.android.material.textfield;

import android.R;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$style;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.ListPopupWindow;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final String SWITCH_ACCESS_ACTIVITY_NAME = "SwitchAccess";
    private final AccessibilityManager accessibilityManager;
    private ColorStateList dropDownBackgroundTint;
    private final ListPopupWindow modalListPopup;
    private final float popupElevation;
    private final int[] selectedStateSet;
    private final int simpleItemLayout;
    private int simpleItemSelectedColor;
    private ColorStateList simpleItemSelectedRippleColor;
    private final Rect tempRect;

    /* renamed from: com.google.android.material.textfield.MaterialAutoCompleteTextView$1, reason: invalid class name */
    public class AnonymousClass1 implements AdapterView.OnItemClickListener {
        final /* synthetic */ MaterialAutoCompleteTextView this$0;

        public AnonymousClass1(MaterialAutoCompleteTextView r1) {
            this.this$0 = r1;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> r7, View r8, int r9, long r10) {
            MaterialAutoCompleteTextView r72 = this.this$0;
            if (r9 >= 0) goto L5;
            Object r73 = MaterialAutoCompleteTextView.access$000(r72).getSelectedItem();
        L6:
            MaterialAutoCompleteTextView r0 = this.this$0;
            r0.setText(MaterialAutoCompleteTextView.access$100(r0, r73), false);
            AdapterView.OnItemClickListener r02 = this.this$0.getOnItemClickListener();
            if (r02 == null) goto L14;
            if (r8 == null) goto L12;
            if (r9 < 0) goto L12;
        L11:
            View r2 = r8;
            int r3 = r9;
            long r4 = r10;
            r02.onItemClick(MaterialAutoCompleteTextView.access$000(this.this$0).getListView(), r2, r3, r4);
        L12:
            r8 = MaterialAutoCompleteTextView.access$000(this.this$0).getSelectedView();
            r9 = MaterialAutoCompleteTextView.access$000(this.this$0).getSelectedItemPosition();
            r10 = MaterialAutoCompleteTextView.access$000(this.this$0).getSelectedItemId();
        L14:
            MaterialAutoCompleteTextView.access$000(this.this$0).dismiss();
            return;
        L5:
            r73 = r72.getAdapter().getItem(r9);
            goto L6
        }
    }

    public class MaterialArrayAdapter<T> extends ArrayAdapter<String> {
        private ColorStateList pressedRippleColor;
        private ColorStateList selectedItemRippleOverlaidColor;
        final /* synthetic */ MaterialAutoCompleteTextView this$0;

        public MaterialArrayAdapter(MaterialAutoCompleteTextView r1, Context r2, int r3, String[] r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            updateSelectedItemColorStateList();
        }

        private ColorStateList createItemSelectedColorStateList() {
            if (hasSelectedColor() == true) goto L5;
            return null;
        L5:
            if (hasSelectedRippleColor() == false) goto L11;
            int[] r0 = {R.attr.state_hovered, -16842919};
            int[] r1 = {R.attr.state_selected, -16842919};
            int r2 = MaterialAutoCompleteTextView.access$400(this.this$0).getColorForState(r1, 0);
            int r4 = MaterialAutoCompleteTextView.access$400(this.this$0).getColorForState(r0, 0);
            int[] r6 = {MaterialColors.layer(MaterialAutoCompleteTextView.access$200(this.this$0), r2), MaterialColors.layer(MaterialAutoCompleteTextView.access$200(this.this$0), r4), MaterialAutoCompleteTextView.access$200(this.this$0)};
            return new ColorStateList(new int[][]{r1, r0, new int[0]}, r6);
        L11:
            return null;
        }

        private Drawable getSelectedItemDrawable() {
            if (hasSelectedColor() == true) goto L5;
            return null;
        L5:
            ColorDrawable r0 = new ColorDrawable(MaterialAutoCompleteTextView.access$200(this.this$0));
            if (this.pressedRippleColor == null) goto L11;
            r0.setTintList(this.selectedItemRippleOverlaidColor);
            RippleDrawable r2 = new RippleDrawable(this.pressedRippleColor, r0, null);
            FocusRingDrawable r02 = FocusRingDrawable.layer(getContext(), r2);
            if (r02 == null) goto L10;
            r02.setFocusRingStateSet(MaterialAutoCompleteTextView.access$300(this.this$0));
        L10:
            return r2;
        L11:
            return r0;
        }

        private boolean hasSelectedColor() {
            if (MaterialAutoCompleteTextView.access$200(this.this$0) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        private boolean hasSelectedRippleColor() {
            if (MaterialAutoCompleteTextView.access$400(this.this$0) == null) goto L6;
            return true;
        L6:
            return false;
        }

        private ColorStateList sanitizeDropdownItemSelectedRippleColor() {
            if (hasSelectedRippleColor() == true) goto L6;
            return null;
        L6:
            int[] r0 = {R.attr.state_pressed};
            int[] r2 = {MaterialAutoCompleteTextView.access$400(this.this$0).getColorForState(r0, 0), 0};
            return new ColorStateList(new int[][]{r0, new int[0]}, r2);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int r2, View r3, ViewGroup r4) {
            View r22 = super.getView(r2, r3, r4);
            if ((r22 instanceof TextView) == false) goto L9;
            TextView r32 = (TextView) r22;
            if (this.this$0.getText().toString().contentEquals(r32.getText()) == false) goto L7;
            Drawable r1 = getSelectedItemDrawable();
        L8:
            r32.setBackground(r1);
            goto L9
        L7:
            r1 = null;
        L9:
            return r22;
        }

        public void updateSelectedItemColorStateList() {
            this.pressedRippleColor = sanitizeDropdownItemSelectedRippleColor();
            this.selectedItemRippleOverlaidColor = createItemSelectedColorStateList();
        }
    }

    public MaterialAutoCompleteTextView(Context r2) {
        this(r2, null);
    }

    public MaterialAutoCompleteTextView(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.autoCompleteTextViewStyle);
    }

    public MaterialAutoCompleteTextView(Context r8, AttributeSet r9, int r10) {
        super(MaterialThemeOverlay.wrap(r8, r9, r10, 0), r9, r10);
        this.selectedStateSet = new int[]{R.attr.state_selected};
        this.tempRect = new Rect();
        Context r1 = getContext();
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r1, r9, com.google.android.material.R.styleable.MaterialAutoCompleteTextView, r10, R$style.Widget_AppCompat_AutoCompleteTextView, new int[0]);
        if (r82.hasValue(com.google.android.material.R.styleable.MaterialAutoCompleteTextView_android_inputType) == true) goto L5;
    L7:
        this.simpleItemLayout = r82.getResourceId(com.google.android.material.R.styleable.MaterialAutoCompleteTextView_simpleItemLayout, com.google.android.material.R.layout.mtrl_auto_complete_simple_item);
        this.popupElevation = r82.getDimensionPixelOffset(com.google.android.material.R.styleable.MaterialAutoCompleteTextView_android_popupElevation, com.google.android.material.R.dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        if (r82.hasValue(com.google.android.material.R.styleable.MaterialAutoCompleteTextView_dropDownBackgroundTint) == false) goto L10;
        this.dropDownBackgroundTint = ColorStateList.valueOf(r82.getColor(com.google.android.material.R.styleable.MaterialAutoCompleteTextView_dropDownBackgroundTint, 0));
    L10:
        this.simpleItemSelectedColor = r82.getColor(com.google.android.material.R.styleable.MaterialAutoCompleteTextView_simpleItemSelectedColor, 0);
        this.simpleItemSelectedRippleColor = MaterialResources.getColorStateList(r1, r82, com.google.android.material.R.styleable.MaterialAutoCompleteTextView_simpleItemSelectedRippleColor);
        this.accessibilityManager = (AccessibilityManager) r1.getSystemService("accessibility");
        ListPopupWindow r92 = new ListPopupWindow(r1);
        this.modalListPopup = r92;
        r92.setModal(true);
        r92.setAnchorView(this);
        r92.setInputMethodMode(2);
        r92.setAdapter(getAdapter());
        r92.setOnItemClickListener(new AnonymousClass1(this));
        if (r82.hasValue(com.google.android.material.R.styleable.MaterialAutoCompleteTextView_simpleItems) == false) goto L13;
        setSimpleItems(r82.getResourceId(com.google.android.material.R.styleable.MaterialAutoCompleteTextView_simpleItems, 0));
    L13:
        r82.recycle();
        return;
    L5:
        if (r82.getInt(com.google.android.material.R.styleable.MaterialAutoCompleteTextView_android_inputType, 0) != 0) goto L7;
        setKeyListener(null);
        goto L7
    }

    public static /* synthetic */ ListPopupWindow access$000(MaterialAutoCompleteTextView r0) {
        return r0.modalListPopup;
    }

    public static /* synthetic */ CharSequence access$100(MaterialAutoCompleteTextView r0, Object r1) {
        return r0.convertSelectionToString(r1);
    }

    public static /* synthetic */ int access$200(MaterialAutoCompleteTextView r0) {
        return r0.simpleItemSelectedColor;
    }

    public static /* synthetic */ int[] access$300(MaterialAutoCompleteTextView r0) {
        return r0.selectedStateSet;
    }

    public static /* synthetic */ ColorStateList access$400(MaterialAutoCompleteTextView r0) {
        return r0.simpleItemSelectedRippleColor;
    }

    private TextInputLayout findTextInputLayoutAncestor() {
        ViewParent r1 = getParent();
    L3:
        if (r1 == null) goto L9;
        if ((r1 instanceof TextInputLayout) == true) goto L7;
        r1 = r1.getParent();
        goto L3
    L7:
        return (TextInputLayout) r1;
    L9:
        return null;
    }

    private boolean isPopupRequired() {
        if (isTouchExplorationEnabled() == false) goto L5;
        return true;
    L5:
        if (isSwitchAccessEnabled() == true) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isSwitchAccessEnabled() {
        AccessibilityManager r0 = this.accessibilityManager;
        if (r0 != null) goto L5;
    L18:
        return false;
    L5:
        if (r0.isEnabled() == false) goto L18;
        List<AccessibilityServiceInfo> r3 = this.accessibilityManager.getEnabledAccessibilityServiceList(16);
        if (r3 == null) goto L18;
        Iterator<AccessibilityServiceInfo> r32 = r3.iterator();
    L11:
        if (r32.hasNext() == false) goto L18;
        AccessibilityServiceInfo r02 = r32.next();
        if (r02.getSettingsActivityName() == null) goto L11;
        if (r02.getSettingsActivityName().contains(SWITCH_ACCESS_ACTIVITY_NAME) == false) goto L11;
        return true;
    }

    private boolean isTouchExplorationEnabled() {
        AccessibilityManager r0 = this.accessibilityManager;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isTouchExplorationEnabled() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private int measureContentWidth() {
        ListAdapter r0 = getAdapter();
        TextInputLayout r1 = findTextInputLayoutAncestor();
        int r2 = 0;
        if (r0 == null) goto L20;
        if (r1 == null) goto L20;
        int r3 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int r4 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int r5 = Math.min(r0.getCount(), Math.max(0, this.modalListPopup.getSelectedItemPosition()) + 15);
        int r8 = Math.max(0, r5 - 15);
        View r9 = null;
        int r6 = 0;
    L7:
        if (r8 >= r5) goto L15;
        int r10 = r0.getItemViewType(r8);
        if (r10 == r2) goto L11;
        r9 = null;
        r2 = r10;
    L11:
        r9 = r0.getView(r8, r9, r1);
        if (r9.getLayoutParams() != null) goto L14;
        r9.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    L14:
        r9.measure(r3, r4);
        r6 = Math.max(r6, r9.getMeasuredWidth());
        r8 = r8 + 1;
        goto L7
    L15:
        Drawable r02 = this.modalListPopup.getBackground();
        if (r02 == null) goto L19;
        r02.getPadding(this.tempRect);
        Rect r12 = this.tempRect;
        r6 = r6 + (r12.left + r12.right);
    L19:
        return r6 + r1.getEndIconView().getMeasuredWidth();
    L20:
        return 0;
    }

    private void onInputTypeChanged() {
        TextInputLayout r0 = findTextInputLayoutAncestor();
        if (r0 == null) goto L6;
        r0.updateEditTextBoxBackgroundIfNeeded();
        return;
    }

    @Override // android.widget.AutoCompleteTextView
    public void dismissDropDown() {
        if (isPopupRequired() == false) goto L6;
        this.modalListPopup.dismiss();
        return;
    L6:
        super.dismissDropDown();
    }

    public ColorStateList getDropDownBackgroundTintList() {
        return this.dropDownBackgroundTint;
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout r0 = findTextInputLayoutAncestor();
        if (r0 == null) goto L9;
        if (r0.isProvidingHint() == false) goto L9;
        return r0.getHint();
    L9:
        return super.getHint();
    }

    public float getPopupElevation() {
        return this.popupElevation;
    }

    public int getSimpleItemSelectedColor() {
        return this.simpleItemSelectedColor;
    }

    public ColorStateList getSimpleItemSelectedRippleColor() {
        return this.simpleItemSelectedRippleColor;
    }

    @Override // android.widget.AutoCompleteTextView
    public boolean isPopupShowing() {
        ListPopupWindow r0 = this.modalListPopup;
        if (r0 == null) goto L9;
        if (r0.isShowing() == false) goto L9;
        return true;
    L9:
        return super.isPopupShowing();
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout r0 = findTextInputLayoutAncestor();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isProvidingHint() == true) goto L7;
        return;
    L7:
        if (super.getHint() == null) goto L9;
        return;
    L9:
        if (ManufacturerUtils.isMeizuDevice() == false) goto L15;
        setHint(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    }

    @Override // android.widget.AutoCompleteTextView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.modalListPopup.dismiss();
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r2, KeyEvent r3) {
        if (isPopupShowing() == false) goto L7;
        return super.onKeyDown(r2, r3);
    L7:
        if (shouldShowPopup(r2) == false) goto L14;
        TextInputLayout r1 = findTextInputLayoutAncestor();
        if (r1 == null) goto L15;
        r1.getEndIconView().performClick();
        return true;
    L15:
        return true;
    L14:
        return super.onKeyDown(r2, r3);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        if (View.MeasureSpec.getMode(r2) != Integer.MIN_VALUE) goto L6;
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), measureContentWidth()), View.MeasureSpec.getSize(r2)), getMeasuredHeight());
        return;
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean r2) {
        if (isPopupRequired() == false) goto L5;
        return;
    L5:
        super.onWindowFocusChanged(r2);
    }

    @Override // android.widget.AutoCompleteTextView
    public <T extends ListAdapter & Filterable> void setAdapter(T r1) {
        super.setAdapter(r1);
        this.modalListPopup.setAdapter(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundDrawable(Drawable r1) {
        super.setDropDownBackgroundDrawable(r1);
        ListPopupWindow r0 = this.modalListPopup;
        if (r0 == null) goto L6;
        r0.setBackgroundDrawable(r1);
        return;
    }

    public void setDropDownBackgroundTint(int r1) {
        setDropDownBackgroundTintList(ColorStateList.valueOf(r1));
    }

    public void setDropDownBackgroundTintList(ColorStateList r2) {
        this.dropDownBackgroundTint = r2;
        Drawable r22 = getDropDownBackground();
        if ((r22 instanceof MaterialShapeDrawable) == false) goto L6;
        ((MaterialShapeDrawable) r22).setFillColor(this.dropDownBackgroundTint);
        return;
    }

    @Override // android.widget.AutoCompleteTextView
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener r1) {
        super.setOnItemSelectedListener(r1);
        this.modalListPopup.setOnItemSelectedListener(getOnItemSelectedListener());
    }

    @Override // android.widget.TextView
    public void setRawInputType(int r1) {
        super.setRawInputType(r1);
        onInputTypeChanged();
    }

    public void setSimpleItemSelectedColor(int r1) {
        this.simpleItemSelectedColor = r1;
        if ((getAdapter() instanceof MaterialArrayAdapter) == false) goto L6;
        ((MaterialArrayAdapter) getAdapter()).updateSelectedItemColorStateList();
        return;
    }

    public void setSimpleItemSelectedRippleColor(ColorStateList r1) {
        this.simpleItemSelectedRippleColor = r1;
        if ((getAdapter() instanceof MaterialArrayAdapter) == false) goto L6;
        ((MaterialArrayAdapter) getAdapter()).updateSelectedItemColorStateList();
        return;
    }

    public void setSimpleItems(int r2) {
        setSimpleItems(getResources().getStringArray(r2));
    }

    public void setSimpleItems(String[] r4) {
        setAdapter(new MaterialArrayAdapter(this, getContext(), this.simpleItemLayout, r4));
    }

    public boolean shouldShowPopup(int r5) {
        if (r5 != 66) goto L5;
    L8:
        boolean r0 = true;
    L10:
        if (r5 != 62) goto L12;
        boolean r52 = true;
    L14:
        if (getKeyListener() == null) goto L20;
        if (r0 == true) goto L17;
    L19:
        return false;
    L17:
        if (getMaxLines() != 1) goto L19;
        return true;
    L20:
        if (r0 == true) goto L24;
        if (r52 == true) goto L24;
        return false;
    L24:
        return true;
    L12:
        r52 = false;
        goto L14
    L5:
        if (r5 == 23) goto L8;
        r0 = false;
        goto L10
    }

    @Override // android.widget.AutoCompleteTextView
    public void showDropDown() {
        if (isPopupRequired() == false) goto L6;
        this.modalListPopup.show();
        return;
    L6:
        super.showDropDown();
    }
}
