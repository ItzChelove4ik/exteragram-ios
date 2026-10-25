.class public final synthetic Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;


# instance fields
.field public final synthetic f$0:Lorg/telegram/ui/LaunchActivity;

.field public final synthetic f$1:Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/ui/LaunchActivity;Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda2;->f$0:Lorg/telegram/ui/LaunchActivity;

    iput-object p2, p0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda2;->f$1:Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;

    return-void
.end method


# virtual methods
.method public final onClick(Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda2;->f$0:Lorg/telegram/ui/LaunchActivity;

    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda2;->f$1:Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;

    invoke-static {v0, p0, p1, p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->$r8$lambda$WeeawD0afuH0k272RT_v_fiH5FA(Lorg/telegram/ui/LaunchActivity;Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method
