package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes4.dex */
public class CastMediaOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CastMediaOptions> CREATOR = null;
    private static final Logger zza = null;
    private final String zzb;
    private final String zzc;
    private final zzd zzd;
    private final NotificationOptions zze;
    private final boolean zzf;
    private final boolean zzg;

    public static final class Builder {
        private String zza;
        private String zzb;
        private ImagePicker zzc;
        private NotificationOptions zzd;
        private boolean zze;

        public Builder() {
            this.zza = "com.google.android.gms.cast.framework.media.MediaIntentReceiver";
            this.zzd = new NotificationOptions.Builder().build();
            this.zze = true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0, types: [android.os.IBinder] */
        public CastMediaOptions build() {
            ImagePicker r0 = this.zzc;
            if (r0 != null) goto L6;
            zzd r02 = null;
        L5:
            ?? r4 = r02;
            return new CastMediaOptions(this.zza, this.zzb, r4, this.zzd, false, this.zze);
        L6:
            r02 = r0.zza();
            goto L5
        }

        public Builder setExpandedControllerActivityClassName(String r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setImagePicker(ImagePicker r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setMediaIntentReceiverClassName(String r1) {
            this.zza = r1;
            return this;
        }

        public Builder setMediaSessionEnabled(boolean r1) {
            this.zze = r1;
            return this;
        }

        public Builder setNotificationOptions(NotificationOptions r1) {
            this.zzd = r1;
            return this;
        }
    }

    static {
        zza = new Logger("CastMediaOptions");
        CREATOR = new zza();
    }

    public CastMediaOptions(String r1, String r2, IBinder r3, NotificationOptions r4, boolean r5, boolean r6) {
        this.zzb = r1;
        this.zzc = r2;
        if (r3 != null) goto L5;
        zzd r12 = null;
    L9:
        this.zzd = r12;
        this.zze = r4;
        this.zzf = r5;
        this.zzg = r6;
        return;
    L5:
        IInterface r13 = r3.queryLocalInterface("com.google.android.gms.cast.framework.media.IImagePicker");
        if ((r13 instanceof zzd) == false) goto L8;
        r12 = (zzd) r13;
        goto L9
    L8:
        r12 = new zzb(r3);
        goto L9
    }

    public String getExpandedControllerActivityClassName() {
        return this.zzc;
    }

    public ImagePicker getImagePicker() {
        zzd r3 = this.zzd;
        if (r3 == null) goto L12;
        return (ImagePicker) ObjectWrapper.unwrap(r3.zzf());
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"getWrappedClientObject", zzd.class.getSimpleName()});
        return null;
    L12:
        return null;
    }

    public String getMediaIntentReceiverClassName() {
        return this.zzb;
    }

    public boolean getMediaSessionEnabled() {
        return this.zzg;
    }

    public NotificationOptions getNotificationOptions() {
        return this.zze;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, getMediaIntentReceiverClassName(), false);
        SafeParcelWriter.writeString(r5, 3, getExpandedControllerActivityClassName(), false);
        zzd r1 = this.zzd;
        if (r1 != null) goto L5;
        IBinder r12 = null;
    L6:
        SafeParcelWriter.writeIBinder(r5, 4, r12, false);
        SafeParcelWriter.writeParcelable(r5, 5, getNotificationOptions(), r6, false);
        SafeParcelWriter.writeBoolean(r5, 6, this.zzf);
        SafeParcelWriter.writeBoolean(r5, 7, getMediaSessionEnabled());
        SafeParcelWriter.finishObjectHeader(r5, r0);
        return;
    L5:
        r12 = r1.asBinder();
        goto L6
    }

    public final boolean zza() {
        return this.zzf;
    }
}
