/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.sandhooktest.core.hook;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.MethodParams;
import com.swift.sandhook.annotation.ThisObject;
import com.swift.sandhook.wrapper.HookWrapper;

import java.lang.reflect.Method;

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
