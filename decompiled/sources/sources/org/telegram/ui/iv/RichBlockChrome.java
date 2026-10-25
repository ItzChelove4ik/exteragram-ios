package org.telegram.ui.iv;

import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ReplyMessageLine;

/* loaded from: classes7.dex */
public abstract class RichBlockChrome {
    public static void applyEditorQuoteColor(ReplyMessageLine r2, Theme.ResourcesProvider r3) {
        if (r3 == null) goto L4;
        boolean r0 = r3.isDark();
    L5:
        r2.setSimpleColor(Theme.getColor(Theme.key_featuredStickers_addButton, r3), r0);
        return;
    L4:
        r0 = Theme.isCurrentThemeDark();
        goto L5
    }

    public static void applyInsetPx(View r7, int r8, int r9, int r10, int r11, int r12) {
        applyInsetPx(r7, r8, 0, r9, r10, r11, r12);
    }

    public static void applyInsetPx(View r1, int r2, int r3, int r4, int r5, int r6, int r7) {
        if (rtl() == false) goto L6;
        r1.setPadding(r4 + r3, r5, r6 + r2, r7);
        return;
    L6:
        r1.setPadding(r4 + r2, r5, r6 + r3, r7);
    }

    public static int insetEndFor(BlockRow r0) {
        return quoteInsetEnd(r0);
    }

    public static int insetFor(BlockRow r2) {
        if (r2 != null) goto L6;
        return 0;
    L6:
        return quoteInset(r2) + insetForDepth(Math.max(0, r2.level));
    }

    public static int insetForDepth(int r0) {
        if (r0 > 0) goto L6;
        return 0;
    L6:
        return AndroidUtilities.dp(((r0 - 1) * 24) + 28);
    }

    public static int quoteBottomPad(BlockRow r0) {
        if (r0 != null) goto L6;
        return 0;
    L6:
        return quoteEdgePad(r0.quoteBottomEdge);
    }

    public static int quoteDepth(BlockRow r0) {
        if (r0 != null) goto L6;
        return 0;
    L6:
        return r0.quoteIds.size();
    }

    public static int quoteEdgePad(int r0) {
        if (r0 > 0) goto L6;
        return 0;
    L6:
        return AndroidUtilities.dp(((r0 - 1) * 16) + 10);
    }

    public static int quoteInset(BlockRow r0) {
        if (quoteDepth(r0) > 0) goto L7;
        return 0;
    L7:
        return AndroidUtilities.dp(((r0 - 1) * 16) + 12);
    }

    public static int quoteInsetEnd(BlockRow r0) {
        if (quoteDepth(r0) > 0) goto L7;
        return 0;
    L7:
        return AndroidUtilities.dp(((r0 - 1) * 16) + 8);
    }

    public static int quoteTopPad(BlockRow r0) {
        if (r0 != null) goto L6;
        return 0;
    L6:
        return quoteEdgePad(r0.quoteTopEdge);
    }

    public static boolean rtl() {
        return LocaleController.isRTL;
    }
}
