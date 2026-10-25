package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public class COSEAlgorithmIdentifier implements Parcelable {
    public static final Parcelable.Creator<COSEAlgorithmIdentifier> CREATOR = null;
    private final Algorithm zza;

    public static class UnsupportedAlgorithmIdentifierException extends Exception {
        public UnsupportedAlgorithmIdentifierException(int r3) {
            super("Algorithm with COSE value " + r3 + " not supported");
        }
    }

    static {
        CREATOR = new zzp();
    }

    public COSEAlgorithmIdentifier(Algorithm r1) {
        this.zza = (Algorithm) Preconditions.checkNotNull(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static COSEAlgorithmIdentifier fromCoseValue(int r7) {
        if (r7 != RSAAlgorithm.LEGACY_RS1.getAlgoValue()) goto L5;
        RSAAlgorithm r72 = RSAAlgorithm.RS1;
    L17:
        return new COSEAlgorithmIdentifier(r72);
    L5:
        RSAAlgorithm[] r1 = RSAAlgorithm.values();
        int r2 = r1.length;
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L11;
        RSAAlgorithm r5 = r1[r4];
        if (r5.getAlgoValue() == r7) goto L9;
        r4 = r4 + 1;
        goto L6
    L9:
        r72 = r5;
        goto L17
    L11:
        EC2Algorithm[] r12 = EC2Algorithm.values();
        int r22 = r12.length;
    L12:
        if (r3 >= r22) goto L20;
        RSAAlgorithm r42 = r12[r3];
        if (r42.getAlgoValue() == r7) goto L15;
        r3 = r3 + 1;
        goto L12
    L15:
        r72 = r42;
        goto L17
    L20:
        throw new UnsupportedAlgorithmIdentifierException(r7);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof COSEAlgorithmIdentifier) == true) goto L6;
        return false;
    L6:
        if (this.zza.getAlgoValue() != ((COSEAlgorithmIdentifier) r3).zza.getAlgoValue()) goto L9;
        return true;
    L9:
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza});
    }

    public int toCoseValue() {
        return this.zza.getAlgoValue();
    }

    public final String toString() {
        return "COSEAlgorithmIdentifier{algorithm=" + String.valueOf(this.zza) + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeInt(this.zza.getAlgoValue());
    }
}
