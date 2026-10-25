package org.telegram.ui.Components;

import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class RecyclerViewItemRangeSelector implements RecyclerView.OnItemTouchListener {
    private Runnable autoScrollRunnable;
    private int autoScrollVelocity;
    private int currentSelection;
    private RecyclerViewItemRangeSelectorDelegate delegate;
    private boolean dragSelectActive;
    private int hotspotBottomBoundEnd;
    private int hotspotBottomBoundStart;
    private int hotspotHeight;
    private int hotspotOffsetBottom;
    private int hotspotOffsetTop;
    private int hotspotTopBoundEnd;
    private int hotspotTopBoundStart;
    private boolean inBottomHotspot;
    private boolean inTopHotspot;
    private final SparseBooleanArray initialSelectedStates;
    private int initialSelection;
    private int lastDraggedIndex;
    private float lastTouchX;
    private float lastTouchY;
    private RecyclerView recyclerView;

    /* renamed from: org.telegram.ui.Components.RecyclerViewItemRangeSelector$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ RecyclerViewItemRangeSelector this$0;

        public AnonymousClass1(RecyclerViewItemRangeSelector r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (RecyclerViewItemRangeSelector.m12492$$Nest$fgetrecyclerView(this.this$0) == null) goto L14;
            boolean r0 = RecyclerViewItemRangeSelector.m12491$$Nest$fgetinTopHotspot(this.this$0);
            RecyclerViewItemRangeSelector r1 = this.this$0;
            if (r0 == false) goto L10;
            RecyclerViewItemRangeSelector.m12492$$Nest$fgetrecyclerView(r1).scrollBy(0, -RecyclerViewItemRangeSelector.m12489$$Nest$fgetautoScrollVelocity(this.this$0));
            RecyclerViewItemRangeSelector.m12493$$Nest$mapplySelectionAtTouchPosition(this.this$0);
            AndroidUtilities.runOnUIThread(this);
            return;
        L10:
            if (RecyclerViewItemRangeSelector.m12490$$Nest$fgetinBottomHotspot(r1) == false) goto L13;
            RecyclerViewItemRangeSelector.m12492$$Nest$fgetrecyclerView(this.this$0).scrollBy(0, RecyclerViewItemRangeSelector.m12489$$Nest$fgetautoScrollVelocity(this.this$0));
            RecyclerViewItemRangeSelector.m12493$$Nest$mapplySelectionAtTouchPosition(this.this$0);
            AndroidUtilities.runOnUIThread(this);
            return;
        L13:
            return;
        }
    }

    public interface RecyclerViewItemRangeSelectorDelegate {
        int getItemCount();

        boolean isIndexSelectable(int r1);

        boolean isSelected(int r1);

        void onStartStopSelection(boolean r1);

        void setSelected(View r1, int r2, boolean r3);
    }

    /* renamed from: -$$Nest$fgetautoScrollVelocity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12489$$Nest$fgetautoScrollVelocity(RecyclerViewItemRangeSelector r0) {
        return r0.autoScrollVelocity;
    }

    /* renamed from: -$$Nest$fgetinBottomHotspot, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12490$$Nest$fgetinBottomHotspot(RecyclerViewItemRangeSelector r0) {
        return r0.inBottomHotspot;
    }

    /* renamed from: -$$Nest$fgetinTopHotspot, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12491$$Nest$fgetinTopHotspot(RecyclerViewItemRangeSelector r0) {
        return r0.inTopHotspot;
    }

    /* renamed from: -$$Nest$fgetrecyclerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerView m12492$$Nest$fgetrecyclerView(RecyclerViewItemRangeSelector r0) {
        return r0.recyclerView;
    }

    /* renamed from: -$$Nest$mapplySelectionAtTouchPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12493$$Nest$mapplySelectionAtTouchPosition(RecyclerViewItemRangeSelector r0) {
        r0.applySelectionAtTouchPosition();
    }

    public RecyclerViewItemRangeSelector(RecyclerViewItemRangeSelectorDelegate r2) {
        this.lastDraggedIndex = -1;
        this.initialSelection = -1;
        this.currentSelection = -1;
        this.initialSelectedStates = new SparseBooleanArray();
        this.hotspotHeight = AndroidUtilities.dp(80.0f);
        this.autoScrollRunnable = new AnonymousClass1(this);
        this.delegate = r2;
    }

    private void applyRangeSelection(int r6) {
        int r0 = this.initialSelection;
        if (r0 == (-1)) goto L18;
        int r2 = this.currentSelection;
        if (r2 == (-1)) goto L19;
        int r02 = Math.min(r0, r2);
        int r1 = Math.max(this.initialSelection, this.currentSelection);
        int r22 = Math.min(this.initialSelection, r6);
        int r3 = Math.max(this.initialSelection, r6);
        if (r22 >= r02) goto L10;
        toggleRangeSelected(r22, r02 - 1);
    L10:
        if (r3 <= r1) goto L12;
        toggleRangeSelected(r1 + 1, r3);
    L12:
        if (r02 >= r22) goto L14;
        restoreRangeSelected(r02, r22 - 1);
    L14:
        if (r1 <= r3) goto L16;
        restoreRangeSelected(r3 + 1, r1);
    L16:
        this.currentSelection = r6;
        return;
    L19:
        return;
    }

    private void applySelectionAtTouchPosition() {
        RecyclerView r0 = this.recyclerView;
        if (r0 == null) goto L19;
        View r02 = r0.findChildViewUnder(this.lastTouchX, this.lastTouchY);
        if (r02 == null) goto L20;
        int r03 = this.recyclerView.getChildAdapterPosition(r02);
        if (r03 != (-1)) goto L11;
        return;
    L11:
        if (r03 != this.lastDraggedIndex) goto L13;
        return;
    L13:
        if (isSelectableIndex(r03) == false) goto L18;
        applyRangeSelection(r03);
        this.lastDraggedIndex = r03;
        return;
    L18:
        return;
    L20:
        return;
    }

    private boolean getInitialSelected(int r3) {
        if (this.initialSelectedStates.indexOfKey(r3) >= 0) goto L6;
        this.initialSelectedStates.put(r3, this.delegate.isSelected(r3));
    L6:
        return this.initialSelectedStates.get(r3);
    }

    private boolean isSelectableIndex(int r2) {
        if (r2 >= 0) goto L4;
        return false;
    L4:
        if (r2 < this.delegate.getItemCount()) goto L6;
        return false;
    L6:
        if (this.delegate.isIndexSelectable(r2) == false) goto L12;
        return true;
    L12:
        return false;
    }

    private void onDragSelectionStop() {
        if (this.dragSelectActive == true) goto L5;
        return;
    L5:
        this.dragSelectActive = false;
        this.lastDraggedIndex = -1;
        this.initialSelection = -1;
        this.currentSelection = -1;
        this.initialSelectedStates.clear();
        this.inTopHotspot = false;
        this.inBottomHotspot = false;
        AndroidUtilities.cancelRunOnUIThread(this.autoScrollRunnable);
        this.delegate.onStartStopSelection(false);
    }

    private void restoreRangeSelected(int r2, int r3) {
    L2:
        if (r2 > r3) goto L7;
        if (isSelectableIndex(r2) == false) goto L6;
        setIndexSelected(r2, getInitialSelected(r2));
    L6:
        r2 = r2 + 1;
        goto L2
    }

    private void setIndexSelected(int r3, boolean r4) {
        RecyclerView r0 = this.recyclerView;
        View r1 = null;
        if (r0 == null) goto L5;
        RecyclerView.ViewHolder r02 = r0.findViewHolderForAdapterPosition(r3);
    L6:
        if (r02 == null) goto L8;
        r1 = r02.itemView;
    L8:
        setIndexSelected(r1, r3, r4);
        return;
    L5:
        r02 = null;
        goto L6
    }

    private void setIndexSelected(View r2, int r3, boolean r4) {
        if (isSelectableIndex(r3) == true) goto L6;
        return;
    L6:
        if (this.delegate.isSelected(r3) != r4) goto L8;
        return;
    L8:
        this.delegate.setSelected(r2, r3, r4);
    }

    private void toggleRangeSelected(int r2, int r3) {
    L2:
        if (r2 > r3) goto L7;
        if (isSelectableIndex(r2) == false) goto L6;
        setIndexSelected(r2, !getInitialSelected(r2));
    L6:
        r2 = r2 + 1;
        goto L2
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView r5, MotionEvent r6) {
        boolean r1 = false;
        if (r5.getAdapter() != null) goto L5;
    L8:
        boolean r0 = true;
    L10:
        if (this.dragSelectActive == false) goto L13;
        if (r0 == true) goto L13;
        r1 = true;
    L13:
        if (r1 == false) goto L17;
        this.recyclerView = r5;
        int r02 = this.hotspotHeight;
        if (r02 <= (-1)) goto L17;
        int r3 = this.hotspotOffsetTop;
        this.hotspotTopBoundStart = r3;
        this.hotspotTopBoundEnd = r3 + r02;
        this.hotspotBottomBoundStart = (r5.getMeasuredHeight() - this.hotspotHeight) - this.hotspotOffsetBottom;
        this.hotspotBottomBoundEnd = r5.getMeasuredHeight() - this.hotspotOffsetBottom;
    L17:
        if (r1 == true) goto L19;
    L23:
        return r1;
    L19:
        if (r6.getAction() != 1) goto L21;
    L22:
        onDragSelectionStop();
        goto L23
    L21:
        if (r6.getAction() != 3) goto L23;
    L5:
        if (r5.getAdapter().getItemCount() == 0) goto L8;
        r0 = false;
        goto L10
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean r1) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView r6, MotionEvent r7) {
        this.lastTouchX = r7.getX();
        this.lastTouchY = r7.getY();
        View r0 = r6.findChildViewUnder(r7.getX(), r7.getY());
        if (r0 == null) goto L5;
        int r62 = r6.getChildAdapterPosition(r0);
    L6:
        float r02 = r7.getY();
        int r72 = r7.getAction();
        if (r72 != 1) goto L9;
        onDragSelectionStop();
        return;
    L9:
        if (r72 == 2) goto L16;
        if (r72 != 3) goto L50;
        onDragSelectionStop();
        return;
    L50:
        return;
    L16:
        if (this.hotspotHeight > (-1)) goto L18;
    L38:
        if (r62 != (-1)) goto L40;
        return;
    L40:
        if (this.lastDraggedIndex != r62) goto L42;
        return;
    L42:
        if (isSelectableIndex(r62) == false) goto L51;
        applyRangeSelection(r62);
        this.lastDraggedIndex = r62;
        return;
    L51:
        return;
    L18:
        if (r02 < this.hotspotTopBoundStart) goto L26;
        if (r02 > this.hotspotTopBoundEnd) goto L26;
        this.inBottomHotspot = false;
        if (this.inTopHotspot == true) goto L24;
        this.inTopHotspot = true;
        AndroidUtilities.cancelRunOnUIThread(this.autoScrollRunnable);
        AndroidUtilities.runOnUIThread(this.autoScrollRunnable);
    L24:
        int r73 = this.hotspotTopBoundEnd;
        this.autoScrollVelocity = ((int) ((r73 - r2) - (r02 - this.hotspotTopBoundStart))) / 2;
    L26:
        if (r02 < this.hotspotBottomBoundStart) goto L34;
        if (r02 > this.hotspotBottomBoundEnd) goto L34;
        this.inTopHotspot = false;
        if (this.inBottomHotspot == true) goto L32;
        this.inBottomHotspot = true;
        AndroidUtilities.cancelRunOnUIThread(this.autoScrollRunnable);
        AndroidUtilities.runOnUIThread(this.autoScrollRunnable);
    L32:
        this.autoScrollVelocity = ((int) ((r02 + this.hotspotBottomBoundEnd) - (this.hotspotBottomBoundStart + r7))) / 2;
    L34:
        if (this.inTopHotspot == false) goto L36;
    L37:
        AndroidUtilities.cancelRunOnUIThread(this.autoScrollRunnable);
        this.inTopHotspot = false;
        this.inBottomHotspot = false;
        goto L38
    L36:
        if (this.inBottomHotspot == false) goto L38;
    L5:
        r62 = -1;
        goto L6
    }

    public boolean startSelection(View r3, int r4) {
        if (this.dragSelectActive == false) goto L5;
        return false;
    L5:
        this.lastDraggedIndex = -1;
        this.initialSelection = -1;
        this.currentSelection = -1;
        this.initialSelectedStates.clear();
        AndroidUtilities.cancelRunOnUIThread(this.autoScrollRunnable);
        this.inTopHotspot = false;
        this.inBottomHotspot = false;
        if (isSelectableIndex(r4) == true) goto L9;
        this.dragSelectActive = false;
        return false;
    L9:
        this.delegate.onStartStopSelection(true);
        this.dragSelectActive = true;
        this.currentSelection = r4;
        this.initialSelection = r4;
        this.lastDraggedIndex = r4;
        setIndexSelected(r3, r4, !getInitialSelected(r4));
        return true;
    }
}
