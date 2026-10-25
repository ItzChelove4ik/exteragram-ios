package androidx.media3.exoplayer.source;

import android.util.SparseArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;

/* loaded from: classes4.dex */
final class SpannedData<V> {
    private int memoizedReadIndex;
    private final Consumer<V> removeCallback;
    private final SparseArray<V> spans;

    public SpannedData(Consumer<V> r2) {
        this.spans = new SparseArray();
        this.removeCallback = r2;
        this.memoizedReadIndex = -1;
    }

    public void appendSpan(int r5, V r6) {
        boolean r2 = false;
        if (this.memoizedReadIndex != (-1)) goto L10;
        if (this.spans.size() != 0) goto L7;
        boolean r0 = true;
    L8:
        Assertions.checkState(r0);
        this.memoizedReadIndex = 0;
        goto L10
    L7:
        r0 = false;
    L10:
        if (this.spans.size() <= 0) goto L17;
        SparseArray<V> r02 = this.spans;
        int r03 = r02.keyAt(r02.size() - 1);
        if (r5 < r03) goto L14;
        r2 = true;
    L14:
        Assertions.checkArgument(r2);
        if (r03 != r5) goto L17;
        Consumer<V> r04 = this.removeCallback;
        SparseArray<V> r1 = this.spans;
        r04.accept(r1.valueAt(r1.size() - 1));
    L17:
        this.spans.append(r5, r6);
    }

    public void clear() {
        int r0 = 0;
    L4:
        if (r0 >= this.spans.size()) goto L6;
        this.removeCallback.accept(this.spans.valueAt(r0));
        r0 = r0 + 1;
        goto L4
    L6:
        this.memoizedReadIndex = -1;
        this.spans.clear();
    }

    public void discardFrom(int r4) {
        int r0 = this.spans.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (r4 >= this.spans.keyAt(r0)) goto L8;
        this.removeCallback.accept(this.spans.valueAt(r0));
        this.spans.removeAt(r0);
        r0 = r0 - 1;
    L8:
        if (this.spans.size() <= 0) goto L10;
        int r42 = Math.min(this.memoizedReadIndex, this.spans.size() - 1);
    L11:
        this.memoizedReadIndex = r42;
        return;
    L10:
        r42 = -1;
        goto L11
    }

    public void discardTo(int r5) {
        int r0 = 0;
    L4:
        if (r0 >= (this.spans.size() - 1)) goto L11;
        int r2 = r0 + 1;
        if (r5 < this.spans.keyAt(r2)) goto L16;
        this.removeCallback.accept(this.spans.valueAt(r0));
        this.spans.removeAt(r0);
        int r02 = this.memoizedReadIndex;
        if (r02 <= 0) goto L10;
        this.memoizedReadIndex = r02 - 1;
    L10:
        r0 = r2;
        goto L4
    L16:
        return;
    }

    public V get(int r3) {
        if (this.memoizedReadIndex != (-1)) goto L5;
        this.memoizedReadIndex = 0;
    L5:
        int r0 = this.memoizedReadIndex;
        if (r0 <= 0) goto L11;
        if (r3 >= this.spans.keyAt(r0)) goto L11;
        this.memoizedReadIndex--;
    L11:
        if (this.memoizedReadIndex >= (this.spans.size() - 1)) goto L16;
        if (r3 < this.spans.keyAt(this.memoizedReadIndex + 1)) goto L16;
        this.memoizedReadIndex++;
    L16:
        return this.spans.valueAt(this.memoizedReadIndex);
    }

    public V getEndValue() {
        return this.spans.valueAt(r1.size() - 1);
    }

    public boolean isEmpty() {
        if (this.spans.size() != 0) goto L6;
        return true;
    L6:
        return false;
    }
}
