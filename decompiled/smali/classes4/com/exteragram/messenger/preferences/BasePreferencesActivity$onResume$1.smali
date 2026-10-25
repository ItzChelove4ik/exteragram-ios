.class public final Lcom/exteragram/messenger/preferences/BasePreferencesActivity$onResume$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/Components/Bulletin$Delegate;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onResume()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016\u00a8\u0006\u0006"
    }
    d2 = {
        "com/exteragram/messenger/preferences/BasePreferencesActivity$onResume$1",
        "Lorg/telegram/ui/Components/Bulletin$Delegate;",
        "getTopOffset",
        "",
        "tag",
        "getBottomOffset",
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


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$onResume$1;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    .line 268
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getBottomOffset(I)I
    .locals 0

    .line 271
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$onResume$1;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getBottomInset()I

    move-result p0

    return p0
.end method

.method public getTopOffset(I)I
    .locals 0

    .line 270
    sget p0, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    invoke-static {}, Lorg/telegram/ui/ActionBar/ActionBar;->getCurrentActionBarHeight()I

    move-result p1

    add-int/2addr p0, p1

    return p0
.end method
