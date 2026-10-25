.class public final Landroidx/car/app/messaging/model/ConversationItem$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/messaging/model/ConversationItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field final mActions:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/car/app/model/Action;",
            ">;"
        }
    .end annotation
.end field

.field mConversationCallbackDelegate:Landroidx/car/app/messaging/model/ConversationCallbackDelegate;

.field mIcon:Landroidx/car/app/model/CarIcon;

.field mId:Ljava/lang/String;

.field mIsGroupConversation:Z

.field mMessages:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/car/app/messaging/model/CarMessage;",
            ">;"
        }
    .end annotation
.end field

.field mSelf:Landroidx/core/app/Person;

.field mTitle:Landroidx/car/app/model/CarText;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 328
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 329
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mActions:Ljava/util/List;

    return-void
.end method

.method public constructor <init>(Landroidx/car/app/messaging/model/ConversationItem;)V
    .locals 1

    .line 333
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 334
    invoke-virtual {p1}, Landroidx/car/app/messaging/model/ConversationItem;->getId()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mId:Ljava/lang/String;

    .line 335
    invoke-virtual {p1}, Landroidx/car/app/messaging/model/ConversationItem;->getTitle()Landroidx/car/app/model/CarText;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mTitle:Landroidx/car/app/model/CarText;

    .line 336
    invoke-virtual {p1}, Landroidx/car/app/messaging/model/ConversationItem;->getSelf()Landroidx/core/app/Person;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mSelf:Landroidx/core/app/Person;

    .line 337
    invoke-virtual {p1}, Landroidx/car/app/messaging/model/ConversationItem;->getIcon()Landroidx/car/app/model/CarIcon;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mIcon:Landroidx/car/app/model/CarIcon;

    .line 338
    invoke-virtual {p1}, Landroidx/car/app/messaging/model/ConversationItem;->isGroupConversation()Z

    move-result v0

    iput-boolean v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mIsGroupConversation:Z

    .line 339
    invoke-virtual {p1}, Landroidx/car/app/messaging/model/ConversationItem;->getConversationCallbackDelegate()Landroidx/car/app/messaging/model/ConversationCallbackDelegate;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mConversationCallbackDelegate:Landroidx/car/app/messaging/model/ConversationCallbackDelegate;

    .line 340
    invoke-virtual {p1}, Landroidx/car/app/messaging/model/ConversationItem;->getMessages()Ljava/util/List;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mMessages:Ljava/util/List;

    .line 341
    new-instance v0, Ljava/util/ArrayList;

    invoke-virtual {p1}, Landroidx/car/app/messaging/model/ConversationItem;->getActions()Ljava/util/List;

    move-result-object p1

    invoke-direct {v0, p1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    iput-object v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mActions:Ljava/util/List;

    return-void
.end method


# virtual methods
.method public build()Landroidx/car/app/messaging/model/ConversationItem;
    .locals 1

    .line 324
    new-instance v0, Landroidx/car/app/messaging/model/ConversationItem;

    invoke-direct {v0, p0}, Landroidx/car/app/messaging/model/ConversationItem;-><init>(Landroidx/car/app/messaging/model/ConversationItem$Builder;)V

    return-object v0
.end method

.method public setConversationCallback(Landroidx/car/app/messaging/model/ConversationCallback;)Landroidx/car/app/messaging/model/ConversationItem$Builder;
    .locals 1
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "MissingGetterMatchingBuilder",
            "ExecutorRegistration"
        }
    .end annotation

    .line 299
    new-instance v0, Landroidx/car/app/messaging/model/ConversationCallbackDelegateImpl;

    .line 300
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    check-cast p1, Landroidx/car/app/messaging/model/ConversationCallback;

    invoke-direct {v0, p1}, Landroidx/car/app/messaging/model/ConversationCallbackDelegateImpl;-><init>(Landroidx/car/app/messaging/model/ConversationCallback;)V

    iput-object v0, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mConversationCallbackDelegate:Landroidx/car/app/messaging/model/ConversationCallbackDelegate;

    return-object p0
.end method

.method public setGroupConversation(Z)Landroidx/car/app/messaging/model/ConversationItem$Builder;
    .locals 0

    .line 280
    iput-boolean p1, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mIsGroupConversation:Z

    return-object p0
.end method

.method public setIcon(Landroidx/car/app/model/CarIcon;)Landroidx/car/app/messaging/model/ConversationItem$Builder;
    .locals 0

    .line 251
    iput-object p1, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mIcon:Landroidx/car/app/model/CarIcon;

    return-object p0
.end method

.method public setId(Ljava/lang/String;)Landroidx/car/app/messaging/model/ConversationItem$Builder;
    .locals 0

    .line 237
    iput-object p1, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mId:Ljava/lang/String;

    return-object p0
.end method

.method public setMessages(Ljava/util/List;)Landroidx/car/app/messaging/model/ConversationItem$Builder;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroidx/car/app/messaging/model/CarMessage;",
            ">;)",
            "Landroidx/car/app/messaging/model/ConversationItem$Builder;"
        }
    .end annotation

    .line 291
    iput-object p1, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mMessages:Ljava/util/List;

    return-object p0
.end method

.method public setSelf(Landroidx/core/app/Person;)Landroidx/car/app/messaging/model/ConversationItem$Builder;
    .locals 0

    .line 264
    iput-object p1, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mSelf:Landroidx/core/app/Person;

    return-object p0
.end method

.method public setTitle(Landroidx/car/app/model/CarText;)Landroidx/car/app/messaging/model/ConversationItem$Builder;
    .locals 0

    .line 244
    iput-object p1, p0, Landroidx/car/app/messaging/model/ConversationItem$Builder;->mTitle:Landroidx/car/app/model/CarText;

    return-object p0
.end method
