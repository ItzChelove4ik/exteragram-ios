.class public abstract Lorg/telegram/tgnet/TLRPC$InputBotApp;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "InputBotApp"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 61248
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$InputBotApp;
    .locals 2

    const v0, -0x6f73fbf9

    if-eq p1, v0, :cond_1

    const v0, -0x56df4286

    if-eq p1, v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 61254
    :cond_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputBotAppID;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputBotAppID;-><init>()V

    goto :goto_0

    .line 61257
    :cond_1
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputBotAppShortName;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputBotAppShortName;-><init>()V

    .line 61260
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$InputBotApp;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$InputBotApp;

    return-object p0
.end method
