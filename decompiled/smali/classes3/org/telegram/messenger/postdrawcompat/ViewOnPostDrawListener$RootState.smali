.class final Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "RootState"
.end annotation


# instance fields
.field final viewOnPostDraw:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;


# direct methods
.method public constructor <init>(Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;)V
    .locals 0

    .line 168
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 169
    iput-object p1, p0, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener$RootState;->viewOnPostDraw:Lorg/telegram/messenger/postdrawcompat/ViewOnPostDraw;

    return-void
.end method
