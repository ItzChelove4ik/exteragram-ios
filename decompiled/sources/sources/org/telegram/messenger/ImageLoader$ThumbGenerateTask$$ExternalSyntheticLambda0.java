package org.telegram.messenger;

import android.graphics.drawable.BitmapDrawable;
import java.util.ArrayList;
import org.telegram.messenger.ImageLoader;

/* loaded from: classes5.dex */
public final /* synthetic */ class ImageLoader$ThumbGenerateTask$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageLoader.ThumbGenerateTask f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ BitmapDrawable f$3;
    public final /* synthetic */ ArrayList f$4;

    public /* synthetic */ ImageLoader$ThumbGenerateTask$$ExternalSyntheticLambda0(ImageLoader.ThumbGenerateTask r1, String r2, ArrayList r3, BitmapDrawable r4, ArrayList r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ImageLoader.ThumbGenerateTask.m4196$r8$lambda$loz4IIPCiFJ8XLLrCL4xOIfALA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
