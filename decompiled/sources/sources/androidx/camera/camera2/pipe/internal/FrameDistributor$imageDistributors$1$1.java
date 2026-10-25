package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.media.ImageListener;
import androidx.camera.camera2.pipe.media.ImageSource;
import androidx.camera.camera2.pipe.media.OutputImage;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFrameDistributor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameDistributor.kt\nandroidx/camera/camera2/pipe/internal/FrameDistributor$imageDistributors$1$1\n+ 2 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n*L\n1#1,409:1\n64#2:410\n68#2:411\n*S KotlinDebug\n*F\n+ 1 FrameDistributor.kt\nandroidx/camera/camera2/pipe/internal/FrameDistributor$imageDistributors$1$1\n*L\n134#1:410\n139#1:411\n*E\n"})
/* loaded from: classes3.dex */
public final class FrameDistributor$imageDistributors$1$1 implements ImageListener {
    final /* synthetic */ Map<OutputId, OutputDistributor<OutputImage>> $imageDistributorMap;
    final /* synthetic */ ImageSource $imageSource;

    public FrameDistributor$imageDistributors$1$1(Map<OutputId, OutputDistributor<OutputImage>> r1, ImageSource r2) {
        this.$imageDistributorMap = r1;
        this.$imageSource = r2;
    }
}
