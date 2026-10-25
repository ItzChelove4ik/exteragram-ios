package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;

/* loaded from: classes3.dex */
public class EffectsTextView extends SpoilersTextView implements RecyclerListView.HitTestable {
    private boolean disablePaddingsOffset;
    private boolean disablePaddingsOffsetX;
    private boolean disablePaddingsOffsetY;
    private boolean isCustomLinkCollector;
    private LinkSpanDrawable.LinkCollector links;
    private LinkSpanDrawable.LinksTextView.OnLinkPress onLongPressListener;
    private LinkSpanDrawable.LinksTextView.OnLinkPress onPressListener;
    private LinkSpanDrawable<ClickableSpan> pressedLink;
    private Theme.ResourcesProvider resourcesProvider;

    public static /* synthetic */ void $r8$lambda$SsaFMslY7aIfPvYk1BEQQ4ik6BQ(EffectsTextView r0, LinkSpanDrawable r1, ClickableSpan r2) {
        r0.lambda$onTouchEvent$0(r1, r2);
    }

    public EffectsTextView(Context r2) {
        this(r2, null);
    }

    public EffectsTextView(Context r2, Theme.ResourcesProvider r3) {
        super(r2, true);
        this.isCustomLinkCollector = false;
        this.links = new LinkSpanDrawable.LinkCollector(this);
        this.resourcesProvider = r3;
    }

    private /* synthetic */ void lambda$onTouchEvent$0(LinkSpanDrawable r3, ClickableSpan r4) {
        LinkSpanDrawable.LinksTextView.OnLinkPress r0 = this.onLongPressListener;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.pressedLink != r3) goto L9;
        r0.run(r4);
        this.pressedLink = null;
        this.links.clear();
        return;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.HitTestable
    public boolean hasClickableNodeAt(float r6, float r7) {
        if (isClickable() == true) goto L5;
        return false;
    L5:
        CharSequence r0 = getText();
        if ((r0 instanceof Spanned) == false) goto L23;
        Spanned r02 = (Spanned) r0;
        float r62 = (r6 - getTotalPaddingLeft()) + getScrollX();
        float r72 = (r7 - getTotalPaddingTop()) + getScrollY();
        Layout r5 = getLayout();
        if (r5 == null) goto L23;
        int r73 = r5.getLineForVertical((int) r72);
        if (r73 < 0) goto L23;
        if (r73 >= r5.getLineCount()) goto L23;
        float r2 = r5.getLineWidth(r73);
        float r3 = r5.getLineLeft(r73);
        if (r62 < r3) goto L23;
        if (r62 > (r3 + r2)) goto L23;
        int r52 = r5.getOffsetForHorizontal(r73, r62);
        if (((ClickableSpan[]) r02.getSpans(r52, r52, ClickableSpan.class)).length <= 0) goto L23;
        return true;
    L23:
        return false;
    }

    @Override // org.telegram.ui.Components.spoilers.SpoilersTextView
    public ClickableSpan hit(int r6, int r7) {
        Layout r0 = getLayout();
        if (r0 != null) goto L5;
        return null;
    L5:
        int r62 = r6 - getPaddingLeft();
        int r72 = r7 - getPaddingTop();
        int r2 = r0.getLineForVertical(r72);
        float r63 = r62;
        int r3 = r0.getOffsetForHorizontal(r2, r63);
        float r5 = getLayout().getLineLeft(r2);
        if (r5 <= r63) goto L8;
    L18:
        return null;
    L8:
        if ((r5 + r0.getLineWidth(r2)) < r63) goto L18;
        if (r72 < 0) goto L18;
        if (r72 > r0.getHeight()) goto L18;
        ClickableSpan[] r52 = (ClickableSpan[]) new SpannableString(r0.getText()).getSpans(r3, r3, ClickableSpan.class);
        if (r52.length == 0) goto L18;
        if (AndroidUtilities.isAccessibilityScreenReaderEnabled() == true) goto L18;
        return r52[0];
    }

