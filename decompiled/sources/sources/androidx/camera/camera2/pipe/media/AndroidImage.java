package androidx.camera.camera2.pipe.media;

import android.media.Image;
import android.os.Build;
import androidx.camera.camera2.pipe.StreamFormat;
import androidx.camera.camera2.pipe.compat.Api28Compat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\n\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0007*\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u001b\u0010\u0019R\u001a\u0010\u001c\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019R\u001a\u0010\u001f\u001a\u00020\u001e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Landroidx/camera/camera2/pipe/media/AndroidImage;", "Landroidx/camera/camera2/pipe/media/ImageWrapper;", "Landroid/media/Image;", "image", "<init>", "(Landroid/media/Image;)V", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", "Landroid/media/Image;", "lock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "format", "I", "getFormat", "()I", "width", "getWidth", "height", "getHeight", _UrlKt.FRAGMENT_ENCODE_SET, "timestamp", "J", "getTimestamp", "()J", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAndroidImage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidImage.kt\nandroidx/camera/camera2/pipe/media/AndroidImage\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,128:1\n11228#2:129\n11563#2,3:130\n*S KotlinDebug\n*F\n+ 1 AndroidImage.kt\nandroidx/camera/camera2/pipe/media/AndroidImage\n*L\n119#1:129\n119#1:130,3\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidImage implements ImageWrapper {
    private final int format;
    private final int height;
    private final Image image;
    private final Object lock;
    private final long timestamp;
    private final int width;

    public AndroidImage(Image r3) {
        this.image = r3;
        this.lock = new Object();
        this.format = r3.getFormat();
        this.width = r3.getWidth();
        this.height = r3.getHeight();
        this.timestamp = r3.getTimestamp();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.image.close();
    }

    public int getFormat() {
        return this.format;
    }

    public int getHeight() {
        return this.height;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public int getWidth() {
        return this.width;
    }

    public String toString() {
        return "Image-" + StreamFormat.m393getNameimpl(StreamFormat.m390constructorimpl(getFormat())) + "-w" + getWidth() + 'h' + getHeight() + "-t" + getTimestamp();
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r3) {
        if (Intrinsics.areEqual(r3, Reflection.getOrCreateKotlinClass(Image.class)) == false) goto L7;
        return (T) this.image;
    L7:
        if (Build.VERSION.SDK_INT > 27) goto L9;
        return null;
    L9:
        return (T) Api28Compat.unwrapAsHardwareBuffer(this.image, r3);
    }
}
