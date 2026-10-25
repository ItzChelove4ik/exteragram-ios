package androidx.camera.camera2.pipe.media;

import android.hardware.camera2.params.OutputConfiguration;
import android.media.ImageReader;
import androidx.camera.camera2.pipe.UnsafeWrapper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0001R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/media/AndroidMultiResolutionImageReader;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/media/ImageReader$OnImageAvailableListener;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/OutputConfiguration;", "outputConfigurations", "Ljava/util/List;", "getOutputConfigurations$camera_camera2_pipe", "()Ljava/util/List;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAndroidImageReaders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidImageReaders.kt\nandroidx/camera/camera2/pipe/media/AndroidMultiResolutionImageReader\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,438:1\n465#2:439\n415#2:440\n1252#3,4:441\n1563#3:445\n1634#3,3:446\n*S KotlinDebug\n*F\n+ 1 AndroidImageReaders.kt\nandroidx/camera/camera2/pipe/media/AndroidMultiResolutionImageReader\n*L\n219#1:439\n219#1:440\n219#1:441,4\n277#1:445\n277#1:446,3\n*E\n"})
/* loaded from: classes3.dex */
public abstract class AndroidMultiResolutionImageReader implements UnsafeWrapper, AutoCloseable, ImageReader.OnImageAvailableListener {
    public abstract List<OutputConfiguration> getOutputConfigurations$camera_camera2_pipe();
}
