package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.style.ReplacementSpan;
import android.view.View;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.WebInstantView;

/* loaded from: classes7.dex */
public class TextPaintImageReceiverSpan extends ReplacementSpan {
    private boolean alignTop;
    private boolean baselineMode;
    private int contentWidth;
    private int depth;
    private int height;
    private ImageReceiver imageReceiver;
    private int lastDrawTop;
    private float lastDrawX;
    private boolean lastDrawn;
    private int scrollX;
    private int width;

    public static /* synthetic */ void $r8$lambda$4MMEUN7lGItDcmEJ4PJPQuQdpRk(ImageReceiver r0, boolean r1, boolean r2, boolean r3) {
        if (r0.canInvertBitmap() == true) goto L5;
        return;
    L5:
        r0.setColorFilter(new ColorMatrixColorFilter(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
    }

    public static /* synthetic */ void $r8$lambda$hL00ZSraoBvcqklhxroC5of8_Ck(ImageReceiver r0, boolean r1, boolean r2, boolean r3) {
        if (r0.canInvertBitmap() == true) goto L5;
        return;
    L5:
        r0.setColorFilter(new ColorMatrixColorFilter(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
    }

    public static /* synthetic */ void $r8$lambda$njb1CVCkH53R0cVT72Kh89O4uj4() {
    }

    public TextPaintImageReceiverSpan(View r1, Bitmap r2, int r3, int r4, int r5, int r6) {
        this.width = r3;
        this.height = r4;
        this.contentWidth = r3;
        ImageReceiver r32 = new ImageReceiver(r1);
        this.imageReceiver = r32;
        r32.setInvalidateAll(true);
        this.imageReceiver.setImageBitmap(r2);
        this.imageReceiver.setColorFilter(new PorterDuffColorFilter(r5, PorterDuff.Mode.SRC_IN));
        this.depth = r6;
        this.baselineMode = true;
    }

    public TextPaintImageReceiverSpan(View r17, TLRPC.Document r18, Object r19, int r20, int r21, boolean r22, boolean r23) {
        String r8 = String.format(Locale.US, "%d_%d_i", new Object[]{Integer.valueOf(r20), Integer.valueOf(r21)});
        this.width = r20;
        this.height = r21;
        this.contentWidth = r20;
        ImageReceiver r2 = new ImageReceiver(r17);
        this.imageReceiver = r2;
        r2.setInvalidateAll(true);
        if (r23 == false) goto L5;
        this.imageReceiver.setDelegate(new TextPaintImageReceiverSpan$$ExternalSyntheticLambda2());
    L5:
        TLRPC.PhotoSize r24 = FileLoader.getClosestPhotoSizeWithSize(r18.thumbs, 90);
        this.imageReceiver.setImage(ImageLocation.getForDocument(r18), r8, ImageLocation.getForDocument(r24, r18), r8, -1, null, r19, 1);
        this.alignTop = r22;
    }

    public TextPaintImageReceiverSpan(View r1, WebInstantView.WebPhoto r2, Object r3, int r4, int r5, boolean r6, boolean r7) {
        this.width = r4;
        this.height = r5;
        this.contentWidth = r4;
        ImageReceiver r32 = new ImageReceiver(r1);
        this.imageReceiver = r32;
        r32.setInvalidateAll(true);
        if (r7 == false) goto L5;
        this.imageReceiver.setDelegate(new TextPaintImageReceiverSpan$$ExternalSyntheticLambda0());
    L5:
        WebInstantView.loadPhoto(r2, this.imageReceiver, new TextPaintImageReceiverSpan$$ExternalSyntheticLambda1());
        this.alignTop = r6;
    }

    public boolean containsPoint(float r3, float r4) {
        if (this.lastDrawn == true) goto L5;
        return false;
    L5:
        if (isScrollable() == false) goto L18;
        float r0 = this.lastDrawX;
        if (r3 >= r0) goto L9;
        return false;
    L9:
        if (r3 <= (r0 + this.width)) goto L11;
        return false;
    L11:
        if (r4 >= this.lastDrawTop) goto L13;
        return false;
    L13:
        if (r4 > (r3 + this.height)) goto L22;
        return true;
    L22:
        return false;
    L18:
        return false;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        r1.save();
        if (this.baselineMode == false) goto L9;
        int r22 = this.height;
        int r72 = r7 - (r22 - this.depth);
        this.lastDrawX = r5;
        this.lastDrawTop = r72;
        this.lastDrawn = true;
        int r32 = this.contentWidth;
        int r42 = this.width;
        if (r32 <= r42) goto L7;
        float r33 = r72;
        r1.clipRect(r5, r33, r42 + r5, r72 + r22);
        this.imageReceiver.setImageCoords((int) (r5 - this.scrollX), r33, this.contentWidth, this.height);
    L12:
        this.imageReceiver.draw(r1);
        r1.restore();
        return;
    L7:
        this.imageReceiver.setImageCoords((int) r5, r72, r42, r22);
        goto L12
    L9:
        if (this.alignTop == false) goto L11;
        this.imageReceiver.setImageCoords((int) r5, r6 - 1, this.width, this.height);
        goto L12
    L11:
        int r82 = (r8 - AndroidUtilities.dp(4.0f)) - r6;
        this.imageReceiver.setImageCoords((int) r5, r6 + ((r82 - r4) / 2), this.width, this.height);
        goto L12
    }

    public int getMaxScroll() {
        return Math.max(0, this.contentWidth - this.width);
    }

    public int getScroll() {
        return this.scrollX;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        if (r5 == null) goto L11;
        if (this.baselineMode == false) goto L7;
        int r12 = this.height;
        int r22 = this.depth;
        int r13 = -(r12 - r22);
        r5.ascent = r13;
        r5.top = r13;
        r5.descent = r22;
        r5.bottom = r22;
        goto L11
    L7:
        if (this.alignTop == false) goto L9;
        int r14 = (r5.descent - r5.ascent) - AndroidUtilities.dp(4.0f);
        int r23 = this.height - r14;
        r5.descent = r23;
        r5.bottom = r23;
        int r15 = 0 - r14;
        r5.ascent = r15;
        r5.top = r15;
        goto L11
    L9:
        int r16 = ((-this.height) / 2) - AndroidUtilities.dp(4.0f);
        r5.ascent = r16;
        r5.top = r16;
        int r17 = this.height;
        int r18 = (r17 - (r17 / 2)) - AndroidUtilities.dp(4.0f);
        r5.descent = r18;
        r5.bottom = r18;
    L11:
        return this.width;
    }

    public boolean isScrollable() {
        if (this.contentWidth <= this.width) goto L6;
        return true;
    L6:
        return false;
    }

    public void setScroll(int r3) {
        this.scrollX = Utilities.clamp(r3, getMaxScroll(), 0);
    }

    public void setScrollableContentWidth(int r1) {
        this.contentWidth = r1;
    }
}
