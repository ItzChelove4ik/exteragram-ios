package com.exteragram.messenger.utils.text;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class ZalgoFilter {
    private static ArrayList<int[]> addMarkSequenceRange(ArrayList<int[]> r0, int r1, int r2, int r3, int r4) {
        if (r1 < 0) goto L6;
        if (r3 > r4) goto L5;
        return r0;
    L5:
        return addRange(r0, r1, r2);
    L6:
        return r0;
    }

    private static ArrayList<int[]> addRange(ArrayList<int[]> r0, int r1, int r2) {
        if (r0 != null) goto L4;
        r0 = new ArrayList();
    L4:
        r0.add(new int[]{r1, r2});
        return r0;
    }

    private static void appendReplacement(StringBuilder r2, int r3) {
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L5;
        r2.append(8288);
        r0 = r0 + 1;
        goto L3
    }

    public static boolean canFilter(CharSequence r1) {
        if (ExteraConfig.getFilterZalgo() == true) goto L5;
        return false;
    L5:
        if (TextUtils.isEmpty(r1) == false) goto L7;
        return false;
    L7:
        if (findReplacementRanges(r1) == null) goto L13;
        return true;
    L13:
        return false;
    }

    public static String filter(CharSequence r0) {
        if (r0 != null) goto L6;
        return null;
    L6:
        return filter(r0.toString());
    }

    public static String filter(String r1) {
        if (ExteraConfig.getFilterZalgo() == true) goto L5;
        return r1;
    L5:
        if (TextUtils.isEmpty(r1) == false) goto L8;
        return r1;
    L8:
        return filterEnabled(r1);
    }

    private static String filterEnabled(String r9) {
        ArrayList<int[]> r0 = findReplacementRanges(r9);
        if (r0 != null) goto L5;
        return r9;
    L5:
        StringBuilder r1 = new StringBuilder(r9.length());
        int r2 = r0.size();
        int r4 = 0;
        int r5 = 0;
    L6:
        if (r5 >= r2) goto L8;
        int[] r6 = r0.get(r5);
        r5 = r5 + 1;
        int[] r62 = r6;
        r1.append(r9, r4, r62[0]);
        appendReplacement(r1, r62[1] - r62[0]);
        r4 = r62[1];
        goto L6
    L8:
        r1.append(r9, r4, r9.length());
        return r1.toString();
    }

    public static CharSequence filterSpannable(CharSequence r10) {
        if (canFilter(r10) == true) goto L6;
        return r10;
    L6:
        if ((r10 instanceof Spannable) == false) goto L20;
        Spannable r102 = (Spannable) r10;
        SpannableString r0 = new SpannableString(filterEnabled(r102.toString()));
        Object[] r1 = r102.getSpans(0, r102.length(), Object.class);
        int r2 = r1.length;
        int r4 = 0;
    L8:
        if (r4 >= r2) goto L18;
        Object r5 = r1[r4];
        int r6 = Math.min(r102.getSpanStart(r5), r0.length());
        int r7 = Math.min(r102.getSpanEnd(r5), r0.length());
        int r8 = r102.getSpanFlags(r5);
        if (r6 >= 0) goto L13;
        r6 = 0;
    L13:
        if (r7 <= r0.length()) goto L15;
        r7 = r0.length();
    L15:
        if (r6 <= r7) goto L17;
        r6 = r7;
    L17:
        r0.setSpan(r5, r6, r7, r8);
        r4 = r4 + 1;
        goto L8
    L18:
        return r0;
    L20:
        return filter(r10);
    }

    private static ArrayList<int[]> findReplacementRanges(CharSequence r12) {
        int r0 = r12.length();
        ArrayList<int[]> r1 = null;
        int r6 = -1;
        int r5 = 0;
        int r7 = 0;
        int r8 = 4;
    L3:
        if (r5 >= r0) goto L16;
        int r9 = Character.codePointAt(r12, r5);
        int r10 = Character.charCount(r9);
        int r11 = getAllowedMarksPerSequence(r9);
        if (r11 <= 0) goto L10;
        if (r6 >= 0) goto L8;
        r7 = 0;
        r6 = r5;
    L9:
        r7 = r7 + 1;
        r8 = r11;
    L14:
        r5 = r5 + r10;
        goto L3
    L8:
        r11 = Math.min(r8, r11);
        goto L9
    L10:
        r1 = addMarkSequenceRange(r1, r6, r5, r7, r8);
        if (isDirectionControl(r9) == false) goto L13;
        r1 = addRange(r1, r5, r5 + r10);
    L13:
        r6 = -1;
        r7 = 0;
        r8 = 4;
        goto L14
    L16:
        return addMarkSequenceRange(r1, r6, r0, r7, r8);
    }

    private static int getAllowedMarksPerSequence(int r3) {
        if (r3 >= 768) goto L5;
        return 0;
    L5:
        int r0 = Character.getType(r3);
        if (r0 == 6) goto L11;
        if (r0 == 7) goto L11;
        return 0;
    L11:
        if (isZalgoMarkRange(r3) == false) goto L14;
        return 2;
    L14:
        return 4;
    }

    private static boolean isDirectionControl(int r1) {
        if (r1 != 1564) goto L5;
        return true;
    L5:
        if (r1 != 8206) goto L7;
        return true;
    L7:
        if (r1 != 8207) goto L9;
        return true;
    L9:
        if (r1 < 8234) goto L13;
        if (r1 > 8238) goto L13;
        return true;
    L13:
        if (r1 >= 8294) goto L15;
        return false;
    L15:
        if (r1 <= 8297) goto L25;
        return false;
    L25:
        return true;
    }

    private static boolean isZalgoMarkRange(int r1) {
        if (r1 < 768) goto L7;
        if (r1 > 879) goto L7;
        return true;
    L7:
        if (r1 < 6832) goto L11;
        if (r1 > 6911) goto L11;
        return true;
    L11:
        if (r1 < 7616) goto L15;
        if (r1 > 7679) goto L15;
        return true;
    L15:
        if (r1 < 8400) goto L19;
        if (r1 > 8447) goto L19;
        return true;
    L19:
        if (r1 >= 65056) goto L21;
        return false;
    L21:
        if (r1 > 65071) goto L30;
        return true;
    L30:
        return false;
    }
}
