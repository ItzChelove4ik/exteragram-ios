package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.widget.PopupMenu;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.StateListCornerSize;
import com.google.android.material.shape.StateListShapeAppearanceModel;
import com.google.android.material.shape.StateListSizeChange;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes5.dex */
public class MaterialButtonGroup extends LinearLayout {
    private static final int DEF_STYLE_RES = 0;
    private static final String LOG_TAG = "MButtonGroup";
    public static final Object OVERFLOW_BUTTON_TAG = null;
    public static final int OVERFLOW_MODE_MENU = 1;
    public static final int OVERFLOW_MODE_NONE = 0;
    public static final int OVERFLOW_MODE_WRAP = 2;
    private boolean buttonOverflowInitialized;
    private StateListSizeChange buttonSizeChange;
    private final Map<Button, MenuItem> buttonToMenuItemMapping;
    private Integer[] childOrder;
    private final Comparator<MaterialButton> childOrderComparator;
    private boolean childShapesDirty;
    private StateListShapeAppearanceModel groupStateListShapeAppearance;
    StateListCornerSize innerCornerSize;
    private final List<ShapeAppearance> originalChildShapeAppearanceModels;
    private MaterialButton overflowButton;
    private final List<Button> overflowButtonsList;
    private final int overflowMenuItemIconPadding;
    private int overflowMode;
    private PopupMenu popupMenu;
    private final Map<Integer, Button> popupMenuItemToButtonMapping;
    private final PressedStateTracker pressedStateTracker;
    private final List<Integer> rowButtonFirstIndices;
    private int spacing;
    private final List<Button> tempOverflowButtonsList;

    /* renamed from: com.google.android.material.button.MaterialButtonGroup$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public Drawable overflowIcon;
        public CharSequence overflowText;

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(int r1, int r2, float r3) {
            super(r1, r2, r3);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(int r1, int r2, float r3, Drawable r4, CharSequence r5) {
            super(r1, r2, r3);
            this.overflowIcon = r4;
            this.overflowText = r5;
        }

        public LayoutParams(Context r2, AttributeSet r3) {
            super(r2, r3);
            this.overflowIcon = null;
            this.overflowText = null;
            TypedArray r22 = r2.obtainStyledAttributes(r3, R.styleable.MaterialButtonGroup_Layout);
            this.overflowIcon = r22.getDrawable(R.styleable.MaterialButtonGroup_Layout_layout_overflowIcon);
            this.overflowText = r22.getText(R.styleable.MaterialButtonGroup_Layout_layout_overflowText);
            r22.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams r1) {
            super(r1);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(LinearLayout.LayoutParams r1) {
            super(r1);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(LayoutParams r2) {
            super(r2);
            this.overflowIcon = null;
            this.overflowText = null;
            this.overflowText = r2.overflowText;
            this.overflowIcon = r2.overflowIcon;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OverflowMode {
    }

    public static class OverflowUtils {
        private OverflowUtils() {
        }

        public static CharSequence getMenuItemText(View r1, CharSequence r2) {
            if (TextUtils.isEmpty(r2) == true) goto L6;
            return r2;
        L6:
            if ((r1 instanceof MaterialButton) == false) goto L12;
            MaterialButton r22 = (MaterialButton) r1;
            if (TextUtils.isEmpty(r22.getText()) == true) goto L12;
            return r22.getText();
        L12:
            return r1.getContentDescription();
        }
    }

    public class PressedStateTracker implements MaterialButton.OnPressedChangeListener {
        final /* synthetic */ MaterialButtonGroup this$0;

        private PressedStateTracker(MaterialButtonGroup r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ PressedStateTracker(MaterialButtonGroup r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.android.material.button.MaterialButton.OnPressedChangeListener
        public void onPressedChanged(MaterialButton r1, boolean r2) {
            this.this$0.invalidate();
        }
    }

    public static /* synthetic */ boolean $r8$lambda$PPvYzu5sUsE2QBY_Uh56lm9gCgE(Button r0, MenuItem r1) {
        r0.performClick();
        return true;
    }

    public static /* synthetic */ int $r8$lambda$Rax0YMRIbiIrB6RD8v2eDsNN8o4(MaterialButtonGroup r2, MaterialButton r3, MaterialButton r4) {
        r2.getClass();
        int r0 = Boolean.valueOf(r3.isChecked()).compareTo(Boolean.valueOf(r4.isChecked()));
        if (r0 == 0) goto L5;
        return r0;
    L5:
        int r02 = Boolean.valueOf(r3.isPressed()).compareTo(Boolean.valueOf(r4.isPressed()));
        if (r02 == 0) goto L9;
        return r02;
    L9:
        return Integer.compare(r2.indexOfChild(r3), r2.indexOfChild(r4));
    }

    /* renamed from: $r8$lambda$nSb5s-0Ox4JObG6P_T034TqSgIw, reason: not valid java name */
    public static /* synthetic */ void m1825$r8$lambda$nSb5s0Ox4JObG6P_T034TqSgIw(MaterialButtonGroup r0, View r1) {
        r0.updateOverflowMenuItemsState();
        r0.popupMenu.show();
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_MaterialButtonGroup;
        OVERFLOW_BUTTON_TAG = new Object();
    }

    public MaterialButtonGroup(Context r2) {
        this(r2, null);
    }

    public MaterialButtonGroup(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialButtonGroupStyle);
    }

