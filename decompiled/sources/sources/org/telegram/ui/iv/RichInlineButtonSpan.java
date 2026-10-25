package org.telegram.ui.iv;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.style.ReplacementSpan;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class RichInlineButtonSpan extends ReplacementSpan {
    private View attachedView;
    private final TL_iv.textButton button;
    private int currentAccount;
    private RichMessageLayout.RichButtonSpan renderedSpan;
    private Theme.ResourcesProvider resourcesProvider;

    public RichInlineButtonSpan(TL_iv.textButton r2) {
        this.currentAccount = UserConfig.selectedAccount;
        this.button = r2;
    }

    private RichMessageLayout.RichButtonSpan ensureRenderer() {
        if (this.renderedSpan != null) goto L8;
        RichMessageLayout.RichButtonSpan r0 = RichMessageLayout.createEditorButtonSpan(this.currentAccount, AndroidUtilities.dp(240.0f), this.resourcesProvider, this.button);
        this.renderedSpan = r0;
        View r1 = this.attachedView;
        if (r1 == null) goto L8;
        r0.attach(r1);
    L8:
        return this.renderedSpan;
    }

    public static boolean isSupported(TL_keyboard.InlineButtonType r1) {
        if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeUrl) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeCopy) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeUserProfile) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public void bind(View r3, int r4, Theme.ResourcesProvider r5) {
        RichMessageLayout.RichButtonSpan r0 = this.renderedSpan;
        if (r0 == null) goto L7;
        View r1 = this.attachedView;
        if (r1 == null) goto L7;
        r0.detach(r1);
    L7:
        this.attachedView = r3;
        this.currentAccount = r4;
        this.resourcesProvider = r5;
        this.renderedSpan = null;
        ensureRenderer();
    }

    public void detach(View r3) {
        RichMessageLayout.RichButtonSpan r0 = this.renderedSpan;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.attachedView != r3) goto L9;
        r0.detach(r3);
        this.attachedView = null;
        return;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        ensureRenderer().draw(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public TL_iv.textButton getButton() {
        return this.button;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        return ensureRenderer().getSize(r1, r2, r3, r4, r5);
    }

    public void removeNestedReplacementSpans(Spannable r9) {
        if (r9 == null) goto L24;
        int r0 = r9.getSpanStart(this);
        int r1 = r9.getSpanEnd(this);
        if (r0 < 0) goto L18;
        if (r1 <= r0) goto L25;
        ReplacementSpan[] r2 = (ReplacementSpan[]) r9.getSpans(r0, r1, ReplacementSpan.class);
        int r3 = r2.length;
        int r4 = 0;
    L9:
        if (r4 >= r3) goto L26;
        ReplacementSpan r5 = r2[r4];
        if (r5 == this) goto L17;
        int r6 = r9.getSpanStart(r5);
        int r7 = r9.getSpanEnd(r5);
        if (r6 >= r1) goto L17;
        if (r7 <= r0) goto L17;
        r9.removeSpan(r5);
    L17:
        r4 = r4 + 1;
        goto L9
    L26:
        return;
    L25:
        return;
    L18:
        return;
    }

    public void setPressed(boolean r1) {
        ensureRenderer().setPressed(r1);
    }
}
