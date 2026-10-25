.class public final Lcom/google/android/recaptcha/internal/zzyi;
.super Lcom/google/android/recaptcha/internal/zzaaw;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzyo;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzaea;


# direct methods
.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzyo;Lcom/google/android/recaptcha/internal/zzaea;)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaaw;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzyi;->zza:Lcom/google/android/recaptcha/internal/zzyo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzyi;->zzb:Lcom/google/android/recaptcha/internal/zzaea;

    return-void
.end method

.method public static zzc(Lcom/google/android/recaptcha/internal/zzyo;Lcom/google/android/recaptcha/internal/zzaea;)Lcom/google/android/recaptcha/internal/zzyi;
    .locals 2

    .line 1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaea;->zzc()I

    move-result v0

    const/16 v1, 0x20

    if-ne v0, v1, :cond_1

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzd()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    .line 3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v1

    .line 4
    invoke-virtual {p1, v1}, Lcom/google/android/recaptcha/internal/zzaea;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)[B

    move-result-object v1

    .line 5
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zztp;->zzb([B)[B

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zztp;->zza([B)[B

    move-result-object v1

    .line 6
    invoke-static {v0, v1}, Ljava/util/Arrays;->equals([B[B)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 7
    new-instance v0, Lcom/google/android/recaptcha/internal/zzyi;

    invoke-direct {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzyi;-><init>(Lcom/google/android/recaptcha/internal/zzyo;Lcom/google/android/recaptcha/internal/zzaea;)V

    return-object v0

    :cond_0
    const-string p0, "Ed25519 keys mismatch"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    .line 1
    :cond_1
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaea;->zzc()I

    move-result p1

    .line 2
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, 0x41

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v0, "Ed25519 key must be constructed with key of length 32 bytes, not "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0
.end method


# virtual methods
.method public final synthetic zza()Lcom/google/android/recaptcha/internal/zzsl;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyi;->zza:Lcom/google/android/recaptcha/internal/zzyo;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzf()Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object p0

    return-object p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzyh;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyi;->zza:Lcom/google/android/recaptcha/internal/zzyo;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzf()Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object p0

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzyo;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyi;->zza:Lcom/google/android/recaptcha/internal/zzyo;

    return-object p0
.end method

.method public final zzf()Lcom/google/android/recaptcha/internal/zzaea;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyi;->zzb:Lcom/google/android/recaptcha/internal/zzaea;

    return-object p0
.end method

.method public final synthetic zzg()Lcom/google/android/recaptcha/internal/zzaax;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyi;->zza:Lcom/google/android/recaptcha/internal/zzyo;

    return-object p0
.end method
