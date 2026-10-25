.class Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;
.super Landroid/view/View;
.source "SourceFile"


# instance fields
.field private final callbacks:Lme/vkryl/core/reference/ReferenceList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lme/vkryl/core/reference/ReferenceList<",
            "Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;",
            ">;"
        }
    .end annotation
.end field

.field private observer:Landroid/view/ViewTreeObserver;

.field private final onDrawListener:Landroid/view/ViewTreeObserver$OnDrawListener;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    .line 20
    invoke-direct {p0, p1}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    .line 15
    new-instance p1, Lme/vkryl/core/reference/ReferenceList;

    invoke-direct {p1}, Lme/vkryl/core/reference/ReferenceList;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->callbacks:Lme/vkryl/core/reference/ReferenceList;

    .line 16
    new-instance p1, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;)V

    iput-object p1, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->onDrawListener:Landroid/view/ViewTreeObserver$OnDrawListener;

    return-void
.end method

.method private dispatchOnPostDrawListeners()V
    .locals 1

    .line 47
    const-string v0, "OnPostDraw"

    invoke-static {v0}, Landroid/os/Trace;->beginSection(Ljava/lang/String;)V

    .line 49
    :try_start_0
    iget-object p0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->callbacks:Lme/vkryl/core/reference/ReferenceList;

    invoke-virtual {p0}, Lme/vkryl/core/reference/ReferenceList;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;

    .line 50
    invoke-interface {v0}, Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;->onPostDraw()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    .line 53
    :cond_0
    invoke-static {}, Landroid/os/Trace;->endSection()V

    return-void

    :catchall_0
    move-exception p0

    invoke-static {}, Landroid/os/Trace;->endSection()V

    .line 54
    throw p0
.end method


# virtual methods
.method public addOnPostDrawListener(Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V
    .locals 0

    .line 58
    iget-object p0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->callbacks:Lme/vkryl/core/reference/ReferenceList;

    invoke-virtual {p0, p1}, Lme/vkryl/core/reference/ReferenceList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public onAttachedToWindow()V
    .locals 1

    .line 25
    invoke-super {p0}, Landroid/view/View;->onAttachedToWindow()V

    .line 27
    invoke-virtual {p0}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->observer:Landroid/view/ViewTreeObserver;

    .line 28
    iget-object p0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->onDrawListener:Landroid/view/ViewTreeObserver$OnDrawListener;

    invoke-virtual {v0, p0}, Landroid/view/ViewTreeObserver;->addOnDrawListener(Landroid/view/ViewTreeObserver$OnDrawListener;)V

    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 2

    .line 33
    invoke-super {p0}, Landroid/view/View;->onDetachedFromWindow()V

    .line 35
    iget-object v0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->observer:Landroid/view/ViewTreeObserver;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/ViewTreeObserver;->isAlive()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 36
    iget-object v0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->observer:Landroid/view/ViewTreeObserver;

    iget-object v1, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->onDrawListener:Landroid/view/ViewTreeObserver$OnDrawListener;

    invoke-virtual {v0, v1}, Landroid/view/ViewTreeObserver;->removeOnDrawListener(Landroid/view/ViewTreeObserver$OnDrawListener;)V

    :cond_0
    const/4 v0, 0x0

    .line 38
    iput-object v0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->observer:Landroid/view/ViewTreeObserver;

    return-void
.end method

.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 0

    .line 43
    invoke-direct {p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->dispatchOnPostDrawListeners()V

    return-void
.end method

.method public removeOnPostDrawListener(Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V
    .locals 0

    .line 62
    iget-object p0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->callbacks:Lme/vkryl/core/reference/ReferenceList;

    invoke-virtual {p0, p1}, Lme/vkryl/core/reference/ReferenceList;->remove(Ljava/lang/Object;)Z

    return-void
.end method
