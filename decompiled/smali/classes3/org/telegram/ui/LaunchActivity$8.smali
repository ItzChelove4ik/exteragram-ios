.class Lorg/telegram/ui/LaunchActivity$8;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/window/OnBackAnimationCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/telegram/ui/LaunchActivity;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field private drawerPredictiveBackStarted:Z

.field private invoked:Z

.field private locked:Z

.field private locker:Lorg/telegram/messenger/AnimationNotificationsLocker;

.field private predictiveBackStarted:Z

.field private started:Z

.field final synthetic this$0:Lorg/telegram/ui/LaunchActivity;


# direct methods
.method public constructor <init>(Lorg/telegram/ui/LaunchActivity;)V
    .locals 0

    .line 796
    iput-object p1, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 797
    new-instance p1, Lorg/telegram/messenger/AnimationNotificationsLocker;

    invoke-direct {p1}, Lorg/telegram/messenger/AnimationNotificationsLocker;-><init>()V

    iput-object p1, p0, Lorg/telegram/ui/LaunchActivity$8;->locker:Lorg/telegram/messenger/AnimationNotificationsLocker;

    const/4 p1, 0x0

    .line 800
    iput-boolean p1, p0, Lorg/telegram/ui/LaunchActivity$8;->started:Z

    .line 801
    iput-boolean p1, p0, Lorg/telegram/ui/LaunchActivity$8;->invoked:Z

    .line 802
    iput-boolean p1, p0, Lorg/telegram/ui/LaunchActivity$8;->drawerPredictiveBackStarted:Z

    return-void
.end method

.method private onBackStartedInternal(Landroid/window/BackEvent;)V
    .locals 3

    .line 836
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->isTablet()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 837
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lorg/telegram/ui/LaunchActivity;->onBackPressed(Z)Z

    move-result v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 838
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object v0, v0, Lorg/telegram/ui/LaunchActivity;->drawerLayoutContainer:Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;

    const/4 v1, 0x1

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;->getDrawerContainer()Lcom/exteragram/messenger/drawer/DrawerContainer;

    move-result-object v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object v0, v0, Lorg/telegram/ui/LaunchActivity;->drawerLayoutContainer:Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;

    .line 839
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;->getDrawerContainer()Lcom/exteragram/messenger/drawer/DrawerContainer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/drawer/DrawerContainer;->startPredictiveBack()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 840
    iput-boolean v1, p0, Lorg/telegram/ui/LaunchActivity$8;->drawerPredictiveBackStarted:Z

    return-void

    .line 843
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object v0, v0, Lorg/telegram/ui/LaunchActivity;->actionBarLayout:Lorg/telegram/ui/ActionBar/ActionBarLayout;

    if-eqz v0, :cond_3

    .line 844
    invoke-virtual {p1}, Landroid/window/BackEvent;->getTouchX()F

    move-result v2

    invoke-virtual {p1}, Landroid/window/BackEvent;->getTouchY()F

    move-result p1

    invoke-virtual {v0, v2, p1}, Lorg/telegram/ui/ActionBar/ActionBarLayout;->onBackStarted(FF)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 845
    iget-boolean p1, p0, Lorg/telegram/ui/LaunchActivity$8;->locked:Z

    if-nez p1, :cond_3

    .line 846
    iget-object p1, p0, Lorg/telegram/ui/LaunchActivity$8;->locker:Lorg/telegram/messenger/AnimationNotificationsLocker;

    invoke-virtual {p1}, Lorg/telegram/messenger/AnimationNotificationsLocker;->lock()V

    .line 847
    iput-boolean v1, p0, Lorg/telegram/ui/LaunchActivity$8;->locked:Z

    :cond_3
    :goto_0
    return-void
.end method


