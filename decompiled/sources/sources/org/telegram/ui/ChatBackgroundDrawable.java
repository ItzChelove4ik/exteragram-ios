package org.telegram.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.wallpaper.WallpaperBitmapHolder;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.MotionBackgroundDrawable;

/* loaded from: classes3.dex */
public class ChatBackgroundDrawable extends Drawable {
    int alpha;
    private boolean attached;
    private final ArrayList<View> attachedViews;
    private boolean colorFilterSetted;
    float dimAmount;
    ImageReceiver imageReceiver;
    boolean isPattern;
    MotionBackgroundDrawable motionBackgroundDrawable;
    WeakReference<View> parent;
    private final boolean themeIsDark;
    final TLRPC.WallPaper wallpaper;

    /* renamed from: org.telegram.ui.ChatBackgroundDrawable$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ImageReceiver {
        final /* synthetic */ ChatBackgroundDrawable this$0;

        public AnonymousClass1(ChatBackgroundDrawable r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.ImageReceiver, org.telegram.ui.Components.AnimatedEmojiSpan.InvalidateHolder
        public void invalidate() {
            WeakReference<View> r0 = this.this$0.parent;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.get() == null) goto L9;
            this.this$0.parent.get().invalidate();
            return;
        }
    }

    /* renamed from: $r8$lambda$7-3PYOrGzQTEuHkfepAmdbypBb8, reason: not valid java name */
    public static /* synthetic */ void m8244$r8$lambda$73PYOrGzQTEuHkfepAmdbypBb8(ChatBackgroundDrawable r0, TLRPC.WallPaper r1, WallpaperBitmapHolder r2) {
        r0.lambda$new$0(r1, r2);
    }

    public ChatBackgroundDrawable(TLRPC.WallPaper r8, boolean r9, boolean r10) {
        this.alpha = 255;
        this.imageReceiver = new AnonymousClass1(this);
        this.attachedViews = new ArrayList();
        this.imageReceiver.setInvalidateAll(true);
        boolean r0 = r8.pattern;
        this.isPattern = r0;
        this.wallpaper = r8;
        this.themeIsDark = r9;
        if (r9 == true) goto L5;
    L12:
        if (r0 == false) goto L14;
    L15:
        TLRPC.WallPaperSettings r92 = r8.settings;
        if (r92 != null) goto L18;
    L23:
        Point r93 = AndroidUtilities.displaySize;
        int r94 = Math.min(r93.x, r93.y);
        Point r02 = AndroidUtilities.displaySize;
        int r03 = Math.max(r02.x, r02.y);
        if (r10 == false) goto L26;
        String r95 = "150_150_wallpaper";
    L27:
        String r2 = (r95 + r8.id) + hash(r8.settings);
        Drawable r3 = createThumb(r8);
        String r96 = r8.uploadingImage;
        if (r96 == null) goto L31;
        this.imageReceiver.setImage(ImageLocation.getForPath(r96), r2, r3, null, r8, 1);
        return;
    L31:
        TLRPC.Document r82 = r8.document;
        ImageReceiver r04 = this.imageReceiver;
        if (r82 == null) goto L35;
        r04.setImage(ImageLocation.getForDocument(r82), r2, r3, null, r8, 1);
        return;
    L35:
        r04.setImageBitmap(r3);
        return;
    L26:
        r95 = ((int) (r94 / AndroidUtilities.density)) + "_" + ((int) (r03 / AndroidUtilities.density)) + "_wallpaper";
        goto L27
    L18:
        if (r92.second_background_color == 0) goto L23;
        if (r92.third_background_color == 0) goto L23;
        MotionBackgroundDrawable r97 = new MotionBackgroundDrawable();
        this.motionBackgroundDrawable = r97;
        TLRPC.WallPaperSettings r102 = r8.settings;
        r97.setColors(r102.background_color, r102.second_background_color, r102.third_background_color, r102.fourth_background_color);
        EmojiThemes.loadWallpaperImage(UserConfig.selectedAccount, r8.id, r8, new ChatBackgroundDrawable$$ExternalSyntheticLambda0(this, r8));
        return;
    L14:
        if (r8.document != null) goto L23;
    L5:
        if (r8.document == null) goto L7;
    L8:
        if (r0 == true) goto L12;
        if (r8.settings == null) goto L12;
        this.dimAmount = r9.intensity / 100.0f;
        goto L12
    L7:
        if (r8.uploadingImage == null) goto L12;
        goto L8
    }

    private static Drawable bitmapDrawableOf(Drawable r4) {
        Bitmap r0 = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888);
        Canvas r2 = new Canvas(r0);
        r4.setBounds(0, 0, 20, 20);
        r4.draw(r2);
        return new BitmapDrawable(r0);
    }

    public static Drawable createThumb(TLRPC.WallPaper r6) {
        Drawable r0 = r6.thumbDrawable;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (r6.stripedThumb == null) goto L10;
        return new BitmapDrawable(r6.stripedThumb);
    L10:
        if (r6.pattern == true) goto L12;
    L15:
        int r2 = 0;
        if (r6.document == null) goto L24;
        Drawable r02 = null;
    L19:
        if (r2 >= r6.document.thumbs.size()) goto L41;
        if ((r6.document.thumbs.get(r2) instanceof TLRPC.TL_photoStrippedSize) == false) goto L23;
        r02 = new BitmapDrawable(ImageLoader.getStrippedPhotoBitmap(r6.document.thumbs.get(r2).bytes, "b"));
    L23:
        r2 = r2 + 1;
    L41:
        r6.thumbDrawable = r02;
        return r02;
    L24:
        TLRPC.WallPaperSettings r03 = r6.settings;
        if (r03 != null) goto L27;
    L40:
        r02 = bitmapDrawableOf(new ColorDrawable(-16777216));
        goto L41
    L27:
        if (r03.intensity < 0) goto L40;
        if (r03.second_background_color != 0) goto L33;
        r02 = bitmapDrawableOf(new ColorDrawable(ColorUtils.setAlphaComponent(r6.settings.background_color, 255)));
        goto L41
    L33:
        if (r03.third_background_color != 0) goto L35;
        int r04 = ColorUtils.setAlphaComponent(r03.background_color, 255);
        int r1 = ColorUtils.setAlphaComponent(r6.settings.second_background_color, 255);
        r02 = bitmapDrawableOf(new GradientDrawable(BackgroundGradientDrawable.getGradientOrientation(r6.settings.rotation), new int[]{r04, r1}));
        goto L41
    L35:
        int r05 = ColorUtils.setAlphaComponent(r03.background_color, 255);
        int r12 = ColorUtils.setAlphaComponent(r6.settings.second_background_color, 255);
        int r4 = ColorUtils.setAlphaComponent(r6.settings.third_background_color, 255);
        int r5 = r6.settings.fourth_background_color;
        if (r5 == 0) goto L39;
        r2 = ColorUtils.setAlphaComponent(r5, 255);
    L39:
        MotionBackgroundDrawable r3 = new MotionBackgroundDrawable();
        r3.setColors(r05, r12, r4, r2);
        r02 = new BitmapDrawable(r3.getBitmap());
        goto L41
    L12:
        if (r6.settings != null) goto L15;
        return new ColorDrawable(-16777216);
    }

    public static Drawable getOrCreate(Drawable r4, TLRPC.WallPaper r5, boolean r6) {
        if ((r4 instanceof ChatBackgroundDrawable) == false) goto L33;
        ChatBackgroundDrawable r42 = (ChatBackgroundDrawable) r4;
        String r0 = r5.uploadingImage;
        if (r0 == null) goto L18;
        if (r0.equals(r42.wallpaper.uploadingImage) == false) goto L33;
        TLRPC.WallPaperSettings r02 = r5.settings;
        if (r02 != null) goto L11;
    L31:
        return r42;
    L11:
        if (r42.wallpaper.settings == null) goto L31;
        if (r02.intensity <= 0) goto L31;
        if (r42.themeIsDark != r6) goto L33;
    L18:
        if (r5.id != r42.wallpaper.id) goto L33;
        if (TextUtils.equals(hash(r5.settings), hash(r42.wallpaper.settings)) == false) goto L33;
        if (r5.document == null) goto L31;
        if (r5.pattern == true) goto L31;
        TLRPC.WallPaperSettings r03 = r5.settings;
        if (r03 == null) goto L31;
        if (r03.intensity <= 0) goto L31;
        if (r42.themeIsDark == r6) goto L31;
    L33:
        return new ChatBackgroundDrawable(r5, r6, false);
    }

    public static String hash(TLRPC.WallPaperSettings r8) {
        if (r8 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return String.valueOf(Objects.hash(new Object[]{Boolean.valueOf(r8.blur), Boolean.valueOf(r8.motion), Integer.valueOf(r8.intensity), Integer.valueOf(r8.background_color), Integer.valueOf(r8.second_background_color), Integer.valueOf(r8.third_background_color), Integer.valueOf(r8.fourth_background_color)}));
    }

    private boolean isAttached() {
        if (this.attachedViews.size() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$new$0(TLRPC.WallPaper r2, WallpaperBitmapHolder r3) {
        this.motionBackgroundDrawable.setPatternBitmap(r2.settings.intensity, r3.bitmap);
        WeakReference<View> r22 = this.parent;
        if (r22 != null) goto L5;
        return;
    L5:
        if (r22.get() == null) goto L9;
        this.parent.get().invalidate();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r7) {
        MotionBackgroundDrawable r0 = this.motionBackgroundDrawable;
        if (r0 == null) goto L6;
        r0.setBounds(getBounds());
        this.motionBackgroundDrawable.setAlpha(this.alpha);
        this.motionBackgroundDrawable.draw(r7);
        return;
    L6:
        boolean r2 = true;
        if (this.imageReceiver.hasImageLoaded() == true) goto L9;
    L15:
        this.imageReceiver.setImageCoords(getBounds());
        this.imageReceiver.setAlpha(this.alpha / 255.0f);
        this.imageReceiver.draw(r7);
        if (r2 == false) goto L21;
        float r6 = this.dimAmount;
        if (r6 == 0.0f) goto L22;
        r7.drawColor(ColorUtils.setAlphaComponent(-16777216, (int) (r6 * 255.0f)));
        return;
    L22:
        return;
    L21:
        return;
    L9:
        if (this.imageReceiver.getCurrentAlpha() != 1.0f) goto L15;
        if (this.colorFilterSetted == true) goto L14;
        this.colorFilterSetted = true;
        this.imageReceiver.setColorFilter(new PorterDuffColorFilter(ColorUtils.setAlphaComponent(-16777216, (int) (this.dimAmount * 255.0f)), PorterDuff.Mode.DARKEN));
    L14:
        r2 = false;
        goto L15
    }

    public float getDimAmount() {
        if (this.motionBackgroundDrawable == null) goto L5;
        return 0.0f;
    L5:
        return this.dimAmount;
    }

    public Drawable getDrawable(boolean r2) {
        MotionBackgroundDrawable r0 = this.motionBackgroundDrawable;
        if (r0 == null) goto L5;
        return r0;
    L5:
        if (r2 == true) goto L7;
    L10:
        Drawable r22 = this.imageReceiver.getThumb();
        ImageReceiver r02 = this.imageReceiver;
        if (r22 != null) goto L13;
        Drawable r23 = r02.getDrawable();
        ImageReceiver r1 = this.imageReceiver;
        if (r23 == null) goto L19;
        return r1.getDrawable();
    L19:
        return r1.getStaticThumb();
    L13:
        return r02.getThumb();
    L7:
        if (this.imageReceiver.getStaticThumb() == null) goto L10;
        return this.imageReceiver.getStaticThumb();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public void onAttachedToWindow(View r2) {
        if (this.attachedViews.contains(r2) == true) goto L6;
        this.attachedViews.add(r2);
    L6:
        if (isAttached() == false) goto L11;
        if (this.attached == true) goto L11;
        this.attached = true;
        this.imageReceiver.onAttachedToWindow();
    L15:
        MotionBackgroundDrawable r1 = this.motionBackgroundDrawable;
        if (r1 == null) goto L19;
        r1.onAttachedToWindow();
        return;
    L19:
        return;
    L11:
        if (isAttached() == true) goto L15;
        if (this.attached == false) goto L15;
        this.attached = false;
        this.imageReceiver.onDetachedFromWindow();
        goto L15
    }

    public void onDetachedFromWindow(View r2) {
        this.attachedViews.remove(r2);
        if (isAttached() == false) goto L8;
        if (this.attached == true) goto L8;
        this.attached = true;
        this.imageReceiver.onAttachedToWindow();
    L12:
        MotionBackgroundDrawable r1 = this.motionBackgroundDrawable;
        if (r1 == null) goto L16;
        r1.onDetachedFromWindow();
        return;
    L16:
        return;
    L8:
        if (isAttached() == true) goto L12;
        if (this.attached == false) goto L12;
        this.attached = false;
        this.imageReceiver.onDetachedFromWindow();
        goto L12
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        if (this.alpha == r2) goto L6;
        this.alpha = r2;
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setParent(View r2) {
        this.parent = new WeakReference(r2);
        MotionBackgroundDrawable r1 = this.motionBackgroundDrawable;
        if (r1 == null) goto L6;
        r1.setParentView(r2);
        return;
    }
}
