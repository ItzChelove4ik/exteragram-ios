.class public Lorg/telegram/tgnet/TLRPC$TL_null;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "TL_null"
.end annotation


# static fields
.field public static final constructor:I = 0x56730bcc


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 18434
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$TL_null;
    .locals 2

    const v0, 0x56730bcc

    if-eq v0, p1, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 18438
    :cond_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_null;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_null;-><init>()V

    .line 18439
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$TL_null;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$TL_null;

    return-object p0
.end method


# virtual methods
.method public serializeToStream(Lorg/telegram/tgnet/OutputSerializedData;)V
    .locals 0

    const p0, 0x56730bcc

    .line 18443
    invoke-interface {p1, p0}, Lorg/telegram/tgnet/OutputSerializedData;->writeInt32(I)V

    return-void
.end method
