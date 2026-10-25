package org.telegram.ui.Components;

import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.text.style.ParagraphStyle;
import android.text.style.SuggestionSpan;
import android.util.Pair;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes7.dex */
public class EditTextSuggestionsFix implements TextWatcher {
    private HashMap<Object, Pair<Integer, Integer>> beforeSpans;
    private int beforeSuggestionsCount;
    private boolean ignore;

    public EditTextSuggestionsFix() {
    }

    private static void applySpans(CharSequence r4, HashMap<Object, Pair<Integer, Integer>> r5) {
        if (r5 != null) goto L5;
        return;
    L5:
        if ((r4 instanceof Spannable) == false) goto L34;
        Spannable r42 = (Spannable) r4;
        Iterator<Map.Entry<Object, Pair<Integer, Integer>>> r52 = r5.entrySet().iterator();
    L9:
        if (r52.hasNext() == false) goto L35;
        Map.Entry<Object, Pair<Integer, Integer>> r0 = r52.next();
        if (r42.getSpanStart(r0.getKey()) != (-1)) goto L9;
        int r1 = ((Integer) r0.getValue().first).intValue();
        int r2 = ((Integer) r0.getValue().second).intValue();
        int r3 = r42.length();
        if (r1 > r3) goto L9;
        if (r1 < 0) goto L9;
        if (r2 <= r3) goto L19;
        r2 = r3;
    L19:
        if (r1 > r2) goto L9;
        r42.setSpan(r0.getKey(), r1, r2, 33);
        goto L9
    L35:
        return;
    }

    private static HashMap<Object, Pair<Integer, Integer>> saveSpans(CharSequence r9) {
        HashMap<Object, Pair<Integer, Integer>> r0 = new HashMap();
        if ((r9 instanceof Spannable) == false) goto L33;
        Spannable r92 = (Spannable) r9;
        int r3 = 0;
        CharacterStyle[] r1 = (CharacterStyle[]) r92.getSpans(0, r92.length(), CharacterStyle.class);
        ParagraphStyle[] r2 = (ParagraphStyle[]) r92.getSpans(0, r92.length(), ParagraphStyle.class);
        if (r1 != null) goto L8;
    L20:
        if (r2 == null) goto L33;
        if (r2.length <= 0) goto L33;
    L24:
        if (r3 >= r2.length) goto L33;
        ParagraphStyle r12 = r2[r3];
        if (r12 == null) goto L32;
        if ((r12 instanceof SuggestionSpan) == true) goto L32;
        r0.put(r12, new Pair(Integer.valueOf(r92.getSpanStart(r12)), Integer.valueOf(r92.getSpanEnd(r12))));
    L32:
        r3 = r3 + 1;
        goto L24
    L8:
        if (r1.length <= 0) goto L20;
        int r4 = 0;
    L11:
        if (r4 >= r1.length) goto L20;
        CharacterStyle r5 = r1[r4];
        if (r5 == null) goto L19;
        if ((r5 instanceof SuggestionSpan) == true) goto L19;
        r0.put(r5, new Pair(Integer.valueOf(r92.getSpanStart(r5)), Integer.valueOf(r92.getSpanEnd(r5))));
    L19:
        r4 = r4 + 1;
    L33:
        return r0;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable r1) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        if (this.ignore == false) goto L5;
        return;
    L5:
        this.beforeSpans = saveSpans(r1);
        int r32 = 0;
        if ((r1 instanceof Spannable) == false) goto L8;
        r32 = ((SuggestionSpan[]) ((Spannable) r1).getSpans(0, r1.length(), SuggestionSpan.class)).length;
    L8:
        this.beforeSuggestionsCount = r32;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence r5, int r6, int r7, int r8) {
        if (this.ignore == false) goto L6;
        return;
    L6:
        if ((r5 instanceof Spannable) == false) goto L8;
        int r0 = ((SuggestionSpan[]) ((Spannable) r5).getSpans(0, r5.length(), SuggestionSpan.class)).length;
    L9:
        HashMap<Object, Pair<Integer, Integer>> r2 = this.beforeSpans;
        if (r2 == null) goto L18;
        if (r0 <= 0) goto L13;
    L14:
        if (r6 != 0) goto L20;
        if (r7 != r8) goto L21;
        this.ignore = true;
        applySpans(r5, r2);
        this.ignore = false;
        return;
    L21:
        return;
    L20:
        return;
    L13:
        if (this.beforeSuggestionsCount > 0) goto L14;
        return;
    L18:
        return;
    L8:
        r0 = 0;
        goto L9
    }
}
