.class abstract Lcom/google/android/recaptcha/internal/zzra;
.super Lcom/google/android/recaptcha/internal/zzqx;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzqx;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)Lcom/google/android/recaptcha/internal/zzre;
    .locals 1

    .line 1
    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p1

    .line 2
    array-length p2, p1

    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0, p2}, Lcom/google/android/recaptcha/internal/zzqx;->zzb([BII)Lcom/google/android/recaptcha/internal/zzre;

    move-result-object p0

    return-object p0
.end method

.method public final zzc()Lcom/google/android/recaptcha/internal/zzrg;
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzqz;

    const/16 v1, 0x20

    invoke-direct {v0, p0, v1}, Lcom/google/android/recaptcha/internal/zzqz;-><init>(Lcom/google/android/recaptcha/internal/zzra;I)V

    return-object v0
.end method
