package org.telegram.ui.iv;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import com.android.dx.rop.code.RegisterSpec;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;

/* loaded from: classes7.dex */
public class RichMediaItem {
    private static ColorMatrixColorFilter fancyBlurFilter;
    private boolean attached;
    private final ImageReceiver blurImageReceiver;
    private Bitmap blurSource;
    private final ImageReceiver imageReceiver;
    private String loadedKey;
    private MediaUploadState media;
    private final View parent;
    private final RadialProgress2 radialProgress;

    public RichMediaItem(View r3, Theme.ResourcesProvider r4) {
        this.parent = r3;
        this.imageReceiver = new ImageReceiver(r3);
        this.blurImageReceiver = new ImageReceiver(r3);
        RadialProgress2 r0 = new RadialProgress2(r3, r4);
        this.radialProgress = r0;
        r0.setProgressColor(-1);
        r0.setColors(1711276032, 2130706432, -1, -2500135);
        r0.setIcon(3, false, false);
    }

    private void applyImage() {
        if (this.media != null) goto L6;
        this.loadedKey = null;
        this.imageReceiver.setImageBitmap(null);
        return;
    L6:
        int r1 = AndroidUtilities.displaySize.x;
        String r7 = r1 + "_" + r1;
        if ((imageKey() + "@" + r7).equals(this.loadedKey) == false) goto L10;
        return;
    L10:
        if (this.media.localThumbBitmap == null) goto L12;
        BitmapDrawable r12 = new BitmapDrawable(this.parent.getResources(), this.media.localThumbBitmap);
    L13:
        MediaUploadState r13 = this.media;
        if (r13.isVideo == false) goto L28;
        if (r13.localPath == null) goto L20;
        this.imageReceiver.setOrientation(0, 0, false);
        this.imageReceiver.setImage(ImageLocation.getForVideoPath(this.media.localPath), ImageLoader.AUTOPLAY_FILTER, null, r7, null, r7, r12, 0, null, null, 0);
        return;
    L20:
        if (r13.isReady() == false) goto L25;
        TLRPC.Document r14 = this.media.document;
        if (r14 == null) goto L25;
        TLRPC.PhotoSize r15 = pickNonStrippedClosest(r14.thumbs, AndroidUtilities.getPhotoSize());
        TLRPC.PhotoSize r2 = pickStripped(this.media.document.thumbs);
        this.imageReceiver.setOrientation(0, 0, false);
        this.imageReceiver.setImage(ImageLocation.getForDocument(this.media.document), ImageLoader.AUTOPLAY_FILTER, ImageLocation.getForDocument(r15, this.media.document), r7, ImageLocation.getForDocument(r2, this.media.document), r7, r12, 0, null, this.media.document, 0);
        return;
    L25:
        this.imageReceiver.setImageBitmap(null);
        return;
    L28:
        if (r13.localPath == null) goto L32;
        this.imageReceiver.setOrientation(r13.orientation, r13.invert, true);
        this.imageReceiver.setImage(ImageLocation.getForPath(this.media.localPath), r7, null, null, null, 0);
        return;
    L32:
        if (r13.isReady() == false) goto L37;
        TLRPC.Photo r16 = this.media.photo;
        if (r16 == null) goto L37;
        TLRPC.PhotoSize r17 = FileLoader.getClosestPhotoSizeWithSize(r16.sizes, AndroidUtilities.getPhotoSize());
        TLRPC.PhotoSize r22 = FileLoader.getClosestPhotoSizeWithSize(this.media.photo.sizes, 100);
        this.imageReceiver.setOrientation(0, 0, false);
        this.imageReceiver.setImage(ImageLocation.getForPhoto(r17, this.media.photo), r7, ImageLocation.getForPhoto(r22, this.media.photo), r7, null, 0, null, this.media.photo, 0);
        return;
    L37:
        this.imageReceiver.setImageBitmap(null);
        return;
    L12:
        r12 = null;
        goto L13
    }

