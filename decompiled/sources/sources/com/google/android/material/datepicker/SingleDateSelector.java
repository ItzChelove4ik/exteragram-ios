package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.util.Pair;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes5.dex */
public class SingleDateSelector implements DateSelector<Long> {
    public static final Parcelable.Creator<SingleDateSelector> CREATOR = null;
    private CharSequence error;
    private Long selectedItem;
    private SimpleDateFormat textInputFormat;

    /* renamed from: com.google.android.material.datepicker.SingleDateSelector$1, reason: invalid class name */
    public class AnonymousClass1 extends DateFormatTextWatcher {
        final /* synthetic */ SingleDateSelector this$0;
        final /* synthetic */ TextInputLayout val$dateTextInput;
        final /* synthetic */ OnSelectionChangedListener val$listener;

        public AnonymousClass1(SingleDateSelector r1, String r2, DateFormat r3, TextInputLayout r4, CalendarConstraints r5, OnSelectionChangedListener r6, TextInputLayout r7) {
            this.this$0 = r1;
            this.val$listener = r6;
            this.val$dateTextInput = r7;
            super(r2, r3, r4, r5);
        }

        @Override // com.google.android.material.datepicker.DateFormatTextWatcher
        public void onInvalidDate() {
            SingleDateSelector.access$102(this.this$0, this.val$dateTextInput.getError());
            this.val$listener.onIncompleteSelectionChanged();
        }

        @Override // com.google.android.material.datepicker.DateFormatTextWatcher
        public void onValidDate(Long r4) {
            SingleDateSelector r0 = this.this$0;
            if (r4 != null) goto L5;
            SingleDateSelector.access$000(r0);
        L6:
            SingleDateSelector.access$102(this.this$0, null);
            this.val$listener.onSelectionChanged(this.this$0.getSelection());
            return;
        L5:
            r0.select(r4.longValue());
            goto L6
        }
    }

    /* renamed from: com.google.android.material.datepicker.SingleDateSelector$2, reason: invalid class name */
    public class AnonymousClass2 implements Parcelable.Creator<SingleDateSelector> {
        public AnonymousClass2() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SingleDateSelector createFromParcel(Parcel r2) {
            SingleDateSelector r1 = new SingleDateSelector();
            SingleDateSelector.access$202(r1, (Long) r2.readValue(Long.class.getClassLoader()));
            return r1;
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ SingleDateSelector createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SingleDateSelector[] newArray(int r1) {
            return new SingleDateSelector[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ SingleDateSelector[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass2();
    }

    public SingleDateSelector() {
    }

    public static /* synthetic */ void access$000(SingleDateSelector r0) {
        r0.clearSelection();
    }

    public static /* synthetic */ CharSequence access$102(SingleDateSelector r0, CharSequence r1) {
        r0.error = r1;
        return r1;
    }

    public static /* synthetic */ Long access$202(SingleDateSelector r0, Long r1) {
        r0.selectedItem = r1;
        return r1;
    }

    private void clearSelection() {
        this.selectedItem = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultThemeResId(Context r2) {
        return MaterialAttributes.resolveOrThrow(r2, R.attr.materialCalendarTheme, MaterialDatePicker.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultTitleResId() {
        return R.string.mtrl_picker_date_header_title;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String getError() {
        if (TextUtils.isEmpty(this.error) == false) goto L7;
        return null;
    L7:
        return this.error.toString();
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> getSelectedDays() {
        ArrayList r0 = new ArrayList();
        Long r1 = this.selectedItem;
        if (r1 == null) goto L5;
        r0.add(r1);
    L5:
        return r0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Pair<Long, Long>> getSelectedRanges() {
        return new ArrayList();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.material.datepicker.DateSelector
    public Long getSelection() {
        return this.selectedItem;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public /* bridge */ /* synthetic */ Long getSelection() {
        return getSelection();
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String getSelectionContentDescription(Context r3) {
        Resources r32 = r3.getResources();
        Long r2 = this.selectedItem;
        if (r2 != null) goto L5;
        String r22 = r32.getString(R.string.mtrl_picker_announce_current_selection_none);
    L7:
        return r32.getString(R.string.mtrl_picker_announce_current_selection, new Object[]{r22});
    L5:
        r22 = DateStrings.getYearMonthDay(r2.longValue());
        goto L7
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String getSelectionDisplayString(Context r3) {
        Resources r32 = r3.getResources();
        Long r2 = this.selectedItem;
        if (r2 == null) goto L5;
        String r22 = DateStrings.getYearMonthDay(r2.longValue());
        return r32.getString(R.string.mtrl_picker_date_header_selected, new Object[]{r22});
    L5:
        return r32.getString(R.string.mtrl_picker_date_header_unselected);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean isSelectionComplete() {
        if (this.selectedItem == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View onCreateTextInputView(LayoutInflater r10, ViewGroup r11, Bundle r12, CalendarConstraints r13, OnSelectionChangedListener<Long> r14) {
        boolean r0 = false;
        View r102 = r10.inflate(R.layout.mtrl_picker_text_input_date, r11, false);
        TextInputLayout r5 = (TextInputLayout) r102.findViewById(R.id.mtrl_picker_text_input_date);
        EditText r112 = r5.getEditText();
        Integer r122 = MaterialColors.getColorOrNull(r102.getContext(), R.attr.colorOnSurfaceVariant);
        if (r122 == null) goto L6;
        r112.setHintTextColor(r122.intValue());
    L6:
        if (ManufacturerUtils.isDateInputKeyboardMissingSeparatorCharacters() == false) goto L8;
        r112.setInputType(17);
    L8:
        SimpleDateFormat r123 = this.textInputFormat;
        if (r123 == null) goto L11;
        r0 = true;
    L11:
        if (r0 == false) goto L13;
    L12:
        SimpleDateFormat r4 = r123;
        if (r0 == false) goto L17;
        String r124 = r4.toPattern();
    L16:
        String r3 = r124;
        r5.setPlaceholderText(UtcDates.getVerbatimTextInputHint(r3));
        Long r125 = this.selectedItem;
        if (r125 == null) goto L23;
        r112.setText(r4.format(r125));
        Editable r126 = r112.getText();
        if (r126 == null) goto L23;
        r112.setSelection(r126.length());
    L23:
        r112.addTextChangedListener(new AnonymousClass1(this, r3, r4, r5, r13, r14, r5));
        if (DateSelector.isTouchExplorationEnabled(r102.getContext()) == true) goto L26;
        DateSelector.showKeyboardWithAutoHideBehavior(new EditText[]{r112});
    L26:
        return r102;
    L17:
        r124 = UtcDates.getDefaultTextInputHint(r102.getResources(), r4);
        goto L16
    L13:
        r123 = UtcDates.getDefaultTextInputFormat();
        goto L12
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void select(long r1) {
        this.selectedItem = Long.valueOf(r1);
    }

    /* renamed from: setSelection, reason: avoid collision after fix types in other method */
    public void setSelection2(Long r3) {
        if (r3 != null) goto L4;
        Long r32 = null;
    L5:
        this.selectedItem = r32;
        return;
    L4:
        r32 = Long.valueOf(UtcDates.canonicalYearMonthDay(r3.longValue()));
        goto L5
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public /* bridge */ /* synthetic */ void setSelection(Long r1) {
        setSelection2(r1);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void setTextInputFormat(SimpleDateFormat r1) {
        if (r1 == null) goto L4;
        r1 = (SimpleDateFormat) UtcDates.getNormalizedFormat(r1);
    L4:
        this.textInputFormat = r1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeValue(this.selectedItem);
    }
}
