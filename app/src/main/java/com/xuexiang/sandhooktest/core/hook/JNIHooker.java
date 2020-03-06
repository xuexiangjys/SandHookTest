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

import android.os.Build;
import android.util.Log;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.SkipParamCheck;
import com.swift.sandhook.annotation.ThisObject;
import com.xuexiang.sandhooktest.core.entity.JNIApi;
import com.xuexiang.sandhooktest.utils.XToastUtils;

import java.lang.reflect.Method;

/**
 * 对JNI方法进行 Hook
 *
 * @author xuexiang
 * @since 2020/3/6 4:02 PM
 */
@HookClass(JNIApi.class)
public class JNIHooker {

    @HookMethodBackup("stringFromJNI")
    @SkipParamCheck
    static Method backup;

    @HookMethod("stringFromJNI")
    public static String JniMethodHook(@ThisObject JNIApi thiz) throws Throwable {
        String result = (String) SandHook.callOriginByBackup(backup, thiz);
        String content = "jni method hooked success :" + result;
        Log.e("JniHooker", content);
        XToastUtils.toast(content);
        return result;
    }
}
