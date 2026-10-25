.class public abstract Lcom/google/android/recaptcha/internal/zzafa;
.super Lcom/google/android/recaptcha/internal/zzaej;
.source "SourceFile"


# instance fields
.field zza:Ljava/lang/Object;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaej;-><init>()V

    return-void
.end method

.method public static zzu(I)I
    .locals 0

    .line 1
    invoke-static {p0}, Ljava/lang/Integer;->numberOfLeadingZeros(I)I

    move-result p0

    mul-int/lit8 p0, p0, 0x9

    rsub-int p0, p0, 0x160

    ushr-int/lit8 p0, p0, 0x6

    return p0
.end method

.method public static zzv(J)I
    .locals 0

    .line 1
    invoke-static {p0, p1}, Ljava/lang/Long;->numberOfLeadingZeros(J)I

    move-result p0

    mul-int/lit8 p0, p0, 0x9

    rsub-int p0, p0, 0x280

    ushr-int/lit8 p0, p0, 0x6

    return p0
.end method


# virtual methods
.method public abstract zza(II)V
.end method

.method public abstract zzb(II)V
.end method

.method public abstract zzc(II)V
.end method

.method public abstract zzd(II)V
.end method

.method public abstract zze(IJ)V
.end method

.method public abstract zzf(IJ)V
.end method

.method public abstract zzg(IZ)V
.end method

.method public abstract zzh(ILjava/lang/String;)V
.end method

.method public abstract zzi(ILcom/google/android/recaptcha/internal/zzaer;)V
.end method

.method public abstract zzl(B)V
.end method

.method public abstract zzm(I)V
.end method

.method public abstract zzn(I)V
.end method

.method public abstract zzo(I)V
.end method

.method public abstract zzp(J)V
.end method

.method public abstract zzq(J)V
.end method

.method public abstract zzt()I
.end method

.method public final zzw()V
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafa;->zzt()I

    move-result v0

    if-gtz v0, :cond_1

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafa;->zzt()I

    move-result p0

    if-ltz p0, :cond_0

    return-void

    :cond_0
    const-string p0, "Wrote more data than expected."

    .line 2
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 1
    :cond_1
    const-string p0, "Did not write as much data as expected."

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
