.class public final synthetic Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/core/util/Consumer;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/preferences/SwitchGroup$Child;


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda5;->f$0:Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda5;->f$0:Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    invoke-static {p0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->$r8$lambda$e8_Wb2zIsFTiLBfPR7uY6g-swZ0(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;Z)V

    return-void
.end method
