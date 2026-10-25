package org.telegram.messenger;

import android.graphics.drawable.Drawable;
import org.telegram.messenger.ImageLoader;

/* loaded from: classes.dex */
public final /* synthetic */ class ImageLoader$CacheOutTask$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageLoader.CacheOutTask f$0;
    public final /* synthetic */ Drawable f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ ImageLoader$CacheOutTask$$ExternalSyntheticLambda0(ImageLoader.CacheOutTask r1, Drawable r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ImageLoader.CacheOutTask.m4175$r8$lambda$IfkHX80OfIhnqccKgD6M9yPds(this.f$0, this.f$1, this.f$2);
    }
}