    public MaterialButtonGroup(Context r8, AttributeSet r9, int r10) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r8, r9, r10, r4), r9, r10);
        this.overflowMode = 0;
        this.originalChildShapeAppearanceModels = new ArrayList();
        this.pressedStateTracker = new PressedStateTracker(this, null);
        this.childOrderComparator = new MaterialButtonGroup$$ExternalSyntheticLambda1(this);
        this.childShapesDirty = true;
        this.popupMenuItemToButtonMapping = new HashMap();
        this.buttonToMenuItemMapping = new HashMap();
        this.tempOverflowButtonsList = new ArrayList();
        this.overflowButtonsList = new ArrayList();
        this.rowButtonFirstIndices = new ArrayList();
        Context r0 = getContext();
        TypedArray r92 = ThemeEnforcement.obtainStyledAttributes(r0, r9, R.styleable.MaterialButtonGroup, r10, r4, new int[0]);
        if (r92.hasValue(R.styleable.MaterialButtonGroup_buttonSizeChange) == false) goto L6;
        this.buttonSizeChange = StateListSizeChange.create(r0, r92, R.styleable.MaterialButtonGroup_buttonSizeChange);
    L6:
        if (r92.hasValue(R.styleable.MaterialButtonGroup_shapeAppearance) == false) goto L11;
        StateListShapeAppearanceModel r102 = StateListShapeAppearanceModel.create(r0, r92, R.styleable.MaterialButtonGroup_shapeAppearance);
        this.groupStateListShapeAppearance = r102;
        if (r102 != null) goto L11;
        this.groupStateListShapeAppearance = new StateListShapeAppearanceModel.Builder(ShapeAppearanceModel.builder(r0, r92.getResourceId(R.styleable.MaterialButtonGroup_shapeAppearance, 0), r92.getResourceId(R.styleable.MaterialButtonGroup_shapeAppearanceOverlay, 0)).build()).build();
    L11:
        if (r92.hasValue(R.styleable.MaterialButtonGroup_innerCornerSize) == false) goto L13;
        this.innerCornerSize = StateListCornerSize.create(r0, r92, R.styleable.MaterialButtonGroup_innerCornerSize, new AbsoluteCornerSize(0.0f));
    L13:
        this.spacing = r92.getDimensionPixelSize(R.styleable.MaterialButtonGroup_android_spacing, 0);
        setChildrenDrawingOrderEnabled(true);
        setEnabled(r92.getBoolean(R.styleable.MaterialButtonGroup_android_enabled, true));
        setOverflowMode(r92.getInt(R.styleable.MaterialButtonGroup_overflowMode, 0));
        this.overflowMenuItemIconPadding = getResources().getDimensionPixelOffset(R.dimen.m3_btn_group_overflow_item_icon_horizontal_padding);
        if (isOverflowMenuSupported() == false) goto L16;
        initializeButtonOverflow(r0, r92);
    L16:
        r92.recycle();
    }

    private MenuItem addMenuItemForButton(Menu r9, Button r10) {
        if ((r10.getLayoutParams() instanceof LayoutParams) == true) goto L6;
        return null;
    L6:
        LayoutParams r0 = (LayoutParams) r10.getLayoutParams();
        CharSequence r1 = OverflowUtils.getMenuItemText(r10, r0.overflowText);
        Drawable r3 = r0.overflowIcon;
        MenuItem r92 = r9.add(r1);
        if (r3 == null) goto L9;
        int r4 = this.overflowMenuItemIconPadding;
        r92.setIcon(new InsetDrawable(r3, r4, 0, r4, 0));
    L9:
        r92.setOnMenuItemClickListener(new MaterialButtonGroup$$ExternalSyntheticLambda2(r10));
        return r92;
    }

    private void adjustChildMarginsAndUpdateLayout() {
        int r0 = getFirstVisibleChildIndex();
        if (r0 != (-1)) goto L5;
        return;
    L5:
        int r1 = r0 + 1;
    L7:
        if (r1 >= getChildCount()) goto L22;
        View r2 = getChildAt(r1);
        View r3 = getChildAt(r1 - 1);
        if ((r2 instanceof MaterialButton) == true) goto L11;
    L16:
        int r6 = 0;
    L17:
        LinearLayout.LayoutParams r32 = buildLayoutParams(r2);
        if (getOrientation() != 0) goto L20;
        r32.setMarginEnd(0);
        r32.setMarginStart(this.spacing - r6);
        r32.topMargin = 0;
    L21:
        r2.setLayoutParams(r32);
        r1 = r1 + 1;
        goto L7
    L20:
        r32.bottomMargin = 0;
        r32.topMargin = this.spacing - r6;
        r32.setMarginStart(0);
        goto L21
    L11:
        if ((r3 instanceof MaterialButton) == false) goto L16;
        MaterialButton r4 = (MaterialButton) r2;
        MaterialButton r33 = (MaterialButton) r3;
        if (this.spacing > 0) goto L15;
        r6 = Math.min(r4.getStrokeWidth(), r33.getStrokeWidth());
        r4.setShouldDrawSurfaceColorStroke(true);
        r33.setShouldDrawSurfaceColorStroke(true);
        goto L17
    L15:
        r4.setShouldDrawSurfaceColorStroke(false);
        r33.setShouldDrawSurfaceColorStroke(false);
        goto L16
    L22:
        resetChildMargins(r0);
    }

    private void adjustChildSizeChange() {
        int r0 = getFirstVisibleChildIndex();
        int r1 = getLastVisibleChildIndex();
        if (r0 != (-1)) goto L5;
        return;
    L5:
        if (this.buttonSizeChange != null) goto L8;
        return;
    L8:
        if (this.overflowMode != 2) goto L18;
        int r02 = 0;
    L11:
        if (r02 >= this.rowButtonFirstIndices.size()) goto L23;
        int r12 = this.rowButtonFirstIndices.get(r02).intValue();
        if (r02 != (this.rowButtonFirstIndices.size() - 1)) goto L16;
        int r2 = getChildCount();
    L17:
        adjustChildSizeChangeInRange(r12, r2 - 1);
        r02 = r02 + 1;
        goto L11
    L16:
        r2 = this.rowButtonFirstIndices.get(r02 + 1).intValue();
        goto L17
    L23:
        return;
    L18:
        adjustChildSizeChangeInRange(r0, r1);
    }

    private void adjustChildSizeChangeInRange(int r5, int r6) {
        if (r5 != r6) goto L5;
        getChildButton(r5).setWidthChangeDirection(MaterialButton.WidthChangeDirection.NONE);
        return;
    L5:
        int r0 = Integer.MAX_VALUE;
        int r1 = r5;
    L6:
        if (r1 > r6) goto L22;
        if (isChildVisible(r1) == false) goto L21;
        MaterialButton r2 = getChildButton(r1);
        if (r1 != r5) goto L13;
        MaterialButton.WidthChangeDirection r3 = MaterialButton.WidthChangeDirection.END;
    L16:
        r2.setWidthChangeDirection(r3);
        int r22 = getButtonAllowedWidthIncrease(r1);
        if (r1 == r5) goto L20;
        if (r1 == r6) goto L20;
        r22 = r22 / 2;
    L20:
        r0 = Math.min(r0, r22);
        goto L21
    L13:
        if (r1 != r6) goto L15;
        r3 = MaterialButton.WidthChangeDirection.START;
        goto L16
    L15:
        r3 = MaterialButton.WidthChangeDirection.BOTH;
    L21:
        r1 = r1 + 1;
    L22:
        if (r5 > r6) goto L28;
        if (isChildVisible(r5) == false) goto L27;
        MaterialButton r12 = getChildButton(r5);
        r12.setSizeChange(this.buttonSizeChange);
        r12.setWidthChangeMax(r0 * 2);
    L27:
        r5 = r5 + 1;
        goto L22
    }

    private int getButtonAllowedWidthIncrease(int r5) {
        int r1 = 0;
        if (isChildVisible(r5) == true) goto L5;
    L17:
        return 0;
    L5:
        if (this.buttonSizeChange == null) goto L17;
        int r0 = Math.max(0, this.buttonSizeChange.getMaxWidthChange(getChildButton(r5).getWidth()));
        MaterialButton r3 = getPrevVisibleChildButton(r5, true);
        if (r3 != null) goto L10;
        int r32 = 0;
    L11:
        MaterialButton r4 = getNextVisibleChildButton(r5, true);
        if (r4 == null) goto L16;
        r1 = r4.getAllowedWidthDecrease();
    L16:
        return Math.min(r0, r32 + r1);
    L10:
        r32 = r3.getAllowedWidthDecrease();
        goto L11
    }

    private int getFirstVisibleChildIndex() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (isChildVisible(r1) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    private int getLastVisibleChildIndex() {
        int r0 = getChildCount() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (isChildVisible(r0) == true) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        return r0;
    L8:
        return -1;
    }

    private MaterialButton getNextVisibleChildButton(int r2) {
        return getNextVisibleChildButton(r2, false);
    }

    private MaterialButton getNextVisibleChildButton(int r8, boolean r9) {
        int r0 = getChildCount();
        int r1 = r8 + 1;
    L4:
        if (r1 >= r0) goto L9;
        if (isChildVisible(r1) == true) goto L11;
        r1 = r1 + 1;
    L11:
        if (r9 == true) goto L13;
    L27:
        if (r1 != (-1)) goto L30;
        return null;
    L30:
        return getChildButton(r1);
    L13:
        if (this.rowButtonFirstIndices.isEmpty() == true) goto L27;
        int r92 = 0;
    L16:
        if (r92 >= this.rowButtonFirstIndices.size()) goto L27;
        int r4 = this.rowButtonFirstIndices.get(r92).intValue();
        if (r92 != (this.rowButtonFirstIndices.size() - 1)) goto L20;
        int r5 = r0 - 1;
    L21:
        if (r8 < r4) goto L26;
        if (r8 > r5) goto L26;
        if (r1 < r4) goto L25;
        if (r1 <= r5) goto L26;
    L25:
        return null;
    L26:
        r92 = r92 + 1;
        goto L16
    L20:
        r5 = this.rowButtonFirstIndices.get(r92 + 1).intValue() - 1;
        goto L21
    L9:
        r1 = -1;
        goto L11
    }

    private StateListShapeAppearanceModel.Builder getOriginalStateListShapeBuilder(boolean r2, boolean r3, int r4) {
        Object r0 = this.groupStateListShapeAppearance;
        if (r0 == null) goto L7;
        if (r2 == true) goto L9;
        if (r3 == false) goto L7;
    L9:
        if ((r0 instanceof StateListShapeAppearanceModel) == true) goto L13;
        return new StateListShapeAppearanceModel.Builder((ShapeAppearanceModel) this.originalChildShapeAppearanceModels.get(r4));
    L13:
        return ((StateListShapeAppearanceModel) r0).toBuilder();
    L7:
        r0 = (ShapeAppearance) this.originalChildShapeAppearanceModels.get(r4);
        goto L9
    }

    private MaterialButton getPrevVisibleChildButton(int r2) {
        return getPrevVisibleChildButton(r2, false);
    }

    private MaterialButton getPrevVisibleChildButton(int r8, boolean r9) {
        int r0 = getChildCount();
        int r1 = r8 - 1;
    L4:
        if (r1 < 0) goto L9;
        if (isChildVisible(r1) == true) goto L11;
        r1 = r1 - 1;
    L11:
        if (r9 == true) goto L13;
    L27:
        if (r1 != (-1)) goto L30;
        return null;
    L30:
        return getChildButton(r1);
    L13:
        if (this.rowButtonFirstIndices.isEmpty() == true) goto L27;
        int r92 = 0;
    L16:
        if (r92 >= this.rowButtonFirstIndices.size()) goto L27;
        int r4 = this.rowButtonFirstIndices.get(r92).intValue();
        if (r92 != (this.rowButtonFirstIndices.size() - 1)) goto L20;
        int r5 = r0;
    L21:
        if (r8 < r4) goto L26;
        if (r8 >= r5) goto L26;
        if (r1 < r4) goto L25;
        if (r1 < r5) goto L26;
    L25:
        return null;
    L26:
        r92 = r92 + 1;
        goto L16
    L20:
        r5 = this.rowButtonFirstIndices.get(r92 + 1).intValue();
        goto L21
    L9:
        r1 = -1;
        goto L11
    }

    private boolean isChildVisible(int r1) {
        if (getChildAt(r1).getVisibility() == 8) goto L6;
        return true;
    L6:
        return false;
    }

    private void maybeUpdateOverflowMenu(int r11, int r12) {
        if (this.buttonOverflowInitialized == true) goto L6;
        return;
    L6:
        if (this.overflowMode == 1) goto L10;
        this.overflowButton.setVisibility(8);
        return;
    L10:
        if (getOrientation() != 0) goto L12;
        boolean r0 = true;
    L13:
        this.tempOverflowButtonsList.clear();
        if (r0 == false) goto L16;
        int r4 = View.MeasureSpec.getSize(r11);
    L17:
        int r5 = measureAndGetChildButtonSize(r0, this.overflowButton, r11, r12);
        int r6 = 0;
        int r7 = 0;
    L19:
        if (r6 >= (getChildCount() - 1)) goto L30;
        MaterialButton r8 = getChildButton(r6);
        r7 = r7 + measureAndGetChildButtonSize(r0, r8, r11, r12);
        if ((r7 + r5) <= r4) goto L23;
        this.tempOverflowButtonsList.add(r8);
    L23:
        if (r7 > r4) goto L24;
        r6 = r6 + 1;
        goto L19
    L24:
        int r62 = r6 + 1;
    L26:
        if (r62 >= (getChildCount() - 1)) goto L28;
        this.tempOverflowButtonsList.add(getChildButton(r62));
        r62 = r62 + 1;
        goto L26
    L28:
        this.overflowButton.setVisibility(0);
    L31:
        maybeUpdateOverflowMenuItemsAndChildVisibility();
        return;
    L30:
        this.overflowButton.setVisibility(8);
        this.tempOverflowButtonsList.clear();
        goto L31
    L16:
        r4 = View.MeasureSpec.getSize(r12);
        goto L17
    L12:
        r0 = false;
        goto L13
    }

    private void maybeUpdateOverflowMenuItemsAndChildVisibility() {
        if (this.tempOverflowButtonsList.equals(this.overflowButtonsList) == false) goto L5;
        return;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= (getChildCount() - 1)) goto L12;
        MaterialButton r2 = getChildButton(r1);
        if (this.buttonToMenuItemMapping.containsKey(r2) == false) goto L11;
        r2.setVisibility(0);
    L11:
        r1 = r1 + 1;
        goto L7
    L12:
        this.overflowButtonsList.clear();
        this.overflowButtonsList.addAll(this.tempOverflowButtonsList);
        Menu r0 = this.popupMenu.getMenu();
        this.popupMenuItemToButtonMapping.clear();
        this.buttonToMenuItemMapping.clear();
        r0.clear();
        Iterator<Button> r12 = this.overflowButtonsList.iterator();
    L14:
        if (r12.hasNext() == false) goto L19;
        Button r22 = r12.next();
        MenuItem r3 = addMenuItemForButton(r0, r22);
        if (r3 == null) goto L14;
        this.popupMenuItemToButtonMapping.put(Integer.valueOf(r3.getItemId()), r22);
        this.buttonToMenuItemMapping.put(r22, r3);
        r22.setVisibility(8);
        goto L14
    L19:
        updateOverflowMenuItemsState();
    }

    private int maybeWrapButtons(int r18, int r19) {
        this.rowButtonFirstIndices.clear();
        int r1 = View.MeasureSpec.getSize(r18);
        ArrayList r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
    L4:
        if (r5 >= getChildCount()) goto L32;
        if (isChildVisible(r5) == false) goto L31;
        MaterialButton r9 = getChildButton(r5);
        measureChild(r9, r18, r19);
        int r12 = r9.getMeasuredWidth();
        int r13 = r9.getMeasuredHeight();
        if (r12 <= 0) goto L31;
        LinearLayout.LayoutParams r14 = buildLayoutParams(r9);
        int r15 = r6 + r12;
        if (r2.isEmpty() == false) goto L14;
        int r4 = 0;
    L16:
        if ((r15 + r4) > r1) goto L20;
        if (r2.isEmpty() == true) goto L20;
    L27:
        if (r6 != 0) goto L29;
        int r42 = 0;
    L30:
        r6 = r6 + (r12 + r42);
        r7 = Math.max(r7, r13);
        r2.add(Integer.valueOf(r5));
        r14.topMargin += r8;
        r9.setLayoutParams(r14);
        goto L31
    L29:
        r42 = this.spacing;
    L20:
        if (r2.isEmpty() == true) goto L23;
        r3.add(Integer.valueOf(r6));
    L23:
        if (this.rowButtonFirstIndices.isEmpty() == false) goto L25;
        int r43 = 0;
    L26:
        r8 = r8 + (r7 + r43);
        this.rowButtonFirstIndices.add(Integer.valueOf(r5));
        r14.setMarginStart(-r6);
        r2.clear();
        r6 = 0;
        r7 = 0;
        goto L27
    L25:
        r43 = this.spacing;
        goto L26
    L14:
        r4 = this.spacing;
    L31:
        r5 = r5 + 1;
        goto L4
    L32:
        r3.add(Integer.valueOf(r6));
        int r16 = ((Integer) Collections.max(r3)).intValue();
        int r44 = 0;
        int r162 = 0;
    L34:
        if (r44 >= this.rowButtonFirstIndices.size()) goto L44;
        int r22 = this.rowButtonFirstIndices.get(r44).intValue();
        int r52 = ((Integer) r3.get(r44)).intValue();
        MaterialButton r23 = getChildButton(r22);
        LinearLayout.LayoutParams r62 = buildLayoutParams(r23);
        int r92 = r62.gravity & 8388615;
        int r10 = Gravity.getAbsoluteGravity(r92, getLayoutDirection());
        int r53 = r16 - r52;
        if (r92 == 8388611) goto L42;
        if (r10 != 1) goto L41;
        r53 = r53 / 2;
    L41:
        r62.setMarginStart((r62.getMarginStart() + r53) - r162);
        r23.setLayoutParams(r62);
        r162 = r53;
    L42:
        r44 = r44 + 1;
        goto L34
    L44:
        return ((r8 + r7) + getPaddingTop()) + getPaddingBottom();
    }

    private int measureAndGetChildButtonSize(boolean r1, Button r2, int r3, int r4) {
        measureChild(r2, r3, r4);
        LinearLayout.LayoutParams r0 = (LinearLayout.LayoutParams) r2.getLayoutParams();
        if (r1 == false) goto L5;
        int r32 = r2.getMeasuredWidth();
    L6:
        if (r1 == false) goto L9;
        int r42 = r0.leftMargin;
        int r02 = r0.rightMargin;
    L8:
        int r43 = r42 + r02;
        if (r32 != 0) goto L16;
        if (r1 == false) goto L14;
        int r03 = r2.getMinimumWidth();
    L13:
        r32 = r03;
        goto L16
    L14:
        r03 = r2.getMinimumHeight();
    L16:
        return r32 + r43;
    L9:
        r42 = r0.topMargin;
        r02 = r0.bottomMargin;
        goto L8
    L5:
        r32 = r2.getMeasuredHeight();
        goto L6
    }

    private void recoverAllChildrenLayoutParams() {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L6;
        getChildButton(r0).recoverOriginalLayoutParams();
        r0 = r0 + 1;
        goto L4
    }

    private void resetChildMargins(int r3) {
        if (getChildCount() != 0) goto L5;
        return;
    L5:
        if (r3 == (-1)) goto L14;
        LinearLayout.LayoutParams r32 = buildLayoutParams(getChildButton(r3));
        if (getOrientation() != 1) goto L11;
        r32.topMargin = 0;
        r32.bottomMargin = 0;
        return;
    L11:
        r32.setMarginEnd(0);
        r32.setMarginStart(0);
        r32.leftMargin = 0;
        r32.rightMargin = 0;
        return;
    }

    private void setGeneratedIdIfNeeded(MaterialButton r2) {
        if (r2.getId() != (-1)) goto L6;
        r2.setId(View.generateViewId());
        return;
    }

    private void updateChildOrder() {
        TreeMap r0 = new TreeMap(this.childOrderComparator);
        int r1 = getChildCount();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        r0.put(getChildButton(r3), Integer.valueOf(r3));
        r3 = r3 + 1;
        goto L3
    L5:
        this.childOrder = (Integer[]) r0.values().toArray(new Integer[0]);
    }

    private void updateOverflowMenuItemsState() {
        Iterator<Map.Entry<Button, MenuItem>> r4 = this.buttonToMenuItemMapping.entrySet().iterator();
    L4:
        if (r4.hasNext() == false) goto L9;
        Map.Entry<Button, MenuItem> r0 = r4.next();
        Button r1 = r0.getKey();
        MenuItem r2 = r0.getValue();
        if ((r0.getKey() instanceof MaterialButton) == false) goto L8;
        MaterialButton r02 = (MaterialButton) r1;
        r2.setCheckable(r02.isCheckable());
        r2.setChecked(r02.isChecked());
    L8:
        r2.setEnabled(r1.isEnabled());
        goto L4
    }

    @Override // android.view.ViewGroup
    public void addView(View r3, int r4, ViewGroup.LayoutParams r5) {
        if ((r3 instanceof MaterialButton) == true) goto L6;
        Log.e(LOG_TAG, "Child views must be of type MaterialButton.");
        return;
    L6:
        recoverAllChildrenLayoutParams();
        this.childShapesDirty = true;
        int r0 = indexOfChild(this.overflowButton);
        if (r0 >= 0) goto L9;
    L11:
        super.addView(r3, r4, r5);
    L12:
        MaterialButton r32 = (MaterialButton) r3;
        setGeneratedIdIfNeeded(r32);
        r32.setOnPressedChangeListenerInternal(this.pressedStateTracker);
        this.originalChildShapeAppearanceModels.add(r32.getShapeAppearance());
        r32.setEnabled(isEnabled());
        return;
    L9:
        if (r4 != (-1)) goto L11;
        super.addView(r3, r0, r5);
        goto L12
    }

    public LinearLayout.LayoutParams buildLayoutParams(View r2) {
        ViewGroup.LayoutParams r1 = r2.getLayoutParams();
        if ((r1 instanceof LinearLayout.LayoutParams) == false) goto L7;
        return (LinearLayout.LayoutParams) r1;
    L7:
        return new LayoutParams(r1.width, r1.height);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        return r1 instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        updateChildOrder();
        super.dispatchDraw(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        if ((r1 instanceof LinearLayout.LayoutParams) == false) goto L7;
        return new LayoutParams((LinearLayout.LayoutParams) r1);
    L7:
        if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L11;
        return new LayoutParams((ViewGroup.MarginLayoutParams) r1);
    L11:
        return new LayoutParams(r1);
    }

    public StateListSizeChange getButtonSizeChange() {
        return this.buttonSizeChange;
    }

    public MaterialButton getChildButton(int r1) {
        return (MaterialButton) getChildAt(r1);
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int r1, int r2) {
        Integer[] r0 = this.childOrder;
        if (r0 != null) goto L5;
    L9:
        Log.w(LOG_TAG, "Child order wasn't updated");
        return r2;
    L5:
        if (r2 >= r0.length) goto L9;
        return r0[r2].intValue();
    }

    public ShapeAppearanceModel getChildOriginalShapeAppearanceModel(int r1) {
        return this.originalChildShapeAppearanceModels.get(r1).getDefaultShape();
    }

    public CornerSize getInnerCornerSize() {
        return this.innerCornerSize.getDefaultCornerSize();
    }

    public StateListCornerSize getInnerCornerSizeStateList() {
        return this.innerCornerSize;
    }

    public Drawable getOverflowButtonIcon() {
        return this.overflowButton.getIcon();
    }

    public int getOverflowMode() {
        return this.overflowMode;
    }

    public ShapeAppearanceModel getShapeAppearance() {
        StateListShapeAppearanceModel r1 = this.groupStateListShapeAppearance;
        if (r1 != null) goto L7;
        return null;
    L7:
        return r1.getDefaultShape(true);
    }

    public int getSpacing() {
        return this.spacing;
    }

    public StateListShapeAppearanceModel getStateListShapeAppearance() {
        return this.groupStateListShapeAppearance;
    }

    public void initializeButtonOverflow(Context r7, TypedArray r8) {
        Drawable r82 = r8.getDrawable(R.styleable.MaterialButtonGroup_overflowButtonIcon);
        MaterialButton r72 = (MaterialButton) LayoutInflater.from(r7).inflate(R.layout.m3_button_group_overflow_button, this, false);
        this.overflowButton = r72;
        r72.setTag(OVERFLOW_BUTTON_TAG);
        setOverflowButtonIcon(r82);
        if (this.overflowButton.getContentDescription() != null) goto L5;
        this.overflowButton.setContentDescription(getResources().getString(R.string.mtrl_button_overflow_icon_content_description));
    L5:
        this.overflowButton.setVisibility(8);
        int r5 = MaterialAttributes.resolveOrThrow(this, R.attr.materialButtonGroupPopupMenuStyle);
        PopupMenu r0 = new PopupMenu(getContext(), this.overflowButton, 17, 0, r5);
        this.popupMenu = r0;
        r0.setForceShowIcon(true);
        this.overflowButton.setOnClickListener(new MaterialButtonGroup$$ExternalSyntheticLambda0(this));
        addView(this.overflowButton);
        this.buttonOverflowInitialized = true;
    }

    public boolean isOverflowMenuSupported() {
        return true;
    }

    public void onButtonWidthChanged(MaterialButton r3, int r4) {
        int r32 = indexOfChild(r3);
        if (r32 < 0) goto L19;
        MaterialButton r1 = getPrevVisibleChildButton(r32, true);
        MaterialButton r2 = getNextVisibleChildButton(r32, true);
        if (r1 != null) goto L9;
        if (r2 != null) goto L9;
        return;
    L9:
        if (r1 != null) goto L11;
        r2.setDisplayedWidthDecrease(r4);
    L11:
        if (r2 != null) goto L13;
        r1.setDisplayedWidthDecrease(r4);
    L13:
        if (r1 == null) goto L17;
        if (r2 == null) goto L18;
        r1.setDisplayedWidthDecrease(r4 / 2);
        r2.setDisplayedWidthDecrease((r4 + 1) / 2);
        return;
    L18:
        return;
    L17:
        return;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (r1 == false) goto L6;
        recoverAllChildrenLayoutParams();
        adjustChildSizeChange();
        return;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        adjustChildMarginsAndUpdateLayout();
        if (this.overflowMode == 2) goto L5;
        int r0 = 0;
    L14:
        maybeUpdateOverflowMenu(r4, r5);
        updateChildShapes();
        super.onMeasure(r4, r5);
        if (this.overflowMode == 2) goto L17;
        return;
    L17:
        if (r0 == getMeasuredHeight()) goto L21;
        setMeasuredDimension(getMeasuredWidth(), r0);
        return;
    L21:
        return;
    L5:
        if (getOrientation() != 1) goto L7;
        f$$ExternalSyntheticBUOutline1.m("The wrap overflow mode is not compatible to the vertical orientation.");
        return;
    L7:
        if (View.MeasureSpec.getMode(r4) == Integer.MIN_VALUE) goto L9;
        r0 = maybeWrapButtons(r4, r5);
        goto L14
    L9:
        f$$ExternalSyntheticBUOutline1.m("The wrap overflow mode is not compatible with wrap_content layout width.");
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View r3) {
        super.onViewRemoved(r3);
        if ((r3 instanceof MaterialButton) == false) goto L5;
        ((MaterialButton) r3).setOnPressedChangeListenerInternal(null);
    L5:
        int r32 = indexOfChild(r3);
        if (r32 < 0) goto L8;
        this.originalChildShapeAppearanceModels.remove(r32);
    L8:
        this.childShapesDirty = true;
        updateChildShapes();
        recoverAllChildrenLayoutParams();
        adjustChildMarginsAndUpdateLayout();
    }

    public void setButtonSizeChange(StateListSizeChange r2) {
        if (this.buttonSizeChange == r2) goto L6;
        this.buttonSizeChange = r2;
        adjustChildSizeChange();
        requestLayout();
        invalidate();
        return;
    }

    @Override // android.view.View
    public void setEnabled(boolean r3) {
        super.setEnabled(r3);
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L6;
        getChildButton(r0).setEnabled(r3);
        r0 = r0 + 1;
        goto L4
    }

    public void setInnerCornerSize(CornerSize r1) {
        this.innerCornerSize = StateListCornerSize.create(r1);
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    public void setInnerCornerSizeStateList(StateListCornerSize r1) {
        this.innerCornerSize = r1;
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int r2) {
        if (getOrientation() == r2) goto L5;
        this.childShapesDirty = true;
    L5:
        super.setOrientation(r2);
    }

    public void setOverflowButtonIcon(Drawable r1) {
        this.overflowButton.setIcon(r1);
    }

    public void setOverflowButtonIconResource(int r1) {
        this.overflowButton.setIconResource(r1);
    }

    public void setOverflowMode(int r2) {
        if (this.overflowMode == r2) goto L6;
        this.overflowMode = r2;
        requestLayout();
        invalidate();
        return;
    }

    public void setShapeAppearance(ShapeAppearanceModel r2) {
        this.groupStateListShapeAppearance = new StateListShapeAppearanceModel.Builder(r2).build();
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    public void setSpacing(int r1) {
        this.spacing = r1;
        invalidate();
        requestLayout();
    }

    public void setStateListShapeAppearance(StateListShapeAppearanceModel r1) {
        this.groupStateListShapeAppearance = r1;
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    public void updateChildShapes() {
        if (this.innerCornerSize != null) goto L7;
        if (this.groupStateListShapeAppearance != null) goto L7;
        return;
    L7:
        if (this.childShapesDirty == false) goto L49;
        this.childShapesDirty = false;
        int r1 = getChildCount();
        int r2 = getFirstVisibleChildIndex();
        int r3 = getLastVisibleChildIndex();
        int r4 = 0;
    L10:
        if (r4 >= r1) goto L50;
        MaterialButton r5 = getChildButton(r4);
        if (r5.getVisibility() == 8) goto L44;
        if (r4 != r2) goto L17;
        boolean r7 = true;
    L18:
        if (r4 != r3) goto L20;
        boolean r8 = true;
    L21:
        StateListShapeAppearanceModel.Builder r9 = getOriginalStateListShapeBuilder(r7, r8, r4);
        if (getOrientation() != 0) goto L24;
        boolean r10 = true;
    L25:
        boolean r11 = ViewUtils.isLayoutRtl(this);
        if (r10 == false) goto L34;
        if (r7 == false) goto L29;
        int r72 = 5;
    L30:
        if (r8 == false) goto L32;
        r72 = r72 | 10;
    L32:
        if (r11 == false) goto L39;
        r72 = StateListShapeAppearanceModel.swapCornerPositionRtl(r72);
    L39:
        StateListShapeAppearanceModel r73 = r9.setCornerSizeOverride(this.innerCornerSize, ~r72).build();
        boolean r82 = r73.isStateful();
        ShapeAppearance r74 = r73;
        if (r82 == true) goto L43;
        r74 = r73.getDefaultShape(true);
    L43:
        r5.setShapeAppearance(r74);
        goto L44
    L29:
        r72 = 0;
        goto L30
    L34:
        if (r7 == false) goto L36;
        r72 = 3;
    L37:
        if (r8 == false) goto L39;
        r72 = r72 | 12;
        goto L39
    L36:
        r72 = 0;
        goto L37
    L24:
        r10 = false;
        goto L25
    L20:
        r8 = false;
        goto L21
    L17:
        r7 = false;
    L44:
        r4 = r4 + 1;
        goto L10
    L50:
        return;
    }
}
