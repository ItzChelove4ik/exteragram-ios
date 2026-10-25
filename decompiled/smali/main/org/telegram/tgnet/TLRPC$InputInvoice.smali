.class public abstract Lorg/telegram/tgnet/TLRPC$InputInvoice;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "InputInvoice"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 61561
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$InputInvoice;
    .locals 2

    .line 61564
    const-class v0, Lorg/telegram/tgnet/TLRPC$InputInvoice;

    invoke-static {p1}, Lorg/telegram/tgnet/TLRPC$InputInvoice;->fromConstructor(I)Lorg/telegram/tgnet/TLRPC$InputInvoice;

    move-result-object v1

    invoke-static {v0, v1, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$InputInvoice;

    return-object p0
.end method

.method private static fromConstructor(I)Lorg/telegram/tgnet/TLRPC$InputInvoice;
    .locals 0

    sparse-switch p0, :sswitch_data_0

    const/4 p0, 0x0

    return-object p0

    .line 61576
    :sswitch_0
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStars;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStars;-><init>()V

    return-object p0

    .line 61582
    :sswitch_1
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftUpgrade;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftUpgrade;-><init>()V

    return-object p0

    .line 61584
    :sswitch_2
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftTransfer;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftTransfer;-><init>()V

    return-object p0

    .line 61592
    :sswitch_3
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoicePremiumAuthCode;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoicePremiumAuthCode;-><init>()V

    return-object p0

    .line 61578
    :sswitch_4
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceChatInviteSubscription;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceChatInviteSubscription;-><init>()V

    return-object p0

    .line 61596
    :sswitch_5
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftAuctionBid;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftAuctionBid;-><init>()V

    return-object p0

    .line 61594
    :sswitch_6
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftDropOriginalDetails;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftDropOriginalDetails;-><init>()V

    return-object p0

    .line 61588
    :sswitch_7
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftResale;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftResale;-><init>()V

    return-object p0

    .line 61580
    :sswitch_8
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGift;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGift;-><init>()V

    return-object p0

    .line 61586
    :sswitch_9
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoicePremiumGiftStars;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoicePremiumGiftStars;-><init>()V

    return-object p0

    .line 61572
    :sswitch_a
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceMessage;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceMessage;-><init>()V

    return-object p0

    .line 61574
    :sswitch_b
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;-><init>()V

    return-object p0

    .line 61590
    :sswitch_c
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftPrepaidUpgrade;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceStarGiftPrepaidUpgrade;-><init>()V

    return-object p0

    .line 61570
    :sswitch_d
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputInvoicePremiumGiftCode;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoicePremiumGiftCode;-><init>()V

    return-object p0

    nop

    :sswitch_data_0
    .sparse-switch
        -0x676793f3 -> :sswitch_d
        -0x65f4b748 -> :sswitch_c
        -0x3cd93511 -> :sswitch_b
        -0x3a4a97a7 -> :sswitch_a
        -0x25454d11 -> :sswitch_9
        -0x179da16e -> :sswitch_8
        -0x164f39a8 -> :sswitch_7
        0x923d8d1 -> :sswitch_6
        0x1ecafa10 -> :sswitch_5
        0x34e793f1 -> :sswitch_4
        0x3e77f614 -> :sswitch_3
        0x4a5f5bd9 -> :sswitch_2
        0x4d818d5d -> :sswitch_1
        0x65f00ce3 -> :sswitch_0
    .end sparse-switch
.end method
