.class public abstract Lcom/exteragram/messenger/feed/FeedRequestNormalizer;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;
    }
.end annotation


# static fields
.field private static final EMPTY_FIELDS:[Ljava/lang/reflect/Field;

.field private static final EMPTY_METADATA:Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;

.field private static final metadataCache:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/Class<",
            "*>;",
            "Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static synthetic $r8$lambda$3v1ZZjmWnHXiJEBcg16a6ZyslPw(Ljava/lang/Class;)Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;
    .locals 0

    .line 0
    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->buildMetadata(Ljava/lang/Class;)Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;

    move-result-object p0

    return-object p0
.end method

.method static constructor <clinit>()V
    .locals 8

    const/4 v0, 0x0

    .line 16
    new-array v6, v0, [Ljava/lang/reflect/Field;

    sput-object v6, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->EMPTY_FIELDS:[Ljava/lang/reflect/Field;

    .line 17
    new-instance v1, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;

    const/4 v5, 0x0

    const/4 v7, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;-><init>(Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;[Ljava/lang/reflect/Field;Lcom/exteragram/messenger/feed/FeedRequestNormalizer-IA;)V

    sput-object v1, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->EMPTY_METADATA:Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;

    .line 18
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->metadataCache:Ljava/util/concurrent/ConcurrentHashMap;

    return-void
.end method

.method private static buildMetadata(Ljava/lang/Class;)Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;
    .locals 11
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;)",
            "Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;"
        }
    .end annotation

    .line 58
    :try_start_0
    invoke-virtual {p0}, Ljava/lang/Class;->getFields()[Ljava/lang/reflect/Field;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 60
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    const-string v1, "fields of "

    invoke-virtual {v1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v0}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->reportFailure(Ljava/lang/String;Ljava/lang/Exception;)V

    .line 61
    sget-object p0, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->EMPTY_FIELDS:[Ljava/lang/reflect/Field;

    .line 63
    :goto_0
    array-length v0, p0

    const/4 v1, 0x0

    const/4 v2, 0x0

    move v4, v1

    move-object v3, v2

    move-object v5, v3

    move-object v6, v5

    move-object v7, v6

    :goto_1
    if-ge v4, v0, :cond_6

    aget-object v8, p0, v4

    .line 64
    invoke-virtual {v8}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object v9

    .line 65
    const-string v10, "from_peer"

    invoke-virtual {v10, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_0

    if-nez v2, :cond_0

    move-object v2, v8

    goto :goto_2

    .line 67
    :cond_0
    const-string/jumbo v10, "peer"

    invoke-virtual {v10, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_1

    if-nez v5, :cond_1

    move-object v5, v8

    goto :goto_2

    .line 69
    :cond_1
    const-string v10, "channel"

    invoke-virtual {v10, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_2

    if-nez v6, :cond_2

    move-object v6, v8

    goto :goto_2

    .line 71
    :cond_2
    const-string/jumbo v10, "invoice"

    invoke-virtual {v10, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_3

    if-nez v7, :cond_3

    move-object v7, v8

    .line 74
    :cond_3
    :goto_2
    invoke-static {v8}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->isMessageIdField(Ljava/lang/reflect/Field;)Z

    move-result v9

    if-eqz v9, :cond_5

    if-nez v3, :cond_4

    .line 76
    new-instance v3, Ljava/util/ArrayList;

    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    .line 78
    :cond_4
    invoke-virtual {v3, v8}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_5
    add-int/lit8 v4, v4, 0x1

    goto :goto_1

    .line 81
    :cond_6
    new-instance p0, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;

    if-eqz v2, :cond_7

    move-object v4, v2

    goto :goto_3

    :cond_7
    move-object v4, v5

    :goto_3
    if-eqz v3, :cond_8

    .line 86
    new-array v0, v1, [Ljava/lang/reflect/Field;

    invoke-virtual {v3, v0}, Ljava/util/ArrayList;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/reflect/Field;

    :goto_4
    move-object v8, v0

    goto :goto_5

    :cond_8
    sget-object v0, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->EMPTY_FIELDS:[Ljava/lang/reflect/Field;

    goto :goto_4

    :goto_5
    const/4 v9, 0x0

    move-object v3, p0

    invoke-direct/range {v3 .. v9}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;-><init>(Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;[Ljava/lang/reflect/Field;Lcom/exteragram/messenger/feed/FeedRequestNormalizer-IA;)V

    return-object v3
.end method

.method private static getChannelDialogId(Ljava/lang/reflect/Field;Ljava/lang/Object;)J
    .locals 0

    .line 225
    invoke-static {p0, p1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->getFieldValue(Ljava/lang/reflect/Field;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 226
    instance-of p1, p0, Lorg/telegram/tgnet/TLRPC$InputChannel;

    if-eqz p1, :cond_0

    .line 227
    check-cast p0, Lorg/telegram/tgnet/TLRPC$InputChannel;

    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->getInputChannelDialogId(Lorg/telegram/tgnet/TLRPC$InputChannel;)J

    move-result-wide p0

    return-wide p0

    :cond_0
    const-wide/16 p0, 0x0

    return-wide p0
.end method

.method private static getDialogId(Ljava/lang/reflect/Field;Ljava/lang/Object;)J
    .locals 4

    const-wide/16 v0, 0x0

    if-nez p0, :cond_0

    return-wide v0

    .line 214
    :cond_0
    :try_start_0
    invoke-virtual {p0, p1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    .line 215
    instance-of v2, p1, Lorg/telegram/tgnet/TLRPC$InputPeer;

    if-eqz v2, :cond_1

    .line 216
    check-cast p1, Lorg/telegram/tgnet/TLRPC$InputPeer;

    invoke-static {p1}, Lorg/telegram/messenger/DialogObject;->getPeerDialogId(Lorg/telegram/tgnet/TLRPC$InputPeer;)J

    move-result-wide p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-wide p0

    :catch_0
    move-exception p1

    .line 219
    new-instance v2, Ljava/lang/StringBuilder;

    const-string/jumbo v3, "peer field "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, p1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->reportFailure(Ljava/lang/String;Ljava/lang/Exception;)V

    :cond_1
    return-wide v0
.end method

.method private static getFieldValue(Ljava/lang/reflect/Field;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    .line 244
    :cond_0
    :try_start_0
    invoke-virtual {p0, p1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p1

    .line 246
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "field "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, p1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->reportFailure(Ljava/lang/String;Ljava/lang/Exception;)V

    return-object v0
.end method

.method private static getInputChannelDialogId(Lorg/telegram/tgnet/TLRPC$InputChannel;)J
    .locals 4

    const-wide/16 v0, 0x0

    if-eqz p0, :cond_1

    .line 233
    iget-wide v2, p0, Lorg/telegram/tgnet/TLRPC$InputChannel;->channel_id:J

    cmp-long p0, v2, v0

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    neg-long v0, v2

    :cond_1
    :goto_0
    return-wide v0
.end method

.method private static getMetadata(Ljava/lang/Object;)Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;
    .locals 2

    if-nez p0, :cond_0

    .line 45
    sget-object p0, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->EMPTY_METADATA:Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;

    return-object p0

    .line 47
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->metadataCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    new-instance v1, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$$ExternalSyntheticLambda0;

    invoke-direct {v1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$$ExternalSyntheticLambda0;-><init>()V

    invoke-virtual {v0, p0, v1}, Ljava/util/concurrent/ConcurrentHashMap;->computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;

    return-object p0
.end method

.method private static isMessageIdField(Ljava/lang/reflect/Field;)Z
    .locals 2

    const/4 v0, 0x0

    if-eqz p0, :cond_3

    .line 125
    invoke-virtual {p0}, Ljava/lang/reflect/Field;->getModifiers()I

    move-result v1

    invoke-static {v1}, Ljava/lang/reflect/Modifier;->isStatic(I)Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_1

    .line 128
    :cond_0
    invoke-virtual {p0}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object p0

    .line 129
    const-string/jumbo v1, "id"

    invoke-virtual {v1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    const-string/jumbo v1, "msg_id"

    invoke-virtual {v1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    const-string v1, "_msg_id"

    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_1

    goto :goto_0

    :cond_1
    return v0

    :cond_2
    :goto_0
    const/4 p0, 0x1

    return p0

    :cond_3
    :goto_1
    return v0
.end method

.method private static mergeResolvedDialogIds(JJ)J
    .locals 3

    const-wide/16 v0, 0x0

    cmp-long v2, p0, v0

    if-nez v2, :cond_0

    return-wide p2

    :cond_0
    cmp-long v2, p2, v0

    if-nez v2, :cond_1

    goto :goto_0

    :cond_1
    cmp-long p2, p0, p2

    if-nez p2, :cond_2

    :goto_0
    return-wide p0

    :cond_2
    return-wide v0
.end method

.method public static normalize(ILorg/telegram/tgnet/TLObject;)Lorg/telegram/tgnet/TLObject;
    .locals 3

    if-nez p1, :cond_0

    return-object p1

    .line 27
    :cond_0
    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedController;->peekInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 28
    invoke-virtual {v0}, Lcom/exteragram/messenger/feed/FeedController;->hasNoSyntheticIds()Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_0

    .line 31
    :cond_1
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    const-string/jumbo v2, "org.telegram.tgnet."

    invoke-virtual {v1, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_0

    .line 34
    :cond_2
    invoke-static {p1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->getMetadata(Ljava/lang/Object;)Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;

    move-result-object v1

    .line 35
    invoke-static {v1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;->-$$Nest$fgetmessageIdFields(Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)[Ljava/lang/reflect/Field;

    move-result-object v2

    array-length v2, v2

    if-nez v2, :cond_3

    invoke-static {v1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;->-$$Nest$fgetinvoiceField(Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)Ljava/lang/reflect/Field;

    move-result-object v2

    if-nez v2, :cond_3

    goto :goto_0

    .line 38
    :cond_3
    invoke-static {p0, v0, p1, v1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->normalizeMessageIds(ILcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)V

    .line 39
    invoke-static {v1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;->-$$Nest$fgetinvoiceField(Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)Ljava/lang/reflect/Field;

    move-result-object v1

    invoke-static {v1, p1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->getFieldValue(Ljava/lang/reflect/Field;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    invoke-static {p0, v0, v1}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->normalizeInvoice(ILcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;)V

    :cond_4
    :goto_0
    return-object p1
.end method

.method private static normalizeInvoice(ILcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;)V
    .locals 1

    .line 133
    instance-of v0, p2, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceMessage;

    if-eqz v0, :cond_0

    .line 134
    invoke-static {p0, p1, p2}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->normalizeMessageIds(ILcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;)V

    :cond_0
    return-void
.end method

.method private static normalizeMessageIdField(Lcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;Ljava/lang/reflect/Field;)J
    .locals 9

    const-wide/16 v0, 0x0

    .line 141
    :try_start_0
    invoke-virtual {p2, p1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    .line 142
    instance-of v3, v2, Ljava/lang/Integer;

    if-eqz v3, :cond_0

    check-cast v2, Ljava/lang/Integer;

    .line 143
    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v3

    invoke-virtual {p0, v3}, Lcom/exteragram/messenger/feed/FeedController;->resolveRealDialogId(I)J

    move-result-wide v3

    cmp-long v5, v3, v0

    if-eqz v5, :cond_3

    .line 145
    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    invoke-virtual {p0, v3, v4, v2}, Lcom/exteragram/messenger/feed/FeedController;->resolveRealMessageId(JI)I

    move-result p0

    invoke-virtual {p2, p1, p0}, Ljava/lang/reflect/Field;->setInt(Ljava/lang/Object;I)V

    return-wide v3

    :catch_0
    move-exception p0

    goto :goto_2

    .line 148
    :cond_0
    instance-of p1, v2, Ljava/util/ArrayList;

    if-eqz p1, :cond_3

    check-cast v2, Ljava/util/ArrayList;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const/4 p1, 0x0

    move-wide v3, v0

    .line 149
    :goto_0
    :try_start_1
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v5

    if-ge p1, v5, :cond_2

    .line 150
    invoke-virtual {v2, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    .line 151
    instance-of v6, v5, Ljava/lang/Integer;

    if-eqz v6, :cond_1

    check-cast v5, Ljava/lang/Integer;

    .line 152
    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v6

    invoke-virtual {p0, v6}, Lcom/exteragram/messenger/feed/FeedController;->resolveRealDialogId(I)J

    move-result-wide v6

    cmp-long v8, v6, v0

    if-eqz v8, :cond_1

    .line 154
    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    invoke-virtual {p0, v6, v7, v5}, Lcom/exteragram/messenger/feed/FeedController;->resolveRealMessageId(JI)I

    move-result v5

    invoke-static {v2, p1, v5}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->setListInteger(Ljava/util/ArrayList;II)V

    .line 155
    invoke-static {v3, v4, v6, v7}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->mergeResolvedDialogIds(JJ)J

    move-result-wide v3
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_1

    :catch_1
    move-exception p0

    move-wide v0, v3

    goto :goto_2

    :cond_1
    :goto_1
    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    :cond_2
    return-wide v3

    :cond_3
    return-wide v0

    .line 161
    :goto_2
    new-instance p1, Ljava/lang/StringBuilder;

    const-string/jumbo v2, "message id field "

    invoke-direct {p1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->reportFailure(Ljava/lang/String;Ljava/lang/Exception;)V

    return-wide v0
.end method

.method private static normalizeMessageIdFields(Lcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)J
    .locals 6

    const-wide/16 v0, 0x0

    if-nez p1, :cond_0

    return-wide v0

    .line 118
    :cond_0
    invoke-static {p2}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;->-$$Nest$fgetmessageIdFields(Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)[Ljava/lang/reflect/Field;

    move-result-object p2

    array-length v2, p2

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    aget-object v4, p2, v3

    .line 119
    invoke-static {p0, p1, v4}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->normalizeMessageIdField(Lcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;Ljava/lang/reflect/Field;)J

    move-result-wide v4

    invoke-static {v0, v1, v4, v5}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->mergeResolvedDialogIds(JJ)J

    move-result-wide v0

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    return-wide v0
.end method

.method private static normalizeMessageIds(ILcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;)V
    .locals 1

    .line 91
    invoke-static {p2}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->getMetadata(Ljava/lang/Object;)Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;

    move-result-object v0

    invoke-static {p0, p1, p2, v0}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->normalizeMessageIds(ILcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)V

    return-void
.end method

.method private static normalizeMessageIds(ILcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)V
    .locals 7

    .line 95
    invoke-static {p3}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;->-$$Nest$fgetrequestPeerField(Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)Ljava/lang/reflect/Field;

    move-result-object v0

    .line 96
    invoke-static {v0, p2}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->getDialogId(Ljava/lang/reflect/Field;Ljava/lang/Object;)J

    move-result-wide v1

    const-wide/16 v3, 0x0

    cmp-long v5, v1, v3

    if-nez v5, :cond_0

    .line 98
    invoke-static {p3}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;->-$$Nest$fgetpeerField(Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)Ljava/lang/reflect/Field;

    move-result-object v1

    invoke-static {v1, p2}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->getDialogId(Ljava/lang/reflect/Field;Ljava/lang/Object;)J

    move-result-wide v1

    :cond_0
    cmp-long v5, v1, v3

    if-nez v5, :cond_1

    .line 101
    invoke-static {p3}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;->-$$Nest$fgetchannelField(Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)Ljava/lang/reflect/Field;

    move-result-object v1

    invoke-static {v1, p2}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->getChannelDialogId(Ljava/lang/reflect/Field;Ljava/lang/Object;)J

    move-result-wide v1

    .line 103
    :cond_1
    invoke-static {p1, p2, p3}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->normalizeMessageIdFields(Lcom/exteragram/messenger/feed/FeedController;Ljava/lang/Object;Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)J

    move-result-wide v5

    cmp-long p1, v5, v3

    if-eqz p1, :cond_3

    cmp-long p1, v5, v1

    if-eqz p1, :cond_3

    if-eqz v0, :cond_2

    .line 106
    invoke-static {p0, v0, p2, v5, v6}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->setInputPeer(ILjava/lang/reflect/Field;Ljava/lang/Object;J)V

    return-void

    .line 107
    :cond_2
    invoke-static {p3}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;->-$$Nest$fgetchannelField(Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)Ljava/lang/reflect/Field;

    move-result-object p1

    if-eqz p1, :cond_3

    .line 108
    invoke-static {p3}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;->-$$Nest$fgetchannelField(Lcom/exteragram/messenger/feed/FeedRequestNormalizer$ClassMetadata;)Ljava/lang/reflect/Field;

    move-result-object p1

    invoke-static {p0, p1, p2, v5, v6}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->setInputChannel(ILjava/lang/reflect/Field;Ljava/lang/Object;J)V

    :cond_3
    return-void
.end method

.method private static reportFailure(Ljava/lang/String;Ljava/lang/Exception;)V
    .locals 2

    .line 252
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "FeedRequestNormalizer failed to normalize "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method private static setInputChannel(ILjava/lang/reflect/Field;Ljava/lang/Object;J)V
    .locals 2

    if-ltz p0, :cond_1

    const-wide/16 v0, 0x0

    cmp-long v0, p3, v0

    if-ltz v0, :cond_0

    goto :goto_0

    .line 200
    :cond_0
    :try_start_0
    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    neg-long p3, p3

    invoke-virtual {p0, p3, p4}, Lorg/telegram/messenger/MessagesController;->getInputChannel(J)Lorg/telegram/tgnet/TLRPC$InputChannel;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 202
    invoke-virtual {p1, p2, p0}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 205
    new-instance p2, Ljava/lang/StringBuilder;

    const-string p3, "channel field "

    invoke-direct {p2, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->reportFailure(Ljava/lang/String;Ljava/lang/Exception;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private static setInputPeer(ILjava/lang/reflect/Field;Ljava/lang/Object;J)V
    .locals 0

    if-gez p0, :cond_0

    goto :goto_0

    .line 186
    :cond_0
    :try_start_0
    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    invoke-virtual {p0, p3, p4}, Lorg/telegram/messenger/MessagesController;->getInputPeer(J)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 188
    invoke-virtual {p1, p2, p0}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    goto :goto_1

    :cond_1
    :goto_0
    return-void

    .line 191
    :goto_1
    new-instance p2, Ljava/lang/StringBuilder;

    const-string/jumbo p3, "peer field "

    invoke-direct {p2, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lcom/exteragram/messenger/feed/FeedRequestNormalizer;->reportFailure(Ljava/lang/String;Ljava/lang/Exception;)V

    return-void
.end method

.method private static setListInteger(Ljava/util/ArrayList;II)V
    .locals 0

    .line 168
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Ljava/util/ArrayList;->set(ILjava/lang/Object;)Ljava/lang/Object;

    return-void
.end method
