package androidx.media3.session.legacy;

import android.media.VolumeProvider;
import android.os.Build;

/* loaded from: classes4.dex */
public abstract class VolumeProviderCompat {
    private final String controlId;
    private final int controlType;
    private int currentVolume;
    private final int maxVolume;
    private VolumeProvider volumeProviderFwk;

    /* renamed from: androidx.media3.session.legacy.VolumeProviderCompat$1, reason: invalid class name */
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

    /* renamed from: androidx.media3.session.legacy.VolumeProviderCompat$2, reason: invalid class name */
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

    public VolumeProviderCompat(int r1, int r2, int r3, String r4) {
        this.controlType = r1;
        this.maxVolume = r2;
        this.currentVolume = r3;
        this.controlId = r4;
    }

    public Object getVolumeProvider() {
        if (this.volumeProviderFwk == null) goto L5;
        VolumeProviderCompat r3 = this;
    L10:
        return r3.volumeProviderFwk;
    L5:
        if (Build.VERSION.SDK_INT < 30) goto L7;
        r3 = this;
        r3.volumeProviderFwk = new AnonymousClass1(r3, this.controlType, this.maxVolume, this.currentVolume, this.controlId);
        goto L10
    L7:
        r3 = this;
        r3.volumeProviderFwk = new AnonymousClass2(r3, r3.controlType, r3.maxVolume, r3.currentVolume);
        goto L10
    }

    public abstract void onAdjustVolume(int r1);

    public abstract void onSetVolumeTo(int r1);

    public final void setCurrentVolume(int r1) {
        this.currentVolume = r1;
        ((VolumeProvider) getVolumeProvider()).setCurrentVolume(r1);
    }
}
