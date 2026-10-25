.class public abstract Lorg/telegram/tgnet/TLRPC$InputNotifyPeer;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "InputNotifyPeer"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 20892
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$InputNotifyPeer;
    .locals 2

    .line 20895
    const-class v0, Lorg/telegram/tgnet/TLRPC$InputNotifyPeer;

    invoke-static {p1}, Lorg/telegram/tgnet/TLRPC$InputNotifyPeer;->fromConstructor(I)Lorg/telegram/tgnet/TLRPC$InputNotifyPeer;

    move-result-object v1

    invoke-static {v0, v1, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$InputNotifyPeer;

    return-object p0
.end method

.method private static fromConstructor(I)Lorg/telegram/tgnet/TLRPC$InputNotifyPeer;
    .locals 0

    sparse-switch p0, :sswitch_data_0

    const/4 p0, 0x0

    return-object p0

    .line 20909
    :sswitch_0
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyForumTopic;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyForumTopic;-><init>()V

    return-object p0

    .line 20901
    :sswitch_1
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyChats;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyChats;-><init>()V

    return-object p0

    .line 20911
    :sswitch_2
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyCommunity;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyCommunity;-><init>()V

    return-object p0

    .line 20905
    :sswitch_3
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyUsers;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyUsers;-><init>()V

    return-object p0

    .line 20903
    :sswitch_4
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyPeer;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyPeer;-><init>()V

    return-object p0

    .line 20907
    :sswitch_5
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyBroadcasts;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputNotifyBroadcasts;-><init>()V

    return-object p0

    nop

    :sswitch_data_0
    .sparse-switch
        -0x4e248382 -> :sswitch_5
        -0x4743a4f4 -> :sswitch_4
        0x193b4417 -> :sswitch_3
        0x27bb1adc -> :sswitch_2
        0x4a95e84e -> :sswitch_1
        0x5c467992 -> :sswitch_0
    .end sparse-switch
.end method
