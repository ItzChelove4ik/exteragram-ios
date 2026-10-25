.class public abstract Lorg/chromium/net/telemetry/Hash;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final MD5_MESSAGE_DIGEST:Ljava/security/MessageDigest;

.field private static final TAG:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 31
    const-class v0, Lorg/chromium/net/telemetry/CronetLoggerImpl;

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lorg/chromium/net/telemetry/Hash;->TAG:Ljava/lang/String;

    .line 44
    invoke-static {}, Lorg/chromium/net/telemetry/Hash;->getMd5MessageDigest()Ljava/security/MessageDigest;

    move-result-object v0

    sput-object v0, Lorg/chromium/net/telemetry/Hash;->MD5_MESSAGE_DIGEST:Ljava/security/MessageDigest;

    return-void
.end method

.method private static getMd5MessageDigest()Ljava/security/MessageDigest;
    .locals 3

    .line 35
    :try_start_0
    const-string v0, "MD5"

    invoke-static {v0}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object v0
    :try_end_0
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    .line 37
    sget-object v1, Lorg/chromium/net/telemetry/Hash;->TAG:Ljava/lang/String;

    const/4 v2, 0x3

    invoke-static {v1, v2}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 38
    const-string v2, "Error while instantiating messageDigest"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method

.method public static hash(Ljava/lang/String;)J
    .locals 2

    .line 63
    sget-object v0, Lorg/chromium/net/telemetry/Hash;->MD5_MESSAGE_DIGEST:Ljava/security/MessageDigest;

    if-eqz v0, :cond_1

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 65
    :cond_0
    sget-object v0, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p0, v0}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p0

    invoke-static {p0}, Lorg/chromium/net/telemetry/Hash;->hash([B)J

    move-result-wide v0

    return-wide v0

    :cond_1
    :goto_0
    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public static hash([B)J
    .locals 2

    .line 52
    sget-object v0, Lorg/chromium/net/telemetry/Hash;->MD5_MESSAGE_DIGEST:Ljava/security/MessageDigest;

    if-eqz v0, :cond_1

    if-eqz p0, :cond_1

    array-length v1, p0

    if-nez v1, :cond_0

    goto :goto_0

    .line 54
    :cond_0
    invoke-virtual {v0, p0}, Ljava/security/MessageDigest;->digest([B)[B

    move-result-object p0

    invoke-static {p0}, Ljava/nio/ByteBuffer;->wrap([B)Ljava/nio/ByteBuffer;

    move-result-object p0

    invoke-virtual {p0}, Ljava/nio/ByteBuffer;->getLong()J

    move-result-wide v0

    return-wide v0

    :cond_1
    :goto_0
    const-wide/16 v0, 0x0

    return-wide v0
.end method
