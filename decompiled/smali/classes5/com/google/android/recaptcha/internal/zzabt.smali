.class public abstract Lcom/google/android/recaptcha/internal/zzabt;
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

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabs;->zza:Lcom/google/android/recaptcha/internal/zzabs;

    const-class v1, Lcom/google/android/recaptcha/internal/zzyh;

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzvi;->zzb(Lcom/google/android/recaptcha/internal/zzvh;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvi;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zza:Lcom/google/android/recaptcha/internal/zzvi;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabn;->zza:Lcom/google/android/recaptcha/internal/zzabn;

    const-string/jumbo v1, "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey"

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzvf;->zzb(Lcom/google/android/recaptcha/internal/zzve;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzvf;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zzb:Lcom/google/android/recaptcha/internal/zzvf;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabo;->zza:Lcom/google/android/recaptcha/internal/zzabo;

    const-class v2, Lcom/google/android/recaptcha/internal/zzyo;

    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzue;->zzc(Lcom/google/android/recaptcha/internal/zzud;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzue;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zzc:Lcom/google/android/recaptcha/internal/zzue;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabp;->zza:Lcom/google/android/recaptcha/internal/zzabp;

    const-string/jumbo v2, "type.googleapis.com/google.crypto.tink.Ed25519PublicKey"

    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzub;->zzc(Lcom/google/android/recaptcha/internal/zzua;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzub;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zzd:Lcom/google/android/recaptcha/internal/zzub;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabq;->zza:Lcom/google/android/recaptcha/internal/zzabq;

    const-class v2, Lcom/google/android/recaptcha/internal/zzyi;

    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzue;->zzc(Lcom/google/android/recaptcha/internal/zzud;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzue;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zze:Lcom/google/android/recaptcha/internal/zzue;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabr;->zza:Lcom/google/android/recaptcha/internal/zzabr;

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzub;->zzc(Lcom/google/android/recaptcha/internal/zzua;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzub;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zzf:Lcom/google/android/recaptcha/internal/zzub;

    return-void
.end method

.method public static zza(Lcom/google/android/recaptcha/internal/zzvb;)V
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zza:Lcom/google/android/recaptcha/internal/zzvi;

    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzd(Lcom/google/android/recaptcha/internal/zzvi;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zzb:Lcom/google/android/recaptcha/internal/zzvf;

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zze(Lcom/google/android/recaptcha/internal/zzvf;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zzc:Lcom/google/android/recaptcha/internal/zzue;

    .line 3
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzb(Lcom/google/android/recaptcha/internal/zzue;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zzd:Lcom/google/android/recaptcha/internal/zzub;

    .line 4
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzc(Lcom/google/android/recaptcha/internal/zzub;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zze:Lcom/google/android/recaptcha/internal/zzue;

    .line 5
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzb(Lcom/google/android/recaptcha/internal/zzue;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabt;->zzf:Lcom/google/android/recaptcha/internal/zzub;

    .line 6
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzvb;->zzc(Lcom/google/android/recaptcha/internal/zzub;)V

    return-void
.end method

.method public static synthetic zzb(Lcom/google/android/recaptcha/internal/zzyo;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;
    .locals 3

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzabt;->zzh(Lcom/google/android/recaptcha/internal/zzyo;)Lcom/google/android/recaptcha/internal/zzwn;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaec;->zzv()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zzd:Lcom/google/android/recaptcha/internal/zzsn;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzf()Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzabt;->zzf(Lcom/google/android/recaptcha/internal/zzyg;)Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v1

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzb()Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v2, "type.googleapis.com/google.crypto.tink.Ed25519PublicKey"

    .line 4
    invoke-static {v2, p1, v0, v1, p0}, Lcom/google/android/recaptcha/internal/zzsp;->zza(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzsn;Lcom/google/android/recaptcha/internal/zzso;Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zzc(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzyo;
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object p1

    const-string/jumbo v0, "type.googleapis.com/google.crypto.tink.Ed25519PublicKey"

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
    invoke-static {p1, v1}, Lcom/google/android/recaptcha/internal/zzwn;->zzc(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzwn;

    move-result-object p1

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzwn;->zza()I

    move-result v1

    if-nez v1, :cond_0

    .line 6
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v1

    .line 7
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzabt;->zzg(Lcom/google/android/recaptcha/internal/zzso;)Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object v1

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzwn;->zzb()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p1

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzady;->zza([B)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object p1

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzf()Ljava/lang/Integer;

    move-result-object p0

    .line 9
    invoke-static {v1, p1, p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzc(Lcom/google/android/recaptcha/internal/zzyg;Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzyo;

    move-result-object p0

    return-object p0

    .line 6
    :cond_0
    new-instance p0, Ljava/security/GeneralSecurityException;

    const-string p1, "Only version 0 keys are accepted"

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0

    .line 10
    :catch_0
    const-string p0, "Parsing Ed25519PublicKey failed"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    .line 1
    :cond_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object p0

    .line 2
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string p1, "Wrong type URL in call to Ed25519ProtoSerialization.parsePublicKey: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0
.end method

.method public static synthetic zzd(Lcom/google/android/recaptcha/internal/zzyi;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;
    .locals 3

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwl;->zze()Lcom/google/android/recaptcha/internal/zzwk;

    move-result-object v0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zze()Lcom/google/android/recaptcha/internal/zzyo;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzabt;->zzh(Lcom/google/android/recaptcha/internal/zzyo;)Lcom/google/android/recaptcha/internal/zzwn;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzwk;->zzb(Lcom/google/android/recaptcha/internal/zzwn;)Lcom/google/android/recaptcha/internal/zzwk;

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zzf()Lcom/google/android/recaptcha/internal/zzaea;

    move-result-object v1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzaea;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)[B

    move-result-object p1

    array-length v1, p1

    const/4 v2, 0x0

    .line 4
    invoke-static {p1, v2, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    .line 5
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzwk;->zza(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzwk;

    .line 6
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzwl;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaec;->zzv()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zzc:Lcom/google/android/recaptcha/internal/zzsn;

    .line 8
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zzd()Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzabt;->zzf(Lcom/google/android/recaptcha/internal/zzyg;)Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v1

    .line 9
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zze()Lcom/google/android/recaptcha/internal/zzyo;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzse;->zzb()Ljava/lang/Integer;

    move-result-object p0

    const-string/jumbo v2, "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey"

    .line 10
    invoke-static {v2, p1, v0, v1, p0}, Lcom/google/android/recaptcha/internal/zzsp;->zza(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzsn;Lcom/google/android/recaptcha/internal/zzso;Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zze(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzyi;
    .locals 5

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object v0

    const-string/jumbo v1, "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey"

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
    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzwl;->zzd(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzwl;

    move-result-object v0

    .line 5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzwl;->zza()I

    move-result v2
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0

    const-string v3, "Only version 0 keys are accepted"

    if-nez v2, :cond_1

    .line 7
    :try_start_1
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzwl;->zzc()Lcom/google/android/recaptcha/internal/zzwn;

    move-result-object v2

    .line 8
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwn;->zza()I

    move-result v4

    if-nez v4, :cond_0

    .line 9
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v3

    .line 10
    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzabt;->zzg(Lcom/google/android/recaptcha/internal/zzso;)Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object v3

    .line 11
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwn;->zzb()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzady;->zza([B)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzf()Ljava/lang/Integer;

    move-result-object p0

    .line 12
    invoke-static {v3, v2, p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzc(Lcom/google/android/recaptcha/internal/zzyg;Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzyo;

    move-result-object p0

    .line 13
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzwl;->zzb()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object v0

    .line 14
    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzaea;->zza([BLcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzaea;

    move-result-object p1

    .line 15
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzyi;->zzc(Lcom/google/android/recaptcha/internal/zzyo;Lcom/google/android/recaptcha/internal/zzaea;)Lcom/google/android/recaptcha/internal/zzyi;

    move-result-object p0

    return-object p0

    .line 9
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

    .line 16
    :catch_0
    const-string p0, "Parsing Ed25519PrivateKey failed"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 1
    :cond_2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object p0

    .line 2
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string p1, "Wrong type URL in call to Ed25519ProtoSerialization.parsePrivateKey: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method

.method private static zzf(Lcom/google/android/recaptcha/internal/zzyg;)Lcom/google/android/recaptcha/internal/zzso;
    .locals 2

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzyg;->zzd:Lcom/google/android/recaptcha/internal/zzyg;

    invoke-virtual {p0, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    return-object p0

    :cond_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzyg;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    if-eq p0, v0, :cond_3

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzyg;->zzb:Lcom/google/android/recaptcha/internal/zzyg;

    if-eq p0, v0, :cond_2

    .line 3
    sget-object v0, Lcom/google/android/recaptcha/internal/zzyg;->zzc:Lcom/google/android/recaptcha/internal/zzyg;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zze:Lcom/google/android/recaptcha/internal/zzso;

    return-object p0

    .line 2
    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    return-object p0
.end method

.method private static zzg(Lcom/google/android/recaptcha/internal/zzso;)Lcom/google/android/recaptcha/internal/zzyg;
    .locals 2

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    if-ne p0, v0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzyg;->zzd:Lcom/google/android/recaptcha/internal/zzyg;

    return-object p0

    :cond_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    if-ne p0, v0, :cond_1

    sget-object p0, Lcom/google/android/recaptcha/internal/zzyg;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    return-object p0

    :cond_1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzso;->zze:Lcom/google/android/recaptcha/internal/zzso;

    if-ne p0, v0, :cond_2

    sget-object p0, Lcom/google/android/recaptcha/internal/zzyg;->zzb:Lcom/google/android/recaptcha/internal/zzyg;

    return-object p0

    :cond_2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzc:Lcom/google/android/recaptcha/internal/zzso;

    if-ne p0, v0, :cond_3

    .line 2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzyg;->zzc:Lcom/google/android/recaptcha/internal/zzyg;

    return-object p0

    .line 1
    :cond_3
    new-instance v0, Ljava/security/GeneralSecurityException;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzso;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v1, "Unable to parse OutputPrefixType: "

    invoke-virtual {v1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 2
    invoke-direct {v0, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private static zzh(Lcom/google/android/recaptcha/internal/zzyo;)Lcom/google/android/recaptcha/internal/zzwn;
    .locals 3

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwn;->zzd()Lcom/google/android/recaptcha/internal/zzwm;

    move-result-object v0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzd()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object p0

    array-length v1, p0

    const/4 v2, 0x0

    .line 3
    invoke-static {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    .line 2
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzwm;->zza(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzwm;

    .line 4
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzwn;

    return-object p0
.end method
