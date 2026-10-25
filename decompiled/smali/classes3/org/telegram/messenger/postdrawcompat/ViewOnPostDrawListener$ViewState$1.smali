.class Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnAttachStateChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;


# direct methods
.method public constructor <init>(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V
    .locals 0

    .line 145
    iput-object p1, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState$1;->this$0:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onViewAttachedToWindow(Landroid/view/View;)V
    .locals 0

    .line 148
    invoke-static {p1}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->-$$Nest$smgetViewState(Landroid/view/View;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 150
    invoke-static {p1, p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->-$$Nest$smattachAllListeners(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V

    :cond_0
    return-void
.end method

.method public onViewDetachedFromWindow(Landroid/view/View;)V
    .locals 0

    .line 156
    invoke-static {p1}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->-$$Nest$smgetViewState(Landroid/view/View;)Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 158
    invoke-static {p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->-$$Nest$smdetachAllListeners(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V

    :cond_0
    return-void
.end method
