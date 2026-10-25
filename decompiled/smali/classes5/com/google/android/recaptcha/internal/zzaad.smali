.class public abstract Lcom/google/android/recaptcha/internal/zzaad;
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
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaaa;->zza:Lcom/google/android/recaptcha/internal/zzaaa;

    const-class v1, Lcom/google/android/recaptcha/internal/zzzx;

    const-class v2, Lcom/google/android/recaptcha/internal/zzss;

    invoke-static {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzvp;->zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaad;->zza:Lcom/google/android/recaptcha/internal/zzvp;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaac;->zza:Lcom/google/android/recaptcha/internal/zzaac;

    const-class v1, Lcom/google/android/recaptcha/internal/zzzz;

    const-class v3, Lcom/google/android/recaptcha/internal/zzst;

    invoke-static {v0, v1, v3}, Lcom/google/android/recaptcha/internal/zzvp;->zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaad;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    const-string/jumbo v0, "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey"

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzxj;->zzl()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v1

    invoke-static {v0, v2, v1}, Lcom/google/android/recaptcha/internal/zzug;->zzb(Ljava/lang/String;Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsm;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaad;->zzc:Lcom/google/android/recaptcha/internal/zzsm;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzxl;->zzi()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v0

    const-string/jumbo v1, "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey"

    const/4 v2, 0x5

    invoke-static {v1, v3, v2, v0}, Lcom/google/android/recaptcha/internal/zzug;->zzc(Ljava/lang/String;Ljava/lang/Class;ILcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsf;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaad;->zzd:Lcom/google/android/recaptcha/internal/zzsf;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaab;->zza:Lcom/google/android/recaptcha/internal/zzaab;

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaad;->zze:Lcom/google/android/recaptcha/internal/zztx;

    const/4 v0, 0x2

    sput v0, Lcom/google/android/recaptcha/internal/zzaad;->zzf:I

    return-void
.end method

.method public static zza(Z)V
    .locals 7

    .line 1
    sget p0, Lcom/google/android/recaptcha/internal/zzaad;->zzf:I

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 3
    sget v0, Lcom/google/android/recaptcha/internal/zzacc;->$r8$clinit:I

    .line 4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzvb;->zza()Lcom/google/android/recaptcha/internal/zzvb;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzacc;->zza(Lcom/google/android/recaptcha/internal/zzvb;)V

    .line 5
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzuw;->zza()Lcom/google/android/recaptcha/internal/zzuw;

    move-result-object v0

    new-instance v1, Ljava/util/HashMap;

    .line 6
    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    const-string v2, "RSA_SSA_PKCS1_3072_SHA256_F4"

    .line 7
    sget-object v3, Lcom/google/android/recaptcha/internal/zzzn;->zzh:Lcom/google/android/recaptcha/internal/zzzv;

    invoke-interface {v1, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 8
    sget-object v2, Lcom/google/android/recaptcha/internal/zzzv;->zza:Ljava/math/BigInteger;

    .line 9
    new-instance v2, Lcom/google/android/recaptcha/internal/zzzs;

    const/4 v3, 0x0

    invoke-direct {v2, v3}, Lcom/google/android/recaptcha/internal/zzzs;-><init>([B)V

    sget-object v4, Lcom/google/android/recaptcha/internal/zzzt;->zza:Lcom/google/android/recaptcha/internal/zzzt;

    .line 10
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzzs;->zzd(Lcom/google/android/recaptcha/internal/zzzt;)Lcom/google/android/recaptcha/internal/zzzs;

    const/16 v4, 0xc00

    .line 11
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzzs;->zza(I)Lcom/google/android/recaptcha/internal/zzzs;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzzv;->zza:Ljava/math/BigInteger;

    .line 12
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzzs;->zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzzs;

    sget-object v5, Lcom/google/android/recaptcha/internal/zzzu;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    .line 13
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzzs;->zzc(Lcom/google/android/recaptcha/internal/zzzu;)Lcom/google/android/recaptcha/internal/zzzs;

    .line 14
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzzs;->zze()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object v2

    const-string v6, "RSA_SSA_PKCS1_3072_SHA256_F4_RAW"

    .line 15
    invoke-interface {v1, v6, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX"

    sget-object v6, Lcom/google/android/recaptcha/internal/zzzn;->zzi:Lcom/google/android/recaptcha/internal/zzzv;

    .line 16
    invoke-interface {v1, v2, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "RSA_SSA_PKCS1_4096_SHA512_F4"

    sget-object v6, Lcom/google/android/recaptcha/internal/zzzn;->zzj:Lcom/google/android/recaptcha/internal/zzzv;

    .line 17
    invoke-interface {v1, v2, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzzs;

    .line 18
    invoke-direct {v2, v3}, Lcom/google/android/recaptcha/internal/zzzs;-><init>([B)V

    sget-object v3, Lcom/google/android/recaptcha/internal/zzzt;->zzc:Lcom/google/android/recaptcha/internal/zzzt;

    .line 19
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzzs;->zzd(Lcom/google/android/recaptcha/internal/zzzt;)Lcom/google/android/recaptcha/internal/zzzs;

    const/16 v3, 0x1000

    .line 20
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzzs;->zza(I)Lcom/google/android/recaptcha/internal/zzzs;

    .line 21
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzzs;->zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzzs;

    .line 22
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzzs;->zzc(Lcom/google/android/recaptcha/internal/zzzu;)Lcom/google/android/recaptcha/internal/zzzs;

    .line 23
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzzs;->zze()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object v2

    const-string v3, "RSA_SSA_PKCS1_4096_SHA512_F4_RAW"

    .line 24
    invoke-interface {v1, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 5
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzuw;->zzc(Ljava/util/Map;)V

    .line 25
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaad;->zza:Lcom/google/android/recaptcha/internal/zzvp;

    .line 26
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzb(Lcom/google/android/recaptcha/internal/zzvp;)V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaad;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    .line 27
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzb(Lcom/google/android/recaptcha/internal/zzvp;)V

    .line 28
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzur;->zza()Lcom/google/android/recaptcha/internal/zzur;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaad;->zze:Lcom/google/android/recaptcha/internal/zztx;

    const-class v2, Lcom/google/android/recaptcha/internal/zzzv;

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzur;->zzb(Lcom/google/android/recaptcha/internal/zztx;Ljava/lang/Class;)V

    .line 29
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzty;->zza()Lcom/google/android/recaptcha/internal/zzty;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaad;->zzc:Lcom/google/android/recaptcha/internal/zzsm;

    const/4 v2, 0x1

    .line 30
    invoke-virtual {v0, v1, p0, v2}, Lcom/google/android/recaptcha/internal/zzty;->zzc(Lcom/google/android/recaptcha/internal/zzsf;IZ)V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzty;->zza()Lcom/google/android/recaptcha/internal/zzty;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaad;->zzd:Lcom/google/android/recaptcha/internal/zzsf;

    const/4 v2, 0x0

    .line 31
    invoke-virtual {v0, v1, p0, v2}, Lcom/google/android/recaptcha/internal/zzty;->zzc(Lcom/google/android/recaptcha/internal/zzsf;IZ)V

    return-void

    .line 2
    :cond_0
    const-string p0, "Can not use RSA SSA PKCS1 in FIPS-mode, as BoringCrypto module is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
