.class public abstract Landroidx/media3/extractor/WavUtil;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static getPcmEncodingForType(II)I
    .locals 2

    const/4 v0, 0x1

    if-eq p0, v0, :cond_2

    const/4 v0, 0x3

    const/4 v1, 0x0

    if-eq p0, v0, :cond_0

    const v0, 0xfffe

    if-eq p0, v0, :cond_2

    return v1

    :cond_0
    const/16 p0, 0x20

    if-ne p1, p0, :cond_1

    const/4 p0, 0x4

    return p0

    :cond_1
    return v1

    .line 99
    :cond_2
    invoke-static {p1}, Landroidx/media3/common/util/Util;->getPcmEncoding(I)I

    move-result p0

    return p0
.end method
