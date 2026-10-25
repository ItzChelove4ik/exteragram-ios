package androidx.camera.core.processing;

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
import androidx.camera.core.processing.util.OutConfig;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public class SurfaceProcessorNode {
    final CameraInternal mCameraInternal;
    private final String mDebugInfo;
    private In mInput;
    private Out mOutput;
    final SurfaceProcessorInternal mSurfaceProcessor;

    /* renamed from: androidx.camera.core.processing.SurfaceProcessorNode$1, reason: invalid class name */
    public class AnonymousClass1 implements FutureCallback<SurfaceOutput> {
        final /* synthetic */ SurfaceProcessorNode this$0;
        final /* synthetic */ SurfaceEdge val$outputEdge;

        public AnonymousClass1(SurfaceProcessorNode r1, SurfaceEdge r2) {
            this.this$0 = r1;
            this.val$outputEdge = r2;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable r4) {
            if (this.val$outputEdge.getTargets() == 2) goto L5;
        L8:
            Logger.w("SurfaceProcessorNode", "Downstream node failed to provide Surface. Target: " + TargetUtils.getHumanReadableName(this.val$outputEdge.getTargets()), r4);
            return;
        L5:
            if ((r4 instanceof CancellationException) == false) goto L8;
            Logger.d("SurfaceProcessorNode", "Downstream VideoCapture failed to provide Surface.");
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

        public static In of(SurfaceEdge r1, List<OutConfig> r2) {
            return new AutoValue_SurfaceProcessorNode_In(r1, r2);
        }

        public abstract List<OutConfig> getOutConfigs();

        public abstract SurfaceEdge getSurfaceEdge();
    }

    public static class Out extends HashMap<OutConfig, SurfaceEdge> {
        public Out() {
        }
    }

    public static /* synthetic */ void $r8$lambda$25D5GW5SGVAqbtw6iAhbTXquKmw(SurfaceProcessorNode r0, SurfaceEdge r1, Map.Entry r2) {
        r0.createAndSendSurfaceOutput(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$T0sBPWZuFEggCvdrrj2wks9PtNc(Map r3, SurfaceRequest.TransformationInfo r4) {
        Iterator r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L9;
        Map.Entry r0 = (Map.Entry) r32.next();
        int r1 = r4.getRotationDegrees() - ((OutConfig) r0.getKey()).getRotationDegrees();
        if (((OutConfig) r0.getKey()).isMirroring() == false) goto L8;
        r1 = -r1;
    L8:
        int r12 = TransformUtils.within360(r1);
        ((SurfaceEdge) r0.getValue()).updateTransformation(r12, -1);
        goto L4
    }

    /* renamed from: $r8$lambda$k6CWcef-Le9tXuLSlGJo2BURuBM, reason: not valid java name */
    public static /* synthetic */ void m617$r8$lambda$k6CWcefLe9tXuLSlGJo2BURuBM(SurfaceProcessorNode r1) {
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

    @SuppressLint({"LambdaLast"})
    public SurfaceProcessorNode(CameraInternal r1, SurfaceProcessorInternal r2, String r3) {
        this.mCameraInternal = r1;
        this.mSurfaceProcessor = r2;
        this.mDebugInfo = r3;
    }

    private void createAndSendSurfaceOutput(SurfaceEdge r7, Map.Entry<OutConfig, SurfaceEdge> r8) {
        SurfaceEdge r0 = r8.getValue();
        Logger.d("SurfaceProcessorNode", "     -> outputEdge = " + r0);
        Size r1 = r7.getStreamSpec().getResolution();
        Rect r2 = r8.getKey().getCropRect();
        if (r7.hasCameraTransform() == false) goto L5;
        CameraInternal r72 = this.mCameraInternal;
    L6:
        Futures.addCallback(r0.createSurfaceOutputFuture(r8.getKey().getFormat(), SurfaceOutput.CameraInputInfo.of(r1, r2, r72, r8.getKey().getRotationDegrees(), r8.getKey().isMirroring()), null), new AnonymousClass1(this, r0), CameraXExecutors.mainThreadExecutor());
        return;
    L5:
        r72 = null;
        goto L6
    }

    private void sendSurfaceOutputs(SurfaceEdge r4, Map<OutConfig, SurfaceEdge> r5) {
        Iterator<Map.Entry<OutConfig, SurfaceEdge>> r52 = r5.entrySet().iterator();
    L4:
        if (r52.hasNext() == false) goto L6;
        Map.Entry<OutConfig, SurfaceEdge> r0 = r52.next();
        createAndSendSurfaceOutput(r4, r0);
        r0.getValue().addOnInvalidatedListener(new SurfaceProcessorNode$$ExternalSyntheticLambda0(this, r4, r0));
        goto L4
    }

    private void sendSurfaceRequest(SurfaceEdge r2) {
        this.mSurfaceProcessor.onInputSurface(r2.createSurfaceRequest(this.mCameraInternal));
    }

    private SurfaceEdge transformSingleOutput(SurfaceEdge r13, OutConfig r14) {
        Rect r12 = r14.getCropRect();
        int r0 = r14.getRotationDegrees();
        boolean r1 = r14.isMirroring();
        Matrix r6 = new Matrix(r13.getSensorToBufferTransform());
        Matrix r2 = TransformUtils.getRectToRect(new RectF(r12), TransformUtils.sizeToRectF(r14.getSize()), r0, r1);
        r6.postConcat(r2);
        Preconditions.checkArgument(TransformUtils.isAspectRatioMatchingWithRoundingError(TransformUtils.getRotatedSize(r12, r0), r14.getSize()));
        if (r14.shouldRespectInputCropRect() == false) goto L6;
        Preconditions.checkArgument(r14.getCropRect().contains(r13.getCropRect()), String.format("Output crop rect %s must contain input crop rect %s", new Object[]{r14.getCropRect(), r13.getCropRect()}));
        Rect r122 = new Rect();
        RectF r3 = new RectF(r13.getCropRect());
        r2.mapRect(r3);
        r3.round(r122);
    L5:
        Rect r8 = r122;
        StreamSpec r5 = r13.getStreamSpec().toBuilder().setResolution(r14.getSize()).build();
        int r32 = r14.getTargets();
        int r4 = r14.getFormat();
        int r9 = r13.getRotationDegrees() - r0;
        if (r13.isMirroring() == r1) goto L11;
        boolean r123 = true;
    L13:
        return new SurfaceEdge(r32, r4, r5, r6, false, r8, r9, -1, r123);
    L11:
        r123 = false;
        goto L13
    L6:
        r122 = TransformUtils.sizeToRect(r14.getSize());
        goto L5
    }

    public void release() {
        this.mSurfaceProcessor.release();
        Threads.runOnMain(new SurfaceProcessorNode$$ExternalSyntheticLambda2(this));
    }

    public void setUpRotationUpdates(SurfaceEdge r1, Map<OutConfig, SurfaceEdge> r2) {
        r1.addTransformationUpdateListener(new SurfaceProcessorNode$$ExternalSyntheticLambda1(r2));
    }

    public Out transform(In r6) {
        Threads.checkMainThread();
        if (this.mDebugInfo != null) goto L5;
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        Logger.d("SurfaceProcessorNode", r0 + "SurfaceProcessorNode Transform (Processor=" + this.mSurfaceProcessor + "\n   inputEdge = " + r6.getSurfaceEdge());
        Iterator<OutConfig> r02 = r6.getOutConfigs().iterator();
    L8:
        if (r02.hasNext() == false) goto L10;
        Logger.d("SurfaceProcessorNode", "   outputConfig = " + r02.next());
        goto L8
    L10:
        this.mInput = r6;
        this.mOutput = new Out();
        SurfaceEdge r03 = r6.getSurfaceEdge();
        Iterator<OutConfig> r62 = r6.getOutConfigs().iterator();
    L12:
        if (r62.hasNext() == false) goto L14;
        OutConfig r1 = r62.next();
        this.mOutput.put(r1, transformSingleOutput(r03, r1));
        goto L12
    L14:
        sendSurfaceRequest(r03);
        sendSurfaceOutputs(r03, this.mOutput);
        setUpRotationUpdates(r03, this.mOutput);
        return this.mOutput;
    L5:
        r0 = "[" + this.mDebugInfo + "] ";
        goto L6
    }
}
