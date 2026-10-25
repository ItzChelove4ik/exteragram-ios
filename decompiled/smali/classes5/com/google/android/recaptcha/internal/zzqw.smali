.class abstract Lcom/google/android/recaptcha/internal/zzqw;
.super Lcom/google/android/recaptcha/internal/zzqy;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzqy;-><init>()V

    return-void
.end method


# virtual methods
.method public abstract zza([BII)V
.end method

.method public final zzb([B)Lcom/google/android/recaptcha/internal/zzrg;
    .locals 2

    .line 2
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    const/4 v0, 0x0

    array-length v1, p1

    .line 1
    invoke-virtual {p0, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzqw;->zza([BII)V

    return-object p0
.end method

.method public final zzc([BII)Lcom/google/android/recaptcha/internal/zzrg;
    .locals 1

    .line 1
    array-length p2, p1

    const/4 v0, 0x0

    invoke-static {v0, p3, p2}, Lcom/google/android/recaptcha/internal/zzoq;->zzg(III)V

    .line 2
    invoke-virtual {p0, p1, v0, p3}, Lcom/google/android/recaptcha/internal/zzqw;->zza([BII)V

    return-object p0
.end method
