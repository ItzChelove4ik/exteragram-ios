package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
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
public class RangeDateSelector implements DateSelector<Pair<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = null;
    private CharSequence error;
    private final String invalidRangeEndError;
    private String invalidRangeStartError;
    private Long proposedTextEnd;
    private Long proposedTextStart;
    private Long selectedEndItem;
    private Long selectedStartItem;
    private SimpleDateFormat textInputFormat;

    /* renamed from: com.google.android.material.datepicker.RangeDateSelector$1, reason: invalid class name */
    public class AnonymousClass1 extends DateFormatTextWatcher {
        final /* synthetic */ RangeDateSelector this$0;
        final /* synthetic */ TextInputLayout val$endTextInput;
        final /* synthetic */ OnSelectionChangedListener val$listener;
        final /* synthetic */ TextInputLayout val$startTextInput;

        public AnonymousClass1(RangeDateSelector r1, String r2, DateFormat r3, TextInputLayout r4, CalendarConstraints r5, TextInputLayout r6, TextInputLayout r7, OnSelectionChangedListener r8) {
            this.this$0 = r1;
            this.val$startTextInput = r6;
            this.val$endTextInput = r7;
            this.val$listener = r8;
            super(r2, r3, r4, r5);
        }

        @Override // com.google.android.material.datepicker.DateFormatTextWatcher
        public void onInvalidDate() {
            RangeDateSelector.access$002(this.this$0, null);
            RangeDateSelector.access$100(this.this$0, this.val$startTextInput, this.val$endTextInput, this.val$listener);
        }

        @Override // com.google.android.material.datepicker.DateFormatTextWatcher
        public void onValidDate(Long r3) {
            RangeDateSelector.access$002(this.this$0, r3);
            RangeDateSelector.access$100(this.this$0, this.val$startTextInput, this.val$endTextInput, this.val$listener);
        }
    }

    /* renamed from: com.google.android.material.datepicker.RangeDateSelector$2, reason: invalid class name */
    public class AnonymousClass2 extends DateFormatTextWatcher {
        final /* synthetic */ RangeDateSelector this$0;
        final /* synthetic */ TextInputLayout val$endTextInput;
        final /* synthetic */ OnSelectionChangedListener val$listener;
        final /* synthetic */ TextInputLayout val$startTextInput;

        public AnonymousClass2(RangeDateSelector r1, String r2, DateFormat r3, TextInputLayout r4, CalendarConstraints r5, TextInputLayout r6, TextInputLayout r7, OnSelectionChangedListener r8) {
            this.this$0 = r1;
            this.val$startTextInput = r6;
            this.val$endTextInput = r7;
            this.val$listener = r8;
            super(r2, r3, r4, r5);
        }

        @Override // com.google.android.material.datepicker.DateFormatTextWatcher
        public void onInvalidDate() {
            RangeDateSelector.access$202(this.this$0, null);
            RangeDateSelector.access$100(this.this$0, this.val$startTextInput, this.val$endTextInput, this.val$listener);
        }

        @Override // com.google.android.material.datepicker.DateFormatTextWatcher
        public void onValidDate(Long r3) {
            RangeDateSelector.access$202(this.this$0, r3);
            RangeDateSelector.access$100(this.this$0, this.val$startTextInput, this.val$endTextInput, this.val$listener);
        }
    }

    /* renamed from: com.google.android.material.datepicker.RangeDateSelector$3, reason: invalid class name */
    public class AnonymousClass3 implements Parcelable.Creator<RangeDateSelector> {
        public AnonymousClass3() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RangeDateSelector createFromParcel(Parcel r3) {
            RangeDateSelector r2 = new RangeDateSelector();
            RangeDateSelector.access$302(r2, (Long) r3.readValue(Long.class.getClassLoader()));
            RangeDateSelector.access$402(r2, (Long) r3.readValue(Long.class.getClassLoader()));
            return r2;
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ RangeDateSelector createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RangeDateSelector[] newArray(int r1) {
            return new RangeDateSelector[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ RangeDateSelector[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass3();
    }

    public RangeDateSelector() {
        this.invalidRangeEndError = " ";
        this.selectedStartItem = null;
        this.selectedEndItem = null;
        this.proposedTextStart = null;
        this.proposedTextEnd = null;
    }

    public static /* synthetic */ Long access$002(RangeDateSelector r0, Long r1) {
        r0.proposedTextStart = r1;
        return r1;
    }

    public static /* synthetic */ void access$100(RangeDateSelector r0, TextInputLayout r1, TextInputLayout r2, OnSelectionChangedListener r3) {
        r0.updateIfValidTextProposal(r1, r2, r3);
    }

    public static /* synthetic */ Long access$202(RangeDateSelector r0, Long r1) {
        r0.proposedTextEnd = r1;
        return r1;
    }

    public static /* synthetic */ Long access$302(RangeDateSelector r0, Long r1) {
        r0.selectedStartItem = r1;
        return r1;
    }

    public static /* synthetic */ Long access$402(RangeDateSelector r0, Long r1) {
        r0.selectedEndItem = r1;
        return r1;
    }

    private void clearInvalidRange(TextInputLayout r3, TextInputLayout r4) {
        if (r3.getError() == null) goto L8;
        if (this.invalidRangeStartError.contentEquals(r3.getError()) == false) goto L8;
        r3.setError(null);
    L8:
        if (r4.getError() != null) goto L10;
        return;
    L10:
        if (" ".contentEquals(r4.getError()) == false) goto L14;
        r4.setError(null);
        return;
    }

    private boolean isValidRange(long r1, long r3) {
        if (r1 > r3) goto L6;
        return true;
    L6:
        return false;
    }

    private void setInvalidRange(TextInputLayout r1, TextInputLayout r2) {
        r1.setError(this.invalidRangeStartError);
        r2.setError(" ");
    }

    private void updateError(TextInputLayout r2, TextInputLayout r3) {
        if (TextUtils.isEmpty(r2.getError()) == true) goto L7;
        this.error = r2.getError();
        return;
    L7:
        if (TextUtils.isEmpty(r3.getError()) == true) goto L10;
        this.error = r3.getError();
        return;
    L10:
        this.error = null;
    }

    private void updateIfValidTextProposal(TextInputLayout r5, TextInputLayout r6, OnSelectionChangedListener<Pair<Long, Long>> r7) {
        Long r0 = this.proposedTextStart;
        if (r0 != null) goto L5;
    L11:
        clearInvalidRange(r5, r6);
        r7.onIncompleteSelectionChanged();
    L12:
        updateError(r5, r6);
        return;
    L5:
        if (this.proposedTextEnd == null) goto L11;
        if (isValidRange(r0.longValue(), this.proposedTextEnd.longValue()) == false) goto L10;
        this.selectedStartItem = this.proposedTextStart;
        this.selectedEndItem = this.proposedTextEnd;
        r7.onSelectionChanged(getSelection());
        goto L12
    L10:
        setInvalidRange(r5, r6);
        r7.onIncompleteSelectionChanged();
        goto L12
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultThemeResId(Context r3) {
        Resources r2 = r3.getResources();
        DisplayMetrics r0 = r2.getDisplayMetrics();
        int r22 = r2.getDimensionPixelSize(R.dimen.mtrl_calendar_maximum_default_fullscreen_minor_axis);
        if (Math.min(r0.widthPixels, r0.heightPixels) <= r22) goto L5;
        int r23 = R.attr.materialCalendarTheme;
    L7:
        return MaterialAttributes.resolveOrThrow(r3, r23, MaterialDatePicker.class.getCanonicalName());
    L5:
        r23 = R.attr.materialCalendarFullscreenTheme;
        goto L7
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultTitleResId() {
        return R.string.mtrl_picker_range_header_title;
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
        Long r1 = this.selectedStartItem;
        if (r1 == null) goto L5;
        r0.add(r1);
    L5:
        Long r2 = this.selectedEndItem;
        if (r2 == null) goto L8;
        r0.add(r2);
    L8:
        return r0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Pair<Long, Long>> getSelectedRanges() {
        ArrayList r0 = new ArrayList();
        r0.add(new Pair(this.selectedStartItem, this.selectedEndItem));
        return r0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.material.datepicker.DateSelector
    public Pair<Long, Long> getSelection() {
        return new Pair(this.selectedStartItem, this.selectedEndItem);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public /* bridge */ /* synthetic */ Pair<Long, Long> getSelection() {
        return getSelection();
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String getSelectionContentDescription(Context r3) {
        Resources r32 = r3.getResources();
        Pair<String, String> r2 = DateStrings.getDateRangeString(this.selectedStartItem, this.selectedEndItem);
        String r0 = r2.first;
        if (r0 != null) goto L5;
        String r02 = r32.getString(R.string.mtrl_picker_announce_current_selection_none);
    L6:
        String r22 = r2.second;
        if (r22 != null) goto L9;
        String r23 = r32.getString(R.string.mtrl_picker_announce_current_selection_none);
    L11:
        return r32.getString(R.string.mtrl_picker_announce_current_range_selection, new Object[]{r02, r23});
    L9:
        r23 = r22;
        goto L11
    L5:
        r02 = r0;
        goto L6
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String getSelectionDisplayString(Context r4) {
        Resources r42 = r4.getResources();
        Long r0 = this.selectedStartItem;
        if (r0 == null) goto L5;
    L8:
        Long r3 = this.selectedEndItem;
        if (r3 == null) goto L11;
        if (r0 == null) goto L14;
        Pair<String, String> r32 = DateStrings.getDateRangeString(r0, r3);
        return r42.getString(R.string.mtrl_picker_range_header_selected, new Object[]{r32.first, r32.second});
    L14:
        return r42.getString(R.string.mtrl_picker_range_header_only_end_selected, new Object[]{DateStrings.getDateString(r3.longValue())});
    L11:
        return r42.getString(R.string.mtrl_picker_range_header_only_start_selected, new Object[]{DateStrings.getDateString(r0.longValue())});
    L5:
        if (this.selectedEndItem != null) goto L8;
        return r42.getString(R.string.mtrl_picker_range_header_unselected);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean isSelectionComplete() {
        Long r0 = this.selectedStartItem;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (this.selectedEndItem != null) goto L7;
        return false;
    L7:
        if (isValidRange(r0.longValue(), this.selectedEndItem.longValue()) == false) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View onCreateTextInputView(LayoutInflater r11, ViewGroup r12, Bundle r13, CalendarConstraints r14, OnSelectionChangedListener<Pair<Long, Long>> r15) {
        boolean r0 = false;
        View r112 = r11.inflate(R.layout.mtrl_picker_text_input_date_range, r12, false);
        TextInputLayout r5 = (TextInputLayout) r112.findViewById(R.id.mtrl_picker_text_input_range_start);
        TextInputLayout r8 = (TextInputLayout) r112.findViewById(R.id.mtrl_picker_text_input_range_end);
        EditText r122 = r5.getEditText();
        EditText r132 = r8.getEditText();
        Integer r1 = MaterialColors.getColorOrNull(r112.getContext(), R.attr.colorOnSurfaceVariant);
        if (r1 == null) goto L6;
        r122.setHintTextColor(r1.intValue());
        r132.setHintTextColor(r1.intValue());
    L6:
        if (ManufacturerUtils.isDateInputKeyboardMissingSeparatorCharacters() == false) goto L8;
        r122.setInputType(17);
        r132.setInputType(17);
    L8:
        this.invalidRangeStartError = r112.getResources().getString(R.string.mtrl_picker_invalid_range);
        SimpleDateFormat r16 = this.textInputFormat;
        if (r16 == null) goto L11;
        r0 = true;
    L11:
        if (r0 == false) goto L13;
    L12:
        SimpleDateFormat r4 = r16;
        Long r17 = this.selectedStartItem;
        if (r17 == null) goto L19;
        r122.setText(r4.format(r17));
        this.proposedTextStart = this.selectedStartItem;
        Editable r18 = r122.getText();
        if (r18 == null) goto L19;
        r122.setSelection(r18.length());
    L19:
        Long r19 = this.selectedEndItem;
        if (r19 == null) goto L22;
        r132.setText(r4.format(r19));
        this.proposedTextEnd = this.selectedEndItem;
    L22:
        if (r0 == false) goto L25;
        String r02 = r4.toPattern();
    L24:
        String r3 = r02;
        SpannableString r03 = UtcDates.getVerbatimTextInputHint(r3);
        r5.setPlaceholderText(r03);
        r8.setPlaceholderText(r03);
        r122.addTextChangedListener(new AnonymousClass1(this, r3, r4, r5, r14, r5, r8, r15));
        r132.addTextChangedListener(new AnonymousClass2(this, r3, r4, r8, r14, r5, r8, r15));
        if (DateSelector.isTouchExplorationEnabled(r112.getContext()) == true) goto L29;
        DateSelector.showKeyboardWithAutoHideBehavior(new EditText[]{r122, r132});
    L29:
        return r112;
    L25:
        r02 = UtcDates.getDefaultTextInputHint(r112.getResources(), r4);
        goto L24
    L13:
        r16 = UtcDates.getDefaultTextInputFormat();
        goto L12
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void select(long r3) {
        Long r0 = this.selectedStartItem;
        if (r0 != null) goto L7;
        this.selectedStartItem = Long.valueOf(r3);
        return;
    L7:
        if (this.selectedEndItem == null) goto L9;
    L12:
        this.selectedEndItem = null;
        this.selectedStartItem = Long.valueOf(r3);
        return;
    L9:
        if (isValidRange(r0.longValue(), r3) == false) goto L12;
        this.selectedEndItem = Long.valueOf(r3);
    }

    /* renamed from: setSelection, reason: avoid collision after fix types in other method */
    public void setSelection2(Pair<Long, Long> r5) {
        Long r0 = r5.first;
        if (r0 != null) goto L5;
    L7:
        Long r02 = r5.first;
        Long r1 = null;
        if (r02 != null) goto L10;
        Long r03 = null;
    L11:
        this.selectedStartItem = r03;
        Long r52 = r5.second;
        if (r52 == null) goto L15;
        r1 = Long.valueOf(UtcDates.canonicalYearMonthDay(r52.longValue()));
    L15:
        this.selectedEndItem = r1;
        return;
    L10:
        r03 = Long.valueOf(UtcDates.canonicalYearMonthDay(r02.longValue()));
        goto L11
    L5:
        if (r5.second == null) goto L7;
        Preconditions.checkArgument(isValidRange(r0.longValue(), r5.second.longValue()));
        goto L7
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public /* bridge */ /* synthetic */ void setSelection(Pair<Long, Long> r1) {
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
        r1.writeValue(this.selectedStartItem);
        r1.writeValue(this.selectedEndItem);
    }
}
