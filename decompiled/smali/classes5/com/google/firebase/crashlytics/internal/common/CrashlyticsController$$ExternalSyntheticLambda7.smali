.class public final synthetic Lcom/google/firebase/crashlytics/internal/common/CrashlyticsController$$ExternalSyntheticLambda7;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/function/Function;


# direct methods
.method public synthetic constructor <init>()V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    check-cast p1, Ljava/io/File;

    invoke-static {p1}, Lcom/google/firebase/crashlytics/internal/common/CrashlyticsController;->$r8$lambda$x6EyoEY6_xUqHU0usyLcw6xr-24(Ljava/io/File;)Ljava/lang/Integer;

    move-result-object p0

    return-object p0
.end method
