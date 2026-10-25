package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class StickerSetLinkIcon extends Drawable {
    private final int N;
    public int alpha;
    private final int count;
    private final AnimatedEmojiDrawable[] drawables;
    private boolean hit;
    public final boolean out;
    private final RectF rect;

    public StickerSetLinkIcon(int r5, boolean r6, ArrayList<TLRPC.Document> r7, boolean r8) {
        this.alpha = 255;
        this.rect = new RectF();
        int r82 = 0;
        this.hit = false;
        this.out = r6;
        int r62 = (int) Math.max(1.0d, Math.sqrt(r7.size()));
        this.N = r62;
        int r0 = Math.min(r62 * r62, r7.size());
        this.count = r0;
        this.drawables = new AnimatedEmojiDrawable[r0];
        if (r7.isEmpty() == true) goto L6;
        MessageObject.isAnimatedEmoji(r7.get(0));
    L6:
        if (r62 >= 2) goto L8;
        int r63 = 1;
    L10:
        if (r82 >= this.count) goto L12;
        this.drawables[r82] = AnimatedEmojiDrawable.make(r5, r63, r7.get(r82));
        r82 = r82 + 1;
        goto L10
    L12:
        return;
    L8:
        r63 = 0;
        goto L10
    }

    public void attach(View r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.count) goto L6;
        this.drawables[r0].addView(r3);
        r0 = r0 + 1;
        goto L4
    }

    public void detach(View r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.count) goto L6;
        this.drawables[r0].removeView(r3);
        r0 = r0 + 1;
        goto L4
    }

    public boolean die() {
        return this.hit;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r14) {
        if (this.alpha > 0) goto L5;
        return;
    L5:
        this.rect.set(getBounds());
        float r0 = this.rect.centerX() - (getIntrinsicWidth() / 2.0f);
        float r1 = this.rect.centerY() - (getIntrinsicHeight() / 2.0f);
        float r2 = getIntrinsicWidth() / this.N;
        float r3 = getIntrinsicHeight() / this.N;
        r14.save();
        r14.clipRect(r0, r1, getIntrinsicWidth() + r0, getIntrinsicHeight() + r1);
        int r5 = 0;
    L7:
        if (r5 >= this.N) goto L26;
        int r6 = 0;
    L9:
        int r7 = this.N;
        if (r6 >= r7) goto L25;
        int r72 = (r7 * r5) + r6;
        if (r72 < 0) goto L24;
        AnimatedEmojiDrawable[] r8 = this.drawables;
        if (r72 >= r8.length) goto L24;
        AnimatedEmojiDrawable r82 = r8[r72];
        if (r82 == null) goto L24;
        r82.setBounds((int) ((r6 * r2) + r0), (int) ((r5 * r3) + r1), (int) (((r6 + 1) * r2) + r0), (int) (((r5 + 1) * r3) + r1));
        this.drawables[r72].setAlpha(this.alpha);
        AnimatedEmojiDrawable r83 = this.drawables[r72];
        if (this.out == false) goto L22;
        PorterDuffColorFilter r9 = Theme.chat_outAnimatedEmojiTextColorFilter;
    L23:
        r83.setColorFilter(r9);
        this.drawables[r72].draw(r14);
        goto L24
    L22:
        r9 = Theme.chat_animatedEmojiTextColorFilter;
    L24:
        r6 = r6 + 1;
        goto L9
    L25:
        r5 = r5 + 1;
        goto L7
    L26:
        r14.restore();
    }

    public boolean equals(ArrayList<TLRPC.Document> r8) {
        AnimatedEmojiDrawable[] r0 = this.drawables;
        if (r8 != null) goto L9;
        if (r0.length != 0) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r0.length == r8.size()) goto L11;
        return false;
    L11:
        int r02 = 0;
    L12:
        AnimatedEmojiDrawable[] r3 = this.drawables;
        if (r02 >= r3.length) goto L22;
        TLRPC.Document r32 = r3[r02].getDocument();
        if (r32 != null) goto L17;
        long r33 = 0;
    L19:
        if (r33 != r8.get(r02).id) goto L20;
        r02 = r02 + 1;
        goto L12
    L20:
        return false;
    L17:
        r33 = r32.id;
        goto L19
    L22:
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(48.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(48.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public void keepAlive() {
        this.hit = false;
    }

    public void readyToDie() {
        this.hit = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
