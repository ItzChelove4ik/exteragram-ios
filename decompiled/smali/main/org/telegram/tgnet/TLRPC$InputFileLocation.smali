.class public abstract Lorg/telegram/tgnet/TLRPC$InputFileLocation;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "InputFileLocation"
.end annotation


# instance fields
.field public access_hash:J

.field public file_reference:[B

.field public flags:I

.field public id:J

.field public local_id:I

.field public secret:J

.field public thumb_size:Ljava/lang/String;

.field public volume_id:J


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 20990
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$InputFileLocation;
    .locals 2

    sparse-switch p1, :sswitch_data_0

    const/4 v0, 0x0

    goto :goto_0

    .line 21005
    :sswitch_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputPhotoFileLocation;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputPhotoFileLocation;-><init>()V

    goto :goto_0

    .line 21011
    :sswitch_1
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputPeerPhotoFileLocation;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputPeerPhotoFileLocation;-><init>()V

    goto :goto_0

    .line 21020
    :sswitch_2
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputGroupCallStream;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputGroupCallStream;-><init>()V

    goto :goto_0

    .line 21014
    :sswitch_3
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFileLocation;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFileLocation;-><init>()V

    goto :goto_0

    .line 21023
    :sswitch_4
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputFileLocation;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputFileLocation;-><init>()V

    goto :goto_0

    .line 21026
    :sswitch_5
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputSecureFileLocation;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputSecureFileLocation;-><init>()V

    goto :goto_0

    .line 21008
    :sswitch_6
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputDocumentFileLocation;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputDocumentFileLocation;-><init>()V

    goto :goto_0

    .line 21017
    :sswitch_7
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputStickerSetThumb;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputStickerSetThumb;-><init>()V

    .line 21029
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$InputFileLocation;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$InputFileLocation;

    return-object p0

    nop

    :sswitch_data_0
    .sparse-switch
        -0x627b0c25 -> :sswitch_7
        -0x452f8a7c -> :sswitch_6
        -0x343811d8 -> :sswitch_5
        -0x2025541f -> :sswitch_4
        -0xadca2ab -> :sswitch_3
        0x598a92a -> :sswitch_2
        0x37257e99 -> :sswitch_1
        0x40181ffe -> :sswitch_0
    .end sparse-switch
.end method
