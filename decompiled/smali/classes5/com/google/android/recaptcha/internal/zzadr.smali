.class public abstract Lcom/google/android/recaptcha/internal/zzadr;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzst;


# static fields
.field static final zza:Lcom/google/android/recaptcha/internal/zztv;

.field private static final zzb:[B

.field private static final zzc:[B


# direct methods
.method static constructor <clinit>()V
    .locals 3

    const/4 v0, 0x0

    .line 1
    new-array v1, v0, [B

    sput-object v1, Lcom/google/android/recaptcha/internal/zzadr;->zzb:[B

    const/4 v1, 0x1

    new-array v1, v1, [B

    aput-byte v0, v1, v0

    sput-object v1, Lcom/google/android/recaptcha/internal/zzadr;->zzc:[B

    invoke-static {}, Lcom/google/android/recaptcha/internal/zztv;->zza()Lcom/google/android/recaptcha/internal/zztu;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzado;->zzc:Lcom/google/android/recaptcha/internal/zzado;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzzt;->zza:Lcom/google/android/recaptcha/internal/zzzt;

    .line 2
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzado;->zzd:Lcom/google/android/recaptcha/internal/zzado;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzzt;->zzb:Lcom/google/android/recaptcha/internal/zzzt;

    .line 3
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzado;->zze:Lcom/google/android/recaptcha/internal/zzado;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzzt;->zzc:Lcom/google/android/recaptcha/internal/zzzt;

    .line 4
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    .line 5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zztu;->zzb()Lcom/google/android/recaptcha/internal/zztv;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzadr;->zza:Lcom/google/android/recaptcha/internal/zztv;

    return-void
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzzz;)Lcom/google/android/recaptcha/internal/zzst;
    .locals 7

    .line 1
    :try_start_0
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzace;->zzd(Lcom/google/android/recaptcha/internal/zzzz;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object p0
    :try_end_0
    .catch Ljava/security/NoSuchProviderException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 2
    :catch_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzadf;->zzc:Lcom/google/android/recaptcha/internal/zzadf;

    const-string v1, "RSA"

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/security/KeyFactory;

    .line 3
    new-instance v1, Ljava/security/spec/RSAPublicKeySpec;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zzd()Ljava/math/BigInteger;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zzf()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzzv;->zzc()Ljava/math/BigInteger;

    move-result-object v3

    invoke-direct {v1, v2, v3}, Ljava/security/spec/RSAPublicKeySpec;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    .line 5
    invoke-virtual {v0, v1}, Ljava/security/KeyFactory;->generatePublic(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;

    move-result-object v0

    move-object v2, v0

    check-cast v2, Ljava/security/interfaces/RSAPublicKey;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzadq;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzadr;->zza:Lcom/google/android/recaptcha/internal/zztv;

    .line 6
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zzf()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzzv;->zze()Lcom/google/android/recaptcha/internal/zzzt;

    move-result-object v3

    invoke-virtual {v0, v3}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v0

    move-object v3, v0

    check-cast v3, Lcom/google/android/recaptcha/internal/zzado;

    .line 7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v4

    .line 8
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zzf()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzv;->zzd()Lcom/google/android/recaptcha/internal/zzzu;

    move-result-object p0

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzu;->zzc:Lcom/google/android/recaptcha/internal/zzzu;

    invoke-virtual {p0, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzadr;->zzc:[B

    :goto_0
    move-object v5, p0

    goto :goto_1

    .line 9
    :cond_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzadr;->zzb:[B

    goto :goto_0

    :goto_1
    const/4 v6, 0x0

    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzadq;-><init>(Ljava/security/interfaces/RSAPublicKey;Lcom/google/android/recaptcha/internal/zzado;[B[B[B)V

    return-object v1
.end method
