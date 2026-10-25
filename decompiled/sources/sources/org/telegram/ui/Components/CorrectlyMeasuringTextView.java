package org.telegram.ui.Components;

import android.content.Context;
import android.text.Layout;
import android.widget.TextView;
import org.telegram.tgnet.TLObject;

/* loaded from: classes7.dex */
public class CorrectlyMeasuringTextView extends TextView {
    public CorrectlyMeasuringTextView(Context r1) {
        super(r1);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int r6, int r7) {
        super.onMeasure(r6, r7);
        Layout r62 = getLayout();     // Catch: Exception -> L11
        if (r62.getLineCount() <= 1) goto L15;
        int r72 = r62.getLineCount() - 1;     // Catch: Exception -> L11
        int r0 = 0;
    L7:
        if (r72 < 0) goto L9;
        r0 = Math.max(r0, Math.round(r62.getPaint().measureText(getText(), r62.getLineStart(r72), r62.getLineEnd(r72))));     // Catch: Exception -> L11
        r72 = r72 - 1;
        goto L7
    L9:
        super.onMeasure(Math.min((r0 + getPaddingLeft()) + getPaddingRight(), getMeasuredWidth()) | TLObject.FLAG_30, 1073741824 | getMeasuredHeight());     // Catch: Exception -> L11
        return;
    L15:
        return;
    }
}
