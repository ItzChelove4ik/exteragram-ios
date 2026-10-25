package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public enum Attachment extends Enum<Attachment> implements Parcelable {
    public static final Parcelable.Creator<Attachment> CREATOR = null;
    public static final Attachment CROSS_PLATFORM = null;
    public static final Attachment PLATFORM = null;
    private static final /* synthetic */ Attachment[] zza = null;
    private final String zzb;

    public static class UnsupportedAttachmentException extends Exception {
        public UnsupportedAttachmentException(String r2) {
            super(String.format("Attachment %s not supported", new Object[]{r2}));
        }
    }

    static {
        Attachment r0 = new Attachment("PLATFORM", 0, "platform");
        PLATFORM = r0;
        Attachment r1 = new Attachment("CROSS_PLATFORM", 1, "cross-platform");
        CROSS_PLATFORM = r1;
        zza = new Attachment[]{r0, r1};
        CREATOR = new zza();
    }

    Attachment(String r1, int r2, String r3) {
        this.zzb = r3;
    }

    public static Attachment fromString(String r5) {
        Attachment[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        Attachment r3 = r0[r2];
        if (r5.equals(r3.zzb) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L9:
        throw new UnsupportedAttachmentException(r5);
    }

    public static Attachment valueOf(String r1) {
        return (Attachment) Enum.valueOf(Attachment.class, r1);
    }

    public static Attachment[] values() {
        return (Attachment[]) zza.clone();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeString(this.zzb);
    }
}
