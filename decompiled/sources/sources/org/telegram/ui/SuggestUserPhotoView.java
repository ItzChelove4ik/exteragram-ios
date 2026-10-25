package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.Crop.CropAreaView;
import org.telegram.ui.Components.PhotoCropView;

/* loaded from: classes6.dex */
public class SuggestUserPhotoView extends View {
    Drawable arrowDrawable;
    AvatarDrawable avatarDrawable;
    View containterView;
    ImageReceiver currentPhoto;
    ImageReceiver newPhoto;
    Path path;
    PhotoCropView photoCropView;

    public SuggestUserPhotoView(Context r4) {
        super(r4);
        this.currentPhoto = new ImageReceiver(this);
        this.newPhoto = new ImageReceiver(this);
        this.avatarDrawable = new AvatarDrawable();
        this.path = new Path();
        AvatarDrawable r0 = this.avatarDrawable;
        int r1 = UserConfig.selectedAccount;
        r0.setInfo(r1, UserConfig.getInstance(r1).getCurrentUser());
        this.currentPhoto.setForUserOrChat(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser(), this.avatarDrawable);
        this.newPhoto.setForUserOrChat(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser(), this.avatarDrawable);
        Drawable r42 = ContextCompat.getDrawable(r4, R.drawable.msg_arrow_avatar);
        this.arrowDrawable = r42;
        r42.setAlpha(100);
    }

    private void setImageCoords(ImageReceiver r2, int r3, int r4) {
        r2.setImageCoords(r3 - AndroidUtilities.dp(30.0f), r4 - AndroidUtilities.dp(30.0f), AndroidUtilities.dp(60.0f), AndroidUtilities.dp(60.0f));
    }

    @Override // android.view.View
    public void draw(Canvas r11) {
        int r0 = getMeasuredWidth() >> 1;
        int r2 = getMeasuredHeight() - AndroidUtilities.dp(30.0f);
        int r5 = r0 - AndroidUtilities.dp(46.0f);
        int r4 = AndroidUtilities.dp(46.0f) + r0;
        setImageCoords(this.currentPhoto, r5, r2);
        setImageCoords(this.newPhoto, r4, r2);
        Drawable r52 = this.arrowDrawable;
        r52.setBounds(r0 - (r52.getIntrinsicWidth() / 2), r2 - (this.arrowDrawable.getIntrinsicHeight() / 2), r0 + (this.arrowDrawable.getIntrinsicWidth() / 2), (this.arrowDrawable.getIntrinsicHeight() / 2) + r2);
        this.arrowDrawable.draw(r11);
        this.path.reset();
        this.path.addCircle(r4, r2, AndroidUtilities.dp(30.0f), Path.Direction.CW);
        this.currentPhoto.draw(r11);
        if (this.containterView == null) goto L5;
        float r7 = AndroidUtilities.dp(60.0f);
        CropAreaView r8 = this.photoCropView.cropView.areaView;
        float r72 = r7 / r8.size;
        float r02 = (0.0f - this.photoCropView.getTop()) - r8.top;
        float r6 = (0.0f - this.photoCropView.getLeft()) - r8.left;
        r11.save();
        r11.clipPath(this.path);
        r11.scale(r72, r72, 0.0f, 0.0f);
        r11.translate(r6, r02);
        r11.translate((r4 - AndroidUtilities.dp(30.0f)) / r72, (r2 - AndroidUtilities.dp(30.0f)) / r72);
        PhotoViewer.getInstance().skipLastFrameDraw = true;
        this.containterView.draw(r11);
        PhotoViewer.getInstance().skipLastFrameDraw = false;
        r11.restore();
    L5:
        super.draw(r11);
        this.containterView.invalidate();
        invalidate();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.currentPhoto.onAttachedToWindow();
        this.newPhoto.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.currentPhoto.onDetachedFromWindow();
        this.newPhoto.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        this.currentPhoto.setRoundRadius(AndroidUtilities.dp(30.0f));
        this.newPhoto.setRoundRadius(AndroidUtilities.dp(30.0f));
        super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(86.0f), TLObject.FLAG_30));
    }

    public void setImages(TLObject r3, View r4, PhotoCropView r5) {
        this.avatarDrawable.setInfo(r3);
        this.currentPhoto.setForUserOrChat(r3, this.avatarDrawable);
        this.containterView = r4;
        this.photoCropView = r5;
    }
}
