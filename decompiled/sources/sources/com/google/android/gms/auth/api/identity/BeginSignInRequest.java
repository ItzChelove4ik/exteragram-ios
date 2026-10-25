package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Deprecated
/* loaded from: classes4.dex */
public final class BeginSignInRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<BeginSignInRequest> CREATOR = null;
    private final PasswordRequestOptions zba;
    private final GoogleIdTokenRequestOptions zbb;
    private final String zbc;
    private final boolean zbd;
    private final int zbe;
    private final PasskeysRequestOptions zbf;
    private final PasskeyJsonRequestOptions zbg;
    private final boolean zbh;

    @Deprecated
    public static final class Builder {
        private PasswordRequestOptions zba;
        private GoogleIdTokenRequestOptions zbb;
        private PasskeysRequestOptions zbc;
        private PasskeyJsonRequestOptions zbd;
        private String zbe;
        private boolean zbf;
        private int zbg;
        private boolean zbh;

        public Builder() {
            PasswordRequestOptions.Builder r0 = PasswordRequestOptions.builder();
            r0.setSupported(false);
            this.zba = r0.build();
            GoogleIdTokenRequestOptions.Builder r02 = GoogleIdTokenRequestOptions.builder();
            r02.setSupported(false);
            this.zbb = r02.build();
            PasskeysRequestOptions.Builder r03 = PasskeysRequestOptions.builder();
            r03.setSupported(false);
            this.zbc = r03.build();
            PasskeyJsonRequestOptions.Builder r04 = PasskeyJsonRequestOptions.builder();
            r04.setSupported(false);
            this.zbd = r04.build();
        }

        public BeginSignInRequest build() {
            return new BeginSignInRequest(this.zba, this.zbb, this.zbe, this.zbf, this.zbg, this.zbc, this.zbd, this.zbh);
        }

        public Builder setAutoSelectEnabled(boolean r1) {
            this.zbf = r1;
            return this;
        }

        public Builder setGoogleIdTokenRequestOptions(GoogleIdTokenRequestOptions r1) {
            this.zbb = (GoogleIdTokenRequestOptions) Preconditions.checkNotNull(r1);
            return this;
        }

        public Builder setPasskeyJsonSignInRequestOptions(PasskeyJsonRequestOptions r1) {
            this.zbd = (PasskeyJsonRequestOptions) Preconditions.checkNotNull(r1);
            return this;
        }

        @Deprecated
        public Builder setPasskeysSignInRequestOptions(PasskeysRequestOptions r1) {
            this.zbc = (PasskeysRequestOptions) Preconditions.checkNotNull(r1);
            return this;
        }

        public Builder setPasswordRequestOptions(PasswordRequestOptions r1) {
            this.zba = (PasswordRequestOptions) Preconditions.checkNotNull(r1);
            return this;
        }

        public Builder setPreferImmediatelyAvailableCredentials(boolean r1) {
            this.zbh = r1;
            return this;
        }

        public final Builder zba(String r1) {
            this.zbe = r1;
            return this;
        }

        public final Builder zbb(int r1) {
            this.zbg = r1;
            return this;
        }
    }

    @Deprecated
    public static final class GoogleIdTokenRequestOptions extends AbstractSafeParcelable {
        public static final Parcelable.Creator<GoogleIdTokenRequestOptions> CREATOR = null;
        private final boolean zba;
        private final String zbb;
        private final String zbc;
        private final boolean zbd;
        private final String zbe;
        private final List zbf;
        private final boolean zbg;
        private final List zbh;
        private final String zbi;

        public static final class Builder {
            private boolean zba;
            private String zbb;
            private String zbc;
            private boolean zbd;
            private String zbe;
            private List zbf;
            private boolean zbg;

            public Builder() {
                this.zba = false;
                this.zbb = null;
                this.zbc = null;
                this.zbd = true;
                this.zbe = null;
                this.zbf = null;
                this.zbg = false;
            }

            public GoogleIdTokenRequestOptions build() {
                return new GoogleIdTokenRequestOptions(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg, null, null);
            }

            public Builder setSupported(boolean r1) {
                this.zba = r1;
                return this;
            }
        }

        static {
            CREATOR = new zbl();
        }

        public GoogleIdTokenRequestOptions(boolean r3, String r4, String r5, boolean r6, String r7, List r8, boolean r9, List r10, String r11) {
            boolean r0 = true;
            if (r6 == false) goto L7;
            if (r9 == false) goto L7;
            r0 = false;
        L7:
            Preconditions.checkArgument(r0, "filterByAuthorizedAccounts and requestVerifiedPhoneNumber must not both be true; the Verified Phone Number feature only works in sign-ups.");
            this.zba = r3;
            if (r3 == false) goto L10;
            Preconditions.checkNotNull(r4, "serverClientId must be provided if Google ID tokens are requested");
        L10:
            this.zbb = r4;
            this.zbc = r5;
            this.zbd = r6;
            Parcelable.Creator<BeginSignInRequest> r32 = BeginSignInRequest.CREATOR;
            ArrayList r33 = null;
            if (r8 != null) goto L13;
        L16:
            this.zbf = r33;
            this.zbe = r7;
            this.zbg = r9;
            this.zbh = r10;
            this.zbi = r11;
            return;
        L13:
            if (r8.isEmpty() == true) goto L16;
            r33 = new ArrayList(r8);
            Collections.sort(r33);
            goto L16
        }

        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof GoogleIdTokenRequestOptions) == true) goto L5;
            return false;
        L5:
            GoogleIdTokenRequestOptions r42 = (GoogleIdTokenRequestOptions) r4;
            if (this.zba == r42.zba) goto L8;
        L25:
            return false;
        L8:
            if (Objects.equal(this.zbb, r42.zbb) == false) goto L25;
            if (Objects.equal(this.zbc, r42.zbc) == false) goto L25;
            if (this.zbd != r42.zbd) goto L25;
            if (Objects.equal(this.zbe, r42.zbe) == false) goto L25;
            if (Objects.equal(this.zbf, r42.zbf) == false) goto L25;
            if (this.zbg != r42.zbg) goto L25;
            if (Objects.equal(this.zbh, r42.zbh) == false) goto L25;
            if (Objects.equal(this.zbi, r42.zbi) == false) goto L25;
            return true;
        }

        public boolean filterByAuthorizedAccounts() {
            return this.zbd;
        }

        public List<String> getIdTokenDepositionScopes() {
            return this.zbf;
        }

        public String getLinkedServiceId() {
            return this.zbe;
        }

        public String getNonce() {
            return this.zbc;
        }

        public String getServerClientId() {
            return this.zbb;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{Boolean.valueOf(this.zba), this.zbb, this.zbc, Boolean.valueOf(this.zbd), this.zbe, this.zbf, Boolean.valueOf(this.zbg), this.zbh, this.zbi});
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Deprecated
        public boolean requestVerifiedPhoneNumber() {
            return this.zbg;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r4, int r5) {
            int r52 = SafeParcelWriter.beginObjectHeader(r4);
            SafeParcelWriter.writeBoolean(r4, 1, isSupported());
            SafeParcelWriter.writeString(r4, 2, getServerClientId(), false);
            SafeParcelWriter.writeString(r4, 3, getNonce(), false);
            SafeParcelWriter.writeBoolean(r4, 4, filterByAuthorizedAccounts());
            SafeParcelWriter.writeString(r4, 5, getLinkedServiceId(), false);
            SafeParcelWriter.writeStringList(r4, 6, getIdTokenDepositionScopes(), false);
            SafeParcelWriter.writeBoolean(r4, 7, requestVerifiedPhoneNumber());
            SafeParcelWriter.writeTypedList(r4, 8, this.zbh, false);
            SafeParcelWriter.writeString(r4, 9, this.zbi, false);
            SafeParcelWriter.finishObjectHeader(r4, r52);
        }
    }

    @Deprecated
    public static final class PasskeyJsonRequestOptions extends AbstractSafeParcelable {
        public static final Parcelable.Creator<PasskeyJsonRequestOptions> CREATOR = null;
        private final boolean zba;
        private final String zbb;

        public static final class Builder {
            private boolean zba;
            private String zbb;

            public Builder() {
                this.zba = false;
            }

            public PasskeyJsonRequestOptions build() {
                return new PasskeyJsonRequestOptions(this.zba, this.zbb);
            }

            public Builder setRequestJson(String r1) {
                this.zbb = r1;
                return this;
            }

            public Builder setSupported(boolean r1) {
                this.zba = r1;
                return this;
            }
        }

        static {
            CREATOR = new zbm();
        }

        public PasskeyJsonRequestOptions(boolean r1, String r2) {
            if (r1 == false) goto L5;
            Preconditions.checkNotNull(r2);
        L5:
            this.zba = r1;
            this.zbb = r2;
        }

        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof PasskeyJsonRequestOptions) == true) goto L8;
            return false;
        L8:
            PasskeyJsonRequestOptions r52 = (PasskeyJsonRequestOptions) r5;
            if (this.zba == r52.zba) goto L11;
        L13:
            return false;
        L11:
            if (Objects.equal(this.zbb, r52.zbb) == false) goto L13;
            return true;
        }

        public String getRequestJson() {
            return this.zbb;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{Boolean.valueOf(this.zba), this.zbb});
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r3, int r4) {
            int r42 = SafeParcelWriter.beginObjectHeader(r3);
            SafeParcelWriter.writeBoolean(r3, 1, isSupported());
            SafeParcelWriter.writeString(r3, 2, getRequestJson(), false);
            SafeParcelWriter.finishObjectHeader(r3, r42);
        }
    }

    @Deprecated
    public static final class PasskeysRequestOptions extends AbstractSafeParcelable {
        public static final Parcelable.Creator<PasskeysRequestOptions> CREATOR = null;
        private final boolean zba;
        private final byte[] zbb;
        private final String zbc;

        public static final class Builder {
            private boolean zba;
            private byte[] zbb;
            private String zbc;

            public Builder() {
                this.zba = false;
            }

            public PasskeysRequestOptions build() {
                return new PasskeysRequestOptions(this.zba, this.zbb, this.zbc);
            }

            public Builder setChallenge(byte[] r1) {
                this.zbb = r1;
                return this;
            }

            public Builder setRpId(String r1) {
                this.zbc = r1;
                return this;
            }

            public Builder setSupported(boolean r1) {
                this.zba = r1;
                return this;
            }
        }

        static {
            CREATOR = new zbn();
        }

        public PasskeysRequestOptions(boolean r1, byte[] r2, String r3) {
            if (r1 == false) goto L5;
            Preconditions.checkNotNull(r2);
            Preconditions.checkNotNull(r3);
        L5:
            this.zba = r1;
            this.zbb = r2;
            this.zbc = r3;
        }

        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof PasskeysRequestOptions) == true) goto L8;
            return false;
        L8:
            PasskeysRequestOptions r52 = (PasskeysRequestOptions) r5;
            if (this.zba == r52.zba) goto L11;
        L15:
            return false;
        L11:
            if (Arrays.equals(this.zbb, r52.zbb) == false) goto L15;
            if (java.util.Objects.equals(this.zbc, r52.zbc) == false) goto L15;
            return true;
        }

        public byte[] getChallenge() {
            return this.zbb;
        }

        public String getRpId() {
            return this.zbc;
        }

        public int hashCode() {
            return (java.util.Objects.hash(new Object[]{Boolean.valueOf(this.zba), this.zbc}) * 31) + Arrays.hashCode(this.zbb);
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r4, int r5) {
            int r52 = SafeParcelWriter.beginObjectHeader(r4);
            SafeParcelWriter.writeBoolean(r4, 1, isSupported());
            SafeParcelWriter.writeByteArray(r4, 2, getChallenge(), false);
            SafeParcelWriter.writeString(r4, 3, getRpId(), false);
            SafeParcelWriter.finishObjectHeader(r4, r52);
        }
    }

    @Deprecated
    public static final class PasswordRequestOptions extends AbstractSafeParcelable {
        public static final Parcelable.Creator<PasswordRequestOptions> CREATOR = null;
        private final boolean zba;

        public static final class Builder {
            private boolean zba;

            public Builder() {
                this.zba = false;
            }

            public PasswordRequestOptions build() {
                return new PasswordRequestOptions(this.zba);
            }

            public Builder setSupported(boolean r1) {
                this.zba = r1;
                return this;
            }
        }

        static {
            CREATOR = new zbo();
        }

        public PasswordRequestOptions(boolean r1) {
            this.zba = r1;
        }

        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(Object r3) {
            if ((r3 instanceof PasswordRequestOptions) == true) goto L6;
            return false;
        L6:
            if (this.zba != ((PasswordRequestOptions) r3).zba) goto L9;
            return true;
        L9:
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{Boolean.valueOf(this.zba)});
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r2, int r3) {
            int r32 = SafeParcelWriter.beginObjectHeader(r2);
            SafeParcelWriter.writeBoolean(r2, 1, isSupported());
            SafeParcelWriter.finishObjectHeader(r2, r32);
        }
    }

    static {
        CREATOR = new zbd();
    }

    public BeginSignInRequest(PasswordRequestOptions r1, GoogleIdTokenRequestOptions r2, String r3, boolean r4, int r5, PasskeysRequestOptions r6, PasskeyJsonRequestOptions r7, boolean r8) {
        this.zba = (PasswordRequestOptions) Preconditions.checkNotNull(r1);
        this.zbb = (GoogleIdTokenRequestOptions) Preconditions.checkNotNull(r2);
        this.zbc = r3;
        this.zbd = r4;
        this.zbe = r5;
        if (r6 != null) goto L5;
        PasskeysRequestOptions.Builder r22 = PasskeysRequestOptions.builder();
        r22.setSupported(false);
        r6 = r22.build();
    L5:
        this.zbf = r6;
        if (r7 != null) goto L8;
        PasskeyJsonRequestOptions.Builder r23 = PasskeyJsonRequestOptions.builder();
        r23.setSupported(false);
        r7 = r23.build();
    L8:
        this.zbg = r7;
        this.zbh = r8;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zba(BeginSignInRequest r2) {
        Preconditions.checkNotNull(r2);
        Builder r0 = builder();
        r0.setGoogleIdTokenRequestOptions(r2.getGoogleIdTokenRequestOptions());
        r0.setPasswordRequestOptions(r2.getPasswordRequestOptions());
        r0.setPasskeysSignInRequestOptions(r2.getPasskeysRequestOptions());
        r0.setPasskeyJsonSignInRequestOptions(r2.getPasskeyJsonRequestOptions());
        r0.setAutoSelectEnabled(r2.zbd);
        r0.zbb(r2.zbe);
        r0.setPreferImmediatelyAvailableCredentials(r2.zbh);
        String r22 = r2.zbc;
        if (r22 == null) goto L5;
        r0.zba(r22);
    L5:
        return r0;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof BeginSignInRequest) == true) goto L5;
        return false;
    L5:
        BeginSignInRequest r42 = (BeginSignInRequest) r4;
        if (Objects.equal(this.zba, r42.zba) == true) goto L8;
    L23:
        return false;
    L8:
        if (Objects.equal(this.zbb, r42.zbb) == false) goto L23;
        if (Objects.equal(this.zbf, r42.zbf) == false) goto L23;
        if (Objects.equal(this.zbg, r42.zbg) == false) goto L23;
        if (Objects.equal(this.zbc, r42.zbc) == false) goto L23;
        if (this.zbd != r42.zbd) goto L23;
        if (this.zbe != r42.zbe) goto L23;
        if (this.zbh != r42.zbh) goto L23;
        return true;
    }

    public GoogleIdTokenRequestOptions getGoogleIdTokenRequestOptions() {
        return this.zbb;
    }

    public PasskeyJsonRequestOptions getPasskeyJsonRequestOptions() {
        return this.zbg;
    }

    public PasskeysRequestOptions getPasskeysRequestOptions() {
        return this.zbf;
    }

    public PasswordRequestOptions getPasswordRequestOptions() {
        return this.zba;
    }

    public boolean getPreferImmediatelyAvailableCredentials() {
        return this.zbh;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba, this.zbb, this.zbf, this.zbg, this.zbc, Boolean.valueOf(this.zbd), Integer.valueOf(this.zbe), Boolean.valueOf(this.zbh)});
    }

    public boolean isAutoSelectEnabled() {
        return this.zbd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 1, getPasswordRequestOptions(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 2, getGoogleIdTokenRequestOptions(), r6, false);
        SafeParcelWriter.writeString(r5, 3, this.zbc, false);
        SafeParcelWriter.writeBoolean(r5, 4, isAutoSelectEnabled());
        SafeParcelWriter.writeInt(r5, 5, this.zbe);
        SafeParcelWriter.writeParcelable(r5, 6, getPasskeysRequestOptions(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 7, getPasskeyJsonRequestOptions(), r6, false);
        SafeParcelWriter.writeBoolean(r5, 8, getPreferImmediatelyAvailableCredentials());
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
