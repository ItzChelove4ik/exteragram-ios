package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;

/* loaded from: classes7.dex */
public class MessageSeenCheckDrawable {
    private int colorKey;
    private Drawable drawable;
    private int h;
    private int lastColor;
    private float lastDensity;
    private CharSequence lastSpanned;
    private float oy;
    private int resId;
    private int w;

    public MessageSeenCheckDrawable(int r2, int r3) {
        this.w = -1;
        this.h = -1;
        this.oy = 4.66f;
        this.resId = r2;
        this.colorKey = r3;
    }

    public MessageSeenCheckDrawable(int r1, int r2, int r3, int r4, float r5) {
        this(r1, r2);
        this.w = r3;
        this.h = r4;
        this.oy = r5;
    }

    public CharSequence getSpanned(Context r5, Theme.ResourcesProvider r6) {
        if (this.lastSpanned != null) goto L5;
    L13:
        if (r5 != null) goto L16;
        return null;
    L16:
        SpannableStringBuilder r0 = new SpannableStringBuilder("v ");
        this.lastDensity = AndroidUtilities.density;
        Drawable r52 = r5.getResources().getDrawable(this.resId).mutate();
        this.drawable = r52;
        int r62 = Theme.getColor(this.colorKey, r6);
        this.lastColor = r62;
        r52.setColorFilter(new PorterDuffColorFilter(r62, PorterDuff.Mode.SRC_IN));
        int r53 = this.w;
        if (r53 > 0) goto L19;
        int r54 = this.drawable.getIntrinsicWidth();
    L20:
        int r63 = this.h;
        if (r63 > 0) goto L23;
        int r64 = this.drawable.getIntrinsicHeight();
    L24:
        int r1 = AndroidUtilities.dp(this.oy);
        this.drawable.setBounds(0, r1, r54, r64 + r1);
        r0.setSpan(new ImageSpan(this.drawable, 2), 0, 1, 33);
        r0.setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(2.0f)), 1, 2, 33);
        this.lastSpanned = r0;
        return r0;
    L23:
        r64 = AndroidUtilities.dp(r63);
        goto L24
    L19:
        r54 = AndroidUtilities.dp(r53);
        goto L20
    L5:
        if (this.drawable == null) goto L13;
        if (AndroidUtilities.density != this.lastDensity) goto L13;
        if (this.lastColor == Theme.getColor(this.colorKey, r6)) goto L12;
        Drawable r55 = this.drawable;
        int r65 = Theme.getColor(this.colorKey, r6);
        this.lastColor = r65;
        r55.setColorFilter(new PorterDuffColorFilter(r65, PorterDuff.Mode.SRC_IN));
    L12:
        return this.lastSpanned;
    }
}
