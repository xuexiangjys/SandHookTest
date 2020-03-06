/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
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

package com.xuexiang.sandhooktest.fragment;

import android.util.Log;

import com.xuexiang.sandhooktest.core.BaseSimpleListFragment;
import com.xuexiang.sandhooktest.core.entity.JNIApi;
import com.xuexiang.sandhooktest.core.entity.TestClass;
import com.xuexiang.xpage.annotation.Page;

import java.util.List;

/**
 * 这个只是一个空壳Fragment，只是用于演示而已
 *
 * @author xuexiang
 * @since 2019-07-08 00:52
 */
@Page(name = "Hook测试")
public class TestFragment extends BaseSimpleListFragment {

    @Override
    protected List<String> initSimpleData(List<String> lists) {
        lists.add("测试Hook Log.w(tag, message)方法");
        lists.add("测试Hook Object的toString方法");
        lists.add("测试Hook TestClass的构造方法");
        lists.add("测试Hook TestClass的静态方法");
        lists.add("测试Hook TestClass的异常方法");
        lists.add("测试Hook JNI方法");
        return lists;
    }

    @Override
    protected void onItemClick(int position) {
        switch(position) {
            case 0:
                //这里只Hook了 Log.w 方法
                Log.w("xuexiang", "这是Log.w测试的内容");
                Log.i("xuexiang", "这是Log.i日志内容");
                break;
            case 1:
                //只能Hook Object的toString父类方法
                new TestClass(12, "哈哈", 12.45F).toString();
                break;
            case 2:
                //Hook TestClass的构造方法
                new TestClass(12, "哈哈", 12.45F);
                break;
            case 3:
                //Hook TestClass的静态方法
                int result = TestClass.staticMethodHook(1, 2);
                Log.e("xuexiang", "result = " + result);
                break;
            case 4:
                new TestClass().errorMethod();
                break;
            case 5:
                new JNIApi().stringFromJNI();
                break;
            default:
                break;
        }
    }
}
