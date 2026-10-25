package org.telegram.ui.Components;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.TextStyleSpan;

/* loaded from: classes3.dex */
public class FormattedDateSpan extends URLSpan {
    public final boolean applied;
    public final TLRPC.TL_messageEntityFormattedDate entity;
    public final String originalText;
    public final TextStyleSpan.TextStyleRun style;

    public FormattedDateSpan(String r1, TextStyleSpan.TextStyleRun r2, TLRPC.TL_messageEntityFormattedDate r3) {
        super(r1);
        this.originalText = r1;
        this.entity = r3;
        this.style = r2;
        this.applied = false;
    }

    private FormattedDateSpan(FormattedDateSpan r2, boolean r3) {
        super(r2.originalText);
        this.originalText = r2.originalText;
        this.entity = r2.entity;
        this.style = r2.style;
        this.applied = r3;
    }

    public static CharSequence applyFormatedDateEntities(CharSequence r1) {
        return rebuildFormatedDateEntities(r1, true);
    }

    public static ArrayList<Integer> getAllRelativeDates(CharSequence r5) {
        ArrayList<Integer> r1 = null;
        if ((r5 instanceof Spanned) == false) goto L13;
        Spanned r52 = (Spanned) r5;
        int r3 = 0;
        FormattedDateSpan[] r53 = (FormattedDateSpan[]) r52.getSpans(0, r52.length(), FormattedDateSpan.class);
        int r0 = r53.length;
    L5:
        if (r3 >= r0) goto L13;
        FormattedDateSpan r2 = r53[r3];
        if (r2.entity.relative == false) goto L12;
        if (r1 != null) goto L11;
        r1 = new ArrayList(r53.length);
    L11:
        r1.add(Integer.valueOf(r2.entity.date));
    L12:
        r3 = r3 + 1;
    L13:
        return r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [android.text.SpannableStringBuilder] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    private static CharSequence rebuildFormatedDateEntities(CharSequence r9, boolean r10) {
        if ((r9 instanceof Spanned) == false) goto L22;
        Spanned r0 = (Spanned) r9;
        int r3 = 0;
        FormattedDateSpan[] r1 = (FormattedDateSpan[]) r0.getSpans(0, r0.length(), FormattedDateSpan.class);
        int r2 = r1.length;
        ?? r4 = 0;
    L5:
        if (r3 >= r2) goto L22;
        FormattedDateSpan r5 = r1[r3];
        if (r5.needReplaceText() == false) goto L21;
        if (r5.applied != r10) goto L14;
        if (r10 == false) goto L21;
        if (r5.entity.relative == false) goto L21;
    L14:
        if (r4 != 0) goto L16;
        r9 = new SpannableStringBuilder(r0);
        r4 = r9;
    L16:
        int r6 = r4.getSpanStart(r5);
        int r7 = r4.getSpanEnd(r5);
        if (r10 == false) goto L19;
        String r8 = LocaleController.formatEntityFormattedDate(r5.entity);
    L20:
        r4.removeSpan(r5);
        r4.replace(r6, r7, r8);
        r4.setSpan(new FormattedDateSpan(r5, r10), r6, r8.length() + r6, 33);
        goto L21
    L19:
        r8 = r5.originalText;
    L21:
        r3 = r3 + 1;
        r4 = r4;
    L22:
        return r9;
    }

    public static CharSequence restoreFormatedDateEntities(CharSequence r1) {
        return rebuildFormatedDateEntities(r1, false);
    }

    public boolean needReplaceText() {
        if (this.entity.flags == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View r1) {
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r3) {
        int r0 = r3.linkColor;
        int r1 = r3.getColor();
        super.updateDrawState(r3);
        TextStyleSpan.TextStyleRun r2 = this.style;
        if (r2 == null) goto L5;
        r2.applyStyle(r3);
    L5:
        if (r0 != r1) goto L7;
        boolean r22 = true;
    L8:
        r3.setUnderlineText(r22);
        return;
    L7:
        r22 = false;
        goto L8
    }
}
