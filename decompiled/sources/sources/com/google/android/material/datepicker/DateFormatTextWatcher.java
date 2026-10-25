package com.google.android.material.datepicker;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import kotlin.text.Typography;

/* loaded from: classes5.dex */
abstract class DateFormatTextWatcher extends TextWatcherAdapter {
    private final CalendarConstraints constraints;
    private final DateFormat dateFormat;
    private final String formatHint;
    private int lastLength;
    private final String outOfRange;
    private final Runnable setErrorCallback;
    private Runnable setRangeErrorCallback;
    private final TextInputLayout textInputLayout;

    /* renamed from: $r8$lambda$HHcrrijRZjKF-RrqXL2jy2Z-ghE, reason: not valid java name */
    public static /* synthetic */ void m1882$r8$lambda$HHcrrijRZjKFRrqXL2jy2ZghE(DateFormatTextWatcher r1, long r2) {
        r1.getClass();
        String r22 = DateStrings.getDateString(r2);
        r1.textInputLayout.setError(String.format(r1.outOfRange, new Object[]{r1.sanitizeDateString(r22)}));
        r1.onInvalidDate();
    }

    public static /* synthetic */ void $r8$lambda$WisZO76PIdPa6C8OvZJgtYqx1W8(DateFormatTextWatcher r7, String r8) {
        TextInputLayout r0 = r7.textInputLayout;
        DateFormat r1 = r7.dateFormat;
        Context r2 = r0.getContext();
        r0.setError(r2.getString(R.string.mtrl_picker_invalid_format) + "\n" + String.format(r2.getString(R.string.mtrl_picker_invalid_format_use), new Object[]{r7.sanitizeDateString(r8)}) + "\n" + String.format(r2.getString(R.string.mtrl_picker_invalid_format_example), new Object[]{r7.sanitizeDateString(r1.format(new Date(UtcDates.getTodayCalendar().getTimeInMillis())))}));
        r7.onInvalidDate();
    }

    public DateFormatTextWatcher(String r2, DateFormat r3, TextInputLayout r4, CalendarConstraints r5) {
        this.lastLength = 0;
        this.formatHint = r2;
        this.dateFormat = r3;
        this.textInputLayout = r4;
        this.constraints = r5;
        this.outOfRange = r4.getContext().getString(R.string.mtrl_picker_out_of_range);
        this.setErrorCallback = new DateFormatTextWatcher$$ExternalSyntheticLambda0(this, r2);
    }

    private Runnable createRangeErrorCallback(long r2) {
        return new DateFormatTextWatcher$$ExternalSyntheticLambda1(this, r2);
    }

    private String sanitizeDateString(String r2) {
        return r2.replace(' ', Typography.nbsp);
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void afterTextChanged(Editable r3) {
        if (Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage()) == false) goto L6;
        return;
    L6:
        if (r3.length() != 0) goto L8;
        return;
    L8:
        if (r3.length() < this.formatHint.length()) goto L10;
        return;
    L10:
        if (r3.length() < this.lastLength) goto L20;
        char r2 = this.formatHint.charAt(r3.length());
        if (Character.isLetterOrDigit(r2) == true) goto L18;
        r3.append(r2);
        return;
    L18:
        return;
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        this.lastLength = r1.length();
    }

    public void onInvalidDate() {
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        this.textInputLayout.removeCallbacks(this.setErrorCallback);
        this.textInputLayout.removeCallbacks(this.setRangeErrorCallback);
        this.textInputLayout.setError(null);
        onValidDate(null);
        if (TextUtils.isEmpty(r1) == false) goto L5;
        return;
    L5:
        if (r1.length() < this.formatHint.length()) goto L19;
        Date r12 = this.dateFormat.parse(r1.toString());     // Catch: ParseException -> L15
        this.textInputLayout.setError(null);     // Catch: ParseException -> L15
        long r22 = r12.getTime();     // Catch: ParseException -> L15
        if (this.constraints.getDateValidator().isValid(r22) == true) goto L10;
    L13:
        Runnable r13 = createRangeErrorCallback(r22);     // Catch: ParseException -> L15
        this.setRangeErrorCallback = r13;     // Catch: ParseException -> L15
        runValidation(this.textInputLayout, r13);     // Catch: ParseException -> L15
        return;
    L10:
        if (this.constraints.isWithinBounds(r22) == false) goto L13;
        onValidDate(Long.valueOf(r12.getTime()));     // Catch: ParseException -> L15
        return;
    L15:
        runValidation(this.textInputLayout, this.setErrorCallback);
        return;
    }

    public abstract void onValidDate(Long r1);

    public void runValidation(View r1, Runnable r2) {
        r1.post(r2);
    }
}
