.class public abstract Lorg/telegram/tgnet/TLRPC$EmailVerification;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "EmailVerification"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 141
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$EmailVerification;
    .locals 2

    const v0, -0x6dd1aa57

    if-eq p1, v0, :cond_2

    const v0, -0x692f8b03

    if-eq p1, v0, :cond_1

    const v0, -0x246f613e

    if-eq p1, v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 150
    :cond_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_emailVerificationGoogle;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_emailVerificationGoogle;-><init>()V

    goto :goto_0

    .line 153
    :cond_1
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_emailVerificationApple;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_emailVerificationApple;-><init>()V

    goto :goto_0

    .line 147
    :cond_2
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_emailVerificationCode;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_emailVerificationCode;-><init>()V

    .line 156
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$EmailVerification;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$EmailVerification;

    return-object p0
.end method
