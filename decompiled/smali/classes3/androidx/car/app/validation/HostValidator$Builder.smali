.class public final Landroidx/car/app/validation/HostValidator$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/validation/HostValidator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field private final mAllowedHosts:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation
.end field

.field private final mContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .line 358
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 354
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Landroidx/car/app/validation/HostValidator$Builder;->mAllowedHosts:Ljava/util/Map;

    .line 359
    iput-object p1, p0, Landroidx/car/app/validation/HostValidator$Builder;->mContext:Landroid/content/Context;

    return-void
.end method

.method private cleanUp(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 428
    sget-object p0, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {p1, p0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    const-string p1, " "

    const-string v0, ""

    invoke-virtual {p0, p1, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public addAllowedHost(Ljava/lang/String;Ljava/lang/String;)Landroidx/car/app/validation/HostValidator$Builder;
    .locals 2

    .line 377
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 378
    invoke-static {p2}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 379
    iget-object v0, p0, Landroidx/car/app/validation/HostValidator$Builder;->mAllowedHosts:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/List;

    if-nez v0, :cond_0

    .line 381
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 382
    iget-object v1, p0, Landroidx/car/app/validation/HostValidator$Builder;->mAllowedHosts:Ljava/util/Map;

    invoke-interface {v1, p1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 384
    :cond_0
    invoke-interface {v0, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-object p0
.end method

.method public addAllowedHosts(I)Landroidx/car/app/validation/HostValidator$Builder;
    .locals 8
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "MissingGetterMatchingBuilder"
        }
    .end annotation

    .line 403
    iget-object v0, p0, Landroidx/car/app/validation/HostValidator$Builder;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 404
    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    .line 409
    array-length p1, v0

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, p1, :cond_1

    aget-object v4, v0, v3

    .line 411
    const-string v5, ","

    const/4 v6, -0x1

    invoke-virtual {v4, v5, v6}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v5

    .line 412
    array-length v6, v5

    const/4 v7, 0x2

    if-ne v6, v7, :cond_0

    const/4 v4, 0x1

    .line 416
    aget-object v4, v5, v4

    invoke-direct {p0, v4}, Landroidx/car/app/validation/HostValidator$Builder;->cleanUp(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    aget-object v5, v5, v2

    invoke-direct {p0, v5}, Landroidx/car/app/validation/HostValidator$Builder;->cleanUp(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p0, v4, v5}, Landroidx/car/app/validation/HostValidator$Builder;->addAllowedHost(Ljava/lang/String;Ljava/lang/String;)Landroidx/car/app/validation/HostValidator$Builder;

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 413
    :cond_0
    const-string p0, "Invalid allowed host entry: \'"

    const-string p1, "\'"

    invoke-static {p0, v4, p1}, Lcom/sun/jna/Native$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v1

    :cond_1
    return-object p0

    .line 406
    :cond_2
    const-string p0, "Invalid allowlist res id: "

    invoke-static {p0, p1}, Lkotlin/CharCodeKt$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;I)V

    return-object v1
.end method

.method public build()Landroidx/car/app/validation/HostValidator;
    .locals 3

    .line 424
    new-instance v0, Landroidx/car/app/validation/HostValidator;

    iget-object v1, p0, Landroidx/car/app/validation/HostValidator$Builder;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    iget-object p0, p0, Landroidx/car/app/validation/HostValidator$Builder;->mAllowedHosts:Ljava/util/Map;

    const/4 v2, 0x0

    invoke-direct {v0, v1, p0, v2}, Landroidx/car/app/validation/HostValidator;-><init>(Landroid/content/pm/PackageManager;Ljava/util/Map;Z)V

    return-object v0
.end method
