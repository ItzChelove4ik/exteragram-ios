.class public abstract Lcom/google/android/recaptcha/internal/zzaap;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzvp;

.field private static final zzb:Lcom/google/android/recaptcha/internal/zzvp;

.field private static final zzc:Lcom/google/android/recaptcha/internal/zzsm;

.field private static final zzd:Lcom/google/android/recaptcha/internal/zzsf;

.field private static final zze:Lcom/google/android/recaptcha/internal/zztx;

.field private static final zzf:I


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaam;->zza:Lcom/google/android/recaptcha/internal/zzaam;

    const-class v1, Lcom/google/android/recaptcha/internal/zzaaj;

    const-class v2, Lcom/google/android/recaptcha/internal/zzss;

    invoke-static {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzvp;->zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaap;->zza:Lcom/google/android/recaptcha/internal/zzvp;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaao;->zza:Lcom/google/android/recaptcha/internal/zzaao;

    const-class v1, Lcom/google/android/recaptcha/internal/zzaal;

    const-class v3, Lcom/google/android/recaptcha/internal/zzst;

    invoke-static {v0, v1, v3}, Lcom/google/android/recaptcha/internal/zzvp;->zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaap;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    const-string/jumbo v0, "type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey"

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzxp;->zzl()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v1

    invoke-static {v0, v2, v1}, Lcom/google/android/recaptcha/internal/zzug;->zzb(Ljava/lang/String;Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsm;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaap;->zzc:Lcom/google/android/recaptcha/internal/zzsm;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzxr;->zzi()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v0

    const-string/jumbo v1, "type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey"

    const/4 v2, 0x5

    invoke-static {v1, v3, v2, v0}, Lcom/google/android/recaptcha/internal/zzug;->zzc(Ljava/lang/String;Ljava/lang/Class;ILcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsf;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaap;->zzd:Lcom/google/android/recaptcha/internal/zzsf;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaan;->zza:Lcom/google/android/recaptcha/internal/zzaan;

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaap;->zze:Lcom/google/android/recaptcha/internal/zztx;

    const/4 v0, 0x2

    sput v0, Lcom/google/android/recaptcha/internal/zzaap;->zzf:I

    return-void
.end method

.method public static zza(Z)V
    .locals 10

    .line 1
    sget p0, Lcom/google/android/recaptcha/internal/zzaap;->zzf:I

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 3
    sget v0, Lcom/google/android/recaptcha/internal/zzacl;->$r8$clinit:I

    .line 4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzvb;->zza()Lcom/google/android/recaptcha/internal/zzvb;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzacl;->zza(Lcom/google/android/recaptcha/internal/zzvb;)V

    .line 5
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzuw;->zza()Lcom/google/android/recaptcha/internal/zzuw;

    move-result-object v0

    new-instance v1, Ljava/util/HashMap;

    .line 6
    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    .line 7
    sget-object v2, Lcom/google/android/recaptcha/internal/zzaah;->zza:Ljava/math/BigInteger;

    .line 8
    new-instance v2, Lcom/google/android/recaptcha/internal/zzaae;

    const/4 v3, 0x0

    invoke-direct {v2, v3}, Lcom/google/android/recaptcha/internal/zzaae;-><init>([B)V

    sget-object v4, Lcom/google/android/recaptcha/internal/zzaaf;->zza:Lcom/google/android/recaptcha/internal/zzaaf;

    .line 9
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzaae;->zzd(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 10
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzaae;->zze(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;

    const/16 v5, 0x20

    .line 11
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzaae;->zzf(I)Lcom/google/android/recaptcha/internal/zzaae;

    const/16 v6, 0xc00

    .line 12
    invoke-virtual {v2, v6}, Lcom/google/android/recaptcha/internal/zzaae;->zza(I)Lcom/google/android/recaptcha/internal/zzaae;

    sget-object v7, Lcom/google/android/recaptcha/internal/zzaah;->zza:Ljava/math/BigInteger;

    .line 13
    invoke-virtual {v2, v7}, Lcom/google/android/recaptcha/internal/zzaae;->zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzaae;

    sget-object v8, Lcom/google/android/recaptcha/internal/zzaag;->zza:Lcom/google/android/recaptcha/internal/zzaag;

    .line 14
    invoke-virtual {v2, v8}, Lcom/google/android/recaptcha/internal/zzaae;->zzc(Lcom/google/android/recaptcha/internal/zzaag;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 15
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaae;->zzg()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v2

    const-string v9, "RSA_SSA_PSS_3072_SHA256_F4"

    .line 16
    invoke-interface {v1, v9, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzaae;

    .line 17
    invoke-direct {v2, v3}, Lcom/google/android/recaptcha/internal/zzaae;-><init>([B)V

    .line 18
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzaae;->zzd(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 19
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzaae;->zze(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 20
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzaae;->zzf(I)Lcom/google/android/recaptcha/internal/zzaae;

    .line 21
    invoke-virtual {v2, v6}, Lcom/google/android/recaptcha/internal/zzaae;->zza(I)Lcom/google/android/recaptcha/internal/zzaae;

    .line 22
    invoke-virtual {v2, v7}, Lcom/google/android/recaptcha/internal/zzaae;->zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzaae;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzaag;->zzd:Lcom/google/android/recaptcha/internal/zzaag;

    .line 23
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzaae;->zzc(Lcom/google/android/recaptcha/internal/zzaag;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 24
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaae;->zzg()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v2

    const-string v5, "RSA_SSA_PSS_3072_SHA256_F4_RAW"

    .line 25
    invoke-interface {v1, v5, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "RSA_SSA_PSS_3072_SHA256_SHA256_32_F4"

    .line 26
    sget-object v5, Lcom/google/android/recaptcha/internal/zzzn;->zzk:Lcom/google/android/recaptcha/internal/zzaah;

    invoke-interface {v1, v2, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzaae;

    .line 27
    invoke-direct {v2, v3}, Lcom/google/android/recaptcha/internal/zzaae;-><init>([B)V

    sget-object v5, Lcom/google/android/recaptcha/internal/zzaaf;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    .line 28
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzaae;->zzd(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 29
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzaae;->zze(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;

    const/16 v6, 0x40

    .line 30
    invoke-virtual {v2, v6}, Lcom/google/android/recaptcha/internal/zzaae;->zzf(I)Lcom/google/android/recaptcha/internal/zzaae;

    const/16 v9, 0x1000

    .line 31
    invoke-virtual {v2, v9}, Lcom/google/android/recaptcha/internal/zzaae;->zza(I)Lcom/google/android/recaptcha/internal/zzaae;

    .line 32
    invoke-virtual {v2, v7}, Lcom/google/android/recaptcha/internal/zzaae;->zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 33
    invoke-virtual {v2, v8}, Lcom/google/android/recaptcha/internal/zzaae;->zzc(Lcom/google/android/recaptcha/internal/zzaag;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 34
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaae;->zzg()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v2

    const-string v8, "RSA_SSA_PSS_4096_SHA512_F4"

    .line 35
    invoke-interface {v1, v8, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzaae;

    .line 36
    invoke-direct {v2, v3}, Lcom/google/android/recaptcha/internal/zzaae;-><init>([B)V

    .line 37
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzaae;->zzd(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 38
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzaae;->zze(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 39
    invoke-virtual {v2, v6}, Lcom/google/android/recaptcha/internal/zzaae;->zzf(I)Lcom/google/android/recaptcha/internal/zzaae;

    .line 40
    invoke-virtual {v2, v9}, Lcom/google/android/recaptcha/internal/zzaae;->zza(I)Lcom/google/android/recaptcha/internal/zzaae;

    .line 41
    invoke-virtual {v2, v7}, Lcom/google/android/recaptcha/internal/zzaae;->zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 42
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzaae;->zzc(Lcom/google/android/recaptcha/internal/zzaag;)Lcom/google/android/recaptcha/internal/zzaae;

    .line 43
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaae;->zzg()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v2

    const-string v3, "RSA_SSA_PSS_4096_SHA512_F4_RAW"

    .line 44
    invoke-interface {v1, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "RSA_SSA_PSS_4096_SHA512_SHA512_64_F4"

    sget-object v3, Lcom/google/android/recaptcha/internal/zzzn;->zzl:Lcom/google/android/recaptcha/internal/zzaah;

    .line 45
    invoke-interface {v1, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 46
    invoke-static {v1}, Ljava/util/Collections;->unmodifiableMap(Ljava/util/Map;)Ljava/util/Map;

    move-result-object v1

    .line 5
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzuw;->zzc(Ljava/util/Map;)V

    .line 47
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaap;->zza:Lcom/google/android/recaptcha/internal/zzvp;

    .line 48
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzb(Lcom/google/android/recaptcha/internal/zzvp;)V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaap;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    .line 49
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzb(Lcom/google/android/recaptcha/internal/zzvp;)V

    .line 50
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzur;->zza()Lcom/google/android/recaptcha/internal/zzur;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaap;->zze:Lcom/google/android/recaptcha/internal/zztx;

    const-class v2, Lcom/google/android/recaptcha/internal/zzaah;

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzur;->zzb(Lcom/google/android/recaptcha/internal/zztx;Ljava/lang/Class;)V

    .line 51
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzty;->zza()Lcom/google/android/recaptcha/internal/zzty;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaap;->zzc:Lcom/google/android/recaptcha/internal/zzsm;

    const/4 v2, 0x1

    .line 52
    invoke-virtual {v0, v1, p0, v2}, Lcom/google/android/recaptcha/internal/zzty;->zzc(Lcom/google/android/recaptcha/internal/zzsf;IZ)V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzty;->zza()Lcom/google/android/recaptcha/internal/zzty;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaap;->zzd:Lcom/google/android/recaptcha/internal/zzsf;

    const/4 v2, 0x0

    .line 53
    invoke-virtual {v0, v1, p0, v2}, Lcom/google/android/recaptcha/internal/zzty;->zzc(Lcom/google/android/recaptcha/internal/zzsf;IZ)V

    return-void

    .line 2
    :cond_0
    const-string p0, "Can not use RSA SSA PSS in FIPS-mode, as BoringCrypto module is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
