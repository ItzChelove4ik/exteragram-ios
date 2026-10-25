.class public abstract Lcom/google/android/recaptcha/internal/zzadt;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzss;


# static fields
.field private static final zza:[B

.field private static final zzb:[B


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const/4 v0, 0x0

    new-array v1, v0, [B

    sput-object v1, Lcom/google/android/recaptcha/internal/zzadt;->zza:[B

    const/4 v1, 0x1

    new-array v1, v1, [B

    aput-byte v0, v1, v0

    sput-object v1, Lcom/google/android/recaptcha/internal/zzadt;->zzb:[B

    return-void
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzaaj;)Lcom/google/android/recaptcha/internal/zzss;
    .locals 12

    .line 1
    :try_start_0
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzacm;->zzb(Lcom/google/android/recaptcha/internal/zzaaj;)Lcom/google/android/recaptcha/internal/zzss;

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
    new-instance v1, Ljava/security/spec/RSAPrivateCrtKeySpec;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zze()Lcom/google/android/recaptcha/internal/zzaal;

    move-result-object v2

    .line 4
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaal;->zzd()Ljava/math/BigInteger;

    move-result-object v2

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzd()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaah;->zzc()Ljava/math/BigInteger;

    move-result-object v3

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzi()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v4

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v5

    .line 6
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v4

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzf()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v5

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v6

    .line 7
    invoke-virtual {v5, v6}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v5

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzh()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v6

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v7

    .line 8
    invoke-virtual {v6, v7}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v6

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzj()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v7

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v8

    .line 9
    invoke-virtual {v7, v8}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v7

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzk()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v8

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v9

    .line 10
    invoke-virtual {v8, v9}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v8

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzl()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v9

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v10

    .line 11
    invoke-virtual {v9, v10}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v9

    invoke-direct/range {v1 .. v9}, Ljava/security/spec/RSAPrivateCrtKeySpec;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    .line 12
    invoke-virtual {v0, v1}, Ljava/security/KeyFactory;->generatePrivate(Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;

    move-result-object v0

    move-object v2, v0

    check-cast v2, Ljava/security/interfaces/RSAPrivateCrtKey;

    .line 13
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzd()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v0

    new-instance v1, Lcom/google/android/recaptcha/internal/zzads;

    .line 14
    sget-object v3, Lcom/google/android/recaptcha/internal/zzadv;->zza:Lcom/google/android/recaptcha/internal/zztv;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaah;->zze()Lcom/google/android/recaptcha/internal/zzaaf;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v4

    check-cast v4, Lcom/google/android/recaptcha/internal/zzado;

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaah;->zzf()Lcom/google/android/recaptcha/internal/zzaaf;

    move-result-object v5

    invoke-virtual {v3, v5}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v3

    check-cast v3, Lcom/google/android/recaptcha/internal/zzado;

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaah;->zzg()I

    move-result v5

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zze()Lcom/google/android/recaptcha/internal/zzaal;

    move-result-object v0

    .line 18
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaax;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    .line 19
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v6

    .line 20
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzd()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaah;->zzd()Lcom/google/android/recaptcha/internal/zzaag;

    move-result-object p0

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaag;->zzc:Lcom/google/android/recaptcha/internal/zzaag;

    invoke-virtual {p0, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzadt;->zzb:[B

    :goto_0
    move-object v7, p0

    goto :goto_1

    .line 21
    :cond_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzadt;->zza:[B

    goto :goto_0

    :goto_1
    const/4 v8, 0x0

    move-object v11, v4

    move-object v4, v3

    move-object v3, v11

    invoke-direct/range {v1 .. v8}, Lcom/google/android/recaptcha/internal/zzads;-><init>(Ljava/security/interfaces/RSAPrivateCrtKey;Lcom/google/android/recaptcha/internal/zzado;Lcom/google/android/recaptcha/internal/zzado;I[B[B[B)V

    return-object v1
.end method
