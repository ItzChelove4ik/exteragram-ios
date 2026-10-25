.class public final Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDonateClick$1;
.super Lcom/exteragram/messenger/components/BoostyBottomSheet;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->handleDonateClick(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0008\u0010\u0002\u001a\u00020\u0003H\u0014\u00a8\u0006\u0004"
    }
    d2 = {
        "com/exteragram/messenger/preferences/OtherPreferencesActivity$handleDonateClick$1",
        "Lcom/exteragram/messenger/components/BoostyBottomSheet;",
        "onButtonClick",
        "",
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
.field final synthetic $donate:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;

.field final synthetic this$0:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;Landroid/app/Activity;Ljava/util/ArrayList;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;",
            "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;",
            "Landroid/app/Activity;",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDonateClick$1;->this$0:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;

    iput-object p2, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDonateClick$1;->$donate:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;

    .line 296
    invoke-direct {p0, p3, p4}, Lcom/exteragram/messenger/components/BoostyBottomSheet;-><init>(Landroid/content/Context;Ljava/util/List;)V

    return-void
.end method


# virtual methods
.method public onButtonClick()V
    .locals 1

    .line 297
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDonateClick$1;->this$0:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    iget-object p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDonateClick$1;->$donate:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getDetails()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method
