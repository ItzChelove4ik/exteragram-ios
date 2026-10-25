.class public abstract Lorg/chromium/net/telemetry/SizeBuckets;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static calcRequestBodySizeBucket(J)I
    .locals 2

    .line 80
    const-string v0, "Request body size is negative"

    invoke-static {p0, p1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->checkSizeIsValid(JLjava/lang/String;)V

    long-to-double p0, p0

    const-wide/high16 v0, 0x4090000000000000L    # 1024.0

    div-double/2addr p0, v0

    const-wide/16 v0, 0x0

    cmpl-double v0, p0, v0

    if-nez v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    if-lez v0, :cond_1

    const-wide/high16 v0, 0x4024000000000000L    # 10.0

    cmpg-double v0, p0, v0

    if-gez v0, :cond_1

    const/4 p0, 0x2

    return p0

    :cond_1
    const/16 v0, 0xa

    const/16 v1, 0x32

    .line 90
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 p0, 0x3

    return p0

    :cond_2
    const/16 v0, 0xc8

    .line 93
    invoke-static {p0, p1, v1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v1

    if-eqz v1, :cond_3

    const/4 p0, 0x4

    return p0

    :cond_3
    const/16 v1, 0x1f4

    .line 96
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v0

    if-eqz v0, :cond_4

    const/4 p0, 0x5

    return p0

    :cond_4
    const/16 v0, 0x3e8

    .line 99
    invoke-static {p0, p1, v1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v1

    if-eqz v1, :cond_5

    const/4 p0, 0x6

    return p0

    :cond_5
    const/16 v1, 0x1388

    .line 102
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result p0

    if-eqz p0, :cond_6

    const/4 p0, 0x7

    return p0

    :cond_6
    const/16 p0, 0x8

    return p0
.end method

.method public static calcRequestHeadersSizeBucket(J)I
    .locals 2

    .line 16
    const-string v0, "Request header size is negative"

    invoke-static {p0, p1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->checkSizeIsValid(JLjava/lang/String;)V

    long-to-double p0, p0

    const-wide/high16 v0, 0x4090000000000000L    # 1024.0

    div-double/2addr p0, v0

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 20
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v0

    if-eqz v0, :cond_0

    return v1

    :cond_0
    const/16 v0, 0xa

    .line 23
    invoke-static {p0, p1, v1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v1

    if-eqz v1, :cond_1

    const/4 p0, 0x2

    return p0

    :cond_1
    const/16 v1, 0x19

    .line 26
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 p0, 0x3

    return p0

    :cond_2
    const/16 v0, 0x32

    .line 29
    invoke-static {p0, p1, v1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v1

    if-eqz v1, :cond_3

    const/4 p0, 0x4

    return p0

    :cond_3
    const/16 v1, 0x64

    .line 32
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result p0

    if-eqz p0, :cond_4

    const/4 p0, 0x5

    return p0

    :cond_4
    const/4 p0, 0x6

    return p0
.end method

.method public static calcResponseBodySizeBucket(J)I
    .locals 2

    .line 118
    const-string v0, "Response body size is negative"

    invoke-static {p0, p1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->checkSizeIsValid(JLjava/lang/String;)V

    long-to-double p0, p0

    const-wide/high16 v0, 0x4090000000000000L    # 1024.0

    div-double/2addr p0, v0

    const-wide/16 v0, 0x0

    cmpl-double v0, p0, v0

    if-nez v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    if-lez v0, :cond_1

    const-wide/high16 v0, 0x4024000000000000L    # 10.0

    cmpg-double v0, p0, v0

    if-gez v0, :cond_1

    const/4 p0, 0x2

    return p0

    :cond_1
    const/16 v0, 0xa

    const/16 v1, 0x32

    .line 128
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 p0, 0x3

    return p0

    :cond_2
    const/16 v0, 0xc8

    .line 131
    invoke-static {p0, p1, v1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v1

    if-eqz v1, :cond_3

    const/4 p0, 0x4

    return p0

    :cond_3
    const/16 v1, 0x1f4

    .line 134
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v0

    if-eqz v0, :cond_4

    const/4 p0, 0x5

    return p0

    :cond_4
    const/16 v0, 0x3e8

    .line 137
    invoke-static {p0, p1, v1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v1

    if-eqz v1, :cond_5

    const/4 p0, 0x6

    return p0

    :cond_5
    const/16 v1, 0x1388

    .line 140
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result p0

    if-eqz p0, :cond_6

    const/4 p0, 0x7

    return p0

    :cond_6
    const/16 p0, 0x8

    return p0
.end method

.method public static calcResponseHeadersSizeBucket(J)I
    .locals 2

    .line 48
    const-string v0, "Response header size is negative"

    invoke-static {p0, p1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->checkSizeIsValid(JLjava/lang/String;)V

    long-to-double p0, p0

    const-wide/high16 v0, 0x4090000000000000L    # 1024.0

    div-double/2addr p0, v0

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 52
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v0

    if-eqz v0, :cond_0

    return v1

    :cond_0
    const/16 v0, 0xa

    .line 55
    invoke-static {p0, p1, v1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v1

    if-eqz v1, :cond_1

    const/4 p0, 0x2

    return p0

    :cond_1
    const/16 v1, 0x19

    .line 58
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 p0, 0x3

    return p0

    :cond_2
    const/16 v0, 0x32

    .line 61
    invoke-static {p0, p1, v1, v0}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result v1

    if-eqz v1, :cond_3

    const/4 p0, 0x4

    return p0

    :cond_3
    const/16 v1, 0x64

    .line 64
    invoke-static {p0, p1, v0, v1}, Lorg/chromium/net/telemetry/SizeBuckets;->isInClosedOpenRange(DII)Z

    move-result p0

    if-eqz p0, :cond_4

    const/4 p0, 0x5

    return p0

    :cond_4
    const/4 p0, 0x6

    return p0
.end method

.method private static checkSizeIsValid(JLjava/lang/String;)V
    .locals 2

    const-wide/16 v0, 0x0

    cmp-long p0, p0, v0

    if-ltz p0, :cond_0

    return-void

    .line 151
    :cond_0
    invoke-static {p2}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method private static isInClosedOpenRange(DII)Z
    .locals 2

    int-to-double v0, p2

    cmpl-double p2, p0, v0

    if-ltz p2, :cond_0

    int-to-double p2, p3

    cmpg-double p0, p0, p2

    if-gez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
