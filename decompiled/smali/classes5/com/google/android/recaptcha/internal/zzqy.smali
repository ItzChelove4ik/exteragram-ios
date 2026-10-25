.class abstract Lcom/google/android/recaptcha/internal/zzqy;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzrg;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public zzb([B)Lcom/google/android/recaptcha/internal/zzrg;
    .locals 2

    const/4 v0, 0x0

    .line 1
    array-length v1, p1

    invoke-virtual {p0, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzqy;->zzc([BII)Lcom/google/android/recaptcha/internal/zzrg;

    return-object p0
.end method

.method public abstract zzc([BII)Lcom/google/android/recaptcha/internal/zzrg;
.end method

.method public final zzd(Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)Lcom/google/android/recaptcha/internal/zzrg;
    .locals 0

    .line 1
    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzqy;->zzb([B)Lcom/google/android/recaptcha/internal/zzrg;

    return-object p0
.end method
