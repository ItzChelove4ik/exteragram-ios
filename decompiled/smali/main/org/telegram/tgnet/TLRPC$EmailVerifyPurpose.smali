.class public abstract Lorg/telegram/tgnet/TLRPC$EmailVerifyPurpose;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "EmailVerifyPurpose"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 75
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$EmailVerifyPurpose;
    .locals 2

    const v0, -0x440ae97b

    if-eq p1, v0, :cond_2

    const v0, 0x4345be73

    if-eq p1, v0, :cond_1

    const v0, 0x527d22eb

    if-eq p1, v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 84
    :cond_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_emailVerifyPurposeLoginChange;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_emailVerifyPurposeLoginChange;-><init>()V

    goto :goto_0

    .line 81
    :cond_1
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_emailVerifyPurposeLoginSetup;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_emailVerifyPurposeLoginSetup;-><init>()V

    goto :goto_0

    .line 87
    :cond_2
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_emailVerifyPurposePassport;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_emailVerifyPurposePassport;-><init>()V

    .line 90
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$EmailVerifyPurpose;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$EmailVerifyPurpose;

    return-object p0
.end method
