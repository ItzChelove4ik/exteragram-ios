package com.google.android.gms.wallet;

import android.accounts.Account;
import android.content.Context;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.wallet.zzr;
import com.google.android.gms.wallet.wobs.WalletObjects;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class Wallet {
    public static final Api<WalletOptions> API = null;
    public static final WalletObjects zza = null;

    @Deprecated
    public static final zzr zzb = null;
    public static final com.google.android.gms.internal.wallet.zzz zzc = null;
    private static final Api.ClientKey zzd = null;
    private static final Api.AbstractClientBuilder zze = null;

    public static final class WalletOptions implements Api.ApiOptions.HasAccountOptions {
        public final int environment;
        public final int theme;
        public final Account zza;
        final boolean zzb;

        public static final class Builder {
            private int zza;
            private int zzb;
            private boolean zzc;

            public Builder() {
                this.zza = 3;
                this.zzb = 1;
                this.zzc = true;
            }

            public static /* bridge */ /* synthetic */ int zza(Builder r0) {
                return r0.zza;
            }

            public static /* bridge */ /* synthetic */ int zzb(Builder r0) {
                return r0.zzb;
            }

            public static /* bridge */ /* synthetic */ boolean zzc(Builder r0) {
                return r0.zzc;
            }

            public WalletOptions build() {
                return new WalletOptions(this, null);
            }

            public Builder setEnvironment(int r2) {
                if (r2 == 0) goto L16;
                if (r2 != 0) goto L5;
                r2 = 0;
                goto L16
            L5:
                if (r2 == 2) goto L16;
                if (r2 == 1) goto L16;
                if (r2 == 23) goto L16;
                if (r2 == 3) goto L16;
                f$$ExternalSyntheticBUOutline1.m(String.format(Locale.US, "Invalid environment value %d", new Object[]{Integer.valueOf(r2)}));
                return null;
            L16:
                this.zza = r2;
                return this;
            }

            public Builder setTheme(int r2) {
                if (r2 != 0) goto L4;
            L12:
                this.zzb = r2;
                return this;
            L4:
                if (r2 == 1) goto L12;
                if (r2 == 2) goto L12;
                if (r2 == 3) goto L12;
                f$$ExternalSyntheticBUOutline1.m(String.format(Locale.US, "Invalid theme value %d", new Object[]{Integer.valueOf(r2)}));
                return null;
            }
        }

        private WalletOptions(Builder r2) {
            this.environment = Builder.zza(r2);
            this.theme = Builder.zzb(r2);
            this.zzb = Builder.zzc(r2);
            this.zza = null;
        }

        public /* synthetic */ WalletOptions(Builder r1, zzar r2) {
            this(r1);
        }

        public /* synthetic */ WalletOptions(zzar r1) {
            this(new Builder());
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof WalletOptions) == false) goto L14;
            WalletOptions r42 = (WalletOptions) r4;
            if (Objects.equal(Integer.valueOf(this.environment), Integer.valueOf(r42.environment)) == false) goto L14;
            if (Objects.equal(Integer.valueOf(this.theme), Integer.valueOf(r42.theme)) == false) goto L14;
            if (Objects.equal(null, null) == false) goto L14;
            if (Objects.equal(Boolean.valueOf(this.zzb), Boolean.valueOf(r42.zzb)) == false) goto L14;
            return true;
        L14:
            return false;
        }

        @Override // com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions
        public Account getAccount() {
            return null;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{Integer.valueOf(this.environment), Integer.valueOf(this.theme), null, Boolean.valueOf(this.zzb)});
        }
    }

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zzd = r0;
        zzap r1 = new zzap();
        zze = r1;
        API = new Api("Wallet.API", r1, r0);
        zzb = new zzr();
        zza = new com.google.android.gms.internal.wallet.zzab();
        zzc = new com.google.android.gms.internal.wallet.zzz();
    }

    public static PaymentsClient getPaymentsClient(Context r1, WalletOptions r2) {
        return new PaymentsClient(r1, r2);
    }
}
