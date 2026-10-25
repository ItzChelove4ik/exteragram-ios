.class public Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "TL_receivedNotifyMessage"
.end annotation


# static fields
.field public static final constructor:I = -0x5c7b4887


# instance fields
.field public flags:I

.field public id:I


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 30901
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;
    .locals 2

    const v0, -0x5c7b4887

    if-eq v0, p1, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 30908
    :cond_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;-><init>()V

    .line 30909
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;

    return-object p0
.end method


# virtual methods
.method public readParams(Lorg/telegram/tgnet/InputSerializedData;Z)V
    .locals 1

    .line 30913
    invoke-interface {p1, p2}, Lorg/telegram/tgnet/InputSerializedData;->readInt32(Z)I

    move-result v0

    iput v0, p0, Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;->id:I

    .line 30914
    invoke-interface {p1, p2}, Lorg/telegram/tgnet/InputSerializedData;->readInt32(Z)I

    move-result p1

    iput p1, p0, Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;->flags:I

    return-void
.end method

.method public serializeToStream(Lorg/telegram/tgnet/OutputSerializedData;)V
    .locals 1

    const v0, -0x5c7b4887

    .line 30918
    invoke-interface {p1, v0}, Lorg/telegram/tgnet/OutputSerializedData;->writeInt32(I)V

    .line 30919
    iget v0, p0, Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;->id:I

    invoke-interface {p1, v0}, Lorg/telegram/tgnet/OutputSerializedData;->writeInt32(I)V

    .line 30920
    iget p0, p0, Lorg/telegram/tgnet/TLRPC$TL_receivedNotifyMessage;->flags:I

    invoke-interface {p1, p0}, Lorg/telegram/tgnet/OutputSerializedData;->writeInt32(I)V

    return-void
.end method
