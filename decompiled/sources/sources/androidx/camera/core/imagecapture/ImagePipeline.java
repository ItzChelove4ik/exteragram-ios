package androidx.camera.core.imagecapture;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Size;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.imagecapture.CaptureNode;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.compat.workaround.ExifRotationAvailability;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public class ImagePipeline {
    static final ExifRotationAvailability EXIF_ROTATION_AVAILABILITY = null;
    private final CaptureConfig mCaptureConfig;
    private final CaptureNode mCaptureNode;
    private final CaptureNode.In mPipelineIn;
    private final ProcessingNode mProcessingNode;
    private final ImageCaptureConfig mUseCaseConfig;

    static {
        EXIF_ROTATION_AVAILABILITY = new ExifRotationAvailability();
    }

    public ImagePipeline(ImageCaptureConfig r10, Size r11, CameraCharacteristics r12, CameraEffect r13, boolean r14, PostviewSettings r15) {
        Threads.checkMainThread();
        this.mUseCaseConfig = r10;
        this.mCaptureConfig = CaptureConfig.Builder.createFrom(r10).build();
        CaptureNode r132 = new CaptureNode();
        this.mCaptureNode = r132;
        Executor r1 = r10.getIoExecutor(CameraXExecutors.ioExecutor());
        Objects.requireNonNull(r1);
        ProcessingNode r0 = new ProcessingNode(r1, r12, null);
        this.mProcessingNode = r0;
        ArrayList r5 = new ArrayList();
        if (r10.getSecondaryInputFormat() == 0) goto L5;
        r5.add(32);
        r5.add(256);
    L6:
        int r4 = r10.getInputFormat();
        r10.getImageReaderProxyProvider();
        CaptureNode.In r102 = CaptureNode.In.of(r11, r4, r5, r14, null, r15);
        this.mPipelineIn = r102;
        r0.transform(r132.transform(r102));
        return;
    L5:
        r5.add(Integer.valueOf(getOutputFormat()));
        goto L6
    }

    private int getOutputFormat() {
        Integer r0 = (Integer) this.mUseCaseConfig.retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
        if (r0 != null) goto L5;
        Integer r3 = (Integer) this.mUseCaseConfig.retrieveOption(ImageInputConfig.OPTION_INPUT_FORMAT, null);
        if (r3 != null) goto L9;
    L11:
        if (r3 != null) goto L13;
        return 256;
    L13:
        if (r3.intValue() != 32) goto L17;
        return 32;
    L17:
        return 256;
    L9:
        if (r3.intValue() != 4101) goto L11;
        return 4101;
    L5:
        return r0.intValue();
    }

    public void close() {
        Threads.checkMainThread();
        this.mCaptureNode.release();
        this.mProcessingNode.release();
    }

    public SessionConfig.Builder createSessionConfigBuilder(Size r3) {
        SessionConfig.Builder r32 = SessionConfig.Builder.createFrom(this.mUseCaseConfig, r3);
        r32.addNonRepeatingSurface(this.mPipelineIn.getSurface());
        if (this.mPipelineIn.getOutputFormats().size() <= 1) goto L8;
        if (this.mPipelineIn.getSecondarySurface() == null) goto L8;
        r32.addNonRepeatingSurface(this.mPipelineIn.getSecondarySurface());
    L8:
        if (this.mPipelineIn.getPostviewSurface() == null) goto L10;
        r32.setPostviewSurface(this.mPipelineIn.getPostviewSurface());
    L10:
        return r32;
    }

    public int getCapacity() {
        Threads.checkMainThread();
        return this.mCaptureNode.getCapacity();
    }

    public void setOnImageCloseListener(ForwardingImageProxy.OnImageCloseListener r1) {
        Threads.checkMainThread();
        this.mCaptureNode.setOnImageCloseListener(r1);
    }
}
