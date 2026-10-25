package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class EditCoverButton extends View {
    private final Drawable arrowDrawable;
    private BlurredBackgroundDrawable blurredBackgroundDrawable;
    private final Rect bounds;
    private final RectF imageBounds;
    private final ImageReceiver imageReceiver;
    private final Text text;

    /* renamed from: $r8$lambda$98fJkw1e-_a1FSFHekpV9kiWh5E, reason: not valid java name */
    public static /* synthetic */ void m10134$r8$lambda$98fJkw1e_a1FSFHekpV9kiWh5E(EditCoverButton r0, Bitmap r1) {
        r0.lambda$setImage$0(r1);
    }

    /* renamed from: $r8$lambda$MevFKXIybHtw-EjO74NuU2mWp_g, reason: not valid java name */
    public static /* synthetic */ void m10135$r8$lambda$MevFKXIybHtwEjO74NuU2mWp_g(EditCoverButton r0, String r1) {
        r0.lambda$setImage$1(r1);
    }

    public EditCoverButton(Context r4, CharSequence r5, boolean r6) {
        super(r4);
        this.bounds = new Rect();
        this.imageBounds = new RectF();
        ImageReceiver r0 = new ImageReceiver(this);
        this.imageReceiver = r0;
        r0.setRoundRadius(AndroidUtilities.dp(22.66f));
        this.text = new Text(r5, 14.0f, AndroidUtilities.bold());
        if (r6 == false) goto L6;
        Drawable r42 = r4.getResources().getDrawable(R.drawable.arrow_newchat).mutate();
        this.arrowDrawable = r42;
        r42.setColorFilter(new PorterDuffColorFilter(-1711276033, PorterDuff.Mode.SRC_IN));
        return;
    L6:
        this.arrowDrawable = null;
    }

    private /* synthetic */ void lambda$setImage$0(Bitmap r1) {
        setImage(r1);
    }

    private /* synthetic */ void lambda$setImage$1(String r7) {
        Bitmap r72 = BitmapFactory.decodeFile(r7);
        Canvas r1 = new Canvas(Bitmap.createBitmap(AndroidUtilities.dp(26.0f), AndroidUtilities.dp(26.0f), Bitmap.Config.ARGB_8888));
        Paint r2 = new Paint(3);
        r1.translate(r0.getWidth() / 2.0f, r0.getHeight() / 2.0f);
        float r0 = Math.max(r0.getWidth() / r72.getWidth(), r0.getHeight() / r72.getHeight());
        r1.scale(r0, r0);
        r1.drawBitmap(r72, (-r72.getWidth()) / 2.0f, (-r72.getHeight()) / 2.0f, r2);
        AndroidUtilities.runOnUIThread(new EditCoverButton$$ExternalSyntheticLambda1(this, r72));
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent r4) {
        if (this.bounds.contains((int) r4.getX(), (int) r4.getY()) == true) goto L9;
        if (r4.getAction() != 0) goto L9;
        return false;
    L9:
        return super.dispatchTouchEvent(r4);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.imageReceiver.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.imageReceiver.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        boolean r0 = this.imageReceiver.hasBitmapImage();
        if (r0 == false) goto L6;
        float r1 = 30.33f;
    L7:
        int r2 = (((int) Math.ceil(this.text.getCurrentWidth())) + AndroidUtilities.dp(r1)) + AndroidUtilities.dp(19.0f);
        int r3 = AndroidUtilities.dp(24.0f);
        int r4 = (getWidth() - r2) / 2;
        int r5 = getHeight() / 2;
        int r6 = r5 - (r3 / 2);
        int r22 = r2 + r4;
        this.bounds.set(r4, r6, r22, r3 + r6);
        this.bounds.inset(-AndroidUtilities.dp(4.0f), -AndroidUtilities.dp(4.0f));
        BlurredBackgroundDrawable r32 = this.blurredBackgroundDrawable;
        if (r32 == null) goto L10;
        r32.setBounds(this.bounds);
        this.blurredBackgroundDrawable.draw(r13);
    L10:
        if (r0 == false) goto L12;
        float r62 = r5;
        this.imageBounds.set(AndroidUtilities.dp(0.66f) + r4, r62 - (AndroidUtilities.dp(22.66f) / 2.0f), AndroidUtilities.dp(23.32f) + r4, r62 + (AndroidUtilities.dp(22.66f) / 2.0f));
        this.imageReceiver.setImageCoords(this.imageBounds);
        this.imageReceiver.draw(r13);
    L12:
        this.text.draw(r13, r4 + r1, r5, -1, 1.0f);
        this.arrowDrawable.setBounds(r22 - AndroidUtilities.dp(17.0f), r5 - AndroidUtilities.dp(6.0f), r22 - AndroidUtilities.dp(5.0f), r5 + AndroidUtilities.dp(6.0f));
        this.arrowDrawable.draw(r13);
        return;
    L6:
        r1 = 11.33f;
        goto L7
    }

    public void setBlurredBackgroundDrawable(BlurredBackgroundDrawable r2) {
        this.blurredBackgroundDrawable = r2.setPadding(AndroidUtilities.dp(4.0f)).setRadius(AndroidUtilities.dp(11.0f));
    }

    public void setImage(Bitmap r2) {
        this.imageReceiver.setImageBitmap(r2);
        invalidate();
    }

    public void setImage(String r3) {
        if (r3 != null) goto L5;
        setImage(null);
        return;
    L5:
        Utilities.globalQueue.postRunnable(new EditCoverButton$$ExternalSyntheticLambda0(this, r3));
    }

    public void setImage(TLRPC.Photo r13, Object r14) {
        if (r13 != null) goto L6;
        setImage(null);
        return;
    L6:
        TLRPC.PhotoSize r0 = FileLoader.getClosestPhotoSizeWithSize(r13.sizes, AndroidUtilities.dp(48.0f), false, null, true);
        TLRPC.PhotoSize r1 = FileLoader.getClosestPhotoSizeWithSize(r13.sizes, AndroidUtilities.dp(24.0f), false, r0, false);
        this.imageReceiver.setImage(ImageLocation.getForPhoto(r0, r13), "24_24", ImageLocation.getForPhoto(r1, r13), "24_24", 0, null, r14, 0);
    }
}
