package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;

/* loaded from: classes4.dex */
public interface ImageOutput {
    public static final ImageOutput NO_OP = null;

    /* renamed from: androidx.media3.exoplayer.image.ImageOutput$1, reason: invalid class name */
    public class AnonymousClass1 implements ImageOutput {
        public AnonymousClass1() {
        }

        @Override // androidx.media3.exoplayer.image.ImageOutput
        public void onDisabled() {
        }

        @Override // androidx.media3.exoplayer.image.ImageOutput
        public void onImageAvailable(long r1, Bitmap r3) {
        }
    }

    static {
        NO_OP = new AnonymousClass1();
    }

    void onDisabled();

    void onImageAvailable(long r1, Bitmap r3);
}
