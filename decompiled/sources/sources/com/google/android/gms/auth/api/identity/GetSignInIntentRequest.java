package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

@Deprecated
/* loaded from: classes4.dex */
public class GetSignInIntentRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetSignInIntentRequest> CREATOR = null;
    private final String zba;
    private final String zbb;
    private final String zbc;
    private final String zbd;
    private final boolean zbe;
    private final int zbf;
    private final List zbg;

    public static final class Builder {
        private String zba;
        private String zbb;
        private String zbc;
        private List zbd;
        private String zbe;
        private boolean zbf;
        private int zbg;

        public Builder() {
        }

        public GetSignInIntentRequest build() {
            return new GetSignInIntentRequest(this.zba, this.zbb, this.zbc, this.zbe, this.zbf, this.zbg, this.zbd);
        }

        public Builder filterByHostedDomain(String r1) {
            this.zbb = r1;
            return this;
        }

        public Builder setNonce(String r1) {
            this.zbe = r1;
            return this;
        }

        @Deprecated
        public Builder setRequestVerifiedPhoneNumber(boolean r1) {
            this.zbf = r1;
            return this;
        }

        public Builder setServerClientId(String r1) {
            Preconditions.checkNotNull(r1);
            this.zba = r1;
            return this;
        }

        public final Builder zba(String r1) {
            this.zbc = r1;
            return this;
        }

        public final Builder zbb(int r1) {
            this.zbg = r1;
            return this;
        }

        public final Builder zbc(List r1) {
            this.zbd = r1;
            return this;
        }
    }

    static {
        CREATOR = new zbk();
    }

    public GetSignInIntentRequest(String r1, String r2, String r3, String r4, boolean r5, int r6, List r7) {
        Preconditions.checkNotNull(r1);
        this.zba = r1;
        this.zbb = r2;
        this.zbc = r3;
        this.zbd = r4;
        this.zbe = r5;
        this.zbf = r6;
        this.zbg = r7;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zba(GetSignInIntentRequest r2) {
        Preconditions.checkNotNull(r2);
        Builder r0 = builder();
        r0.setServerClientId(r2.getServerClientId());
        r0.setNonce(r2.getNonce());
        r0.filterByHostedDomain(r2.getHostedDomainFilter());
        r0.setRequestVerifiedPhoneNumber(r2.zbe);
        r0.zbb(r2.zbf);
        r0.zbc(r2.zbg);
        String r22 = r2.zbc;
        if (r22 == null) goto L5;
        r0.zba(r22);
    L5:
        return r0;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof GetSignInIntentRequest) == true) goto L5;
        return false;
    L5:
        GetSignInIntentRequest r42 = (GetSignInIntentRequest) r4;
        if (Objects.equal(this.zba, r42.zba) == true) goto L8;
    L19:
        return false;
    L8:
        if (Objects.equal(this.zbd, r42.zbd) == false) goto L19;
        if (Objects.equal(this.zbb, r42.zbb) == false) goto L19;
        if (Objects.equal(Boolean.valueOf(this.zbe), Boolean.valueOf(r42.zbe)) == false) goto L19;
        if (this.zbf != r42.zbf) goto L19;
        if (Objects.equal(this.zbg, r42.zbg) == false) goto L19;
        return true;
    }

    public String getHostedDomainFilter() {
        return this.zbb;
    }

    public String getNonce() {
        return this.zbd;
    }

    public String getServerClientId() {
        return this.zba;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba, this.zbb, this.zbd, Boolean.valueOf(this.zbe), Integer.valueOf(this.zbf), this.zbg});
    }

    @Deprecated
    public boolean requestVerifiedPhoneNumber() {
        return this.zbe;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, getServerClientId(), false);
        SafeParcelWriter.writeString(r4, 2, getHostedDomainFilter(), false);
        SafeParcelWriter.writeString(r4, 3, this.zbc, false);
        SafeParcelWriter.writeString(r4, 4, getNonce(), false);
        SafeParcelWriter.writeBoolean(r4, 5, requestVerifiedPhoneNumber());
        SafeParcelWriter.writeInt(r4, 6, this.zbf);
        SafeParcelWriter.writeTypedList(r4, 7, this.zbg, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
