package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraExtensionMetadata;
import androidx.camera.camera2.pipe.CameraMetadata;
import java.util.Set;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00112\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0012\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "cameraId", "Landroidx/camera/camera2/pipe/CameraMetadata;", "getCameraMetadata-0r8Bogc", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCameraMetadata", "awaitCameraMetadata-EfqyGwQ", "(Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "extension", "Landroidx/camera/camera2/pipe/CameraExtensionMetadata;", "awaitCameraExtensionMetadata-0r8Bogc", "(Ljava/lang/String;I)Landroidx/camera/camera2/pipe/CameraExtensionMetadata;", "awaitCameraExtensionMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "getSupportedCameraExtensions-EfqyGwQ", "(Ljava/lang/String;)Ljava/util/Set;", "getSupportedCameraExtensions", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface Camera2MetadataProvider {
    /* renamed from: awaitCameraExtensionMetadata-0r8Bogc */
    CameraExtensionMetadata mo455awaitCameraExtensionMetadata0r8Bogc(String r1, int r2);

    /* renamed from: awaitCameraMetadata-EfqyGwQ */
    CameraMetadata mo456awaitCameraMetadataEfqyGwQ(String r1);

    /* renamed from: getCameraMetadata-0r8Bogc */
    Object mo457getCameraMetadata0r8Bogc(String r1, Continuation<? super CameraMetadata> r2);

    /* renamed from: getSupportedCameraExtensions-EfqyGwQ */
    Set<Integer> mo458getSupportedCameraExtensionsEfqyGwQ(String r1);
}