    private void drawProgress(Canvas r6, RectF r7) {
        MediaUploadState r0 = this.media;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isPending() == false) goto L9;
        int r02 = AndroidUtilities.dp(48.0f);
        int r1 = Math.round(r7.centerX());
        int r72 = Math.round(r7.centerY());
        int r03 = r02 / 2;
        this.radialProgress.setProgressRect(r1 - r03, r72 - r03, r1 + r03, r72 + r03);
        this.radialProgress.setProgress(this.media.progress, true);
        this.radialProgress.draw(r6);
        return;
    }

    private boolean ensureBlur() {
        if (hasImage() == true) goto L5;
        return false;
    L5:
        Bitmap r0 = this.imageReceiver.getBitmap();
        if (r0 != null) goto L8;
    L27:
        return false;
    L8:
        if (r0.isRecycled() == true) goto L27;
        if (this.blurImageReceiver.getBitmap() == null) goto L16;
        if (this.imageReceiver.getAnimation() == null) goto L16;
    L24:
        if (this.blurImageReceiver.getBitmap() == null) goto L27;
        return true;
    L16:
        if (r0 == this.blurSource) goto L18;
    L19:
        this.blurSource = r0;
        this.blurImageReceiver.setImageBitmap(Utilities.stackBlurBitmapMax(r0, false));
        if (fancyBlurFilter != null) goto L22;
        ColorMatrix r02 = new ColorMatrix();
        AndroidUtilities.multiplyBrightnessColorMatrix(r02, 0.9f);
        AndroidUtilities.adjustSaturationColorMatrix(r02, 0.6f);
        fancyBlurFilter = new ColorMatrixColorFilter(r02);
    L22:
        this.blurImageReceiver.setColorFilter(fancyBlurFilter);
        goto L24
    L18:
        if (this.blurImageReceiver.getBitmap() != null) goto L24;
        goto L19
    }

    private String imageKey() {
        MediaUploadState r0 = this.media;
        if (r0 != null) goto L7;
        return "null";
    L7:
        if (r0.isVideo == false) goto L10;
        String r1 = RegisterSpec.PREFIX;
    L14:
        if (r0.localPath != null) goto L16;
        long r2 = 0;
        if (r0.isReady() == false) goto L26;
        MediaUploadState r02 = this.media;
        TLRPC.Document r4 = r02.document;
        if (r4 == null) goto L22;
        r2 = r4.id;
        goto L26
    L22:
        TLRPC.Photo r03 = r02.photo;
        if (r03 == null) goto L26;
        r2 = r03.id;
    L26:
        return r1 + ":" + this.media.state + ":" + r2;
    L16:
        return r1 + ":local:" + this.media.localPath;
    L10:
        if (r0.isAudio == false) goto L12;
        r1 = "a";
        goto L14
    L12:
        r1 = "p";
        goto L14
    }

    private boolean isLocalRotated90() {
        MediaUploadState r0 = this.media;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isVideo == false) goto L7;
        return false;
    L7:
        if (r0.isReady() == true) goto L18;
        int r2 = this.media.orientation;
        if (r2 != 90) goto L11;
        return true;
    L11:
        if (r2 != 270) goto L19;
        return true;
    L19:
        return false;
    L18:
        return false;
    }

    private static TLRPC.PhotoSize pickNonStrippedClosest(ArrayList<TLRPC.PhotoSize> r6, int r7) {
        TLRPC.PhotoSize r0 = null;
        if (r6 != null) goto L5;
        return null;
    L5:
        int r1 = Integer.MAX_VALUE;
        int r2 = 0;
    L7:
        if (r2 >= r6.size()) goto L17;
        TLRPC.PhotoSize r3 = r6.get(r2);
        if ((r3 instanceof TLRPC.TL_photoStrippedSize) == true) goto L16;
        if ((r3 instanceof TLRPC.TL_photoPathSize) == true) goto L16;
        int r4 = Math.abs(Math.max(r3.w, r3.h) - r7);
        if (r4 >= r1) goto L16;
        r0 = r3;
        r1 = r4;
    L16:
        r2 = r2 + 1;
        goto L7
    L17:
        return r0;
    }

    private static TLRPC.PhotoSize pickStripped(ArrayList<TLRPC.PhotoSize> r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r3.size()) goto L13;
        if ((r3.get(r1) instanceof TLRPC.TL_photoStrippedSize) == true) goto L11;
        r1 = r1 + 1;
        goto L7
    L11:
        return r3.get(r1);
    L13:
        return null;
    }

    public void attach() {
        this.attached = true;
        this.imageReceiver.onAttachedToWindow();
        this.blurImageReceiver.onAttachedToWindow();
        this.loadedKey = null;
        applyImage();
    }

    public void detach() {
        this.attached = false;
        this.imageReceiver.onDetachedFromWindow();
        this.blurImageReceiver.onDetachedFromWindow();
        this.blurSource = null;
    }

    public void draw(Canvas r6, RectF r7) {
        this.imageReceiver.setImageCoords(Math.round(r7.left), Math.round(r7.top), Math.round(r7.width()), Math.round(r7.height()));
        if (hasImage() == false) goto L5;
        this.imageReceiver.draw(r6);
    L5:
        drawProgress(r6, r7);
    }

    public void drawBlurBackground(Canvas r2, RectF r3) {
        if (ensureBlur() == true) goto L5;
        return;
    L5:
        this.blurImageReceiver.setImageCoords(r3);
        this.blurImageReceiver.setAlpha(this.imageReceiver.getCurrentAlpha());
        this.blurImageReceiver.draw(r2);
    }

    public void drawSpoiler(Canvas r8, RectF r9, SpoilerEffect2 r10, View r11) {
        r8.save();
        r8.clipRect(r9);
        if (ensureBlur() == false) goto L5;
        this.blurImageReceiver.setImageCoords(r9);
        this.blurImageReceiver.setAlpha(this.imageReceiver.getCurrentAlpha());
        this.blurImageReceiver.draw(r8);
    L5:
        if (r10 == null) goto L7;
        r8.translate(r9.left, r9.top);
        Canvas r2 = r8;
        r10.draw(r2, r11, Math.round(r9.width()), Math.round(r9.height()), this.imageReceiver.getCurrentAlpha());
    L8:
        r2.restore();
        return;
    L7:
        r2 = r8;
        goto L8
    }

    public int getHeight() {
        if (this.media != null) goto L6;
        return 0;
    L6:
        boolean r0 = isLocalRotated90();
        MediaUploadState r1 = this.media;
        if (r0 == false) goto L11;
        return r1.width;
    L11:
        return r1.height;
    }

    public MediaUploadState getMedia() {
        return this.media;
    }

    public int getWidth() {
        if (this.media != null) goto L6;
        return 0;
    L6:
        boolean r0 = isLocalRotated90();
        MediaUploadState r1 = this.media;
        if (r0 == false) goto L11;
        return r1.height;
    L11:
        return r1.width;
    }

    public boolean hasImage() {
        MediaUploadState r1 = this.media;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.localPath == null) goto L7;
        return true;
    L7:
        if (r1.isReady() == false) goto L13;
        return true;
    L13:
        return false;
    }

    public void setMedia(MediaUploadState r1) {
        this.media = r1;
        applyImage();
    }

    public void setRoundRadius(int r2, int r3, int r4, int r5) {
        this.imageReceiver.setRoundRadius(r2, r3, r4, r5);
        this.blurImageReceiver.setRoundRadius(r2, r3, r4, r5);
    }
}
