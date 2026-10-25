.class final Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$ObserversGroup;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/NotificationCenter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ObserversGroupImpl"
.end annotation


# instance fields
.field private delegate:Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

.field private globalGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

.field private final ids:Landroidx/collection/MutableIntList;

.field private notificationCenter:Lorg/telegram/messenger/NotificationCenter;


# direct methods
.method private constructor <init>(Lorg/telegram/messenger/NotificationCenter;Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;)V
    .locals 1

    .line 743
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 740
    new-instance v0, Landroidx/collection/MutableIntList;

    invoke-direct {v0}, Landroidx/collection/MutableIntList;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->ids:Landroidx/collection/MutableIntList;

    .line 744
    iput-object p1, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->notificationCenter:Lorg/telegram/messenger/NotificationCenter;

    .line 745
    iput-object p2, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->delegate:Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/messenger/NotificationCenter;Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;Lorg/telegram/messenger/NotificationCenter-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;-><init>(Lorg/telegram/messenger/NotificationCenter;Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;)V

    return-void
.end method


# virtual methods
.method public add(I)Lorg/telegram/messenger/NotificationCenter$ObserversGroup;
    .locals 2

    .line 750
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->delegate:Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

    if-nez v0, :cond_0

    return-object p0

    .line 754
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->ids:Landroidx/collection/MutableIntList;

    invoke-virtual {v0, p1}, Landroidx/collection/MutableIntList;->add(I)Z

    .line 755
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->notificationCenter:Lorg/telegram/messenger/NotificationCenter;

    iget-object v1, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->delegate:Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

    invoke-static {v0, v1, p1}, Lorg/telegram/messenger/NotificationCenter;->-$$Nest$maddObserverInternal(Lorg/telegram/messenger/NotificationCenter;Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    return-object p0
.end method

.method public addGlobal(I)Lorg/telegram/messenger/NotificationCenter$ObserversGroup;
    .locals 3

    .line 761
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->delegate:Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

    if-nez v0, :cond_0

    return-object p0

    .line 765
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->globalGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    if-nez v0, :cond_1

    .line 766
    new-instance v0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v1

    iget-object v2, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->delegate:Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

    invoke-direct {v0, v1, v2}, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;-><init>(Lorg/telegram/messenger/NotificationCenter;Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;)V

    iput-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->globalGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    .line 769
    :cond_1
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->globalGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    invoke-virtual {v0, p1}, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->add(I)Lorg/telegram/messenger/NotificationCenter$ObserversGroup;

    return-object p0
.end method

.method public removeAllObservers()V
    .locals 6

    .line 775
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->delegate:Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

    if-nez v0, :cond_0

    return-void

    .line 779
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->globalGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 780
    invoke-virtual {v0}, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->removeAllObservers()V

    .line 781
    iput-object v1, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->globalGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    .line 784
    :cond_1
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->ids:Landroidx/collection/MutableIntList;

    invoke-virtual {v0}, Landroidx/collection/IntList;->getSize()I

    move-result v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v0, :cond_2

    .line 785
    iget-object v3, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->notificationCenter:Lorg/telegram/messenger/NotificationCenter;

    iget-object v4, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->delegate:Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

    iget-object v5, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->ids:Landroidx/collection/MutableIntList;

    invoke-virtual {v5, v2}, Landroidx/collection/IntList;->get(I)I

    move-result v5

    invoke-virtual {v3, v4, v5}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 787
    :cond_2
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->ids:Landroidx/collection/MutableIntList;

    invoke-virtual {v0}, Landroidx/collection/MutableIntList;->clear()V

    .line 788
    iput-object v1, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->notificationCenter:Lorg/telegram/messenger/NotificationCenter;

    .line 789
    iput-object v1, p0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->delegate:Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

    return-void
.end method
