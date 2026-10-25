.class final Lorg/telegram/messenger/NotificationCenter$WeakObserversGroupImpl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$ObserversGroup;
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/NotificationCenter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "WeakObserversGroupImpl"
.end annotation


# instance fields
.field private final observersGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

.field private final reference:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method private constructor <init>(Lorg/telegram/messenger/NotificationCenter;Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;)V
    .locals 2

    .line 797
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 798
    new-instance v0, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    const/4 v1, 0x0

    invoke-direct {v0, p1, p0, v1}, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;-><init>(Lorg/telegram/messenger/NotificationCenter;Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;Lorg/telegram/messenger/NotificationCenter-IA;)V

    iput-object v0, p0, Lorg/telegram/messenger/NotificationCenter$WeakObserversGroupImpl;->observersGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    .line 799
    new-instance p1, Ljava/lang/ref/WeakReference;

    invoke-direct {p1, p2}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object p1, p0, Lorg/telegram/messenger/NotificationCenter$WeakObserversGroupImpl;->reference:Ljava/lang/ref/WeakReference;

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/messenger/NotificationCenter;Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;Lorg/telegram/messenger/NotificationCenter-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/messenger/NotificationCenter$WeakObserversGroupImpl;-><init>(Lorg/telegram/messenger/NotificationCenter;Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;)V

    return-void
.end method


# virtual methods
.method public add(I)Lorg/telegram/messenger/NotificationCenter$ObserversGroup;
    .locals 0

    .line 815
    iget-object p0, p0, Lorg/telegram/messenger/NotificationCenter$WeakObserversGroupImpl;->observersGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->add(I)Lorg/telegram/messenger/NotificationCenter$ObserversGroup;

    move-result-object p0

    return-object p0
.end method

.method public addGlobal(I)Lorg/telegram/messenger/NotificationCenter$ObserversGroup;
    .locals 0

    .line 820
    iget-object p0, p0, Lorg/telegram/messenger/NotificationCenter$WeakObserversGroupImpl;->observersGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->addGlobal(I)Lorg/telegram/messenger/NotificationCenter$ObserversGroup;

    move-result-object p0

    return-object p0
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 1

    .line 804
    iget-object v0, p0, Lorg/telegram/messenger/NotificationCenter$WeakObserversGroupImpl;->reference:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;

    if-eqz v0, :cond_0

    .line 806
    invoke-interface {v0, p1, p2, p3}, Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;->didReceivedNotification(II[Ljava/lang/Object;)V

    return-void

    .line 808
    :cond_0
    new-instance p2, Ljava/lang/StringBuilder;

    const-string p3, "MEMORY_LEAK observer "

    invoke-direct {p2, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, " with destroyed WeakReference"

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    .line 809
    invoke-virtual {p0}, Lorg/telegram/messenger/NotificationCenter$WeakObserversGroupImpl;->removeAllObservers()V

    return-void
.end method

.method public removeAllObservers()V
    .locals 0

    .line 825
    iget-object p0, p0, Lorg/telegram/messenger/NotificationCenter$WeakObserversGroupImpl;->observersGroup:Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;

    invoke-virtual {p0}, Lorg/telegram/messenger/NotificationCenter$ObserversGroupImpl;->removeAllObservers()V

    return-void
.end method
