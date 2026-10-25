.class public final enum Lcom/google/android/recaptcha/internal/zzami;
.super Ljava/lang/Enum;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzafz;


# static fields
.field public static final enum zza:Lcom/google/android/recaptcha/internal/zzami;

.field public static final enum zzb:Lcom/google/android/recaptcha/internal/zzami;

.field public static final enum zzc:Lcom/google/android/recaptcha/internal/zzami;

.field public static final enum zzd:Lcom/google/android/recaptcha/internal/zzami;

.field public static final enum zze:Lcom/google/android/recaptcha/internal/zzami;

.field public static final enum zzf:Lcom/google/android/recaptcha/internal/zzami;

.field public static final enum zzg:Lcom/google/android/recaptcha/internal/zzami;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation
.end field

.field public static final enum zzh:Lcom/google/android/recaptcha/internal/zzami;

.field public static final enum zzi:Lcom/google/android/recaptcha/internal/zzami;

.field public static final enum zzj:Lcom/google/android/recaptcha/internal/zzami;

.field public static final enum zzk:Lcom/google/android/recaptcha/internal/zzami;

.field private static final synthetic zzm:[Lcom/google/android/recaptcha/internal/zzami;


# instance fields
.field private final zzl:I


# direct methods
.method static constructor <clinit>()V
    .locals 14

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzami;

    const-string v1, "JS_CODE_UNSPECIFIED"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2, v2}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzami;->zza:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzami;

    const-string v2, "JS_CODE_SUCCESS"

    const/4 v3, 0x1

    .line 2
    invoke-direct {v1, v2, v3, v3}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v1, Lcom/google/android/recaptcha/internal/zzami;->zzb:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzami;

    const-string v3, "JS_NETWORK_ERROR"

    const/4 v4, 0x2

    .line 3
    invoke-direct {v2, v3, v4, v4}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v2, Lcom/google/android/recaptcha/internal/zzami;->zzc:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v3, Lcom/google/android/recaptcha/internal/zzami;

    const-string v4, "JS_INTERNAL_ERROR"

    const/4 v5, 0x3

    .line 4
    invoke-direct {v3, v4, v5, v5}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v3, Lcom/google/android/recaptcha/internal/zzami;->zzd:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v4, Lcom/google/android/recaptcha/internal/zzami;

    const-string v5, "JS_INVALID_SITE_KEY"

    const/4 v6, 0x4

    .line 5
    invoke-direct {v4, v5, v6, v6}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v4, Lcom/google/android/recaptcha/internal/zzami;->zze:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v5, Lcom/google/android/recaptcha/internal/zzami;

    const-string v6, "JS_INVALID_SITE_KEY_TYPE"

    const/4 v7, 0x5

    .line 6
    invoke-direct {v5, v6, v7, v7}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v5, Lcom/google/android/recaptcha/internal/zzami;->zzf:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v6, Lcom/google/android/recaptcha/internal/zzami;

    const-string v7, "JS_3P_APP_PACKAGE_NAME_NOT_ALLOWED"

    const/4 v8, 0x6

    .line 7
    invoke-direct {v6, v7, v8, v8}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v6, Lcom/google/android/recaptcha/internal/zzami;->zzg:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v7, Lcom/google/android/recaptcha/internal/zzami;

    const-string v8, "JS_INVALID_ACTION"

    const/4 v9, 0x7

    .line 8
    invoke-direct {v7, v8, v9, v9}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v7, Lcom/google/android/recaptcha/internal/zzami;->zzh:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v8, Lcom/google/android/recaptcha/internal/zzami;

    const-string v9, "JS_THIRD_PARTY_APP_PACKAGE_NAME_NOT_ALLOWED"

    const/16 v10, 0x8

    .line 9
    invoke-direct {v8, v9, v10, v10}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v8, Lcom/google/android/recaptcha/internal/zzami;->zzi:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v9, Lcom/google/android/recaptcha/internal/zzami;

    const-string v10, "JS_PROGRAM_ERROR"

    const/16 v11, 0x9

    .line 10
    invoke-direct {v9, v10, v11, v11}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v9, Lcom/google/android/recaptcha/internal/zzami;->zzj:Lcom/google/android/recaptcha/internal/zzami;

    new-instance v10, Lcom/google/android/recaptcha/internal/zzami;

    const/16 v11, 0xa

    const/4 v12, -0x1

    .line 11
    const-string v13, "UNRECOGNIZED"

    invoke-direct {v10, v13, v11, v12}, Lcom/google/android/recaptcha/internal/zzami;-><init>(Ljava/lang/String;II)V

    sput-object v10, Lcom/google/android/recaptcha/internal/zzami;->zzk:Lcom/google/android/recaptcha/internal/zzami;

    filled-new-array/range {v0 .. v10}, [Lcom/google/android/recaptcha/internal/zzami;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzami;->zzm:[Lcom/google/android/recaptcha/internal/zzami;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput p3, p0, Lcom/google/android/recaptcha/internal/zzami;->zzl:I

    return-void
.end method

.method public static values()[Lcom/google/android/recaptcha/internal/zzami;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzami;->zzm:[Lcom/google/android/recaptcha/internal/zzami;

    invoke-virtual {v0}, [Lcom/google/android/recaptcha/internal/zzami;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/google/android/recaptcha/internal/zzami;

    return-object v0
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzami;->zzl:I

    invoke-static {p0}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
