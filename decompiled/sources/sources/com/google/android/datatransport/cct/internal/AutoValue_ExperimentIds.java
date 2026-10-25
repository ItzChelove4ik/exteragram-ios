package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ExperimentIds;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class AutoValue_ExperimentIds extends ExperimentIds {
    private final byte[] clearBlob;
    private final byte[] encryptedBlob;

    /* renamed from: com.google.android.datatransport.cct.internal.AutoValue_ExperimentIds$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends ExperimentIds.Builder {
        private byte[] clearBlob;
        private byte[] encryptedBlob;

        public Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.ExperimentIds.Builder
        public ExperimentIds build() {
            return new AutoValue_ExperimentIds(this.clearBlob, this.encryptedBlob, null);
        }

        @Override // com.google.android.datatransport.cct.internal.ExperimentIds.Builder
        public ExperimentIds.Builder setClearBlob(byte[] r1) {
            this.clearBlob = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.ExperimentIds.Builder
        public ExperimentIds.Builder setEncryptedBlob(byte[] r1) {
            this.encryptedBlob = r1;
            return this;
        }
    }

    private AutoValue_ExperimentIds(byte[] r1, byte[] r2) {
        this.clearBlob = r1;
        this.encryptedBlob = r2;
    }

    public /* synthetic */ AutoValue_ExperimentIds(byte[] r1, byte[] r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof ExperimentIds) == false) goto L20;
        ExperimentIds r62 = (ExperimentIds) r6;
        byte[] r1 = this.clearBlob;
        boolean r3 = r62 instanceof AutoValue_ExperimentIds;
        if (r3 == false) goto L10;
        byte[] r4 = ((AutoValue_ExperimentIds) r62).clearBlob;
    L12:
        if (Arrays.equals(r1, r4) == false) goto L20;
        byte[] r5 = this.encryptedBlob;
        if (r3 == false) goto L16;
        byte[] r63 = ((AutoValue_ExperimentIds) r62).encryptedBlob;
    L18:
        if (Arrays.equals(r5, r63) == false) goto L20;
        return true;
    L16:
        r63 = r62.getEncryptedBlob();
        goto L18
    L10:
        r4 = r62.getClearBlob();
    L20:
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.ExperimentIds
    public byte[] getClearBlob() {
        return this.clearBlob;
    }

    @Override // com.google.android.datatransport.cct.internal.ExperimentIds
    public byte[] getEncryptedBlob() {
        return this.encryptedBlob;
    }

    public int hashCode() {
        int r0 = (Arrays.hashCode(this.clearBlob) ^ 1000003) * 1000003;
        return Arrays.hashCode(this.encryptedBlob) ^ r0;
    }

    public String toString() {
        return "ExperimentIds{clearBlob=" + Arrays.toString(this.clearBlob) + ", encryptedBlob=" + Arrays.toString(this.encryptedBlob) + "}";
    }
}
