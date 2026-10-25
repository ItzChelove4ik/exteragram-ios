package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R$attr;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.widget.ListViewAutoScrollHelper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
class DropDownListView extends ListView {
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private int mMotionPosition;
    ResolveHoverRunnable mResolveHoverRunnable;
    private ListViewAutoScrollHelper mScrollHelper;
    private int mSelectionBottomPadding;
    private int mSelectionLeftPadding;
    private int mSelectionRightPadding;
    private int mSelectionTopPadding;
    private GateKeeperDrawable mSelector;
    private final Rect mSelectorRect;

    public static class Api21Impl {
        public static void drawableHotspotChanged(View r0, float r1, float r2) {
            r0.drawableHotspotChanged(r1, r2);
        }
    }

    public static class Api30Impl {
        private static boolean sHasMethods;
        private static Method sPositionSelector;
        private static Method sSetNextSelectedPositionInt;
        private static Method sSetSelectedPositionInt;

        static {
            Class r3 = Integer.TYPE;     // Catch: NoSuchMethodException -> L5
            Class r5 = Boolean.TYPE;     // Catch: NoSuchMethodException -> L5
            Class r6 = Float.TYPE;     // Catch: NoSuchMethodException -> L5
            Method r1 = AbsListView.class.getDeclaredMethod("positionSelector", new Class[]{r3, View.class, r5, r6, r6});     // Catch: NoSuchMethodException -> L5
            sPositionSelector = r1;     // Catch: NoSuchMethodException -> L5
            r1.setAccessible(true);     // Catch: NoSuchMethodException -> L5
            Method r12 = AdapterView.class.getDeclaredMethod("setSelectedPositionInt", new Class[]{r3});     // Catch: NoSuchMethodException -> L5
            sSetSelectedPositionInt = r12;     // Catch: NoSuchMethodException -> L5
            r12.setAccessible(true);     // Catch: NoSuchMethodException -> L5
            Method r0 = AdapterView.class.getDeclaredMethod("setNextSelectedPositionInt", new Class[]{r3});     // Catch: NoSuchMethodException -> L5
            sSetNextSelectedPositionInt = r0;     // Catch: NoSuchMethodException -> L5
            r0.setAccessible(true);     // Catch: NoSuchMethodException -> L5
            sHasMethods = true;     // Catch: NoSuchMethodException -> L5
            return;
        L5:
            e = move-exception;
            e.printStackTrace();
        }

        public static boolean canPositionSelectorForHoveredItem() {
            return sHasMethods;
        }

        @SuppressLint({"BanUncheckedReflection"})
        public static void positionSelectorForHoveredItem(DropDownListView r4, int r5, View r6) {
            sPositionSelector.invoke(r4, new Object[]{Integer.valueOf(r5), r6, Boolean.FALSE, -1, -1});     // Catch: InvocationTargetException -> L5 IllegalAccessException -> L7
            sSetSelectedPositionInt.invoke(r4, new Object[]{Integer.valueOf(r5)});     // Catch: InvocationTargetException -> L5 IllegalAccessException -> L7
            sSetNextSelectedPositionInt.invoke(r4, new Object[]{Integer.valueOf(r5)});     // Catch: InvocationTargetException -> L5 IllegalAccessException -> L7
            return;
        L7:
            e = move-exception;
            e.printStackTrace();
            return;
        L5:
            e = move-exception;
            e.printStackTrace();
        }
    }

    public static class Api33Impl {
        public static boolean isSelectedChildViewEnabled(AbsListView r0) {
            return r0.isSelectedChildViewEnabled();
        }

        public static void setSelectedChildViewEnabled(AbsListView r0, boolean r1) {
            r0.setSelectedChildViewEnabled(r1);
        }
    }

    public static class GateKeeperDrawable extends DrawableWrapperCompat {
        private boolean mEnabled;

