.class public abstract Lcom/google/android/recaptcha/internal/zzabk;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzvi;

.field private static final zzb:Lcom/google/android/recaptcha/internal/zzvf;

.field private static final zzc:Lcom/google/android/recaptcha/internal/zzue;

.field private static final zzd:Lcom/google/android/recaptcha/internal/zzub;

.field private static final zze:Lcom/google/android/recaptcha/internal/zzue;

.field private static final zzf:Lcom/google/android/recaptcha/internal/zzub;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabj;->zza:Lcom/google/android/recaptcha/internal/zzabj;

    const-class v1, Lcom/google/android/recaptcha/internal/zzxx;

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzvi;->zzb(Lcom/google/android/recaptcha/internal/zzvh;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvi;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zza:Lcom/google/android/recaptcha/internal/zzvi;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabe;->zza:Lcom/google/android/recaptcha/internal/zzabe;

    const-string/jumbo v1, "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey"

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzvf;->zzb(Lcom/google/android/recaptcha/internal/zzve;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzvf;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zzb:Lcom/google/android/recaptcha/internal/zzvf;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabf;->zza:Lcom/google/android/recaptcha/internal/zzabf;

    const-class v2, Lcom/google/android/recaptcha/internal/zzyb;

    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzue;->zzc(Lcom/google/android/recaptcha/internal/zzud;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzue;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zzc:Lcom/google/android/recaptcha/internal/zzue;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabg;->zza:Lcom/google/android/recaptcha/internal/zzabg;

    const-string/jumbo v2, "type.googleapis.com/google.crypto.tink.EcdsaPublicKey"

    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzub;->zzc(Lcom/google/android/recaptcha/internal/zzua;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzub;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zzd:Lcom/google/android/recaptcha/internal/zzub;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabh;->zza:Lcom/google/android/recaptcha/internal/zzabh;

    const-class v2, Lcom/google/android/recaptcha/internal/zzxz;

    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzue;->zzc(Lcom/google/android/recaptcha/internal/zzud;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzue;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zze:Lcom/google/android/recaptcha/internal/zzue;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabi;->zza:Lcom/google/android/recaptcha/internal/zzabi;

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzub;->zzc(Lcom/google/android/recaptcha/internal/zzua;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzub;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zzf:Lcom/google/android/recaptcha/internal/zzub;

    return-void
.end method

.method public static zza(Lcom/google/android/recaptcha/internal/zzvb;)V
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zza:Lcom/google/android/recaptcha/internal/zzvi;

    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzd(Lcom/google/android/recaptcha/internal/zzvi;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zzb:Lcom/google/android/recaptcha/internal/zzvf;

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zze(Lcom/google/android/recaptcha/internal/zzvf;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zzc:Lcom/google/android/recaptcha/internal/zzue;

    .line 3
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzb(Lcom/google/android/recaptcha/internal/zzue;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zzd:Lcom/google/android/recaptcha/internal/zzub;

    .line 4
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzc(Lcom/google/android/recaptcha/internal/zzub;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zze:Lcom/google/android/recaptcha/internal/zzue;

    .line 5
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzb(Lcom/google/android/recaptcha/internal/zzue;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabk;->zzf:Lcom/google/android/recaptcha/internal/zzub;

    .line 6
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzc(Lcom/google/android/recaptcha/internal/zzub;)V

    return-void
.end method

.method public static synthetic zzb(Lcom/google/android/recaptcha/internal/zzyb;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;
    .locals 3

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzabk;->zzj(Lcom/google/android/recaptcha/internal/zzyb;)Lcom/google/android/recaptcha/internal/zzwi;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaec;->zzv()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zzd:Lcom/google/android/recaptcha/internal/zzsn;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzf()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzxx;->zze()Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzabk;->zzf(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v1

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzb()Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v2, "type.googleapis.com/google.crypto.tink.EcdsaPublicKey"

    .line 4
    invoke-static {v2, p1, v0, v1, p0}, Lcom/google/android/recaptcha/internal/zzsp;->zza(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzsn;Lcom/google/android/recaptcha/internal/zzso;Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zzc(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzyb;
    .locals 6

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object p1

    const-string/jumbo v0, "type.googleapis.com/google.crypto.tink.EcdsaPublicKey"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    .line 2
    :try_start_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzc()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafh;->zzb()Lcom/google/android/recaptcha/internal/zzafh;

    move-result-object v1

    .line 4
    invoke-static {p1, v1}, Lcom/google/android/recaptcha/internal/zzwi;->zze(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzwi;

    move-result-object p1

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzwi;->zza()I

    move-result v1

    if-nez v1, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzxx;->zza()Lcom/google/android/recaptcha/internal/zzxs;

    move-result-object v1

    .line 6
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzwi;->zzb()Lcom/google/android/recaptcha/internal/zzwe;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwe;->zza()Lcom/google/android/recaptcha/internal/zzwp;

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzabk;->zzg(Lcom/google/android/recaptcha/internal/zzwp;)Lcom/google/android/recaptcha/internal/zzxu;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzxs;->zzc(Lcom/google/android/recaptcha/internal/zzxu;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzwi;->zzb()Lcom/google/android/recaptcha/internal/zzwe;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwe;->zzh()I

    move-result v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzabk;->zzl(I)Lcom/google/android/recaptcha/internal/zzxv;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzxs;->zza(Lcom/google/android/recaptcha/internal/zzxv;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzwi;->zzb()Lcom/google/android/recaptcha/internal/zzwe;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwe;->zzg()I

    move-result v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzabk;->zzk(I)Lcom/google/android/recaptcha/internal/zzxt;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzxs;->zzb(Lcom/google/android/recaptcha/internal/zzxt;)Lcom/google/android/recaptcha/internal/zzxs;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v2

    .line 9
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzabk;->zzh(Lcom/google/android/recaptcha/internal/zzso;)Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzxs;->zzd(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 10
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzxs;->zze()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzyb;->zzc()Lcom/google/android/recaptcha/internal/zzya;

    move-result-object v2

    .line 11
    invoke-virtual {v2, v1}, Lcom/google/android/recaptcha/internal/zzya;->zza(Lcom/google/android/recaptcha/internal/zzxx;)Lcom/google/android/recaptcha/internal/zzya;

    new-instance v1, Ljava/security/spec/ECPoint;

    .line 12
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzwi;->zzc()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object v3

    new-instance v4, Ljava/math/BigInteger;

    const/4 v5, 0x1

    .line 13
    invoke-direct {v4, v5, v3}, Ljava/math/BigInteger;-><init>(I[B)V

    .line 14
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzwi;->zzd()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p1

    new-instance v3, Ljava/math/BigInteger;

    .line 15
    invoke-direct {v3, v5, p1}, Ljava/math/BigInteger;-><init>(I[B)V

    .line 14
    invoke-direct {v1, v4, v3}, Ljava/security/spec/ECPoint;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    .line 16
    invoke-virtual {v2, v1}, Lcom/google/android/recaptcha/internal/zzya;->zzb(Ljava/security/spec/ECPoint;)Lcom/google/android/recaptcha/internal/zzya;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzf()Ljava/lang/Integer;

    move-result-object p0

    .line 17
    invoke-virtual {v2, p0}, Lcom/google/android/recaptcha/internal/zzya;->zzc(Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzya;

    .line 18
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzya;->zzd()Lcom/google/android/recaptcha/internal/zzyb;

    move-result-object p0

    return-object p0

    .line 19
    :cond_0
    new-instance p0, Ljava/security/GeneralSecurityException;

    const-string p1, "Only version 0 keys are accepted"

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    .line 20
    :catch_0
    const-string p0, "Parsing EcdsaPublicKey failed"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    .line 1
    :cond_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object p0

    .line 2
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string p1, "Wrong type URL in call to EcdsaProtoSerialization.parsePublicKey: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0
.end method

.method public static synthetic zzd(Lcom/google/android/recaptcha/internal/zzxz;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;
    .locals 3

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zzd()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zzc()Lcom/google/android/recaptcha/internal/zzxt;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzabk;->zzi(Lcom/google/android/recaptcha/internal/zzxt;)I

    move-result v0

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwg;->zze()Lcom/google/android/recaptcha/internal/zzwf;

    move-result-object v1

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zze()Lcom/google/android/recaptcha/internal/zzyb;

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzabk;->zzj(Lcom/google/android/recaptcha/internal/zzyb;)Lcom/google/android/recaptcha/internal/zzwi;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzwf;->zza(Lcom/google/android/recaptcha/internal/zzwi;)Lcom/google/android/recaptcha/internal/zzwf;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zzf()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v2

    invoke-virtual {v2, p1}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object p1

    .line 5
    invoke-static {p1, v0}, Lcom/google/android/recaptcha/internal/zzth;->zzb(Ljava/math/BigInteger;I)[B

    move-result-object p1

    .line 6
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    .line 7
    array-length v0, p1

    const/4 v2, 0x0

    invoke-static {p1, v2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    .line 8
    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzwf;->zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzwf;

    .line 9
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzwg;

    .line 10
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaec;->zzv()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zzc:Lcom/google/android/recaptcha/internal/zzsn;

    .line 11
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zzd()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzxx;->zze()Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzabk;->zzf(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v1

    .line 12
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxz;->zze()Lcom/google/android/recaptcha/internal/zzyb;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzse;->zzb()Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v2, "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey"

    .line 13
    invoke-static {v2, p1, v0, v1, p0}, Lcom/google/android/recaptcha/internal/zzsp;->zza(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzsn;Lcom/google/android/recaptcha/internal/zzso;Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zze(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzxz;
    .locals 8

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object v0

    const-string/jumbo v1, "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    .line 2
    :try_start_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzc()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v0

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafh;->zzb()Lcom/google/android/recaptcha/internal/zzafh;

    move-result-object v2

    .line 4
    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzwg;->zzd(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzwg;

    move-result-object v0

    .line 5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzwg;->zza()I

    move-result v2
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    const-string v3, "Only version 0 keys are accepted"

    if-nez v2, :cond_1

    .line 7
    :try_start_1
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzwg;->zzb()Lcom/google/android/recaptcha/internal/zzwi;

    move-result-object v2

    .line 8
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwi;->zza()I

    move-result v4

    if-nez v4, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzxx;->zza()Lcom/google/android/recaptcha/internal/zzxs;

    move-result-object v3

    .line 9
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwi;->zzb()Lcom/google/android/recaptcha/internal/zzwe;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzwe;->zza()Lcom/google/android/recaptcha/internal/zzwp;

    move-result-object v4

    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzabk;->zzg(Lcom/google/android/recaptcha/internal/zzwp;)Lcom/google/android/recaptcha/internal/zzxu;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zzc(Lcom/google/android/recaptcha/internal/zzxu;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 10
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwi;->zzb()Lcom/google/android/recaptcha/internal/zzwe;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzwe;->zzh()I

    move-result v4

    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzabk;->zzl(I)Lcom/google/android/recaptcha/internal/zzxv;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zza(Lcom/google/android/recaptcha/internal/zzxv;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 11
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwi;->zzb()Lcom/google/android/recaptcha/internal/zzwe;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzwe;->zzg()I

    move-result v4

    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzabk;->zzk(I)Lcom/google/android/recaptcha/internal/zzxt;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zzb(Lcom/google/android/recaptcha/internal/zzxt;)Lcom/google/android/recaptcha/internal/zzxs;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v4

    .line 12
    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzabk;->zzh(Lcom/google/android/recaptcha/internal/zzso;)Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzxs;->zzd(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 13
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzxs;->zze()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v3

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzyb;->zzc()Lcom/google/android/recaptcha/internal/zzya;

    move-result-object v4

    .line 14
    invoke-virtual {v4, v3}, Lcom/google/android/recaptcha/internal/zzya;->zza(Lcom/google/android/recaptcha/internal/zzxx;)Lcom/google/android/recaptcha/internal/zzya;

    new-instance v3, Ljava/security/spec/ECPoint;

    .line 15
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwi;->zzc()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v5

    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object v5

    new-instance v6, Ljava/math/BigInteger;

    const/4 v7, 0x1

    .line 16
    invoke-direct {v6, v7, v5}, Ljava/math/BigInteger;-><init>(I[B)V

    .line 17
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwi;->zzd()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object v2

    new-instance v5, Ljava/math/BigInteger;

    .line 18
    invoke-direct {v5, v7, v2}, Ljava/math/BigInteger;-><init>(I[B)V

    .line 19
    invoke-direct {v3, v6, v5}, Ljava/security/spec/ECPoint;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    .line 20
    invoke-virtual {v4, v3}, Lcom/google/android/recaptcha/internal/zzya;->zzb(Ljava/security/spec/ECPoint;)Lcom/google/android/recaptcha/internal/zzya;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzf()Ljava/lang/Integer;

    move-result-object p0

    .line 21
    invoke-virtual {v4, p0}, Lcom/google/android/recaptcha/internal/zzya;->zzc(Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzya;

    .line 22
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzya;->zzd()Lcom/google/android/recaptcha/internal/zzyb;

    move-result-object p0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzxz;->zzc()Lcom/google/android/recaptcha/internal/zzxy;

    move-result-object v2

    .line 23
    invoke-virtual {v2, p0}, Lcom/google/android/recaptcha/internal/zzxy;->zza(Lcom/google/android/recaptcha/internal/zzyb;)Lcom/google/android/recaptcha/internal/zzxy;

    .line 24
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzwg;->zzc()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p0

    new-instance v0, Ljava/math/BigInteger;

    .line 25
    invoke-direct {v0, v7, p0}, Ljava/math/BigInteger;-><init>(I[B)V

    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzadz;->zza(Ljava/math/BigInteger;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object p0

    .line 26
    invoke-virtual {v2, p0}, Lcom/google/android/recaptcha/internal/zzxy;->zzb(Lcom/google/android/recaptcha/internal/zzadz;)Lcom/google/android/recaptcha/internal/zzxy;

    .line 27
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzxy;->zzc()Lcom/google/android/recaptcha/internal/zzxz;

    move-result-object p0

    return-object p0

    .line 28
    :cond_0
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-direct {p0, v3}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 6
    :cond_1
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-direct {p0, v3}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_1 .. :try_end_1} :catch_0

    .line 29
    :catch_0
    const-string p0, "Parsing EcdsaPrivateKey failed"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 1
    :cond_2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object p0

    .line 2
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string p1, "Wrong type URL in call to EcdsaProtoSerialization.parsePrivateKey: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method

.method private static zzf(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzso;
    .locals 2

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzxw;->zza:Lcom/google/android/recaptcha/internal/zzxw;

    invoke-virtual {p0, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    return-object p0

    :cond_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzxw;->zzb:Lcom/google/android/recaptcha/internal/zzxw;

    if-eq p0, v0, :cond_3

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzxw;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    if-eq p0, v0, :cond_2

    .line 3
    sget-object v0, Lcom/google/android/recaptcha/internal/zzxw;->zzc:Lcom/google/android/recaptcha/internal/zzxw;

    if-ne p0, v0, :cond_1

    .line 5
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zzc:Lcom/google/android/recaptcha/internal/zzso;

    return-object p0

    :cond_1
    new-instance v0, Ljava/security/GeneralSecurityException;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v1, "Unable to serialize variant: "

    invoke-virtual {v1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 3
    :cond_2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    return-object p0

    .line 2
    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zze:Lcom/google/android/recaptcha/internal/zzso;

    return-object p0
.end method

.method private static zzg(Lcom/google/android/recaptcha/internal/zzwp;)Lcom/google/android/recaptcha/internal/zzxu;
    .locals 3

    .line 1
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result v0

    const/4 v1, 0x2

    if-eq v0, v1, :cond_2

    const/4 v1, 0x3

    if-eq v0, v1, :cond_1

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    .line 3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxu;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    return-object p0

    .line 2
    :cond_0
    new-instance v0, Ljava/security/GeneralSecurityException;

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwp;->zza()I

    move-result p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    add-int/lit8 v1, v1, 0x1a

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "Unable to parse HashType: "

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxu;->zza:Lcom/google/android/recaptcha/internal/zzxu;

    return-object p0

    :cond_2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxu;->zzb:Lcom/google/android/recaptcha/internal/zzxu;

    return-object p0
.end method

.method private static zzh(Lcom/google/android/recaptcha/internal/zzso;)Lcom/google/android/recaptcha/internal/zzxw;
    .locals 2

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    invoke-virtual {p0, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzxw;->zza:Lcom/google/android/recaptcha/internal/zzxw;

    return-object p0

    :cond_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzso;->zze:Lcom/google/android/recaptcha/internal/zzso;

    if-eq p0, v0, :cond_3

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzc:Lcom/google/android/recaptcha/internal/zzso;

    if-eq p0, v0, :cond_2

    .line 3
    sget-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    if-ne p0, v0, :cond_1

    .line 5
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxw;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    return-object p0

    :cond_1
    new-instance v0, Ljava/security/GeneralSecurityException;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v1, "Unable to parse OutputPrefixType: "

    invoke-virtual {v1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 3
    :cond_2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxw;->zzc:Lcom/google/android/recaptcha/internal/zzxw;

    return-object p0

    .line 2
    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxw;->zzb:Lcom/google/android/recaptcha/internal/zzxw;

    return-object p0
.end method

.method private static zzi(Lcom/google/android/recaptcha/internal/zzxt;)I
    .locals 2

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzxt;->zza:Lcom/google/android/recaptcha/internal/zzxt;

    invoke-virtual {p0, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 p0, 0x21

    return p0

    :cond_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzxt;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    if-eq p0, v0, :cond_2

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzxt;->zzc:Lcom/google/android/recaptcha/internal/zzxt;

    if-ne p0, v0, :cond_1

    const/16 p0, 0x43

    return p0

    .line 4
    :cond_1
    new-instance v0, Ljava/security/GeneralSecurityException;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v1, "Unable to serialize CurveType "

    invoke-virtual {v1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_2
    const/16 p0, 0x31

    return p0
.end method

.method private static zzj(Lcom/google/android/recaptcha/internal/zzyb;)Lcom/google/android/recaptcha/internal/zzwi;
    .locals 7

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzf()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zzc()Lcom/google/android/recaptcha/internal/zzxt;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzabk;->zzi(Lcom/google/android/recaptcha/internal/zzxt;)I

    move-result v0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzd()Ljava/security/spec/ECPoint;

    move-result-object v1

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwi;->zzg()Lcom/google/android/recaptcha/internal/zzwh;

    move-result-object v2

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzf()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object p0

    .line 5
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwe;->zzb()Lcom/google/android/recaptcha/internal/zzwd;

    move-result-object v3

    .line 6
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxx;->zzd()Lcom/google/android/recaptcha/internal/zzxu;

    move-result-object v4

    sget-object v5, Lcom/google/android/recaptcha/internal/zzxu;->zza:Lcom/google/android/recaptcha/internal/zzxu;

    .line 7
    invoke-virtual {v4, v5}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_0

    sget-object v4, Lcom/google/android/recaptcha/internal/zzwp;->zzd:Lcom/google/android/recaptcha/internal/zzwp;

    goto :goto_0

    .line 13
    :cond_0
    sget-object v5, Lcom/google/android/recaptcha/internal/zzxu;->zzb:Lcom/google/android/recaptcha/internal/zzxu;

    if-eq v4, v5, :cond_2

    .line 8
    sget-object v5, Lcom/google/android/recaptcha/internal/zzxu;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    if-ne v4, v5, :cond_1

    .line 29
    sget-object v4, Lcom/google/android/recaptcha/internal/zzwp;->zze:Lcom/google/android/recaptcha/internal/zzwp;

    goto :goto_0

    .line 30
    :cond_1
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-virtual {v4}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "Unable to serialize HashType "

    invoke-virtual {v1, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 8
    :cond_2
    sget-object v4, Lcom/google/android/recaptcha/internal/zzwp;->zzc:Lcom/google/android/recaptcha/internal/zzwp;

    .line 6
    :goto_0
    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzwd;->zza(Lcom/google/android/recaptcha/internal/zzwp;)Lcom/google/android/recaptcha/internal/zzwd;

    .line 10
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxx;->zzc()Lcom/google/android/recaptcha/internal/zzxt;

    move-result-object v4

    sget-object v5, Lcom/google/android/recaptcha/internal/zzxt;->zza:Lcom/google/android/recaptcha/internal/zzxt;

    .line 11
    invoke-virtual {v4, v5}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v5

    const/4 v6, 0x4

    if-eqz v5, :cond_3

    move v4, v6

    goto :goto_1

    .line 16
    :cond_3
    sget-object v5, Lcom/google/android/recaptcha/internal/zzxt;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    if-eq v4, v5, :cond_5

    .line 12
    sget-object v5, Lcom/google/android/recaptcha/internal/zzxt;->zzc:Lcom/google/android/recaptcha/internal/zzxt;

    if-ne v4, v5, :cond_4

    const/4 v4, 0x6

    goto :goto_1

    .line 29
    :cond_4
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-virtual {v4}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "Unable to serialize CurveType "

    invoke-virtual {v1, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    :cond_5
    const/4 v4, 0x5

    .line 10
    :goto_1
    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzwd;->zzb(I)Lcom/google/android/recaptcha/internal/zzwd;

    .line 14
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxx;->zzb()Lcom/google/android/recaptcha/internal/zzxv;

    move-result-object p0

    sget-object v4, Lcom/google/android/recaptcha/internal/zzxv;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    .line 15
    invoke-virtual {p0, v4}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_6

    const/4 v6, 0x3

    goto :goto_2

    .line 27
    :cond_6
    sget-object v4, Lcom/google/android/recaptcha/internal/zzxv;->zzb:Lcom/google/android/recaptcha/internal/zzxv;

    if-ne p0, v4, :cond_7

    .line 14
    :goto_2
    invoke-virtual {v3, v6}, Lcom/google/android/recaptcha/internal/zzwd;->zzc(I)Lcom/google/android/recaptcha/internal/zzwd;

    .line 17
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzwe;

    .line 4
    invoke-virtual {v2, p0}, Lcom/google/android/recaptcha/internal/zzwh;->zza(Lcom/google/android/recaptcha/internal/zzwe;)Lcom/google/android/recaptcha/internal/zzwh;

    .line 18
    invoke-virtual {v1}, Ljava/security/spec/ECPoint;->getAffineX()Ljava/math/BigInteger;

    move-result-object p0

    .line 19
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzth;->zzb(Ljava/math/BigInteger;I)[B

    move-result-object p0

    .line 20
    sget-object v3, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    .line 21
    array-length v3, p0

    const/4 v4, 0x0

    invoke-static {p0, v4, v3}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    .line 22
    invoke-virtual {v2, p0}, Lcom/google/android/recaptcha/internal/zzwh;->zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzwh;

    .line 23
    invoke-virtual {v1}, Ljava/security/spec/ECPoint;->getAffineY()Ljava/math/BigInteger;

    move-result-object p0

    .line 24
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzth;->zzb(Ljava/math/BigInteger;I)[B

    move-result-object p0

    .line 25
    array-length v0, p0

    invoke-static {p0, v4, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    .line 26
    invoke-virtual {v2, p0}, Lcom/google/android/recaptcha/internal/zzwh;->zzc(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzwh;

    .line 27
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzwi;

    return-object p0

    .line 28
    :cond_7
    new-instance v0, Ljava/security/GeneralSecurityException;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v1, "Unable to serialize SignatureEncoding "

    invoke-virtual {v1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private static zzk(I)Lcom/google/android/recaptcha/internal/zzxt;
    .locals 3

    add-int/lit8 v0, p0, -0x2

    const/4 v1, 0x2

    if-eq v0, v1, :cond_2

    const/4 v1, 0x3

    if-eq v0, v1, :cond_1

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    .line 1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxt;->zzc:Lcom/google/android/recaptcha/internal/zzxt;

    return-object p0

    .line 4
    :cond_0
    new-instance v0, Ljava/security/GeneralSecurityException;

    .line 5
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzwo;->zza(I)I

    move-result p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    add-int/lit8 v1, v1, 0x23

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "Unable to parse EllipticCurveType: "

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 2
    :cond_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxt;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    return-object p0

    .line 3
    :cond_2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxt;->zza:Lcom/google/android/recaptcha/internal/zzxt;

    return-object p0
.end method

.method private static zzl(I)Lcom/google/android/recaptcha/internal/zzxv;
    .locals 3

    add-int/lit8 v0, p0, -0x2

    const/4 v1, 0x1

    if-eq v0, v1, :cond_1

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    .line 2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxv;->zzb:Lcom/google/android/recaptcha/internal/zzxv;

    return-object p0

    .line 1
    :cond_0
    new-instance v0, Ljava/security/GeneralSecurityException;

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzwj;->zza(I)I

    move-result p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    add-int/lit8 v1, v1, 0x28

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "Unable to parse EcdsaSignatureEncoding: "

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxv;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    return-object p0
.end method
