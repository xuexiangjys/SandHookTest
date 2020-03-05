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
import com.swift.sandhook.annotation.HookMode;
import com.swift.sandhook.annotation.SkipParamCheck;
import com.swift.sandhook.annotation.ThisObject;
import com.xuexiang.sandhooktest.core.entity.TestClass;
import com.xuexiang.sandhooktest.utils.XToastUtils;

import java.lang.reflect.Method;

/**
 * 主要Hook TestClass的方法
 *
 * @author xuexiang
 * @since 2020/3/6 2:11 AM
 */
@HookClass(TestClass.class)
public class ClassMethodHooker {

    /**
     * 构造方法Hook
     */
    @HookMethodBackup
    @SkipParamCheck
    static Method constructionMethodBackup;


    @HookMethod
    public static void onConstructionHook(@ThisObject TestClass thiz, int a, String b, float c) throws Throwable {
        String content = "TestClass(a, b, c) been hooked！ a:" + a + ", b:" + b + ", c:" + c;
        Log.e("TestClassHook", content);
        XToastUtils.toast(content);
        SandHook.callOriginByBackup(constructionMethodBackup, thiz, a, b, c);
    }




}