    @Override // org.telegram.ui.Components.spoilers.SpoilersTextView, android.widget.TextView, android.view.View
    public void onDraw(Canvas r4) {
        if (this.isCustomLinkCollector == true) goto L19;
        r4.save();
        if (this.disablePaddingsOffset == true) goto L16;
        float r1 = 0.0f;
        if (this.disablePaddingsOffsetX == false) goto L9;
        float r0 = 0.0f;
    L11:
        if (this.disablePaddingsOffsetY == true) goto L14;
        r1 = getPaddingTop();
    L14:
        r4.translate(r0, r1);
        goto L16
    L9:
        r0 = getPaddingLeft();
    L16:
        if (this.links.draw(r4) == false) goto L18;
        invalidate();
    L18:
        r4.restore();
    L19:
        super.onDraw(r4);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (this.links == null) goto L29;
        Layout r0 = getLayout();
        ClickableSpan r2 = hit((int) r8.getX(), (int) r8.getY());
        if (r2 == null) goto L11;
        if (r8.getAction() != 0) goto L11;
        LinkSpanDrawable<ClickableSpan> r3 = new LinkSpanDrawable(r2, this.resourcesProvider, r8.getX(), r8.getY());
        this.pressedLink = r3;
        this.links.addLink(r3);
        SpannableString r82 = new SpannableString(r0.getText());
        int r4 = r82.getSpanStart(this.pressedLink.getSpan());
        int r83 = r82.getSpanEnd(this.pressedLink.getSpan());
        LinkPath r5 = this.pressedLink.obtainNewPath();
        r5.setCurrentLayout(r0, r4, getPaddingTop());
        r0.getSelectionPath(r4, r83, r5);
        AndroidUtilities.runOnUIThread(new EffectsTextView$$ExternalSyntheticLambda0(this, r3, r2), ViewConfiguration.getLongPressTimeout());
        return true;
    L11:
        if (r8.getAction() != 1) goto L26;
        this.links.clear();
        LinkSpanDrawable<ClickableSpan> r02 = this.pressedLink;
        if (r02 != null) goto L15;
    L24:
        this.pressedLink = null;
        goto L26
    L15:
        if (r02.getSpan() != r2) goto L24;
        LinkSpanDrawable.LinksTextView.OnLinkPress r84 = this.onPressListener;
        LinkSpanDrawable<ClickableSpan> r03 = this.pressedLink;
        if (r84 == null) goto L20;
        r84.run((ClickableSpan) r03.getSpan());
    L22:
        this.pressedLink = null;
        return true;
    L20:
        if (r03.getSpan() == null) goto L22;
        ((ClickableSpan) this.pressedLink.getSpan()).onClick(this);
    L26:
        if (r8.getAction() != 3) goto L29;
        this.links.clear();
        this.pressedLink = null;
    L29:
        if (this.pressedLink == null) goto L31;
    L35:
        return true;
    L31:
        if (super.onTouchEvent(r8) == true) goto L35;
        return false;
    }

    @Override // org.telegram.ui.Components.spoilers.SpoilersTextView
    public void setDisablePaddingsOffset(boolean r1) {
        this.disablePaddingsOffset = r1;
    }

    @Override // org.telegram.ui.Components.spoilers.SpoilersTextView
    public void setDisablePaddingsOffsetX(boolean r1) {
        this.disablePaddingsOffsetX = r1;
    }

    @Override // org.telegram.ui.Components.spoilers.SpoilersTextView
    public void setDisablePaddingsOffsetY(boolean r1) {
        this.disablePaddingsOffsetY = r1;
    }

    @Override // org.telegram.ui.Components.spoilers.SpoilersTextView, android.widget.TextView
    public void setText(CharSequence r3, TextView.BufferType r4) {
        super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
    }
}
