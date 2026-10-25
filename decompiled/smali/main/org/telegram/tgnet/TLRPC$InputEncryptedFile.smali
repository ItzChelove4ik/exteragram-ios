.class public abstract Lorg/telegram/tgnet/TLRPC$InputEncryptedFile;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "InputEncryptedFile"
.end annotation


# instance fields
.field public access_hash:J

.field public id:J

.field public key_fingerprint:I

.field public md5_checksum:Ljava/lang/String;

.field public parts:I


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 30924
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$InputEncryptedFile;
    .locals 2

    sparse-switch p1, :sswitch_data_0

    const/4 v0, 0x0

    goto :goto_0

    .line 30944
    :sswitch_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFileUploaded;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFileUploaded;-><init>()V

    goto :goto_0

    .line 30935
    :sswitch_1
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFile;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFile;-><init>()V

    goto :goto_0

    .line 30938
    :sswitch_2
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFileBigUploaded;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFileBigUploaded;-><init>()V

    goto :goto_0

    .line 30941
    :sswitch_3
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFileEmpty;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputEncryptedFileEmpty;-><init>()V

    .line 30947
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$InputEncryptedFile;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$InputEncryptedFile;

    return-object p0

    nop

    :sswitch_data_0
    .sparse-switch
        0x1837c364 -> :sswitch_3
        0x2dc173c8 -> :sswitch_2
        0x5a17b5e5 -> :sswitch_1
        0x64bd0306 -> :sswitch_0
    .end sparse-switch
.end method