        public GateKeeperDrawable(Drawable r1) {
            super(r1);
            this.mEnabled = true;
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public void draw(Canvas r2) {
            if (this.mEnabled == false) goto L6;
            super.draw(r2);
            return;
        }

        public void setEnabled(boolean r1) {
            this.mEnabled = r1;
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public void setHotspot(float r2, float r3) {
            if (this.mEnabled == false) goto L6;
            super.setHotspot(r2, r3);
            return;
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public void setHotspotBounds(int r2, int r3, int r4, int r5) {
            if (this.mEnabled == false) goto L6;
            super.setHotspotBounds(r2, r3, r4, r5);
            return;
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public boolean setState(int[] r2) {
            if (this.mEnabled == true) goto L5;
            return false;
        L5:
            return super.setState(r2);
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public boolean setVisible(boolean r2, boolean r3) {
            if (this.mEnabled == true) goto L5;
            return false;
        L5:
            return super.setVisible(r2, r3);
        }
    }

    public static class PreApi33Impl {
        private static final Field sIsChildViewEnabled = null;

        static {
            Field r0 = null;
            r0 = AbsListView.class.getDeclaredField("mIsChildViewEnabled");     // Catch: NoSuchFieldException -> L5
            r0.setAccessible(true);     // Catch: NoSuchFieldException -> L5
        L7:
            sIsChildViewEnabled = r0;
            return;
        L5:
            e = move-exception;
            e.printStackTrace();
            goto L7
        }

        public static boolean isSelectedChildViewEnabled(AbsListView r1) {
            Field r0 = sIsChildViewEnabled;
            if (r0 == null) goto L12;
            return r0.getBoolean(r1);
        L6:
            e = move-exception;
            e.printStackTrace();
            return false;
        L12:
            return false;
        }

        public static void setSelectedChildViewEnabled(AbsListView r1, boolean r2) {
            Field r0 = sIsChildViewEnabled;
            if (r0 == null) goto L11;
            r0.set(r1, Boolean.valueOf(r2));     // Catch: IllegalAccessException -> L6
            return;
        L6:
            e = move-exception;
            e.printStackTrace();
            return;
        }
    }

    public class ResolveHoverRunnable implements Runnable {
        final /* synthetic */ DropDownListView this$0;

        public ResolveHoverRunnable(DropDownListView r1) {
            this.this$0 = r1;
        }

        public void cancel() {
            DropDownListView r0 = this.this$0;
            r0.mResolveHoverRunnable = null;
            r0.removeCallbacks(this);
        }

        public void post() {
            this.this$0.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            DropDownListView r1 = this.this$0;
            r1.mResolveHoverRunnable = null;
            r1.drawableStateChanged();
        }
    }

    public DropDownListView(Context r3, boolean r4) {
        super(r3, null, R$attr.dropDownListViewStyle);
        this.mSelectorRect = new Rect();
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mHijackFocus = r4;
        setCacheColorHint(0);
    }

    private void clearPressedItem() {
        this.mDrawsInPressedState = false;
        setPressed(false);
        drawableStateChanged();
        View r1 = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
        if (r1 == null) goto L5;
        r1.setPressed(false);
    L5:
        ViewPropertyAnimatorCompat r0 = this.mClickAnimation;
        if (r0 == null) goto L9;
        r0.cancel();
        this.mClickAnimation = null;
        return;
    }

    private void clickPressedItem(View r3, int r4) {
        performItemClick(r3, r4, getItemIdAtPosition(r4));
    }

    private void drawSelectorCompat(Canvas r2) {
        if (this.mSelectorRect.isEmpty() == true) goto L8;
        Drawable r0 = getSelector();
        if (r0 == null) goto L9;
        r0.setBounds(this.mSelectorRect);
        r0.draw(r2);
        return;
    L9:
        return;
    }

    private void positionSelectorCompat(int r6, View r7) {
        Rect r0 = this.mSelectorRect;
        r0.set(r7.getLeft(), r7.getTop(), r7.getRight(), r7.getBottom());
        r0.left -= this.mSelectionLeftPadding;
        r0.top -= this.mSelectionTopPadding;
        r0.right += this.mSelectionRightPadding;
        r0.bottom += this.mSelectionBottomPadding;
        boolean r02 = superIsSelectedChildViewEnabled();
        if (r7.isEnabled() == r02) goto L8;
        superSetSelectedChildViewEnabled(!r02);
        if (r6 == (-1)) goto L9;
        refreshDrawableState();
        return;
    L9:
        return;
    }

    private void positionSelectorLikeFocusCompat(int r5, View r6) {
        Drawable r0 = getSelector();
        boolean r1 = true;
        if (r0 != null) goto L5;
    L7:
        boolean r3 = false;
    L8:
        if (r3 == false) goto L10;
        r0.setVisible(false, false);
    L10:
        positionSelectorCompat(r5, r6);
        if (r3 == false) goto L18;
        Rect r52 = this.mSelectorRect;
        float r62 = r52.exactCenterX();
        float r53 = r52.exactCenterY();
        if (getVisibility() == 0) goto L16;
        r1 = false;
    L16:
        r0.setVisible(r1, false);
        DrawableCompat.setHotspot(r0, r62, r53);
        return;
    L18:
        return;
    L5:
        if (r5 == (-1)) goto L7;
        r3 = true;
        goto L8
    }

    private void positionSelectorLikeTouchCompat(int r1, View r2, float r3, float r4) {
        positionSelectorLikeFocusCompat(r1, r2);
        Drawable r0 = getSelector();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r1 == (-1)) goto L9;
        DrawableCompat.setHotspot(r0, r3, r4);
        return;
    }

    private void setPressedItem(View r5, int r6, float r7, float r8) {
        this.mDrawsInPressedState = true;
        Api21Impl.drawableHotspotChanged(this, r7, r8);
        if (isPressed() == true) goto L5;
        setPressed(true);
    L5:
        layoutChildren();
        int r1 = this.mMotionPosition;
        if (r1 == (-1)) goto L13;
        View r12 = getChildAt(r1 - getFirstVisiblePosition());
        if (r12 == null) goto L13;
        if (r12 == r5) goto L13;
        if (r12.isPressed() == false) goto L13;
        r12.setPressed(false);
    L13:
        this.mMotionPosition = r6;
        Api21Impl.drawableHotspotChanged(r5, r7 - r5.getLeft(), r8 - r5.getTop());
        if (r5.isPressed() == true) goto L16;
        r5.setPressed(true);
    L16:
        positionSelectorLikeTouchCompat(r6, r5, r7, r8);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    private void setSelectorEnabled(boolean r1) {
        GateKeeperDrawable r0 = this.mSelector;
        if (r0 == null) goto L6;
        r0.setEnabled(r1);
        return;
    }

    private boolean superIsSelectedChildViewEnabled() {
        if (Build.VERSION.SDK_INT < 33) goto L7;
        return Api33Impl.isSelectedChildViewEnabled(this);
    L7:
        return PreApi33Impl.isSelectedChildViewEnabled(this);
    }

    private void superSetSelectedChildViewEnabled(boolean r3) {
        if (Build.VERSION.SDK_INT < 33) goto L6;
        Api33Impl.setSelectedChildViewEnabled(this, r3);
        return;
    L6:
        PreApi33Impl.setSelectedChildViewEnabled(this, r3);
    }

    private boolean touchModeDrawsInPressedStateCompat() {
        return this.mDrawsInPressedState;
    }

    private void updateSelectorStateCompat() {
        Drawable r0 = getSelector();
        if (r0 != null) goto L5;
        return;
    L5:
        if (touchModeDrawsInPressedStateCompat() == true) goto L7;
        return;
    L7:
        if (isPressed() == false) goto L12;
        r0.setState(getDrawableState());
        return;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        drawSelectorCompat(r1);
        super.dispatchDraw(r1);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.mResolveHoverRunnable == null) goto L5;
        return;
    L5:
        super.drawableStateChanged();
        setSelectorEnabled(true);
        updateSelectorStateCompat();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        if (this.mHijackFocus == false) goto L5;
        return true;
    L5:
        if (super.hasFocus() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        if (this.mHijackFocus == false) goto L5;
        return true;
    L5:
        if (super.hasWindowFocus() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.View
    public boolean isFocused() {
        if (this.mHijackFocus == false) goto L5;
        return true;
    L5:
        if (super.isFocused() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        if (this.mHijackFocus == false) goto L7;
        if (this.mListSelectionHidden == false) goto L7;
        return true;
    L7:
        if (super.isInTouchMode() == true) goto L12;
        return false;
    L12:
        return true;
    }

    public int measureHeightOfChildrenCompat(int r11, int r12, int r13, int r14, int r15) {
        int r122 = getListPaddingTop();
        int r132 = getListPaddingBottom();
        int r0 = getDividerHeight();
        Drawable r1 = getDivider();
        ListAdapter r2 = getAdapter();
        if (r2 == null) goto L5;
        int r123 = r122 + r132;
        if (r0 <= 0) goto L10;
        if (r1 == null) goto L10;
    L11:
        int r16 = r2.getCount();
        int r4 = 0;
        int r5 = 0;
        int r7 = 0;
        View r6 = null;
    L12:
        if (r4 >= r16) goto L38;
        int r8 = r2.getItemViewType(r4);
        if (r8 == r5) goto L16;
        r6 = null;
        r5 = r8;
    L16:
        r6 = r2.getView(r4, r6, this);
        ViewGroup.LayoutParams r82 = r6.getLayoutParams();
        if (r82 != null) goto L19;
        r82 = generateDefaultLayoutParams();
        r6.setLayoutParams(r82);
    L19:
        int r83 = r82.height;
        if (r83 <= 0) goto L22;
        int r84 = View.MeasureSpec.makeMeasureSpec(r83, TLObject.FLAG_30);
    L23:
        r6.measure(r11, r84);
        r6.forceLayout();
        if (r4 <= 0) goto L26;
        r123 = r123 + r0;
    L26:
        r123 = r123 + r6.getMeasuredHeight();
        if (r123 >= r14) goto L28;
        if (r15 < 0) goto L37;
        if (r4 < r15) goto L37;
        r7 = r123;
    L37:
        r4 = r4 + 1;
        goto L12
    L28:
        if (r15 < 0) goto L33;
        if (r4 <= r15) goto L33;
        if (r7 <= 0) goto L33;
        if (r123 == r14) goto L33;
        return r7;
    L33:
        return r14;
    L22:
        r84 = View.MeasureSpec.makeMeasureSpec(0, 0);
        goto L23
    L38:
        return r123;
    L10:
        r0 = 0;
        goto L11
    L5:
        return r122 + r132;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.mResolveHoverRunnable = null;
        super.onDetachedFromWindow();
    }

    public boolean onForwardedEvent(MotionEvent r8, int r9) {
        int r0 = r8.getActionMasked();
        if (r0 != 1) goto L5;
        boolean r3 = false;
    L12:
        int r92 = r8.findPointerIndex(r9);
        if (r92 < 0) goto L9;
        int r4 = (int) r8.getX(r92);
        int r93 = (int) r8.getY(r92);
        int r5 = pointToPosition(r4, r93);
        if (r5 != (-1)) goto L18;
        boolean r94 = true;
    L21:
        if (r3 == false) goto L23;
        if (r94 == true) goto L23;
    L24:
        ListViewAutoScrollHelper r95 = this.mScrollHelper;
        if (r3 == false) goto L30;
        if (r95 != null) goto L28;
        this.mScrollHelper = new ListViewAutoScrollHelper(this);
    L28:
        this.mScrollHelper.setEnabled(true);
        this.mScrollHelper.onTouch(this, r8);
        return r3;
    L30:
        if (r95 == null) goto L32;
        r95.setEnabled(false);
    L32:
        return r3;
    L23:
        clearPressedItem();
        goto L24
    L18:
        View r32 = getChildAt(r5 - getFirstVisiblePosition());
        setPressedItem(r32, r5, r4, r93);
        if (r0 == 1) goto L20;
    L8:
        r3 = true;
        r94 = false;
        goto L21
    L20:
        clickPressedItem(r32, r5);
    L9:
        r94 = false;
        r3 = false;
        goto L21
    L5:
        if (r0 != 2) goto L7;
        r3 = true;
        goto L12
    L7:
        if (r0 == 3) goto L9;
        goto L8
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent r6) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 26) goto L5;
        int r1 = r6.getActionMasked();
        if (r1 == 10) goto L9;
    L11:
        boolean r2 = super.onHoverEvent(r6);
        if (r1 != 9) goto L14;
    L18:
        int r62 = pointToPosition((int) r6.getX(), (int) r6.getY());
        if (r62 != (-1)) goto L21;
    L31:
        return r2;
    L21:
        if (r62 == getSelectedItemPosition()) goto L31;
        View r12 = getChildAt(r62 - getFirstVisiblePosition());
        if (r12.isEnabled() == false) goto L30;
        requestFocus();
        if (r0 >= 30) goto L27;
    L29:
        setSelectionFromTop(r62, r12.getTop() - getTop());
        goto L30
    L27:
        if (Api30Impl.canPositionSelectorForHoveredItem() == false) goto L29;
        Api30Impl.positionSelectorForHoveredItem(this, r62, r12);
    L30:
        updateSelectorStateCompat();
        goto L31
    L14:
        if (r1 == 7) goto L18;
        setSelection(-1);
        return r2;
    L9:
        if (this.mResolveHoverRunnable != null) goto L11;
        ResolveHoverRunnable r22 = new ResolveHoverRunnable(this);
        this.mResolveHoverRunnable = r22;
        r22.post();
        goto L11
    L5:
        return super.onHoverEvent(r6);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (r3.getAction() != 0) goto L6;
        this.mMotionPosition = pointToPosition((int) r3.getX(), (int) r3.getY());
    L6:
        ResolveHoverRunnable r0 = this.mResolveHoverRunnable;
        if (r0 == null) goto L10;
        r0.cancel();
    L10:
        return super.onTouchEvent(r3);
    }

    public void setListSelectionHidden(boolean r1) {
        this.mListSelectionHidden = r1;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable r2) {
        if (r2 == null) goto L4;
        GateKeeperDrawable r0 = new GateKeeperDrawable(r2);
    L5:
        this.mSelector = r0;
        super.setSelector(r0);
        Rect r02 = new Rect();
        if (r2 == null) goto L8;
        r2.getPadding(r02);
    L8:
        this.mSelectionLeftPadding = r02.left;
        this.mSelectionTopPadding = r02.top;
        this.mSelectionRightPadding = r02.right;
        this.mSelectionBottomPadding = r02.bottom;
        return;
    L4:
        r0 = null;
        goto L5
    }
}