# virtual methods
.method public onBackCancelled()V
    .locals 2

    const/4 v0, 0x0

    .line 878
    iput-boolean v0, p0, Lorg/telegram/ui/LaunchActivity$8;->started:Z

    .line 879
    iput-boolean v0, p0, Lorg/telegram/ui/LaunchActivity$8;->invoked:Z

    .line 880
    iget-boolean v1, p0, Lorg/telegram/ui/LaunchActivity$8;->locked:Z

    if-eqz v1, :cond_0

    .line 881
    iget-object v1, p0, Lorg/telegram/ui/LaunchActivity$8;->locker:Lorg/telegram/messenger/AnimationNotificationsLocker;

    invoke-virtual {v1}, Lorg/telegram/messenger/AnimationNotificationsLocker;->unlock()V

    .line 882
    iput-boolean v0, p0, Lorg/telegram/ui/LaunchActivity$8;->locked:Z

    .line 885
    :cond_0
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->isTablet()Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_0

    .line 886
    :cond_1
    iget-boolean v1, p0, Lorg/telegram/ui/LaunchActivity$8;->drawerPredictiveBackStarted:Z

    if-eqz v1, :cond_2

    iget-object v1, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object v1, v1, Lorg/telegram/ui/LaunchActivity;->drawerLayoutContainer:Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;

    if-eqz v1, :cond_2

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;->getDrawerContainer()Lcom/exteragram/messenger/drawer/DrawerContainer;

    move-result-object v1

    if-eqz v1, :cond_2

    .line 887
    iget-object v1, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object v1, v1, Lorg/telegram/ui/LaunchActivity;->drawerLayoutContainer:Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;->getDrawerContainer()Lcom/exteragram/messenger/drawer/DrawerContainer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/drawer/DrawerContainer;->cancelPredictiveBack()V

    .line 888
    iput-boolean v0, p0, Lorg/telegram/ui/LaunchActivity$8;->drawerPredictiveBackStarted:Z

    return-void

    .line 889
    :cond_2
    iget-object p0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object p0, p0, Lorg/telegram/ui/LaunchActivity;->actionBarLayout:Lorg/telegram/ui/ActionBar/ActionBarLayout;

    if-eqz p0, :cond_3

    .line 890
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBarLayout;->onBackCancelled()V

    :cond_3
    :goto_0
    return-void
.end method

.method public onBackInvoked()V
    .locals 3

    const/4 v0, 0x1

    .line 806
    iput-boolean v0, p0, Lorg/telegram/ui/LaunchActivity$8;->invoked:Z

    .line 807
    iget-boolean v1, p0, Lorg/telegram/ui/LaunchActivity$8;->locked:Z

    if-eqz v1, :cond_0

    .line 808
    iget-object v1, p0, Lorg/telegram/ui/LaunchActivity$8;->locker:Lorg/telegram/messenger/AnimationNotificationsLocker;

    invoke-virtual {v1}, Lorg/telegram/messenger/AnimationNotificationsLocker;->unlock()V

    const/4 v1, 0x0

    .line 809
    iput-boolean v1, p0, Lorg/telegram/ui/LaunchActivity$8;->locked:Z

    .line 812
    :cond_0
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->isTablet()Z

    move-result v1

    .line 816
    iget-object v2, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    if-eqz v1, :cond_1

    .line 813
    invoke-virtual {v2}, Lorg/telegram/ui/LaunchActivity;->onBackPressed()V

    return-void

    .line 816
    :cond_1
    invoke-virtual {v2, v0}, Lorg/telegram/ui/LaunchActivity;->onBackPressed(Z)Z

    move-result v0

    if-nez v0, :cond_2

    return-void

    .line 818
    :cond_2
    iget-boolean v0, p0, Lorg/telegram/ui/LaunchActivity$8;->drawerPredictiveBackStarted:Z

    if-eqz v0, :cond_3

    iget-object v0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object v0, v0, Lorg/telegram/ui/LaunchActivity;->drawerLayoutContainer:Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;->getDrawerContainer()Lcom/exteragram/messenger/drawer/DrawerContainer;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 819
    iget-object p0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object p0, p0, Lorg/telegram/ui/LaunchActivity;->drawerLayoutContainer:Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;->getDrawerContainer()Lcom/exteragram/messenger/drawer/DrawerContainer;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/drawer/DrawerContainer;->commitPredictiveBack()V

    return-void

    .line 820
    :cond_3
    iget-object p0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object v0, p0, Lorg/telegram/ui/LaunchActivity;->actionBarLayout:Lorg/telegram/ui/ActionBar/ActionBarLayout;

    if-eqz v0, :cond_4

    .line 821
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/ActionBarLayout;->onBackInvoked()V

    return-void

    .line 823
    :cond_4
    invoke-virtual {p0}, Lorg/telegram/ui/LaunchActivity;->onBackPressed()V

    return-void
