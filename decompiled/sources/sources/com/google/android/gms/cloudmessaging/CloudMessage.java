package com.google.android.gms.cloudmessaging;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes.dex */
public final class CloudMessage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CloudMessage> CREATOR = null;
    final Intent zza;

    static {
        CREATOR = new zza();
    }

    public CloudMessage(Intent r1) {
        this.zza = r1;
    }

    public Intent getIntent() {
        return this.zza;
    }

    public String getMessageId() {
        Intent r1 = this.zza;
        String r0 = r1.getStringExtra("google.message_id");
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r1.getStringExtra("message_id");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeParcelable(r4, 1, this.zza, r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }

    public final Integer zza() {
        Intent r2 = this.zza;
        if (r2.hasExtra("google.product_id") == true) goto L5;
        return null;
    L5:
        return Integer.valueOf(r2.getIntExtra("google.product_id", 0));
    }
}
