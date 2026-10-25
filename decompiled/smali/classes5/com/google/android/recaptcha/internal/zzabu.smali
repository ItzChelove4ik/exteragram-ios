.class public final Lcom/google/android/recaptcha/internal/zzabu;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzss;


# static fields
.field private static final zza:[B


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/16 v0, 0x10

    new-array v0, v0, [B

    fill-array-data v0, :array_0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabu;->zza:[B

    return-void

    :array_0
    .array-data 1
        0x30t
        0x2et
        0x2t
        0x1t
        0x0t
        0x30t
        0x5t
        0x6t
        0x3t
        0x2bt
        0x65t
        0x70t
        0x4t
        0x22t
        0x4t
        0x20t
    .end array-data
.end method

.method private constructor <init>([B[B[BLjava/security/Provider;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p0, 0x1

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result p0

    const/4 p2, 0x0

    if-eqz p0, :cond_1

    .line 2
    array-length p0, p1

    .line 3
    new-instance p3, Ljava/security/spec/PKCS8EncodedKeySpec;

    const/16 v0, 0x20

    if-ne p0, v0, :cond_0

    .line 4
    sget-object p0, Lcom/google/android/recaptcha/internal/zzabu;->zza:[B

    filled-new-array {p0, p1}, [[B

    move-result-object p0

    .line 5
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzacu;->zza([[B)[B

    move-result-object p0

    .line 3
    invoke-direct {p3, p0}, Ljava/security/spec/PKCS8EncodedKeySpec;-><init>([B)V

    const-string p0, "Ed25519"

    .line 6
    invoke-static {p0, p4}, Ljava/security/KeyFactory;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/KeyFactory;

    move-result-object p0

    .line 7
    invoke-virtual {p0, p3}, Ljava/security/KeyFactory;->generatePrivate(Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;

    return-void

    .line 4
    :cond_0
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string p1, "Given private key\'s length is not %s"

    invoke-static {p1, p0}, Lcom/google/protobuf/FieldSet$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[Ljava/lang/Object;)V

    throw p2

    .line 2
    :cond_1
    const-string p0, "Can not use Ed25519 in FIPS-mode."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw p2
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzyi;)Lcom/google/android/recaptcha/internal/zzss;
    .locals 5

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzti;->zza()Ljava/security/Provider;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 2
    new-instance v1, Lcom/google/android/recaptcha/internal/zzabu;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zzf()Lcom/google/android/recaptcha/internal/zzaea;

    move-result-object v2

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v3

    .line 3
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzaea;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)[B

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zze()Lcom/google/android/recaptcha/internal/zzyo;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaax;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v3

    .line 4
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v3

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyi;->zzd()Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object p0

    sget-object v4, Lcom/google/android/recaptcha/internal/zzyg;->zzc:Lcom/google/android/recaptcha/internal/zzyg;

    .line 5
    invoke-virtual {p0, v4}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    const/4 v4, 0x0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    new-array p0, p0, [B

    aput-byte v4, p0, v4

    goto :goto_0

    .line 6
    :cond_0
    new-array p0, v4, [B

    :goto_0
    invoke-direct {v1, v2, v3, p0, v0}, Lcom/google/android/recaptcha/internal/zzabu;-><init>([B[B[BLjava/security/Provider;)V

    return-object v1

    .line 2
    :cond_1
    new-instance p0, Ljava/security/NoSuchProviderException;

    const-string v0, "Ed25519SignJce requires the Conscrypt provider."

    invoke-direct {p0, v0}, Ljava/security/NoSuchProviderException;-><init>(Ljava/lang/String;)V

    throw p0
.end method


# virtual methods
.method public final zza([B)[B
    .locals 0

    const/4 p0, 0x0

    throw p0
.end method
