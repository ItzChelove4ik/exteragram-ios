package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.RequestNumber;
import kotlin.Metadata;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicLong;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0001\u0010\u0002\"\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u001a\u0010\t\u001a\u00020\b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u001a\u0010\r\u001a\u00020\b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/pipe/RequestNumber;", "nextRequestNumber", "()J", "Lkotlinx/atomicfu/AtomicInt;", "captureSequenceProcessorDebugIds", "Lkotlinx/atomicfu/AtomicInt;", "getCaptureSequenceProcessorDebugIds", "()Lkotlinx/atomicfu/AtomicInt;", "Lkotlinx/atomicfu/AtomicLong;", "captureSequenceDebugIds", "Lkotlinx/atomicfu/AtomicLong;", "getCaptureSequenceDebugIds", "()Lkotlinx/atomicfu/AtomicLong;", "requestTags", "getRequestTags", "camera-camera2-pipe"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Camera2CaptureSequenceProcessorKt {
    private static final AtomicLong captureSequenceDebugIds = null;
    private static final AtomicInt captureSequenceProcessorDebugIds = null;
    private static final AtomicLong requestTags = null;

    static {
        captureSequenceProcessorDebugIds = AtomicFU.atomic(0);
        captureSequenceDebugIds = AtomicFU.atomic(0);
        requestTags = AtomicFU.atomic(0);
    }

    public static final AtomicLong getCaptureSequenceDebugIds() {
        return captureSequenceDebugIds;
    }

    public static final AtomicInt getCaptureSequenceProcessorDebugIds() {
        return captureSequenceProcessorDebugIds;
    }

    public static final long nextRequestNumber() {
        return RequestNumber.m365constructorimpl(requestTags.incrementAndGet());
    }
}
