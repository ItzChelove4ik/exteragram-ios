.class final synthetic Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$1;
.super Lkotlin/jvm/internal/FunctionReferenceImpl;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;-><init>()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1019
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/FunctionReferenceImpl;",
        "Lkotlin/jvm/functions/Function0<",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    k = 0x3
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# direct methods
.method public constructor <init>(Ljava/lang/Object;)V
    .locals 7

    const-string v5, "updateMD3Styles()V"

    const/4 v6, 0x0

    const/4 v1, 0x0

    const-class v3, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;

    const-string v4, "updateMD3Styles"

    move-object v0, p0

    move-object v2, p1

    invoke-direct/range {v0 .. v6}, Lkotlin/jvm/internal/FunctionReferenceImpl;-><init>(ILjava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 113
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$1;->invoke()V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public final invoke()V
    .locals 0

    .line 113
    iget-object p0, p0, Lkotlin/jvm/internal/CallableReference;->receiver:Ljava/lang/Object;

    check-cast p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->access$updateMD3Styles(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    return-void
.end method
