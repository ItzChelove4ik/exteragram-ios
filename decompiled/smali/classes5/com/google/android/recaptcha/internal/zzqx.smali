.class abstract Lcom/google/android/recaptcha/internal/zzqx;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzrf;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public zza(Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)Lcom/google/android/recaptcha/internal/zzre;
    .locals 0

    .line 1
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzrf;->zzc()Lcom/google/android/recaptcha/internal/zzrg;

    move-result-object p0

    invoke-interface {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzrg;->zzd(Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)Lcom/google/android/recaptcha/internal/zzrg;

    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzrg;->zzf()Lcom/google/android/recaptcha/internal/zzre;

    move-result-object p0

    return-object p0
.end method

.method public zzb([BII)Lcom/google/android/recaptcha/internal/zzre;
    .locals 0

    const/4 p0, 0x0

    throw p0
.end method
