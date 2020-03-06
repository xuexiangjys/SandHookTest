# SandHookTest

[SandHook](https://github.com/ganyao114/SandHook)框架使用演示，用于Android Hook。

## 特点

* 主要基于 inline hook，条件不允许时进行 ArtMethod 入口替换
* 接口为注解式，较为友好
* 支持直接 Hook，也支持在插件场景中的 Hook

## 支持 OS

4.4 - 10.0

## 支持架构

* ARM32
* Thumb-2
* ARM64

## Hook范围

* 对象方法
* Static 方法
* 构造方法
* JNI 方法
* 系统方法


## 使用方法

### 增加依赖

```
    implementation 'com.swift.sandhook:hooklib:4.1.7'
```

### 定义Hook类

```
/**
 * Hook Activity 对象方法
 *
 * @author xuexiang
 * @since 2020/3/5 9:38 PM
 */
@HookClass(Activity.class)
public class ActivityHooker {

    @HookMethodBackup("onCreate")
    @MethodParams(Bundle.class)
    static Method onCreateBackup;

    @HookMethodBackup("onPause")
    static HookWrapper.HookEntity onPauseBackup;

    @HookMethodBackup("onResume")
    static HookWrapper.HookEntity onResumeBackup;

    @HookMethod("onCreate")
    @MethodParams(Bundle.class)
    public static void onCreateHook(Activity activity, Bundle bundle) throws Throwable {
        Log.e("ActivityHooker", "hooked onCreate success " + activity);
        SandHook.callOriginByBackup(onCreateBackup, activity, bundle);
    }

    @HookMethod("onPause")
    public static void onPauseHook(@ThisObject Activity activity) throws Throwable {
        Log.e("ActivityHooker", "hooked onPause success " + activity);
        onPauseBackup.callOrigin(activity);
    }


    @HookMethod("onResume")
    public static void onResumeHook(@ThisObject Activity activity) throws Throwable {
        Log.e("ActivityHooker", "hooked onResume success " + activity);
        onResumeBackup.callOrigin(activity);
    }

}
```


### 注册Hook类


```
private void initSandHook() {
    try {
        SandHook.addHookClass(
                ActivityHooker.class,
                LogHooker.class,
                ObjectHooker.class,
                ClassMethodHooker.class,
                JNIHooker.class
        );
    } catch (HookErrorException e) {
        e.printStackTrace();
    }
}
```


## 混淆配置

```
# SandHook
-keep class com.swift.sandhook.** { *; }
-keep @com.swift.sandhook.annotation.* class * {*;}
-keep class * {
    @com.swift.sandhook.annotation.* <fields>;
}
-keepclassmembers class * {
    @com.swift.sandhook.annotation.* <methods>;
}



## 这里填你需要Hook的类
-keep class com.xuexiang.sandhooktest.core.entity.** {*;}
```
