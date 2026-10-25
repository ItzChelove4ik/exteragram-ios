package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ReplacementSpan;
import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;

/* loaded from: classes.dex */
public class AvatarSpan extends ReplacementSpan {
    private final AvatarDrawable avatarDrawable;
    private final int currentAccount;
    private final ImageReceiver imageReceiver;
    public boolean needDrawShadow;
    private View parent;
    private final View.OnAttachStateChangeListener parentAttachListener;
    private final Paint shadowPaint;
    private int shadowPaintAlpha;
    private float sz;
    private float translateX;
    private float translateY;
    public boolean usePaintAlpha;

    /* renamed from: org.telegram.ui.AvatarSpan$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ AvatarSpan this$0;

        public AnonymousClass1(AvatarSpan r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            AvatarSpan.m6282$$Nest$fgetimageReceiver(this.this$0).onAttachedToWindow();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            AvatarSpan.m6282$$Nest$fgetimageReceiver(this.this$0).onDetachedFromWindow();
        }
    }

    /* renamed from: -$$Nest$fgetimageReceiver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m6282$$Nest$fgetimageReceiver(AvatarSpan r0) {
        return r0.imageReceiver;
    }

    public AvatarSpan(View r2, int r3) {
        this(r2, r3, 18.0f);
    }

    public AvatarSpan(View r4, int r5, float r6) {
        this.needDrawShadow = true;
        this.parentAttachListener = new AnonymousClass1(this);
        this.shadowPaintAlpha = 255;
        this.usePaintAlpha = true;
        this.currentAccount = r5;
        ImageReceiver r52 = new ImageReceiver(r4);
        this.imageReceiver = r52;
        r52.setInvalidateAll(true);
        this.avatarDrawable = new AvatarDrawable();
        setSize(r6);
        Paint r53 = new Paint(1);
        this.shadowPaint = r53;
        r53.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, AndroidUtilities.dp(0.66f), 855638016);
        setParent(r4);
    }

    public static void checkSpansParent(CharSequence r3, View r4) {
        if (r3 != null) goto L5;
        return;
    L5:
        if ((r3 instanceof Spannable) == false) goto L9;
        Spannable r32 = (Spannable) r3;
        int r2 = 0;
        AvatarSpan[] r33 = (AvatarSpan[]) r32.getSpans(0, r32.length(), AvatarSpan.class);
        int r0 = r33.length;
    L7:
        if (r2 >= r0) goto L12;
        r33[r2].setParent(r4);
        r2 = r2 + 1;
        goto L7
    L12:
        return;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r12, CharSequence r13, int r14, int r15, float r16, int r17, int r18, int r19, Paint r20) {
        float r1 = 1.0f;
        if (this.needDrawShadow == true) goto L5;
    L8:
        this.imageReceiver.setImageCoords(this.translateX + r16, (this.translateY + ((r17 + r19) / 2.0f)) - (AndroidUtilities.dp(this.sz) / 2.0f), AndroidUtilities.dp(this.sz), AndroidUtilities.dp(this.sz));
        ImageReceiver r132 = this.imageReceiver;
        if (this.usePaintAlpha == false) goto L11;
        r1 = r20.getAlpha() / 255.0f;
    L11:
        r132.setAlpha(r1);
        this.imageReceiver.draw(r12);
        return;
    L5:
        if (this.shadowPaintAlpha == r20.getAlpha()) goto L7;
        Paint r133 = this.shadowPaint;
        int r3 = r20.getAlpha();
        this.shadowPaintAlpha = r3;
        r133.setAlpha(r3);
        this.shadowPaint.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, AndroidUtilities.dp(0.66f), Theme.multAlpha(855638016, this.shadowPaintAlpha / 255.0f));
    L7:
        float r32 = (r17 + r19) / 2.0f;
        r12.drawRoundRect(this.translateX + r16, (this.translateY + r32) - (AndroidUtilities.dp(this.sz) / 2.0f), AndroidUtilities.dp(this.sz) + (this.translateX + r16), ((this.translateY + r32) - (AndroidUtilities.dp(this.sz) / 2.0f)) + AndroidUtilities.dp(this.sz), ExteraConfig.getAvatarCorners(this.sz), ExteraConfig.getAvatarCorners(this.sz), this.shadowPaint);
        goto L8
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        return AndroidUtilities.dp(this.sz);
    }

    public void setChat(TLRPC.Chat r3) {
        this.avatarDrawable.setInfo(this.currentAccount, r3);
        this.imageReceiver.setForUserOrChat(r3, this.avatarDrawable);
    }

    public void setDialogId(long r3) {
        int r1 = this.currentAccount;
        if (r3 < 0) goto L6;
        setUser(MessagesController.getInstance(r1).getUser(Long.valueOf(r3)));
        return;
    L6:
        setChat(MessagesController.getInstance(r1).getChat(Long.valueOf(-r3)));
    }

    public void setImageDrawable(Drawable r1) {
        this.imageReceiver.setImageBitmap(r1);
    }

    public void setObject(TLObject r3) {
        this.avatarDrawable.setInfo(this.currentAccount, r3);
        this.imageReceiver.setForUserOrChat(r3, this.avatarDrawable);
    }

    public void setParent(View r3) {
        View r0 = this.parent;
        if (r0 == r3) goto L24;
        if (r0 == null) goto L11;
        r0.removeOnAttachStateChangeListener(this.parentAttachListener);
        if (this.parent.isAttachedToWindow() == false) goto L11;
        if (r3.isAttachedToWindow() == true) goto L11;
        this.imageReceiver.onDetachedFromWindow();
    L11:
        View r02 = this.parent;
        if (r02 != null) goto L14;
    L15:
        if (r3 != null) goto L17;
    L19:
        this.parent = r3;
        this.imageReceiver.setParentView(r3);
        if (r3 == null) goto L23;
        r3.addOnAttachStateChangeListener(this.parentAttachListener);
        return;
    L23:
        return;
    L17:
        if (r3.isAttachedToWindow() == false) goto L19;
        this.imageReceiver.onAttachedToWindow();
        goto L19
    L14:
        if (r02.isAttachedToWindow() == true) goto L19;
    }

    public void setSize(float r3) {
        this.avatarDrawable.setTextSize(AndroidUtilities.dp(5.0f + r3));
        this.imageReceiver.setRoundRadius(ExteraConfig.getAvatarCorners(r3));
        this.sz = r3;
    }

    public void setUser(TLRPC.User r3) {
        this.avatarDrawable.setInfo(this.currentAccount, r3);
        this.imageReceiver.setForUserOrChat(r3, this.avatarDrawable);
    }

    public void translate(float r1, float r2) {
        this.translateX = r1;
        this.translateY = r2;
    }
}
