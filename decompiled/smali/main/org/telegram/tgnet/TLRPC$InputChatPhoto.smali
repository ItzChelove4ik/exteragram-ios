.class public abstract Lorg/telegram/tgnet/TLRPC$InputChatPhoto;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "InputChatPhoto"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 49007
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$InputChatPhoto;
    .locals 2

    const v0, -0x76ac52c9

    if-eq p1, v0, :cond_2

    const v0, -0x42325140

    if-eq p1, v0, :cond_1

    const v0, 0x1ca48f57

    if-eq p1, v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 49016
    :cond_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputChatPhotoEmpty;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputChatPhotoEmpty;-><init>()V

    goto :goto_0

    .line 49019
    :cond_1
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputChatUploadedPhoto;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputChatUploadedPhoto;-><init>()V

    goto :goto_0

    .line 49013
    :cond_2
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputChatPhoto;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputChatPhoto;-><init>()V

    .line 49022
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$InputChatPhoto;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$InputChatPhoto;

    return-object p0
.end method
