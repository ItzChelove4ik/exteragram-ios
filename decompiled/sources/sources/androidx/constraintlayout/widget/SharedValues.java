package androidx.constraintlayout.widget;

import android.util.SparseIntArray;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes3.dex */
public class SharedValues {
    private SparseIntArray mValues;
    private HashMap<Integer, HashSet<WeakReference<Object>>> mValuesListeners;

    public SharedValues() {
        this.mValues = new SparseIntArray();
        this.mValuesListeners = new HashMap();
    }
}
