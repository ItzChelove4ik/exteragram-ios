package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class GetPhoneNumberHintIntentRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetPhoneNumberHintIntentRequest> CREATOR = null;
    private final int zba;

    public static final class Builder {
        public /* synthetic */ Builder(byte[] r1) {
        }

        public GetPhoneNumberHintIntentRequest build() {
            return new GetPhoneNumberHintIntentRequest(0);
        }
    }

    static {
        CREATOR = new zbj();
    }

    public GetPhoneNumberHintIntentRequest(int r1) {
        this.zba = r1;
    }

    public static Builder builder() {
        return new Builder(null);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof GetPhoneNumberHintIntentRequest) == true) goto L7;
        return false;
    L7:
        return Objects.equal(Integer.valueOf(this.zba), Integer.valueOf(((GetPhoneNumberHintIntentRequest) r2).zba));
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zba)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r2, int r3) {
        int r1 = this.zba;
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeInt(r2, 1, r1);
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }
}