.end method

.method public onBackProgressed(Landroid/window/BackEvent;)V
    .locals 3

    .line 858
    iget-boolean v0, p0, Lorg/telegram/ui/LaunchActivity$8;->started:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lorg/telegram/ui/LaunchActivity$8;->invoked:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 860
    :cond_0
    invoke-virtual {p1}, Landroid/window/BackEvent;->getProgress()F

    move-result v0

    .line 861
    iget-boolean v1, p0, Lorg/telegram/ui/LaunchActivity$8;->predictiveBackStarted:Z

    const v2, 0x3c75c28f    # 0.015f

    if-nez v1, :cond_1

    cmpl-float v1, v0, v2

    if-lez v1, :cond_1

    const/4 v1, 0x1

    .line 862
    iput-boolean v1, p0, Lorg/telegram/ui/LaunchActivity$8;->predictiveBackStarted:Z

    .line 863
    invoke-direct {p0, p1}, Lorg/telegram/ui/LaunchActivity$8;->onBackStartedInternal(Landroid/window/BackEvent;)V

    :cond_1
    const/4 v1, 0x0

    sub-float/2addr v0, v2

    .line 866
    invoke-static {v1, v0}, Ljava/lang/Math;->max(FF)F

    move-result v0

    const v1, 0x3f7c28f6    # 0.985f

    div-float/2addr v0, v1

    .line 868
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->isTablet()Z

    move-result v1

    if-eqz v1, :cond_2

    goto :goto_0

    .line 869
    :cond_2
    iget-boolean v1, p0, Lorg/telegram/ui/LaunchActivity$8;->drawerPredictiveBackStarted:Z

    if-eqz v1, :cond_3

    iget-object v1, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object v1, v1, Lorg/telegram/ui/LaunchActivity;->drawerLayoutContainer:Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;

    if-eqz v1, :cond_3

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;->getDrawerContainer()Lcom/exteragram/messenger/drawer/DrawerContainer;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 870
    iget-object p0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object p0, p0, Lorg/telegram/ui/LaunchActivity;->drawerLayoutContainer:Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/DrawerLayoutContainer;->getDrawerContainer()Lcom/exteragram/messenger/drawer/DrawerContainer;

    move-result-object p0

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/drawer/DrawerContainer;->updatePredictiveBackProgress(F)V

    return-void

    .line 871
    :cond_3
    iget-object p0, p0, Lorg/telegram/ui/LaunchActivity$8;->this$0:Lorg/telegram/ui/LaunchActivity;

    iget-object p0, p0, Lorg/telegram/ui/LaunchActivity;->actionBarLayout:Lorg/telegram/ui/ActionBar/ActionBarLayout;

    if-eqz p0, :cond_4

    .line 872
    invoke-virtual {p1}, Landroid/window/BackEvent;->getTouchY()F

    move-result p1

    invoke-virtual {p0, v0, p1}, Lorg/telegram/ui/ActionBar/ActionBarLayout;->onBackProgress(FF)V

    :cond_4
    :goto_0
    return-void
.end method

.method public onBackStarted(Landroid/window/BackEvent;)V
    .locals 0

    const/4 p1, 0x1

    .line 829
    iput-boolean p1, p0, Lorg/telegram/ui/LaunchActivity$8;->started:Z

    const/4 p1, 0x0

    .line 830
    iput-boolean p1, p0, Lorg/telegram/ui/LaunchActivity$8;->invoked:Z

    .line 831
    iput-boolean p1, p0, Lorg/telegram/ui/LaunchActivity$8;->predictiveBackStarted:Z

    .line 832
    iput-boolean p1, p0, Lorg/telegram/ui/LaunchActivity$8;->drawerPredictiveBackStarted:Z

    return-void
.end method
