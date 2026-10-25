.class Lorg/telegram/messenger/car/HomeScreen$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/model/TabTemplate$TabCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/telegram/messenger/car/HomeScreen;->onGetTemplate()Landroidx/car/app/model/Template;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/telegram/messenger/car/HomeScreen;


# direct methods
.method public constructor <init>(Lorg/telegram/messenger/car/HomeScreen;)V
    .locals 0

    .line 114
    iput-object p1, p0, Lorg/telegram/messenger/car/HomeScreen$1;->this$0:Lorg/telegram/messenger/car/HomeScreen;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onTabSelected(Ljava/lang/String;)V
    .locals 1

    .line 117
    iget-object v0, p0, Lorg/telegram/messenger/car/HomeScreen$1;->this$0:Lorg/telegram/messenger/car/HomeScreen;

    invoke-static {v0, p1}, Lorg/telegram/messenger/car/HomeScreen;->-$$Nest$fputactiveTabId(Lorg/telegram/messenger/car/HomeScreen;Ljava/lang/String;)V

    .line 118
    iget-object p0, p0, Lorg/telegram/messenger/car/HomeScreen$1;->this$0:Lorg/telegram/messenger/car/HomeScreen;

    invoke-virtual {p0}, Landroidx/car/app/Screen;->invalidate()V

    return-void
.end method
