.class public final Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder$Companion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\u0008\u0086\u0003\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u0004\u001a\u00020\u0005H\u0007J\u0012\u0010\u0006\u001a\u00020\u00052\u0008\u0010\u0007\u001a\u0004\u0018\u00010\u0008H\u0007\u00a8\u0006\t"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder$Companion;",
        "",
        "<init>",
        "()V",
        "create",
        "Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;",
        "from",
        "fragment",
        "Lorg/telegram/ui/ActionBar/BaseFragment;",
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


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 107
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder$Companion;-><init>()V

    return-void
.end method


# virtual methods
.method public final create()Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 109
    new-instance p0, Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-object p0
.end method

.method public final from(Lorg/telegram/ui/ActionBar/BaseFragment;)Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    if-nez p1, :cond_0

    .line 114
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder$Companion;->create()Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;

    move-result-object p0

    return-object p0

    .line 117
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder$Companion;->create()Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;

    move-result-object p0

    .line 118
    const-string v0, "fragment"

    invoke-virtual {p0, v0, p1}, Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;->withCustom(Ljava/lang/String;Ljava/lang/Object;)Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;

    move-result-object p0

    .line 119
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getCurrentAccount()I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;->withAccount(I)Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;

    move-result-object p0

    .line 120
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;->withContext(Landroid/content/Context;)Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;

    move-result-object p0

    return-object p0
.end method
