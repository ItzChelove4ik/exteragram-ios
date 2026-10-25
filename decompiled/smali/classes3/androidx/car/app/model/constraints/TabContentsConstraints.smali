.class public Landroidx/car/app/model/constraints/TabContentsConstraints;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final API_7:Landroidx/car/app/model/constraints/TabContentsConstraints;

.field public static final DEFAULT:Landroidx/car/app/model/constraints/TabContentsConstraints;


# instance fields
.field private mAllowedTemplateTypes:Ljava/util/HashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashSet<",
            "Ljava/lang/Class<",
            "+",
            "Landroidx/car/app/model/Template;",
            ">;>;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 7

    .line 48
    new-instance v0, Landroidx/car/app/model/constraints/TabContentsConstraints;

    const-class v1, Landroidx/car/app/model/MessageTemplate;

    const-class v2, Landroidx/car/app/model/SearchTemplate;

    const-class v3, Landroidx/car/app/model/ListTemplate;

    const-class v4, Landroidx/car/app/model/PaneTemplate;

    const-class v5, Landroidx/car/app/model/GridTemplate;

    filled-new-array {v3, v4, v5, v1, v2}, [Ljava/lang/Class;

    move-result-object v1

    .line 49
    invoke-static {v1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v1

    invoke-direct {v0, v1}, Landroidx/car/app/model/constraints/TabContentsConstraints;-><init>(Ljava/util/List;)V

    sput-object v0, Landroidx/car/app/model/constraints/TabContentsConstraints;->DEFAULT:Landroidx/car/app/model/constraints/TabContentsConstraints;

    .line 59
    new-instance v0, Landroidx/car/app/model/constraints/TabContentsConstraints;

    const-class v5, Landroidx/car/app/model/SearchTemplate;

    const-class v6, Landroidx/car/app/navigation/model/NavigationTemplate;

    const-class v1, Landroidx/car/app/model/ListTemplate;

    const-class v2, Landroidx/car/app/model/PaneTemplate;

    const-class v3, Landroidx/car/app/model/GridTemplate;

    const-class v4, Landroidx/car/app/model/MessageTemplate;

    filled-new-array/range {v1 .. v6}, [Ljava/lang/Class;

    move-result-object v1

    .line 60
    invoke-static {v1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v1

    invoke-direct {v0, v1}, Landroidx/car/app/model/constraints/TabContentsConstraints;-><init>(Ljava/util/List;)V

    sput-object v0, Landroidx/car/app/model/constraints/TabContentsConstraints;->API_7:Landroidx/car/app/model/constraints/TabContentsConstraints;

    return-void
.end method

.method private constructor <init>(Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/Class<",
            "+",
            "Landroidx/car/app/model/Template;",
            ">;>;)V"
        }
    .end annotation

    .line 83
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 84
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0, p1}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    iput-object v0, p0, Landroidx/car/app/model/constraints/TabContentsConstraints;->mAllowedTemplateTypes:Ljava/util/HashSet;

    return-void
.end method


# virtual methods
.method public validateOrThrow(Landroidx/car/app/model/Template;)V
    .locals 1

    .line 77
    iget-object p0, p0, Landroidx/car/app/model/constraints/TabContentsConstraints;->mAllowedTemplateTypes:Ljava/util/HashSet;

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    return-void

    .line 79
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object p0

    const-string p1, "Type is not allowed in tabs: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
