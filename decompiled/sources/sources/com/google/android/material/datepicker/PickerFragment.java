package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

/* loaded from: classes5.dex */
abstract class PickerFragment<S> extends Fragment {
    protected final LinkedHashSet<OnSelectionChangedListener<S>> onSelectionChangedListeners;

    public PickerFragment() {
        this.onSelectionChangedListeners = new LinkedHashSet();
    }

    public boolean addOnSelectionChangedListener(OnSelectionChangedListener<S> r1) {
        return this.onSelectionChangedListeners.add(r1);
    }

    public void clearOnSelectionChangedListeners() {
        this.onSelectionChangedListeners.clear();
    }

    public abstract DateSelector<S> getDateSelector();

    public boolean removeOnSelectionChangedListener(OnSelectionChangedListener<S> r1) {
        return this.onSelectionChangedListeners.remove(r1);
    }
}
