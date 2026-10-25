.class public abstract Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;,
        Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;
    }
.end annotation


# direct methods
.method public static bridge synthetic -$$Nest$smattachAllListeners(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->attachAllListeners(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$smdetachAllListeners(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->detachAllListeners(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$smgetViewState(Landroid/view/View;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->getViewState(Landroid/view/View;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;

    move-result-object p0

    return-object p0
.end method

.method public static addListener(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V
    .locals 2

    if-eqz p0, :cond_5

    if-nez p1, :cond_0

    goto :goto_1

    .line 22
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-virtual {p0}, Landroid/view/View;->getRootView()Landroid/view/View;

    move-result-object v0

    if-eq p0, v0, :cond_1

    goto :goto_0

    .line 23
    :cond_1
    const-string p0, "Cannot add OnPostDrawListener to root view"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 26
    :cond_2
    :goto_0
    invoke-static {p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->getViewState(Landroid/view/View;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;

    move-result-object v0

    if-nez v0, :cond_3

    .line 28
    new-instance v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;-><init>(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener-IA;)V

    .line 30
    sget v1, Lorg/telegram/messenger/R$id;->tag_view_on_post_draw_state:I

    invoke-virtual {p0, v1, v0}, Landroid/view/View;->setTag(ILjava/lang/Object;)V

    .line 31
    iget-object v1, v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->attachStateChangeListener:Landroid/view/View$OnAttachStateChangeListener;

    invoke-virtual {p0, v1}, Landroid/view/View;->addOnAttachStateChangeListener(Landroid/view/View$OnAttachStateChangeListener;)V

    .line 34
    :cond_3
    iget-object v1, v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->listeners:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    goto :goto_1

    .line 38
    :cond_4
    iget-object v1, v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->listeners:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 40
    invoke-virtual {p0}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v1

    if-eqz v1, :cond_5

    .line 41
    invoke-static {p0, v0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->attachToRoot(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    move-result-object p0

    if-eqz p0, :cond_5

    .line 43
    iget-object p0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;->viewOnPostDraw:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->addOnPostDrawListener(Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V

    :cond_5
    :goto_1
    return-void
.end method

.method private static attachAllListeners(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V
    .locals 4

    .line 107
    invoke-static {p0, p1}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->attachToRoot(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    move-result-object p0

    if-nez p0, :cond_0

    goto :goto_1

    .line 112
    :cond_0
    iget-object p1, p1, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->listeners:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v1, v1, 0x1

    check-cast v2, Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;

    .line 113
    iget-object v3, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;->viewOnPostDraw:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;

    invoke-virtual {v3, v2}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->addOnPostDrawListener(Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V

    goto :goto_0

    :cond_1
    :goto_1
    return-void
.end method

.method private static attachToRoot(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;
    .locals 5

    .line 71
    iget-object v0, p1, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->rootState:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    if-eqz v0, :cond_0

    return-object v0

    .line 75
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getRootView()Landroid/view/View;

    move-result-object v0

    const/4 v1, 0x0

    if-eq p0, v0, :cond_4

    .line 81
    instance-of p0, v0, Landroid/view/ViewGroup;

    if-nez p0, :cond_1

    return-object v1

    .line 85
    :cond_1
    check-cast v0, Landroid/view/ViewGroup;

    .line 87
    invoke-static {v0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->getRootState(Landroid/view/ViewGroup;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    move-result-object p0

    if-nez p0, :cond_3

    .line 89
    new-instance p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;

    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {p0, v1}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;-><init>(Landroid/content/Context;)V

    .line 91
    new-instance v1, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    invoke-direct {v1, p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;-><init>(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;)V

    .line 93
    sget v2, Lorg/telegram/messenger/R$id;->tag_view_on_post_draw_root_state:I

    invoke-virtual {v0, v2, v1}, Landroid/view/View;->setTag(ILjava/lang/Object;)V

    .line 95
    instance-of v2, v0, Landroid/widget/FrameLayout;

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    .line 96
    new-instance v2, Landroid/widget/FrameLayout$LayoutParams;

    const/16 v4, 0x11

    invoke-direct {v2, v3, v3, v4}, Landroid/widget/FrameLayout$LayoutParams;-><init>(III)V

    invoke-virtual {v0, p0, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_0

    .line 98
    :cond_2
    new-instance v2, Landroid/view/ViewGroup$LayoutParams;

    invoke-direct {v2, v3, v3}, Landroid/view/ViewGroup$LayoutParams;-><init>(II)V

    invoke-virtual {v0, p0, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    :goto_0
    move-object p0, v1

    .line 102
    :cond_3
    iput-object p0, p1, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->rootState:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    return-object p0

    .line 78
    :cond_4
    const-string p0, "Cannot add OnPostDrawListener to root view"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method

.method private static detachAllListeners(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V
    .locals 6

    .line 118
    iget-object v0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->rootState:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    if-nez v0, :cond_0

    return-void

    .line 123
    :cond_0
    iget-object v1, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->listeners:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v2

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    invoke-virtual {v1, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    check-cast v4, Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;

    .line 124
    iget-object v5, v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;->viewOnPostDraw:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;

    invoke-virtual {v5, v4}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->removeOnPostDrawListener(Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 127
    iput-object v0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->rootState:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    return-void
.end method

.method private static getRootState(Landroid/view/ViewGroup;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;
    .locals 1

    .line 135
    sget v0, Lorg/telegram/messenger/R$id;->tag_view_on_post_draw_root_state:I

    invoke-virtual {p0, v0}, Landroid/view/View;->getTag(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    return-object p0
.end method

.method private static getViewState(Landroid/view/View;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;
    .locals 1

    .line 131
    sget v0, Lorg/telegram/messenger/R$id;->tag_view_on_post_draw_state:I

    invoke-virtual {p0, v0}, Landroid/view/View;->getTag(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;

    return-object p0
.end method

.method public static removeListener(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V
    .locals 2

    if-eqz p0, :cond_3

    if-nez p1, :cond_0

    goto :goto_0

    .line 53
    :cond_0
    invoke-static {p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->getViewState(Landroid/view/View;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 54
    iget-object v1, v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->listeners:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    .line 58
    :cond_1
    iget-object v1, v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->rootState:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    if-eqz v1, :cond_2

    .line 59
    iget-object v1, v1, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;->viewOnPostDraw:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;

    invoke-virtual {v1, p1}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;->removeOnPostDrawListener(Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V

    .line 62
    :cond_2
    iget-object p1, v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->listeners:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_3

    const/4 p1, 0x0

    .line 63
    iput-object p1, v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->rootState:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;

    .line 65
    iget-object v0, v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->attachStateChangeListener:Landroid/view/View$OnAttachStateChangeListener;

    invoke-virtual {p0, v0}, Landroid/view/View;->removeOnAttachStateChangeListener(Landroid/view/View$OnAttachStateChangeListener;)V

    .line 66
    sget v0, Lorg/telegram/messenger/R$id;->tag_view_on_post_draw_state:I

    invoke-virtual {p0, v0, p1}, Landroid/view/View;->setTag(ILjava/lang/Object;)V

    :cond_3
    :goto_0
    return-void
.end method
