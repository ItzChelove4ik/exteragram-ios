.class public abstract Lorg/telegram/tgnet/TLRPC$ChannelMessagesFilter;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "ChannelMessagesFilter"
.end annotation


# instance fields
.field public exclude_new_messages:Z

.field public flags:I

.field public ranges:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/tgnet/TLRPC$TL_messageRange;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 34481
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    .line 34484
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/tgnet/TLRPC$ChannelMessagesFilter;->ranges:Ljava/util/ArrayList;

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$ChannelMessagesFilter;
    .locals 2

    const v0, -0x6b2bd119

    if-eq p1, v0, :cond_1

    const v0, -0x328826a9

    if-eq p1, v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 34493
    :cond_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_channelMessagesFilter;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_channelMessagesFilter;-><init>()V

    goto :goto_0

    .line 34490
    :cond_1
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_channelMessagesFilterEmpty;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_channelMessagesFilterEmpty;-><init>()V

    .line 34496
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$ChannelMessagesFilter;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$ChannelMessagesFilter;

    return-object p0
.end method
