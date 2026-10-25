package org.telegram.ui.Components.poll.attached;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.PorterDuffColorFilterState;
import org.telegram.ui.Components.poll.PollAttachedMedia;

/* loaded from: classes7.dex */
public class PollAttachedMediaLink extends PollAttachedMedia implements Drawable.Callback, FactorAnimator.Target {
    private final BoolAnimator animatorHasImage;
    private final BoolAnimator animatorProgress;
    private View attachedTo;
    private final PorterDuffColorFilterState colorFilterState;
    private final Drawable drawable;
    private final Paint paint;
    private final CircularProgressDrawable progressDrawable;
    public final String url;
    private TLRPC.WebPage webPage;

    public PollAttachedMediaLink(String r9) {
        this.colorFilterState = new PorterDuffColorFilterState();
        this.paint = new Paint(1);
        CircularProgressDrawable r0 = new CircularProgressDrawable();
        this.progressDrawable = r0;
        CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorProgress = new BoolAnimator(0, this, r4, 320);
        this.animatorHasImage = new BoolAnimator(0, this, r4, 320);
        this.url = r9;
        this.imageReceiver.setRoundRadius(AndroidUtilities.dp(7.0f));
        this.drawable = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.media_link_24).mutate();
        r0.setCallback(this);
        r0.setColor(Theme.getColor(Theme.key_pollCreateIcons));
        r0.size = AndroidUtilities.dp(15.0f);
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void attach(View r1) {
        super.attach(r1);
        this.attachedTo = r1;
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void detach() {
        super.detach();
        this.attachedTo = null;
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void draw(Canvas r13, int r14, int r15) {
        float r4 = r14;
        float r5 = r15;
        this.imageReceiver.setImageCoords(0.0f, 0.0f, r4, r5);
        this.imageReceiver.draw(r13);
        this.progressDrawable.setBounds(0, 0, r14, r15);
        this.paint.setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundGray), TLObject.FLAG_30, this.animatorHasImage.getFloatValue()));
        r13.drawRoundRect(0.0f, 0.0f, r4, r5, AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), this.paint);
        this.drawable.setColorFilter(this.colorFilterState.get(ColorUtils.blendARGB(Theme.getColor(Theme.key_pollCreateIcons), -1, this.animatorHasImage.getFloatValue()), PorterDuff.Mode.SRC_IN));
        DrawableUtils.setBounds(this.drawable, r4 / 2.0f, r5 / 2.0f, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), 17);
        DrawableUtils.drawWithScale(r13, this.drawable, 1.0f - this.animatorProgress.getFloatValue());
        DrawableUtils.drawWithScale(r13, this.progressDrawable, this.animatorProgress.getFloatValue());
    }

    public TLRPC.WebPage getWebPage() {
        return this.webPage;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        View r0 = this.attachedTo;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        View r0 = this.attachedTo;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
    }

    public void setWebPage(TLRPC.WebPage r13, boolean r14, boolean r15) {
        if (r14 == false) goto L5;
    L8:
        boolean r142 = true;
    L9:
        this.animatorProgress.setValue(r142, r15);
        this.webPage = r13;
        if (r13 == null) goto L15;
        TLRPC.Photo r143 = r13.photo;
        if (r143 == null) goto L15;
        TLRPC.PhotoSize r144 = FileLoader.getClosestPhotoSizeWithSize(r143.sizes, 40);
        this.imageReceiver.setImage(ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(r13.photo.sizes, AndroidUtilities.dp(36.0f), false, r144, true), r13.photo), "48_48", ImageLocation.getForObject(r144, r13.photo), "48_48_b", 0, null, r13, 1);
        this.animatorHasImage.setValue(true, r15);
        return;
    L15:
        this.animatorHasImage.setValue(false, r15);
        this.imageReceiver.clearImage();
        return;
    L5:
        if ((r13 instanceof TLRPC.TL_webPagePending) == true) goto L8;
        r142 = false;
        goto L9
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
    }
}
