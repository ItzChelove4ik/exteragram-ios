.class public final synthetic Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field public final synthetic f$0:Lkotlin/reflect/KMutableProperty0;


# direct methods
.method public synthetic constructor <init>(Lkotlin/reflect/KMutableProperty0;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda1;->f$0:Lkotlin/reflect/KMutableProperty0;

    return-void
.end method


# virtual methods
.method public final invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda1;->f$0:Lkotlin/reflect/KMutableProperty0;

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    invoke-static {p0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->$r8$lambda$Wm1qktTcUdWT7W6MQ8u_rRLcscg(Lkotlin/reflect/KMutableProperty0;Z)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method
