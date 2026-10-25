package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes7.dex */
public abstract class TextHelper {
    public static LinkSpanDrawable.LinksTextView makeLinkTextView(Context r1, float r2, int r3, int r4, boolean r5, Theme.ResourcesProvider r6) {
        LinkSpanDrawable.LinksTextView r0 = new LinkSpanDrawable.LinksTextView(r1);
        r0.setTextSize(1, r2);
        r0.setTextColor(Theme.getColor(r3, r6));
        r0.setLinkTextColor(Theme.getColor(r4, r6));
        if (r5 == false) goto L5;
        r0.setTypeface(AndroidUtilities.bold());
    L5:
        return r0;
    }

    public static LinkSpanDrawable.LinksTextView makeLinkTextView(Context r6, float r7, int r8, boolean r9, Theme.ResourcesProvider r10) {
        return makeLinkTextView(r6, r7, r8, Theme.key_chat_messageLinkIn, r9, r10);
    }

    public static TextView makeTextView(Context r1, float r2, int r3, boolean r4) {
        return makeTextView(r1, r2, r3, r4, null);
    }

    public static TextView makeTextView(Context r1, float r2, int r3, boolean r4, Theme.ResourcesProvider r5) {
        TextView r0 = new TextView(r1);
        r0.setTextSize(1, r2);
        r0.setTextColor(Theme.getColor(r3, r5));
        if (r4 == false) goto L5;
        Typeface r12 = AndroidUtilities.bold();
    L6:
        r0.setTypeface(r12);
        return r0;
    L5:
        r12 = AndroidUtilities.regular();
        goto L6
    }
}
