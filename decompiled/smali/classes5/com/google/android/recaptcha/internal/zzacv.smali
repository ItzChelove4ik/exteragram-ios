.class public final Lcom/google/android/recaptcha/internal/zzacv;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzss;


# instance fields
.field private final zza:[B


# direct methods
.method private constructor <init>([B[B[B)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p2, 0x1

    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result p2

    const/4 p3, 0x0

    if-eqz p2, :cond_1

    .line 2
    array-length p2, p1

    const/16 v0, 0x20

    if-ne p2, v0, :cond_0

    .line 4
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zztp;->zzb([B)[B

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzacv;->zza:[B

    .line 5
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zztp;->zza([B)[B

    return-void

    .line 3
    :cond_0
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string p1, "Given private key\'s length is not %s"

    invoke-static {p1, p0}, Lcom/google/protobuf/FieldSet$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[Ljava/lang/Object;)V

    throw p3

    .line 2
    :cond_1
    const-string p0, "Can not use Ed25519 in FIPS-mode."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw p3
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzyi;)Lcom/google/android/recaptcha/internal/zzss;
    .locals 5

    const/4 v0, 0x1

    .line 1
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 3
    :try_start_0
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzabu;->zzb(Lcom/google/android/recaptcha/internal/zzyi;)Lcom/google/android/recaptcha/internal/zzss;

    move-result-object p0
    :try_end_0
    .catch Ljava/security/GeneralSecurityException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    new-instance v1, Lcom/google/android/recaptcha/internal/zzacv;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zzf()Lcom/google/android/recaptcha/internal/zzaea;

    move-result-object v2

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v3

    .line 4
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzaea;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)[B

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zze()Lcom/google/android/recaptcha/internal/zzyo;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaax;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v3

    .line 5
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v3

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zzd()Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object p0

    sget-object v4, Lcom/google/android/recaptcha/internal/zzyg;->zzc:Lcom/google/android/recaptcha/internal/zzyg;

    .line 6
    invoke-virtual {p0, v4}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    const/4 v4, 0x0

    if-eqz p0, :cond_0

    new-array p0, v0, [B

    aput-byte v4, p0, v4

    goto :goto_0

    .line 7
    :cond_0
    new-array p0, v4, [B

    :goto_0
    invoke-direct {v1, v2, v3, p0}, Lcom/google/android/recaptcha/internal/zzacv;-><init>([B[B[B)V

    return-object v1

    .line 2
    :cond_1
    const-string p0, "Can not use Ed25519 in FIPS-mode."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public final zza([B)[B
    .locals 0

    const/4 p0, 0x0

    throw p0
.end method
