package androidx.camera.core.processing.concurrent;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.core.processing.SurfaceProcessorInternal;
import androidx.camera.core.processing.TargetUtils;
import androidx.camera.core.processing.util.OutConfig;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public class DualSurfaceProcessorNode {
    private final String mDebugInfo;
    private In mInput;
    private Out mOutput;
    final CameraInternal mPrimaryCameraInternal;
    final CameraInternal mSecondaryCameraInternal;
    final SurfaceProcessorInternal mSurfaceProcessor;

    /* renamed from: androidx.camera.core.processing.concurrent.DualSurfaceProcessorNode$1, reason: invalid class name */
    public class AnonymousClass1 implements FutureCallback<SurfaceOutput> {
        final /* synthetic */ DualSurfaceProcessorNode this$0;
        final /* synthetic */ SurfaceEdge val$outputEdge;

        public AnonymousClass1(DualSurfaceProcessorNode r1, SurfaceEdge r2) {
            this.this$0 = r1;
            this.val$outputEdge = r2;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable r4) {
            if (this.val$outputEdge.getTargets() == 2) goto L5;
        L8:
            Logger.w("DualSurfaceProcessorNode", "Downstream node failed to provide Surface. Target: " + TargetUtils.getHumanReadableName(this.val$outputEdge.getTargets()), r4);
            return;
        L5:
            if ((r4 instanceof CancellationException) == false) goto L8;
            Logger.d("DualSurfaceProcessorNode", "Downstream VideoCapture failed to provide Surface.");
        }

        /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
        public void onSuccess2(SurfaceOutput r1) {
            Preconditions.checkNotNull(r1);
            this.this$0.mSurfaceProcessor.onOutputSurface(r1);
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public /* bridge */ /* synthetic */ void onSuccess(SurfaceOutput r1) {
            onSuccess2(r1);
        }
    }

    public static abstract class In {
        public In() {
        }

        public static In of(SurfaceEdge r1, SurfaceEdge r2, List<DualOutConfig> r3) {
            return new AutoValue_DualSurfaceProcessorNode_In(r1, r2, r3);
        }

        public abstract List<DualOutConfig> getOutConfigs();

        public abstract SurfaceEdge getPrimarySurfaceEdge();

        public abstract SurfaceEdge getSecondarySurfaceEdge();
    }

    public static class Out extends HashMap<DualOutConfig, SurfaceEdge> {
        public Out() {
        }
    }

    /* renamed from: $r8$lambda$-CIgK46nPXeCm1Yzy9mUIJ-nLQU, reason: not valid java name */
    public static /* synthetic */ void m625$r8$lambda$CIgK46nPXeCm1Yzy9mUIJnLQU(DualSurfaceProcessorNode r1) {
        Out r12 = r1.mOutput;
        if (r12 == null) goto L8;
        Iterator<SurfaceEdge> r13 = r12.values().iterator();
    L6:
        if (r13.hasNext() == false) goto L10;
        r13.next().close();
        goto L6
    L10:
        return;
    }

    public static /* synthetic */ void $r8$lambda$bt74CxLa0a7Yc5G9XI0_olc5UM0(DualSurfaceProcessorNode r0, CameraInternal r1, CameraInternal r2, SurfaceEdge r3, SurfaceEdge r4, Map.Entry r5) {
        r0.createAndSendSurfaceOutput(r1, r2, r3, r4, r5);
    }

    @SuppressLint({"LambdaLast"})
    public DualSurfaceProcessorNode(CameraInternal r1, CameraInternal r2, SurfaceProcessorInternal r3, String r4) {
        this.mPrimaryCameraInternal = r1;
        this.mSecondaryCameraInternal = r2;
        this.mSurfaceProcessor = r3;
        this.mDebugInfo = r4;
    }

    private void createAndSendSurfaceOutput(CameraInternal r6, CameraInternal r7, SurfaceEdge r8, SurfaceEdge r9, Map.Entry<DualOutConfig, SurfaceEdge> r10) {
        SurfaceEdge r0 = r10.getValue();
        Logger.d("DualSurfaceProcessorNode", "     -> outputEdge = " + r0);
        Size r1 = r8.getStreamSpec().getResolution();
        Rect r2 = r10.getKey().getPrimaryOutConfig().getCropRect();
        if (r8.hasCameraTransform() == true) goto L6;
        r6 = null;
    L6:
        SurfaceOutput.CameraInputInfo r62 = SurfaceOutput.CameraInputInfo.of(r1, r2, r6, r10.getKey().getPrimaryOutConfig().getRotationDegrees(), r10.getKey().getPrimaryOutConfig().isMirroring());
        Size r82 = r9.getStreamSpec().getResolution();
        Rect r12 = r10.getKey().getSecondaryOutConfig().getCropRect();
        if (r9.hasCameraTransform() == true) goto L10;
        r7 = null;
    L10:
        Futures.addCallback(r0.createSurfaceOutputFuture(r10.getKey().getPrimaryOutConfig().getFormat(), r62, SurfaceOutput.CameraInputInfo.of(r82, r12, r7, r10.getKey().getSecondaryOutConfig().getRotationDegrees(), r10.getKey().getSecondaryOutConfig().isMirroring())), new AnonymousClass1(this, r0), CameraXExecutors.mainThreadExecutor());
    }

    private void sendSurfaceOutputs(CameraInternal r9, CameraInternal r10, SurfaceEdge r11, SurfaceEdge r12, Map<DualOutConfig, SurfaceEdge> r13) {
        Iterator<Map.Entry<DualOutConfig, SurfaceEdge>> r132 = r13.entrySet().iterator();
    L4:
        if (r132.hasNext() == false) goto L6;
        Map.Entry<DualOutConfig, SurfaceEdge> r6 = r132.next();
        DualSurfaceProcessorNode r1 = this;
        CameraInternal r2 = r9;
        CameraInternal r3 = r10;
        SurfaceEdge r4 = r11;
        SurfaceEdge r5 = r12;
        r1.createAndSendSurfaceOutput(r2, r3, r4, r5, r6);
        r6.getValue().addOnInvalidatedListener(new DualSurfaceProcessorNode$$ExternalSyntheticLambda1(r1, r2, r3, r4, r5, r6));
        DualSurfaceProcessorNode this = r1;
        r9 = r2;
        r10 = r3;
        r11 = r4;
        r12 = r5;
        goto L4
    }

    private void sendSurfaceRequest(CameraInternal r1, SurfaceEdge r2, boolean r3) {
        SurfaceRequest r12 = r2.createSurfaceRequest(r1, r3);
        this.mSurfaceProcessor.onInputSurface(r12);
    }

    private SurfaceEdge transformSingleOutput(SurfaceEdge r13, OutConfig r14) {
        Rect r12 = r14.getCropRect();
        int r0 = r14.getRotationDegrees();
        boolean r1 = r14.isMirroring();
        Matrix r6 = new Matrix(r13.getSensorToBufferTransform());
        r6.postConcat(TransformUtils.getRectToRect(new RectF(r12), TransformUtils.sizeToRectF(r14.getSize()), r0, r1));
        Preconditions.checkArgument(TransformUtils.isAspectRatioMatchingWithRoundingError(TransformUtils.getRotatedSize(r12, r0), r14.getSize()));
        Rect r8 = TransformUtils.sizeToRect(r14.getSize());
        StreamSpec r5 = r13.getStreamSpec().toBuilder().setResolution(r14.getSize()).build();
        int r3 = r14.getTargets();
        int r4 = r14.getFormat();
        int r9 = r13.getRotationDegrees() - r0;
        if (r13.isMirroring() == r1) goto L6;
        boolean r122 = true;
    L8:
        return new SurfaceEdge(r3, r4, r5, r6, false, r8, r9, -1, r122);
    L6:
        r122 = false;
        goto L8
    }

    public void release() {
        this.mSurfaceProcessor.release();
        Threads.runOnMain(new DualSurfaceProcessorNode$$ExternalSyntheticLambda0(this));
    }

    public Out transform(In r7) {
        Threads.checkMainThread();
        if (this.mDebugInfo != null) goto L5;
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        Logger.d("DualSurfaceProcessorNode", r0 + "DualSurfaceProcessorNode Transform Processor = " + this.mSurfaceProcessor + "\n   primary input = " + r7.getPrimarySurfaceEdge() + "\n   secondary input = " + r7.getSecondarySurfaceEdge());
        Iterator<DualOutConfig> r02 = r7.getOutConfigs().iterator();
    L8:
        if (r02.hasNext() == false) goto L10;
        Logger.d("SurfaceProcessorNode", "   outputConfig = " + r02.next());
        goto L8
    L10:
        this.mInput = r7;
        this.mOutput = new Out();
        SurfaceEdge r3 = this.mInput.getPrimarySurfaceEdge();
        SurfaceEdge r4 = this.mInput.getSecondarySurfaceEdge();
        Iterator<DualOutConfig> r72 = this.mInput.getOutConfigs().iterator();
    L12:
        if (r72.hasNext() == false) goto L14;
        DualOutConfig r03 = r72.next();
        this.mOutput.put(r03, transformSingleOutput(r3, r03.getPrimaryOutConfig()));
        goto L12
    L14:
        sendSurfaceRequest(this.mPrimaryCameraInternal, r3, true);
        sendSurfaceRequest(this.mSecondaryCameraInternal, r4, false);
        sendSurfaceOutputs(this.mPrimaryCameraInternal, this.mSecondaryCameraInternal, r3, r4, this.mOutput);
        return this.mOutput;
    L5:
        r0 = "[" + this.mDebugInfo + "] ";
        goto L6
    }
}
