package com.google.firebase.appindexing;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.zzc;

/* loaded from: classes5.dex */
public interface Action {

    public static class Builder {
        private final Bundle zza;
        private final String zzb;
        private String zzc;
        private String zzd;
        private String zze;
        private com.google.firebase.appindexing.internal.zzb zzf;
        private String zzg;

        public Builder(String r2) {
            this.zza = new Bundle();
            this.zzb = r2;
        }

        public Action build() {
            Preconditions.checkNotNull(this.zzc, "setObject is required before calling build().");
            Preconditions.checkNotNull(this.zzd, "setObject is required before calling build().");
            String r3 = this.zzb;
            String r4 = this.zzc;
            String r5 = this.zzd;
            String r6 = this.zze;
            com.google.firebase.appindexing.internal.zzb r0 = this.zzf;
            if (r0 != null) goto L6;
            r0 = new Action$Metadata$Builder().zza();
        L6:
            return new zzc(r3, r4, r5, r6, r0, this.zzg, this.zza);
        }

        public Builder put(String r2, String... r3) {
            IndexableBuilder.zza(this.zza, r2, r3);
            return this;
        }

        public Builder setActionStatus(String r1) {
            Preconditions.checkNotNull(r1);
            this.zzg = r1;
            return this;
        }

        public final Builder setName(String r2) {
            Preconditions.checkNotNull(r2);
            this.zzc = r2;
            return put("name", new String[]{r2});
        }

        public final Builder setUrl(String r2) {
            Preconditions.checkNotNull(r2);
            this.zzd = r2;
            return put("url", new String[]{r2});
        }

        public final String zza() {
            String r1 = this.zzc;
            if (r1 != null) goto L7;
            return null;
        L7:
            return new String(r1);
        }

        public final String zzb() {
            String r1 = this.zzd;
            if (r1 != null) goto L7;
            return null;
        L7:
            return new String(r1);
        }

        public final String zzc() {
            return new String(this.zzg);
        }
    }
}
