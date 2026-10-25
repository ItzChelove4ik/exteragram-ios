package androidx.camera.core.streamsharing;

import androidx.camera.core.impl.CameraCaptureMetaData$AeMode;
import androidx.camera.core.impl.CameraCaptureMetaData$AeState;
import androidx.camera.core.impl.CameraCaptureMetaData$AfMode;
import androidx.camera.core.impl.CameraCaptureMetaData$AfState;
import androidx.camera.core.impl.CameraCaptureMetaData$AwbMode;
import androidx.camera.core.impl.CameraCaptureMetaData$AwbState;
import androidx.camera.core.impl.CameraCaptureMetaData$FlashState;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.TagBundle;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class VirtualCameraCaptureResult implements CameraCaptureResult {
    private final CameraCaptureResult mBaseCameraCaptureResult;
    private final TagBundle mTagBundle;
    private final long mTimestamp;

    private VirtualCameraCaptureResult(CameraCaptureResult r1, TagBundle r2, long r3) {
        this.mBaseCameraCaptureResult = r1;
        this.mTagBundle = r2;
        this.mTimestamp = r3;
    }

    public VirtualCameraCaptureResult(TagBundle r2, long r3) {
        this(null, r2, r3);
    }

    public VirtualCameraCaptureResult(TagBundle r3, CameraCaptureResult r4) {
        this(r4, r3, -1);
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AeMode getAeMode() {
        CameraCaptureResult r0 = this.mBaseCameraCaptureResult;
        if (r0 == null) goto L7;
        return r0.getAeMode();
    L7:
        return CameraCaptureMetaData$AeMode.UNKNOWN;
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AeState getAeState() {
        CameraCaptureResult r0 = this.mBaseCameraCaptureResult;
        if (r0 == null) goto L7;
        return r0.getAeState();
    L7:
        return CameraCaptureMetaData$AeState.UNKNOWN;
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AfMode getAfMode() {
        CameraCaptureResult r0 = this.mBaseCameraCaptureResult;
        if (r0 == null) goto L7;
        return r0.getAfMode();
    L7:
        return CameraCaptureMetaData$AfMode.UNKNOWN;
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AfState getAfState() {
        CameraCaptureResult r0 = this.mBaseCameraCaptureResult;
        if (r0 == null) goto L7;
        return r0.getAfState();
    L7:
        return CameraCaptureMetaData$AfState.UNKNOWN;
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AwbMode getAwbMode() {
        CameraCaptureResult r0 = this.mBaseCameraCaptureResult;
        if (r0 == null) goto L7;
        return r0.getAwbMode();
    L7:
        return CameraCaptureMetaData$AwbMode.UNKNOWN;
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AwbState getAwbState() {
        CameraCaptureResult r0 = this.mBaseCameraCaptureResult;
        if (r0 == null) goto L7;
        return r0.getAwbState();
    L7:
        return CameraCaptureMetaData$AwbState.UNKNOWN;
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$FlashState getFlashState() {
        CameraCaptureResult r0 = this.mBaseCameraCaptureResult;
        if (r0 == null) goto L7;
        return r0.getFlashState();
    L7:
        return CameraCaptureMetaData$FlashState.UNKNOWN;
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public TagBundle getTagBundle() {
        return this.mTagBundle;
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public long getTimestamp() {
        CameraCaptureResult r0 = this.mBaseCameraCaptureResult;
        if (r0 != null) goto L5;
        long r02 = this.mTimestamp;
        if (r02 == (-1)) goto L9;
        return r02;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("No timestamp is available.");
        return 0;
    L5:
        return r0.getTimestamp();
    }
}
