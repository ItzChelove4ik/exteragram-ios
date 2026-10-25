.class public final synthetic Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/core/util/Consumer;


# instance fields
.field public final synthetic f$0:Landroid/content/SharedPreferences;


# direct methods
.method public synthetic constructor <init>(Landroid/content/SharedPreferences;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda5;->f$0:Landroid/content/SharedPreferences;

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda5;->f$0:Landroid/content/SharedPreferences;

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    invoke-static {p0, p1}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;->$r8$lambda$75FvA-8Wx6_ammvo-Vdr0N3hTJI(Landroid/content/SharedPreferences;Z)V

    return-void
.end method
