.class public abstract Lcom/google/android/recaptcha/internal/zzyf;
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
    sget-object v0, Lcom/google/android/recaptcha/internal/zzyc;->zza:Lcom/google/android/recaptcha/internal/zzyc;

    const-class v1, Lcom/google/android/recaptcha/internal/zzxz;

    const-class v2, Lcom/google/android/recaptcha/internal/zzss;

    invoke-static {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzvp;->zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyf;->zza:Lcom/google/android/recaptcha/internal/zzvp;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzye;->zza:Lcom/google/android/recaptcha/internal/zzye;

    const-class v1, Lcom/google/android/recaptcha/internal/zzyb;

    const-class v3, Lcom/google/android/recaptcha/internal/zzst;

    invoke-static {v0, v1, v3}, Lcom/google/android/recaptcha/internal/zzvp;->zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyf;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    const-string/jumbo v0, "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey"

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwg;->zzg()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v1

    invoke-static {v0, v2, v1}, Lcom/google/android/recaptcha/internal/zzug;->zzb(Ljava/lang/String;Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsm;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyf;->zzc:Lcom/google/android/recaptcha/internal/zzsm;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwi;->zzi()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v0

    const-string/jumbo v1, "type.googleapis.com/google.crypto.tink.EcdsaPublicKey"

    const/4 v2, 0x5

    invoke-static {v1, v3, v2, v0}, Lcom/google/android/recaptcha/internal/zzug;->zzc(Ljava/lang/String;Ljava/lang/Class;ILcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsf;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyf;->zzd:Lcom/google/android/recaptcha/internal/zzsf;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzyd;->zza:Lcom/google/android/recaptcha/internal/zzyd;

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyf;->zze:Lcom/google/android/recaptcha/internal/zztx;

    const/4 v0, 0x2

    sput v0, Lcom/google/android/recaptcha/internal/zzyf;->zzf:I

    return-void
.end method

.method public static zza(Z)V
    .locals 8

    .line 1
    sget p0, Lcom/google/android/recaptcha/internal/zzyf;->zzf:I

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzvb;->zza()Lcom/google/android/recaptcha/internal/zzvb;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzabk;->zza(Lcom/google/android/recaptcha/internal/zzvb;)V

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzuw;->zza()Lcom/google/android/recaptcha/internal/zzuw;

    move-result-object v0

    new-instance v1, Ljava/util/HashMap;

    .line 4
    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    const-string v2, "ECDSA_P256"

    .line 5
    sget-object v3, Lcom/google/android/recaptcha/internal/zzzn;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    invoke-interface {v1, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "ECDSA_P256_IEEE_P1363"

    sget-object v3, Lcom/google/android/recaptcha/internal/zzzn;->zzd:Lcom/google/android/recaptcha/internal/zzxx;

    .line 6
    invoke-interface {v1, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzxs;

    const/4 v3, 0x0

    invoke-direct {v2, v3}, Lcom/google/android/recaptcha/internal/zzxs;-><init>([B)V

    sget-object v4, Lcom/google/android/recaptcha/internal/zzxu;->zza:Lcom/google/android/recaptcha/internal/zzxu;

    .line 7
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zzc(Lcom/google/android/recaptcha/internal/zzxu;)Lcom/google/android/recaptcha/internal/zzxs;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzxt;->zza:Lcom/google/android/recaptcha/internal/zzxt;

    .line 8
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zzb(Lcom/google/android/recaptcha/internal/zzxt;)Lcom/google/android/recaptcha/internal/zzxs;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzxv;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    .line 9
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zza(Lcom/google/android/recaptcha/internal/zzxv;)Lcom/google/android/recaptcha/internal/zzxs;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzxw;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    .line 10
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zzd(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 11
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzxs;->zze()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v2

    const-string v4, "ECDSA_P256_RAW"

    .line 12
    invoke-interface {v1, v4, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "ECDSA_P256_IEEE_P1363_WITHOUT_PREFIX"

    sget-object v4, Lcom/google/android/recaptcha/internal/zzzn;->zzf:Lcom/google/android/recaptcha/internal/zzxx;

    .line 13
    invoke-interface {v1, v2, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "ECDSA_P384"

    sget-object v4, Lcom/google/android/recaptcha/internal/zzzn;->zzb:Lcom/google/android/recaptcha/internal/zzxx;

    .line 14
    invoke-interface {v1, v2, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "ECDSA_P384_IEEE_P1363"

    sget-object v4, Lcom/google/android/recaptcha/internal/zzzn;->zze:Lcom/google/android/recaptcha/internal/zzxx;

    .line 15
    invoke-interface {v1, v2, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzxs;

    invoke-direct {v2, v3}, Lcom/google/android/recaptcha/internal/zzxs;-><init>([B)V

    sget-object v4, Lcom/google/android/recaptcha/internal/zzxu;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    .line 16
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zzc(Lcom/google/android/recaptcha/internal/zzxu;)Lcom/google/android/recaptcha/internal/zzxs;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzxt;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    .line 17
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zzb(Lcom/google/android/recaptcha/internal/zzxt;)Lcom/google/android/recaptcha/internal/zzxs;

    sget-object v5, Lcom/google/android/recaptcha/internal/zzxv;->zzb:Lcom/google/android/recaptcha/internal/zzxv;

    .line 18
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzxs;->zza(Lcom/google/android/recaptcha/internal/zzxv;)Lcom/google/android/recaptcha/internal/zzxs;

    sget-object v6, Lcom/google/android/recaptcha/internal/zzxw;->zza:Lcom/google/android/recaptcha/internal/zzxw;

    .line 19
    invoke-virtual {v2, v6}, Lcom/google/android/recaptcha/internal/zzxs;->zzd(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 20
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzxs;->zze()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v2

    const-string v7, "ECDSA_P384_SHA512"

    .line 21
    invoke-interface {v1, v7, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzxs;

    invoke-direct {v2, v3}, Lcom/google/android/recaptcha/internal/zzxs;-><init>([B)V

    sget-object v3, Lcom/google/android/recaptcha/internal/zzxu;->zzb:Lcom/google/android/recaptcha/internal/zzxu;

    .line 22
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzxs;->zzc(Lcom/google/android/recaptcha/internal/zzxu;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 23
    invoke-virtual {v2, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zzb(Lcom/google/android/recaptcha/internal/zzxt;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 24
    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzxs;->zza(Lcom/google/android/recaptcha/internal/zzxv;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 25
    invoke-virtual {v2, v6}, Lcom/google/android/recaptcha/internal/zzxs;->zzd(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 26
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzxs;->zze()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v2

    const-string v3, "ECDSA_P384_SHA384"

    .line 27
    invoke-interface {v1, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "ECDSA_P521"

    sget-object v3, Lcom/google/android/recaptcha/internal/zzzn;->zzc:Lcom/google/android/recaptcha/internal/zzxx;

    .line 28
    invoke-interface {v1, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "ECDSA_P521_IEEE_P1363"

    sget-object v3, Lcom/google/android/recaptcha/internal/zzzn;->zzg:Lcom/google/android/recaptcha/internal/zzxx;

    .line 29
    invoke-interface {v1, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 30
    invoke-static {v1}, Ljava/util/Collections;->unmodifiableMap(Ljava/util/Map;)Ljava/util/Map;

    move-result-object v1

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzuw;->zzc(Ljava/util/Map;)V

    .line 31
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyf;->zza:Lcom/google/android/recaptcha/internal/zzvp;

    .line 32
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzb(Lcom/google/android/recaptcha/internal/zzvp;)V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyf;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    .line 33
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzb(Lcom/google/android/recaptcha/internal/zzvp;)V

    .line 34
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzur;->zza()Lcom/google/android/recaptcha/internal/zzur;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyf;->zze:Lcom/google/android/recaptcha/internal/zztx;

    const-class v2, Lcom/google/android/recaptcha/internal/zzxx;

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzur;->zzb(Lcom/google/android/recaptcha/internal/zztx;Ljava/lang/Class;)V

    .line 35
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzty;->zza()Lcom/google/android/recaptcha/internal/zzty;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyf;->zzc:Lcom/google/android/recaptcha/internal/zzsm;

    const/4 v2, 0x1

    .line 36
    invoke-virtual {v0, v1, p0, v2}, Lcom/google/android/recaptcha/internal/zzty;->zzc(Lcom/google/android/recaptcha/internal/zzsf;IZ)V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzty;->zza()Lcom/google/android/recaptcha/internal/zzty;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyf;->zzd:Lcom/google/android/recaptcha/internal/zzsf;

    const/4 v2, 0x0

    .line 37
    invoke-virtual {v0, v1, p0, v2}, Lcom/google/android/recaptcha/internal/zzty;->zzc(Lcom/google/android/recaptcha/internal/zzsf;IZ)V

    return-void

    .line 38
    :cond_0
    const-string p0, "Can not use ECDSA in FIPS-mode, as BoringCrypto module is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
