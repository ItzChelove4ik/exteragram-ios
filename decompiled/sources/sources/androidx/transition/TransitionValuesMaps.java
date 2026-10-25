package androidx.transition;

import android.util.SparseArray;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;

/* loaded from: classes4.dex */
class TransitionValuesMaps {
    final SparseArray<View> mIdValues;
    final LongSparseArray<View> mItemIdValues;
    final ArrayMap<String, View> mNameValues;
    final ArrayMap<View, TransitionValues> mViewValues;

    public TransitionValuesMaps() {
        this.mViewValues = new ArrayMap();
        this.mIdValues = new SparseArray();
        this.mItemIdValues = new LongSparseArray();
        this.mNameValues = new ArrayMap();
    }
}
