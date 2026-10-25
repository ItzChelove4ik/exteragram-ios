.class public abstract Lcom/google/android/recaptcha/internal/zzyn;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzvp;

.field private static final zzb:Lcom/google/android/recaptcha/internal/zzvp;

.field private static final zzc:Lcom/google/android/recaptcha/internal/zzsm;

.field private static final zzd:Lcom/google/android/recaptcha/internal/zzsf;

.field private static final zze:Lcom/google/android/recaptcha/internal/zzus;

.field private static final zzf:Lcom/google/android/recaptcha/internal/zztx;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzyl;->zza:Lcom/google/android/recaptcha/internal/zzyl;

    const-class v1, Lcom/google/android/recaptcha/internal/zzyi;

    const-class v2, Lcom/google/android/recaptcha/internal/zzss;

    invoke-static {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzvp;->zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyn;->zza:Lcom/google/android/recaptcha/internal/zzvp;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzym;->zza:Lcom/google/android/recaptcha/internal/zzym;

    const-class v1, Lcom/google/android/recaptcha/internal/zzyo;

    const-class v3, Lcom/google/android/recaptcha/internal/zzst;

    invoke-static {v0, v1, v3}, Lcom/google/android/recaptcha/internal/zzvp;->zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyn;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    const-string/jumbo v0, "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey"

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwl;->zzg()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v1

    invoke-static {v0, v2, v1}, Lcom/google/android/recaptcha/internal/zzug;->zzb(Ljava/lang/String;Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsm;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyn;->zzc:Lcom/google/android/recaptcha/internal/zzsm;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwn;->zzg()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v0

    const-string/jumbo v1, "type.googleapis.com/google.crypto.tink.Ed25519PublicKey"

    const/4 v2, 0x5

    invoke-static {v1, v3, v2, v0}, Lcom/google/android/recaptcha/internal/zzug;->zzc(Ljava/lang/String;Ljava/lang/Class;ILcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsf;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyn;->zzd:Lcom/google/android/recaptcha/internal/zzsf;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzyk;->zza:Lcom/google/android/recaptcha/internal/zzyk;

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyn;->zze:Lcom/google/android/recaptcha/internal/zzus;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzyj;->zza:Lcom/google/android/recaptcha/internal/zzyj;

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyn;->zzf:Lcom/google/android/recaptcha/internal/zztx;

    return-void
.end method

.method public static zza(Z)V
    .locals 5

    const/4 p0, 0x1

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzvb;->zza()Lcom/google/android/recaptcha/internal/zzvb;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzabt;->zza(Lcom/google/android/recaptcha/internal/zzvb;)V

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzuw;->zza()Lcom/google/android/recaptcha/internal/zzuw;

    move-result-object v0

    new-instance v1, Ljava/util/HashMap;

    .line 4
    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    sget-object v2, Lcom/google/android/recaptcha/internal/zzyg;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzyh;->zza(Lcom/google/android/recaptcha/internal/zzyg;)Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object v2

    const-string v3, "ED25519"

    .line 5
    invoke-interface {v1, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzyg;->zzd:Lcom/google/android/recaptcha/internal/zzyg;

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzyh;->zza(Lcom/google/android/recaptcha/internal/zzyg;)Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object v3

    const-string v4, "ED25519_RAW"

    .line 6
    invoke-interface {v1, v4, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzyh;->zza(Lcom/google/android/recaptcha/internal/zzyg;)Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object v2

    const-string v3, "ED25519WithRawOutput"

    .line 7
    invoke-interface {v1, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 8
    invoke-static {v1}, Ljava/util/Collections;->unmodifiableMap(Ljava/util/Map;)Ljava/util/Map;

    move-result-object v1

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzuw;->zzc(Ljava/util/Map;)V

    .line 9
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzur;->zza()Lcom/google/android/recaptcha/internal/zzur;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyn;->zzf:Lcom/google/android/recaptcha/internal/zztx;

    const-class v2, Lcom/google/android/recaptcha/internal/zzyh;

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzur;->zzb(Lcom/google/android/recaptcha/internal/zztx;Ljava/lang/Class;)V

    .line 10
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzut;->zza()Lcom/google/android/recaptcha/internal/zzut;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyn;->zze:Lcom/google/android/recaptcha/internal/zzus;

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzut;->zzb(Lcom/google/android/recaptcha/internal/zzus;Ljava/lang/Class;)V

    .line 11
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyn;->zza:Lcom/google/android/recaptcha/internal/zzvp;

    .line 12
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzb(Lcom/google/android/recaptcha/internal/zzvp;)V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyn;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    .line 13
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzb(Lcom/google/android/recaptcha/internal/zzvp;)V

    .line 14
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzty;->zza()Lcom/google/android/recaptcha/internal/zzty;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyn;->zzc:Lcom/google/android/recaptcha/internal/zzsm;

    invoke-virtual {v0, v1, p0}, Lcom/google/android/recaptcha/internal/zzty;->zzb(Lcom/google/android/recaptcha/internal/zzsf;Z)V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzty;->zza()Lcom/google/android/recaptcha/internal/zzty;

    move-result-object p0

    sget-object v0, Lcom/google/android/recaptcha/internal/zzyn;->zzd:Lcom/google/android/recaptcha/internal/zzsf;

    const/4 v1, 0x0

    .line 15
    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzty;->zzb(Lcom/google/android/recaptcha/internal/zzsf;Z)V

    return-void

    .line 16
    :cond_0
    const-string p0, "Registering AES GCM SIV is not supported in FIPS mode"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
