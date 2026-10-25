package org.telegram.ui.Components.chat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ChatBackgroundDrawable;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceWrapped;
import org.telegram.ui.Components.blur3.utils.BitmapMemoizedMetadata;

/* loaded from: classes7.dex */
public class WallpaperBitmapProvider {
    private static final Rect tmpRect = null;
    private final BitmapMemoizedMetadata<Bitmap> blurredFromBitmap;
    private final BitmapMemoizedMetadata<Integer> navbarColorFromBitmap;
    private final BlurredBackgroundSourceBitmap sourceBitmap;
    private final BlurredBackgroundSourceColor sourceColor;
    private final BitmapMemoizedMetadata<Integer> statusBarColorFromBitmap;

    public static /* synthetic */ Bitmap $r8$lambda$QXHEXiCdGIr8VLJfjASkNpQrsr8(Bitmap r0) {
        return blurBitmap(r0);
    }

    public static /* synthetic */ int $r8$lambda$Tz0RM45G66WEm9KqmizyeQmpwh0(Bitmap r0) {
        return averageTopColor(r0);
    }

    /* renamed from: $r8$lambda$Uz-dJsnFzn0w0GcC1rUEi1BDHmI, reason: not valid java name */
    public static /* synthetic */ int m13694$r8$lambda$UzdJsnFzn0w0GcC1rUEi1BDHmI(Bitmap r0) {
        return averageBottomColor(r0);
    }

    static {
        tmpRect = new Rect();
    }

    public WallpaperBitmapProvider() {
        this.sourceColor = new BlurredBackgroundSourceColor();
        this.sourceBitmap = new BlurredBackgroundSourceBitmap();
        this.blurredFromBitmap = new BitmapMemoizedMetadata(new WallpaperBitmapProvider$$ExternalSyntheticLambda0());
        this.navbarColorFromBitmap = new BitmapMemoizedMetadata(new WallpaperBitmapProvider$$ExternalSyntheticLambda1());
        this.statusBarColorFromBitmap = new BitmapMemoizedMetadata(new WallpaperBitmapProvider$$ExternalSyntheticLambda2());
    }

    private static int averageBottomColor(Bitmap r4) {
        if (r4 != null) goto L5;
    L9:
        return 0;
    L5:
        if (r4.isRecycled() == true) goto L9;
        int r1 = r4.getHeight();
        int r3 = (r1 * 9) / 10;
        return Utilities.averageBitmapColor(r4, 0, r3, r4.getWidth(), r1);
    }

    private static int averageTopColor(Bitmap r3) {
        if (r3 != null) goto L5;
    L9:
        return 0;
    L5:
        if (r3.isRecycled() == true) goto L9;
        int r1 = r3.getHeight();
        return Utilities.averageBitmapColor(r3, 0, 0, r3.getWidth(), r1 / 10);
    }

    private static Bitmap blurBitmap(Bitmap r3) {
        if (r3 != null) goto L4;
        return null;
    L4:
        if (r3.isRecycled() == true) goto L10;
        Bitmap r32 = Utilities.stackBlurBitmapWithScaleFactor(r3, Math.max(r3.getWidth() / 90.0f, r3.getHeight() / 120.0f));
        r32.setHasAlpha(false);
        return r32;
    L10:
        return null;
    }

    public int getNavigationBarColor(BlurredBackgroundSource r2) {
        if ((r2 instanceof BlurredBackgroundSourceColor) == false) goto L7;
        return ((BlurredBackgroundSourceColor) r2).getColor();
    L7:
        if ((r2 instanceof BlurredBackgroundSourceBitmap) == false) goto L11;
        Bitmap r22 = ((BlurredBackgroundSourceBitmap) r2).getBitmap();
        return this.navbarColorFromBitmap.get(r22).intValue();
    L11:
        if ((r2 instanceof BlurredBackgroundSourceWrapped) == true) goto L13;
        return 0;
    L13:
        return getNavigationBarColor(((BlurredBackgroundSourceWrapped) r2).getSource());
    }

    public int getStatusBarColor(BlurredBackgroundSource r2) {
        if ((r2 instanceof BlurredBackgroundSourceColor) == false) goto L7;
        return ((BlurredBackgroundSourceColor) r2).getColor();
    L7:
        if ((r2 instanceof BlurredBackgroundSourceBitmap) == false) goto L11;
        Bitmap r22 = ((BlurredBackgroundSourceBitmap) r2).getBitmap();
        return this.statusBarColorFromBitmap.get(r22).intValue();
    L11:
        if ((r2 instanceof BlurredBackgroundSourceWrapped) == true) goto L13;
        return 0;
    L13:
        return getStatusBarColor(((BlurredBackgroundSourceWrapped) r2).getSource());
    }

    public BlurredBackgroundSource updateSourceFromBackgroundViewDrawable(Drawable r7) {
        if ((r7 instanceof ColorDrawable) == false) goto L7;
        int r72 = ((ColorDrawable) r7).getColor();
        this.sourceColor.setColor(r72);
        return this.sourceColor;
    L7:
        if ((r7 instanceof MotionBackgroundDrawable) == false) goto L15;
        MotionBackgroundDrawable r73 = (MotionBackgroundDrawable) r7;
        if (r73.getIntensity() >= 0) goto L12;
        this.sourceColor.setColor(-16777216);
        return this.sourceColor;
    L12:
        this.sourceBitmap.setBitmap(r73.getBitmap());
        return this.sourceBitmap;
    L15:
        if ((r7 instanceof BitmapDrawable) == false) goto L19;
        Bitmap r74 = this.blurredFromBitmap.get(((BitmapDrawable) r7).getBitmap());
        this.sourceBitmap.setBitmap(r74);
        return this.sourceBitmap;
    L19:
        if ((r7 instanceof ChatBackgroundDrawable) == true) goto L21;
        if (r7 == null) goto L25;
        Canvas r0 = this.sourceBitmap.beginRecording(120, 160);
        Rect r4 = tmpRect;
        r4.set(r7.getBounds());
        r7.setBounds(0, 0, 120, 160);
        r7.draw(r0);
        r7.setBounds(r4);
        this.sourceBitmap.endRecording();
        BlurredBackgroundSourceBitmap r75 = this.sourceBitmap;
        r75.setBitmap(this.blurredFromBitmap.get(r75.getBitmap()));
    L25:
        return this.sourceBitmap;
    L21:
        return updateSourceFromBackgroundViewDrawable(((ChatBackgroundDrawable) r7).getDrawable(false));
    }
}
