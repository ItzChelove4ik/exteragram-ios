package androidx.recyclerview.widget;

/* loaded from: classes.dex */
public class BatchingListUpdateCallback implements ListUpdateCallback {
    int mLastEventCount;
    Object mLastEventPayload;
    int mLastEventPosition;
    int mLastEventType;
    final ListUpdateCallback mWrapped;

    public BatchingListUpdateCallback(ListUpdateCallback r2) {
        this.mLastEventType = 0;
        this.mLastEventPosition = -1;
        this.mLastEventCount = -1;
        this.mLastEventPayload = null;
        this.mWrapped = r2;
    }

    public void dispatchLastEvent() {
        int r0 = this.mLastEventType;
        if (r0 != 0) goto L6;
        return;
    L6:
        if (r0 != 1) goto L8;
        this.mWrapped.onInserted(this.mLastEventPosition, this.mLastEventCount);
    L15:
        this.mLastEventPayload = null;
        this.mLastEventType = 0;
        return;
    L8:
        if (r0 != 2) goto L10;
        this.mWrapped.onRemoved(this.mLastEventPosition, this.mLastEventCount);
        goto L15
    L10:
        if (r0 != 3) goto L15;
        this.mWrapped.onChanged(this.mLastEventPosition, this.mLastEventCount, this.mLastEventPayload);
        goto L15
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int r6, int r7, Object r8) {
        if (this.mLastEventType != 3) goto L12;
        int r0 = this.mLastEventPosition;
        int r2 = this.mLastEventCount;
        if (r6 > (r0 + r2)) goto L12;
        int r3 = r6 + r7;
        if (r3 < r0) goto L12;
        if (this.mLastEventPayload != r8) goto L12;
        this.mLastEventPosition = Math.min(r6, r0);
        this.mLastEventCount = Math.max(r2 + r0, r3) - this.mLastEventPosition;
        return;
    L12:
        dispatchLastEvent();
        this.mLastEventPosition = r6;
        this.mLastEventCount = r7;
        this.mLastEventPayload = r8;
        this.mLastEventType = 3;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int r5, int r6) {
        if (this.mLastEventType != 1) goto L10;
        int r0 = this.mLastEventPosition;
        if (r5 < r0) goto L10;
        int r2 = this.mLastEventCount;
        if (r5 > (r0 + r2)) goto L10;
        this.mLastEventCount = r2 + r6;
        this.mLastEventPosition = Math.min(r5, r0);
        return;
    L10:
        dispatchLastEvent();
        this.mLastEventPosition = r5;
        this.mLastEventCount = r6;
        this.mLastEventType = 1;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int r1, int r2) {
        dispatchLastEvent();
        this.mWrapped.onMoved(r1, r2);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int r4, int r5) {
        if (this.mLastEventType != 2) goto L10;
        int r0 = this.mLastEventPosition;
        if (r0 < r4) goto L10;
        if (r0 > (r4 + r5)) goto L10;
        this.mLastEventCount += r5;
        this.mLastEventPosition = r4;
        return;
    L10:
        dispatchLastEvent();
        this.mLastEventPosition = r4;
        this.mLastEventCount = r5;
        this.mLastEventType = 2;
    }
}
