package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import android.hardware.camera2.CameraCharacteristics;
import androidx.camera.core.CameraXTracer;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.imagecapture.Bitmap2JpegBytes;
import androidx.camera.core.imagecapture.Image2JpegBytes;
import androidx.camera.core.imagecapture.JpegBytes2Disk;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.IncorrectJpegMetadataQuirk;
import androidx.camera.core.internal.compat.quirk.LowMemoryQuirk;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.processing.Edge;
import androidx.camera.core.processing.InternalImageProcessor;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import androidx.core.util.Preconditions;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public class ProcessingNode {
    private Operation<Bitmap2JpegBytes.In, Packet<byte[]>> mBitmap2JpegBytes;
    private Operation<Packet<Bitmap>, Packet<Bitmap>> mBitmapEffect;
    final Executor mBlockingExecutor;
    private final CameraCharacteristics mCameraCharacteristics;
    private final boolean mHasIncorrectJpegMetadataQuirk;
    private Operation<Packet<ImageProxy>, Bitmap> mImage2Bitmap;
    private Operation<Image2JpegBytes.In, Packet<byte[]>> mImage2JpegBytes;
    final InternalImageProcessor mImageProcessor;
    private Operation<InputPacket, Packet<ImageProxy>> mInput2Packet;
    private In mInputEdge;
    private Operation<Packet<byte[]>, Packet<Bitmap>> mJpegBytes2CroppedBitmap;
    private Operation<JpegBytes2Disk.In, ImageCapture.OutputFileResults> mJpegBytes2Disk;
    private Operation<Packet<byte[]>, Packet<ImageProxy>> mJpegBytes2Image;
    private Operation<Packet<ImageProxy>, ImageProxy> mJpegImage2Result;
    private final Quirks mQuirks;

    public static abstract class In {
        public In() {
        }

        public static In of(int r3, List<Integer> r4) {
            return new AutoValue_ProcessingNode_In(new Edge(), new Edge(), r3, r4);
        }

        public abstract Edge<InputPacket> getEdge();

        public abstract int getInputFormat();

        public abstract List<Integer> getOutputFormats();

        public abstract Edge<InputPacket> getPostviewEdge();
    }

    public static abstract class InputPacket {
        public InputPacket() {
        }

        public static InputPacket of(ProcessingRequest r1, ImageProxy r2) {
            return new AutoValue_ProcessingNode_InputPacket(r1, r2);
        }

        public abstract ImageProxy getImageProxy();

        public abstract ProcessingRequest getProcessingRequest();
    }

    public static /* synthetic */ void $r8$lambda$1KS_UxQ559WWCCTkk2ROJ1HvnoQ(ProcessingRequest r0, ImageCapture.OutputFileResults r1) {
        r0.onFinalResult(r1);
    }

    /* renamed from: $r8$lambda$2hhbegOIewvk-ax_ceIbveBS8sQ, reason: not valid java name */
    public static /* synthetic */ void m591$r8$lambda$2hhbegOIewvkax_ceIbveBS8sQ(ProcessingRequest r0, Bitmap r1) {
        r0.onPostviewBitmapAvailable(r1);
    }

    /* renamed from: $r8$lambda$6U13Zlx7P74SDa-Hwc-iYmFh6g8, reason: not valid java name */
    public static /* synthetic */ void m592$r8$lambda$6U13Zlx7P74SDaHwciYmFh6g8(ProcessingNode r2, InputPacket r3) {
        r2.getClass();
        if (r3.getProcessingRequest().isAborted() == false) goto L6;
        r3.getImageProxy().close();
        return;
    L6:
        r2.mBlockingExecutor.execute(new ProcessingNode$$ExternalSyntheticLambda3(r2, r3));
    }

    /* renamed from: $r8$lambda$Dyd-C8C5v5RyU_LUTOih5XrCnUA, reason: not valid java name */
    public static /* synthetic */ void m593$r8$lambda$DydC8C5v5RyU_LUTOih5XrCnUA(ProcessingRequest r0, ImageProxy r1) {
        r0.onFinalResult(r1);
    }

    /* renamed from: $r8$lambda$EI5MKd_Nwbt-kM8O4-wtsjxa9o4, reason: not valid java name */
    public static /* synthetic */ void m594$r8$lambda$EI5MKd_NwbtkM8O4wtsjxa9o4(ProcessingNode r2, InputPacket r3) {
        r2.getClass();
        if (r3.getProcessingRequest().isAborted() == false) goto L6;
        Logger.w("ProcessingNode", "The postview image is closed due to request aborted");
        r3.getImageProxy().close();
        return;
    L6:
        r2.mBlockingExecutor.execute(new ProcessingNode$$ExternalSyntheticLambda2(r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$JmQGVOKdnxLl7G2mmDH2jCuypVI(ProcessingNode r0, InputPacket r1) {
        r0.processPostviewInputPacket(r1);
    }

    public static /* synthetic */ void $r8$lambda$cI2ICEMuDd0UqmUT_5T0Vcv_lxM(ProcessingNode r0, InputPacket r1) {
        r0.processInputPacket(r1);
    }

    /* renamed from: $r8$lambda$gHbFgZCj0Kb9M357jP-QNmeH6-s, reason: not valid java name */
    public static /* synthetic */ void m595$r8$lambda$gHbFgZCj0Kb9M357jPQNmeH6s(ProcessingNode r4, InputPacket r5) {
        r4.getClass();
        ProcessingRequest r0 = r5.getProcessingRequest();
        boolean r3 = true;
        if (r4.mInputEdge.getOutputFormats().size() > 1) goto L8;
        r3 = false;
    L8:
        if (r5.getProcessingRequest().isInMemoryCapture() == false) goto L17;
        CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda6(r0, r4.processInMemoryCapture(r5)));     // Catch: RuntimeException -> L11 OutOfMemoryError -> L13 ImageCaptureException -> L15
        return;
    L17:
        ImageCapture.OutputFileResults r52 = r4.processOnDiskCapture(r5);     // Catch: RuntimeException -> L11 OutOfMemoryError -> L13 ImageCaptureException -> L15
        if (r3 == true) goto L21;
        CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda7(r0, r52));     // Catch: RuntimeException -> L11 OutOfMemoryError -> L13 ImageCaptureException -> L15
        return;
    L21:
        r0.getTakePictureRequest();     // Catch: RuntimeException -> L11 OutOfMemoryError -> L13 ImageCaptureException -> L15
        throw null;     // Catch: RuntimeException -> L11
    L11:
        e = move-exception;
        r4.sendError(r0, new ImageCaptureException(0, "Processing failed.", e));
        return;
    L15:
        e = move-exception;
        r4.sendError(r0, e);
        return;
    L13:
        e = move-exception;
        r4.sendError(r0, new ImageCaptureException(0, "Processing failed due to low memory.", e));
    }

    public static /* synthetic */ void $r8$lambda$sQYLwVPOMgYdEnTQKxgzAk_5pYg(ProcessingRequest r0, ImageCaptureException r1) {
        r0.onProcessFailure(r1);
    }

    public ProcessingNode(Executor r2, CameraCharacteristics r3, InternalImageProcessor r4) {
        this(r2, r3, r4, DeviceQuirks.getAll());
    }

    public ProcessingNode(Executor r2, CameraCharacteristics r3, InternalImageProcessor r4, Quirks r5) {
        if (DeviceQuirks.get(LowMemoryQuirk.class) == null) goto L5;
        this.mBlockingExecutor = CameraXExecutors.newSequentialExecutor(r2);
    L6:
        this.mImageProcessor = r4;
        this.mCameraCharacteristics = r3;
        this.mQuirks = r5;
        this.mHasIncorrectJpegMetadataQuirk = r5.contains(IncorrectJpegMetadataQuirk.class);
        return;
    L5:
        this.mBlockingExecutor = r2;
        goto L6
    }

    private Packet<byte[]> cropAndMaybeApplyEffect(Packet<byte[]> r2, int r3) {
        Preconditions.checkState(ImageUtil.isJpegFormats(r2.getFormat()));
        Packet<Bitmap> r22 = this.mJpegBytes2CroppedBitmap.apply(r2);
        Operation<Packet<Bitmap>, Packet<Bitmap>> r0 = this.mBitmapEffect;
        if (r0 == null) goto L6;
        r22 = r0.apply(r22);
    L6:
        return this.mBitmap2JpegBytes.apply(Bitmap2JpegBytes.In.of(r22, r3));
    }

    private void sendError(ProcessingRequest r2, ImageCaptureException r3) {
        CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda8(r2, r3));
    }

    public ImageProxy processInMemoryCapture(InputPacket r7) {
        Logger.d("ProcessingNode", "processInMemoryCapture: request ID = " + r7.getProcessingRequest().getRequestId());
        ProcessingRequest r0 = r7.getProcessingRequest();
        Packet<ImageProxy> r72 = this.mInput2Packet.apply(r7);
        List<Integer> r1 = this.mInputEdge.getOutputFormats();
        Preconditions.checkArgument(!r1.isEmpty());
        int r2 = r1.get(0).intValue();
        if (r72.getFormat() == 35) goto L9;
        if (this.mBitmapEffect != null) goto L9;
        if (this.mHasIncorrectJpegMetadataQuirk == true) goto L9;
    L14:
        ImageProxy r6 = this.mJpegImage2Result.apply(r72);
        if (r1.size() > 1) goto L17;
        return r6;
    L17:
        r0.getTakePictureRequest();
        r6.getFormat();
        throw null;
    L9:
        if (r2 != 256) goto L14;
        Packet<byte[]> r73 = this.mImage2JpegBytes.apply(Image2JpegBytes.In.of(r72, r0.getJpegQuality()));
        if (this.mBitmapEffect == null) goto L13;
        r73 = cropAndMaybeApplyEffect(r73, r0.getJpegQuality());
    L13:
        r72 = this.mJpegBytes2Image.apply(r73);
        goto L14
    }

    public void processInputPacket(InputPacket r2) {
        CameraXTracer.trace("processInputPacket", new ProcessingNode$$ExternalSyntheticLambda4(this, r2));
    }

    public ImageCapture.OutputFileResults processOnDiskCapture(InputPacket r8) {
        Logger.d("ProcessingNode", "processOnDiskCapture: request ID = " + r8.getProcessingRequest().getRequestId());
        List<Integer> r0 = this.mInputEdge.getOutputFormats();
        Preconditions.checkArgument(r0.isEmpty() ^ true);
        Integer r3 = r0.get(0);
        int r4 = r3.intValue();
        if (ImageUtil.isJpegFormats(r4) == false) goto L5;
    L8:
        boolean r5 = true;
    L9:
        Preconditions.checkArgument(r5, String.format("On-disk capture only support JPEG and JPEG/R and RAW output formats. Output format: %s", new Object[]{r3}));
        ProcessingRequest r32 = r8.getProcessingRequest();
        r32.getOutputFileOptions();
        Preconditions.checkArgument(false, "OutputFileOptions cannot be empty");
        Packet<ImageProxy> r7 = this.mInput2Packet.apply(r8);
        if (r0.size() <= 1) goto L17;
        r32.getOutputFileOptions();
        Preconditions.checkArgument(false, "The number of OutputFileOptions for simultaneous capture should be at least two");
        if (r7.getFormat() == 32) goto L15;
        r32.getSecondaryOutputFileOptions();
        throw null;
    L15:
        r32.getOutputFileOptions();
        throw null;
    L17:
        if (r4 == 32) goto L20;
        r32.getOutputFileOptions();
        throw null;
    L20:
        r32.getOutputFileOptions();
        throw null;
    L5:
        if (ImageUtil.isRawFormats(r4) == true) goto L8;
        r5 = false;
        goto L9
    }

    public void processPostviewInputPacket(InputPacket r6) {
        ProcessingRequest r0 = r6.getProcessingRequest();
        Packet<ImageProxy> r1 = this.mInput2Packet.apply(r6);     // Catch: Exception -> L14
        int r2 = r1.getFormat();     // Catch: Exception -> L14
        if (r2 != 35) goto L6;
    L11:
        boolean r3 = true;
    L12:
        Preconditions.checkArgument(r3, String.format("Postview only supports to convert YUV, JPEG and JPEG_R format image to the postview output bitmap. Image format: %s", new Object[]{Integer.valueOf(r2)}));     // Catch: Exception -> L14
        Bitmap r5 = this.mImage2Bitmap.apply(r1);     // Catch: Exception -> L14
        CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda5(r0, r5));     // Catch: Exception -> L14
        return;
    L6:
        if (r2 == 256) goto L11;
        if (r2 == 4101) goto L11;
        r3 = false;
    L14:
        e = move-exception;
        r6.getImageProxy().close();
        Logger.e("ProcessingNode", "process postview input packet failed.", e);
    }

    public void release() {
    }

    public Void transform(In r3) {
        this.mInputEdge = r3;
        r3.getEdge().setListener(new ProcessingNode$$ExternalSyntheticLambda0(this));
        r3.getPostviewEdge().setListener(new ProcessingNode$$ExternalSyntheticLambda1(this));
        this.mInput2Packet = new ProcessingInput2Packet();
        this.mImage2JpegBytes = new Image2JpegBytes(this.mQuirks);
        this.mJpegBytes2CroppedBitmap = new JpegBytes2CroppedBitmap();
        this.mBitmap2JpegBytes = new Bitmap2JpegBytes();
        this.mJpegBytes2Disk = new JpegBytes2Disk();
        this.mJpegImage2Result = new JpegImage2Result();
        this.mImage2Bitmap = new Image2Bitmap();
        if (r3.getInputFormat() != 35) goto L5;
    L6:
        this.mJpegBytes2Image = new JpegBytes2Image();
        return null;
    L5:
        if (this.mHasIncorrectJpegMetadataQuirk == true) goto L6;
        return null;
    }
}
