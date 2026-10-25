.class public Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "TL_secureCredentialsEncrypted"
.end annotation


# static fields
.field public static final constructor:I = 0x33f0ea47


# instance fields
.field public data:[B

.field public hash:[B

.field public secret:[B


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 42764
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;
    .locals 2

    const v0, 0x33f0ea47

    if-eq v0, p1, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 42772
    :cond_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;-><init>()V

    .line 42773
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;

    return-object p0
.end method


# virtual methods
.method public readParams(Lorg/telegram/tgnet/InputSerializedData;Z)V
    .locals 1

    .line 42777
    invoke-interface {p1, p2}, Lorg/telegram/tgnet/InputSerializedData;->readByteArray(Z)[B

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;->data:[B

    .line 42778
    invoke-interface {p1, p2}, Lorg/telegram/tgnet/InputSerializedData;->readByteArray(Z)[B

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;->hash:[B

    .line 42779
    invoke-interface {p1, p2}, Lorg/telegram/tgnet/InputSerializedData;->readByteArray(Z)[B

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;->secret:[B

    return-void
.end method

.method public serializeToStream(Lorg/telegram/tgnet/OutputSerializedData;)V
    .locals 1

    const v0, 0x33f0ea47

    .line 42783
    invoke-interface {p1, v0}, Lorg/telegram/tgnet/OutputSerializedData;->writeInt32(I)V

    .line 42784
    iget-object v0, p0, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;->data:[B

    invoke-interface {p1, v0}, Lorg/telegram/tgnet/OutputSerializedData;->writeByteArray([B)V

    .line 42785
    iget-object v0, p0, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;->hash:[B

    invoke-interface {p1, v0}, Lorg/telegram/tgnet/OutputSerializedData;->writeByteArray([B)V

    .line 42786
    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$TL_secureCredentialsEncrypted;->secret:[B

    invoke-interface {p1, p0}, Lorg/telegram/tgnet/OutputSerializedData;->writeByteArray([B)V

    return-void
.end method
