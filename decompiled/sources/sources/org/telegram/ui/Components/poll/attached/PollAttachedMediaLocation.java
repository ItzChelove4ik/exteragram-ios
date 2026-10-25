package org.telegram.ui.Components.poll.attached;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ClipRoundedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.poll.PollAttachedMedia;

/* loaded from: classes7.dex */
public class PollAttachedMediaLocation extends PollAttachedMedia {
    public final TLRPC.MessageMedia media;

    /* renamed from: org.telegram.ui.Components.poll.attached.PollAttachedMediaLocation$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ PollAttachedMediaLocation this$0;
        final /* synthetic */ ImageReceiver val$imageReceiver;

        public AnonymousClass1(PollAttachedMediaLocation r1, ImageReceiver r2) {
            this.this$0 = r1;
            this.val$imageReceiver = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.val$imageReceiver.onAttachedToWindow();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.val$imageReceiver.onDetachedFromWindow();
        }
    }

    /* renamed from: org.telegram.ui.Components.poll.attached.PollAttachedMediaLocation$2, reason: invalid class name */
    public class AnonymousClass2 extends Drawable {
        final /* synthetic */ PollAttachedMediaLocation this$0;
        final /* synthetic */ ImageReceiver val$imageReceiver;
        final /* synthetic */ Drawable val$redLocationIcon;

        public AnonymousClass2(PollAttachedMediaLocation r1, ImageReceiver r2, Drawable r3) {
            this.this$0 = r1;
            this.val$imageReceiver = r2;
            this.val$redLocationIcon = r3;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r9) {
            this.val$imageReceiver.draw(r9);
            int r0 = (int) (this.val$redLocationIcon.getIntrinsicWidth() * 0.8f);
            int r1 = (int) (this.val$redLocationIcon.getIntrinsicHeight() * 0.8f);
            int r2 = (int) (this.val$imageReceiver.getImageX() + ((this.val$imageReceiver.getImageWidth() - r0) / 2.0f));
            int r3 = (int) ((this.val$imageReceiver.getImageY() + ((this.val$imageReceiver.getImageHeight() / 2.0f) - r1)) - (AndroidUtilities.dp(16.0f) * (1.0f - CubicBezierInterpolator.EASE_OUT_BACK.getInterpolation(this.val$imageReceiver.getCurrentAlpha()))));
            this.val$redLocationIcon.setAlpha((int) ((Math.min(1.0f, this.val$imageReceiver.getCurrentAlpha() * 5.0f) * 255.0f) * this.val$imageReceiver.getAlpha()));
            this.val$redLocationIcon.setBounds(r2, r3, r0 + r2, r1 + r3);
            this.val$redLocationIcon.draw(r9);
        }

        @Override // android.graphics.drawable.Drawable
        public int getAlpha() {
            return (int) (this.val$imageReceiver.getAlpha() * 255.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect r7) {
            this.val$imageReceiver.setImageCoords(r7.left + AndroidUtilities.dp(2.0f), r7.top + AndroidUtilities.dp(2.0f), (r7.right - AndroidUtilities.dp(2.0f)) - (r7.left + AndroidUtilities.dp(2.0f)), (r7.bottom - AndroidUtilities.dp(2.0f)) - (r7.top + AndroidUtilities.dp(2.0f)));
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            this.val$imageReceiver.setAlpha(r2 / 255.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public PollAttachedMediaLocation(TLRPC.MessageMedia r2) {
        this.media = r2;
        this.imageReceiver.setRoundRadius(AndroidUtilities.dp(7.0f));
        setupImageReceiver(this.imageReceiver);
    }

    private void setupImageReceiver(ImageReceiver r9) {
        TLRPC.GeoPoint r8 = this.media.geo;
        if (r8 != null) goto L6;
        r9.clearImage();
        return;
    L6:
        r9.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(r8, 38, 38, 13, Math.min(2, (int) Math.ceil(AndroidUtilities.density)))), null, null, null, null, null, 0);
    }

    public Drawable createMessagePreviewDrawable(View r9) {
        ImageReceiver r0 = new ImageReceiver(r9);
        int r1 = R.raw.map_placeholder;
        int r2 = Theme.key_chat_outLocationIcon;
        if (Theme.isCurrentThemeDark() == false) goto L5;
        int r3 = 3;
    L6:
        SvgHelper.SvgDrawable r12 = DocumentObject.getSvgThumb(r1, r2, r3 * 0.12f);
        r12.setAspectCenter(true);
        r12.setColorKey(Theme.key_chat_inLocationIcon);
        r0.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(this.media.geo, 300, 168, 15, Math.min(2, (int) Math.ceil(AndroidUtilities.density)))), null, null, null, new ClipRoundedDrawable(r12), null, 0);
        r9.addOnAttachStateChangeListener(new AnonymousClass1(this, r0));
        r0.setRoundRadius(AndroidUtilities.dp(14.0f));
        return new AnonymousClass2(this, r0, r9.getContext().getResources().getDrawable(R.drawable.map_pin).mutate());
    L5:
        r3 = 6;
        goto L6
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void draw(Canvas r3, int r4, int r5) {
        this.imageReceiver.setImageCoords(0.0f, 0.0f, r4, r5);
        this.imageReceiver.draw(r3);
    }
}
