package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.StateListCornerSize;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public class MaterialButtonToggleGroup extends MaterialButtonGroup {
    private static final int DEF_STYLE_RES = 0;
    private static final String LOG_TAG = "MButtonToggleGroup";
    private Set<Integer> checkedIds;
    private final int defaultCheckId;
    private final LinkedHashSet<OnButtonCheckedListener> onButtonCheckedListeners;
    private boolean selectionRequired;
    private boolean singleSelection;
    private boolean skipCheckedStateTracker;

    /* renamed from: com.google.android.material.button.MaterialButtonToggleGroup$1, reason: invalid class name */
    public class AnonymousClass1 extends AccessibilityDelegateCompat {
        final /* synthetic */ MaterialButtonToggleGroup this$0;

        public AnonymousClass1(MaterialButtonToggleGroup r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r7, AccessibilityNodeInfoCompat r8) {
            super.onInitializeAccessibilityNodeInfo(r7, r8);
            r8.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, MaterialButtonToggleGroup.access$000(this.this$0, r7), 1, false, ((MaterialButton) r7).isChecked()));
        }
    }

    public interface OnButtonCheckedListener {
        void onButtonChecked(MaterialButtonToggleGroup r1, int r2, boolean r3);
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_MaterialButtonToggleGroup;
    }

    public MaterialButtonToggleGroup(Context r2) {
        this(r2, null);
    }

    public MaterialButtonToggleGroup(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialButtonToggleGroupStyle);
    }

    public MaterialButtonToggleGroup(Context r7, AttributeSet r8, int r9) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r4), r8, r9);
        this.onButtonCheckedListeners = new LinkedHashSet();
        this.skipCheckedStateTracker = false;
        this.checkedIds = new HashSet();
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(getContext(), r8, R.styleable.MaterialButtonToggleGroup, r9, r4, new int[0]);
        setSingleSelection(r82.getBoolean(R.styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.defaultCheckId = r82.getResourceId(R.styleable.MaterialButtonToggleGroup_checkedButton, -1);
        this.selectionRequired = r82.getBoolean(R.styleable.MaterialButtonToggleGroup_selectionRequired, false);
        if (this.innerCornerSize != null) goto L5;
        this.innerCornerSize = StateListCornerSize.create(new AbsoluteCornerSize(0.0f));
    L5:
        setEnabled(r82.getBoolean(R.styleable.MaterialButtonToggleGroup_android_enabled, true));
        r82.recycle();
        setImportantForAccessibility(1);
    }

    public static /* synthetic */ int access$000(MaterialButtonToggleGroup r0, View r1) {
        return r0.getIndexWithinVisibleButtons(r1);
    }

    private void checkInternal(int r3, boolean r4) {
        if (r3 != (-1)) goto L6;
        Log.e(LOG_TAG, "Button ID is not valid: " + r3);
        return;
    L6:
        HashSet r0 = new HashSet(this.checkedIds);
        if (r4 == true) goto L9;
    L16:
        if (r4 == false) goto L18;
        return;
    L18:
        if (r0.contains(Integer.valueOf(r3)) == true) goto L20;
        return;
    L20:
        if (this.selectionRequired == true) goto L22;
    L23:
        r0.remove(Integer.valueOf(r3));
    L24:
        updateCheckedIds(r0);
        return;
    L22:
        if (r0.size() <= 1) goto L24;
    L9:
        if (r0.contains(Integer.valueOf(r3)) == true) goto L16;
        if (this.singleSelection == true) goto L13;
    L15:
        r0.add(Integer.valueOf(r3));
        goto L24
    L13:
        if (r0.isEmpty() == true) goto L15;
        r0.clear();
        goto L15
    }

    private void dispatchOnButtonChecked(int r3, boolean r4) {
        Iterator<OnButtonCheckedListener> r0 = this.onButtonCheckedListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onButtonChecked(this, r3, r4);
        goto L4
    }

    private String getChildrenA11yClassName() {
        if (this.singleSelection == false) goto L7;
        Class r0 = RadioButton.class;
    L6:
        return r0.getName();
    L7:
        r0 = ToggleButton.class;
        goto L6
    }

    private int getIndexWithinVisibleButtons(View r5) {
        if ((r5 instanceof MaterialButton) == true) goto L5;
        return -1;
    L5:
        int r0 = 0;
        int r2 = 0;
    L7:
        if (r0 >= getChildCount()) goto L17;
        if (getChildAt(r0) == r5) goto L10;
        if ((getChildAt(r0) instanceof MaterialButton) == false) goto L16;
        if (isChildVisible(r0) == false) goto L16;
        r2 = r2 + 1;
    L16:
        r0 = r0 + 1;
        goto L7
    L10:
        return r2;
    L17:
        return -1;
    }

    private int getVisibleButtonCount() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= getChildCount()) goto L11;
        if ((getChildAt(r0) instanceof MaterialButton) == false) goto L10;
        if (isChildVisible(r0) == false) goto L10;
        r1 = r1 + 1;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return r1;
    }

    private boolean isChildVisible(int r1) {
        if (getChildAt(r1).getVisibility() == 8) goto L6;
        return true;
    L6:
        return false;
    }

    private void setCheckedStateForView(int r2, boolean r3) {
        View r22 = findViewById(r2);
        if ((r22 instanceof MaterialButton) == false) goto L6;
        this.skipCheckedStateTracker = true;
        ((MaterialButton) r22).setChecked(r3);
        this.skipCheckedStateTracker = false;
        return;
    }

    private void setupButtonChild(MaterialButton r3) {
        r3.setMaxLines(1);
        r3.setEllipsize(TextUtils.TruncateAt.END);
        r3.setCheckable(true);
        r3.setA11yClassName(getChildrenA11yClassName());
    }

    private void updateCheckedIds(Set<Integer> r6) {
        Set<Integer> r0 = this.checkedIds;
        this.checkedIds = new HashSet(r6);
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L9;
        int r2 = getChildButton(r1).getId();
        setCheckedStateForView(r2, r6.contains(Integer.valueOf(r2)));
        if (r0.contains(Integer.valueOf(r2)) == r6.contains(Integer.valueOf(r2))) goto L8;
        dispatchOnButtonChecked(r2, r6.contains(Integer.valueOf(r2)));
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        invalidate();
    }

    private void updateChildrenA11yClassName() {
        String r0 = getChildrenA11yClassName();
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L6;
        getChildButton(r1).setA11yClassName(r0);
        r1 = r1 + 1;
        goto L4
    }

    public void addOnButtonCheckedListener(OnButtonCheckedListener r1) {
        this.onButtonCheckedListeners.add(r1);
    }

    @Override // com.google.android.material.button.MaterialButtonGroup, android.view.ViewGroup
    public void addView(View r2, int r3, ViewGroup.LayoutParams r4) {
        if ((r2 instanceof MaterialButton) == true) goto L6;
        Log.e(LOG_TAG, "Child views must be of type MaterialButton.");
        return;
    L6:
        super.addView(r2, r3, r4);
        MaterialButton r22 = (MaterialButton) r2;
        setupButtonChild(r22);
        checkInternal(r22.getId(), r22.isChecked());
        ViewCompat.setAccessibilityDelegate(r22, new AnonymousClass1(this));
    }

    public void check(int r2) {
        checkInternal(r2, true);
    }

    public void clearChecked() {
        updateCheckedIds(new HashSet());
    }

    public void clearOnButtonCheckedListeners() {
        this.onButtonCheckedListeners.clear();
    }

    public int getCheckedButtonId() {
        if (this.singleSelection == true) goto L5;
        return -1;
    L5:
        if (this.checkedIds.isEmpty() == false) goto L7;
        return -1;
    L7:
        return this.checkedIds.iterator().next().intValue();
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L9;
        int r2 = getChildButton(r1).getId();
        if (this.checkedIds.contains(Integer.valueOf(r2)) == false) goto L8;
        r0.add(Integer.valueOf(r2));
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        return r0;
    }

    @Override // com.google.android.material.button.MaterialButtonGroup
    public boolean isOverflowMenuSupported() {
        return false;
    }

    public boolean isSelectionRequired() {
        return this.selectionRequired;
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void onButtonCheckedStateChanged(MaterialButton r2, boolean r3) {
        if (this.skipCheckedStateTracker == false) goto L5;
        return;
    L5:
        checkInternal(r2.getId(), r3);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int r0 = this.defaultCheckId;
        if (r0 == (-1)) goto L6;
        updateCheckedIds(Collections.singleton(Integer.valueOf(r0)));
        return;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfo(r4);
        AccessibilityNodeInfoCompat r42 = AccessibilityNodeInfoCompat.wrap(r4);
        int r0 = getVisibleButtonCount();
        if (isSingleSelection() == false) goto L5;
        int r3 = 1;
    L6:
        r42.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, r0, false, r3));
        return;
    L5:
        r3 = 2;
        goto L6
    }

    public void removeOnButtonCheckedListener(OnButtonCheckedListener r1) {
        this.onButtonCheckedListeners.remove(r1);
    }

    public void setSelectionRequired(boolean r1) {
        this.selectionRequired = r1;
    }

    public void setSingleSelection(int r2) {
        setSingleSelection(getResources().getBoolean(r2));
    }

    public void setSingleSelection(boolean r2) {
        if (this.singleSelection == r2) goto L5;
        this.singleSelection = r2;
        clearChecked();
    L5:
        updateChildrenA11yClassName();
    }

    public void uncheck(int r2) {
        checkInternal(r2, false);
    }
}
