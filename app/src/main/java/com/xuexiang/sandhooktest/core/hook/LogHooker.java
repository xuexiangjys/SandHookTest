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

import android.util.Log;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.Param;
import com.swift.sandhook.annotation.SkipParamCheck;
import com.xuexiang.sandhooktest.utils.XToastUtils;

import java.lang.reflect.Method;

/**
 * Hook 日志 静态方法
 *
 * @author xuexiang
 * @since 2020/3/6 1:31 AM
 */
@HookClass(Log.class)
public class LogHooker {

    @HookMethodBackup("w")
    @SkipParamCheck
    static Method logWBackup;

    @HookMethod("w")
    public static int logHook(String tag, @Param("java.lang.String") Object msg) throws Throwable {
        String content = "hooked Log.w success! tag:" + tag + ", msg:" + msg;
        Log.e("LogHooker", content);
        XToastUtils.toast(content);
        return (int) SandHook.callOriginByBackup(logWBackup, null, tag, msg);
    }

}
