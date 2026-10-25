.class public abstract Lorg/chromium/net/impl/Preconditions;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static checkDirect(Ljava/nio/ByteBuffer;)V
    .locals 0

    .line 14
    invoke-virtual {p0}, Ljava/nio/ByteBuffer;->isDirect()Z

    move-result p0

    if-eqz p0, :cond_0

    return-void

    .line 15
    :cond_0
    const-string p0, "byteBuffer must be a direct ByteBuffer."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method public static checkHasRemaining(Ljava/nio/ByteBuffer;)V
    .locals 0

    .line 20
    invoke-virtual {p0}, Ljava/nio/Buffer;->hasRemaining()Z

    move-result p0

    if-eqz p0, :cond_0

    return-void

    .line 21
    :cond_0
    const-string p0, "ByteBuffer is already full."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
