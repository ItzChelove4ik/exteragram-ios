.class public final Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;,
        Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;,
        Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0007\n\u0002\u0008\u0008\u0018\u00002\u00020\u0001:\u0002 !B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\t\u001a\u00020\nH\u0016J\u0008\u0010\u000b\u001a\u00020\u000cH\u0002J\u0008\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\u000f\u001a\u00020\u000c2\u000c\u0010\u0010\u001a\u0008\u0012\u0004\u0012\u00020\u00110\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J0\u0010\u0014\u001a\u00020\u000c2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00082\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0014J0\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00082\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0014J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u0008H\u0002J\u0010\u0010\u001f\u001a\u00020\u000c2\u0006\u0010\u001e\u001a\u00020\u0008H\u0002R\u0014\u0010\u0004\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""
    }
    d2 = {
        "Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "entries",
        "Ljava/util/ArrayList;",
        "Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;",
        "lastEntryId",
        "",
        "onFragmentCreate",
        "",
        "formatContents",
        "",
        "getTitle",
        "",
        "fillItems",
        "items",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "Landroid/view/View;",
        "position",
        "x",
        "",
        "y",
        "onLongClick",
        "findEntry",
        "id",
        "deleteTurn",
        "HistoryItem",
        "Entry",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nAiHistoryActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AiHistoryActivity.kt\ncom/exteragram/messenger/ai/ui/activities/AiHistoryActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,146:1\n1915#2,2:147\n1915#2,2:149\n296#2,2:151\n363#2,7:153\n1915#2,2:160\n1586#2:162\n1661#2,3:163\n1915#2,2:166\n1586#2:168\n1661#2,3:169\n*S KotlinDebug\n*F\n+ 1 AiHistoryActivity.kt\ncom/exteragram/messenger/ai/ui/activities/AiHistoryActivity\n*L\n53#1:147,2\n84#1:149,2\n125#1:151,2\n128#1:153,7\n141#1:160,2\n143#1:162\n143#1:163,3\n68#1:166,2\n65#1:168\n65#1:169,3\n*E\n"
    }
.end annotation


# instance fields
.field private final entries:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;",
            ">;"
        }
    .end annotation
.end field

.field private lastEntryId:I


# direct methods
.method public static $r8$lambda$TeKMotS-U3GWzCgiEGZlnRzfEBQ(Lcom/exteragram/messenger/ai/data/Message;Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;)V
    .locals 0

    .line 114
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 115
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_0
    return-void
.end method

.method public static $r8$lambda$X7WILYf2_F_e3VttIMbLGN8pCPY(Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;Lorg/telegram/ui/Components/UItem;)V
    .locals 0

    .line 117
    iget p1, p1, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->deleteTurn(I)V

    return-void
.end method

