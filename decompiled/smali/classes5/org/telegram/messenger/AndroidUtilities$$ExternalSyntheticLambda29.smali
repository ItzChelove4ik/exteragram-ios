.class public final synthetic Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/proxy/ProxySettings;

.field public final synthetic f$1:Lcom/exteragram/messenger/proxy/ProxyController;

.field public final synthetic f$2:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

.field public final synthetic f$3:Landroid/app/Activity;

.field public final synthetic f$4:Ljava/lang/Runnable;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/proxy/ProxySettings;Lcom/exteragram/messenger/proxy/ProxyController;Lorg/telegram/messenger/SharedConfig$ProxyInfo;Landroid/app/Activity;Ljava/lang/Runnable;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$0:Lorg/telegram/utils/proxy/ProxySettings;

    iput-object p2, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$1:Lcom/exteragram/messenger/proxy/ProxyController;

    iput-object p3, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$2:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    iput-object p4, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$3:Landroid/app/Activity;

    iput-object p5, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$4:Ljava/lang/Runnable;

    return-void
.end method


# virtual methods
.method public final onClick(Landroid/view/View;)V
    .locals 6

    .line 0
    iget-object v0, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$0:Lorg/telegram/utils/proxy/ProxySettings;

    iget-object v1, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$1:Lcom/exteragram/messenger/proxy/ProxyController;

    iget-object v2, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$2:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    iget-object v3, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$3:Landroid/app/Activity;

    iget-object v4, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda29;->f$4:Ljava/lang/Runnable;

    move-object v5, p1

    invoke-static/range {v0 .. v5}, Lorg/telegram/messenger/AndroidUtilities;->$r8$lambda$AXWadX_SG4DsFFzs-oANjNwcQcI(Lorg/telegram/utils/proxy/ProxySettings;Lcom/exteragram/messenger/proxy/ProxyController;Lorg/telegram/messenger/SharedConfig$ProxyInfo;Landroid/app/Activity;Ljava/lang/Runnable;Landroid/view/View;)V

    return-void
.end method
