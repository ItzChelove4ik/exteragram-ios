.class final synthetic Lcom/google/android/recaptcha/internal/zzlk;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# static fields
.field static final synthetic zza:Lcom/google/android/recaptcha/internal/zzlk;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzlk;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzlk;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzlk;->zza:Lcom/google/android/recaptcha/internal/zzlk;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlo;

    sget-object p0, Lcom/google/android/recaptcha/internal/zzlm;->zza:Lcom/google/android/recaptcha/internal/zzll;

    .line 1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzlo;->zzc()I

    move-result p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
