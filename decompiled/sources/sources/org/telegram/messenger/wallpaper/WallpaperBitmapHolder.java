package org.telegram.messenger.wallpaper;

import android.graphics.Bitmap;
import java.util.List;

/* loaded from: classes6.dex */
public class WallpaperBitmapHolder {
    public final Bitmap bitmap;
    public final List<WallpaperGiftPatternPosition> giftPatternPositions;
    public final int mode;

    public WallpaperBitmapHolder(Bitmap r2, int r3) {
        this(r2, r3, null);
    }

    public WallpaperBitmapHolder(Bitmap r1, int r2, List<WallpaperGiftPatternPosition> r3) {
        this.giftPatternPositions = r3;
        this.bitmap = r1;
        this.mode = r2;
    }
}
