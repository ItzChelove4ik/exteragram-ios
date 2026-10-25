.class public final synthetic Landroidx/lifecycle/LifecycleRegistry$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field public final synthetic f$0:Landroidx/lifecycle/LifecycleRegistry;

.field public final synthetic f$1:Landroidx/lifecycle/LifecycleOwner;


# direct methods
.method public synthetic constructor <init>(Landroidx/lifecycle/LifecycleRegistry;Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/lifecycle/LifecycleRegistry$$ExternalSyntheticLambda2;->f$0:Landroidx/lifecycle/LifecycleRegistry;

    iput-object p2, p0, Landroidx/lifecycle/LifecycleRegistry$$ExternalSyntheticLambda2;->f$1:Landroidx/lifecycle/LifecycleOwner;

    return-void
.end method


# virtual methods
.method public final invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/lifecycle/LifecycleRegistry$$ExternalSyntheticLambda2;->f$0:Landroidx/lifecycle/LifecycleRegistry;

    iget-object p0, p0, Landroidx/lifecycle/LifecycleRegistry$$ExternalSyntheticLambda2;->f$1:Landroidx/lifecycle/LifecycleOwner;

    check-cast p1, Ljava/util/Map$Entry;

    invoke-static {v0, p0, p1}, Landroidx/lifecycle/LifecycleRegistry;->$r8$lambda$sIRNHHyujojK-OxRELgp4WNMal8(Landroidx/lifecycle/LifecycleRegistry;Landroidx/lifecycle/LifecycleOwner;Ljava/util/Map$Entry;)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method
