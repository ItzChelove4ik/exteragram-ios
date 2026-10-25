package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import androidx.core.util.Pair;
import com.google.android.material.internal.ViewUtils;
import java.text.SimpleDateFormat;
import java.util.Collection;

/* loaded from: classes5.dex */
public interface DateSelector<S> extends Parcelable {
    static /* synthetic */ void $r8$lambda$TNhbzYkxt_f1_O4dm_hxdpI0i6Q(EditText[] r3, View r4, boolean r5) {
        int r52 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r52) goto L8;
        if (r3[r1].hasFocus() == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return;
    L8:
        ViewUtils.hideKeyboard(r4, false);
    }

    static /* synthetic */ void $r8$lambda$vs5DDK9P9VRLIKi_FniZWAFh_28(View r1) {
        ViewUtils.requestFocusAndShowKeyboard(r1, false);
    }

    static boolean isTouchExplorationEnabled(Context r1) {
        AccessibilityManager r12 = (AccessibilityManager) r1.getSystemService("accessibility");
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.isTouchExplorationEnabled() == false) goto L10;
        return true;
    L10:
        return false;
    }

    static void showKeyboardWithAutoHideBehavior(EditText... r5) {
        if (r5.length != 0) goto L5;
        return;
    L5:
        DateSelector$$ExternalSyntheticLambda0 r0 = new DateSelector$$ExternalSyntheticLambda0(r5);
        int r1 = r5.length;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L8;
        r5[r3].setOnFocusChangeListener(r0);
        r3 = r3 + 1;
        goto L6
    L8:
        EditText r52 = r5[0];
        r52.postDelayed(new DateSelector$$ExternalSyntheticLambda1(r52), 100);
    }

    int getDefaultThemeResId(Context r1);

    int getDefaultTitleResId();

    String getError();

    Collection<Long> getSelectedDays();

    Collection<Pair<Long, Long>> getSelectedRanges();

    S getSelection();

    String getSelectionContentDescription(Context r1);

    String getSelectionDisplayString(Context r1);

    boolean isSelectionComplete();

    View onCreateTextInputView(LayoutInflater r1, ViewGroup r2, Bundle r3, CalendarConstraints r4, OnSelectionChangedListener<S> r5);

    void select(long r1);

    void setSelection(S r1);

    void setTextInputFormat(SimpleDateFormat r1);
}
