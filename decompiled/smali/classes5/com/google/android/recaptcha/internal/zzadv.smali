.class public abstract Lcom/google/android/recaptcha/internal/zzadv;
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

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zztv;->zza()Lcom/google/android/recaptcha/internal/zztu;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzado;->zzc:Lcom/google/android/recaptcha/internal/zzado;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzaaf;->zza:Lcom/google/android/recaptcha/internal/zzaaf;

    .line 2
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzado;->zzd:Lcom/google/android/recaptcha/internal/zzado;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzaaf;->zzb:Lcom/google/android/recaptcha/internal/zzaaf;

    .line 3
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzado;->zze:Lcom/google/android/recaptcha/internal/zzado;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzaaf;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    .line 4
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    .line 5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zztu;->zzb()Lcom/google/android/recaptcha/internal/zztv;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzadv;->zza:Lcom/google/android/recaptcha/internal/zztv;

    const/4 v0, 0x0

    new-array v1, v0, [B

    sput-object v1, Lcom/google/android/recaptcha/internal/zzadv;->zzb:[B

    const/4 v1, 0x1

    new-array v1, v1, [B

    aput-byte v0, v1, v0

    sput-object v1, Lcom/google/android/recaptcha/internal/zzadv;->zzc:[B

    return-void
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzaal;)Lcom/google/android/recaptcha/internal/zzst;
    .locals 10

    .line 1
    :try_start_0
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzacn;->zze(Lcom/google/android/recaptcha/internal/zzaal;)Lcom/google/android/recaptcha/internal/zzst;

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

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zzd()Ljava/math/BigInteger;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zzf()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v3

    .line 4
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaah;->zzc()Ljava/math/BigInteger;

    move-result-object v3

    invoke-direct {v1, v2, v3}, Ljava/security/spec/RSAPublicKeySpec;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    .line 5
    invoke-virtual {v0, v1}, Ljava/security/KeyFactory;->generatePublic(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;

    move-result-object v0

    move-object v2, v0

    check-cast v2, Ljava/security/interfaces/RSAPublicKey;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zzf()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v0

    new-instance v1, Lcom/google/android/recaptcha/internal/zzadu;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzadv;->zza:Lcom/google/android/recaptcha/internal/zztv;

    .line 6
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaah;->zze()Lcom/google/android/recaptcha/internal/zzaaf;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v4

    check-cast v4, Lcom/google/android/recaptcha/internal/zzado;

    .line 7
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaah;->zzf()Lcom/google/android/recaptcha/internal/zzaaf;

    move-result-object v5

    invoke-virtual {v3, v5}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v3

    check-cast v3, Lcom/google/android/recaptcha/internal/zzado;

    .line 8
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaah;->zzg()I

    move-result v5

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    .line 9
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v6

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zzf()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object p0

    .line 10
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaah;->zzd()Lcom/google/android/recaptcha/internal/zzaag;

    move-result-object p0

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaag;->zzc:Lcom/google/android/recaptcha/internal/zzaag;

    invoke-virtual {p0, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzadv;->zzc:[B

    :goto_0
    move-object v7, p0

    goto :goto_1

    .line 11
    :cond_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzadv;->zzb:[B

    goto :goto_0

    :goto_1
    const/4 v8, 0x0

    move-object v9, v4

    move-object v4, v3

    move-object v3, v9

    invoke-direct/range {v1 .. v8}, Lcom/google/android/recaptcha/internal/zzadu;-><init>(Ljava/security/interfaces/RSAPublicKey;Lcom/google/android/recaptcha/internal/zzado;Lcom/google/android/recaptcha/internal/zzado;I[B[B[B)V

    return-object v1
.end method
