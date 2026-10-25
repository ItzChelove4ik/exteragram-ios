.class final enum Lcom/google/android/recaptcha/internal/zzrj;
.super Ljava/lang/Enum;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzrk;


# static fields
.field public static final enum zza:Lcom/google/android/recaptcha/internal/zzrj;

.field private static final synthetic zzb:[Lcom/google/android/recaptcha/internal/zzrj;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzrj;

    const-string v1, "INSTANCE"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzrj;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzrj;->zza:Lcom/google/android/recaptcha/internal/zzrj;

    filled-new-array {v0}, [Lcom/google/android/recaptcha/internal/zzrj;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzrj;->zzb:[Lcom/google/android/recaptcha/internal/zzrj;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0

    .line 1
    const-string p1, "INSTANCE"

    const/4 p2, 0x0

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static values()[Lcom/google/android/recaptcha/internal/zzrj;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzrj;->zzb:[Lcom/google/android/recaptcha/internal/zzrj;

    invoke-virtual {v0}, [Lcom/google/android/recaptcha/internal/zzrj;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/google/android/recaptcha/internal/zzrj;

    return-object v0
.end method


# virtual methods
.method public final synthetic zza([BI)J
    .locals 19

    invoke-virtual/range {p0 .. p0}, Ljava/lang/Enum;->ordinal()I

    move-result v0

    if-nez v0, :cond_0

    add-int/lit8 v0, p2, 0x7

    .line 1
    aget-byte v0, p1, v0

    add-int/lit8 v1, p2, 0x6

    aget-byte v1, p1, v1

    add-int/lit8 v2, p2, 0x5

    aget-byte v2, p1, v2

    add-int/lit8 v3, p2, 0x4

    aget-byte v3, p1, v3

    add-int/lit8 v4, p2, 0x3

    aget-byte v4, p1, v4

    add-int/lit8 v5, p2, 0x2

    aget-byte v5, p1, v5

    add-int/lit8 v6, p2, 0x1

    aget-byte v6, p1, v6

    aget-byte v7, p1, p2

    int-to-long v8, v0

    int-to-long v0, v1

    int-to-long v10, v2

    int-to-long v2, v3

    int-to-long v12, v4

    int-to-long v4, v5

    int-to-long v14, v6

    int-to-long v6, v7

    const-wide/16 v16, 0xff

    and-long v8, v8, v16

    and-long v0, v0, v16

    and-long v10, v10, v16

    and-long v2, v2, v16

    and-long v12, v12, v16

    and-long v4, v4, v16

    and-long v14, v14, v16

    const/16 v18, 0x38

    shl-long v8, v8, v18

    const/16 v18, 0x30

    shl-long v0, v0, v18

    or-long/2addr v0, v8

    const/16 v8, 0x28

    shl-long v8, v10, v8

    or-long/2addr v0, v8

    const/16 v8, 0x20

    shl-long/2addr v2, v8

    or-long/2addr v0, v2

    const/16 v2, 0x18

    shl-long v2, v12, v2

    or-long/2addr v0, v2

    const/16 v2, 0x10

    shl-long v2, v4, v2

    or-long/2addr v0, v2

    const/16 v2, 0x8

    shl-long v2, v14, v2

    or-long/2addr v0, v2

    and-long v2, v6, v16

    or-long/2addr v0, v2

    return-wide v0

    :cond_0
    const/4 v0, 0x0

    .line 0
    throw v0
.end method
