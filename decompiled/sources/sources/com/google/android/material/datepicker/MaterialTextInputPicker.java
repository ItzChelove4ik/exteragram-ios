package com.google.android.material.datepicker;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class MaterialTextInputPicker<S> extends PickerFragment<S> {
    private static final String CALENDAR_CONSTRAINTS_KEY = "CALENDAR_CONSTRAINTS_KEY";
    private static final String DATE_SELECTOR_KEY = "DATE_SELECTOR_KEY";
    private static final String THEME_RES_ID_KEY = "THEME_RES_ID_KEY";
    private CalendarConstraints calendarConstraints;
    private DateSelector<S> dateSelector;
    private int themeResId;

    /* renamed from: com.google.android.material.datepicker.MaterialTextInputPicker$1, reason: invalid class name */
    public class AnonymousClass1 extends OnSelectionChangedListener<S> {
        final /* synthetic */ MaterialTextInputPicker this$0;

        public AnonymousClass1(MaterialTextInputPicker r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onIncompleteSelectionChanged() {
            Iterator<OnSelectionChangedListener<S>> r1 = this.this$0.onSelectionChangedListeners.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().onIncompleteSelectionChanged();
            goto L4
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onSelectionChanged(S r2) {
            Iterator<OnSelectionChangedListener<S>> r1 = this.this$0.onSelectionChangedListeners.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().onSelectionChanged(r2);
            goto L4
        }
    }

    public MaterialTextInputPicker() {
    }

    public static <T> MaterialTextInputPicker<T> newInstance(DateSelector<T> r3, int r4, CalendarConstraints r5) {
        MaterialTextInputPicker<T> r0 = new MaterialTextInputPicker();
        Bundle r1 = new Bundle();
        r1.putInt(THEME_RES_ID_KEY, r4);
        r1.putParcelable(DATE_SELECTOR_KEY, r3);
        r1.putParcelable(CALENDAR_CONSTRAINTS_KEY, r5);
        r0.setArguments(r1);
        return r0;
    }

    @Override // com.google.android.material.datepicker.PickerFragment
    public DateSelector<S> getDateSelector() {
        DateSelector<S> r0 = this.dateSelector;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("dateSelector should not be null. Use MaterialTextInputPicker#newInstance() to create this fragment with a DateSelector, and call this method after the fragment has been created.");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle r2) {
        super.onCreate(r2);
        if (r2 != null) goto L5;
        r2 = getArguments();
    L5:
        this.themeResId = r2.getInt(THEME_RES_ID_KEY);
        this.dateSelector = (DateSelector) r2.getParcelable(DATE_SELECTOR_KEY);
        this.calendarConstraints = (CalendarConstraints) r2.getParcelable(CALENDAR_CONSTRAINTS_KEY);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater r10, ViewGroup r11, Bundle r12) {
        LayoutInflater r4 = r10.cloneInContext(new ContextThemeWrapper(getContext(), this.themeResId));
        return this.dateSelector.onCreateTextInputView(r4, r11, r12, this.calendarConstraints, new AnonymousClass1(this));
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle r3) {
        super.onSaveInstanceState(r3);
        r3.putInt(THEME_RES_ID_KEY, this.themeResId);
        r3.putParcelable(DATE_SELECTOR_KEY, this.dateSelector);
        r3.putParcelable(CALENDAR_CONSTRAINTS_KEY, this.calendarConstraints);
    }
}
