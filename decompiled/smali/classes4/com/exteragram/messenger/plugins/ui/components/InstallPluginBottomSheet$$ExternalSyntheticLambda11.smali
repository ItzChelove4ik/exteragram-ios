.class public final synthetic Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda11;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Ljava/lang/String;

.field public final synthetic f$1:Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;

.field public final synthetic f$2:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda11;->f$0:Ljava/lang/String;

    iput-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda11;->f$1:Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;

    iput-object p3, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda11;->f$2:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda11;->f$0:Ljava/lang/String;

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda11;->f$1:Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda11;->f$2:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;

    invoke-static {v0, v1, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->$r8$lambda$S6yUHpGKTwONSUPNmjtxj1U2NIs(Ljava/lang/String;Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    return-void
.end method
