package org.telegram.messenger.wallpaper;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.util.List;

/* loaded from: classes6.dex */
public class WallpaperGiftBitmapDrawable extends BitmapDrawable {
    public final List<WallpaperGiftPatternPosition> patternPositions;

    public WallpaperGiftBitmapDrawable(Bitmap r1, List<WallpaperGiftPatternPosition> r2) {
        super(r1);
        this.patternPositions = r2;
    }

    public static BitmapDrawable create(Bitmap r1, List<WallpaperGiftPatternPosition> r2) {
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r2 == null) goto L12;
        if (r2.isEmpty() == true) goto L12;
        return new WallpaperGiftBitmapDrawable(r1, r2);
    L12:
        return new BitmapDrawable(r1);
    }
}
