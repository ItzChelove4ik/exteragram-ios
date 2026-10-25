.class public final enum Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/httpflags/ResolvedFlags$Value;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "Type"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

.field public static final enum BOOL:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

.field public static final enum BYTES:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

.field public static final enum FLOAT:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

.field public static final enum INT:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

.field public static final enum STRING:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;


# direct methods
.method private static synthetic $values()[Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;
    .locals 5

    .line 32
    sget-object v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->BOOL:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    sget-object v1, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->INT:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    sget-object v2, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->FLOAT:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    sget-object v3, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->STRING:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    sget-object v4, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->BYTES:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    filled-new-array {v0, v1, v2, v3, v4}, [Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 33
    new-instance v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    const-string v1, "BOOL"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->BOOL:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    .line 34
    new-instance v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    const-string v1, "INT"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->INT:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    .line 35
    new-instance v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    const-string v1, "FLOAT"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->FLOAT:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    .line 36
    new-instance v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    const-string v1, "STRING"

    const/4 v2, 0x3

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->STRING:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    .line 37
    new-instance v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    const-string v1, "BYTES"

    const/4 v2, 0x4

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->BYTES:Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    .line 32
    invoke-static {}, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->$values()[Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    move-result-object v0

    sput-object v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->$VALUES:[Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 32
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;
    .locals 1

    .line 32
    const-class v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    return-object p0
.end method

.method public static values()[Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;
    .locals 1

    .line 32
    sget-object v0, Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->$VALUES:[Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    invoke-virtual {v0}, [Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/chromium/net/httpflags/ResolvedFlags$Value$Type;

    return-object v0
.end method
