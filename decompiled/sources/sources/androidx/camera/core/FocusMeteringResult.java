package androidx.camera.core;

/* loaded from: classes3.dex */
public final class FocusMeteringResult {
    private boolean mIsFocusSuccessful;

    private FocusMeteringResult(boolean r1) {
        this.mIsFocusSuccessful = r1;
    }

    public static FocusMeteringResult create(boolean r1) {
        return new FocusMeteringResult(r1);
    }

    public static FocusMeteringResult emptyInstance() {
        return new FocusMeteringResult(false);
    }
}
