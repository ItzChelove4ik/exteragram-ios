.class public final synthetic Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Comparator;


# direct methods
.method public synthetic constructor <init>()V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final compare(Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 0

    .line 0
    check-cast p1, Lorg/telegram/tgnet/TLRPC$Chat;

    check-cast p2, Lorg/telegram/tgnet/TLRPC$Chat;

    invoke-static {p1, p2}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->$r8$lambda$6HdJlreNY2feI1YiMjmGZIb4Kyo(Lorg/telegram/tgnet/TLRPC$Chat;Lorg/telegram/tgnet/TLRPC$Chat;)I

    move-result p0

    return p0
.end method
