.class final Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ViewState"
.end annotation


# instance fields
.field final attachStateChangeListener:Landroid/view/View$OnAttachStateChangeListener;

.field final listeners:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;",
            ">;"
        }
    .end annotation
.end field

.field rootState:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;


# direct methods
.method private constructor <init>()V
    .locals 1

    .line 138
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 140
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->listeners:Ljava/util/ArrayList;

    .line 144
    new-instance v0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState$1;

    invoke-direct {v0, p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState$1;-><init>(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;)V

    iput-object v0, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;->attachStateChangeListener:Landroid/view/View$OnAttachStateChangeListener;

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$ViewState;-><init>()V

    return-void
.end method
