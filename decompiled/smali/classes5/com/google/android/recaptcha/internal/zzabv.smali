.class public final Lcom/google/android/recaptcha/internal/zzabv;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzst;


# static fields
.field private static final zza:[B


# instance fields
.field private final zzb:Ljava/security/PublicKey;

.field private final zzc:[B

.field private final zzd:[B

.field private final zze:Ljava/security/Provider;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/16 v0, 0xc

    new-array v0, v0, [B

    fill-array-data v0, :array_0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabv;->zza:[B

    return-void

    :array_0
    .array-data 1
        0x30t
        0x2at
        0x30t
        0x5t
        0x6t
        0x3t
        0x2bt
        0x65t
        0x70t
        0x3t
        0x21t
        0x0t
    .end array-data
.end method

.method private constructor <init>([B[B[BLjava/security/Provider;)V
    .locals 4

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x1

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 2
    array-length v0, p1

    .line 3
    new-instance v2, Ljava/security/spec/X509EncodedKeySpec;

    const/16 v3, 0x20

    if-ne v0, v3, :cond_0

    .line 4
    sget-object v0, Lcom/google/android/recaptcha/internal/zzabv;->zza:[B

    filled-new-array {v0, p1}, [[B

    move-result-object p1

    .line 5
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzacu;->zza([[B)[B

    move-result-object p1

    .line 3
    invoke-direct {v2, p1}, Ljava/security/spec/X509EncodedKeySpec;-><init>([B)V

    const-string p1, "Ed25519"

    .line 6
    invoke-static {p1, p4}, Ljava/security/KeyFactory;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/KeyFactory;

    move-result-object p1

    .line 7
    invoke-virtual {p1, v2}, Ljava/security/KeyFactory;->generatePublic(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzabv;->zzb:Ljava/security/PublicKey;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzabv;->zzc:[B

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzabv;->zzd:[B

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzabv;->zze:Ljava/security/Provider;

    return-void

    .line 4
    :cond_0
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string p1, "Given public key\'s length is not %s."

    invoke-static {p1, p0}, Lcom/google/protobuf/FieldSet$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[Ljava/lang/Object;)V

    throw v1

    .line 2
    :cond_1
    const-string p0, "Can not use Ed25519 in FIPS-mode."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v1
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzyo;)Lcom/google/android/recaptcha/internal/zzst;
    .locals 6

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzti;->zza()Ljava/security/Provider;

    move-result-object v0

    if-eqz v0, :cond_2

    const/4 v1, 0x1

    .line 3
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 4
    new-instance v2, Lcom/google/android/recaptcha/internal/zzabv;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzd()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v3

    .line 5
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v3

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v4

    .line 6
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v4

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyo;->zzf()Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object p0

    sget-object v5, Lcom/google/android/recaptcha/internal/zzyg;->zzc:Lcom/google/android/recaptcha/internal/zzyg;

    .line 7
    invoke-virtual {p0, v5}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    const/4 v5, 0x0

    if-eqz p0, :cond_0

    new-array p0, v1, [B

    aput-byte v5, p0, v5

    goto :goto_0

    .line 8
    :cond_0
    new-array p0, v5, [B

    :goto_0
    invoke-direct {v2, v3, v4, p0, v0}, Lcom/google/android/recaptcha/internal/zzabv;-><init>([B[B[BLjava/security/Provider;)V

    return-object v2

    .line 4
    :cond_1
    const-string p0, "Can not use Ed25519 in FIPS-mode."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    .line 2
    :cond_2
    new-instance p0, Ljava/security/NoSuchProviderException;

    const-string v0, "Ed25519VerifyJce requires the Conscrypt provider."

    invoke-direct {p0, v0}, Ljava/security/NoSuchProviderException;-><init>(Ljava/lang/String;)V

    throw p0
.end method


# virtual methods
.method public final zza([B[B)V
    .locals 5

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzabv;->zzc:[B

    array-length v1, v0

    array-length v2, p1

    add-int/lit8 v3, v1, 0x40

    const/16 v4, 0x40

    if-ne v2, v3, :cond_2

    .line 4
    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzwc;->zzc([B[B)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 5
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzabv;->zze:Ljava/security/Provider;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzabv;->zzb:Ljava/security/PublicKey;

    const-string v3, "Ed25519"

    .line 6
    invoke-static {v3, v0}, Ljava/security/Signature;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/Signature;

    move-result-object v0

    .line 7
    invoke-virtual {v0, v2}, Ljava/security/Signature;->initVerify(Ljava/security/PublicKey;)V

    .line 8
    invoke-virtual {v0, p2}, Ljava/security/Signature;->update([B)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzabv;->zzd:[B

    .line 9
    invoke-virtual {v0, p0}, Ljava/security/Signature;->update([B)V

    .line 10
    :try_start_0
    invoke-virtual {v0, p1, v1, v4}, Ljava/security/Signature;->verify([BII)Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    if-eqz p0, :cond_0

    return-void

    .line 11
    :catch_0
    :cond_0
    const-string p0, "Signature check failed."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 5
    :cond_1
    const-string p0, "Invalid signature (output prefix mismatch)"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 2
    :cond_2
    new-instance p0, Ljava/security/GeneralSecurityException;

    .line 3
    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const-string p2, "Invalid signature length: %s"

    invoke-static {p2, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0
.end method
