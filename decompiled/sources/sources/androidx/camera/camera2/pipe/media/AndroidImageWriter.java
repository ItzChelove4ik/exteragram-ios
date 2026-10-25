package androidx.camera.camera2.pipe.media;

import android.media.Image;
import android.media.ImageWriter;
import android.os.Build;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.view.Surface;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.camera.camera2.pipe.InputStreamId;
import androidx.camera.camera2.pipe.StreamFormat;
import androidx.camera.camera2.pipe.compat.Api29Compat;
import androidx.camera.camera2.pipe.core.Log;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import okhttp3.internal.url._UrlKt;
import okio.SegmentedByteString$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 (2\u00020\u00012\u00020\u0002:\u0001(B\u0019\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J)\u0010\u0018\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0015*\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001dR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001eR\u001c\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u001a\u0010#\u001a\u00020\"8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010\u001e\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\"8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010\u001e\u001a\u0004\b'\u0010%¨\u0006)"}, d2 = {"Landroidx/camera/camera2/pipe/media/AndroidImageWriter;", "Landroidx/camera/camera2/pipe/media/ImageWriterWrapper;", "Landroid/media/ImageWriter$OnImageReleasedListener;", "Landroid/media/ImageWriter;", "imageWriter", "Landroidx/camera/camera2/pipe/InputStreamId;", "inputStreamId", "<init>", "(Landroid/media/ImageWriter;I)V", "Landroidx/camera/camera2/pipe/media/ImageWrapper;", "image", _UrlKt.FRAGMENT_ENCODE_SET, "queueInputImage", "(Landroidx/camera/camera2/pipe/media/ImageWrapper;)Z", "writer", _UrlKt.FRAGMENT_ENCODE_SET, "onImageReleased", "(Landroid/media/ImageWriter;)V", "close", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroid/media/ImageWriter;", "I", "Lkotlinx/atomicfu/AtomicRef;", "onImageReleasedListener", "Lkotlinx/atomicfu/AtomicRef;", _UrlKt.FRAGMENT_ENCODE_SET, "maxImages", "getMaxImages", "()I", "format", "getFormat", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAndroidImageWriter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidImageWriter.kt\nandroidx/camera/camera2/pipe/media/AndroidImageWriter\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,130:1\n71#2,2:131\n71#2,2:133\n*S KotlinDebug\n*F\n+ 1 AndroidImageWriter.kt\nandroidx/camera/camera2/pipe/media/AndroidImageWriter\n*L\n47#1:131,2\n53#1:133,2\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidImageWriter implements ImageWriterWrapper, ImageWriter.OnImageReleasedListener {
    public static final Companion Companion = null;
    private final int format;
    private final ImageWriter imageWriter;
    private final int inputStreamId;
    private final int maxImages;
    private final AtomicRef<Object> onImageReleasedListener;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/pipe/media/AndroidImageWriter$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/camera/camera2/pipe/media/ImageWriterWrapper;", "surface", "Landroid/view/Surface;", "inputStreamId", "Landroidx/camera/camera2/pipe/InputStreamId;", "maxImages", _UrlKt.FRAGMENT_ENCODE_SET, "format", "Landroidx/camera/camera2/pipe/StreamFormat;", "handler", "Landroid/os/Handler;", "create-U86x6Zg", "(Landroid/view/Surface;IILandroidx/camera/camera2/pipe/StreamFormat;Landroid/os/Handler;)Landroidx/camera/camera2/pipe/media/ImageWriterWrapper;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAndroidImageWriter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidImageWriter.kt\nandroidx/camera/camera2/pipe/media/AndroidImageWriter$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,130:1\n1#2:131\n71#3,2:132\n*S KotlinDebug\n*F\n+ 1 AndroidImageWriter.kt\nandroidx/camera/camera2/pipe/media/AndroidImageWriter$Companion\n*L\n115#1:132,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: create-U86x6Zg, reason: not valid java name */
        public final ImageWriterWrapper m580createU86x6Zg(Surface r4, int r5, int r6, StreamFormat r7, Handler r8) {
            if (r6 > 0) goto L5;
            SegmentedByteString$$ExternalSyntheticBUOutline0.m("Max images (", r6, ") must be > 0");
            return null;
        L5:
            if (r6 > 54) goto L17;
            int r0 = Build.VERSION.SDK_INT;
            if (r0 < 29) goto L10;
            if (r7 == null) goto L10;
            ImageWriter r42 = Api29Compat.imageWriterNewInstance(r4, r6, r7.m396unboximpl());
        L15:
            AndroidImageWriter r62 = new AndroidImageWriter(r42, r5, null);
            r42.setOnImageReleasedListener(r62, r8);
            return r62;
        L10:
            if (r7 != null) goto L12;
        L14:
            r42 = ImageWriter.newInstance(r4, r6);
            goto L15
        L12:
            if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L14;
            android.util.Log.w("CXCP", "Ignoring format (" + StreamFormat.m395toStringimpl(r7.m396unboximpl()) + ") for " + InputStreamId.m279toStringimpl(r5) + ". Android " + r0 + " does not support creating ImageWriters with formats. This may lead to unexpected behaviors.");
            goto L14
        L17:
            f$$ExternalSyntheticBUOutline1.m("Max images for ImageWriters is restricted to 54 to prevent overloading downstream consumer components.");
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }

    private AndroidImageWriter(ImageWriter r1, int r2) {
        this.imageWriter = r1;
        this.inputStreamId = r2;
        this.onImageReleasedListener = AtomicFU.atomic(null);
        this.maxImages = r1.getMaxImages();
        this.format = r1.getFormat();
    }

    public /* synthetic */ AndroidImageWriter(ImageWriter r1, int r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.imageWriter.close();
    }

    @Override // android.media.ImageWriter.OnImageReleasedListener
    public void onImageReleased(ImageWriter r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.onImageReleasedListener.getValue());
    }

    @Override // androidx.camera.camera2.pipe.media.ImageWriterWrapper
    public boolean queueInputImage(ImageWrapper r6) {
        Image r3 = (Image) r6.unwrapAs(Reflection.getOrCreateKotlinClass(Image.class));     // Catch: Throwable -> L8
        if (r3 == null) goto L6;
        this.imageWriter.queueInputImage(r3);     // Catch: Throwable -> L8
        return true;
    L6:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L10;
        android.util.Log.w("CXCP", "Failed to unwrap image wrapper " + r6);     // Catch: Throwable -> L8
    L10:
        return false;
    L8:
        th = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L17;
        android.util.Log.w("CXCP", "Failed to queue image to " + this + " due to error " + th.getMessage() + ". Ignoring failure and closing " + r6);
    L17:
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r6);
        return false;
    }

    public String toString() {
        return "ImageWriter-" + StreamFormat.m393getNameimpl(StreamFormat.m390constructorimpl(this.imageWriter.getFormat())) + SignatureVisitor.SUPER + InputStreamId.m279toStringimpl(this.inputStreamId);
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(ImageWriter.class)) == true) goto L5;
        return null;
    L5:
        return (T) this.imageWriter;
    }
}
