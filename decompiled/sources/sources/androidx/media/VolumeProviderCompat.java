package androidx.media;

import android.media.VolumeProvider;
import android.os.Build;

/* loaded from: classes3.dex */
public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private Callback mCallback;
    private final String mControlId;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
    private VolumeProvider mVolumeProviderFwk;

    /* renamed from: androidx.media.VolumeProviderCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends VolumeProvider {
        final /* synthetic */ VolumeProviderCompat this$0;

        public AnonymousClass1(VolumeProviderCompat r1, int r2, int r3, int r4, String r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // android.media.VolumeProvider
        public void onAdjustVolume(int r1) {
            this.this$0.onAdjustVolume(r1);
        }

        @Override // android.media.VolumeProvider
        public void onSetVolumeTo(int r1) {
            this.this$0.onSetVolumeTo(r1);
        }
    }

    /* renamed from: androidx.media.VolumeProviderCompat$2, reason: invalid class name */
    public class AnonymousClass2 extends VolumeProvider {
        final /* synthetic */ VolumeProviderCompat this$0;

        public AnonymousClass2(VolumeProviderCompat r1, int r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.media.VolumeProvider
        public void onAdjustVolume(int r1) {
            this.this$0.onAdjustVolume(r1);
        }

        @Override // android.media.VolumeProvider
        public void onSetVolumeTo(int r1) {
            this.this$0.onSetVolumeTo(r1);
        }
    }

    public static class Api21Impl {
        public static void setCurrentVolume(VolumeProvider r0, int r1) {
            r0.setCurrentVolume(r1);
        }
    }

    public static abstract class Callback {
    }

    public VolumeProviderCompat(int r1, int r2, int r3, String r4) {
        this.mControlType = r1;
        this.mMaxVolume = r2;
        this.mCurrentVolume = r3;
        this.mControlId = r4;
    }

    public final int getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public final int getMaxVolume() {
        return this.mMaxVolume;
    }

    public final int getVolumeControl() {
        return this.mControlType;
    }

    public final String getVolumeControlId() {
        return this.mControlId;
    }

    public Object getVolumeProvider() {
        if (this.mVolumeProviderFwk == null) goto L5;
        VolumeProviderCompat r3 = this;
    L10:
        return r3.mVolumeProviderFwk;
    L5:
        if (Build.VERSION.SDK_INT < 30) goto L7;
        r3 = this;
        r3.mVolumeProviderFwk = new AnonymousClass1(r3, this.mControlType, this.mMaxVolume, this.mCurrentVolume, this.mControlId);
        goto L10
    L7:
        r3 = this;
        r3.mVolumeProviderFwk = new AnonymousClass2(r3, r3.mControlType, r3.mMaxVolume, r3.mCurrentVolume);
        goto L10
    }

    public abstract void onAdjustVolume(int r1);

    public abstract void onSetVolumeTo(int r1);

    public void setCallback(Callback r1) {
    }

    public final void setCurrentVolume(int r1) {
        this.mCurrentVolume = r1;
        Api21Impl.setCurrentVolume((VolumeProvider) getVolumeProvider(), r1);
    }
}
