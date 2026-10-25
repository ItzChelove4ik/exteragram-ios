package androidx.camera.core.impl;

import androidx.camera.core.impl.utils.ExifData;

/* loaded from: classes3.dex */
public interface CameraCaptureResult {

    public static final class EmptyCameraCaptureResult implements CameraCaptureResult {
        public EmptyCameraCaptureResult() {
        }

        public static CameraCaptureResult create() {
            return new EmptyCameraCaptureResult();
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public CameraCaptureMetaData$AeMode getAeMode() {
            return CameraCaptureMetaData$AeMode.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public CameraCaptureMetaData$AeState getAeState() {
            return CameraCaptureMetaData$AeState.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public CameraCaptureMetaData$AfMode getAfMode() {
            return CameraCaptureMetaData$AfMode.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public CameraCaptureMetaData$AfState getAfState() {
            return CameraCaptureMetaData$AfState.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public CameraCaptureMetaData$AwbMode getAwbMode() {
            return CameraCaptureMetaData$AwbMode.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public CameraCaptureMetaData$AwbState getAwbState() {
            return CameraCaptureMetaData$AwbState.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public CameraCaptureMetaData$FlashState getFlashState() {
            return CameraCaptureMetaData$FlashState.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public TagBundle getTagBundle() {
            return TagBundle.emptyBundle();
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public long getTimestamp() {
            return -1;
        }
    }

    CameraCaptureMetaData$AeMode getAeMode();

    CameraCaptureMetaData$AeState getAeState();

    CameraCaptureMetaData$AfMode getAfMode();

    CameraCaptureMetaData$AfState getAfState();

    CameraCaptureMetaData$AwbMode getAwbMode();

    CameraCaptureMetaData$AwbState getAwbState();

    CameraCaptureMetaData$FlashState getFlashState();

    TagBundle getTagBundle();

    long getTimestamp();

    default void populateExifData(ExifData.Builder r1) {
        r1.setFlashState(getFlashState());
    }
}
