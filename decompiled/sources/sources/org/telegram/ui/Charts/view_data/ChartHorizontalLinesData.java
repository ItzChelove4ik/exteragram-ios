package org.telegram.ui.Charts.view_data;

import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ChannelMonetizationLayout;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes6.dex */
public class ChartHorizontalLinesData {
    public int alpha;
    public int fixedAlpha;
    private DecimalFormat formatterTON;
    private StaticLayout[] layouts;
    private StaticLayout[] layouts2;
    public long[] values;
    public CharSequence[] valuesStr;
    public CharSequence[] valuesStr2;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, org.telegram.ui.Charts.view_data.ChartHorizontalLinesData] */
    public ChartHorizontalLinesData(long r24, long r26, boolean r28, float r29, int r30, TextPaint r31, TextPaint r32) {
        ?? r0 = new Object();
        r0.fixedAlpha = 255;
        if (r28 == false) goto L5;
        long r1 = r24 - r26;
        if (r1 != 0) goto L46;
        long r9 = r26 - 1;
        int r12 = 3;
    L44:
        float r13 = 1.0f;
    L56:
        r0.values = new long[r12];
        r0.valuesStr = new CharSequence[r12];
        r0.layouts = new StaticLayout[r12];
        if (r29 <= 0.0f) goto L60;
        r0.valuesStr2 = new CharSequence[r12];
        r0.layouts2 = new StaticLayout[r12];
    L60:
        if ((r13 / r29) >= 1.0f) goto L62;
        boolean r11 = true;
    L63:
        int r15 = 0;
        ChartHorizontalLinesData r02 = r0;
    L64:
        if (r15 >= r12) goto L89;
        long r3 = r9 + ((long) (r15 * r13));
        r02.values[r15] = r3;
        r02.valuesStr[r15] = r02.format(0, r31, r3, r30);
        if (r29 <= 0.0f) goto L77;
        float r14 = r02.values[r15] / r29;
        if (r11 == false) goto L76;
        long r33 = (long) r14;
        if ((r14 - r33) < 0.01f) goto L75;
        if (r30 == 1) goto L75;
        if (r30 == 2) goto L75;
        r02.valuesStr2[r15] = _UrlKt.FRAGMENT_ENCODE_SET;
    L75:
        r02.valuesStr2[r15] = r02.format(1, r32, r33, r30);
        goto L77
    L76:
        r02.valuesStr2[r15] = r02.format(1, r32, (long) r14, r30);
    L77:
        r15 = r15 + 1;
        r02 = this;
        goto L64
    L89:
        return;
    L62:
        r11 = false;
        goto L63
    L46:
        if (r1 >= 6) goto L49;
        long r16 = Math.max(2, r1 + 1);
    L48:
        r9 = r26;
        r12 = (int) r16;
        goto L44
    L49:
        long r4 = r1 / 2;
        if (r4 >= 6) goto L52;
        r9 = r26;
        r12 = (int) ((r4 + (r1 % 2)) + 1);
        r13 = 2.0f;
        goto L56
    L52:
        float r42 = r1 / 5.0f;
        if (r42 > 0.0f) goto L55;
        r16 = Math.max(2, r1 + 1);
        goto L48
    L55:
        r9 = r26;
        r13 = r42;
        r12 = 6;
        goto L56
    L5:
        if (r24 <= 100) goto L8;
        long r17 = round(r24);
    L9:
        long r43 = Math.max(1, (long) Math.ceil(r17 / 5.0d));
        if (r17 >= 6) goto L13;
        int r18 = (int) Math.max(2, r17 + 1);
    L12:
        int r92 = r18;
    L19:
        r0.values = new long[r92];
        r0.valuesStr = new CharSequence[r92];
        r0.layouts = new StaticLayout[r92];
        if (r29 <= 0.0f) goto L23;
        r0.valuesStr2 = new CharSequence[r92];
        r0.layouts2 = new StaticLayout[r92];
    L23:
        if ((r43 / r29) >= 1.0f) goto L25;
        boolean r112 = true;
    L26:
        int r122 = 1;
    L27:
        if (r122 >= r92) goto L78;
        long r2 = r122 * r43;
        r0.values[r122] = r2;
        long r142 = r43;
        r0.valuesStr[r122] = r0.format(0, r31, r2, r30);
        if (r29 <= 0.0f) goto L40;
        float r19 = r0.values[r122] / r29;
        if (r112 == false) goto L39;
        long r34 = (long) r19;
        if ((r19 - r34) < 0.01f) goto L38;
        if (r30 == 1) goto L38;
        if (r30 == 2) goto L38;
        r0.valuesStr2[r122] = _UrlKt.FRAGMENT_ENCODE_SET;
    L38:
        r0.valuesStr2[r122] = r0.format(1, r32, r34, r30);
        goto L40
    L39:
        r0.valuesStr2[r122] = r0.format(1, r32, (long) r19, r30);
    L40:
        r122 = r122 + 1;
        r43 = r142;
        goto L27
    L78:
        return;
    L25:
        r112 = false;
        goto L26
    L13:
        long r21 = r17 / 2;
        if (r21 >= 6) goto L18;
        r92 = (int) (r21 + 1);
        if ((r17 % 2) == 0) goto L19;
        r18 = r92 + 1;
        goto L12
    L18:
        r92 = 6;
        goto L19
    L8:
        r17 = r24;
        goto L9
    }

    public static long lookupHeight(long r2) {
        if (r2 <= 100) goto L6;
        r2 = round(r2);
    L6:
        return ((long) Math.ceil(r2 / 5.0f)) * 5;
    }

    private static long round(long r4) {
        if (((r4 / 5) % 10.0f) != 0.0f) goto L6;
        return r4;
    L6:
        return ((r4 / 10) + 1) * 10;
    }

    public void drawText(Canvas r10, int r11, int r12, float r13, float r14, TextPaint r15) {
        if (r11 != 0) goto L4;
        StaticLayout[] r0 = this.layouts;
    L5:
        StaticLayout r02 = r0[r12];
        if (r02 != null) goto L15;
        if (r11 != 0) goto L9;
        CharSequence[] r03 = this.valuesStr;
    L10:
        CharSequence r2 = r03[r12];
        if (r11 != 0) goto L13;
        StaticLayout[] r9 = this.layouts;
    L14:
        TextPaint r3 = r15;
        StaticLayout r1 = new StaticLayout(r2, r3, AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        r9[r12] = r1;
        r02 = r1;
    L16:
        r10.save();
        r10.translate(r13, r14 + r3.ascent());
        r02.draw(r10);
        r10.restore();
        return;
    L13:
        r9 = this.layouts2;
        goto L14
    L9:
        r03 = this.valuesStr2;
        goto L10
    L15:
        r3 = r15;
        goto L16
    L4:
        r0 = this.layouts2;
        goto L5
    }

    public CharSequence format(int r6, TextPaint r7, long r8, int r10) {
        int r3 = 2;
        if (r10 != 1) goto L16;
        if (r6 != 1) goto L8;
        return "≈" + BillingController.getInstance().formatCurrency(r8, "USD");
    L8:
        if (this.formatterTON != null) goto L10;
        DecimalFormatSymbols r62 = new DecimalFormatSymbols(Locale.US);
        r62.setDecimalSeparator('.');
        DecimalFormat r0 = new DecimalFormat("#.##", r62);
        this.formatterTON = r0;
        r0.setMinimumFractionDigits(2);
        this.formatterTON.setMaximumFractionDigits(6);
        this.formatterTON.setGroupingUsed(false);
    L10:
        DecimalFormat r63 = this.formatterTON;
        if (r8 > 1000000000) goto L14;
        r3 = 6;
    L14:
        r63.setMaximumFractionDigits(r3);
        return ChannelMonetizationLayout.replaceTON("TON " + this.formatterTON.format(r8 / 1.0E9d), r7, 0.8f, -AndroidUtilities.dp(0.66f), false);
    L16:
        if (r10 != 2) goto L23;
        if (r6 != 1) goto L21;
        return "≈" + BillingController.getInstance().formatCurrency(r8, "USD");
    L21:
        return StarsIntroActivity.replaceStarsWithPlain("XTR " + LocaleController.formatNumber(r8, ' '), 0.65f);
    L23:
        return AndroidUtilities.formatWholeNumber((int) r8, 0);
    }
}