.method public static $r8$lambda$gfvGL5WLbqSMT9-bA3eD6l_37F4(Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;)V
    .locals 1

    .line 104
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 105
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$ofDW94-8OSkXzL1ZijoW2hKrSBA(Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;Ljava/util/ArrayList;Ljava/util/List;)V
    .locals 3

    .line 67
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 68
    :cond_0
    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->getIndices(Ljava/util/Collection;)Lkotlin/ranges/IntRange;

    move-result-object v0

    .line 1915
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    move-object v1, v0

    check-cast v1, Lkotlin/collections/IntIterator;

    invoke-virtual {v1}, Lkotlin/collections/IntIterator;->nextInt()I

    move-result v1

    .line 68
    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    invoke-interface {p2, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/CharSequence;

    invoke-virtual {v2, v1}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->setContent(Ljava/lang/CharSequence;)V

    goto :goto_0

    .line 69
    :cond_1
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_2

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_2
    :goto_1
    return-void
.end method

.method public static $r8$lambda$yHw1Jp_QCjZs1mLMPCKkqJo8p1s(Z)V
    .locals 0

    .line 102
    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setSaveHistory(Z)V

    return-void
.end method

.method public static $r8$lambda$zMhmBRYvGwkxzZlxU_WuxAu4bFc(Ljava/util/ArrayList;Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;)V
    .locals 4

    .line 1586
    new-instance v0, Ljava/util/ArrayList;

    const/16 v1, 0xa

    invoke-static {p0, v1}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_0

    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v2, v2, 0x1

    .line 1662
    check-cast v3, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    .line 65
    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v3

    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Lcom/exteragram/messenger/ai/ui/MarkdownPreview;->format(Ljava/lang/String;)Ljava/lang/CharSequence;

    move-result-object v3

    .line 1662
    invoke-interface {v0, v3}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 66
    :cond_0
    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda3;

    invoke-direct {v1, p1, p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;Ljava/util/ArrayList;Ljava/util/List;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 33
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 49
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    const/16 v0, 0x64

    .line 50
    iput v0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->lastEntryId:I

    return-void
.end method

.method private final deleteTurn(I)V
    .locals 7

    .line 128
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    .line 364
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    move v3, v2

    move v4, v3

    :goto_0
    const/4 v5, -0x1

    if-ge v4, v1, :cond_1

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v4, v4, 0x1

    .line 365
    check-cast v6, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    .line 128
    invoke-virtual {v6}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getId()I

    move-result v6

    if-ne v6, p1, :cond_0

    goto :goto_1

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    move v3, v5

    :goto_1
    if-ne v3, v5, :cond_2

    goto/16 :goto_7

    .line 131
    :cond_2
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-virtual {p1, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object p1

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object p1

    const-string v0, "assistant"

    invoke-static {v0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_3

    add-int/lit8 p1, v3, -0x1

    goto :goto_2

    :cond_3
    move p1, v3

    :goto_2
    add-int/lit8 v1, p1, 0x1

    if-ltz p1, :cond_5

    .line 133
    iget-object v4, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v4

    if-ge v1, v4, :cond_5

    .line 134
    iget-object v4, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-virtual {v4, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v4

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object v4

    invoke-static {v0, v4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_5

    .line 135
    iget-object v4, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-virtual {v4, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v4

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object v4

    invoke-static {v0, v4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_4

    goto :goto_3

    :cond_4
    move v3, v1

    goto :goto_4

    :cond_5
    :goto_3
    move p1, v3

    .line 141
    :goto_4
    invoke-static {v3, p1}, Lkotlin/ranges/RangesKt;->downTo(II)Lkotlin/ranges/IntProgression;

    move-result-object p1

    .line 1915
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_5
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_6

    move-object v0, p1

    check-cast v0, Lkotlin/collections/IntIterator;

    invoke-virtual {v0}, Lkotlin/collections/IntIterator;->nextInt()I

    move-result v0

    .line 141
    iget-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    goto :goto_5

    .line 143
    :cond_6
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    .line 1586
    new-instance v0, Ljava/util/ArrayList;

    const/16 v1, 0xa

    invoke-static {p1, v1}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v1

    :goto_6
    if-ge v2, v1, :cond_7

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v2, v2, 0x1

    .line 1662
    check-cast v3, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    .line 143
    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v3

    .line 1662
    invoke-interface {v0, v3}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_6

    .line 143
    :cond_7
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-static {p1}, Lcom/exteragram/messenger/ai/AiConfig;->saveConversationHistory(Ljava/util/ArrayList;)V

    .line 144
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_8

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_8
    :goto_7
    return-void
.end method

.method private final findEntry(I)Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;
    .locals 4

    .line 125
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    .line 296
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    :cond_0
    if-ge v1, v0, :cond_1

    invoke-virtual {p0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v1, v1, 0x1

    move-object v3, v2

    check-cast v3, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    .line 125
    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getId()I

    move-result v3

    if-ne v3, p1, :cond_0

    goto :goto_0

    :cond_1
    const/4 v2, 0x0

    .line 297
    :goto_0
    check-cast v2, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    return-object v2
.end method

.method private final formatContents()V
    .locals 3

    .line 61
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 63
    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 64
    sget-object v1, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v2, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda2;

    invoke-direct {v2, v0, p0}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda2;-><init>(Ljava/util/ArrayList;Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;)V

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method


# virtual methods
.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 16
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            ")V"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v14, p1

    .line 77
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->SAVE_HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->getId()I

    move-result v1

    sget v2, Lorg/telegram/messenger/R$string;->MessageHistory:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asRippleCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 78
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSaveHistory()Z

    move-result v2

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 77
    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 79
    sget v1, Lorg/telegram/messenger/R$string;->HistoryInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 81
    iget-object v1, v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    return-void

    .line 83
    :cond_0
    iget-object v1, v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    const-string v4, "messages"

    invoke-static {v4, v1, v3}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 84
    iget-object v1, v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    .line 1915
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v3

    :goto_0
    if-ge v2, v3, :cond_1

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v2, v2, 0x1

    check-cast v4, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    .line 85
    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getId()I

    move-result v5

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v6

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getContent()Ljava/lang/CharSequence;

    move-result-object v7

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getExpanded()Z

    move-result v4

    invoke-static {v5, v6, v7, v4}, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell$Factory;->asHistoryCell(ILcom/exteragram/messenger/ai/data/Message;Ljava/lang/CharSequence;Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v4

    invoke-virtual {v14, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_1
    const/4 v15, 0x0

    .line 87
    invoke-static {v15}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 89
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->CLEAR_HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    sget v2, Lorg/telegram/messenger/R$string;->ClearHistory:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_delete:I

    const/16 v12, 0x6b8

    const/4 v13, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x1

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 90
    invoke-static {v15}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 74
    sget p0, Lorg/telegram/messenger/R$string;->MessageHistory:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 94
    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 p3, 0x1

    if-lez p2, :cond_3

    invoke-static {}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p4

    invoke-interface {p4}, Ljava/util/List;->size()I

    move-result p4

    if-le p2, p4, :cond_0

    goto :goto_0

    .line 101
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p2

    iget p4, p1, Lorg/telegram/ui/Components/UItem;->id:I

    sub-int/2addr p4, p3

    invoke-interface {p2, p4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    sget-object p4, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    aget p2, p4, p2

    if-eq p2, p3, :cond_2

    const/4 p1, 0x2

    if-ne p2, p1, :cond_1

    .line 103
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    new-instance p2, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda5;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;)V

    invoke-static {p0, p1, p3, p2}, Lcom/exteragram/messenger/ai/AiController;->clearHistory(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ZLjava/lang/Runnable;)V

    return-void

    .line 101
    :cond_1
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 102
    :cond_2
    new-instance p2, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda4;

    invoke-direct {p2}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda4;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 95
    :cond_3
    :goto_0
    iget p1, p1, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->findEntry(I)Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    move-result-object p1

    if-eqz p1, :cond_4

    .line 96
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->getExpanded()Z

    move-result p2

    xor-int/2addr p2, p3

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->setExpanded(Z)V

    .line 97
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_4

    invoke-virtual {p0, p3}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_4
    return-void
.end method

.method public onFragmentCreate()Z
    .locals 11

    .line 53
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getConversationHistory()Ljava/util/ArrayList;

    move-result-object v0

    .line 1915
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_0

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v2, v2, 0x1

    move-object v6, v3

    check-cast v6, Lcom/exteragram/messenger/ai/data/Message;

    .line 54
    iget-object v3, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->entries:Ljava/util/ArrayList;

    new-instance v4, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;

    iget v5, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->lastEntryId:I

    add-int/lit8 v7, v5, 0x1

    iput v7, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->lastEntryId:I

    const/16 v9, 0xc

    const/4 v10, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    invoke-direct/range {v4 .. v10}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;-><init>(ILcom/exteragram/messenger/ai/data/Message;Ljava/lang/CharSequence;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    invoke-virtual {v3, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 56
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->formatContents()V

    .line 57
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    move-result p0

    return p0
.end method

.method public onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z
    .locals 2

    .line 111
    iget-object p3, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p4, p3, Lcom/exteragram/messenger/ai/data/Message;

    if-eqz p4, :cond_0

    check-cast p3, Lcom/exteragram/messenger/ai/data/Message;

    goto :goto_0

    :cond_0
    const/4 p3, 0x0

    :goto_0
    if-nez p3, :cond_1

    const/4 p0, 0x0

    return p0

    .line 112
    :cond_1
    invoke-static {p0, p2}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/View;)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object p4

    .line 113
    sget p5, Lorg/telegram/messenger/R$drawable;->msg_copy:I

    sget v0, Lorg/telegram/messenger/R$string;->Copy:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda0;

    invoke-direct {v1, p3, p0}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/data/Message;Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;)V

    invoke-virtual {p4, p5, v0, v1}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 117
    sget p3, Lorg/telegram/messenger/R$drawable;->msg_delete:I

    sget p5, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {p5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p5

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;Lorg/telegram/ui/Components/UItem;)V

    const/4 p1, 0x1

    invoke-virtual {p4, p3, p5, p1, v0}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;ZLjava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 118
    sget-boolean p3, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz p3, :cond_2

    const/4 p3, 0x3

    goto :goto_1

    :cond_2
    const/4 p3, 0x5

    :goto_1
    invoke-virtual {p4, p3}, Lorg/telegram/ui/Components/ItemOptions;->setGravity(I)Lorg/telegram/ui/Components/ItemOptions;

    .line 119
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/RecyclerListView;->getClipBackground(Landroid/view/View;)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    invoke-virtual {p4, p0}, Lorg/telegram/ui/Components/ItemOptions;->setScrimViewBackground(Landroid/graphics/drawable/Drawable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 120
    invoke-virtual {p4}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    return p1
.end method
