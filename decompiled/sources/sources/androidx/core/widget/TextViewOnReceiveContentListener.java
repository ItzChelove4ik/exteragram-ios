package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentListener;

/* loaded from: classes.dex */
public final class TextViewOnReceiveContentListener implements OnReceiveContentListener {
    public TextViewOnReceiveContentListener() {
    }

    private static CharSequence coerceToText(Context r0, ClipData.Item r1, int r2) {
        if ((r2 & 1) == 0) goto L9;
        CharSequence r02 = r1.coerceToText(r0);
        if ((r02 instanceof Spanned) == true) goto L7;
        return r02;
    L7:
        return r02.toString();
    L9:
        return r1.coerceToStyledText(r0);
    }

    private static void replaceSelection(Editable r4, CharSequence r5) {
        int r0 = Selection.getSelectionStart(r4);
        int r1 = Selection.getSelectionEnd(r4);
        int r2 = Math.max(0, Math.min(r0, r1));
        int r02 = Math.max(0, Math.max(r0, r1));
        Selection.setSelection(r4, r02);
        r4.replace(r2, r02, r5);
    }

    @Override // androidx.core.view.OnReceiveContentListener
    public ContentInfoCompat onReceiveContent(View r7, ContentInfoCompat r8) {
        if (Log.isLoggable("ReceiveContent", 3) == false) goto L6;
        Log.d("ReceiveContent", "onReceive: " + r8);
    L6:
        if (r8.getSource() != 2) goto L8;
        return r8;
    L8:
        ClipData r6 = r8.getClip();
        int r82 = r8.getFlags();
        TextView r72 = (TextView) r7;
        Editable r0 = (Editable) r72.getText();
        Context r73 = r72.getContext();
        int r1 = 0;
        boolean r2 = false;
    L10:
        if (r1 >= r6.getItemCount()) goto L17;
        CharSequence r3 = coerceToText(r73, r6.getItemAt(r1), r82);
        if (r3 == null) goto L16;
        if (r2 == true) goto L15;
        replaceSelection(r0, r3);
        r2 = true;
        goto L16
    L15:
        r0.insert(Selection.getSelectionEnd(r0), "\n");
        r0.insert(Selection.getSelectionEnd(r0), r3);
    L16:
        r1 = r1 + 1;
        goto L10
    L17:
        return null;
    }
}
