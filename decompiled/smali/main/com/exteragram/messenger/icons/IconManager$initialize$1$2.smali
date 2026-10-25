.class final Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/icons/IconManager$initialize$1;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/coroutines/jvm/internal/SuspendLambda;",
        "Lkotlin/jvm/functions/Function2<",
        "Lkotlinx/coroutines/CoroutineScope;",
        "Lkotlin/coroutines/Continuation<",
        "-",
        "Lkotlin/Unit;",
        ">;",
        "Ljava/lang/Object;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"
    }
    d2 = {
        "<anonymous>",
        "",
        "Lkotlinx/coroutines/CoroutineScope;"
    }
    k = 0x3
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation runtime Lkotlin/coroutines/jvm/internal/DebugMetadata;
    c = "com.exteragram.messenger.icons.IconManager$initialize$1$2"
    f = "IconManager.kt"
    i = {}
    l = {}
    m = "invokeSuspend"
    n = {}
    s = {}
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$initialize$1$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1058:1\n296#2,2:1059\n296#2,2:1061\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$initialize$1$2\n*L\n839#1:1059,2\n840#1:1061,2\n*E\n"
    }
.end annotation


# instance fields
.field final synthetic $generation:J

.field final synthetic $result:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;

.field label:I


# direct methods
.method public constructor <init>(JLcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;Lkotlin/coroutines/Continuation;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J",
            "Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;",
            ">;)V"
        }
    .end annotation

    iput-wide p1, p0, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->$generation:J

    iput-object p3, p0, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->$result:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Lkotlin/coroutines/Continuation<",
            "*>;)",
            "Lkotlin/coroutines/Continuation<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation

    new-instance p1, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;

    iget-wide v0, p0, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->$generation:J

    iget-object p0, p0, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->$result:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;

    invoke-direct {p1, v0, v1, p0, p2}, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;-><init>(JLcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->invoke(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invoke(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlinx/coroutines/CoroutineScope;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Unit;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    .line 0
    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 834
    iget v0, p0, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->label:I

    const/4 v1, 0x0

    if-nez v0, :cond_d

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 835
    iget-wide v2, p0, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->$generation:J

    invoke-static {}, Lcom/exteragram/messenger/icons/IconManager;->access$getInitializationGeneration$p()J

    move-result-wide v4

    cmp-long p1, v2, v4

    if-eqz p1, :cond_0

    .line 836
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 838
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditingIconPackId()Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x1

    const/4 v2, 0x0

    if-eqz p1, :cond_8

    .line 839
    invoke-static {}, Lcom/exteragram/messenger/icons/IconManager;->access$getActivePacks$p()Ljava/util/concurrent/CopyOnWriteArrayList;

    move-result-object p1

    .line 296
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    move-object v4, v3

    check-cast v4, Lcom/exteragram/messenger/icons/IconPack;

    .line 839
    invoke-virtual {v4}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v4

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditingIconPackId()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    goto :goto_0

    :cond_2
    move-object v3, v1

    :goto_0
    check-cast v3, Lcom/exteragram/messenger/icons/IconPack;

    .line 840
    invoke-static {}, Lcom/exteragram/messenger/icons/IconManager;->access$getActivePacks$p()Ljava/util/concurrent/CopyOnWriteArrayList;

    move-result-object p1

    .line 296
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_3
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_4

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    move-object v5, v4

    check-cast v5, Lcom/exteragram/messenger/icons/IconPack;

    .line 840
    invoke-virtual {v5}, Lcom/exteragram/messenger/icons/IconPack;->isBase()Z

    move-result v5

    if-nez v5, :cond_3

    goto :goto_1

    :cond_4
    move-object v4, v1

    :goto_1
    check-cast v4, Lcom/exteragram/messenger/icons/IconPack;

    if-eqz v3, :cond_7

    .line 842
    invoke-virtual {v3}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object p1

    if-eqz v4, :cond_5

    invoke-virtual {v4}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v3

    goto :goto_2

    :cond_5
    move-object v3, v1

    :goto_2
    invoke-static {p1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_6

    goto :goto_3

    .line 850
    :cond_6
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object p1

    if-eqz p1, :cond_8

    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p1

    if-eqz p1, :cond_8

    .line 851
    instance-of v3, p1, Lorg/telegram/ui/LaunchActivity;

    if-eqz v3, :cond_8

    .line 852
    check-cast p1, Lorg/telegram/ui/LaunchActivity;

    invoke-static {p1, v0}, Lcom/exteragram/messenger/icons/ui/picker/IconPickerController;->setActive(Lorg/telegram/ui/LaunchActivity;Z)V

    goto :goto_4

    .line 843
    :cond_7
    :goto_3
    invoke-static {v1}, Lcom/exteragram/messenger/ExteraConfig;->setEditingIconPackId(Ljava/lang/String;)V

    .line 844
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object p1

    if-eqz p1, :cond_8

    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p1

    if-eqz p1, :cond_8

    .line 845
    instance-of v3, p1, Lorg/telegram/ui/LaunchActivity;

    if-eqz v3, :cond_8

    .line 846
    check-cast p1, Lorg/telegram/ui/LaunchActivity;

    invoke-static {p1, v2}, Lcom/exteragram/messenger/icons/ui/picker/IconPickerController;->setActive(Lorg/telegram/ui/LaunchActivity;Z)V

    .line 858
    :cond_8
    :goto_4
    iget-object p1, p0, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->$result:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;

    sget-object v3, Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;->INITIAL:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;

    if-ne p1, v3, :cond_9

    invoke-static {}, Lcom/exteragram/messenger/icons/IconManager;->access$getInitializationTimedOut$p()Z

    move-result p1

    if-nez p1, :cond_9

    .line 859
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 862
    :cond_9
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    .line 863
    sget v4, Lorg/telegram/messenger/NotificationCenter;->iconPackUpdated:I

    new-array v5, v2, [Ljava/lang/Object;

    invoke-virtual {p1, v4, v5}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 864
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object p1

    if-eqz p1, :cond_c

    iget-object p0, p0, Lcom/exteragram/messenger/icons/IconManager$initialize$1$2;->$result:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;

    .line 865
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v4

    invoke-static {v4}, Lorg/telegram/ui/ActionBar/Theme;->reloadAllResources(Landroid/content/Context;)V

    if-ne p0, v3, :cond_b

    .line 867
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    instance-of p1, p0, Lorg/telegram/ui/LaunchActivity;

    if-eqz p1, :cond_a

    move-object v1, p0

    check-cast v1, Lorg/telegram/ui/LaunchActivity;

    :cond_a
    if-eqz v1, :cond_c

    invoke-virtual {v1, v0}, Lorg/telegram/ui/LaunchActivity;->rebuildAllFragments(Z)V

    goto :goto_5

    .line 869
    :cond_b
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object p0

    if-eqz p0, :cond_c

    invoke-interface {p0, v2}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    .line 872
    :cond_c
    :goto_5
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 834
    :cond_d
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
