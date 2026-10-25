package com.google.mlkit.vision.segmentation.subject;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzrx;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzrz;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class SubjectSegmenterOptions {
    private final boolean zza;
    private final boolean zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final boolean zze;
    private final Executor zzf;

    public static class Builder {
        private boolean zza;
        private boolean zzb;
        private boolean zzc;
        private boolean zzd;
        private boolean zze;
        private Executor zzf;

        public Builder() {
            this.zza = false;
            this.zzb = false;
            this.zzc = false;
            this.zzd = false;
            this.zze = false;
        }

        public static /* bridge */ /* synthetic */ Executor zza(Builder r0) {
            return r0.zzf;
        }

        public static /* bridge */ /* synthetic */ boolean zzb(Builder r0) {
            return r0.zzb;
        }

        public static /* bridge */ /* synthetic */ boolean zzc(Builder r0) {
            return r0.zza;
        }

        public static /* bridge */ /* synthetic */ boolean zzd(Builder r0) {
            return r0.zzc;
        }

        public static /* bridge */ /* synthetic */ boolean zze(Builder r0) {
            return r0.zze;
        }

        public static /* bridge */ /* synthetic */ boolean zzf(Builder r0) {
            return r0.zzd;
        }

        public SubjectSegmenterOptions build() {
            return new SubjectSegmenterOptions(this, null);
        }

        public Builder enableForegroundBitmap() {
            this.zzb = true;
            return this;
        }

        public Builder enableMultipleSubjects(SubjectResultOptions r2) {
            this.zzc = true;
            this.zzd = SubjectResultOptions.zza(r2);
            this.zze = SubjectResultOptions.zzb(r2);
            return this;
        }
    }

    public static class SubjectResultOptions {
        private boolean zza;
        private boolean zzb;

        public static class Builder {
            private boolean zza;
            private boolean zzb;

            public Builder() {
                this.zza = false;
                this.zzb = false;
            }

            public static /* bridge */ /* synthetic */ boolean zza(Builder r0) {
                return r0.zza;
            }

            public static /* bridge */ /* synthetic */ boolean zzb(Builder r0) {
                return r0.zzb;
            }

            public SubjectResultOptions build() {
                return new SubjectResultOptions(this, null);
            }

            public Builder enableSubjectBitmap() {
                this.zzb = true;
                return this;
            }
        }

        public /* synthetic */ SubjectResultOptions(Builder r1, zza r2) {
            this.zza = false;
            this.zzb = false;
            this.zza = Builder.zza(r1);
            this.zzb = Builder.zzb(r1);
        }

        public static /* bridge */ /* synthetic */ boolean zza(SubjectResultOptions r0) {
            return r0.zza;
        }

        public static /* bridge */ /* synthetic */ boolean zzb(SubjectResultOptions r0) {
            return r0.zzb;
        }

        public boolean equals(Object r5) {
            if (r5 != this) goto L6;
            return true;
        L6:
            if ((r5 instanceof SubjectResultOptions) == true) goto L8;
            return false;
        L8:
            SubjectResultOptions r52 = (SubjectResultOptions) r5;
            if (this.zza == r52.zza) goto L11;
        L13:
            return false;
        L11:
            if (this.zzb != r52.zzb) goto L13;
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{Boolean.valueOf(this.zza), Boolean.valueOf(this.zzb)});
        }
    }

    public /* synthetic */ SubjectSegmenterOptions(Builder r1, zzb r2) {
        this.zza = Builder.zzc(r1);
        this.zzb = Builder.zzb(r1);
        this.zzc = Builder.zzd(r1);
        this.zzd = Builder.zzf(r1);
        this.zze = Builder.zze(r1);
        this.zzf = Builder.zza(r1);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SubjectSegmenterOptions) == true) goto L8;
        return false;
    L8:
        SubjectSegmenterOptions r52 = (SubjectSegmenterOptions) r5;
        if (this.zza == r52.zza) goto L11;
    L21:
        return false;
    L11:
        if (this.zzb != r52.zzb) goto L21;
        if (this.zzc != r52.zzc) goto L21;
        if (this.zzd != r52.zzd) goto L21;
        if (this.zze != r52.zze) goto L21;
        if (Objects.equal(this.zzf, r52.zzf) == false) goto L21;
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zza), Boolean.valueOf(this.zzb), Boolean.valueOf(this.zzc), Boolean.valueOf(this.zzd), Boolean.valueOf(this.zze), this.zzf});
    }

    public final zzrz zza() {
        zzrx r0 = new zzrx();
        r0.zzb(Boolean.valueOf(this.zza));
        r0.zza(Boolean.valueOf(this.zzb));
        r0.zzc(Boolean.valueOf(this.zzc));
        r0.zze(Boolean.valueOf(this.zzd));
        r0.zzd(Boolean.valueOf(this.zze));
        return r0.zzf();
    }

    public final Executor zzb() {
        return this.zzf;
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final boolean zzd() {
        return this.zza;
    }

    public final boolean zze() {
        return this.zzc;
    }

    public final boolean zzf() {
        return this.zze;
    }

    public final boolean zzg() {
        return this.zzd;
    }
}
