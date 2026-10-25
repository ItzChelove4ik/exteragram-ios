package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public class IdenticonDrawable extends Drawable {
    private int[] colors;
    private byte[] data;
    private Paint paint;

    public IdenticonDrawable() {
        this.paint = new Paint();
        this.colors = new int[]{-1, -2758925, -13805707, -13657655};
    }

    private int getBits(int r2) {
        return (this.data[r2 / 8] >> (r2 % 8)) & 3;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r23) {
        byte[] r1 = this.data;
        if (r1 != null) goto L6;
        return;
    L6:
        if (r1.length != 16) goto L14;
        float r12 = (float) Math.floor(Math.min(getBounds().width(), getBounds().height()) / 8.0f);
        float r2 = 8.0f * r12;
        float r6 = Math.max(0.0f, (getBounds().width() - r2) / 2.0f);
        float r22 = Math.max(0.0f, (getBounds().height() - r2) / 2.0f);
        int r3 = 0;
        int r4 = 0;
    L9:
        if (r3 >= 8) goto L26;
        int r8 = 0;
    L11:
        if (r8 >= 8) goto L13;
        int r9 = getBits(r4);
        r4 = r4 + 2;
        this.paint.setColor(this.colors[Math.abs(r9) % 4]);
        float r11 = r6 + (r8 * r12);
        float r92 = r3 * r12;
        r23.drawRect(r11, r92 + r22, r11 + r12, (r92 + r12) + r22, this.paint);
        r8 = r8 + 1;
        goto L11
    L13:
        r3 = r3 + 1;
        goto L9
    L26:
        return;
    L14:
        float r13 = (float) Math.floor(Math.min(getBounds().width(), getBounds().height()) / 12.0f);
        float r24 = 12.0f * r13;
        float r62 = Math.max(0.0f, (getBounds().width() - r24) / 2.0f);
        float r25 = Math.max(0.0f, (getBounds().height() - r24) / 2.0f);
        int r32 = 0;
        int r42 = 0;
    L16:
        if (r32 >= 12) goto L27;
        int r82 = 0;
    L18:
        if (r82 >= 12) goto L20;
        this.paint.setColor(this.colors[Math.abs(getBits(r42)) % 4]);
        float r17 = r62 + (r82 * r13);
        float r93 = r32 * r13;
        r23.drawRect(r17, r93 + r25, r17 + r13, (r93 + r13) + r25, this.paint);
        r42 = r42 + 2;
        r82 = r82 + 1;
        goto L18
    L20:
        r32 = r32 + 1;
        goto L16
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(32.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(32.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setEncryptedChat(TLRPC.EncryptedChat r2) {
        byte[] r0 = r2.key_hash;
        this.data = r0;
        if (r0 != null) goto L5;
        byte[] r02 = AndroidUtilities.calcAuthKeyHash(r2.auth_key);
        this.data = r02;
        r2.key_hash = r02;
    L5:
        invalidateSelf();
    }
}
