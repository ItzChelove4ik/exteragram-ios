.class public abstract Lcom/google/android/recaptcha/internal/zzacx;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzst;


# direct methods
.method public static zzb(Lcom/google/android/recaptcha/internal/zzyo;)Lcom/google/android/recaptcha/internal/zzst;
    .locals 6

    const/4 v0, 0x1

    .line 1
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    .line 3
    :try_start_0
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzabv;->zzb(Lcom/google/android/recaptcha/internal/zzyo;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object p0
    :try_end_0
    .catch Ljava/security/GeneralSecurityException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    new-instance v1, Lcom/google/android/recaptcha/internal/zzacw;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzd()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v3

    .line 4
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v3

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v4

    .line 5
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v4

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzf()Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object p0

    sget-object v5, Lcom/google/android/recaptcha/internal/zzyg;->zzc:Lcom/google/android/recaptcha/internal/zzyg;

    .line 6
    invoke-virtual {p0, v5}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    const/4 v5, 0x0

    if-eqz p0, :cond_0

    new-array p0, v0, [B

    aput-byte v5, p0, v5

    goto :goto_0

    .line 7
    :cond_0
    new-array p0, v5, [B

    :goto_0
    invoke-direct {v1, v3, v4, p0, v2}, Lcom/google/android/recaptcha/internal/zzacw;-><init>([B[B[B[B)V

    return-object v1

    .line 2
    :cond_1
    const-string p0, "Can not use Ed25519 in FIPS-mode."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v2
.end method
