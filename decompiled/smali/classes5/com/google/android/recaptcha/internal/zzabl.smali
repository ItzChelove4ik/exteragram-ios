.class public final Lcom/google/android/recaptcha/internal/zzabl;
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

    sput-object v1, Lcom/google/android/recaptcha/internal/zzabl;->zza:[B

    const/4 v1, 0x1

    new-array v1, v1, [B

    aput-byte v0, v1, v0

    sput-object v1, Lcom/google/android/recaptcha/internal/zzabl;->zzb:[B

    return-void
.end method

.method private constructor <init>(Ljava/security/interfaces/ECPrivateKey;Lcom/google/android/recaptcha/internal/zzado;Lcom/google/android/recaptcha/internal/zzacz;[B[BLjava/security/Provider;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p0, 0x2

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 3
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzadw;->zza(Lcom/google/android/recaptcha/internal/zzado;)Ljava/lang/String;

    return-void

    .line 2
    :cond_0
    const-string p0, "Can not use ECDSA in FIPS-mode, as BoringCrypto is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzxz;)Lcom/google/android/recaptcha/internal/zzss;
    .locals 7

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzti;->zza()Ljava/security/Provider;

    move-result-object v6

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzabm;->zza:Lcom/google/android/recaptcha/internal/zztv;

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zzd()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzxx;->zzd()Lcom/google/android/recaptcha/internal/zzxu;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v0

    move-object v2, v0

    check-cast v2, Lcom/google/android/recaptcha/internal/zzado;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabm;->zzb:Lcom/google/android/recaptcha/internal/zztv;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zzd()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzxx;->zzb()Lcom/google/android/recaptcha/internal/zzxv;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v0

    move-object v3, v0

    check-cast v3, Lcom/google/android/recaptcha/internal/zzacz;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabm;->zzc:Lcom/google/android/recaptcha/internal/zztv;

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zzd()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzxx;->zzc()Lcom/google/android/recaptcha/internal/zzxt;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzacy;

    .line 6
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzada;->zzb(Lcom/google/android/recaptcha/internal/zzacy;)Ljava/security/spec/ECParameterSpec;

    move-result-object v0

    .line 7
    new-instance v1, Ljava/security/spec/ECPrivateKeySpec;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zzf()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v4

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v5

    .line 8
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v4

    invoke-direct {v1, v4, v0}, Ljava/security/spec/ECPrivateKeySpec;-><init>(Ljava/math/BigInteger;Ljava/security/spec/ECParameterSpec;)V

    const-string v0, "EC"

    if-eqz v6, :cond_0

    .line 9
    invoke-static {v0, v6}, Ljava/security/KeyFactory;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/KeyFactory;

    move-result-object v0

    goto :goto_0

    .line 10
    :cond_0
    sget-object v4, Lcom/google/android/recaptcha/internal/zzadf;->zzc:Lcom/google/android/recaptcha/internal/zzadf;

    invoke-virtual {v4, v0}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/security/KeyFactory;

    .line 11
    :goto_0
    invoke-virtual {v0, v1}, Ljava/security/KeyFactory;->generatePrivate(Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;

    move-result-object v0

    move-object v1, v0

    check-cast v1, Ljava/security/interfaces/ECPrivateKey;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzabl;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zze()Lcom/google/android/recaptcha/internal/zzyb;

    move-result-object v4

    .line 12
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaax;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v4

    .line 13
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v4

    .line 14
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zzd()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxx;->zze()Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object p0

    sget-object v5, Lcom/google/android/recaptcha/internal/zzxw;->zzc:Lcom/google/android/recaptcha/internal/zzxw;

    invoke-virtual {p0, v5}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    sget-object p0, Lcom/google/android/recaptcha/internal/zzabl;->zzb:[B

    :goto_1
    move-object v5, p0

    goto :goto_2

    .line 15
    :cond_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzabl;->zza:[B

    goto :goto_1

    :goto_2
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzabl;-><init>(Ljava/security/interfaces/ECPrivateKey;Lcom/google/android/recaptcha/internal/zzado;Lcom/google/android/recaptcha/internal/zzacz;[B[BLjava/security/Provider;)V

    return-object v0
.end method


# virtual methods
.method public final zza([B)[B
    .locals 0

    const/4 p0, 0x0

    throw p0
.end method
