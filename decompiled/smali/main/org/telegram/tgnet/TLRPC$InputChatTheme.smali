.class public Lorg/telegram/tgnet/TLRPC$InputChatTheme;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "InputChatTheme"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$InputChatTheme;
    .locals 2

    const-class v0, Lorg/telegram/tgnet/TLRPC$InputChatTheme;

    invoke-static {p1}, Lorg/telegram/tgnet/TLRPC$InputChatTheme;->fromConstructor(I)Lorg/telegram/tgnet/TLRPC$InputChatTheme;

    move-result-object v1

    invoke-static {v0, v1, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$InputChatTheme;

    return-object p0
.end method

.method private static fromConstructor(I)Lorg/telegram/tgnet/TLRPC$InputChatTheme;
    .locals 1

    const v0, -0x7cd97b7d

    if-eq p0, v0, :cond_2

    const v0, -0x781a201c

    if-eq p0, v0, :cond_1

    const v0, -0x36c216a4    # -777877.75f

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    new-instance p0, Lorg/telegram/tgnet/TLRPC$Tl_inputChatTheme;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$Tl_inputChatTheme;-><init>()V

    return-object p0

    :cond_1
    new-instance p0, Lorg/telegram/tgnet/TLRPC$Tl_inputChatThemeUniqueGift;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$Tl_inputChatThemeUniqueGift;-><init>()V

    return-object p0

    :cond_2
    new-instance p0, Lorg/telegram/tgnet/TLRPC$Tl_inputChatThemeEmpty;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$Tl_inputChatThemeEmpty;-><init>()V

    return-object p0
.end method
